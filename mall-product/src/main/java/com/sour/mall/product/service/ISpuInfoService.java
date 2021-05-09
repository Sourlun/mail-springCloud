package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.SpuInfoDescEntity;
import com.sour.mall.product.entity.SpuInfoEntity;
import com.sour.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface ISpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 创建spv
     *
     * @author xgl
     * @date 2021/4/5 11:13
     **/
    void saveSpuInfo(SpuSaveVo vo);

    /**
     * 保存spu基本信息  pms_sku_info
     *
     * @author xgl
     * @date 2021/4/5 11:25
     **/
    void savebaseSpuInfo(SpuInfoEntity spuInfoEntity);

    /**
     * skp列表
     *
     * @author xgl
     * @date 2021/4/11 17:58
     **/
    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 商品上架
     *
     * @author xgl
     * @date 2021/5/9 11:03
     **/
    void up(Long spuId);
}

