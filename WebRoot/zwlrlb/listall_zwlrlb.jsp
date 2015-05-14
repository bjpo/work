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
<!-- 
h3 {
	margin: 10px 10px;
}

.right {
	width: 200px;
}

.left {
	width: 300px;
}

.xsxh {
	width: 300px;
	height: 25px;
	margin: 2px 0px;
}

.xsxh div,.ycjrs div {
	display: inline;
}

.zwlr {
	width: 300px;
	height: 70px;
	margin-top: 1px;
}

.btn {
	margin: 20px auto;
	display: inline;
}

.ycjrs {
	width: 150px;
	height: 25px;
	margin-right: 30px;
	display: inline;
}

.zwcjzwlb {
	width: 180px;
	height: 160px;
	margin: 10px 0 0 10px;
	overflow: scroll;
	overflow-x: hidden;
}

button {
	width: 120px;
}

#rSelect select option input {
	display: inline;
}
-->

<style>
.a {
	width: 230px;
	height: 100px;
	border: 1px solid #99bbee;
	overflow: scroll;
	overflow-x: hidden;
	float: left;
	margin: 0 10px 0 10px;
}

.btn {
	width: 80px;
	height: 25px;
	margin: 5px 0;
}

#zwlrTitle {
	font-size: 24px;
}

.wbk {
	width: 200px;
	height: 140px;
	border: 1px solid #99bbee;
	float: right;
	margin-top: 20px;
	overflow: scroll;
	overflow-x: hidden;
}

.abtn:hover {
	color: #f00;
}

from {
	margin: 10px 0;
}
</style>

<jsp:include page="../common/include.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/zwlrlb/zwlrlb.js"></script>

