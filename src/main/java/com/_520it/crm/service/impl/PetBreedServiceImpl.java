package com._520it.crm.service.impl;

import com._520it.crm.domain.PetBreed;
import com._520it.crm.mapper.PetBreedMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPetBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class PetBreedServiceImpl implements IPetBreedService {
	@Autowired
	private PetBreedMapper petBreedMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return petBreedMapper.deleteByPrimaryKey(id);
	}

	public int insert(PetBreed record) {
		return petBreedMapper.insert(record);
	}

	public PetBreed selectByPrimaryKey(Long id) {
		return petBreedMapper.selectByPrimaryKey(id);
	}

	public List<PetBreed> selectAll() {
		return petBreedMapper.selectAll();
	}

	public int updateByPrimaryKey(PetBreed record) {
		return petBreedMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = petBreedMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<PetBreed> result = petBreedMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<PetBreed> selectForFather() {
		return petBreedMapper.selectForFather();
	}

	@Override
	public List<PetBreed> selectForChildren(Long parentId) {
		return petBreedMapper.selectForChildren(parentId);
	}
}
