<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>校长前台</title>
    <jsp:include page="../common/include.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/xzqt/index.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/xz/index.css" />
    <link rel="stylesheet" type="text/css" href="../css/xz/public.css" />
    <script type="text/javascript" src="<%=basePath%>js/echarts/echarts-all.js"></script>
    <style type="text/css">
        .combo-panel {
            background:url(../img/xz/cx-fxk.png) ;
            border:none;
        }
        .textbox{
            background:none;
        }
        .textbox-text{
            background:none;
        }
    </style>
</head>
<body>
    <div class="main l-f">
        <!--header-->
        <div class="header">
            <div class="portrait l-f"></div>
            <div class="use l-f">
                <span>当前用户：</span>
                <span id="user"><s:property value="#session.yonghu.yhm" /></span>
                <span>>></span>
            </div>
            <div class="tool l-f">
                <a id="personal" href="user.jsp">个人中心</a> <a href="logout.action">退出登录</a>
            </div>
            <a class="qh l-r" href="../index.jsp"></a><!--切换至专业版-->
            <div class="xxts l-r">
                <a id="message">信息（<span id="messageNum"></span>）</a>
            </div>
        </div>
        <div class="clear"></div>
        <!--查询下拉框-->
        <div class="cxk">
            <span class="l-f" style="margin-top:8px">我的查询：</span>
            <div class="ssk l-f">
                <input id="cxtj" class="easyui-combobox"/>
            </div>
            <button class="cx-btn l-f" onclick="query()"></button>
            <div class="clear"></div>
        </div>
        <!--学院统计-->
        <div class="tj l-f">
            <!-- 学院统计header-->
            <div class="title l-f"><!-- 学院统计标题,学院统计说明-->
                <div class="tj-menu"><!-- 学院统计下拉框-->
                    <div class="content">
                        <ul id="xy"></ul><!-- 学院统计下拉框内容-->
                        <div class="clear"></div>
                        <span class="red">最多可选三个学院</span>
                        <div class="tj-btn l-r"></div><!--学院统计按钮-->
                    </div>
                </div>
            </div>
            <!-- 学院统计结果1-->
            <div class="content" id="content_0">
                <div class="tjbt l-f">
                    <span id="tj_1"></span><!-- 标题 -->
                </div>
                <div class="l-f" id="data_0" style="width:350px">
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #277ebc;" id="cxBar_0"></div><!-- 出席 -->
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #091a6d;" id="cdBar_0"></div><!-- 迟到 -->
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #e7908e;" id="ztBar_0"></div><!-- 早退 -->
                    <div class="l-f easyui-tooltip" style=" width:0px ; height: 35px; background: #830300;" id="qxBar_0"></div><!-- 缺席 -->
                    <div class="clear"></div>
                    <div class="cxqk l-f">
                        <span>出席:<span id="cx_0"></span>%  迟到:<span id="cd_0"></span>%  早退:<span id="zt_0"></span>%  缺席:<span id="qx_0"></span>%</span>
                    </div>
                </div>
                <div id="ycnr_0" class="l-f" style="width:350px; display:none;">
                    <div class="l-f" style="width:330px ; height: 35px; background: url(../img/xz/deloy-bg.png)"></div>
                    <div class="cxqk l-f">
                        <span>出席:无  迟到:无  早退:无  缺席:无</span>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <!-- 学院统计结果2-->
            <div class="content" id="content_1">
                <div class="tjbt l-f">
                    <span id="tj_2"></span><!-- 标题 -->
                </div>
                <div  id="data_1" class="l-f" style="width:350px">
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #277ebc;" id="cxBar_1"></div><!-- 出席 -->
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #091a6d;" id="cdBar_1"></div><!-- 迟到 -->
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #e7908e;" id="ztBar_1"></div><!-- 早退 -->
                    <div class="l-f easyui-tooltip" style=" width:0px ; height: 35px; background: #830300;" id="qxBar_1"></div><!-- 缺席 -->
                    <div class="clear"></div>
                    <div class="cxqk l-f">
                        <span>出席:<span id="cx_1"></span>%  迟到:<span id="cd_1"></span>%  早退:<span id="zt_1"></span>%  缺席<span id="qx_1"></span>%</span>
                    </div>
                </div>
                <div  id="ycnr_1" class="l-f" style="width:350px; display:none;">
                    <div class="l-f" style="width:330px ; height: 35px; background: url(../img/xz/deloy-bg.png)"></div>
                    <div class="cxqk l-f">
                        <span>出席:无  迟到:无  早退:无  缺席:无</span>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <!-- 学院统计结果3-->
            <div class="content" id="content_2">
                <div class="tjbt l-f">
                    <span id="tj_3"></span><!-- 标题 -->
                </div>
                <div class="l-f" id="data_2" class="l-f" style="width:350px">
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #277ebc;" id="cxBar_2"></div><!-- 出席 -->
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #091a6d;" id="cdBar_2"></div><!-- 迟到 -->
                    <div class="l-f easyui-tooltip" style="width:0px ; height: 35px; background: #e7908e;" id="ztBar_2" ></div><!-- 早退 -->
                    <div class="l-f easyui-tooltip" style=" width:0px ; height: 35px; background: #830300;" id="qxBar_2"></div><!-- 缺席 -->
                    <div class="clear"></div>
                    <div class="cxqk l-f">
                        <span>出席:<span id="cx_2"></span>%  迟到:<span id="cd_2"></span>%  早退:<span id="zt_2"></span>%  缺席:<span id="qx_2"></span>%</span>
                    </div>
                </div>
                <div id="ycnr_2" class="l-f" style="width:350px; display:none;">
                    <div class="l-f" style="width:330px ; height: 35px; background: url(../img/xz/deloy-bg.png);"></div>
                    <div class="cxqk l-f">
                        <span>出席:无  迟到:无  早退:无  缺席:无</span>
                    </div>
                </div>
            </div>
        </div>
        <!--分割线-->
        <div class="fgx l-f"></div>
        <!--饼图统计-->
        <div id="main" class="l-r"></div>
        <div class="clear"></div>
        <!--全校统计-->
        <div class="footer l-f">
            <div class="qxcx l-f">
                <span>全校出勤：正常<span id="zc"></span></span> <span>缺席<span id="qx"></span></span> <span>迟到<span id="cd"></span></span> <span>早退<span id="zt"></span></span>
            </div>
            <div class="tcdl l-r">
                <a class="tc-span" href="logout.action"></a>
            </div>
            <div class="pz l-r">
                <a class="pz-span" href="deploy.jsp"></a>
            </div>
        </div>
    </div>
</body>
</html>