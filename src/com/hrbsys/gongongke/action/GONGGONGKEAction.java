package com.hrbsys.gongongke.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.IfFunc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.basicinfo.banji.service.BANJIService;
import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.GONGGONGKE;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.XueShengKQ;
import com.hrbsys.gongongke.service.GONGGONGKEService;
import com.hrbsys.nianji.service.NIANJIService;
import com.hrbsys.realtime.service.RealTimeService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.xuesheng.service.XsxxService;
import com.hrbsys.zhuanye.service.ZhuanYeService;

/**
 * 公共课Action
 */
public class GONGGONGKEAction {
	// 公共课服务接口
	private GONGGONGKEService ggkeService;
	private XsxxService xsxxService;
	private RealTimeService realService;
	private BANJIService bjService;
	private NIANJIService njService;
	private ZhuanYeService zyService;
	private StudentService stuService;
	// 老师名称
	private String LAOSHIMC;
	// 教室名称
	private String JSMC;
	// 课程信息名称
	private String KCXXMC;
	// 课时开始时间
	private String KS_KSSJ;
	// 课程的结束时间
	private String KS_JSSJ;
	private String SKSJ_KS;
	private String SKSJ_JS;
	private String SKSJ;
	private String XUEQI_ID;
	private String JS_ID;
	private String JG_ID;
	private String KS_ID;
	private String KCB_ID;
	private String LAOSHI_ID;
	private String KCB_FXS_ID;
	private String XSXH;
	private String ZSXM;
	private String BJMC;
	private String NJMC;
	private String ZYMC;
	private String CQZT;
	// 星期
	private String XINGQI;
	private String XUEQI;
	// 周
	private String ZHOU;
	private String Term_KSMC;
	private String Day_KSMC;

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	/**
	 * 获取公共课的列表
	 */
	public void listGONGGONGKE() {
		// 分页相关变量
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		// 将一些参数通过jsonMap传送到前台
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LAOSHIMC", this.LAOSHIMC);
		params.put("JSMC", this.JSMC);
		params.put("KCXXMC", this.KCXXMC);
		params.put("KS_KSSJ", this.SKSJ_KS);
		params.put("KS_JSSJ", this.SKSJ_JS);

		params.put("XINGQI", this.XINGQI);
		params.put("ZHOU", this.ZHOU);
		params.put("Term_KSMC", this.Term_KSMC);
		params.put("Day_KSMC", this.Day_KSMC);
		params.put("XUEQI", this.XUEQI);
		// 用于存储出勤的人数
		int zt = 0;
		int cd = 0;
		int zc = 0;
		int cdAndzt = 0;
		JsonConfig config = new JsonConfig();
		// 读取课程表中的公共课信息的集合
		List<KECHENGB> list = ggkeService.findGONGGONGKByPageApp(start, number,
				params, order, sort);
//		// 当授课日期的开始时间为null时，将其转换成String
//		if (this.SKSJ_KS == null) {
//			this.SKSJ_KS = "";
//		}
//		// 当授课日期的结束时间为null时，将其转换成String
//		if (this.SKSJ_JS == null) {
//			this.SKSJ_JS = "";
//		}
		List dataList = new ArrayList();
		/*
		 * 当开始日期与结束日期不为空时，查询往天，否则进行实时查询
		 */
		if (this.SKSJ_KS != null && !"".equals(this.SKSJ_KS)
				&& this.SKSJ_JS != null && !"".equals(this.SKSJ_JS)) {
			// 遍历往天课程表四公共选修课的具体课信息程
			for (int i = 0; i < list.size(); i++) {
				KECHENGB kcb = list.get(i);
				GONGGONGKE kcbxx = new GONGGONGKE();
				// 正常的显示信息
				kcbxx.setKCB_ID(kcb.getKCB_ID());// 课程表ID
				kcbxx.setKCB_FXS_ID(kcb.getKCB_FXS_ID());// 课程表分学时ID
				kcbxx.setKCXXMC(kcb.getKCXXMC());// 课程信息名称
				kcbxx.setLAOSHIMC(kcb.getLAOSHIMC());// 授课教师名称
				kcbxx.setLAOSHI_ID(kcb.getLAOSHI_ID());
				kcbxx.setJSMC(kcb.getJSMC());// 教室名称
				kcbxx.setJS_ID(kcb.getJS_ID());// 教室ID
				kcbxx.setSKSJ(kcb.getKS_KSSJ() + "~" + kcb.getKS_JSSJ());// 授课时间
				// 获取往天的学生出勤的情况
				Map<String, Integer> ggkeMap = ggkeService.getKQQK(
						kcb.getJS_ID(), kcb.getLAOSHI_ID(), kcb.getKS_ID(),
						kcb.getKCB_ID(), this.SKSJ_KS, this.SKSJ_JS);

				if (ggkeMap.isEmpty()) {
					// 如果数据集合为空，则正常出勤，应出勤，缺席人数，迟到人数,早退人数都为0
					kcbxx.setZCCXRS(String.valueOf(0));// 正常出勤
					kcbxx.setYCXRS(String.valueOf(0));// 应出勤
					kcbxx.setQXRS(String.valueOf(0));// 缺席人数
					kcbxx.setCDRS(String.valueOf(0));// 迟到人数
					kcbxx.setZTRS(String.valueOf(0));// 早退人数
				} else {
					kcbxx.setZCCXRS(ggkeMap.get("zhengchang").toString());// 正常出勤
					kcbxx.setYCXRS(ggkeMap.get("yingchuqin").toString());// 应出勤
					kcbxx.setQXRS(ggkeMap.get("queqin").toString());// 缺席人数
					kcbxx.setCDRS(ggkeMap.get("chidao").toString());// 迟到人数
					kcbxx.setZTRS(ggkeMap.get("zaotui").toString());// 早退人数
				}
				dataList.add(kcbxx);
			}

			if (dataList.isEmpty()) {
				jsonNull();
			} else {
				// 总的记录条数
				jsonMap.put("total", dataList.size());
				// rows键 存放每页记录 list
				jsonMap.put("rows", dataList);
				jsonMap.put("page", intPage);
				// 将jsonMap中的信息返回到前台页面中
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
			}
		} else {

			// 遍历课程信息集合
			for (int i = 0; i < list.size(); i++) {
				KECHENGB kcb = list.get(i);
				// 获取实时出勤的情况
				Map<String, Integer> ggkeMap = ggkeService.getKQQK(
						kcb.getJS_ID(), kcb.getLAOSHI_ID(), kcb.getKS_ID(),
						kcb.getKCB_ID(), this.SKSJ_KS, this.SKSJ_JS);
				GONGGONGKE kcbxx = new GONGGONGKE();
				/*
				 * 正常的显示信息
				 */
				kcbxx.setKCB_ID(kcb.getKCB_ID());// 课程表ID
				kcbxx.setKCB_FXS_ID(kcb.getKCB_FXS_ID());// 课程表分学时ID
				kcbxx.setKCXXMC(kcb.getKCXXMC());// 课程信息名称
				kcbxx.setLAOSHI_ID(kcb.getLAOSHI_ID());
				kcbxx.setLAOSHIMC(kcb.getLAOSHIMC());// 授课教师名称
				kcbxx.setJS_ID(kcb.getJS_ID());
				kcbxx.setJSMC(kcb.getJSMC());// 教室名称
				kcbxx.setKS_ID(kcb.getKS_ID());
				kcbxx.setSKSJ(kcb.getKS_KSSJ() + "~" + kcb.getKS_JSSJ());// 授课时间
				// 统计实时数据，统计出勤情况
				// ArrayList<XueShengKQ> listKaiFang =
				// realService.getKQQK_GGK(kcb.getJS_ID(),
				// kcb.getLAOSHI_ID(),kcb.getKS_ID(),kcb.getKCB_ID(), null);
				// 判断获取的出勤数据集合是否为空
				if (ggkeMap.isEmpty()) {
					// 如果数据集合为空，则正常出勤，应出勤，缺席人数，迟到人数,早退人数都为0
					kcbxx.setZCCXRS(String.valueOf(0));// 正常出勤
					kcbxx.setYCXRS(String.valueOf(0));// 应出勤
					kcbxx.setQXRS(String.valueOf(0));// 缺席人数
					kcbxx.setCDRS(String.valueOf(0));// 迟到人数
					kcbxx.setZTRS(String.valueOf(0));// 早退人数
				} else {
					kcbxx.setZCCXRS(ggkeMap.get("zhengchang").toString());// 正常出勤
					kcbxx.setYCXRS(ggkeMap.get("yingchuqin").toString());// 应出勤
					kcbxx.setQXRS(ggkeMap.get("queqin").toString());// 缺席人数
					kcbxx.setCDRS(ggkeMap.get("chidao").toString());// 迟到人数
					kcbxx.setZTRS(ggkeMap.get("zaotui").toString());// 早退人数
				}
				// 将对象加入到集合中
				dataList.add(kcbxx);
			}

			// 判断数据集合是否为空
			if (dataList.isEmpty()) {
				jsonNull();
				return;
			} else {
				// 总的记录条数
				jsonMap.put("total", dataList.size());
				// rows键 存放每页记录 list
				jsonMap.put("rows", dataList);
				jsonMap.put("page", intPage);
				// 将jsonMap中的信息返回到前台页面中
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
				return;
			}

		}
	}

