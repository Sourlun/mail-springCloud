package com.sour.mall.ware.dao;

import com.sour.mall.ware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:19:53
 */
@Mapper
public interface IPurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
