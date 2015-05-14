<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'zwlrPlug.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript">
	function closeDownLoad() {
		$("#downLoad").css({
			display : "none"
		});
	}
	/*
	$("#gb").click(function() {
		
	});*/
	function downLoad() {
		$('#downLoad').form('submit', {
			url : download.action,
			success : function(data) {
				alert(data)
			}
		});

	}
</script>

</head>

<body>
	<div id="downLoad">
	<span>未检测到指纹录入所需要的插件请进行下载</span>
	<a id="gb" onclick="closeDownLoad();"><img
		src="<%=basePath%>/img/close.png" /></a>
	<a class="button" href="download.action"> </a>
	</div>
</body>
</html>
