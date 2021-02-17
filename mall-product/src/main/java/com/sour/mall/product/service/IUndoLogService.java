package com.sour.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.product.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
public interface IUndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

