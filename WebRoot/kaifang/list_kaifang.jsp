<%@page import="org.apache.commons.fileupload.RequestContext"%>
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

<title>My JSP 'listkaifang.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<jsp:include page="../common/include.jsp"></jsp:include>
<!-- 引入js文件 -->
<script type="text/javascript" src="<%=basePath%>js/kaifang/kaifang.js"></script>
</head>

<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">开放性课程</span>
			</ul>
		</div>
		<div data-options="region:'center'">
				<!--显示开放性课程信息列表  -->
	<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<!-- 查询条件 开始-->
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<form id="serform">
			<table class="ssk">
				<tr>
					<td class="title"><span>开始日期:</span></td>
					<td><input id="ksTime_serchbar" name="skkssj"  /></td>
					<td class="title"><span>结束日期:</span></td>
					<td><input id="jsTime_serchbar" name="xkjssj" /></td>
				</tr>
				<tr>
					<td class="title"><span>课时:</span></td>
					<td><input id="ks_serchbar" name="KS_ID" /></td>
					<td class="title"><span>任课教师:</span></td>
					<td><input id="rkjs_serchbar" name="JG_ID" /></td>
				</tr>
				<tr>
					<td class="title"><span>课程名称:</span></td>
					<td><input id="kcmc_serchbar" name="KCB_ID" /></td>
					<td class="title"><span>上课教室:</span></td>
					<td><input id="skjs_serchbar" name="JS_ID" /></td>
					<td class="title"><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><input type="reset" class="qx" value="" /></td>
				</tr>
			</table>
		</form>
	</div>
	<form id="dataToDetail" method="post"></form>
		</div>

</body>
</html>
