$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var supplierDatagrid,supplierDialog,supplierForm;
	supplierDatagrid = $("#supplier_datagrid");
	supplierDialog = $("#supplier_dialog");
	supplierForm = $("#supplier_form");
	/*
	 * 初始化数据表格 
	 */
	supplierDatagrid.datagrid({
		url:"/supplier/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#supplier_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	supplierDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#supplier_dialog_bt'
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
				supplierForm.form("clear");
				supplierDialog.dialog("setTitle","新增");
				supplierDialog.dialog("open");
			},
			edit:function(){
				var rowData = supplierDatagrid.datagrid("getSelected");
				if(rowData){
					supplierForm.form("clear");
					supplierDialog.dialog("setTitle","编辑");
					supplierDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					supplierForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = supplierDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/supplier/delete?supplierId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										supplierDatagrid.datagrid("reload");
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
				supplierDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/supplier/update"
				}else{
					url = "/supplier/save";
				}
				supplierForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								supplierDialog.dialog("close");
								supplierDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				supplierDialog.dialog("close");
			}
	}
});
