/** **************************************************初始化并异步查询学院数据集合************************************** */
$(function() {
	// 显示数据表格
	$('#datagrid')
			.datagrid(
					{
						url : 'listXueYuan.action',
						title : '学院管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'xyid',
						columns : [ [
								{
									title : '<b>学院名称</b>',
									field : 'xymc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学院代码</b>',
									field : 'xydm',
									sortable : true,
									width : 100
								},
								{
									title : '<b>学院地址</b>',
									field : 'xydz',
									sortable : true,
									width : 200
								},
								{
									title : '<b>详细信息</b>',
									field : 'xxxx',
									sortable : true,
									width : 200
								},
								{
									title : '<b>邮政编码</b>',
									field : 'yzbm',
									sortable : true,
									width : 100
								},
								{
									title : '<b>备注</b>',
									field : 'bz',
									sortable : true,
									width : 200
								},
								{
									title : '<b>上级院系</b>',
									field : 'fxymc',
									sortable : true,
									width : 200
								},

								{
									title : '<b>操作</b>',
									field : 'xyid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='update_xy_dlg();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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

/** *********************************************弹出新增学院信息对话框*********************************************** */
function addXUANYUAN_dialog() {
	$('#addxy_dlg').dialog('open').dialog('setTitle', '新增学院信息'); // 新增学院信息弹出窗口
	$('#addXY_combobox').combobox({
		url : 'listforXUEYUAN.action',
		valueField : 'xyid',
		textField : 'xymc'
	});
	$.parser.parse('#addxy_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}

/** **************************************************新增学院数据信息******************************************** */
function addXUANYUAN() {
	$('#addxy_form').form('submit', {
		url : 'addXueYuan.action',
		onSubmit : function() {
			/*
			 * //只允许出现中文 var res="^[\u4E00-\uFA29]|[\uE7C7-\uE7F3]$";
			 */
			// 中文、英文、数字包括下划线
			var reg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_]+$");
			var xydmReg = new RegExp("^\\w+$");
			if (reg.test($('#xymc').val())) {
				// 判断学院的代码是否为空
				if (xydmReg.test($('#xydm').val())) {
//					// 不为空时返回true
//					if ($('#addXY_combobox').combobox('getText') != "") {
//						return true;
//					} else {
//						$.messager.alert('提示', '请选择上级院系');
//						return false;
//					}

				} else {
					// 学院代码为空时进行提示
					$.messager.alert('提示', '学院代码不能为空或含有特殊字符！');
					return false;
				}
			} else {
				// 学院名称为空时进行提示
				$.messager.alert('提示', '学院名不能为空或包含有特殊字符！');
				return false;
			}
		},
		// 服务器返回状态，如果成功返回则执行下面的内容
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增学院信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addxy_form').form('clear');
				$('#addxy_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');

		}
	});
}

/** ********************************************删除用户组信息方法弹出删除对话框******************************* */
function delXUENYUAN_dialog() {
	// 在删除信息之前给予用户提示
	$.messager.confirm("删除信息", " 确认要删除选中的学院信息吗？", function(r) {

		if (r) {
			var row = $('#datagrid').datagrid('getSelections');
			// 判断是否选择了记录，如果没有选择进行提示
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}

			// 存放拼接Id的变量
			var IdStr = "";
			// 进行循环读取Id
			for ( var i = 0; i < row.length; i++) {
				if (0 == i) {
					// 小于等于一个Id时行拼接
					IdStr = row[i].xyid;
				} else {
					// 大于一个Id时进行拼接，存入到IdStr变量中
					IdStr += ',' + row[i].xyid;
				}
			}

			$.ajax({
				url : "delXueYuan.action?xyid=" + IdStr,
				context : document.body,
				success : function(data) {
					var obj = eval("(" + data + ")");
					$.messager.alert('提示', obj.message);
					$('#datagrid').datagrid('clearSelections');
					$('#datagrid').datagrid('reload');
				}
			});
		}
	});
	return false;
}

/** ****************************************************打开修改学院信息对话框********************************************** */
function update_xy_dlg() {
	var row = $('#datagrid').datagrid('getSelected');

	$('#update_fxymc').combobox({
		url : 'listforXUEYUAN.action',
		valueField : 'xyid',
		textField : 'xymc'
	});
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.xyid) {
		$('#update_xy_dlg').dialog('open').dialog('setTitle', '查看/修改学院信息'); // 弹出窗口
		$('#update_xyid').val(row.xyid);
		$('#update_xymc').val(row.xymc);
		$('#update_xydm').val(row.xydm);
		$('#update_xydz').val(row.xydz);
		$('#update_yzbm').val(row.yzbm);
		$('#update_xxxx').val(row.xxxx);
		$('#update_bz').val(row.bz);
		$('#update_ms').val(row.ms);
		$('#update_fxymc').val(row.xymc);
		$('#update_fxymc').combobox('setValue', row.fxymc);
		// $.parser.parse('#update_xy_dlg'); // 需要重新渲染对话框，否则easyui不起作用

	}
}

/** **********************************************修改用户信息**************************************************** */
function updateXUANYUAN() {
	$('#updateXY_form').form('submit', {
		url : 'updateXueYuan.action',
		onSubmit : function() {
		},
		success : function(data) {

			if ("false" == data) {
				$.messager.alert('提示', '修改学院信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateXY_form').form('clear');
				$('#update_xy_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

/** ******************************根据用户输入的条件进行模糊查询**************************************** */
function XySearch() {
	$('#datagrid').datagrid('load', {
		xymc : $('#xymc_serchbar').val()
	});
}
/** **********************************************js验证************************************ */
