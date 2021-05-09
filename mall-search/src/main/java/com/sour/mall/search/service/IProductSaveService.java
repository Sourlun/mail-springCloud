package com.sour.mall.search.service;

import com.sour.mall.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface IProductSaveService {

    /**
     *
     *
     * @author xgl
     * @date 2021/5/9 18:29
     *
     * @return*/
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
