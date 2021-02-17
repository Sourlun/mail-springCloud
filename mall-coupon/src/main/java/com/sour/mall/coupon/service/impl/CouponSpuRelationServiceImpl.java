package com.sour.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.coupon.dao.ICouponSpuRelationDao;
import com.sour.mall.coupon.entity.CouponSpuRelationEntity;
import com.sour.mall.coupon.service.ICouponSpuRelationService;


@Service("couponSpuRelationService")
public class CouponSpuRelationServiceImpl extends ServiceImpl<ICouponSpuRelationDao, CouponSpuRelationEntity> implements ICouponSpuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuRelationEntity> page = this.page(
                new Query<CouponSpuRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuRelationEntity>()
        );

        return new PageUtils(page);
    }

}