$(function () {
	var changeId = 1;
	var changeText = "会员等级";
	var url;
	 var stockChartData = $("#stockChartData");
	//初始化combobox
	$("#stock_combobox").combobox({
		url:'../../js/data/combobox_data.json',    
	    valueField:'id',    
	    textField:'text',
	    onChange:function(newValue, oldValue){
	    	 stockChartData.datagrid("selectAll");
	    	 stockChartData.datagrid("clearSelections");
	    	changeId=newValue;
	    	console.log(changeId);
	    	console.log("changeId:"+changeId);
	    	console.log("newValue:"+newValue);
	    	//获取当前选中的value
	    	changeText = $("#stock_combobox").combobox("getText")
	    	console.log("changeText:"+changeText);
	    	if(newValue){
	    		stockChartData.datagrid("load",{
					changeId:newValue
				})
			}
	    }
	});
	console.log(changeId);
	
	 stockChartData.datagrid({
		        url:'/baobiao/queryStockData?changeId='+changeId,
		        fit: true,
		        fitColumns: true,
		        pagination: true,
		        toolbar:'#dataGrid_tb',
		        singleSelect: true,
		        rownumbers: true,
		        columns: [[
		            {field: 'groupByStockName', title:'被查询分类名称', align: 'center', width: 1},
		            {field: 'totalNumber', title: '库存总数量', align: 'center', width: 1},
		            {field: 'totalAmount', title: '库存总价值', align: 'center', width: 1}
		        ]]
		    });
		    $('#graphics').click(function(){
		    	if(changeId==1){
		    		window.showModalDialog("/baobiao/showWindowBrand","","dialogHeight:600px;dialogWidth:800px");
		    	}else{
		    		window.showModalDialog("/baobiao/showWindowKind","","dialogHeight:600px;dialogWidth:800px");
		    	}
		    });
		
	
});
