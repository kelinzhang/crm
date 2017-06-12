package com._520it.crm.service.impl;

import com._520it.crm.domain.SystemLog;
import com._520it.crm.mapper.SystemLogMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SystemLogServiceImpl implements ISystemLogService {
	@Autowired
	private SystemLogMapper systemLogMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return systemLogMapper.deleteByPrimaryKey(id);
	}

	public int insert(SystemLog record) {
		return systemLogMapper.insert(record);
	}

	public SystemLog selectByPrimaryKey(Long id) {
		return systemLogMapper.selectByPrimaryKey(id);
	}

	public List<SystemLog> selectAll() {
		return systemLogMapper.selectAll();
	}

	public int updateByPrimaryKey(SystemLog record) {
		return systemLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = systemLogMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SystemLog> result = systemLogMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
