//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		XINGQI_CD : $('#XINGQI_CD_serchbar').val(),
		KSMC_CD : $('#KSMC_CD_serchbar').val(),
		LAOSHI_XM : $('#LAOSHI_XM_serchbar').val(),
		KCB_KCMC : $('#KCB_KCMC_serchbar').val(),
		ZHOU : $('#ZHOU_serchbar').val(),
		KCB_KCMC_CD : $('#KCB_KCMC_CD_serchbar').val(),
		LAOSHI_GH : $('#LAOSHI_GH_serchbar').val(),
		LAOSHI_GH_CD : $('#LAOSHI_GH_CD_serchbar').val(),
		XINGQI : $('#XINGQI_serchbar').val(),
		ZHOU_CD : $('#ZHOU_CD_serchbar').val(),
		JSMC_CD : $('#JSMC_CD_serchbar').val(),
		JSMC : $('#JSMC_serchbar').val(),
		LAOSHI_XM_CD : $('#LAOSHI_XM_CD_serchbar').val(),
		KSMC : $('#KSMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框

var addJsId = null;// 向后台传送教室下拉列表中选择值的ID
var addJgId = null;// 向后台传送教师下拉列表中选择值的ID
var addKcId = null;// 向后台传送课程下拉列表中选择值的ID
var addKsId = null;// 向后台传送课时下拉列表中选择值的ID

var CK_addJsName = null;// 向后台传送教室下拉列表中选择的值
var CK_addJgGh = null;// 向后台传送老师下拉列表中选择的值
var CK_addJgName = null;// 向后台传送老师名称下拉列表中选择的值
var CK_addKcName = null;// 向后台传送课程名称下拉列表中选择的值
var CK_addKsName = null;// 向后台传送课时名称下拉列表中选择的值

var addJsId_CD = null;// 向后台传送串到的教室下拉列表中选择的值的ID
var addJgId_CD = null;// 向后台传送串到的教师下拉列表中选择的值的ID
var addKcId_CD = null;// 向后台传送串到的课程下拉列表中选择的值的ID
var addKsId_CD = null;// 向后台传送串到的课时下拉列表中选择的值的ID

var CK_addJsName_CD = null;// 向后台传送串到教室下拉列表中选择的值
var CK_addJgGh_CD = null;// 向后台传送串到老师下拉列表中选择的值
var CK_addJgName_CD = null;// 向后台传送串到老师名称下拉列表中选择的值
var CK_addKcName_CD = null;// 向后台传送串到的课程名称下拉列表中选择的值
var CK_addKsName_CD = null;// 向后台传送串到的课时名称下拉列表中选择的值

function addCKXX_dialog() {
	$('#addCKXX_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	// 教室名称下拉列表
	$('#JSMC').combobox({
		url : 'listAllJIAOSHI.action',
		valueField : 'JS_ID',
		textField : 'JSMC',
		onSelect : function(rec) {
			CK_addJsName = $(this).combobox('getText');
			addJsId = $(this).combobox('getValue');
		}
	});

	// 教工号与教师名称下拉列表
	$('#LAOSHI_GH').combobox(
			{
				url : 'listAllJIAOGONG.action',
				valueField : 'JG_ID',
				editable : false,
				// 格式化显示数据
				formatter : function(row) {
					var opts = $(this).combobox('options');
					// addJgId = $(this).combobox('getValue');
					addJgId = row[opts.textField = 'JG_ID'];
					CK_addJgGh = row[opts.textField = 'JGGH'];
					CK_addJgName = row[opts.textField = 'JGMC'];
					return CK_addJgGh + "/" + CK_addJgName;
				},
				onSelect : function() {
					CK_addJgName = $(this).combobox('getText');// 获取教工名字
					// 异步查询教工的工号
					$.ajax({
						type : "POST",
						url : "findJIAOGONG.action?LAOSHI_ID="
								+ $(this).combobox('getValue'),
						success : function(data) {
							var obj = eval("(" + data + ")");
							// 遍历json中的数据
							$.each(obj, function(n, value) {
								// 获取到的教工工号
								CK_addJgGh = value.JGGH;
							});
						}
					});
				}
			});

	// 课程名称下拉列表
	$('#KCB_KCMC').combobox({
		url : 'listAllKECHENGB.action',
		valueField : 'KCB_FXS_ID',
		textField : 'KCXXMC',
		onSelect : function() {
			CK_addKcName = $(this).combobox('getText');
			addKcId = $(this).combobox('getValue');
		}
	});

	// 课时名称下拉列表
	$('#KSMC').combobox({
		url : 'listAllKESHI.action',
		valueField : 'KS_ID',
		textField : 'KSMC',
		onSelect : function() {
			CK_addKsName = $(this).combobox('getText');
			addKsId = $(this).combobox('getValue');
		}
	});

	// 串到的课程名称
	$('#KCB_KCMC_CD').combobox({
		url : 'listAllKECHENGXX.action',
		valueField : 'KECHENGXX_ID',
		textField : 'KECHENGMC',
		onSelect : function() {
			CK_addKcName_CD = $(this).combobox('getText');
			addKcId_CD = $(this).combobox('getValue');
		}
	});
	// 串到的课时名称
	$('#KSMC_CD').combobox({
		url : 'listAllKESHI.action',
		valueField : 'KS_ID',
		textField : 'KSMC',
		onSelect : function() {
			CK_addKsName_CD = $(this).combobox('getText');
			addKsId_CD = $(this).combobox('getValue');
		}
	});

	// 串到的教工工号与串到的教师名称下拉列表
	$('#LAOSHI_GH_CD').combobox(
			{
				url : 'listAllJIAOGONG.action',
				valueField : 'JG_ID',
				editable : false,
				// 格式化显示数据
				formatter : function(row) {
					var opts = $(this).combobox('options');
					// addJgId = $(this).combobox('getValue');
					addJgId_CD = row[opts.textField = 'JG_ID'];
					CK_addJgGh_CD = row[opts.textField = 'JGGH'];
					CK_addJgName_CD = row[opts.textField = 'JGMC'];
					return CK_addJgGh_CD + "/" + CK_addJgName_CD;
				},
				onSelect : function() {
					CK_addJgName_CD = $(this).combobox('getText');// 获取教工名字
					// 异步查询教工的工号
					$.ajax({
						type : "POST",
						url : "findJIAOGONG.action?LAOSHI_ID="
								+ $(this).combobox('getValue'),
						success : function(data) {
							// alert(data.JGGH);
							var obj = eval("(" + data + ")");
							// 遍历json中的数据
							$.each(obj, function(n, value) {
								// 获取到的教工工号
								CK_addJgGh_CD = value.JGGH;
							});
						}
					});
				}
			});
	// 串到的教室名称:
	$('#JSMC_CD').combobox({
		url : 'listAllJIAOSHI.action',
		valueField : 'JS_ID',
		textField : 'JSMC',
		onSelect : function() {
			CK_addJsName_CD = $(this).combobox('getText');
			addJsId_CD = $(this).combobox('getValue');
		}
	});
	$.parser.parse('#addCKXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addCKXX() {
	$('#addCKXX_form')
			.form(
					'submit',
					{
						url : 'addCKXX.action?addJsName='
								+ encodeURI(encodeURI(CK_addJsName))
								+ "&addJgGh="
								+ encodeURI(encodeURI(CK_addJgGh))
								+ "&addJgName="
								+ encodeURI(encodeURI(CK_addJgName))
								+ "&addKcName="
								+ encodeURI(encodeURI(CK_addKcName))
								+ "&addKsName="
								+ encodeURI(encodeURI(CK_addKsName))
								+ "&addKcName_CD="
								+ encodeURI(encodeURI(CK_addKcName_CD))
								+ "&addKsName_CD="
								+ encodeURI(encodeURI(CK_addKsName_CD))
								+ "&addJgGh_CD="
								+ encodeURI(encodeURI(CK_addJgGh_CD))
								+ "&addJgName_CD="
								+ encodeURI(encodeURI(CK_addJgName_CD))
								+ "&addJsName_CD="
								+ encodeURI(encodeURI(CK_addJsName_CD))
								+ "&addJgId_CD=" + addJgId_CD + "&addKcId_CD="
								+ addKcId_CD + "&addKsId_CD=" + addKsId_CD
								+ "&addJsId_CD=" + addJsId_CD + "&addJgId="
								+ addJgId + "&addKsId=" + addKsId + "&addKcId="
								+ addKcId + "&addJsId=" + addJsId,
						onSubmit : function() {

							// 判断教工工号/教师名称下拉列表框是否为空
							if ($('#LAOSHI_GH').combobox('getText') != "") {
								// 判断课程名称下拉列表框是否为空
								if ($('#KCB_KCMC').combobox('getText') != "") {
									// 判断课时名称下拉列表框是否为空
									if ($('#KSMC').combobox('getText') != "") {
										// 判断星期下拉列表框是否为空
										if ($('#XINGQI').combobox('getText') != "") {
											// 判断周下拉列表框是否为空
											if ($('#ZHOU_serchbar').combobox('getText') != "") {

												// 判断教室名称下拉列表框是否为空
												if ($('#JSMC').combobox(
														'getText') != "") {

													// 判断串到的课程名称下拉列表框是否为空
													if ($('#KCB_KCMC_CD')
															.combobox('getText') != "") {
														// 判断串到的课时名称下拉列表框是否为空
														if ($('#KSMC_CD')
																.combobox(
																		'getText') != "") {
															// 判断串到的星期下拉列表框是否为空
															if ($('#XINGQI_CD')
																	.combobox(
																			'getText') != "") {

																// 判断串到的周下拉列表框是否为空
																if ($(
																		'#ZHOU_CD')
																		.combobox(
																				'getText') != "") {

																	// 判断串到的教师工号/教师名称下拉列表框是否为空
																	if ($(
																			'#LAOSHI_GH_CD')
																			.combobox(
																					'getText') != "") {

																		// 判断串到的教室名称下拉列表框是否为空
																		if ($(
																				'#JSMC_CD')
																				.combobox(
																						'getText') != "") {
																			return true;

																		} else {
																			$.messager
																					.alert(
																							'提示',
																							'请选择串到的教室名称');
																			return false;
																		}
																	} else {
																		$.messager
																				.alert(
																						'提示',
																						'请选择串到的教师工号/教师名称');
																		return false;
																	}
																} else {
																	$.messager
																			.alert(
																					'提示',
																					'请选择串到的周');
																	return false;
																}
															} else {
																$.messager
																		.alert(
																				'提示',
																				'请选择串到的星期');
																return false;
															}
														} else {
															$.messager
																	.alert(
																			'提示',
																			'请选择串到的课时名称');
															return false;
														}
													} else {
														$.messager.alert('提示',
																'请选择串到的课程名称');
														return false;
													}
												} else {
													$.messager.alert('提示',
															'请选择教室名称');
													return false;
												}
											} else {
												$.messager.alert('提示', '请选择周');
												return false;
											}
										} else {
											$.messager.alert('提示', '请选择星期');
											return false;
										}
									} else {
										$.messager.alert('提示', '请选择课时名称');
										return false;
									}
								} else {
									$.messager.alert('提示', '请选择课程名称');
									return false;
								}
							} else {
								$.messager.alert('提示', '请选择教工工号/教师名称');
								return false;
							}

						},
						success : function(data) {
							if ("false" == data) {
								$.messager.alert('提示', '新增信息失败！');
							} else {
								var obj2 = eval("(" + data + ")");
								$.messager.alert('提示', obj2.message);
								$('#addCKXX_form').form('clear');
								$('#addCKXX_dlg').dialog('close');
							}
							$('#datagrid').datagrid('clearSelections');
							$('#datagrid').datagrid('reload');
						}
					});
}
var CKXX_ID = null;
var CK_updateJgName = null;
var CK_updateJgGh = null;
var CK_updateJgGh_CD = null;
var CK_updateJgName_CD = null;
function updateCKXX_dialog() {

	var row = $('#datagrid').datagrid('getSelected');
	CKXX_ID = row.CKXX_ID;
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}

	// 教工工号与教师名称下拉列表
	$('#update_LAOSHI_GH').combobox(
			{
				url : 'listAllJIAOGONG.action',
				valueField : 'JG_ID',
				editable : false,
				// 格式化显示的数据
				formatter : function(row) {
					var opts = $(this).combobox('options');
					CK_updateJgGh = row[opts.textField = 'JGGH'];
					CK_updateJgName = row[opts.textField = 'JGMC'];
					return CK_updateJgGh + "/" + CK_updateJgName;
				},
				// 改变其值时触发
				onSelect : function() {
					CK_updateJgName = $(this).combobox('getText');// 获取教工名字
					// 异步查询教工的工号
					$.ajax({
						type : "POST",
						url : "findJIAOGONG.action?LAOSHI_ID="
								+ $(this).combobox('getValue'),
						success : function(data) {
							var obj = eval("(" + data + ")");
							// 遍历json中的数据
							$.each(obj, function(n, value) {
								// 获取到的教工工号
								CK_updateJgGh = value.JGGH;
								$('#update_LAOSHI_GH').combobox('setText',
										CK_updateJgGh + "/" + CK_updateJgName);
							});
						}
					});
				}
			});

	// 课程名称下拉列表
	$('#update_KCB_KCMC').combobox({
		url : 'listAllKECHENGXX.action',
		valueField : 'KECHENGXX_ID',
		textField : 'KECHENGMC'
	});

	// 课时名称下拉列表
	$('#update_KSMC').combobox({
		url : 'listAllKESHI.action',
		valueField : 'KS_ID',
		textField : 'KSMC'
	});

	// 教室名称下拉列表
	$('#update_JSMC').combobox({
		url : 'listAllJIAOSHI.action',
		valueField : 'JS_ID',
		textField : 'JSMC'
	});

	// 串到的课程名称
	$('#update_KCB_KCMC_CD').combobox({
		url : 'listAllKECHENGXX.action',
		valueField : 'KECHENGXX_ID',
		textField : 'KECHENGMC'
	});

	// 串到的课时名称
	$('#update_KSMC_CD').combobox({
		url : 'listAllKESHI.action',
		valueField : 'KS_ID',
		textField : 'KSMC'
	});

	// 串到的教师工号/姓名下拉列表

	$('#update_LAOSHI_GH_CD').combobox(
			{
				url : 'listAllJIAOGONG.action',
				valueField : 'JG_ID',
				editable : false,
				// 格式化显示的数据
				formatter : function(row) {
					var opts = $(this).combobox('options');
					CK_updateJgGh_CD = row[opts.textField = 'JGGH'];
					CK_updateJgName_CD = row[opts.textField = 'JGMC'];
					return CK_updateJgGh_CD + "/" + CK_updateJgName_CD;
				},
				// 改变其值时触发
				onSelect : function() {
					CK_updateJgName_CD = $(this).combobox('getText');// 获取教工名字
					// 异步查询教工的工号
					$.ajax({
						type : "POST",
						url : "findJIAOGONG.action?LAOSHI_ID="
								+ $(this).combobox('getValue'),
						success : function(data) {
							var obj = eval("(" + data + ")");
							// 遍历json中的数据
							$.each(obj, function(n, value) {
								CK_updateJgGh_CD = value.JGGH;
								$('#update_LAOSHI_GH_CD').combobox(
										'setText',
										CK_updateJgGh_CD + "/"
												+ CK_updateJgName_CD);
							});
						}
					});
				}
			});

	// 串到的教室名称:
	$('#update_JSMC_CD').combobox({
		url : 'listAllJIAOSHI.action',
		valueField : 'JS_ID',
		textField : 'JSMC'
	});
	// 按串课信息ID进行修改
	if (row.CKXX_ID) {
		$.ajax({
			url : 'updateCKXX.action?updateCkxxId=' + row.CKXX_ID,
			context : document.body,
			success : function(data) {
				var ckobj = eval("(" + data + ")");
				// 弹出窗口
				$('#updateCKXX_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息');
				// 需要重新渲染对话框，否则easyui不起作用
				$.parser.parse('#updateCKXX_dlg');
				$('#update_LAOSHI_GH').combobox('setValue', ckobj.LAOSHI_ID);
				// 设置工号的回显值
				$('#update_LAOSHI_GH').combobox('setText',
						ckobj.LAOSHI_GH + "/" + ckobj.LAOSHI_XM);

				$('#update_KCB_KCMC').combobox('setValue', ckobj.KCB_KCMC);
				$('#update_KSMC').combobox('setValue', ckobj.KSMC);
				$('#update_XINGQI').combobox('setValue', ckobj.XINGQI);
				$('#update_ZHOU').combobox('setValue', ckobj.ZHOU);
				$('#update_JSMC').combobox('setValue', ckobj.JSMC);
				$('#update_KCB_KCMC_CD')
						.combobox('setValue', ckobj.KCB_KCMC_CD);
				$('#update_KSMC_CD').combobox('setValue', ckobj.KSMC_CD);
				$('#update_XINGQI_CD').combobox('setValue', ckobj.XINGQI_CD);
				$('#update_ZHOU_CD').combobox('setValue', ckobj.ZHOU_CD);

				$('#update_LAOSHI_GH_CD').combobox('setValue',
						ckobj.LAOSHI_ID_CD);

				// 回显串到的老师工号与名称
				$('#update_LAOSHI_GH_CD').combobox('setText',
						ckobj.LAOSHI_GH_CD + "/" + ckobj.LAOSHI_XM_CD);

				$('#update_JSMC_CD').combobox('setValue', ckobj.JSMC_CD);
				$('#update_MS').val(ckobj.MS);
				$('#update_BZ').val(ckobj.BZ);

				// ID设置回显值部分
				// $('#update_CKXX_ID').val(ckobj.CKXX_ID);
				$('#update_KCB_ID').val(ckobj.KCB_ID);
				// $('#update_LAOSHI_ID').val(ckobj.LAOSHI_ID);
				// $('#update_JS_ID').val(ckobj.JS_ID);
				// $('#update_KS_ID').val(ckobj.KS_ID);
				//
				// $('#update_KS_ID_CD').val(ckobj.KS_ID_CD);
				$('#update_KCB_ID_CD').val(ckobj.KCB_ID_CD);
				// $('#update_JS_ID_CD').val(ckobj.JS_ID_CD);
				// $('#update_LAOSHI_ID_CD').val(ckobj.LAOSHI_ID_CD);
				$('#update_optionflag').val('updateCKXX');
			}
		});
	}
}
// 修改信息
function updateCKXX() {
	$('#updateCKXX_form').form(
			'submit',
			{
				url : 'updateCKXX.action?updateCkxxId=' + CKXX_ID
						+ "&LAOSHI_XM=" + encodeURI(encodeURI(CK_updateJgName))
						+ "&LAOSHI_GH=" + encodeURI(encodeURI(CK_updateJgGh))
						+ "&LAOSHI_GH_CD="
						+ encodeURI(encodeURI(CK_updateJgGh_CD))
						+ "&LAOSHI_XM_CD="
						+ encodeURI(encodeURI(CK_updateJgName_CD)),
				onSubmit : function() {
				},
				success : function(data) {
					if ("false" == data) {
						$.messager.alert('提示', "修改信息失败！");
					} else {
						var obj2 = eval("(" + data + ")");
						$.messager.alert('提示', obj2.message);
						$('#updateCKXX_form').form('clear');
						$('#updateCKXX_dlg').dialog('close');
						$('#datagrid').datagrid('clearSelections');
						$('#datagrid').datagrid('reload');
					}
				}
			});

	// 将文本框中的内容清空
	$('#update_XINGQI_CD').val("");
	$('#update_KSMC_CD').val("");
	$('#update_LAOSHI_XM').val("");
	$('#update_KCB_ID').val("");
	$('#update_KCB_KCMC').val("");
	$('#update_LAOSHI_ID').val("");
	$('#update_LAOSHI_ID_CD').val("");
	$('#update_JS_ID_CD').val("");
	$('#update_ZHOU').val("");
	$('#update_KCB_KCMC_CD').val("");
	$('#update_MS').val("");
	$('#update_LAOSHI_GH').val("");
	$('#update_BZ').val("");
	$('#update_KS_ID').val("");
	$('#update_LAOSHI_GH_CD').val("");
	$('#update_XINGQI').val("");
	$('#update_ZHOU_CD').val("");
	$('#update_JSMC_CD').val("");
	$('#update_JSMC').val("");
	$('#update_KS_ID_CD').val("");
	$('#update_LAOSHI_XM_CD').val("");
	$('#update_KSMC').val("");
	$('#update_KCB_ID_CD').val("");
	$('#update_JS_ID').val("");
	$('#update_CKXX_ID').val("");
	$('#update_optionflag').val("");
}
// 删除信息方法 弹出删除对话框：
function showEnterDialog() {
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
				tmpyhzid = tmpyhzid + row[tmpi].CKXX_ID + ",";
			}
			$.ajax({
				url : "delCKXX.action?nowtime=" + NowTime + "&CKXX_ID="
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
	return false;
}
// 初始化方法
$(function() {
	$('#datagrid')
			.datagrid(
					{
						url : 'listCKXX.action',
						title : '串课信息管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'CKXX_ID',
						columns : [ [
								{
									title : '<b>老师工号</b>',
									field : 'LAOSHI_GH',
									sortable : true,
									width : 100
								},
								{
									title : '<b>老师姓名</b>',
									field : 'LAOSHI_XM',
									sortable : true,
									width : 100
								},

								{
									title : '<b>课程名称</b>',
									field : 'KCB_KCMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课时名称</b>',
									field : 'KSMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>星期</b>',
									field : 'XINGQI',
									sortable : true,
									width : 100
								},
								{
									title : '<b>周</b>',
									field : 'ZHOU',
									sortable : true,
									width : 100
								},

								{
									title : '<b>教室名称</b>',
									field : 'JSMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>串到的课程名称</b>',
									field : 'KCB_KCMC_CD',
									sortable : true,
									width : 100
								},
								{
									title : '<b>串到的课时名称</b>',
									field : 'KSMC_CD',
									sortable : true,
									width : 100
								},
								{
									title : '<b>串到的星期</b>',
									field : 'XINGQI_CD',
									sortable : true,
									width : 100
								},
								{
									title : '<b>串到的周</b>',
									field : 'ZHOU_CD',
									sortable : true,
									width : 100
								},
								{
									title : '<b>串到的老师工号</b>',
									field : 'LAOSHI_GH_CD',
									sortable : true,
									width : 100
								},
								{
									title : '<b>串到的老师姓名</b>',
									field : 'LAOSHI_XM_CD',
									sortable : true,
									width : 100
								},

								{
									title : '<b>串到的教室名称</b>',
									field : 'JSMC_CD',
									sortable : true,
									width : 100
								},
								{
									title : '<b>描述</b>',
									field : 'MS',
									sortable : true,
									width : 100
								},
								{
									title : '<b>备注</b>',
									field : 'BZ',
									sortable : true,
									width : 100
								},

								{
									title : '<b>操作</b>',
									field : 'CKXX_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateCKXX_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
									}
								} ] ],
						singleSelect : false,// 是否单选
						pagination : true,// 分页控件
						rownumbers : true,// 行号
						frozenColumns : [ [ {
							field : 'ck',
							checkbox : true
						} ] ]
					});
	$('#datagrid').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});
