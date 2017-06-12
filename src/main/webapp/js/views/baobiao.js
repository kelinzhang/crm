$(function () {
	
	 var petServiceChartData = $("#petServiceChartData");
	 var stockChartData = $("#stockChartData");
	
	/**
	 * 统一监听点击图片事件
	 */
	$("div[data-cmd]").on("click",function(){
		
		var methods = $(this).data("cmd");
		if(methods){
			cmdObj[methods]();
		}
	})
	var cmdObj = {
		//宠物服务方法
		pitServiceMethod:function(){
			document.getElementById("showgrid").src='http://localhost/petChart'
		},
		stockMethod:function(){
			document.getElementById("showgrid").src='http://localhost/stockChart'
		},
		
		cashMethod:function(){
			document.getElementById("showgrid").src='http://localhost/memberChart'
		}
	}
	
   
   
});
