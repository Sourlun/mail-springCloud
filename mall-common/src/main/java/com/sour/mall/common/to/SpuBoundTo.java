package com.sour.mall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 积分信息
 *
 * @author xgl
 * @date 2021/4/11 15:43
 **/
@Data
public class SpuBoundTo {

    private long spuId;

    /**
     * 成长积分
     */
    private BigDecimal growBounds;

    /**
     * 购物积分
     */
    private BigDecimal buyBounds;
}
