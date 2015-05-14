//模糊查询用户
function doSearch() {
	$('#datagrid').datagrid('load', {
		zsxm : $('#zsxm_serchbar').val()
	});
}
// 打开新增用户信息对话框
function add_dialog() {
	$('#add_dlg').dialog('open').dialog('setTitle', '添加学生信息'); // 弹出窗口

	$('#addpic').removeAttr('src');
	$('#addFJ_combobox').combobox({
		url : 'listJXLforFJ.action',
		valueField : 'jxlId',
		textField : 'jxlmc'
	});
}

// 新增房间信息
function addStudent() {
	$('#add_form')
			.form(
					'submit',
					{
						url : 'addStudent.action',
						onSubmit : function() {
							/**
							 * 匹配规则
							 */
							// 学号匹配规则
							var xhReg = new RegExp("^\\w+$");
							// 身份证匹配规则
							var idCardReg = new RegExp(
									"^\\d{17}[\\d|X]|\\d{15}$");
							// 联系电话匹配规则（手机)
							var telReg = new RegExp("\\d{11}");
							// 联系电话座机匹配规则(座机)
							var tel2Reg = new RegExp(
									"^\\d{3,4}-\\d{7}|\\d{3}-\\d{8}$");
							/**
							 * 验证
							 */
							// 对学号进行验证
							if (xhReg.test($('#xs_xh').val())) {
								// 对姓名进行验证
								if ($('#xs_zsxm').val() != "") {
//									// 对性别下拉列表框是否为空
//									if ($('#xs_xb').combobox('getText') != "") {
//										// 判断培养层次下拉列表
//										if ($('#xs_pycc').combobox('getText') != "") {
//											// 判断所在学院下拉列表框是否为空
//											if ($('#xs_xyId').combobox(
//													'getText') != "") {
//
//												// 判断所在专业下拉列表框是否为空
//												if ($('#xs_zyId').combobox(
//														'getText') != "") {
//													// 判断所在年级下拉列表框是否为空
//													if ($('#xs_njId').combobox(
//															'getText') != "") {
//														// 判断所在班级下拉列表框是否为空
//														if ($('#xs_bjId')
//																.combobox(
//																		'getText') != "") {
//
//															// 对身份证号进行验证
//															if (idCardReg
//																	.test($(
//																			'#xs_sfzhm')
//																			.val())) {
//
//																// 判断出生日期是否为空
//																if ($(
//																		'#xs_csny')
//																		.datebox(
//																				'getValue') != "") {
//
//																	// 判断民族是否为空
//																	if ($(
//																			'#xs_mz')
//																			.combobox(
//																					'getText') != "") {
//
//																		// 判断户籍类别是否为空
//																		if ($(
//																				'#xs_hjlb')
//																				.combobox(
//																						'getText') != "") {
//
//																			// 判断宿舍号是否为空
//																			if ($(
//																					'#xs_sushehao')
//																					.val() != "") {
//
//																				// 判断联系人是否为空
//																				if ($(
//																						'#xs_lxr')
//																						.val() != "") {
//
//																					// 判断联系地址是否为空
//																					if ($(
//																							'#xs_lxdz')
//																							.val() != "") {
//
//																						// 对联系电话进行验证
//																						if (telReg
//																								.test($(
//																										'#xs_lxdh')
//																										.val())
//																								|| tel2Reg
//																										.test($(
//																												'#xs_lxdh')
//																												.val())) {
//
//																							// 判断考生类别下拉列表框是否为空
//																							if ($(
//																									'#xs_kslb')
//																									.combobox(
//																											'getText') != "") {
//
//																								// 判断毕业类别下拉列表框是否为空
//																								if ($(
//																										'#xs_bylb')
//																										.combobox(
//																												'getText') != "") {
//
//																									// 判断入学时间是否为空
//																									if ($(
//																											'#xs_rxsj')
//																											.datebox(
//																													'getValue') != "") {
//
//																										// 判断学制是否为空
//																										if ($(
//																												'#xs_xz')
//																												.combobox(
//																														'getText') != "") {
//																											return true;
//																										} else {
//																											// 学制为空时，执行提示
//																											$.messager
//																													.alert(
//																															'提示',
//																															'请选择您的学制');
//																											return false;
//																										}
//
//																									} else {
//																										// 入学时间为空时，执行提示
//																										$.messager
//																												.alert(
//																														'提示',
//																														'请选择您的入学时间');
//																										return false;
//																									}
//
//																								} else {
//																									// 毕业类别为空时，执行提示
//																									$.messager
//																											.alert(
//																													'提示',
//																													'请选择毕业类别');
//																									return false;
//																								}
//																							} else {
//																								// 考生类别为空时，执行提示
//																								$.messager
//																										.alert(
//																												'提示',
//																												'请选择考生类别');
//																								return false;
//																							}
//
//																						} else {
//																							// 电话为空时，执行提示
//																							$.messager
//																									.alert(
//																											'提示',
//																											'请输入正确的手机号或座机号(如： 0511-4405222 或 021-87888822)');
//																							return false;
//																						}
//																					} else {
//																						// 联系地址为空时，执行提示
//																						$.messager
//																								.alert(
//																										'提示',
//																										'请输入联系地址');
//																						return false;
//																					}
//																				} else {
//																					// 联系人为空时，执行提示
//																					$.messager
//																							.alert(
//																									'提示',
//																									'请输入联系人');
//																					return false;
//																				}
//																			} else {
//																				// 宿舍号为空时，执行提示
//																				$.messager
//																						.alert(
//																								'提示',
//																								'请输入宿舍号');
//																				return false;
//																			}
//																		} else {
//																			// 户籍类别为空时，执行提示
//																			$.messager
//																					.alert(
//																							'提示',
//																							'请选择户籍类别');
//																			return false;
//																		}
//																	} else {
//																		// 民族为空时，执行提示
//																		$.messager
//																				.alert(
//																						'提示',
//																						'请选择民族');
//																		return false;
//																	}
//																} else {
//																	// 出生日期为空时，执行提示
//																	$.messager
//																			.alert(
//																					'提示',
//																					'请选择您的出生日期');
//																	return false;
//																}
//															} else {
//																// 身份证号不匹配时，执行提示
//																$.messager
//																		.alert(
//																				'提示',
//																				'请按正确的格式输入身份证号');
//																return false;
//															}
//														} else {
//															// 所在班级下拉列表框为空时，执行提示
//															$.messager
//																	.alert(
//																			'提示',
//																			'请选择您所在的班级');
//															return false;
//														}
//													} else {
//														// 所在年级下拉列表框为空时，执行提示
//														$.messager.alert('提示',
//																'请选择您所在年级');
//														return false;
//													}
//
//												} else {
//													// 所在专业为空时，执行提示
//													$.messager.alert('提示',
//															'请选择您所在的专业');
//													return false;
//												}
//											} else {
//												// 所在学院下拉列表框为空时，执行提示
//												$.messager.alert('提示',
//														'请选择您所在的学院');
//												return false;
//											}
//										} else {
//											// 培养层次为空时，执行提示
//											$.messager.alert('提示', '请选择您的培养层次');
//											return false;
//										}
//									} else {
//										// 性别下拉列表框为空时，执行提示
//										$.messager.alert('提示', '请选择您的性别');
//										return false;
//									}
								} else {
									// 姓名为空时，执行提示
									$.messager.alert('提示', '请输入您的姓名');
									return false;
								}
							} else {
								// 学号不匹配时，执行提示
								$.messager.alert('提示', '请输入学号(字母，数字，下划线)');
								return false;
							}
						},
						success : function(data) {
							var obj = eval('(' + data + ')');
							if (obj.success) {
								$('#add_dlg').dialog('close');
								$('#datagrid').datagrid('reload');
								$('#add_form').form('clear');
							}
							$.messager.alert('提示', obj.message);
						}
					});

}

