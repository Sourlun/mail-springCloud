package com.sour.mall.product.dao;

import com.sour.mall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-03-27 15:57:53
 */
@Mapper
public interface IAttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    /**
     * 批量删除关联关系
     *
     * @author xgl
     * @date 2021/4/3 16:52
     **/
    void batchDeleteRelation(@Param("entities") List<AttrAttrgroupRelationEntity> relationEntities);
}
