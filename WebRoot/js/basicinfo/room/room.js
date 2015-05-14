//模糊查询用户
function doSearch() {
	$('#datagrid').datagrid('load', {
		fjmc : $('#fjmc_serchbar').val()
	});
}
// 打开新增用户信息对话框
function addFJ_dialog() {
	$('#add_dlg').dialog('open').dialog('setTitle', '添加房间'); // 弹出窗口
	$('#fj_mc').val("");
	$('#fj_dm').val("");
	$('#fj_dz').val("");
	$('#fj_h').val("");
	$('#fj_lc').val("");
	$('#fj_rs').val("");
	$('#fj_mt1').attr("checked", 'checked');
	$('#addFJ_combobox').combobox({
		url : 'listJXLforFJ.action',
		valueField : 'jxlId',
		textField : 'jxlmc'
	});
	$.parser.parse('#add_dlg');
}

// 新增房间信息
function addFJ() {
	$('#add_form').form('submit', {
		url : 'addFJ.action',
		onSubmit : function() {
			// 房间名称匹配规则
			var roomNameReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
			// 房间号和楼层匹配规则
			var roomNoAndFloorReg = new RegExp("^\\d+$");
			// 房间名称进行验证
			if (roomNameReg.test($('#fj_mc').val())) {
				// 判断房间的位置文本框是否为空
				if ($('#fjdz').val() != "") {
					// 对房间号进行验证
					if (roomNoAndFloorReg.test($('#fj_h').val())) {
						// 对楼层号进行验证
						if (roomNoAndFloorReg.test($('#fj_lc').val())) {
							// 对可容纳人数进行验证
							if (roomNoAndFloorReg.test($('#fj_rs').val())) {
								// 判断教学楼下列表框是否为空
								if ($('#addFJ_combobox').combobox('getText')!="") {
									return true;
								} else {
									$.messager.alert('提示', '请选择教学楼');
									return false;
								}

							} else {
								$.messager.alert('提示', '请输入房间可容纳的人数');
								return false;
							}
						} else {
							$.messager.alert('提示', '请输入正确的楼层号');
							return false;
						}
					} else {
						$.messager.alert('提示', '请输入正确的房间号');
						return false;
					}
				} else {
					$.messager.alert('提示', '请输入房间的位置');
					return false;

				}
			} else {
				$.messager.alert('提示', '请输入房间的名称');
				return false;
			}
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '添加失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#add_form').form('clear');
				$('#add_dlg').dialog('close');
			}
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateFJ_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.fjId) {
		$('#updateFJ_dlg').dialog('open').dialog('setTitle', '查看/修改用户信息'); // 弹出窗口
		$('#update_fjId').val(row.fjId);
		$('#update_fjmc').val(row.fjmc);
		$('#update_fjdz').val(row.fjdz);
		$('#update_fjh').val(row.fjh);
		$('#update_louceng').val(row.louceng);
		if ('有' == row.isdmt) {
			$('#update_isdmt1').attr("checked", 'checked');
		} else if ('无' == row.isdmt) {
			$('#update_isdmt2').attr("checked", 'checked');
		}
		$('#update_renshu').val(row.renshu);
		$('#update_combobox').combobox({
			url : 'listJXLforFJ.action',
			valueField : 'jxlId',
			textField : 'jxlmc'
		});
		$('#update_combobox').combobox('select', row.jxlId);
		$.parser.parse('#updatefj_dlg');
	}
}

function updateFJ() {
	$('#updateFJ_form').form('submit', {
		url : 'updateFJ.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				$.messager.alert('提示', obj2.message);
				$('#updateFJ_form').form('clear');
				$('#updateFJ_dlg').dialog('close');
				$('#datagrid').datagrid('clearSelections');
				$('#datagrid').datagrid('reload');
			}
		}
	});
	$('#update_fjId').val("");
	$('#update_fjmc').val("");
	$('#update_fjdz').val("");
	$('#update_fjh').val("");
	$('#update_louceng').val("");
	$('#update_isdmt').val("");
	$('#update_renshu').val("");
	$('#update_combobox').combobox('clear');
}

// 删除用户组信息方法 弹出删除对话框：
function delFJ_dialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的房间信息吗？", function(r) {
		if (r) {
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var IdStr = "";
			for ( var i = 0; i < row.length; i++) {
				if (0 == i) {
					IdStr = row[i].fjId;
				} else {
					IdStr += ',' + row[i].fjId;
				}

			}
			$.ajax({
				url : "delFJ.action?fjId=" + IdStr,
				context : document.body,
				success : function(data) {
					var obj = eval("(" + data + ")");
					$.messager.alert('提示', obj.message);
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
						url : 'listFJ.action',
						title : '房间管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'fjId',
						columns : [ [
								{
									title : '<b>房间名称</b>',
									field : 'fjmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>房间代码</b>',
									field : 'fjdm',
									sortable : true,
									width : 100
								},
								{
									title : '<b>房间地址</b>',
									field : 'fjdz',
									sortable : true,
									width : 100
								},
								{
									title : '<b>房间号</b>',
									field : 'fjh',
									sortable : true,
									width : 100
								},
								{
									title : '<b>有无多媒体</b>',
									field : 'isdmt',
									sortable : true,
									width : 100
								},
								{
									title : '<b>可容纳人数</b>',
									field : 'renshu',
									sortable : true,
									width : 100
								},
								{
									title : '<b>所在楼层</b>',
									field : 'louceng',
									sortable : true,
									width : 100
								},
								{
									title : '<b>教学楼ID</b>',
									field : 'jxlId',
									sortable : true,
									hidden : true
								},
								{
									title : '<b>所在教学楼</b>',
									field : 'jxlMc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>所在楼号</b>',
									field : 'jxllh',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'fjId',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateFJ_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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