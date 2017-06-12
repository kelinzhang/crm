package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Product;
import com._520it.crm.mapper.ProductMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IProductService;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	public int insert(Product record) {
		return productMapper.insert(record);
	}

	public Product selectByPrimaryKey(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	public List<Product> selectAll() {
		return productMapper.selectAll();
	}

	public int updateByPrimaryKey(Product record) {
		return productMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = productMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Product> result = productMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
