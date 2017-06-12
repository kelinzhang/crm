package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.page.PageResult;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    void login(String username, String password,String authCode);

    PageResult query(EmployeeQueryObject qo);

    void quit(Long id);
}
