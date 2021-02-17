package com.sour.mall.order.dao;

import com.sour.mall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:09:55
 */
@Mapper
public interface IRefundInfoDao extends BaseMapper<RefundInfoEntity> {
	
}
