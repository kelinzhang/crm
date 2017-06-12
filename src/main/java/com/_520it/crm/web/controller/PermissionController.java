package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.PermissionQueryObject;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	IPermissionService permissionService;
	
	@RequestMapping("")
	public String index(){
		return "permission";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(PermissionQueryObject qo){
		PageResult pageResult = null;
		pageResult = permissionService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/reload")
	@ResponseBody
	public AjaxResult reload(){
		AjaxResult result = null;
		try{
			permissionService.reload();
			result = new AjaxResult(true,"权限加载成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("权限加载失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/queryForRoleForm")
	@ResponseBody
	public PageResult queryForRoleForm(){
		PageResult pageResult = null;
		pageResult = permissionService.queryForRoleForm();
		return pageResult;
	}
	@RequestMapping("/queryByRole")
	@ResponseBody
	public PageResult queryByRole(Long roleId){
		PageResult pageResult = null;
		pageResult = permissionService.queryByRole(roleId);
		return pageResult;
	}
}
