package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.ProductKind;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IProductKindService {
	int deleteByPrimaryKey(Long id);
    int insert(ProductKind record);
    ProductKind selectByPrimaryKey(Long id);
    List<ProductKind> selectAll();
    int updateByPrimaryKey(ProductKind record);
	PageResult queryByConditionPage(QueryObject qo);
}
