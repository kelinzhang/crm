package com._520it.crm.service.impl;

import com._520it.crm.domain.Member;
import com._520it.crm.domain.Recharge;
import com._520it.crm.mapper.MemberMapper;
import com._520it.crm.mapper.VipLevelMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private VipLevelMapper vipLevelMapper;

	public int deleteByPrimaryKey(Long id) {
		return memberMapper.deleteByPrimaryKey(id);
	}

	public int insert(Member record) {
		return memberMapper.insert(record);
	}

	public Member selectByPrimaryKey(Long id) {
		Member member =  memberMapper.selectByPrimaryKey(id);
		return member;
	}

	public List<Member> selectAll() {
		return memberMapper.selectAll();
	}

	public int updateByPrimaryKey(Member record) {
		return memberMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = memberMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Member> result = memberMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public Member queryByMember(String number) {

		return memberMapper.queryByMember(number);
	}


	@Override
	public Member queryBack(String tel) {
		return memberMapper.queryBack(tel);
	}


	@Override
	public void insertForPet(Member member) {
		//保存前设置余额并设置消费金额为0
		member.setBalance(member.getCredit());
		member.setConsume(BigDecimal.ZERO);
		memberMapper.insert(member);
	}


	@Override
	public void insertForRecharge(Member member, Recharge recharge) {
		//充值时将余额设置成之前的余额加上充值金额然后保存进数据库
		member.setBalance(member.getBalance().add(recharge.getPay()));
		memberMapper.updateByPrimaryKey(member);
	}

	@Override
	public void updateForPet(Member member) {
		Member member1 = memberMapper.selectByNum(member.getNumber());
		member1.setNumber(member.getNumber());
		member1.setName(member.getName());
		member1.setGender(member.getGender());
		member1.setAddress(member.getAddress());
		member1.setLevel(vipLevelMapper.selectByPrimaryKey(member.getLevel().getId()));
		memberMapper.updateByPrimaryKey(member1);
	}
@Override
	public List<Member> memberChart() {
		return memberMapper.memberChart();
	}

}
