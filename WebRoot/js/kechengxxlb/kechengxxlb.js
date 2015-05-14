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
		BZ : $('#BZ_serchbar').val(),
		KECHENGXXLBMC : $('#KECHENGXXLBMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addKECHENGXXLB_dialog() {
	$('#addKECHENGXXLB_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addKECHENGXXLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addKECHENGXXLB() {
	$('#addKECHENGXXLB_form').form(
			'submit',
			{
				url : 'addKECHENGXXLB.action',
				onSubmit : function() {
					// 课程信息类型名称匹配规则
					var KECHENGXXLBMCReg = new RegExp(
							"^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
					// 对课程信息类型名称进行验证
					if (KECHENGXXLBMCReg.test($('#KECHENGXXLBMC').val())) {
						return true;
					} else {
						$.messager.alert('提示', '请输入课程信息类别名称(字母、数字、下划线、中括号)');
						return false;
					}

				},
				success : function(data) {
					if ("false" == data) {
						$.messager.alert('提示', '新增信息失败！');
					} else {
						var obj2 = eval("(" + data + ")");
						$.messager.alert('提示', obj2.message);
						$('#addKECHENGXXLB_form').form('clear');
						$('#addKECHENGXXLB_dlg').dialog('close');
					}
					$('#datagrid').datagrid('clearSelections');
					$('#datagrid').datagrid('reload');
				}
			});
}
// 打开修改对话框
function updateKECHENGXXLB_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.KECHENGXXLB_ID) {
		$.ajax({
			url : "updateKECHENGXXLB.action?nowtime=" + NowTime
					+ "&KECHENGXXLB_ID=" + row.KECHENGXXLB_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateKECHENGXXLB_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateKECHENGXXLB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_MS').val(yhzobj.MS);
				$('#update_TMP6').val(yhzobj.TMP6);
				$('#update_TMP5').val(yhzobj.TMP5);
				$('#update_TMP4').val(yhzobj.TMP4);
				$('#update_TMP3').val(yhzobj.TMP3);
				$('#update_TMP2').val(yhzobj.TMP2);
				$('#update_TMP1').val(yhzobj.TMP1);
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_KECHENGXXLBMC').val(yhzobj.KECHENGXXLBMC);
				$('#update_KECHENGXXLB_ID').val(yhzobj.KECHENGXXLB_ID);
				$('#update_optionflag').val('updateKECHENGXXLB');
			}
		});
	}
}
// 修改信息
function updateKECHENGXXLB() {
	$('#updateKECHENGXXLB_form').form('submit', {
		url : 'updateKECHENGXXLB.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateKECHENGXXLB_form').form('clear');
				$('#updateKECHENGXXLB_dlg').dialog('close');
			}
		}
	});

	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_MS').val("");
	$('#update_TMP6').val("");
	$('#update_TMP5').val("");
	$('#update_TMP4').val("");
	$('#update_TMP3').val("");
	$('#update_TMP2').val("");
	$('#update_TMP1').val("");
	$('#update_BZ').val("");
	$('#update_KECHENGXXLBMC').val("");
	$('#update_KECHENGXXLB_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].KECHENGXXLB_ID + ",";
			}
			$.ajax({
				url : "delKECHENGXXLB.action?nowtime=" + NowTime
						+ "&KECHENGXXLB_ID=" + tmpyhzid,
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
						url : 'listKECHENGXXLB.action',
						title : '课程信息类别管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'KECHENGXXLB_ID',
						columns : [ [
								{
									title : '<b>课程信息类别名称</b>',
									field : 'KECHENGXXLBMC',
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
									title : '<b>备注</b>',
									field : 'BZ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'KECHENGXXLB_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateKECHENGXXLB_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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