//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		JSMC : $('#JSMC_serchbar').val(),
		FJMC : $('#FJMC_serchbar').val(),
		ISDMT : $('#ISDMT_serchbar').val(),
		FJDM : $('#FJDM_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addJIAOSHI_dialog() {
	$('#addJIAOSHI_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addJIAOSHI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addJIAOSHI() {
	$('#addJIAOSHI_form').form('submit', {
		url : 'addJIAOSHI.action',
		onSubmit : function() {
			// 教室名称匹配规则
			var jsmcReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
			if (jsmcReg.test($('#JSMC').val())) {
				return true;
			} else {
				$.messager.alert('提示', '请输入正确的教室(中文，字母，数字,下划线以及中括号)');
				return false;
			}
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if (obj2.success) {
				$('#addJIAOSHI_form').form('clear');
				$('#addJIAOSHI_dlg').dialog('close');
			}
			$.messager.alert('提示', obj2.message);
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateJIAOSHI_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.JS_ID) {
		$('#updateJIAOSHI_dlg').dialog('open').dialog('setTitle', '修改信息');
		$.parser.parse('#updateJIAOSHI_dlg');
		$('#update_JS_ID').val(row.JS_ID);
		$('#update_JSMC').val(row.JSMC);
		$('#update_FJID').combobox('select', row.FJID);
	}
}
// 修改信息
function updateJIAOSHI() {
	$('#updateJIAOSHI_form').form('submit', {
		url : 'updateJIAOSHI.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if (obj2.success) {
				$('#updateJIAOSHI_form').form('clear');
				$('#updateJIAOSHI_dlg').dialog('close');
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
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpyhzid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpyhzid = tmpyhzid + row[tmpi].JS_ID + ",";
			}
			$.ajax({
				url : "delJIAOSHI.action?JS_ID=" + tmpyhzid,
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
						url : 'listJIAOSHI.action',
						title : '教室管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'JS_ID',
						columns : [ [
								{
									title : '<b>教室名称</b>',
									field : 'JSMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>房间名称</b>',
									field : 'FJMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>房间ID</b>',
									field : 'FJID',
									sortable : true,
									width : 100,
									hidden : true
								},
								{
									title : '<b>有无多媒体</b>',
									field : 'ISDMT',
									sortable : true,
									width : 100
								},
								{
									title : '<b>房间代码</b>',
									field : 'FJDM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'JS_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJIAOSHI_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
