// 初始化方法
$(function() {
	$('#datagrid')
			.datagrid(
					{
						url : 'zwdd.json',
						title : '指纹断电列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'id',
						columns : [ [
								{
									title : '<b>设备编号</b>',
									field : 'sbbh',
									sortable : true,
									width : 100
								},
								{
									title : '<b>设备名称</b>',
									field : 'sbmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>所在教室</b>',
									field : 'jsmc',
									sortable : true,
									width : 100
								},
								{
									title : '<b>状态</b>',
									field : 'zt',
									sortable : true,
									width : 100
								},
								{
									title : '<b>当前操作员</b>',
									field : 'czy',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'id',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a id='tt'><input type='button'onclick='tog(this);' value='关闭' id='s"+rowIndex+"'/></a>";
									}
								} ] ],
						singleSelect : false,// 是否单选
						pagination : true,// 分页控件
						rownumbers : true
					});
	$('#datagrid').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});

var sm = true;
function tog(e) {
	var tr=$(e).parent().parent().parent().parent().attr("datagrid-row-index");
	var t=$(e).attr("id");
	$("#"+t).click(function() {
		if (sm == true) {
			$("table tr[datagrid-row-index='"+tr+"'] td[field='zt'] div").text("关闭");
			$("#"+t).attr("value", "开启");
			sm = false;
			return ;
		}else{
			if (sm == false) {
				$("table tr[datagrid-row-index='"+tr+"'] td[field='zt'] div").text("开启");
				$("#"+t).attr("value", "关闭");
				sm = true;
				return ;
			}
		}
	});

}
