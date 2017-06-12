package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter@ObjectProp("入库明细")
public class StockIncomeItem {

    private Long id;
    @ObjectProp("商品编号")
    private Long proSn;
    @ObjectProp("商品名称")
    private String proName;
    @ObjectProp("商品种类")
    private String kind;
    @ObjectProp("商品品牌")
    private String brand;
    @ObjectProp("商品供应商")
    private String supplier;
    @ObjectProp("入库数量")
    private BigDecimal incomeNumber;
    @ObjectProp("入库价格")
    private BigDecimal incomePrice;
    @ObjectProp("入库总价值")
    private BigDecimal incomeAmount;
    @ObjectProp("入库单")
    private StockIncome stockIn;
}
