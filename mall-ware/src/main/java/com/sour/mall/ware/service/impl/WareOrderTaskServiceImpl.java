package com.sour.mall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.common.utils.Query;

import com.sour.mall.ware.dao.IWareOrderTaskDao;
import com.sour.mall.ware.entity.WareOrderTaskEntity;
import com.sour.mall.ware.service.IWareOrderTaskService;


@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<IWareOrderTaskDao, WareOrderTaskEntity> implements IWareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskEntity> page = this.page(
                new Query<WareOrderTaskEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskEntity>()
        );

        return new PageUtils(page);
    }

}