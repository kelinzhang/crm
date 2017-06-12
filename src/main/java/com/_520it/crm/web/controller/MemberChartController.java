package com._520it.crm.web.controller;

import com._520it.crm.service.IMemberChartService;
import com._520it.crm.vo.MemberVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memberChart")
public class MemberChartController {
    @Autowired
    private IMemberChartService memberChartService;

    @RequestMapping("")
    public String memberChart() {
        return "memberChart";
    }


    @RequestMapping("/viewByLine")
    public String viewByLine(HttpServletRequest request) {
        List<Object[]> lists = new ArrayList<>();
        List<MemberVO> vos = memberChartService.memberChartByLine();
        for (MemberVO vo : vos) {
            lists.add(new Object[]{vo.getNumber(),vo.getConsume()});

        }
        request.getSession().setAttribute("lists", JSON.toJSONString(lists));
        return "viewByLine";

    }

}
