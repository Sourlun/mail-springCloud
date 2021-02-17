package com.sour.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.coupon.dao.ISeckillSkuRelationDao;
import com.sour.mall.coupon.entity.SeckillSkuRelationEntity;
import com.sour.mall.coupon.service.ISeckillSkuRelationService;


@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl extends ServiceImpl<ISeckillSkuRelationDao, SeckillSkuRelationEntity> implements ISeckillSkuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSkuRelationEntity> page = this.page(
                new Query<SeckillSkuRelationEntity>().getPage(params),
                new QueryWrapper<SeckillSkuRelationEntity>()
        );

        return new PageUtils(page);
    }

}