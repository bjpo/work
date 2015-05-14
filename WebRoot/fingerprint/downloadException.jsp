<%--
    Document   : downloadException
    Created on : 2015-5-13, 13:11:40
    Author     : Li
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body style="background:url(<%=basePath%>img/ssk-bg.png);">
        <h1>系统繁忙,请稍后再试!</h1>
        <a href="<%=basePath%>fingerprint/fingerprint.jsp">返回指纹录入</a>
    </body>
</html>