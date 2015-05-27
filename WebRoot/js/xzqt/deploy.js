var nj_id = "";// 记录年级id
var nj_name = "";// 记录选中年级名称
var zy_id = "";// 记录专业id
var zy_name = "";// 记录选中专业名称
var bj_id = "";// 记录班级id
var bj_name = "";// 记录选中班级名称
var xy_id = "";// 记录学院id
var xy_name = "";// 记录选中的学院名称
var arr = new Array();// 用于存储要删除查询条件的id
// 异步分页
function toPage(page) {
	document.getElementById("currPage").value = page;
	$("#pz_xy").empty();
	getXY(page);
}

// 初始化
$(function() {
	/* 个人中心菜单动态效果 */
	$("#user").click(function() {
		$(".tool").fadeToggle("slow");
	});

	/* 配置页中中部内容下的保存按钮 */
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

	/* 下一页效果 */
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

	/* 上一页效果 */
	$(".uppage").mousedown(function() {
		$(this).css({
			background : "url(../img/xz/uppage-click.png)"
		});
		$(this).mouseup(function() {
			$(this).css({
				background : "url(../img/xz/uppage.png)"
			});
		});
	});

	/* 选框一效果 */
	$(".tjxk").click(function() {
		if ($(this).hasClass("tjxk-click")) {
			$(this).removeClass("tjxk-click");
		} else {
			$(this).addClass("tjxk-click");
		}
	});

	/* 选框二效果 */
	$(".xk").click(function() {
		if ($(this).hasClass("xk-click")) {
			$(this).removeClass("xk-click");
		} else {
			$(this).addClass("xk-click");
		}
	});

	/* 编辑效果 */
	$(".bj").click(function() {
		// 点击编辑时，让checkbox进行显示
		$("[name='pztj_items']").css({
			display : "block"
		});

		$(".bj").css({
			display : "none"
		});
		$(".bc").css({
			display : "block"
		});
		$(".sc").css({
			display : "block"
		});
		$(".xq").css({
			display : "block"
		});
		$(".bcpz-bg ul li div").css({
			display : "block"
		});
	});

	/* 保存效果 */
	$(".bc").click(function() {
		// 点击“保存”按钮后对checkbox进行隐藏
		$("[name='pztj_items']").css({
			display : "none"
		});
		$(".bc").css({
			display : "none"
		});
		$(".bj").css({
			display : "block"
		});
		$(".sc").css({
			display : "none"
		});
		$(".xq").css({
			display : "block"
		});
		$(".bcpz-bg ul li div").css({
			display : "none"
		});
	});

	/* 删除按钮效果 */
	$(".sc").mousedown(function() {
		// 判断用户选择的学院的个数是否是1个
		var pztjitems = $("[name='pztj_items']:checked");
		// 判断对象是否为空
		if (pztjitems != null && pztjitems != "") {
			$.each(pztjitems, function(i, n) {
				arr.push($(this).siblings().val());// 将id放入到数组
			});
			// 请求删除
			$.ajax({
				url : "conditionsConfigurationDel.action",
				data : "CondId=" + arr,
				success : function(data) {
					$("#pztj").empty();
					var obj = eval("(" + data + ")");
					$.ajax({
						url : "listConditionsConfiguration.action",
						success : function(data) {
							var obj = eval("(" + data + ")");
							// 遍历获取的数据
							$.each(obj, function(i, n) {
								$("#pztj").append(
										"<li> <input type='checkbox' name='pztj_items' value='"
												+ obj[i].queryTitle
												+ "' class='tjxk l-f'/>"
												+ obj[i].queryTitle
												+ "<input type='hidden' value='" + obj[i].id
												+ "'/></li>");
							});
						}
					});
					//将数组变为空
					arr.splice(0,arr.length);
					$.messager.alert("提示", obj.message);
				}
			});
		}

		$(".sc").css({
			"background" : "url(../img/xz/sc-btn-click.png)"
		});
		$(".sc").mouseup(function() {
			$(".sc").css({
				"background" : "url(../img/xz/sc-btn.png)"
			});
		});
	});

	/* 查看详情按钮效果 */
	$(".xq").mousedown(function() {
		$(".xq").css({
			"background" : "url(../img/xz/ckxq-btn.png)"
		});
		$(".xq").mouseup(function() {
			$(".xq").css({
				"background" : "url(../img/xz/ckxq-btn-click.png)"
			});
		});
	});

	/* 点击单条数据效果 */
	$(".bcpz-bg ul li").click(function() {
		$(".bcpz-bg ul li").removeClass("bxx");
		$(this).addClass("bxx");
	});

	/*
	 * 开始日期下拉菜单效果 $(".ksrq").mouseenter(function() {
	 * $(".ksrq-bk").stop().slideDown("slow"); $(".ksrq").mouseleave(function() {
	 * $(".ksrq-bk").stop().slideUp("slow"); }); });
	 * 
	 * 结束日期下拉菜单效果 $(".jsrq").mouseenter(function() {
	 * $(".jsrq-bk").stop().slideDown("slow"); $(".jsrq").mouseleave(function() {
	 * $(".jsrq-bk").stop().slideUp("slow"); }); });
	 */
	/* 学期下拉菜单效果 */
	/*
	 * $(".xq-cn").mouseenter(function() { $(".xq-bk").stop().slideDown("slow");
	 * $(".xq-cn").mouseleave(function() { $(".xq-bk").stop().slideUp("slow");
	 * }); });
	 */
	$("#message").click(function() {
		window.location.href = "user.jsp?name=tzzx"
	});
	$.ajax({
		url : "updateMessage.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			$("#messageNum").html(obj.messageNum);
		}
	});

	// 配置页面中的学院数据显示
	getXY(1);
	// 配置条件列表显示
	listConditionsConfiguration();
	/*校长前台deploy.jsp下全校出勤*/
	$.ajax({
		  url: "listQUANXIAO1.action",
		  success: function(data){
			  var obj=eval("("+data+")");
				$("#zc").append(obj.zcPercen);//正常出席人数百分比
				$("#cd").append(obj.cdPercen);//迟到百分比
				$("#zt").append(obj.ztPercen);//早退百分比
				$("#qx").append(obj.qxPercen);//缺席百分比
		  }
		});
});

