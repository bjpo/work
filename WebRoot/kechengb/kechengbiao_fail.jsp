<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<style type="text/css">
    table{border-left: 1px solid #666; border-bottom: 1px solid #666;}
	td{border-right:1px solid #666;border-top: 1px solid #666;}
</style>


</head>

<body width="640" >
<table width="640" style="border-collapse:collapse;" >
 <tr>
    <td colspan="8" align="center"><b>课程表</b></td>
  </tr>
  <tr>
    <td width="80">&nbsp;</td>
    <td width="80">星期一</td>
    <td width="80">星期二</td>
    <td width="80">星期三</td>
    <td width="80">星期四</td>
    <td width="80">星期五</td>
    <td width="80">星期六</td>
    <td width="80">星期日</td>
  </tr>
  <tr>
    <td colspan="8" align="center"><span style="color: red;">课程表查询失败!</span></td>
  </tr>
</table>
</body>
</html>
