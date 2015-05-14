<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<!--引入外部user.js-->
<script type="text/javascript" src="<%=basePath%>js/xzqt/user.js"></script>

<!--引入外部 css-->
<link rel="stylesheet" type="text/css" href="../css/xz/index.css" />
<link rel="stylesheet" type="text/css" href="../css/xz/user.css" />
<link rel="stylesheet" type="text/css" href="../css/xz/public.css" />
<script type="text/javascript">
	/*
	 * 异步请求要修改用户密码的用户id
	 */
	$.ajax({
		url : "updateYONGHU.action?yh_id=" + '${yonghu.yhid}',
		success : function(data) {
			var obj = eval("(" + data + ")");
			$("#yhz_id").val(obj.json_yhzid);
			$("#yhmc").val(obj.yhm);
			$("#yhid").val(obj.yhid);
			$("#updateyh").val("updateyh");
		}
	});
	/*
	 * 旧密码输入完成后进行验证，通过了让其输入新密码
	 */
	function check() {
		if ($("#oldPwd").val() != '${yonghu.yhmm}') {
			$.messager.alert('提示', '原密码不正确');
			$("#newPwd").attr({
				"disabled" : "true"
			});
			$("#reNewPwd").attr({
				"disabled" : "true"
			});
			return;
		} else {
			$("#newPwd").removeAttr("disabled");
			$("#reNewPwd").removeAttr("disabled");
		}
	}
	//跳到通知中心
	if ("tzzx"=='<%=request.getParameter("name")%>') {
	$(function(){
			reload();
		$(".grzx ul li").removeClass("user-bg");
		$(".grzx ul li span").removeClass("blus");
		$(".grzx-tzxx").addClass("user-bg");
		$(".grzx-tzxx span").addClass("blus");
		$(".tzzx").css({
			display : "block"
		});
		$(".grzl").css({
			display : "none"
		});
		$(".xgmm").css({
			display : "none"
		});
		$(".tzzx-title").css({
			display : "block"
		});
		$(".grzl-title").css({
			display : "none"
		});
		$(".xgmm-title").css({
			display : "none"
		});
	});
	
	}
</script>
<style type="text/css">
/*限制文字显示字数*/
.tzzx-content {
	width: 100px;
	table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}

