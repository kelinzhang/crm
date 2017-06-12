<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>宠物服务管理</title>
    <%@include file="common.jsp" %>
    <style type="text/css">
        .onetd {
            /*text-align: center;*/
            /*vertical-align: middle;*/
            float: right;
            height: 30px;
            line-height: 30px;
        }

        #listAlign tr {
            height: 30px;
        }
        .selfinput {
            width: 110px;
            height: 10px;
        }
    </style>
    <script type="text/javascript" src="/js/views/petService.js"></script>
</head>
<body>
<%--页面布局--%>
<div id="pet_menu" class="easyui-layout" fit="true">
    <div id="pet_menuTitle" data-options="region:'west',width:230,border:false,border:false">
        <ul id="pet_menuTree"></ul>
    </div>
    <div data-options="region:'center'">
        <!-- 数据表格 -->
        <table id="petService_datagrid"></table>
    </div>
</div>
<!-- 新增宠物登记对话框 -->
<div id="petService_dialog">
    <form id="petService_form" method="post">
        <table>
            <tr>
                <td>会员号:</td>
                <td><input id="VipNumberInput">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="queryByVipNumber"></a>
                </td>
            </tr>
        </table>
        <div class="easyui-tabs" style="width:532px;height:345px" data-options="plain:true">
            <div title="宠物" style="padding:10px">
                <table align="center" width="100%" height="95%" id="listAlign">
                    <tr>
                        <td class="onetd"><span style='color: red;'>服务项目:</span></td>
                        <td colspan="2">
                            <input class="easyui-combobox" name="" id="BuildingId2" style="width: 50px;">
                            <input class="easyui-combobox" name="serviceProject" id="BuildingId3" style="width: 100px;">
                            <input type="text" name="servicePrice" id="priceInput" style="width: 40px;">
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="onetd"><span style='color: red; '>宠物名:</span></td>
                        <td><input id="petNameInput" type="text" name="petName"></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="onetd">主人名:</td>
                        <td><input id="personNameInput" type="text" name="personName"></td>
                        <td class="onetd">联系方式:</td>
                        <td><input id="selfTel" type="text" name="tel"></td>
                    </tr>
                    <tr>
                        <td class="onetd">预计开始时间:</td>
                        <td><input type="text" class="easyui-datetimebox" data-options="showSeconds:true"
                                   name="startDateTime"></td>
                        <td class="onetd">预计结束时间:</td>
                        <td><input class="easyui-datetimebox" type="text" name=""></td>
                    </tr>
                    <tr>
                        <td class="onetd">宠物年龄:</td>
                        <td><input class="" type="text" name=""></td>
                        <td class="onetd">宠物品种:</td>
                        <td align="left"><input class="easyui-combobox" name="" id="kindParent" style="width: 60px;">
                            <input class="easyui-combobox" name="" id="kindChildren" style="width: 90px;"></td>
                    </tr>
                    <tr>
                        <td class="onetd">上次驱虫时间:</td>
                        <td><input type="text" class="easyui-datebox" data-options="sharedCalendar:'#cc'" name=""></td>
                        <td class="onetd">上次疫苗时间:</td>
                        <td><input class="easyui-datebox" data-options="sharedCalendar:'#cc'" type="text" name=""></td>
                    </tr>
                    <tr>
                        <td class="onetd">宠物性别:</td>
                        <td><input type="radio" name="gender" value="1" id="man">弟弟<input type="radio" value="2"
                                                                                          id="woman"
                                                                                          name="gender">妹妹<input
                                type="radio" value="0" id="unknow" name="gender">不详
                        </td>
                        <td class="onetd">预付款:</td>
                        <td align="left"><input class="text" value="0" name="beforePrice"></td>
                    </tr>
                    <tr>
                        <td class="onetd">自带物品:</td>
                        <td><input class="easyui-textbox" name=""></td>
                        <td class="onetd">特殊备注:</td>
                        <td align="left"><input type="text" name=""></td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>
<%-- 新增添加服务项目对话框 --%>
<div id="addService_dialog">
    <form id="addService_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id" id="addServiceId">
            <tr>
                <td><span style='color: red; '>服务名称</span></td>
                <td><input type="text" name="text"></td>
            </tr>
            <tr>
                <td>所属项目</td>
                <td><input class="easyui-combobox" name="parent.id" id="BuildingId"
                           data-options="valueField:'id',textField:'text',url:'/petMenu/queryTree',onselect"></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input type="text" name="servicePrice"></td>
            </tr>
            <tr>
                <td>单位(次,天)</td>
                <td><input type="text" name="times"></td>
            </tr>
        </table>
    </form>
</div>
<div id="petService_datagrid_tb">
    <div>
        宠物名称:<input type="text" name="keyname">
        手机号码<input type="text" name="keytel">
        <select class="easyui-combobox" id="queryBycombobox" name="keyCurState" style="width: 100px;">
            <option>请选择</option>
            <option value="0">进行中</option>
            <option value="1">已结束</option>
        </select>
        <a class="easyui-linkbutton" data-cmd="highquery">查询</a>
        <a class="easyui-linkbutton" data-cmd="returnservice">返回服务</a>
    </div>
    <div>
        <a class="easyui-linkbutton" data-cmd="addService">添加服务项目</a>
        <a class="easyui-linkbutton" data-cmd="add">宠物登记</a>
        <a class="easyui-linkbutton" id="btn_over" data-cmd="endService">结束服务</a>
        <a class="easyui-linkbutton" data-cmd="">洗澡</a>
        <a class="easyui-linkbutton" id="btn_pay" data-cmd="pay">付款</a>
    </div>
</div>
<!-- 宠物登记 -->
<div id="petService_dialog_bt">
    <a class="easyui-linkbutton" data-cmd="save">添加</a>
    <a class="easyui-linkbutton" data-cmd="close">取消</a>
</div>
<%-- 添加服务 --%>
<div id="petService_saveProject_dialog_bt">
    <a class="easyui-linkbutton" data-cmd="saveProject">添加项目</a>
    <a class="easyui-linkbutton" data-cmd="close">取消</a>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div data-options="iconCls:'icon-save'">New</div>
    <div data-options="iconCls:'icon-edit'">Edit</div>
    <div data-options="iconCls:'icon-remove'">Delete</div>
    <div>Exit</div>
</div>
</body>
</html>