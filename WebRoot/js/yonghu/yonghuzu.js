//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		yhz_yhzmc : $('#yhzmc_serchbar').val(),
		yhz_bz : $('#yhzbz_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增用户组信息对话框
function addYHZ_dialog() {
	$('#addyhz_dlg').dialog('open').dialog('setTitle', '新增用户组信息'); // 弹出窗口
	$.parser.parse('#addyhz_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增用户组信息
function addYHZ() {
	$('#addYHZ_form').form('submit', {
		url : 'addYHZ.action',
		onSubmit : function() {
			// 用户组名称匹配规则
			var yhz_yhzmcReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
			//用户组名称验证
			if (yhz_yhzmcReg.test($('#yhz_yhzmc').val())) {
				return true;
			} else {
				$.messager.alert('提示', '请输入用户组名称(字母、数字、下划线以及中括号)');
				return false;
			}
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增用户组信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addYHZ_form').form('clear');
				$('#addyhz_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateYHZ_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.yhzid) {
		$.ajax({
			url : "updateYHZ.action?nowtime=" + NowTime + "&yhz_id="
					+ row.yhzid,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateyhz_dlg').dialog('open').dialog('setTitle',
						'查看/修改用户组信息'); // 弹出窗口
				$.parser.parse('#updateyhz_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_yhz_yhzmc').val(yhzobj.yhzmc);
				$('#update_yhz_id').val(yhzobj.yhzid);
				$('#update_yhz_bz').val(yhzobj.bz);
				$('#update_optionflag').val("updateyhz");
			}
		});
	}
}
// 修改用户组信息
function updateYHZ() {
	$('#updateYHZ_form').form('submit', {
		url : 'updateYHZ.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改用户组信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateYHZ_form').form('clear');
				$('#updateyhz_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');

	$('#update_yhz_yhzmc').val("");
	$('#update_yhz_id').val("");
	$('#update_yhz_bz').val("");
	$('#update_optionflag').val("");
}
// 删除用户组信息方法 弹出删除对话框：

function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的用户组信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');

				return;
			}
			var tmpyhzid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpyhzid = tmpyhzid + row[tmpi].yhzid + ",";
			}
			$.ajax({
				url : "delYHZ.action?nowtime=" + NowTime + "&yhz_id="
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
	// 显示数据表格
	$('#datagrid')
			.datagrid(
					{
						url : 'listYHZ.action',
						title : '用户组管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'yhzid',
						columns : [ [
								{
									title : '<b>用户组名称</b>',
									field : 'yhzmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>备注信息</b>',
									field : 'bz',
									sortable : true,
									width : 100
								},
								{
									title : '<b>登记日期</b>',
									field : 'djrq',
									sortable : true,
									width : 200
								},
								{
									title : '<b>修改日期</b>',
									field : 'xgrq',
									sortable : true,
									width : 200
								},
								{
									title : '<b>操作</b>',
									field : 'yhzid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateYHZ_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
										// return "<a
										// onclick='showEnterDialog();'><img
										// src='js/jquery-easyui/themes/icons/cancel.png'
										// style='border:0px;'/></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
										// style='cursor:hand;'
										// onclick='updateYHZ_dialog();'><img
										// src='js/jquery-easyui/themes/icons/pencil.png'
										// style='border:0px;'/></a>";
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
	// $('#DIV_toolbar').appendTo('.datagrid-toolbar');// 加上搜索条
	$('#datagrid').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});