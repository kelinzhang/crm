package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Supplier;
import com._520it.crm.mapper.SupplierMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISupplierService;
@Service
public class SupplierServiceImpl implements ISupplierService {
	@Autowired
	private SupplierMapper supplierMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return supplierMapper.deleteByPrimaryKey(id);
	}

	public int insert(Supplier record) {
		return supplierMapper.insert(record);
	}

	public Supplier selectByPrimaryKey(Long id) {
		return supplierMapper.selectByPrimaryKey(id);
	}

	public List<Supplier> selectAll() {
		return supplierMapper.selectAll();
	}

	public int updateByPrimaryKey(Supplier record) {
		return supplierMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = supplierMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Supplier> result = supplierMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
