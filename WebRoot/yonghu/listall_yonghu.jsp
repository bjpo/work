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

<title>用户列表</title>

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
	<script type="text/javascript" src="<%=basePath%>js/yonghu/yonghu.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">用户管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addYH_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增用户信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除用户信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>用户名称:</span></td>
						<td><input id="yhmc_serchbar" name="yh_yhmc" /></td>
						<td class="title">
						<button class="easyui-linkbutton"	plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增用户对话框  BEGIN-->
		<div id="addyh_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addyh_dlg-buttons" modal="true">
			<form id="addYH_form" method="post">
				<table>
					<tr>
						<td class="title"><label for="addYH_YHZ_combobox">用户组:</label></td>
						<td><input class="easyui-combobox" id="addYH_YHZ_combobox"
							name="yhz_id" style="width:200px" /></td>
					</tr>
					<tr>
						<td class="title"><label for="yh_yhmc">用户名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="yh_yhmc" name="yh_yhmc" data-options="required:true"
							style="width:200px" /></td>
					</tr>
					<tr>
						<td class="title"><label for="yh_yhmm">用户密码:</label></td>
						<td><input class="easyui-validatebox" type="password"
							id="yh_yhmm" name="yh_yhmm" style="width:200px" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addyh_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addYONGHU();" iconCls="icon-ok">新增用户</a>
		</div>
		<!-- 新增用户对话框  END-->

		<!-- 查看/修改用户对话框  BEGIN-->
		<div id="updateyh_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_yh_dlg-buttons" modal="true">
			<form id="updateYH_form" method="post">
				<table>
					<tr>
						<td class="title"><label for="updateYH_YHZ_combobox">用户组:</label></td>
						<td><input class="easyui-combobox" id="updateYH_YHZ_combobox"
							name="yhz_id" style="width:200px" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_yh_yhmc">用户名称:</label></td>
						<td><input type="text" id="update_yh_yhmc" name="yh_yhmc"
							style="width:200px" /> <input type="hidden" id="update_yh_id"
							name="yh_id" style="width:200px" /> <input type="hidden"
							id="update_optionflag" name="optionflag" style="width:200px" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_yh_yhmm">用户密码:</label></td>
						<td><input type="text" id="update_yh_yhmm" name="yh_yhmm"
							style="width:200px" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="update_yh_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateYONGHU();" iconCls="icon-ok">修改用户信息</a>
		</div>
		<!-- 查看/修改用户对话框  END-->
	</div>


</body>
</html>
