<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
	<%--页面布局--%>
	<div id="role_menu" class="easyui-layout" fit="true">
		<div id="role_menuTitle" data-options="region:'west',title:'角色[]的菜单',width:230,border:false,border:false,tools:'#role_menu_tt'">
			<ul id="role_menuTree"></ul>
		</div>
		<div data-options="region:'center'">
			<!-- 数据表格 -->
			<table id="role_datagrid">
				<thead>
				<tr>
					<%--<th data-options="field:'permissions',width:1,align:'center'">角色权限</th>--%>
					<th data-options="field:'name',width:1,align:'center'">角色名称</th>
					<th data-options="field:'sn',width:1,align:'center'">角色编码</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- 新增编辑对话框 -->
	<div id="role_dialog">
		<form id="role_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>角色名称<input type="text" name="name"></td>
				<td>角色编码<input type="text" name="sn"></td>
			</tr>
			<tr>
				<td><div id="allPermissions"></div></td>
				<td><div id="selfPermissions"></div></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="role_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="role_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
	<%--菜单栏工具按钮--%>
	<div id="role_menu_tt">
		<a class="icon-save"  data-cmd="addMenu"></a>
	</div>
</body>
</html>