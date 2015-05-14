<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<link href="css/index.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui/jquery.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/login/login.js"></script>
<script type="text/javascript">
	//点击“换一张”
	function change(img) {
		img.src = "imageCode.action?date=" + new Date().getTime();
	}
</script>
<title>高等院校仪器设备与人员远程管理系统</title>
</head>
<body>
	<div class="content">
		<div class="main">
			<div class="top-button f-r">
				<ul>
				
					<li><a href="javaScript:void(0);" onclick="AddFavorite(window.location,document.title)"><font face="仿宋" size="3" color="red">加入收藏&nbsp;&nbsp;&nbsp;&nbsp;</b></font></a></li>
				
				</ul>
			</div>
			<div id="message"></div>
			<div class="clear"></div>
			<div id="scrollPics">
				<ul class="slider">
					<li><img src="img/1.jpg" /></li>
					<li><img src="img/2.jpg" /></li>
					<li><img src="img/3.jpg" /></li>
					<li><img src="img/4.jpg" /></li>
					<li><img src="img/5.jpg" /></li>
				</ul>
				<ul class="num">
					<li class="on">1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
				</ul>
			</div>
			<button class="button"></button>

			<!--判断是否显示登录面板 开始-->
			<s:if test="flag==null">
				<div class="topdown"></div>
				<div class="topbox">
					<button class="close" title="关闭"></button>
					<form id="loginForm" method="post">
						<input type="text" class="input01" id="j_username" name="uname"
							title="用户名" /> <input type="password" class="input02"
							id="j_password" name="upassword" title="密码" />
						<!--验证码输入框 class="input03" -->
						<input type="text" class="input03" name="imageCode" />
						<!--验证码显示-->
						<img src="imageCode.action" name="imageCode"
							onclick="change(this);" title="点击更换" />
						<!-- 显示错误信息 -->
						<font color="red"><s:property value="errorMsg" /></font>
						<button class="jin" type="submit" onclick="loginCheck();"
							title="登陆"></button>
					</form>
				</div>
			</s:if>
			<s:elseif test="flag==false">
				<div class="topdown" style="display:block"></div>
				<div class="topbox" style="display:block;">
					<button class="close" title="关闭"></button>
					<form id="loginForm" method="post">
						<input type="text" class="input01" id="j_username" name="uname"
							title="用户名" /> <input type="password" class="input02"
							id="j_password" name="upassword" title="密码" />
						<!--验证码输入框 class="input03" -->
						<input type="text" class="input03" name="imageCode" />
						<!--验证码显示-->
						<img src="imageCode.action" name="imageCode"
							onclick="change(this);" title="点击更换" />
						<!-- 显示错误信息 -->
						<font color="red"><s:property value="errorMsg" /></font>
						<button class="jin" type="submit" onclick="loginCheck();" title="登陆"></button>
					</form>
				</div>
			</s:elseif>
			<s:if test="flag==true">
				<div class="topdown"></div>
				<div class="topbox" style="display: none">
					<button class="close" title="关闭"></button>
					<form id="loginForm" method="post">
						<input type="text" class="input01" id="j_username" name="uname"
							title="用户名" /> <input type="password" class="input02"
							id="j_password" name="upassword" title="密码" />
						<!--验证码输入框 class="input03" -->
						<input type="text" class="input03" name="imageCode" />
						<!--验证码显示-->
						<img src="imageCode.action" name="imageCode"
							onclick="change(this);" title="点击更换" />
						<!-- 显示错误信息 -->
						<font color="red"><s:property value="errorMsg" /></font>
						<button class="jin" type="submit" onclick="loginCheck();"
							title="登陆"></button>
					</form>
				</div>
			</s:if>			
			<!--判断是否显示登录面板 结束-->
		</div>
	</div>
</body>
</html>
