$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var rechargeDatagrid,rechargeDialog,rechargeForm;
	rechargeDatagrid = $("#recharge_datagrid");
	rechargeDialog = $("#recharge_dialog");
	rechargeForm = $("#recharge_form");
	/*
	 * 初始化数据表格 
	 */
	rechargeDatagrid.datagrid({
		url:"/recharge/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#recharge_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	rechargeDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#recharge_dialog_bt'
	});
	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				rechargeForm.form("clear");
				rechargeDialog.dialog("setTitle","新增");
				rechargeDialog.dialog("open");
			},
			edit:function(){
				var rowData = rechargeDatagrid.datagrid("getSelected");
				if(rowData){
					rechargeForm.form("clear");
					rechargeDialog.dialog("setTitle","编辑");
					rechargeDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					rechargeForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = rechargeDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/recharge/delete?rechargeId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										rechargeDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的数据!","warining");
				}
			},
			reload:function(){
				rechargeDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/recharge/update"
				}else{
					url = "/recharge/save";
				}
				rechargeForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								rechargeDialog.dialog("close");
								rechargeDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				rechargeDialog.dialog("close");
			}
	}
});
