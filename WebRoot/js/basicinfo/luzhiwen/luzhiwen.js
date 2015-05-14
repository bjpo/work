var TmpId = 0;

function Transaction() {
	var ens = document.getElementById("es");
	var istatus = FPEngineEx1.GetWorkMsg();
	switch (istatus) {
	case 1:
		ens.value = "设备未连接";
		break;
	case 2:
		ens.value = "请按手指";
		break;
	case 3:
		ens.value = "请抬起手指";
		break;
	case 4:
		ens.value = "采集图像成功";
		imagedata = FPEngineEx1.GetImageEx();
		var en3 = document.getElementById("e3");
		en3.value = imagedata;
		break;
	case 5:
		ens.value = "采集指纹特征点完成";
		matdata = FPEngineEx1.GetCharEx();
		var en2 = document.getElementById("e2");
		en2.value = matdata;
		clearTimeout(timer);
		return;
	case 6:
		ens.value = "登记指纹特征点完成";
		refdata = FPEngineEx1.GetFptEx();
		var en1 = document.getElementById(TmpId);
		en1.value = refdata;
		clearTimeout(timer);
		return;
	}
	timer = setTimeout("Transaction()", 500);
}

function EnrollRefTemplate() {
	if (FPEngineEx1.OpenDevice(0, 0, 0) == 1) {
		if (FPEngineEx1.LinkDevice() == 1) {
			var ens = document.getElementById("es");
			FPEngineEx1.EnrFptEx();
			ens.value = "就绪";
			timer = setTimeout("Transaction()", 500);
		} else
			alert("连接USB指纹仪失败");
	} else
		alert("打开USB指纹仪失败");
}

// 录入指纹1
var TmpId = 0;
function OpenEnrollFpDlg(id) {
	$('#addDAT_dlg').dialog('open').dialog('setTitle', '添加指纹信息'); // 弹出窗口
	$.parser.parse('#addDAT_dlg');
	$('#es').val("");
	TmpId = id;
}

function OKTemplate() {
	FPEngineEx1.CloseDevice();
	$('#addDAT_dlg').dialog('close');
}

var rowid = 0;

function del_dialog() {
	$.messager.confirm("删除指纹信息", " 确认要删除选中的学生指纹信息吗？", function(r) {
		if (r) {
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			var tmpyhid = "";
			for ( var tmpi = 0; tmpi < row.length; tmpi++) {
				if (0 == tmpi) {
					tmpyhid = row[tmpi].xsId;
				} else {
					tmpyhid += ',' + row[tmpi].xsId;
				}
			}
			$.ajax({
				url : "delZHIWEN.action?xsId=" + tmpyhid,
				context : document.body,
				cache : false,
				success : function(data) {
					var obj = eval("(" + data + ")");
					$.messager.alert('提示', obj.message);
					if (obj.success) {
						$('#datagrid').datagrid('clearSelections');
						$('#datagrid').datagrid('reload');
					}
				}
			});
		}
	});
	return false;
}

// 打开新增用户信息对话框
function add_dialog() {
	// 读取选中的记录
	var rows = $('#datagrid').datagrid('getSelections');

	// 选取记录的个数
	if (rowid == rows.length) {
		$.messager.alert('提示信息', '无选中项');
		return;
	}
	// 打开添加指纹信息页面
	$('#add_dlg').dialog('open').dialog('setTitle', '添加指纹信息');
	$.parser.parse('#add_dlg');
	$('#xs_xh').val(rows[rowid].xh);
	$('#xs_id').val(rows[rowid].xsId);
	$('#xs_zsxm').val(rows[rowid].zsxm);
	$('#xs_xb').val(rows[rowid].xb);
	$('#xs_xyId').val(rows[rowid].tmp1);
	$('#xs_zyId').val(rows[rowid].tmp2);
	$('#xs_njId').val(rows[rowid].tmp3);
	$('#xs_bjId').val(rows[rowid].tmp4);
	if ("无" == rows[rowid].tmp5) {
		$('#xs_zhiwenId1').val("");
		$('#xs_zhiwenId2').val("");
	} else {
		$('#xs_zhiwenId1').val("已存在");
		$('#xs_zhiwenId2').val("已存在");
	}
	$('#datagrid').reload();
	rowid = 1;

}

