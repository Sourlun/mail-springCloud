package com.sour.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  1, 整合MyBatis plus
 *      1, pom (在common里面)
 *      2, 配置 (通过官网教程)
 *          1, 数据源
 *              1, 导入数据库驱动 (在common里面)
 *              2, 在yml配置数据源和相关信息
 *          2, 相关信息
 *              1, 启动类配置 @MapperScan
 *              2, 告诉sql映射文件在哪里 (在yml里面)
 *
 */
@MapperScan("com.sour.mall.product.dao")
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
