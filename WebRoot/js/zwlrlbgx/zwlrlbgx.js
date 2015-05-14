//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		ZWLRBIAO_TMP4 : $('#ZWLRBIAO_TMP4_serchbar').val(),
		ZWLRBIAO_TMP3 : $('#ZWLRBIAO_TMP3_serchbar').val(),
		ZWLRBIAO_TMP2 : $('#ZWLRBIAO_TMP2_serchbar').val(),
		ZWLRBIAO_TMP1 : $('#ZWLRBIAO_TMP1_serchbar').val(),
		ZWLRBIAO : $('#ZWLRBIAO_serchbar').val(),
		ZWLR_MC : $('#ZWLR_MC_serchbar').val(),
		ZWLRBIAO_ID : $('#ZWLRBIAO_ID_serchbar').val(),
		ZWLRBIAO_MC : $('#ZWLRBIAO_MC_serchbar').val(),
		ZWLRBIAO_TMP9 : $('#ZWLRBIAO_TMP9_serchbar').val(),
		ZWLRBIAO_HM : $('#ZWLRBIAO_HM_serchbar').val(),
		ZWLRBIAO_TMP8 : $('#ZWLRBIAO_TMP8_serchbar').val(),
		ZWLRBIAO_TMP7 : $('#ZWLRBIAO_TMP7_serchbar').val(),
		ZWLRBIAO_TMP6 : $('#ZWLRBIAO_TMP6_serchbar').val(),
		PAIXU : $('#PAIXU_serchbar').val(),
		ZWLRBIAO_TMP5 : $('#ZWLRBIAO_TMP5_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addZWLRLBGX_dialog() {
	$('#addZWLRLBGX_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addZWLRLBGX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addZWLRLBGX() {
	$('#addZWLRLBGX_form').form('submit', {
		url : 'addZWLRLBGX.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addZWLRLBGX_form').form('clear');
				$('#addZWLRLBGX_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateZWLRLBGX_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof(row) == "undefined") { 
		return ;
	}
	if (!row && typeof(row)!="undefined" && row!=0){   
		   return ;
	}
	if (row.ZWLRGX_ID) {
		$.ajax({
			url : "updateZWLRLBGX.action?nowtime=" + NowTime + "&ZWLRGX_ID="
					+ row.ZWLRGX_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateZWLRLBGX_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateZWLRLBGX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_ZWLRBIAO_TMP4').val(yhzobj.ZWLRBIAO_TMP4);
				$('#update_ZWLRBIAO_TMP3').val(yhzobj.ZWLRBIAO_TMP3);
				$('#update_ZWLRBIAO_TMP2').val(yhzobj.ZWLRBIAO_TMP2);
				$('#update_ZWLRBIAO_TMP1').val(yhzobj.ZWLRBIAO_TMP1);
				$('#update_ZWLRBIAO').val(yhzobj.ZWLRBIAO);
				$('#update_ZWLR_MC').val(yhzobj.ZWLR_MC);
				$('#update_ZWLRBIAO_ID').val(yhzobj.ZWLRBIAO_ID);
				$('#update_ZWLRBIAO_MC').val(yhzobj.ZWLRBIAO_MC);
				$('#update_ZWLRBIAO_TMP9').val(yhzobj.ZWLRBIAO_TMP9);
				$('#update_ZWLRBIAO_HM').val(yhzobj.ZWLRBIAO_HM);
				$('#update_ZWLRBIAO_TMP8').val(yhzobj.ZWLRBIAO_TMP8);
				$('#update_ZWLRBIAO_TMP7').val(yhzobj.ZWLRBIAO_TMP7);
				$('#update_ZWLRBIAO_TMP6').val(yhzobj.ZWLRBIAO_TMP6);
				$('#update_PAIXU').val(yhzobj.PAIXU);
				$('#update_ZWLRBIAO_TMP5').val(yhzobj.ZWLRBIAO_TMP5);
				$('#update_ZWLRGX_ID').val(yhzobj.ZWLRGX_ID);
				$('#update_optionflag').val('updateZWLRLBGX');
			}
		});
	}
}
// 修改信息
function updateZWLRLBGX() {
	$('#updateZWLRLBGX_form').form('submit', {
		url : 'updateZWLRLBGX.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateZWLRLBGX_form').form('clear');
				$('#updateZWLRLBGX_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_ZWLRBIAO_TMP4').val("");
	$('#update_ZWLRBIAO_TMP3').val("");
	$('#update_ZWLRBIAO_TMP2').val("");
	$('#update_ZWLRBIAO_TMP1').val("");
	$('#update_ZWLRBIAO').val("");
	$('#update_ZWLR_MC').val("");
	$('#update_ZWLRBIAO_ID').val("");
	$('#update_ZWLRBIAO_MC').val("");
	$('#update_ZWLRBIAO_TMP9').val("");
	$('#update_ZWLRBIAO_HM').val("");
	$('#update_ZWLRBIAO_TMP8').val("");
	$('#update_ZWLRBIAO_TMP7').val("");
	$('#update_ZWLRBIAO_TMP6').val("");
	$('#update_PAIXU').val("");
	$('#update_ZWLRBIAO_TMP5').val("");
	$('#update_ZWLRGX_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].ZWLRGX_ID + ",";
			}
			$.ajax({
				url : "delZWLRLBGX.action?nowtime=" + NowTime + "&ZWLRGX_ID="
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
						url : 'listZWLRLBGX.action',
						title : '指纹录入列表-关系表列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'ZWLRGX_ID',
						columns : [ [
								{
									title : '<b>字段1</b>',
									field : 'ZWLRBIAO_TMP4',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段2</b>',
									field : 'ZWLRBIAO_TMP3',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段3</b>',
									field : 'ZWLRBIAO_TMP2',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段4</b>',
									field : 'ZWLRBIAO_TMP1',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段5</b>',
									field : 'ZWLRBIAO',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段6</b>',
									field : 'ZWLR_MC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段7</b>',
									field : 'ZWLRBIAO_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段8</b>',
									field : 'ZWLRBIAO_MC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段9</b>',
									field : 'ZWLRBIAO_TMP9',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段10</b>',
									field : 'ZWLRBIAO_HM',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段11</b>',
									field : 'ZWLRBIAO_TMP8',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段12</b>',
									field : 'ZWLRBIAO_TMP7',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段13</b>',
									field : 'ZWLRBIAO_TMP6',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段14</b>',
									field : 'PAIXU',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段15</b>',
									field : 'ZWLRBIAO_TMP5',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'ZWLRGX_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateZWLRLBGX_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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

