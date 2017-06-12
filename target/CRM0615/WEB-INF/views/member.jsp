<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会员管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/member.js"></script>
</head>
<body>
<!-- 数据表格 -->
<table id="member_datagrid">
    <thead >
    <tr>
        <th data-options="field:'member',width:1,align:'center',formatter: numberFormat">会员号</th>
        <th data-options="field:'petName',width:1,align:'center'">宠物名</th>
        <th data-options="field:'breed',width:1,align:'center',formatter: breedFormat">品种</th>
        <th data-options="field:'sex',width:1,align:'center',formatter: sexFormat">性别</th>
        <th data-options="field:'birthday',width:1,align:'center'">宠物生日</th>
        <th data-options="field:'ancestry',width:1,align:'center'">血统</th>
        <th data-options="field:'color',width:1,align:'center'">毛色</th>
        <th data-options="field:'member1',width:1,align:'center',formatter: balanceFormat">余额</th>
        <th data-options="field:'member2',width:1,align:'center',formatter: addressFormat">地址</th>
        <th data-options="field:'pic',width:1,align:'center',formatter: picFormat">图片</th>
    </tr>
    </thead>
</table>
<!-- 新增会员对话框 -->
<div id="member_dialog">
    <form id="member_form" method="post">
        <table style="margin-top: 15px; " align="center" cellspacing="20px">
            <input type="hidden" name="sn" >
            <input type="hidden" name="id" >
            <tr>
                <td>主人信息</td>
            </tr>
            <tr>
                <td><font color="red">手机号码:</font></td>
                <td><input type="text" name="number" required="required"></td>
                <td>会员姓名:</td>
                <td><input type="text" name="name"></td>
                <td><font color="red" id="money">首充金额:</font></td>
                <td><input type="text" name="credit" required="required"></td>

            </tr>
            <tr>
                <td>性别:</td>
                <td>男<input type="radio" id="boy" name="gender" value="1" > 女<input type="radio" id="girl" name="gender" value="2"> 不详<input
                        type="radio" name="gender" id="noKnow" value="0"></td>
                <td>住址:</td>
                <td><input type="text" name="address"></td>
                <td>会员等级:</td>
                <td><input class="easyui-combobox" id="level1" name="level.id"
                           data-options="valueField:'id',textField:'levelName',url:'/vipLevel/selectListForDepositForm',width:'147'">
                </td>
                <td>商品折扣:<span name="productDisCount"></span>% 服务折扣:<span name="serviceDisCount"></span>%</td>
            </tr>
            <tr>
                <td>宠物信息:</td>
            </tr>
            <tr>
                <td><font color="red">宠物名:</font></td>
                <td><input type="text" name="petName" required="required" ></td>
                <td>宠物生日:</td>
                <td><input id="birthday" type="text" class="easyui-datebox"  name="birthday" >
                <s:data></s:data>
                </td>
                <td>宠物性别:</td>
                <td>弟弟<input type="radio" name="sex" value="1"> 妹妹<input type="radio" name="sex" value="2"> 不详<input
                        type="radio" name="sex" value="0"></td>
            </tr>
            <tr>
                <td>宠物品种:</td>
                <td><input class="easyui-combobox" id="breed" name="breed.id"
                           data-options="valueField:'id',textField:'text',url:'/petBreed/selectListForPetForm',width:'147'">
                </td>
                <td>颜色:</td>
                <td><input type="text" name="color"></td>
                <td>血统:</td>
                <td><input type="text" name="ancestry"></td>
            </tr>
        </table>
    </form>
</div>
<!-- 新增宠物对话框 -->
<div id="pet_dialog">
    <form id="pet_form" method="post">
        <table style="margin-top: 15px; " align="center" cellspacing="20px">
            <input type="hidden" name="member.id">
            <tr>
                <td>会员编号:</td>
                <td><input  name="member.number" readonly="true" style="color: grey"></td>
                <td><font color="red">宠物名:</font></td>
                <td><input type="text" name="petName"></td>
            </tr>
            <tr>
                <td>宠物生日:</td>
                <td><input type="text" class="easyui-datebox" id="birth" name="birthday"></td>
                <td>宠物性别:</td>
                <td>弟弟<input id="didi" type="radio" name="sex" value="1"> 妹妹<input type="radio" name="sex" value="2"> 不详<input
                type="radio" name="sex" value="0"></td>
            </tr>
            <tr>
                <td><label color="red">宠物品种:</label></td>
                <td><input class="easyui-combobox" id="breed1" name="breed.id" required="required"
                           data-options="valueField:'id',textField:'text',url:'/petBreed/selectListForPetForm',width:'147'">
                </td>
                <td>宠物血统:</td>
                <td><input type="text" name="ancestry"></td>
            </tr>
            <tr>
                <td>颜色:</td>
                <td><input type="text" name="color"></td>
            </tr>
        </table>
    </form>
</div>
<%--宠物操作菜单--%>
<div id="petCRUD" class="easyui-menu" >
    <div><a data-cmd="addPet">添加宠物</a></div>
  <%--  <div>
        &lt;%&ndash;<span>Open</span>&ndash;%&gt;
        &lt;%&ndash;<div style="width:150px;">
            <div><b>Word</b></div>
            <div>Excel</div>
            <div>PowerPoint</div>
        </div>&ndash;%&gt;
    </div>--%>
    <div><a data-cmd="editMember">修改</a></div>
    <%--<div class="menu-sep"></div>--%>
    <div><a data-cmd="deletePet">删除</a></div>
