<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="common/include.jsp" />
</head>
<body>
<script type="text/javascript" src="<%=basePath%>js/head/head.js"></script>
<style type="text/css">
body {overflow: hidden;}
.bg {background: url(img/bg.png) repeat-y left;width: 100%;height: 60px;float: left;}
.bg .title {background: url(img/rjm.png);width: 337px;height: 21px;float: left;margin: 15px 0 0 20px;}
.bg .user {float: right;margin: 20px 0 0 0; width:900px;}
.bg .user span,.bg .user a {color: #174289;font-weight: 800;line-height: 18px;cursor: pointer;}
.bg .user .use {width: 100px;height: 16px;cursor: pointer;position: relative;}
.bg .user .use img {margin: 0 0 0 5px;}
.bg .user .use .content {width: 302px;height: 32px;background: url(img/grxx.png);position: relative; top:-23px; right:-40px;z-index: 10000;display: none;}
.bg .user .use .content ul {float: left;margin: 1px 0 0 8px;}
.bg .user .use .content li {margin: 0 0 0 2px;width: 71px;height: 27px;text-align: center;}
.bg .user .use .content li:hover {background: #e0ecff;}
.bg .user .use .content li span {line-height: 27px;}
.bg .user .date {margin-right: 40px;}
.bg .user .qh {margin-right: 20px;}
.bg .user .qh a:hover {color: #f00;}
.bg .user ul li {float: left;}
</style>
	<div class="bg">
		<div class="title"></div>
		<div class="user l-r">
			<ul>
				<li class="date"><span>当前系统时间： <span id="localtime"></span></span></li>
				<s:if test="#session.yhJs=='校长角色'">
					<li class="qh"><a href="xzqt/index.jsp">切换至精简版>></a></li>
				</s:if>
				<li style="margin-right: 10px"><a onclick="myNotice(this);"><span
						id="userNote">用户通知</span>(<span
						id="megNum">${sessionScope.message}</span>)</a></li>
				<li><span>当前用户：</span></li>
				<li class="use"><span>${yonghu.yhm}</span><img src="img/up.png" />
					<div class="content" id="grxx">
						<ul>
							<li><a onclick="perCenter(this);"><span>个人中心</span></a></li>
							<li><a onclick="changePwd(this);"><span>修改密码</span></a></li>
							<li><a onclick="myNotice(this);"><span id="userNote">通知中心</span></a></li>
							<li><a onclick="loginOut(this);"><span>退出登录</span></a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
	</div>
	
</body>
</html>
