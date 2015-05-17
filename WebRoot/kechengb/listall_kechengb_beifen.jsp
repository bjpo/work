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
		src="<%=basePath%>js/kechengb/kechengb.js"></script>
		
			<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">课程表管理</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onclick="addKECHENGB_dialog();" plain="true"
			class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
			onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
			iconCls="icon-remove">删除信息</a>
		<form>
			<table class="ssk">
				<tr>
					<td class="title"><span>老师工号:</span></td>
					<td><input id="LAOSHIGH_serchbar" name="LAOSHIGH" /></td>
					<td class="title"><span>老师姓名:</span></td>
					<td><input id="LAOSHIMC_serchbar" name="LAOSHIMC" /></td>
					<td class="title"><span>课程信息:</span></td>
					<td><input id="KCXXMC_serchbar" name="KCXXMC" /></td>
				</tr>
				<tr>
					<td class="title"><span>课程表类别:</span></td>
					<td><input id="KCBLB_serchbar" name="KCBLB" /></td>
					<td class="title"><span>教室名称:</span></td>
					<td><input id="JSMC_serchbar" name="JSMC" /></td>
					<td class="title"><span>课时名称:</span></td>
					<td><input id="KSMC_serchbar" name="KSMC" /></td>
				</tr>
				<tr>
					<td class="title"><span>星期:</span></td>
					<td><input id="XINGQI_serchbar" name="XINGQI" /></td>
					<td class="title"><span>星期序号:</span></td>
					<td><input id="XINGQIXH_serchbar" name="XINGQIXH" /></td>
					<td class="title"><span>开始周:</span></td>
					<td><input id="KSZHOU_serchbar" name="KSZHOU" /></td>
				</tr>
				<tr>
					<td class="title"><span>结束周:</span></td>
					<td><input id="JSZHOU_serchbar" name="JSZHOU" /></td>
					<td class="title"><span>课时开始时间:</span></td>
					<td><input id="KS_KSSJ_serchbar" name="KS_KSSJ" /></td>
					<td class="title"><span>课时结束时间:</span></td>
					<td><input id="KS_JSSJ_serchbar" name="KS_JSSJ" /></td>
				</tr>
				<tr>
					<td class="title"><span>描述:</span></td>
					<td><input id="MS_serchbar" name="MS" /></td>
					<td class="title"><span>备注:</span></td>
					<td><input id="BZ_serchbar" name="BZ" /></td>
				<tr>
				<tr>
					<td class="title" colspan=3;><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><input type="reset" class="qx" value="" /></td>
				<tr>
			</table>
		</form>





	</div>
	<!-- 新增对话框  BEGIN-->
	<div id="addKECHENGB_dlg" class="easyui-dialog"
		style="width:400px;height:250px;padding:10px 20px" closed="true"
		buttons="#addKECHENGB_dlg-buttons" modal="true">
		<form id="addKECHENGB_form" method="post">
			<div id="addkcb_step1">
			<table>
				<tr>
					<!--选择排课的学期 -->
					<td class="title"><label>请选择排课的学期:</label></td>
					<td><input
						class="easyui-combobox easyui-validatebox" id="XUEQI" name="XUEQI"
						data-options="valueField:'XQ_ID',textField:'XQMC',url:'listAllXUEQI.action',required:true,missingMessage:'请选择任排课的学期',
							onSelect:function(){
								$.ajax({
					 					 url: 'deterTerm.action?XUEQI='+$('#XUEQI').combobox('getText'),
					 					 success: function(data){
					 					var obj2 = eval('(' + data + ')');
					 					if(!obj2.success){
					 					$.messager.alert('提示',obj2.message);
					 						$('#XUEQI').combobox('setValue','');
					 					}
					 					
					  							return ;
					 						}
										});
									}" style="width:150px;" /></td>
					
				</tr>
				<tr>
					<td class="title"><input type="hidden" name="KCB_ID" ID="KCB_ID" /> <label>请选择任课教师:</label></td>
					<td><input class="easyui-combobox easyui-validatebox" id="LAOSHIGH"
						name="LAOSHIGH"
						data-options="valueField:'JG_ID',textField:'JGMC',url:'<%=basePath%>listAllJIAOGONG.action',required:true,missingMessage:'请选择任课教师'" style="width:150px;"/>
						</td>
				</tr>
				</table>
				<div>
					<a onclick="showaddStep2();" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'">下一步</a>
				</div>
			</div>
			<div id="addkcb_step2">
				<table>
					<tr>
						<td class="title"><label>课程信息名称:</label></td>
						<td><input class="easyui-combobox" id="KCXXMC" name="KCXXMC"
							data-options="valueField:'KECHENGXX_ID',textField:'KECHENGMC',url:'<%=basePath%>listAllKECHENGXX.action'" /></td>
					</tr>
					<tr>
						<td class="title"><label>开始周:</label></td>
						<td><input class="easyui-combobox" id="KSZHOU" name="KSZHOU"
							data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" /></td>
					</tr>
					<tr>
						<td class="title"><label>结束周:</label></td>
						<td><input class="easyui-combobox" id="JSZHOU" name="JSZHOU"
							data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" /></td>
					</tr>
					<tr>
						<td class="title"><label>课程表类别:</label></td>
						 
						<td><input class="easyui-combobox" id="KCBLB" name="KCBLB"
							data-options="valueField:'kcblb',textField:'kcblb',url:'<%=basePath%>js/kechengbiaoleibie.json'" /></td>
							
							<!-- <td><input id="KCBLB" name="KCBLB"/></td>-->
					</tr>
					  
					<tr id="skbj">
						<td class="title"><label>上课班级:</label></td>
						<td><input class="easyui-combobox" id="SKBJ" name="SKBJ"
							multiple="true"
							data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'" /></td>
					</tr>
						<tr>
						<td class="title"><label>课程最大容纳人数:</label></td>
						<td><input type="text" name="maxrs"/></td>
					</tr>
					<tr>
						<td class="title"><a onclick="showaddStep3();"
							class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加排课</a></td>
						<td><a onclick="showaddStep1();" class="easyui-linkbutton"
							data-options="iconCls:'icon-ok'">上一步</a></td>
					</tr>
				</table>
			</div>
			<div id="addkcb_step3">
				<div>
					<label>教室:</label> <input class="easyui-combobox" id="JS_ID"
						name="JS_ID"
						data-options="valueField:'JS_ID',textField:'FJMC',url:'<%=basePath%>listAllJIAOSHI.action'" />
				</div>
				<div>
					<label>星期:</label> <input class="easyui-combobox" id="XINGQI"
						name="XINGQI"
						data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" />
				</div>
				<div>
					<label>课时:</label> <input class="easyui-combobox" id="KESHI"
						name="KS_ID"
						data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'" />
				</div>
				<div>
					<a onclick="addpaike();" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'">保存排课</a>
				</div>
			</div>
		</form>
	</div>
	<div id="addKECHENGB_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addKECHENGB();" iconCls="icon-ok">完成排课</a>
	</div>
	<!-- 新增对话框  END-->
	<!-- 查看/修改对话框  BEGIN-->
	<div id="updateKECHENGB_dlg" class="easyui-dialog"
		style="width:400px;height:250px;padding:10px 20px" closed="true"
		buttons="#update_KECHENGB_dlg-buttons" modal="true">
		<form id="updateKECHENGB_form" method="post">
			<table>
				<tr>
					<td class="title"><label>任课教师:</label></td>
					<td><input class="easyui-combobox" id="update_LAOSHIMC"
						name="LAOSHIMC"
						data-options="valueField:'JG_ID',textField:'JGMC',url:'<%=basePath%>listAllJIAOGONG.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label>课程:</label></td>
					<td><input class="easyui-combobox" id="update_KCXXMC"
						name="KCXXMC"
						data-options="valueField:'KECHENGXX_ID',textField:'KECHENGMC',url:'<%=basePath%>listAllKECHENGXX.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label>课程表类别:</label></td>
					<td><input class="easyui-combobox" id="update_KCBLB"
						name="KCBLB"
						data-options="valueField:'kcblb',textField:'kcblb',url:'<%=basePath%>js/kechengbiaoleibie.json'" /></td>
				</tr>
				<tr>
					<td class="title"><label>教室名称:</label></td>
					<td><input class="easyui-combobox" id="update_JSMC"
						name="JS_ID"
						data-options="valueField:'JS_ID',textField:'FJMC',url:'<%=basePath%>listAllJIAOSHI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label>课时名称:</label></td>
					<td><input class="easyui-combobox" id="update_KSMC"
						name="KSMC"
						data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label>星期:</label>
					<td><input class="easyui-combobox" id="update_XINGQI"
						name="XINGQI"
						data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" /></td>
				</tr>
				<tr>
					<td class="title"><label>开始周:</label></td>
					<td><input class="easyui-combobox" id="update_KSZHOU"
						name="KSZHOU"
						data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" /></td>
				</tr>
				<tr>
					<td class="title"><label>结束周:</label></td>
					<td><input class="easyui-combobox" id="update_JSZHOU"
						name="JSZHOU"
						data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" /></td>
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
			<input type="hidden" id="update_KCB_FXS_ID" name="KCB_FXS_ID" /> <input
				type="hidden" id="update_KCB_ID" name="KCB_ID" /> <input
				type="hidden" id="update_optionflag" name="optionflag" />
		</form>
	</div>
	<div id="update_KECHENGB_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateKECHENGB();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改对话框  END-->
		</div>
		
		
	
</body>
</html>

