package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface ISkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存 sku的信息
     *
     * @author xgl
     * @date 2021/4/5 13:23
     **/
    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    /**
     * sku列表
     *
     * @author xgl
     * @date 2021/4/17 17:41
     **/
    PageUtils queryPageByCondition(Map<String, Object> params);
}

