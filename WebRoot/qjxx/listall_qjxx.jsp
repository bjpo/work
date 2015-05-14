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
	<script type="text/javascript" src="<%=basePath%>js/qjxx/qjxx.js"></script>

	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">请假管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addQJXX_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>学生学号:</span></td>
						<td><input id="XSXH_serchbar" name="XSXH" /></td>
						<td class="title"><span>学生姓名:</span></td>
						<td><input id="XSXM_serchbar" name="XSXM" /></td>
						<td class="title"><span>老师姓名:</span></td>
						<td><input id="JGXM_serchbar" name="JGXM" /></td>
					</tr>
					<tr>
						<td class="title"><span>老师工号:</span></td>
						<td><input id="JGGH_serchbar" name="JGGH" /></td>
						<td class="title"><span>登记日期:</span></td>
						<td><input class="easyui-datebox" id="DJRQ_serchbar"
							name="DJRQ" data-options="formatter:myformatter,parser:myparser" /></td>
						<td class="title"><span>修改日期:</span></td>
						<td><input class="easyui-datebox" id="XGRQ_serchbar"
							name="XGRQ" data-options="formatter:myformatter,parser:myparser" /></td>
					</tr>
					<tr>
						<td class="title"><span>请假结束时间:</span></td>
						<td><input class="easyui-datebox" id="QJJSJS_serchbar"
							name="QJJSJS"
							data-options="formatter:myformatter,parser:myparser" /></td>
						<td class="title"><span>请假开始时间:</span></td>
						<td><input style="border:none;" class="easyui-datebox"
							id="QJKSSJ_serchbar" name="QJKSSJ"
							data-options="formatter:myformatter,parser:myparser" /></td>
					</tr>
					<tr>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>







		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addQJXX_dlg" class="easyui-dialog"
			style="width:430px;height:300px;padding:10px 20px" closed="true"
			buttons="#addQJXX_dlg-buttons" modal="true">
			<form id="addQJXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>学号:</label></td>
						<td><input class="easyui-validatebox" type="text" id="XSXH"
							name="XSXH" data-options="required:true" /></td>
					</tr>
					<tr>
						<td class="title"><label>学生姓名:</label></td>
						<td><input class="easyui-validatebox" type="text" id="XSXM"
							name="XSXM" data-options="required:true" /></td>
					</tr>
					<tr>
						<td class="title"><label>请假开始时间:</label></td>
						<td><input class="easyui-datetimebox" id="QJKSSJ"
							name="QJKSSJ" data-options="required:true,showSeconds:false"
							style="line-height:26px;border:1px solid #ccc"></td>
					</tr>
					<tr>
						<td class="title"><label>请假结束时间:</label></td>
						<td><input class="easyui-datetimebox" id="QJJSJS"
							name="QJJSJS" data-options="required:true,showSeconds:false"
							style="line-height:26px;border:1px solid #ccc"></td>
					</tr>
					<tr>
						<td class="title"><label>填写学生请假信息<br />的老师工号:
						</label></td>
						<td><input class="easyui-validatebox" type="text" id="JGGH"
							name="JGGH" data-options="required:true" /></td>
					</tr>
					<tr>
						<td class="title"><label>填写学生请假信息<br />的老师姓名:
						</label></td>
						<td><input class="easyui-validatebox" type="text" id="JGXM"
							name="JGXM" data-options="required:true" /></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input class="easyui-validatebox" type="text" id="MS"
							name="MS" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input class="easyui-validatebox" type="text" id="BZ"
							name="BZ" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addQJXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addQJXX();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateQJXX_dlg" class="easyui-dialog"
			style="width:430px;height:300px;padding:10px 20px" closed="true"
			buttons="#update_QJXX_dlg-buttons" modal="true">
			<form id="updateQJXX_form" method="post">
				<table>
					<tr>
						<td class="title"><label>学生姓名:</label></td>
						<td><input type="text" id="update_XSXM" name="XSXM" disabled /></td>
					</tr>
					<tr>
						<td class="title"><label>教工工号:</label></td>
						<td><input type="text" id="update_JGGH" name="JGGH" disabled /></td>
					</tr>
					<tr>
						<td class="title"><label>教工姓名:</label></td>
						<td><input type="text" id="update_JGXM" name="JGXM" disabled /></td>
					</tr>
					<tr>
						<td class="title"><label>学生学号:</label></td>
						<td><input type="text" id="update_XSXH" name="XSXH" disabled /></td>
					</tr>
					<tr>
						<td class="title"><label>请假开始时间:</label></td>
						<td><input class="easyui-datetimebox" id="update_QJKSSJ"
							name="QJKSSJ" data-options="required:true,showSeconds:false" /></td>
					</tr>
					<tr>
						<td class="title"><label>请假结束时间:</label></td>
						<td><input class="easyui-datetimebox" id="update_QJJSJS"
							name="QJJSJS" data-options="required:true,showSeconds:false"
							style="line-height:26px;border:1px solid #ccc"></td>
					</tr>
					<tr>
						<td class="title"><label>描述:</label></td>
						<td><input type="text" id="update_MS" name="MS" /></td>
					</tr>
					<tr>
						<td class="title"><label>备注:</label></td>
						<td><input type="text" id="update_BZ" name="BZ" /></td>
					</tr>
				</table>
				<input type="hidden" id="update_QJXX_ID" name="QJXX_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_QJXX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateQJXX();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>

</body>
</html>

