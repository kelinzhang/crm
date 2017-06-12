package com._520it.crm.service.impl;

import com._520it.crm.domain.Member;
import com._520it.crm.domain.Recharge;
import com._520it.crm.mapper.RechargeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class RechargeServiceImpl implements IRechargeService {
	@Autowired
	private RechargeMapper rechargeMapper;

	public int deleteByPrimaryKey(Long id) {
		return rechargeMapper.deleteByPrimaryKey(id);
	}

	public int insert(Recharge record) {
		return rechargeMapper.insert(record);
	}

	public Recharge selectByPrimaryKey(Long id) {
		return rechargeMapper.selectByPrimaryKey(id);
	}

	public List<Recharge> selectAll() {
		return rechargeMapper.selectAll();
	}

	public int updateByPrimaryKey(Recharge record) {
		return rechargeMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = rechargeMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Recharge> result = rechargeMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public void insertForMember(Member member,Recharge recharge) {
		//在添加会员时需要进行首冲,此时生成一条充值记录,设置备注为首冲并且将充值金额设置为首冲金额,设置当前时间为充值时间,设置会员外键关联然后保存进数据库
		Recharge recharge1 = new Recharge();
		recharge1.setRemark("首冲");
		recharge1.setPay(member.getCredit());
		recharge1.setPayDate(new Date());
		recharge1.setSn(recharge.getSn());
		recharge1.setMember(member);
		rechargeMapper.insert(recharge1);
	}

	@Override
	public void insertByMember(Member member, Recharge recharge) {
		//生成一个充值记录表,将前台传进来的充值金额和备注设置进去,然后设置时间和外键关联到会员
		Recharge recharge1 = new Recharge();
		recharge1.setPay(recharge.getPay());
		recharge1.setSn(recharge.getSn());
		recharge1.setPayDate(new Date());
		recharge1.setRemark(recharge.getRemark());
		recharge1.setMember(member);
		rechargeMapper.insert(recharge1);
	}

}
