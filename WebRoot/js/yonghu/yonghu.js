//模糊查询用户
function doSearch() {
	$('#datagrid').datagrid('reload', {
		yh_yhmc : $('#yhmc_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增用户信息对话框
function addYH_dialog() {
	$('#addyh_dlg').dialog('open').dialog('setTitle', '新增用户信息'); // 弹出窗口
	$('#addYH_YHZ_combobox').combobox({
		url : 'listYHZforYONGHU.action',
		valueField : 'yhzid',
		textField : 'yhzmc'
	});
	$.parser.parse('#addyh_dlg'); // 需要重新渲染对话框，否则easyui不起作用

}
// 新增用户信息
function addYONGHU() {
	$('#addYH_form').form('submit', {
		url : 'addYONGHU.action',
		onSubmit : function() {
			// 用户名称匹配规则
			var yh_yhmcReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
			// 用户密码匹配规则
			var yh_yhmmReg = new RegExp("^\\w+$");

			// 判断用户组下拉列表是否为空
			if ($('#addYH_YHZ_combobox').combobox('getText') != "") {
				// 对用户名称进行验证
				if (yh_yhmcReg.test($('#yh_yhmc').val())) {
					// 对用户密码进行验证
					if (yh_yhmmReg.test($('#yh_yhmm').val())) {
						return true;
					} else {
						$.messager.alert('提示', '请输入用户密码(字母、数字、下划线)');
						return false;
					}
				} else {
					$.messager.alert('提示', '请输入用户名称(字母、数字、下划线以及中括号)');
					return false;
				}
			} else {
				$.messager.alert('提示', '请选择用户组');
				return false;
			}

		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增用户失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addYH_form').form('clear');
				$('#addyh_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateYH_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	$('#updateYH_YHZ_combobox').combobox({
		url : 'listYHZforYONGHU.action',
		valueField : 'yhzid',
		textField : 'yhzmc'
	});
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.yhid) {
		$.ajax({
			url : "updateYONGHU.action?nowtime=" + NowTime + "&yh_id="
					+ row.yhid,
			context : document.body,
			success : function(data) {
				var yhobj = eval("(" + data + ")");
				$('#updateyh_dlg').dialog('open').dialog('setTitle',
						'查看/修改用户信息'); // 弹出窗口
				$.parser.parse('#updateyh_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_yh_yhmc').val(yhobj.yhm);
				$('#update_yh_yhmm').val(yhobj.yhmm);
				$('#update_yh_id').val(yhobj.yhid);
				$('#update_optionflag').val("updateyh");
				$('#updateYH_YHZ_combobox').combobox('setValue',
						yhobj.json_yhzid);
			}
		});
	}
}
// 修改用户信息
function updateYONGHU() {
	$('#updateYH_form').form('submit', {
		url : 'updateYONGHU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改用户信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateYH_form').form('clear');
				$('#updateyh_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');

	$('#update_yh_yhmc').val("");
	$('#update_yh_id').val("");
	$('#update_yh_yhmm').val("");
	$('#update_optionflag').val("");
}
// 删除用户组信息方法 弹出删除对话框：
function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的用户信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpyhid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpyhid = tmpyhid + row[tmpi].yhid + ",";
			}
			$.ajax({
				url : "delYONGHU.action?nowtime=" + NowTime + "&yh_id="
						+ tmpyhid,
				context : document.body,
				success : function(data) {
					var yhobj = eval("(" + data + ")");
					$.messager.alert('提示', yhobj.message);
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
						url : 'listYONGHU.action',
						title : '用户管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'yhid',
						columns : [ [
								{
									title : '<b>用户名称</b>',
									field : 'yhm',
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
									field : 'yhid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateYH_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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