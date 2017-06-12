package com._520it.crm.web.controller;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.VipLevelQueryObject;
import com._520it.crm.service.IVipLevelService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/vipLevel")
public class VipLevelController {
	@Autowired
	IVipLevelService vipLevelService;
	
	@RequestMapping("")
	public String index(){
		return "vipLevel";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(VipLevelQueryObject qo){
		PageResult pageResult = null;
		pageResult = vipLevelService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/selectListForDepositForm")
	@ResponseBody
	public List<VipLevel> selectListForDepositForm(){
		return  vipLevelService.selectAll();
	}
	@RequestMapping("/queryLevel")
	@ResponseBody
	public VipLevel queryLevel(Long levelId){
		return  vipLevelService.selectByPrimaryKey(levelId);
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(VipLevel vipLevel){
		AjaxResult result = null;
		try{
			vipLevelService.insert(vipLevel);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(VipLevel vipLevel){
		AjaxResult result = null;
		try{
			vipLevelService.updateByPrimaryKey(vipLevel);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long vipLevelId){
		AjaxResult result = null;
		try{
			vipLevelService.deleteByPrimaryKey(vipLevelId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
}
