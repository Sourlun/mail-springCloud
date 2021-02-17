package com.sour.mall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.order.dao.IOrderReturnReasonDao;
import com.sour.mall.order.entity.OrderReturnReasonEntity;
import com.sour.mall.order.service.IOrderReturnReasonService;


@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<IOrderReturnReasonDao, OrderReturnReasonEntity> implements IOrderReturnReasonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnReasonEntity> page = this.page(
                new Query<OrderReturnReasonEntity>().getPage(params),
                new QueryWrapper<OrderReturnReasonEntity>()
        );

        return new PageUtils(page);
    }

}