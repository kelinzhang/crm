package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.mapper.ProductBrandMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IProductBrandService;
@Service
public class ProductBrandServiceImpl implements IProductBrandService {
	@Autowired
	private ProductBrandMapper productBrandMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return productBrandMapper.deleteByPrimaryKey(id);
	}

	public int insert(ProductBrand record) {
		return productBrandMapper.insert(record);
	}

	public ProductBrand selectByPrimaryKey(Long id) {
		return productBrandMapper.selectByPrimaryKey(id);
	}

	public List<ProductBrand> selectAll() {
		return productBrandMapper.selectAll();
	}

	public int updateByPrimaryKey(ProductBrand record) {
		return productBrandMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = productBrandMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		
		List<ProductBrand> result = productBrandMapper.queryByConditionResult(qo);
		
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
