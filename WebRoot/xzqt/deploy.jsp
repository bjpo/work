<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<meta charset="utf-8" />
<title>校长前台</title>
<!--引入外部公共页面  -->
<jsp:include page="../common/include.jsp"></jsp:include>
<!--引入外部deploy.js -->
<script type="text/javascript" src="<%=basePath%>js/xzqt/deploy.js"></script>
<!--引入外部css  -->
<link rel="stylesheet" type="text/css" href="../css/xz/index.css" />
<link rel="stylesheet" type="text/css" href="../css/xz/deploy.css" />
<link rel="stylesheet" type="text/css" href="../css/xz/public.css" />
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<style>
	#topic {
				width: 270px;
				background: #fff;
				padding: 3px;
				border-top: 1px solid #ddd;
				border-right: 2px solid #ddd;
				border-bottom: 2px solid #ddd;
				border-left: 1px solid #ddd;
				position: absolute;
				top: 100px;
				left: 200px;
			}
	#topic .inner_html {
				padding: 10px;
				line-height: 20px;
				font-size: 12px;
				color: #666;
				text-indent: 24px;
				font-family: arial;
			}
			#topic .inner_html a {
				color: #990000;
				font-weight: bold;
				text-decoration: none;
				border-bottom: 1px dotted #990000;
			}
			#topic .inner_html a:hover {
				border-bottom: 2px solid #990000;
			}

</style>
</head>
<body>
		<!--存放当前页码 -->
		<input type="hidden" value="" id="currPage"/>
		<!--存放数据的总页数-->
		<input type="hidden" value="" id="currTotalPage"/>
	<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：header区域开始
        -->
	<div class="main l-f">
		<div class="header">
			<div class="portrait l-f"></div>
			<div class="use l-f">
				<span>当前用户：</span> <span id="user"><s:property
						value="#session.yonghu.yhm"/></span> <span>>></span>
			</div>
			<div class="tool l-f">
				<a id="personal" href="user.jsp">个人中心</a> <a href="logout.action">退出登录</a>
			</div>
			<a class="qh l-r" href="../index.jsp"></a>
			<div class="xxts l-r">
				<a id="message">信息（<span id="messageNum"></span>）</a>
			</div>
		</div>
		<div class="clear"></div>
		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：header区域结束
       		 -->
		<!--
        	作者：李诚
        	时间：2015-01-28
        	描述：配置统计查询区域开始
       		 -->
		<div class="deploy l-f">
			<span class="title">配置查询:</span>
			<div class="deployone-bg">
				<ul class="xlxx l-f">
					<li class="ksrq"><span>开始日期</span> 
						<input id="kstime" type="text" readonly="readonly" style="width:100px;" onclick="WdatePicker({el:$dp.$('kstime')})"/>
						</li>
					<li class="jsrq"><span>结束日期</span>
						<input id="jstime" type="text" readonly="readonly"  style="width:100px;" onclick="WdatePicker({el:$dp.$('jstime')})"/>
						</li>
					<li class="xq-cn"><span>学期</span>
						<div class="easyui-combobox" id="xq" name="XUEQI_ID" data-options="url:'listAllXUEQI.action' ,valueField:'XQ_ID' ,textField:'XQMC',editable:false"></div>
						</li>
				</ul>
				<span class="tsxx">※每项最多选择一个</span>
				<div class="clear"></div>
				<div class="content l-f">
					<span class="l-f">学院</span>
					<div class="deploytwo-bg l-f">
						<ul id="pz_xy">
						<%-- 	<li>
								<div class="tjxk l-f"></div> <span class="l-f">工学院</span>
							</li> --%>
						</ul>
						<button class="next l-r" onclick="toPage(parseInt(document.getElementById('currPage').value)==parseInt(document.getElementById('currTotalPage').value)?parseInt(document.getElementById('currTotalPage').value):parseInt(document.getElementById('currPage').value)+1);" style="border:none"></button>
						<button class="uppage l-r" onclick="toPage(parseInt(document.getElementById('currPage').value)==1?1:parseInt(document.getElementById('currPage').value)-1);" style="border:none"></button>
					</div>
				</div>
				<div class="content l-f">
					<span class="l-f">专业</span>
					<div class="deploytwo-bg l-f">
						<ul id="pz_zy">
						<%-- 	<li><span class="l-f">工学院：</span></li>
							<li>
								<div class="tjxk l-f"></div> <span class="l-f">土木工程施工管理</span>
							</li> --%>
						</ul>
					<!-- 	<div class="next l-r"></div>
						<div class="uppage l-r"></div> -->
					</div>
				</div>

				<div class="content l-f">
					<span class="l-f">年级</span>
					<div class="deploytwo-bg l-f">
						<ul class="l-f" id="pz_nj">
						</ul>
					<!-- 	<div class="next l-r"></div>
						<div class="uppage l-r"></div> -->
					</div>
				</div>
			<div class="content l-f">
				<span class="l-f">班级</span>
				<div class="deploytwo-bg l-f">
					<ul id="pz_bj">
					</ul>
				<!-- 	<div class="next l-r"></div>
					<div class="uppage l-r"></div> -->
				</div>
			</div>
		</div>
	<button class="pzbc l-r" onclick="pzbc();"></button>
	</div>
	<!--
        	作者：李诚
        	时间：2015-01-28
        	描述：配置统计查询区域结束
       		 -->
	<!--
        	作者：李诚
        	时间：2015-01-28
        	描述：保存配置开始
       		 -->
	<div class="fgx l-f"></div>
	<a href="index.jsp" class="fhsy"></a>
	<div class="bcpz l-f">
		<span class="title l-r">以保存统计查询配置</span>
		<div class="bcpz-bg l-f">
			<ul class="l-f" id="pztj">
			<%-- 	<li>
					<div class="xk"></div> <span>工学院+土木系0201++++++++++++++++++++++++++++++</span>
				</li> --%>
			</ul>
			<span class="tsxx">※最多保存10条信息</span>
		</div>
		<div class="bc l-r"></div>
		<div class="bj l-r"></div>
		<div class="sc l-r"></div>
		<!-- <div class="xq l-r"></div> -->
	</div>
	<!--
        	作者：李诚
        	时间：2015-01-28
        	描述：保存配置开始
       		 -->
	<div class="clear"></div>
	<!--
              	作者：李诚
              	时间：2015-01-28
              	描述：footer
              -->

	<div class="footer">
		<div class="qxcx l-f">
			<span>全校出勤：正常<span id="zc"></span></span> <span>缺席<span id="qx"></span></span> <span>迟到<span id="cd"></span></span> <span>早退<span id="zt"></span></span>
		</div>
		<div class="tcdl l-r">
			<a class="tc-span" href="logout.action"></a>
		</div>
		<div class="pz l-r">
			<a class="pz-span" href="deploy.jsp"></a>
		</div>
	</div>
	</div>
<!--浮动时显示标题  -->	
	<div id="topic" style="display: none; z-index:2">
			<div class="adorn"></div>
			<div class="inner_html"></div>
	</div>

</body>
</html>