	/**
	 * 进一步查看请求的方法
	 */
	public void enterView() {
		// 将一些参数通过jsonMap传送到前台
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 分页相关变量
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		JsonConfig config = new JsonConfig();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("JS_ID", this.JS_ID);
		params.put("JG_ID", this.JG_ID);
		params.put("KS_ID", this.KS_ID);
		params.put("KCB_ID", this.KCB_ID);
		params.put("kbc_fxs", this.KCB_FXS_ID);
		params.put("skkssj", this.SKSJ_KS);
		params.put("xkjssj", this.SKSJ_JS);
		params.put("xsxh", this.XSXH);
		params.put("xsxm", this.ZSXM);
		params.put("cqzt", this.CQZT);
		params.put("zymc", this.ZYMC);
		params.put("bjmc", this.BJMC);
		params.put("njmc", this.NJMC);

		// 存储的数据集合
		List dataList = new ArrayList();
		// 判断授课的开始日期与结束日期是否为空
		if (this.SKSJ_KS != null && !"".equals(this.SKSJ_KS)
				&& this.SKSJ_JS != null && !"".equals(this.SKSJ_JS)) {
			/******************************** 进行查询往天数据 ***************************************/
			List<KQXX_XSCQ> ggkeList = ggkeService.findGGKByPageApp(start,
					number, params, order, sort);
			for (KQXX_XSCQ xs : ggkeList) {
				Xsxx xsxx = xsxxService.findStudentByXSId(xs.getXS_ID());
				GONGGONGKE ggk = new GONGGONGKE();
				ggk.setXS_ID(xs.getXS_ID());
				ggk.setZSXM(xsxx.getZsxm());
				ggk.setXSXH(xsxx.getXh());
				ggk.setXB(xsxx.getXb());
				ggk.setNJMC(xsxx.getNJMC());
				ggk.setBJMC(xsxx.getBJMC());
				ggk.setZYMC(xsxx.getZYMC());
				ggk.setCQZT(xs.getCQQK());
				dataList.add(ggk);
			}

			// 判断集合数据是否为空
			if (dataList.isEmpty()) {
				jsonNull();
				return;
			} else {
				// 总的记录条数
				jsonMap.put("total", dataList.size());
				// rows键 存放每页记录 list
				jsonMap.put("rows", dataList);
				jsonMap.put("page", intPage);
				// 将jsonMap中的信息返回到前台页面中
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
				return;
			}
		} else {
			/********************************************** 进行查询实时数据 ***********************************/

			// 查询回实时数据
			List<XueShengKQ> realStuList = realService.getKQQK_GGK(this.JS_ID,
					this.JG_ID, this.KS_ID, this.KCB_ID, null);

			if (realStuList.isEmpty()) {
				jsonNull();
			} else {
				for (XueShengKQ xs : realStuList) {
					Xsxx xsxx = xsxxService.findStudentByXSId(xs.getXs_id());
					GONGGONGKE ggk = new GONGGONGKE();
					ggk.setXS_ID(xsxx.getXsId());
					ggk.setZSXM(xsxx.getZsxm());
					ggk.setXSXH(xsxx.getXh());
					ggk.setXB(xsxx.getXb());
					ggk.setNJMC(xsxx.getNJMC());
					ggk.setBJMC(xsxx.getBJMC());
					ggk.setZYMC(xsxx.getZYMC());
					ggk.setCQZT(xs.getCqqk());
					dataList.add(ggk);
				}
			}
			
			// 对实时数据进行过滤 获取用户想查询的数据
			if (this.XSXH != null && !"".equals(this.XSXH) || this.ZSXM != null
					&& !"".equals(this.ZSXM) || this.CQZT != null
					&& !"".equals(this.CQZT) || this.ZYMC != null
					&& !"".equals(this.ZYMC) || this.BJMC != null
					&& !"".equals(this.BJMC) || this.NJMC != null
					&& !"".equals(this.NJMC)) {
				// 在用户获取要查询的记录之前，先清空存储数据的集合
				dataList.clear();
				// 遍历实时数据，并过滤用户想要的数据
				for (int i = 0; i < realStuList.size(); i++) {
					XueShengKQ xs1 = realStuList.get(i);
					GONGGONGKE ggk = new GONGGONGKE();
					Xsxx xsxx = stuService.findStudentById(xs1.getXs_id());
					if (this.XSXH.equals(xsxx.getXh())
							|| this.ZSXM.equals(xsxx.getZsxm())
							|| this.CQZT.equals(xs1.getCqqk())
							|| this.BJMC.equals(xsxx.getBjId())
							|| this.NJMC.equals(xsxx.getNjId())
							|| this.ZYMC.equals(xsxx.getZyId())) {
						ggk.setXS_ID(xsxx.getXsId());
						ggk.setZSXM(xsxx.getZsxm());
						ggk.setXSXH(xsxx.getXh());
						ggk.setXB(xsxx.getXb());
						ggk.setNJMC(xsxx.getNJMC());
						ggk.setBJMC(xsxx.getBJMC());
						ggk.setZYMC(xsxx.getZYMC());
						ggk.setCQZT(xs1.getCqqk());
						// 将符合用户要求的数据放入到其中
						dataList.add(ggk);
					}
				}
			} 

			// 判断是升序还是降序 asc 升序 desc降序
			if (this.order != null && !"".equals(this.order)) {
				if (this.order.equals("desc")) {
					// 集合倒序
					Collections.reverse(dataList);
				}
			}

			// 判断数据集合是否为空
			if (dataList.isEmpty()) {
				jsonNull();
			}
			// 总的记录条数
			jsonMap.put("total", dataList.size());
			// rows键 存放每页记录 list
			jsonMap.put("rows", dataList);
			jsonMap.put("page", intPage);
			// 将jsonMap中的信息返回到前台页面中
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
					config));
		}
	}

	/**
	 * 查询课程表中公共课的老师的名称
	 */
	public void findLAOSHIMC() {
		JsonConfig config = new JsonConfig();
		List list = ggkeService.findLAOSHIMC();
		List laoShiMcAll = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> ggkeMap = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			ggkeMap.put("laoshimc", obj[0]);
			ggkeMap.put("laoshi_id", obj[1]);
			laoShiMcAll.add(ggkeMap);
		}
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(laoShiMcAll,
				config));
	}

	/**
	 * 查询教室的名称
	 */
	public void findJSMC() {
		JsonConfig config = new JsonConfig();
		List list = ggkeService.findJSMC();
		List jsMcAll = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> ggkeMap = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			ggkeMap.put("jsmc", obj[0]);
			ggkeMap.put("js_id", obj[1]);
			jsMcAll.add(ggkeMap);
		}
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(jsMcAll,
				config));
	}

	/**
	 * 查询授课课程
	 */
	public void findSKMC() {
		JsonConfig config = new JsonConfig();
		List list = ggkeService.findSKMC();
		List all = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> ggkeMap = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			ggkeMap.put("kcxxmc", obj[0]);
			ggkeMap.put("kcxx_id", obj[1]);
			all.add(ggkeMap);
		}
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(all, config));
	}

	/************************************** 获取课时名称 ***********************************/
	public void findKSMC() {
		JsonConfig config = new JsonConfig();
		List list = ggkeService.findKSMC();
		List all = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> ggkeMap = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			ggkeMap.put("KSMC", obj[0]);
			ggkeMap.put("KS_ID", obj[1]);
			all.add(ggkeMap);
		}
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(all, config));
	}

	/**
	 * 当查询的数据为空时，调用此方法
	 */
	public void jsonNull() {
		JsonConfig config = new JsonConfig();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 用于存储参数
		List noData = new ArrayList();// 存储“无记录”信息
		GONGGONGKE ggk = new GONGGONGKE();
		ggk.setKCB_ID("null");
		ggk.setXS_ID("null");
		ggk.setKCXXMC("无记录");
		ggk.setZSXM("无记录");
		ggk.setXSXH("无记录");
		ggk.setXB("无记录");
		ggk.setBJMC("无记录");
		ggk.setNJMC("无记录");
		ggk.setZYMC("无记录");
		ggk.setKS_ID("无记录");
		ggk.setKCB_FXS_ID("无记录");
		ggk.setJS_ID("无记录");
		ggk.setJSMC("无记录");
		ggk.setLAOSHI_ID("无记录");
		ggk.setLAOSHIMC("无记录");
		ggk.setCQZT("无记录");
		ggk.setSKSJ("无记录");
		ggk.setYCXRS("无记录");
		ggk.setCQZT("无记录");
		ggk.setCDRS("无记录");
		ggk.setZTRS("无记录");
		ggk.setZCCXRS("无记录");
		ggk.setQXRS("无记录");
		noData.add(ggk);
		jsonMap.put("total", noData.size());
		jsonMap.put("rows", noData);// rows键 存放每页记录 list
		jsonMap.put("page", 1);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	/********************************** get和set方法 ************************************************************/
	public GONGGONGKEService getGgkeService() {
		return ggkeService;
	}

	public void setGgkeService(GONGGONGKEService ggkeService) {
		this.ggkeService = ggkeService;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String jSMC) {
		JSMC = jSMC;
	}

	public String getKCXXMC() {
		return KCXXMC;
	}

	public void setKCXXMC(String kCXXMC) {
		KCXXMC = kCXXMC;
	}

	public String getKS_KSSJ() {
		return KS_KSSJ;
	}

	public void setKS_KSSJ(String kS_KSSJ) {
		KS_KSSJ = kS_KSSJ;
	}

	public String getKS_JSSJ() {
		return KS_JSSJ;
	}

	public void setKS_JSSJ(String kS_JSSJ) {
		KS_JSSJ = kS_JSSJ;
	}

	public String getLAOSHIMC() {
		return LAOSHIMC;
	}

	public void setLAOSHIMC(String lAOSHIMC) {
		LAOSHIMC = lAOSHIMC;
	}

	public String getXINGQI() {
		return XINGQI;
	}

	public void setXINGQI(String xINGQI) {
		XINGQI = xINGQI;
	}

	public String getZHOU() {
		return ZHOU;
	}

	public void setZHOU(String zHOU) {
		ZHOU = zHOU;
	}

	public String getTerm_KSMC() {
		return Term_KSMC;
	}

	public void setTerm_KSMC(String term_KSMC) {
		Term_KSMC = term_KSMC;
	}

	public String getDay_KSMC() {
		return Day_KSMC;
	}

	public void setDay_KSMC(String day_KSMC) {
		Day_KSMC = day_KSMC;
	}

	public String getXUEQI() {
		return XUEQI;
	}

	public void setXUEQI(String xUEQI) {
		XUEQI = xUEQI;
	}

	public String getSKSJ_KS() {
		return SKSJ_KS;
	}

	public void setSKSJ_KS(String sKSJ_KS) {
		SKSJ_KS = sKSJ_KS;
	}

	public String getSKSJ_JS() {
		return SKSJ_JS;
	}

	public void setSKSJ_JS(String sKSJ_JS) {
		SKSJ_JS = sKSJ_JS;
	}

	public String getSKSJ() {
		return SKSJ;
	}

	public void setSKSJ(String sKSJ) {
		SKSJ = sKSJ;
	}

	public XsxxService getXsxxService() {
		return xsxxService;
	}

	public void setXsxxService(XsxxService xsxxService) {
		this.xsxxService = xsxxService;
	}

	public String getJG_ID() {
		return JG_ID;
	}

	public void setJG_ID(String jG_ID) {
		JG_ID = jG_ID;
	}

	public String getXUEQI_ID() {
		return XUEQI_ID;
	}

	public void setXUEQI_ID(String xUEQI_ID) {
		XUEQI_ID = xUEQI_ID;
	}

	public RealTimeService getRealService() {
		return realService;
	}

	public void setRealService(RealTimeService realService) {
		this.realService = realService;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String jS_ID) {
		JS_ID = jS_ID;
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String kS_ID) {
		KS_ID = kS_ID;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String kCB_ID) {
		KCB_ID = kCB_ID;
	}

	public String getLAOSHI_ID() {
		return LAOSHI_ID;
	}

	public void setLAOSHI_ID(String lAOSHI_ID) {
		LAOSHI_ID = lAOSHI_ID;
	}

	public String getKCB_FXS_ID() {
		return KCB_FXS_ID;
	}

	public void setKCB_FXS_ID(String kCB_FXS_ID) {
		KCB_FXS_ID = kCB_FXS_ID;
	}

	public BANJIService getBjService() {
		return bjService;
	}

	public void setBjService(BANJIService bjService) {
		this.bjService = bjService;
	}

	public NIANJIService getNjService() {
		return njService;
	}

	public void setNjService(NIANJIService njService) {
		this.njService = njService;
	}

	public ZhuanYeService getZyService() {
		return zyService;
	}

	public void setZyService(ZhuanYeService zyService) {
		this.zyService = zyService;
	}

	public StudentService getStuService() {
		return stuService;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}

	public String getXSXH() {
		return XSXH;
	}

	public void setXSXH(String xSXH) {
		XSXH = xSXH;
	}

	public String getZSXM() {
		return ZSXM;
	}

	public void setZSXM(String zSXM) {
		ZSXM = zSXM;
	}

	public String getBJMC() {
		return BJMC;
	}

	public void setBJMC(String bJMC) {
		BJMC = bJMC;
	}

	public String getNJMC() {
		return NJMC;
	}

	public void setNJMC(String nJMC) {
		NJMC = nJMC;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	public String getCQZT() {
		return CQZT;
	}

	public void setCQZT(String cQZT) {
		CQZT = cQZT;
	}

}