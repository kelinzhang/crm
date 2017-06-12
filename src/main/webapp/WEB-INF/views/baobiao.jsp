<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <style type="text/css">
        .imageButton {
            float: left;
            margin-right: 27px;
            background: #fafbfb;
            box-shadow: 1px 2px 5px 0 rgba(111, 111, 111, 0.7);
            width: 70px;
            height: 70px;
            padding-top: 10px;
        }
    </style>
    <%@include file="common.jsp" %>

    <script type="text/javascript" src="/js/views/baobiao.js"></script>
    <script type="text/javascript" src="../../js/views/index.js"></script>
    <link media="all" href="../../css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
<div  fit="true">
    <div data-options="region:'north'" style="padding-left: 260px;height: 90px">
        <div class="imageButton" data-cmd="cashMethod">
            <img src="/images/systemSetting/index2.png" width="30" style="padding-left: 15px">
            <p>收银会员报表</p>
        </div>
        <div class="imageButton" data-cmd="pitServiceMethod">
            <img src="/images/systemSetting/index3.png" width="30" style="padding-left: 15px">
            <p>宠物服务报表</p>
        </div>
        <div class="imageButton" data-cmd="stockMethod">
            <img src="/images/systemSetting/index4.png" width="30" style="padding-left: 15px">
            <p>库存报表</p>
        </div>
    </div>
</div>	

	<div>
        <iframe style="width: 100%;height: 90%" id="showgrid" name="showgrid" frameborder="1" src="../petChart"></iframe>           
    </div>
	
	
</body>
</html>
