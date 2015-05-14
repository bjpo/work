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

<script type="text/javascript">
  $(function() {
	$('#datagrid').datagrid({
		url : 'enterView.action?KS_ID=<%=request.getParameter("KS_ID")%>'+'&JS_ID=<%=request.getParameter("JS_ID")%>'+'&SKSJ_KS=<%=request.getParameter("SKSJ_KS")%>'+'&SKSJ_JS=<%=request.getParameter("SKSJ_JS")%>'+'&KCB_ID=<%=request.getParameter("KCB_ID")%>'+'&KCB_FXS_ID=<%=request.getParameter("KCB_FXS_ID")%>'+'&JG_ID=<%=request.getParameter("JG_ID")%>',
							title : '按公共课查询',
							iconCls : 'icon-cls',
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'XS_ID',
							columns : [ [ {
								title : '<b>学生姓名</b>',
								field : 'ZSXM',
								sortable : true,
								width : 100
							}, {
								title : '<b>学号</b>',
								field : 'XSXH',
								sortable : true,
								width : 100
							}, {
								title : '<b>性别</b>',
								field : 'XB',
								width : 100
							}, {
								title : '<b>所学专业</b>',
								field : 'ZYMC',
								width : 100
							}, {
								title : '<b>所在班级</b>',
								field : 'BJMC',
								width : 100
							}, {
								title : '<b>所在年级</b>',
								field : 'NJMC',
								width : 100
							}, {
								title : '<b>出席状态</b>',
								field : 'CQZT',
								width : 100
							} ] ],
							loadMsg : null,
							singleSelect : false,// 是否单选
							pagination : true,// 分页控件
							rownumbers : true
						});
		$('#datagrid').datagrid('getPager').pagination({
			pageSize : 10,// 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});
</script>
</head>
<body>

	<!-- 显示数据的表格 -->
	<table id="datagrid" toolbar="#toolbar"></table>

	<div id="toolbar" style="padding:3px;padding-top:0px;margin-left: 5px;">
			<table class="ssk">
			<form>
				<tr>
					<td class="title"><span>学生姓名:</span></td>
					<td><input id="xsxm_serchbar"/></td>
					<td class="title"><span>学生学号:</span></td>
					<td><input id="xsxh_serchbar"/></td>
					<td class="title"><span>出勤状态:</span></td>
					<td><input id="cqzt_serchbar" /></td>
				</tr>
				<tr>
					<td class="title"><span>所学专业:</span></td>
					<td><input id="zymc_serchbar"/></td>
					<td class="title"><span>所在班级:</span></td>
					<td><input id="bjmc_serchbar" /></td>
					<td class="title"><span>所在年级:</span></td>
					<td><input id="njmc_serchbar" /></td>
				</tr>
				<form>
				<tr>
					<td></td>
					<td></td>
					<td ><button  class="easyui-linkbutton" onclick="doSearch();"></button></td>
					<td><input type="reset" class="qx" value="" /></td>
					<td><a href='javascript:history.go(-1);' class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
				</tr>
			</table>
	</div>
	<script type="text/javascript"
	src="<%=basePath%>js/gonggongke/enterView.js"></script>
</body>
</html>
