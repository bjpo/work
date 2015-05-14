//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		KHFS : $('#KHFS_serchbar').val(),
		KECHENGMC : $('#KECHENGMC_serchbar').val(),
		KSXQ : $('#KSXQ_serchbar').val(),
		XSFP_SYSJ : $('#XSFP_SYSJ_serchbar').val(),
		YXXF : $('#YXXF_serchbar').val(),
		ZYMC : $('#ZYMC_serchbar').val(),
		KSXF : $('#KSXF_serchbar').val(),
		KECHENGXXLB_ID : $('#KECHENGXXLB_ID_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		KECHENGXXLBMC : $('#KECHENGXXLBMC_serchbar').val(),
		XSFP_LLJX : $('#XSFP_LLJX_serchbar').val(),
		ZY_ID : $('#ZY_ID_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		KECHENGDM : $('#KECHENGDM_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addKECHENGXX_dialog() {
	$('#addKECHENGXX_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addKECHENGXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addKECHENGXX() {
	if (!check_validateNumber($('#YXXF').val())) {
		$.messager.alert("提示", "应选学分应为数值！");
		return;
	}
	if (!check_validateNumber($('#KSXF').val())) {
		$.messager.alert("提示", "开设学分应为数值！");
		return;
	}
	$('#addKECHENGXX_form').form('submit', {
		url : 'addKECHENGXX.action',
		onSubmit : function() {
			// 课程信息名称匹配规则
			var KECHENGMCReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
			// 课程信息代码匹配规则
			var KECHENGDMReg = new RegExp("^\\w.+$");

			// 对课程信息名称进行验证
			if (KECHENGMCReg.test($('#KECHENGMC').val())) {
				// 对课程代码进行验证
				if (KECHENGDMReg.test($('#KECHENGDM').val())) {
					// 判断专业下拉列表框是否为空
					if ($('#ZYMC').combobox('getText') != "") {
						// 判断课程信息类别下拉列表是否为空
						if ($('#KECHENGXXLBMC').combobox('getText') != "") {
							return true;
						} else {
							$.messager.alert('提示', '请选择课程信息类别');
							return false;
						}
					} else {
						$.messager.alert('提示', '请选择专业名称');
						return false;
					}
				} else {
					$.messager.alert('提示', '请输入课程代码(字母、数字以及点)');
					return false;
				}
			} else {
				$.messager.alert('提示', '请输入课程信息名称(字母、数字、下划线、中括号)');
				return false;
			}

		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addKECHENGXX_form').form('clear');
				$('#addKECHENGXX_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateKECHENGXX_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.KECHENGXX_ID) {
		$.ajax({
			url : "updateKECHENGXX.action?nowtime=" + NowTime
					+ "&KECHENGXX_ID=" + row.KECHENGXX_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateKECHENGXX_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateKECHENGXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_KHFS').val(yhzobj.KHFS);
				$('#update_KECHENGMC').val(yhzobj.KECHENGMC);
				$('#update_KSXQ').val(yhzobj.KSXQ);
				$('#update_XSFP_SYSJ').val(yhzobj.XSFP_SYSJ);
				$('#update_YXXF').val(yhzobj.YXXF);
				$('#update_ZYMC').combobox('setValue', yhzobj.ZY_ID);
				$('#update_KSXF').val(yhzobj.KSXF);
				$('#update_MS').val(yhzobj.MS);
				$('#update_KECHENGXXLBMC').combobox('setValue',
						yhzobj.KECHENGXXLB_ID);
				$('#update_XSFP_LLJX').val(yhzobj.XSFP_LLJX);
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_KECHENGDM').val(yhzobj.KECHENGDM);
				$('#update_KECHENGXX_ID').val(yhzobj.KECHENGXX_ID);
				$('#update_optionflag').val('updateKECHENGXX');
			}
		});
	}
}
// 修改信息
function updateKECHENGXX() {
	if (!check_validateNumber($('#update_YXXF').val())) {
		$.messager.alert("提示", "应选学分应为数值！");
		return;
	}
	if (!check_validateNumber($('#update_KSXF').val())) {
		$.messager.alert("提示", "开设学分应为数值！");
		return;
	}

	$('#updateKECHENGXX_form').form('submit', {
		url : 'updateKECHENGXX.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateKECHENGXX_form').form('clear');
				$('#updateKECHENGXX_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_KHFS').val("");
	$('#update_KECHENGMC').val("");
	$('#update_KSXQ').val("");
	$('#update_XSFP_SYSJ').val("");
	$('#update_YXXF').val("");
	$('#update_ZYMC').val("");
	$('#update_KSXF').val("");
	$('#update_KECHENGXXLB_ID').val("");
	$('#update_MS').val("");
	$('#update_KECHENGXXLBMC').val("");
	$('#update_XSFP_LLJX').val("");
	$('#update_ZY_ID').val("");
	$('#update_BZ').val("");
	$('#update_KECHENGDM').val("");
	$('#update_KECHENGXX_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].KECHENGXX_ID + ",";
			}
			$.ajax({
				url : "delKECHENGXX.action?nowtime=" + NowTime
						+ "&KECHENGXX_ID=" + tmpyhzid,
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
						url : 'listKECHENGXX.action',
						title : '课程信息管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'KECHENGXX_ID',
						columns : [ [
								{
									title : '<b>课程名称</b>',
									field : 'KECHENGMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课程代码</b>',
									field : 'KECHENGDM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>专业</b>',
									field : 'ZYMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课程类别</b>',
									field : 'KECHENGXXLBMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>考核方式</b>',
									field : 'KHFS',
									sortable : true,
									width : 100
								},
								{
									title : '<b>考试学期</b>',
									field : 'KSXQ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>开设学分</b>',
									field : 'KSXF',
									sortable : true,
									width : 100
								},
								{
									title : '<b>应选学分</b>',
									field : 'YXXF',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学时分配：理论教学</b>',
									field : 'XSFP_LLJX',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学分分配：实验实践</b>',
									field : 'XSFP_SYSJ',
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
									field : 'KECHENGXX_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateKECHENGXX_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
