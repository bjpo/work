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
	<script type="text/javascript" src="<%=basePath%>js/nianji/nianji.js"></script>

	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">年级管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addNIANJI_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>年级名称:</span></td>
						<td><input id="NJMC_serchbar" name="NJMC" /></td>
						<td class="title"><span>年级代码:</span></td>
						<td><input id="NJDM_serchbar" name="NJDM" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

			<div class="one"></div>
		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addNIANJI_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addNIANJI_dlg-buttons" modal="true">
			<form id="addNIANJI_form" method="post">
				<table>
					<tr>
						<td class="title"><label>年级名称:</label></td>
						<td><input class="easyui-validatebox" type="text" id="NJMC"
							name="NJMC" data-options="required:true,missingMessage:'请输入年级名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>年级代码:</label></td>
						<td><input class="easyui-validatebox" type="text" id="NJDM"
							name="NJDM" data-options="required:true,missingMessage:'请输入年级代码'" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input class="easyui-validatebox" type="text" id="BZ"
							name="BZ" data-options="required:true" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input class="easyui-validatebox" type="text" id="MS"
							name="MS" data-options="required:true" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addNIANJI_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addNIANJI();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateNIANJI_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_NIANJI_dlg-buttons" modal="true">
			<form id="updateNIANJI_form" method="post">
				<table>
					<tr>
						<td class="title"><label>年级名称:</label></td>
						<td><input type="text" id="update_NJMC" name="NJMC" /></td>
					</tr>
					<tr>
						<td class="title"><label>年级代码:</label></td>
						<td><input type="text" id="update_NJDM" name="NJDM" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input type="text" id="update_BZ" name="BZ" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input type="text" id="update_MS" name="MS" /></td>
					</tr>
				</table>
				<input type="hidden" id="update_NJ_ID" name="NJ_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_NIANJI_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateNIANJI();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>

