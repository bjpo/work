<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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
<jsp:include page="../common/zwlrPlug.jsp"></jsp:include>
<script type="text/javascript"
		src="<%=basePath%>js/jiaogong/jiaogong.js"></script>
</head>
<body class="easyui-layout">
		<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">教工信息管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">

			<table class="ssk">
				<tr>
					<td class="title"><a onclick="addJIAOGONG_dialog();"
						plain="true" class="easyui-linkbutton" iconCls="icon-add">新增信息</a></td>
					<td><a onclick="showEnterDialog();" plain="true"
						class="easyui-linkbutton" iconCls="icon-remove">删除信息</a></td>
				</tr>
				<tr>
					<td class="title"><span>教工工号:</span></td>
					<td><input id="JGGH_serchbar" name="JGGH" /></td>
					<td class="title"><span>真实姓名:</span></td>
					<td><input id="ZSXM_serchbar" name="ZSXM" /></td>
					<td class="title"><span>性别:</span></td>
					<td><input id="XB_serchbar" name="XB" /></td>
				</tr>
				<tr>
					<td class="title"><span>教工姓名:</span></td>
					<td><input id="JGMC_serchbar" name="JGMC" /></td>
					<td class="title"><span>家庭住址:</span></td>
					<td><input id="JTZZ_serchbar" name="JTZZ" /></td>
					<td class="title"><span>身高:</span></td>
					<td><input id="SHENGAO_serchbar" name="SHENGAO" /></td>
				</tr>
				<tr>
					<td class="title"><span>身份证号码:</span></td>
					<td><input id="SFZHM_serchbar" name="SFZHM" /></td>
					<td class="title"><span>民族:</span></td>
					<td><input id="MZ_serchbar" name="MZ" /></td>
					<td class="title"><span>教工类别名称:</span></td>
					<td><input id="JGLBMC_serchbar" name="JGLBMC" /></td>
				</tr>
				<tr>
					<td class="title"><span>体重:</span></td>
					<td><input id="TIZHONG_serchbar" name="TIZHONG" /></td>
					<td class="title"><span>描述:</span></td>
					<td><input id="MS_serchbar" name="MS" /></td>
					<td class="title"><span>学历:</span></td>
					<td><input id="XUELI_serchbar" name="XUELI" /></td>
				</tr>
				<tr>
					<td class="title"><span>毕业院校:</span></td>
					<td><input id="BYYX_serchbar" name="BYYX" /></td>
					<td class="title"><span>备注:</span></td>
					<td><input id="BZ_serchbar" name="BZ" /></td>
					<td class="title"><span>出生年月:</span></td>
					<td><input id="CSNY_serchbar" name="CSNY" /></td>
				</tr>

				<tr>
					<td class="title" colspan=3;><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
                                                                                           <td colspan="3"><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
				</tr>
			</table>


	</div>
	<!-- 新增对话框  BEGIN-->
	<div id="addJIAOGONG_dlg" class="easyui-dialog"
		style="width:600px;height:400px;padding:10px 20px" closed="true"
		buttons="#addJIAOGONG_dlg-buttons" modal="true">
		<form id="addJIAOGONG_form" method="post">
			<table>
				<tr>
					<td class="title"><label>教工名称:</label></td>
					<td style="width:180px;"><input class="easyui-validatebox"
						type="text" id="JGMC" name="JGMC"
						data-options="required:true,missingMessage:'请输入教工名称'" /></td>
					<td class="title"><label>真实姓名:</label></td>
					<td><input class="easyui-validatebox" type="text" id="ZSXM"
						name="ZSXM" data-options="required:true,missingMessage:'请输入真实姓名'" /></td>
				</tr>
				<tr>
					<td class="title"><label>教工工号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="JGGH"
						name="JGGH" data-options="required:true,missingMessage:'请输入教工工号'" /></td>
					<td class="title"><label>家庭住址:</label></td>
					<td><input type="text" id="JTZZ" name="JTZZ" /></td>
				</tr>
				<tr>
					<td class="title"><label>性别:</label></td>
					<td><input class="easyui-combobox" id="XB" name="XB"
						data-options="valueField:'xingbie',textField:'xingbie',url:'<%=basePath%>js/xingbie.json'"
						style="width:150px;" /></td>
					<td class="title"><label>教工类别:</label></td>
					<td><input class="easyui-combobox" id="JGLBMC" name="JGLBMC"
						data-options="valueField:'JIAOGONGLB_ID',textField:'JIAOGONGLBMC',url:'<%=basePath%>listAllJIAOGONGLB.action'"
						style="width:150px;;" /></td>
				</tr>
				<tr>
					<td class="title"><label>身高:</label></td>
					<td><input type="text" id="SHENGAO" name="SHENGAO" /></td>
					<td class="title"><label>体重:</label></td>
					<td><input type="text" id="TIZHONG" name="TIZHONG" /></td>
				</tr>
				<tr>
					<td class="title"><label>民族:</label></td>
					<td><input class="easyui-combobox" id="MZ" name="MZ"
						data-options="valueField:'minzu',textField:'minzu',url:'<%=basePath%>js/minzu.json'"
						style="width:150px;;" /></td>
					<td class="title"><label>身份证号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="SFZHM"
						name="SFZHM" data-options="required:true,missingMessage:'请输入身份证号'" /></td>
				</tr>
				<tr>
					<td class="title"><label>出生年月:</label></td>
					<td><input class="easyui-datebox" id="CSNY" name="CSNY"
						data-options="editable:false" style="width:150px;" /></td>
					<td class="title"><label>学历:</label></td>
					<td><input class="easyui-combobox" id="XUELI" name="XUELI"
						data-options="valueField:'xueli',textField:'xueli',url:'<%=basePath%>js/xueli.json'"
						style="width:150px;;" /></td>
				</tr>
				<tr>
					<td class="title"><label>毕业院校:</label></td>
					<td><input type="text" id="BYYX" name="BYYX" /></td>
					<td class="title"><label>备注:</label></td>
					<td><input type="text" id="BZ" name="BZ" /></td>
				</tr>
				<tr>
					<td class="title"><label>关联用户:</label></td>
					<td><input class="easyui-combobox" id="YH_ID" name="YH_ID"
						data-options="valueField:'yhid',textField:'yhm',url:'<%=basePath%>listYONGHUforYONGHU.action',editable:false"
						style="width:150px;;" /></td>
					<td class="title"><label>描述:</label></td>
					<td><input type="text" id="MS" name="MS" /></td>
				</tr>
				<tr>
					<td class="title"><label>排课权限:</label></td>
					<td><input class="easyui-combobox" id="TMP1" name="TMP1"
						data-options="valueField:'keys',textField:'values',url:'<%=basePath%>js/jiaogongqx.json',editable:false"
						style="width:150px;;" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addJIAOGONG_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addJIAOGONG();" iconCls="icon-ok">保存</a>
	</div>
	<!-- 新增对话框  END-->
	<!-- 查看/修改对话框  BEGIN-->
	<div id="updateJIAOGONG_dlg" class="easyui-dialog"
		style="width:600px;height:250px;padding:10px 20px" closed="true"
		buttons="#update_JIAOGONG_dlg-buttons" modal="true">
		<form id="updateJIAOGONG_form" method="post">
			<table>
				<tr>
					<td class="title"><label>教工名称:</label></td>
					<td style="width:180px;"><input type="text" id="update_JGMC"
						name="JGMC" /></td>
					<td class="title"><label>真实姓名:</label></td>
					<td><input type="text" id="update_ZSXM" name="ZSXM" /></td>
				</tr>
				<tr>
					<td class="title"><label>教工工号:</label></td>
					<td><input type="text" id="update_JGGH" name="JGGH" /></td>
					<td class="title"><label>家庭住址:</label></td>
					<td><input type="text" id="update_JTZZ" name="JTZZ" /></td>

				</tr>
				<tr>
					<td class="title"><label>性别:</label></td>
					<td><input class="easyui-combobox" id="update_XB" name="XB"
						data-options="valueField:'xingbie',textField:'xingbie',url:'<%=basePath%>js/xingbie.json'"
						style="width:130px;" /></td>
					<td class="title"><label>教工类别:</label></td>
					<td><input class="easyui-combobox" id="update_JGLBMC"
						name="JGLBMC"
						data-options="valueField:'JIAOGONGLB_ID',textField:'JIAOGONGLBMC',url:'<%=basePath%>listAllJIAOGONGLB.action'"
						style="width:130px;" /></td>
				</tr>
				<tr>
					<td class="title"><label>身高:</label></td>
					<td><input type="text" id="update_SHENGAO" name="SHENGAO" /></td>
					<td class="title"><label>体重:</label></td>
					<td><input type="text" id="update_TIZHONG" name="TIZHONG" /></td>
				</tr>
				<tr>
					<td class="title"><label>民族:</label></td>
					<td><input class="easyui-combobox" id="update_MZ" name="MZ"
						data-options="valueField:'minzu',textField:'minzu',url:'<%=basePath%>js/minzu.json'"
						style="width:130px;" /></td>

					<td class="title"><label>身份证号:</label></td>
					<td><input type="text" id="update_SFZHM" name="SFZHM" /></td>
				</tr>
				<tr>
					<td class="title"><label>出生年月:</label></td>
					<td><input class="easyui-datebox" id="update_CSNY" name="CSNY"
						style="width:130px;" /></td>
					<td class="title"><label>学历:</label></td>
					<td><input class="easyui-combobox" id="update_XUELI"
						name="XUELI"
						data-options="valueField:'xueli',textField:'xueli',url:'<%=basePath%>js/xueli.json'"
						style="width:130px;" /></td>
				</tr>
				<tr>
					<td class="title"><label>毕业院校:</label></td>
					<td><input type="text" id="update_BYYX" name="BYYX" /></td>
					<td class="title"><label>备注:</label></td>
					<td><input type="text" id="update_BZ" name="BZ" /></td>
				</tr>
				<tr>

					<td class="title"><label>关联用户:</label></td>
					<td><input class="easyui-combobox" id="update_YH_ID"
						name="YH_ID"
						data-options="valueField:'yhid',textField:'yhm',url:'<%=basePath%>listYONGHUforYONGHU.action'"
						style="width:130px;" /></td>
					<td class="title"><label>描述:</label></td>
					<td><input type="text" id="update_MS" name="MS" /></td>
				</tr>
				<tr>
					<td class="title"><label>排课权限:</label></td>
					<td><input class="easyui-combobox" id="update_TMP1"
						name="TMP1"
						data-options="valueField:'keys',textField:'values',url:'<%=basePath%>js/jiaogongqx.json'"
						style="width:130px;" /></td>
				</tr>
			</table>



			<input type="hidden" id="update_JG_ID" name="JG_ID" /> <input
				type="hidden" id="update_optionflag" name="optionflag" />
		</form>
	</div>
	<div id="update_JIAOGONG_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateJIAOGONG();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改对话框  END-->

	<!-- 指纹录入  BEGIN-->
	<div id="jg_zwlr_dlg" class="easyui-dialog"
		style="width:530px;height:220px;" closed="true"
		buttons="#addJg_zwlr_dlg-buttons" modal="true">
		<form id="jg_zwlr_form" method="post" enctype="multipart/form-data">
		<div style="float:left;">
		<div style="margin-top:10px;margin-left:100px">
				<label for="jg_zhiwenId1">指纹表ID1:</label>
				<input class="easyui-validatebox" type="text" id="jg_zhiwenId1" name="zhiwenId1" data-options="required:true" style="border:1px solid #99bbe8; height:24px; line-height:24px;"/>
	    		<a class="easyui-linkbutton" plain="true" onclick="OpenEnrollFpDlg('jg_zhiwenId1');"><b>录入指纹1</b></a>
			</div>
			<div style="margin-top:10px;margin-left:100px">
			<label for="jg_zhiwenId2">指纹表ID2:</label>
				<input class="easyui-validatebox" type="text" id="jg_zhiwenId2" name="zhiwenId2" data-options="required:true" style="border:1px solid #99bbe8; height:24px; line-height:24px;"/>
	    		<a class="easyui-linkbutton" plain="true" onclick="OpenEnrollFpDlg('jg_zhiwenId2');"><b>录入指纹2</b></a>
			</div>
			<input type="hidden" id="jg_id" name="JG_ID"/>
			</div>
		</form>
	</div>
	<div id="addJg_zwlr_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addJGFp();" iconCls="icon-ok">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cacelSave();" iconCls="icon-cancel">取消</a>
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
		</div>
	
	
</body>
</html>

