package com.sour.mall.product.feign;

import com.sour.mall.common.to.SkuReductionTo;
import com.sour.mall.common.to.SpuBoundTo;
import com.sour.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 *
 * @author xgl
 * @date 2021/4/11 15:45
 **/
@FeignClient("mall-coupon")
public interface ICouponFeignService {

    /**
     *  保存 积分信息
     *  1, @RequestBody是将对象转成json
     *  2, 找到'mall-coupon'这个服务, 给'coupon/spubounds/save'发请求
     *  3, 对方接受到请求, 请求体有'@RequestBody XXX xxx',
     *      则将json转成相应的对象
     *
     *  只有双方的json模型是兼容的, 双方无需用同一个类
     *
     * @author xgl
     * @date 2021/4/11 15:45
     **/
    @PostMapping("coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    /**
     *  保存 sku的优惠信息
     *
     * @author xgl
     * @date 2021/4/11 16:14
     **/
    @PostMapping("coupon/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
