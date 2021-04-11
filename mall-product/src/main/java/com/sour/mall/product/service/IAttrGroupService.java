package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.AttrGroupEntity;
import com.sour.mall.product.vo.AttrGroupRelationVo;
import com.sour.mall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface IAttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据 catelogId 分页查询列表
     *
     * @author xgl
     * @date 2021/3/27 16:45
     **/
    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    /**
     * 删除 分组属性关联关系
     *
     * @author xgl
     * @date 2021/4/3 16:41
     **/
    void deleteRelation(List<AttrGroupRelationVo> vos);

    /**
     * 1, 查出当前分类下的所有属性分组  2,查出每个属性分组的属性
     *
     * @author xgl
     * @date 2021/4/4 17:29
     **/
    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
}