//获取学院
function getXY(page){
	$.ajax({
		url : "listXueYuan.action",
		data : "rows=5&page="+page,
		success : function(data) {
			var obj = eval("(" + data + ")");
			$("#currPage").val(obj.page);
			$("#currTotalPage").val(obj.totalPages);
			// 遍历获取的数据，并将其插入到id为xy的ul下
			$.each(obj.rows, function(i, n) {
				$("#pz_xy").append(
						"<li> <input type='checkbox' name='xy_items' value='"
								+ obj.rows[i].xymc + "' class='tjxk l-f'/>"
								+ obj.rows[i].xymc
								+ "<input type='hidden' value='"
								+ obj.rows[i].xyid + "'/></li>");
			});
			// 判断用户选择的学院的个数是否是1个
			var items = $("[name='xy_items']");
			for ( var i = 0; i < items.length; i++) {
				items[i].onclick = function() {
					// 记录有多少个 items 被选中了
					var number = 0;
					for ( var j = 0; j < i; j++) {
						// 判断是否选中
						if (items[j].checked) {
							number++;
							// 判断用户选择的数据的个数是否大于3
							if (number > 1) {
								// 如果大于3个就不让其在选择
								items[j].checked = false;
							}
							xy_id = $(this).siblings().val();// 获取选中学院的id
							xy_name = $(this).val();// 获取选中学院的名称
							getZY($(this).siblings().val());
						} else {
							$("#pz_zy").empty();
							$("#pz_nj").empty();
							$("#pz_bj").empty();
						}
					}
				}
			}
		}
	});
}
// 获取相应学院的专业
function getZY(id) {
	$.ajax({
		url : "listAllZHUANYE.action",
		data : "XYID=" + id,
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 遍历获取的数据，并将其插入到id为xy的ul下
			$.each(obj, function(i, n) {
				$("#pz_zy").append(
						"<li> <input type='checkbox' name='zy_items' value='"
								+ obj[i].ZYMC + "' class='tjxk l-f'/>"
								+ obj[i].ZYMC + "<input type='hidden' value='"
								+ obj[i].ZY_ID + "'/></li>");
			});
			// 判断用户选择的学院的个数是否是1个
			var zyitems = $("[name='zy_items']");
			for ( var i = 0; i < zyitems.length; i++) {
				// 判断专业是否被选中
				zyitems[i].onclick = function() {
					// 记录有多少个 items 被选中了
					var number = 0;
					for ( var j = 0; j < i; j++) {
						if (zyitems[j].checked) {
							number++;
							// 判断用户选择的数据的个数是否大于3
							if (number > 1) {
								// 如果大于3个就不让其在选择
								zyitems[j].checked = false;
								$("#pz_nj").empty();
							} else {
								// 记录专业id
								zy_id = $(this).siblings().val();
								// 记录专业名称
								zy_name = $(this).val();
								getNJ();// 获取所有的年级
							}
						} else {
							$("#pz_nj").empty();
						}
					}
				}
			}
		}
	});
}
// 获取相应学院的专业年级
function getNJ() {
	$.ajax({
		url : "listAllNIANJI.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 遍历获取的数据，并将其插入到id为xy的ul下
			$.each(obj, function(i, n) {
				$("#pz_nj").append(
						"<li> <input type='checkbox' name='nj_items' value='"
								+ obj[i].NJMC + "' class='tjxk l-f'/>"
								+ obj[i].NJMC + "<input type='hidden' value='"
								+ obj[i].NJ_ID + "'/></li>");
			});
			// 判断用户选择的学院的个数是否是1个
			var njitems = $("[name='nj_items']");
			// 遍历年级集合的个数
			for ( var i = 0; i < njitems.length; i++) {
				njitems[i].onclick = function() {
					// 记录有多少个 items 被选中了
					var number = 0;
					for ( var j = 0; j < i; j++) {
						// 判断年级是否被选中
						if (njitems[j].checked) {
							number++;
							// 判断用户选择的数据的个数是否大于3
							if (number > 1) {
								// 如果大于3个就不让其在选择
								njitems[j].checked = false;
							} else {
								// 记录年级id
								nj_id = $(this).siblings().val();
								// 记录年级名称
								nj_name = $(this).val();
								getBJ();// 获取所有的班级
							}
						}else{
							$("#pz_bj").empty();
						}
					}
				}
			}
		}
	});
}
// 获取相应学院的专业年级的班级
function getBJ() {
	$.ajax({
		url : "listAllBANJI.action",
		data : "NJ_ID=" + nj_id + "&ZY_ID=" + zy_id,
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 遍历获取的数据，并将其插入到id为xy的ul下
			$.each(obj, function(i, n) {
				$("#pz_bj").append(
						"<li> <input type='checkbox' name='bj_items' value='"
								+ obj[i].BJMC + "' class='tjxk l-f'/>"
								+ obj[i].BJMC + "<input type='hidden' value='"
								+ obj[i].BJ_ID + "'/></li>");
			});
			// 判断用户选择的学院的个数是否是1个
			var bjitems = $("[name='bj_items']");
			for ( var i = 0; i < bjitems.length; i++) {
				bjitems[i].onclick = function() {
					// 记录有多少个 items 被选中了
					var number = 0;
					for ( var j = 0; j < i; j++) {
						// 判断班级是否被选中
						if (bjitems[j].checked) {
							// 记录选中的个数
							number++;
							// 判断用户选择的数据的个数是否大于3
							if (number > 1) {
								// 如果大于3个就不让其在选择
								bjitems[j].checked = false;
							} else {
								// 获取班级id
								bj_id = $(this).siblings().val();
								// 获取班级名称
								bj_name = $(this).val();
							}
						}
					}
				}
			}
		}
	});
}
// 配置页面中的条件保存
function pzbc() {
	// 存放拼接的查询标题
	var queryTitle = "";
	// 判断学院名称是否为空
	if (xy_name != "") {
		queryTitle += xy_name;
	}
	// 判断专业名称是否为空
	if (zy_name != "") {
		queryTitle += " +" + zy_name;
	}
	// 判断年级名称是否为空
	if (nj_name != "") {
		queryTitle += " +" + nj_name;
	}
	// 判断班级名称是否为空
	if (bj_name != "") {
		queryTitle += " +" + bj_name;
	}
	// 判断学期名称是否为空
	if ($("#xq").combobox('getText') != "") {
		queryTitle += $("#xq").combobox('getText');
	}
	// 判断开始日期是否为空
	if ($("#kstime").val() != "") {
		queryTitle += " +" + $("#kstime").val();
	}
	// 判断结束日期是否为空
	if ($("#jstime").val() != "") {
		queryTitle += " +" + $("#jstime").val();
	}
    if (xy_id.length === 0)
    {
        $.messager.alert("提示", "请选择学院");
        return false;
    }
	$.ajax({
		url : "conditionsConfigurationSave.action",
		data : "KSRQ=" + $("#kstime").val() + "&JSRQ=" + $("#jstime").val()
				+ "&XUEQI_ID=" + $("#xq").combobox('getValue') + "&XY_ID="
				+ xy_id + "&NJ_ID=" + nj_id + "&BJ_ID=" + bj_id + "&ZY_ID="
				+ zy_id + "&queryTitle=" + encodeURI(encodeURI(queryTitle)),
		success : function(data) {
			var obj = eval("(" + data + ")");
			$.messager.alert("提示", obj.message);
            $("#pztj > li").remove();
            listConditionsConfiguration();	
		}
	});
}

