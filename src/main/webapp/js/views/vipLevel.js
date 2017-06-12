$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var vipLevelDatagrid,vipLevelDialog,vipLevelForm;
	vipLevelDatagrid = $("#vipLevel_datagrid");
	vipLevelDialog = $("#vipLevel_dialog");
	vipLevelForm = $("#vipLevel_form");
	/*
	 * 初始化数据表格 
	 */
	vipLevelDatagrid.datagrid({
		url:"/vipLevel/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#vipLevel_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	vipLevelDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#vipLevel_dialog_bt'
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
				vipLevelForm.form("clear");
				vipLevelDialog.dialog("setTitle","新增");
				vipLevelDialog.dialog("open");
			},
			edit:function(){
				var rowData = vipLevelDatagrid.datagrid("getSelected");
				if(rowData){
					vipLevelForm.form("clear");
					vipLevelDialog.dialog("setTitle","编辑");
					vipLevelDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					vipLevelForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = vipLevelDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/vipLevel/delete?vipLevelId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										vipLevelDatagrid.datagrid("reload");
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
				vipLevelDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/vipLevel/update"
				}else{
					url = "/vipLevel/save";
				}
				vipLevelForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								vipLevelDialog.dialog("close");
								vipLevelDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				vipLevelDialog.dialog("close");
			}
	}
});
