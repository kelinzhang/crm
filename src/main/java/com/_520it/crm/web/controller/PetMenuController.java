package com._520it.crm.web.controller;

import com._520it.crm.domain.PetMenu;
import com._520it.crm.service.IPetMenuService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/petMenu")
public class PetMenuController {
	@Autowired
	IPetMenuService petMenuService;
	@RequestMapping("")
	public String index(){
		return "petMenu";
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(PetMenu petMenu){
		AjaxResult result = null;
		try{
			petMenuService.insert(petMenu);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("啊哦~保存失败！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(PetMenu petMenu){
		AjaxResult result = null;
		try{
			petMenuService.update(petMenu);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult result = null;
		try {
			petMenuService.delete(id);
			result = new AjaxResult(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false, "删除失败");
		}
		return result;
	}
	@RequestMapping("/queryTree")
	@ResponseBody
	public List<PetMenu> queryTree(){
		List<PetMenu> result = petMenuService.queryTree();
		return result;
	}
	@RequestMapping("/queryChildrenTree")
	@ResponseBody
	public List<PetMenu> queryChildrenTree(Long parentId){
		List<PetMenu> result = petMenuService.queryChildrenTree(parentId);
		return result;
	}

	@RequestMapping("/selectByPrimaryKey")
	@ResponseBody
	public PetMenu selectByPrimaryKey(Long id){
		PetMenu pm = petMenuService.selectByPrimaryKey(id);
		return pm;
	}
}
