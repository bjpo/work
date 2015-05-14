// 初始化方法
$(function() {
	$('#datagrid')
			.datagrid(
					{
						url : "listKaiFang.action",
						title : '开放性课程列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'kcb_id',
						columns : [ [
								{
									title : '<b>课程名称</b>',
									field : 'kcmc',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>上课教师</b>',
									field : 'lsxm',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>教师ID</b>',
									field : 'ls_id',
									hidden : true
								},
								{
									title : '<b>上课教室</b>',
									field : 'jsmc',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>教室ID</b>',
									field : 'js_id',
									hidden : true
								},
								{
									title : '<b>课时</b>',
									field : 'ksmc',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>课时ID</b>',
									field : 'ks_id',
									hidden : true
								},
								{
									title : '<b>课程表分学时ID</b>',
									field : 'kcb_fxs_id',
									hidden : true
								},
								{
									title : '<b>上课时间</b>',
									field : 'sksj',
									sortable : true,
									fitColumns : true
								},
								{
									title : '<b>正常</b>',
									field : 'zc',
									fitColumns : true
								},
								{
									title : '<b>迟到</b>',
									field : 'cd',
									fitColumns : true
								},
								{
									title : '<b>早退</b>',
									field : 'zt',
									fitColumns : true
								},
								{
									title : '<b>迟到&早退</b>',
									field : 'cdAndzt',
									fitColumns : true
								},

								{
									title : '<b>详情</b>',
									field : 'kcb_id',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										if (" " == value+" ") {
											return "无记录";
										} else {
											var ret = "<a style='cursor:hand;' onclick='detail();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/>详细</a>";
											return ret;
										}

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


/***************************初始化时显示的Combobox*********************/
$(function() {
	// 初始化时显示开始时间combobox
	$('#ksTime_serchbar').datebox(
			{
				onSelect : function() {
					var today = new Date();
					var y = today.getFullYear();
					var month = today.getMonth() + 1;
					var td = today.getDate();
					// 给其一个默认值，当前系统日期
					$('#jsTime_serchbar').combobox('setText',
							y + "-" + month + "-" + td);
				}
			});
	// 初始化时显示结束时间combobox
	$('#jsTime_serchbar').datebox(
			{
				onSelect : function() {
					var today = new Date();
					var y = today.getFullYear();
					var month = today.getMonth() + 1
					var td = today.getDate();
					// 给其一个默认值，当前系统日期
					$('#ksTime_serchbar').combobox('setText',
							y + "-" + month + "-" + td);
				}
			});
	// 初始化时显示课时combobox
	$('#ks_serchbar').combobox({
		url : 'listAllKESHI.action',
		valueField : 'KS_ID',
		textField : 'KSMC'
	});
	// 初始化时显示任课教师combobox
	$('#rkjs_serchbar').combobox({
		url : 'listAllJIAOGONG.action',
		valueField : 'JG_ID',
		textField : 'JGMC'
	});
	// 初始化时显示课程名称combobox
	$('#kcmc_serchbar').combobox({
		url : 'listAllKECHENGXX.action',
		valueField : 'KECHENGXX_ID',
		textField : 'KECHENGMC'
	});
	// 初始化时显示上课教室combobox
	$('#skjs_serchbar').combobox({
		url : 'listAllJIAOSHI.action',
		valueField : 'JS_ID',
		textField : 'JSMC'
	});
});

/**********************************模糊查询************************/
function doSearch() {
	// 获取开始日期
	var startTime = $('#ksTime_serchbar').combobox('getText');
	// 获取结束日期
	var endTime = $('#jsTime_serchbar').combobox('getText');
	// 对开始日期与结束日期进行判断
	if (startTime > endTime) {
		// 开始日期大于结束日期时，进行提示
		$.messager.alert('提示', '开始日期不能大于结束日期！');
		return;
	} else {
		// 如果开始日期不大于结束日期时，进行查询
		$('#datagrid').datagrid('load', {
			skkssj : $("#ksTime_serchbar").combobox("getText"),//上课的开始日期
			xkjssj : $("#jsTime_serchbar").combobox("getText"),//下课的结束日期
			KS_ID : $("#ks_serchbar").combobox("getValue"),// 课时id
			JG_ID : $("#rkjs_serchbar").combobox("getValue"),// 任课老师id
			KCXX_ID : $("#kcmc_serchbar").combobox("getValue"), // 课程名称id
			JS_ID : $("#skjs_serchbar").combobox("getValue")  //教室id
		});
	}

}

/*******************************点击详细之后跳到的页面********************************/
function detail() {
	//获取选中的对象
	var row = $("#datagrid").datagrid("getSelected");
	// 请求的路径
	var url = "kaifang/detail.jsp?KS_ID=" + row.ks_id + "&JS_ID=" + row.js_id
			+ "&JG_ID=" + row.ls_id + "&ksTime="
			+ $('#ksTime_serchbar').combobox('getText') + "&jsTime="
			+ $('#jsTime_serchbar').combobox('getText') + "&KCB_ID="
			+ row.kcb_id + "&KCB_FXS_ID=" + row.kcb_fxs_id+"&newTime="+Math.random();

	// 条件判断
	if (row.ks_id != null && row.ks_id != "" || row.js_id != null
			&& row.js_id != "" || row.ls_id != null && row.ls_id != "") {
		$('#dataToDetail').attr("action", url).submit();
	}

}
