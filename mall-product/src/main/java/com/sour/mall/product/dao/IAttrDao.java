package com.sour.mall.product.dao;

import com.sour.mall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@Mapper
public interface IAttrDao extends BaseMapper<AttrEntity> {

    /**
     * 在指定的属性集合里面, 挑出检索属性
     *
     * @author xgl
     * @date 2021/5/9 17:02
     **/
    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
