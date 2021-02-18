package com.sour.mall.member.feign;

import com.sour.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author xgl
 * @date 2021/2/18 13:09
 **/
@FeignClient("mall-coupon")
public interface ICouponFeignService {

    /**
     *  列表 - 会员拥有的优惠券
     * @path coupon/coupon/couponListOfMember
     *
     * @return R
     *
     * @author xgl
     * @date 2021/2/18 13:03
     **/
    @RequestMapping("coupon/coupon/couponListOfMember")
    R couponListOfMember();
}
