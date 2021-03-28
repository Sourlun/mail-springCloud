package com.sour.mall.product.dao;

import com.sour.mall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌分类关联
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-03-27 15:57:53
 */
@Mapper
public interface ICategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    /**
     * 更新分类信息 (用sql)
     *
     * @author xgl
     * @date 2021/3/28 16:53
     **/
    void updateCategory(@Param("catId") Long catId, @Param("catelogName") String catelogName);
}
