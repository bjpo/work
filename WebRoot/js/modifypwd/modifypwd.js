$(function() {
	/*
	 * 禁用新密码与确认密码输入框
	 */
	$("#newPwd").attr({
		"disabled" : "true"
	});
	$("#reNewPwd").attr({
		"disabled" : "true"
	});
	$("#user").attr({
		"disabled" : "true"
	});
});
/*
 * 修改密码
 */
function updatePwd() {
	$("#modifyPwdForm").form(
			"submit",
			{
				url : 'updateYONGHU.action',
				type : "POST",
				onSubmit : function() {
					// 提交之前进行验证
					if ($("#oldPwd").val() == "") {
						$.messager.alert('提示', '请输入原密码');
						return false;
					}
					if ($("#newPwd").val() == "") {
						$.messager.alert('提示', '请输入新密码');
						return false;
					}
					if ($("#reNewPwd").val() == "") {
						$.messager.alert('提示', '再次输入新密码');
						return false;
					}
					if ($("#newPwd").val() != $("#reNewPwd").val()) {
						$.messager.alert('提示', '两次输入密码不一致！');
						return false;
					}
				},
				success : function(data) {
					var obj = eval('(' + data + ')');
					if (obj.success) {
						$.messager.confirm("提示", "密码修改成功,请用新密码重新登录！",
								function(r) {
									if (r) {
										//window.location.href = "logout.action";
										//在当前窗体打开链接，并替换当前的整个窗体(框架页) 
										window.open("logout.action","_top");
									}
								});
					}

				}
			});
}
/*
 * 重置
 */
function clearForm() {
	$("#modifyPwdForm").form("clear");
}