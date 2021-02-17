package com.sour.mall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.member.dao.IMemberStatisticsInfoDao;
import com.sour.mall.member.entity.MemberStatisticsInfoEntity;
import com.sour.mall.member.service.IMemberStatisticsInfoService;


@Service("memberStatisticsInfoService")
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<IMemberStatisticsInfoDao, MemberStatisticsInfoEntity> implements IMemberStatisticsInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberStatisticsInfoEntity> page = this.page(
                new Query<MemberStatisticsInfoEntity>().getPage(params),
                new QueryWrapper<MemberStatisticsInfoEntity>()
        );

        return new PageUtils(page);
    }

}