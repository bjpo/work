//模糊查询
function doSearch() {
	$('#datagrid').datagrid('load', {
		KS_KSSJ : $('#KS_KSSJ_serchbar').val(),
		KCBLB : $('#KCBLB_serchbar').val(),
		LAOSHIGH : $('#LAOSHIGH_serchbar').val(),
		LAOSHIMC : $('#LAOSHIMC_serchbar').val(),
		JSZHOU : $('#JSZHOU_serchbar').val(),
		KSZHOU : $('#KSZHOU_serchbar').val(),
		XINGQI : $('#XINGQI_serchbar').val(),
		MS : $('#MS_serchbar').val(),
		JSMC : $('#JSMC_serchbar').val(),
		BZ : $('#BZ_serchbar').val(),
		XINGQIXH : $('#XINGQIXH_serchbar').val(),
		KSMC : $('#KSMC_serchbar').val(),
		KS_JSSJ : $('#KS_JSSJ_serchbar').val(),
		KCXXMC : $('#KCXXMC_serchbar').val(),
		optionflag : 'selbylike'
	});
}
function showaddStep1() {
	$("#addkcb_step1").show();
	$("#addkcb_step2").hide();
	$("#addkcb_step3").hide();
}
function showaddStep2() {
	// 判断任课教师下拉列表框是否为空
	if ($('#LAOSHIGH').combobox('getText') != "") {
		$("#addkcb_step1").hide();
		$("#addkcb_step2").show();
		$("#addkcb_step3").hide();
	} else {
		// 任课教师下拉列表框为空时，进行提示
		$.messager.alert('提示', '请先选择任课教师');
		return false;
	}

}
function showaddStep3() {
	// 判断课程信息名称下拉列表框是否为空
	if ($('#KCXXMC').combobox('getText') != "") {
		// 判断开始周下拉列表框是否为空
		if ($('#KSZHOU').combobox('getText') != "") {
			// 判断结束周下拉列表框是否为空
			if ($('#JSZHOU').combobox('getText') != "") {
				// 判断课程表类别下拉列表框是否为空
				if ($('#KCBLB').combobox('getText') != "") {
					// 判断上课班级下拉列表框是否为空
					// if ($('#SKBJ').combobox('getText') != "") {
					$("#addkcb_step1").hide();
					$("#addkcb_step2").hide();
					$("#addkcb_step3").show();
					// } else {
					// $.messager.alert('提示', '请选择上课的班级');
					// return false;
					// }
				} else {
					$.messager.alert('提示', '请选择课程表的类别');
					return false;
				}
			} else {
				$.messager.alert('提示', '请选择课程的结束周');
				return false;
			}
		} else {
			$.messager.alert('提示', '请选择课程的开始周');
			return false;
		}
	} else {
		$.messager.alert('提示', '请选择课程信息名称');
		return false;
	}

}

