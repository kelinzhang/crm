package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("")
    @RequiredPermission("员工列表")
    public String index() {
        System.out.println("in..." + employeeService);
        return "employee";
    }

    @RequestMapping("/login")
    @ResponseBody
    @RequiredPermission("员工登录")
    public AjaxResult login(String username, String password,String authCode) {

        AjaxResult result = null;
        try {
            employeeService.login(username, password,authCode);
            result = new AjaxResult(true, "登陆成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiredPermission("员工数据")
    public PageResult list(EmployeeQueryObject qo) {
        PageResult result = employeeService.query(qo);
        return result;
    }
    @RequestMapping("/selectListForDepositForm")
    @ResponseBody
    public List<Employee> selectListForDepositForm() {

        return employeeService.selectAll();
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiredPermission("员工保存")
    public AjaxResult save(Employee emp) {
        AjaxResult result = null;
        try {
            employeeService.insert(emp);
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "保存失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiredPermission("员工更改")
    public AjaxResult update(Employee emp) {
        AjaxResult result = null;
        try {
            employeeService.updateByPrimaryKey(emp);
            result = new AjaxResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "修改失败");
        }
        return result;
    }

    @RequestMapping("/quit")
    @ResponseBody
    @RequiredPermission("员工离职")
    public AjaxResult quit(Long id) {
        AjaxResult result = null;
        try {
            employeeService.quit(id);
            result = new AjaxResult(true, "离职成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "离职失败");
        }
        return result;
    }

}
