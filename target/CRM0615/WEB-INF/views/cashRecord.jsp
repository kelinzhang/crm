<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>收银记录</title>
<%@include file="common.jsp" %>
<script type="text/javascript" src="/js/views/cashRecord.js"></script>
<div id="query_cardRecord" data-options="region:'north',split:true" align="center" style="height:45px;">
    <tr>
        会员号：
        <input id="number" name="number" type="text"/>
        收银店铺：
        <select class="div_div" id="storeName" name="storeName" onchange="DoSearch();">
            <option value="0">所有</option>
        </select>
        收银时间:
        <input type="text" id="start" name="start" class="easyui-datebox" style="height:18px;width:100px;line-height:18px" />
        -
        <input type="text" id="end" name="end" class="easyui-datebox" style="height:18px;width:100px;line-height:18px" />
        <a class="easyui-linkbutton" data-cmd="query">查询</a>
    </tr>
</div>
<div data-options="region:'center'" style="height:550px;">
    <!-- 数据表格 -->
    <table id="cashRecord_datagrid">
    </table>
    <div id="item_dialog">
        <table id="item_data" style="margin-top: 15px;"></table>
    </div>
</div>

