package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("会员等级domain")
public class VipLevel {

	private Long id;
	/**
	 * 会员等级名称
	 */
	@ObjectProp("会员等级")
	private String levelName;
	/**
	 * 服务折扣(单位百分号)
	 */
	@ObjectProp("服务折扣")
	private Integer levelServiceDisCount;
	
	/**
	 * 商品折扣
	 */
	@ObjectProp("商品折扣")
	private Integer levelProducteDisCount;
	
}
