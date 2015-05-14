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

<title>角色列表</title>

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
	<script type="text/javascript" src="<%=basePath%>js/yonghu/juese.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">角色管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px" class="sskzt">
			<a onclick="addJS_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增角色信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除角色信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>角色名称:</span></td>
						<td><input id="jsmc_serchbar" name="js_jsmc" /></td>
						<td class="title"><span>角色描述:</span></td>
						<td><input id="jsms_serchbar" name="js_ms" /></td>
						<td class="title"><span>角色备注:</span></td>
						<td><input id="jsbz_serchbar" name="js_bz" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增角色对话框  BEGIN-->
		<div id="addjs_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addjs_dlg-buttons" modal="true">
			<form id="addJS_form" method="post">
				<div>
					<label for="js_jsmc">角色名称:</label> <input
						class="easyui-validatebox" type="text" id="js_jsmc" name="js_jsmc"
						data-options="required:true,missingMessage:'请输入角色名称'" />
				</div>
				<div>
					<label for="js_ms">描述信息:</label> <input class="easyui-validatebox"
						type="text" id="js_ms" name="js_ms" />
				</div>
				<div>
					<label for="js_ms">备注信息:</label> <input class="easyui-validatebox"
						type="text" id="js_bz" name="js_bz" />
				</div>
			</form>
		</div>
		<div id="addjs_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addJUESE();" iconCls="icon-ok">新增角色</a>
		</div>
		<!-- 新增角色对话框  END-->

		<!-- 查看/修改角色对话框  BEGIN-->
		<div id="updatejs_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_js_dlg-buttons" modal="true">
			<form id="updateJS_form" method="post">
				<div>
					<label for="update_js_jsmc">角色名称:</label> <input type="text"
						id="update_js_jsmc" name="js_jsmc" /> <input type="hidden"
						id="update_js_id" name="js_id" /> <input type="hidden"
						id="update_optionflag" name="optionflag" />
				</div>
				<div>
					<label for="update_js_ms">角色描述:</label> <input type="text"
						id="update_js_ms" name="js_ms" />
				</div>
				<div>
					<label for="update_js_bz">角色备注:</label> <input type="text"
						id="update_js_bz" name="js_bz" />
				</div>
			</form>
		</div>
		<div id="update_js_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateJUESE();" iconCls="icon-ok">修改角色信息</a>
		</div>
		<!-- 查看/修改角色对话框  END-->
	</div>



</body>
</html>