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

<title>按学院统计出勤</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../common/include.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/tongji/xueyuan.js"></script>
<style>
td {
	margin-left: 10px;
	height: 40px;
	text-align: right;
}
</style>

<script type="text/javascript">

	//模糊查询
	function doSearch() {
	$('#datagrid').datagrid({
    url:'listXUEYUANTONGJI.action?XY_ID=<%=request.getParameter("XY_ID")%>',
    queryParams:{
			//	NJ_ID : $('#NJ_ID_serchbar').combobox('getValue'), //年级ID
				KSRQ : $('#KSRQ_serchbar').val(),//开始日期
			//	BJ_ID : $('#BJ_ID_serchbar').combobox('getValue'), //班级ID
				XINGQI : $('#XINGQI_serchbar').combobox('getValue'), //星期
				ZHOU : $('#ZHOU_serchbar').combobox('getValue'), //周
				KS_ID :$('#KS_ID_serchbar').combobox('getValue'), //课时
				XUEQI_ID : $('#XUEQI_ID_serchbar').combobox('getValue'), //学期
				JSRQ : $('#JSRQ_serchbar').val(),//结束日期
				ZY_ID : $('#ZY_ID_serchbar').combobox('getValue'), //专业ID
				optionflag : 'selbylike'
			}
			});
	}
	function hrefsubmit(value){
	    var path = "tongji/xueyuan_tongji2.jsp";
	    $('#ZY_ID_serchbar').combobox('setValue',value);
	    $('#serform').attr("action", path).submit();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////end
	$(function(){
	    hiddenAll();//隐藏全部
	    showdayType_day_begin();//默认为日期方式显示：显示日期相关
	    showdayType_day_end();
	    
		//查询方式复选框变更联动
		$('#selectType').combobox({onChange:function(n,o){
		    	if("day"==n.toString()){ //按天统计
		    				hiddenAll();
							showdayType_day_begin();
						    showdayType_day_end();
		    	}else if("term"==n.toString()){
							hiddenAll();
							showTermType_term();//显示学期
							showTermType_zhou();//显示周
							showTermType_tian();
							showTermType_keshi();
		    	}
		}});

		var actionURL='listXUEYUANTONGJI.action?XY_ID=<%=request.getParameter("XY_ID")%>';
		$('#XY_ID_HIDDEN').val('<%=request.getParameter("XY_ID")%>');
		<%if (null != request.getParameter("dept")
					&& !"".equals(request.getParameter("dept"))) {%>
			$('#XUEQI_ID_serchbar').combobox('setValue','<%=request.getParameter("XUEQI_ID")%>');
		<%}%>
		
		<%if (null != request.getParameter("KSRQ") && !"".equals(request.getParameter("KSRQ"))) {%>
			$('#KSRQ_serchbar').val('<%=request.getParameter("KSRQ")%>');
			actionURL+='&KSRQ=<%=request.getParameter("KSRQ")%>';
			<%}%>
		<%if (null != request.getParameter("JSRQ")&& !"".equals(request.getParameter("JSRQ"))) {%>
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
	//actionURL=encodeURI(encodeURI(actionURL)); 

		//		alert(actionURL);
		$('#datagrid')
				.datagrid(
						{
							url : actionURL,
							title : '全校-学院--按专业统计',
							iconCls : 'icon-cls',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'ZY_ID',
							columns : [ [
									{
										title : '<b>所在院系</b>',
										field : 'XYMC',
										sortable : true,
										width : 100
									},
									{
										title : '<b>所在专业</b>',
										field : 'ZYMC',
										sortable : true,
										width : 150
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
										field : 'ZY_ID',
										width : 100,
										formatter : function(value, rowData,
												rowIndex) {
											//	return "<a href='tongji/xueyuan_tongji2.jsp?ZY_ID="+value+"'>进一步查看组成</a>";
											if ("null" == value + "") {
												return "无记录";
											}
											ret = "<a style='cursor:hand' onclick=\"hrefsubmit('"
													+ value
													+ "');\">进一步查看组成</a>";
											return ret;
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
	});
</script>

</head>
<body class="easyui-layout">
	<!-- 左侧菜单 -->
	<div data-options="region:'west',title:'菜单',split:true"
		style="width:15%;">
		<ul id="qxmenu" style="text-align:center">
			<span class="tree-icon tree-file "></span>
			<span class="tree-title">学院统计</span>
		</ul>
	</div>
	<!-- 中心内容 -->
	<div data-options="region:'center'">
		<div class="sskzt" style="padding:5px;">
			<form id="serform">
				<table class="ssk">
					<tr>
						<!-- 查询方式 -->
						<td class="title">查询方式:</td>
						<td>
						<input id="selectType" class="easyui-combobox" name="dept" data-options="valueField:'id',textField:'text',url:'js/fangshi.json'">
						<!-- 
						<select id="selectType" class="easyui-combobox"
							name="dept" style="width:150px;">
								<option value="day">日期</option>
								<option value="term">学期</option>
						</select>
						 -->
						</td>
						<!-- 查询方式为：日期：******************************** -->
						<td class="title" id="td_daytype_day_begin">开始日期:</td>
						<td id="td_daytype_day_begin2">
							<input id="KSRQ_serchbar" name="KSRQ" class="Wdate" type="text"   onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-{%d-1}'})"/>
							<!-- 
								<input id="KSRQ_serchbar" name="KSRQ" class="easyui-datebox"></input></td>
							 -->
						<td class="title" id="td_daytype_day_end">结束日期:</td>
						<td id="td_daytype_day_end2">
							<input id="JSRQ_serchbar" name="JSRQ" class="Wdate" type="text"   onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-{%d-1}'})"/>
							<!-- 
								<input id="JSRQ_serchbar" name="JSRQ" class="easyui-datebox"></input></td>
						 	-->
						<!-- 查询方式为：学期： ******************************** -->
						<!-- 查询方式为：学期：学期 -->
						<td class="title" id="td_termtype_term">学期:</td>
						<td id="td_termtype_term2"><input class="easyui-combobox"
							name="XUEQI_ID" id="XUEQI_ID_serchbar"
							data-options="valueField:'XQ_ID',textField:'XQMC',url:'<%=basePath%>listAllXUEQI.action'" />
						</td>
						<!-- 查询方式为：学期：周 -->
					</tr>
					<tr>
						<td class="title" id="td_termtype_zhou">周:</td>
						<td id="td_termtype_zhou2"><input class="easyui-combobox"
							id="ZHOU_serchbar" name="ZHOU"
							data-options="valueField:'zhouid',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" />
						</td>
						<!-- 查询方式为：学期：天 -->
						<td class="title" id="td_termtype_tian">天:</td>
						<td id="td_termtype_tian2"><input class="easyui-combobox"
							id="XINGQI_serchbar" name="XINGQI"
							data-options="valueField:'xqid',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" />
						</td>
					</tr>
					<!-- 查询方式为：学期：课时 -->

					<tr>
						<td class="title" id="td_termtype_keshi">课时:</td>
						<td id="td_termtype_keshi2"><select class="easyui-combobox"
							id="KS_ID_serchbar" name="KS_ID"
							data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'"
							style="width:150px;">
						</select></td>

						<!-- 增加：专业、学年、班级 -->
						<!-- 专业 -->
						<td class="title" id="td_class">专业:</td>
						<td><select class="easyui-combobox" id="ZY_ID_serchbar"
							name="ZY_ID" style="width: 150px;"
							data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYE.action'">
						</select></td>
						<td id="td_nianji">
							<!--	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级: -->
						</td>
						<td>
							<!--<select class="easyui-combobox"  id="NJ_ID_serchbar" name="NJ_ID" data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJI.action'"></select>
							 -->
						</td>
						<!-- 班级 -->
						<td id="td_banji">
							<!-- 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级:
			 -->
						</td>
						<td>
							<!-- 
				<select class="easyui-combobox"  id="BJ_ID_serchbar" name="BJ_ID" data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'">
					 </select>  
			-->
						</td>
						<td style="width:200px;"><a id="btn" onclick="doSearch();"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
							<a href='javascript:history.go(-1);' class="easyui-linkbutton"
							data-options="iconCls:'icon-back'">返回</a>
							</div></td>
					</tr>
				</table>


			</form>
		</div>
		<div style="clear:both;"></div>
		<table id="datagrid"></table>

	</div>

</body>
</html>
