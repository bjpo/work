//模糊查询

function doSearch() {
    $('#datagrid').datagrid('load', {
        JSMC: $('#JSMC_serchbar').val(),
        SBMC: $('#SBMC_serchbar').val(),
        optionflag: 'selbylike'
    });
}
// 打开新增信息对话框
var addJsId = null;

function addSBXX_dialog() {
    $('#addSBXX_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口
  
    $('#JSMC').combobox({
        onSelect: function (rec) {
            addJsId = $(this).combobox('getValue');
        }
    });

    $.parser.parse('#addSBXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}
// 新增信息

function addSBXX() {
    $('#addSBXX_form').form('submit', {
//    	addJsName=' + encodeURI(encodeURI($('#JSMC').combobox('getText'))) + "&
        url: "addSBXX.action?addJsId=" + addJsId+"&addJsName=" + encodeURI(encodeURI($('#JSMC').combobox('getText'))),
        onSubmit: function () {
            // 设备名称匹配规则
            var sbNameReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
            var sbid_reg=new RegExp("^\\w{1,4}$");
           
            if (sbid_reg.test($("#SBXX_ID").val())) {
            	//判断设备ID是否符合验证规则
                if (sbNameReg.test($('#SBMC').val())) {
                    // 判断教室名称下拉列表是否为空
                    if ($('#JSMC').combobox('getText') != "") {
                        return true;
                    } else {
                        $.messager.alert('提示', '请选择教室名称');
                        return false;
                    }
                } else {
                    $.messager.alert('提示', '请输入设备名称');
                    return false;
                }
			}else{
				$.messager.alert('提示','请输入正确设备ID,1到4位数字或字母');
				return false;
			}
        

        },
        success: function (data) {
        	 var obj2 = eval("(" + data + ")");
            if (obj2.success ==false) {
                $.messager.alert('提示', obj2.message);
                $("SBXX_ID").val("");
                return ;
            } else {
                $.messager.alert('提示', obj2.message);
                $('#addSBXX_form').form('clear');
                $('#addSBXX_dlg').dialog('close');
                $('#datagrid').datagrid('clearSelections');
                $('#datagrid').datagrid('reload');
            }
            
        }
    });
}
// 打开修改对话框
var updateJsMc = null;

function updateSBXX_dialog() {
	 
    var row = $('#datagrid').datagrid('getSelected');
    
    $('#update_JSMC').combobox({
        url: 'listAllJIAOSHI.action',
        valueField: 'JS_ID',
        textField: 'JSMC',
        onSelect: function (rec) {
            updateJsMc = $(this).combobox('getText');
        }
    });

    if (typeof(row) == "undefined") {
        return;
    }
    if (!row && typeof(row) != "undefined" && row != 0) {
        return;
    }
    
    if (row.ID) {
        $.ajax({
            url: "updateSBXX.action?ID=" + row.ID,
            context: document.body,
            success: function (data) {
                var sbobj = eval("(" + data + ")");
                $('#updateSBXX_dlg').dialog('open').dialog('setTitle', '查看/修改信息'); // 弹出窗口
                $.parser.parse('#updateSBXX_dlg'); // 需要重新渲染对话框，否则easyui不起作用
                $('#update_JSMC').combobox('setValue', sbobj.JSMC);
                $('#update_BZ').val(sbobj.BZ);
                $('#update_JS_ID').val(sbobj.JS_ID);
                $('#update_MS').val(sbobj.MS);
                $('#update_SBMC').val(sbobj.SBMC);
                $('#update_SBXX_ID').val(sbobj.SBXX_ID);
                $('#update_ID').val(sbobj.ID);
                $('#update_optionflag').val('updateSBXX');
            }
        });
    }
}

/**
 * 修改信息
 */
function updateSBXX() {
    $('#updateSBXX_form').form('submit', {
//    	updateJsMc=' + encodeURI(encodeURI(updateJsMc))
        url: "updateSBXX.action",
        onSubmit: function () {
            // 设备名称匹配规则
            var sbNameReg = new RegExp("^[\u4E00-\u9FA5A-Za-z0-9_()]+$");
            var sbid_reg=new RegExp("^\\d{1,4}$");
            if (sbid_reg.test($("#update_SBXX_ID").val())) {
            	//判断设备ID是否符合验证规则
                if (sbNameReg.test($('#update_SBMC').val())) {
                    // 判断教室名称下拉列表是否为空
                    if ($('#update_JSMC').combobox('getText') != "") {
                        return true;
                    } else {
                        $.messager.alert('提示', '请选择教室名称');
                        return false;
                    }
                } else {
                    $.messager.alert('提示', '请输入设备名称');
                    return false;
                }
			}else{
				$.messager.alert('提示','请输入正确设备ID的格式！正确的格式：1-9999');
				return false;
			}
        },
        success: function (data) {
        	 var obj2 = eval("(" + data + ")");
            if (obj2.success==false) {
                $.messager.alert('提示', obj2.message);
                return ;
            } else {
                $.messager.alert('提示', obj2.message);
                $('#updateSBXX_form').form('clear');
                $('#updateSBXX_dlg').dialog('close');
                $('#datagrid').datagrid('clearSelections');
                $('#datagrid').datagrid('reload');
                $('#update_JSMC').val("");
                $('#update_BZ').val("");
                $('#update_MS').val("");
                $('#update_SBMC').val("");
                $('#update_optionflag').val("");
            }
        }
    });
   
 
}


/**
 * 删除信息方法 弹出删除对话框
 */
function showEnterDialog() {
    $.messager.confirm("删除信息", " 确认要删除选中的信息吗？", function (r) {
        if (r) {
            var NowTime = new Date().toLocaleTimeString();
            var row = $('#datagrid').datagrid('getSelections');
            if (row < 1) {
                $.messager.alert('提示', '请选择要删除的记录');
                return;
            }
            var tmpyhzid = "";
            for (var tmpi = 0; tmpi < row.length; tmpi++) {
                tmpyhzid = tmpyhzid + row[tmpi].ID + ",";
            }
            $.ajax({
                url: "delSBXX.action?nowtime=" + NowTime + "&ID=" + tmpyhzid,
                context: document.body,
                success: function (data) {
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

/**
 * 初始化方法
 */
$(function () {
    $('#datagrid').datagrid({
        url: 'listSBXX.action',
        title: '设备管理列表',
        iconCls: 'icon-cls',
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50, 60, 70, 80],
        fitColumns: true,
        nowrap: false,
        border: false,
         idField : 'ID',
        columns: [
            [{
                title: '<b>设备ID</b>',
                field: 'SBXX_ID',
                sortable: true
            },
            {
                title: '<b>设备名称</b>',
                field: 'SBMC',
                sortable: true
            },
            {
                title: '<b>教室名称</b>',
                field: 'JSMC',
                sortable: true
            },
            {
                title: '<b>备注</b>',
                field: 'BZ',
                sortable: true
            },
            {
                title: '<b>描述</b>',
                field: 'MS',
                sortable: true
            },
            {
                title: '<b>操作</b>',
                field: 'ID',
                formatter: function (value, rowData, rowIndex) {
                    return "<a style='cursor:hand;' onclick='updateSBXX_dialog();'><img	src='js/jquery-easyui/themes/icons/pencil.png' alt='修改' style='border:0px;'/></a>";
                }
            }]
        ],
        singleSelect: false,
        //是否单选
        pagination: true,
        // 分页控件
        rownumbers: true,
        // 行号
        frozenColumns: [
            [{
                field: 'ck',
                checkbox: true
            }]
        ]
    });
    $('#datagrid').datagrid('getPager').pagination({
        pageSize: 10,
        // 每页显示的记录条数，默认为10
        pageList: [10, 20, 30, 40, 50, 60, 70, 80],
        // 可以设置每页记录条数的列表
        beforePageText: '第',
        // 页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
});