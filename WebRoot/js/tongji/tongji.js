//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		XS_CD : $('#XS_CD_serchbar').val(),
		XS_ID : $('#XS_ID_serchbar').val(),
		XS_XM : $('#XS_XM_serchbar').val(),
		QXRS : $('#QXRS_serchbar').val(),
		NJ_ID : $('#NJ_ID_serchbar').val(),
		CDRSBL : $('#CDRSBL_serchbar').val(),
		XS_XH : $('#XS_XH_serchbar').val(),
		ZCCXRS : $('#ZCCXRS_serchbar').val(),
		JS_JSMC : $('#JS_JSMC_serchbar').val(),
		KSRQ : $('#KSRQ_serchbar').val(),
		XS_QX : $('#XS_QX_serchbar').val(),
		ZTRS : $('#ZTRS_serchbar').val(),
		NJMC : $('#NJMC_serchbar').val(),
		YSKRS : $('#YSKRS_serchbar').val(),
		JG_JGGH : $('#JG_JGGH_serchbar').val(),
		XS_ZT : $('#XS_ZT_serchbar').val(),
		BJMC : $('#BJMC_serchbar').val(),
		BJ_ID : $('#BJ_ID_serchbar').val(),
		JG_JGMC : $('#JG_JGMC_serchbar').val(),
		KCXX_KCMC : $('#KCXX_KCMC_serchbar').val(),
		SKRQ : $('#SKRQ_serchbar').val(),
		XINGQI : $('#XINGQI_serchbar').val(),
		KCXX_ID : $('#KCXX_ID_serchbar').val(),
		ZYMC : $('#ZYMC_serchbar').val(),
		ZCCXBL : $('#ZCCXBL_serchbar').val(),
		XY_ID : $('#XY_ID_serchbar').val(),
		CQQK : $('#CQQK_serchbar').val(),
		ZTRSBL : $('#ZTRSBL_serchbar').val(),
		ZHOU : $('#ZHOU_serchbar').val(),
		KS_ID : $('#KS_ID_serchbar').val(),
		XYMC : $('#XYMC_serchbar').val(),
		CDRS : $('#CDRS_serchbar').val(),
		XUEQI_ID : $('#XUEQI_ID_serchbar').val(),
		XS_ZCCX : $('#XS_ZCCX_serchbar').val(),
		JSRQ : $('#JSRQ_serchbar').val(),
		QXRSBL : $('#QXRSBL_serchbar').val(),
		JS_ID : $('#JS_ID_serchbar').val(),
		JG_ID : $('#JG_ID_serchbar').val(),
		ZY_ID : $('#ZY_ID_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addTONGJI_dialog() {
	$('#addTONGJI_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addTONGJI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addTONGJI() {
	$('#addTONGJI_form').form('submit', {
		url : 'addTONGJI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addTONGJI_form').form('clear');
				$('#addTONGJI_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
}
// 打开修改对话框
function updateTONGJI_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof(row) == "undefined") { 
		return ;
	}
	if (!row && typeof(row)!="undefined" && row!=0){   
		   return ;
	}
	if (row.TONGJI_ID) {
		$.ajax({
			url : "updateTONGJI.action?nowtime=" + NowTime + "&TONGJI_ID="
					+ row.TONGJI_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateTONGJI_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateTONGJI_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_XS_CD').val(yhzobj.XS_CD);
				$('#update_XS_ID').val(yhzobj.XS_ID);
				$('#update_XS_XM').val(yhzobj.XS_XM);
				$('#update_QXRS').val(yhzobj.QXRS);
				$('#update_NJ_ID').val(yhzobj.NJ_ID);
				$('#update_CDRSBL').val(yhzobj.CDRSBL);
				$('#update_XS_XH').val(yhzobj.XS_XH);
				$('#update_ZCCXRS').val(yhzobj.ZCCXRS);
				$('#update_JS_JSMC').val(yhzobj.JS_JSMC);
				$('#update_KSRQ').val(yhzobj.KSRQ);
				$('#update_XS_QX').val(yhzobj.XS_QX);
				$('#update_ZTRS').val(yhzobj.ZTRS);
				$('#update_NJMC').val(yhzobj.NJMC);
				$('#update_YSKRS').val(yhzobj.YSKRS);
				$('#update_JG_JGGH').val(yhzobj.JG_JGGH);
				$('#update_XS_ZT').val(yhzobj.XS_ZT);
				$('#update_BJMC').val(yhzobj.BJMC);
				$('#update_BJ_ID').val(yhzobj.BJ_ID);
				$('#update_JG_JGMC').val(yhzobj.JG_JGMC);
				$('#update_KCXX_KCMC').val(yhzobj.KCXX_KCMC);
				$('#update_SKRQ').val(yhzobj.SKRQ);
				$('#update_XINGQI').val(yhzobj.XINGQI);
				$('#update_KCXX_ID').val(yhzobj.KCXX_ID);
				$('#update_ZYMC').val(yhzobj.ZYMC);
				$('#update_ZCCXBL').val(yhzobj.ZCCXBL);
				$('#update_XY_ID').val(yhzobj.XY_ID);
				$('#update_CQQK').val(yhzobj.CQQK);
				$('#update_ZTRSBL').val(yhzobj.ZTRSBL);
				$('#update_ZHOU').val(yhzobj.ZHOU);
				$('#update_KS_ID').val(yhzobj.KS_ID);
				$('#update_XYMC').val(yhzobj.XYMC);
				$('#update_CDRS').val(yhzobj.CDRS);
				$('#update_XUEQI_ID').val(yhzobj.XUEQI_ID);
				$('#update_XS_ZCCX').val(yhzobj.XS_ZCCX);
				$('#update_JSRQ').val(yhzobj.JSRQ);
				$('#update_QXRSBL').val(yhzobj.QXRSBL);
				$('#update_JS_ID').val(yhzobj.JS_ID);
				$('#update_JG_ID').val(yhzobj.JG_ID);
				$('#update_ZY_ID').val(yhzobj.ZY_ID);
				$('#update_TONGJI_ID').val(yhzobj.TONGJI_ID);
				$('#update_optionflag').val('updateTONGJI');
			}
		});
	}
}
// 修改信息
function updateTONGJI() {
	$('#updateTONGJI_form').form('submit', {
		url : 'updateTONGJI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateTONGJI_form').form('clear');
				$('#updateTONGJI_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_XS_CD').val("");
	$('#update_XS_ID').val("");
	$('#update_XS_XM').val("");
	$('#update_QXRS').val("");
	$('#update_NJ_ID').val("");
	$('#update_CDRSBL').val("");
	$('#update_XS_XH').val("");
	$('#update_ZCCXRS').val("");
	$('#update_JS_JSMC').val("");
	$('#update_KSRQ').val("");
	$('#update_XS_QX').val("");
	$('#update_ZTRS').val("");
	$('#update_NJMC').val("");
	$('#update_YSKRS').val("");
	$('#update_JG_JGGH').val("");
	$('#update_XS_ZT').val("");
	$('#update_BJMC').val("");
	$('#update_BJ_ID').val("");
	$('#update_JG_JGMC').val("");
	$('#update_KCXX_KCMC').val("");
	$('#update_SKRQ').val("");
	$('#update_XINGQI').val("");
	$('#update_KCXX_ID').val("");
	$('#update_ZYMC').val("");
	$('#update_ZCCXBL').val("");
	$('#update_XY_ID').val("");
	$('#update_CQQK').val("");
	$('#update_ZTRSBL').val("");
	$('#update_ZHOU').val("");
	$('#update_KS_ID').val("");
	$('#update_XYMC').val("");
	$('#update_CDRS').val("");
	$('#update_XUEQI_ID').val("");
	$('#update_XS_ZCCX').val("");
	$('#update_JSRQ').val("");
	$('#update_QXRSBL').val("");
	$('#update_JS_ID').val("");
	$('#update_JG_ID').val("");
	$('#update_ZY_ID').val("");
	$('#update_TONGJI_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].TONGJI_ID + ",";
			}
			$.ajax({
				url : "delTONGJI.action?nowtime=" + NowTime + "&TONGJI_ID="
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
						url : 'listTONGJI.action',
						title : '所有',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'TONGJI_ID',
						columns : [ [
								{
									title : '<b>字段1</b>',
									field : 'XS_CD',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段2</b>',
									field : 'XS_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段3</b>',
									field : 'XS_XM',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段4</b>',
									field : 'QXRS',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段5</b>',
									field : 'NJ_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段6</b>',
									field : 'CDRSBL',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段7</b>',
									field : 'XS_XH',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段8</b>',
									field : 'ZCCXRS',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段9</b>',
									field : 'JS_JSMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段10</b>',
									field : 'KSRQ',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段11</b>',
									field : 'XS_QX',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段12</b>',
									field : 'ZTRS',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段13</b>',
									field : 'NJMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段14</b>',
									field : 'YSKRS',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段15</b>',
									field : 'JG_JGGH',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段16</b>',
									field : 'XS_ZT',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段17</b>',
									field : 'BJMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段18</b>',
									field : 'BJ_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段19</b>',
									field : 'JG_JGMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段20</b>',
									field : 'KCXX_KCMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段21</b>',
									field : 'SKRQ',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段22</b>',
									field : 'XINGQI',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段23</b>',
									field : 'KCXX_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段24</b>',
									field : 'ZYMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段25</b>',
									field : 'ZCCXBL',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段26</b>',
									field : 'XY_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段27</b>',
									field : 'CQQK',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段28</b>',
									field : 'ZTRSBL',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段29</b>',
									field : 'ZHOU',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段30</b>',
									field : 'KS_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段31</b>',
									field : 'XYMC',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段32</b>',
									field : 'CDRS',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段33</b>',
									field : 'XUEQI_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段34</b>',
									field : 'XS_ZCCX',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段35</b>',
									field : 'JSRQ',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段36</b>',
									field : 'QXRSBL',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段37</b>',
									field : 'JS_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段38</b>',
									field : 'JG_ID',
									sortable : true,
									width : 100
								},								{
									title : '<b>字段39</b>',
									field : 'ZY_ID',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'TONGJI_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateTONGJI_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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

