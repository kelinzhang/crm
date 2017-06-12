package com._520it.crm.test;

import com._520it.crm.domain.Employee;
import com._520it.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeTest {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void test() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("admin");
        employee.setRealname("管理员");
        employee.setPassword("1");
        employee.setEmail("admin@163.com");
        employee.setState(Employee.STATE_NORMAL);
        employee.setAdmin(true);
        employee.setTel("13011112222");
        employeeService.insert(employee);
    }
}
