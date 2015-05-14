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
<script type="text/javascript" src="<%=basePath%>js/jiaoshi/jiaoshi.js"></script>
		<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file"></span>
				<span class="tree-title">教室管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
				<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onclick="addJIAOSHI_dialog();" plain="true"
			class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
			onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
			iconCls="icon-remove">删除信息</a>

			<table class="ssk">
				<tr>
					<td class="title"><span>教室名称:</span></td>
					<td><input id="JSMC_serchbar" name="JSMC" /></td>
					<td class="title"><span>房间名称:</span></td>
					<td><input id="FJMC_serchbar" name="FJMC" /></td>
					<td class="title"><span>有无多媒体:</span></td>
					<td><input id="ISDMT_serchbar" name="ISDMT" /></td>
				</tr>
				<tr>
					<td class="title"><span>房间代码:</span></td>
					<td><input id="FJDM_serchbar" name="FJDM" /></td>
					<td class="title"><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
				</tr>
			</table>

		<div class="one"></div>
		<div class="one"></div>
	</div>
	<!-- 新增对话框  BEGIN-->
	<div id="addJIAOSHI_dlg" class="easyui-dialog"
		style="width:400px;height:220px;padding:10px 20px" closed="true"
		buttons="#addJIAOSHI_dlg-buttons" modal="true">
		<form id="addJIAOSHI_form" method="post">
			<table>
				<tr>
					<td class="title"><label>教室名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="JSMC"
						name="JSMC" data-options="required:true,missingMessage:'请输入教室名称'" /></td>
				</tr>
				<tr>
					<td class="title"><label>房间:</label></td>
					<td><input class="easyui-combobox" id="FJID" name="FJID"
						style="width:230px;"
						data-options="valueField:'fjId',textField:'fjdm',url:'<%=basePath%>listAllFANGJIAN.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label>描述:</label></td>
					<td><input type="text" id="MS" name="MS" /></td>
				</tr>
				<tr>
					<td class="title"><label>备注:</label></td>
					<td><input type="text" id="BZ" name="BZ" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addJIAOSHI_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addJIAOSHI();" iconCls="icon-ok">保存</a>
	</div>
	<!-- 新增对话框  END-->
	<!-- 查看/修改对话框  BEGIN-->
	<div id="updateJIAOSHI_dlg" class="easyui-dialog"
		style="width:400px;height:220px;padding:10px 20px" closed="true"
		buttons="#update_JIAOSHI_dlg-buttons" modal="true">
		<form id="updateJIAOSHI_form" method="post">
			<table>
				<tr>
					<td class="title"><label>教室名:</label></td>
					<td><input type="text" id="update_JSMC" name="JSMC" /></td>
				</tr>
				<tr>
					<td class="title"><label>房间:</label></td>
					<td><input class="easyui-combobox" id="update_FJID"
						name="FJID" style="width:230px;"
						data-options="valueField:'fjId',textField:'fjdm',url:'<%=basePath%>listAllFANGJIAN.action'" /></td>
				</tr>
			</table>
			<input type="hidden" id="update_JS_ID" name="JS_ID" />
		</form>
	</div>
	<div id="update_JIAOSHI_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateJIAOSHI();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改对话框  END-->
		</div>
	

</body>
</html>

