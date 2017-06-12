package com._520it.crm.service;
import com._520it.crm.domain.StockIncome;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IStockIncomeService {
	int deleteByPrimaryKey(Long id);
    int insert(StockIncome record);
    StockIncome selectByPrimaryKey(Long id);
    List<StockIncome> selectAll();
    int updateByPrimaryKey(StockIncome record);
	PageResult queryByConditionPage(QueryObject qo);

    PageResult listItem(Long id);
}
