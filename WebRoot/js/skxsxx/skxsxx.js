//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		KCB_KCXXMC : $('#KCB_KCXXMC_serchbar').val(),
		XSXM : $('#XSXM_serchbar').val(),
		XUEHAO : $('#XUEHAO_serchbar').val(),
		KCB_ID : $('#KCB_ID_serchbar').val(),
		XS_ID : $('#XS_ID_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addSKXSXX_dialog() {
	$('#addSKXSXX_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addSKXSXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addSKXSXX() {
	$('#addSKXSXX_form').form('submit', {
		url : 'addSKXSXX.action',
		onSubmit : function() {
			if ($('#KCB_ID').combobox('getText') != "") {
				if ($('#XS_ID').combobox('getText')!="") {
					return true;
				} else {
					$.messager.alert('提示','请选择学生');
					return false;
				}
			} else {
				$.messager.alert('提示', '请选择课程');
				return false;
			}

		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addSKXSXX_form').form('clear');
				$('#addSKXSXX_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateSKXSXX_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.KCXS_ID) {
		$.ajax({
			url : "updateSKXSXX.action?nowtime=" + NowTime + "&KCXS_ID="
					+ row.KCXS_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateSKXSXX_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateSKXSXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_KCB_KCXXMC').val(yhzobj.KCB_KCXXMC);
				$('#update_XSXM').val(yhzobj.XSXM);
				$('#update_XUEHAO').val(yhzobj.XUEHAO);
				$('#update_KCB_ID').val(yhzobj.KCB_ID);
				$('#update_XS_ID').val(yhzobj.XS_ID);
				$('#update_KCXS_ID').val(yhzobj.KCXS_ID);
				$('#update_optionflag').val('updateSKXSXX');
			}
		});
	}
}
// 修改信息
function updateSKXSXX() {
	$('#updateSKXSXX_form').form('submit', {
		url : 'updateSKXSXX.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateSKXSXX_form').form('clear');
				$('#updateSKXSXX_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_KCB_KCXXMC').val("");
	$('#update_XSXM').val("");
	$('#update_XUEHAO').val("");
	$('#update_KCB_ID').val("");
	$('#update_XS_ID').val("");
	$('#update_KCXS_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].KCXS_ID + ",";
			}
			$.ajax({
				url : "delSKXSXX.action?nowtime=" + NowTime + "&KCXS_ID="
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
						url : 'listSKXSXX.action',
						title : '上课学生信息管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'KCXS_ID',
						columns : [ [
								{
									title : '<b>课程名</b>',
									field : 'KCB_KCXXMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学生姓名</b>',
									field : 'XSXM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学号</b>',
									field : 'XUEHAO',
									sortable : true,
									width : 100
								},
								/*
								 * { title : '<b>字段4</b>', field : 'KCB_ID',
								 * sortable : true, width : 100 }, { title : '<b>字段5</b>',
								 * field : 'XS_ID', sortable : true, width : 100 },
								 */
								{
									title : '<b>操作</b>',
									field : 'KCXS_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateSKXSXX_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
