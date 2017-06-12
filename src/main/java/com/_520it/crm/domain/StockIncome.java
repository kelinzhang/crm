package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter@ObjectProp("入库单")
public class StockIncome {
    private Long id;
    @ObjectProp("入库编号")
    private String sn;
    @ObjectProp("入库总数")
    private BigDecimal totalNumber;
    @ObjectProp("入库总价值")
    private BigDecimal totalAmount;
    @ObjectProp("供应商")
    private String supplier;
    @ObjectProp("入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vdate;
    @ObjectProp("操作人")
    private Employee inputUser;

    @ObjectProp("入库明细")
    private List<StockIncomeItem> items = new ArrayList<>();

    public int getItemSize(){
        return this.items.size();
    }

    @Override
    public String toString() {
        return "StockIncome{" +
                "id=" + id +
                ", sn='" + sn +
                ", totalNumber=" + totalNumber +
                ", totalAmount=" + totalAmount +
                ", supplier='" + supplier +
                ", vdate=" + vdate +
                ", inputUser=" + inputUser +
                ", items=" + items.size() +
                '}';
    }
}
