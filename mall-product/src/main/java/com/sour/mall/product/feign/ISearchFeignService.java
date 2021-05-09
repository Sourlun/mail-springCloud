package com.sour.mall.product.feign;

import com.sour.mall.common.to.es.SkuEsModel;
import com.sour.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *

 * @author xgl
 * @date 2021/5/9 19:49
 **/
@FeignClient(value = "mall-search")
public interface ISearchFeignService {
    /**
     * 上架商品
     *
     * @author xgl
     * @date 2021/5/9 18:26
     **/
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
