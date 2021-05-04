package com.sour.mall.search;

import com.sour.mall.search.config.MallElasticSearchConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1, 导入依赖
 * 2, 编写配置, {@link MallElasticSearchConfig#esRestClient}
 * 3, 官方API  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html
 *
 * @author xgl
 * @date 2021/5/3 15:35
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class MallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSearchApplication.class, args);
    }

}
