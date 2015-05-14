//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		JGMC : $('#JGMC_serchbar').val(),
		JTZZ : $('#JTZZ_serchbar').val(),
		SHENGAO : $('#SHENGAO_serchbar').val(),
		SFZHM : $('#SFZHM_serchbar').val(),
		MZ : $('#MZ_serchbar').val(),
		JGLBMC : $('#JGLBMC_serchbar').val(),
		TIZHONG : $('#TIZHONG_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		XUELI : $('#XUELI_serchbar').val(),
		BYYX : $('#BYYX_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		CSNY : $('#CSNY_serchbar').val(),
		XB : $('#XB_serchbar').val(),
		ZSXM : $('#ZSXM_serchbar').val(),
		JGGH : $('#JGGH_serchbar').val(),
		optionflag : 'selbylike'
	});
}

// 打开新增信息对话框
function addJIAOGONG_dialog() {
	$('#addJIAOGONG_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	$.parser.parse('#addJIAOGONG_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息
function addJIAOGONG() {
	$('#addJIAOGONG_form')
			.form(
					'submit',
					{
						url : 'addJIAOGONG.action',
						onSubmit : function() {
							/**
							 * 对添加的信息进行验证
							 */
							// 教工名称匹配规则和教工的真实姓名匹配规则和家庭地址匹配规则
							var jgAndzsNameRes = new RegExp(
									"^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
							// 教工工号匹配规则(8位教工工号)
							var jgGHRex = new RegExp("^\\d{8}$");
							// 身高和体重匹配规则
							// var sgTzRex=new RegExp("^\\d{8}$");
							// 身份证匹配
							var idCard = new RegExp("^\\d{17}[\\d|X]|\\d{15}$");

							// 对教工名称进行验证
							if (jgAndzsNameRes.test($("#JGMC").val())) {
								// 对教工的真实姓名进行验证
								if (jgAndzsNameRes.test($("#ZSXM").val())) {
									
								} else {
									$.messager.alert('提示',
											'请输入正确的真实姓名(中文、英文、数字、下划线或者中括号)');
									return false;
								}
							} else {
								$.messager.alert('提示',
										'请输入正确的教工名称(中文、英文、数字、下划线或者中括号)');
								return false;
							}

						},
						success : function(data) {
							if ("false" == data) {
								$.messager.alert('提示', '新增信息失败！');
							} else {
								var obj2 = eval("(" + data + ")");
								$.messager.alert('提示', obj2.message);
								$('#addJIAOGONG_form').form('clear');
								$('#addJIAOGONG_dlg').dialog('close');
							}
							$('#datagrid').datagrid('clearSelections');
							$('#datagrid').datagrid('reload');
						}
					});
}
// 打开修改对话框
function updateJIAOGONG_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.JG_ID) {
		$.ajax({
			url : "updateJIAOGONG.action?nowtime=" + NowTime + "&JG_ID="
					+ row.JG_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateJIAOGONG_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateJIAOGONG_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_JGMC').val(yhzobj.JGMC);
				$('#update_JTZZ').val(yhzobj.JTZZ);
				$('#update_SHENGAO').val(yhzobj.SHENGAO);
				$('#update_SFZHM').val(yhzobj.SFZHM);
				$('#update_MZ').combobox('setValue', yhzobj.MZ);
				$('#update_JGLBMC').combobox('setValue', yhzobj.JGLB_ID);
				$('#update_TIZHONG').val(yhzobj.TIZHONG);
				$('#update_MS').val(yhzobj.MS);
				$('#update_XUELI').combobox('setValue', yhzobj.XUELI);
				$('#update_BYYX').val(yhzobj.BYYX);
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_CSNY').datebox("setValue", yhzobj.CSNY);
				$('#update_XB').combobox('setValue', yhzobj.XB);

				$('#update_YH_ID').combobox('setValue', yhzobj.YH_ID);
				$('#update_TMP1').combobox('setValue', yhzobj.TMP1);

				$('#update_ZSXM').val(yhzobj.ZSXM);
				var gh = yhzobj.JGGH;
				$('#update_JGGH').val(
						gh.substring(gh.indexOf("L") + 1, gh.length));

				$('#update_JG_ID').val(yhzobj.JG_ID);
				$('#update_optionflag').val('updateJIAOGONG');
			}
		});
	}
}
// 修改信息
function updateJIAOGONG() {
	$('#updateJIAOGONG_form').form('submit', {
		url : 'updateJIAOGONG.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateJIAOGONG_form').form('clear');
				$('#updateJIAOGONG_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_JGMC').val("");
	$('#update_JTZZ').val("");
	$('#update_SHENGAO').val("");
	$('#update_SFZHM').val("");
	$('#update_MZ').val("");
	$('#update_JGLBMC').val("");
	$('#update_TIZHONG').val("");
	$('#update_MS').val("");
	$('#update_XUELI').val("");
	$('#update_BYYX').val("");
	$('#update_BZ').val("");
	$('#update_CSNY').val("");
	$('#update_XB').val("");
	$('#update_ZSXM').val("");
	$('#update_JGGH').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].JG_ID + ",";
			}
			$.ajax({
				url : "delJIAOGONG.action?nowtime=" + NowTime + "&JG_ID="
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

var jgid="";
// 打开教工指纹录入对话框
function jgzwlr_dlg() {
	var row = $("#datagrid").datagrid("getSelected");
	jgid=row.JG_ID;
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.JG_ID) {
		// 打开对话框
		$("#jg_zwlr_dlg").dialog("open").dialog("setTitle", "录入指纹");
		$.parser.parse('#jg_zwlr_dlg');
		// 查询出老师的指纹是否存在，如果存在，就进行提示“指纹已经存在”
		$.ajax({
			type : "POST",
			url : "updateJIAOGONG.action?JG_ID=" + row.JG_ID,
			success : function(data) {
				var obj = eval("(" + data + ")");
				$("#jg_id").val(row.JG_ID);
				// 判断指纹1是否存在
				if (obj.ZHIWEN_ID1 == "" || obj.ZHIWEN_ID1 == "无") {
					$('#jg_zhiwenId1').val("");
				} else {
					$('#jg_zhiwenId1').val("已存在");
				}
				// 判断指纹2是否存在
				if (obj.ZHIWEN_ID2 == "" || obj.ZHIWEN_ID2 == "无") {
					$('#jg_zhiwenId2').val("");
				} else {
					$('#jg_zhiwenId2').val("已存在");
				}
			}
		});

	}
}

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
	try{
		if (FPEngineEx1.OpenDevice(0, 0, 0) == 1) {
			if (FPEngineEx1.LinkDevice() == 1) {
				var ens = document.getElementById("es");
				FPEngineEx1.EnrFptEx();
				ens.value = "就绪";
				timer = setTimeout("Transaction()", 500);
			} else
				{alert("连接USB指纹仪失败");}
		} else
			{alert("打开USB指纹仪失败");}
	}catch(ex){
		$(function(){
			$("#downLoad").slideToggle(500);
		});
	}

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
// 新增房间信息
function addJGFp() {
			$('#jg_zwlr_form').form(
					'submit',
					{
						url : 'addJGZHIWEN.action',
						onSubmit : function() {
							return $(this).form('validate');
						},
						success : function(data) {
							var obj = eval('(' + data + ')');
							$.messager.alert('提示', obj.message);
							$("#jg_zwlr_dlg").dialog("close");
								$('#jg_zwlr_form').form('clear');
								$('#datagrid').datagrid('clearSelections');
								$('#datagrid').datagrid('reload');
							}
//						}
					});
		}



// 取消按钮
function cacelSave() {
	// 关闭窗体
	$("#jg_zwlr_dlg").dialog("close");
	// 清除表单
	$("#single_zwlr_form").form("clear");
	$('#datagrid').datagrid('clearSelections');
}

function delJGZHIWEN() {
	var row = $('#datagrid').datagrid('getSelected');
	 if (row < 1) {
//	 $.messager.alert('提示', '请选择要删除指纹的记录');
	 return;
	 }else {
		  $.messager.confirm("删除指纹信息", " 确认要删除选中的老师指纹信息吗？", function(r) {
			if (r) {
				$.ajax({
					url : "delJGZHIWEN.action?JG_ID=" + row.JG_ID+"&"+Math.random(),
					success : function(data) {
						var obj = eval("(" + data + ")");
						$.messager.alert('提示', obj.message);
							$('#datagrid').datagrid('clearSelections');
							$('#datagrid').datagrid('reload');
					}
				});
			}
		});
		}
}
// 初始化方法
$(function() {
	$('#datagrid')
			.datagrid(
					{
						url : 'listJIAOGONG.action',
						title : '教工信息管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns :false,
						nowrap : false,
						border : false,
						idField : 'JG_ID',
						columns : [ [
								{
									title : '<b>教工名称</b>',
									field : 'JGMC',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>真实姓名</b>',
									field : 'ZSXM',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>教工工号</b>',
									field : 'JGGH',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>教工类别</b>',
									field : 'JGLBMC',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>性别</b>',
									field : 'XB',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>家庭住址</b>',
									field : 'JTZZ',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>身高</b>',
									field : 'SHENGAO',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>体重</b>',
									field : 'TIZHONG',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>民族</b>',
									field : 'MZ',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>身份证号码</b>',
									field : 'SFZHM',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>出生年月</b>',
									field : 'CSNY',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>学历</b>',
									field : 'XUELI',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>毕业院校</b>',
									field : 'BYYX',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>备注</b>',
									field : 'BZ',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>描述</b>',
									field : 'MS',
									sortable : true,
									fitColumns :true
								},
								{
									title : '<b>操作</b>',
									field : 'JG_ID',
									width:150,
									formatter : function(value, rowData,
											rowIndex) {
										return "<a style='cursor:hand;' onclick='updateJIAOGONG_dialog();'>修改</a>"
												+ "-"
												+ "<a style='cursor:hand;' onclick='jgzwlr_dlg();'>指纹录入</a>"
												+ "-"
												+ "<a style='cursor:hand;' onclick='delJGZHIWEN();'>指纹删除</a>";
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