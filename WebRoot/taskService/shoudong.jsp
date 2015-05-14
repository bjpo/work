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
<title>列表</title>
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
</head>
<body class="easyui-layout">
	<script type="text/javascript">
		//学生考勤计算
		function doxuesheng() {
			var urltmp = "doXueshengExecute.action";
			var riqival = $("#riqi_date").datebox('getValue');
			urltmp += "?riqi=" + riqival;
			$.ajax({
				url : urltmp,
				context : document.body,
				success : function(data) {
					$.messager.alert('标题', '学生考勤计算已经开始执行！');
				}
			});
		}
		//教室考勤计算
		function dobanji() {
			var urltmp = "doBanjiExecute.action";
			var riqival = $("#riqi_date").datebox('getValue');
			urltmp += "?riqi=" + riqival;
			$.ajax({
				url : urltmp,
				context : document.body,
				success : function(data) {
					$.messager.alert('标题', '班级考勤计算已经开始执行！');
				}
			});
		}
	</script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">考勤计算手动执行</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<div class="sskzt" style="padding:10px">
			<form>

				请选择日期： <input id="riqi_date" class="easyui-datebox"></input> <a
					id="btn1" onclick="doxuesheng();" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'">学生考勤计算</a> <a id="btn2"
					onclick="dobanji();" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'">教学班级考勤计算</a>
			</form>
		</div>
	</div>

</body>
</html>

