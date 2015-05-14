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
<!-- 引入xueyuan.js文件 -->
<script type="text/javascript" src="<%=basePath%>js/zhuanye/zhuanye.js"
	charset="UTF-8"></script>
</head>
<body class="easyui-layout">

	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">专业管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addZHUANYE_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>专业名称:</span></td>
						<td><input id="ZYMC_serchbar" name="ZYMC" /></td>
						<td class="title"><span>专业代码:</span></td>
						<td><input id="ZYDM_serchbar" name="ZYDM" /></td>
						<td class="title"><span>学院名称:</span></td>
						<td><input id="XYMC_serchbar" name="XYMC" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addZHUANYE_dlg" class="easyui-dialog"
			style="width:400px;height:240px;padding:10px 20px" closed="true"
			buttons="#addZHUANYE_dlg-buttons" modal="true">
			<form id="addZHUANYE_form" method="post">
				<table>
					<tr>
						<td class="title"><label>专业名称:</label></td>
						<td><input class="easyui-validatebox" type="text" id="ZYMC"
							name="ZYMC" data-options="required:true,missingMessage:'请输入专业名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>专业代码:</label></td>
						<td><input class="easyui-validatebox" type="text" id="ZYDM"
							name="ZYDM" data-options="required:true,missingMessage:'请输入专业代码'" /></td>
					</tr>
					<tr>
						<td class="title"><label>学院名称:</label></td>
						<td><input class="easyui-combobox" type="text" id="XYMC"
							name="XYMC"
							data-options="valueField:'xyid',textField:'xymc',url:'listAllXUEYUAN.action',missingMessage:'请选择学院'" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input class="easyui-validatebox" type="text" id="MS"
							name="MS" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input class="easyui-validatebox" type="text" id="BZ"
							name="BZ" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addZHUANYE_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addZHUANYE();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateZHUANYE_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_ZHUANYE_dlg-buttons" modal="true">
			<form id="updateZHUANYE_form" method="post">
				<input type="hidden" id="update_XYID" name="XYID" />
				<table>
					<tr>
						<td class="title"><label>专业名称:</label></td>
						<td><input type="text" id="update_ZYMC" name="ZYMC" /></td>
					</tr>
					<tr>
						<td class="title"><label>专业代码:</label></td>
						<td><input type="text" id="update_ZYDM" name="ZYDM" /></td>
					</tr>
					<tr>
						<td class="title"><label>学院名称:</label></td>
						<td><input type="text" class="easyui-combobox"
							id="update_XYMC" name="XYMC" /></td>
						</trv>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input type="text" id="update_MS" name="MS" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input type="text" id="update_BZ" name="BZ" /></td>
					</tr>
				</table>
				<input type="hidden" id="update_ZY_ID" name="ZY_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_ZHUANYE_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateZHUANYE();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>