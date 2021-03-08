package com.sour.mall.thirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class MallThirdPartyApplicationTests {

    @Autowired
    OSS ossClient;

    /**
     *  阿里云文件上传 新
     *   说明: https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample
     *         spring cloud-mall 62个视频有解释
     *
     * @author xgl
     * @date 2021/3/7 12:55
     **/
    @Test
    void uploadNew() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\Users\\SourLun\\Pictures\\20181120141017_37530.jpg");
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。  Bucket:存储空间
        ossClient.putObject("sour-mall", "20181120141017_37530.jpg", inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功!");
    }

}
