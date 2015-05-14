/*
 * 异步请求
 */
function updateStatu(methods) {
	$.ajax({
		url : methods,
		success : function() {
			$("#dg").datagrid("reload");
		}
	});
}

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
			var href = "../updateMessage.action?megId=" +megId
					+ "&readStatu=1" + "&megType=" + megType + "&nowTime="
					+ Math.random();
			$.messager.alert("我的消息",text);
			updateStatu(href);
		}
		if ("all" ==megType) {
			$.messager.alert("我的消息", text);
			var href = "../updateMessage.action?megId=" + megId
					+ "&readStatu=1" + "&megType=" + megType + "&nowTime="
					+ Math.random();
			updateStatu(href);
		}
}

/*
 * 初始化
 */
$(function() {
	/* 菜单抽拉盒效果 */
	$(".bg").click(function() {
		$(".gnq").css({
			display : "block"
		});
		$("strong").css({
			display : "inline"
		});
		$(".gnq").animate({
			"right" : "-125px"
		}, 1000);

	});
	$("strong").click(function() {
		$(".gnq").animate({
			"right" : "-425px"
		}, 1000);
	});
	/* 内容区显示效果 */
	$("#wdxx").click(function() {
		$(".grxx").toggle("slow");
		$(".wdxx").css({
			display : "none"
		});
		$(".wdts").css({
			display : "none"
		});

	});
	$("#wdzl").click(function() {
		$(".wdxx").toggle("slow");
		$(".grxx").css({
			display : "none"
		});
		$(".wdts").css({
			display : "none"
		});
	});
	$("#wdtz").click(function() {
		$(".wdts").toggle("slow");
		$(".wdxx").css({
			display : "none"
		});
		$(".grxx").css({
			display : "none"
		});
		//重新加载当前页面
		$("#dg").datagrid('reload');
	});
	$(".next").mousedown(function() {
		$(this).css({
			"background" : "url(../img/next-click.png)"
		});
		$(this).mouseup(function() {
			$(this).css({
				"background" : "url(../img/next.png)"
			});
		});
	});
	$(".uppage").mousedown(function() {
		$(this).css({
			"background" : "url(../img/uppage-click.png)"
		});
		$(this).mouseup(function() {
			$(this).css({
				"background" : "url(../img/uppage.png)"
			});
		});
	});

	
	//初始化消息表格
	$("#dg").datagrid({
						url : "getAllMessage.action?sort=readStatus&order=asc&nowTime="+Math.random(),
						idField : "megId",
						fitColumns : true,
						border : false,
						pagination : true,
						pageSize : 5,
						pageList : [5,10,15,20,25,30],
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
	// 为数据表格添加样式，使其border变透明
	$("#dg").datagrid("getPanel").removeClass("lines-both lines-no lines-right lines-bottom")
								.addClass("lines-no")
								.addClass("datagrid-header");
	// 改变消息头显示的长度
	$(".datagrid-btable").addClass("datagrid-row");
});
