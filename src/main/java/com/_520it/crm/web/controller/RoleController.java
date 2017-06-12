package com._520it.crm.web.controller;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.service.IRoleService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	IRoleService roleService;
	
	@RequestMapping("")
	public String index(){
		return "role";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(RoleQueryObject qo){
		PageResult pageResult = null;
		pageResult = roleService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Role role){
		AjaxResult result = null;
		try{
			roleService.insert(role);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Role role){
		AjaxResult result = null;
		try{
			roleService.updateByPrimaryKey(role);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long roleId){
		AjaxResult result = null;
		try{
			roleService.deleteByPrimaryKey(roleId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/selectListForEmployeeForm")
	@ResponseBody
	public List<Role> selectListForEmployeeForm(){
		List<Role> result = roleService.selectListForEmployeeForm();
		return result;
	}
	@RequestMapping("/queryRoleByEmp")
	@ResponseBody
	public List<Long> queryRoleByEmp(Long empId){
		List<Long> result = roleService.queryRoleByEmp(empId);
		return result;
	}
	@RequestMapping("/addMenu")
	@ResponseBody
	public AjaxResult addMenu(@RequestParam("ids[]") ArrayList<Long> ids, Long roleId){
		AjaxResult result = null;
		try{
			roleService.addmenu(ids,roleId);
			result = new AjaxResult(true,"菜单添加成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("菜单添加失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/getRoleMenuId")
	@ResponseBody
	public List<Long> getRoleMenuId(Long roleId){
		List<Long> result = roleService.getRoleMenuId(roleId);
		return result;
	}
}
