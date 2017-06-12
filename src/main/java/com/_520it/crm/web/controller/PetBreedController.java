package com._520it.crm.web.controller;

import com._520it.crm.domain.PetBreed;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetBreedQueryObject;
import com._520it.crm.service.IPetBreedService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/petBreed")
public class PetBreedController {
	@Autowired
	IPetBreedService petBreedService;
	
	@RequestMapping("")
	public String index(){
		return "petBreed";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(PetBreedQueryObject qo){
		PageResult pageResult = null;
		pageResult = petBreedService.queryByConditionPage(qo);
		System.out.println(pageResult);
		return pageResult;
	}
	@RequestMapping("/selectListForPetForm")
	@ResponseBody
	public List<PetBreed> selectListForPetForm(){
		return petBreedService.selectAll();
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(PetBreed petBreed){
		AjaxResult result = null;
		try{
			petBreedService.insert(petBreed);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(PetBreed petBreed){
		AjaxResult result = null;
		try{
			petBreedService.updateByPrimaryKey(petBreed);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long petBreedId){
		AjaxResult result = null;
		try{
			petBreedService.deleteByPrimaryKey(petBreedId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

	@RequestMapping("/selectForFather")
	@ResponseBody
	public List<PetBreed> selectForFather(){

		return petBreedService.selectForFather();
	}

	@RequestMapping("/selectForChildren")
	@ResponseBody
	public List<PetBreed> selectForChildren(Long parentId){

		return petBreedService.selectForChildren(parentId);
	}
}
