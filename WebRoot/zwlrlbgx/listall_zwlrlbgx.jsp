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
	<script type="text/javascript"
		src="<%=basePath%>js/zwlrlbgx/zwlrlbgx.js"></script>
	<div data-options="region:'west',title:'菜单',split:true"	style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">指纹录入列表-关系表</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>
		<div id="DIV_toolbar"
			style="padding:3px;padding-top:0px;  margin-left: 5px;">
			<a onclick="addZWLRLBGX_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a> <br /> <span>字段1:</span> <input
				id="ZWLRBIAO_TMP4_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP4" />
			<span>字段2:</span> <input id="ZWLRBIAO_TMP3_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP3" />
			<span>字段3:</span> <input id="ZWLRBIAO_TMP2_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP2" />
			<span>字段4:</span> <input id="ZWLRBIAO_TMP1_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP1" />
			<span>字段5:</span> <input id="ZWLRBIAO_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO" /> <span>字段6:</span>
			<input id="ZWLR_MC_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLR_MC" /> <span>字段7:</span>
			<input id="ZWLRBIAO_ID_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_ID" />
			<span>字段8:</span> <input id="ZWLRBIAO_MC_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_MC" />
			<span>字段9:</span> <input id="ZWLRBIAO_TMP9_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP9" />
			<span>字段10:</span> <input id="ZWLRBIAO_HM_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_HM" />
			<span>字段11:</span> <input id="ZWLRBIAO_TMP8_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP8" />
			<span>字段12:</span> <input id="ZWLRBIAO_TMP7_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP7" />
			<span>字段13:</span> <input id="ZWLRBIAO_TMP6_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP6" />
			<span>字段14:</span> <input id="PAIXU_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="PAIXU" /> <span>字段15:</span>
			<input id="ZWLRBIAO_TMP5_serchbar"
				style="line-height:26px;border:1px solid #ccc" name="ZWLRBIAO_TMP5" />
			<a class="easyui-linkbutton" plain="true" onclick="doSearch();"><b>搜索</b></a>
		</div>
		<!-- 新增对话框  BEGIN-->
		<div id="addZWLRLBGX_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#addZWLRLBGX_dlg-buttons" modal="true">
			<form id="addZWLRLBGX_form" method="post">
				<div>
					<label>字段1:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP4" name="ZWLRBIAO_TMP4"
						data-options="required:true" />
				</div>
				<div>
					<label>字段2:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP3" name="ZWLRBIAO_TMP3"
						data-options="required:true" />
				</div>
				<div>
					<label>字段3:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP2" name="ZWLRBIAO_TMP2"
						data-options="required:true" />
				</div>
				<div>
					<label>字段4:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP1" name="ZWLRBIAO_TMP1"
						data-options="required:true" />
				</div>
				<div>
					<label>字段5:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO" name="ZWLRBIAO" data-options="required:true" />
				</div>
				<div>
					<label>字段6:</label> <input class="easyui-validatebox" type="text"
						id="ZWLR_MC" name="ZWLR_MC" data-options="required:true" />
				</div>
				<div>
					<label>字段7:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_ID" name="ZWLRBIAO_ID" data-options="required:true" />
				</div>
				<div>
					<label>字段8:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_MC" name="ZWLRBIAO_MC" data-options="required:true" />
				</div>
				<div>
					<label>字段9:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP9" name="ZWLRBIAO_TMP9"
						data-options="required:true" />
				</div>
				<div>
					<label>字段10:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_HM" name="ZWLRBIAO_HM" data-options="required:true" />
				</div>
				<div>
					<label>字段11:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP8" name="ZWLRBIAO_TMP8"
						data-options="required:true" />
				</div>
				<div>
					<label>字段12:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP7" name="ZWLRBIAO_TMP7"
						data-options="required:true" />
				</div>
				<div>
					<label>字段13:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP6" name="ZWLRBIAO_TMP6"
						data-options="required:true" />
				</div>
				<div>
					<label>字段14:</label> <input class="easyui-validatebox" type="text"
						id="PAIXU" name="PAIXU" data-options="required:true" />
				</div>
				<div>
					<label>字段15:</label> <input class="easyui-validatebox" type="text"
						id="ZWLRBIAO_TMP5" name="ZWLRBIAO_TMP5"
						data-options="required:true" />
				</div>
			</form>
		</div>
		<div id="addZWLRLBGX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addZWLRLBGX();" iconCls="icon-ok">保存</a>
		</div>
		<!-- 新增对话框  END-->
		<!-- 查看/修改对话框  BEGIN-->
		<div id="updateZWLRLBGX_dlg" class="easyui-dialog"
			style="width:400px;height:220px;padding:10px 20px" closed="true"
			buttons="#update_ZWLRLBGX_dlg-buttons" modal="true">
			<form id="updateZWLRLBGX_form" method="post">
				<div>
					<label>字段1:</label> <input type="text" id="update_ZWLRBIAO_TMP4"
						name="ZWLRBIAO_TMP4" />
				</div>
				<div>
					<label>字段2:</label> <input type="text" id="update_ZWLRBIAO_TMP3"
						name="ZWLRBIAO_TMP3" />
				</div>
				<div>
					<label>字段3:</label> <input type="text" id="update_ZWLRBIAO_TMP2"
						name="ZWLRBIAO_TMP2" />
				</div>
				<div>
					<label>字段4:</label> <input type="text" id="update_ZWLRBIAO_TMP1"
						name="ZWLRBIAO_TMP1" />
				</div>
				<div>
					<label>字段5:</label> <input type="text" id="update_ZWLRBIAO"
						name="ZWLRBIAO" />
				</div>
				<div>
					<label>字段6:</label> <input type="text" id="update_ZWLR_MC"
						name="ZWLR_MC" />
				</div>
				<div>
					<label>字段7:</label> <input type="text" id="update_ZWLRBIAO_ID"
						name="ZWLRBIAO_ID" />
				</div>
				<div>
					<label>字段8:</label> <input type="text" id="update_ZWLRBIAO_MC"
						name="ZWLRBIAO_MC" />
				</div>
				<div>
					<label>字段9:</label> <input type="text" id="update_ZWLRBIAO_TMP9"
						name="ZWLRBIAO_TMP9" />
				</div>
				<div>
					<label>字段10:</label> <input type="text" id="update_ZWLRBIAO_HM"
						name="ZWLRBIAO_HM" />
				</div>
				<div>
					<label>字段11:</label> <input type="text" id="update_ZWLRBIAO_TMP8"
						name="ZWLRBIAO_TMP8" />
				</div>
				<div>
					<label>字段12:</label> <input type="text" id="update_ZWLRBIAO_TMP7"
						name="ZWLRBIAO_TMP7" />
				</div>
				<div>
					<label>字段13:</label> <input type="text" id="update_ZWLRBIAO_TMP6"
						name="ZWLRBIAO_TMP6" />
				</div>
				<div>
					<label>字段14:</label> <input type="text" id="update_PAIXU"
						name="PAIXU" />
				</div>
				<div>
					<label>字段15:</label> <input type="text" id="update_ZWLRBIAO_TMP5"
						name="ZWLRBIAO_TMP5" />
				</div>
				<input type="hidden" id="update_ZWLRGX_ID" name="ZWLRGX_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" />
			</form>
		</div>
		<div id="update_ZWLRLBGX_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateZWLRLBGX();" iconCls="icon-ok">修改信息</a>
		</div>
		<!-- 查看/修改对话框  END-->
	</div>
</body>
</html>

