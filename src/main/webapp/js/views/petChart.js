$(function () {
	
	 var petServiceChartData = $("#petServiceChartData");
	
		    petServiceChartData.datagrid({
		        url:'/baobiao/queryPetServiceData',
		        fit: true,
		        fitColumns: true,
		        pagination: true,
		        toolbar:'#dataGrid_tb',
		        singleSelect: true,
		        rownumbers: true,
		        columns: [[
		            {field: 'groupByServiceName', title: '服务名称', align: 'center', width: 1},
		            {field: 'totalNumber', title: '服务次数', align: 'center', width: 1},
		            {field: 'totalAmount', title: '总金额', align: 'center', width: 1}
		        ]]
		    });
		    $('#graphics').click(function(){
		        window.showModalDialog("/baobiao/showWindow","","dialogHeight:600px;dialogWidth:800px");
		    });

});
