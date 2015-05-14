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

<title>学院管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 设置样式 -->
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
<!-- 引入公共的JqueryEasyUi外部文件共享页面 -->
<jsp:include page="../common/include.jsp"></jsp:include>
<!-- 引入xueyuan.js文件 -->
<script type="text/javascript" src="<%=basePath%>js/xueyuan/xueyuan.js"
	charset="UTF-8">
	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">学院管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<!--显示数据表格begin -->
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<!-- 显示数据表格end -->

		<!-- 新增/删除按钮 开始-->
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<!-- 新增学院信息按钮 -->
			<a onclick="addXUANYUAN_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增学院信息</a>
			<!-- 删除学院信息按钮 -->
			<a onclick="delXUENYUAN_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-remove">删除学院信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>学院名称:</span></td>
						<td><input id="xymc_serchbar" name="xymc" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="XySearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增/删除按钮 结束-->

		<!--新增学院信息开始 -->
		<div id="addxy_dlg" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px;" closed="true"
			buttons="#addxy_dlg-buttons" modal="true">
			<form id="addxy_form" method="post">
				<input type="hidden" id="add_xyid" name="xyid" />
				<div>
					<label for="xymc">学院名称:</label> <input class="easyui-validatebox"
						type="text" id="xymc" name="xymc"
						data-options="required:true,missingMessage:'请填写学院名称'" />&nbsp;<img
						src="img/xinghao.png" style=" margin:-10px 0 0 0;" />
				</div>
				<div>
					<label for="xydm">学院代码:</label> <input class="easyui-validatebox"
						type="text" id="xydm" name="xydm"
						data-options="required:true,missingMessage:'请填写学院代码'" />
				</div>
				<div>
					<label for="xydz">学院地址:</label> <input class="easyui-validatebox"
						type="text" id="xydz" name="xydz" />
				</div>
				<div>
					<label for="xxxx">详细信息:</label> <input class="easyui-validatebox"
						type="text" id="xxxx" name="xxxx" />
				</div>
				<div>
					<label for="yzbm">邮政编码:</label> <input class="easyui-validatebox"
						type="text" id="yzbm" name="yzbm" />
				</div>
				<div>
					<label for="addXY_combobox">上级院系:</label> <input
						class="easyui-combobox" id="addXY_combobox" name="xyid" />
				</div>
				<div>
					<label for="bz">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</label> <input
						class="easyui-validatebox" type="text" id="bz" name="bz" />
				</div>

			</form>
		</div>
		<!--新增学院信息结束 -->
		<!-- 保存按钮开始 -->
		<div id="addxy_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addXUANYUAN();" iconCls="icon-ok">新增学院信息</a>
		</div>
		<!-- 保存按钮结束 -->


		<!-- 查看/修改学院信息对话框开始-->
		<div id="update_xy_dlg" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px" closed="true"
			buttons="#update_xy_dlg-buttons" modal="true">

			<form id="updateXY_form" method="post">
				<input type="hidden" id="update_xyid" name="update_xyid" />
				<div>

					<label for="update_xymc">学院名称:</label> <input
						class="easyui-validatebox" type="text" id="update_xymc"
						name="xymc" data-options="required:true" />

				</div>
				<div>
					<label for="update_xydm">学院代码:</label> <input
						class="easyui-validatebox" type="text" id="update_xydm"
						name="xydm" data-options="required:true" />
				</div>
				<div>
					<label for="update_xydz">学院地址:</label> <input
						class="easyui-validatebox" type="text" id="update_xydz"
						name="xydz" />
				</div>
				<div>
					<label for="update_xxxx">详细信息:</label> <input
						class="easyui-validatebox" type="text" id="update_xxxx"
						name="xxxx" />
				</div>
				<div>
					<label for="update_yzbm">邮政编码:</label> <input
						class="easyui-validatebox" type="text" id="update_yzbm"
						name="yzbm" />
				</div>
				<div>
					<label for="update_fxymc">上级院系:</label> <input
						class="easyui-combobox" id="update_fxymc" name="xyid" />
				</div>
				<div>
					<label for="update_bz">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:</label>
					<input class="easyui-validatebox" type="text" id="update_bz"
						name="bz" />
				</div>

			</form>
		</div>
		<div id="update_xy_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateXUANYUAN();" iconCls="icon-ok">修改学院信息</a>
		</div>
		<!-- 查看/修改学院对话框  END-->

	</div>
</body>
</html>
