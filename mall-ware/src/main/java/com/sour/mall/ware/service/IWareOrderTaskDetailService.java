package com.sour.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:19:53
 */
public interface IWareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

