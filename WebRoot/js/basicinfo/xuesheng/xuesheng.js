//模糊查询用户
function doSearch() {
	$('#datagrid').datagrid('load', {
		zsxm : $('#zsxm_serchbar').val()
	});
}

// 新增房间信息
function updateXsxx() {
	$('#add_form')
			.form(
					'submit',
					{
						url : 'upateXsxx.action',
						onSubmit : function() {
							/**
							 * 匹配规则
							 */
							// 学号匹配规则
							var xhReg = new RegExp("^\\w+$");
							// 身份证匹配规则
							var idCardReg = new RegExp(
									"^\\d{17}[\\d|X]|\\d{15}$");
							/**
							 * 验证
							 */
							// 对学号进行验证
							if (xhReg.test($('#xs_xh').val())) {
								// 对姓名进行验证
								if ($('#xs_zsxm').val() != "") {
									// 对身份证号进行验证
									if (idCardReg.test($('#xs_sfzhm').val())) {
												// 判断所在学院下拉列表框是否为空
												if ($('#xs_xyId').combobox('getText') != "") {
													// 判断所在专业下拉列表框是否为空
													if ($('#xs_zyId').combobox('getText') != "") {
														// 判断所在年级下拉列表框是否为空
														if ($('#xs_njId').combobox('getText') != "") {
															// 判断所在班级下拉列表框是否为空
															if ($('#xs_bjId').combobox('getText') != "") {
																	return true;
																} else {	
																		// 所在班级下拉列表框为空时，执行提示
																		$.messager.alert('提示',	'请选择您所在的班级');
																					return false;
																		}
														} else {
																// 所在年级下拉列表框为空时，执行提示
																$.messager.alert('提示','请选择您所在年级');
																				return false;
																}
													} else {
															// 所在专业为空时，执行提示
															$.messager.alert('提示','请选择您所在的专业');
																			return false;
															}

												} else {
													// 所在学院下拉列表框为空时，执行提示
													$.messager.alert('提示','请选择您所在的学院');
													return false;
												}
										} else {
											// 身份证号不匹配时，执行提示
											$.messager.alert('提示', '请按正确的格式输入身份证号');
											return false;
									}
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
							var obj = eval("(" + data + ")");
							$.messager.alert('提示', obj.message);
							// $(function() {
							$('#add_form')
									.form(
											'submit',
											{
												url : 'listXsxx.action',
												onSubmit : function() {
												},
												success : function(data) {
													var obj = eval("(" + data
															+ ")");
													// 判断modify_num是否等于1
													// modify_num是否不等于1
													if ("false" == obj.status) {
														document
																.getElementById("addpic").src = obj.message;
													} else if ("new" == obj.status) {
														$('#xs_id').val(
																obj.xsId);
														$('#xs_yhId').val(
																obj.yhid);
														$('#addpic')
																.removeAttr(
																		'src');
													} else if ("display" == obj.status) {
														if (obj.modify_num == 1) {
															$(
																	"#add_form .easyui-combobox")
																	.combobox(
																			'disable');
															$("#add_form input")
																	.attr(
																			'disabled',
																			true);
															$("#gx")
																	.linkbutton(
																			'disable');
															$(".easyui-datebox")
																	.datebox(
																			'disable');
															$("#add_form input")
																	.css(
																			{
																				color : "#8db2e3"
																			});
														}
														$('#xs_id').val(
																obj.xsId);
														$('#xs_xh').val(obj.xh);
														$('#xs_zsxm').val(
																obj.zsxm);
														$('#xs_cym').val(
																obj.cym);
														$('#xs_xb').combobox(
																'select',
																obj.xb);
														$('#xs_sfzhm').val(
																obj.sfzhm);
														$('#xs_csny').datebox(
																'setValue',
																obj.csny);
														$('#xs_mz').combobox(
																'select',
																obj.mz);
														$('#xs_rtsj').datebox(
																'setValue',
																obj.rtsj);
														$('#xs_csd').val(
																obj.csd);
														$('#xs_hjlb').combobox(
																'select',
																obj.hjlb);
														$('#xs_sushehao').val(
																obj.sushehao);
														$('#xs_ssdh').val(
																obj.ssdh);
														$('#xs_gatdm').val(
																obj.gatdm);
														$('#xs_lxr').val(
																obj.lxr);
														$('#xs_yzbm').val(
																obj.yzbm);
														$('#xs_dahh').val(
																obj.dahh);
														$('#xs_dayh').val(
																obj.dayh);
														$('#xs_dzyx').val(
																obj.dzyx);
														$('#xs_lxdz').val(
																obj.lxdz);
														$('#xs_lxdh').val(
																obj.lxdh);
														$('#xs_zsjj').combobox(
																'select',
																obj.zsjj);
														$('#xs_zkzh').val(
																obj.zkzh);
														$('#xs_ksh').val(
																obj.ksh);
														$('#xs_kslb').combobox(
																'select',
																obj.kslb);
														$('#xs_jkzk').val(
																obj.jkzk);
														$('#xs_kstz').val(
																obj.kstz);
														$('#xs_rxwhcd').val(
																obj.rxwhcd);
														$('#xs_sysf').val(
																obj.sysf);
														$('#xs_bylb').combobox(
																'select',
																obj.bylb);
														$('#xs_sydq').val(
																obj.sydq);
														$('#xs_kldm').val(
																obj.kldm);
														$('#xs_rxcj').val(
																obj.rxcj);
														$('#xs_xxtj').val(
																obj.xxtj);
														$('#xs_tc').val(obj.tc);
														$('#xs_rxsj').datebox(
																'setValue',
																obj.rxsj);
														$('#xs_zyIdLq').val(
																obj.zyIdLq);
														$('#xs_xz').combobox(
																'select',
																obj.xz);
														$('#xs_rxnf').datebox(
																'setValue',
																obj.rxnf);
														$('#xs_rxfs').combobox(
																'select',
																obj.rxfs);
														$('#xs_pylb').combobox(
																'select',
																obj.pylb);
														$('#xs_pydx').val(
																obj.pydx);
														$('#xs_pycc').combobox(
																'select',
																obj.pycc);
														$('#xs_bxfs').val(
																obj.bxfs);
														$('#xs_xxnx').val(
																obj.xxnx);
														$('#xs_qtbxxs').val(
																obj.qtbxxs);
														$('#xs_zxwyyz').val(
																obj.zxwyyz);
														$('#xs_zxwyjb').val(
																obj.zxwyjb);
														$('#xs_bxlx').val(
																obj.bxlx);
														$('#xs_byzx').val(
																obj.byzx);
														$('#xs_sg').val(obj.sg);
														$('#xs_tz').val(obj.tz);
														$('#xs_jsjnldj').val(
																obj.jsjnldj);
														$('#xs_jtzz').val(
																obj.jtzz);
														$('#xs_yhId').val(
																obj.yhid);
														$('#xs_xyId').combobox(
																'select',
																obj.xyId);
														$('#xs_zyId').combobox(
																'select',
																obj.zyId);
														$('#xs_njId').combobox(
																'select',
																obj.njId);
														$('#xs_bjId').combobox(
																'select',
																obj.bjId);
														$('#xs_zhiwenId1').val(
																obj.xszw1);
														$('#xs_zhiwenId2').val(
																obj.xszw2);
														if (obj.success) {
															document
																	.getElementById("addpic").src = obj.message;
														}
													}

												}
											});
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

// 初始化方法
$(function() {
	$('#add_form').form('submit', {
		url : 'listXsxx.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj = eval("(" + data + ")");
			// 判断modify_num是否等于1
			// modify_num是否不等于1
			if ("false" == obj.status) {
				document.getElementById("addpic").src = obj.message;
			} else if ("new" == obj.status) {
				$('#xs_id').val(obj.xsId);
				$('#xs_yhId').val(obj.yhid);
				$('#addpic').removeAttr('src');
			} else if ("display" == obj.status) {
				if (obj.modify_num == 1) {
					$("#add_form .easyui-combobox").combobox('disable');
					$("#add_form input").attr('disabled', true);
					$("#gx").linkbutton('disable');
					$(".easyui-datebox").datebox('disable');
					$("#add_form input").css({
						color : "#8db2e3"
					});
				}
				$('#xs_id').val(obj.xsId);
				$('#xs_xh').val(obj.xh);
				$('#xs_zsxm').val(obj.zsxm);
				$('#xs_cym').val(obj.cym);
				$('#xs_xb').combobox('select', obj.xb);
				$('#xs_sfzhm').val(obj.sfzhm);
				$('#xs_csny').datebox('setValue', obj.csny);
				$('#xs_mz').combobox('select', obj.mz);
				$('#xs_rtsj').datebox('setValue', obj.rtsj);
				$('#xs_csd').val(obj.csd);
				$('#xs_hjlb').combobox('select', obj.hjlb);
				$('#xs_sushehao').val(obj.sushehao);
				$('#xs_ssdh').val(obj.ssdh);
				$('#xs_gatdm').val(obj.gatdm);
				$('#xs_lxr').val(obj.lxr);
				$('#xs_yzbm').val(obj.yzbm);
				$('#xs_dahh').val(obj.dahh);
				$('#xs_dayh').val(obj.dayh);
				$('#xs_dzyx').val(obj.dzyx);
				$('#xs_lxdz').val(obj.lxdz);
				$('#xs_lxdh').val(obj.lxdh);
				$('#xs_zsjj').combobox('select', obj.zsjj);
				$('#xs_zkzh').val(obj.zkzh);
				$('#xs_ksh').val(obj.ksh);
				$('#xs_kslb').combobox('select', obj.kslb);
				$('#xs_jkzk').val(obj.jkzk);
				$('#xs_kstz').val(obj.kstz);
				$('#xs_rxwhcd').val(obj.rxwhcd);
				$('#xs_sysf').val(obj.sysf);
				$('#xs_bylb').combobox('select', obj.bylb);
				$('#xs_sydq').val(obj.sydq);
				$('#xs_kldm').val(obj.kldm);
				$('#xs_rxcj').val(obj.rxcj);
				$('#xs_xxtj').val(obj.xxtj);
				$('#xs_tc').val(obj.tc);
				$('#xs_rxsj').datebox('setValue', obj.rxsj);
				$('#xs_zyIdLq').val(obj.zyIdLq);
				$('#xs_xz').combobox('select', obj.xz);
				$('#xs_rxnf').datebox('setValue', obj.rxnf);
				$('#xs_rxfs').combobox('select', obj.rxfs);
				$('#xs_pylb').combobox('select', obj.pylb);
				$('#xs_pydx').val(obj.pydx);
				$('#xs_pycc').combobox('select', obj.pycc);
				$('#xs_bxfs').val(obj.bxfs);
				$('#xs_xxnx').val(obj.xxnx);
				$('#xs_qtbxxs').val(obj.qtbxxs);
				$('#xs_zxwyyz').val(obj.zxwyyz);
				$('#xs_zxwyjb').val(obj.zxwyjb);
				$('#xs_bxlx').val(obj.bxlx);
				$('#xs_byzx').val(obj.byzx);
				$('#xs_sg').val(obj.sg);
				$('#xs_tz').val(obj.tz);
				$('#xs_jsjnldj').val(obj.jsjnldj);
				$('#xs_jtzz').val(obj.jtzz);
				$('#xs_yhId').val(obj.yhid);
				$('#xs_xyId').combobox('select', obj.xyId);
				$('#xs_zyId').combobox('select', obj.zyId);
				$('#xs_njId').combobox('select', obj.njId);
				$('#xs_bjId').combobox('select', obj.bjId);
				$('#xs_zhiwenId1').val(obj.xszw1);
				$('#xs_zhiwenId2').val(obj.xszw2);
				if (obj.success) {
					document.getElementById("addpic").src = obj.message;
				}
			}

		}
	});
});