function doSearch() {
	$('#datagrid').datagrid('load', {
		/* 日期查询条件 */
		LAOSHIMC : $('#rkls_serchbar').combobox('getText'),// 老师名称
		KCXXMC : $('#rkkc_serchbar').combobox('getText'),// 课程信息名称
		JSMC : $('#skjs_serchbar').combobox('getText'),// 教室名称
		SKSJ_KS : $('#sksj_kssj_serchbar').combobox('getText'),// 课时的开始时间
		SKSJ_JS : $('#sksj_jssj_serchbar').combobox('getText'),// 课时的结束时间
		/* 学期查询条件 */
		XINGQI : $('#XINGQI_serchbar').combobox('getText'), // 星期
		ZHOU : $('#ZHOU_serchbar').combobox('getText'), // 周
		Day_KSMC : $('#queryDayKs').combobox('getText'), // 课时
		Term_KSMC : $('#queryTermKs').combobox('getText'), // 课时
		XUEQI : $('#XUEQI_serchbar').combobox('getText')
	// 学期
	});
}
// 将日期查询的条件置空
function clearDay() {
	// 初始化任课老师Combobox
	$('#rkls_serchbar').combobox('setText', '');

	// 初始化授课教室Combobox
	$('#skjs_serchbar').combobox('setText', '');

	// 初始化授课课程Combobox
	$('#rkkc_serchbar').combobox('setText', '');

	// 初始化授课时间的开始时间Combobox
	$('#sksj_kssj_serchbar').datebox('setText', '');
	// 初始化授课时间的结束时间Combobox
	$('#sksj_jssj_serchbar').datebox('setText', '');
	// 课时combobox
	$('#queryDayKs').combobox('setText', '');
}
// 将学期查询的条件置空
function clearTerm() {
	$('#queryTermKs').combobox('setText', '');
	// 选择学期
	$('#XUEQI_serchbar').combobox('setText', '');
	// 周
	$('#ZHOU_serchbar').combobox('setText', '');
	// 天
	$('#XINGQI_serchbar').combobox('setText', '');

}
// 初始化Combobox方法
function InitCombobox() {
	$('#selectMode').hide();
	// 初始化任课老师Combobox
	$('#rkls_serchbar').combobox({
		url : 'findLAOSHIMC.action',
		valueField : 'laoshi_id',
		textField : 'laoshimc'
	});

	// 初始化授课教室Combobox
	$('#skjs_serchbar').combobox({
		url : 'findJSMC.action',
		valueField : 'js_id',
		textField : 'jsmc'
	});

	// 初始化授课课程Combobox
	$('#rkkc_serchbar').combobox({
		url : 'findSKMC.action',
		valueField : 'kcxx_id',
		textField : 'kcxxmc'
	});

	// 初始化授课时间的开始时间Combobox
	$('#sksj_kssj_serchbar').datebox({
		editable:false
	});
	// 初始化授课时间的结束时间Combobox
	$('#sksj_jssj_serchbar').datebox({
		editable:false
	});
	// 课时combobox
	$('#queryDayKs').combobox({
		url : 'findKSMC.action',
		valueField : 'KS_ID',
		textField : 'KSMC'
	});

	$('#queryTermKs').combobox({
		url : 'listAllKESHI.action',
		valueField : 'KS_ID',
		textField : 'KSMC',
		setValue : '全部课时'

	});
	// 选择学期
	$('#XUEQI_serchbar').combobox({
		url : 'listAllXUEQI.action',
		valueField : 'XQ_ID',
		textField : 'XQMC'
	});
	// 周
	$('#ZHOU_serchbar').combobox({
		url : 'js/jiaoxuezhou.json',
		valueField : 'zhouid',
		textField : 'jiaoxuezhou'
	});
	// 天
	$('#XINGQI_serchbar').combobox({
		url : 'js/xingqi.json',
		valueField : 'xqid',
		textField : 'xingqi'
	});
	// 初始化时隐藏按学期查询条件
	$('#queryTerm').hide();
	// 查询方式复选框变更联动
	$('#selectType').combobox({
		onChange : function(n, o) {
			if ("day" == n.toString()) {
				$('#queryDay').show();
				$('#queryTerm').hide();
				clearTerm();

			} else if ("term" == n.toString()) {
				$('#queryDay').hide();
				$('#queryTerm').show();
				clearDay();
			}
		}
	});

}
//进一步查看
function enterView(){
	var row = $('#datagrid').datagrid('getSelected');
	//进一步查看的页面
	var url = "gonggongke/list_enterview.jsp?KS_ID=" + row.KS_ID 
				+ "&JS_ID=" + row.JS_ID+ "&JG_ID=" + row.LAOSHI_ID + "&SKSJ_KS="
				+ $('#sksj_kssj_serchbar').combobox('getText') + "&SKSJ_JS="+ $('#sksj_jssj_serchbar').combobox('getText') 
				+ "&KCB_ID="+ row.KCB_ID + "&KCB_FXS_ID=" + row.KCB_FXS_ID;
	//判断把选择的当前的记录是不为空
	if (row.KCB_ID != " " && row.KCB_ID != null) {
		// 对请求的页面进行提交
		$('#serform').attr("action", url).submit();
	}
	
}

// 初始化方法
$(function() {
	// 初始化combobox
	InitCombobox();
	// 初始化数据列表
	$('#datagrid').datagrid({
		url : 'listGONGGONGKE.action',
		title : '公共课管理列表',
		iconCls : 'icon-cls',
		fitColumns : false,
		nowrap : false,
		border : false,
		idField : 'KCB_ID',
		columns : [ [ {
			title : '<b>课程名称</b>',
			field : 'KCXXMC',
			sortable : true,
			width : 100
		},
		 {
			title : '<b>课时ID</b>',
			field : 'KS_ID',
			hidden:true
		},{
			title : '<b>任课老师</b>',
			field : 'LAOSHIMC',
			sortable : true,
			width : 100
		},{
			title : '<b>老师ID</b>',
			field : 'LAOSHI_ID',
			sortable : true,
			hidden:true
		}, {
			title : '<b>所在教室</b>',
			field : 'JSMC',
			sortable : true,
			width : 100
		}, 
		{
			title : '<b>教室ID</b>',
			field : 'JS_ID',
			sortable : true,
			hidden:true
		},{
			title : '<b>分学时ID</b>',
			field : 'KCB_FXS_ID',
			sortable : true,
			hidden:true
		},{
			title : '<b>授课时间</b>',
			field : 'SKSJ',
			width : 100
		}, {
			title : '<b>应出席人数</b>',
			field : 'YCXRS',
			width : 100
		}, {
			title : '<b>迟到人数</b>',
			field : 'CDRS',
			width : 100
		}, {
			title : '<b>正常出席人数</b>',
			field : 'ZCCXRS',
			width : 100
		}, {
			title : '<b>缺席人数</b>',
			field : 'QXRS',
			width : 100
		}, {
			title : '<b>早退人数</b>',
			field : 'ZTRS',
			width : 100
		}, {
			title : '<b>进一步查看组成</b>',
			field : 'KCB_ID',
			width : 100,
			formatter : function(value, rowData, rowIndex) {
				if ("null"==value+"") {
					return "无记录";
				}else{
					return "<a style='cursor:hand;' onclick='enterView();'>进一步查看</a>";
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
