package com.sour.mall.member.dao;

import com.sour.mall.member.entity.GrowthChangeHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成长值变化历史记录
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:57:05
 */
@Mapper
public interface IGrowthChangeHistoryDao extends BaseMapper<GrowthChangeHistoryEntity> {
	
}
