package com.sour.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.coupon.dao.ISpuBoundsDao;
import com.sour.mall.coupon.entity.SpuBoundsEntity;
import com.sour.mall.coupon.service.ISpuBoundsService;


@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<ISpuBoundsDao, SpuBoundsEntity> implements ISpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBoundsEntity> page = this.page(
                new Query<SpuBoundsEntity>().getPage(params),
                new QueryWrapper<SpuBoundsEntity>()
        );

        return new PageUtils(page);
    }

}