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

<title>My JSP 'query1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
.td {
	border: solid #99BBE8;
	border-width: 0px 1px 1px 0px;
}

.table {
	border: solid #99BBE8;
	border-width: 1px 0px 0px 1px;
}
</style>
<jsp:include page="../common/include.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=basePath%>js/gonggongke/gonggongke.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">按公共课查询</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>

		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<div id="selectMode">
				<span class="title">查询方式:</span> <select id="selectType"
					class="easyui-combobox" name="dept">
					<option value="day">按日期方式查询</option>
					<option value="term">按学期方式查询</option>
				</select>
			</div>
			<!--按日期查询 -->
			<div id="queryDay">
				<!-- 
			<div>
				<span class="title">任课教师:</span> <input id="rkls_serchbar" /> <span
					class="title">授课教室:</span> <input id="skjs_serchbar" /> <span
					class="title">授课课程:</span> <input id="rkkc_serchbar" /> <span
					class="title">课时:</span> <input id="queryDayKs" />
			</div>
			 -->

					<table class="ssk" id="queryDay">
						<tr>
							<td class="title">任课教师:</td>
							<td><input id="rkls_serchbar" /></td>
							<td class="title">授课教室:</td>
							<td><input id="skjs_serchbar" /></td>
							<td class="title">授课课程:</td>
							<td><input id="rkkc_serchbar" /></td>
							<td class="title">课时:</td>
							<td><input id="queryDayKs" /></td>
						</tr>
						<tr>
							<!-- 授课的开始时间 -->
							<td class="title">授课开始日期:</td>
							<td><input id="sksj_kssj_serchbar" /></td>
							<!-- 授课的结束时间 -->
							<td class="title">授课结束日期:</td>
							<td><input id="sksj_jssj_serchbar" type="text" /></td>
							<td><button onclick="doSearch();"></button></td>
							<td><input type="reset" class="qx" value=""></input></td>
						</tr>
						<tr>
							<span style="font-size:12px;color: red;">*注:可通过课程、教师、时间、教室等方式查询结果.</span>
						</tr>
					</table>

			</div>

			<div id="queryTerm">
				<div>
					<span class="title">选择学期:</span> <input id="XUEQI_serchbar"
						name="XUEQI_ID" /> <span class="title">周:</span> <input
						id="ZHOU_serchbar" /> <span class="title">天:</span> <input
						id="XINGQI_serchbar" /> <span class="title">课时:</span> <input
						id="queryTermKs" />
				</div>
			</div>
			<!-- 
	<div class="ts">
		<button class="easyui-linkbutton" plain="false" onclick="doSearch();"></button>
	</div>
	 -->
			<form id="serform" method="post"></form>
		</div>
</body>
</html>
