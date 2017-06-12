package com._520it.crm.service;
import com._520it.crm.domain.CashRecord;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ICashRecordService {
	int deleteByPrimaryKey(Long id);
    int insert(CashRecord record);
    CashRecord selectByPrimaryKey(Long id);
    List<CashRecord> selectAll();
    int updateByPrimaryKey(CashRecord record);
	PageResult queryByConditionPage(QueryObject qo);

    PageResult listItem(Long id);
}
