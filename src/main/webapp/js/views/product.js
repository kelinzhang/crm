$(function(){
	var newKindName,newBrandName;
	
	$('#box_brand_ha').combobox({
		onChange:function(newValue, oldValue){
			newBrandName = newValue;
			var keyword = $("[name='keyword']").val();
			if(keyword){
				productDatagrid.datagrid("load",{
					keyword:keyword,
					brandName:newValue,
					kindName:newKindName
				})
			}else{
				productDatagrid.datagrid("load",{
					keyword:null,
					brandName:newValue,
					kindName:newKindName
				})
			}
		}
	})
	$('#box_kind_ha').combobox({
		onChange:function(newValue, oldValue){
			newKindName = newValue;
			var keyword = $("[name='keyword']").val();
			if(keyword){
				productDatagrid.datagrid("load",{
					keyword:keyword,
					brandName:newBrandName,
					kindName:newValue
				})
			}else{
				productDatagrid.datagrid("load",{
					keyword:null,
					brandName:newBrandName,
					kindName:newValue
				})
			}
			
		}
	})
	
	
	$('#box_brand').combobox({
		onUnselect:function(){
			alert(1)
		}
	});
	
	
	/*
	 * 抽取所有需要用得元素.
	 */
	var productDatagrid,productDialog,productForm;
	productDatagrid = $("#product_datagrid");
	productDialog = $("#product_dialog");
	productForm = $("#product_form");
	/*
	 * 初始化数据表格 
	 */
	productDatagrid.datagrid({
		url:"/product/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#product_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	productDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#product_dialog_bt'
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
			query:function(){
				var keyword = $("[name='keyword']").val();
				productDatagrid.datagrid("load",{
					keyword:keyword
				})
			},
			 add:function(){
				productForm.form("clear");
				
				var data = $('#box_brand').combobox('getData');
				//console.log(data);
			    if (data.length > 0) {
			        $('#box_brand').combobox('select', data[0].id);
			    } 
			    var data = $('#box_kind').combobox('getData');
			    console.log(data);
			    if (data.length > 0) {
			    	$('#box_kind').combobox('select', data[0].id);
			    } 
			    
				productDialog.dialog("setTitle","新增");
				productDialog.dialog("open");
			},
			edit:function(){
				var rowData = productDatagrid.datagrid("getSelected");
				if(rowData){
					productForm.form("clear");
					productDialog.dialog("setTitle","编辑");
					productDialog.dialog("open");
					if(rowData.kind)
						rowData["kind.id"] = rowData.kind.id;
					productForm.form("load",rowData);
					if(rowData.brand)
						rowData["brand.id"] = rowData.brand.id;
					productForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = productDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/product/delete?productId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										productDatagrid.datagrid("reload");
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
				productDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/product/update"
				}else{
					url = "/product/save";
				}
				productForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								productDialog.dialog("close");
								productDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				productDialog.dialog("close");
			}
	}
});
function brandFormatter(value,record,index){
	if(value){
		return value.brandName;
	}else {
		return value;
	}
}

function kindFormatter(value,record,index){
	if(value){
		return value.name;
	}else {
		return value;
	}
}













