package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.AttrEntity;
import com.sour.mall.product.vo.AttrRespVo;
import com.sour.mall.product.vo.AttrVo;

import java.util.List;
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

    /**
     * 保存
     *
     * @author xgl
     * @date 2021/3/28 17:38
     **/
    void saveAttr(AttrVo attr);

    /**
     * 查询 对应产品分类下面的商品属性
     *
     * @author xgl
     * @date 2021/3/28 20:03
     **/
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    /**
     * 查询 当前规则参数 内容vo
     *
     * @param attrId
     * @return 内容vo
     * @author xgl
     * @date 2021/4/3 14:46
     **/
    AttrRespVo getAttrInfo(Long attrId);

    /**
     * 创建 或者 更新
     *
     * @author xgl
     * @date 2021/4/3 15:19
     **/
    void updateAttr(AttrRespVo attrRespVo);

    /**
     * 当前分组有多少属性
     *
     * @author xgl
     * @date 2021/4/3 16:27
     **/
    List<AttrEntity> getRelationAttr(Long attrGroupId);

    /**
     * 当前分组没有关联的属性
     *
     * @author xgl
     * @date 2021/4/3 17:13
     **/
    PageUtils getNoAttrRelation(Map<String, Object> params, Long attrGroupId);

    /**
     * 在指定的属性集合里面, 挑出检索属性
     *
     * @author xgl
     * @date 2021/5/9 16:59
     **/
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

