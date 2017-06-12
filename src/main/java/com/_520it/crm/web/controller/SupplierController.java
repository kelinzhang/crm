package com._520it.crm.web.controller;

import com._520it.crm.domain.Supplier;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SupplierQueryObject;
import com._520it.crm.service.ISupplierService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	ISupplierService supplierService;
	
	@RequestMapping("")
	public String index(){
		return "supplier";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SupplierQueryObject qo){
		PageResult pageResult = null;
		pageResult = supplierService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Supplier supplier){
		AjaxResult result = null;
		try{
			supplierService.insert(supplier);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Supplier supplier){
		AjaxResult result = null;
		try{
			supplierService.updateByPrimaryKey(supplier);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long supplierId){
		AjaxResult result = null;
		try{
			supplierService.deleteByPrimaryKey(supplierId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Supplier> getAll(){
		List<Supplier> result = new ArrayList<>();
		try{
			result = supplierService.selectAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
