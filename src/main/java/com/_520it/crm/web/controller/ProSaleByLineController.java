package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("proSaleLine")
public class ProSaleByLineController {
	@RequestMapping("")
	public String getLine(){
		return "proSaleLine";
	}
}
