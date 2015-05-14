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
<%-- 引入公共的JqueryEasyUi外部文件共享页面 --%>
<jsp:include page="../common/include.jsp"></jsp:include>
<%--引入外部的 FileUpload.js文件--%>
<script type="text/javascript"
	src="<%=basePath%>js/fileupload/FileUpload.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">基础数据导入</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>

		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<!-- 上传文件按钮 -->
			<a onclick="FileUpload_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">上传文件</a> <a
				onclick="delUpload_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除文件</a><br />
			<%-- 搜索模糊上传文件信息按钮 --%>
			<form>
				<table class="ssk">
					<tr>
						<td class="title"><span>上传文件:</span></td>
						<td><input id="fileUpload_serchbar" name="fileName" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="fileUploadSearch();"></button></td>
						<td><input type="reset" class="qx" value="" /></td>
					</tr>
				</table>
			</form>


		</div>
		<%-- 上传文件开始 --%>
		<div id="FileUpload_dlg" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px;" closed="true"
			buttons="#fileUpload_dlg-buttons" modal="true">
			<form id="fileUpload_form" method="post"
				enctype="multipart/form-data">
				选择文件： <input title="file123" type="file" name="upload" size="20"
					id="fileToUpload" onchange="fileSelected();" /><br />
				<div id="fileName" style="padding: 10px"></div>
				<div id="fileSize" style="padding: 10px"></div>
				<div id="fileType" style="padding: 10px"></div>
			</form>
		</div>
		<%-- 上传文件开结束--%>
		<%-- 上传文件按钮开始 --%>
		<div id="fileUpload_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="FileUpload();" iconCls="icon-ok">上传文件</a>
		</div>
		<%-- 上传文件束 --%>
		<%--导入数据按钮开始 --%>
		<div id="fileUploadImport_dlg" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px;" closed="true"
			buttons="#fileUploadImport_dlg-buttons" modal="true">
			<form id="fileUploadImport_form" method="post">
				<input id="fileId" type="hidden" name="fileId" />
				<div>
					<label for="selectMb">请选择模板:</label> <input id="selectMb"
						width="50" />
				</div>
			</form>
		</div>
		<%--导入数据按钮结束--%>
		<%-- 开始导入按钮开始 --%>
		<div id="fileUploadImport_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="importDataToDb();" iconCls="icon-ok">开始导入</a>
		</div>
		<%-- 保存按钮结束 --%>
	</div>

</body>
</html>
