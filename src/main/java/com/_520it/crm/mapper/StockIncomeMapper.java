package com._520it.crm.mapper;

import com._520it.crm.domain.StockIncome;
import com._520it.crm.domain.StockIncomeItem;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface StockIncomeMapper {
    int deleteByPrimaryKey(Long id);
    int insert(StockIncome record);
    StockIncome selectByPrimaryKey(Long id);
    List<StockIncome> selectAll();
    int updateByPrimaryKey(StockIncome record);
	Long queryByCondictionCount(QueryObject qo);
	List<StockIncome> queryByConditionResult(QueryObject qo);

    void insertRelation(StockIncomeItem item);

    Long getItemCount(Long id);

    List<StockIncomeItem> getItems(Long id);
}