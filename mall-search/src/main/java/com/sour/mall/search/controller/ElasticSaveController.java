package com.sour.mall.search.controller;

import com.sour.mall.common.exception.BizCodeEnume;
import com.sour.mall.common.to.es.SkuEsModel;
import com.sour.mall.common.utils.R;
import com.sour.mall.search.service.IProductSaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.List;

/**
 * Elasticsearch保存接口
 *
 * @author xgl
 * @date 2021/5/9 18:25
 **/
@RestController
@RequestMapping(value ="/search/save")
public class ElasticSaveController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    IProductSaveService productSaveService;

    /**
     * 上架商品
     *
     * @author xgl
     * @date 2021/5/9 18:26
     **/
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        boolean b;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            logger.error("ElasticSearch商品上架失败", e);
            return R.error(BizCodeEnume.PRODUCT_UP__EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP__EXCEPTION.getMsg());
        }
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }

    }

}
