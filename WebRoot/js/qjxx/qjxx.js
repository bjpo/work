//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		BZ : $('#BZ_serchbar').val(),
		XSXM : $('#XSXM_serchbar').val(),
		JGXM : $('#JGXM_serchbar').val(),
		JGGH : $('#JGGH_serchbar').val(),
		DJRQ : $('#DJRQ_serchbar').val(),
		JG_ID : $('#JG_ID_serchbar').val(),
		XSXH : $('#XSXH_serchbar').val(),
		XGRQ : $('#XGRQ_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		QJJSJS : $('#QJJSJS_serchbar').val(),
		QJKSSJ : $('#QJKSSJ_serchbar').val(),
		XS_ID : $('#XS_ID_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addQJXX_dialog() {
	$('#addQJXX_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addQJXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
	$('#addQJXX_form').form('clear');
	$('#datagrid').datagrid('clearSelections');
}
// 新增信息
function addQJXX() {
	$('#addQJXX_form').form('submit', {
		url : 'addQJXX.action',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var o = eval("(" + data + ")");
			if (false == o.success) {
				$.messager.alert('提示', o.message);
			} else {
				$.messager.alert('提示', o.message);
				$('#addQJXX_form').form('clear');
				$('#addQJXX_dlg').dialog('close');
				$('#datagrid').datagrid('clearSelections');
				$('#datagrid').datagrid('reload');
			}
		}
	});
}

function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}

// 打开修改对话框
function updateQJXX_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	if (typeof(row) == "undefined") {
		return;
	}
	if (!row && typeof(row) != "undefined" && row != 0) {
		return;
	}
	if (row.QJXX_ID) {
		$('#updateQJXX_dlg').dialog('open').dialog('setTitle', '修改信息');
		$.parser.parse('#updateQJXX_dlg');
		
		$('#update_BZ').val(row.BZ);
		$('#update_XSXM').val(row.XSXM);
		$('#update_JGXM').val(row.JGXM);
		$('#update_JGGH').val(row.JGGH);
		$('#update_DJRQ').val(row.DJRQ);
		$('#update_JG_ID').val(row.JG_ID);
		$('#update_XSXH').val(row.XSXH);
		$('#update_XGRQ').val(row.XGRQ);
		$('#update_MS').val(row.MS);
		$('#update_QJKSSJ').datetimebox('setValue', row.QJKSSJ);
		$('#update_QJJSJS').datetimebox('setValue', row.QJJSJS);
		$('#update_XS_ID').val(row.XS_ID);
		$('#update_QJXX_ID').val(row.QJXX_ID);
		$('#update_optionflag').val('updateQJXX');
	}
}
// 修改信息
function updateQJXX() {
	$('#updateQJXX_form').form('submit', {
		url : 'updateQJXX.action',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var o = eval("(" + data + ")");
			$.messager.alert('提示', o.message);
			if (o.success) {
				$('#updateQJXX_form').form('clear');
				$('#updateQJXX_dlg').dialog('close');
				$('#datagrid').datagrid('clearSelections');
				$('#datagrid').datagrid('reload');
				$('#update_BZ').val("");
				$('#update_XSXM').val("");
				$('#update_JGXM').val("");
				$('#update_JGGH').val("");
				$('#update_DJRQ').val("");
				$('#update_JG_ID').val("");
				$('#update_XSXH').val("");
				$('#update_XGRQ').val("");
				$('#update_MS').val("");
				$('#update_QJJSJS').val("");
				$('#update_QJKSSJ').val("");
				$('#update_XS_ID').val("");
				$('#update_QJXX_ID').val("");
				$('#update_optionflag').val("");
			}
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
				tmpyhzid = tmpyhzid + row[tmpi].QJXX_ID + ",";
			}
			$.ajax({
				url : "delQJXX.action?nowtime=" + NowTime + "&QJXX_ID=" + tmpyhzid,
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
	$('#datagrid').datagrid({
		url : 'listQJXX.action',
		title : '所有',
		iconCls : 'icon-cls',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false,
		nowrap : false,
		border : false,
		idField : 'QJXX_ID',
		columns : [ [ {
			title : '<b>学生学号</b>',
			field : 'XSXH',
			sortable : true,
			width : 100
		}, {
			title : '<b>学生姓名</b>',
			field : 'XSXM',
			sortable : true,
			width : 100
		}, {
			title : '<b>请假开始时间</b>',
			field : 'QJKSSJ',
			sortable : true,
			width : 150
		}, {
			title : '<b>请假结束时间</b>',
			field : 'QJJSJS',
			sortable : true,
			width : 150
		}, {
			title : '<b>填写学生请假信息的老师姓名</b>',
			field : 'JGXM',
			sortable : true,
			width : 200
		}, {
			title : '<b>填写学生请假信息的老师工号</b>',
			field : 'JGGH',
			sortable : true,
			width : 200
		}, {
			title : '<b>登记日期</b>',
			field : 'DJRQ',
			sortable : true,
			width : 150
		}, {
			title : '<b>填写学生请假信息的老师教工ID</b>',
			field : 'JG_ID',
			sortable : true,
			width : 200,
			hidden : true
		}, {
			title : '<b>修改日期</b>',
			field : 'XGRQ',
			sortable : true,
			width : 150
		}, {
			title : '<b>学生信息表ID</b>',
			field : 'XS_ID',
			sortable : true,
			width : 100,
			hidden : true
		}, {
			title : '<b>描述</b>',
			field : 'MS',
			sortable : true,
			width : 100
		}, {
			title : '<b>备注</b>',
			field : 'BZ',
			sortable : true,
			width : 100
		}, {
			title : '<b>操作</b>',
			field : 'QJXX_ID',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				return "<a style='cursor:hand;' onclick='updateQJXX_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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
