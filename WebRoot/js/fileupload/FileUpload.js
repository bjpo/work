/** **************************************************初始化数据********************************************* */
$(function() {
	// 显示数据表格
	$('#datagrid')
			.datagrid(
					{
						url : 'showFileList.action',
						title : '基础数据管理列表',
						iconCls : 'icon-cls',
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'fileId',
						columns : [ [
								{
									title : '<b>文件名称</b>',
									field : 'fileName',
									sortable : true,
									width : 200
								},
								{
									title : '<b>文件类型</b>',
									field : 'fileType',
									sortable : true,
									width : 150
								},
								{
									title : '<b>上传时间</b>',
									field : 'updateTime',
									sortable : true,
									width : 150
								},
								{
									title : '<b>导入到数据库</b>',
									width : 150,
									field : 'fileId',
									formatter : function(value, rowData,
											rowIndex) {
										if (rowData.importDataStatus == "0") {
											return "<a style='cursor:hand;' onclick='fileUploadImport_dlg();'><img src='js/jquery-easyui/themes/icons/pencil.png' alt='导入' style='border:0px;'/></a>";
										} else {
											return "<a style='cursor:hand;'>文件已经导入</a>";
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
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});

/** *************************************导入********************************** */
var comBoBoxId = "";
var toMoBanId = "";
function fileUploadImport_dlg() {
	// 导入数据弹出窗口
	$('#fileUploadImport_dlg').dialog('open').dialog('setTitle', '导入数据');
	// 模板选择下拉列表框
	$('#selectMb').combobox({
		url : 'listAllMOBAN.action',
		valueField : 'MB_ID',
		textField : 'MB_NAME',
		onSelect : function(rec) {
			toMoBanId = rec.MB_ID;
		}
	});

	$.parser.parse('#fileUploadImport_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
/** ****************************将excel中的数据导入到数据库*************************************** */
var importDataStatus = "";
function importDataToDb() {
	$.messager.confirm("导入提示信息", " 确认要将选中的文件导入吗？", function(r) {
		if (r) {
			importDataStatus = "1";
			// var row = $('#datagrid').datagrid('getSelections');
			var row = $('#datagrid').datagrid('getSelected');
			// 获取要导入文件的ID
			var importFileId = "";

			// 获取要导入文件的名字
			var importFileName = "";

			for ( var i = 0; i < row.length; i++) {
				if (0 == i) {
					// 小于等于一个Id时行拼接
					importFileId = row[i].fileId;
					importFileName = row[i].fileName;
				} else {
					importFileId += ',' + row[i].fileId;
					importFileName += ',' + row[i].fileName;
				}
			}
			$.ajax({
				url : "importDataToDB.action?newtime=" + Math.random()
						+ "&importFileId=" + importFileId + "&importFileName="
						+ encodeURI(encodeURI(row.fileName)) + "&comBoBoxId="
						+ comBoBoxId + "&importDataStatus=" + importDataStatus
						+ "&toMoBanId=" + toMoBanId,
				context : document.body,
				success : function(data) {
					var obj = eval("(" + data + ")");
					$.messager.alert('提示', obj.message);
					// 导入成功之后，"操作"列显示"已经导入"
					$('#fileUploadImport_form').form('clear');
					$('#fileUploadImport_dlg').dialog('close');
					$('#datagrid').datagrid('clearSelections');
					$('#datagrid').datagrid('reload');
				}
			});
		} else {
			importDataStatus = "0";
		}
	});
	return false;
}

/** ***********************************************点击上传按钮调用的方法************************************** */
function FileUpload_dialog() {
	$('#FileUpload_dlg').dialog('open').dialog('setTitle', '上传文件'); // 新增学院信息弹出窗口
	$('#fileName').text("");
	$('#fileSize').text("");
	$('#fileType').text("");
	$.parser.parse('#FileUpload_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}

/** **************************************************上传文件方法**************************************** */
function FileUpload() {
	$('#fileUpload_form').form('submit', {
		url : 'fileUpload.action',
		onSubmit : function() {
			if (document.getElementById('fileToUpload').value == null) {
				$.messager.alert('提示', '上传文件不能为空，请选择要上传的文件！');
				return "false";
			}
		},
		// 服务器返回状态，如果成功返回则执行下面的内容
		success : function(data) {
			// alert(data);
			// return;
			// 进行判断上传文件是不是成功了
			if (data.success==false) {
				$.messager.alert('提示', '上传文件失败！');
			} else {
				$.messager.alert('提示', '上传文件成功！');
				$('#fileUpload_form').form('clear');
				$('#FileUpload_dlg').dialog('close');
			}

			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');

		}
	});
}
/** ****************************************对上传的文件进行判断类型***************************** */
function fileSelected() {
	// 获取上传文件对象
	var file = document.getElementById('fileToUpload').files[0];
	// 获取上传文件的名字
	var fileName = file.name;
	// 获取上传文件的类型
	var file_typename = fileName.substring(fileName.lastIndexOf('.'),
			fileName.length);
	// 文件类型
	var type = "";
	// 这里限定上传文件文件类型
	if (file_typename == '.xls' || file_typename == '.xlsx') {
		// 判断是不是文件
		if (file) {
			var fileSize = 0;
			// 判断文件的大小,并显示其文件大小的单位
			if (file.size > 1024 * 1024)
				fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100)
						.toString()
						+ 'MB';
			else
				fileSize = (Math.round(file.size * 100 / 1024) / 100)
						.toString()
						+ 'KB';

			if (file.type == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
				type = ".xlsx"
			} else {
				type = ".xls";
			}
			// 显示一些文件的信息
			$('#fileName').text("文件名: " + file.name);
			$('#fileSize').text("大小:" + fileSize);
			$('#fileType').text("类型: " + type);
		}
	} else {
		$('#fileName').text(
				"<span style='color:Red'>错误提示:上传文件应该是.xls或.xlsx后缀而不应该是"
						+ file_typename + ",请重新选择文件</span>");
		$('#fileSize').text("");
		$('#fileType').text("");
	}
}

/** ********************************************删除上传文件****************************** */
function delUpload_dialog() {
	// 在删除信息之前给予用户提示
	$.messager.confirm("删除信息", " 确认要删除选中的文件吗？", function(r) {

		if (r) {
			var row = $('#datagrid').datagrid('getSelections');
			// 判断是否选择了记录，如果没有选择进行提示
			if (row < 1) {
				$.messager.alert('提示', '请选择要删除的记录');
				return;
			}
			// 存放拼接要删除文件的名字的变量
			var fileIdStr = "";
			var nameStr = "";
			// 进行循环读取Id
			for ( var i = 0; i < row.length; i++) {
				if (0 == i) {
					// 小于等于一个NameStr时行拼接
					fileIdStr = row[i].fileId;
					nameStr = row[i].fileName;
				} else {
					// 大于一个NameStr时进行拼接，存入到IdStr变量中
					fileIdStr += ',' + row[i].fileId;
					nameStr += ',' + row[i].fileName;
				}
			}
			$.ajax({
				url : "delUploadFile.action?fileId=" + fileIdStr
						+ "&delFileName=" + encodeURI(encodeURI(nameStr)),
				context : document.body,
				success : function(data) {
					var obj = eval("(" + data + ")");
					$('#datagrid').datagrid('clearSelections');
					$('#datagrid').datagrid('reload');
				}
			});
		}
	});
	return false;
}
/** ******************************根据用户输入的条件进行模糊查询**************************************** */
function fileUploadSearch() {
	$('#datagrid').datagrid('load', {
		fileName : $('#fileUpload_serchbar').val()
	});
}
