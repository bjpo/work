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

<title>教学楼列表</title>

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
<jsp:include page="../../common/include.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<script type="text/javascript"
		src="<%=basePath%>/js/basicinfo/buildings/buildings.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">教学楼管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="buildings_datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addJXL_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">添加</a> <a onclick="delJXL_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-remove">删除</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>教学楼名称:</span></td>
						<td><input id="jxlmc_serchbar" name="jxlmc" /></td>
						<td class="title">
							<button class="easyui-linkbutton" plain="true"
								onclick="doSearch();"></button>
						</td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增用户对话框  BEGIN-->
		<div id="addjxl_dlg" class="easyui-dialog"
			style="width:400px;height:240px;padding:10px 20px" closed="true"
			buttons="#addjxl_dlg-buttons" modal="true">
			<form id="building_addJXL_form" method="post">
				<div>
					<label for="jxl_mc">教学楼名称:</label> <input
						class="easyui-validatebox" type="text" id="jxl_mc" name="jxlmc"
						data-options="required:true,missingMessage:'请输入教学楼名称'" />
				</div>
				<div>
					<label for="jxl_dm">教学楼代码:</label> <input
						class="easyui-validatebox" type="text" id="jxl_dm" name="jxldm"
						data-options="required:true,missingMessage:'请输入教学楼代码'" />
				</div>
				<div>
					<label for="jxl_lh">教学楼楼号:</label> <input
						class="easyui-validatebox" type="text" id="jxl_lh" name="jxllh"
						data-options="required:true,missingMessage:'请输入教学楼楼号'" />
				</div>
				<div>
					<label for="jxl_wz">教学楼位置:</label> <input
						class="easyui-validatebox" type="text" id="jxl_wz" name="jxlwz"
						data-options="required:true,missingMessage:'请输入教学楼位置'" />
				</div>
			</form>
		</div>
		<div id="addjxl_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addJXL();" iconCls="icon-ok">新增教学楼信息</a>
		</div>
		<!-- 新增用户对话框  END-->

		<!-- 查看/修改用户对话框  BEGIN-->
		<div id="updatejxl_dlg" class="easyui-dialog"
			style="width:400px;height:240px;padding:10px 20px" closed="true"
			buttons="#update_jxl_dlg-buttons" modal="true">
			<form id="building_updateJXL_form" method="post">
				<div>
					<label for="update_jxl_mc">教学楼名称:</label> <input type="text"
						id="update_jxlmc" name="jxlmc" /> <input type="hidden"
						id="update_jxlId" name="jxlId" />
				</div>
				<div>
					<label for="update_jxl_dm">教学楼代码:</label> <input type="text"
						id="update_jxldm" name="jxldm" />
				</div>
				<div>
					<label for="update_jxl_dm">教学楼楼号:</label> <input type="text"
						id="update_jxllh" name="jxllh" />
				</div>
				<div>
					<label for="update_jxl_dm">教学楼位置:</label> <input type="text"
						id="update_jxlwz" name="jxlwz" />
				</div>
			</form>
		</div>
		<div id="update_jxl_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateJXL();" iconCls="icon-ok">修改教学楼信息</a>
		</div>
		<!-- 查看/修改用户对话框  END-->
	</div>



</body>
</html>
