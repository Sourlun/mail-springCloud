package com.sour.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.coupon.dao.IHomeSubjectSpuDao;
import com.sour.mall.coupon.entity.HomeSubjectSpuEntity;
import com.sour.mall.coupon.service.IHomeSubjectSpuService;


@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<IHomeSubjectSpuDao, HomeSubjectSpuEntity> implements IHomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(params),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );

        return new PageUtils(page);
    }

}