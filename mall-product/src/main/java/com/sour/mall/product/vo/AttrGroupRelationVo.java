package com.sour.mall.product.vo;

import lombok.Data;

/**
 *
 *
 * @author xgl
 * @date 2021/4/3 16:38
 **/
@Data
public class AttrGroupRelationVo {

    /**
     *  [{attrId: 1, attrGroupId: 1}]
     */

    /**
     *  属性id
     */
    private Long attrId;

    /**
     *  属性分组id
     */
    private Long attrGroupId;
}
