package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("商品种类")
public class ProductKind {
    @ObjectProp("种类编号")
    private Long id;
    @ObjectProp("种类名称")
    private String name;
}
