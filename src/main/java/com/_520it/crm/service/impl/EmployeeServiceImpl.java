package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.domain.SystemMenu;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.service.ISystemMenuService;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        record.setState(Employee.STATE_NORMAL);
        record.setAdmin(false);
        record.setInputtime(new Date());
        record.setPassword("123");
        int count = employeeMapper.insert(record);
        //处理中间表关系
        for (Role role : record.getRoles()) {
            employeeMapper.insertRelation(role.getId(),record.getId());
        }
        return count;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        //删除旧的关联关系
        employeeMapper.deleteRelation(record.getId());
        //处理中间表关系
        for (Role role : record.getRoles()) {
            employeeMapper.insertRelation(role.getId(),record.getId());
        }
        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public void login(String username, String password,String authCode) {
        Employee current = employeeMapper.getUser(username,password);
        if (current == null) {
            throw new RuntimeException("账号或密码错误!");
        }
      
        //判断验证码
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getSession().getAttribute("strCode")+"---------------"+authCode);
        if(!request.getSession().getAttribute("strCode").equals(authCode)){
        	throw new RuntimeException("验证码错误!");
        }

        UserContext.setCurrentUser(current);
        //将当前用户的名称存到session中
        request.getSession().setAttribute("USER_NAME",current.getUsername());

        Set<String> expSet = new HashSet<>();
        List<Permission> permissions = permissionService.getPerByEmp(current.getId());
        for (Permission permission : permissions) {
            expSet.add(permission.getResource());
        }
        UserContext.setUserPermission(expSet);
        List<SystemMenu> userMenus = systemMenuService.indexMenu();
        UserContext.setUserMenus(userMenus);
        
        
    }

    @Override
    public PageResult query(EmployeeQueryObject qo) {
        Long count = employeeMapper.queryCount(qo);
        if(count <= 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Employee> result = employeeMapper.queryList(qo);
        return new PageResult(count,result);
    }

    @Override
    public void quit(Long id) {
        employeeMapper.changeState(id,Employee.STATE_QUIT);
    }
}
