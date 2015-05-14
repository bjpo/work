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
	<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
		<ul style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">班级管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addBANJI_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>年级代码:</span></td>
						<td><input id="NJDM_serchbar" name="NJDM" /></td>
						<td class="title"><span>专业代码:</span></td>
						<td><input id="ZYDM_serchbar" name="ZYDM" /></td>
						<td class="title"><span>年级名称:</span></td>
						<td><input id="NJMC_serchbar" style="name=" NJMC" /></td>
					</tr>
					<tr>
						<td class="title"><span>专业名称:</span></td>
						<td><input id="ZYMC_serchbar" name="ZYMC" /></td>
						<td class="title"><span>学生类型:</span></td>
						<td><input id="BZK_serchbar" name="BZK" /></td>
						<td class="title"><span>班级名称:</span></td>
						<td><input id="BJMC_serchbar" name="BJMC" /></td>
					</tr>
					<tr>
						<td class="title"><span>班级号:</span></td>
						<td><input id="BJHM_serchbar" name="BJHM" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addBANJI_dlg" class="easyui-dialog"
			style="width:400px;height:280px;padding:10px 20px" closed="true"
			buttons="#addBANJI_dlg-buttons" modal="true">
			<form id="addBANJI_form" method="post">
				<div>
					<%--<label >班级名称:</label>--%>
					<input class="easyui-validatebox" type="hidden" id="BJMC"
						name="BJMC" data-options="required:true" />
				</div>
				<table>
					<tr>
						<td class="title"><label>班级号:</label></td>
						<td><input class="easyui-validatebox" type="text" id="BJHM"
							name="BJHM" data-options="required:true,missingMessage:'请填写班级号'" /></td>
					</tr>
					<tr>
						<td class="title"><label>年级:</label></td>
						<td><input class="easyui-combobox" id="NJ_ID" name="NJ_ID"
							style="width:240px;"
							data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJIFORBANJI.action'" /></td>
					</tr>
					<tr>
						<td class="title"><label>专业:</label></td>
						<td><input class="easyui-combobox" id="ZY_ID" name="ZY_ID"
							style="width:240px;"
							data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYEFORBANJI.action'" /></td>
					</tr>
					<tr>
						<td class="title"><label>学生类型:</label></td>
						<td><select class="easyui-combobox" id="BZK" name="BZK"
							style="width:50px;">
								<option value=" "></option>
								<option value="研究生">研究生</option>
								<option value="本科">本科</option>
								<option value="专科">专科</option>
						</select></td>
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
		<div id="addBANJI_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addBANJI();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateBANJI_dlg" class="easyui-dialog"
			style="width:400px;height:280px;padding:10px 20px" closed="true"
			buttons="#update_BANJI_dlg-buttons" modal="true">
			<form id="updateBANJI_form" method="post">
				<table>
					<tr>
						<td class="title"><label>班级名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_BJMC" name="BJMC"
							data-options="required:true,missingMessage:'请输入班级名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>班级号:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_BJHM" name="BJHM"
							data-options="required:true,missingMessage:'请输入班级号'" /></td>
					</tr>
					<tr>
						<td class="title"><label>年级:</label></td>
						<td><input class="easyui-validatebox easyui-combobox"
							id="update_NJ_ID" name="NJ_ID" style="width:240px;"
							data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJIFORBANJI.action',missingMessage:'请选择年级'" /></td>
					</tr>
					<tr>
						<td class="title"><label>专业:</label></td>
						<td><input
							class="easyui-validatebox easyui-validatebox easyui-combobox"
							id="update_ZY_ID" name="ZY_ID" style="width:240px;"
							data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYEFORBANJI.action',missingMessage:'请选择专业'" /></td>
					</tr>
					<tr>
						<td class="title"><label>学生类型:</label></td>
						<td><select class="easyui-combobox" id="update_BZK"
							name="BZK" style="width:50px;"
							data-options="required:true,missingMessage:'请选择学生类型'">
								<option value=" "></option>
								<option value="研究生">研究生</option>
								<option value="本科">本科</option>
								<option value="专科">专科</option>
						</select></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input type="text" id="update_MS" name="MS" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input type="text" id="update_BZ" name="BZ" /></td>
					</tr>
				</table>
				<input type="hidden" id="update_BJ_ID" name="BJ_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_BANJI_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateBANJI();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
	<script type="text/javascript" src="<%=basePath%>js/banji/banji.js"></script>

</body>
</html>

