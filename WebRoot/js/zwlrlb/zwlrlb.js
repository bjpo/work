/** **************************************************初始化方法********************************* */
$(function() {
	$('#datagrid')
			.datagrid(
					{
						url : 'listZWLRLB.action',
						title : '指纹录入列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'ZWLR_ID',
						columns : [ [
								{
									title : '<b>列表名称</b>',
									field : 'LBMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>指纹录入情况</b>',
									field : 'SFLR',
									sortable : true,
									width : 100,
									hidden:true
								},
								{
									title : '<b>指纹录入时间</b>',
									field : 'ZWLRSJ',
									sortable : true,
									fitColumns : true,
									hidden:true
								},
								{
									title : '<b>列表创建时间</b>',
									field : 'LBCJSJ',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>列表最后修改时间</b>',
									field : 'LASTMODIFYTIME',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>操作</b>',
									field : 'ZWLR_ID',
									fitColumns : true,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateZWLRLB_dialog()'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/>修改</a>"
												+ "|"
												+ "<a style='cursor:hand;' onclick='zwlr_dlg()'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='录入指纹信息' style='border:0px;'/>指纹录入</a>";
									}
								} ] ],
						singleSelect : false,
						// 是否单选
						pagination : true,
						// 分页控件
						rownumbers : true,
						// 行号
						frozenColumns : [ [ {
							field : 'ck',
							checkbox : true
						} ] ]
					});
	$('#datagrid').datagrid('getPager').pagination({
		pageSize : 10,
		// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		// 可以设置每页记录条数的列表
		beforePageText : '第',
		// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});

/**************指纹录入列表展示 模糊查询********/
function doSearch() {
	$('#datagrid').datagrid('load', {
		LBMC : $('#LBMC_serchbar').val(),
		// SFLR : $('#SFLR_serchbar').val(),
		// ZWLRSJ : $('#ZWLRSJ_serchbar').val(),
		// LBCJSJ : $('#LBCJSJ_serchbar').val(),
		optionflag : 'selbylike'
	});
}

/** **************************************打开新增信息对话框**************************************** */
function addZWLRLB_dialog() {
	//获取用户点击右上角的x，触发的事件
    $('#addZWLRLB_dlg').dialog({onClose:function () {
    	$("table tr td div[name='list1'] div").remove();
    	$("table tr td div[name='list2'] div").remove();
    }});
	$('#addZWLRLB_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口

	// 根据班级下拉列表框选择的条件进行查询，属于这个班级的学生
	$("#BJ")
			.combobox(
					{
						url : 'listAllBANJI.action',
						valueField : 'BJ_ID',
						textField : 'BJMC',
						panelWidth : '200',
						onChange : function(newValue, oldValue) {
							$
									.ajax({
										type : "POST",
										url : "banJiForXS.action?BJ_ID="
												+ newValue,
										success : function(data) {
											var obj = eval("(" + data + ")");
											// 判断success==true 将学生的学号读取出来
											if (obj.success) {
												// 将不符合的节点，取消选中
												for ( var i = 0; i < obj.stuList.length; i++) {
													// 查找不符合的节点
													var nodeListN = $("div[name='list2'] div[name!='"
															+ obj.stuList[i].xh
															+ "'] input:checked");
													nodeListN.attr("checked",
															false);
												}
												// 查询符合的节点进行选中
												$
														.each(
																obj.stuList,
																function(i, n) {
																	// 查找符合的节点
																	var nodeListY = $("div[name='list2'] div[name='"
																			+ obj.stuList[i].xh
																			+ "'] input[value='"
																			+ obj.stuList[i].xh
																			+ "']");
																	// 如果此节点存在就将其选中，将其他的选中节点置空
																	nodeListY
																			.attr(
																					"checked",
																					true);
																});
											} else {
												// 当success==false时进行提示
												$.messager.alert('提示',
														obj.message);
												return;
											}
										}
									});

						}
					});

	$.ajax({
		url : "listAllXUESHENG.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			$.each(obj, function(i, n) {
				$("div[name='list2'] ").append(
						"<div name=" + obj[i].xh
								+ "><input type='checkbox' value=" + obj[i].xh
								+ ">" + obj[i].zsxm + "|" + obj[i].xh
								+ "</div>");

			});

		}
	});

	$.parser.parse('#addZWLRLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
/** ****************************************新增信息按钮————>添加列表标题方法*********************************** */
var ZWLR_ID = "";
function listTitle() {
	$
			.ajax({
				type : "post",
				url : "addZWLRLB.action?LBMC="
						+ encodeURI(encodeURI($('#LBMC').val())),
				success : function(msg) {
					var obj = eval("(" + msg + ")");
					// 判断success的返回状态
					if (obj.success) {
						// 如果返回的是true
						$.ajax({
									type : "POST",
									url : "listTitleId.action?LBMC="+ $('#LBMC').val(),
									success : function(data) {
										var obj = eval("(" + data + ")");
										// 将标题的id读取回来
										ZWLR_ID = obj[0].ZWLR_ID;
										$.messager.alert('提示', '指纹录入列表标题保存成功！');
									}
								});
					} else {
						// 如果返回的false
						$.messager.alert('提示', obj.message);
					}

				}
			});
}

/**
 * ************************************************新增信息对话框中的
 * 添加按钮方法******************
 */
function add() {
	var div1 = $("div[name='list2'] input[type='checkbox']:checked");
	var div2 = $("div[name='list1']");
	div1.each(function(i) {
		// 对未录入指纹的学生的名字字符串进行分割
		var t = $("div[name='list2'] div[name=" + $(this).val() + "]").text()
				.split("\"");
		// 向录入指纹的学生中添加学生
		div2.append("<div value='" + $(this).val()
				+ "'><input type='checkbox' value=" + $(this).val() + ">"
				+ t[0] + "</div>");
		// 遍历删除未录入指纹学生名单中选中的学生
		for ( var i = 0; i < div1.length; i++) {
			$("div div[name=" + $(this).val() + "]").remove(i);
		}
	});
}

/**
 * ****新增信息对话框 删除按钮的方法*********
 */
function del() {
	// 获取div集合
	var div1 = $("div[name='list2'] ");
	var div2 = $("div[name='list1'] input[type='checkbox']:checked");

	// 对选中的数据进行遍历
	div2.each(function(i) {
		// 对录入指纹的学生的名字字符串进行分割
		var t = $("div[name='list1'] div[value=" + $(this).val() + "]").text()
				.split("\"");
		// 将删除录入指纹的名单中的学生，添加到未录入指纹的学生的名单中
		div1.append("<div name=" + $(this).val()
				+ "><input type='checkbox' value=" + $(this).val() + ">" + t[0]
				+ "</div>");
		// 遍历删除录入指纹学生名单中选中的学生
		for ( var i = 0; i < div2.length; i++) {
			$(" div div[value='" + $(this).val() + "']").remove(i);
		}
	});
}

/** *******************************************新增信息对话框----》新增信息完毕********************** */
function addZWLRLB_over() {
	// 关闭修改信息框后，对节点进行删除
	// $("div[name='update_list1'] div").remove();
	// // 关闭修改信息框后，对节点进行删除
	// $("div[name='update_list2'] div").remove();

	$("table tr td div[name='list1'] div").remove();
	$("table tr td div[name='list2'] div").remove();

	// 关闭与清理表单与窗体
	$('#addZWLRLB_form').form('clear');
	$('#addZWLRLB_dlg').dialog('close');
	$('#datagrid').datagrid('clearSelections');
	// 新增加载数据
	$('#datagrid').datagrid('reload');
}

/*************将学生信息添加到指纹录入名单中 保存指纹录入列表按钮*******/
function stuToZWLRList() {
	// 查找符合的节点
	var nodeValues = $("div[name='list1'] div");
	// 用来存放所选择学生的学号
	var arrayXH = new Array();
	// 通过循环来获取节点的值
	for ( var i = 0; i < nodeValues.length; i++) {
		arrayXH.push(nodeValues.eq(i).attr("value"));
	}
	if (ZWLR_ID == null || ZWLR_ID == "") {
		$.messager.alert('提示', '请录入指纹录入标题并进行保存！');
		return;
	} else {
		$.ajax({
			type : "post",
			url : "stuToZWLRList.action?ZWLR_ID=" + ZWLR_ID + "&arrayXH="
					+ arrayXH,
			success : function(data) {
				var obj = eval("(" + data + ")");
				$.messager.alert('提示', obj.message);
			}
		});
	}

}

/** ******************************************打开修改对话框************************************** */
function updateZWLRLB_dialog() {
	// 获取选取的数据记录
	var rows = $('#datagrid').datagrid('getSelected');
	if (typeof (rows) == "undefined") {
		return;
	}
	if (!rows && typeof (rows) != "undefined" && rows != 0) {
		return;
	}
	//获取用户点击右上角的x，触发的事件
    $('#updateZWLRLB_dlg').dialog({onClose:function () {
    	// 关闭修改信息框后，对节点进行删除
    	$("div[name='update_list1'] div").remove();
    	// 关闭修改信息框后，对节点进行删除
    	$("div[name='update_list2'] div").remove();
    }});
	// 判断当前列表的id是否为空
	if (rows.ZWLR_ID) {
		// 打开修改页面
		$('#updateZWLRLB_dlg').dialog('open').dialog('setTitle', '查看/修改信息');
		// 显示出当前要修改的信息
		$.ajax({
			type : "POST",
			url : "updateZWLRLB.action?ZWLR_ID=" + rows.ZWLR_ID,
			success : function(data) {
				var obj = eval("(" + data + ")");
				$.parser.parse('#updateZWLRLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_LBMC').val(obj.LBMC);
				$('#update_SFLR').val(obj.SFLR);
				$('#update_ZWLRSJ').val(obj.ZWLRSJ);
				$('#update_LBCJSJ').val(obj.LBCJSJ);
				// $('#update_LASTMODIFYTIME').val(obj.LASTMODIFYTIME);
				// 修改标题时的用到的ZWLR_ID
				$('#update_title_ZWLR_ID').val(obj.ZWLR_ID);
				// 修改标题时用到的标志
				$('#update_title_optionflag').val('updateZWLRLB');

				// 修改数据列表时用到的ZWLR_ID
				$('#update_ZWLR_ID').val(obj.ZWLR_ID);
				// 修改数据列表时用到的标志
				$('#update_optionflag').val('updateList');
			}
		});
		// 显示班级下拉列表，以及选择班级触发的事件
		$("#update_BJ")
				.combobox(
						{
							url : 'listAllBANJI.action',
							valueField : 'BJ_ID',
							textField : 'BJMC',
							panelWidth : '200',
							onChange : function(newValue, oldValue) {
								$
										.ajax({
											type : "POST",
											url : "banJiForXS.action?BJ_ID="
													+ newValue,
											success : function(data) {
												var obj = eval("(" + data + ")");
												// 判断success==true 将学生的学号读取出来
												if (obj.success) {
													// 将不符合的节点，取消选中
													for ( var i = 0; i < obj.stuList.length; i++) {
														// 查找不符合的节点
														var nodeListN = $("div[name='update_list2'] div[name!='"
																+ obj.stuList[i].xh
																+ "'] input:checked");
														nodeListN.attr(
																"checked",
																false);
													}
													// 查询符合的节点进行选中
													$
															.each(
																	obj.stuList,
																	function(i,
																			n) {
																		// 查找符合的节点
																		var nodeListY = $("div[name='update_list2'] div[name='"
																				+ obj.stuList[i].xh
																				+ "'] input[value='"
																				+ obj.stuList[i].xh
																				+ "']");
																		// 如果此节点存在就将其选中，将其他的选中节点置空
																		nodeListY
																				.attr(
																						"checked",
																						true);
																	});
												} else {
													// 当success==false时进行提示
													$.messager.alert('提示',
															obj.message);
													return;
												}
											}
										});
							}
						});

		// 异步查询列表中的学生信息
		$.ajax({
			type : "POST",
			url : "updateInXs.action?ZWLR_ID=" + rows.ZWLR_ID,
			success : function(data) {
				var obj = eval("(" + data + ")");
				// 将当前指纹录入名单中的学生信息显示出来
				$.each(obj, function(i, n) {
					$("div[name='update_list1']").append(
							"<div value=" + obj[i].XH
									+ "><input type='checkbox' value="
									+ obj[i].XH + ">" + obj[i].ZSXM + "|"
									+ obj[i].XH);
				});
			}
		});

		// 不在当前指纹录入名单中的学生信息显示出来
		$.ajax({
			type : "POST",
			url : "updateNotInXs.action",
			success : function(data) {
				var obj = eval("(" + data + ")");
				// 将当前指纹录入名单中的学生信息显示出来
				$.each(obj, function(i, n) {
					$("div[name='update_list2']").append(
							"<div name=" + obj[i].XH
									+ "><input type='checkbox' value="
									+ obj[i].XH + ">" + obj[i].ZSXM + "|"
									+ obj[i].XH + "</div>");
				});
			}
		});
	}
}

/** **************************************保存修改后的标题*************************************** */
function updateListTitle() {
	$('#update_title').form('submit', {
		url : 'updateZWLRLB.action',
		onSubmit : function() {

		},
		success : function(data) {
			var obj = eval("(" + data + ")");
			if (obj.success == "false") {
				$.messager.alert('提示', "修改标题信息失败！");
				return;
			} else {
				$.messager.alert('提示', obj.message);
				return;
			}
		}
	});
}

/********************指纹录入列表 修改对话框中的 添加按钮方法**********************/
function update_add() {
	var div1 = $("div[name='update_list2'] input[type='checkbox']:checked");
	var div2 = $("div[name='update_list1']");
	var stuName = "";
	div1.each(function(i) {
		// 对未录入指纹的学生的名字字符串进行分割
		stuName = $("div[name='update_list2'] div[name=" + $(this).val() + "]")
				.text().split("\"");
		// 向录入指纹的学生中添加学生
		div2.append("<div value='" + $(this).val()
				+ "'><input type='checkbox' value=" + $(this).val() + ">"
				+ stuName[0] + "</div>");
		// 遍历删除未录入指纹学生名单中选中的学生
		for ( var i = 0; i < div1.length; i++) {
			$("div div[name=" + $(this).val() + "]").remove(i);
		}
	});
}

/******************************************指纹录入列表 修改对话框中 删除按钮方法******/
var delArrayXH = new Array();
function update_del() {
	// 点击删除按钮时，记录下删除学生的学号将
	var delNode = $("div[name='update_list1'] div input:checked");
	// 遍历获取学生的学号
	for ( var i = 0; i < delNode.length; i++) {
		delArrayXH.push(delNode.eq(i).attr("value"));
	}

	// 获取div集合
	var div1 = $("div[name='update_list2']");
	var div2 = $("div[name='update_list1'] input[type='checkbox']:checked");

	// 对选中的数据进行遍历
	div2
			.each(function(i) {
				// 对录入指纹的学生的名字字符串进行分割
				var t = $(
						"div[name='update_list1'] div[value='" + $(this).val()
								+ "']").text().split("\"");
				// 将删除录入指纹的名单中的学生，添加到未录入指纹的学生的名单中
				div1.append("<div name=" + $(this).val()
						+ "><input type='checkbox' value=" + $(this).val()
						+ ">" + t[0] + "</div>");
				// 遍历删除录入指纹学生名单中选中的学生
				for ( var i = 0; i < div2.length; i++) {
					$(
							"div[name='update_list1'] div[value='"
									+ $(this).val() + "']").remove(i);
				}
			});

}

/** ***********************************保存修改后的指纹录入名单*************************** */
function updateList() {
	// 查找符合的节点
	var nodeValues = $("div[name='update_list1'] div");
	var arrayXH = new Array();
	// 通过循环来获取节点的值
	for ( var i = 0; i < nodeValues.length; i++) {
		arrayXH.push(nodeValues.eq(i).attr("value"));
	}
	$('#updateZWLRLB_form').form('submit', {
		url : "updateZWLRLB.action?arrayXH=" + arrayXH,
		onSubmit : function() {

		},
		success : function(data) {
			var obj = eval("(" + data + ")");
			if (obj.success == "false") {
				$.messager.alert('提示', "修改信息失败！");
				return;
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
			}
		}
	});
	// 判断要删除的学号数组是否为空
	if (delArrayXH == null && delArrayXH == "") {
		return;
	} else {
		$.ajax({
			url : 'update_del.action',
			data : "delArrayXH=" + delArrayXH,
			success : function(data) {
				var obj = eval("(" + data + ")");
				$.messager.alert('提示', obj.message);
			}
		});
	}
}

/** ********************************************************修改信息对话框---》修改信息完毕******************************* */
function updateZWLRLB_over() {
	// 关闭修改信息框后，对节点进行删除
	$("div[name='update_list1'] div").remove();
	// 关闭修改信息框后，对节点进行删除
	$("div[name='update_list2'] div").remove();
	// 对表单以及窗体进行关闭与清理
	$('#update_title').form('clear');
	$('#updateZWLRLB_form').form('clear');
	$('#updateZWLRLB_dlg').dialog('close');
	$('#datagrid').datagrid('clearSelections');

	// 修改标题时的用到的ZWLR_ID
	$('#update_title_ZWLR_ID').val("");
	// 修改标题时用到的标志
	$('#update_title_optionflag').val("");
	// 修改数据列表时用到的ZWLR_ID
	$('#update_ZWLR_ID').val("");
	// 修改数据列表时用到的标志
	$('#update_optionflag').val("");
	// 重新加载数据
	$('#datagrid').datagrid('reload');
}

/** ******* 删除信息方法 弹出删除对话框************************ */
function showEnterDialog() {
	var row = $('#datagrid').datagrid('getSelections');
	if (row.length<1) {
		$.messager.alert('提示','请选择要删除的记录');
		return false;
	}else{
		$.messager.confirm("删除信息", " 确认要删除选中的信息吗？", function(r) {
			if (r) {
				var NowTime = new Date().toLocaleTimeString();
				var row = $('#datagrid').datagrid('getSelections');
				if (row < 1) {
					$.messager.alert('提示', '请选择要删除的记录');
					return;
				}
				var tmpyhzid = "";
				for ( var tmpi = 0; tmpi < row.length; tmpi++) {
					tmpyhzid = tmpyhzid + row[tmpi].ZWLR_ID + ",";
				}
				// 删除指纹录入列表
				$.ajax({
					url : "delZWLRLB.action?nowtime=" + NowTime + "&ZWLR_ID="
							+ tmpyhzid,
					context : document.body,
					success : function(data) {
						var yhzobj = eval("(" + data + ")");
						$.messager.alert('提示', yhzobj.message);
						$('#datagrid').datagrid('clearSelections');
						$('#datagrid').datagrid('reload');
					}
				});
			}
		});
	}
	return false;
}

/**
 * ****指纹录入列表-->名单 指纹录入部分
 */
/** *******************指纹录入对话框************** */
var dataLoc = 0; // 存储查询数据的位置
function zwlr_dlg() {
	var row = $('#datagrid').datagrid('getSelected');
	//意外情况处理    获取用户点击右上角的x，触发的事件
	$('#zwlr_dlg').dialog({
	  onClose: function() {
	    // 采集完毕后清理标题
	    $("div[id='zwlrTitle'] center").remove();
	    // 采集完毕后清理暂未录入的学生信息
	    $(".wbk div").remove();
	    // 采集完毕后清理已经采集人数与总人数的dom节点
	    $("span[id='totalPeopel']").remove();
	    // 采集完毕后清理学生学号与学生姓名的dom节点
	    $("#xs_xh font").remove();
	    $("#next_xs_xh font").remove();
	    $("#xs_xm font").remove();
	    $("#next_xs_xm font").remove();
	  }
	});

	ZWLR_ID = row.ZWLR_ID;
	if (typeof(row) == "undefined") {
	  return;
	}
	if (!row && typeof(row) != "undefined" && row != 0) {
	  return;
	}
	if (row.ZWLR_ID) {
	  $('#zwlr_dlg').dialog('open').dialog('setTitle', '指纹录入');
	  // 显示指纹录入标题
	  $("div[id='zwlrTitle']").append("<center>" + row.LBMC + "</center");
	  // 显示已经采集人数与总人数
	  $.ajax({
	    type: "POST",
	    url: "getCountZWLRMD.action?ZWLR_ID=" + row.ZWLR_ID,
	    success: function(data) {
	      var obj = eval("(" + data + ")");
	      $("div[id='total'] span").after("<span id='totalPeopel'>" + obj.zwlrrsCount + "/" + obj.totalCount + "</span>");
	    }
	  });
	  // 显示暂未录入指纹的学生信息
	  $.ajax({
	    type: "POST",
	    url: "notFP.action?ZWLR_ID=" + row.ZWLR_ID,
	    success: function(data) {
	      var obj = eval("(" + data + ")");
	      // 将当前指纹录入名单中的学生信息显示出来
	      for (var i = 0; i < obj.list.length; i++) {
	        $(".wbk").append("<div id=" + obj.list[i].xh + "><div style='width:60px; float:left; margin-top:5px;' id='xm'>" + obj.list[i].zsxm + "</div><div style='float:left;' id='xh' >" + obj.list[i].xh + "<input type='button' value='采集' onclick='collBtn_dlg(this);' style='margin-left:5px;'></div></div>");
	      }
	    }

	  });
	  // jquery实现按钮可用
	  $("#next").removeAttr("disabled");
	  // 异步获取两条数据
	  $.ajax({
	    type: "POST",
	    url: "findTwoData.action?ZWLR_ID=" + row.ZWLR_ID + "&optionflag=initFindTwoData" + "&loc=2",
	    success: function(data) {
	      var obj = eval("(" + data + ")");
	      dataLoc = obj.loc; // 读取当前数据的位置
	      //当数据的条数小于0时
	      if (obj.list.length<=0) {
              $("#xs_xh").append("<font color='red'>无</font>");
              $('#xs_xm').append("<font color='red'>无</font>");

              $("#next_xs_xh").append("<font color='red'>无</font>");
              $('#next_xs_xm').append("<font color='red'>无</font>");
              $("#next").attr("disabled", true);
              $("#save").attr("disabled", true);
            }
	      // 将当前与下一位录入指纹的学生信息显示出来
	      for (var i = 0; i < obj.list.length; i++) {
	        // 当前录入指纹的学生
	        if (i == 0) {
	          $("#xs_xh").append("<font color='red' value='" + obj.list[i].XH + "'>" + obj.list[i].XH + "</font>");
	          $("from").append("<input type='hidden' name='" + obj.list[i].XS_ID + "'>");

	          $('#xs_xm').append("<font color='red'>" + obj.list[i].ZSXM + "</font>");
	        }
	        // 下一位要录入指纹的学生
	        if (i == 1) {
	          $("#next_xs_xh").append("<font color='red'>" + obj.list[i].XH + "</font>");
	          $('#next_xs_xm').append("<font color='red'>" + obj.list[i].ZSXM + "</font>");
	        }
	        // 判断数据有多少条，如果小于所设定的条数据就显示此信息
	        if (obj.list.length < 2) {
	          $("#next_xs_xh").append("<font color='red'>无</font>");
	          $('#next_xs_xm').append("<font color='red'>无</font>");
	          $("#next").attr("disabled", true);
	          return;
	        }
	      }
	    }
	  });
	  $.parser.parse('#zwlr_dlg');
	}
}
/** **************************录入指纹1与录入指纹2******************** */
var TmpId = 0;
function OpenEnrollFpDlg(id) {
	$('#addDAT_dlg').dialog('open').dialog('setTitle', '登记指纹信息'); // 弹出窗口
	$.parser.parse('#addDAT_dlg');
	$('#es').val("");
	TmpId = id;
}

/** ********************指纹录入对话框--》录入指纹按钮--》登记参考模板 ***************************** */
function EnrollRefTemplate() {
	try{
		if (FPEngineEx1.OpenDevice(0, 0, 0) == 1) {
			if (FPEngineEx1.LinkDevice() == 1) {
				var ens = document.getElementById("es");
				FPEngineEx1.EnrFptEx();
				ens.value = "就绪";
				timer = setTimeout("Transaction()", 500);
			} else{
				alert("连接USB指纹仪失败");
			}
		} else
			alert("打开USB指纹仪失败");
	}catch(ex){
		$(function(){
			$("#downLoad").slideToggle(500);
		});
	}
}
/** **********************指纹录入对话框--》录入指纹按钮--》确定按钮************** */
function OKTemplate() {
	FPEngineEx1.CloseDevice();
	$('#addDAT_dlg').dialog('close');
}

function Transaction() {
	var ens = document.getElementById("es");
	var istatus = FPEngineEx1.GetWorkMsg();
	switch (istatus) {
	case 1:
		ens.value = "设备未连接";
		break;
	case 2:
		ens.value = "请按手指";
		break;
	case 3:
		ens.value = "请抬起手指";
		break;
	case 4:
		ens.value = "采集图像成功";
		imagedata = FPEngineEx1.GetImageEx();
		var en3 = document.getElementById("e3");
		en3.value = imagedata;
		break;
	case 5:
		ens.value = "采集指纹特征点完成";
		matdata = FPEngineEx1.GetCharEx();
		var en2 = document.getElementById("e2");
		en2.value = matdata;
		clearTimeout(timer);
		return;
	case 6:
		ens.value = "登记指纹特征点完成";
		refdata = FPEngineEx1.GetFptEx();
		var en1 = document.getElementById(TmpId);
		en1.value = refdata;
		clearTimeout(timer);
		return;
	}
	timer = setTimeout("Transaction()", 500);
}

/**
 * 下一位采集者
 */

function nextData() {
	// 读取下一位学生信息之前，先将现在的信息清理一下
	$("#xs_xh font").remove();
	$("#next_xs_xh font").remove();
	$("#xs_xm font").remove();
	$("#next_xs_xm font").remove();

	$.ajax({
	  type: "POST",
	  url: "findTwoData.action?ZWLR_ID=" + ZWLR_ID + "&loc=" + dataLoc,
	  success: function(data) {
	    var obj = eval("(" + data + ")");
	    dataLoc = obj.loc; // 读取数据的位置
	    if (obj.list != null) {
	      // 将查询的数据显示到对应的位置
	      for (var i = 0; i < obj.list.length; i++) {
	        // 当前录入指纹的学生
	        if (i == 0) {
	          $("#xs_xh").append("<font color='red' value='" + obj.list[i].XH + "'>" + obj.list[i].XH + "</font>");
	          $('#xs_xm').append("<font color='red'>" + obj.list[i].ZSXM + "</font>");
	        }
	        // 下一位要录入指纹的学生
	        if (i == 1) {
	          $("#next_xs_xh").append("<font color='red'>" + obj.list[i].XH + "</font>");
	          $('#next_xs_xm').append("<font color='red'>" + obj.list[i].ZSXM + "</font>");
	        }
	        // 判断数据有多少条，如果小于所设定的条数，就进行提示
	        if (obj.list.length < 2) {
	          $("#next_xs_xh").append("<font color='red'>无</font>");
	          $('#next_xs_xm').append("<font color='red'>无</font>");
	          // 提示信息
	          $.messager.alert('提示', '这是最后一条数据信息！');
	          // 使下一位采集者按钮不可能
	          $("#next").attr("disabled", true);
	          return;
	        }
	      }
	    }

	  }
	});
}

// 将指纹存入到数据库中
function addFp() {
	// 保存指纹时，读取当前学生的学号
	var xs_xh = $("#xs_xh font").attr("value");
	// 给用户提示，是否进行保存指纹信息
	$.messager.confirm("保存指纹", "确认提交指纹信息？",
	function(r) {
	  // 判断用户点击是“确定”，还是“取消”
	  if (r) {
	    // 用户点击“确定”，对表单进行提交
	    $('#zwlr_form').form('submit', {
	      url: "addZHIWEN.action?xh=" + xs_xh,
	      onSubmit: function() {
	        return $(this).form('validate');
	      },
	      success: function(data) {
	        // 成功之后返回json对象
	        var obj = eval('(' + data + ')');
	        // 显示数据存储成功信息
	        $.messager.alert('提示', obj.message);
	        // 数据存储成功之后，进行页面节点清理
	        $("#xs_xh font").remove();
	        $("#next_xs_xh font").remove();
	        $("#xs_xm font").remove();
	        $("#next_xs_xm font").remove();
	        
	        // 这个学号值要等于删除的学号，就将其在“暂未录入指纹学生”栏中删除
	        // 获取子节点的集合
	        var node = $(".wbk").children("div");
	        // 遍历子节点集合
	        $.each(node,
	        function(i, n) {
	          // 当前学生录入指纹的学生与“暂未录入指纹的学生”进行匹配，匹配上了就删除节点
	          if (node.eq(i).attr("id") == xs_xh) {
	            // 如何要求进行节点删除
	            node.eq(i).remove();
	          }
	        });
	        // 重新查询一下数据，显示到窗体上方，当前学生信息
	        $.ajax({
	          type: "post",
	          url: "findTwoData.action?ZWLR_ID=" + ZWLR_ID + "&optionflag=initFindTwoData" + "&loc=2" + "&newTime=" + Math.random(),
	          success: function(data) {
	            var obj = eval("(" + data + ")");
	            dataLoc = obj.loc; // 读取当前数据的位置
	            //当前要显示的两条学生信息为空时，显示为无
	            if (obj.list.length==0) {
	              $("#xs_xh").append("<font color='red'>无</font>");
	              $('#xs_xm').append("<font color='red'>无</font>");

	              $("#next_xs_xh").append("<font color='red'>无</font>");
	              $('#next_xs_xm').append("<font color='red'>无</font>");
	              $("#next").attr("disabled", true);
	              $("#save").attr("disabled", true);
	            }
	            // 判断当前要显示的学生信息条数是否小于2条
	            if (obj.list.length < 2) {
	              $("#xs_xh").append("<font color='red' value='" + obj.list[0].XH + "'>" + obj.list[0].XH + "</font>");
	              $('#xs_xm').append("<font color='red'>" + obj.list[0].ZSXM + "</font>");

	              $("#next_xs_xh").append("<font color='red'>无</font>");
	              $('#next_xs_xm').append("<font color='red'>无</font>");
	              $("#next").attr("disabled", true);
	              return;
	            } else {
	              for (var i = 0; i < obj.list.length; i++) {
	                // 判断数据有多少条，如果小于所设定的条数据就显示此信息
	                // 当前录入指纹的学生
	                if (i == 0) {
	                  $("#xs_xh").append("<font color='red' value='" + obj.list[i].XH + "'>" + obj.list[i].XH + "</font>");
	                  $("from").append("<input type='hidden' name='" + obj.list[i].XS_ID + "'>");

	                  $('#xs_xm').append("<font color='red'>" + obj.list[i].ZSXM + "</font>");
	                }
	                // 下一位要录入指纹的学生
	                if (i == 1) {
	                  $("#next_xs_xh").append("<font color='red'>" + obj.list[i].XH + "</font>");
	                  $('#next_xs_xm').append("<font color='red'>" + obj.list[i].ZSXM + "</font>");
	                }

	              }
	            }

	          }
	        });
	        $("span[id='totalPeopel']").remove();
	        // 显示已经采集人数与总人数
	  	  $.ajax({
	  	    type: "POST",
	  	    url: "getCountZWLRMD.action?ZWLR_ID=" +ZWLR_ID,
	  	    success: function(data) {
	  	      var obj = eval("(" + data + ")");
	  	      $("div[id='total'] span").after("<span id='totalPeopel'>" + obj.zwlrrsCount + "/" + obj.totalCount + "</span>");
	  	    }
	  	  });
	        // 清除表单中的内容
	        $('#zwlr_form').form('clear');
	      }
	    });
	  }
	});
}

// 单人采集 点击采集按钮
var xs_xh = "";
function collBtn_dlg(ele) {
	// 打开单人采集窗体
	$("#coll_dlg").dialog("open").dialog("setTitle", "单人采集");
	// 获取其学号
	xs_xh = $(ele).parent().parent().attr("id");
	$.parser.parse('#coll_dlg');
}
function coll_over() {
	$('#single_zwlr_form').form('clear');
	$("#coll_dlg").dialog("close");
}

var single_TmpId = "";
function single_OpenEnrollFpDlg(id) {
	$('#single_addDAT_dlg').dialog('open').dialog('setTitle', '登记指纹信息'); // 弹出窗口
	$.parser.parse('#single_addDAT_dlg');
	$('#single_es').val("");
	single_TmpId = id;
}

function single_EnrollRefTemplate() {
	try{
		if (FPEngineEx2.OpenDevice(0, 0, 0) == 1) {
			if (FPEngineEx2.LinkDevice() == 1) {
				var ens = document.getElementById("single_es");
				FPEngineEx2.EnrFptEx();
				ens.value = "就绪";
				timer = setTimeout("single_Transaction()", 500);
			} else
			{alert("连接USB指纹仪失败");}
		} else
			{alert("打开USB指纹仪失败");}
	}catch(ex){
		$(function(){
			$("#downLoad").slideToggle(500);
		});
			
	}
}

function single_OKTemplate() {
	FPEngineEx2.CloseDevice();
	$('#single_addDAT_dlg').dialog('close');
}

function single_Transaction() {
	var ens = document.getElementById("single_es");
	var istatus = FPEngineEx2.GetWorkMsg();
	switch (istatus) {
	case 1:
		ens.value = "设备未连接";
		break;
	case 2:
		ens.value = "请按手指";
		break;
	case 3:
		ens.value = "请抬起手指";
		break;
	case 4:
		ens.value = "采集图像成功";
		imagedata = FPEngineEx1.GetImageEx();
		var en3 = document.getElementById("e3");
		en3.value = imagedata;
		break;
	case 5:
		ens.value = "采集指纹特征点完成";
		matdata = FPEngineEx2.GetCharEx();
		var en2 = document.getElementById("e2");
		en2.value = matdata;
		clearTimeout(timer);
		return;
	case 6:
		ens.value = "登记指纹特征点完成";
		refdata = FPEngineEx2.GetFptEx();
		var en1 = document.getElementById(single_TmpId);
		en1.value = refdata;
		clearTimeout(timer);
		return;
	}
	timer = setTimeout("single_Transaction()", 500);
}

// 单个人指纹信息的保存
function single_saveFp() {
	$.messager.confirm("保存指纹", "确认提交指纹信息？", function(r) {
		// 判断用户点击是“确定”，还是“取消”
		if (r) {
			// 用户点击“确定”，对表单进行提交
			$('#single_zwlr_form').form('submit', {
				url : 'addZHIWEN.action?xh=' + xs_xh,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					// 成功之后返回json对象
					var obj = eval('(' + data + ')');
					// 将显示的当前学生进行更新显示
					if (obj.success == true) {
						// 显示数据存储成功信息
						$.messager.alert('提示', obj.message);
						// 获取子节点的集合
						var node = $(".wbk").children("div");
						// 遍历子节点集合
						$.each(node, function(i, n) {
							// 当前学生录入指纹的学生与“暂未录入指纹的学生”进行匹配，匹配上了就删除节点
							if (node.eq(i).attr("id") == xs_xh) {
								// 如何要求进行节点删除
								node.eq(i).remove();
							}
						});
						// 删除当前显示的学生信息
						$("#xs_xh font").remove();
						$("#next_xs_xh font").remove();
						$("#xs_xm font").remove();
						$("#next_xs_xm font").remove();
						// 更新显示的信息
						nextData();
						// 清理表单
						$("#single_zwlr_form").form("clear");
					}

				}
			});
		}
	});
}

/**************************修改功能---------->删除指纹************************/
function del_zw(){
	// 点击删除指纹按钮时，记录下要删除指纹学生的学号
	var delNode = $("div[name='update_list1'] div input:checked");
	//获取要删除指纹的学生的学号
	for ( var i = 0; i < delNode.length; i++) {
		delArrayXH.push(delNode.eq(i).attr("value"));
		$.messager.alert('提示',delNode.eq(i).attr("value"));
	}
	//将存储学号的数据传到
	$.ajax({
		  url: "delStuZHIWEN.action?delArrayXH="+delArrayXH,
		  success: function(data){
			  var obj=eval("("+data+")");
			  $.messager.alert('提示',obj.message);
		  }
		});
//	$.messager.alert('提示选中的节点大小',delNode.length);
}
/** *************************************指纹录入对话框------>采集完毕********************* */
function zwlr_over() {
	// 采集完毕后清理标题
	$("div[id='zwlrTitle'] center").remove();
	// 采集完毕后清理暂未录入的学生信息
	$(".wbk div").remove();
	// 采集完毕后清理已经采集人数与总人数的dom节点
	$("span[id='totalPeopel']").remove();
	// 采集完毕后清理学生学号与学生姓名的dom节点
	$("#xs_xh font").remove();
	$("#next_xs_xh font").remove();
	$("#xs_xm font").remove();
	$("#next_xs_xm font").remove();

	$("#zwlr_dlg").dialog("close");
	$('#datagrid').datagrid('clearSelections');
	// 新增加载数据
	$('#datagrid').datagrid('reload');
}

