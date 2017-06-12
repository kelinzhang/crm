<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/memberChart.js"></script>
    <script type="javascript" src="/js/views/jquery-1.8.2.js"></script>
    <script type="javascript" src="/js/views/highcharts.js"></script>
</head>
<body>
<%--
<form id="searchForm" namespace="/" action="chart_saleChart" method="post">--%>
<div class="easyui-layout" fit="true">
    <div data-options="region:'center'">
        <table id="memberChart_datagrid"></table>
    </div>
</div>
<div id="memberChart_datagrid_tb">
    业务时间:
    <input id="start"  class="easyui-datebox"/>
    -
    <input id="end"  class="easyui-datebox"/>
    <a class="easyui-linkbutton btn_query">查询</a>
    <a style="float: right" class="easyui-linkbutton btn_chart">图表分析</a>
</div>

</body>
</html>
