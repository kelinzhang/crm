<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宠物管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/pet.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="pet_datagrid">
		<thead>
			<tr>
                <th data-options="field:'member',width:1,align:'center'">会员</th>
                <th data-options="field:'name',width:1,align:'center'">宠物名</th>
                <th data-options="field:'breed',width:1,align:'center'">品种</th>
                <th data-options="field:'sex',width:1,align:'center'">性别</th>
                <th data-options="field:'birthday',width:1,align:'center'">宠物生日</th>
                <th data-options="field:'ancestry',width:1,align:'center'">血统</th>
                <th data-options="field:'color',width:1,align:'center'">毛色</th>
				<th data-options="field:'pic',width:1,align:'center'">图片</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="pet_dialog">
		<form id="pet_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>血统</td>
				<td><input type="text" name="ancestry"></td>
			</tr>
			<tr>
				<td>会员</td>
				<td><input type="text" name="member"></td>
			</tr>
			<tr>
				<td>宠物生日</td>
				<td><input type="text" name="birthday"></td>
			</tr>
			<tr>
				<td>品种</td>
				<td><input type="text" name="breed"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input type="text" name="sex"></td>
			</tr>
			<tr>
				<td>毛色</td>
				<td><input type="text" name="color"></td>
			</tr>
			<tr>
				<td>宠物名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>图片</td>
				<td><input type="text" name="pic"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="pet_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="pet_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>