//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		MB_NAME : $('#MB_NAME_serchbar').val(),
		BIAOMING : $('#BIAOMING_serchbar').val(),
		optionflag : 'selbylike'
	});
}
// 打开新增信息对话框
function addMOBAN_dialog() {
	$('#addMOBAN_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addMOBAN_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
function tt(){
	$.messager.alert("提示",$("#radioBtn input:radio:checked").val());
}
// 新增信息
function addMOBAN() {
	var signFrame = findObj("SignFrame", document);
	var rowscount = signFrame.rows.length;

	var txtExcelName = new Array();
	var txtDB = new Array();
	// 遍历填写数据的行数
	for ( var i = 1; i < rowscount; i++) {
		// 获取excel标题列的单元格的值
		var ExcelName = $('#txtName' + i).val();
		txtExcelName.push(ExcelName);
		// 获取DB字段列的单元格的值
		var DB = $('#txtDB' + i).val();
		txtDB.push(DB);
	}

	$('#addMOBAN_form').form(
			'submit',
			{
				url : 'addMOBAN.action?rowcount=' + rowscount
						+ "&txtExcelName=" + txtExcelName + "&txtDB=" + txtDB,
				onSubmit : function() {
					//模板名称匹配规则
					var MB_NAMEReg=new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
					// 判断模板名称是否为空
					if (MB_NAMEReg.test($('#MB_NAME').val())) {
						// 判断模板对应表是否为空
						if ($('#BIAOMING').val() != "") {
							// 判断对应表字段是否为空
							if ($('#ZHUJIAN').val() != "") {
								return true;
							} else {
								$.messager.alert('提示', '请输入模板对应字段');
								return false;
							}
						} else {
							$.messager.alert('提示', '请输入模板对应表');
							return false;
						}
					} else {
						$.messager.alert('提示', '请输入模板名称(字母、数字、下划线以及中括号)');
						return false;
					}

				},
				success : function(data) {
					if ("false" == data) {
						$.messager.alert('提示', '新增信息失败！');
					} else {
						var obj2 = eval("(" + data + ")");
						$.messager.alert('提示', obj2.message);
						$('#addMOBAN_form').form('clear');
						$('#addMOBAN_dlg').dialog('close');
					}
					$('#datagrid').datagrid('clearSelections');
					$('#datagrid').datagrid('reload');
				}
			});

}
// 打开修改对话框
var updateMB_ID = "";
function updateMOBAN_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	updateMB_ID = row.MB_ID;
	if (row.MB_ID) {
		$.ajax({
			url : "updateMOBAN.action?nowtime=" + Math.random() + "&MB_ID="
					+ row.MB_ID,
			context : document.body,
			success : function(data) {
				var mohanobj = eval("(" + data + ")");
				$('#updateMOBAN_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateMOBAN_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_MB_NAME').val(mohanobj.MB_NAME);
				$('#update_BIAOMING').val(mohanobj.BIAOMING);
				$('#update_ZHUJIAN').val(mohanobj.ZHUJIAN);
				$('#update_MB_ID').val(mohanobj.MB_ID);
				$('#update_optionflag').val('updateMOBAN');
			}
		});
	}
}
// 修改信息
function updateMOBAN() {
	$('#updateMOBAN_form').form('submit', {
		url : 'updateMOBAN.action?updateMB_ID=' + updateMB_ID,
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateMOBAN_form').form('clear');
				$('#updateMOBAN_dlg').dialog('close');

			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_MB_NAME').val("");
	$('#update_MB_ID').val("");
	$('#update_BIAOMING').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].MB_ID + ",";
			}
			$.ajax({
				url : "delMOBAN.action?nowtime=" + NowTime + "&MB_ID="
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
						url : 'listMOBAN.action',
						title : '模板管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'MB_ID',
						columns : [ [
								{
									title : '<b>模板名称</b>',
									field : 'MB_NAME',
									sortable : true,
									width : 200
								},
								{
									title : '<b>对应表名</b>',
									field : 'BIAOMING',
									sortable : true,
									width : 200
								},
								{
									title : '<b>操作</b>',
									field : 'MB_ID',
									width : 200,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateMOBAN_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png' alt='修改' style='border:0px;'/></a>";
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
	//用户分组combobox
	$("#insertType").combobox({
		url : "listAllYHZ.action",
		valueField : "yhzid",
		textField : "yhzmc"
	});
});

/** *******************************************************************模板配置*************************** */
function templateconf_dialog() {
	$('#templateconf_dlg').dialog('open').dialog('setTitle', '模板配置'); // 新增模板信息弹出窗口
	// $.parser.parse('#templateconf_dialog'); // 需要重新渲染对话框，否则easyui不起作用

}

/** *************************************************************动态加行与列************************************* */
function findObj(theObj, theDoc) {
	var p, i, foundObj;
	if (!theDoc)
		theDoc = document;
	if ((p = theObj.indexOf("?")) > 0 && parent.frames.length) {
		theDoc = parent.frames[theObj.substring(p + 1)].document;
		theObj = theObj.substring(0, p);
	}
	if (!(foundObj = theDoc[theObj]) && theDoc.all)
		foundObj = theDoc.all[theObj];

	for (i = 0; !foundObj && i < theDoc.forms.length; i++)
		foundObj = theDoc.forms[i][theObj];

	for (i = 0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
		foundObj = findObj(theObj, theDoc.layers[i].document);

	if (!foundObj && document.getElementById)
		foundObj = document.getElementById(theObj);

	return foundObj;
}
/** ************************************************添加一行************************************ */
function AddSignRow() {
	// 读取最后一行的行号，存放在txtTRLastIndex文本框中
	var txtTRLastIndex = findObj("txtTRLastIndex", document);
	var rowID = parseInt(txtTRLastIndex.value);

	var signFrame = findObj("SignFrame", document);

	// 添加行
	var newTR = signFrame.insertRow(signFrame.rows.length);
	newTR.id = "SignItem" + rowID;

	// 添加列:序号
	var newNameTD = newTR.insertCell(0);
	// 添加列内容
	newNameTD.innerHTML = newTR.rowIndex.toString();

	// 添加列:excel标题
	var newNameTD = newTR.insertCell(1);
	// 添加列内容
	newNameTD.innerHTML = "<input name='txtName" + rowID + "' id='txtName"
			+ rowID + "' type='text' size='15px' />";

	// 添加列:DB字段
	var newDBTD = newTR.insertCell(2);
	// 添加列内容
	newDBTD.innerHTML = "<input name='txtDB" + rowID + "' id='txtDB" + rowID
			+ "' type='text' size='15px' />";

	// 添加列:删除按钮
	var newDeleteTD = newTR.insertCell(3);
	// 添加列内容
	newDeleteTD.innerHTML = "<div style='width:40px'><a href='javascript:;' onclick=\"DeleteSignRow('SignItem"
			+ rowID + "')\">删除</a></div>";

	// // 将行号推进下一行
	txtTRLastIndex.value = (rowID + 1).toString();

}
/** ********************************************v删除指定行********************************* */
function DeleteSignRow(rowid) {
	var signFrame = findObj("SignFrame", document);
	var signItem = findObj(rowid, document);

	// 获取将要删除的行的Index
	var rowIndex = signItem.rowIndex;

	// 删除指定Index的行
	signFrame.deleteRow(rowIndex);

	// 重新排列序号，如果没有序号，这一步省略
	for (i = rowIndex; i < signFrame.rows.length; i++) {
		signFrame.rows[i].cells[0].innerHTML = i.toString();
	}
}
/** ********************************************清空列表*************************************** */
function ClearAllSign() {
	$.messager.confirm("删除信息", " 确认要删除填写的所有信息吗？", function(r) {
		if (r) {
			var signFrame = findObj("SignFrame", document);
			var rowscount = signFrame.rows.length;

			// 循环删除行,从最后一行往前删除
			for (i = rowscount - 1; i > 0; i--) {
				signFrame.deleteRow(i);
			}
			signFrame.deleteRow(rowscount);
			// 重置最后行号为1
			var txtTRLastIndex = findObj("txtTRLastIndex", document);
			txtTRLastIndex.value = "1";

			// 预添加一行
			AddSignRow();
		}
	});
}
/** ******************************************获取动态单元格中的数据**************************** */

function getData() {
	var row = $('#datagrid').datagrid('getSelected');

	var signFrame = findObj("SignFrame", document);
	var rowscount = signFrame.rows.length;

	var txtExcelName = null;
	var txtDB = null;

	// 遍历填写数据的行数
	for ( var i = 1; i < rowscount; i++) {
		// 获取excel标题列的单元格的值
		txtExcelName = $('#txtName' + i).val();
		// 获取DB字段列的单元格的值
		txtDB = $('#txtDB' + i).val();
		$.ajax({
			url : "addMOBAN_ZIDUAN.action?newTime=" + Math.random() + "&MB_ID="
					+ row.MB_ID + "&txtExcelName=" + txtExcelName + "&txtDB="
					+ txtDB,
			success : function(data) {
				if ("false" == data) {
					$.messager.alert('提示', '新增模板字段失败！');
				} else {
					var obj2 = eval("(" + data + ")");
					$.messager.alert('提示', obj2.message);
					$('#templateconf_form').form('clear');
					$('#templateconf_dlg').dialog('close');
				}
				$('#datagrid').datagrid('clearSelections');
				$('#datagrid').datagrid('reload');
			}
		});
	}
}
