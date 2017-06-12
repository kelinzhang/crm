package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Supplier;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ISupplierService {
	int deleteByPrimaryKey(Long id);
    int insert(Supplier record);
    Supplier selectByPrimaryKey(Long id);
    List<Supplier> selectAll();
    int updateByPrimaryKey(Supplier record);
	PageResult queryByConditionPage(QueryObject qo);
}
