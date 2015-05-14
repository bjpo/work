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
<title>角色模块关系列表</title>
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
		src="<%=basePath%>js/yonghu/juese2mokuai.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">模块赋予角色管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addjm_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增角色模块关系</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除角色模块信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>角色:</span></td>
						<td><input id="jsmc_serchbar" name="jm_jsmc" /></td>
						<td class="title"><span>模块名称:</span></td>
						<td><input id="mkmc_serchbar" name="jm_mkmc" /></td>
						<td class="title"><span>备注:</span></td>
						<td><input id="bz_serchbar" name="jm_bz" /></td>
					</tr>
					<tr>
						<td class="title"><span>描述:</span></td>
						<td><input id="ms_serchbar" name="jm_ms" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>










		</div>

		<!-- 新增角色模块对话框  BEGIN-->
		<div id="addjm_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#addjm_dlg-buttons" modal="true">
			<form id="addjm_form" method="post">
				<div>
					<label for="jm_jsid">角色名称:</label> <input
						class="easyui-validatebox" type="text" id="jm_jsid" name="jm_jsid"
						data-options="required:true" />
				</div>
				<div>
					<label for="jm_mkid">模块名称:</label> <input
						class="easyui-validatebox" type="text" id="jm_mkid" name="jm_mkid" />
				</div>
				<div>
					<label for="jm_ms">描述信息:</label> <input class="easyui-validatebox"
						type="text" id="jm_ms" name="jm_ms" />
				</div>
				<div>
					<label for="jm_bz">备注信息:</label> <input class="easyui-validatebox"
						type="text" id="jm_bz" name="jm_bz" />
				</div>
				<div>
					<label>是否可以为其他用户赋权:</label> <label for="jm_canfq_yes">是</label><input
						type="radio" id="jm_canfq_yes" name="jm_canfq" value="yes"
						checked="checked" /> <label for="jm_canfq_no">否</label><input
						type="radio" id="jm_canfq_no" name="jm_canfq" value="no" />
				</div>
			</form>
		</div>
		<div id="addjm_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addJUESE2MOKUAI();" iconCls="icon-ok">新增角色模块</a>
		</div>
		<!-- 新增角色模块对话框  END-->

		<!-- 查看/修改角色模块对话框  BEGIN-->
		<div id="updatejm_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#update_jm_dlg-buttons" modal="true">
			<form id="updatejm_form" method="post">
				<div>
					<label for="update_jm_jsid">角色名称:</label> <input type="text"
						id="update_jm_jsid" name="jm_jsid" /> <input type="hidden"
						id="update_jm_id" name="jm_id" /> <input type="hidden"
						id="update_optionflag" name="optionflag" />
				</div>
				<div>
					<label for="update_jm_mkid">模块名称:</label> <input
						class="easyui-validatebox" type="text" id="update_jm_mkid"
						name="jm_mkid" />
				</div>
				<div>
					<label for="update_jm_ms">描述信息:</label> <input type="text"
						id="update_jm_ms" name="jm_ms" />
					<div>
						<label for="update_jm_bz">备注信息:</label> <input type="text"
							id="update_jm_bz" name="jm_bz" />
					</div>
					<div>
						<label>是否可以为其他用户赋权:</label> <label for="update_jm_canfq">是</label><input
							type="radio" id="update_jm_canfq_yes" name="jm_canfq" value="yes"
							checked="checked" /> <label for="update_jm_canfq">否</label><input
							type="radio" id="update_jm_canfq_no" name="jm_canfq" value="no" />
					</div>
			</form>
		</div>
		<div id="update_jm_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateJUESE2MOKUAI();" iconCls="icon-ok">修改角色用户信息</a>
		</div>
		<!-- 查看/修改角色模块对话框  END-->
	</div>
</body>
</html>