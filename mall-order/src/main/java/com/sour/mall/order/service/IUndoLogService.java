package com.sour.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.order.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 22:09:55
 */
public interface IUndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

