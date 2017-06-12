$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var productKindDatagrid,productKindDialog,productKindForm;
	productKindDatagrid = $("#productKind_datagrid");
	productKindDialog = $("#productKind_dialog");
	productKindForm = $("#productKind_form");
	/*
	 * 初始化数据表格 
	 */
	productKindDatagrid.datagrid({
		url:"/productKind/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#productKind_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	productKindDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#productKind_dialog_bt'
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
				productKindForm.form("clear");
				productKindDialog.dialog("setTitle","新增");
				productKindDialog.dialog("open");
			},
			edit:function(){
				var rowData = productKindDatagrid.datagrid("getSelected");
				if(rowData){
					productKindForm.form("clear");
					productKindDialog.dialog("setTitle","编辑");
					productKindDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					productKindForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = productKindDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/productKind/delete?productKindId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										productKindDatagrid.datagrid("reload");
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
				productKindDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/productKind/update"
				}else{
					url = "/productKind/save";
				}
				productKindForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								productKindDialog.dialog("close");
								productKindDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				productKindDialog.dialog("close");
			}
	}
});
