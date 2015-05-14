//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		BZ : $('#BZ_serchbar').val(),
		KSRQ : $('#KSRQ_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		JSRQ : $('#JSRQ_serchbar').val(),
		XQMC : $('#XQMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addXUEQI_dialog() {
	$('#addXUEQI_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addXUEQI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addXUEQI() {
	$('#addXUEQI_form')
			.form(
					'submit',
					{
						url : 'addXUEQI.action',
						onSubmit : function() {
							// 学期名称匹配规则
							var xqmcReg = new RegExp(
									"^[\u4E00-\u9FA5A-Za-z0-9_~()]+$");

							// 判断学期名称是不断为空
							if (xqmcReg.test($('#XQMC').val())) {
								// 判断学期的开始时间是否为空
								if ($('#KSRQ').datebox('getText') != "") {
									// 判断学期的结束时间是否为空
									if ($('#JSRQ').datebox('getText') != "") {
										// 判断结束的时间是否大于开始时间
										if ($('#KSRQ').datebox('getValue') < $(
												'#JSRQ').datebox('getValue')) {
											return true;
										} else {
											$.messager.alert('提示',
													'学期的结束时间必须大于学期的开始时间');
											return false;
										}
									} else {
										$.messager.alert('提示', '请输入学期的结束时间');
										return false;
									}
								} else {
									$.messager.alert('提示', '请输入学期的开始时间');
									return false;
								}
							} else {
								$.messager.alert('提示', '请输入学期的名称');
								return false;
							}

						},
						success : function(data) {
							if ("false" == data) {
								$.messager.alert('提示', '新增信息失败！');
							} else {
								var obj2 = eval("(" + data + ")");
								$.messager.alert('提示', obj2.message);
								$('#addXUEQI_form').form('clear');
								$('#addXUEQI_dlg').dialog('close');
							}
							$('#datagrid').datagrid('clearSelections');
							$('#datagrid').datagrid('reload');
						}
					});
}
// 打开修改对话框
function updateXUEQI_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.XQ_ID) {
		$.ajax({
			url : "updateXUEQI.action?nowtime=" + NowTime + "&XQ_ID="
					+ row.XQ_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateXUEQI_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateXUEQI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_KSRQ').val(yhzobj.KSRQ);
				$('#update_MS').val(yhzobj.MS);
				$('#update_JSRQ').val(yhzobj.JSRQ);
				$('#update_XQMC').val(yhzobj.XQMC);
				$('#update_XQ_ID').val(yhzobj.XQ_ID);
				$('#update_optionflag').val('updateXUEQI');
			}
		});
	}
}
// 修改信息
function updateXUEQI() {
	$('#updateXUEQI_form').form('submit', {
		url : 'updateXUEQI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateXUEQI_form').form('clear');
				$('#updateXUEQI_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_BZ').val("");
	$('#update_KSRQ').val("");
	$('#update_MS').val("");
	$('#update_JSRQ').val("");
	$('#update_XQMC').val("");
	$('#update_XQ_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].XQ_ID + ",";
			}
			$.ajax({
				url : "delXUEQI.action?nowtime=" + NowTime + "&XQ_ID="
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
						url : 'listXUEQI.action',
						title : '学期管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'XQ_ID',
						columns : [ [
								{
									title : '<b>学期名称</b>',
									field : 'XQMC',
									sortable : true,
									width : 200
								},
								{
									title : '<b>学期开始日期</b>',
									field : 'KSRQ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学期结束日期</b>',
									field : 'JSRQ',
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
									field : 'XQ_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateXUEQI_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
