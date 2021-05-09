package com.sour.mall.product.service.impl;

import com.sour.mall.common.constant.ProductConstant;
import com.sour.mall.common.to.SkuHasStockTo;
import com.sour.mall.common.to.SkuReductionTo;
import com.sour.mall.common.to.SpuBoundTo;
import com.sour.mall.common.to.es.SkuEsModel;
import com.sour.mall.common.utils.R;
import com.sour.mall.product.entity.*;
import com.sour.mall.product.feign.ICouponFeignService;
import com.sour.mall.product.feign.ISearchFeignService;
import com.sour.mall.product.feign.IWareFeignService;
import com.sour.mall.product.service.*;
import com.sour.mall.product.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.product.dao.ISpuInfoDao;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<ISpuInfoDao, SpuInfoEntity> implements ISpuInfoService {

    @Autowired
    private ISpuInfoDescService spuInfoDescService;

    @Autowired
    private ISpuImagesService spuImagesService;

    @Autowired
    private IAttrService attrService;

    @Autowired
    private IProductAttrValueService productAttrValueService;

    @Autowired
    private ISkuInfoService skuInfoService;

    @Autowired
    private ISkuImagesService skuImagesService;

    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private ICouponFeignService couponFeignService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IWareFeignService wareFeignService;

    @Autowired
    private ISearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        // 1, 保存spu基本信息  pms_sku_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        BeanUtils.copyProperties(vo, spuInfoEntity);
        this.savebaseSpuInfo(spuInfoEntity);

        // 2, 保存spu描述图片  pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(String.join(",", decript));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);

        // 3, 保存spu的图片集  pms_spu_images
        List<String> images = vo.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);

        // 4, 保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(item -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setAttrId(item.getAttrId());
            AttrEntity attrEntity = attrService.getById(item.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setAttrValue(item.getAttrValues());
            productAttrValueEntity.setQuickShow(item.getShowDesc());
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());
            return productAttrValueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(productAttrValueEntities);

        // 5, 保存spu的积分信息 (远程)
        Bounds bound = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bound, spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        R r1 = couponFeignService.saveSpuBounds(spuBoundTo);
        if (r1.getCode() != 0) {
            log.error("远程保存spu积分信息失败!");
        }

        // 6, 保存当前spu对应的所有sku的信息  pms_spu_info
        List<Skus> skus = vo.getSkus();
        if ( null != skus && skus.size() > 0 ) {

            skus.forEach(item -> {
                // 默认图片
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if ( image.getDefaultImg() == 1 ) {
                        defaultImg = image.getImgUrl();
                    }
                }

                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);

                skuInfoService.saveSkuInfo(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();
                // sku图片信息
                List<SkuImagesEntity> skuImagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter( entity -> StringUtils.isNotBlank(entity.getImgUrl()) ).collect(Collectors.toList());

                // 6.1, sku的图片信息 pms_sku_images
                skuImagesService.saveBatch(skuImagesEntities);

                // 6.2, sku的的销售属性  pms_sku_sale_attr_value
                List<Attr> attrs = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attrs.stream().map(attr -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(attr, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                // 6.3  sku的优惠信息  sms_sku_ladder  sms_sku_full_reduction  sms_member_price  (远程)
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                R r = couponFeignService.saveSkuReduction(skuReductionTo);
                if (r.getCode() != 0) {
                    log.error("远程保存sku的优惠信息信息失败!");
                }
            });
        }
    }

    @Override
    public void savebaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<SpuInfoEntity>();
        if ( params.containsKey("key") ) {
            String key = (String) params.get("key");
            if ( StringUtils.isNotBlank(key) ) {
                wrapper.and( w -> w.eq("id", key).or().like("spu_name", key).or().like("spu_description", key) );
            }
        }
        if ( params.containsKey("status") ) {
            String status = (String) params.get("status");
            if ( StringUtils.isNotBlank(status) ) {
                wrapper.eq("publish_status", status);
            }
        }
        if ( params.containsKey("brandId") ) {
            String brandId = (String) params.get("brandId");
            if ( StringUtils.isNotBlank(brandId) ) {
                wrapper.eq("brand_id", brandId);
            }
        }
        if ( params.containsKey("catelogId") ) {
            String catelogId = (String) params.get("catelogId");
            if ( StringUtils.isNotBlank(catelogId) ) {
                wrapper.eq("catalog_id", catelogId);
            }
        }

        IPage<SpuInfoEntity> page = page(new Query<SpuInfoEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {

        // 查询当前sku当前所有可以被检索的规格属性
        List<ProductAttrValueEntity> productAttrValueEntities = productAttrValueService.baseAttrListForSpu(spuId);
        List<Long> attrIds = productAttrValueEntities.stream().map(ProductAttrValueEntity::getAttrId).collect(Collectors.toList());
        List<Long> searchIds = attrService.selectSearchAttrIds(attrIds);
        Set<Long> searchIdsSet = new HashSet<>(searchIds);
        List<SkuEsModel.Attr> attrs = attrIds.stream().filter(searchIdsSet::contains).map(item -> {
            SkuEsModel.Attr attr = new SkuEsModel.Attr();
            BeanUtils.copyProperties(item, attr);
            return attr;
        }).collect(Collectors.toList());

        // 1, 查出当前spuId对应的所有sku信息, 品牌名字
        List<SkuInfoEntity> skus = skuInfoService.getSkusBySpuId(spuId);
        List<Long> skuIds = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

        // 1.1, 发送远程调用, 查询库存是否有库存
        Map<Long, Boolean> skusHasStockMap = null;
        try {
            R<List<SkuHasStockTo>> skusHasStock = wareFeignService.getSkusHasStock(skuIds);
            skusHasStockMap = skusHasStock.getData().stream().collect(Collectors.toMap(SkuHasStockTo::getSkuId, SkuHasStockTo::getHasStock));
        } catch ( Exception e ) {
            log.error("库存服务查询异常", e);
        }


        // 2, 封装每个sku的信息
        Map<Long, Boolean> finalSkusHasStockMap = skusHasStockMap;
        List<SkuEsModel> esModels = skus.stream().map(sku -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(sku, skuEsModel);
            skuEsModel.setSkuPrice(sku.getPrice());
            skuEsModel.setSkuImg(sku.getSkuDefaultImg());

            // 2.1, 库存是否有库存
            if ( null == finalSkusHasStockMap) {
                skuEsModel.setHasStock(false);
            } else {
                skuEsModel.setHasStock(finalSkusHasStockMap.get(sku.getSkuId()));
            }

            // 2.2, 热度评分, 暂时设置0
            skuEsModel.setHotScore(0L);

            // 2.3, 查询品牌
            BrandEntity brand = brandService.getById(skuEsModel.getBrandId());
            skuEsModel.setBrandName(brand.getName());
            skuEsModel.setBrandImg(brand.getLogo());

            // 2.4, 查询分类名字信息
            CategoryEntity category = categoryService.getById(skuEsModel.getCatalogId());
            skuEsModel.setCatalogName(category.getName());

            // 2.5, 设置检索属性
            skuEsModel.setAttrs(attrs);

            return skuEsModel;
        }).collect(Collectors.toList());

        // 3, 数据发给ES进行保存
        R r = searchFeignService.productStatusUp(esModels);
        if ( r.getCode() == 0 ) {
            // 修改spu的状态
            baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
        } else {
            // 远程调用失败 TODO
        }
    }


}