package com.sour.mall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sour.mall.common.to.SkuHasStockTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sour.mall.ware.entity.WareSkuEntity;
import com.sour.mall.ware.service.IWareSkuService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.R;



/**
 * 商品库存
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:19:53
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private IWareSkuService wareSkuService;

    /**
     * 查询sku是否有库存
     *
     * @author xgl
     * @date 2021/5/9 17:34
     **/
    @RequestMapping("/sasStock")
    public R<List<SkuHasStockTo>> getSkusHasStock(@RequestBody List<Long> skuIds){
        List<SkuHasStockTo> skuHasStockVos = wareSkuService.getSkuHasStock(skuIds);
        R ok = R.ok();
        ok.setData(skuHasStockVos);
        return ok;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("ware:waresku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("ware:waresku:info")
    public R info(@PathVariable("id") Long id){
		WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("ware:waresku:save")
    public R save(@RequestBody WareSkuEntity wareSku){
		wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("ware:waresku:update")
    public R update(@RequestBody WareSkuEntity wareSku){
		wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("ware:waresku:delete")
    public R delete(@RequestBody Long[] ids){
		wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
