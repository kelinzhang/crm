<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统设置首页</title>
    <%@include file="common.jsp"%>
    <script type="application/javascript" src="/js/views/systemSetting.js"></script>
 	
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
</head>
<body>
	 <div style="padding-left: 260px">
    <div class="imageButton" data-cmd="loginLogMethod" >
        <img src="/images/systemSetting/index2.png" width="30" style="padding-left: 15px">
        <p>登录日志 </p>
    </div>
    <div class="imageButton" data-cmd="proKindMethod" >
        <img src="/images/systemSetting/index3.png" width="30" style="padding-left: 15px">
        <p>商品类目 </p>
    </div>
    <div class="imageButton" data-cmd="supplierMethod">
        <img src="/images/systemSetting/index4.png" width="30" style="padding-left: 15px">
        <p>供应商</p>
    </div>
    <div class="imageButton" data-cmd="brandMethod">
        <img src="/images/systemSetting/index5.png" width="30" style="padding-left: 15px">
        <p>商品品牌 </p>
    </div>
    <div class="imageButton" data-cmd="productMethod">
        <img src="/images/systemSetting/index7.png" width="30" style="padding-left: 15px">
        <p>商品信息 </p>
    </div>
    <div class="imageButton" data-cmd="vipLevelMethod">
        <img src="/images/systemSetting/index8.png" width="30" style="padding-left: 15px">
        <p>会员等级 </p>
    </div>
    <div class="imageButton" data-cmd="petBreedMethod">
        <img src="/images/systemSetting/index9.png" width="30" style="padding-left: 15px">
        <p>宠物品种 </p>
    </div>
    </div>
    
	<div >
            <iframe style="width: 100%;height: 90%" id="showgrid" name="showgrid" frameborder="1" src="../product"></iframe>           
    </div>

</body>
</html>
