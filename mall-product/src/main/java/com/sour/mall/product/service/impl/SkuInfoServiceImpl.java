package com.sour.mall.product.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.product.dao.ISkuInfoDao;
import com.sour.mall.product.entity.SkuInfoEntity;
import com.sour.mall.product.service.ISkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<ISkuInfoDao, SkuInfoEntity> implements ISkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and( w -> w.eq("sku_id", key).or().like("sku_name", key));
        }

        String catelogId = (String) params.get("catalogId");
        if (StringUtils.isNotBlank(catelogId) && !"0".equals(catelogId)) {
            wrapper.eq("catalog_id", key);
        }

        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !"0".equals(brandId)) {
            wrapper.eq("brand_id", brandId);
        }

        String min = (String) params.get("min");
        if (StringUtils.isNotBlank(min)) {
            BigDecimal bigDecimal = new BigDecimal(min);
            if ( bigDecimal.compareTo(new BigDecimal(0)) == 1 ) {
                wrapper.ge("price", min);
            }
        }

        String max = (String) params.get("max");
        if (StringUtils.isNotBlank(max)) {
            BigDecimal bigDecimal = new BigDecimal(max);
            if ( bigDecimal.compareTo(new BigDecimal(0)) == 1 ) {
                wrapper.le("price", max);
            }
        }


        IPage<SkuInfoEntity> page = page(new Query<SkuInfoEntity>().getPage(params), wrapper);
        return  new PageUtils(page);
    }

}