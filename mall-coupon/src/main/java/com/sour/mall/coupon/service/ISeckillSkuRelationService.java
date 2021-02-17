package com.sour.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:39:23
 */
public interface ISeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

