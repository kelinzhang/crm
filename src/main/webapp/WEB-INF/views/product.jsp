<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品类管理</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/product.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="product_datagrid">
		<thead>
			<tr>
				<th data-options="field:'proName',width:1,align:'center'">商品名称</th>
				<th data-options="field:'kind',width:1,align:'center',formatter:kindFormatter">商品种类名称</th>
				<th data-options="field:'brand',width:1,align:'center',formatter:brandFormatter">商品品牌名称</th>
				<th data-options="field:'costNumber',width:1,align:'center'">商品进价</th>
				<th data-options="field:'saleNumber',width:1,align:'center'">商品售价</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="product_dialog">
		<form id="product_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="proName"></td>
			</tr>
			
			 <tr>
                <td>商品品牌</td>
                <td>
                    <input id="box_brand" class="easyui-combobox" name="brand.id"
                           data-options="valueField:'id',textField:'brandName',url:'/productBrand/listAll'"/>
                </td>
            </tr>
			
			<tr>
				 <td>商品类别</td>
                <td>
                    <input id="box_kind" class="easyui-combobox" name="kind.id"
                           data-options="valueField:'id',textField:'name',url:'/productKind/listAll'"/>
                </td>
			</tr>
			
			
			<tr>
				<td>商品进价</td>
				<td><input type="text" name="costNumber"></td>
			</tr>
			<tr>
				<td>商品售价</td>
				<td><input type="text" name="saleNumber"></td>
			</tr>
			
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="product_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
		<div>
			<input type="text" name="keyword">关键字
			 <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query"></a>
			<input id="box_kind_ha" class="easyui-combobox" name="kindName"
                           data-options="valueField:'id',textField:'name',url:'/productKind/listAll'"/>分类
			<input id="box_brand_ha" class="easyui-combobox" name="brandName"
                           data-options="valueField:'id',textField:'brandName',url:'/productBrand/listAll'"/>品牌
           
		</div>
		
		
		
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="product_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>