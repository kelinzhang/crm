package com._520it.crm.mapper;

import com._520it.crm.domain.PetBreed;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PetBreedMapper {
    int deleteByPrimaryKey(Long id);
    int insert(PetBreed record);
    PetBreed selectByPrimaryKey(Long id);
    List<PetBreed> selectAll();
    int updateByPrimaryKey(PetBreed record);
	Long queryByCondictionCount(QueryObject qo);
	List<PetBreed> queryByConditionResult(QueryObject qo);

    List<PetBreed> selectForFather();

    List<PetBreed> selectForChildren(Long parentId);
}