package com.sour.mall.ware.service.impl;

import com.sour.mall.common.to.SkuHasStockTo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.ware.dao.IWareSkuDao;
import com.sour.mall.ware.entity.WareSkuEntity;
import com.sour.mall.ware.service.IWareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<IWareSkuDao, WareSkuEntity> implements IWareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (StringUtils.isNotBlank(skuId)) {
            wrapper.eq("sku_id", skuId);
        }
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotBlank(skuId)) {
            wrapper.eq("ware_id", wareId);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuHasStockTo> getSkuHasStock(List<Long> skuIds) {
        List<SkuHasStockTo> collect = skuIds.stream().map(sku -> {
            SkuHasStockTo skuHasStockVo = new SkuHasStockTo();
            Long count = baseMapper.getSkuStock(sku);
            skuHasStockVo.setSkuId(sku);
            skuHasStockVo.setHasStock(count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return collect;
    }

}