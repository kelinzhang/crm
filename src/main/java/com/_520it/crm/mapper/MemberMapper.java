package com._520it.crm.mapper;

import com._520it.crm.domain.Member;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Member record);
    Member selectByPrimaryKey(Long id);
    List<Member> selectAll();
    int updateByPrimaryKey(Member record);
	Long queryByCondictionCount(QueryObject qo);
	List<Member> queryByConditionResult(QueryObject qo);

    Member queryByMember(String number);

    //杨雪原狗的查询
    Member queryBack(String tel);

    List<Member> memberChart();

    Member selectByNum(String number);
}