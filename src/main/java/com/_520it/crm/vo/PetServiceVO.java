package com._520it.crm.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PetServiceVO {
    private String groupByServiceName; //分组名称
    private BigDecimal totalNumber; //总的销售数量
    private BigDecimal totalAmount; //总的销售金额

    public PetServiceVO() {
        super();
    }

    public PetServiceVO(String groupByServiceName, BigDecimal totalNumber, BigDecimal totalAmount) {
        this.groupByServiceName = groupByServiceName;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
    }
}
