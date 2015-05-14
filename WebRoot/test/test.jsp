<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="../common/include.jsp"></jsp:include>
  	<script type="text/javascript">
  		$(function(){
	//显示数据表格
    $('#datagrid').datagrid({
		url:'xx.action',
		title:'测试',
		iconCls:'icon-cls',
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50,60,70,80],
		fitColumns:true,
		nowrap:false,
		border:false,
		idField:'userId',
		columns:[[{title:'主键',field:'userId'},
				  {title:'姓名',field:'username'},
				  {title:'密码',field:'userpwd'},
				  {title:'操作',field:'userId',formatter:function(value,rowData,rowIndex){ 
                            return '【<a href="jiaoxuemishu_updatepk.jsp">修改</a>】【<a href="jiaoxuemishu_selpk.jsp" onclick="delCol();">删除</a>】'; 															
																	}
				  }
				  ]]
	  });
	});
  	</script>
  </head>
  <body>
		<table id="datagrid">
		</table>
  </body>
</html>
