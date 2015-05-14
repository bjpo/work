<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>My JSP 'modifyPWD.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 引入公共文件 -->
<jsp:include page="../common/include.jsp"></jsp:include>
<script type="text/javascript">
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
/*
			if ($("#newPwd").val() == "") {
				$.messager.alert('提示', '新密码不能空');
				return;
			}
			if ($("#newPwd").val() != $("#reNewPwd").val()) {
				$.messager.alert('提示', '两次输入的密码不一致');
				return;
			}
			*/
		}

	}
</script>
</head>
<body>
<script type="text/javascript"	src="<%=basePath%>js/modifypwd/modifypwd.js"></script>
<div style="margin:50px 50px;">
				<div style="margin:0 0 3px 48px;">
				<span>用户名：</span>
				<input type="text" id="user" value="${yonghu.yhm}" /></div>
	<form id="modifyPwdForm" method="post">

		<table class="ssk" >
			<tr>
				<td class="title"><span>原密码：</span></td>
				<td><input type="text" id="oldPwd" onblur="check();" /><font color="red">&nbsp;&nbsp;*</font></td>
			</tr>
			<tr>
				<td class="title">新密码：</td>
				<td><input type="text" id="newPwd" name="yh_yhmm" /><font color="red">&nbsp;&nbsp;*</font></td>
			</tr>
			<tr>
				<td>再次输入新密码：</td>
				<td><input type="text" id="reNewPwd"  /><font color="red">&nbsp;&nbsp;*</font></td>
			</tr>
			<tr>
				<td></td>
				<td><a class="easyui-linkbutton" onclick="updatePwd();">提交</a>
					<a class="easyui-linkbutton" onclick="clearForm();">重置</a></td>
			</tr>
		</table>
		<!-- 用户组id -->
		<input type="hidden" name="yhz_id" id="yhz_id">
		<!-- 用户名称 -->
		 <input	type="hidden" name="yh_yhmc" id="yhmc" />
		 <!-- 用户id --> 
		 <input type="hidden" name="yh_id" id="yhid" />
		 <!-- 状态标志 --> 
		 <input type="hidden" name="optionflag"	id="updateyh" />
	</form>
	</div>
</body>
</html>
