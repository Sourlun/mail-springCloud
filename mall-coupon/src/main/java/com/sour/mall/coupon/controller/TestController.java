package com.sour.mall.coupon.controller;

import com.sour.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021年2月18日 15点55分
 */
@RefreshScope
@RestController
@RequestMapping("coupon/test")
public class TestController {

    @Value("${coupon.user.name}")
    private String name;

    @Value("${coupon.user.age}")
    private Integer age;

    /**
     *  测试 - NacosConfig 配置中心
     * @path coupon/test/testNacosConfig
     *
     * @author xgl
     * @date 2021/2/18 13:03
     **/
    @RequestMapping("/testNacosConfig")
    public R testNacosConfig() {
        return R.ok().put("coupon-name", name).put("coupon-age", age);
    }
}
