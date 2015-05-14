/** **********************************进行模糊查询*********************************** */
function doSearch() {
	$('#datagrid').datagrid('load', {
		XSXH : $("#xsxh_serchbar").val(),// 学生的学号
		ZSXM : $("#xsxm_serchbar").val(),// 学生的真实姓名
		CQZT : $("#cqzt_serchbar").val(),// 出勤状态
		ZYMC : $("#zymc_serchbar").combobox("getValue"),// 专业名称
		BJMC : $("#bjmc_serchbar").combobox("getValue"),// 班级名称
		NJMC : $("#njmc_serchbar").combobox("getValue")
	});
}

//初始化combobox
$(function() {
	$("#zymc_serchbar").combobox({
		url : 'listAllZHUANYE.action',
		valueField : 'ZY_ID',
		textField : 'ZYMC'
	});
	$("#bjmc_serchbar").combobox({
		url : 'listAllBANJI.action',
		valueField : 'BJ_ID',
		textField : 'BJMC'
	});

	$("#njmc_serchbar").combobox({
		url : 'listAllNIANJI.action',
		valueField : 'NJ_ID',
		textField : 'NJMC'
	});
});
