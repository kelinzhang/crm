package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.mapper.VipLevelMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IVipLevelService;
@Service
public class VipLevelServiceImpl implements IVipLevelService {
	@Autowired
	private VipLevelMapper vipLevelMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return vipLevelMapper.deleteByPrimaryKey(id);
	}

	public int insert(VipLevel record) {
		return vipLevelMapper.insert(record);
	}

	public VipLevel selectByPrimaryKey(Long id) {
		return vipLevelMapper.selectByPrimaryKey(id);
	}

	public List<VipLevel> selectAll() {
		return vipLevelMapper.selectAll();
	}

	public int updateByPrimaryKey(VipLevel record) {
		return vipLevelMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = vipLevelMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<VipLevel> result = vipLevelMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
}
