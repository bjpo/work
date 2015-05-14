//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		NJMC : $('#NJMC_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		TMP6 : $('#TMP6_serchbar').val(),
		TMP5 : $('#TMP5_serchbar').val(),
		TMP4 : $('#TMP4_serchbar').val(),
		NJDM : $('#NJDM_serchbar').val(),
		TMP3 : $('#TMP3_serchbar').val(),
		TMP2 : $('#TMP2_serchbar').val(),
		TMP1 : $('#TMP1_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addNIANJI_dialog() {
	$('#addNIANJI_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addNIANJI_dlg');
}
// 新增信息
function addNIANJI() {
	$('#addNIANJI_form').form('submit', {
		url : 'addNIANJI.action',
		onSubmit : function() {
			var njNameReg=new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_~()]+$");
			//对年级名称进行验证
			if (njNameReg.test($('#NJMC').val())) {
				//对年级代码进行验证
				if (njNameReg.test($('#NJDM').val())) {
					return true;
				}else{
					$.messager.alert('提示','请输入年级的代码(字母、数字、下划线)');
					return false;
				}
			}else{
				$.messager.alert('提示','请输入年级名称(字母、数字、下划线、中括号)');
				return false;
			}
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if (obj2.success) {
				$('#addNIANJI_form').form('clear');
				$('#addNIANJI_dlg').dialog('close');
			}
			$.messager.alert('提示', obj2.message);
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateNIANJI_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof(row) == "undefined") { 
		return ;
	}
	if (!row && typeof(row)!="undefined" && row!=0){   
		   return ;
	}
	if (row.NJ_ID) {
		$('#updateNIANJI_dlg').dialog('open').dialog('setTitle', '修改信息');
		$.parser.parse('#updateNIANJI_dlg');
		$('#update_NJ_ID').val(row.NJ_ID);
		$('#update_NJMC').val(row.NJMC);
		$('#update_NJDM').val(row.NJDM);
		$('#update_BZ').val(row.BZ);
		$('#update_MS').val(row.MS);
	}
}
// 修改信息
function updateNIANJI() {
	$('#updateNIANJI_form').form('submit', {
		url : 'updateNIANJI.action',
		onSubmit : function() {
		},
		success : function(data) {
			var obj2 = eval("(" + data + ")");
			if (obj2.success) {
				$('#updateNIANJI_form').form('clear');
				$('#updateNIANJI_dlg').dialog('close');
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
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpyhzid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpyhzid = tmpyhzid + row[tmpi].NJ_ID + ",";
			}
			$.ajax({
				url : "delNIANJI.action?NJ_ID="+ tmpyhzid,
				context : document.body,
				success : function(data) {
					var obj = eval("(" + data + ")");
					if (obj.success) {
						$('#datagrid').datagrid('clearSelections');
						$('#datagrid').datagrid('reload');
					}
					$.messager.alert('提示', obj.message);
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
						url : 'listNIANJI.action',
						title : '年级管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'NJ_ID',
						columns : [ [
								{
									title : '<b>年级名称</b>',
									field : 'NJMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>年级代码</b>',
									field : 'NJDM',
									sortable : true,
									width : 100
								},								{
									title : '<b>备注</b>',
									field : 'BZ',
									sortable : true,
									width : 100
								},										{
									title : '<b>描述</b>',
									field : 'MS',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'NJ_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateNIANJI_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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

