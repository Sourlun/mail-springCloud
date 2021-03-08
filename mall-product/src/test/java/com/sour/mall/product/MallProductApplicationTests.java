package com.sour.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sour.mall.product.entity.BrandEntity;
import com.sour.mall.product.service.IBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    IBrandService brandService;

//    @Autowired
//    OSSClient ossClient;

    @Test
    void create() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("测试6");
        brandEntity.setName("名称6");
        brandEntity.setShowStatus(1);
        brandEntity.setSort(0);
        brandService.save(brandEntity);
        System.out.println("创建成功, " + brandEntity.toString());
    }

    @Test
    void update() {
        BrandEntity brandEntity = brandService.getById(8);
        brandEntity.setName("测试测试");
        brandService.updateById(brandEntity);
        System.out.println("更新成功, " + brandEntity.toString());
    }

    @Test
    void list() {
        // 查询条件
        QueryWrapper<BrandEntity> like = new QueryWrapper<BrandEntity>().like("name", "小");
        List<BrandEntity> list = brandService.list(like);
        list.forEach(item -> {
            System.out.println(item);
        });
        System.out.println("列表完成!");
    }

//    /**
//     *  阿里云文件上传
//     *   说明: https://help.aliyun.com/document_detail/84781.html?spm=a2c4g.11186623.6.958.691a7a74BdLSbm
//     *         spring cloud-mall 62个视频有解释
//     *    spring-cloud-alibaba 有maven版 在下面
//     * @author xgl
//     * @date 2021/3/7 12:55
//     **/
//    @Test
//    void upload() throws FileNotFoundException {
//        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。 获取:https://oss.console.aliyun.com/bucket/oss-cn-guangzhou/sour-mall/overview
//        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
//        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "LTAI4G5WZfgz5UT6b4o6f7FL";
//        String accessKeySecret = "L78uXRhwrqoT3qQ9QQGxzsiKeCsrDX";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        InputStream inputStream = new FileInputStream("D:\\Users\\SourLun\\Pictures\\捕获2.PNG");
//        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。  Bucket:存储空间
//        ossClient.putObject("sour-mall", "捕获2.PNG", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//
//        System.out.println("上传成功!");
//    }
//
//
//    /**
//     *  阿里云文件上传 新
//     *   说明: https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample
//     *         spring cloud-mall 62个视频有解释
//     *
//     * @author xgl
//     * @date 2021/3/7 12:55
//     **/
//    @Test
//    void uploadNew() throws FileNotFoundException {
//        InputStream inputStream = new FileInputStream("D:\\Users\\SourLun\\Pictures\\20181120141017_37530.jpg");
//        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。  Bucket:存储空间
//        ossClient.putObject("sour-mall", "20181120141017_37530.jpg", inputStream);
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        System.out.println("上传成功!");
//    }
}
