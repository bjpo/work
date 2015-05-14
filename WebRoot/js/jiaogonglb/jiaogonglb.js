//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		BZ : $('#BZ_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		JIAOGONGLBMC : $('#JIAOGONGLBMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addJIAOGONGLB_dialog() {
	$('#addJIAOGONGLB_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addJIAOGONGLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addJIAOGONGLB() {
	$('#addJIAOGONGLB_form').form('submit', {
		url : 'addJIAOGONGLB.action',
		onSubmit : function() {
			/*
			 * 提交之前对教工类别名称进行验证
			 * 教工类别名称  必须为中文、英文、数字包括下划线
			 */
			var res = "^[\u4E00-\u9FA5A-Za-z0-9_]+$";
			var reg = new RegExp(res);
			if (reg.test($('#JIAOGONGLBMC').val())) {
				return true;
			} else {
				$.messager.alert('提示', '教工类别名称不能有特殊字符，如：空格，$，！等');
				return false;
			}
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addJIAOGONGLB_form').form('clear');
				$('#addJIAOGONGLB_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateJIAOGONGLB_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.JIAOGONGLB_ID) {
		$.ajax({
			url : "updateJIAOGONGLB.action?nowtime=" + NowTime
					+ "&JIAOGONGLB_ID=" + row.JIAOGONGLB_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateJIAOGONGLB_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateJIAOGONGLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_MS').val(yhzobj.MS);
				$('#update_JIAOGONGLBMC').val(yhzobj.JIAOGONGLBMC);
				$('#update_JIAOGONGLB_ID').val(yhzobj.JIAOGONGLB_ID);
				$('#update_optionflag').val('updateJIAOGONGLB');
			}
		});
	}
}
// 修改信息
function updateJIAOGONGLB() {
	$('#updateJIAOGONGLB_form').form('submit', {
		url : 'updateJIAOGONGLB.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateJIAOGONGLB_form').form('clear');
				$('#updateJIAOGONGLB_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_BZ').val("");
	$('#update_MS').val("");
	$('#update_JIAOGONGLBMC').val("");
	$('#update_JIAOGONGLB_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].JIAOGONGLB_ID + ",";
			}
			$.ajax({
				url : "delJIAOGONGLB.action?nowtime=" + NowTime
						+ "&JIAOGONGLB_ID=" + tmpyhzid,
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
						url : 'listJIAOGONGLB.action',
						title : '教工类别管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'JIAOGONGLB_ID',
						columns : [ [
								{
									title : '<b>教工类别名称</b>',
									field : 'JIAOGONGLBMC',
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
									field : 'JIAOGONGLB_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJIAOGONGLB_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
