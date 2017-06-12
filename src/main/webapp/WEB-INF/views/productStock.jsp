<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品库存管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/productStock.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="productStock_datagrid">
    <thead>
    <tr>
        <th data-options="field:'proSn',width:1,align:'center'">商品编码</th>
        <th data-options="field:'proName',width:1,align:'center'">商品名称</th>
        <th data-options="field:'kind',width:1,align:'center'">商品种类</th>
        <th data-options="field:'brand',width:1,align:'center'">商品品牌</th>
        <%--<th data-options="field:'supplier',width:1,align:'center'">商品供应商</th>--%>
        <th data-options="field:'stockNumber',width:1,align:'center'">库存数量</th>
        <th data-options="field:'stockPrice',width:1,align:'center'">库存价格</th>
        <th data-options="field:'stockAmount',width:1,align:'center'">库存总价值</th>
    </tr>
    </thead>
</table>
<!-- 数据表格CRUD按钮 -->
<div id="productStock_datagrid_tb">
    <div>
        商品条码:<input type="text" name="proSn">
        商品名称:<input type="text" name="proName">
        <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="query">查询</a>
    </div>
    <div>
        <div>
            <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="open"
                        data-title="商品入库管理" data-url="/stockIncome/stockInput">入库制单</a>
            <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="open"
                        data-title="入库单管理" data-url="/stockIncome">入库信息</a>
            <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="open"
                        data-title="收银记录" data-url="/cashRecord">收银信息</a>
            <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
        </div>
        <div id="selectButton">
           <%-- 种类:<input class="easyui-combobox" name="kind" onchange="select()"
					   data-options="valueField:'name',textField:'name',url:'/productKind/getAll'" />
            品牌:<input class="easyui-combobox" name="brand" onchange="select()"
					   data-options="valueField:'brandName',textField:'brandName',url:'/productBrand/getAll'" />
            供应商:<input class="easyui-combobox" name="supplier" onchange="select()"
					   data-options="valueField:'name',textField:'name',url:'/supplier/getAll'" />--%>
               种类:<input id="kind"/>
               品牌:<input id="brand"/>
               供应商:<input id="supplier"/>
        </div>
    </div>
</div>
</body>
</html>