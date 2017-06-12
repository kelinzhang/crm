package com._520it.crm.service;
import com._520it.crm.domain.Member;
import com._520it.crm.domain.Recharge;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IRechargeService {
	int deleteByPrimaryKey(Long id);
    int insert(Recharge record);
    Recharge selectByPrimaryKey(Long id);
    List<Recharge> selectAll();
    int updateByPrimaryKey(Recharge record);
	PageResult queryByConditionPage(QueryObject qo);

    void insertForMember(Member member,Recharge recharge);

    void insertByMember(Member member, Recharge recharge);
}
