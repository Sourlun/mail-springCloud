package com.sour.mall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.order.dao.IOrderOperateHistoryDao;
import com.sour.mall.order.entity.OrderOperateHistoryEntity;
import com.sour.mall.order.service.IOrderOperateHistoryService;


@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl extends ServiceImpl<IOrderOperateHistoryDao, OrderOperateHistoryEntity> implements IOrderOperateHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderOperateHistoryEntity> page = this.page(
                new Query<OrderOperateHistoryEntity>().getPage(params),
                new QueryWrapper<OrderOperateHistoryEntity>()
        );

        return new PageUtils(page);
    }

}