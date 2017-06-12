package com._520it.crm.util;

import com._520it.crm.domain.Employee;

import java.lang.reflect.Method;
import java.util.Set;

public class PermissionUtil {
    public static String buildExp(Method m) {
        Class clz = m.getDeclaringClass();
        return clz.getName() + ":" + m.getName();
    }

    //判断登录用户是否拥有权限
    public static boolean checkPermission(String expression) {
        //判断该用户是否是超级管理员
        Employee current = UserContext.getCurrentUser();
        if (current.isAdmin()) {
            return true;
        }
        //判断该表达式是否受权限控制
        //获取该表达式对应的方法
        Method method = PermissionUtil.getMethodByExpression(expression);
        if (method.getAnnotation(RequiredPermission.class) == null) {
            //判断该方法是否贴有标签
            return true;
        }
        //获取用户权限集合,判断是否拥有该权限
        Set<String> expressions = UserContext.getUserPermission();
        if(expressions.contains(expression)){
            return true;
        }
        return false;
    }

    private static Method getMethodByExpression(String expression) {
        String[] args = expression.split(":");
        if (args.length != 2) {
            return null;
        }
        try {
            Class clz = Class.forName(args[0]);
            Method[] methods = clz.getMethods();
            for (Method method : methods) {
                if (args[1].equals(method.getName())) {
                    return method;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
