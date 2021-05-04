package com.sour.mall.search;

import com.alibaba.fastjson.JSON;
import com.sour.mall.search.config.MallElasticSearchConfig;
import lombok.Data;
import lombok.ToString;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class MallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 存储数据到es
     *  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.12/java-rest-high-document-index.html
     *
     * @author xgl
     * @date 2021/5/4 11:34
     **/
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        // 数据的id
        indexRequest.id("1");
        //设置json字符串
        User user = new User();
        user.setUsername("user1");
        user.setGender("m");
        user.setAge(10);
        String jsonString = JSON.toJSONString(user);
        // 设置要保存的内容
        indexRequest.source(jsonString, XContentType.JSON);

        // 执行操作
        IndexResponse indexResponse = client.index(indexRequest, MallElasticSearchConfig.COMMON_OPTIONS);
        // 提取有用的相应数据
        System.out.println(indexResponse);
    }


    /**
     * 聚合查询
     * *
     *
     *
     * @author xgl
     * @date 2021/5/4 16:25
     **/
    @Test
    public void searchData() throws IOException {
        // 1, 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices("bank");
        // 指定DSL(检索条件)
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 1.1, 构造检索条件
//        sourceBuilder.query();
//        sourceBuilder.from();
//        sourceBuilder.size();
//        sourceBuilder.aggregation();
        sourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));

        // 添加'聚合'条件
        // 1.2, 按照年龄的值分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(ageAgg);
        // 1.3, 计算平均薪资
        AvgAggregationBuilder balanceAgg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAgg);

        System.out.println("检索条件" + sourceBuilder.toString());

        // DSL添加到检索请求
        searchRequest.source(sourceBuilder);

        // 2, 执行检索
        SearchResponse searchResponse = client.search(searchRequest, MallElasticSearchConfig.COMMON_OPTIONS);

        // 3, 结果分析
        System.out.println("结果分析" + searchResponse);
//        Map map = JSON.parseObject(searchResponse.toString(), Map.class);
        // 3.1, 获取所有查到的数据
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        // 总记录数
        long value = totalHits.value;
        // 最大命中
        float maxScore = hits.getMaxScore();
        // 获取命中的记录
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            String string = hit.getSourceAsString();
            Accout accout = JSON.parseObject(string, Accout.class);
            System.out.println("转化的对象:" + accout);
        }

        // 3.2, 获取这次的分析信息
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations.asList()) {
//            System.out.println("当前聚合: " + aggregation.getName());
//        }
        // 年龄聚合
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄:" + keyAsString + ",  有几个人:" + bucket.getDocCount());
        }
        // 平均薪资
        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资: " + balanceAvg1.getValue());
    }
    /*
        GET bank/_search
        {
          "query": {
            "match": {
              "address": {
                "query": "mill"
              }
            }
          },
          "aggregations": {
            "ageAgg": {
              "terms": {
                "field": "age",
                "size": 10
              }
            },
            "balanceAvg": {
              "avg": {
                "field": "balance"
              }
            }
          }
        }
     */

    @Test
    public void contextLoads() {
        System.out.println(client);
    }

    @Data
    class User{
        private String username;
        private String gender;
        private Integer age;
    }


    @Data
    @ToString
    public static class Accout {
        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;
    }

}
