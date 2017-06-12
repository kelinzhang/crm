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

@Setter@Getter@ObjectProp("收银记录")
public class CashRecord {
    private Long id;
    @ObjectProp("收银编号")
    private String proSn;
    @ObjectProp("会员卡号")
    private String number;
    @ObjectProp("订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;
    @ObjectProp("商品总数")
    private BigDecimal shopAmount;
    @ObjectProp("消费金额")
    private BigDecimal consume;
    @ObjectProp("操作人")
    private Employee inputUser;
    private int state=1;
    @ObjectProp("收银明细")
    private List<CashItem> items= new ArrayList<>();

    @Override
    public String toString() {
        return "CashRecord{" +
                "id=" + id +
                ", proSn=" + proSn +
                ", number='" + number + '\'' +
                ", inputTime=" + inputTime +
                ", shopAmount=" + shopAmount +
                ", consume=" + consume +
                ", inputUser=" + inputUser +
                ", state=" + state +
                ", items=" + items.size() +
                '}';
    }
}
