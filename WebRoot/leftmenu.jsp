<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="<%=basePath%>js/leftMenu/leftMenu.js"></script>

<div id="aa" class="easyui-accordion" data-options="multiple:true">
	<!--2015-01-26 进行屏蔽
	<div title="查询信息" data-options="iconCls:'icon-save',selected:true"	style="padding:10px;">
		<ul id="treemenu">
		</ul>
	</div> 
	-->
</div>

