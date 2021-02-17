package com.sour.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sour.mall.common.utils.PageUtils;
import com.sour.mall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 21:57:05
 */
public interface IMemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

