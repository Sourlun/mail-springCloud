package com.sour.mall.product.service.impl;

import com.sour.mall.common.constant.ProductConstant;
import com.sour.mall.product.dao.*;
import com.sour.mall.product.entity.AttrAttrgroupRelationEntity;
import com.sour.mall.product.entity.AttrGroupEntity;
import com.sour.mall.product.entity.CategoryEntity;
import com.sour.mall.product.service.ICategoryService;
import com.sour.mall.product.vo.AttrRespVo;
import com.sour.mall.product.vo.AttrVo;
import com.sun.deploy.ui.DialogTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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

    @Autowired
    private ICategoryService categoryService;

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
        // 基本类型才有分组
        if ( attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationDao.insert(relationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());
        if ( catelogId != 0 ) {
            // 属性类型[0-销售属性，1-基本属性
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
        if ( null != attrEntities && attrEntities.size() > 0 ) {
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
        }


        return new PageUtils(page);
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {

        AttrRespVo attrRespVo = new AttrRespVo();
        AttrEntity attrEntity = getById(attrId);
        BeanUtils.copyProperties(attrEntity, attrRespVo);

        // 属性-分组--关联表
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id", attrId);
        AttrAttrgroupRelationEntity relationEntity = attrAttrgroupRelationDao.selectOne(wrapper);
        if (null != relationEntity) {
            // 分组详细信息
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
            if (attrGroupEntity != null) {
                attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                attrRespVo.setAttrGroupId(attrGroupEntity.getAttrGroupId());
            }
        }

        // 基本类型才有分组
        if ( attrRespVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            // 设置分类信息
            Long[] catelogPath = categoryService.findCatelogPath(attrEntity.getCatelogId());
            attrRespVo.setCatelogPath(catelogPath);
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if ( categoryEntity != null ) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
        }

        return attrRespVo;
    }

    @Override
    public void updateAttr(AttrRespVo attrRespVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrRespVo, attrEntity);
        updateById(attrEntity);

        // 修改分组关联
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrId(attrRespVo.getAttrId());
        relationEntity.setAttrGroupId(attrRespVo.getAttrGroupId());

        // 基本类型才有分组
        if ( attrRespVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            // 更新
            if ( count > 0 ) {
                attrAttrgroupRelationDao.update(
                        relationEntity,
                        new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId())
                );
            } else {
                attrAttrgroupRelationDao.insert(relationEntity);
            }
        }
    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrGroupId) {

        // 关联关系
        List<AttrAttrgroupRelationEntity> relationEntities = attrAttrgroupRelationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>()
                        .eq("attr_group_id", attrGroupId)
        );
        // 所有属性id
        List<Long> attrIds = relationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());

        if ( null != attrIds && attrIds.size() > 0 ) {
            List<AttrEntity> attrEntities = (List<AttrEntity>) listByIds(attrIds);
            return attrEntities;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public PageUtils getNoAttrRelation(Map<String, Object> params, Long attrGroupId) {

        // 查询当前分组对应的产品id
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
        Long catelogId = attrGroupEntity.getCatelogId();

        // 1, 当前分组只能关联自己所属分组的所有属性
        // 1.1, 相同产品id的其他分组信息
        List<AttrGroupEntity> otherAttrGroupEntities = attrGroupDao.selectList(
                new QueryWrapper<AttrGroupEntity>()
                        .eq("catelog_id", catelogId)
                        .ne("attr_group_id", attrGroupId)
        );
        List<Long> attrGroupIds = otherAttrGroupEntities.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());
        // 1.2 其他分组关联的属性
        List<AttrAttrgroupRelationEntity> otherRelationEntities = attrAttrgroupRelationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>()
                        .in("attr_group_id", attrGroupIds)
        );
        List<Long> attrIds = otherRelationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        // 1.3 从当前分类移除这些属性 (分页查询)
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().notIn("attr_id", attrIds);
        if ( params.containsKey("key") ) {
            String key = (String) params.get("key");
            wrapper.and( w -> {
                w.eq("attr_id", key).or().like("attr_name", key).or().like("icon", key);
            });
        }
        IPage<AttrEntity> attrEntityIPage = page(new Query<AttrEntity>().getPage(params), wrapper);

        PageUtils pageUtils = new PageUtils(attrEntityIPage);
        return pageUtils;

    }

    @Override
    public List<Long> selectSearchAttrIds(List<Long> attrIds) {

        /**
         * SELECT attr_id FROM `pms_attr`WHERE attr_id IN (?) AND search_type = 1
         */

        return baseMapper.selectSearchAttrIds(attrIds);
    }

}