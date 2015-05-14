<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>My JSP 'list_zwdd.jsp' starting page</title>
<jsp:include page="../common/include.jsp" />
<script type="text/javascript" src="<%=basePath%>js/zwdd/zwdd.js"></script>
</head>

<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true" style="width:17%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">实验仪器设备远程监管及管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<form>
			<table class="ssk">
				<tr>
					<td class="title"><span>设备名称:</span></td>
					<td><input id="SBMC_serchbar" /></td>
					<td class="title"><span>设备编号:</span></td>
					<td><input id="SBBH_serchbar"/></td>
					<td class="title"><span>所在教室:</span></td>
					<td><input id="SZJS_serchbar"/></td>
					<td class="title"><span>当前状态:</span></td>
					<td><input id="DAZT_serchbar"/></td>
					<td class="title"><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><input type="reset" class="qx" value="" /></td>
				</tr>
			</table>
		</form>
		<div class="one"></div>
	</div>
		</div>
	
</body>
</html>
