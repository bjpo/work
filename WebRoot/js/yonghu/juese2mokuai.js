//模糊查询角色模块
function doSearch() {
	$('#datagrid').datagrid('load', {
		jm_jsmc : $('#jsmc_serchbar').val(),
		jm_mkmc : $('#mkmc_serchbar').val(),
		jm_bz : $('#bz_serchbar').val(),
		jm_ms : $('#ms_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增角色模块信息对话框
function addjm_dialog() {
	$('#addjm_dlg').dialog('open').dialog('setTitle', '新增角色模块配置信息'); // 弹出窗口
	$('#jm_jsid').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid', 
	    textField:'jsmc'
	});
	$('#jm_mkid').combobox({
	    url:'listMOKUAIforYONGHU.action',  
	    valueField:'mkid',  
	    textField:'mkmc'  
	});
	$.parser.parse('#addjm_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增角色模块信息
function addJUESE2MOKUAI() {
	$('#addjm_form').form('submit', {
		url : 'addJUESE2MOKUAI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增角色模块失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addjm_form').form('clear');
				$('#addjm_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}

// 打开修改对话框
function updatejm_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString(); 
	if (typeof(row) == "undefined") { 
		return ;
	}
	if (!row && typeof(row)!="undefined" && row!=0){
		   return ;
	}
	$('#update_jm_jsid').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid',  
	    textField:'jsmc'  
	});
	$('#update_jm_mkid').combobox({
	    url:'listMOKUAIforYONGHU.action',  
	    valueField:'mkid',  
	    textField:'mkmc'  
	});
	if (row.jsmkid) {
		$.ajax({
			url : "updateJUESE2MOKUAI.action?nowtime=" + NowTime + "&jm_id="
					+ row.jsmkid,
			context : document.body,
			success : function(data) {
				var jmobj = eval("(" + data + ")");
				$('#updatejm_dlg').dialog('open').dialog('setTitle','查看/修改角色模块信息'); // 弹出窗口
				$.parser.parse('#updatejm_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_jm_ms').val(jmobj.ms);
				$('#update_jm_bz').val(jmobj.bz);
				$('#update_jm_id').val(jmobj.jsmkid);
				$('#update_optionflag').val("updatejm");
				$('#update_jm_jsid').combobox('setValue',jmobj.jsid);
				$('#update_jm_mkid').combobox('setValue',jmobj.mkid);
				if("yes"==jmobj.canfq){
					$('#update_jm_canfq_yes').attr('checked',true);	
				}else{
					$('#update_jm_canfq_no').attr('checked',true);
				}
				
			}
		});
	}
}
// 修改角色模块信息
function updateJUESE2MOKUAI() {
	$('#updatejm_form').form('submit', {
		url : 'updateJUESE2MOKUAI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改角色模块信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updatejm_form').form('clear');
				$('#updatejm_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_jm_ms').val("");
	$('#update_jm_bz').val("");
	$('#update_jm_jsid').val("");
	$('#update_optionflag').val("");
	$('#update_jm_mkid').val("");
}
// 删除角色模块信息方法 弹出删除对话框：
function showEnterDialog() {
	$.messager.confirm("删除信息", " 确认要删除选中的角色模块信息吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpjmid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				tmpjmid = tmpjmid + row[tmpi].jsmkid + ",";
			}
			$.ajax({
				url : "delJUESE2MOKUAI.action?nowtime=" + NowTime + "&jm_id="+ tmpjmid,
				context : document.body,
				success : function(data) {
					var jmobj = eval("(" + data + ")");
					$.messager.alert('提示', jmobj.message);
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
						url : 'listJUESE2MOKUAI.action',
						title : '角色模块列表',
						iconCls : 'icon-cls',
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'jsmkid',
						columns : [ [
								{
									title : '<b>角色名称</b>',
									field : 'jsmc',
									sortable : true,
									width : 100
								},{
									title : '<b>模块名称</b>',
									field : 'mkmc',
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
									title : '<b>是否可以为其他用户赋权</b>',
									field : 'canfq',
									sortable : true,
									width : 150
								},{
									title : '<b>操作</b>',
									field : 'jsmkid',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updatejm_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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