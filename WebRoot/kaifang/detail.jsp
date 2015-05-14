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

<title>My JSP 'detail.jsp' starting page</title>

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
<script type="text/javascript" src="<%=basePath%>js/kaifang/detail.js"></script>
<script type="text/javascript">
	$(function() {
	$('#datagrid').datagrid({
		 url : 'detail.action?KS_ID=<%=request.getParameter("KS_ID")%>'+'&JS_ID=<%=request.getParameter("JS_ID")%>'	+ '&skkssj=<%=request.getParameter("ksTime")%>'+'&xkjssj=<%=request.getParameter("jsTime")%>'+'&KCB_ID=<%=request.getParameter("KCB_ID")%>'+'&KCB_FXS_ID=<%=request.getParameter("KCB_FXS_ID")%>'+'&JG_ID=<%=request.getParameter("JG_ID")%>'+'&<%=request.getParameter("newTime")%>',
		title : '详细内容列表',
		iconCls : 'icon-cls',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false,
		nowrap : false,
		border : false,
		//idField : 'xskqid',
		columns : [ [ {
			title : '<b>课程名称</b>',
			field : 'kcmc',
		sortable : true,
			width : 100
		}, {
			title : '<b>上课教师</b>',
			field : 'lsxm',
			sortable : true,
			width : 100
		}, {
			title : '<b>学号</b>',
			field : 'xsxh',
			sortable : true,
			width : 100
		}, {
			title : '<b>姓名</b>',
			field : 'xsxm',
			sortable : true,
			width : 100
		}, {
			title : '<b>出勤情况</b>',
			field : 'cqqk',
			width : 100
		}, {
			title : '<b>打卡时间(上课)</b>',
			field : 'sksj',
			fitColumns : true
		}, {
			title : '<b>打卡时间(下课)</b>',
			field : 'xksj',
			fitColumns : true
		} ] ],
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		rownumbers : true,// 行号
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ]
	});

	$('#datagrid').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});

function doSearch() {
	// 判断日期时间
	// var startTime = $('#ksTime_serchbar').combobox('getText');
	// var endTime = $('#jsTime_serchbar').combobox('getText');
	$('#datagrid').datagrid('load', {
		xsxh: $("#xsxh_serchbar").val(),
		xsxm: $("#xsxm_serchbar").val()
	});
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">开发性课程详细页面</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<!--显示开放性课程信息列表  -->
	<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<!-- 查询条件 开始-->
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<form>
			<table class="ssk">
				<!-- 
				<tr>
					<td class="title"><span>开始日期:</span></td>
					<td><input id="ksTime_serchbar"  /></td>
					<td class="title"><span>结束日期:</span></td>
					<td><input id="jsTime_serchbar"  /></td>
				</tr>
				<tr>
					<td class="title"><span>课时:</span></td>
					<td><input id="ks_serchbar" /></td>
					<td class="title"><span>任课教师:</span></td>
					<td><input id="rkjs_serchbar"  /></td>
				</tr>
				 -->
				<tr>
				<!-- 
					<td class="title"><span>课程名称:</span></td>
					<td><input id="kcmc_serchbar"  /></td>
					<td class="title"><span>上课教室:</span></td>
					<td><input id="skjs_serchbar"/></td>
					 -->
					 <td class="title"><span>学生学号:</span></td>
					<td><input id="xsxh_serchbar" name="xsxh"/></td>
					 <td class="title"><span>学生姓名:</span></td>
					<td><input id="xsxm_serchbar" name="xsxm"/></td>
					<td class="title"><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><input type="reset" class="qx" value="" /></td>
					<td><a class="easyui-linkbutton"
						href="javascript:history.go(-1)">返回</td>
				</tr>
			</table>
		</form>
	</div>
		</div>
	
</body>
</html>
