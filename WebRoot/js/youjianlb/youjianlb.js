//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		MS : $('#MS_serchbar').val(),
		XINGMING : $('#XINGMING_serchbar').val(),
		YXMC : $('#YXMC_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addYOUJIANLB_dialog() {
	$('#addYOUJIANLB_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addYOUJIANLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
	$('#addYOUJIANLB_form').form('clear');
	$('#datagrid').datagrid('clearSelections');
}
// 新增信息
function addYOUJIANLB() {
	$('#addYOUJIANLB_form').form('submit', {
		url : 'addYOUJIANLB.action',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var o = eval("(" + data + ")");
			$.messager.alert('提示', o.message);
			if (o.success) {
				$('#addYOUJIANLB_form').form('clear');
				$('#addYOUJIANLB_dlg').dialog('close');
				$('#datagrid').datagrid('clearSelections');
				$('#datagrid').datagrid('reload');
			}
		}
	});
}
// 打开修改对话框
function updateYOUJIANLB_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.YOUJIANLB_ID) {
		$.ajax({
			url : "updateYOUJIANLB.action?nowtime=" + NowTime + "&YOUJIANLB_ID=" + row.YOUJIANLB_ID,
			context : document.body,
			cache : false,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateYOUJIANLB_dlg').dialog('open').dialog('setTitle', '查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateYOUJIANLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_MS').val(yhzobj.MS);
				$('#update_XINGMING').val(yhzobj.XINGMING);
				$('#update_YXMC').val(yhzobj.YXMC);
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_YOUJIANLB_ID').val(yhzobj.YOUJIANLB_ID);
				$('#update_optionflag').val('updateYOUJIANLB');
			}
		});
	}
}
// 修改信息
function updateYOUJIANLB() {
	$('#updateYOUJIANLB_form').form('submit', {
		url : 'updateYOUJIANLB.action',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var o = eval("(" + data + ")");
			$.messager.alert('提示', o.message);
			if (o.success) {
				$('#updateYOUJIANLB_form').form('clear');
				$('#updateYOUJIANLB_dlg').dialog('close');
				$('#datagrid').datagrid('clearSelections');
				$('#datagrid').datagrid('reload');
				$('#update_MS').val("");
				$('#update_XINGMING').val("");
				$('#update_YXMC').val("");
				$('#update_BZ').val("");
				$('#update_YOUJIANLB_ID').val("");
				$('#update_optionflag').val("");
			}
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
				tmpyhzid = tmpyhzid + row[tmpi].YOUJIANLB_ID + ",";
			}
			$.ajax({
				url : "delYOUJIANLB.action?nowtime=" + NowTime + "&YOUJIANLB_ID=" + tmpyhzid,
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
	$('#datagrid').datagrid({
		url : 'listYOUJIANLB.action',
		title : '邮件管理列表',
		iconCls : 'icon-cls',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false,
		nowrap : false,
		border : false,
		idField : 'YOUJIANLB_ID',
		columns : [ [ {
			title : '<b>邮箱</b>',
			field : 'YXMC',
			sortable : true,
			width : 150
		}, {
			title : '<b>姓名</b>',
			field : 'XINGMING',
			sortable : true,
			width : 150
		}, {
			title : '<b>描述</b>',
			field : 'MS',
			sortable : true,
			width : 100
		}, {
			title : '<b>备注</b>',
			field : 'BZ',
			sortable : true,
			width : 100
		}, {
			title : '<b>操作</b>',
			field : 'YOUJIANLB_ID',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return "<a style='cursor:hand;' onclick='updateYOUJIANLB_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
