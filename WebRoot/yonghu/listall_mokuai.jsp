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

<title>模块列表</title>

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
	<script type="text/javascript" src="<%=basePath%>js/yonghu/mokuai.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">模块管理</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addMK_dialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-add">新增模块信息</a> <a onclick="showEnterDialog();"
				plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除模块信息</a>

				<table class="ssk">
					<tr>
						<td class="title"><span>模块名称:</span></td>
						<td><input id="mkmc_serchbar" name="js_jsmc" /></td>
						<td class="title"><span>模块描述:</span></td>
						<td><input id="mkms_serchbar" name="js_ms" /></td>
						<td class="title"><span>模块备注:</span></td>
						<td><input id="mkbz_serchbar" name="js_bz" /></td>
					</tr>
					<tr>
						<td class="title"><span>模块编码:</span></td>
						<td><input id="mkbm_serchbar" name="js_bz" /></td>
						<td class="title"><button class="easyui-linkbutton"
								plain="true" onclick="doSearch();"></button></td>
						<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
					</tr>
				</table>

		</div>
		<!-- 新增模块对话框  BEGIN-->
		<div id="addmk_dlg" class="easyui-dialog"
			style="width:400px;height:320px;padding:10px 20px" closed="true"
			buttons="#addmk_dlg-buttons" modal="true">
			<form id="addMK_form" method="post">
				<table>
					<tr>
						<td class="title"><label for="mk_jsmc">模块名称:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_mkmc" name="mk_mkmc"
							data-options="required:true,missingMessage:'请输入模块名称'" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_mkbm">模块编码:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_mkbm" name="mk_mkbm" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_ms">描述信息:</label></td>
						<td><input class="easyui-validatebox" type="text" id="mk_ms"
							name="mk_ms" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_ms">备注信息:</label></td>
						<td><input class="easyui-validatebox" type="text" id="mk_bz"
							name="mk_bz" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_mkurl">URL:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_mkurl" name="mk_mkurl"
							data-options="required:true,missingMessage:'请输入URL'" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_mkym">模块页面:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_mkym" name="mk_mkym" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_openstate">是否打开:</label></td>
						<td><label for="openstate_open">打开</label><input type="radio"
							id="openstate_open" name="mk_openstate" value="open"
							checked="checked" /> <label for="openstate_closed">关闭</label><input
							type="radio" id="openstate_closed" name="mk_openstate" value="" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_isshowinleftmenu">菜单显示:</label></td>
						<td><label for="mk_isshowinleftmenu_yes">是</label><input
							type="radio" id="mk_isshowinleftmenu_yes"
							name="mk_isshowinleftmenu" value="yes" /> <label
							for="mk_isshowinleftmenu_no">否</label><input type="radio"
							id="mk_isshowinleftmenu_no" name="mk_isshowinleftmenu" value="no"
							checked="checked" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_isshowinleftmenu">菜单分类:</label></td>
						<td><div class="easyui-combobox"
								data-options="valueField:'menuCateId',textField:'menuName',url:'getAllMenuCate.action'"
								name="menuCateId" style="width:150px"></div></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_iconcls">图标样式:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_iconcls" name="mk_iconcls" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_paixu">排序:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_paixu" name="mk_paixu"
							data-options="required:true,missingMessage:'请输入排序的数字'" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_fk_mk">上级模块:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="mk_fk_mk" name="mk_fk_mk" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addmk_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addMOKUAI();" iconCls="icon-ok">新增模块</a>
		</div>
		<!-- 新增模块对话框  END-->

		<!-- 查看/修改模块对话框  BEGIN-->
		<div id="updatemk_dlg" class="easyui-dialog"
			style="width:400px;height:320px;padding:10px 20px" closed="true"
			buttons="#update_mk_dlg-buttons" modal="true">
			<form id="updateMK_form" method="post">
				<table>
					<tr>
						<td class="title"><label for="update_mk_mkmc">模块名称:</label></td>
						<td><input type="text" id="update_mk_mkmc" name="mk_mkmc" />
							<input type="hidden" id="update_mk_id" name="mk_id" /> <input
							type="hidden" id="update_optionflag" name="optionflag" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_mkbm">模块编码:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_mk_mkbm" name="mk_mkbm" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_ms">模块描述:</label></td>
						<td><input type="text" id="update_mk_ms" name="mk_ms" /></td>
					<tr>
						<td class="title"><label for="update_mk_bz">模块备注:</label></td>
						<td><input type="text" id="update_mk_bz" name="mk_bz" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_mkurl">URL:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_mk_mkurl" name="mk_mkurl" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_mkym">模块页面:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_mk_mkym" name="mk_mkym" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_openstate">是否打开:</label></td>
						<td><label for="update_mk_openstate_open">打开</label><input
							type="radio" id="update_mk_openstate_open" name="mk_openstate"
							checked="checked" value="open" /> <label
							for="update_mk_openstate_closed">关闭</label><input type="radio"
							id="update_mk_openstate_closed" name="mk_openstate" value="" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_isshowinleftmenu">菜单显示:</label></td>
						<td><label for="update_mk_isshowinleftmenu_yes">是</label><input
							type="radio" id="update_mk_isshowinleftmenu_yes"
							name="mk_isshowinleftmenu" value="yes" /> <label
							for="update_mk_isshowinleftmenu_no">否</label><input type="radio"
							id="update_mk_isshowinleftmenu_no" name="mk_isshowinleftmenu"
							checked="checked" value="no" /></td>
					</tr>
					<tr>
						<td class="title"><label for="mk_isshowinleftmenu">菜单分类:</label></td>
						<td><div class="easyui-combobox"
								data-options="valueField:'menuCateId',textField:'menuName',url:'getAllMenuCate.action'"
								name="menuCateId" style="width:150px" id="updateMenuCate"></div></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_iconcls">图标样式:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_mk_iconcls" name="mk_iconcls" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_paixu">排序:</label></td>
						<td><input class="easyui-validatebox" type="text"
							id="update_mk_paixu" name="mk_paixu" /></td>
					</tr>
					<tr>
						<td class="title"><label for="update_mk_fk_mk">上级模块:</label></td>
						<td><input class="easyui-combobox" id="update_mk_fk_mk"
							name="mk_fk_mk" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="update_mk_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateMOKUAI();" iconCls="icon-ok">修改模块信息</a>
		</div>
		<!-- 查看/修改模块对话框  END-->
	</div>


</body>
</html>