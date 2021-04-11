package com.sour.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.sour.mall.product.vo.AttrRespVo;
import com.sour.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sour.mall.product.entity.AttrEntity;
import com.sour.mall.product.service.IAttrService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.R;



/**
 * 商品属性
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private IAttrService attrService;

    /**
     *
     *
     * @author xgl
     * @date 2021/3/28 20:00
     **/
    @GetMapping(value = "{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable Long catelogId,
                          @PathVariable(value = "attrType") String type) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, type);
        return R.ok().put("page", page);
    }


    /**
     *
     *
     * @author xgl
     * @date 2021/4/3 15:18
     **/
    @RequestMapping("/update")
    public R updateAttr(@RequestBody AttrRespVo attrRespVo){
        attrService.updateAttr(attrRespVo);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
		AttrRespVo vo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", vo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
