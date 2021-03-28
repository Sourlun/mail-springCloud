package com.sour.mall.product.service.impl;

import com.sour.mall.product.dao.*;
import com.sour.mall.product.entity.AttrAttrgroupRelationEntity;
import com.sour.mall.product.entity.AttrGroupEntity;
import com.sour.mall.product.entity.CategoryEntity;
import com.sour.mall.product.service.ICategoryBrandRelationService;
import com.sour.mall.product.vo.AttrRespVo;
import com.sour.mall.product.vo.AttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.product.entity.AttrEntity;
import com.sour.mall.product.service.IAttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<IAttrDao, AttrEntity> implements IAttrService {

    @Autowired
    private IAttrGroupDao attrGroupDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    private IAttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        // 1, 保存基本数据
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        save(attrEntity);

        // 2, 保存关联关系
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attr.getAttrGroupId());
        relationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationDao.insert(relationEntity);
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        if ( catelogId != 0 ) {
            wrapper.eq("catelog_id", catelogId);
        }

        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and( wrapperTemp -> {
                wrapperTemp.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);
        List<AttrEntity> attrEntities = page.getRecords();

        // 设置 vo
        List<AttrRespVo> respVos = attrEntities.stream().map(attrEntity -> {
            // 1, 先设置 属性基本信息
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);

            // 2, 获取分组名字  先查 关联表 再查 分组表
            if (null != attrEntity.getAttrId()) {
                AttrAttrgroupRelationEntity relationEntity
                        = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if ( null != relationEntity ) {
                    Long attrGroupId = relationEntity.getAttrGroupId();
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
                    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            // 3, 获取分类名字
            if ( null != attrEntity.getCatelogId() ) {
                CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
                if (null != categoryEntity) {
                    attrRespVo.setCatelogName(categoryEntity.getName());
                }
            }
            return attrRespVo;
        }).collect(Collectors.toList());

        return new PageUtils(page);
    }

}