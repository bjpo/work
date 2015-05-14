//初始化
$(function() {
	//校长前台 查询条件下拉列表
	$("#cxtj").combobox({
		url : "listConditionsConfiguration.action",
		width : 453,
		height : 30,
		editable:false,
		valueField : 'id',
		textField : 'queryTitle'
	});
	// 校长前台学院统计 自动将前3个学院显示出来
	$.ajax({
		url : "listAllXUEYUAN.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 改变校长前台首页左侧条形图数据
			xyBar(obj);
		}
	});

	/* 校长前台index.jsp下全校出勤 */
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
	/* 个人中心效果 */
	$("#user").click(function() {
		$(".tool").fadeToggle("slow");

	});

	/* 查询按钮效果 */
	$(".cx-btn").mousedown(function() {
		$(".cx-btn").stop().css({
			"background" : "url(../img/xz/cx-btn-click.png)"
		});
		$(".cx-btn").mouseup(function() {
			$(".cx-btn").stop().css({
				"background" : "url(../img/xz/cx-btn.png)"
			});
		});
	});

	/* 搜索框下拉效果效果 */
	$("#xlcd").mouseenter(function() {
		$("#xlcd").css({
			"background" : "url(../img/xz/cx-xl-click.png)"
		});
		$(".menu").stop().slideDown("slow");
		$("#xlcd").mouseleave(function() {
			$("#xlcd").css({
				"background" : "url(../img/xz/cx-xl.png)"
			});
			$(".menu").stop().slideUp("slow");
		});
	});
	// 存放用户选择的学院名称
	var strName = new Array();
	/* 统计按钮效果 */
	$(".tj-btn").mousedown(function() {
		//手动选择学院
		manuallySelectedXY();
		$(".tj-btn").stop().css({
			"background" : "url(../img/xz/tj-btn-click.png)"
		});
		$(".tj-btn").mouseup(function() {
			$(".tj-btn").stop().css({
				"background" : "url(../img/xz/tj-btn.png)"
			});
		});
	});

	/* 统计下拉菜单效果 */
	$(".tj-menu").mouseenter(
			function() {
				// 当鼠标放上时请求方法
				$.ajax({
					url : "listAllXUEYUAN.action",
					success : function(data) {
						var obj = eval("(" + data + ")");
						// 遍历获取的数据，并将其插入到id为xy的ul下
						$.each(obj, function(i, n) {
							$("#xy").append(
									"<li> <input type='checkbox' name='items' value='"
											+ obj[i].xymc + "'/>" + obj[i].xymc
											+ "<input type='hidden' value='"
											+ obj[i].xyid + "'/></li>");
						});
						// 判断用户选择的学院的个数是否是3个
						var items = document.getElementsByName("items");
						for ( var i = 0; i < items.length; i++) {
							items[i].onclick = function() {
								// 记录有多少个 items 被选中了
								var number = 0;
								for ( var j = 0; j < i; j++) {
									if (items[j].checked) {
										number++;
										// 判断用户选择的数据的个数是否大于3
										if (number > 3) {
											// 如果大于3个就不让其在选择
											items[j].checked = false;
										}
									}
								}
							}
						}
					}
				});
				$(".tj-menu .content").stop().fadeIn("slow");
				$(".tj-menu").mouseleave(function() {
					$("#xy").empty();
					$(".tj-menu .content").stop().fadeOut("slow");
				});
			});
	
	/* 选框效果 */
	$(".tjxk").click(function() {
		if ($(this).hasClass("tjxk-click")) {
			$(this).removeClass("tjxk-click");
		} else {
			$(this).addClass("tjxk-click");
		}
	});
	$("#message").click(function() {
		window.location.href = "user.jsp?name=tzzx"
	});

	$.ajax({
		url : "updateMessage.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			$("#messageNum").html(obj.messageNum);
			// reload();
		}
	});
	//调用饼图并获取数据
	dataGraph();
});

//点击查询按钮时触发此事件
function query() {
	$.ajax({
		url : "getDataGraph.action",
		data : "con_id=" + $("#cxtj").combobox("getValue"),
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 进行判断数据正常出席，早退，迟到，缺席
			if (obj.ZCCXRS == 0 && obj.CDRS == 0 && obj.ZTRS == 0
					&& obj.QXRS == 0) {
				dataGraph();
			} else {
				var result = [ {
					value : obj.ZCCXRS,
					name : '出席',
					itemStyle : {
						normal : {
							color : '#277ebc'
						}
					}
				}, {
					value : obj.CDRS,
					name : '迟到',
					itemStyle : {
						normal : {
							color : '#091a6d'
						}
					}
				}, {
					value : obj.ZTRS,
					name : '早退',
					itemStyle : {
						normal : {
							color : '#e7908e'
						}
					}
				}, {
					value : obj.QXRS,
					name : '缺席',
					itemStyle : {
						normal : {
							color : '#830300'
						}
					}
				} ];
				var myChart = echarts.init(document.getElementById('main'));
				var option = {
					legend : {
						orient : 'horizontal',
						y : '20px',
						x : 'right',
						data : [ '出席', '迟到', '早退', '缺席' ]
					},
					series : [ {
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : result
					} ]
				};
				// 将生成数据图放入到页面中
				myChart.setOption(option);
			}
		}
	});
}

