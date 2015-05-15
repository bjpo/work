<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <title>列表</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <style type="text/css">
            .td {
                border: solid #99BBE8;
                border-width: 0px 1px 1px 0px;
            }
            .table {
                border: solid #99BBE8;
                border-width: 1px 0px 0px 1px;
            }
        </style>
        <jsp:include page="../common/include.jsp"></jsp:include>
        <script type="text/javascript" src="<%=basePath%>js/kechengb/kechengb.js"></script>
    </head>
    <body class="easyui-layout">
        <div data-options="region:'west',title:'菜单',split:true" style="width:15%;">
            <ul id="qxmenu" style="text-align:center">
                <span class="tree-icon tree-file "></span>
                <span class="tree-title">课程表管理</span>
            </ul>
        </div>
        <div data-options="region:'center'">
            <table id="datagrid" toolbar="#DIV_toolbar"></table>
            <div id="DIV_toolbar" style="padding:5px;" class="sskzt">
                <a type="button" onclick="addKECHENGB_dialog1()" plain="true" class="easyui-linkbutton" iconCls="icon-add">新增信息</a>
                <a onclick="showEnterDialog();" plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除信息</a>
                <table class="ssk">
                    <tr>
                        <td class="title"><span>老师工号:</span></td>
                        <td><input id="LAOSHIGH_serchbar" name="LAOSHIGH" /></td>
                        <td class="title"><span>老师姓名:</span></td>
                        <td><input id="LAOSHIMC_serchbar" name="LAOSHIMC" /></td>
                        <td class="title"><span>课程信息:</span></td>
                        <td><input id="KCXXMC_serchbar" name="KCXXMC" /></td>
                    </tr>
                    <tr>
                        <td class="title"><span>课程表类别:</span></td>
                        <td><input id="KCBLB_serchbar" name="KCBLB" /></td>
                        <td class="title"><span>教室名称:</span></td>
                        <td><input id="JSMC_serchbar" name="JSMC" /></td>
                        <td class="title"><span>课时名称:</span></td>
                        <td><input id="KSMC_serchbar" name="KSMC" /></td>
                    </tr>
                    <tr>
                        <td class="title"><span>星期:</span></td>
                        <td><input id="XINGQI_serchbar" name="XINGQI" /></td>
                        <td class="title"><span>星期序号:</span></td>
                        <td><input id="XINGQIXH_serchbar" name="XINGQIXH" /></td>
                        <td class="title"><span>开始周:</span></td>
                        <td><input id="KSZHOU_serchbar" name="KSZHOU" /></td>
                    </tr>
                    <tr>
                        <td class="title"><span>结束周:</span></td>
                        <td><input id="JSZHOU_serchbar" name="JSZHOU" /></td>
                        <td class="title"><span>课时开始时间:</span></td>
                        <td><input id="KS_KSSJ_serchbar" name="KS_KSSJ" /></td>
                        <td class="title"><span>课时结束时间:</span></td>
                        <td><input id="KS_JSSJ_serchbar" name="KS_JSSJ" /></td>
                    </tr>
                    <tr>
                        <td class="title"><span>描述:</span></td>
                        <td><input id="MS_serchbar" name="MS" /></td>
                        <td class="title"><span>备注:</span></td>
                        <td><input id="BZ_serchbar" name="BZ" /></td>
                    <tr>
                    <tr>
                        <td class="title" colspan=3;><button class="easyui-linkbutton" plain="true" onclick="doSearch();"></button></td>
                        <td><button class="easyui-linkbutton qx" plain="true" onclick="doReset()"></button></td>
                    <tr>
                </table>
            </div>
            <!--新版新增信息对话框 开始--->
            <input id="basePath" type="hidden" value="<%=basePath%>">
            <div id="addKECHENGB_dlg1" buttons="#addKECHENGB_dlg-buttons1" title="新增信息" class="easyui-dialog" style="width: 700px;height: 400px" closed="true" modal="true">
                <form id="addKECHENGB_form1" method="post">
                    <div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <label>排课学期:</label>
                            <input class="easyui-combobox easyui-validatebox" id="XUEQI" name="XUEQI"
                                   data-options="valueField:'XQ_ID',textField:'XQMC',url:'listAllXUEQI.action',required:true,missingMessage:'请选择排课学期',onSelect:function(){
                                   $.ajax({
                                   url: 'deterTerm.action?XUEQI='+$('#XUEQI').combobox('getText'),
                                   success: function(data){
                                   var obj2 = eval('(' + data + ')');
                                   if(!obj2.success){
                                   $.messager.alert('提示',obj2.message);
                                   $('#XUEQI').combobox('setValue','');
                                   }
                                   return ;
                                   }
                                   });
                                   }" style="width:70%;" />
                        </div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <input type="hidden" name="KCB_ID" ID="KCB_ID" />
                            <label>任课教师:</label>
                            <input class="easyui-combobox easyui-validatebox" id="LAOSHIGH" style="width:70%;" name="LAOSHIGH" data-options="valueField:'JG_ID',textField:'JGMC',required:true,missingMessage:'请选择任课教师',url:'<%=basePath%>listAllJIAOGONG.action'"/>
                        </div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <label>课程名称:</label>
                            <input class="easyui-combobox easyui-validatebox" id="KCXXMC" style="width:70%;" name="KCXXMC" data-options="valueField:'KECHENGXX_ID',textField:'KECHENGMC',required:true,missingMessage:'请选择课程名称',url:'<%=basePath%>listAllKECHENGXX.action'"/>
                        </div>
                    </div>
                    <div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <label>开始周:</label>
                            <input class="easyui-combobox easyui-validatebox" id="KSZHOU" style="width:70%;" name="KSZHOU" data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',required:true,missingMessage:'请选择课程开始周',url:'<%=basePath%>js/jiaoxuezhou.json'"/>
                        </div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <label>结束周:</label>
                            <input class="easyui-combobox easyui-validatebox" id="JSZHOU" style="width:70%;" name="JSZHOU" data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',required:true,missingMessage:'请选择课程结束周',url:'<%=basePath%>js/jiaoxuezhou.json'"/>
                        </div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <label>课程类别:</label>
                            <input class="easyui-combobox easyui-validatebox" id="KCBLB" style="width:70%;" name="KCBLB" data-options="valueField:'kcblb',textField:'kcblb',required:true,missingMessage:'请选择课程类别',url:'<%=basePath%>js/kechengbiaoleibie.json'"/>
                        </div>
                    </div>
                    <div>
                        <div style="float: left;width: 29%;text-align: right" class="title">
                            <label>课程人数:</label>
                            <input type="text" id="maxrs" name="maxrs" style="width:70%;"/>
                        </div>
                        <div id="skbj" style="float: left;width: 29%;text-align: right" class="title">
                            <label>上课班级:</label>
                            <input class="easyui-combobox" id="SKBJ" style="width:70%;" name="SKBJ" multiple="true" data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'" />
                        </div>
                    </div>
                    <div id="cleardiv" style="clear: both;border-style: none none none solid;border-width: 670px;border-color: #277ebc;"></div>
                    <div id="flagdiv">
                        <p style="padding-top: 20%">1:请依次选择排课学期,任课教师,课程名称,开始周,结束周,课程类别,上课班级,教室,星期,课时.上述选项为必选项.</p>
                        <p>2:课程人数为可选项.</p>
                        <p>3:当课程类别为公共选修课或开放性课程时,不必选择上课班级.</p>
                    </div>
                </form>
            </div>
            <div id="addKECHENGB_dlg-buttons1" style="height: 23px">
                <a onclick="zengjiapaike()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加排课</a>
                <a onclick="baocunpaike();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存排课</a>
                <a onclick="wanchengpaike();" class="easyui-linkbutton" iconCls="icon-ok">完成排课</a>
            </div>
            <div id="copydiv" style="display: none">
                <div class="copy">
                    <div style="float: left;width: 29%;text-align: right;" class="title">
                        <label>教室:</label>
                        <select style="width:70%;height: 22px" name="JS_ID">
                            <option>请选择</option>
                        </select>
                    </div>
                    <div style="float: left;width: 29%;text-align: right" class="title">
                        <label>星期:</label>
                        <select style="width:70%;height: 22px" name="XINGQI">
                            <option>请选择</option>
                        </select>
                    </div>
                    <div style="float: left;width: 29%;text-align: right" class="title">
                        <label>课时:</label>
                        <select style="width:70%;height: 22px" name="KS_ID">
                            <option>请选择</option>
                        </select>
                    </div>
                    <div style="float: left;width: 10%;text-align: left" class="title">
                        <a class="easyui-linkbutton" iconCls="icon-clear" style="height: 22px;" onclick="shanchupaike(this)">删除</a>
                    </div>
                </div>
            </div>
            <!--新版新增信息对话框 结束--->
            <!-- 查看/修改对话框  BEGIN-->
            <div id="updateKECHENGB_dlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px" closed="true" buttons="#update_KECHENGB_dlg-buttons" modal="true">
                <form id="updateKECHENGB_form" method="post">
                    <table>
                        <tr>
                            <td class="title"><label>任课教师:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_LAOSHIMC" name="LAOSHIMC" data-options="valueField:'JG_ID',textField:'JGMC',url:'<%=basePath%>listAllJIAOGONG.action'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>课程:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_KCXXMC" name="KCXXMC" data-options="valueField:'KECHENGXX_ID',textField:'KECHENGMC',url:'<%=basePath%>listAllKECHENGXX.action'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>课程表类别:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_KCBLB" name="KCBLB" data-options="valueField:'kcblb',textField:'kcblb',url:'<%=basePath%>js/kechengbiaoleibie.json'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>教室名称:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_JSMC" name="JS_ID" data-options="valueField:'JS_ID',textField:'FJMC',url:'<%=basePath%>listAllJIAOSHI.action'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>课时名称:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_KSMC" name="KSMC" data-options="valueField:'KS_ID',textField:'KSMC',url:'<%=basePath%>listAllKESHI.action'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>星期:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_XINGQI" name="XINGQI" data-options="valueField:'xingqi',textField:'xingqi',url:'<%=basePath%>js/xingqi.json'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>开始周:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_KSZHOU" name="KSZHOU" data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>结束周:</label></td>
                            <td>
                                <input class="easyui-combobox" id="update_JSZHOU" name="JSZHOU" data-options="valueField:'jiaoxuezhou',textField:'jiaoxuezhou',url:'<%=basePath%>js/jiaoxuezhou.json'" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>描述:</label></td>
                            <td>
                                <input type="text" id="update_MS" name="MS" />
                            </td>
                        </tr>
                        <tr>
                            <td class="title"><label>备注:</label></td>
                            <td>
                                <input type="text" id="update_BZ" name="BZ" />
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" id="update_KCB_FXS_ID" name="KCB_FXS_ID" />
                    <input type="hidden" id="update_KCB_ID" name="KCB_ID" />
                    <input type="hidden" id="update_optionflag" name="optionflag" />
                </form>
            </div>
            <div id="update_KECHENGB_dlg-buttons">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateKECHENGB();" iconCls="icon-ok">修改信息</a>
            </div>
            <!-- 查看/修改对话框  END-->
        </div>
    </body>
</html>