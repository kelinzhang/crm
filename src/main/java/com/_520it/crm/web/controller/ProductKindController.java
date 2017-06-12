package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductKind;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductKindQueryObject;
import com._520it.crm.service.IProductKindService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productKind")
public class ProductKindController {
	@Autowired
	IProductKindService productKindService;
	
	@RequestMapping("")
	public String index(){
		return "productKind";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(ProductKindQueryObject qo){
		PageResult pageResult = null;
		pageResult = productKindService.queryByConditionPage(qo);
		return pageResult;
	}
	
	@RequestMapping("/listAll")
	@ResponseBody
	public List<ProductKind> listAll(){
		List<ProductKind> listAll = productKindService.selectAll();
		return listAll;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(ProductKind productKind){
		AjaxResult result = null;
		try{
			productKindService.insert(productKind);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(ProductKind productKind){
		AjaxResult result = null;
		try{
			productKindService.updateByPrimaryKey(productKind);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long productKindId){
		AjaxResult result = null;
		try{
			productKindService.deleteByPrimaryKey(productKindId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public List<ProductKind> getAll(){
		List<ProductKind> result = new ArrayList<>();
		try{
			result = productKindService.selectAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