<jsp:include page="../common/zwlrPlug.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">指纹录入列表</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<table id="datagrid" toolbar="#DIV_toolbar"></table>

		<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
			<a onclick="addZWLRLB_dialog();" plain="true"
				class="easyui-linkbutton" iconCls="icon-add">新增信息</a> <a
				onclick="showEnterDialog();" plain="true" class="easyui-linkbutton"
				iconCls="icon-remove">删除信息</a>
			<table class="ssk">
				<tr>
					<td><span>指纹录入列表名称:</span></td>
					<td><input id="LBMC_serchbar" name="LBMC" /></td>
					<!-- 
				<td><span>是否录入:</span></td>
				<td><input id="SFLR_serchbar" name="SFLR" /></td> 
				-->
					<td><button class="easyui-linkbutton" plain="true"
							onclick="doSearch();"></button></td>
				</tr>
			</table>
		</div>

		<div id="addZWLRLB_dlg" class="easyui-dialog"
			style="width:630px;height:310px;padding:10px 20px" closed="true"
			buttons="#addZWLRLB_dlg-buttons" modal="true" resizable="true">
			<form>
				<table>
					<tr>
						<td class="title"><lable>班级:</lable></td>
						<td><div id="BJ" style="width: 150px;"></div></td>
					</tr>
					<!--指纹录入列表名称 -->
					<tr>
						<td class="title"><span>列表名称:</span></td>
						<td><input class="easyui-validatebox" type="text" id="LBMC"
							data-options="required:true,missingMessage:'请输入指纹采集列表名称'"
							style=" border:1px solid #99bbe8; height:24px; line-height:24px;" /></td>
						<td colspan=2><input type="button" value="保存指纹录入列表标题"
							onclick="listTitle();" /></td>
						<!-- 左侧数据列表  列表中已选择学生 -->
					</tr>

					<tr>
						<td colspan=2><div class="a" name="list1"></div></td>
						<!-- 中间添加删除按键 -->
						<td>
							<div style="float:left; width:80px; margin-top:30px;">
                                                            <button class="btn" onclick="add();" type="button">添加</button>
                                                            <button class="btn" onclick="del();" type="button">删除</button>
							</div>
						</td>
						<!-- 右侧数据列表 未录入指纹的学生-->
						<td><div class="a" name="list2"></div></td>
					</tr>
					<!-- 保存指纹录入列表的按钮 -->
					<tr>
						<td colspan=4; align="center"><button
								onclick="stuToZWLRList();" style="margin:10px 100px 0 100px;">保存指纹录入列表</button></td>
					</tr>

				</table>
			</form>
		</div>

		<div id="addZWLRLB_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addZWLRLB_over();" iconCls="icon-ok">添加完成</a>
		</div>

		<%----------------------------- 新增信息对话框结束--------------------------------%>


		<%--------------------------------------------------- 查看/修改对话框开始-------------------------%>
		<div id="updateZWLRLB_dlg" class="easyui-dialog"
			style="width:680px;height:320px;padding:10px 20px" closed="true"
			buttons="#update_ZWLRLB_dlg-buttons" modal="true" resizable="true">

			<span style="padding-left:22px;">班级:</span>
			<div id="update_BJ"></div>

			<!--指纹录入列表名称 -->

			<form id="update_title" style="margin-top:10px;">
				<span>列表名称:</span> <input class="easyui-validatebox" type="text"
					id="update_LBMC" name="LBMC"
					data-options="required:true,missingMessage:'请输入指纹采集列表名称'"
					style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
				<input type="hidden" id="update_SFLR" name="SFLR" /> <input
					type="hidden" id=update_ZWLRSJ name="ZWLRSJ" /> <input
					type="hidden" id="update_LBCJSJ" name="LBCJSJ" /> <input
					type="hidden" id="update_title_ZWLR_ID" name="ZWLR_ID" /> <input
					type="hidden" id="update_title_optionflag" name="optionflag" /> <input
					type="button" value="保存指纹录入列表标题" onclick="updateListTitle();" />
			</form>


			<form id="updateZWLRLB_form" method="post" style="margin-top:10px;">
				<input type="hidden" id="update_ZWLR_ID" name="ZWLR_ID" /> <input
					type="hidden" id="update_optionflag" name="optionflag" /> <input
					type="hidden" id="update_SFLR" name="SFLR" /> <input type="hidden"
					id=update_ZWLRSJ name="ZWLRSJ" /> <input type="hidden"
					id="update_LBCJSJ" name="LBCJSJ" />
				<!-- 左侧数据列表  列表中已选择学生 -->
				<div class="a" name="update_list1"></div>
				<!-- 右侧数据列表 未录入指纹的学生-->
				<!-- 中间添加、删除、删除指纹按钮 -->
				<div style="float:left; width:50px; margin-top:-5px;" id="toolbar">
                                    <button class="btn" onclick="del_zw();" type="button">指纹删除</button>
                                    <button class="btn" onclick="update_add();" type="button">添加</button>
                                    <button class="btn" onclick="update_del();" type="button">删除</button>
				</div>
				<div class="a" name="update_list2"></div>
				<div style="clear:both;"></div>
				<!-- 保存指纹录入列表的按钮 -->
				<button onclick="updateList();" style="margin-left:210px;">保存指纹录入列表</button>
			</form>
		</div>

		<%-----------------------------------------修改信息按钮开始 ---------------------------- --%>
		<div id="update_ZWLRLB_dlg-buttons" style="margin-left:300px;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updateZWLRLB_over();" iconCls="icon-ok">修改信息完成</a>
		</div>
		<%----------------------------------修改信息按钮结束 ---------------------------------------%>
		<%--------------------------------查看/修改对话框结束-----------------------------------------%>

		<%-------------------------------------操作  指纹录入对话框开始--------------------------------- --%>
		<div id="zwlr_dlg" class="easyui-dialog"
			style="width:770px;height:300px;padding:10px 20px" closed="true"
			buttons="#zwlr_dlg-buttons" modal="true" resizable="true">
			<div id="zwlrTitle"></div>
			<div style="float:left;">
				<div style="float:left;">

					<table>
						<tr>
							<td
								style="border:1px solid #99bbee; border-right:none; text-align:right;">学生学号:</td>
							<td id="xs_xh"
								style="border:1px solid #99bbee; border-right:none; border-left:none; width:130px;"></td>
							<td
								style="border:1px solid #99bbee; border-right:none;  border-left:none; text-align:right;">学生姓名:</td>
							<td id="xs_xm"
								style="border:1px solid #99bbee; border-left:none; width:130px;"></td>
						</tr>

						<tr>
							<td style="border:1px solid #99bbee; border-right:none;">下一位学生学号:</td>
							<td id="next_xs_xh"
								style="border:1px solid #99bbee; border-right:none; border-left:none;"></td>
							<td
								style="border:1px solid #99bbee; border-right:none;  border-left:none;">下一位学生姓名:</td>
							<td id="next_xs_xm"
								style="border:1px solid #99bbee; border-left:none;"></td>
						</tr>
					</table>

					<form id="zwlr_form" method="post" enctype="multipart/form-data">
						<div style="margin-top:10px;margin-left:100px">
							<label for="xs_zhiwenId1">指纹表ID1:</label> <input
								class="easyui-validatebox" type="text" id="xs_zhiwenId1"
								name="zhiwenId1" data-options="required:true"
								style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
							<a class="easyui-linkbutton" plain="true"
								onclick="OpenEnrollFpDlg('xs_zhiwenId1');"><b>录入指纹1</b></a>
						</div>
						<div style="margin-top:10px;margin-left:100px">
							<label for="xs_zhiwenId2">指纹表ID2:</label> <input
								class="easyui-validatebox" type="text" id="xs_zhiwenId2"
								name="zhiwenId2" data-options="required:true"
								style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
							<a class="easyui-linkbutton" plain="true"
								onclick="OpenEnrollFpDlg('xs_zhiwenId2');"><b>录入指纹2</b></a>
						</div>
					</form>
				</div>
			</div>

			<div id="total" style="float:right;">
				<span>已采集人数/总人数：</span>
			</div>
			<!-- 暂未采集学生指纹列表 -->
			<div class="wbk"></div>

			<div style="margin-left:50px; margin-top:10px; float:left;">
				<input type="button" value="下一位采集者" onclick="nextData();" id="next" />
				<input type="button" value="保存指纹信息" onclick="addFp();" id="save" />
				<!-- <input type="button" value="保存当前采集状态" onclick=""/> -->
			</div>
		</div>




		<div id="zwlr_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="zwlr_over();" iconCls="icon-ok">采集完毕</a>
		</div>
		<!--添加学生指纹信息开始 -->
		<div id="addDAT_dlg" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px" closed="true"
			modal="true">
			<div>
				<table style="font-family: 宋体; font-size: 9pt" method="post"
					enctype="multipart/form-data">
					<tr>
						<td><object
								classid="clsid:059059BE-8F4C-49AC-B2A9-5649F02A4FC6"
								id="FPEngineEx1"
								data="DATA:application/x-oleobject;BASE64,汶六啂偹䕲祭噱䩚䌸偰杸䩁䅁奄睅䅁䈲䅍䅁㴽"
								style="height: 200px; width: 150px; "> </object></td>
						<td><input type="text" id="es" data-options="required:true"
							style=" border:1px solid #99bbe8; height:24px; line-height:24px;" /></td>
					</tr>
					<tr>
						<td><input type="button" value="登记参考模板"
							onclick="EnrollRefTemplate();"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input align="right" type="button" value="确定"
							onclick="OKTemplate();"></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 添加学生指纹信息结束 -->
		<!-- 单个人进行采集开始 -->

		<div id="single_addDAT_dlg" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px" closed="true"
			modal="true">
			<div>
				<table style="font-family: 宋体; font-size: 9pt" method="post"
					enctype="multipart/form-data">
					<tr>
						<td><object
								classid="clsid:059059BE-8F4C-49AC-B2A9-5649F02A4FC6"
								id="FPEngineEx2"
								data="DATA:application/x-oleobject;BASE64,汶六啂偹䕲祭噱䩚䌸偰杸䩁䅁奄睅䅁䈲䅍䅁㴽"
								style="height: 200px; width: 150px;"> </object></td>
						<td><input type="text" id="single_es"
							data-options="required:true" /></td>
					</tr>
					<tr>
						<td><input type="button" value="登记参考模板"
							onclick="single_EnrollRefTemplate();"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input align="right" type="button" value="确定"
							onclick="single_OKTemplate();"></td>
					</tr>
				</table>
			</div>
		</div>


		<div id="coll_dlg" class="easyui-dialog"
			style="width:530px;height:220px;" closed="true"
			buttons="#coll_dlg-buttons" modal="true" resizable="true">
			<form id="single_zwlr_form" method="post"
				enctype="multipart/form-data">
				<div style="float:left;">
					<div style="margin-top:10px;margin-left:100px">
						<label for="xs_zhiwenId1">指纹表ID1:</label> <input
							class="easyui-validatebox" type="text" id="single_xs_zhiwenId1"
							name="zhiwenId1" data-options="required:true"
							style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
						<a class="easyui-linkbutton" plain="true"
							onclick="single_OpenEnrollFpDlg('single_xs_zhiwenId1');"><b>录入指纹1</b></a>
					</div>
					<div style="margin-top:10px;margin-left:100px">
						<label for="xs_zhiwenId2">指纹表ID2:</label> <input
							class="easyui-validatebox" type="text" id="single_xs_zhiwenId2"
							name="zhiwenId2" data-options="required:true"
							style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
						<a class="easyui-linkbutton" plain="true"
							onclick="single_OpenEnrollFpDlg('single_xs_zhiwenId2');"><b>录入指纹2</b></a>
					</div>
				</div>
			</form>
			<div class="clear"></div>
			<input type="button" value="保存指纹信息" onclick="single_saveFp();"
				style="margin-left:200px;" />
		</div>


		<div id="coll_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="coll_over();" iconCls="icon-ok">采集完毕</a>
		</div>
		<!-- 单个人进行采集结束 -->

		<%------------------------------------------------------操作   指纹录入结束 -------------------------------------%>


	</div>

</body>
</html>

