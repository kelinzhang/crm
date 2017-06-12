package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.ProductBrand;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IProductBrandService {
	int deleteByPrimaryKey(Long id);
    int insert(ProductBrand record);
    ProductBrand selectByPrimaryKey(Long id);
    List<ProductBrand> selectAll();
    int updateByPrimaryKey(ProductBrand record);
	PageResult queryByConditionPage(QueryObject qo);
}
