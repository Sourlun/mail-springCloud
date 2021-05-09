package com.sour.mall.ware.dao;

import com.sour.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:19:53
 */
@Mapper
public interface IWareSkuDao extends BaseMapper<WareSkuEntity> {

    /**
     *
     *
     * @author xgl
     * @date 2021/5/9 17:47
     **/
    Long getSkuStock(Long skuId);
}
