package com._520it.crm.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class QueryObject {
    private Integer page = 1;
    private Integer rows = 10;



    public QueryObject(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Integer getStart(){
        return (this.page - 1)*this.rows;
    }
}
