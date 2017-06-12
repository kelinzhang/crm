package com._520it.crm.web.controller;

import com._520it.crm.domain.Member;
import com._520it.crm.domain.Recharge;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MemberQueryObject;
import com._520it.crm.service.IMemberService;
import com._520it.crm.service.IRechargeService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	IMemberService memberService;
	@Autowired
	IRechargeService rechargeService;

	@RequestMapping("")
	public String index(){
		return "member";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(MemberQueryObject qo){
		PageResult pageResult = null;
		pageResult = memberService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/selectOne")
	@ResponseBody
	public Member selectOne(String number){
		Member member=null;
		member = memberService.queryByMember(number);
		return member;
	}
	@RequestMapping("/selectById")
	@ResponseBody
	public Member selectById(Long memberId){
		return memberService.selectByPrimaryKey(memberId);
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Member member){
		AjaxResult result = null;
		try{
			memberService.insert(member);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/credit")
	@ResponseBody
	public AjaxResult credit(@ModelAttribute("mem")Member member,@ModelAttribute("rec") Recharge recharge){
		AjaxResult result = null;
		try{
			member = memberService.selectByPrimaryKey(member.getId());
			memberService.insertForRecharge(member,recharge);
			rechargeService.insertByMember(member,recharge);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Member member){
		AjaxResult result = null;
		try{
			memberService.updateByPrimaryKey(member);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long memberId){
		AjaxResult result = null;
		try{
			memberService.deleteByPrimaryKey(memberId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/queryBack")
	@ResponseBody
	public Member queryBack(String tel){
		Member m = memberService.queryBack(tel);

		return m;
	}
	@InitBinder("mem")
	public void initBinder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("member.");
	}
	@InitBinder("rec")
	public void initBinder1(WebDataBinder binder){
		binder.setFieldDefaultPrefix("recharge.");
	}
	@RequestMapping("/memberChart")
	@ResponseBody
	public List<Member> memberChart() {
		return memberService.memberChart();
	}
}
