package com._520it.crm.web.interceptor;

import com._520it.crm.domain.Employee;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.RequiredPermission;
import com._520it.crm.util.UserContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

//权限检查拦截器
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }
        //判断当前用户是否为超级管理员
        Employee current = UserContext.getCurrentUser();
        if (current.isAdmin()) {
            return true;
        }
        //判断当前访问的方法是否需要权限
        HandlerMethod hm = (HandlerMethod) handler;
        RequiredPermission rp = hm.getMethod().getAnnotation(RequiredPermission.class);
        if (rp == null) {
            return true;
        }
        //判断当前用户是否拥有需要的权限
        Set<String> permissions = UserContext.getUserPermission();
        String expression = PermissionUtil.buildExp(hm.getMethod());
        if (permissions.contains(expression)) {
            return true;
        }
        //如果请求的是一个页面
        if (hm.getMethod().getAnnotation(ResponseBody.class) == null) {
            response.sendRedirect("/noPermission.jsp");
        } else {
            //如果请求的是ajax
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("{\"success\":false,\"msg\":\"你没有该权限!\"}");
        }
        return false;
    }
}
