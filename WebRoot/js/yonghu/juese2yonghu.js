//模糊查询角色用户
function doSearch() {
	$('#datagrid').datagrid('load', {
		jy_jsmc : $('#jsmc_serchbar').val(),
		jy_yhmc : $('#yhmc_serchbar').val(),
		jy_bz : $('#bz_serchbar').val(),
		jy_ms : $('#ms_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增角色用户信息对话框
function addJY_dialog() {
	$('#addjy_dlg').dialog('open').dialog('setTitle', '新增角色用户配置信息'); // 弹出窗口
	$('#jy_jsid').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid', 
	    textField:'jsmc'
	});
	$('#jy_yhid').combobox({
	    url:'listYONGHUforYONGHU.action',  
	    valueField:'yhid',  
	    textField:'yhm'  
	});
	$.parser.parse('#addjy_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增角色用户信息
function addJUESE2YONGHU() {
	$('#addJY_form').form('submit', {
		url : 'addJUESE2YONGHU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增角色用户失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addJY_form').form('clear');
				$('#addjy_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateJY_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString(); 
	if (typeof(row) == "undefined") { 
		return ;
	}
	if (!row && typeof(row)!="undefined" && row!=0){
		   return ;
	}
	$('#update_jy_jsid').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid',  
	    textField:'jsmc'  
	});
	$('#update_jy_yhid').combobox({
	    url:'listYONGHUforYONGHU.action',  
	    valueField:'yhid',  
	    textField:'yhm'  
	});
	if (row.jsyhid) {
		$.ajax({
			url : "updateJUESE2YONGHU.action?nowtime=" + NowTime + "&jy_id="
					+ row.jsyhid,
			context : document.body,
			success : function(data) {
				var jyobj = eval("(" + data + ")");
				$('#updatejy_dlg').dialog('open').dialog('setTitle','查看/修改角色用户信息'); // 弹出窗口
				$.parser.parse('#updatejy_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_jy_ms').val(jyobj.ms);
				$('#update_jy_bz').val(jyobj.bz);
				$('#update_jy_id').val(jyobj.jsyhid);
				$('#update_optionflag').val("updatejy");
				$('#update_jy_jsid').combobox('setValue',jyobj.jsid);
				$('#update_jy_yhid').combobox('setValue',jyobj.yhid);
			}
		});
	}
}
// 修改角色用户信息
function updateJUESE2YONGHU() {
	$('#updateJY_form').form('submit', {
		url : 'updateJUESE2YONGHU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改角色用户信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateJY_form').form('clear');
				$('#updatejy_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_jy_ms').val("");
	$('#update_jy_bz').val("");
	$('#update_jy_jsid').val("");
	$('#update_optionflag').val("");
	$('#update_jy_yhid').val("");
}
// 删除角色用户信息方法 弹出删除对话框：
function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的角色用户信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpjyid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpjyid = tmpjyid + row[tmpi].jsyhid + ",";
			}
			$.ajax({
				url : "delJUESE2YONGHU.action?nowtime=" + NowTime + "&jy_id="+ tmpjyid,
				context : document.body,
				success : function(data) {
					var jyobj = eval("(" + data + ")");
					$.messager.alert('提示', jyobj.message);
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
						url : 'listJUESE2YONGHU.action',
						title : '角色用户列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'jsyhid',
						columns : [ [
								{
									title : '<b>角色名称</b>',
									field : 'jsmc',
									sortable : true,
									width : 100
								},{
									title : '<b>用户名称</b>',
									field : 'yhmc',
									sortable : true,
									width : 100
								},{
									title : '<b>描述</b>',
									field : 'ms',
									sortable : true,
									width : 100
								},{
									title : '<b>备注</b>',
									field : 'bz',
									sortable : true,
									width : 100
								},{
									title : '<b>登记日期</b>',
									field : 'djrq',
									sortable : true,
									width : 150
								},{
									title : '<b>修改日期</b>',
									field : 'xgrq',
									sortable : true,
									width : 150
								},{
									title : '<b>操作</b>',
									field : 'jsyhid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJY_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
		pageList : [ 5, 10, 15,20,25,30 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});