//新版新增信息JS 开始
//新增信息按钮点击事件
function addKECHENGB_dialog1()
{
    var NowTime = new Date().toLocaleTimeString();
    // 获取一个课程表ID
    $.ajax({
        url: "getKCBID.action?nowtime=" + NowTime,
        context: document.body,
        success: function (data) {
            var yhzobj = eval("(" + data + ")");
            $('#KCB_ID').val(yhzobj.uuid);
        }
    });
    // 老师下拉框
    $('#LAOSHIGH').combobox({
        formatter: function (row) {
            return "" + row.JGGH + "|" + row.JGMC;
        }
    });
    // 课程下拉框
    $('#KCXXMC').combobox({
        formatter: function (row) {
            return "" + row.KECHENGDM + "|" + row.KECHENGMC;
        }
    });
    $("#addKECHENGB_dlg1").dialog({
        onBeforeClose: function () {
            $("#addKECHENGB_dlg1 div[class='copy']").remove();
            $("#addKECHENGB_form1").form("clear");
            $("#datagrid").datagrid("clearSelections");
            $("#datagrid").datagrid("reload");
        }
    });
    $("#addKECHENGB_dlg1").dialog("open");
    zengjiapaike();
}
//增加排课按钮点击事件
function zengjiapaike()
{
    $("#flagdiv").before($("#copydiv > div").clone());
    zengjiadiv();
}
//增加教室,星期,课时DIV
function zengjiadiv()
{
    $.ajax({
        url: $("#basePath").val() + "listAllJIAOSHI.action",
        success: function (data) {
            var obj = eval("(" + data + ")");
            $.each(obj, function (i, n)
            {
                $("#flagdiv").prev("div").children("div").eq(0).children("select").append("<option value='" + n.JS_ID + "'>" + n.FJMC + "</option>");
            });
        }
    });
    $.ajax({
        url: $("#basePath").val() + "js/xingqi.json",
        success: function (data) {
            $.each(data, function (i, n)
            {
                $("#flagdiv").prev("div").children("div").eq(1).children("select").append("<option value='" + n.xingqi + "'>" + n.xingqi + "</option>");
            });
        }
    });
    $.ajax({
        url: $("#basePath").val() + "listAllKESHI.action",
        success: function (data) {
            var obj = eval("(" + data + ")");
            $.each(obj, function (i, n)
            {
                $("#flagdiv").prev("div").children("div").eq(2).children("select").append("<option value='" + n.KS_ID + "'>" + n.KSMC + "</option>");
            });
        }
    });
}
//保存排课按钮点击事件
function baocunpaike()
{
    if ($("#XUEQI").combobox("isValid") === false)
    {
        $.messager.alert("提示", "请选择排课学期");
        return;
    }
    if ($("#LAOSHIGH").combobox("isValid") === false)
    {
        $.messager.alert("提示", "请选择任课教师");
        return;
    }
    if ($("#KCXXMC").combobox("isValid") === false)
    {
        $.messager.alert("提示", "请选择课程名称");
        return;
    }
    if ($("#KSZHOU").combobox("isValid") === false)
    {
        $.messager.alert("提示", "请选择课程开始周");
        return;
    }
    if ($("#JSZHOU").combobox("isValid") === false)
    {
        $.messager.alert("提示", "请选择课程结束周");
        return;
    }
    if ($("#KCBLB").combobox("isValid") === false)
    {
        $.messager.alert("提示", "请选择课程类别");
        return;
    }
    if (yanzhengdiv() === false)
    {
        return;
    }
    $("#addKECHENGB_dlg1 div[class='copy']").each(function (i, n)
    {
        $.ajax({
            type: "POST",
            traditional: true,
            url: $("#basePath").val() + "addKECHENGB.action",
            data: {"JSZHOU": $("#JSZHOU").combobox("getValue"), "JS_ID": $(n).find("option:selected:eq(0)").val(), "KCBLB": $("#KCBLB").combobox("getValue"), "KCB_ID": $("#KCB_ID").val()
                , "KCXXMC": $("#KCXXMC").combobox("getValue"), "KSZHOU": $("#KSZHOU").combobox("getValue"), "KS_ID": $(n).find("option:selected:eq(2)").val()
                , "LAOSHIGH": $("#LAOSHIGH").combobox("getValue"), "SKBJ": $("#SKBJ").combobox("getValues"), "XINGQI": $(n).find("option:selected:eq(1)").val()
                , "XUEQI": $("#XUEQI").combobox("getValue"), "maxrs": $("#maxrs").val()},
            success: function (data)
            {
                if (i === $("#addKECHENGB_dlg1 div[class='copy']").length - 1)
                {
                    if ("false" === data)
                    {
                        $.messager.alert('提示', '新增信息失败!');
                    } else {
                        var obj = eval("(" + data + ")");
                        $.messager.alert('提示', obj.message);
                        $("#addKECHENGB_dlg1").dialog("close");
                    }
                }
            }
        });
    });
}
//验证教室,星期,课时DIV
function yanzhengdiv()
{
    var yanzheng = true;
    $("#addKECHENGB_dlg1 div[class='copy']").each(function (i, n)
    {
        if ($(n).find("select[name='JS_ID'] > option:selected").text() === "请选择")
        {
            $.messager.alert("提示", "请选择教室");
            yanzheng = false;
            return false;
        }
        if ($(n).find("select[name='XINGQI'] > option:selected").text() === "请选择")
        {
            $.messager.alert("提示", "请选择星期");
            yanzheng = false;
            return false;
        }
        if ($(n).find("select[name='KS_ID'] > option:selected").text() === "请选择")
        {
            $.messager.alert("提示", "请选择课时");
            yanzheng = false;
            return false;
        }
    });
    return yanzheng;
}
//完成排课按钮点击事件
function wanchengpaike()
{
    $("#addKECHENGB_dlg1").dialog("close");
}
//新版新增信息JS 结束

