package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stockChart")
public class StockChartController {

	@RequestMapping("")
	public String getChart(){
		return "stockChart";
	}
}
