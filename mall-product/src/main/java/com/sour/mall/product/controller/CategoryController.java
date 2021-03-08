package com.sour.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sour.mall.product.entity.CategoryEntity;
import com.sour.mall.product.service.ICategoryService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.R;



/**
 * 商品三级分类
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

   /**
    * 列表 - 所有分类以及子分类, 已树形结构组装起来
    * @path product/category/list/tree
    *
    * @author xgl
    * @date 2021/2/21 20:05
    **/
    @RequestMapping("/list/tree")
    public R list(){

        List<CategoryEntity> list = categoryService.listTree();

        return R.ok().put("data", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     *  @RequestMapping 获取请求体, 所以需要发送POST请求
     *  SpringMVC字段将请求体的数据(json), 转为对应的对象
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){

        categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
