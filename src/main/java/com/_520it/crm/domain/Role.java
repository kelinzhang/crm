package com._520it.crm.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter@ObjectProp("角色")
public class Role {
    private Long id;
    @ObjectProp("角色名称")
    private String name;
    @ObjectProp("角色编码")
    private String sn;
    @ObjectProp("角色权限")
    private List<Permission> permissions = new ArrayList<>();
    @ObjectProp("角色菜单")
    private List<SystemMenu> menus = new ArrayList<>();
}
