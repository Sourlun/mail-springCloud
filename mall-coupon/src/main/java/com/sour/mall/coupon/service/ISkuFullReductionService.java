package com.sour.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.to.SkuReductionTo;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:39:23
 */
public interface ISkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存 sku的优惠信息
     *
     * @author xgl
     * @date 2021/4/11 16:17
     **/
    void saveSkuReduction(SkuReductionTo skuReductionTo);
}

