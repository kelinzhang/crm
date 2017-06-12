package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/systemSetting")
public class SystemSettinController {

	@RequestMapping("")
	public String index(){
		return "systemSetting";
	}
}
