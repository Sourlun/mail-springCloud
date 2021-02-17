package com.sour.mall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.member.dao.IMemberCollectSubjectDao;
import com.sour.mall.member.entity.MemberCollectSubjectEntity;
import com.sour.mall.member.service.IMemberCollectSubjectService;


@Service("memberCollectSubjectService")
public class MemberCollectSubjectServiceImpl extends ServiceImpl<IMemberCollectSubjectDao, MemberCollectSubjectEntity> implements IMemberCollectSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCollectSubjectEntity> page = this.page(
                new Query<MemberCollectSubjectEntity>().getPage(params),
                new QueryWrapper<MemberCollectSubjectEntity>()
        );

        return new PageUtils(page);
    }

}