// 饼图
function dataGraph() {
	/*
	 * 校长前台index.jsp中右侧图
	 */
	var myChart = echarts.init(document.getElementById('main'));
	var option = {
		legend : {
			orient : 'horizontal',
			y : '20px',
			x : 'right',
			data : [ '无查询数据' ]
		},
		series : [ {
			type : 'pie',
			radius : '55%',
			center : [ '50%' ],
			data : [ {
				value : 135,
				name : '无查询数据',
				itemStyle : {
					normal : {
						color : '#830400'
					}
				}
			}, ]
		} ]
	};
	// 将生成数据图放入到页面中
	myChart.setOption(option);
}

// 学院统计条形图
function xyBar(obj) {
	// 遍历获取的数据，并将其插入到id为xy的ul下
	$.each(obj, function(i, n) {
		// 判断是否是前3个学院
		if (i < 3) {
			$("#tj_" + (i + 1)).append(obj[i].xymc);
			// 请求前3个学院统计数据
			$.ajax({
				url : "listXUEYUANTONGJI.action",
				data : "XY_ID=" + obj[i].xyid,
				success : function(data) {
					var xyData = eval("(" + data + ")");
					/*
					 * 判断早退，迟到，出席，正常是否为空 如果为空，则对显示数据的条形进行隐藏
					 */
					if (xyData.rows[0].CDRS == "无记录"
							&& xyData.rows[0].QXRS == "无记录"
							&& xyData.rows[0].ZCCXRS == "无记录"
							&& xyData.rows[0].ZTRS == "无记录") {
						$("#data_" + i).css({
							display : "none"
						});// 无数据时，将条形图进行隐藏
						$("#ycnr_" + i).css({
							display : "block"
						});// 无数据时，显示无数据的条形图
					} else {
						$("#data_" + i).css({
							display : "block"
						});// 有数据时，将条形图进行显示
						$("#ycnr_" + i).css({
							display : "none"
						});// 有数据时，不显示无数的条形图
						// 根据数据来设置条形图
						$("#cxBar_" + i).css({
							width : xyData.rows[0].ZCCXRS
						});
						$("#cdBar_" + i).css({
							width : xyData.rows[0].CDRS
						});
						$("#ztBar_" + i).css({
							width : xyData.rows[0].ZTRS
						});
						$("#qxBar_" + i).css({
							width : xyData.rows[0].QXRS
						});
						// 设置条形图下显示的数据
						$("#cx_" + i).append(xyData.rows[0].ZCCXRS);// 正常出席人数百分比
						$("#cd_" + i).append(xyData.rows[0].CDRS);// 迟到百分比
						$("#zt_" + i).append(xyData.rows[0].ZTRS);// 早退百分比
						$("#qx_" + i).append(xyData.rows[0].QXRS);// 缺席百分比
					}
				}
			});
		}
	});
}

// 手动选中三个学院
function manuallySelectedXY() {
	// 获取选中学院数据对象
	var items = $("li input:checkbox:checked");
	//进行判断那几个条形图进行
	switch (items.length) {
	case 1:
		$("#content_1").css({display:"none"});
		$("#content_2").css({display:"none"});
		break;
	case 2:
		$("#content_2").css({display:"none"});
		break;
	default:
		$("#content_0").css({display:"block"});
		$("#content_1").css({display:"block"});
		$("#content_2").css({display:"block"});
	}
	// 遍历对象获取学院名称
	$.each(items, function(i, n) {
		// 判断是否是前3个学院
		if (i < 3) {
			$("#tj_" + (i + 1)).empty();
			$("#tj_" + (i + 1)).append(items.eq(i).val());
			// 请求前3个学院统计数据
			$.ajax({
				url : "listXUEYUANTONGJI.action",
				data : "XY_ID=" + items.eq(i).siblings().val(),
				success : function(data) {
					var xyData = eval("(" + data + ")");
					/*
					 * 判断早退，迟到，出席，正常是否为空 如果为空，则对显示数据的条形进行隐藏
					 */
					if (xyData.rows[0].CDRS == "无记录"
							&& xyData.rows[0].QXRS == "无记录"
							&& xyData.rows[0].ZCCXRS == "无记录"
							&& xyData.rows[0].ZTRS == "无记录") {
						$("#data_" + i).css({
							display : "none"
						});// 无数据时，将条形图进行隐藏
						$("#ycnr_" + i).css({
							display : "block"
						});// 无数据时，显示无数据的条形图
					} else {
						$("#data_" + i).css({
							display : "block"
						});// 有数据时，将条形图进行显示
						$("#ycnr_" + i).css({
							display : "none"
						});// 有数据时，不显示无数的条形图
						// 根据数据来设置条形图的数据显示
						$("#cxBar_" + i).css({
							width : xyData.rows[0].ZCCXRS
						});
						$("#cdBar_" + i).css({
							width : xyData.rows[0].CDRS
						});
						$("#ztBar_" + i).css({
							width : xyData.rows[0].ZTRS
						});
						$("#qxBar_" + i).css({
							width : xyData.rows[0].QXRS
						});
						// 设置条形图下显示的数据
						$("#cx_" + i).empty().append(xyData.rows[0].ZCCXRS);// 正常出席人数百分比
						$("#cd_" + i).empty().append(xyData.rows[0].CDRS);// 迟到百分比
						$("#zt_" + i).empty().append(xyData.rows[0].ZTRS);// 早退百分比
						$("#qx_" + i).empty().append(xyData.rows[0].QXRS);// 缺席百分比
					}
				}
			});
		}
	});
}