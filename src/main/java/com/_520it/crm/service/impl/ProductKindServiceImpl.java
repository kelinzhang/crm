package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.ProductKind;
import com._520it.crm.mapper.ProductKindMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IProductKindService;
@Service
public class ProductKindServiceImpl implements IProductKindService {
	@Autowired
	private ProductKindMapper productKindMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return productKindMapper.deleteByPrimaryKey(id);
	}

	public int insert(ProductKind record) {
		return productKindMapper.insert(record);
	}

	public ProductKind selectByPrimaryKey(Long id) {
		return productKindMapper.selectByPrimaryKey(id);
	}

	public List<ProductKind> selectAll() {
		return productKindMapper.selectAll();
	}

	public int updateByPrimaryKey(ProductKind record) {
		return productKindMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = productKindMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<ProductKind> result = productKindMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
