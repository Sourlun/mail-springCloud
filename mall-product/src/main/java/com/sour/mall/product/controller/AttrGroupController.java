package com.sour.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sour.mall.product.entity.AttrEntity;
import com.sour.mall.product.service.IAttrAttrgroupRelationService;
import com.sour.mall.product.service.IAttrService;
import com.sour.mall.product.service.ICategoryService;
import com.sour.mall.product.vo.AttrGroupRelationVo;
import com.sour.mall.product.vo.AttrGroupWithAttrsVo;
import com.sour.mall.product.vo.AttrRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sour.mall.product.entity.AttrGroupEntity;
import com.sour.mall.product.service.IAttrGroupService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.R;



/**
 * 属性分组
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:11:40
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private IAttrGroupService attrGroupService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IAttrService attrService;
    @Autowired
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 当前分组有多少属性
     *
     * @author xgl
     * @date 2021/4/3 16:26
     **/
    @GetMapping("/{attrGroupId}/attr/relation")
    public R attrRelation(@PathVariable Long attrGroupId) {
        List<AttrEntity> attrEntities =  attrService.getRelationAttr(attrGroupId);
        return R.ok().put("data", attrEntities);
    }

    /**
     * 批量删除关系
     *
     * @author xgl
     * @date 2021/4/3 17:10
     **/
    @PostMapping(value = "/attr/relation/delete")
    public R deleteRelation(@RequestBody List<AttrGroupRelationVo> vos) {
        attrGroupService.deleteRelation(vos);
        return R.ok();
    }

    /**
     * 当前分组没有关联的属性
     *
     * @author xgl
     * @date 2021/4/3 17点12分
     **/
    @GetMapping("/{attrGroupId}/noattr/relation")
    public R noAttrRelation(@RequestParam Map<String, Object> params, @PathVariable Long attrGroupId) {
        PageUtils page =  attrService.getNoAttrRelation(params, attrGroupId);
        return R.ok().put("data", page);
    }


    /**
     * 当前分组没有关联的属性
     *
     * @author xgl
     * @date 2021/4/3 17点12分
     **/
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrRespVo> attrRespVos) {
        attrAttrgroupRelationService.saveBatch(attrRespVos);
        return R.ok();
    }

    // /225/withattr
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {
        // 1, 查出当前分类下的所有属性分组  2,查出每个属性分组的属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data", vos);
    }


    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long catelogId){
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
		//获取产品CatelogId完整路径
        Long catelogId = attrGroup.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(catelogPath);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
