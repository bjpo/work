<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

#SignFrame td {
	border: 1px solid #666;
	padding: 3px;
	width: 80px;
	text-align: right;
}

#SignFrame td input {
	width: 70px;
}
.textbox{
position: relative;
padding:0;
top:-26px;
left:180px;
}
</style>
<jsp:include page="../common/include.jsp"></jsp:include>
<!-- 引入moban.js文件 -->
<script type="text/javascript" src="<%=basePath%>js/moban/moban.js"
	charset="UTF-8"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">模板管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addMOBAN_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>
			<form>
				<table class="ssk">
					<tr>
						<td class="title"><span>模板名称:</span></td>
						<td><input id="MB_NAME_serchbar" name="MB_NAME" /></td>
						<td class="title"><span>模板对应表:</span></td>
						<td><input id="BIAOMING_serchbar" name="BIAOMING" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><input type="reset" class="qx" value="" /></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addMOBAN_dlg" class="easyui-dialog"
			style="width:450px;height:320px;padding:10px 20px" closed="true"
			buttons="#addMOBAN_dlg-buttons" modal="true" resizable="true">
			<input type="hidden" id="MB_ID" name="MB_ID" />
			<form id="addMOBAN_form" method="post">
				<table>
					<tr>
						<td class="title"><label>模板名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="MB_NAME" name="MB_NAME" data-options="required:true"
							missingMessage="请输入模板名称" /></td>
					</tr>
					<tr>
						<td class="title"><label>模板对应表:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="BIAOMING" name="BIAOMING" data-options="required:true"
							missingMessage="请输入模板对应表的名称" /></td>
					</tr>

					<tr>
						<td class="title"><label>对应表字段:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="ZHUJIAN" name="ZHUJIAN" data-options="required:true"
							missingMessage="请输入模板对应表的主键" /></td>
					</tr>
					<tr></tr>
					<tr>
						<font size="2px" color="red">注意：用户名不能为中文</font>
						<td class="title"><label>用户名对应的列名:</label></td>
						<td><input type="text" class="easyui-validatebox"	name="userColumn"	data-options="required:true,missingMessage:'用户名对应的列名'" /></td>
					</tr>
					<tr>
						<s:radio list="#{'0':'是','1':'否'}" name="isCreateUser" value="0" label="是否创建相应用户"></s:radio>
					</tr>
				</table>
				<input type="text" name="insertType" id="insertType"/>
				<div>
					<table id="SignFrame">
						<tr id="trHeader">
							<td width="50">序号</td>
							<td width="80">Excel标题</td>
							<td width="80">DB字段</td>
							<td width="80">操作</td>
						</tr>
					</table>
				</div>
				<div style="text-align:justify;padding-top:5px;margin-left:3px">
					<a class="easyui-linkbutton" iconCls="icon-add" name="Submit"
						value="添加" onclick="AddSignRow();">添加</a> <a
						class="easyui-linkbutton" iconCls="icon-remove" name="Submit2"
						value="清空" onclick="ClearAllSign();">清空</a> <input
						name='txtTRLastIndex' type='hidden' id='txtTRLastIndex' value="1" />
				</div>
			</form>
			<%--模板配置对话框开始 --%>
			<%--
		<div id="templateconf_dlg" class="easyui-dialog"
			style="width:450px;height:370px;padding:10px 20px" closed="true"
			buttons="#templateSave-buttons" modal="true">
			<form id="templateconf_form" method="post">

				<div style="text-align:center;padding-top:20px;margin-left:10px;">
					<table width="350" border="0" id="SignFrame">
						<tr id="trHeader" style="align:center">
							<td width="50" bgcolor="#96E0E2">序号</td>
							<td width="80" bgcolor="#96E0E2">Excel标题</td>
							<td width="80" bgcolor="#96E0E2">DB字段</td>
							<td width="80" bgcolor="#96E0E2">操作</td>
						</tr>
					</table>
				</div>
				<div style="text-align:justify;padding-top:5px;margin-left:3px">
					<a class="easyui-linkbutton" iconCls="icon-add" name="Submit"
						value="添加" onclick="AddSignRow();">添加</a> <a
						class="easyui-linkbutton" iconCls="icon-remove" name="Submit2"
						value="清空" onclick="ClearAllSign();">清空</a> <input
						name='txtTRLastIndex' type='hidden' id='txtTRLastIndex' value="1" />
				</div>
			</form>
		</div>
		 --%>
			<%--模板配置对话框结束--%>
			<%--模板配置保存按键开始 --%>
			<%--
		<div id="templateSave-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="getData();" iconCls="icon-ok">保存模板</a>
		</div>
		 --%>
			<%--模板配置保存按键结束--%>
		</div>
		<div id="addMOBAN_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addMOBAN();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateMOBAN_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_MOBAN_dlg-buttons" modal="true">
			<form id="updateMOBAN_form" method="post">
				<input type="hidden" id="update_MB_ID" name="MB_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
				<table>
					<tr>
						<td class="title"><label>模板名称:</label></td>
						<td><input type="text" id="update_MB_NAME" name="MB_NAME" /></td>
					</tr>
					<tr>
						<td class="title"><label>模板对应表:</label></td>
						<td><input type="text" id="update_BIAOMING" name="BIAOMING" /></td>
					</tr>
					<tr>
						<td class="title"><label>对应主键名:</label></td>
						<td><input type="text" id="update_ZHUJIAN" name="ZHUJIAN" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="update_MOBAN_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateMOBAN();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>

