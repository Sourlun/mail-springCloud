package com.sour.mall.order.dao;

import com.sour.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:09:55
 */
@Mapper
public interface IOrderDao extends BaseMapper<OrderEntity> {
	
}
