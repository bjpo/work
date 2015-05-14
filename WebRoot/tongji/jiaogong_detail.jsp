<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'query1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../common/include.jsp"></jsp:include>
	<!-- 
	<script type="text/javascript" src="<%=basePath%>js/tongji/xueyuan.js"></script>
	 -->
	<style>
		td{margin-left:10px; height:40px; text-align:right;}
	</style>

<script type="text/javascript">
	//隐藏日期类型:开始时间
	function hiddendayType_day_begin(){
		$("#td_daytype_day_begin").hide();
		$("#td_daytype_day_begin2").hide();
		$("#db_daytype_day_begin").datebox({disabled:true,required:false}); 
	}
	//隐藏日期类型：结束时间
	function hiddendayType_day_end(){
		$("#td_daytype_day_end").hide();
		$("#td_daytype_day_end2").hide();
		$("#db_daytype_day_end").datebox({disabled:true,required:false}); 
	}
	//隐藏学期类型:学期
	function hiddenTermType_term(){
		$("#td_termtype_term").hide();
		$("#td_termtype_term2").hide();
		$("#db_termtype_term").combobox({disabled:true});
	}
	//隐藏学期类型:周
	function hiddenTermType_zhou(){
		$("#td_termtype_zhou").hide();
		$("#td_termtype_zhou2").hide();
		$("#db_termtype_zhou").combobox({disabled:true});
	}
	
	//隐藏学期类型:天
	function hiddenTermType_tian(){
		$("#td_termtype_tian").hide();
		$("#td_termtype_tian2").hide();
		$("#db_termtype_tian").combobox({disabled:true});
	}
	//隐藏学期类型:课时
	function hiddenTermType_keshi(){
	/*	$("#td_termtype_keshi").hide();
		$("#td_termtype_keshi2").hide();
		$("#db_termtype_keshi").combobox({disabled:true});
	*/}
	//显示日期类型:开始时间
	function showdayType_day_begin(){
		$("#td_daytype_day_begin").show();
		$("#td_daytype_day_begin2").show();
		$("#db_daytype_day_begin").datebox({disabled:false,required:true}); 
	}
	//显示日期类型：结束时间
	function showdayType_day_end(){
		$("#td_daytype_day_end").show();
		$("#td_daytype_day_end2").show();
		$("#db_daytype_day_end").datebox({disabled:false,required:true}); 
	}
	//显示学期类型:学期
	function showTermType_term(){
		$("#td_termtype_term").show();
		$("#td_termtype_term2").show();
		$("#db_termtype_term").combobox({disabled:false});
	}
	//显示学期类型:周
	function showTermType_zhou(){
		$("#td_termtype_zhou").show();
		$("#td_termtype_zhou2").show();
		$("#db_termtype_zhou").combobox({disabled:false});
	}
	
	//显示学期类型:天
	function showTermType_tian(){
		$("#td_termtype_tian").show();
		$("#td_termtype_tian2").show();
		$("#db_termtype_tian").combobox({disabled:false});
	}
	//显示学期类型:课时
	function showTermType_keshi(){
		$("#td_termtype_keshi").show();
		$("#td_termtype_keshi2").show();
		$("#db_termtype_keshi").combobox({disabled:false});
	}

	//隐藏全部
	function hiddenAll(){
		hiddendayType_day_begin();
		hiddendayType_day_end();
		hiddenTermType_term();
		hiddenTermType_zhou();
		hiddenTermType_tian();
		hiddenTermType_keshi();
	}

	//模糊查询
	function doSearch() {
	$('#datagrid').datagrid({
    url:'listTONGJIbyJGdetial.action',
    queryParams:{
    			JG_ID:$('#JG_serchbar').combobox('getValue'),
				KSRQ : $('#KSRQ_serchbar').combobox('getValue'),//开始日期
				XINGQI : $('#XINGQI_serchbar').combobox('getValue'), //星期
				ZHOU : $('#ZHOU_serchbar').combobox('getValue'), //周
				KS_ID :$('#KS_ID_serchbar').combobox('getValue'), //课时
				XUEQI_ID : $('#XUEQI_ID_serchbar').combobox('getValue'), //学期
				JSRQ : $('#JSRQ_serchbar').combobox('getValue'),//结束日期
				KCXX_ID:$('#hidden_kcxx_id').val(),
				optionflag : 'selbylike'
			}
			});
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

		var actionURL='listTONGJIbyJGdetial.action?1=1';
		<%
		if(null!=request.getParameter("dept")&&!"".equals(request.getParameter("dept"))){
		%>
		//	$('#XUEQI_ID_serchbar').combobox('setValue','<%=request.getParameter("XUEQI_ID")%>');
		<%
		}
		%>
		<%
		if(null!=request.getParameter("KSRQ")&&!"".equals(request.getParameter("KSRQ"))){
			%>
			$('#KSRQ_serchbar').datebox('setValue','<%=request.getParameter("KSRQ")%>');
			actionURL+='&KSRQ=<%=request.getParameter("KSRQ")%>';
			<%
		}
		%>
		<%
		if(null!=request.getParameter("JSRQ")&&!"".equals(request.getParameter("JSRQ"))){
			%>
			$('#JSRQ_serchbar').datebox('setValue','<%=request.getParameter("JSRQ")%>');
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
		<%
		if(null!=request.getParameter("JG_ID")&&!"".equals(request.getParameter("JG_ID"))){
			%>
			$('#JG_serchbar').combobox('setValue','<%=request.getParameter("JG_ID")%>');
			actionURL+='&JG_ID=<%=request.getParameter("JG_ID")%>';
			<%
		}
		%>

		<%
		if(null!=request.getParameter("KCXX_ID")&&!"".equals(request.getParameter("KCXX_ID"))){
			%>
			$('#hidden_kcxx_id').val('<%=request.getParameter("KCXX_ID")%>');
			actionURL+='&KCXX_ID=<%=request.getParameter("KCXX_ID")%>';
			<%
		}
		%>
		$('#datagrid').datagrid({
					url : actionURL,
					title : '按教师统计详细展示',
					iconCls : 'icon-cls',
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					fitColumns : false,
					nowrap : false,
					border : false,
					idField : 'ZY_ID',
					columns : [ [
							{
								title : '<b>课程名称</b>',
								field : 'KCXX_KCMC',
								sortable : true,
								width : 100
							},{
								title : '<b>任课教师</b>',
								field : 'JG_JGMC',
								sortable : true,
								width : 150
							},{
								title : '<b>课时</b>',
								field : 'KSMC',
								sortable : true,
								width : 100
							},{
								title : '<b>学生学号</b>',
								field : 'XS_XH',
								sortable : true,
								width : 100
							},{
								title : '<b>学生姓名</b>',
								field : 'XS_XM',
								sortable : true,
								width : 100
							},{
								title : '<b>所在院系</b>',
								field : 'XYMC',
								sortable : true,
								width : 100
							},{
								title : '<b>所在专业</b>',
								field : 'ZYMC',
								sortable : true,
								width : 100
							},{
								title : '<b>所在年级</b>',
								field : 'NJMC',
								sortable : true,
								width : 100
							} ,{
								title : '<b>所在班级</b>',
								field : 'BJMC',
								sortable : true,
								width : 100
							} ,{
								title : '<b>上课时间</b>',
								field : 'SKRQ',
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
	<table style="float: left; margin:10px 0 0 10px;">
	<tr>
		<td colspan="10">
			任课教师:
			<select class="easyui-combobox"  id="JG_serchbar" name="JG_ID" data-options="valueField:'JG_ID',textField:'JGMC',url:'<%=basePath%>listAllJIAOGONG.action'" style="width: 150px;"/>
		</td>
	</tr>
		<tr>
			<!-- 查询方式 -->
			<td>查询方式:</td>
			<td>
				<select id="selectType" class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="day">日期</option>
				    <option value="term">学期</option>    
				</select>
			</td>
			<!-- 查询方式为：日期：******************************** -->
			<td id="td_daytype_day_begin"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始日期:
			</td>
			<td id="td_daytype_day_begin2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input id="KSRQ_serchbar" name="KSRQ" class="easyui-datebox"></input>
			</td>
			<td id="td_daytype_day_end"  style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束日期:
			</td>
			<td id="td_daytype_day_end2" style="border:solid #99BBE8; border-width:0px 0px 0px 0px;">
				<input id="JSRQ_serchbar" name="JSRQ" class="easyui-datebox"></input>
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
				<select  class="easyui-combobox"  id="KS_ID_serchbar" name="KS_ID" data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'">
					<option>全部学时</option>
				</select>
				<input type="hidden" id="hidden_kcxx_id" name="KCXX_ID" />
			</td>
		</tr>	
	</table>
	<div style="width: 200px;float:right; text-align:right; border: 1px; border-color: red;"><br/><br/>
	<a id="btn" onclick="doSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	<a href='javascript:history.go(-1);' class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></div>
</form>
<table id="datagrid"></table>
  </body>
</html>
