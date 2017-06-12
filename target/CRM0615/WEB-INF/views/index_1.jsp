<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台首页</title>
    <%@include file="common.jsp"%>
</head>
<body>
<!DOCTYPE html>
<html style="width: 100%; height: 100%">
<head style="width: 100%; height: 100%;">
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">

    
    <title>本店信息</title>
    

<link media="all" href="../../css/index_1.css" type="text/css" rel="stylesheet">
</head>
<body style="width: 100%; font-family: &quot;微软雅黑&quot;; background: transparent none repeat scroll 0% 0%; color: black;">
    <div class="areabx clear" style="width: 100%;">
        <div id="Info" class="tian_xi">
<form action="http://pet.byiaa.com/UserDetailedInfo/StoreListOne" clase="form-inline" enctype="multipart/form-data" id="Grid" method="post" name="uploadForm" role="form" style="height: 100%; display: block;">                <div style="width: 52%; height: 487px; background: transparent url('ba.png') no-repeat scroll 0% 0%; overflow: hidden; float: left;">
                    <div>
                        <img id="uploadPreview" class="dpImg" src="fm_default.png">
                        <div style="width: 350px; height: 35px; line-height: 35px; color: rgb(255, 255, 255); background: rgb(57, 57, 57) none repeat scroll 0% 0%; opacity: 0.6; text-align: center; position: absolute; left: 141px; top: 396px; font-size: 14px;">修改图片</div>
                        <input class="sc_btn" id="fileUpload" onchange="loadImageFile();" name="fileUpload" size="25" style="opacity: 0; position: absolute; left: 141px; top: 61px; width: 350px; height: 350px;" type="file">
                    </div>
                    
                </div>
</form>            <div style="margin-right: 15px; float: left; height: 487px; width: 40%;">
                <div>
                    <div class="guider" id="guider">
                        <div class="font-50" style="font-size: 24px;">
                            <input data-val="true" data-val-number="字段 ID 必须是一个数字。" data-val-required="ID 字段是必需的。" id="ID" name="ID" value="733" type="hidden">
                            <input data-val="true" data-val-number="字段 UserID 必须是一个数字。" data-val-required="UserID 字段是必需的。" id="UserID" name="UserID" value="689" type="hidden">
                            <span style="float: left; padding-top: 10px;">爱如歌</span><img id="starimg" src="star.png" style="transform: rotate(40deg); margin-bottom: 5px; margin-left: -22px; display: none; float: left;">
                            <span class="update_info" onclick="UpdateRecord();">
                                <p style="float: left;">修改</p>
                            </span>
                        </div>
                    </div>
                    <div style="clear: both;"></div>
                    <div style="width: 90%; font-weight: 800;">
                        <div class="Contact_tit"></div>
                        <div class="con_info"><span>负责人：程震</span></div>
                        <div class="con_info"><span>联系电话：15637379966</span></div>
                        <div class="con_info"><span id="Address">店铺地址：</span><span id="StoreAddress">河南省新乡市卫辉市卫辉市友谊新村</span></div>
                        <div class="con_info"><span>成立时间：2017年04月24日</span></div>
                    </div>
                </div>
        </div>
        <div style="clear: both;">
            <div id="StoreInfo" class="areabx clear" style="height: 36px; padding-top: 10px; display: none;">
                <span>店铺名称：</span><input class="input_check" id="StoreName" name="StoreName" onchange="DoSearch();" value="" type="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="com_btn1" value="查询" onclick="DoSearch();" type="button">
                <div id="grid">
                </div>
            </div>
        </div>
    </div>
    
    </div>



</body>
</html>


</body>
</html>