function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}

function changeXYRelInfo() {
	alert('--->');
	// var tXyId = $('#xs_xyId').val();
	// if(!"".equals(tXyId)){
	// alert('--->'+tXyId);
	// }
	// else{
	// alert('--->2');
	// }
}

function addPic() {
	$('#add_form').form('submit', {
		url : 'changePic.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj = eval("(" + data + ")");
			if (obj.success) {
				document.getElementById("addpic").src = obj.message;
			}
		}
	});
}

function changePic() {
	$('#update_form').form('submit', {
		url : 'changePic.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj = eval("(" + data + ")");
			if (obj.success) {
				document.getElementById("pic").src = obj.message;
			}
		}
	});
}

function updateStudent_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	var xh = row.xh;

	$('#update_dlg').dialog('open').dialog('setTitle', '修改/查看学生信息');
	$.parser.parse('#update_dlg');
	$('#up_xs_id').val(row.xsId);
	$('#up_xs_xh').val(row.xh);
	$('#up_xs_zsxm').val(row.zsxm);
	$('#up_xs_cym').val(row.cym);
	$('#up_xs_xb').combobox('select', row.xb);
	$('#up_xs_sfzhm').val(row.sfzhm);
	$('#up_xs_csny').datebox('setValue', row.csny);
	$('#up_xs_mz').val(row.mz);
	$('#up_xs_rtsj').datebox('setValue', row.rtsj);
	$('#up_xs_csd').val(row.csd);
	$('#up_xs_hjlb').combobox('select', row.hjlb);
	$('#up_xs_sushehao').val(row.sushehao);
	$('#up_xs_ssdh').val(row.ssdh);
	$('#up_xs_gatdm').val(row.gatdm);
	$('#up_xs_lxr').val(row.lxr);
	$('#up_xs_yzbm').val(row.yzbm);
	$('#up_xs_dahh').val(row.dahh);
	$('#up_xs_dayh').val(row.dayh);
	$('#up_xs_dzyx').val(row.dzyx);
	$('#up_xs_lxdz').val(row.lxdz);
	$('#up_xs_lxdh').val(row.lxdh);
	$('#up_xs_zsjj').combobox('select', row.zsjj);
	$('#up_xs_zkzh').val(row.zkzh);
	$('#up_xs_ksh').val(row.ksh);
	$('#up_xs_kslb').combobox('select', row.kslb);
	$('#up_xs_jkzk').val(row.jkzk);
	$('#up_xs_kstz').val(row.kstz);
	$('#up_xs_rxwhcd').val(row.rxwhcd);
	$('#up_xs_sysf').val(row.sysf);
	$('#up_xs_bylb').combobox('select', row.bylb);
	$('#up_xs_sydq').val(row.sydq);
	$('#up_xs_kldm').val(row.kldm);
	$('#up_xs_rxcj').val(row.rxcj);
	$('#up_xs_xxtj').val(row.xxtj);
	$('#up_xs_tc').val(row.tc);
	$('#up_xs_rxsj').datebox('setValue', row.rxsj);
	$('#up_xs_zyIdLq').val(row.zyIdLq);
	$('#up_xs_xz').combobox('select', row.xz);
	$('#up_xs_rxnf').datebox('setValue', row.rxnf);
	$('#up_xs_rxfs').combobox('select', row.rxfs);
	$('#up_xs_pylb').combobox('select', row.pylb);
	$('#up_xs_pydx').val(row.pydx);
	$('#up_xs_pycc').combobox('select', row.pycc);
	$('#up_xs_bxfs').val(row.bxfs);
	$('#up_xs_xxnx').val(row.xxnx);
	$('#up_xs_qtbxxs').val(row.qtbxxs);
	$('#up_xs_zxwyyz').val(row.zxwyyz);
	$('#up_xs_zxwyjb').val(row.zxwyjb);
	$('#up_xs_bxlx').val(row.bxlx);
	$('#up_xs_byzx').val(row.byzx);
	$('#up_xs_sg').val(row.sg);
	$('#up_xs_tz').val(row.tz);
	$('#up_xs_jsjnldj').val(row.jsjnldj);
	$('#up_xs_jtzz').val(row.jtzz);
	$('#up_xs_yhId').val(row.yhId);
	$('#up_xs_xyId').combobox('select', row.xyId);
	$('#up_xs_zyId').combobox('select', row.zyId);
	$('#up_xs_njId').combobox('select', row.njId);
	$('#up_xs_bjId').combobox('select', row.bjId);
	$('#up_xs_imgfile').val("");
	$('#update_form').form('submit', {
		url : "findStudent.action",
		onSubmit : function() {
		},
		success : function(data) {
			var obj = eval("(" + data + ")");
			if (obj.success) {
				document.getElementById("pic").src = obj.message;
			}

		}
	});
}

