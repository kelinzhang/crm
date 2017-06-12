package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ObjectProp("会员")
public class Member {
    public static final Integer GENNER_MAN = 1;
    public static final Integer GENNER_WOMAN = 2;
    public static final Integer GENNER_UNKONW= 0;
    private Long id;
    @ObjectProp("会员号")
    private String number;
    @ObjectProp("姓名")
    private String name;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                ", credit=" + credit +
                ", consume=" + consume +
                '}';
    }

    @ObjectProp("性别")
    private Integer gender;
    @ObjectProp("地址")
    private String address;
    @ObjectProp("余额")
    private BigDecimal balance;
    @ObjectProp("首冲金额")
    private BigDecimal credit;
    @ObjectProp("消费金额")
    private BigDecimal consume;
    @ObjectProp("会员等级")
    private VipLevel level;
    List<Pet> pets;
}
