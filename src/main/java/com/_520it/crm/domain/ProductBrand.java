package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import genertor.ObjectProp;
@Setter@Getter@ObjectProp("商品品牌")
public class ProductBrand {

	private Long id;
	@ObjectProp("商品品牌")
	private String brandName;
}
