package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductBrandQueryObject;
import com._520it.crm.service.IProductBrandService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productBrand")
public class ProductBrandController {
	@Autowired
	IProductBrandService productBrandService;
	
	@RequestMapping("")
	public String index(){
		return "productBrand";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(ProductBrandQueryObject qo){
		PageResult pageResult = null;
		pageResult = productBrandService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/listAll")
	@ResponseBody
	public List<ProductBrand> listAll(){
		List<ProductBrand> listAll = productBrandService.selectAll();
		return listAll;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(ProductBrand productBrand){
		AjaxResult result = null;
		try{
			productBrandService.insert(productBrand);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(ProductBrand productBrand){
		AjaxResult result = null;
		try{
			productBrandService.updateByPrimaryKey(productBrand);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long productBrandId){
		AjaxResult result = null;
		try{
			productBrandService.deleteByPrimaryKey(productBrandId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public List<ProductBrand> getAll(){
		List<ProductBrand> result = new ArrayList<>();
		try{
			result = productBrandService.selectAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
