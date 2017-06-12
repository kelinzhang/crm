$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var petDatagrid,petDialog,petForm;
	petDatagrid = $("#pet_datagrid");
	petDialog = $("#pet_dialog");
	petForm = $("#pet_form");
	/*
	 * 初始化数据表格 
	 */
	petDatagrid.datagrid({
		url:"/pet/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#pet_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	petDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#pet_dialog_bt'
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
				petForm.form("clear");
				petDialog.dialog("setTitle","新增");
				petDialog.dialog("open");
			},
			edit:function(){
				var rowData = petDatagrid.datagrid("getSelected");
				if(rowData){
					petForm.form("clear");
					petDialog.dialog("setTitle","编辑");
					petDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					petForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = petDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/pet/delete?petId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										petDatagrid.datagrid("reload");
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
				petDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/pet/update"
				}else{
					url = "/pet/save";
				}
				petForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								petDialog.dialog("close");
								petDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				petDialog.dialog("close");
			}
	}
});
