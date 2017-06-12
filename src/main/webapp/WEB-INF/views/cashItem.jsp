<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <title>收银记录</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/cashItem.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

<div data-options="region:'north',split:true" align="center" style="height:45px;">
    <tr>
    商品名称：
    <input id="MemberNumber" name="MemberNumber"  type="text" value="" />



    <input type="button" class="comBtn gray" value="查询"  style="margin-top: -5px;">
    </tr>
</div>

<div data-options="region:'south',split:true" align="center" style="height:550px;">
    <!-- 数据表格 -->
    <table id="cashItem_datagrid" >
        <thead>
        <tr>
            <th data-options="field:'sn',width:140,align:'center'">商品条码</th>
            <th data-options="field:'card',width:140,align:'center'">商品名称</th>
            <th data-options="field:'name',width:140,align:'center'">单位</th>
            <th data-options="field:'inputTime',width:140,align:'center'">商品原价(元)</th>
            <th data-options="field:'shopCount',width:140,align:'center'">折扣(元)</th>
            <th data-options="field:'costPrice',width:140,align:'center'">售价(元)</th>
            <th data-options="field:'inputUser',width:140,align:'center'">销售数据</th>
            <th data-options="field:'pattern',width:140,align:'center'">折扣原因</th>
            <th data-options="field:'sn',width:140,align:'center'">服务人员</th>
        </tr>
        </thead>
    </table>
</div>

