package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.AttrAttrgroupRelationEntity;
import com.sour.mall.product.vo.AttrRespVo;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-03-27 15:57:53
 */
public interface IAttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 批量删除
     *
     * @author xgl
     * @date 2021/4/3 16:50
     **/
    void batchDeleteRelation(List<AttrAttrgroupRelationEntity> relationEntities);

    /**
     *  批量新增属性关联
     *
     * @author xgl
     * @date 2021/4/4 16:14
     **/
    void saveBatch(List<AttrRespVo> attrRespVos);
}

