package com._520it.crm.service;
import com._520it.crm.domain.Member;
import com._520it.crm.domain.Recharge;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IMemberService {
	int deleteByPrimaryKey(Long id);
    int insert(Member record);
    Member selectByPrimaryKey(Long id);
    List<Member> selectAll();
    int updateByPrimaryKey(Member record);
	PageResult queryByConditionPage(QueryObject qo);

    Member queryByMember(String number);


    Member queryBack(String tel);


    void insertForPet(Member member);



    void insertForRecharge(Member member, Recharge recharge);


    void updateForPet(Member member);

    List<Member> memberChart();
}
