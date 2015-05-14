$(document).ready(function() {
	$(".use").mouseover(function() {
		$("#grxx").stop().fadeIn("slow");

	});
	$(".use").mouseleave(function() {
		$("#grxx").stop().fadeOut("slow");

	});
	$.ajax({
		url : "updateMessage.action?megType=null",
		success : function(data) {
			if(obj==null){

			}else{
				var obj = eval("(" + data + ")");
				$("#megNum").empty().append(obj.messageNum);				
			}
		}
	});
});
// 获取当前系统时间
function GetTime() {
	var now = new Date();
	var str, colorhead, colorfoot;
	var yy = now.getYear();
	if (yy < 1900)
		yy = yy + 1900;
	var MM = now.getMonth() + 1;
	if (MM < 10)
		MM = '0' + MM;
	var dd = now.getDate();
	if (dd < 10)
		dd = '0' + dd;
	var hh = now.getHours();
	if (hh < 10)
		hh = '0' + hh;
	var mm = now.getMinutes();
	if (mm < 10)
		mm = '0' + mm;
	var ss = now.getSeconds();
	if (ss < 10)
		ss = '0' + ss;
	var ww = now.getDay();
	if (ww == 0)
		colorhead = "<font>";
	if (ww > 0 && ww < 6)
		colorhead = "<font>";
	if (ww == 6)
		colorhead = "<font>";
	if (ww == 0)
		ww = "星期日";
	if (ww == 1)
		ww = "星期一";
	if (ww == 2)
		ww = "星期二";
	if (ww == 3)
		ww = "星期三";
	if (ww == 4)
		ww = "星期四";
	if (ww == 5)
		ww = "星期五";
	if (ww == 6)
		ww = "星期六";
	colorfoot = "</font>"
	str = colorhead + yy + "年" + MM + "月" + dd + "日&nbsp;&nbsp;&nbsp;" + hh
			+ ":" + mm + ":" + ss + " " + ww + colorfoot;
	$("#localtime").html(str);
	window.setTimeout("GetTime()", 1000);
}
var centerTabs;
// 初始化
$(function() {
	// 当前系统时间显示
	GetTime();
	// 初始化tab
	centerTabs = $('#centerTabs').tabs({
		border : false,
		fit : true,
		onSelect : function(title) {
		}
	});
});

// 退出当前系统
function loginOut(a) {
	a.href = "logout.action?date=" + new Date().getTime();
}

// 个人中心tab
function perCenter(a) {
	commonTab("grzx/grzx.jsp", a.innerHTML);
}
// 修改密码tab
function changePwd(a) {
	commonTab("modifypwd/modifyPWD.jsp", a.innerHTML);
}
// 我的通知Tab
function myNotice(a) {
	commonTab("grzx/grzx.jsp?name=wdtz", $(a).find("span[id='userNote']")
			.text());
}

/*
 * 公共的Tab方法
 */
function commonTab(href, title) {
	// 判断此tab是否存在
	if (centerTabs.tabs('exists', title)) {
		centerTabs.tabs('select', title);
	} else {
		// 不存在进行添加
		$('#centerTabs')
				.tabs(
						'add',
						{
							title : title,
							content : "<iframe src="
									+ href
									+ " frameborder='0' style='border:0;width:100%;height:99%'/>",
							closable : true
						});
	}
}