package com.sour.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.sour.mall.common.to.es.SkuEsModel;
import com.sour.mall.search.config.MallElasticSearchConfig;
import com.sour.mall.search.constant.EsConstant;
import com.sour.mall.search.service.IProductSaveService;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSaveServiceImpl implements IProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

        // 保存到es
        // 1, 建立好映射关系 product-mapping.txt, 建立好索引关系

        // 2, 给es中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            // 指定索引
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            // 指定id
            indexRequest.id(String.valueOf(skuEsModel.getSkuId()));
            // 指定存储的内容
            String jsonString = JSON.toJSONString(skuEsModel);
            indexRequest.source(jsonString, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }
        // 批量保存
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, MallElasticSearchConfig.COMMON_OPTIONS);

        // TODO 如果批量错误, 后续处理
        List<String> collect = Arrays.asList(bulk.getItems()).stream().map(BulkItemResponse::getId).collect(Collectors.toList());
        boolean b = bulk.hasFailures();

        return b;

    }
}
