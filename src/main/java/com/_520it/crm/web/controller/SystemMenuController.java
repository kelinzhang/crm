package com._520it.crm.web.controller;

import com._520it.crm.domain.SystemMenu;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SystemMenuQueryObject;
import com._520it.crm.service.ISystemMenuService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemMenu")
public class SystemMenuController {
	@Autowired
	ISystemMenuService systemMenuService;
	
	@RequestMapping("")
	public String index(){
		return "systemMenu";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SystemMenuQueryObject qo){
		PageResult pageResult = null;
		pageResult = systemMenuService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemMenu systemMenu){
		AjaxResult result = null;
		try{
			systemMenuService.insert(systemMenu);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(SystemMenu systemMenu){
		AjaxResult result = null;
		try{
			systemMenuService.updateByPrimaryKey(systemMenu);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("菜单更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long systemMenuId){
		AjaxResult result = null;
		try{
			systemMenuService.deleteByPrimaryKey(systemMenuId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("菜单删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/queryTree")
	@ResponseBody
	public List<SystemMenu> queryTree(){
		List<SystemMenu> result = systemMenuService.queryTree();
		return result;
	}
	@RequestMapping("/roleMenu")
	@ResponseBody
	public List<SystemMenu> roleMenu(){
		List<SystemMenu> result = systemMenuService.roleMenu();
		return result;
	}
	@RequestMapping("/indexMenu")
	@ResponseBody
	public List<SystemMenu> indexMenu(){
		List<SystemMenu> menus = UserContext.getUserMenus();
		System.out.println("menus = " + menus);
		List<SystemMenu> result = menus;
		return result;
	}
}
