package com._520it.crm.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import genertor.ObjectProp;
@Setter@Getter@ObjectProp("商品类")
public class Product {
	@ObjectProp("商品编号")
	private Long id;
	@ObjectProp("商品名称")
	private String proName;
	@ObjectProp("商品进价")
	private BigDecimal costNumber;
	@ObjectProp("商品售价")
	private BigDecimal saleNumber;
	@ObjectProp("商品品牌名称")
	private ProductBrand brand;
	@ObjectProp("商品种类名称")
	private ProductKind kind;
}
