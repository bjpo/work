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
	<script type="text/javascript" src="<%=basePath%>js/skxsxx/skxsxx.js"></script>

	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">上课学生信息管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addSKXSXX_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>课程名:</span></td>
						<td><input id="KCB_KCXXMC_serchbar" name="KCB_KCXXMC" /></td>
						<td class="title"><span>学生姓名:</span></td>
						<td><input id="XSXM_serchbar" name="XSXM" /></td>
						<td class="title"><span>学号:</span></td>
						<td><input id="XUEHAO_serchbar" name="XUEHAO" /></td>
					</tr>
					<tr>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>








		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addSKXSXX_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addSKXSXX_dlg-buttons" modal="true">
			<form id="addSKXSXX_form" method="post">
				<div>
					<label>课程:</label> <input
						class="easyui-validatebox easyui-combobox" id="KCB_ID"
						name="KCB_ID"
						data-options="valueField:'KCB_ID',textField:'KCXXMC',url:'<%=basePath%>listAllKECHENGB.action',required:true,missingMessage:'请选择课程'" />
				</div>
				<div>
					<label>学生:</label> <input
						class="easyui-validatebox easyui-combobox" id="XS_ID" name="XS_ID"
						data-options="valueField:'id',textField:'jiaoxuezhou',url:'<%=basePath%>js/tmp_xuesheng.json',required:true,missingMessage:'请选择学生'" />
				</div>
			</form>
		</div>
		<div id="addSKXSXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addSKXSXX();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateSKXSXX_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_SKXSXX_dlg-buttons" modal="true">
			<form id="updateSKXSXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>课程表:</label></td>
						<td><input class="easyui-combobox" id="update_KCB_ID"
							name="KCB_ID"
							data-options="valueField:'KCB_ID',textField:'KCXXMC',url:'<%=basePath%>listAllKECHENGB.action'" /></td>
					</tr>
					<tr>
						<td class="title"><label>学生:</label></td>
						<td><input class="easyui-combobox" id="update_XS_ID"
							name="XS_ID"
							data-options="valueField:'id',textField:'jiaoxuezhou',url:'<%=basePath%>js/tmp_xuesheng.json'" /></td>
					</tr>
				</table>
				<input type="hidden" id="update_KCXS_ID" name="KCXS_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_SKXSXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateSKXSXX();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>


</body>
</html>

