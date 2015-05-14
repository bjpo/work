//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		NJDM : $('#NJDM_serchbar').val(),
		NJ_ID : $('#NJ_ID_serchbar').val(),
		ZYDM : $('#ZYDM_serchbar').val(),
		TMP6 : $('#TMP6_serchbar').val(),
		TMP5 : $('#TMP5_serchbar').val(),
		TMP4 : $('#TMP4_serchbar').val(),
		TMP3 : $('#TMP3_serchbar').val(),
		TMP2 : $('#TMP2_serchbar').val(),
		TMP1 : $('#TMP1_serchbar').val(),
		NJMC : $('#NJMC_serchbar').val(),
		ZYMC : $('#ZYMC_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		BZK : $('#BZK_serchbar').val(),
		BJMC : $('#BJMC_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		BJHM : $('#BJHM_serchbar').val(),
		ZY_ID : $('#ZY_ID_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addBANJI_dialog() {
	$('#addBANJI_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addBANJI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addBANJI() {
	$('#addBANJI_form').form('submit', {
		url : 'addBANJI.action',
		onSubmit : function() {
			// 只能输入数字0到9一位
			var reg = new RegExp("^\\d{1}$");
			// 对班级号进行验证
			if (reg.test($('#BJHM').val())) {
				// 对年级进行判断是否为空
				if ($('#NJ_ID').combobox('getText') != "") {
					// 对专业进行判断是否为空
					if ($('#ZY_ID').combobox('getText') != "") {
						// 判断学生类型
						if ($('#BZK').combobox('getText') != "") {
							return true;
						} else {
							$.messager.alert('提示', '请选择学生类型');
							return false;
						}
					} else {
						$.messager.alert('提示', '请选择专业');
						return false;
					}
				} else {
					$.messager.alert('提示', '请选择年级');
					return false;
				}
			} else {
				$.messager.alert('提示', '请输入正确的班级号（0-9任意数字一位）！');
				return false;
			}

		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if (obj2.success) {
				$('#addBANJI_form').form('clear');
				$('#addBANJI_dlg').dialog('close');
			}
			$.messager.alert('提示', obj2.message);
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateBANJI_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.BJ_ID) {
		$('#updateBANJI_dlg').dialog('open').dialog('setTitle', '修改信息');
		$.parser.parse('#updateBANJI_dlg');

		$('#update_NJ_ID').combobox('select', row.NJ_ID);
		$('#update_ZY_ID').combobox('select', row.ZY_ID);
		$('#update_BZK').combobox('select', row.BZK);
		$('#update_MS').val(row.MS);
		$('#update_BJMC').val(row.BJMC);
		$('#update_BZ').val(row.BZ);
		$('#update_BJHM').val(row.BJHM);
		$('#update_BJ_ID').val(row.BJ_ID);
	}
}
// 修改信息
function updateBANJI() {
	$('#updateBANJI_form').form('submit', {
		url : 'updateBANJI.action',
		onSubmit : function() {
			// 班级匹配规则
			var bjNameReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_~()]+$");
			// 只能输入数字0到9一位
			var reg = new RegExp("^\\d{1}$");
			if (reg.test($('#update_BJMC').val())) {
				// 对班级号进行验证
				if (reg.test($('#BJHM').val())) {
					// 对年级进行判断是否为空
					if ($('#NJ_ID').combobox('getText') != "") {
						// 对专业进行判断是否为空
						if ($('#ZY_ID').combobox('getText') != "") {
							// 判断学生类型
							if ($('#BZK').combobox('getText') != "") {
								return true;
							} else {
								$.messager.alert('提示', '请选择学生类型');
								return false;
							}
						} else {
							$.messager.alert('提示', '请选择专业');
							return false;
						}
					} else {
						$.messager.alert('提示', '请选择年级');
						return false;
					}
				} else {
					$.messager.alert('提示', '请输入正确的班级号（0-9任意数字一位）！');
					return false;
				}
			} else {
				$.messager.alert('提示', '请输入班级名称');
				return false;
			}
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if (obj2.success) {
				$('#updateBANJI_form').form('clear');
				$('#updateBANJI_dlg').dialog('close');
			}
			$.messager.alert('提示', obj2.message);
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
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
				tmpyhzid = tmpyhzid + row[tmpi].BJ_ID + ",";
			}
			$.ajax({
				url : "delBANJI.action?nowtime=" + NowTime + "&BJ_ID="
						+ tmpyhzid,
				context : document.body,
				success : function(data) {

					var obj2 = eval("(" + data + ")");
					$.messager.alert('提示', obj2.message);
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
						url : 'listBANJI.action',
						title : '班级管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'BJ_ID',
						columns : [ [
								{
									title : '<b>班级名称</b>',
									field : 'BJMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>班号</b>',
									field : 'BJHM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>年级代码</b>',
									field : 'NJDM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>年级主键</b>',
									field : 'NJ_ID',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>专业代码</b>',
									field : 'ZYDM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>年级名称</b>',
									field : 'NJMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>专业名称</b>',
									field : 'ZYMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学生类型</b>',
									field : 'BZK',
									sortable : true,
									width : 100
								},
								{
									title : '<b>专业主键</b>',
									field : 'ZY_ID',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>操作</b>',
									field : 'BJ_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateBANJI_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
