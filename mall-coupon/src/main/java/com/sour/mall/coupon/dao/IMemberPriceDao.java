package com.sour.mall.coupon.dao;

import com.sour.mall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:39:23
 */
@Mapper
public interface IMemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}
