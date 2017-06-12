package com._520it.crm.mapper;

import com._520it.crm.domain.CashItem;
import com._520it.crm.domain.CashRecord;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface CashRecordMapper {
    int deleteByPrimaryKey(Long id);
    int insert(CashRecord record);
    CashRecord selectByPrimaryKey(Long id);
    List<CashRecord> selectAll();
    int updateByPrimaryKey(CashRecord record);
	Long queryByCondictionCount(QueryObject qo);
	List<CashRecord> queryByConditionResult(QueryObject qo);
	
    Long getItemCount(Long id);

    List<CashItem> getItem(Long id);

    void insertRelation(CashItem item);
}