package com._520it.crm.mapper;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductBrandMapper {
    int deleteByPrimaryKey(Long id);
    int insert(ProductBrand record);
    ProductBrand selectByPrimaryKey(Long id);
    List<ProductBrand> selectAll();
    int updateByPrimaryKey(ProductBrand record);
	Long queryByCondictionCount(QueryObject qo);
	List<ProductBrand> queryByConditionResult(QueryObject qo);
}