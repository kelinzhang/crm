package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter@ObjectProp("收银类")
public class Cash {
    private Long id;
    @ObjectProp("库存编码")
    private Long proSn;
    @ObjectProp("会员号")
    private String number;
    @ObjectProp("商品名称")
    private String proName;
    @ObjectProp("原价")
    private BigDecimal costPrice;
    @ObjectProp("会员价")
    private BigDecimal salePrice;
    @ObjectProp("数量")
    private BigDecimal amount;
    @ObjectProp("原价")
    private BigDecimal totalCostPrice;
    @ObjectProp("会员价")
    private BigDecimal totalSalePrice;
    private int delete =1;

}
