package com._520it.crm.mapper;

import com._520it.crm.domain.PetService;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PetServiceMapper {
    int deleteByPrimaryKey(Long id);
    int insert(PetService record);
    PetService selectByPrimaryKey(Long id);
    List<PetService> selectAll();
    int updateByPrimaryKey(PetService record);
	Long queryByCondictionCount(QueryObject qo);
	List<PetService> queryByConditionResult(QueryObject qo);
    PetService queryOnePetService(Long id);
    int updateByPayForPetService(PetService record);
}