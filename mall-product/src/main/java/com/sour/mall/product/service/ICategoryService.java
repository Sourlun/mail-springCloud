package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface ICategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 列表 - 所有分类以及子分类, 已树形结构组装起来
     *
     * @return 分类以及子分类
     *
     * @author xgl
     * @date 2021/2/21 20:07
     **/
    List<CategoryEntity> listTree();

    /**
     * 删除 - 通过多个id删除菜单
     *  带校验
     *
     * @author xgl
     * @date 2021/2/28 11:53
     **/
    void removeMenuByIds(List<Long> ids);
}

