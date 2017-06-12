package com._520it.crm.web.controller;

import com._520it.crm.domain.Cash;
import com._520it.crm.domain.CashRecord;
import com._520it.crm.domain.PetService;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CashQueryObject;
import com._520it.crm.service.ICashService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cash")
public class CashController {
	@Autowired
	ICashService cashService;


	@RequestMapping("")
	public String index() {
		return "cash";
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(CashQueryObject qo) {
		PageResult pageResult = null;
		pageResult = cashService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/listNumber")
	@ResponseBody
	public List<Cash> listNumber(String number) {
		return cashService.listNumber(number);
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Cash cash) {
		AjaxResult result = null;
		try {
			cashService.insert(cash);
			result = new AjaxResult(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Cash cash) {
		AjaxResult result = null;
		try {
			cashService.updateByPrimaryKey(cash);
			result = new AjaxResult(true, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long cashId) {
		AjaxResult result = null;
		try {
			//System.out.println("cashId = " + cashId);
			cashService.deleteByPrimaryKey(cashId);
			result = new AjaxResult(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/selectSingleRowById")
	@ResponseBody
	public CashRecord selectSingleRowById(Long id){
		CashRecord cashRecord = null;
		cashRecord=cashService.selectSingleRowById(id);
		return cashRecord;
	}
	@RequestMapping("/listPrice")
	@ResponseBody
	public BigDecimal listPrice(){
		CashRecord cashRecord = null;
		BigDecimal lsitPri=cashService.listPrice();
		return lsitPri;
	}
	@RequestMapping("/listPriceSale")
	@ResponseBody
	public BigDecimal listPriceSale(){
		CashRecord cashRecord = null;
		BigDecimal lsitPri=cashService.listPriceSale();
		return lsitPri;
	}
	@RequestMapping("/savePetService")
	@ResponseBody
	public AjaxResult savePetService(Long rowData){
		AjaxResult result = null;
		try {
			PetService ps =  cashService.queryOnePetService(rowData);
			System.out.println(ps);
			cashService.saveBill(ps);
			result = new AjaxResult(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}

}

