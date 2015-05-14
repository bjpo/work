$(function ()
{
    //查询指纹录入列表
    $("#fingerprintListTable").datagrid({
        onLoadError: function () {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
        },
        loadingMessage: "处理中",
        url: "../fingerprint_queryFingerprintList.action",
        pagination: true,
        pageSize: 10,
        pageNumber: 1,
        pageList: [10, 20, 30, 40, 50, 60, 70, 80],
        fitColumns: false,
        nowrap: true,
        border: false,
        idField: 'ZWLR_ID',
        columns: [[
                {title: '<b>列表名称</b>', field: 'LBMC', sortable: true},
                {title: '<b>指纹录入情况</b>', field: 'SFLR', sortable: true, hidden: true},
                {title: '<b>指纹录入时间</b>', field: 'ZWLRSJ', sortable: true, fitColumns: true, hidden: true},
                {title: '<b>列表创建时间</b>', field: 'LBCJSJ', sortable: true, fitColumns: true},
                {title: '<b>列表最后修改时间</b>', field: 'LASTMODIFYTIME', sortable: true, fitColumns: true},
                {title: '<b>操作</b>', field: 'ZWLR_ID', fitColumns: true, formatter: function (value, rowData, rowIndex) {
                        return '<a style="cursor:hand;" onclick="openFingerprintListDialog(\'update\')"><img src="../js/jquery-easyui/themes/icons/pencil.png" alt="修改" style="border:0px;">修改</a>'
                                + '|' + '<a style="cursor:hand;" onclick="openFingerprintRegisterDialog(\'' + value + '\')"><img src="../js/jquery-easyui/themes/icons/pencil.png" alt="录入指纹信息" style="border:0px;"/>指纹录入</a>';
                    }
                }
            ]],
        frozenColumns: [[{field: 'ck', checkbox: true}]],
        singleSelect: false,
        rownumbers: true
    });
    //查询班级
    $("#squadList").combobox({
        onLoadError: function () {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
            $("#fingerprintListDialog").dialog("close");
        },
        loadingMessage: "处理中",
        required: true,
        missingMessage: "请选择班级",
        url: "../fingerprint_querySquad.action",
        width: "70%",
        height: "24px",
        valueField: "BJ_ID",
        textField: "BJMC",
        editable: false,
        onChange: function (newValue, oldValue)
        {
            $.ajax({
                type: "POST",
                url: "../fingerprint_queryStudent.action",
                data: {"squad.BJ_ID": newValue},
                success: function (data)
                {
                    if (data.length > 0)
                    {
                        $("#rightStudentList div").remove();
                        $.each(data, function (i, n)
                        {
                            $("#rightStudentList").append("<div style='margin-top:'5px'><input type='checkbox'><span>" + n.zsxm + "</span>|<span>" + n.xh + "</span></div>");
                        });
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown)
                {
                    $("#rightStudentList div").remove();
                    $.messager.alert("失败", "系统繁忙,请稍后再试");
                }
            });
        }
    });
    //增加学生按钮点击事件
    $("#addStudentButton").click(function ()
    {
        var isDuplicated = false;
        var studentNumbers = new Array();
        $("#rightStudentList input:checked ~ span:last-child").each(function (i, n)
        {
            if ($("#leftStudentList:contains(" + $(n).text() + ")").length !== 0)
            {
                isDuplicated = true;
                studentNumbers.push($(n).text());
            }
        });
        if (isDuplicated)
        {
            $.messager.alert("操作失败", "学号为" + studentNumbers + "的学生已经添加过了");
        } else
        {
            $("#leftStudentList").append($("#rightStudentList div:has(input:checked)").clone());
        }
    });
    //删除学生按钮点击事件
    $("#delStudentButton").click(function ()
    {
        if ($("#leftStudentList div:has(input:checked)").length > 0)
        {
            $("#leftStudentList div:has(input:checked)").remove();
        }
    });
    //下一位学生按钮点击事件
    $("#nextStudentButton").click(function ()
    {
        var stuedntNumber = $("#nextStudentNumber").text();
        var studentName = $("#nextStudentName").text();
        $("#currentStudentNumber").text(stuedntNumber);
        $("#currentStudentName").text(studentName);
        var nextStuedntNumber = $("#unregisteredStudentList div:contains(" + stuedntNumber + ") ~ div:eq(1)").text();
        var nextStuedntName = $("#unregisteredStudentList div:contains(" + stuedntNumber + ") ~ div:eq(0)").text();
        if (nextStuedntNumber.length > 0)
        {
            $("#nextStudentNumber").text(nextStuedntNumber);
            $("#nextStudentName").text(nextStuedntName);
        } else
        {
            $("#nextStudentNumber").text("无");
            $("#nextStudentName").text("无");
            $("#nextStudentButton").attr("disabled", "disabled");
        }
    });
});
//模糊查询指纹录入列表
function fuzzyQueryFingerprintList()
{
    $('#fingerprintListTable').datagrid('load', {
        "fingerprintList.LBMC": $('#fingerprintListName1').val()
    });
}
//打开增加,修改指纹录入列表对话框
function openFingerprintListDialog(operationType)
{
    $("#fingerprintListDialog").dialog({
        loadingMessage: "处理中",
        onLoadError: function () {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
            $("#fingerprintListDialog").dialog("close");
        },
        onBeforeOpen: function () {
            if (operationType === 'save')
            {
                $("#fingerprintListDialog").dialog({title: "新增信息"});
                $("#saveFingerprintListButton").show();
            }
            if (operationType === 'update')
            {
                $("#fingerprintListDialog").dialog({title: "修改信息"});
                $("#updateFingerprintListButton").show();
                $("#deleteFingerprintRegisterButton").show();
                var row = $('#fingerprintListTable').datagrid('getSelected');
                $("#fingerprintListName2").val(row.LBMC);
                $("#fingerprintListId").val(row.ZWLR_ID);
                $.ajax({
                    type: "POST",
                    url: "../fingerprint_queryFingerprintRoster.action",
                    data: {"fingerprintRoster.ZWLRMD_ID": row.ZWLR_ID},
                    success: function (data)
                    {
                        if (data.length > 0)
                        {
                            $.each(data, function (i, n)
                            {
                                $("#leftStudentList").append("<div><input type='checkbox'><span>" + n.ZSXM + "</span>|<span>" + n.XH + "</span></div>");
                            });
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown)
                    {
                        $.messager.alert("失败", "系统繁忙,请稍后再试");
                        $("#fingerprintListDialog").dialog("close");
                    }
                });
            }
        },
        onBeforeClose: function () {
            $("#squadList").combobox("clear");
            $("#fingerprintListName2").val("");
            $("#fingerprintListId").val("");
            $("#leftStudentList div").remove();
            $("#rightStudentList div").remove();
            $("#saveFingerprintListButton").hide();
            $("#updateFingerprintListButton").hide();
            $("#deleteFingerprintRegisterButton").hide();
        }
    });
    $("#fingerprintListDialog").dialog("open");
}
//关闭增加,修改指纹录入列表对话框
function closeFingerprintListDialog()
{
    $("#fingerprintListDialog").dialog("close");
}
//增加,修改指纹录入列表
function saveOrUpdateFingerprintList(operationType)
{
    if ($("#fingerprintListName2").validatebox("isValid") && $("#leftStudentList div:has(input)").length > 0)
    {
        var studentNumbers = new Array();
        $("#leftStudentList input ~ span:last-child").each(function (i, n)
        {
            studentNumbers.push($(n).text());
        });
        var reqParams;
        if (operationType === 'save')
        {
            reqParams = {"fingerprintList.LBMC": $("#fingerprintListName2").val(), "studentNumbers": studentNumbers, "operationType": operationType};
        }
        if (operationType === 'update')
        {
            reqParams = {"fingerprintList.LBMC": $("#fingerprintListName2").val(), "fingerprintList.ZWLR_ID": $("#fingerprintListId").val(), "studentNumbers": studentNumbers, "operationType": operationType};
        }
        $.ajax({
            type: "POST",
            url: "fingerprint_saveOrUpdateFingerprintList.action",
            data: reqParams,
            traditional: true,
            success: function (data)
            {
                if (data.success === false)
                {
                    $.messager.alert("失败", data.message);
                } else
                {
                    $("#fingerprintListDialog").dialog("close");
                    fuzzyQueryFingerprintList();
                    $.messager.alert("成功", data.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown)
            {
                $.messager.alert("失败", "系统繁忙,请稍后再试");
            }
        });
    } else
    {
        $.messager.alert("失败", "请输入标题并添加学生");
    }
}
//删除指纹按钮点击事件
function deleteFingerprintRegister()
{
    if ($("#leftStudentList div:has(input:checked)").length > 0)
    {
        $.messager.confirm("删除指纹", "确认删除指纹信息?", function (r)
        {
            if (r)
            {
                var studentNumbers = new Array();
                $("#leftStudentList input:checked ~ span:last-child").each(function (i, n)
                {
                    studentNumbers.push($(n).text());
                });
                $.ajax({
                    type: "POST",
                    url: "../fingerprint_deleteFingerprintRegister.action",
                    data: {"studentNumbers": studentNumbers},
                    traditional: true,
                    success: function (data)
                    {
                        $.messager.alert("成功", "操作成功");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown)
                    {
                        $.messager.alert("失败", "系统繁忙,请稍后再试");
                    }
                });
            }
        });
    }
}
//删除指纹录入列表
function deleteFingerprintList()
{
    var row = $('#fingerprintListTable').datagrid('getSelections');
    if (row.length !== 0)
    {
        $.messager.confirm("删除信息", " 确认要删除选中的信息吗？", function (r)
        {
            if (r)
            {
                var fingerprintListIds = new Array();
                $.each(row, function (i, n)
                {
                    fingerprintListIds.push(n.ZWLR_ID);
                });
                $.ajax({
                    type: "POST",
                    url: "../fingerprint_deleteFingerprintList.action",
                    data: {"fingerprintListIds": fingerprintListIds},
                    traditional: true,
                    success: function (data)
                    {
                        $.messager.alert("成功", "操作成功");
                        fuzzyQueryFingerprintList();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown)
                    {
                        $.messager.alert("失败", "系统繁忙,请稍后再试");
                    }
                });
            }
        });
    } else
    {
        $.messager.alert('失败', '请选择要删除的记录');
    }
}
//打开指纹录入对话框
function openFingerprintRegisterDialog(fingerprintListId)
{
    $("#fingerprintRegisterDialog").dialog({
        loadingMessage: "处理中",
        onLoadError: function () {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
            $("#fingerprintRegisterDialog").dialog("close");
        },
        onBeforeOpen: function () {
            var row = $('#fingerprintListTable').datagrid('getSelected');
            $("#fingerprintRegisterTitle").text(row.LBMC);
            $("#fingerprintListId2").val(fingerprintListId);
            setFingerprintRegisterDialog();
        },
        onBeforeClose: function () {
            $("#fingerprintRegisterTitle").text("");
            restoreFingerprintRegisterDialog();
        }
    });
    $("#fingerprintRegisterDialog").dialog("open");
}
//关闭指纹录入对话框
function closeFingerprintRegisterDialog()
{
    $("#fingerprintRegisterDialog").dialog("close");
}
//根据选中的指纹录入列表,设置指纹录入对话框的值
function setFingerprintRegisterDialog()
{
    $.ajax({
        type: "POST",
        url: "../fingerprint_queryUnregisteredStudent.action",
        data: {"fingerprintList.ZWLR_ID": $("#fingerprintListId2").val()},
        success: function (data)
        {
            $("#amount").text(data.amount);
            $("#registeredAmount").text(data.registeredAmount);
            $.each(data.unregisteredStudent, function (i, n)
            {
                if (i === 0)
                {
                    $("#currentStudentNumber").text(n.xh);
                    $("#currentStudentName").text(n.zsxm);
                    $("#saveFingerprintRegisterButton").attr("disabled", false);
                }
                if (i === 1)
                {
                    $("#nextStudentNumber").text(n.xh);
                    $("#nextStudentName").text(n.zsxm);
                    $("#nextStudentButton").attr("disabled", false);
                }
                $("#unregisteredStudentList").append("<div name='studentName' style='width:60px; float:left; margin-top:5px;'>"
                        + n.zsxm + "</div><div name='studentNumber' style='float:left;'>"
                        + n.xh + "<input type='button' onclick='openSingleFingerprintRegisterDialog(" + n.xh + ")' value='采集' style='margin-left:5px;'></div>");
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown)
        {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
            $("#fingerprintRegisterDialog").dialog("close");
        }
    });
}
//还原指纹录入对话框默认值
function restoreFingerprintRegisterDialog()
{
    $("#amount").text("");
    $("#registeredAmount").text("");
    $("#currentStudentNumber").text("无");
    $("#currentStudentName").text("无");
    $("#nextStudentNumber").text("无");
    $("#nextStudentName").text("无");
    $("#unregisteredStudentList > div").remove();
    $("#nextStudentButton").attr("disabled", "disabled");
    $("#saveFingerprintRegisterButton").attr("disabled", "disabled");
    $("#fingerprintId1").val("");
    $("#fingerprintId2").val("");
}
//保存录入的指纹
function saveFingerprintRegister()
{
    if ($("#fingerprintId1").validatebox("isValid") && $("#fingerprintId2").validatebox("isValid"))
    {
        $.messager.confirm("保存指纹", "确认提交指纹信息？", function (r)
        {
            if (r)
            {
                sendRequest($("#currentStudentNumber").text(), $("#fingerprintId1").val(), $("#fingerprintId2").val());
            }
        });
    } else
    {
        $.messager.alert("警告", "请录入两个指纹");
    }
}
//发送保存录入指纹的请求,重新设置指纹录入对话框的值
function sendRequest(studentNumber, fingerprintId1Value, fingerprintId2Value)
{
    $.ajax({
        type: "POST",
        url: "../fingerprint_saveFingerprintRegister.action",
        data: {"studentInfo.xh": studentNumber, "fingerprintId1": fingerprintId1Value, "fingerprintId2": fingerprintId2Value},
        success: function (data)
        {
            $.messager.alert("成功", "操作成功");
            restoreFingerprintRegisterDialog();
            setFingerprintRegisterDialog();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown)
        {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
        }
    });
}
//打开单人指纹录入对话框
function openSingleFingerprintRegisterDialog(studentNumber)
{
    $("#singleFingerprintRegisterDialog").dialog({
        loadingMessage: "处理中",
        onLoadError: function () {
            $.messager.alert("失败", "系统繁忙,请稍后再试");
            $("#singleFingerprintRegisterDialog").dialog("close");
        },
        onBeforeOpen: function () {
            $("#singleStudentNumber").val(studentNumber);
        },
        onBeforeClose: function () {
            $("#singleFingerprintId1").val("");
            $("#singleFingerprintId2").val("");
        }
    });
    $("#singleFingerprintRegisterDialog").dialog("open");
}
//关闭单人指纹录入对话框
function closeSingleFingerprintRegisterDialog()
{
    $("#singleFingerprintRegisterDialog").dialog("close");
}
//保存单人录入的指纹
function saveSingleFingerprintRegister()
{
    if ($("#singleFingerprintId1").validatebox("isValid") && $("#singleFingerprintId2").validatebox("isValid"))
    {
        $.messager.confirm("保存指纹", "确认提交指纹信息？", function (r)
        {
            if (r)
            {
                sendRequest($("#singleStudentNumber").val(), $("#singleFingerprintId1").val(), $("#singleFingerprintId2").val());
                $("#singleFingerprintRegisterDialog").dialog("close");
                $("#singleFingerprintId1").val("");
                $("#singleFingerprintId2").val("");
            }
        });
    } else
    {
        $.messager.alert("警告", "请录入两个指纹");
    }
}
//打开指纹组件对话框
var FingerprintId;
function openFingerprintModuleDialog(fingerprintId)
{
    FingerprintId = fingerprintId;
    $("#fingerprintModuleDialog").dialog("open");
}
//关闭指纹组件对话框
function closeFingerprintModuleDialog() {
    FPEngineEx1.CloseDevice();
    $('#es').val("");
    $('#fingerprintModuleDialog').dialog('close');
}
//指纹组件
function EnrollRefTemplate() {
    try {
        if (FPEngineEx1.OpenDevice(0, 0, 0) == 1) {
            if (FPEngineEx1.LinkDevice() == 1) {
                var ens = document.getElementById("es");
                FPEngineEx1.EnrFptEx();
                ens.value = "就绪";
                timer = setTimeout("Transaction()", 500);
            } else {
                alert("连接USB指纹仪失败");
            }
        } else
            alert("打开USB指纹仪失败");
    } catch (ex) {
        $(function () {
            $("#downLoad").slideToggle(500);
        });
    }
}
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
            var en1 = document.getElementById(FingerprintId);
            en1.value = refdata;
            clearTimeout(timer);
            return;
    }
    timer = setTimeout("Transaction()", 500);
}
//指纹组件下载
function closeDownLoad() {
    $("#downLoad").css({
        display: "none"
    });
}
//指纹组件下载
function downLoad() {
    $('#downLoad').form('submit', {
        url: "download.action"
    });
}