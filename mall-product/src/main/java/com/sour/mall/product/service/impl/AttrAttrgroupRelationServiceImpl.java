package com.sour.mall.product.service.impl;

import com.sour.mall.product.vo.AttrRespVo;
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

import com.sour.mall.product.dao.IAttrAttrgroupRelationDao;
import com.sour.mall.product.entity.AttrAttrgroupRelationEntity;
import com.sour.mall.product.service.IAttrAttrgroupRelationService;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<IAttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements IAttrAttrgroupRelationService {

    @Autowired
    private IAttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void batchDeleteRelation(List<AttrAttrgroupRelationEntity> relationEntities) {
        attrAttrgroupRelationDao.batchDeleteRelation(relationEntities);
    }

    @Override
    public void saveBatch(List<AttrRespVo> attrRespVos) {
        // 转成entity
        List<AttrAttrgroupRelationEntity> relationEntities = attrRespVos.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());

        saveBatch(relationEntities);
    }

}