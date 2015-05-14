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

<title>按全校统计出勤详细</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../common/include.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=basePath%>js/tongji/quanxiao_2.js"></script>
<script type="text/javascript">
var actionURLper='listQUANXIAO2.action';
var actionURL='?a=b';
$(function(){
		<%if (null != request.getParameter("dept")
					&& !"".equals(request.getParameter("dept"))) {%>
			$('#XUEQI_ID_serchbar').combobox('setValue','<%=request.getParameter("XUEQI_ID")%>');
		<%}%>
		
		<%if (null != request.getParameter("KSRQ")&& !"".equals(request.getParameter("KSRQ"))) {%>
			//$('#KSRQ_serchbar').datebox('setValue','<%=request.getParameter("KSRQ")%>');
			$('#KSRQ_serchbar').val('<%=request.getParameter("KSRQ")%>');
			actionURL+='&KSRQ=<%=request.getParameter("KSRQ")%>';
			<%}%>
		<%if (null != request.getParameter("JSRQ")&& !"".equals(request.getParameter("JSRQ"))) {%>
			//$('#JSRQ_serchbar').datebox('setValue','<%=request.getParameter("JSRQ")%>');
			$('#JSRQ_serchbar').val('<%=request.getParameter("JSRQ")%>');
			actionURL+='&JSRQ=<%=request.getParameter("JSRQ")%>';
			<%}%>
		<%if (null != request.getParameter("ZHOU")
					&& !"".equals(request.getParameter("ZHOU"))) {%>
			$('#ZHOU_serchbar').combobox('setValue','<%=request.getParameter("ZHOU")%>');
			actionURL+='&ZHOU=<%=request.getParameter("ZHOU")%>';
			<%}%>
		<%if (null != request.getParameter("XINGQI")
					&& !"".equals(request.getParameter("XINGQI"))) {%>
			$('#XINGQI_serchbar').combobox('setValue','<%=request.getParameter("XINGQI")%>');
			actionURL+='&XINGQI=<%=request.getParameter("XINGQI")%>';
			<%}%>
		<%if (null != request.getParameter("KS_ID")
					&& !"".equals(request.getParameter("KS_ID"))) {%>
			$('#KS_ID_serchbar').combobox('setValue','<%=request.getParameter("KS_ID")%>');
			actionURL+='&KS_ID=<%=request.getParameter("KS_ID")%>';
			<%}%>
		<%if (null != request.getParameter("XUEQI_ID")
					&& !"".equals(request.getParameter("XUEQI_ID"))) {%>
			$('#XUEQI_ID_serchbar').combobox('setValue','<%=request.getParameter("XUEQI_ID")%>');
			actionURL+='&XUEQI_ID=<%=request.getParameter("XUEQI_ID")%>
	';
<%}%>
	actionURL = encodeURI(encodeURI(actionURL));
		//数据加载 begin
		$('#datagrid')
				.datagrid(
						{
							url : actionURLper + actionURL,
							title : '全校-分学院统计',
							iconCls : 'icon-cls',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'XY_ID',
							columns : [ [
									{
										title : '<b>所在院系</b>',
										field : 'XYMC',
										sortable : true,
										width : 100
									},
									{
										title : '<b>应上课人数</b>',
										field : 'YSKRS',
										sortable : true,
										width : 100
									},
									{
										title : '<b>正常出席人数/%</b>',
										field : 'ZCCXRS',
										sortable : true,
										width : 100
									},
									{
										title : '<b>缺席人数/%</b>',
										field : 'QXRS',
										sortable : true,
										width : 100
									},
									{
										title : '<b>迟到人数/%</b>',
										field : 'CDRS',
										sortable : true,
										width : 100
									},
									{
										title : '<b>早退人数</b>',
										field : 'ZTRS',
										sortable : true,
										width : 100
									},
									{
										title : '<b>进一步查看组成</b>',
										field : 'XY_ID',
										width : 100,
										formatter : function(value, rowData,
												rowIndex) {
											ret = "<a style='cursor:hand' onclick=\"hrefsubmit('"
													+ value
													+ "');\">进一步查看组成</a>";
											return ret;
											//									return "<a href='tongji/xueyuan_tongji.jsp?XY_ID="+value+"'>进一步查看组成</a>";
										}
									} ] ],
							singleSelect : false,// 是否单选
							pagination : true,// 分页控件
							rownumbers : true,// 行号
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ]
						});
		$('#datagrid').datagrid('getPager').pagination({
			pageSize : 10,// 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		//数据加载 end
	});
</script>
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
			<span class="tree-icon tree-file"></span>
			<span class="tree-title">全校-分学院统计</span>
		</ul>
	</div>
	<div data-options="region:'center'">
		<div class="sskzt" style="padding:5px;">
			<form id="serform" method="post">
				<table class="ssk">
					<tr>
						<!-- 查询方式 -->
						<td>查询方式:&nbsp;&nbsp; 
						<input id="selectType" class="easyui-combobox" name="dept" data-options="valueField:'id',textField:'text',url:'js/fangshi.json'">
						<!-- <select id="selectType"
							class="easyui-combobox" name="dept" style="width:150px;">
								<option value="day">日期</option>
								<option value="term">学期</option>
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
						<td id="td_termtype_zhou" style="">
							&nbsp;&nbsp;周:&nbsp;&nbsp;<input class="easyui-combobox"
							id="ZHOU_serchbar" name="ZHOU"
							data-options="valueField:'zhouid',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" />
						</td>
						<!-- 查询方式为：学期：天 -->
						<td id="td_termtype_tian" style="">
							&nbsp;&nbsp;天:&nbsp;&nbsp;<input class="easyui-combobox"
							id="XINGQI_serchbar" name="XINGQI"
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
						</select> <input type="hidden" id="XY_ID_HIDDEN" name="XY_ID" />
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
