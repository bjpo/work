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
	<script type="text/javascript" src="<%=basePath%>js/xueqi/xueqi.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">学期管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addXUEQI_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>学期名称:</span></td>
						<td><input id="XQMC_serchbar" name="XQMC" /></td>
						<td class="title"><span>备注:</span></td>
						<td><input id="BZ_serchbar" name="BZ" /></td>
						<td class="title"><span>描述:</span></td>
						<td><input id="JSRQ_serchbar" name="JSRQ" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addXUEQI_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#addXUEQI_dlg-buttons" modal="true">
			<form id="addXUEQI_form" method="post">
				<table>
					<tr>
						<td class="title"><label>学期名称:</label></td>
						<td><input class="easyui-validatebox" type="text" id="XQMC"
							name="XQMC" data-options="required:true,missingMessage:'请输入学期名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>学期开始时间:</label></td>
						<td><input class="easyui-validatebox easyui-datebox"
							id="KSRQ" name="KSRQ"
							data-options="required:true,missingMessage:'请选择学期开始的时间',editable:false"></input></td>
					</tr>
					<tr>
						<td class="title"><label>学期结束时间:</label></td>
						<td><input class="easyui-validatebox easyui-datebox"
							id="JSRQ" name="JSRQ"
							data-options="required:true,missingMessage:'请选择学期结束的时间',editable:false"></input></td>
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
		<div id="addXUEQI_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addXUEQI();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateXUEQI_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#update_XUEQI_dlg-buttons" modal="true">
			<form id="updateXUEQI_form" method="post">
				<table>
					<tr>
						<td class="title"><label>学期名称:</label></td>
						<td><input type="text" id="update_XQMC" name="XQMC" /></td>
					</tr>
					<tr>
						<td class="title"><label>学期开始日期:</label></td>
						<td><input class="easyui-datebox" id="update_KSRQ"
							name="KSRQ" data-options="editable:false" /></td>
					</tr>
					<tr>
						<td class="title"><label>学期结束日期:</label></td>
						<td><input class="easyui-datebox" id="update_JSRQ"
							name="JSRQ" data-options="editable:false" /></td>
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
				<input type="hidden" id="update_XQ_ID" name="XQ_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_XUEQI_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateXUEQI();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>


</body>
</html>

