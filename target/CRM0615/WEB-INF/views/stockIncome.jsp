<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入库单管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/stockIncome.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="stockIncome_datagrid"></table>
	<div id="item_dialog">
		<table id="item_data" style="margin-top: 15px;"></table>
	</div>
	<div id="stockIncome_datagrid_tb">
		<div>
			<%--<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>--%>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
</body>
</html>