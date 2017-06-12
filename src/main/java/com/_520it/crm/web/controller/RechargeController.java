package com._520it.crm.web.controller;

import com._520it.crm.domain.Recharge;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RechargeQueryObject;
import com._520it.crm.service.IRechargeService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recharge")
public class RechargeController {
	@Autowired
	IRechargeService rechargeService;
	
	@RequestMapping("")
	public String index(){
		return "recharge";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(RechargeQueryObject qo){
		PageResult pageResult = null;
		pageResult = rechargeService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Recharge recharge){
		AjaxResult result = null;
		try{
			rechargeService.insert(recharge);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Recharge recharge){
		AjaxResult result = null;
		try{
			rechargeService.updateByPrimaryKey(recharge);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long rechargeId){
		AjaxResult result = null;
		try{
			rechargeService.deleteByPrimaryKey(rechargeId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
