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

.suggest-container {
	border: 1px solid #C1C1C1;
	visibility: hidden;
}

.suggest-item {
	padding: 3px 2px;
}

.suggest-active {
	background: #33CCFF;
	color: white;
	padding: 3px 2px;
}
</style>
<jsp:include page="../common/include.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<script type="text/javascript"
		src="<%=basePath%>js/youjianlb/youjianlb.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">邮件管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addYOUJIANLB_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>姓名:</span></td>
						<td><input id="XINGMING_serchbar" name="XINGMING" /></td>
						<td class="title"><span>邮箱:</span></td>
						<td><input id="YXMC_serchbar" name="YXMC" /></td>
						<td class="title"><span>描述:</span></td>
						<td><input id="MS_serchbar" name="MS" /></td>
					</tr>
					<tr>
						<td class="title"><span>备注:</span></td>
						<td><input id="BZ_serchbar" name="BZ" /></td>
						<td class="title"></td>
						<td>
							<button class="easyui-linkbutton" plain="true"
								onclick="doSearch();"></button> 
                                                                                                                        <button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button>
						</td>

					</tr>
				</table>

		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addYOUJIANLB_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addYOUJIANLB_dlg-buttons" modal="true">
			<form id="addYOUJIANLB_form" method="post">
				<div>
					<label>姓名:</label> <input class="easyui-validatebox" type="text"
						id="XINGMING" name="XINGMING"
						data-options="required:true,missingMessage:'请输入姓名'" />
				</div>
				<div>
					<label>邮箱:</label> <input class="easyui-validatebox" type="text"
						id="YXMC" name="YXMC"
						data-options="required:true,validType:'email',missingMessage:'请输入邮箱地址'" />
				</div>
				<div>
					<label>描述:</label> <input type="text" id="MS" name="MS" />
				</div>
				<div>
					<label>备注:</label> <input type="text" id="BZ" name="BZ" />
				</div>
			</form>
		</div>
		<div id="addYOUJIANLB_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addYOUJIANLB();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateYOUJIANLB_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_YOUJIANLB_dlg-buttons" modal="true">
			<form id="updateYOUJIANLB_form" method="post">
				<div>
					<label>姓名:</label> <input class="easyui-validatebox" type="text"
						id="update_XINGMING" name="XINGMING" data-options="required:true" />
				</div>
				<div>
					<label>邮箱:</label> <input class="easyui-validatebox" type="text"
						id="update_YXMC" name="YXMC"
						data-options="required:true,validType:'email'" />
				</div>
				<div>
					<label>描述:</label> <input class="easyui-validatebox" type="text"
						id="update_MS" name="MS" />
				</div>
				<div>
					<label>备注:</label> <input class="easyui-validatebox" type="text"
						id="update_BZ" name="BZ" />
				</div>
				<input type="hidden" id="update_YOUJIANLB_ID" name="YOUJIANLB_ID" />
				<input type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_YOUJIANLB_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateYOUJIANLB();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>

