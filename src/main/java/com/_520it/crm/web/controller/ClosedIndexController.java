package com._520it.crm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("closedIndex")
public class ClosedIndexController {
	@RequestMapping("/close")
	@ResponseBody
	public void closedIndex(HttpServletRequest request){
		request.getSession().invalidate();
	}
}