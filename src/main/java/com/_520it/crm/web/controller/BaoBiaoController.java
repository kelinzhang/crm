package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetServiceChartQueryObject;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.service.IBaoBiaoService;
import com._520it.crm.vo.PetServiceVO;
import com._520it.crm.vo.StockAllVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("baobiao")
public class BaoBiaoController {
	@Autowired
	IBaoBiaoService baobiaoService;
	
	

	@RequestMapping("")
	public String getJsp(){
		return "baobiao";
	}

	@RequestMapping("/queryPetServiceData")
	@ResponseBody
	public PageResult queryPetServiceData(PetServiceChartQueryObject qo){
		PageResult result =  baobiaoService.queryPetServiceData(qo);
		System.out.println(result);
		return  result;
	}
	//查询库存数据
	@RequestMapping("/queryStockData")
	@ResponseBody
	public PageResult queryStockData(ProductStockQueryObject qo,String changeId){
		System.out.println("---------"+changeId+"---");
		PageResult result =null;
		/*if("1".equals(changeId)){
			//按品牌分组
			result =  baobiaoService.queryStockBrandData(qo);
		}else if("2".equals(changeId)){
			//按种类分组
			result = baobiaoService.queryStockKindData(qo);
		}else{
			//按供应商分组
			result = baobiaoService.queryStockSupplierData(qo);
		}*/
		if(changeId.endsWith("1")){
			//按品牌分组
			result =  baobiaoService.queryStockBrandData(qo);
		}else if(changeId.endsWith("2")){
			//按种类分组
			result = baobiaoService.queryStockKindData(qo);
			
		}else{
			//按供应商分组
			//result = baobiaoService.queryStockSupplierData(qo);
		}

		return  result;
	}
	
	
	
	
	

	@RequestMapping("showWindow")
	public String showWindow(HttpServletRequest request){
		List<Object[]> datas = new ArrayList<>();
		List<PetServiceVO> psVO = baobiaoService.queryPetServiceDataForPie();
		for (PetServiceVO vo : psVO) {
			datas.add(new Object[]{vo.getGroupByServiceName(),vo.getTotalNumber()});
		}
		request.getSession().setAttribute("psVOjSON", JSON.toJSONString(datas));
		return "petServicePie";
	}
	@RequestMapping("showWindowBrand")
	public String showWindowBrand(HttpServletRequest request){
		List<Object[]> datas = new ArrayList<>();
		List<StockAllVO> psVO = baobiaoService.queryStockBrandDataForPie();
		for (StockAllVO vo : psVO) {
			datas.add(new Object[]{vo.getGroupByStockName(),vo.getTotalNumber()});
		}
		request.getSession().setAttribute("brandVOjSON", JSON.toJSONString(datas));
		return "proBrandPie";
	}
	@RequestMapping("showWindowKind")
	public String showWindowKind(HttpServletRequest request){
		List<Object[]> datas = new ArrayList<>();
		List<StockAllVO> psVO = baobiaoService.queryStockKindDataForPie();
		for (StockAllVO vo : psVO) {
			datas.add(new Object[]{vo.getGroupByStockName(),vo.getTotalNumber()});
		}
		request.getSession().setAttribute("kindVOjSON", JSON.toJSONString(datas));
		return "proKindPie";
	}
}
