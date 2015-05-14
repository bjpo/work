//模糊查询用户
function doSearch() {
	$('#buildings_datagrid').datagrid('load', {
		jxlmc : $('#jxlmc_serchbar').val()
	});
}

// 打开新增用户信息对话框
function addJXL_dialog() {
	$('#addjxl_dlg').dialog('open').dialog('setTitle', '添加教学楼信息'); // 弹出窗口
	$('#jxl_mc').val("");
	$('#jxl_dm').val("");
	$('#jxl_lh').val("");
	$('#jxl_wz').val("");
	$.parser.parse('#addjxl_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增教学楼信息
function addJXL() {
	$('#building_addJXL_form').form(
			'submit',
			{
				url : 'addJXL.action',
				onSubmit : function() {
					// 教学楼名称和教学楼位置匹配规则(中文、字母、数字、下划线以及中括号)
					var jxl_mcAndwz = new RegExp(
							"^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
					// 教学楼代码匹配规则
					var jxl_dm = new RegExp("^[A-Za-z0-9_().]+$");
					// 教学楼楼号匹配规则
					var jxl_lh = new RegExp("^\\d{1,2}$");

					// 对教学楼名称进行验证
					if (jxl_mcAndwz.test($('#jxl_mc').val())) {
						// 对教学楼代码进行验证
						if (jxl_dm.test($('#jxl_dm').val())) {
							// 对教学楼楼号进行验证
							if (jxl_lh.test($('#jxl_lh').val())) {
								// 对教学楼位置进行验证
								if (jxl_mcAndwz.test($('#jxl_wz').val())) {
									return true;
								} else {
									$.messager.alert('提示',
											'请输入教学楼的位置(中文、字母、数字、下划线以及中括号)');
									return false;
								}
							} else {
								$.messager.alert('提示', '请输入教学楼楼号(数字任意2位)');
								return false;
							}
						} else {
							$.messager.alert('提示', '请输入教学楼代码(字母、数字、下划线以及中括号)');
							return false;
						}
					} else {
						$.messager.alert('提示', '请输入教学楼名称(中文、字母、数字、下划线以及中括号)');
						return false;
					}

				},
				success : function(data) {
					if ("false" == data) {
						$.messager.alert('提示', '添加失败！');
					} else {
						var obj2 = eval("(" + data + ")");
						$.messager.alert('提示', obj2.message);
						$('#building_addJXL_form').form('clear');
						$('#addjxl_dlg').dialog('close');
					}
					$('#buildings_datagrid').datagrid('reload');
				}
			});
}

// 打开修改对话框
function updateJXL_dialog() {

	var row = $('#buildings_datagrid').datagrid('getSelected');
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}

	if (row.jxlId) {
		$('#updatejxl_dlg').dialog('open').dialog('setTitle', '修改教学楼信息'); // 弹出窗口
		$.parser.parse('#updatejxl_dlg'); // 需要重新渲染对话框，否则easyui不起作用
		$('#update_jxlId').val(row.jxlId);
		$('#update_jxlmc').val(row.jxlmc);
		$('#update_jxldm').val(row.jxldm);
		$('#update_jxllh').val(row.jxllh);
		$('#update_jxlwz').val(row.jxlwz);
	}
}

function updateJXL() {
	$('#building_updateJXL_form').form('submit', {
		url : 'updateJXL.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {

				$.messager.alert('提示', obj2.message);
				$('#building_updateJXL_form').form('clear');
				$('#updatejxl_dlg').dialog('close');
				$('#buildings_datagrid').datagrid('clearSelections');
				$('#buildings_datagrid').datagrid('reload');
			}
		}
	});
	$('#buildings_datagrid').datagrid('clearSelections');
	$('#buildings_datagrid').datagrid('reload');
	$('#update_jxlId').val("");
	$('#update_jxlmc').val("");
	$('#update_jxldm').val("");
	$('#update_jxllh').val("");
	$('#update_jxlwz').val("");
}

// 删除用户组信息方法 弹出删除对话框：
function delJXL_dialog() {
	$.messager.confirm("删除教学楼信息", " 确认要删除选中的教学楼信息吗？", function(r) {
		if (r) {
			var row = $('#buildings_datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpyhid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				if (0 == tmpi) {
					tmpyhid = row[tmpi].jxlId;
				} else {
					tmpyhid += ',' + row[tmpi].jxlId;
				}
			}
			$.ajax({
				url : "delJXL.action?jxlId=" + tmpyhid,
				context : document.body,
				success : function(data) {
					var yhobj = eval("(" + data + ")");
					$.messager.alert('提示', yhobj.message);
					$('#buildings_datagrid').datagrid('clearSelections');
					$('#buildings_datagrid').datagrid('reload');
				}
			});
		}
	});
	return false;
}

// 初始化方法
$(function() {
	// 显示数据表格
	$('#buildings_datagrid')
			.datagrid(
					{
						url : 'listJXL.action',
						title : '教学楼列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'jxlId',
						columns : [ [
								{
									title : '<b>教学楼名称</b>',
									field : 'jxlmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>教学楼代码</b>',
									field : 'jxldm',
									sortable : true,
									width : 100
								},
								{
									title : '<b>教学楼楼号</b>',
									field : 'jxllh',
									sortable : true,
									width : 100
								},
								{
									title : '<b>教学楼位置</b>',
									field : 'jxlwz',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'jxlId',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJXL_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
});