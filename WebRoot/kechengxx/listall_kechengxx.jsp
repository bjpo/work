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
	<script type="text/javascript"
		src="<%=basePath%>js/kechengxx/kechengxx.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">课程信息管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addKECHENGXX_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>考核方式:</span></td>
						<td><input id="KHFS_serchbar" name="KHFS" /></td>
						<td class="title"><span>课程名称:</span></td>
						<td><input id="KECHENGMC_serchbar" name="KECHENGMC" /></td>
						<td class="title"><span>考试学期:</span></td>
						<td><input id="KSXQ_serchbar" name="KSXQ" /></td>
					</tr>
					<tr>
						<td class="title"><span>专业:</span></td>
						<td><input id="ZYMC_serchbar" name="ZYMC" /></td>
						<td class="title"><span>描述:</span></td>
						<td><input id="MS_serchbar" name="MS" /></td>
						<td class="title"><span>课程类别:</span></td>
						<td><input id="KECHENGXXLBMC_serchbar" name="KECHENGXXLBMC" /></td>
					</tr>
					<tr>
						<td class="title"><span>备注:</span></td>
						<td><input id="BZ_serchbar" name="BZ" /></td>
						<td class="title"><span>课程代码:</span></td>
						<td><input id="KECHENGDM_serchbar" name="KECHENGDM" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addKECHENGXX_dlg" class="easyui-dialog"
			style="width:800px;height:300px;padding:10px 20px" closed="true"
			buttons="#addKECHENGXX_dlg-buttons" modal="true">
			<form id="addKECHENGXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>课程名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="KECHENGMC" name="KECHENGMC"
							data-options="required:true,missingMessage:'请输入课程信息名称'" /></td>
						<td class="title" style="width:180px;"><label>课程代码:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="KECHENGDM" name="KECHENGDM"
							data-options="required:true,missingMessage:'请输入课程代码'" /></td>
					</tr>
					<tr>
						<td class="title"><label>专业:</label></td>
						<td><input class="easyui-combobox" id="ZYMC" name="ZYMC"
							data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYE.action',missingMessage:'请选择专业'" /></td>
						<td class="title"><label>课程信息类别:</label></td>
						<td><input class="easyui-combobox" id="KECHENGXXLBMC"
							name="KECHENGXXLBMC"
							data-options="valueField:'KECHENGXXLB_ID',textField:'KECHENGXXLBMC',url:'<%=basePath%>listAllKECHENGXXLB.action',missingMessage:'请选择课程信息类别'" /></td>
					</tr>
					<tr>
						<td class="title"><label>考核方式:</label></td>
						<td><input type="text" id="KHFS" name="KHFS" /></td>
						<td class="title"><label>考试学期:</label></td>
						<td><input type="text" id="KSXQ" name="KSXQ" /></td>
					</tr>
					<tr>
						<td class="title"><label>学时分配：实验实践:</label></td>
						<td><input type="text" id="XSFP_SYSJ" name="XSFP_SYSJ" /></td>
						<td class="title"><label>学时分配：理论教学:</label></td>
						<td><input type="text" id="XSFP_LLJX" name="XSFP_LLJX" /></td>
					</tr>
					<tr>
						<td class="title"><label>开设学分:</label></td>
						<td><input type="text" id="KSXF" name="KSXF" /></td>
						<td class="title"><label>应选学分:</label></td>
						<td><input type="text" id="YXXF" name="YXXF" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input type="text" id="MS" name="MS" /></td>
						<td class="title"><label>备注:</label></td>
						<td><input type="text" id="BZ" name="BZ" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addKECHENGXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addKECHENGXX();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateKECHENGXX_dlg" class="easyui-dialog"
			style="width:800px;height:270px;padding:10px 20px" closed="true"
			buttons="#update_KECHENGXX_dlg-buttons" modal="true">
			<form id="updateKECHENGXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>课程名称:</label></td>
						<td><input type="text" id="update_KECHENGMC" name="KECHENGMC" /></td>
						<td class="title" style="width:180px;"><label>课程代码:</label></td>
						<td><input type="text" id="update_KECHENGDM" name="KECHENGDM" />
						</td>
					</tr>
					<tr>
						<td class="title"><label>课程类别:</label></td>
						<td><input class="easyui-combobox" id="update_KECHENGXXLBMC"
							name="KECHENGXXLBMC"
							data-options="valueField:'KECHENGXXLB_ID',textField:'KECHENGXXLBMC',url:'<%=basePath%>listAllKECHENGXXLB.action'" /></td>
						<td class="title"><label>专业名称:</label></td>
						<td><input class="easyui-combobox" id="update_ZYMC"
							name="ZYMC"
							data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYE.action'" /></td>
					</tr>
					<tr>
						<td class="title"><label>考核方式:</label>
						<td><input type="text" id="update_KHFS" name="KHFS" />
						<td class="title"><label>考试学期:</label></td>
						<td><input type="text" id="update_KSXQ" name="KSXQ" /></td>
					</tr>
					<tr>
						<td class="title"><label>学时分配：理论教学:</label></td>
						<td><input type="text" id="update_XSFP_LLJX" name="XSFP_LLJX" /></td>
						<td class="title"><label>学时分配：实验实践:</label></td>
						<td><input type="text" id="update_XSFP_SYSJ" name="XSFP_SYSJ" /></td>
					</tr>
					<tr>
						<td class="title"><label>应选学分:</label></td>
						<td><input type="text" id="update_YXXF" name="YXXF" /></td>
						<td class="title"><label>开设学分:</label></td>
						<td><input type="text" id="update_KSXF" name="KSXF" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input type="text" id="update_MS" name="MS" /></td>
						<td class="title"><label>备注:</label></td>
						<td><input type="text" id="update_BZ" name="BZ" /></td>
					</tr>
				</table>
				<input type="hidden" id="update_KECHENGXX_ID" name="KECHENGXX_ID" />
				<input type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_KECHENGXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateKECHENGXX();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>