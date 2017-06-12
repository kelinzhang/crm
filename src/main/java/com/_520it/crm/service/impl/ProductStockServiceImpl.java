package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.mapper.ProductStockMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ProductStockServiceImpl implements IProductStockService {
	@Autowired
	private ProductStockMapper productStockMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return productStockMapper.deleteByPrimaryKey(id);
	}

	public int insert(ProductStock record) {
		return productStockMapper.insert(record);
	}

	public ProductStock selectByPrimaryKey(Long id) {
		return productStockMapper.selectByPrimaryKey(id);
	}

	public List<ProductStock> selectAll() {
		return productStockMapper.selectAll();
	}

	public int updateByPrimaryKey(ProductStock record) {
		return productStockMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(ProductStockQueryObject qo) {
		Long count = productStockMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<ProductStock> result = productStockMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public ProductStock getPsByIncomeItem(Long proSn, String supplier) {
		return productStockMapper.getPsByIncomeItem(proSn,supplier);
	}
}
