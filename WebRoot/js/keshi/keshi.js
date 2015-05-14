//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		MS : $('#MS_serchbar').val(),
		TMP6 : $('#TMP6_serchbar').val(),
		TMP5 : $('#TMP5_serchbar').val(),
		TMP4 : $('#TMP4_serchbar').val(),
		TMP3 : $('#TMP3_serchbar').val(),
		TMP2 : $('#TMP2_serchbar').val(),
		TMP1 : $('#TMP1_serchbar').val(),
		KSSJ : $('#KSSJ_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		JSSJ : $('#JSSJ_serchbar').val(),
		KSMC : $('#KSMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addKESHI_dialog() {
	$('#addKESHI_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addKESHI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addKESHI() {
	$('#addKESHI_form').form('submit', {
		url : 'addKESHI.action',
		onSubmit : function() {
			// 课时名称匹配规则（中文、英文、数字包括下划线）
			var ksmcReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");

			// 对课时名称进行验证
			if (ksmcReg.test($('#KSMC').val())) {
				// 判断课时的开始时间是否为空
				if ($('#KSSJ').timespinner('getValue') != "") {
					// 判断课时的结束时间是否为空
					if ($('#JSSJ').timespinner('getValue') != "") {

						var startTime = $('#KSSJ').timespinner('getValue');
						var endTime = $('#JSSJ').timespinner('getValue');
						if (startTime < endTime) {
							return true;
						} else {
							$.messager.alert('提示', '结束时间要大于开始时间');
							return false;
						}
					} else {
						$.messager.alert('提示', '请输入课时的结束时间(格式：10:25)');
						return false;
					}
				} else {
					$.messager.alert('提示', '请输入课时的开始时间(格式：05:24)');
					return false;
				}

				// 判断课时的结束时间是否大于开始时间

			} else {
				$.messager.alert('提示', '请输入课时名称(中文、英文、数字、下划线以及中括号)');
				return false;
			}

		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addKESHI_form').form('clear');
				$('#addKESHI_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateKESHI_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.KS_ID) {
		$.ajax({
			url : "updateKESHI.action?nowtime=" + NowTime + "&KS_ID="
					+ row.KS_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateKESHI_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateKESHI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_KSMC').val(yhzobj.KSMC);
				$('#update_KSSJ').timespinner('setValue', yhzobj.KSSJ);
				$('#update_JSSJ').timespinner('setValue', yhzobj.JSSJ);
				$('#update_MS').val(yhzobj.MS);
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_KS_ID').val(yhzobj.KS_ID);
				$('#update_optionflag').val('updateKESHI');
			}
		});
	}
}
// 修改信息
function updateKESHI() {
	$('#updateKESHI_form').form('submit', {
		url : 'updateKESHI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateKESHI_form').form('clear');
				$('#updateKESHI_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_MS').val("");
	$('#update_KSSJ').val("");
	$('#update_BZ').val("");
	$('#update_JSSJ').val("");
	$('#update_KSMC').val("");
	$('#update_KS_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].KS_ID + ",";
			}
			$.ajax({
				url : "delKESHI.action?nowtime=" + NowTime + "&KS_ID="
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
						url : 'listKESHI.action',
						title : '课时管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'KS_ID',
						columns : [ [
								{
									title : '<b>课时名称</b>',
									field : 'KSMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课时开始时间</b>',
									field : 'KSSJ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课时结束时间</b>',
									field : 'JSSJ',
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
									title : '<b>描述</b>',
									field : 'MS',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'KS_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateKESHI_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
