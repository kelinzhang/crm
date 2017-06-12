package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("供应商")
public class Supplier {
    private Long id;
    @ObjectProp("供应商名称")
    private String name;
    @ObjectProp("联系电话")
    private String phone;
    @ObjectProp("供应商地址")
    private String address;
}
