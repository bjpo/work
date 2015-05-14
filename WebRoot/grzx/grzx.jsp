<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/grzx.css" />
<jsp:include page="../common/include.jsp" />
<style type="text/css">
.lines-no .datagrid-body td {
	border-top:;
	border-right: 1px dotted transparent;
	border-bottom: 1px dotted transparent;
}

.lines-right .datagrid-body td {
	border-bottom: 1px dotted transparent;
}

.lines-bottom .datagrid-body td {
	border-right: 1px dotted transparent;
}

.datagrid-header {
	border: none;
}

.datagrid-wrap {
	background: transparent;
	margin-top: 100px;
}

.datagrid-pager {
	background: transparent;
}
/*限制文字显示字数*/
.datagrid-row td[field='text'] div {
	border: none;
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	course: hand;
}

.blod {
	font-weight: 800;
	color: #5c8ff3;
}
</style>
<script type="text/javascript">
if ("wdtz"=='<%=request.getParameter("name")%>') {
		$(function() {
			$(".wdts").toggle("slow");
			$(".wdxx").css({
				display : "none"
			});
			$(".grxx").css({
				display : "none"
			});
		});
	}
</script>

</head>
<body class="easyui-layout"
	style="height:99%;margin-top:3px;margin-right: 3px;">
			<script type="text/javascript" src="<%=basePath%>js/grzx/grzx.js"></script>
			<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：标题栏开始
        -->
			<div data-options="region:'west',title:'菜单',split:true"
				style="width:200px;margin-left: 3px;">
				<ul id="tt"></ul>
			</div>
			<!--中间部分-->
			<div data-options="region:'center'" style="overflow:scroll;">
				<div class="title">
					<div class="txbk">
						<img src="../img/txwoman.png" />
					</div>
					<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：标题栏用户信息显示开始
       			 -->
					<div class="yhm">
						<ul>
							<li><span>用户名：</span><span>${sessionScope.yonghu.yhm}</span></li>
							<li><span>所在用户组：</span><span>${sessionScope.yhzMc}</span></li>
						</ul>
					</div>
					<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：标题栏用户信息显示结束
       			 -->
					<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：菜单栏开始
       			 -->
					<div class="gnk">
						<div class="bg">
							<span>菜单</span>
						</div>

						<div class="gnq">
							<ul>
								<li><strong>>></strong></li>
								<li><span id="wdxx">我的资料</span></li>
								<li><span id="wdtz">我的通知</span></li>
							</ul>
						</div>
						<div style="clear: both;"></div>
						<span class="zk">点击展开</span>
					</div>
					<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：菜单栏结束
       			 -->
				</div>
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：提示信息开始
       			 -->
				<div class="tsxx">
					<span>上午好：${sessionScope.yonghu.yhm}</span><br /> <a href="">温馨提示：梦想还是要有的，万一实现了呢?</a>
				</div>
				<div style="clear: both;"></div>
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：提示信息结束
       			 -->
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：标题栏结束
       			 -->
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：用户信息开始
       			 -->
				<div class="grxx">
					<div class="wdzl"></div>
					<div class="content">
						<ul class="yhxx">
							<li><span>用户名：</span> <span>${sessionScope.yonghu.yhm}</span></li>
							<li><span>所在用户组：</span> <span>${sessionScope.yhzMc}</span></li>
							<li><span>上次登录时间：</span> <span>${sessionScope.yonghu.lastLoginTime}</span></li>
						</ul>
						<ul>
							<li><span>您所属类别为：</span> <span>${sessionScope.yhJs}</span> <span
								href="" id="wdzl">（查看我的个人情况）</span></li>
						</ul>
					</div>
				</div>
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：用户信息结束
       			 -->
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：用户资料开始
       			 -->
				<div class="wdxx">
					<div class="wdtb"></div>
					<div class="content">
						<s:if test="#session.jg!=null">
							<ul class="yhxx">
								<li><span>姓名：</span><span>${jg.JGMC}</span></li>
								<li><span>性别：</span><span>${jg.XB}</span></li>
								<li><span>出生年月：</span><span>${jg.CSNY}</span></li>
								<li><span>身份证号码：</span><span>${jg.SFZHM}</span></li>
								<li><span>毕业学校：</span><span>${jg.BYYX}</span></li>
								<li><span>文化程度：</span><span>${jg.XUELI}</span></li>
								<li><span>目前就职：</span><span></span></li>
							</ul>
						</s:if>
						<s:if test="#session.xs!=null">
							<ul class="yhxx">
								<li><span>姓名：</span><span>${xs.zsxm}</span></li>
								<li><span>性别：</span><span>${xs.xb}</span></li>
								<li><span>出生年月：</span><span>${xs.csny}</span></li>
								<li><span>身份证号码：</span><span>${xs.sfzhm}</span></li>
								<li><span>毕业学校：</span><span>2</span></li>
								<li><span>文化程度：</span><span>${xs.pycc}</span></li>
								<li><span>在校职务：</span><span>2</span></li>
							</ul>
						</s:if>
						<s:else>
							<ul class="yhxx">
								<li><span>姓名：</span><span>无</span></li>
								<li><span>性别：</span><span>无</span></li>
								<li><span>出生年月：</span><span>无</span></li>
								<li><span>身份证号码：</span><span>无</span></li>
								<li><span>毕业学校：</span><span>无</span></li>
								<li><span>文化程度：</span><span>无</span></li>
								<li><span>目前就职：</span><span>无</span></li>
							</ul>
						</s:else>
					</div>
				</div>
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：用户资料结束
       			 -->
				<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：用户通知栏开始
       			 -->

				<div class="wdts">
					<div class="wdtbtwo"></div>
					<div class="content">
						<table id="dg" style="width:580px;height:170px;"></table>
						<ul class="yhxx">
						</ul>
						<!-- <div class="next l-r"></div>
				<div class="uppage l-r"></div> -->
					</div>
				</div>
			</div>
			<!--
        	作者：李诚
        	时间：2015-01-22
        	描述：用户通知结束
       			 --></body>

</html>