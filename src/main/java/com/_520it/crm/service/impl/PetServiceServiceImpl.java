package com._520it.crm.service.impl;

import com._520it.crm.domain.PetService;
import com._520it.crm.mapper.PetServiceMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPetServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class PetServiceServiceImpl implements IPetServiceService {
	@Autowired
	private PetServiceMapper petServiceMapper;
	public int deleteByPrimaryKey(Long id) {
		return petServiceMapper.deleteByPrimaryKey(id);
	}
	public int insert(PetService record) {
		record.setState(PetService.STATE_NORMAL);
		record.setAfterPrice(record.getServicePrice().subtract(record.getBeforePrice()));
		record.setCurrentState(PetService.STATE_NORMAL);
		return petServiceMapper.insert(record);
	}
	public PetService selectByPrimaryKey(Long id) {
		return petServiceMapper.selectByPrimaryKey(id);
	}
	public List<PetService> selectAll() {
		return petServiceMapper.selectAll();
	}
	public int updateByPrimaryKey(PetService record) {
		record.setEndDateTime(new Date());
		return petServiceMapper.updateByPrimaryKey(record);
	}
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = petServiceMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<PetService> result = petServiceMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}
	public int updateByPayForPetService(Long id) {
		PetService petService = petServiceMapper.queryOnePetService(id);
		return petServiceMapper.updateByPayForPetService(petService);
	}

}