</div>
<%--页面展示选项操作菜单
<div id="selectedMenu" class="easyui-menu" >
    <div>会员号</div>
    <div>宠物名</div>
    <div>品种</div>
    <div>性别</div>
    <div>宠物生日</div>
    <div>血统</div>
    <div>毛色</div>
    <div>余额</div>
    <div>地址</div>
</div>--%>
<%--导入会员对话框--%>
<div id="excel_dialog">
    <div><font color="red">注意：只能使用office excel2003-2007的文件,且只能导入sheet1内容,单行数据为空将立即停止！</font></div>
    <form id="excel_form" method="post" enctype="multipart/form-data">
     <div></div>
    <div>请选择你要导入的文件:<input type="file" name="excel"></div>
    </form>
</div>
<!-- 充值对话框 -->
<div id="deposit_dialog">
    <form id="deposit_form" method="post">
        <table style="margin-top: 15px; " align="center" cellspacing="20px" cellpadding="-5px">
            <input type="hidden" name="member.id">
            <tr>
                <td>充值单编号:</td>
                <td><input type="text" name="sn" readonly="true" style="color: grey;width: 90px;"></td>
            </tr>
            <tr>
                <td></td>
                <td>会员卡号:<span name="number"></span></td>
                <td>卡内余额:<span name="balance"></span>元</td>
                <td>历史消费:<span name="consume"></span>元</td>
            </tr>
            <tr>
                <td></td>
                <td>充值金额:</td>
                <td><input type="text" name="pay"></td>
            </tr>
            <tr>
                <td></td>
                <td>会员等级:</td>
                <td><input class="easyui-combobox" id="level" readonly="true"
                           data-options="valueField:'id',textField:'levelName',url:'/vipLevel/selectListForDepositForm',width:'147'">
                </td>
                <td>商品折扣:<span name="productDisCount1"></span>%</td>
                <td>服务折扣:<span name="serviceDisCount1"></span>%</td>
            </tr>
            <tr>
                <td></td>
                <td>备注:</td>
                <td><input type="text" name="remark" style="width: 180px;"></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>充值人员</td>
                <td><input class="easyui-combobox" id="emp_depo"
                           data-options="valueField:'id',textField:'realname',url:'/employee/selectListForDepositForm',width:'147'">
            </tr>
        </table>
    </form>
</div>
<!-- 充值记录对话框 -->
<div id="recharge_dialog">
    <table id="recharge_datagrid">

        <thead>
        <tr>
            <th data-options="field:'sn',width:1,align:'center'">充值单编号</th>
            <th data-options="field:'member',width:1,align:'center',formatter: numberFormat">会员卡号</th>
            <th data-options="field:'member1',width:1,align:'center',formatter: nameFormat">会员姓名</th>
            <th data-options="field:'payDate',width:1,align:'center'">充值时间</th>
            <th data-options="field:'pay',width:1,align:'center'">充值金额</th>
            <th data-options="field:'remark',width:1,align:'center'">备注</th>
        </tr>
        </thead>
    </table>
</div>
<!-- 修改图片对话框 -->
<div id="uploadPic_dialog">
    <form id="editpic_form" method="post" enctype="multipart/form-data">
        宠物图片:<input type="file" name="p">
    </form>
</div>
<div id="recharge_datagrid_tb">
<div id="query_recharge">
    充值单编号:<input type="text" name="rechargeId" style="width: 120px;">
    会员卡号:<input type="text" name="tel" style="width: 120px;">
    会员姓名:<input type="text" name="memberName" style="width: 120px;">
    <a class="easyui-linkbutton" iconCls="icon-search"
       data-cmd="select">查询</a>
</div>
</div>
<!-- 数据表格CRUD按钮 -->
<div id="member_datagrid_tb">
    <div>
        宠物品种:<input id="petBreed" class="easyui-combobox"
                    data-options="valueField:'id',textField:'breedName',url:'/petBreed/selectListForPetForm',width:'130'">
        宠物名称:<input type="text" name="keyword" style="width: 120px;">
        手机号码:<input type="text" name="phone" style="width: 120px;">
        <a class="easyui-linkbutton" iconCls="icon-search"
           data-cmd="query">查询</a>
    </div>
    <div>
        <a class="easyui-linkbutton"
           data-cmd="deposit">充值</a>
        <a class="easyui-linkbutton"
           data-cmd="addMember">添加会员</a>
        <a class="easyui-linkbutton"
           data-cmd="addPet">添加宠物</a>
        <%--<a class="easyui-linkbutton"
           data-cmd="query">退卡</a>--%>
        <a class="easyui-linkbutton"
           data-cmd="channel">导入会员</a>
        <a class="easyui-linkbutton"
           data-cmd="showRecharge" style="position: absolute;right: 30px;">充值记录</a>
    </div>
</div>


<!--会员 对话框保存取消按钮 -->
<div id="member_dialog_bt">
    <a class="easyui-linkbutton" id="saveOrUpdate" iconCls="icon-save" plain="true" data-cmd="saveMember">添加会员</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<!-- 宠物对话框保存取消按钮 -->
<div id="pet_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="savePet">添加宠物</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<!-- 充值对话框保存取消按钮 -->
<div id="deposit_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveDeposit">充值</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<!-- 导入会员对话框保存取消按钮 -->
<div id="excel_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveExcel">导入会员</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<!-- 修改图片对话框保存取消按钮 -->
<div id="pic_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="updatePic">确定修改</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<!-- 充值记录对话框保存取消按钮 -->
<div id="recharge_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">关闭</a>
</div>
</body>
</html>