// 获取配置列表
function listConditionsConfiguration() {
	$.ajax({
		url : "listConditionsConfiguration.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 遍历获取的数据
			$.each(obj, function(i, n) {
				$("#pztj").append(
						"<li onmouseover='showTitle(this);' onmouseout='hideTopic();'><input type='checkbox' name='pztj_items' value='"
								+ obj[i].queryTitle
								+ "' class='tjxk l-f' style='display:none''/>"
								+ obj[i].queryTitle
								+ "<input type='hidden' value='" + obj[i].id
								+ "'/></li>");
			});
		}
	});
}

//定时属性
var g_oTimerHide = null;
//显示查询条件标题
function  showTitle(i){
	var oEvent = window.event || ev;
	var oTopic = document.getElementById('topic');
	if (g_oTimerHide) {
		clearTimeout(g_oTimerHide);
	}
	$('.inner_html').append($(i).children("input").eq(0).attr("value"));
	oTopic.style.display = 'block';
	setPosition(oEvent.clientX, oEvent.clientY);
}

//隐藏标题
function hideTopic() {
	$(".inner_html").empty();
	var oTopic = document.getElementById('topic');
	if (g_oTimerHide) {
		clearTimeout(g_oTimerHide);
	}
	g_oTimerHide = setTimeout(
		function() {
			oTopic.style.display = 'none';
		}, 50
	);
}

//获取鼠标位置
function setPosition(x, y) {
	var top = document.body.scrollTop || document.documentElement.scrollTop;
	var left = document.body.scrollLeft || document.documentElement.scrollLeft;
	x += left;
	y += top;
	var oTopic = document.getElementById('topic');
	var l = x + 20;
	var t = y - (oTopic.offsetHeight - 20);
	var bRight = true;
	var iPageRight = left + document.documentElement.clientWidth;
	if (l + oTopic.offsetWidth > iPageRight) {
		bRight = false;
		l = x - (oTopic.offsetWidth + 20);
		oTopic.getElementsByTagName('div')[0].className = 'adorn_r';
	} else {
		oTopic.getElementsByTagName('div')[0].className = 'adorn';
	}
	oTopic.style.left = l + 'px';
	oTopic.style.top = t + 'px';
}