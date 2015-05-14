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

<title>赋权管理</title>

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
<jsp:include page="../common/include.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>js/yonghu/fuquanguanli.js"></script>
</head>
<body>
<div class="sskzt" style="padding:5px;">
	<a onclick="open_addyonghu2juese_dlg();"  style="cursor:pointer;">为用户赋权</a>
	<a onclick="open_addyonghuzu2juese_dlg();" style="cursor:pointer;">为用户组赋权</a>
	<a onclick="open_addmokuai2juese_dlg();" style="cursor:pointer;">为角色添加模块</a>
</div>
	<!-- 新增用户2角色  begin-->
	<div id="addyonghu2juese_dlg" class="easyui-dialog" style="width:900px;padding:10px 20px" closed="true" buttons="#addyonghu2juese_buttons" modal="true">
 		<form id="addyonghu2juese_form" method="post">
 				<div style="float: left;">
					<label for="yonghu">用户名称:</label>
					<input class="easyui-validatebox" type="text" id="yonghuselectbox" name="jy_yhid" />
				</div>
				<div style="clear:both;"></div>
				<div id="jueseliebiao_yonghu" style="float: left;"></div>
		</form>
	</div>
	<div id="addyonghu2juese_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addyonghu2juese();" iconCls="icon-ok">为用户赋权</a>
	</div>
	<!-- 新增用户2角色  end-->
	
	
	
	<!-- 新增用户组2角色  begin-->
	<div id="addyonghuzu2juese_dlg" class="easyui-dialog" style="width:900px;padding:10px 20px" closed="true" buttons="#addyonghuzu2juese_buttons" modal="true">
 		<form id="addyonghuzu2juese_form" method="post">
 				<div style="float: left;">
					<label for="yonghuzu">用户组名称:</label>
					<input class="easyui-validatebox" type="text" id="yonghuzuselectbox" name="jyz_yhzid" />
				</div>
				<div style="clear:both;"></div>
				<div id="jueseliebiao_yonghuzu" style="float: left;"></div>
		</form>
	</div>
	<div id="addyonghuzu2juese_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addyonghuzu2juese();" iconCls="icon-ok">为用户组赋权</a>
	</div>
	<!-- 新增用户组2角色  end-->
	
	<!-- 新增模块2角色  begin-->
	<div id="addmokuai2juese_dlg" class="easyui-dialog" style="width:900px;padding:10px 20px" closed="true" buttons="#addmokuai2juese_buttons" modal="true">
 		<form id="addmokuai2juese_form" method="post">
 				<div style="float: left;">
					<label for="mokuai">角色名称:</label>
					<input class="easyui-validatebox" type="text" id="jueseselectbox" name="jm_jsid" />
				</div>
				<div style="clear:both;"></div>
				<div id="mokuailiebiao_mokuai" style="float: left;"></div>
		</form>
	</div>
	<div id="addmokuai2juese_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addmokuai2juese();" iconCls="icon-ok">为模块赋权</a>
	</div>
	<!-- 新增模块2角色  end-->
	
</body>
</html>