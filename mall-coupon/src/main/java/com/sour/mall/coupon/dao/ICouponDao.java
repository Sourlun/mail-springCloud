package com.sour.mall.coupon.dao;

import com.sour.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:39:23
 */
@Mapper
public interface ICouponDao extends BaseMapper<CouponEntity> {
	
}
