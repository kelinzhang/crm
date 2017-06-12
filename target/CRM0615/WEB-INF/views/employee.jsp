<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="CRMFn" uri="http://www.520it.com/crm/functions" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工信息</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>

<table id="employeeData"></table>
<div id="employeeData_tb">
    <div>
        <c:set value="com._520it.crm.web.controller.EmployeeController:save" var="expression"/>
        <c:if test="${CRMFn:checkPermission(expression)}">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">增加</a>
        </c:if>
        <c:set value="com._520it.crm.web.controller.EmployeeController:update" var="expression"/>
        <c:if test="${CRMFn:checkPermission(expression)}">
            <a class="easyui-linkbutton" id="btn_edit" data-options="iconCls:'icon-edit',plain:true"
               data-cmd="edit">编辑</a>
        </c:if>
        <a class="easyui-linkbutton" id="btn_quit" data-options="iconCls:'icon-remove',plain:true"
           data-cmd="del">删除</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="flush">刷新</a>
    </div>
    <div>
        关键字:<input type="text" name="keyword">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query">搜索</a>
    </div>
</div>
<div id="myDialog_bt">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="myDialog">
    <form id="myForm" method="post" action="/employee_save.do">
        <table style="margin-top: 15px;" align="center">
            <input type="hidden" name="id">
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text" name="realname"></td>
            </tr>
            <tr>
                <td>联系方式</td>
                <td><input type="text" name="tel"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>所属部门</td>
                <td>
                    <input class="easyui-combobox" name="dept.id"
                           data-options="valueField:'id',textField:'name',url:'/department/selectListForEmployeeForm'"/>
                </td>
            </tr>
            <tr>
                <td>角色</td>
                <td>
                    <input id="emp_role" class="easyui-combobox"
                           data-options="valueField:'id',multiple:true,textField:'name',url:'/role/selectListForEmployeeForm'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
