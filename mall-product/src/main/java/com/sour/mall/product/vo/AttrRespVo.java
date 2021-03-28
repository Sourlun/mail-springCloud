package com.sour.mall.product.vo;


import com.sour.mall.product.entity.AttrEntity;
import lombok.Data;

/**
 * 商品属性VO
 *
 * @author xgl
 * @date 2021/3/28 20:31
 **/
@Data
public class AttrRespVo extends AttrEntity {

    /**
     *  所属分类名称
     */
    private String catelogName;

    /**
     *  所属分组信息
     */
    private String groupName;


}
