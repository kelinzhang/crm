package com._520it.crm.mapper;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Long id);
    int insert(ProductStock record);
    ProductStock selectByPrimaryKey(Long id);
    List<ProductStock> selectAll();
    int updateByPrimaryKey(ProductStock record);
	Long queryByCondictionCount(ProductStockQueryObject qo);
	List<ProductStock> queryByConditionResult(ProductStockQueryObject qo);

    ProductStock getPsByIncomeItem(@Param("proSn") Long proSn, @Param("supplier") String supplier);
}