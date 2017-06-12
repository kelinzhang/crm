package com._520it.crm.web.controller;

import com._520it.crm.domain.StockIncome;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StockIncomeQueryObject;
import com._520it.crm.service.IStockIncomeService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stockIncome")
public class StockIncomeController {
	@Autowired
	IStockIncomeService stockIncomeService;
	
	@RequestMapping("")
	public String index(){
		return "stockIncome";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(StockIncomeQueryObject qo){
		PageResult pageResult = null;
		pageResult = stockIncomeService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(StockIncome stockIncome){
		AjaxResult result = null;
		try{
			stockIncomeService.insert(stockIncome);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(StockIncome stockIncome){
		AjaxResult result = null;
		try{
			stockIncomeService.updateByPrimaryKey(stockIncome);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long stockIncomeId){
		AjaxResult result = null;
		try{
			stockIncomeService.deleteByPrimaryKey(stockIncomeId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/stockInput")
	public String stockInput(){
		return "/stockInput";
	}

	@RequestMapping("/listItem")
	@ResponseBody
	public PageResult listItem(Long id){
		PageResult pageResult = null;
		pageResult = stockIncomeService.listItem(id);
		return pageResult;
	}
}
