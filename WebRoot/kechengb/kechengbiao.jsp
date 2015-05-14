<%@ page language="java" import="java.util.*,com.hrbsys.bean.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>课程表</title>
<style type="text/css">
table {
	caption-side: top;
	width: auto;
	border-collapse: collapse;
	empty-cells: hide;
	margin: 20px auto 0;
}

td {
	border: 2px solid #99BBE8;
	width: 100px;
	padding: 0;
}

h1 {
	color: #99BBE8;
	font-size: 20px;
}

th {
	border: 2px solid #99BBE8;
}
</style>


</head>

<body >
		<table class="kcb";>
			<tr>
				<th colspan="8"><h1>课程表</h1></th>
			</tr>
			<tr>
				<td style="width: 150px;">&nbsp;</td>
				<td>星期一</td>
				<td>星期二</td>
				<td>星期三</td>
				<td>星期四</td>
				<td>星期五</td>
				<td>星期六</td>
				<td>星期日</td>
			</tr>
			<%
				List all_keshi = (ArrayList) request.getAttribute("list_keshi");
				List all_kechengb = (ArrayList) request
						.getAttribute("list_kechengb");
				for (int i = 0; i < all_keshi.size(); i++) { //循环行
					boolean xingqi1 = false;
					boolean xingqi2 = false;
					boolean xingqi3 = false;
					boolean xingqi4 = false;
					boolean xingqi5 = false;
					boolean xingqi6 = false;
					boolean xingqi7 = false;
					String danyuange1 = ""; //单元格显示的内容
					String danyuange2 = ""; //单元格显示的内容
					String danyuange3 = ""; //单元格显示的内容
					String danyuange4 = ""; //单元格显示的内容
					String danyuange5 = ""; //单元格显示的内容
					String danyuange6 = ""; //单元格显示的内容
					String danyuange7 = ""; //单元格显示的内容
					String danyuanhang = "";
					KESHI tmp_ks = (KESHI) all_keshi.get(i);
			%>
			<tr>
				<td>
					<%=tmp_ks.getKSMC()%><br/>
					(<%=tmp_ks.getKSSJ()%>-<%=tmp_ks.getJSSJ()%>)
				</td>
				<%
					out.println();
						for (int j = 0; j < all_kechengb.size(); j++) { //循环列
							KECHENGB tmp_k = (KECHENGB) all_kechengb.get(j);
							if (tmp_ks.getKS_ID().equals(tmp_k.getKS_ID())) { //如果是课时的ID相同，那么则要在本行显示相应的诚信表信息
								String xingqi = tmp_k.getXINGQI();
								String kechengxx = tmp_k.getKCXXMC();//课程信息名称
								String laoshi = tmp_k.getLAOSHIMC();//老师名称
								String laoshigh = tmp_k.getLAOSHIGH();//老师工号
								String jiaoshi = tmp_k.getJSMC();//教室
								if (!"星期一".equals(xingqi)) {
									xingqi1 = true;
								} else {
									danyuange1 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
								if (!"星期二".equals(xingqi)) {
									xingqi2 = true;
								} else {
									danyuange2 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
								if (!"星期三".equals(xingqi)) {
									xingqi3 = true;
								} else {
									danyuange3 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
								if (!"星期四".equals(xingqi)) {
									xingqi4 = true;
								} else {
									danyuange4 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
								if (!"星期五".equals(xingqi)) {
									xingqi5 = true;
								} else {
									danyuange5 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
								if (!"星期六".equals(xingqi)) {
									xingqi6 = true;
								} else {
									danyuange6 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
								if (!"星期日".equals(xingqi)) {
									xingqi7 = true;
								} else {
									danyuange7 += "<br/>" + kechengxx + "|" + laoshi
											+ "|" + laoshigh + "|" + jiaoshi; //凑出来单元格要显示的内容
								}
							}
						}
						//如果没有的单元格，就单独的写
						if (xingqi1) { //单元格1
							if (!"".equals(danyuange1)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange1 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}
						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange1 + "</span></td>";
						}
						if (xingqi2) { //单元格2
							if (!"".equals(danyuange2)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange2 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}
						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange2 + "</span></td>";
						}
						if (xingqi3) {
							if (!"".equals(danyuange3)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange3 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}

						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange3 + "</span></td>";
						}
						if (xingqi4) {
							if (!"".equals(danyuange4)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange4 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}
						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange4 + "</span></td>";
						}
						if (xingqi5) {
							if (!"".equals(danyuange5)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange5 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}
						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange5 + "</span></td>";
						}
						if (xingqi6) {
							if (!"".equals(danyuange6)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange6 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}
						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange6 + "</span></td>";
						}
						if (xingqi7) {
							if (!"".equals(danyuange7)) {
								danyuanhang += "<td><span style=\"font-size:14px;\">"
										+ danyuange7 + "</span></td>";
							} else {
								danyuanhang += "<td></td>";
							}
						} else {
							danyuanhang += "<td><span style=\"font-size:14px;\">"
									+ danyuange7 + "</span></td>";
						}
				%>
				<%=danyuanhang%>
			</tr>
			<%
				}
			%>
		</table>

</body>
</html>
