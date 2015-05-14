//模糊查询角色用户组
function doSearch() {
	$('#datagrid').datagrid('load', {
		jyz_jsmc : $('#jsmc_serchbar').val(),
		jyz_yhzmc : $('#yhzmc_serchbar').val(),
		jyz_bz : $('#bz_serchbar').val(),
		jyz_ms : $('#ms_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增角色用户组信息对话框
function addJYZ_dialog() {
	$('#addjyz_dlg').dialog('open').dialog('setTitle', '新增角色用户组配置信息'); // 弹出窗口
	$('#jyz_jsid').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid', 
	    textField:'jsmc'
	});
	$('#jyz_yhzid').combobox({
	    url:'listYHZforYONGHU.action',  
	    valueField:'yhzid',  
	    textField:'yhzmc'  
	});
	$.parser.parse('#addjyz_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增角色用户组信息
function addJUESE2YONGHUZU() {
	$('#addJYZ_form').form('submit', {
		url : 'addJUESE2YONGHUZU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增角色用户组失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addJYZ_form').form('clear');
				$('#addjyz_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updateJYZ_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString(); 
	if (typeof(row) == "undefined") { 
		return ;
	}
	if (!row && typeof(row)!="undefined" && row!=0){
		   return ;
	}
	$('#update_jyz_jsid').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid',  
	    textField:'jsmc'  
	});
	$('#update_jyz_yhzid').combobox({
	    url:'listYHZforYONGHU.action',  
	    valueField:'yhzid',  
	    textField:'yhzmc'  
	});
	if (row.jsyhzid) {
		$.ajax({
			url : "updateJUESE2YONGHUZU.action?nowtime=" + NowTime + "&jyz_id="
					+ row.jsyhzid,
			context : document.body,
			success : function(data) {
				var jyzobj = eval("(" + data + ")");
				$('#updatejyz_dlg').dialog('open').dialog('setTitle','查看/修改角色用户组信息'); // 弹出窗口
				$.parser.parse('#updatejyz_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_jyz_ms').val(jyzobj.ms);
				$('#update_jyz_bz').val(jyzobj.bz);
				//alert(jyzobj.jsyhzid);
				$('#update_jyz_id').val(jyzobj.jsyhzid);
				//alert($('#update_jyz_id').val());
				$('#update_optionflag').val("updatejyz");
				$('#update_jyz_jsid').combobox('setValue',jyzobj.jsid);
				$('#update_jyz_yhzid').combobox('setValue',jyzobj.yhzid);
			}
		});
	}
}
// 修改角色用户组信息
function updateJUESE2YONGHUZU() {
	$('#updateJYZ_form').form('submit', {
		url : 'updateJUESE2YONGHUZU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改角色用户组信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateJYZ_form').form('clear');
				$('#updatejyz_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_jyz_ms').val("");
	$('#update_jyz_bz').val("");
	$('#update_jyz_jsid').val("");
	$('#update_optionflag').val("");
	$('#update_jyz_yhzid').val("");
}
// 删除角色用户组信息方法 弹出删除对话框：
function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的角色用户组信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpjyzid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpjyzid = tmpjyzid + row[tmpi].jsyhzid + ",";
			}
			$.ajax({
				url : "delJUESE2YONGHUZU.action?nowtime=" + NowTime + "&jyz_id="+ tmpjyzid,
				context : document.body,
				success : function(data) {
					var jyzobj = eval("(" + data + ")");
					$.messager.alert('提示', jyzobj.message);
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
						url : 'listJUESE2YONGHUZU.action',
						title : '角色用户组列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'jsyhzid',
						columns : [ [
								{
									title : '<b>角色名称</b>',
									field : 'jsmc',
									sortable : true,
									width : 100
								},{
									title : '<b>用户组名称</b>',
									field : 'yhzmc',
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
									field : 'jsyhzid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJYZ_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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