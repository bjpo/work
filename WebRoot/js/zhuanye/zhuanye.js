//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		MS : $('#MS_serchbar').val(),
		XYID : $('#XYID_serchbar').val(),
		ZYMC : $('#ZYMC_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		XYMC : $('#XYMC_serchbar').val(),
		ZYDM : $('#ZYDM_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
var addXyName = null;
var addXyId = null;
function addZHUANYE_dialog() {
	$('#addZHUANYE_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$('#XYMC').combobox({
		panelWidth : 'auto',
		onSelect : function(rec) {
			addXyName = $(this).combobox('getText');
			addXyId = $(this).combobox('getValue');
		}
	});

	$.parser.parse('#addZHUANYE_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息

function addZHUANYE() {
	$('#addZHUANYE_form')
			.form(
					'submit',
					{
						url : 'addZHUANYE.action?addXyName='
								+ encodeURI(encodeURI(addXyName)) + "&XYID="
								+ addXyId,
						onSubmit : function() {
							/*
							 * //只允许出现中文 var
							 * res="^[\u4E00-\uFA29]|[\uE7C7-\uE7F3]$";
							 */
							// 中文、英文、数字包括下划线
							var zyNameReg = new RegExp(
									"^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
							var zyDmReg = new RegExp("^\\w+$");

							// 对专业名称进行验证
							if (zyNameReg.test($('#ZYMC').val())) {
								// 对专业代码进行验证
								if (zyDmReg.test($('#ZYDM').val())) {
									// 判断学院的名称是否符合要求
									if ($('#XYMC').combobox('getText') != "") {
										return true;
									} else {
										// 不符全要求时进行提示
										$.messager.alert('提示',
												'请选择学院名称');
										return false;
									}
								} else {
									// 专业代码为空时进行提示
									$.messager.alert('提示', '专业代码不能为空或含有特殊字符！');
									return false;
								}
							} else {
								// 学院名称为空时进行提示
								$.messager.alert('提示', '专业名称不能为空或包含有特殊字符！');
								return false;
							}

						},
						success : function(data) {
							if ("false" == data) {
								$.messager.alert('提示', '新增信息失败！');
							} else {
								var obj2 = eval("(" + data + ")");
								$.messager.alert('提示', obj2.message);
								$('#addZHUANYE_form').form('clear');
								$('#addZHUANYE_dlg').dialog('close');
							}
							$('#datagrid').datagrid('clearSelections');
							$('#datagrid').datagrid('reload');
						}
					});
}
// 打开修改对话框
var updateXyName = null;
function updateZHUANYE_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	$('#update_XYMC').combobox({
		url : 'listAllXUEYUAN.action',
		panelWidth : 'auto',
		valueField : 'xyid',
		textField : 'xymc',
		onSelect : function(rec) {
			updateXyName = $(this).combobox('getText');
                                                      $('#update_XYMC').combobox('setValue', updateXyName);
		}
	});
	if (row.ZY_ID) {
		$.ajax({
			url : "updateZHUANYE.action?nowtime=" + NowTime + "&ZY_ID="
					+ row.ZY_ID + "&updateXyName"
					+ encodeURI(encodeURI(updateXyName)),
			context : document.body,
			success : function(data) {
				var zyobj = eval("(" + data + ")");
				$('#updateZHUANYE_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateZHUANYE_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_MS').val(zyobj.MS);
				$('#update_XYID').val(zyobj.XYID);
				$('#update_ZYMC').val(zyobj.ZYMC);
				$('#update_BZ').val(zyobj.BZ);
				$('#update_XYMC').combobox('setValue', zyobj.XYMC);
				$('#update_ZYDM').val(zyobj.ZYDM);
				$('#update_ZY_ID').val(zyobj.ZY_ID);
				$('#update_optionflag').val('updateZHUANYE');
			}
		});
	}
}
// 修改信息
function updateZHUANYE() {
	$('#updateZHUANYE_form').form('submit', {
		url : 'updateZHUANYE.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateZHUANYE_form').form('clear');
				$('#updateZHUANYE_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_MS').val("");
	$('#update_XYID').val("");
	$('#update_ZYMC').val("");
	$('#update_BZ').val("");
	$('#update_XYMC').val("");
	$('#update_ZYDM').val("");
	$('#update_ZY_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].ZY_ID + ",";
			}
			$.ajax({
				url : "delZHUANYE.action?nowtime=" + NowTime + "&ZY_ID="
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
						url : 'listZhuanYe.action',
						title : '专业管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 5, 10, 15, 20, 25, 30, 35, 40 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'ZY_ID',
						columns : [ [
								{
									title : '<b>专业名称</b>',
									field : 'ZYMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>专业代码</b>',
									field : 'ZYDM',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学院名称</b>',
									field : 'XYMC',
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
									field : 'ZY_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateZHUANYE_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
		pageList : [ 5, 10, 15, 20, 25, 30, 35, 40 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});
