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

<title>课程列表</title>

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
<body>
	<script type="text/javascript" src="<%=basePath%>js/yonghu/yonghu.js"></script>
	<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:3px;padding-top:0px;  margin-left: 5px;">  
		<a onclick="addYH_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-add">新增用户信息</a>  
		<a onclick="showEnterDialog();" plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除用户信息</a>
		<br/>
	    <span>用户名称:</span>  
	    <input id="yhmc_serchbar" style="line-height:26px;border:1px solid #ccc" name="yh_yhmc" /> 	     
	    <a class="easyui-linkbutton" plain="true" onclick="doSearch();"><b>搜索</b></a>  
	</div>  
	<!-- 新增用户对话框  BEGIN-->
	<div id="addyh_dlg" class="easyui-dialog" style="width:400px;height:220px;padding:10px 20px" closed="true" buttons="#addyh_dlg-buttons" modal="true">
		<form id="addYH_form" method="post">
			<div>
				<label for="addYH_YHZ_combobox">用户组:</label> 
				<input class="easyui-combobox" id="addYH_YHZ_combobox" name="yhz_id" />
			</div>
			<div>
				<label for="yh_yhmc">用户名称:</label> 
				<input class="easyui-validatebox" type="text" id="yh_yhmc" name="yh_yhmc" data-options="required:true" />
			</div>
			<div>
				<label for="yh_yhmm">用户密码:</label>
				<input class="easyui-validatebox" type="password" id="yh_yhmm" name="yh_yhmm" />
			</div>
		</form>
	</div>
	<div id="addyh_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addYONGHU();" iconCls="icon-ok">新增用户</a>
	</div>
	<!-- 新增用户对话框  END-->
	
		<!-- 查看/修改用户对话框  BEGIN-->
	<div id="updateyh_dlg" class="easyui-dialog" style="width:400px;height:220px;padding:10px 20px" closed="true" buttons="#update_yh_dlg-buttons" modal="true">
		<form id="updateYH_form" method="post">
			<div>
				<label for="updateYH_YHZ_combobox">用户组:</label> 
				<input class="easyui-combobox" id="updateYH_YHZ_combobox" name="yhz_id" />
			</div>
			<div>
				<label for="update_yh_yhmc">用户名称:</label>
				<input type="text" id="update_yh_yhmc" name="yh_yhmc"/>
				<input type="hidden" id="update_yh_id" name="yh_id" />
				<input type="hidden" id="update_optionflag" name="optionflag"/>
			</div>
			<div>
				<label for="update_yh_yhmm">用户密码:</label>
				<input type="text" id="update_yh_yhmm" name="yh_yhmm" />
			</div>
		</form>
	</div>
	<div id="update_yh_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateYONGHU();" iconCls="icon-ok">修改用户信息</a>
	</div>
	<!-- 查看/修改用户对话框  END-->
</body>
</html>
