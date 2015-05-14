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
<title>角色用户关系列表</title>
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
	<script type="text/javascript"
		src="<%=basePath%>js/yonghu/juese2yonghu.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">用户赋角色管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addJY_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增角色用户关系</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除角色用户信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>角色:</span></td>
						<td><input id="jsmc_serchbar" name="jy_jsmc" /></td>
						<td class="title"><span>用户名称:</span></td>
						<td><input id="yhmc_serchbar" name="jy_yhmc" /></td>
						<td class="title"><span>备注:</span></td>
						<td><input id="bz_serchbar" name="jy_bz" /></td>
					</tr>
					<tr>
						<td class="title"><span>描述:</span>
						<td><input id="ms_serchbar" name="jy_ms" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
	</div>

	<!-- 新增角色用户对话框  BEGIN-->
	<div id="addjy_dlg" class="easyui-dialog"
		style="width:400px;height:280px;padding:10px 20px" closed="true"
		buttons="#addJY_dlg-buttons" modal="true">
		<form id="addJY_form" method="post">
			<table>
				<tr>
					<td class="title"><label for="jy_jyid">角色名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="jy_jsid"
						name="jy_jsid" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="title"><label for="jy_yhid">用户名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="jy_yhid"
						name="jy_yhid" /></td>
				</tr>
				<tr>
					<td class="title"><label for="jy_ms">描述信息:</label></td>
					<td><input class="easyui-validatebox" type="text" id="jy_ms"
						name="jy_ms" /></td>
				</tr>
				<tr>
					<td class="title"><label for="jy_ms">备注信息:</label></td>
					<td><input class="easyui-validatebox" type="text" id="jy_bz"
						name="jy_bz" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addJY_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addJUESE2YONGHU();" iconCls="icon-ok">新增角色用户</a>
	</div>
	<!-- 新增角色用户对话框  END-->

	<!-- 查看/修改角色用户对话框  BEGIN-->
	<div id="updatejy_dlg" class="easyui-dialog"
		style="width:400px;height:280px;padding:10px 20px" closed="true"
		buttons="#update_jy_dlg-buttons" modal="true">
		<form id="updateJY_form" method="post">
			<table>
				<tr>
					<td><label for="update_jy_jsid">角色名称:</label></td>
					<td><input type="text" id="update_jy_jsid" name="jy_jsid" /> <input
						type="hidden" id="update_jy_id" name="jy_id" /> <input
						type="hidden" id="update_optionflag" name="optionflag" /></td>
				</tr>
				<tr>
					<td><label for="update_jy_yhid">用户名称:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="update_jy_yhid" name="jy_yhid" /></td>
				</tr>
				<tr>
					<td><label for="update_jy_ms">描述信息:</label></td>
					<td><input type="text" id="update_jy_ms" name="jy_ms" /></td>
				</tr>
				<td><label for="update_jy_bz">备注信息:</label></td>
				<td><input type="text" id="update_jy_bz" name="jy_bz" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="update_jy_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateJUESE2YONGHU();" iconCls="icon-ok">修改角色用户信息</a>
	</div>
	<!-- 查看/修改角色用户对话框  END-->
	</div>


</body>
</html>