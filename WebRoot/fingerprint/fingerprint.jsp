<%--
    Document   : fingerprint
    Created on : 2015-4-2, 10:24:47
    Author     : Li
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>指纹录入列表</title>
        <jsp:include page="../common/include.jsp"></jsp:include>
        <link rel="stylesheet" type="text/css" href="fingerprint.css" />
        <script type="text/javascript" src="../js/fingerprint/fingerprint.js"></script>
    </head>
    <body class="easyui-layout">
        <div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
            <ul id="qxmenu" style="text-align:center">
                <span class="tree-icon tree-file "></span>
                <span class="tree-title">指纹录入列表</span>
            </ul>
        </div>
        <div data-options="region:'center',title:'指纹录入列表'">
            <!--查询-->
            <div style="padding:5px;border:1px solid #99bbe8;background:url(../img/ssk-bg.png);">
                <a class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="openFingerprintListDialog('save')">新增信息</a>
                <a class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="deleteFingerprintList()">删除信息</a>
                <table class="ssk">
                    <tr>
                        <td><span>指纹录入列表名称:</span></td>
                        <td><input id="fingerprintListName1" type="text"></td>
                        <td><button id="queryFingerprintListButton" type="button" onclick="fuzzyQueryFingerprintList()"></button></td>
                    </tr>
                </table>
            </div>
            <table id="fingerprintListTable"></table>
            <!--增加,修改对话框-->
            <div id="fingerprintListDialog" class="easyui-dialog" closed="true" modal="true" resizable="true" width="770px" height="400px" buttons="#closeFingerprintListDialogButton">
                <div style="margin-top:3%"></div>
                <div style="float: left;width: 36%;margin-left: 3%;margin-right: 3%">
                    <div>
                        指纹录入列表名称:
                        <input id="fingerprintListName2" type="text" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入指纹列表名称'" style=" border:1px solid #99bbe8; height:24px; line-height:24px;">
                        <input id="fingerprintListId" type="hidden" value="">
                    </div>
                    <div id="leftStudentList" style="border: 1px solid #99bbee;overflow: scroll;overflow-x: hidden;margin-top: 3%;height: 200px"></div>
                </div>
                <div style="float: left;width: 16%">
                    <button id="addStudentButton" type="button" style="width: 100%;height: 15%;margin-top: 25%">添加</button>
                    <br>
                    <button id="delStudentButton" type="button" style="width:100%;height: 15%">删除</button>
                    <br>
                    <button id="saveFingerprintListButton" type="button" style="width: 100%;height: 15%" onclick="saveOrUpdateFingerprintList('save')" hidden="hidden">保存</button>
                    <button id="updateFingerprintListButton" type="button" style="width: 100%;height: 15%" onclick="saveOrUpdateFingerprintList('update')" hidden="hidden">修改</button>
                    <button id="deleteFingerprintRegisterButton" type="button" style="width: 100%;height: 15%" onclick="deleteFingerprintRegister()" hidden="hidden">删除指纹</button>
                </div>
                <div style="float: left;width: 36%;margin-left: 3%;margin-right: 3%">
                    <div>
                        班级:<select id="squadList"></select>
                    </div>
                    <div id="rightStudentList" style="border: 1px solid #99bbee;overflow: scroll;overflow-x: hidden;margin-top: 3%;height: 200px"></div>
                </div>
            </div>
            <div id="closeFingerprintListDialogButton" style="height: 23px">
                <a class="easyui-linkbutton" onclick="closeFingerprintListDialog();" iconCls="icon-ok">完成</a>
            </div>
            <!--指纹录入对话框-->
            <div id="fingerprintRegisterDialog" class="easyui-dialog" closed="true" modal="true" resizable="true" style="width:770px;height:400px;padding:10px 20px" title="指纹录入" buttons="#closeFingerprintRegisterDialogButton">
                <div id="fingerprintRegisterTitle" style="font-size:24px;text-align: center"></div>
                <div style="float:left;">
                    <table>
                        <tr>
                            <td style="border:1px solid #99bbee; border-right:none; text-align:right">学生学号:</td>
                            <td style="border:1px solid #99bbee; border-right:none; border-left:none; width:130px;"><font id="currentStudentNumber" color="red">无</font></td>
                            <td style="border:1px solid #99bbee; border-right:none;  border-left:none; text-align:right;">学生姓名:</td>
                            <td style="border:1px solid #99bbee; border-left:none; width:130px;"><font id="currentStudentName" color="red">无</font></td>
                        </tr>
                        <tr>
                            <td style="border:1px solid #99bbee; border-right:none;">下一位学生学号:</td>
                            <td style="border:1px solid #99bbee; border-right:none; border-left:none;"><font id="nextStudentNumber" color="red">无</font></td>
                            <td style="border:1px solid #99bbee; border-right:none;  border-left:none;">下一位学生姓名:</td>
                            <td style="border:1px solid #99bbee; border-left:none;"><font id="nextStudentName" color="red">无</font></td>
                        </tr>
                    </table>
                    <div style="margin-top:10px;margin-left:100px">
                        <label>指纹表ID1:</label>
                        <input id="fingerprintId1" class="easyui-validatebox" type="text" data-options="required:true" style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
                        <a class="easyui-linkbutton" plain="true"  onclick="openFingerprintModuleDialog('fingerprintId1')"><b>录入指纹1</b></a>
                    </div>
                    <div style="margin-top:10px;margin-left:100px">
                        <label>指纹表ID2:</label>
                        <input id="fingerprintId2" class="easyui-validatebox" type="text" data-options="required:true" style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
                        <a class="easyui-linkbutton" plain="true" onclick="openFingerprintModuleDialog('fingerprintId2')"><b>录入指纹2</b></a>
                    </div>
                    <input id="fingerprintListId2" type="hidden">
                </div>
                <div style="float:right;">
                    <span>已采集人数/总人数:</span><span id="registeredAmount"></span>/<span id="amount"></span>
                </div>
                <div id="unregisteredStudentList" class="wbk"></div>
                <div style="margin-left:50px; margin-top:10px; float:left;">
                    <input id="nextStudentButton" type="button" value="下一位采集者" disabled="disabled"/>
                    <input id="saveFingerprintRegisterButton" type="button" value="保存指纹信息" onclick="saveFingerprintRegister()" disabled="disabled"/>
                </div>
            </div>
            <div id="closeFingerprintRegisterDialogButton" style="height: 23px">
                <a class="easyui-linkbutton" onclick="closeFingerprintRegisterDialog();" iconCls="icon-ok">采集完毕</a>
            </div>
            <!--单人指纹录入对话框-->
            <div id="singleFingerprintRegisterDialog" class="easyui-dialog" closed="true" modal="true" resizable="true" title="指纹录入" style="width:530px;height:300px;" buttons="#closeSingleFingerprintRegisterDialogButton">
                <div style="float:left;">
                    <div style="margin-top:10px;margin-left:100px">
                        <label>指纹表ID1:</label>
                        <input id="singleFingerprintId1" class="easyui-validatebox" type="text" data-options="required:true" style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
                        <a class="easyui-linkbutton" plain="true" onclick="openFingerprintModuleDialog('singleFingerprintId1')"><b>录入指纹1</b></a>
                    </div>
                    <div style="margin-top:10px;margin-left:100px">
                        <label>指纹表ID2:</label>
                        <input id="singleFingerprintId2" class="easyui-validatebox" type="text" data-options="required:true" style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
                        <a class="easyui-linkbutton" plain="true" onclick="openFingerprintModuleDialog('singleFingerprintId2')"><b>录入指纹2</b></a>
                    </div>
                    <input id="singleStudentNumber" type="hidden">
                </div>
                <div class="clear"></div>
                <input type="button" value="保存指纹信息" onclick="saveSingleFingerprintRegister()" style="margin-left:200px;" />
            </div>
            <div id="closeSingleFingerprintRegisterDialogButton" style="height: 23px">
                <a class="easyui-linkbutton" onclick="closeSingleFingerprintRegisterDialog();" iconCls="icon-ok">采集完毕</a>
            </div>
            <!--指纹组件对话框-->
            <div id="fingerprintModuleDialog" class="easyui-dialog" closed="true" modal="true" resizable="true" title="指纹录入" style="width:530px;height:300px;padding:10px 20px">
                <table style="font-family: 宋体; font-size: 9pt" method="post" enctype="multipart/form-data">
                    <tr>
                        <td>
                            <object classid="clsid:059059BE-8F4C-49AC-B2A9-5649F02A4FC6" id="FPEngineEx1" data="DATA:application/x-oleobject;BASE64,汶六啂偹䕲祭噱䩚䌸偰杸䩁䅁奄睅䅁䈲䅍䅁㴽" style="height: 200px; width: 150px;"> </object>
                        </td>
                        <td>
                            <input type="text" id="es" data-options="required:true" style=" border:1px solid #99bbe8; height:24px; line-height:24px;" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="button" value="登记参考模板" onclick="EnrollRefTemplate();">
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input align="right" type="button" value="确定" onclick="closeFingerprintModuleDialog();"></td>
                    </tr>
                </table>
            </div>
            <!--指纹组件下载DIV-->
            <div id="downLoad" style="display: none">
                <span>未检测到指纹录入所需要的插件请进行下载</span>
                <a id="gb" onclick="closeDownLoad();"><img src="../img/close.png" /></a>
                <a class="button" href="../download.action"></a>
            </div>
        </div>
    </body>
</html>