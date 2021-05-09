package com.sour.mall.product.feign;

import com.sour.mall.common.to.SkuHasStockTo;
import com.sour.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("mall-ware")
public interface IWareFeignService {

    /**
     * 查询sku是否有库存
     *
     * @author xgl
     * @date 2021/5/9 17:34
     **/
    @RequestMapping("ware/waresku/sasStock")
    R<List<SkuHasStockTo>> getSkusHasStock(@RequestBody List<Long> skuIds);
}
