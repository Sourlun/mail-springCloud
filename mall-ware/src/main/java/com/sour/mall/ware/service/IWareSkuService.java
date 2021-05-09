package com.sour.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.to.SkuHasStockTo;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:19:53
 */
public interface IWareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     *
     *
     * @author xgl
     * @date 2021/5/9 17:39
     **/
    List<SkuHasStockTo> getSkuHasStock(List<Long> skuIds);
}

