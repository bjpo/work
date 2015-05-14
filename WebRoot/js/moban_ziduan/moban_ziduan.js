//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		LIE : $('#LIE_serchbar').val(),
		ZIDUAN_ID : $('#ZIDUAN_ID_serchbar').val(),
		MB_ID : $('#MB_ID_serchbar').val(),
		ZIDUANMC : $('#ZIDUANMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addMOBAN_ZIDUAN_dialog() {
	$('#addMOBAN_ZIDUAN_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addMOBAN_ZIDUAN_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addMOBAN_ZIDUAN() {
	$('#addMOBAN_ZIDUAN_form').form('submit', {
		url : 'addMOBAN_ZIDUAN.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addMOBAN_ZIDUAN_form').form('clear');
				$('#addMOBAN_ZIDUAN_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateMOBAN_ZIDUAN_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.ZIDUAN_ID) {
		$.ajax({
			url : "updateMOBAN_ZIDUAN.action?nowtime=" + NowTime
					+ "&ZIDUAN_ID=" + row.ZIDUAN_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateMOBAN_ZIDUAN_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateMOBAN_ZIDUAN_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_LIE').val(yhzobj.LIE);
				$('#update_ZIDUAN_ID').val(yhzobj.ZIDUAN_ID);
				$('#update_MB_ID').val(yhzobj.MB_ID);
				$('#update_ZIDUANMC').val(yhzobj.ZIDUANMC);
				$('#update_ZIDUAN_ID').val(yhzobj.ZIDUAN_ID);
				$('#update_optionflag').val('updateMOBAN_ZIDUAN');
			}
		});
	}
}
// 修改信息
function updateMOBAN_ZIDUAN() {
	$('#updateMOBAN_ZIDUAN_form').form('submit', {
		url : 'updateMOBAN_ZIDUAN.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateMOBAN_ZIDUAN_form').form('clear');
				$('#updateMOBAN_ZIDUAN_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_LIE').val("");
	$('#update_ZIDUAN_ID').val("");
	$('#update_MB_ID').val("");
	$('#update_ZIDUANMC').val("");
	$('#update_ZIDUAN_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].ZIDUAN_ID + ",";
			}
			$.ajax({
				url : "delMOBAN_ZIDUAN.action?nowtime=" + NowTime
						+ "&ZIDUAN_ID=" + tmpyhzid,
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
// // 初始化方法
// $(function() {
// $('#datagrid')
// .datagrid(
// {
// url : 'listMOBAN_ZIDUAN.action',
// title : '所有',
// iconCls : 'icon-cls',
// pageSize : 10,
// pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
// fitColumns : false,
// nowrap : false,
// border : false,
// idField : 'ZIDUAN_ID',
// columns : [ [
// {
// title : '<b>字段1</b>',
// field : 'LIE',
// sortable : true,
// width : 100
// }, {
// title : '<b>字段2</b>',
// field : 'ZIDUAN_ID',
// sortable : true,
// width : 100
// }, {
// title : '<b>字段3</b>',
// field : 'MB_ID',
// sortable : true,
// width : 100
// }, {
// title : '<b>字段4</b>',
// field : 'ZIDUANMC',
// sortable : true,
// width : 100
// },
// {
// title : '<b>操作</b>',
// field : 'ZIDUAN_ID',
// width : 100,
// formatter : function(value, rowData,
// rowIndex) {
// return "<a style='cursor:hand;' onclick='updateMOBAN_ZIDUAN_dialog();'><img
// src='js/jquery-easyui/themes/icons/pencil.png' alt='修改'
// style='border:0px;'/></a>";
// }
// } ] ],
// singleSelect : false,// 是否单选
// pagination : true,// 分页控件
// rownumbers : true,// 行号
// frozenColumns : [ [ {
// field : 'ck',
// checkbox : true
// } ] ]
// });
// $('#datagrid').datagrid('getPager').pagination({
// pageSize : 10,// 每页显示的记录条数，默认为10
// pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
// beforePageText : '第',// 页数文本框前显示的汉字
// afterPageText : '页 共 {pages} 页',
// displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录'
// });
// });

