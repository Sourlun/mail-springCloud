package com.sour.mall.coupon.service.impl;

import com.sour.mall.common.to.MemberPriceTo;
import com.sour.mall.common.to.SkuReductionTo;
import com.sour.mall.coupon.entity.MemberPriceEntity;
import com.sour.mall.coupon.entity.SkuLadderEntity;
import com.sour.mall.coupon.service.IMemberPriceService;
import com.sour.mall.coupon.service.ISkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.coupon.dao.ISkuFullReductionDao;
import com.sour.mall.coupon.entity.SkuFullReductionEntity;
import com.sour.mall.coupon.service.ISkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<ISkuFullReductionDao, SkuFullReductionEntity> implements ISkuFullReductionService {


    @Autowired
    private ISkuLadderService skuLadderService;

    @Autowired
    private IMemberPriceService memberPriceService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
        // 1, 保存满减打折, 会员价 sms_sku_ladder  sms_sku_full_reduction  sms_member_price
        // sms_sku_ladder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionTo.getSkuId());
        skuLadderEntity.setFullCount(skuReductionTo.getFullCount());
        skuLadderEntity.setDiscount(skuReductionTo.getDiscount());
        skuLadderEntity.setAddOther(skuReductionTo.getAddOther());
        skuLadderEntity.setPrice(skuReductionTo.getPrice());
        if ( skuLadderEntity.getFullCount() > 0 ) {
            skuLadderService.save(skuLadderEntity);
        }

        // sms_sku_full_reduction
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
        if ( skuFullReductionEntity.getFullPrice().compareTo(new BigDecimal("0")) == 1 ) {
            save(skuFullReductionEntity);
        }

        // sms_member_price
        List<MemberPriceTo> memberPriceTos = skuReductionTo.getMemberPriceTos();
        if ( null != memberPriceTos && memberPriceTos.size() > 0 ) {
            List<MemberPriceEntity> memberPriceEntities = memberPriceTos.stream().map(item -> {
                MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
                memberPriceEntity.setSkuId(skuReductionTo.getSkuId());
                memberPriceEntity.setMemberLevelId(item.getId());
                memberPriceEntity.setMemberLevelName(item.getName());
                memberPriceEntity.setMemberPrice(item.getPrice());
                memberPriceEntity.setAddOther(1);
                return memberPriceEntity;
            }).filter(memberPriceEntity -> memberPriceEntity.getMemberPrice().compareTo(new BigDecimal(0)) > 0)
                    .collect(Collectors.toList());
            memberPriceService.saveBatch(memberPriceEntities);
        }
    }

}