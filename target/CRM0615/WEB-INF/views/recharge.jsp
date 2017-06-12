<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/recharge.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="recharge_datagrid">
		<thead>
			<tr>
				<th data-options="field:'member',width:1,align:'center'">会员</th>
				<th data-options="field:'pay',width:1,align:'center'">充值金额</th>
				<th data-options="field:'payDate',width:1,align:'center'">充值时间</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="recharge_dialog">
		<form id="recharge_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>会员</td>
				<td><input type="text" name="member"></td>
			</tr>
			<tr>
				<td>充值金额</td>
				<td><input type="text" name="pay"></td>
			</tr>
			<tr>
				<td>充值时间</td>
				<td><input type="text" name="payDate"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="recharge_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="recharge_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>