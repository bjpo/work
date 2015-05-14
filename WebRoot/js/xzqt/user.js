/*
 * 查看选中的文本消息
 * @param node 选中的记录对象
 */
function rowText(node) {
	var text=$(node).parent().parent().siblings("td[field='text']").text();
	var megId=$(node).attr("value");
	var megType=$(node).parent().parent().siblings("td[field='megType']").text();
		// 判断消息读取的状态
		if ("normal" == megType) {
			var href = "updateMessage.action?megId=" +megId
										+ "&readStatu=1" 
										+ "&megType=" + megType 
										+ "&nowTime="+ Math.random();
			$.messager.alert("我的消息",text);
			updateStatu(href);
		}
		if ("all" ==megType) {
			$.messager.alert("我的消息", text);
			var href = "updateMessage.action?megId=" + megId
											+ "&readStatu=1" 
											+ "&megType=" + megType 
											+ "&nowTime="+ Math.random();
			updateStatu(href);
		}
}

//初始化
$(function() {
	/* 校长前台user.jsp下全校出勤 */
	$.ajax({
		url : "listQUANXIAO1.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			$("#zc").append(obj.zcPercen);// 正常出席人数百分比
			$("#cd").append(obj.cdPercen);// 迟到百分比
			$("#zt").append(obj.ztPercen);// 早退百分比
			$("#qx").append(obj.qxPercen);// 缺席百分比
		}
	});
	var liNode=$("[class='grzx l-f'] ul li");
	for ( var i = 0; i < liNode.length; i++) {
		liNode[i].onclick=function(){
			//重新加载当前页面
			reload();
		}
	}
	
	//初始化消息表格
	$("#dg").datagrid({
						url : "getAllMessage.action?sort=readStatus&order=asc&nowTime="+Math.random(),
						idField : "megId",
						fitColumns : true,
						border : false,
						pagination : true,
						pageSize: 5, 
						pageList: [5, 10, 15, 20, 25], // 可以设置每页记录条数的列表
						pagePosition:'bottom',
						showHeader : false,
						singleSelect : true,
						backtground : "none",
						selectOnCheck:false,
						columns : [ [ {
							field : "text",
							align : "center",
							fit : true
						}, {
							field : "megId",
							width : 10,
							formatter : function(value, rowData, rowIndex) {
								return "<a onclick='rowText(this);' value='"+value+"'>查看</a><input type='hidden' value='"+value+"'>";
							}
						}, {
							field : "releaseTime",
							align : "center",
							width : 15
						}, {
							field : "megType",
							hidden : true,
							fit : true
						}, {
							field : "readStatus",
							hidden : true,
							fit : true
						} ] ],
						onLoadSuccess : function() {
							$.each($(".datagrid-row"),function(i, n) {var t = $("#datagrid-row-r1-2-"+ i+ " td[field='readStatus'] div").text();
												if (t == "0") {
													$("#datagrid-row-r1-2-" + i).addClass("blod");
												}
											});
						}
					});
	$('#dg').datagrid('getPager').pagination({
		layout:['first','prev','links','next','last'],
		displayMsg:''
	});
	// 为数据表格添加样式，使其border变透明
	$("#dg").datagrid("getPanel").removeClass("lines-both lines-no lines-right lines-bottom")
								.addClass("lines-no")
								.addClass("datagrid-header");
// 改变消息头显示的长度
	$(".datagrid-btable").addClass("datagrid-row");
	
	/*个人中心效果*/
	$("#user").click(function() {
		$(".tool").fadeToggle("slow");
	});

	/*该函数删除*/
	$(".pzbc").mousedown(function() {
		$(".pzbc").css({
			"background" : "url(../img/xz/btn-pzbc-click.png)"
		});
		$(".pzbc").mouseup(function() {
			$(".pzbc").css({
				"background" : "url(../img/xz/btn-pzbc.png)"
			});
		});
	});
	
	/*下一页效果*/
	$(".next").mousedown(function() {
		$(this).css({
			"background" : "url(../img/xz/next-click.png)"
		});
		$(this).mouseup(function() {
			$(this).css({
				"background" : "url(../img/xz/next.png)"
			});
		});
	});
	
	/*上一页效果*/
	$(".uppage").mousedown(function() {
		$(this).css({
			"background" : "url(../img/xz/uppage-click.png)"
		});
		$(this).mouseup(function() {
			$(this).css({
				"background" : "url(../img/xz/uppage.png)"
			});
		});
	});
	/*个人资料效果*/
	$(".grzx-grzl").click(function() {
		$(".grzx ul li").removeClass("user-bg");
		$(".grzx ul li span").removeClass("blus");
		$(".grzx-grzl").addClass("user-bg");
		$(".grzx-grzl span").addClass("blus");
		$(".grzl").css({
			display : "block"
		});
		$(".tzzx").css({
			display : "none"
		});
		$(".xgmm").css({
			display : "none"
		});
		$(".grzl-title").css({
			display : "block"
		});
		$(".tzzx-title").css({
			display : "none"
		});
		$(".xgmm-title").css({
			display : "none"
		});
	});
	
	/*通知中心效果*/
	$(".grzx-tzxx").click(function() {
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
	
	/*修改密码效果*/
	$(".grzx-xgmm").click(function() {
		$(".grzx ul li").removeClass("user-bg");
		$(".grzx ul li span").removeClass("blus");
		$(".grzx-xgmm").addClass("user-bg");
		$(".grzx-xgmm span").addClass("blus");
		$(".xgmm").css({
			display : "block"
		});
		$(".grzl").css({
			display : "none"
		});
		$(".tzzx").css({
			display : "none"
		});
		$(".xgmm-title").css({
			display : "block"
		});
		$(".grzl-title").css({
			display : "none"
		});
		$(".tzzx-title").css({
			display : "none"
		});
	});
	
	/*这个两个方法重复删除*/
	$(".next").mousedown(function() {
		$(this).css({
			"background" : "url(../img/xz/next-click.png)"
		});
		$(this).mouseup(function() {
			$(this).css({
				"background" : "url(../img/xz/next.png)"
			});
		});
	});
	

	/*
	 * 个人资料中的退出登录
	 */
	$("#tcxt").click(function() {
		$.messager.confirm('提示', '确定退出登录吗?', function(r) {
			if (r) {
				window.location.href = "logout.action";
			}
		});
	});
	/*
	 * 修改密码
	 */
	$("#modifyBtn").click(function() {
		updatePwd();
	});
	$.ajax({
		url :"updateMessage.action",
		success : function(data) {
			var obj=eval("("+data+")");
			$("#messageNum").html(obj.messageNum);
			reload();
		}
	});
});
//显示消息内容
function getText(a) {
	var text=$(a).text();
	var megId=$(a).parent().siblings("input[id='megId']").attr("value");
	var megType=$(a).parent().siblings("input[id='megType']").attr("value");
	var megReadStatu=$(a).parent().siblings("input[id='readStatus']").attr("value");
		// 判断消息读取的状态
		if ("normal" == megType) {
			var href = "updateMessage.action?megId=" +megId
					+ "&readStatu=1" + "&megType=" + megType + "&nowTime="
					+ Math.random();
			$.messager.alert("我的消息",text);
			updateStatu(href);
		}
		if ("all" ==megType) {
			$.messager.alert("我的消息", text);
			var href = "updateMessage.action?megId=" + megId
					+ "&readStatu=1" + "&megType=" + megType + "&nowTime="
					+ Math.random();
			updateStatu(href);
		}
}

function updateStatu(methods) {
	$.ajax({
		url : methods,
		success : function(data) {
			var obj=eval("("+data+")");
			$("#messageNum").html(obj.messageNum);
			reload();
		}
	});
}

//刷新table
function reload(){
	//重新加载当前页面
	$("#dg").datagrid('reload');
}
