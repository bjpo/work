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

<title>按全校统计出勤汇总</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../common/include.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/tongji/quanxiao_1.js"></script>
<style>
td {
	margin-left: 10px;
	height: 40px;
	text-align: right;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">全校</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<div class="sskzt" style="padding:5px;">
			<form id="serform" method="post">
				<table class="ssk">
					<tr>
						<!-- 查询方式 -->
						<td>查询方式:&nbsp;&nbsp; 
						
						<input id="selectType" class="easyui-combobox" name="dept"
    data-options="valueField:'id',textField:'text',url:'js/fangshi.json'">
						
						<!-- <select id="selectType"
							class="easyui-combobox" name="dept" style="width:150px;">
								<option value="day">日期</option>
								<option value="term" selected="selected">学期</option>
						</select>
						 -->
						</td>
						<!-- 查询方式为：日期：******************************** -->
						<td id="td_daytype_day_begin" style="">&nbsp;&nbsp;开始日期: 
							<input id="KSRQ_serchbar" name="KSRQ" class="Wdate" type="text"   onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-{%d-1}'})"/>
							<!--
								<input id="KSRQ_serchbar" name="KSRQ" class="easyui-datebox"></input>
							 -->
						</td>
						<td id="td_daytype_day_end" style="">&nbsp;&nbsp;结束日期:
							<input id="JSRQ_serchbar" name="JSRQ" class="Wdate" type="text"   onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-{%d-1}'})"/>
						<!-- 
							<input id="JSRQ_serchbar" name="JSRQ" class="easyui-datebox"></input>
						 -->
						</td>
						<!-- 查询方式为：学期： ******************************** -->
						<!-- 查询方式为：学期：学期 -->
						<td id="td_termtype_term" style="">
							&nbsp;&nbsp;选择学期:&nbsp;&nbsp;<input class="easyui-combobox"
							name="XUEQI_ID" id="XUEQI_ID_serchbar"
							data-options="valueField:'XQ_ID',textField:'XQMC',url:'<%=basePath%>listAllXUEQI.action'" />
						</td>
						<!-- 查询方式为：学期：周 -->
						<td id="td_termtype_zhou" style="">&nbsp;&nbsp;周:&nbsp;&nbsp;<input
							class="easyui-combobox" name="ZHOU" id="ZHOU_serchbar"
							data-options="valueField:'zhouid',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" />
						</td>
						<!-- 查询方式为：学期：天 -->
						<td id="td_termtype_tian" style="">&nbsp;&nbsp;天:&nbsp;&nbsp;<input
							class="easyui-combobox" name="XINGQI" id="XINGQI_serchbar"
							data-options="valueField:'xqid',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" />
						</td>
						<!-- 查询方式为：学期：课时 -->
					</tr>
					<tr>
						<td id="td_termtype_keshi" style="">
							&nbsp;&nbsp;课时:&nbsp;&nbsp; <select class="easyui-combobox"
							name="KS_ID" id="KS_ID_serchbar"
							data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'"
							style="width:150px;">
						
						</select>
						</td>
						<td><a id="btn" onclick="doSearch();"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
							<a href='javascript:history.go(-1);' class="easyui-linkbutton"
							data-options="iconCls:'icon-back'">返回</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="clear:both;"></div>
		<table id="datagrid">
		</table>
	</div>

</body>
</html>
