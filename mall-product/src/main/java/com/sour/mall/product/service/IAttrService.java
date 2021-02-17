package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface IAttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

