package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Product;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IProductService {
	int deleteByPrimaryKey(Long id);
    int insert(Product record);
    Product selectByPrimaryKey(Long id);
    List<Product> selectAll();
    int updateByPrimaryKey(Product record);
	PageResult queryByConditionPage(QueryObject qo);
}
