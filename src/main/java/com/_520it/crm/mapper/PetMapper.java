package com._520it.crm.mapper;

import com._520it.crm.domain.Member;
import com._520it.crm.domain.Pet;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PetMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Pet record);
    Pet selectByPrimaryKey(Long id);
    List<Pet> selectAll();
    int updateByPrimaryKey(Pet record);
	Long queryByCondictionCount(QueryObject qo);
	List<Pet> queryByConditionResult(QueryObject qo);
    Long selectMemberById(Long petId);
    void insertForMember(Pet pet, Member member);
}