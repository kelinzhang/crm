package com._520it.crm.service.impl;


import com._520it.crm.domain.Member;
import com._520it.crm.domain.Pet;
import com._520it.crm.mapper.MemberMapper;
import com._520it.crm.mapper.PetBreedMapper;
import com._520it.crm.mapper.PetMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class PetServiceImpl implements IPetService {
	@Autowired
	private PetMapper petMapper;
	@Autowired
	private PetBreedMapper petBreedMapper;
	@Autowired
	private MemberMapper memberMapper;

	public int deleteByPrimaryKey(Long id) {
		return petMapper.deleteByPrimaryKey(id);
	}

	public int insert(Pet record) {
		return petMapper.insert(record);
	}

	public Pet selectByPrimaryKey(Long id) {
		return petMapper.selectByPrimaryKey(id);
	}

	public List<Pet> selectAll() {
		return petMapper.selectAll();
	}

	public int updateByPrimaryKey(Pet record) {
		return petMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = petMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Pet> result = petMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public void insertForMember(Pet pet, Member member) {
		//先进行外键关系,再进行保存
		pet.setMember(member);
		petMapper.insert(pet);
	}

	@Override
	public Long selectMemberById(Long petId) {
		return petMapper.selectMemberById(petId);
	}

	@Override
	public void updateForMember(Pet pet, Member member) {
		Pet pet1 = petMapper.selectByPrimaryKey(pet.getId());
		member = memberMapper.selectByNum(member.getNumber());
		pet1.setPetName(pet.getPetName());
		pet1.setBirthday(pet.getBirthday());
		pet1.setSex(pet.getSex());
		pet1.setBreed(petBreedMapper.selectByPrimaryKey(pet.getBreed().getId()));
		pet1.setColor(pet.getColor());
		pet1.setAncestry(pet.getAncestry());
		pet1.setMember(member);
		petMapper.updateByPrimaryKey(pet1);
	}

}
