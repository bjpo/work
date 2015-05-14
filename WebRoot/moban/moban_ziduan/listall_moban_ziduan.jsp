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
<jsp:include page="../../common/include.jsp"></jsp:include>
</head>
<body>
	<script type="text/javascript" src="<%=basePath%>js/moban_ziduan/moban_ziduan.js"></script>
	<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:3px;padding-top:0px;  margin-left: 5px;">
		<a onclick="addMOBAN_ZIDUAN_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-add">新增信息</a>
		<a onclick="showEnterDialog();" plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>
		<br/>
	    <span>字段1:</span>
	    <input id="LIE_serchbar" style="line-height:26px;border:1px solid #ccc" name="LIE" />
	    <span>字段2:</span>
	    <input id="ZIDUAN_ID_serchbar" style="line-height:26px;border:1px solid #ccc" name="ZIDUAN_ID" />
	    <span>字段3:</span>
	    <input id="MB_ID_serchbar" style="line-height:26px;border:1px solid #ccc" name="MB_ID" />
	    <span>字段4:</span>
	    <input id="ZIDUANMC_serchbar" style="line-height:26px;border:1px solid #ccc" name="ZIDUANMC" />
	    <a class="easyui-linkbutton" plain="true" onclick="doSearch();"><b>搜索</b></a>  
	</div>  
	<!-- 新增对话框  BEGIN-->
	<div id="addMOBAN_ZIDUAN_dlg" class="easyui-dialog"
		style="width:400px;height:220px;padding:10px 20px" closed="true"
		buttons="#addMOBAN_ZIDUAN_dlg-buttons" modal="true">
		<form id="addMOBAN_ZIDUAN_form" method="post">
			<div>
				<label >字段1:</label>
				<input class="easyui-validatebox" type="text" id="LIE" name="LIE" data-options="required:true" />
			</div>
			<div>
				<label >字段2:</label>
				<input class="easyui-validatebox" type="text" id="ZIDUAN_ID" name="ZIDUAN_ID" data-options="required:true" />
			</div>
			<div>
				<label >字段3:</label>
				<input class="easyui-validatebox" type="text" id="MB_ID" name="MB_ID" data-options="required:true" />
			</div>
			<div>
				<label >字段4:</label>
				<input class="easyui-validatebox" type="text" id="ZIDUANMC" name="ZIDUANMC" data-options="required:true" />
			</div>
		</form>
	</div>
	<div id="addMOBAN_ZIDUAN_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addMOBAN_ZIDUAN();" iconCls="icon-ok">保存</a>
	</div>
	<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
	<div id="updateMOBAN_ZIDUAN_dlg" class="easyui-dialog"
		style="width:400px;height:220px;padding:10px 20px" closed="true"
		buttons="#update_MOBAN_ZIDUAN_dlg-buttons" modal="true">
		<form id="updateMOBAN_ZIDUAN_form" method="post">
			<div>
				<label >字段1:</label> 
				<input type="text" id="update_LIE" name="LIE"/> 
			</div>
			<div>
				<label >字段2:</label> 
				<input type="text" id="update_ZIDUAN_ID" name="ZIDUAN_ID"/> 
			</div>
			<div>
				<label >字段3:</label> 
				<input type="text" id="update_MB_ID" name="MB_ID"/> 
			</div>
			<div>
				<label >字段4:</label> 
				<input type="text" id="update_ZIDUANMC" name="ZIDUANMC"/> 
			</div>
				<input type="hidden" id="update_ZIDUAN_ID" name="ZIDUAN_ID" />
				<input type="hidden" id="update_optionflag" name="optionflag"/>	
		</form>
	</div>
	<div id="update_MOBAN_ZIDUAN_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateMOBAN_ZIDUAN();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改对话框  END-->
</body>
</html>

