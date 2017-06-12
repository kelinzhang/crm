package com._520it.crm.mapper;

import com._520it.crm.domain.Permission;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Permission record);
    Permission selectByPrimaryKey(Long id);
    List<Permission> selectAll();
    int updateByPrimaryKey(Permission record);
	Long queryByCondictionCount(QueryObject qo);
	List<Permission> queryByConditionResult(QueryObject qo);

    List<Permission> queryByRole(Long roleId);

    List<Permission> getPerByEmp(Long empId);
}