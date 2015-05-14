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
	<script type="text/javascript" src="<%=basePath%>js/keshi/keshi.js"></script>
	
	<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">课时管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onclick="addKESHI_dialog();" plain="true" class="easyui-linkbutton"
			iconCls="icon-add">新增信息</a> <a onclick="showEnterDialog();"
			plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>

			<table class="ssk">
				<tr>
					<td class="title"><span>课时名称:</span></td>
					<td><input id="KSMC_serchbar" name="KSMC" /></td>
					<td class="title"><span>描述:</span></td>
					<td><input id="MS_serchbar" name="MS" /></td>
					<td class="title"><span>备注:</span></td>
					<td><input id="BZ_serchbar" name="BZ" /></td>
				</tr>
				<tr>
					<td class="title"><span>课时开始时间:</span></td>
					<td><input id="KSSJ_serchbar" name="KSSJ" /></td>
					<td class="title"><span>课时结束时间:</span></td>
					<td><input id="JSSJ_serchbar" name="JSSJ" /></td>
					<td class="title"><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
				</tr>
			</table>

	</div>
	<!-- 新增对话框  BEGIN-->
	<div id="addKESHI_dlg" class="easyui-dialog"
		style="width:400px;height:260px;padding:10px 20px" closed="true"
		buttons="#addKESHI_dlg-buttons" modal="true">
		<form id="addKESHI_form" method="post">
			<table>
				<tr>
					<td class="title"><label>课时名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="KSMC"
						name="KSMC" data-options="required:true,missingMessage:'请输入课时名称'" /></td>
				</tr>
				<tr>
					<td class="title"><label>开始时间:</label></td>
					<td><input class="easyui-validatebox easyui-timespinner"
						type="text" id="KSSJ" name="KSSJ"
						data-options="required:true,missingMessage:'请输入课时开始时间（格式：09:25）',editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label>结束时间:</label></td>
					<td><input class="easyui-validatebox easyui-timespinner"
						type="text" id="JSSJ" name="JSSJ"
						data-options="required:true,missingMessage:'请输入课时结束时间（格式：09:25）',editable:false" /></td>
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
	<div id="addKESHI_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addKESHI();" iconCls="icon-ok">保存</a>
	</div>
	<!-- 新增对话框  END-->
	<!-- 查看/修改对话框  BEGIN-->
	<div id="updateKESHI_dlg" class="easyui-dialog"
		style="width:400px;height:260px;padding:10px 20px" closed="true"
		buttons="#update_KESHI_dlg-buttons" modal="true">
		<form id="updateKESHI_form" method="post">
			<table>
				<tr>
					<td class="title"><label>课时名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="update_KSMC" name="KSMC"
						data-options="required: true,missingMessage:'请输入课时名称'" /></td>
				</tr>
				<tr>
					<td class="title"><label>开始时间:</label></td>
					<td><input type="text" class="easyui-validatebox easyui-timespinner"
						id="update_KSSJ" name="KSSJ"
						data-options="required: true,missingMessage:'请选择课时的开始时间',editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label>结束时间:</label></td>
					<td><input type="text" class="easyui-validatebox easyui-timespinner"
						id="update_JSSJ" name="JSSJ"
						data-options="required: true,missingMessage:'请选择课时的结束时间',editable:false" /></td>
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
			<input type="hidden" id="update_KS_ID" name="KS_ID" /> <input
				type="hidden" id="update_optionflag" name="optionflag" />
		</form>
	</div>
	<div id="update_KESHI_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateKESHI();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改对话框  END-->
		</div>
	
</body>
</html>

