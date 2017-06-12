package com._520it.crm.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockAllVO {
    private String groupByStockName; //分组名称
    private BigDecimal totalNumber; //总的销售数量
    private BigDecimal totalAmount; //总的销售金额

    public StockAllVO() {
        super();
    }

    public StockAllVO(String groupByStockName, BigDecimal totalNumber, BigDecimal totalAmount) {
        this.groupByStockName = groupByStockName;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
    }
}
