package com.sour.mall.product.dao;

import com.sour.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@Mapper
public interface ICategoryDao extends BaseMapper<CategoryEntity> {
	
}
