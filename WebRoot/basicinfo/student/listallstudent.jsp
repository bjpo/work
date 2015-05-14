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
<jsp:include page="../../common/include.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<script type="text/javascript"
		src="<%=basePath%>/js/basicinfo/student/student.js"></script>
		<div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
			<ul id="qxmenu" style="text-align:center">
				<span class="tree-icon tree-file "></span>
				<span class="tree-title">学生管理模块</span>
			</ul>
		</div>
		<div data-options="region:'center'">
			<table id="datagrid" toolbar="#DIV_toolbar"></table>
	<div id="DIV_toolbar" style="padding:5px;" class="sskzt">
		<a onclick="add_dialog();" plain="true" class="easyui-linkbutton"
			iconCls="icon-add">添加</a> <a onclick="del_dialog();" plain="true"
			class="easyui-linkbutton" iconCls="icon-remove">删除</a>

			<table class="ssk">
				<tr>
					<td class="title"><span>学生姓名:</span></td>
					<td><input id="zsxm_serchbar" name="zsxm" /></td>
					<td class="title"><button class="easyui-linkbutton"
							plain="true" onclick="doSearch();"></button></td>
					<td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
				</tr>
			</table>

	</div>
	<div id="add_dlg" class="easyui-dialog"
		style="width:90%;height:90%;padding:10px 20px" closed="true"
		buttons="#add_dlg-buttons" modal="true" resizable="true">
		<form id="add_form" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="title"><label for="xs_xh">学号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_xh"
						name="xh" data-options="required:true,missingMessage:'请输入学号'" /></td>
					<td class="title"><label for="xs_zsxm">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_zsxm"
						name="zsxm" data-options="required:true,missingMessage:'请输入姓名'" /></td>
					<td class="title"><label for="xs_xb">性别:</label></td>
					<td><input class="easyui-combobox" id="xs_xb" name="xb"
						data-options="valueField:'xingbie',textField:'xingbie',url:'<%=basePath%>js/xingbie.json'" /></td>
					<td class="title"><label for="xs_pycc">培养层次:</label></td>
					<td><select class="easyui-combobox" id="xs_pycc" name="pycc"
						style="width:150px;">
							<option value=""></option>
							<option value="博士">博士</option>
							<option value="硕士">硕士</option>
							<option value="本科">本科</option>
							<option value="大专">大专</option>
							<option value="中专">中专</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_xyId">所在学院:</label></td>
					<td  colspan=3><input class="easyui-combobox" id="xs_xyId" name="xyId"
						style="width:260px;"
						data-options="valueField:'xyid',textField:'xymc',url:'<%=basePath%>listforXUEYUAN.action'" /></td>
					<td class="title"><label for="xs_zyId">所在专业:</label>
					<td colspan=3><input class="easyui-combobox" id="xs_zyId" name="zyId"
						style="width:260px;"
						data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYEFORBANJI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_njId">所在年级:</label>
					<td colspan=3><input class="easyui-combobox" id="xs_njId" name="njId"
						style="width:260px;"
						data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJIFORBANJI.action'" /></td>
					<td class="title"><label for="xs_bjId">所在班级:</label>
					<td colspan=3><input class="easyui-combobox" id="xs_bjId" name="bjId"
						style="width:260px;"
						data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_cym">曾用名:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_cym"
						name="cym" /></td>
					<td class="title"><label for="xs_sfzhm">身份证号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_sfzhm" name="sfzhm"
						data-options="required:true,validType:'idcard',missingMessage:'请输入身份证号'" /></td>
					<td class="title"><label for="xs_csny">出生年月:</label></td>
					<td><input class="easyui-datebox" id="xs_csny" name="csny"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
					<td class="title"><label for="jxl_mz">民族:</label></td>
					<td><input class="easyui-combobox" id="xs_mz" name="mz"
						data-options="valueField:'minzu',textField:'minzu',url:'<%=basePath%>js/minzu.json'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_rtsj">入团时间:</label></td>
					<td><input class="easyui-datebox" id="xs_rtsj" name="rtsj"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
					<td class="title"><label for="xs_csd">出生地:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_csd"
						name="csd" /></td>
					<td class="title"><label for="xs_hjlb">户籍类别:</label></td>
					<td><select class="easyui-combobox" id="xs_hjlb" name="hjlb"
						style="width:150px;;">
							<option value=""></option>
							<option value="非农业户口">非农业户口</option>
							<option value="农业户口">农业户口</option>
					</select></td>
					<td class="title"><label for="xs_sushehao">宿舍号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_sushehao" name="sushehao"
						data-options="required:true,missingMessage:'请输入宿舍号'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_ssdh">宿舍电话:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_ssdh"
						name="ssdh"
						data-options="validType:'phone',required:true,missingMessage:'请输入宿舍电话'" /></td>
					<td class="title"><label for="xs_gatdm">港澳台代码:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_gatdm" name="gatdm" /></td>
					<td class="title"><label for="xs_lxr">联系人:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_lxr"
						name="lxr" data-options="required:true,missingMessage:'请输入联系人'" /></td>
					<td class="title"><label for="xs_yzbm">邮政编码:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_yzbm"
						name="yzbm" data-options="validType:'zipcode'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_dahh">档案行号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_dahh"
						name="dahh" data-options="validType:'integer'" /></td>
					<td class="title"><label for="xs_dayh">档案页号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_dayh"
						name="dayh" data-options="validType:'integer'" /></td>
					<td class="title"><label for="xs_dzyx">电子邮箱:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_dzyx"
						name="dzyx" data-options="validType:'email'" /></td>
					<td class="title"><label for="xs_lxdz">联系地址:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_lxdz"
						name="lxdz" data-options="required:true,missingMessage:'请输入联系地址'" />
				</tr>
				<tr>
					<td class="title"><label for="xs_lxdh">联系电话:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_lxdh"
						name="lxdh"
						data-options="required:true,validType:'mobile',missingMessage:'请输入联系电话'" /></td>
					<td class="title"><label for="xs_zsjj">招生季节:</label></td>
					<td><select class="easyui-combobox" id="xs_zsjj" name="zsjj"
						style="width:150px;">
							<option value=""></option>
							<option value="春季">春季</option>
							<option value="秋季">秋季</option>
					</select></td>
					<td class="title"><label for="xs_zkzh">准考证号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_zkzh"
						name="zkzh" /></td>
					<td class="title"><label for="xs_ksh">考生号:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_ksh"
						name="ksh" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_kslb">考生类别:</label></td>
					<td><select class="easyui-combobox" id="xs_kslb" name="kslb"
						style="width:150px;">
							<option value=""></option>
							<option value="统招">统招</option>
							<option value="自考">自考</option>
							<option value="成人">成人</option>
							<option value="函授">函授</option>
					</select></td>
					<td class="title"><label for="xs_jkzk">健康状况:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_jkzk"
						name="jkzk" /></td>
					<td class="title"><label for="xs_kstz">考生特征:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_kstz"
						name="kstz" /></td>
					<td class="title"><label for="xs_rxwhcd">入学文化程度:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_rxwhcd" name="rxwhcd" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_sysf">生源省份:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_sysf"
						name="sysf" /></td>
					<td class="title"><label for="xs_bylb">毕业类别:</label></td>
					<td><select class="easyui-combobox" id="xs_bylb" name="bylb"
						style="width:150px;">
							<option value=""></option>
							<option value="全日制">全日制</option>
							<option value="自考">自考</option>
							<option value="函授">函授</option>
							<option value="成人高考">成人高考</option>
							<option value="网络教育">网络教育</option>
							<option value="电大">电大</option>
							<option value="夜校">夜校</option>
					</select></td>
					<td class="title"><label for="xs_sydq">生源地区:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_sydq"
						name="sydq" /></td>
					<td class="title"><label for="xs_kldm">科类代码:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_kldm"
						name="kldm" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_rxcj">入学成绩:</label></td>
					<td><input class="easyui-numberbox" type="text" id="xs_rxcj"
						name="rxcj" precision="2" max="750" min="0" /></td>
					<td class="title"><label for="xs_xxtj">获知学校信息途径:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_xxtj"
						name="xxtj" /></td>
					<td class="title"><label for="xs_tc">特长:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_tc"
						name="tc" /></td>
					<td class="title"><label for="xs_rxsj">入学时间:</label></td>
					<td><input class="easyui-datebox" id="xs_rxsj" name="rxsj"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_zyIdLq">录取专业:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_zyIdLq" name="zyIdLq" 　/></td>
					<td class="title"><label for="xs_xz">学制:</label></td>
					<td><select class="easyui-combobox" id="xs_xz" name="xz"
						style="width:150px;">
							<option value=""></option>
							<option value="两年制">两年制</option>
							<option value="三年制">三年制</option>
							<option value="四年制">四年制</option>
							<option value="五年制">五年制</option>
					</select></td>
					<td class="title"><label for="xs_rxnf">入学年份:</label></td>
					<td><input class="easyui-datebox" id="xs_rxnf" name="rxnf"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
					<td class="title"><label for="xs_rxfs">入学方式:</label></td>
					<td><select class="easyui-combobox" id="xs_rxfs" name="rxfs"
						style="width:150px;">
							<option value=""></option>
							<option value="考试">考试</option>
							<option value="推荐">推荐</option>
							<option value="测验">测验</option>
							<option value="面谈">面谈</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_pylb">培养类别:</label></td>
					<td><select class="easyui-combobox" id="xs_pylb" name="pylb"
						style="width:150px;">
							<option value=""></option>
							<option value="国家计划外自筹经费">国家计划外自筹经费</option>
							<option value="国家计划内定向培养">国家计划内定向培养</option>
							<option value="国家计划外委托培养">国家计划外委托培养</option>
					</select></td>
					<td class="title"><label for="xs_pydx">培养对象:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_pydx"
						name="pydx" /></td>
					<td class="title"><label for="xs_bxfs">办学方式:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_bxfs"
						name="bxfs" /></td>
					<td class="title"><label for="xs_xxnx">学习年限:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_xxnx"
						name="xxnx" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_qtbxxs">其他办学形式:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_qtbxxs" name="qtbxxs" /></td>
					<td class="title"><label for="xs_zxwyyz">主修外语语种:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_zxwyyz" name="zxwyyz" /></td>
					<td class="title"><label for="xs_zxwyjb">主修外语级别:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="xs_zxwyjb" name="zxwyjb" /></td>
					<td class="title"><label for="xs_bxlx">办学类型:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_bxlx"
						name="bxlx" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_byzx">毕业中学:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_byzx"
						name="byzx" /></td>
					<td class="title"><label for="xs_sg">身高:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_sg"
						name="sg" /></td>
					<td class="title"><label for="xs_tz">体重:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_tz"
						name="tz" /></td>
					<td class="title"><label for="xs_jsjnldj">主修计算机能力等级:</label>
					<td><input class="easyui-validatebox" type="text"
						id="xs_jsjnldj" name="jsjnldj" /></td>
				</tr>
				<tr>
					<td class="title"><label for="xs_jtzz">家庭住址:</label></td>
					<td><input class="easyui-validatebox" type="text" id="xs_jtzz"
						name="jtzz" /></td>
						
					<td class="title">
					<!-- <label for="xs_imgfile">照片:</label>
					 -->
					 <%--
				<label for="xs_yhId">用户ID:</label>--%>
				
					<input class="easyui-validatebox" type="hidden"
						id="xs_yhId" name="yhId" />
					</td>
					<td colspan=3>
					<!-- 
					<input type="file" name="imgfile" id="xs_imgfile"
						class="easyui-validatebox" onchange="addPic()"
						data-options="validType:'picture'" /><input
						type="hidden" name="zhaopianId" class="easyui-validatebox" />
						 -->
						</td>
					<td class="title">
					<!-- 
					<label for="addpic">照片显示:</label></td>
					<td><img id="addpic" alt="图片">
					 -->
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="add_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addStudent();" iconCls="icon-ok">新增信息</a>
	</div>


	<div id="addDAT_dlg" class="easyui-dialog"
		style="width:400px;height:350px;padding:10px 20px" closed="true"
		modal="true">
		<div>
			<table style="font-family: 宋体; font-size: 9pt">
				<tr>
					<td><object
							classid="clsid:059059BE-8F4C-49AC-B2A9-5649F02A4FC6"
							id="FPEngineEx1"
							data="DATA:application/x-oleobject;BASE64,汶六啂偹䕲祭噱䩚䌸偰杸䩁䅁奄睅䅁䈲䅍䅁㴽"
							style="height: 200px; width: 150px"></object></td>
					<td><input type="text" id="es" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><input type="button" value="登记参考模板"
						ONCLICK=EnrollRefTemplate()></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input align="right" type="button" value="确定"
						ONCLICK=OKTemplate()></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 查看/修改对话框  BEGIN-->
	<div id="update_dlg" class="easyui-dialog"
		style="width:450px;height:320px;padding:10px 20px" closed="true"
		buttons="#update_dlg-buttons" modal="true">
		<form id="update_form" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="title"><label for="up_xs_xh">学号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_xh" name="xh" data-options="required:true" /><br /> <input
						class="easyui-validatebox" type="hidden" id="up_xs_id" name="xsId" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zsxm">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_zsxm" name="zsxm" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_xb">性别:</label></td>
					<td><input class="easyui-combobox" id="up_xs_xb" name="xb"
						data-options="valueField:'xingbie',textField:'xingbie',url:'<%=basePath%>js/xingbie.json'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_pycc">培养层次:</label></td>
					<td><select class="easyui-combobox" id="up_xs_pycc"
						name="pycc" style="width:150px;">
							<option value=""></option>
							<option value="博士">博士</option>
							<option value="硕士">硕士</option>
							<option value="本科">本科</option>
							<option value="大专">大专</option>
							<option value="中专">中专</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_xyId">所在学院:</label></td>
					<td><input class="easyui-combobox" id="up_xs_xyId" name="xyId"
						style="width:260px;"
						data-options="valueField:'xyid',textField:'xymc',url:'<%=basePath%>listforXUEYUAN.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zyId">所在专业:</label></td>
					<td><input class="easyui-combobox" id="up_xs_zyId" name="zyId"
						style="width:260px;"
						data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYEFORBANJI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_njId">所在年级:</label></td>
					<td><input class="easyui-combobox" id="up_xs_njId" name="njId"
						style="width:260px;"
						data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJIFORBANJI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_bjId">所在班级:</label></td>
					<td><input class="easyui-combobox" id="up_xs_bjId" name="bjId"
						style="width:260px;"
						data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_cym">曾用名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_cym" name="cym" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_sfzhm">身份证号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_sfzhm" name="sfzhm" data-options="validType:'idcard'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_csny">出生年月:</label></td>
					<td><input class="easyui-datebox" id="up_xs_csny" name="csny"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_mz">民族:</label></td>
					<td><input class="easyui-combobox" id="up_xs_mz" name="mz"
						data-options="valueField:'minzu',textField:'minzu',url:'<%=basePath%>js/minzu.json'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_rtsj">入团时间:</label>
					<td><input class="easyui-datebox" id="up_xs_rtsj" name="rtsj"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_csd">出生地:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_csd" name="csd" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_hjlb">户籍类别:</label></td>
					<td><select class="easyui-combobox" id="up_xs_hjlb"
						name="hjlb" style="width:150px;">
							<option value=""></option>
							<option value="非农业户口">非农业户口</option>
							<option value="农业户口">农业户口</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_sushehao">宿舍号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_sushehao" name="sushehao" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_ssdh">宿舍电话:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_ssdh" name="ssdh" data-options="validType:'phone'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_gatdm">港澳台代码:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_gatdm" name="gatdm" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_lxr">联系人:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_lxr" name="lxr" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_yzbm">邮政编码:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_yzbm" name="yzbm" data-options="validType:'zipcode'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_dahh">档案行号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_dahh" name="dahh" data-options="validType:'integer'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_dayh">档案页号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_dayh" name="dayh" data-options="validType:'integer'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_dzyx">电子邮箱:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_dzyx" name="dzyx" data-options="validType:'email'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_lxdz">联系地址:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_lxdz" name="lxdz" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_lxdh">联系电话:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_lxdh" name="lxdh" data-options="validType:'mobile'" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zsjj">招生季节:</label></td>
					<td><select class="easyui-combobox" id="up_xs_zsjj"
						name="zsjj" style="width:150px;">
							<option value=""></option>
							<option value="春季">春季</option>
							<option value="秋季">秋季</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zkzh">准考证号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_zkzh" name="zkzh" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_ksh">考生号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_ksh" name="ksh" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_kslb">考生类别:</label></td>
					<td><select class="easyui-combobox" id="up_xs_kslb"
						name="kslb" style="width:150px;">
							<option value=""></option>
							<option value="统招">统招</option>
							<option value="自考">自考</option>
							<option value="成人">成人</option>
							<option value="函授">函授</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_jkzk">健康状况:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_jkzk" name="jkzk" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_kstz">考生特征:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_kstz" name="kstz" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_rxwhcd">入学文化程度:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_rxwhcd" name="rxwhcd" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_sysf">生源省份:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_sysf" name="sysf" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_bylb">毕业类别:</label></td>
					<td><select class="easyui-combobox" id="up_xs_bylb"
						name="bylb" style="width:150px;">
							<option value=""></option>
							<option value="全日制">全日制</option>
							<option value="自考">自考</option>
							<option value="函授">函授</option>
							<option value="成人高考">成人高考</option>
							<option value="网络教育">网络教育</option>
							<option value="电大">电大</option>
							<option value="夜校">夜校</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_sydq">生源地区:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_sydq" name="sydq" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_kldm">科类代码:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_kldm" name="kldm" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_rxcj">入学成绩:</label></td>
					<td><input class="easyui-numberbox" type="text"
						id="up_xs_rxcj" name="rxcj" precision="2" max="750" min="0" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_xxtj">获知学校信息途径:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_xxtj" name="xxtj" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_tc">特长:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_tc" name="tc" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_rxsj">入学时间:</label></td>
					<td><input class="easyui-datebox" id="up_xs_rxsj" name="rxsj"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zyIdLq">录取专业:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_zyIdLq" name="zyIdLq" 　/></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_xz">学制:</label></td>
					<td><select class="easyui-combobox" id="up_xs_xz" name="xz"
						style="width:150px;">
							<option value=""></option>
							<option value="两年制">两年制</option>
							<option value="三年制">三年制</option>
							<option value="四年制">四年制</option>
							<option value="五年制">五年制</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_rxnf">入学年份:</label></td>
					<td><input class="easyui-datebox" id="up_xs_rxnf" name="rxnf"
						data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_rxfs">入学方式:</label></td>
					<td><select class="easyui-combobox" id="up_xs_rxfs"
						name="rxfs" style="width:150px;">
							<option value=""></option>
							<option value="考试">考试</option>
							<option value="推荐">推荐</option>
							<option value="测验">测验</option>
							<option value="面谈">面谈</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_pylb">培养类别:</label></td>
					<td><select class="easyui-combobox" id="up_xs_pylb"
						name="pylb" style="width:150px;">
							<option value=""></option>
							<option value="国家计划外自筹经费">国家计划外自筹经费</option>
							<option value="国家计划内定向培养">国家计划内定向培养</option>
							<option value="国家计划外委托培养">国家计划外委托培养</option>
					</select></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_pydx">培养对象:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_pydx" name="pydx" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_bxfs">办学方式:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_bxfs" name="bxfs" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_xxnx">学习年限:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_xxnx" name="xxnx" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_qtbxxs">其他办学形式:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_qtbxxs" name="qtbxxs" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zxwyyz">主修外语语种:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_zxwyyz" name="zxwyyz" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_zxwyjb">主修外语级别:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_zxwyjb" name="zxwyjb" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_bxlx">办学类型:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_bxlx" name="bxlx" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_byzx">毕业中学:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_byzx" name="byzx" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_sg">身高:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_sg" name="sg" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_tz">体重:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_tz" name="tz" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_jsjnldj">主修计算机能力等级:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_jsjnldj" name="jsjnldj" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_jtzz">家庭住址:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="up_xs_jtzz" name="jtzz" /></td>
				</tr>
				<tr>
					<td class="title">
						<%--<label for="up_xs_yhId">用户ID:</label>--%>
					</td>
					<td><input class="easyui-validatebox" type="hidden"
						id="up_xs_yhId" name="yhId" /></td>
				</tr>
				<tr>
					<td class="title"><label for="up_xs_imgfile">照片:</label></td>
					<td><input type="file" name="imgfile" id="up_xs_imgfile"
						onchange="changePic()" class="easyui-validatebox"
						data-options="validType:'picture'" /><br> <input
						type="hidden" name="zhaopianId" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td class="title"><label for="pic">照片显示:</label></td>
					<td><img id="pic" alt="图片"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="update_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="updateStudent();" iconCls="icon-ok">修改信息</a>
	</div>
	<!-- 查看/修改用户对话框  END-->
		</div>
	
</body>
</html>
