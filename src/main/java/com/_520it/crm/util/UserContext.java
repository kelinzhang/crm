package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemMenu;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class UserContext {

    private UserContext(){}

    public static final String USER_IN_SESSION = "userinsession";
    public static final String PERMISSION_IN_SESSION = "permissioninsession";
    public static final String MENU_IN_SESSION = "usermenuinsession";

    public static void setCurrentUser(Employee emp){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(USER_IN_SESSION,emp);
    }

    public static Employee getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Employee) request.getSession().getAttribute(USER_IN_SESSION);
    }

    public static void setUserPermission(Set<String> userPermission) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(PERMISSION_IN_SESSION,userPermission);
    }

    public static Set<String> getUserPermission(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Set<String>) request.getSession().getAttribute(PERMISSION_IN_SESSION);
    }

    public static void setUserMenus(List<SystemMenu> userMenus) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(MENU_IN_SESSION,userMenus);
    }

    public static List<SystemMenu> getUserMenus(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (List<SystemMenu>) request.getSession().getAttribute(MENU_IN_SESSION);
    }
}
