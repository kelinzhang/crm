package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter@ObjectProp("充值")
public class Recharge {
    @ObjectProp("充值编号")
    private Long id;
    @ObjectProp("充值时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;
    @ObjectProp("充值金额")
    private BigDecimal pay;
    @ObjectProp("充值单编号")
    private String sn;
    @ObjectProp("备注")
    private String remark;
    @ObjectProp("会员")
    private Member member;

    public Member getMember1() {
        return member;
    }
}