function updateStudent() {
	$('#update_form').form('submit', {
		url : 'updateStudent.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj = eval('(' + data + ')');
			$('#update_dlg').dialog('close');
			$('#datagrid').datagrid('reload');
			$.messager.alert('提示', obj.message);
			$('#update_form').form('clear');
		}
	});
}

function del_dialog() {
	$.messager.confirm("删除学生信息", " 确认要删除选中的学生信息吗？", function(r) {
		if (r) {
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpyhid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				if (0 == tmpi) {
					tmpyhid = row[tmpi].xsId;
				} else {
					tmpyhid += ',' + row[tmpi].xsId;
				}
			}
			$.ajax({
				url : "delStudent.action?xsId=" + tmpyhid,
				context : document.body,
				success : function(data) {
					var yhobj = eval("(" + data + ")");
					$.messager.alert('提示', yhobj.message);
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
	// 显示数据表格
	$('#datagrid')
			.datagrid(
					{
						url : 'listStudent.action',
						title : '学生管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'xsId',
						columns : [ [
								{
									title : '<b>学号</b>',
									field : 'xh',
									sortable : true,
									width : 100
								},
								{
									title : '<b>姓名</b>',
									field : 'zsxm',
									sortable : true,
									width : 100
								},
								{
									title : '<b>曾用名</b>',
									field : 'cym',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>性别</b>',
									field : 'xb',
									sortable : true,
									width : 100
								},
								{
									title : '<b>身份证号</b>',
									field : 'sfzhm',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>出生年月</b>',
									field : 'csny',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>民族</b>',
									field : 'mz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>入团时间</b>',
									field : 'rtsj',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>出生地</b>',
									field : 'csd',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>户籍类别</b>',
									field : 'hjlb',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>宿舍号</b>',
									field : 'sushehao',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>宿舍电话</b>',
									field : 'ssdh',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>港澳台代码</b>',
									field : 'gatdm',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>联系人</b>',
									field : 'lxr',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>邮政编码</b>',
									field : 'yzbm',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>档案行号</b>',
									field : 'dahh',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>档案页号</b>',
									field : 'dayh',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>电子邮箱</b>',
									field : 'dzyx',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>联系地址</b>',
									field : 'lxdz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>联系电话</b>',
									field : 'lxdh',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>招生季节</b>',
									field : 'zsjj',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>准考证号</b>',
									field : 'zkzh',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>考生号</b>',
									field : 'ksh',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>考生类别</b>',
									field : 'kslb',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>健康状况</b>',
									field : 'jkzk',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>考生特征</b>',
									field : 'kstz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>入学文化程度</b>',
									field : 'rxwhcd',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>生源省份</b>',
									field : 'sysf',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>毕业类别</b>',
									field : 'bylb',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>生源地区</b>',
									field : 'sydq',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>科类代码</b>',
									field : 'kldm',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>入学成绩</b>',
									field : 'rxcj',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>获知学校信息途径</b>',
									field : 'xxtj',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>特长</b>',
									field : 'tc',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>入学时间</b>',
									field : 'rxsj',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>录取专业</b>',
									field : 'zyIdLq',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>学制</b>',
									field : 'xz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>入学年份</b>',
									field : 'rxnf',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>入学方式</b>',
									field : 'rxfs',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>培养类别</b>',
									field : 'pylb',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>培养对象</b>',
									field : 'pydx',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>培养层次</b>',
									field : 'pycc',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>办学方式</b>',
									field : 'bxfs',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>学习年限</b>',
									field : 'xxnx',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>其他办学形式</b>',
									field : 'qtbxxs',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>主修外语语种</b>',
									field : 'zxwyyz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>主修外语级别</b>',
									field : 'zxwyjb',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>办学类型</b>',
									field : 'bxlx',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>毕业中学</b>',
									field : 'byzx',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>身高</b>',
									field : 'sg',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>体重</b>',
									field : 'tz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>主修计算机能力等级</b>',
									field : 'jsjnldj',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>家庭住址</b>',
									field : 'jtzz',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>用户ID</b>',
									field : 'yhId',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>所在学院</b>',
									field : 'xyId',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>所在专业</b>',
									field : 'zyId',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>所在年级</b>',
									field : 'njId',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>所在班级</b>',
									field : 'bjId',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>操作</b>',
									field : 'xsId',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateStudent_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});

	$('#xs_xyId').combobox({
		onChange : function(newValue, oldValue) {
			if ('' != newValue) {
				$("#xs_zyId").combobox("clear");
				$("#xs_zyId").combobox({
					url : "listZHUANYEFORXUEYUAN.action?XYID=" + newValue,
					valueField : 'ZY_ID',
					textField : 'ZYMC'
				});
			}
		},
		onLoadSuccess : function() {
		}
	});

	$('#xs_zyId')
			.combobox(
					{
						onChange : function(newValue, oldValue) {
							if ('' != newValue) {
								$("#xs_bjId")
										.combobox(
												{
													url : "listBANJIByZHUANYEAndNIANJI.action?ZY_ID="
															+ newValue
															+ "&NJ_ID="
															+ $('#xs_njId')
																	.combobox(
																			'getValue'),
													valueField : 'BJ_ID',
													textField : 'BJMC'
												});
							}
						},
						onLoadSuccess : function() {
						}
					});

	$('#xs_njId')
			.combobox(
					{
						onChange : function(newValue, oldValue) {
							if ('' != newValue) {
								$("#xs_bjId")
										.combobox(
												{
													url : "listBANJIByZHUANYEAndNIANJI.action?ZY_ID="
															+ $('#xs_zyId')
																	.combobox(
																			'getValue')
															+ "&NJ_ID="
															+ newValue,
													valueField : 'BJ_ID',
													textField : 'BJMC'
												});
							}
						},
						onLoadSuccess : function() {
						}
					});
});