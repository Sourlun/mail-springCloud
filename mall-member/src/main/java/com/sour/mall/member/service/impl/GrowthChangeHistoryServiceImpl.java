package com.sour.mall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.member.dao.IGrowthChangeHistoryDao;
import com.sour.mall.member.entity.GrowthChangeHistoryEntity;
import com.sour.mall.member.service.IGrowthChangeHistoryService;


@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<IGrowthChangeHistoryDao, GrowthChangeHistoryEntity> implements IGrowthChangeHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrowthChangeHistoryEntity> page = this.page(
                new Query<GrowthChangeHistoryEntity>().getPage(params),
                new QueryWrapper<GrowthChangeHistoryEntity>()
        );

        return new PageUtils(page);
    }

}