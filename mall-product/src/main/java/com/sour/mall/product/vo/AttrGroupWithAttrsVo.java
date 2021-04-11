package com.sour.mall.product.vo;

import com.sour.mall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * 属性分组 和 属性
 *
 * @author xgl
 * @date 2021/4/4 17:25
 **/
@Data
public class AttrGroupWithAttrsVo {

    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    /**
     * 属性
     */
    private List<AttrEntity> attrs;
}
