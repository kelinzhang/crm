$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var productBrandDatagrid,productBrandDialog,productBrandForm;
	productBrandDatagrid = $("#productBrand_datagrid");
	productBrandDialog = $("#productBrand_dialog");
	productBrandForm = $("#productBrand_form");
	/*
	 * 初始化数据表格 
	 */
	productBrandDatagrid.datagrid({
		url:"/productBrand/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#productBrand_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	productBrandDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#productBrand_dialog_bt'
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
				productBrandForm.form("clear");
				productBrandDialog.dialog("setTitle","新增");
				productBrandDialog.dialog("open");
			},
			edit:function(){
				var rowData = productBrandDatagrid.datagrid("getSelected");
				if(rowData){
					productBrandForm.form("clear");
					productBrandDialog.dialog("setTitle","编辑");
					productBrandDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					productBrandForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = productBrandDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/productBrand/delete?productBrandId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										productBrandDatagrid.datagrid("reload");
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
				productBrandDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/productBrand/update"
				}else{
					url = "/productBrand/save";
				}
				productBrandForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								productBrandDialog.dialog("close");
								productBrandDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				productBrandDialog.dialog("close");
			}
	}
});
