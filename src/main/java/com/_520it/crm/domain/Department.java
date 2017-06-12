package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Department {
    private Long id;
    private String name;
    private String sn;
    private Boolean state;

    private Employee manager;
    private Department parent;
}