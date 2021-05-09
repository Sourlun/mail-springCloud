package com.sour.mall.product.dao;

import com.sour.mall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@Mapper
public interface ISpuInfoDao extends BaseMapper<SpuInfoEntity> {

    /**
     * 修改spu状态
     *
     * @author xgl
     * @date 2021/5/9 19:57
     **/
    void updateSpuStatus(Long spuId, int code);

}
