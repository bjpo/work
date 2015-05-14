//模糊查询模块
function doSearch() {
	$('#datagrid').datagrid('load', {
		mk_mkmc : $('#mkmc_serchbar').val(),
		mk_mkbm : $('#mkbm_serchbar').val(),
		mk_ms : $('#mkms_serchbar').val(),
		mk_bz : $('#mkbz_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增模块信息对话框
function addMK_dialog() {
	$('#addmk_dlg').dialog('open').dialog('setTitle', '新增模块信息'); // 弹出窗口
	$('#mk_fk_mk').combobox({
		url : 'listMOKUAIforYONGHU.action',
		valueField : 'mkid',
		textField : 'mkmc'
	});
	$('#mk_isshowinleftmenu_no').attr('checked', true);

	$.parser.parse('#addmk_dlg'); // 需要重新渲染对话框，否则easyui不起作用

}
// 新增模块信息
function addMOKUAI() {
	$('#addMK_form').form('submit', {
		url : 'addMOKUAI.action',
		onSubmit : function() {
			// 模块名称匹配规则
			var mokuaiNameReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()-]+$");
			// 模块url名称匹配规则
			var mokuaiUrlReg = new RegExp("^\\w+$");
			// 排序匹配规则
			var paixu = new RegExp("^\\d.+$");

			// 对模块名称进行验证
			if (mokuaiNameReg.test($('#mk_mkmc').val())) {
				// 对模块url名称进行验证
//				if (mokuaiUrlReg.test($('#mk_mkurl').val())) {
					// 对排序进行验证
					if (paixu.test($('#mk_paixu').val())) {
						return true;
					} else {
						$.messager.alert('提示', '请输入排序的数字');
						return false;
					}
//				} else {
//					$.messager.alert('提示', '请输入URL');
//					return false;
//				}
			} else {
				$.messager.alert('提示', '请输入模块名称');
				return false;
			}

		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增模块失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addMK_form').form('clear');
				$('#addmk_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateMK_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	$('#update_mk_fk_mk').combobox({
		url : 'listMOKUAIforYONGHU.action',
		valueField : 'mkid',
		textField : 'mkmc'
	});
	
	
	if (row.mkid) {
		$.ajax({
			url : "updateMOKUAI.action?nowtime=" + NowTime + "&mk_id="
					+ row.mkid,
			context : document.body,
			success : function(data) {
				var mkobj = eval("(" + data + ")");
				$('#updatemk_dlg').dialog('open').dialog('setTitle',
						'查看/修改模块信息'); // 弹出窗口
				$.parser.parse('#updatemk_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_mk_mkmc').val(mkobj.mkmc);
				$('#update_mk_mkbm').val(mkobj.mkbm);
				$('#update_mk_ms').val(mkobj.ms);
				$('#update_mk_bz').val(mkobj.bz);
				$('#update_mk_id').val(mkobj.mkid);
				$('#update_optionflag').val("updatemk");
				$('#update_mk_mkurl').val(mkobj.mkurl);
				$('#update_mk_mkym').val(mkobj.mkym);
				
				$('#updateMenuCate').combobox('setValue',mkobj.menuCateId);
				
				if ("open" == mkobj.openstate) {
					$('#update_mk_openstate_open').attr('checked', true);
				} else {
					$('#update_mk_openstate_closed').attr('checked', true);
				}
				if ("yes" == mkobj.isShowInLeftMenu) {
					$('#update_mk_isshowinleftmenu_yes').attr('checked', true);
				} else {
					$('#update_mk_isshowinleftmenu_no').attr('checked', true);
				}
				$('#update_mk_openstate').val(mkobj.openstate);
				$('#update_mk_iconcls').val(mkobj.iconcls);
				$('#update_mk_paixu').val(mkobj.paixu);
				$('#update_mk_paixu').val(mkobj.paixu);
				$('#update_mk_fk_mk').val(mkobj.mk_fk_mk);
				$('#update_mk_fk_mk').combobox('setValue', mkobj.fkmk);
			}
		});
	}
}
// 修改模块信息
function updateMOKUAI() {
	$('#updateMK_form').form('submit', {
		url : 'updateMOKUAI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改模块信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateMK_form').form('clear');
				$('#updatemk_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');

	$('#update_mk_mkmc').val("");
	$('#update_mk_mkbm').val("");
	$('#update_mk_ms').val("");
	$('#update_mk_bz').val("");
	$('#update_mk_id').val("");
	$('#update_optionflag').val("");
	$('#update_mk_mkurl').val("");
	$('#update_mk_openstate').val("");
	$('#update_mk_iconcls').val("");
	$('#update_mk_paixu').val("");
	$('#update_mk_paixu').val("");
	$('#update_mk_fk_mk').val("");
}
// 删除模块信息方法 弹出删除对话框：
function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的模块信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpmkid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpmkid = tmpmkid + row[tmpi].mkid + ",";
			}
			$.ajax({
				url : "delMOKUAI.action?nowtime=" + NowTime + "&mk_id="
						+ tmpmkid,
				context : document.body,
				success : function(data) {
					var mkobj = eval("(" + data + ")");
					$.messager.alert('提示', mkobj.message);
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
						url : 'listMOKUAI.action',
						title : '模块列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'mkid',
						columns : [ [
								{
									title : '<b>模块名称</b>',
									field : 'mkmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>模块编码</b>',
									field : 'mkbm',
									sortable : true,
									width : 100
								},
								{
									title : '<b>URL</b>',
									field : 'mkurl',
									sortable : true,
									width : 200
								},
								{
									title : '<b>打开状态</b>',
									field : 'openstate',
									sortable : true,
									width : 100
								},
								{
									title : '<b>图标样式</b>',
									field : 'iconcls',
									sortable : true,
									width : 100
								},
								{
									title : '<b>排序</b>',
									field : 'paixu',
									sortable : true,
									width : 50
								},
								{
									title : '<b>模块描述</b>',
									field : 'ms',
									sortable : true,
									width : 100
								},
								{
									title : '<b>模块备注</b>',
									field : 'bz',
									sortable : true,
									width : 100
								},
								{
									title : '<b>登记日期</b>',
									field : 'djrq',
									sortable : true,
									width : 150
								},
								{
									title : '<b>修改日期</b>',
									field : 'xgrq',
									sortable : true,
									width : 150
								},
								{
									title : '<b>上级模块</b>',
									field : 'fkmkmc',
									sortable : true,
									width : 200
								},
								{
									title : '<b>模块页面</b>',
									field : 'mkym',
									width : 100
								},
								{
									title : '<b>是否在左侧菜单中列出</b>',
									field : 'isShowInLeftMenu',
									width : 200
								},
								{
									title : '<b>操作</b>',
									field : 'mk_id',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateMK_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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