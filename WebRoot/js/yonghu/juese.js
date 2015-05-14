//模糊查询角色
function doSearch() {
	$('#datagrid').datagrid('load', {
		js_jsmc : $('#jsmc_serchbar').val(),
		js_ms : $('#jsms_serchbar').val(),
		js_bz : $('#jsbz_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增角色信息对话框
function addJS_dialog() {
	$('#addjs_dlg').dialog('open').dialog('setTitle', '新增角色信息'); // 弹出窗口
	$.parser.parse('#addjs_dlg'); // 需要重新渲染对话框，否则easyui不起作用

}
// 新增角色信息
function addJUESE() {
	$('#addJS_form').form('submit', {
		url : 'addJUESE.action',
		onSubmit : function() {
			//角色名称匹配规则
			var jueseNameReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
			//对角色名称进行验证
			if (jueseNameReg.test($('#js_jsmc').val())) {
				return true;
			} else {
				$.messager.alert('提示', '请输入角色名称(字母、数字、下划线以及中括号)');
				return false;
			}
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增角色失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addJS_form').form('clear');
				$('#addjs_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateJS_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.jsid) {
		$.ajax({
			url : "updateJUESE.action?nowtime=" + NowTime + "&js_id="
					+ row.jsid,
			context : document.body,
			success : function(data) {
				var jsobj = eval("(" + data + ")");
				$('#updatejs_dlg').dialog('open').dialog('setTitle',
						'查看/修改角色信息'); // 弹出窗口
				$.parser.parse('#updatejs_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_js_jsmc').val(jsobj.jsmc);
				$('#update_js_ms').val(jsobj.ms);
				$('#update_js_bz').val(jsobj.bz);
				$('#update_js_id').val(jsobj.jsid);
				$('#update_optionflag').val("updatejs");
			}
		});
	}
}
// 修改角色信息
function updateJUESE() {
	$('#updateJS_form').form('submit', {
		url : 'updateJUESE.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改角色信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateJS_form').form('clear');
				$('#updatejs_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');

	$('#update_js_jsmc').val("");
	$('#update_js_id').val("");
	$('#update_js_bz').val("");
	$('#update_js_ms').val("");
	$('#update_optionflag').val("");
}
// 删除角色信息方法 弹出删除对话框：
function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的角色信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpjsid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpjsid = tmpjsid + row[tmpi].jsid + ",";
			}
			$.ajax({
				url : "delJUESE.action?nowtime=" + NowTime + "&js_id="
						+ tmpjsid,
				context : document.body,
				success : function(data) {
					var jsobj = eval("(" + data + ")");
					$.messager.alert('提示', jsobj.message);
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
						url : 'listJUESE.action',
						title : '角色列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'jsid',
						columns : [ [
								{
									title : '<b>角色名称</b>',
									field : 'jsmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>角色描述</b>',
									field : 'ms',
									sortable : true,
									width : 100
								},
								{
									title : '<b>角色备注</b>',
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
									field : 'jsid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJS_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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