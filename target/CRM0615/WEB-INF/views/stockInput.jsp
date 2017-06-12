<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品入库管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/stockInput.js"></script>
</head>
<body>
	<form id="incomeFirm" method="post">
		<div>
			入库编号:<input type="text" name="sn">
			<%--供应商:<input id="cc" class="easyui-combobox" name="supplier"
					   data-options="valueField:'name',textField:'name',url:'/supplier/getAll'" />--%>
			<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存入库</a>
		</div>
		<div style="height: 600px">
			<!-- 数据表格 -->
			<table id="stock_datagrid"></table>
		</div>
	</form>

	<!-- 新增编辑对话框 -->
	<div id="item_dialog">
		<table id="pro_data" style="margin-top: 15px;"></table>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="stock_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="addItem">添加明细</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="removeItem">删除明细</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="pro_data_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="choose">选择商品</a>
	</div>
</body>
</html>