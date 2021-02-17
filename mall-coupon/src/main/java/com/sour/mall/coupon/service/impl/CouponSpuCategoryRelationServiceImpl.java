package com.sour.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.coupon.dao.ICouponSpuCategoryRelationDao;
import com.sour.mall.coupon.entity.CouponSpuCategoryRelationEntity;
import com.sour.mall.coupon.service.ICouponSpuCategoryRelationService;


@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<ICouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity> implements ICouponSpuCategoryRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuCategoryRelationEntity> page = this.page(
                new Query<CouponSpuCategoryRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuCategoryRelationEntity>()
        );

        return new PageUtils(page);
    }

}