package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter@ObjectProp("商品库存")
public class ProductStock {
    private Long id;
    @ObjectProp("商品编码")
    private Long proSn;//对应的product的ID
    @ObjectProp("商品名称")
    private String proName;
    @ObjectProp("商品种类")
    private String kind;
    @ObjectProp("商品品牌")
    private String brand;
    @ObjectProp("商品供应商")
    private String supplier;
    @ObjectProp("库存数量")
    private BigDecimal stockNumber;
    @ObjectProp("库存价格")
    private BigDecimal stockPrice;
    @ObjectProp("库存总价值")
    private BigDecimal stockAmount;
}
