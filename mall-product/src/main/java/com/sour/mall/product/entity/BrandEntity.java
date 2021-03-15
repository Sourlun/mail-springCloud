package com.sour.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.sour.mall.common.valid.AddGroup;
import com.sour.mall.common.valid.ListValue;
import com.sour.mall.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 * 品牌
 * 
 * @author SourLun
 * @email 1141837289@qq.com
 * @date 2021-02-17 16:15:58
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@NotNull(message = "修改必须指定id", groups = UpdateGroup.class)
	@Null(message = "新增不需要id", groups = AddGroup.class)
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotEmpty(message = "品牌名不能为空!", groups = {AddGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "品牌logo地址格式错误", groups = {AddGroup.class, UpdateGroup.class})
	@NotEmpty(message = "品牌logo地址不能为空!", groups = {AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@ListValue(value = {0, 1}, groups = {AddGroup.class}) //自定义注解
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@Pattern(regexp = "^[a-zA-z]$", message = "检索首字母必须是字母", groups = {AddGroup.class, UpdateGroup.class})
	@NotEmpty(message = "检索首字母不能为空!", groups = {AddGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(value = 0, message = "排序必须大于0", groups = {AddGroup.class, UpdateGroup.class})
	@Max(value = 99999, message = "排序必须小于99999", groups = {AddGroup.class, UpdateGroup.class})
	@NotNull(message = "排序不能为空!", groups = {AddGroup.class})
	private Integer sort;

}
