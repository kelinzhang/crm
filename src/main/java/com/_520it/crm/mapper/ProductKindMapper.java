package com._520it.crm.mapper;

import com._520it.crm.domain.ProductKind;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductKindMapper {
    int deleteByPrimaryKey(Long id);
    int insert(ProductKind record);
    ProductKind selectByPrimaryKey(Long id);
    List<ProductKind> selectAll();
    int updateByPrimaryKey(ProductKind record);
	Long queryByCondictionCount(QueryObject qo);
	List<ProductKind> queryByConditionResult(QueryObject qo);
}