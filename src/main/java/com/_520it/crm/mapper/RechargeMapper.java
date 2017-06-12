package com._520it.crm.mapper;

import com._520it.crm.domain.Recharge;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface RechargeMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Recharge record);
    Recharge selectByPrimaryKey(Long id);
    List<Recharge> selectAll();
    int updateByPrimaryKey(Recharge record);
	Long queryByCondictionCount(QueryObject qo);
	List<Recharge> queryByConditionResult(QueryObject qo);
    Long selectNewRechargeId();
}