package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ObjectProp("宠物服务")
@ToString
public class PetService {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_QUIT = 1;
    private Long id;
    //宠物名
    private String petName;
    //主人名
    private String personName;
    //联系电话
    private String tel;
    //服务项目名
    private String serviceProject;
    //服务价格
    private BigDecimal servicePrice;
    //付款状态
    private int state;
    //预付款
    private BigDecimal beforePrice;
    //尾款
    private BigDecimal afterPrice;
    //当前状态
    private int currentState;
    //次数
    private int times;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDateTime;
}
