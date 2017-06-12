package com._520it.crm.service;


import com._520it.crm.domain.Member;
import com._520it.crm.domain.Pet;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IPetService {
	int deleteByPrimaryKey(Long id);
    int insert(Pet record);
    Pet selectByPrimaryKey(Long id);
    List<Pet> selectAll();
    int updateByPrimaryKey(Pet record);
	PageResult queryByConditionPage(QueryObject qo);


    void insertForMember(Pet pet, Member member);

    Long selectMemberById(Long petId);

    void updateForMember(Pet pet, Member member1);
}
