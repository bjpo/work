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

<title>用户组列表</title>

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
	<script type="text/javascript" src="<%=basePath%>js/yonghu/yonghuzu.js"></script>

	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">用户组管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addYHZ_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增用户组信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除用户组信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>用户组名称:</span></td>
						<td><input id="yhzmc_serchbar" name="yhz_yhzmc" /></td>
						<td class="title"><span>用户组备注:</span></td>
						<td><input id="yhzbz_serchbar" name="yhz_bz" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增用户组对话框  BEGIN-->
		<div id="addyhz_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addyhz_dlg-buttons" modal="true">
			<form id="addYHZ_form" method="post">
				<table>
					<tr>
						<td class="title"><label for="yhzmc">用户组名称:</label>
						<td>
						<td><input class="easyui-validatebox" type="text"
							id="yhz_yhzmc" name="yhz_yhzmc"
							data-options="required:true,missingMessage:'请输入用户组名称'" />
						<td>
					</tr>
					<tr>
						<td class="title"><label for="yhz_bz">用户组备注:</label>
						<td>
						<td><input class="easyui-validatebox" type="text" id="yhz_bz"
							name="yhz_bz" />
						<td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addyhz_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addYHZ();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增用户组对话框  END-->

		<!-- 查看/修改用户组对话框  BEGIN-->
		<div id="updateyhz_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_yhz_dlg-buttons" modal="true">
			<form id="updateYHZ_form" method="post">
				<table>
					<tr>
						<td class="title"><label for="update_yhz_yhzmc">用户组名称:</label></td>
						<td><input type="text" id="update_yhz_yhzmc" name="yhz_yhzmc" />
							<input type="hidden" id="update_yhz_id" name="yhz_id" /> <input
							type="hidden" id="update_optionflag" name="optionflag" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_yhz_bz">用户组备注:</label></td>
						<td><input type="text" id="update_yhz_bz" name="yhz_bz" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="update_yhz_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateYHZ();" iconCls="icon-ok">修改用户组信息</a>
		</div>
		<!-- 查看/修改用户组对话框  END-->
	</div>


</body>
</html>
