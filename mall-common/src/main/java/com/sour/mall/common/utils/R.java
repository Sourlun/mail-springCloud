/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.sour.mall.common.utils;

import com.sour.mall.common.exception.BizCodeEnume;
import org.apache.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	/**
	 * 表单校验不通过
	 * @param result 结果
	 */
	public static R notValid(BindingResult result) {
		R r = new R();
		Map<String, String> errorsMap = new HashMap<>();
		result.getFieldErrors().forEach( item -> {
			// 每个字段的默认信息提示, 有配置的则用配置信息
			String message = item.getDefaultMessage();
			// 字段名
			String field = item.getField();
			errorsMap.put(field, message);
		});
		r.put("data", errorsMap);
		r.put("code", BizCodeEnume.VAILD_EXCEPTION.getCode());
		r.put("msg", BizCodeEnume.VAILD_EXCEPTION.getMsg());
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Integer getCode() {
		return (Integer) this.get("code");
	}
}
