<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="common/include.jsp"></jsp:include>


  </head>
  
<body class="easyui-layout" >
<script type="text/javascript">
var datagrid;
var centerTabs;
function addTab(node){
	if(centerTabs.tabs('exists',node.text)){
		centerTabs.tabs('select',node.text);
	}else{
		if(node.attributes.url&&node.attributes.url.length>0){
			$.messager.progress({text:'页面加载中...',interval:100});
			centerTabs.tabs('add',{
				title:node.text,
				closable:true,
				content:'<iframe src="'+node.attributes.url+'" frameborder="0" style="border:0;width:100%;height:99%"/>'
			});
			$.messager.progress('close');
		}else{
			centerTabs.tabs('add',{
				title:node.text,
				closable:true,
				content:'<iframe src="'+node.attributes.url+'" frameborder="0" style="border:0;width:100%;height:99%"/>',
				tools:[{iconCls:'icon-mini-refresh',
						handler:function(){
							refreshTab(node.text);
						}
					   }]
			});
		}
	}
}

function refreshTab(title){
	var tab=centerTabs.tabs('getTab',title);
	centerTabs.tabs('update',{tab:tab,options:tab.panel('options')});
}

$(function(){
/*
	$('#datagrid').datagrid({
		url:'',
		title:'标题',
		iconCls:'icon-cls',
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40,50,60,70,80],
		fit:true,
		fitColumns:false,
		nowrap:false,
		border:false,
		idField:'id',
		columns:[[{title:'学号',field:'id',width:100},
				  {title:'姓名',field:'id',width:100},
				  {title:'班级',field:'id',width:100}]]
	});
*/
centerTabs=$('#centerTabs').tabs({   
    border:false,
    fit:true,
    onSelect:function(title){   
    }   
});
});


</script>
<!-- 
<div class="easyui-tabs" fit="true" border="false">
	<div title="用户管理" >
		<table id="datagrid"></table>
	</div>
</div>
 -->
 <div id="centerTabs" style="" >
 	<div title="首页" border="false" style="overflow:scroll;position:relative;width:100%;height: 100%;">
 	<!-- 	<iframe src="getKECHENGBIAO.action" width="640" height="500" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
 	 -->
   		<div class="link">
	   		<div class="bd">
		        <form action="http://www.baidu.com/baidu" target="_blank" class="f-l">
					<table>
		        		<tr>
		        			<td>
		                    	<input name=tn type=hidden value=baidu />
		                        <a href="http://www.baidu.com/"><img src="http://img.baidu.com/img/logo-80px.gif" alt="Baidu" align="bottom" border="0" class="f-l" /></a>
		                        <input type=text name=word class="input f-l" />
		                        <input type="submit" value="百度一下" class="ss f-l" />
							</td>
		        		</tr>
		        	</table>
				</form>
			</div>
			<div class="clear"></div>
            <div class="links">
                <a href="http://www.baidu.com" target="_blank" class="zylj"><img src="img/baidu.jpg"/></a>
                <a href="http://www.qq.com" target="_blank" class="zylj"><img src="img/qq.jpg"/></a>
                <a href="http://www.sohu.com" target="_blank" class="zylj"><img src="img/sohu.jpg"/></a>
                <a href="http://www.taobao.com" target="_blank" class="zylj"><img src="img/taobao.jpg"/></a>
                <a href="http://www.tmall.com" target="_blank" class="zylj"><img src="img/tmall.jpg" /></a>
                <a href="http://www.jd.com" target="_blank" class="zylj"><img src="img/jd.jpg" /></a>
                <a href="http://www.vip.com" target="_blank" class="zylj"><img src="img/vip.jpg" /></a>
                <a href="http://www.163.com" target="_blank" class="zylj"><img src="img/163.jpg" /></a>
                <a href="http://www.sina.com.cn" target="_blank" class="zylj"><img src="img/sina.jpg" /></a>
                <a href="http://www.renren.com" target="_blank" class="zylj"><img src="img/renren.jpg" /></a>
                <a href="http://hrb.58.com" target="_blank" class="zylj"><img src="img/58.jpg" /></a>
                <a href="http://www.zhaopin.com" target="_blank" class="zylj"><img src="img/zhaopin.jpg" /></a>
                <a href="http://www.youku.com" target="_blank" class="zylj"><img src="img/youku.jpg" /></a>
                <a href="http://www.iqiyi.com" target="_blank" class="zylj"><img src="img/iqiyi.jpg" /></a>
            </div>
        </div>
        
 	</div>
 	
 </div>
  
</body>

</html>
