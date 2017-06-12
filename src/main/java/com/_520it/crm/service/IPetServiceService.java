package com._520it.crm.service;
import com._520it.crm.domain.PetService;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IPetServiceService {
	int deleteByPrimaryKey(Long id);
    int insert(PetService record);
    PetService selectByPrimaryKey(Long id);
    List<PetService> selectAll();
    int updateByPrimaryKey(PetService record);
	PageResult queryByConditionPage(QueryObject qo);
    int updateByPayForPetService(Long id);
}
