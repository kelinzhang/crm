package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter@ObjectProp("收银明细")
public class CashItem {
      private Long id;
      @ObjectProp("商品条码")
      private Long proSn;
      @ObjectProp("商品名称")
      private String proName;
      @ObjectProp("商品种类")
      private String kind;
      @ObjectProp("商品品种")
      private String brand;
      @ObjectProp("供应商")
      private String supplier;
      @ObjectProp("折扣")
      private Double discount;
      @ObjectProp("售价")
      private BigDecimal salePrice;
      @ObjectProp("销售数量")
      private BigDecimal saleNumber;
      @ObjectProp("销售总额")
      private BigDecimal saleAmount;
      @ObjectProp("销售利润")
      private BigDecimal profix;
      @ObjectProp("收银单")
      private CashRecord record;

      @Override
      public String toString() {
            return "CashItem{" +
                    "id=" + id +
                    ", proSn=" + proSn +
                    ", proName='" + proName +
                    ", kind='" + kind +
                    ", brand='" + brand +
                    ", supplier='" + supplier +
                    ", discount=" + discount +
                    ", salePrice=" + salePrice +
                    ", saleNumber=" + saleNumber +
                    ", saleAmount=" + saleAmount +
                    ", profix=" + profix +
                    ", record=" + record +
                    '}';
      }
}
