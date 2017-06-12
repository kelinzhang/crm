package com._520it.crm.service.impl;

import com._520it.crm.domain.Cash;
import com._520it.crm.domain.CashRecord;
import com._520it.crm.domain.Member;
import com._520it.crm.domain.PetService;
import com._520it.crm.mapper.CashMapper;
import com._520it.crm.mapper.PetServiceMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ICashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
@Service
public class CashServiceImpl implements ICashService {
	@Autowired
	private CashMapper cashMapper;
	@Autowired
	private PetServiceMapper petServiceMapper;

	public int deleteByPrimaryKey(Long id) {
		return cashMapper.deleteByPrimaryKey(id);
	}

	public int insert(Cash record) {

		return cashMapper.insert(record);
	}

	public Cash selectByPrimaryKey(Long id) {
		return cashMapper.selectByPrimaryKey(id);
	}

	public List<Cash> selectAll() {
		return cashMapper.selectAll();
	}

	public int updateByPrimaryKey(Cash record) {
		return cashMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = cashMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Cash> result = cashMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public Member queryByMember(String number) {
		return cashMapper.queryByMember(number);
	}

	@Override
	public CashRecord selectSingleRowById(Long id) {
		return cashMapper.selectSingleRowById(id);
	}

	@Override
	public BigDecimal listPrice() {
		return cashMapper.listPrice();
	}

	@Override
	public BigDecimal listPriceSale() {
		return cashMapper.listPriceSale();
	}

	@Override
	public PetService queryOnePetService(Long id) {
		return petServiceMapper.queryOnePetService(id);
	}

	@Override
	public int saveBill(PetService ps) {
		return  cashMapper.saveBill(ps);
	}

	@Override
	public void clear(String number) {
           cashMapper.clear(number);
	}


	@Override
	public List<Cash> listNumber(String number) {
		return cashMapper.listNumber(number);
	}


}
