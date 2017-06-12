$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var petBreedDatagrid,petBreedDialog,petBreedForm;
	petBreedDatagrid = $("#petBreed_datagrid");
	petBreedDialog = $("#petBreed_dialog");
	petBreedForm = $("#petBreed_form");
	/*
	 * 初始化数据表格 
	 */
	petBreedDatagrid.datagrid({
		url:"/petBreed/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#petBreed_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	petBreedDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#petBreed_dialog_bt'
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
				petBreedForm.form("clear");
				petBreedDialog.dialog("setTitle","新增");
				petBreedDialog.dialog("open");
			},
			edit:function(){
				var rowData = petBreedDatagrid.datagrid("getSelected");
				if(rowData){
					petBreedForm.form("clear");
					petBreedDialog.dialog("setTitle","编辑");
					petBreedDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					petBreedForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = petBreedDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/petBreed/delete?petBreedId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										petBreedDatagrid.datagrid("reload");
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
				petBreedDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/petBreed/update"
				}else{
					url = "/petBreed/save";
				}
				petBreedForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								petBreedDialog.dialog("close");
								petBreedDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				petBreedDialog.dialog("close");
			}
	}
});
