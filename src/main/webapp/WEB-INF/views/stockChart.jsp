<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">

    <%@include file="common.jsp" %>
    <script type="text/javascript" src="../../js/views/stockChart.js"></script>
    <script type="text/javascript" src="../../js/plugins/jquery_portal/jquery.portal.js"></script>
    
  
    <link media="all" href="../../css/index.css" type="text/css" rel="stylesheet">
	
</head>
<body>

	<div class="easyui-layout" fit="true">

    <div data-options="region:'center'">
        <table id="stockChartData"></table>
    </div>
</div>
	<div id="dataGrid_tb">
         分类:<input class="easyui-combobox" name="text" id="stock_combobox">
    <a class="easyui-linkbutton" style="float: right" id="graphics" >图形报表</a>
	</div>
</body>

</body>
</html>
