<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>收银管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/cash.js"></script>
</head>

<body>
<form id="cash_form" method="post">
<div data-options="region:'north',split:true" style="padding:8px;">
        收银编号:<input type="text" name="proSn">
    <a class="easyui-linkbutton btn_cash" id="cash_btn" style="float: right;">收银记录</a>
</div>

</form>
<div id="item_dialog">
    <table id="pro_data" style="margin-top: 15px;"></table>
</div>
<!-- 数据表格CRUD按钮 -->
<div id="cash_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="addItem">添加明细</a>
    </div>
</div>
<!-- 对话框保存取消按钮 -->
<div id="pro_data_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="choose">选择商品</a>
</div>
<div data-options="region:'south',split:true" style="height: 200px">
    <div data-options="region:'center'" style="height:330px;">
        <!-- 数据表格 -->
        <table id="cash_datagrid">
        </table>
    </div>


    <div>
        <table style="width: 90%; height: 50px; margin-left: 60px">
            <tr>
                <td>会员卡号：
                    <input id="number" name="number" style="height:30px;width:150px;" type="text"/>
                </td>
                <td>会员姓名：
                    <input name="name" style="width:50px;border:0px "/>
                </td>
                <td>现有金额：
                    <input name="balance" style="width:50px;border:0px "/>
                </td>
                <td>会员等级：
                    <input name="levelName" style="width:50px;border:0px "/>
                </td>
                <td>商品折扣：
                    <input name="levelProducteDisCount" style="width:50px;border:0px "/>%
                </td>
                <td>服务折扣：
                    <input name="levelServiceDisCount" style="width:50px;border:0px "/>%
                </td>
            </tr>
        </table>
    </div>
    <div style="width: 100%;  margin-left: 60px;">
        <span style="width: 80px">应收金额：</span>
        <input id="price" name="Price" style="height:30px;width:150px;font-size:20px" type="text" value="0"/>
        <span>元</span>
        <span style="margin-left: 15px">实收金额：</span>
        <input data-val="true"
               id="discountPrice" name="discountPrice" style="height:30px;width:150px;font-size:20px" type="text"
               value=""/>
        <span>元</span>
    </div>
    <br/>
    <div class="botbtbx pdb0" style="width: 100%;  margin-left: 60px">
        <a class="easyui-linkbutton" id="payMoney" style="float: left; ">会员结账</a>

    </div>
</div>
</body>
</html>







