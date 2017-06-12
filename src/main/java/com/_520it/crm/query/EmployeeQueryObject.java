package com._520it.crm.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class EmployeeQueryObject extends QueryObject{

    private String keyword;

    public EmployeeQueryObject(Integer page, Integer rows) {
        super(page, rows);
    }
}
