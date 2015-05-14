<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
table{ width:1000px; margin: 20px auto;} 
table th{ font-size: 16px; height: 50px;}
table tr td{ border: 2px solid #95b8e7; height: 40px; }
table tr .title-one{ font-size: 16px; font-weight: 800; color: #ddd; background: #ccddff; height: 50px; }
table tr .title-two{ font-size: 14px;  color: #000000; background: #e0ecff; text-align: right; padding-right:10px ;}
table tr td input,td select{ border: 1px solid #8db2e3; background: #fff;  line-height: 25px; color: #606060; width:170px; font-size:14px;}
table tr .img{ width: 200px;}


.xsxxtp {
	background: url(img/xsxx.png);
	width: 457px;
	height: 283px;
	position: fixed;
	bottom: 0;
	right: 0;
	z-index: 1;
}

form {
	position: relative;
	z-index: 4;
}
</style>
<jsp:include page="../../common/include.jsp"></jsp:include>
</head>
<body>
	<script type="text/javascript"
		src="<%=basePath%>/js/basicinfo/xuesheng/xuesheng.js"></script>
	<!-- 
	<div id="DIV_toolbar" style="padding:5px;">
		<a onclick="updateXsxx();" plain="true" class="easyui-linkbutton"
			iconCls="icon-add">更新</a> <font color="red" size="3px">注意：此信息只允许修改一次，请谨慎操作</font>
	</div>
	 -->
	 <div style="margin:20px 0 0 30%">
	 <font color="red" style="font-size:14px">注意：1、学生基本信息只允许修改一次，请谨慎操作。</font>
	 <br/>
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" style="font-size:14px">2、学号、姓名、身份证号、所在学院、所在专业、所在年级、所在班级为必填项</font>
	 <br />
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" style="font-size:14px">3、填写完毕后如需要进行修改，请联系本院系系教学秘书。</font>
	 </div>
	 
	<form id="add_form" method="post" enctype="multipart/form-data">
	<!--  id="datagrid" -->
		<table>
			<tr>
				<td class="title-two"><label for="xs_xh">学号:</label></td>
				<td>
					<input class="easyui-validatebox" type="text" id="xs_xh" name="xsxx.xh" data-options="required:true,missingMessage:'请输入学号'" />
					<font color="red" style="font-size:14px">*必填</font>
					<br/>
					<input class="easyui-validatebox" type="hidden" id="xs_id" name="xsxx.xsId" />
				</td>
				<td class="title-two"><label for="xs_zsxm">姓名:</label></td>
				<td>
					<input class="easyui-validatebox" type="text" id="xs_zsxm" name="xsxx.zsxm"	data-options="required:true,missingMessage:'请输入姓名'" />
					<font color="red" style="font-size:14px">*必填</font>
				</td>
				<td colspan=2 rowspan="5" class="img">
					<!-- 
					<label for="xs_imgfile">照片:</label><input type="file" name="imgfile" id="xs_imgfile" class="easyui-validatebox" onchange="addPic()" data-options="validType:'picture'" />
					<input type="hidden" name="xsxx.zhaopianId" class="easyui-validatebox" />
					<img id="addpic" />
				 	-->
				</td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_sfzhm">身份证号:</label></td>
				<td>
					<input class="easyui-validatebox" type="text" id="xs_sfzhm" name="xsxx.sfzhm" data-options="required:true,validType:'idcard',missingMessage:'请输入身份证号'" />
					<font color="red" style="font-size:14px">*必填</font>
				</td>
				<td class="title-two"><label for="xs_rtsj">入团时间:</label></td>
				<td><input class="easyui-datebox" id="xs_rtsj" name="xsxx.rtsj"
					data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_xb">性别:</label></td>
				<td><input class="easyui-combobox" id="xs_xb" name="xsxx.xb"
					data-options="valueField:'xingbie',textField:'xingbie',url:'<%=basePath%>js/xingbie.json'" /></td>
				<td class="title-two"><label for="jxl_mz">民族:</label></td>
				<td><input class="easyui-combobox" id="xs_mz" name="xsxx.mz"
					data-options="valueField:'minzu',textField:'minzu',url:'<%=basePath%>js/minzu.json'" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_cym">曾用名:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_cym"
					name="xsxx.cym" /></td>
				<td class="title-two"><label for="xs_hjlb">户籍类别:</label></td>
				<td><select class="easyui-combobox" id="xs_hjlb"
					name="xsxx.hjlb">
						<option value=""></option>
						<option value="非农业户口">非农业户口</option>
						<option value="农业户口">农业户口</option>
				</select></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_zsjj">招生季节:</label></td>
				<td>
					<select class="easyui-combobox" id="xs_zsjj"
						name="xsxx.zsjj">
							<option value=""></option>
							<option value="春季">春季</option>
							<option value="秋季">秋季</option>
					</select>
				</td>
				<td class="title-two"><label for="xs_bylb">毕业类别:</label></td>
				<td>
				<select class="easyui-combobox" id="xs_bylb"
					name="xsxx.bylb">
						<option value=""></option>
						<option value="全日制">全日制</option>
						<option value="自考">自考</option>
						<option value="函授">函授</option>
						<option value="成人高考">成人高考</option>
						<option value="网络教育">网络教育</option>
						<option value="电大">电大</option>
						<option value="夜校">夜校</option>
				</select></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_dahh">档案行号:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_dahh"
					name="xsxx.dahh" data-options="validType:'integer'" /></td>
				<td class="title-two"><label for="xs_dayh">档案页号:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_dayh"
					name="xsxx.dayh" data-options="validType:'integer'" /></td>
				<td class="title-two"><label for="xs_jkzk">健康状况:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_jkzk"
					name="xsxx.jkzk" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_sg">身高:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_sg"
					name="xsxx.sg" /></td>
				<td class="title-two" ><label for="xs_tz">体重:</label></td>
				<td colspan=4><input class="easyui-validatebox" type="text" id="xs_tz"
					name="xsxx.tz" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_jtzz">家庭住址:</label></td>
				<td colspan="5"><input class="easyui-validatebox" type="text" id="xs_jtzz"
					name="xsxx.jtzz"  style="width:500px"/></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_xyId">所在学院:</label></td>
				<td colspan="5">
					<input class="easyui-combobox" id="xs_xyId" name="xsxx.xyId" data-options="valueField:'xyid',textField:'xymc',url:'<%=basePath%>listforXUEYUAN.action'"  style="width:500px"/>
					<font color="red" style="font-size:14px">*必填</font>
				</td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_zyId">所在专业:</label></td>
				<td colspan="5">
					<input class="easyui-combobox" id="xs_zyId" name="xsxx.zyId" style="width:500px;" data-options="valueField:'ZY_ID',textField:'ZYMC',url:'<%=basePath%>listAllZHUANYEFORBANJI.action'" />
					<font color="red" style="font-size:14px">*必填</font>
				</td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_njId">所在年级:</label></td>
				<td colspan="5">
					<input class="easyui-combobox" id="xs_njId" name="xsxx.njId" style="width:500px;" data-options="valueField:'NJ_ID',textField:'NJMC',url:'<%=basePath%>listAllNIANJIFORBANJI.action'" />
					<font color="red" style="font-size:14px">*必填</font>	
				</td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_bjId">所在班级:</label></td>
				<td colspan="5">
					<input class="easyui-combobox" id="xs_bjId" name="xsxx.bjId" style="width:500px;" data-options="valueField:'BJ_ID',textField:'BJMC',url:'<%=basePath%>listAllBANJI.action'" />
					<font color="red" style="font-size:14px">*必填</font>
				</td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_csny">出生年月:</label></td>
				<td colspan="2"><input class="easyui-datebox" id="xs_csny" name="xsxx.csny"
					data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				<td class="title-two"><label for="xs_csd">出生地:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_csd"
					name="xsxx.csd"  style="width:250px"/></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_lxdh">联系电话:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_lxdh"
					name="xsxx.lxdh"
					data-options="validType:'mobile',missingMessage:'请输入联系电话'" /></td>
				<td class="title-two"><label for="xs_lxdz">联系地址:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_lxdz"
					name="xsxx.lxdz" data-options="missingMessage:'请输入联系地址'"  style="width:250px"/></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_gatdm">港澳台代码:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_gatdm"
					name="xsxx.gatdm" /></td>
				<td class="title-two"><label for="xs_zkzh">准考证号:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_zkzh"
					name="xsxx.zkzh"  style="width:250px"/></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_zyIdLq">录取专业:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text"
					id="xs_zyIdLq" name="xsxx.zyIdLq" /></td>
				<td class="title-two"><label for="xs_xxtj">获知学校信息途径:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_xxtj"
					name="xsxx.xxtj"  style="width:250px"/></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_pylb">培养类别:</label></td>
				<td colspan="2"><select class="easyui-combobox" id="xs_pylb"
					name="xsxx.pylb">
						<option value=""></option>
						<option value="国家计划外自筹经费">国家计划外自筹经费</option>
						<option value="国家计划内定向培养">国家计划内定向培养</option>
						<option value="国家计划外委托培养">国家计划外委托培养</option>
				</select>
				</td>
				<td class="title-two"><label for="xs_tc">特长:</label></td>
				<td colspan="2"><input class="easyui-validatebox" type="text" id="xs_tc"
					name="xsxx.tc"  style="width:250px"/></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_pycc">培养层次:</label></td>
				<td><select class="easyui-combobox" id="xs_pycc"
					name="xsxx.pycc">
						<option value=""></option>
						<option value="博士">博士</option>
						<option value="硕士">硕士</option>
						<option value="本科">本科</option>
						<option value="大专">大专</option>
						<option value="中专">中专</option>
				</select></td>
				<td class="title-two"><label for="xs_pydx">培养对象:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_pydx"
					name="xsxx.pydx" /></td>
				<td class="title-two"><label for="xs_xz">学制:</label></td>
				<td><select class="easyui-combobox" id="xs_xz" name="xsxx.xz">
						<option value=""></option>
						<option value="两年制">两年制</option>
						<option value="三年制">三年制</option>
						<option value="四年制">四年制</option>
						<option value="五年制">五年制</option>
				</select></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_sushehao">宿舍号:</label></td>
				<td><input class="easyui-validatebox" type="text"
					id="xs_sushehao" name="xsxx.sushehao"
					data-options="missingMessage:'请输入宿舍号'" /></td>
				<td class="title-two"><label for="xs_ssdh">宿舍电话:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_ssdh"
					name="xsxx.ssdh" data-options="validType:'phone'" /></td>
				<td class="title-two"><label for="xs_lxr">联系人:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_lxr"
					name="xsxx.lxr" data-options="missingMessage:'请输入联系人'" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_dzyx">电子邮箱:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_dzyx"
					name="xsxx.dzyx" data-options="validType:'email'" /></td>
				<td class="title-two"><label for="xs_ksh">考生号:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_ksh"
					name="xsxx.ksh" /></td>
				<td class="title-two"><label for="xs_kslb">考生类别:</label></td>
				<td><select class="easyui-combobox" id="xs_kslb"
					name="xsxx.kslb">
						<option value=""></option>
						<option value="统招">统招</option>
						<option value="自考">自考</option>
						<option value="成人">成人</option>
						<option value="函授">函授</option>
				</select></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_kstz">考生特征:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_kstz"
					name="xsxx.kstz" /></td>
				<td class="title-two"><label for="xs_rxwhcd">入学文化程度:</label></td>
				<td><input class="easyui-validatebox" type="text"
					id="xs_rxwhcd" name="xsxx.rxwhcd" /></td>
				<td class="title-two"><label for="xs_sysf">生源省份:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_sysf"
					name="xsxx.sysf" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_yzbm">邮政编码:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_yzbm"
					name="xsxx.yzbm" data-options="validType:'zipcode'" /></td>
				<td class="title-two"><label for="xs_sydq">生源地区:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_sydq"
					name="xsxx.sydq" /></td>
				<td class="title-two"><label for="xs_kldm">科类代码:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_kldm"
					name="xsxx.kldm" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_rxcj">入学成绩:</label></td>
				<td><input class="easyui-numberbox" type="text" id="xs_rxcj"
					name="xsxx.rxcj" precision="2" max="750" min="0" /></td>
				<td class="title-two"><label for="xs_rxfs">入学方式:</label></td>
				<td><select class="easyui-combobox" id="xs_rxfs"
					name="xsxx.rxfs">
						<option value=""></option>
						<option value="考试">考试</option>
						<option value="推荐">推荐</option>
						<option value="测验">测验</option>
						<option value="面谈">面谈</option>
				</select></td>
				<td class="title-two"><label for="xs_rxsj">入学时间:</label></td>
				<td><input class="easyui-datebox" id="xs_rxsj" name="xsxx.rxsj"
					data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_xxnx">学习年限:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_xxnx"
					name="xsxx.xxnx" /></td>
				<td class="title-two"><label for="xs_rxnf">入学年份:</label></td>
				<td><input class="easyui-datebox" id="xs_rxnf" name="xsxx.rxnf"
					data-options="formatter:myformatter,parser:myparser,editable:false" /></td>
				<td class="title-two"><label for="xs_zxwyyz">主修外语语种:</label></td>
				<td><input class="easyui-validatebox" type="text"
					id="xs_zxwyyz" name="xsxx.zxwyyz" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_zxwyjb">主修外语级别:</label></td>
				<td><input class="easyui-validatebox" type="text"
					id="xs_zxwyjb" name="xsxx.zxwyjb" /></td>
				<td class="title-two"><label for="xs_qtbxxs">其他办学形式:</label></td>
				<td><input class="easyui-validatebox" type="text"
					id="xs_qtbxxs" name="xsxx.qtbxxs" /></td></td>
				<td class="title-two"><label for="xs_bxfs">办学方式:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_bxfs"
					name="xsxx.bxfs" /></td>
			</tr>
			<tr>
				<td class="title-two"><label for="xs_bxlx">办学类型:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_bxlx"
					name="xsxx.bxlx" /></td>
				<td class="title-two"><label for="xs_byzx">毕业中学:</label></td>
				<td><input class="easyui-validatebox" type="text" id="xs_byzx"
					name="xsxx.byzx" /></td>
				<td class="title-two"><label for="xs_jsjnldj">主修计算机等级:</label></td>
				<td><input class="easyui-validatebox" type="text"
					id="xs_jsjnldj" name="xsxx.jsjnldj" /></td>
			</tr>
			<tr>
				<!-- 
				<td class="title"><label for="xs_yhId">用户ID:</label></td>-->
				<input type="hidden"  id="xs_yhId"  name="xsxx.yhId" />
			</tr>
			<tr>
				<td colspan="6">
					<a id="gx"  onclick="updateXsxx();" plain="false" class="easyui-linkbutton" style="letter-spacing:30px">&nbsp;更新</a>
				</td>
			</tr>
			<input type="hidden" id="xs_zhiwenId1" name="xsxx.zhiwenId1"/>
			<input type="hidden" id="xs_zhiwenId2" name="xsxx.zhiwenId2"/>
		</table>
	</form>
	<div class="xsxxtp"></div>
	<!-- 查看/修改用户对话框  END-->
</body>
</html>