// 打开新增对话框
function addKECHENGB_dialog() {
	var NowTime = new Date().toLocaleTimeString();
	$('#addKECHENGB_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
	// 获取一个课程表ID
	$.ajax({
		url : "getKCBID.action?nowtime=" + NowTime,
		context : document.body,
		success : function(data) {
			var yhzobj = eval("(" + data + ")");
			$('#KCB_ID').val(yhzobj.uuid);
		}
	});

	// 老师下拉框
	$('#LAOSHIGH').combobox({
		formatter : function(row) {
			return "" + row.JGGH + "|" + row.JGMC;
		}
	});

	// 课程下拉框
	$('#KCXXMC').combobox({
		formatter : function(row) {
			return "" + row.KECHENGDM + "|" + row.KECHENGMC;
		}
	});

	showaddStep1();
	// $('#addKECHENGB_dlg').
	$.parser.parse('#addKECHENGB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增排课信息
function addpaike() {
	$('#addKECHENGB_form').form('submit', {
		url : 'addKECHENGB.action',
		onSubmit : function() {
			// 判断教室下拉列表是否为空
			if ($('#JS_ID').combobox('getText') != "") {
				// 判断星期下拉列表是否为空
				if ($('#XINGQI').combobox('getText') != "") {
					// 判断课时下拉列表是否为空
					if ($('#KESHI').combobox('getText') != "") {
						showaddStep2();
					} else {
						$.messager.alert('提示', '请选择课时');
						return false;
					}
				} else {
					$.messager.alert('提示', '请选择星期');
					return false;
				}
			} else {
				$.messager.alert('提示', '请选择教室');
				return false;
			}

		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增信息失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				// $('#addKECHENGB_form').form('clear');
				// $('#addKECHENGB_dlg').dialog('close');
			}
			$('#datagrid').datagrid('clearSelections');
			$('#datagrid').datagrid('reload');
		}
	});

}

// 排课完成
function addKECHENGB() {
	// $('#addKECHENGB_form').form('submit', {
	// url : 'addKECHENGB.action',
	// onSubmit : function() {
	// },
	// success : function(data) {
	// if ("false" == data) {
	// $.messager.alert('提示', '新增信息失败！');
	// } else {
	// var obj2 = eval("(" + data + ")");
	// $.messager.alert('提示', obj2.message);
	$('#addKECHENGB_form').form('clear');
	$('#addKECHENGB_dlg').dialog('close');
	// }
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	// }
	// });
}
// 打开修改对话框
function updateKECHENGB_dialog() {
	var row = $('#datagrid').datagrid('getSelected');
	var NowTime = new Date().toLocaleTimeString();
	if (typeof (row) == "undefined") {
		return;
	}
	if (!row && typeof (row) != "undefined" && row != 0) {
		return;
	}
	if (row.KCB_FXS_ID) {
		$.ajax({
			url : "updateKECHENGB.action?nowtime=" + NowTime + "&KCB_FXS_ID="
					+ row.KCB_FXS_ID,
			context : document.body,
			success : function(data) {
				var yhzobj = eval("(" + data + ")");
				$('#updateKECHENGB_dlg').dialog('open').dialog('setTitle',
						'查看/修改信息'); // 弹出窗口
				$.parser.parse('#updateKECHENGB_dlg'); // 需要重新渲染对话框，否则easyui不起作用
				$('#update_JSMC').combobox('setValue', yhzobj.JS_ID);// 设置教室
				$('#update_KCBLB').val(yhzobj.KCBLB);
				$('#update_MS').val(yhzobj.MS);
				$('#update_BZ').val(yhzobj.BZ);
				$('#update_LAOSHIMC').combobox('setValue', yhzobj.LAOSHI_ID);// 设置老师
				$('#update_KSMC').combobox('setValue', yhzobj.KS_ID);// 设置课时
				$('#update_KCXXMC').combobox('setValue', yhzobj.KCXX_ID);// 设置课程
				$('#update_KCBLB').combobox('setValue', yhzobj.KCBLB);// 设置课程
				$('#update_JSZHOU').combobox('setValue', yhzobj.JSZHOU);// 结束周
				$('#update_KSZHOU').combobox('setValue', yhzobj.KSZHOU);// 结束周
				$('#update_XINGQI').combobox('setValue', yhzobj.XINGQI);// 星期
				$('#update_KCB_FXS_ID').val(yhzobj.KCB_FXS_ID);
				$('#update_KCB_ID').val(yhzobj.KCB_ID);
				$('#update_optionflag').val('updateKECHENGB');
			}
		});
	}
}
// 修改信息
function updateKECHENGB() {
	$('#updateKECHENGB_form').form('submit', {
		url : 'updateKECHENGB.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', "修改信息失败！");
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#updateKECHENGB_form').form('clear');
				$('#updateKECHENGB_dlg').dialog('close');
			}
		}
	});
	$('#datagrid').datagrid('clearSelections');
	$('#datagrid').datagrid('reload');
	$('#update_KS_KSSJ').val("");
	$('#update_KCBLB').val("");
	$('#update_LAOSHIMC').val("");
	$('#update_JSZHOU').val("");
	$('#update_KSZHOU').val("");
	$('#update_XINGQI').val("");
	$('#update_MS').val("");
	$('#update_JSMC').val("");
	$('#update_BZ').val("");
	$('#update_XINGQIXH').val("");
	$('#update_KSMC').val("");
	$('#update_KS_JSSJ').val("");
	$('#update_KCXXMC').val("");
	$('#update_KCB_FXS_ID').val("");
	$('#update_KCB_ID').val("");
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
				tmpyhzid = tmpyhzid + row[tmpi].KCB_FXS_ID + ",";
			}
			$.ajax({
				url : "delKECHENGB.action?nowtime=" + NowTime + "&KCB_FXS_ID="
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
						url : 'listKECHENGB.action',
						title : '课程表管理列表',
						iconCls : 'icon-cls',
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						fitColumns : false,
						nowrap : false,
						border : false,
						idField : 'KCB_FXS_ID',
						columns : [ [
								{
									title : '<b>老师工号</b>',
									field : 'LAOSHIGH',
									sortable : true,
									width : 100
								},
								{
									title : '<b>老师姓名</b>',
									field : 'LAOSHIMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课程信息名称</b>',
									field : 'KCXXMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课程表类别</b>',
									field : 'KCBLB',
									sortable : true,
									width : 100
								},
								{
									title : '<b>教室名称</b>',
									field : 'JSMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课时名称</b>',
									field : 'KSMC',
									sortable : true,
									width : 100
								},
								{
									title : '<b>开始周</b>',
									field : 'KSZHOU',
									sortable : true,
									width : 100
								},
								{
									title : '<b>结束周</b>',
									field : 'JSZHOU',
									sortable : true,
									width : 100
								},
								{
									title : '<b>星期</b>',
									field : 'XINGQI',
									sortable : true,
									width : 100
								},
								{
									title : '<b>星期序号</b>',
									field : 'XINGQIXH',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课时开始时间</b>',
									field : 'KS_KSSJ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>课时结束时间</b>',
									field : 'KS_JSSJ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>描述</b>',
									field : 'MS',
									sortable : true,
									width : 100
								},
								{
									title : '<b>备注</b>',
									field : 'BZ',
									sortable : true,
									width : 100
								},
								{
									title : '<b>操作</b>',
									field : 'KCB_FXS_ID',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										if (""==value) {
											return "无记录";
										}else{
											return "<a style='cursor:hand;' onclick='updateKECHENGB_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>";
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

	// 课程表类别
	$('#KCBLB').combobox({
		url : 'listAllKECHENGXXLB.action',
		valueField : 'KECHENGXXLB_ID',
		textField : 'KECHENGXXLBMC',
		onSelect : function() {
			var kcxxlb = $('#KCBLB').combobox("getText");// 课程信息类别
			if ("开放性课程" === kcxxlb||"公共选修课"===kcxxlb) {
				//将“上课班级”下拉列表框进行隐藏
				$("#skbj").hide();
				//将下拉列表框进行置空
				$("#SKBJ").combobox("setValue"," ");
			} else {
				//否则将“上课班级”下拉框进行显示
				$("#skbj").show();
			}
		}
	});

});