// 新增房间信息
function addFp() {
	$.messager.confirm("录指纹", "是否确认提交指纹信息？", function(r) {
		if (r) {
			$('#add_form').form(
					'submit',
					{
						url : 'addZHIWEN.action',
						onSubmit : function() {
							return $(this).form('validate');
						},
						success : function(data) {
							var obj = eval('(' + data + ')');
							$.messager.alert('提示', obj.message);
							if (obj.success) {
								$('#add_form').form('clear');
								$('#add_dlg').dialog('close');
								var rows = $('#datagrid').datagrid(
										'getSelections');
								if (rowid != rows.length) {
									$('#add_dlg').dialog('open').dialog(
											'setTitle', '添加指纹信息');
									$.parser.parse('#add_dlg');
									$('#xs_xh').val(rows[rowid].xh);
									$('#xs_id').val(rows[rowid].xsId);
									$('#xs_zsxm').val(rows[rowid].zsxm);
									$('#xs_xb').val(rows[rowid].xb);
									$('#xs_xyId').val(rows[rowid].tmp1);
									$('#xs_zyId').val(rows[rowid].tmp2);
									$('#xs_njId').val(rows[rowid].tmp3);
									$('#xs_bjId').val(rows[rowid].tmp4);
									if ("无" == rows[rowid].tmp5) {
										$('#xs_zhiwenId1').val("");
										$('#xs_zhiwenId2').val("");
									} else {
										$('#xs_zhiwenId1').val("已存在");
										$('#xs_zhiwenId2').val("已存在");
									}
									rowid++;
									return;
								}
								$('#add_form').form('clear');
								$('#datagrid').datagrid('clearSelections');
								$('#datagrid').datagrid('reload');
							}
						}
					});
		}
	});
}

// 模糊查询用户
function doSearch() {
	$('#datagrid').datagrid('load', {
		xh : $('#xh_serchbar').val(),
		zsxm : $('#xm_serchbar').val(),
		xyId : $('#xy_serchbar').combobox('getValue'),
		zyId : $('#zy_serchbar').combobox('getValue'),
		njId : $('#nj_serchbar').combobox('getValue'),
		bjId : $('#bj_serchbar').combobox('getValue'),
		optionflag : 'selbylike'
	});
}

// 初始化方法
$(function() {
	// 显示数据表格
	$('#datagrid').datagrid({
		url : 'listLUZHIWEN.action',
		title : '学生列表',
		iconCls : 'icon-cls',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false,
		nowrap : false,
		border : false,
		idField : 'xsId',
		columns : [ [ {
			title : '<b>学号</b>',
			field : 'xh',
			sortable : true,
			width : 100
		}, {
			title : '<b>姓名</b>',
			field : 'zsxm',
			sortable : true,
			width : 100
		}, {
			title : '<b>性别</b>',
			field : 'xb',
			sortable : true,
			width : 100
		}, {
			title : '<b>学院</b>',
			field : 'xyId',
			sortable : true,
			width : 100,
			hidden : true
		}, {
			title : '<b>所在学院</b>',
			field : 'tmp1',
			sortable : true,
			width : 100
		}, {
			title : '<b>专业</b>',
			field : 'zyId',
			sortable : true,
			width : 100,
			hidden : true
		}, {
			title : '<b>所在专业</b>',
			field : 'tmp2',
			sortable : true,
			width : 100
		}, {
			title : '<b>年级</b>',
			field : 'njId',
			sortable : true,
			width : 100,
			hidden : true
		}, {
			title : '<b>所在年级</b>',
			field : 'tmp3',
			sortable : true,
			width : 100
		}, {
			title : '<b>班级</b>',
			field : 'bjId',
			sortable : true,
			width : 100,
			hidden : true
		}, {
			title : '<b>所在班级</b>',
			field : 'tmp4',
			sortable : true,
			width : 200
		}, {
			title : '<b>录入状态</b>',
			field : 'tmp5',
			sortable : true,
			width : 100
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