<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="../../js/views/index.js"></script>
    <script type="text/javascript" src="../../js/plugins/jquery_portal/jquery.portal.js"></script>
    <link media="all" href="../../css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="../../js/plugins/jquery_portal/portal.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        $(function () {
            $('#pt').portal({
                border: false,
                fit: true
            });
            //初始化大框架后，添加portal元素
            add();
        });
        function closedIndex() {
            $.messager.confirm("提示", "确定退出当前系统么?", function (msg) {
                if (msg) {
                    $.post("/closedIndex/close", function () {
                        window.location.reload();
                    });
                }
            })
        }
        function add() {
            $('#pt').portal('resize');
        }
        //动态显示时间
        var timerID = null;
        var timerRunning = false;
        function showtime() {
            var now = new Date();
            document.clock.thetime.value = now.toLocaleString() + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
            timerID = setTimeout("showtime()", 1000);
            timerRunning = true;
        }
    </script>

</head>

<body onload="showtime()">

	<div id="cc" class="easyui-layout" fit="true">
		<div data-options="region:'north',split:true" align="center" style="height: 100px; width: 100%; background:url('../../images/index/baiIndex2.png'); background-repeat:no-repeat;"><!-- //url('../../images/index/indexLAO.jpg'); -->
			<!-- 界面上方当前用户,时间,退出系统 -->
			<div id="top">
				<div id="top_links" style="float: right;margin-right: 70px;margin-top: 20px">
					<div id="top_op" style="width: 220px;height: 90px;">
						<img alt="当前用户" src="../../images/common/user.png" style="width: 35px; height: 35px;margin-top: 8px; float: left;"/>
						<a style="float: left; margin-top:13px; font-size: 20px;">
						<%HttpSession s = request.getSession();%>
						<%=s.getAttribute("USER_NAME")%>

						</a>
						<img alt="退出系统" title="退出系统" onclick="closedIndex()" style="width: 35px; height: 35px;margin-top: 8px; margin-right:15px; float: right;" src="../../images/common/out.png"/>
				
						<span id="user_span" ></span>
					    <span style="font-size: 20px">
					    	<form name=clock ><input name=thetime style="background-color:transparent;font-size: 11pt;color:#000000;border:0" size=27></form>
				       </span>	
			
					</div>
			   </div>
			</div>
</div>
        <div class="l-layout" data-options="region:'west',title:'菜单',split:true" style="width:65px; background-color: #393939;">
        <div>
            <ul id="TabPage2" style="height:200px; background-color: #393939;height: 90%; margin-left: 3px;">
                <li style="margin-top: 15px;"></li>
                <li id="member_manager" style="height: 14%">
                    <div class="" data-rootmenu="member_manager" data-title="会员管理" data-url="/member">
                        <lable style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;" ><img alt="会员管理" title="会员管理" src="/images/index/cwgl.png">会员管理</lable>
                    </div>
                <li>
                <li id="collectmoney_manager" style="height: 14%">
                    <div class="active" data-rootmenu="collectmoney_manager" data-title="收银管理"
                         data-url="/cash">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="收银管理" title="收银管理" src="/images/index/sygl.png">收银管理</label>
                    </div>
                </li>
                <li id="pet_manager" style="height: 14%">
                    <div class="" data-rootmenu="pet_manager" data-title="宠物服务" data-url="/petService">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="宠物服务" title="宠物服务" src="/images/index/fw.png">宠物服务</label>
                    </div>
                </li>
                <li id="repertory_manager" style="height: 14%">
                    <div class="" data-rootmenu="repertory_manager" data-title="库存管理" data-url="/productStock">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="库存管理" title="库存管理" src="/images/index/kcgl.png">库存管理</label>
                    </div>
                </li>
                <li id="statement_analyze" style="height: 14%">
                    <div class="" data-rootmenu="statement_analyze" data-title="报表分析" data-url="/baobiao">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="报表分析" title="报表分析" src="/images/index/bbgl.png">报表分析</label>
                    </div>
                </li>
                <li id="statement_employee" style="height: 14%">
                    <div class="" data-rootmenu="statement_employee" data-title="员工管理" data-url="/employee">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="员工管理" title="员工管理" src="/images/index/yggl.png">员工管理</label>
                    </div>
                </li>
                <li id="system_manager" style="height: 14%">
                    <div class="" data-rootmenu="system_manager" data-title="系统设置" data-url="/systemSetting">
                        <label style="float: left;color:#ffffff; margin-top: 5px; margin-left: 2px;"><img alt="系统设置" title="系统设置" src="/images/index/xtsz.png">系统设置</label>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div id="win" class="easyui-window" title="快速操作"
         style="width: 600px; height: 400px"
         data-options="iconCls:'icon-save',modal:true,closed:true">
        Window Content
    </div>
    <div data-options="region:'center'" style="padding: 5px;">
        <div id="mtTabs" class="easyui-tabs" fit="true">
            <div title="欢迎页" closable="true" id="pt">
                <div style="width: 33%">
                    <div id="pgrid1" title="快捷操作" closable="true"
                         style="height: 140px;">
                        <a class="easyui-linkbutton" data-cmd="addMember">添加会员</a> <a
                            class="easyui-linkbutton" data-cmd="deposit">快速充值</a>
                    </div>
                </div>
                <div style="width: 33%">
                    <div id="pgrid" title="以下仓库阈值太低啦=.=,该进货了" closable="true"
                         style="height: 140px; width: 50px;">
                        <table class="easyui-datagrid" style="width: 250px; height: auto"
                               fit="true" border="false" singleSelect="true" idField="itemid"
                               fitColumns="true" url="/productStock/list">
                            <thead>
                            <tr>
                                <th field="proSn" width="1">编号</th>
                                <th field="proName" width="1">商品名称</th>
                                <th field="stockNumber" width="1" align="right">库存数量</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div data-options="region:'south',split:true" align="center"
         style="height: 20px; background: #0092DC;">版权信息
    </div>
</div>
</div>
</body>
</html>
