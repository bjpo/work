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
<script type="text/javascript" src="<%=basePath%>js/ckxx/ckxx.js"
	charset="UTF-8"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">串课信息管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addCKXX_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>教师姓名:</span></td>
						<td><input id="LAOSHI_XM_serchbar" name="LAOSHI_XM" /></td>
						<td class="title"><span>教师工号:</span></td>
						<td><input id="LAOSHI_GH_serchbar" name="LAOSHI_GH" /></td>
						<td class="title"><span>课程名称:</span></td>
						<td><input id="KCB_KCMC_serchbar" name="KCB_KCMC" /></td>
					</tr>
					<tr>
						<td class="title"><span>课时名称:</span></td>
						<td><input id="KSMC_CD_serchbar" name="KSMC_CD" /></td>
						<td class="title"><span>教室名称:</span></td>
						<td><input id="JSMC_serchbar" name="JSMC" /></td>
						<td class="title"><button class="easyui-linkbutton"
								onclick="doSearch();" iconCls="icon-search"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addCKXX_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#addCKXX_dlg-buttons" modal="true" resizable="true">
			<form id="addCKXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>教师工号/教师姓名:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="LAOSHI_GH" name="LAOSHI_GH" data-options="required:true"
							missingMessage="请选择教师工号/教师姓名" /></td>
					</tr>
					<tr>
						<td class="title"><label>课程名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="KCB_KCMC" name="KCB_KCMC"
							data-options="required:true,missingMessage:'请选择课程名称 '" /></td>
					</tr>
					<tr>
						<td class="title"><label>课时名称:</label></td>
						<td><input class="easyui-validatebox" type="text" id="KSMC"
							name="KSMC"
							data-options="required:true,missingMessage:'请选择课时名称 '" /></td>
					</tr>
					<tr>
						<td class="title"><label>星期:</label></td>
						<td><input class="easyui-validatebox easyui-combobox"
							name="XINGQI" id="XINGQI"
							data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json',required:true,missingMessage:'请选择星期'" /></td>
					</tr>
					<tr>
						<td class="title"><label>周:</label></td>
						<td><input class="easyui-validatebox easyui-combobox"
							name="ZHOU" id="ZHOU_serchbar"
							data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json',required:true,missingMessage:'请选择周'" /></td>
					</tr>
					<tr>
						<td class="title"><label>教室名称:</label></td>
						<td><input class="easyui-validatebox" type="text" id="JSMC"
							name="JSMC" data-options="required:true,missingMessage:'请选择教室名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的课程名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="KCB_KCMC_CD" name="KCB_KCMC_CD"
							data-options="required:true,missingMessage:'请选择串到的课程名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的课时名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="KSMC_CD" name="KSMC_CD"
							data-options="required:true,missingMessage:'请选择串到的课时名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的星期:</label></td>
						<td><input class="easyui-validatebox easyui-combobox"
							name="XINGQI_CD" id="XINGQI_CD"
							data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json',required:true,missingMessage:'请选择串到的星期'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的周:</label></td>
						<td><input class="easyui-validatebox easyui-combobox"
							name="ZHOU_CD" id="ZHOU_CD"
							data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json',required:true,missingMessage:'请选择串到的周'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到教师工号/教师姓名:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="LAOSHI_GH_CD" name="LAOSHI_GH_CD"
							data-options="required:true,missingMessage:'请选择串到教师工号/教师姓名'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的教室名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="JSMC_CD" name="JSMC_CD"
							data-options="required:true,missingMessage:'请选择串到的教室名称'" /></td>
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
		<div id="addCKXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addCKXX();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateCKXX_dlg" class="easyui-dialog"
			style="width:400px;height:250px;padding:10px 20px" closed="true"
			buttons="#update_CKXX_dlg-buttons" modal="true">
			<form id="updateCKXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>教师工号/教师姓名:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_LAOSHI_GH" name="LAOSHI_GH" /></td>
					</tr>
					<tr>
						<td class="title"><label>课程名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_KCB_KCMC" name="KCB_KCMC" /></td>
					</tr>
					<tr>
						<td class="title"><label>课时名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_KSMC" name="KSMC" /></td>
					</tr>
					<tr>
						<td class="title"><label>星期:</label></td>
						<td><input class="easyui-combobox" name="XINGQI"
							id="update_XINGQI"
							data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" /></td>
					</tr>
					<tr>
						<td class="title"><label>周:</label></td>
						<td><input class="easyui-combobox" name="ZHOU"
							id="update_ZHOU"
							data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" /></td>
					</tr>
					<tr>
						<td class="title"><label>教室名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_JSMC" name="JSMC" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的课程名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_KCB_KCMC_CD" name="KCB_KCMC_CD" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的课时名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_KSMC_CD" name="KSMC_CD" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的星期:</label></td>
						<td><input class="easyui-combobox" name="XINGQI_CD"
							id="update_XINGQI_CD"
							data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" /></td>
					</tr>
					<tr>
						<td class="title"><label>串到的周:</label></td>
						<td><input class="easyui-combobox" name="ZHOU_CD"
							id="update_ZHOU_CD"
							data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" /></td>
					</tr>


					<tr>
						<td class="title"><label>串到的老师工号/姓名::</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_LAOSHI_GH_CD" name="LAOSHI_GH_CD" /></td>
					</tr>

					<tr>
						<td class="title"><label>串到的教室名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_JSMC_CD" name="JSMC_CD" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_MS" name="MS" /></td>
					</tr>

					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_BZ" name="BZ" /></td>
					</tr>
				</table>
				<!-- 隐藏ID -->
				<input type="hidden" id="update_CKXX_ID" name="updateCkxxId" /> <input
					type="hidden" id="update_KCB_ID" name="KCB_ID" /> <input
					type="hidden" id="update_KCB_ID_CD" name="KCB_ID_CD" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_CKXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateCKXX();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>

</body>
</html>

