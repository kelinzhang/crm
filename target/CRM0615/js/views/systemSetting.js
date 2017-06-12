$(function(){
	
	$("div[data-cmd]").on("click",function(){
		var methods = $(this).data("cmd");
		if(methods){
			cmdObj[methods]();
		}
	})
	var cmdObj = {
		loginLogMethod:function(){
			/*var log = $("#showgrid")[0].src;
			console.log(log);
			log="http://localhost/loginLog";*/
			/*$("#showgrid").src='http://localhost/systemLog'*/
			document.getElementById("showgrid").src='/systemLog'
		},
		proKindMethod:function(){
			document.getElementById("showgrid").src='/productKind'
		},
		supplierMethod:function(){
			document.getElementById("showgrid").src='/supplier'
		},
		brandMethod:function(){
			document.getElementById("showgrid").src='http://localhost/productBrand'
		},
		productMethod:function(){
			document.getElementById("showgrid").src='http://localhost/product'
		},
		vipLevelMethod:function(){
			document.getElementById("showgrid").src='http://localhost/vipLevel'
		},
		petBreedMethod:function(){
			document.getElementById("showgrid").src='http://localhost/petBreed'
		}
	}
})