package com._520it.crm.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter@ObjectProp("会员报表")
public class MemberVO {
    @ObjectProp("会员号")
    private String number;
    @ObjectProp("消费金额")
    private BigDecimal consume;
    @ObjectProp("订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;

    @Override
    public String toString() {
        return "MemberVO{" +
                "number='" + number +
                ", consume=" + consume +
                '}';
    }
}
