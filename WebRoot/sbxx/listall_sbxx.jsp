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
<!-- 引入sbxx.js文件 -->
<script type="text/javascript" src="<%=basePath%>js/sbxx/sbxx.js"
	charset="UTF-8"></script>
</head>
<body class="easyui-layout">
	<script type="text/javascript" src="<%=basePath%>js/sbxx/sbxx.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">设备信息管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addSBXX_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>
			<form>
				<table class="ssk">
					<tr>
						<td class="title"><span>设备ID:</span></td>
						<td><input id="ID_serchbar" name="ID" /></td>
						<td class="title"><span>设备名称:</span></td>
						<td><input id="SBMC_serchbar" name="SBMC" /></td>
						<td class="title"><span>教室名称:</span></td>
						<td><input id="JSMC_serchbar" name="JSMC" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><input type="reset" class="qx" value="" /></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addSBXX_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#addSBXX_dlg-buttons" modal="true">
			<form id="addSBXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>设备ID:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="SBXX_ID" name="SBXX_ID"
							data-options="required:true,missingMessage:'请输入设备ID号 格式:1-9任意,1到4位'" /></td>
					</tr>
					<tr>
						<td class="title"><label>设备名称:</label></td>
						<td><input class="easyui-validatebox" type="text" id="SBMC"
							name="SBMC" data-options="required:true,missingMessage:'请输入设备名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>教室名称:</label></td>
						<td><input class="easyui-combobox" type="text" id="JSMC"
							name="JSMC"
							data-options="valueField:'JS_ID',textField:'JSMC',url:'listAllJIAOSHI.action',missingMessage:'请选择教室名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注信息:</label></td>
						<td><input class="easyui-validatebox" type="text" id="BZ"
							name="BZ" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述信息:</label></td>
						<td><input class="easyui-validatebox" type="text" id="MS"
							name="MS" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addSBXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addSBXX();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateSBXX_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#update_SBXX_dlg-buttons" modal="true">
			<form id="updateSBXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>设备ID:</label></td>
						<td><input type="text" id="update_SBXX_ID" name="SBXX_ID"
							data-options="required:true"
							missingMessage="请输入设备ID 格式:1-9任意,1到4位'" /></td>
					</tr>
					<tr>
						<td class="title"><label>设备名称:</label></td>
						<td><input type="text" id="update_SBMC" name="SBMC"
							data-options="required:true" missingMessage="请输入设备名称" /></td>
					</tr>
					<tr>
						<td class="title"><label>教室名称:</label></td>
						<td><input class="easyui-combobox" type="text"
							id="update_JSMC" name="JSMC" data-options="required:true"
							missingMessage="请选择教室名称" /></td>
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
				<input type="hidden" id="update_JS_ID" name="JS_ID" /> <input
					type="hidden" id="update_ID" name="ID" /> <input type="hidden"
					id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_SBXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateSBXX();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>

