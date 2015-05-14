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

<title>学生列表</title>

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
	<script type="text/javascript" src="<%=basePath%>js/basicinfo/luzhiwen/luzhiwen.js"></script>
	<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onclick="add_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-add">添加</a>  
		<a onclick="del_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
		<form>
		<table class="ssk">
		<tr>
		<td class="title"><span>姓名:</span></td>
	    <td><input class="easyui-validatebox" id="xm_serchbar"/></td>
	    <td class="title"><span>学号:</span></td>
	    <td><input class="easyui-validatebox" id="xh_serchbar"/></td>
	    </tr>
	    <tr>
	    <td class="title"><span>学院:</span></td>
	    <td><input class="easyui-combobox" id="xy_serchbar" data-options="valueField:'xyid',textField:'xymc',url:'<%=basePath%>listforXUEYUAN.action'"/></td>
	    <td class="title"><span>专业:</span></td>
	    <td><input class="easyui-combobox" id="zy_serchbar" data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYE.action'"/></td>
	    <td class="title"><span>年级:</span></td>
	    <td><input class="easyui-combobox" id="nj_serchbar" data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJI.action'"/></td>
	    <td class="title"><span>班级:</span></td>
	    <td><input class="easyui-combobox" id="bj_serchbar" data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'"/></td>
	    <td class="title"><button class="easyui-linkbutton" plain="true" onclick="doSearch();"></button></td>
	    <td><input type="reset" class="qx" value="" /></td>
	    </tr>
	    </table>
	    </form>
	    <span>录入指纹前请下载此插件<a href="download.action"><font size="5" color="red">下载插件</font></a> </span>
	</div>
	<!-- 查看/修改用户对话框  END-->
	<!-- 新增用户对话框  BEGIN-->
	<div id="add_dlg" class="easyui-dialog" style="width:300px;height:300px;padding:10px 20px" closed="true" buttons="#add_dlg-buttons" modal="true">
		<form id="add_form" method="post" enctype="multipart/form-data">
			<div>
				<label for="xs_xh">学号:</label>
				<input class="easyui-validatebox" type="text" id="xs_xh"/>
				<input class="easyui-validatebox" type="hidden" id="xs_id" name="xsId"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
				<label for="xs_zsxm">姓名:</label>
				<input class="easyui-validatebox" type="text" id="xs_zsxm"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
				<label for="xs_xb">性别:</label>
				<input class="easyui-validatebox" type="text" id="xs_xb"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
				<label for="xs_xyId">所在学院:</label>
				<input class="easyui-validatebox" type="text" id="xs_xyId"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
				<label for="xs_zyId">所在专业:</label>
				<input class="easyui-validatebox" type="text" id="xs_zyId"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
				<label for="xs_njId">所在年级:</label>
				<input class="easyui-validatebox" type="text" id="xs_njId"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
				<label for="xs_bjId">所在班级:</label>
				<input class="easyui-validatebox" type="text" id="xs_bjId"/>
			</div>
			<div style="margin-top:2px;margin-left:1px">
			<label for="xs_zhiwenId1">指纹表ID1:</label>
				<input class="easyui-validatebox" type="text" id="xs_zhiwenId1" name="zhiwenId1" data-options="required:true" style="line-height:26px;border:1px solid #ccc"/>
	    		<a class="easyui-linkbutton" plain="true" onclick="OpenEnrollFpDlg('xs_zhiwenId1');"><b>录入指纹1</b></a>
			</div>
			<div style="margin-top:2px;margin-left:1px">
			<label for="xs_zhiwenId2">指纹表ID2:</label>
				<input class="easyui-validatebox" type="text" id="xs_zhiwenId2" name="zhiwenId2" data-options="required:true" style="line-height:26px;border:1px solid #ccc"/>
	    		<a class="easyui-linkbutton" plain="true" onclick="OpenEnrollFpDlg('xs_zhiwenId2');"><b>录入指纹2</b></a>
			</div>
		</form>
	</div>
	<div id="add_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addFp();" iconCls="icon-ok">新增信息</a>
	</div>
	<div id="addDAT_dlg" class="easyui-dialog" style="width:400px;height:350px;padding:10px 20px" closed="true" modal="true">
		<div>
			<table style="font-family: 宋体; font-size: 9pt" method="post" enctype="multipart/form-data">
				<tr>
					<td><object classid="clsid:059059BE-8F4C-49AC-B2A9-5649F02A4FC6" id="FPEngineEx1" data="DATA:application/x-oleobject;BASE64,汶六啂偹䕲祭噱䩚䌸偰杸䩁䅁奄睅䅁䈲䅍䅁㴽" style="height: 200px; width: 150px"></object></td>
					<td><input type="text" id="es" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><input type="button" value="登记参考模板"	onclick="EnrollRefTemplate();"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input align="right" type="button" value="确定"	onclick="OKTemplate();"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
