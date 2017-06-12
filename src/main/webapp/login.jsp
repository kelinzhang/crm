<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="application/javascript" src="/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小码哥客户关系管理系统</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script>
    <script type="application/javascript">
        $(function () {
            $(document).keydown(function (event) {
                if(event.keyCode==13){
                    loginForm();
                }
            });
            $("#btn_login").click(function () {
            
            	
            	
                loginForm();
            });
            
        });
        function loginForm() {
            $.post("/employee/login", $("form").serialize(), function (data) {
                if (data.success) {
                    window.location.href = "/index";
                } else {
                    alert(data.msg);
                }
            },"json");
        }
        function chageCode(){
            $('#codeImage').attr('src','/yanController/valicode.do?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
            
        }
    </script>
</head>
<body>
	<div>
		<img  class="img" src="../../images/login/bai.png">
	</div>
<section class="container">
    <div class="login">
        <h1>用户登录</h1>
        <form action="/employee/login" method="post">
            <p><input type="text" name="username" value="" placeholder="账号"></p>
            <p><input type="password" name="password" value="" placeholder="密码"></p>
           <p><input  id="authCode" name="authCode" type="text" placeholder=" 验证码"></p>
        <!--这里img标签的src属性的值为后台实现图片验证码方法的请求地址-->
        <label><img type="image" src="/yanController/valicode.do" id="codeImage" onclick="chageCode()" title="图片看不清？点击重新得到验证码" style="cursor:pointer;"/></label>
        <label><a onclick="chageCode()">换一张</a></label>
            
            
            
            <p class="submit">
                <input id="btn_login" type="button" value="登录">
                <input type="button" value="重置">
            </p>
        </form>
    </div>
</section>
<div style="text-align:center;" class="login-help">
    <p>Copyright ©2015 广州小码哥教育科技有限公司</p>
</div>
</body>
</html>