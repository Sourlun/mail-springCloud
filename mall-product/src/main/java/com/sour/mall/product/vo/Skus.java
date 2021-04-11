/**
  * Copyright 2021 json.cn 
  */
package com.sour.mall.product.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2021-04-04 18:1:16
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class Skus {

    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> descar;
    private int fullCount;
    private int discount;
    private BigDecimal countStatus;
    private BigDecimal fullPrice;
    private int reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;

}