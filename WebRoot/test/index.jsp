<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="../common/include.jsp"></jsp:include>
</head>

<body>
<body>
<%=basePath %>
	<s:form action="asdf.action">
		用户名:<input name="username" class="easyui-validatebox" data-options="required:true"/>
		密码：<input name="userpwd" class="easyui-validatebox" data-options="required:true"/>
		<s:submit label ="注册"/>
	</s:form>
<a href="xx.action">dojson.action</a>
</body>
</body>
</html>
