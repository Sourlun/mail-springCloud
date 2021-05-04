package com.sour.mall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch配置
 *
 * @author xgl
 * @date 2021/5/3 15:30
 **/
@Configuration
public class MallElasticSearchConfig {

    /**
     * 通用设置项
     *  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.12/java-rest-low-usage-requests.html#java-rest-low-usage-request-options
     */
    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    /**
     *  给容器中注入一个RestHighLevelClient
     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high-getting-started-initialization.html
     *
     * @author xgl
     * @date 2021/5/3 15:38
     **/
    @Bean
    public RestHighLevelClient esRestClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.0.104", 9200, "http")));
        return client;
    }




}
