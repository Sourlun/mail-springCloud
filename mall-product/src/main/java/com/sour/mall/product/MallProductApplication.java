package com.sour.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


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
 *          3, 配置分页:
 *              1, 在 MyBatisConfig 有说明
 *
 *  2, 配置逻辑删除
 *      1, 配置全局逻辑删除规则(yml)
 *      2, 配置逻辑删除组件删除 (bean) (3.1版本以上可以省略这步)
 *      3, 实体类加@TableLogic注解
 *
 *  3, JSR303校验  (  示例: BrandController.save()  )
 *      1, 给bean添加校验注解  javax.validation.constraints, 并定义message提示
 *      2, 开启校验功能: 在保存controller接口添加@Valid注解
 *      3, 校验不通过会返回400错误码
 *      4, 在校验参数后面加一个参数: BindingResult, 可以获取到校验的结果
 *
 *  4, 同一的异常处理
 *      1, 创建MallControllerAdvice这个类专门来处理controller异常
 *      2, 在这个类上加@RestControllerAdvice注解, 并指明哪里的异常需要处理
 *      3, 分组校验
 *          1, 添加接口AddGroup, UpdateGroup用来标识
 *          2, 在entity里面的字段设置分组: groups = {AddGroup.class, UpdateGroup.class})
 *          3, 在controller的接口把@Valid改成@Validated, 并指定校验分组
 *          4, 没有指定分组的字段, 校验不生效
 *      4, 自定义校验   ( 例子: BrandEntity.showStatus  -> ListValue -> Constraint -> ConstraintValidator -> ListValueConstraintValidator  )
 *          1, 编写一个自定义的校验注解
 *          2, 编写一个自定义的校验器
 *          3, 关联自定义校验器和自定义校验注解
 *
 *
 *
 */
@MapperScan("com.sour.mall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class MallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }

}
