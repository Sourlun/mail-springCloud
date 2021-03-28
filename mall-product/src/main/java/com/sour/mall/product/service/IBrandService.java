package com.sour.mall.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface IBrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params, QueryWrapper<BrandEntity> wrapper);

    /**
     * 更新  保证冗余字段的一致
     *
     * @author xgl
     * @date 2021/3/28 16:36
     **/
    void updateDetail(BrandEntity brand);
}

