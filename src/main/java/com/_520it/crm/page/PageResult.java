package com._520it.crm.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class PageResult {

    private Long total;
    private List rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
