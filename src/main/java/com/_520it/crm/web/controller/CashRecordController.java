package com._520it.crm.web.controller;

import com._520it.crm.domain.CashRecord;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CashRecordQueryObject;
import com._520it.crm.service.ICashRecordService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cashRecord")
public class CashRecordController {
	@Autowired
	ICashRecordService cashRecordService;
	
	@RequestMapping("")
	public String index(){
		return "cashRecord";
	}


	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(CashRecordQueryObject qo){
		PageResult pageResult = null;
		System.out.println("qo =============== "+qo);
		pageResult = cashRecordService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(CashRecord cashRecord){
		AjaxResult result = null;
		try{
			System.out.println("cashRecord = " + cashRecord);
			cashRecordService.insert(cashRecord);
			result = new AjaxResult(true,"保存成功");
		}catch(RuntimeException e){
			e.printStackTrace();
			result = new AjaxResult(e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员!");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(CashRecord cashRecord){
		AjaxResult result = null;
		try{
			cashRecordService.updateByPrimaryKey(cashRecord);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long cashRecordId){
		AjaxResult result = null;
		try{
			cashRecordService.deleteByPrimaryKey(cashRecordId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/listItem")
	@ResponseBody
	public PageResult listItem(Long id) {
        PageResult pageResult = null;
		pageResult=cashRecordService.listItem(id);
		return pageResult;
	}
}
