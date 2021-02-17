package com.sour.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.coupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:39:23
 */
public interface ICouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

