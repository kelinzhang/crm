package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("petChart")
public class PetChartController {

	@RequestMapping("")
	public String getChart(){
		return "petChart";
	}
}
