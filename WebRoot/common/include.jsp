<%@ page language="java" import="com.hrbsys.bean.YONGHU" %>
<%
    String path1 = request.getContextPath();
    String basePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
    if (null == session.getAttribute("yonghu")) {
        System.out.println("no login########################tiaoasdf333");
%>
<script type="text/javascript">
    window.location.href = 'login.jsp';
</script>
<%
    }
%>
<script type="text/javascript" src="<%=basePath1%>js/My97DatePicker/WdatePicker.js"></script>
<!--load easyUI begin-->
<script type="text/javascript" src="<%=basePath1%>js/jquery-easyui/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript"	src="<%=basePath1%>js/jquery-easyui/jquery.easyui.min.js" charset="UTF-8"></script>
<script type="text/javascript"	src="<%=basePath1%>js/jquery-easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath1%>js/tongji/tools/validateFormTextVal.js" charset="UTF-8"></script>
<script type="text/javascript">
    function doReset()
    {
        $(".ssk input").val("");
    }
</script>
<link rel="stylesheet"	href="<%=basePath1%>js/jquery-easyui/themes/default/easyui.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath1%>js/jquery-easyui/themes/icon.css"	type="text/css" />
<!-- load easyUI end -->
<link rel="stylesheet" href="<%=basePath1%>css/public.css"	type="text/css" />

