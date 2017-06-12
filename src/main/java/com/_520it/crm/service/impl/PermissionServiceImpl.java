package com._520it.crm.service.impl;

import com._520it.crm.domain.Permission;
import com._520it.crm.mapper.PermissionMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.RequiredPermission;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Service
public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
    @Autowired
    private PermissionMapper permissionMapper;
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryByConditionPage(QueryObject qo) {
        Long count = permissionMapper.queryByCondictionCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Permission> result = permissionMapper.queryByConditionResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public void reload() {

        List<Permission> permissions = permissionMapper.selectAll();
        Set<String> expSet = new HashSet<>();
        for (Permission p : permissions) {
            expSet.add(p.getResource());
        }

        //获取所有控制器上的方法
        RequestMappingHandlerMapping rmhm = ctx.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> hanlderMethods = rmhm.getHandlerMethods();
        RequiredPermission rp = null;
        Permission p = null;
        for (HandlerMethod method : hanlderMethods.values()) {
            rp = method.getMethod().getAnnotation(RequiredPermission.class);
            if (rp == null) {
                continue;
            }
            String expression = PermissionUtil.buildExp(method.getMethod());
            if (!expSet.contains(expression)) {
                p = new Permission();
                p.setName(rp.value());
                p.setResource(expression);
                permissionMapper.insert(p);
            }
        }
    }

    @Override
    public PageResult queryForRoleForm() {
        return new PageResult(Long.MAX_VALUE,permissionMapper.selectAll());
    }

    @Override
    public PageResult queryByRole(Long roleId) {
        return new PageResult(Long.MAX_VALUE,permissionMapper.queryByRole(roleId));
    }

    @Override
    public List<Permission> getPerByEmp(Long empId) {
        return permissionMapper.getPerByEmp(empId);
    }

}
