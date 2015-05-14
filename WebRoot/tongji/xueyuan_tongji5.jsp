<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>按班级统计出勤个人详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../common/include.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>js/tongji/xueyuan5.js"></script>
	<style type="text/css">
	.td{border:solid #99BBE8; border-width:0px 1px 1px 0px;}
	.table{border:solid #99BBE8; border-width:1px 0px 0px 1px;}
	</style>

<script type="text/javascript">
	//模糊查询
	function doSearch() {
		$('#datagrid').datagrid({url : 'listZHUANYE_BANJIXUESHENGBYCQQKTONGJI.action?XS_ID=<%=request.getParameter("XS_ID")%>&CQQK=<%=request.getParameter("CQQK")%>'});
		$('#datagrid').datagrid('load', {
			NJ_ID : $('#NJ_ID_serchbar').combobox('getValue'), //年级ID
			KSRQ :$('#KSRQ_serchbar').val(),//开始日期
			BJ_ID : $('#BJ_ID_serchbar').combobox('getValue'), //班级ID
			XINGQI : $('#XINGQI_serchbar').combobox('getValue'), //星期
			ZHOU : $('#ZHOU_serchbar').combobox('getValue'), //周
			KS_ID :$('#KS_ID_serchbar').combobox('getValue'), //课时
			XUEQI_ID : $('#XUEQI_ID_serchbar').combobox('getValue'), //学期
			JSRQ :$('#JSRQ_serchbar').val(),//结束日期
			ZY_ID : $('#ZY_ID_serchbar').combobox('getValue'), //专业ID
			optionflag : 'selbylike'
		});
	}
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
			
		var actionURL='listZHUANYE_BANJIXUESHENGBYCQQKTONGJI.action?XS_ID=<%=request.getParameter("XS_ID")%>&CQQK=<%=request.getParameter("CQQK")%>';
		$('#XY_ID_HIDDEN').val('<%=request.getParameter("XY_ID")%>');
		
		<%
		if(null!=request.getParameter("NJ_ID")&&!"".equals(request.getParameter("NJ_ID"))){
			%>
			$('#NJ_ID_serchbar').combobox('setValue','<%=request.getParameter("NJ_ID")%>');
			actionURL+='&NJ_ID=<%=request.getParameter("NJ_ID")%>';
			<%
		}
		%>
		
		<%
		if(null!=request.getParameter("dept")&&!"".equals(request.getParameter("dept"))){
		%>
			$('#selectType').combobox('setValue','<%=request.getParameter("dept")%>');
		<%
		}
		%>
		
		<%
		if(null!=request.getParameter("KSRQ")&&!"".equals(request.getParameter("KSRQ"))){
		%>
			$('#KSRQ_serchbar').val('<%=request.getParameter("KSRQ")%>');
			actionURL+='&KSRQ=<%=request.getParameter("KSRQ")%>';
		<%
		}
		%>
		<%
		if(null!=request.getParameter("JSRQ")&&!"".equals(request.getParameter("JSRQ"))){
			%>
			$('#JSRQ_serchbar').val('<%=request.getParameter("JSRQ")%>');
			actionURL+='&JSRQ=<%=request.getParameter("JSRQ")%>';
			<%
		}
		%>
		<%
		if(null!=request.getParameter("ZHOU")&&!"".equals(request.getParameter("ZHOU"))){
			%>
			$('#ZHOU_serchbar').combobox('setValue','<%=request.getParameter("ZHOU")%>');
			actionURL+='&ZHOU=<%=request.getParameter("ZHOU")%>';
			<%
		}
		%>
		<%
		if(null!=request.getParameter("XINGQI")&&!"".equals(request.getParameter("XINGQI"))){
			%>
			$('#XINGQI_serchbar').combobox('setValue','<%=request.getParameter("XINGQI")%>');
			actionURL+='&XINGQI=<%=request.getParameter("XINGQI")%>';
			<%
		}
		%>
		<%
		if(null!=request.getParameter("KS_ID")&&!"".equals(request.getParameter("KS_ID"))){
			%>
			$('#KS_ID_serchbar').combobox('setValue','<%=request.getParameter("KS_ID")%>');
			actionURL+='&KS_ID=<%=request.getParameter("KS_ID")%>';
			<%
		}
		%>
		<%
		if(null!=request.getParameter("XUEQI_ID")&&!"".equals(request.getParameter("XUEQI_ID"))){
			%>
			$('#XUEQI_ID_serchbar').combobox('setValue','<%=request.getParameter("XUEQI_ID")%>');
			actionURL+='&XUEQI_ID=<%=request.getParameter("XUEQI_ID")%>';
			<%
		}
		%>
		actionURL=encodeURI(encodeURI(actionURL)); 

		$('#datagrid').datagrid({
					url : actionURL,
					title : '全校-学院--按专业统计--按年级统计-按班级统计--分学生',
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
							},{
								title : '<b>所在专业</b>',
								field : 'ZYMC',
								sortable : true,
								width : 150
							},{
								title : '<b>所在年级</b>',
								field : 'NJMC',
								sortable : true,
								width : 100
							},{
								title : '<b>所在班级</b>',
								field : 'BJMC',
								sortable : true,
								width : 150
							},{
								title : '<b>学号</b>',
								field : 'XS_XH',
								sortable : true,
								width : 100
							},{
								title : '<b>学生姓名</b>',
								field : 'XS_XM',
								sortable : true,
								width : 100
							},{
								title : '<b>课程名称</b>',
								field : 'KCXX_KCMC',
								sortable : true,
								width : 100
							},{
								title : '<b>出勤情况</b>',
								field : 'CQQK',
								sortable : true,
								width : 100
							},{
								title : '<b>任课教师</b>',
								field : 'JG_JGMC',
								sortable : true,
								width : 100
							},{
								title : '<b>上课教室</b>',
								field : 'JS_JSMC',
								sortable : true,
								width : 100
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
<body>
<form id="serform">
	<table style="border:solid #99BBE8; border-width:0px 0px 0px 0px; ">
		<tr>
			<!-- 查询方式 -->
			<td style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">查询方式:</td>
			<td style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input id="selectType" class="easyui-combobox" name="dept" data-options="valueField:'id',textField:'text',url:'js/fangshi.json'">
			</td>
			<!-- 查询方式为：日期：******************************** -->
			<td id="td_daytype_day_begin"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始日期:
			</td>
			<td id="td_daytype_day_begin2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input id="KSRQ_serchbar" name="KSRQ" class="Wdate" type="text"   onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-{%d-1}'})"/>
			</td>
			<td id="td_daytype_day_end"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束日期:
			</td>
			<td id="td_daytype_day_end2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input id="JSRQ_serchbar" name="JSRQ" class="Wdate" type="text"   onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-{%d-1}'})"/>
			</td>
			<!-- 查询方式为：学期： ******************************** -->
			<!-- 查询方式为：学期：学期 -->
			<td id="td_termtype_term" align="right" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:
			</td>
			<td id="td_termtype_term2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input  class="easyui-combobox"  name="XUEQI_ID" id="XUEQI_ID_serchbar" data-options="valueField:'XQ_ID',textField:'XQMC',url:'<%=basePath%>listAllXUEQI.action'"/>
			</td>
			<!-- 查询方式为：学期：周 -->
			<td id="td_termtype_zhou" align="right"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				周:
			</td>
			<td id="td_termtype_zhou2" style="solid #99BBE8; border-width:0px 0px 0px 0px;">
			<input class="easyui-combobox"  id="ZHOU_serchbar" name="ZHOU" data-options="valueField:'zhouid',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'"/>
			</td>
			<!-- 查询方式为：学期：天 -->
			<td id="td_termtype_tian"  align="right" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				天:
			</td>
			<td id="td_termtype_tian2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input  class="easyui-combobox"  id="XINGQI_serchbar" name="XINGQI" data-options="valueField:'xqid',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" />
			</td>
			<!-- 查询方式为：学期：课时 -->
			<td id="td_termtype_keshi"  align="right" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课时:
			</td>
			<td id="td_termtype_keshi2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input id="KS_ID_serchbar" style="width: 150px;" class="easyui-combobox" name="KS_ID" data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'">
				<!-- 
				<select  class="easyui-combobox" style="width: 150px;" id="KS_ID_serchbar" name="KS_ID" data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'">
				</select>
				 -->
			</td>
		</tr>
		<!-- 增加：专业、学年、班级 -->
		<tr>
			<!-- 专业 -->
			<td id="td_class"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:
			</td>
			<td style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<select class="easyui-combobox" style="width: 150px;" id="ZY_ID_serchbar" name="ZY_ID" data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYE.action'">
				</select>  
			</td>
			<td id="td_nianji" align="right" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级:
			</td>
			<td  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
					<select class="easyui-combobox" style="width: 150px;"    id="NJ_ID_serchbar" name="NJ_ID" data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJI.action'">
					</select>
			</td>
			<!-- 班级 -->
			<td id="td_banji" align="right"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级:
			</td>
			<td style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<select class="easyui-combobox" style="width: 150px;" id="BJ_ID_serchbar" name="BJ_ID" data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'">
					 </select>  
			</td>
		</tr>
		<tr>
			<td>
				<a id="btn" onclick="doSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</td>
			<td>
				<a href='javascript:history.go(-1);' class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
			</td>
		</tr>
	</table>
</form>

<table id="datagrid"></table>
  </body>
</html>
