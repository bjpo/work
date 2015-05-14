<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>哈尔滨学院考勤系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<jsp:include page="common/include.jsp"/>
	
</head>
<body class="easyui-layout" fit="false">
	<div data-options="region:'north',split:true,href:'header.jsp'" id="head" style="border-bottom:none;border-top:none; height:50px; overflow-y: hidden;"></div>
		<div data-options="region:'center'">
			<div class="easyui-tabs" fit="true">
				<div title="考勤系统" href="kq.html" id="kq"></div>
			</div>
	</div>
</body>
</html>
