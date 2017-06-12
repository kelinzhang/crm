package com._520it.crm.service.impl;

import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.RoleMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public int insert(Role record) {
		int count = roleMapper.insert(record);
		//处理关联关系
		for (Permission p : record.getPermissions()) {
			roleMapper.handleRelation(p.getId(),record.getId());
		}
		return count;
	}

	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	public int updateByPrimaryKey(Role record) {
		int acount = roleMapper.updateByPrimaryKey(record);
		//处理关联关系(删除外键关系)
		roleMapper.deleteRelation(record.getId());
		//处理中间表
		for (Permission p : record.getPermissions()) {
			roleMapper.handleRelation(p.getId(),record.getId());
		}
		return acount;
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = roleMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Role> result = roleMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<Role> selectListForEmployeeForm() {

		return roleMapper.selectAll();
	}

	@Override
	public List<Long> queryRoleByEmp(Long empId) {
		return roleMapper.queryRoleByEmp(empId);
	}

	@Override
	public void addmenu(ArrayList<Long> ids, Long roleId) {
		roleMapper.deleteMenu(roleId);
		for (Long menuId : ids) {
			roleMapper.addMenu(menuId,roleId);
		}
	}

	@Override
	public List<Long> getRoleMenuId(Long roleId) {
		return roleMapper.getRoleMenuId(roleId);
	}
}
