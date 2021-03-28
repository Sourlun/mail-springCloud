package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface ICategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存 品牌分类关联
     *
     * @author xgl
     * @date 2021/3/28 16:13
     **/
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新品牌信息
     *
     * @author xgl
     * @date 2021/3/28 16:39
     **/
    void updateBrand(Long brandId, String brandName);

    /**
     * 更新分类信息 (用sql)
     *
     * @author xgl
     * @date 2021/3/28 16:52
     **/
    void updateCategory(Long catId, String name);
}

