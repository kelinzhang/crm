package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.service.IProductStockService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productStock")
public class ProductStockController {
	@Autowired
	IProductStockService productStockService;
	
	@RequestMapping("")
	public String index(){
		return "productStock";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(ProductStockQueryObject qo){
		PageResult pageResult = null;
		System.out.println("qo = " + qo);
		pageResult = productStockService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(ProductStock productStock){
		AjaxResult result = null;
		try{
			productStockService.insert(productStock);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(ProductStock productStock){
		AjaxResult result = null;
		try{
			productStockService.updateByPrimaryKey(productStock);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long productStockId){
		AjaxResult result = null;
		try{
			productStockService.deleteByPrimaryKey(productStockId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

}

