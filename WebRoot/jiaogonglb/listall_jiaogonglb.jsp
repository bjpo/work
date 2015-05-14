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
		<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">教工类别管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
				<script type="text/javascript" src="<%=basePath%>js/jiaogonglb/jiaogonglb.js"></script>
	<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onClick="addJIAOGONGLB_dialog();" plain="true" class="easyui-linkbutton" iconCls="icon-add">新增信息</a>
		<a onClick="showEnterDialog();" plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>
<!--		<button class="sj_btn">展开/隐藏搜索</button>-->
		<table class="ssk">
		<tr>
		    <td class="title"><span>备注:</span></td>
		    <td><input id="BZ_serchbar"  name="BZ" /></td>
		    <td class="title"><span>描述:</span></td>
		    <td><input id="MS_serchbar"  name="MS" /></td>
		    <td class="title"><span>教工类别名称:</span></td>
		    <td><input id="JIAOGONGLBMC_serchbar" name="JIAOGONGLBMC" /></td>
		    <td class="title"><button class="easyui-linkbutton" plain="true" onClick="doSearch();"></button></td> 	    
	    </tr>
	    </table>
	    
	</div>  
	<!-- 新增对话框  BEGIN-->
	<div id="addJIAOGONGLB_dlg" class="easyui-dialog"
		style="width:400px;height:220px;padding:10px 20px" closed="true"
		buttons="#addJIAOGONGLB_dlg-buttons" modal="true">
		<form id="addJIAOGONGLB_form" method="post">
			<table>
				<tr>
					<td class="title"><label >教工类别名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="JIAOGONGLBMC" name="JIAOGONGLBMC" data-options="required:true,missingMessage:'请输入教工类别名称'" /></td>
				</tr>
				<tr>
					<td class="title"><label >备注:</label></td>
					<td><input class="easyui-validatebox" type="text" id="BZ" name="BZ" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="title"><label >描述:</label></td>
					<td><input class="easyui-validatebox" type="text" id="MS" name="MS" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addJIAOGONGLB_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onClick="addJIAOGONGLB();" iconCls="icon-ok">保存</a>
	</div>
	<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
	<div id="updateJIAOGONGLB_dlg" class="easyui-dialog"
		style="width:400px;height:220px;padding:10px 20px" closed="true"
		buttons="#update_JIAOGONGLB_dlg-buttons" modal="true">
		<form id="updateJIAOGONGLB_form" method="post">
		<table>
			<tr>
				<td class="title"><label >教工类别名称:</label></td> 
				<td ><input type="text" id="update_JIAOGONGLBMC" name="JIAOGONGLBMC"/></td> 
			</tr>
			<tr>
				<td class="title"><label >备注:</label></td> 
				<td><input type="text" id="update_BZ" name="BZ"/> </td>
			</tr>
			<tr>
				<td class="title"><label >描述:</label></td> 
				<td><input type="text" id="update_MS" name="MS"/></td> 
			</tr>
			</table>
				<input type="hidden" id="update_JIAOGONGLB_ID" name="JIAOGONGLB_ID" />
				<input type="hidden" id="update_optionflag" name="optionflag"/>
			
		</form>
	</div>
	<div id="update_JIAOGONGLB_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateJIAOGONGLB();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改对话框  END-->
		</div>

<!--	<script type="text/javascript">
   			$("button").click( function(){
   		$(".ssk").fadeToggle(500);
   		 
   	});
   </script>-->
</body>
</html>