.current_page {
	line-height: 34px;
	letter-spacing: 3px;
}
.lines-no .datagrid-body td {
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

.datagrid-wrap,.datagrid-pager {
	background: transparent;
}
/*限制文字显示字数*/
.datagrid-row td[field='text'] div {
	border: none;
	width: 300px;
	overflow-y: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	course: hand;
}

/*给未计信息加粗*/
.blod {
	font-weight: 800;
	color: #5c8ff3;
}
/*设置表格放置的位置*/
.datagrid .datagrid-pager {
border-width: 0 0 0 0;
 margin:0 0 0 280px;
}
/*将数据表格的滚动条隐藏掉*/
.datagrid-body{
	overflow:hidden;
}
</style>
</head>
<body>
	<script type="text/javascript"
		src="<%=basePath%>js/modifypwd/modifypwd.js"></script>
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
				<a id="personal" href="user.jsp">个人中心</a> <a
					href="logout.action">退出登录</a>
			</div>
			<a class="qh l-r" href="../index.jsp"></a>
			<div class="xxts l-r">
				<a class="grzx-tzxx">信息（<span id="messageNum"></span>）</a>
			</div>
		</div>
		<div class="clear"></div>
		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：header区域结束
       		 -->
		<div class="clear"></div>
		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：个人中心开始
       		 -->
		<div class="grzl-title"></div>
		<div class="xgmm-title"></div>
		<div class="tzzx-title"></div>
		<div class="clear"></div>
		<div class="grzx l-f">
			<ul>
				<li class="grzx-grzl user-bg"><span class="blus">个人资料</span></li>
				<li class="grzx-tzxx"><span>通知中心</span></li>
				<li class="grzx-xgmm"><span>修改密码</span></li>
				<li><span id="tcxt">退出登录</span></li>
			</ul>
		</div>
		<!--
                	作者：李诚
                	时间：2015-02-03
                	描述：个人资料
                -->
		<a href="index.jsp" class="fhsy"></a>
		<div class="grzl user-bg l-f">
			<div class="yhtx"></div>
			<div class="content l-f">
				<s:if test="#session.jg!=null">
					<span class="xm l-f"><s:property value="#session.jg.ZSXM" /></span>
					<span class="zc l-r">职称：<s:property value="#session.jg.JGMC" /></span>
				</s:if>
				<s:else>
					<span class="xm l-f">无</span>
					<span class="zc l-r">职称：无</span>
				</s:else>
				<div class="clear"></div>
				<!--判断session中存放的对象是否为空 -->
				<s:if test="#session.jg!=null">
					<ul class="l-f">
						<li><span>姓名：</span><span><s:property
									value="#session.jg.ZSXM" /></span></li>
						<li><span>性别：</span><span><s:property
									value="#session.jg.XB" /></span></li>
						<li><span>出生年月：</span><span><s:property
									value="#session.jg.CSNY" /></span></li>
						<li><span>身份证号码：</span><span><s:property
									value="#session.jg.SFZHM" /></span></li>
						<li><span>毕业学校：</span><span><s:property
									value="#session.jg.BYYX" /></span></li>
						<li><span>文化程度：</span><span><s:property
									value="#session.jg.XUELI" /></span></li>
						<li><span>目前就职：</span><span></span></li>
					</ul>
				</s:if>
				<s:else>
					<ul class="l-f">
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
                	时间：2015-02-03
                	描述：通知中心
                -->
		<div class="tzzx user-bg l-f">
		<table id="dg" style="width:780px;height:310px;"></table>
			<%-- <s:hidden name="pages" id="currPage" />
			<table class="tzzx-content">
						<s:iterator value="messageList">
					<tr>
						<!-- 未读 -->
						<s:if test="readStatus==0">
						<td class="content"><a onclick="getText(this);" class="wd"><s:property value="text" /></a></td> 
						</s:if>
						<s:if test="readStatus==1">
						<td class="content"><a onclick="getText(this);" class="yd"><s:property value="text" /></a></td> 
						</s:if>
						<td id="releaseTime"><span><s:property value="releaseTime" /></span></td>
						<input type="hidden" value="<s:property value="megId"/>" id="megId" />
						<input type="hidden" value="<s:property value="megType"/>"	id="megType" />
						<input type="hidden" value="<s:property value="readStatus"/>" id="readStatus" />
					</tr>
				</s:iterator>
			</table>
			<!--分页-->
			<div>
				<s:if test="pages==1">
					<a href="#" class="uppage l-f"></a>
				</s:if>
				<s:else>
					<a href="javascript:toPage(<s:property value='pages-1'/>)"><a  class="uppage l-r"></a></a>
				</s:else>
				<!-- 页码 -->
				<s:iterator begin="1" end="totalPages" var="p">
					<s:if test="#p==pages">
						<a href="javascript:toPage(<s:property/>)" class="current_page  l-f"><s:property /></a>
					</s:if>
					<s:else>
						<a href="javascript:toPage(<s:property/>)" class="current_page l-f"><s:property /></a>
					</s:else>
				</s:iterator>

				<s:if test="pages==totalPages"><a href="#" class="next l-f"></a></s:if>
				<s:else>
					<a href="javascript:toPage(<s:property value='pages+1'/>)" class="next l-f"></a>
				</s:else>
			</div> --%>
		</div>
		<!--
                	作者：李诚
                	时间：2015-02-03
                	描述：修改密码
                -->
		<form id="modifyPwdForm" method="post" class="xgmm user-bg l-f">
			<table>
				<tr>
					<td class="title"><span>原密码：</span></td>
					<td class="title"><input type="password" id="oldPwd"
						onblur="check();" /></td>
				</tr>
				<tr>
					<td class="title"><span>新密码：</span></td>
					<td><input type="password" id="newPwd" name="yh_yhmm" /></td>
				</tr>
				<tr>
					<td class="title"><span>确定新密码：</span></td>
					<td><input type="password" id="reNewPwd" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<!-- <button id="modifyBtn" onclick="updatePwd()">修改</button> -->
						<input type="button" value="修改" id="modifyBtn"></input>
					</td>
				</tr>
			</table>
			<!-- 用户组id -->
			<input type="hidden" name="yhz_id" id="yhz_id" />
			<!-- 用户名称 -->
			<input type="hidden" name="yh_yhmc" id="yhmc" />
			<!-- 用户id -->
			<input type="hidden" name="yh_id" id="yhid" />
			<!-- 状态标志 -->
			<input type="hidden" name="optionflag" id="updateyh" />
		</form>

		<!--
        	作者：李诚
        	时间：2015-01-27
        	描述：个人中心结束
       		 -->
		<!--
              	作者：李诚
              	时间：2015-01-28
              	描述：footer
              -->
		<div class="clear"></div>
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
</body>
</html>