package com.sour.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sour.mall.common.valid.AddGroup;
import com.sour.mall.common.valid.UpdateGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sour.mall.product.entity.BrandEntity;
import com.sour.mall.product.service.IBrandService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.R;


/**
 * 品牌
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();

        // 获取 key 用来模糊查询
        String key = (String) params.get("key");
        if ( StringUtils.isNotEmpty(key) ) {
            wrapper.eq("brand_id", key).or().like("name", key).or().like("descript", key);
        }

        PageUtils page = brandService.queryPage(params, wrapper);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    // @RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

//    /**
//     * 保存  (手动处理校验异常)
//     * @Valid 告诉这个entity需要校验
//     * @param result BindingResult获取校验结果
//     */
//    @RequestMapping("/save")
//    // @RequiresPermissions("product:brand:save")
//    public R save(@Valid @RequestBody BrandEntity brand, BindingResult result){
//        // 校验不通过
//        if (result.hasErrors()) {
//            return R.notValid(result);
//        }
//        try {
//            brandService.save(brand);
//            return R.ok();
//        } catch ( Exception e ) {
//            e.printStackTrace();
//            return R.error(e.getMessage());
//        }
//    }


    /**
     * 保存
     * @Valid 告诉这个entity需要校验
     */
    @RequestMapping("/save")
    // @RequiresPermissions("product:brand:save")
    public R save(@Validated(value = AddGroup.class) @RequestBody BrandEntity brand){
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("product:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateDetail(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
