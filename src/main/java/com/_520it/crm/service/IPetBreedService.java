package com._520it.crm.service;
import com._520it.crm.domain.PetBreed;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IPetBreedService {
	int deleteByPrimaryKey(Long id);
    int insert(PetBreed record);
    PetBreed selectByPrimaryKey(Long id);
    List<PetBreed> selectAll();
    int updateByPrimaryKey(PetBreed record);
	PageResult queryByConditionPage(QueryObject qo);
    List<PetBreed> selectForFather();
    List<PetBreed> selectForChildren(Long parentId);
}
