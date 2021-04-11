package com.sour.mall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.product.dao.ISpuImagesDao;
import com.sour.mall.product.entity.SpuImagesEntity;
import com.sour.mall.product.service.ISpuImagesService;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<ISpuImagesDao, SpuImagesEntity> implements ISpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Long spuId, List<String> images) {
        if ( null != images && images.size() > 0 ) {
           List<SpuImagesEntity> spuImagesEntities = images.stream().map(item -> {
               SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
               spuImagesEntity.setSpuId(spuId);
               spuImagesEntity.setImgUrl(item);
               return spuImagesEntity;
           }).collect(Collectors.toList());
           saveBatch(spuImagesEntities);
        }
    }

}