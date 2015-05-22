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

<!--引入公共页面  -->
<jsp:include page="../common/include.jsp"></jsp:include>
<!--引入外部index.js -->
<script type="text/javascript" src="<%=basePath%>js/xzqt/index.js"></script>
<!--引入外部css -->
<link rel="stylesheet" type="text/css" href="../css/xz/index.css" />
<link rel="stylesheet" type="text/css" href="../css/xz/public.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui/echarts-all.js"></script>
		<!--设置下拉列表框样式-->
<style type="text/css">
		.combo-panel {
				background:url(../img/xz/cx-fxk.png) ;
				border:none;
		}
		.textbox{
			background:none;
		}
		.textbox-text{
			background:none;
		}
		</style>
</head>
<body>

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
						value="#session.yonghu.yhm" /></span> <span>>></span>
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
        	时间：2015-01-27
        	描述：查询区域开始
        -->
       
		<div class="cxk">
			<span class="l-f" style="margin-top:8px">我的查询：</span>
			<div class="ssk l-f">
				 <input id="cxtj" class="easyui-combobox"/>  
			</div>
			<button class="cx-btn l-f" onclick="query()"></button>
			<div class="clear"></div>
		</div>
		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：查询区域结束
     		 -->
		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：统计区开始
     		 -->
		<div class="tj l-f">
			<div class="title l-f">
				<div class="tj-menu">
					<div class="content">
						<ul id="xy"></ul>
						<div class="clear"></div>
						<span class="red">最多可选三个学院</span>
						<div class=" tj-btn l-r"></div>
					</div>
				</div>
			</div>
			<div class="content" id="content_0">
				<!-- 统计标题 -->
				<div class="tjbt l-f">
					<span id="tj_1"></span>
				</div>
				<div class="l-f" id="data_0">
					<!-- 出席 -->
					<div class="l-f"
						style="width:291px ; height: 35px; background: #277ebc;" id="cxBar_0"></div>
					<!-- 迟到 -->
					<div class="l-f"
						style="width:3px ; height: 35px; background: #091a6d;" id="cdBar_0"></div>
					<!-- 早退 -->
					<div class="l-f"
						style="width:6px ; height: 35px; background: #e7908e;" id="ztBar_0"></div>
					<!-- 缺席 -->
					<div class="l-f"
						style=" width:0px ; height: 35px; background: #830300;" id="qxBar_0"></div>
					<div class="clear"></div>
					<div class="cxqk l-f">
						<span>出席：<span id="cx_0"></span>% 迟到：<span id="cd_0"></span>% 早退：<span id="zt_0"></span>% 缺席：<span id="qx_0"></span>%</span>
					</div>
				</div>
				<div  id="ycnr_0" class="l-f" style="width:320px; display:none;">
					<div class="l-f"
						style="width:300px ; height: 35px; background: url(../img/xz/deloy-bg.png)"></div>
					<div class="cxqk l-f">
						<span>出席：无  迟到：无  早退：无  缺席：无</span>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="content" id="content_1">
				<!-- 统计标题 -->
				<div class="tjbt l-f">
					<span id="tj_2"></span>
				</div>
				<div  id="data_1" class="l-f" style="width:320px">
					<!-- 出席 -->
					<div class="l-f"
						style="width:285px ; height: 35px; background: #277ebc;" id="cxBar_1"></div>
					<!-- 迟到 -->
					<div class="l-f"
						style="width:6px ; height: 35px; background: #091a6d;" id="cdBar_1"></div>
					<!-- 早退 -->
					<div class="l-f"
						style="width:3px ; height: 35px; background: #e7908e;" id="ztBar_1"></div>
					<!-- 缺席 -->
					<div class="l-f"
						style=" width:6px ; height: 35px; background: #830300;" id="qxBar_1"></div>
					<div class="clear"></div>
					<div class="cxqk l-f">
						<span>出席：<span id="cx_1"></span>% 迟到：<span id="cd_1"></span>% 早退：<span id="zt_1"></span>% 缺席：<span id="qx_1"></span>%</span>
					</div>
				</div>
				<!--当数据为空显示  -->
				<div  id="ycnr_1" class="l-f" style="width:320px; display:none;">
					<div class="l-f"
						style="width:300px ; height: 35px; background: url(../img/xz/deloy-bg.png)"></div>
					<div class="cxqk l-f">
						<span>出席：无  迟到：无  早退：无  缺席：无</span>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="content" id="content_2">
				<!-- 统计标题 -->
				<div class="tjbt l-f">
					<span id="tj_3"></span>
				</div>
				<!-- 出席 -->
				<div class="l-f" id="data_2" class="l-f" style="width:320px">
					<div class="l-f"
						style="width:255px ; height: 35px; background: #277ebc;" id="cxBar_2"></div>
					<!-- 迟到 -->
					<div class="l-f"
						style="width:6px ; height: 35px; background: #091a6d;" id="cdBar_2"></div>
					<!-- 早退 -->
					<div class="l-f"
						style="width:9px ; height: 35px; background: #e7908e;" id="ztBar_2" ></div>
					<!-- 缺席 -->
					<div class="l-f"
						style=" width:30px ; height: 35px; background: #830300;" id="qxBar_2"></div>
					<div class="clear"></div>
					<div class="cxqk l-f">
						<span>出席：<span id="cx_2"></span>% 迟到：<span id="cd_2"></span>% 早退：<span id="zt_2"></span>% 缺席：<span id="qx_2"></span>%</span>
					</div>
				</div>
				<div  id="ycnr_2" class="l-f" style="width:320px; display:none;">
					<div class="l-f"
						style="width:300px ; height: 35px; background: url(../img/xz/deloy-bg.png);"></div>
					<div class="cxqk l-f">
						<span>出席：无  迟到：无  早退：无  缺席：无</span>
					</div>
				</div>
			</div>
		</div>
		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：统计区结束
     		 -->
		<!--
              	作者：李诚
              	时间：2015-01-28
              	描述：分割线
              -->
		<div class="fgx l-f"></div>
		<!--
              	作者：李诚
              	时间：2015-01-28
              	描述：饼状图
              -->
		<!-- <div class="bzt l-r"></div> -->
		<div id="main" class="l-r"></div>
		<div class="clear"></div>
		<!--
              	作者：李诚
              	时间：2015-01-28
              	描述：footer
              -->
		<div class="footer l-f">
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


</body>
</html>