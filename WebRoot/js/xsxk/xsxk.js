//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		KCB_KCXXMC : $('#KCB_KCXXMC_serchbar').val(),
		XSXM : $('#XSXM_serchbar').val(),
		XUEHAO : $('#XUEHAO_serchbar').val(),
		KCB_ID : $('#KCB_ID_serchbar').val(),
		XS_ID : $('#XS_ID_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 新增信息
function addKcXX() {
	$.messager.confirm("确认信息", " 确定要选择该门课程吗？", function(r) {
		if (r) {
			var NowTime = new Date().toLocaleTimeString();
			var row = $('#datagrid').datagrid('getSelections');
			if (row < 1) {
				$.messager.alert('提示', '请选择一门课程！');
				return;
			}
			$.ajax({
				url : "addKcXX.action",
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
	$('#addKcXX_form').form('submit', {
		url : 'addSKXSXX.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addSKXSXX_form').form('clear');
				$('#addSKXSXX_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateSKXSXX_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.KCXS_ID) {
		$.ajax({
			url : "updateSKXSXX.action?nowtime=" + NowTime + "&KCXS_ID="
					+ row.KCXS_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateSKXSXX_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateSKXSXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_KCB_KCXXMC').val(yhzobj.KCB_KCXXMC);
				$('#update_XSXM').val(yhzobj.XSXM);
				$('#update_XUEHAO').val(yhzobj.XUEHAO);
				$('#update_KCB_ID').val(yhzobj.KCB_ID);
				$('#update_XS_ID').val(yhzobj.XS_ID);
				$('#update_KCXS_ID').val(yhzobj.KCXS_ID);
				$('#update_optionflag').val('updateSKXSXX');
			}
		});
	}
}
// 修改信息
function updateSKXSXX() {
	$('#updateSKXSXX_form').form('submit', {
		url : 'updateSKXSXX.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateSKXSXX_form').form('clear');
				$('#updateSKXSXX_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_KCB_KCXXMC').val("");
	$('#update_XSXM').val("");
	$('#update_XUEHAO').val("");
	$('#update_KCB_ID').val("");
	$('#update_XS_ID').val("");
	$('#update_KCXS_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].KCXS_ID + ",";
			}
			$.ajax({
				url : "delSKXSXX.action?nowtime=" + NowTime + "&KCXS_ID="
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
						url : 'listXSXK.action',
						title : '学生选课列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'KCB_ID',
						columns : [ [
								{
									title : '<b>任课老师</b>',
									field : 'LAOSHIMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课程名称</b>',
									sortable : true,
									field : 'KCXXMC'
								},
								{
									title : '<b>上课日期</b>',
									sortable : true,
									field : 'XINGQI'
								},
								{
									title : '<b>上课课时</b>',
									sortable : true,
									field : 'KSMC'
								},
								{
									title : '<b>上课地点</b>',
									field : 'JSMC',
									sortable : true
								},
								{
									title : '<b>课程容量</b>',
									field : 'CAPACITY',
									sortable : true,
									width : 100
								},
								{
									title : '<b>已选人数</b>',
									field : 'NUMSELECTED',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'KCB_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='selectClass_Dialog();'><img src='js/jquery-easyui/themes/icons/ok.png'  alt='选择此课程' style='border:0px;'/>选择此课程</a>";
									}
								} ] ], // bind数据成功设置列宽度
								onLoadSuccess : function(data) {
									// datagrid头部 table 的第一个tr 的td们，即columns的集合
									var headerTds = $(
											".datagrid-header-inner table tbody tr:first-child")
											.children();
									// datagrid主体 table 的第一个tr 的td们，即第一个数据行
									var bodyTds = $(
											".datagrid-body table tbody tr:first-child")
											.children();
									var totalWidth = 0; // 合计宽度，用来为datagrid头部和主体设置宽度
									// 循环设置宽度
									bodyTds.each(function(i, obj) {
										var headerTd = $(headerTds.get(i));
										var bodyTd = $(bodyTds.get(i));
										$("div:first-child", headerTds.get(i)).css(
												"text-align", "center");
										var headerTdWidth = headerTd.width(); // 获取第i个头部td的宽度
										// 这里加5个像素
										// 是因为数据主体我们取的是第一行数据，不能确保第一行数据宽度最宽，预留5个像素。有兴趣的朋友可以先判断最大的td宽度都在进行设置
										var bodyTdWidth = bodyTd.width() + 5;
										var width = 0;
										// 如果头部列名宽度比主体数据宽度宽，则它们的宽度都设为头部的宽度。反之亦然
										if (headerTdWidth > bodyTdWidth) {
											width = headerTdWidth;
											bodyTd.width(width);
											headerTd.width(width);
											totalWidth += width;
										} else {
											width = bodyTdWidth;
											headerTd.width(width);
											bodyTd.width(width);
											totalWidth += width;
										}
									});
									var headerTable = $(".datagrid-header-inner table tbody:first-child");
									var bodyTable = $(".datagrid-body table tbody:first-child");
									// 循环完毕即能得到总得宽度设置到头部table和数据主体table中
									headerTable.width(totalWidth);
									bodyTable.width(totalWidth);
									bodyTds.each(function(i, obj) {
										var headerTd = $(headerTds.get(i));
										var bodyTd = $(bodyTds.get(i));
										var headerTdWidth = headerTd.width();
										bodyTd.width(headerTdWidth);
									});
								},
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
/*
 * 列表时修改：分课程，上课时间、地点变化。
 * 选课：kcb——id选课，插入三条。
 * */
function selectClass_Dialog() {
	var row ="";
	$.messager.confirm("确认选课信息", " 确认要选择此课程吗？", function(r) {
		if (r) {
			row = $('#datagrid').datagrid('getSelected');
			$.ajax({
				url : 'xuanke.action?KCB_ID='+row.KCB_ID,
				context : document.body,
				success : function(data) {
					if ("false" == data) {
						$.messager.alert('提示', "通信失败！");
					} else {
						var obj2 = eval("(" + data + ")");
						$.messager.alert('提示', obj2.message);
					}
				}
			});
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
	return true;
//	$('#update_KCB_KCXXMC').val("");
//	$('#update_XSXM').val("");
//	$('#update_XUEHAO').val("");
//	$('#update_KCB_ID').val("");
//	$('#update_XS_ID').val("");
//	$('#update_KCXS_ID').val("");
//	$('#update_optionflag').val("");
	
	
}
