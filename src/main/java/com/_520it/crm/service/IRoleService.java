package com._520it.crm.service;
import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.ArrayList;
import java.util.List;

public interface IRoleService {
	int deleteByPrimaryKey(Long id);
    int insert(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);
	PageResult queryByConditionPage(QueryObject qo);

    List<Role> selectListForEmployeeForm();

    List<Long> queryRoleByEmp(Long empId);

    void addmenu(ArrayList<Long> ids, Long roleId);

    List<Long> getRoleMenuId(Long roleId);
}
