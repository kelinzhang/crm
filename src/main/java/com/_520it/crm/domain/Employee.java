 package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter
public class Employee {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_QUIT = -1;
    //ID
    private Long id;
    //姓名
    private String username;
    //真实姓名
    private String realname;
    //密码
    private String password;
    private String tel;
    private String email;
    private Date inputtime;
    private int state;
    private boolean admin;

    private Department dept;
    private List<Role> roles = new ArrayList<>();
}