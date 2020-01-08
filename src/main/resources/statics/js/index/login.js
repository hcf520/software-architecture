var login = function(value) {
	$.ajaxSettings.async = false; //关闭异步
	$.post("/user/login", value, function(data, status) {
		if(data.code==200){
			console.log("登录成功信息："+JSON.stringify(data) + "->" + status);
//			window.location.href='http://localhost/index.html'
		}else{
			console.log("错误信息："+data.msg);
			console.log("错误信息："+JSON.stringify(data));
			var valiCode = $("#valiCode");
			valiCode.attr("src","/user/number.jpg" + "?" + Math.random());
		}
		
	});
	$.ajaxSettings.async = true//打开异步
}