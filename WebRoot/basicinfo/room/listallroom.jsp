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

<title>教学楼列表</title>

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
<body class="easyui-layout">
	<script type="text/javascript" src="<%=basePath%>/js/basicinfo/room/room.js"></script>
		<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">房间管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onclick="addFJ_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-add">添加</a>  
		<a onclick="delFJ_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除</a>

			<table class="ssk">
				<tr>
					<td class="title"><span>房间名称:</span></td>
					<td><input id="fjmc_serchbar" name="fjmc" /></td>
					<td class="title"><button class="easyui-linkbutton" plain="true" onclick="doSearch();"></button></td>
					<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
				</tr>
			</table>
	    
	</div>
	<!-- 新增用户对话框  BEGIN-->
	<div id="add_dlg" class="easyui-dialog" style="width:400px;height:275px;padding:10px 20px" closed="true" buttons="#add_dlg-buttons" modal="true">
		<form id="add_form" method="post">
		<table>
			<tr>
				<td class="title"><label for="jxl_mc">房间名称:</label></td> 
				<td><input class="easyui-validatebox" type="text" id="fj_mc" name="fjmc" data-options="required:true,missingMessage:'请输入房间名称'" /></td>
			</tr>
			<tr>
				<td class="title"><label for="jxl_lh">所在位置:</label></td>
				<td><input class="easyui-validatebox" type="text" id="fj_dz" name="fjdz" data-options="required:true,missingMessage:'请输入房间所在位置'" /></td>
			</tr>
			<tr>
				<td class="title"><label for="jxl_wz">房间号:</label></td>
				<td><input class="easyui-validatebox" type="text" id="fj_h" name="fjh" data-options="required:true,missingMessage:'请输入房间号'" /></td>
			</tr>
			<tr>
				<td class="title"><label for="jxl_wz">楼层:</label></td>
				<td><input class="easyui-validatebox" type="text" id="fj_lc" name="louceng" data-options="required:true,missingMessage:'请输入房间所在楼层'" /></td>
			</tr>
			<tr>
				<td class="title"><label for="jxl_wz">是否有多媒体:</label></td>
				<td><input class="easyui-validatebox" type="radio" id="fj_mt1" name="isdmt" value="有">有</input>
				<input class="easyui-validatebox" type="radio" id="fj_mt2" name="isdmt" value="无">无</input></td>
			</tr>
			<tr>
				<td class="title"><label for="jxl_wz">可容纳人数:</label></td>
				<td><input class="easyui-validatebox" type="text" id="fj_rs" name="renshu" data-options="required:true,missingMessage:'请输入房间容纳人数'" /></td>
			</tr>
			<tr>
				<td class="title"><label for="addFJ_combobox">教学楼:</label></td> 
				<td><input class="easyui-combobox" id="addFJ_combobox" name="jxlId"/></td>
			</tr>
			</table>
		</form>
	</div>
	<div id="add_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addFJ();" iconCls="icon-ok">新增信息</a>
	</div>
		<!-- 查看/修改对话框  BEGIN-->
	<div id="updateFJ_dlg" class="easyui-dialog" style="width:400px;height:275px;padding:10px 20px" closed="true" buttons="#update_fj_dlg-buttons" modal="true">
		<form id="updateFJ_form" method="post">
		<table>
			<tr>
				<td class="title"><label for="update_fjmc">房间名称:</label></td> 
				<td><input type="text" id="update_fjmc" name="fjmc"/> 
				<input type="hidden" id="update_fjId" name="fjId" /></td>
			</tr>
			<tr>
				<td class="title"><label for="update_fjdz">房间位置:</label> 
				<td><input type="text" id="update_fjdz" name="fjdz" /></td>
			</tr>
			<tr>
				<td class="title"><label for="update_fjh">房间号:</label></td> 
				<td><input type="text" id="update_fjh" name="fjh" /></td>
			</tr>
			<tr>
				<td class="title"><label for="update_louceng">所在楼层:</label></td> 
				<td><input type="text" id="update_louceng" name="louceng" /></td>
			</tr>
			<tr>
				<td class="title"><label for="update_isdmt">有无多媒体:</label></td> 
				<td><input class="easyui-validatebox" type="radio" id="update_isdmt1" name="isdmt" value="有" />有
				<input class="easyui-validatebox" type="radio" id="update_isdmt2" name="isdmt" value="无" />无</td>
			</tr>
			<tr>
				<td class="title"><label for="update_renshu">可容纳人数:</label></td> 
				<td><input type="text" id="update_renshu" name="renshu" /></td>
			</tr>
			<tr>
				<td class="title"><label for="update_combobox">教学楼:</label></td>
				<td><input class="easyui-combobox" id="update_combobox" name="jxlId"/></td>
			</tr>
			</table>
		</form>
	</div>
	<div id="update_fj_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateFJ();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改用户对话框  END-->
		</div>
	
</body>
</html>
