package com.sour.mall.product.service.impl;

import com.sour.mall.product.entity.AttrAttrgroupRelationEntity;
import com.sour.mall.product.entity.AttrEntity;
import com.sour.mall.product.service.IAttrAttrgroupRelationService;
import com.sour.mall.product.service.IAttrService;
import com.sour.mall.product.vo.AttrGroupRelationVo;
import com.sour.mall.product.vo.AttrGroupWithAttrsVo;
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

import com.sour.mall.product.dao.IAttrGroupDao;
import com.sour.mall.product.entity.AttrGroupEntity;
import com.sour.mall.product.service.IAttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<IAttrGroupDao, AttrGroupEntity> implements IAttrGroupService {

    @Autowired
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    private IAttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        if (catelogId == 0) {
            return queryPage(params);
        }
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("catelog_id", catelogId);
        if ( params.containsKey("key") && StringUtils.isNotBlank((String) params.get("key")) ) {
            String key = (String) params.get("key");
            wrapper.and( obj -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }

        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public void deleteRelation(List<AttrGroupRelationVo> vos) {

        // vo转成entity
        List<AttrAttrgroupRelationEntity> relationEntities = vos.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());

        attrAttrgroupRelationService.batchDeleteRelation(relationEntities);

    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        // 查询分组信息
        List<AttrGroupEntity> attrGroupEntities = list(
                new QueryWrapper<AttrGroupEntity>()
                        .eq("catelog_id", catelogId)
        );

        // 查询分组下的属性
        List<AttrGroupWithAttrsVo> attrGroupWithAttrsVos = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo vo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group, vo);
            List<AttrEntity> attrs = attrService.getRelationAttr(vo.getAttrGroupId());
            vo.setAttrs(attrs);
            return vo;
        }).collect(Collectors.toList());

        return attrGroupWithAttrsVos;
    }

}