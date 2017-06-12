package com._520it.crm.mapper;

import com._520it.crm.domain.SystemMenu;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SystemMenu record);
    SystemMenu selectByPrimaryKey(Long id);
    List<SystemMenu> selectAll();
    int updateByPrimaryKey(SystemMenu record);
	Long queryByCondictionCount(QueryObject qo);
	List<SystemMenu> queryByConditionResult(QueryObject qo);
	List<SystemMenu> queryTree();

    List<Long> getEmpMenuIds(Long empId);
}