package com._520it.crm.web.controller;

import com._520it.crm.domain.PetService;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetServiceQueryObject;
import com._520it.crm.service.IPetServiceService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/petService")
public class PetServiceController {
	@Autowired
	IPetServiceService petServiceService;
	
	@RequestMapping("")
	public String index(){
		return "petService";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(PetServiceQueryObject qo){
		PageResult pageResult = null;
		pageResult = petServiceService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(PetService petService){
		AjaxResult result = null;
		try{
			petServiceService.insert(petService);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Long id){
		AjaxResult result = null;
		try{
			PetService petService = petServiceService.selectByPrimaryKey(id);
			petServiceService.updateByPrimaryKey(petService);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long petServiceId){
		AjaxResult result = null;
		try{
			petServiceService.deleteByPrimaryKey(petServiceId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

}
