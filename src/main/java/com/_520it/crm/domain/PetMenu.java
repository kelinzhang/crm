package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ObjectProp("宠物菜单")
public class PetMenu {
    private Long id;
    @ObjectProp("名称")
    private String text;
    @ObjectProp("地址")
    private String url;
    @ObjectProp("服务次数/寄养天数")
    private Integer times;
    private PetMenu parent;
    private BigDecimal servicePrice;
    private List<PetMenu> children = new ArrayList<>();

}
