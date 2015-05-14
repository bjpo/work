package com.hrbsys.ckxx.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.IfFunc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.CKXX;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KCBBJ;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KECHENGXXLB;
import com.hrbsys.ckxx.service.CKXXService;
import com.hrbsys.jiaogong.service.JIAOGONGService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class CKXXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	/** 输出 **/
	private CKXXService ckxxService;
	private JIAOGONGService jgService;

	private String optionflag;
	private String CKXX_ID;
	private String XINGQI_CD;
	private String KSMC_CD;
	private String LAOSHI_XM;
	private String KCB_ID;
	private String KCB_KCMC;
	private String LAOSHI_ID;
	private String LAOSHI_ID_CD;
	private String JS_ID_CD;
	private String ZHOU;
	private String KCB_KCMC_CD;
	private String MS;
	private String LAOSHI_GH;
	private String BZ;
	private String KS_ID;
	private String LAOSHI_GH_CD;
	private String XINGQI;
	private String ZHOU_CD;
	private String JSMC_CD;
	private String JSMC;
	private String KS_ID_CD;
	private String LAOSHI_XM_CD;
	private String KSMC;
	private String KCB_ID_CD;
	private String JS_ID;

	/** 输入 **/
	/* 添加 */
	private String addJsId;// 存放教室下拉列表中选择值的ID
	private String addJgId;// 存放教师下拉列表中选择值的ID
	private String addKcId;// 存放课程下拉列表中选择值的ID
	private String addKsId;// 存放课时下拉列表中选择值的ID

	private String addJsId_CD;// 存放串到的教室下拉列表中选择的值的ID
	private String addJgId_CD;// 存放串到的教师下拉列表中选择的值的ID
	private String addKcId_CD;// 存放串到的课程下拉列表中选择的值的ID
	private String addKsId_CD;// 存放串到的课时下拉列表中选择的值的ID

	private String addJsName;// 存放教室名称下拉列表的值
	private String addJgGh;// 存放老师的工号
	private String addJgName;// 存放老师的名称
	private String addKcName;// 存放课程名称
	private String addKsName;// 存放课时名称

	private String addKcName_CD;// 存放串到的课程名称
	private String addKsName_CD;// 存放串到的课时名称
	private String addJgGh_CD;// 存放串到的教师工号
	private String addJsName_CD;// 存放串到 的教师姓名
	private String addJgName_CD;// 存放串到教室的名称

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;
	private String updateCkxxId;

	public String getUpdateCkxxId() {
		return updateCkxxId;
	}

	public void setUpdateCkxxId(String updateCkxxId) {
		this.updateCkxxId = updateCkxxId;
	}

	/**
	 * 串课信息列表
	 * 
	 * @throws Exception
	 */
	public void listCKXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("XINGQI_CD", this.XINGQI_CD);
		params.put("KSMC_CD", this.KSMC_CD);
		params.put("LAOSHI_XM", this.LAOSHI_XM);
		params.put("KCB_ID", this.KCB_ID);
		params.put("KCB_KCMC", this.KCB_KCMC);
		params.put("LAOSHI_ID", this.LAOSHI_ID);
		params.put("LAOSHI_ID_CD", this.LAOSHI_ID_CD);
		params.put("JS_ID_CD", this.JS_ID_CD);
		params.put("ZHOU", this.ZHOU);
		params.put("KCB_KCMC_CD", this.KCB_KCMC_CD);
		params.put("MS", this.MS);
		params.put("LAOSHI_GH", this.LAOSHI_GH);
		params.put("BZ", this.BZ);
		params.put("KS_ID", this.KS_ID);
		params.put("LAOSHI_GH_CD", this.LAOSHI_GH_CD);
		params.put("XINGQI", this.XINGQI);
		params.put("ZHOU_CD", this.ZHOU_CD);
		params.put("JSMC_CD", this.JSMC_CD);
		params.put("JSMC", this.JSMC);
		params.put("KS_ID_CD", this.KS_ID_CD);
		params.put("LAOSHI_XM_CD", this.LAOSHI_XM_CD);
		params.put("KSMC", this.KSMC);
		params.put("KCB_ID_CD", this.KCB_ID_CD);
		params.put("JS_ID", this.JS_ID);
		List<CKXX> list = ckxxService.findCKXXByPageApp(start, number, params,
				order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", ckxxService.getCountCKXX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	/**
	 * 添加串课信息
	 * 
	 * @throws Exception
	 */
	public void addCKXX() throws Exception {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		CKXX t = new CKXX();
		t.setCKXX_ID(UUIDTools.getUUID());

		t.setKCB_ID(this.getAddKcId());
		t.setKS_ID(this.getAddKsId());
		t.setLAOSHI_ID(this.getAddJgId());
		t.setJS_ID(this.getAddJsId());

		t.setLAOSHI_ID_CD(this.getAddJgId_CD());
		t.setKCB_ID_CD(this.getAddKcId_CD());
		t.setKS_ID_CD(this.getAddKsId_CD());
		t.setJS_ID_CD(this.getAddJsId_CD());

		t.setLAOSHI_XM(URLDecoder.decode(this.getAddJgName(), "UTF-8"));
		t.setKCB_KCMC(URLDecoder.decode(this.getAddKcName(), "UTF-8"));
		t.setJSMC(URLDecoder.decode(this.getAddJsName(), "UTF-8"));
		t.setKSMC(URLDecoder.decode(this.getAddKsName(), "UTF-8"));
		t.setZHOU(this.getZHOU());
		t.setXINGQI(this.getXINGQI());

		t.setKSMC_CD(URLDecoder.decode(this.getAddKsName_CD(), "UTF-8"));
		t.setKCB_KCMC_CD(URLDecoder.decode(this.getAddKcName_CD(), "UTF-8"));
		t.setLAOSHI_GH(URLDecoder.decode(this.getAddJgGh(), "UTF-8"));
		t.setLAOSHI_GH_CD(URLDecoder.decode(this.getAddJgGh_CD(), "UTF-8"));
		t.setXINGQI_CD(this.getXINGQI_CD());
		t.setZHOU_CD(this.getZHOU_CD());
		t.setJSMC_CD(URLDecoder.decode(this.getAddJsName_CD(), "UTF-8"));
		t.setLAOSHI_XM_CD(URLDecoder.decode(this.getAddJgName_CD(), "UTF-8"));

		t.setMS(this.getMS());
		t.setBZ(this.getBZ());

		if (ckxxService.addCKXX(t)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "信息新增成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "信息添加失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	/**
	 * 删除串课信息
	 * 
	 * @throws Exception
	 */
	public void delCKXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != CKXX_ID) && !"".equals(CKXX_ID)) {
			if (ckxxService.delCKXX(CKXX_ID)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	/**
	 * 修改串课信息
	 * 
	 * @throws Exception
	 */
	public void updateCKXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(updateCkxxId) || null == updateCkxxId) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		CKXX t = new CKXX();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateCKXX".equals(optionflag)) {

				t.setCKXX_ID(this.getUpdateCkxxId());
				// t.setKCB_ID(this.getKCB_ID());
				// t.setLAOSHI_ID(this.getLAOSHI_ID());
				// t.setKS_ID(this.getKS_ID());
				t.setJS_ID(this.getJS_ID());

				// t.setLAOSHI_ID_CD(this.getLAOSHI_ID_CD());
				t.setKCB_ID_CD(this.getKCB_ID_CD());
				t.setJS_ID_CD(this.getJS_ID_CD());
				// t.setKS_ID_CD(this.getKS_ID_CD());

				// t.setKSMC_CD(this.getKSMC_CD());
				// t.setKCB_KCMC_CD(this.getKCB_KCMC_CD());
				t.setJSMC_CD(this.getJSMC_CD());

				// String[] ls_gh_cd = this.getLAOSHI_GH_CD().split(",");
				// t.setLAOSHI_GH_CD(URLDecoder.decode(ls_gh_cd[0].toString(),
				// "UTF-8"));
				//
				// String[] ls_xm_cd = this.getLAOSHI_XM_CD().split(",");
				// t.setLAOSHI_XM_CD(URLDecoder.decode(ls_xm_cd[0].toString(),
				// "UTF-8"));

				t.setZHOU_CD(this.getZHOU_CD());
				t.setXINGQI_CD(this.getXINGQI_CD());

				// String[] ls_xm = this.getLAOSHI_XM().split(",");
				// t.setLAOSHI_XM(URLDecoder.decode(ls_xm[0], "UTF-8"));

				// String[] ls_gh = this.getLAOSHI_GH().split(",");
				// t.setLAOSHI_GH(URLDecoder.decode(ls_gh[0], "UTF-8"));

				// t.setKCB_KCMC(this.getKCB_KCMC());
				// t.setKSMC(this.getKSMC());
				t.setZHOU(this.getZHOU());

				t.setXINGQI(this.getXINGQI());
				t.setJSMC(URLDecoder.decode(this.getJSMC(), "UTF-8"));
				t.setMS(this.getMS());
				t.setBZ(this.getBZ());

				// 读取老师工号中存放的老师ID进行查询
				if (null != this.getLAOSHI_GH()
						&& !"".equals(this.getLAOSHI_GH())) {
					String[] ls_gh = this.getLAOSHI_GH().split(",");
					JIAOGONG jg = jgService
							.findJIAOGONG(ls_gh[ls_gh.length - 1].toString()
									.trim());
					if (null != jg && !"".equals(jg)) {
						t.setLAOSHI_XM(jg.getJGMC());
						t.setLAOSHI_GH(jg.getJGGH());
						t.setLAOSHI_ID(jg.getJG_ID());
					}
				}
				// 读取串到的老师工号中存放的ID进行相应的信息
				if (null != this.getLAOSHI_GH_CD()
						&& !"".equals(this.getLAOSHI_GH_CD())) {

					String[] ls_gh = this.getLAOSHI_GH_CD().split(",");
					JIAOGONG jg = jgService
							.findJIAOGONG(ls_gh[ls_gh.length - 1].toString()
									.trim());
					if (null != jg && !"".equals(jg)) {
						t.setLAOSHI_XM_CD(jg.getJGMC());
						t.setLAOSHI_GH_CD(jg.getJGGH());
						t.setLAOSHI_ID_CD(jg.getJG_ID());
					}
				}

				log.info("getKCB_ID()------->" + this.getKCB_ID());
				// 根据课程表ID进行查询相应的信息
				if (null != this.getKCB_ID() && !"".equals(this.getKCB_ID())) {
					KECHENGB kcb = (KECHENGB) ckxxService.findKECHENGB(this
							.getKCB_ID());
					if (null != kcb && !"".equals(kcb)) {
						t.setKS_ID(kcb.getKS_ID());
						t.setKSMC(kcb.getKSMC());
						t.setKCB_KCMC(kcb.getKCXXMC());
					}
				}

				// 根据串到的课程表ID进行查询相应的信息
				if (null != this.getKCB_ID_CD()
						&& !"".equals(this.getKCB_ID_CD())) {
					KECHENGB kcb = (KECHENGB) ckxxService
							.findKECHENGB(getKCB_ID_CD());
					if (null != kcb && !"".equals(kcb)) {
						t.setKS_ID_CD(kcb.getKS_ID());
						t.setKSMC_CD(kcb.getKSMC());
						t.setKCB_KCMC_CD(kcb.getKCXXMC());
					}
				}

				if (ckxxService.updateCKXX(t)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改信息失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(
				ckxxService.findCKXX(updateCkxxId), config));
	}

	public void listAllCKXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("XINGQI_CD", this.XINGQI_CD);
		params.put("KSMC_CD", this.KSMC_CD);
		params.put("LAOSHI_XM", this.LAOSHI_XM);
		params.put("KCB_ID", this.KCB_ID);
		params.put("KCB_KCMC", this.KCB_KCMC);
		params.put("LAOSHI_ID", this.LAOSHI_ID);
		params.put("LAOSHI_ID_CD", this.LAOSHI_ID_CD);
		params.put("JS_ID_CD", this.JS_ID_CD);
		params.put("ZHOU", this.ZHOU);
		params.put("KCB_KCMC_CD", this.KCB_KCMC_CD);
		params.put("MS", this.MS);
		params.put("LAOSHI_GH", this.LAOSHI_GH);
		params.put("BZ", this.BZ);
		params.put("KS_ID", this.KS_ID);
		params.put("LAOSHI_GH_CD", this.LAOSHI_GH_CD);
		params.put("XINGQI", this.XINGQI);
		params.put("ZHOU_CD", this.ZHOU_CD);
		params.put("JSMC_CD", this.JSMC_CD);
		params.put("JSMC", this.JSMC);
		params.put("KS_ID_CD", this.KS_ID_CD);
		params.put("LAOSHI_XM_CD", this.LAOSHI_XM_CD);
		params.put("KSMC", this.KSMC);
		params.put("KCB_ID_CD", this.KCB_ID_CD);
		params.put("JS_ID", this.JS_ID);
		List<CKXX> list = ckxxService.findCKXXByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/**
	 * 查找教工的工号
	 */
	public void findJIAOGONG() {
		List<JIAOGONG> list = ckxxService.findJIAOGONG(this.LAOSHI_ID);
		JIAOGONG jg = new JIAOGONG();
		for (int i = 0; i < list.size(); i++) {
			jg = list.get(i);
		}
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(jg, config));
	}

	public void findKECHENGB() {
		KECHENGB kcb = (KECHENGB) ckxxService.findKECHENGB(this.KCB_ID);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(kcb, config));

	}

	/*********************************************************** get和set方法 ***********************************************************/
	public String getXINGQI_CD() {
		return XINGQI_CD;
	}

	public void setXINGQI_CD(String XINGQI_CD) {
		this.XINGQI_CD = XINGQI_CD;
	}

	public String getKSMC_CD() {
		return KSMC_CD;
	}

	public void setKSMC_CD(String KSMC_CD) {
		this.KSMC_CD = KSMC_CD;
	}

	public String getLAOSHI_XM() {
		return LAOSHI_XM;
	}

	public void setLAOSHI_XM(String LAOSHI_XM) {
		this.LAOSHI_XM = LAOSHI_XM;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String KCB_ID) {
		this.KCB_ID = KCB_ID;
	}

	public String getKCB_KCMC() {
		return KCB_KCMC;
	}

	public void setKCB_KCMC(String KCB_KCMC) {
		this.KCB_KCMC = KCB_KCMC;
	}

	public String getLAOSHI_ID() {
		return LAOSHI_ID;
	}

	public void setLAOSHI_ID(String LAOSHI_ID) {
		this.LAOSHI_ID = LAOSHI_ID;
	}

	public String getLAOSHI_ID_CD() {
		return LAOSHI_ID_CD;
	}

	public void setLAOSHI_ID_CD(String LAOSHI_ID_CD) {
		this.LAOSHI_ID_CD = LAOSHI_ID_CD;
	}

	public String getJS_ID_CD() {
		return JS_ID_CD;
	}

	public void setJS_ID_CD(String JS_ID_CD) {
		this.JS_ID_CD = JS_ID_CD;
	}

	public String getZHOU() {
		return ZHOU;
	}

	public void setZHOU(String ZHOU) {
		this.ZHOU = ZHOU;
	}

	public String getKCB_KCMC_CD() {
		return KCB_KCMC_CD;
	}

	public void setKCB_KCMC_CD(String KCB_KCMC_CD) {
		this.KCB_KCMC_CD = KCB_KCMC_CD;
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getLAOSHI_GH() {
		return LAOSHI_GH;
	}

	public void setLAOSHI_GH(String LAOSHI_GH) {
		this.LAOSHI_GH = LAOSHI_GH;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String KS_ID) {
		this.KS_ID = KS_ID;
	}

	public String getLAOSHI_GH_CD() {
		return LAOSHI_GH_CD;
	}

	public void setLAOSHI_GH_CD(String LAOSHI_GH_CD) {
		this.LAOSHI_GH_CD = LAOSHI_GH_CD;
	}

	public String getXINGQI() {
		return XINGQI;
	}

	public void setXINGQI(String XINGQI) {
		this.XINGQI = XINGQI;
	}

	public String getZHOU_CD() {
		return ZHOU_CD;
	}

	public void setZHOU_CD(String ZHOU_CD) {
		this.ZHOU_CD = ZHOU_CD;
	}

	public String getJSMC_CD() {
		return JSMC_CD;
	}

	public void setJSMC_CD(String JSMC_CD) {
		this.JSMC_CD = JSMC_CD;
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	public String getKS_ID_CD() {
		return KS_ID_CD;
	}

	public void setKS_ID_CD(String KS_ID_CD) {
		this.KS_ID_CD = KS_ID_CD;
	}

	public String getLAOSHI_XM_CD() {
		return LAOSHI_XM_CD;
	}

	public void setLAOSHI_XM_CD(String LAOSHI_XM_CD) {
		this.LAOSHI_XM_CD = LAOSHI_XM_CD;
	}

	public String getKSMC() {
		return KSMC;
	}

	public void setKSMC(String KSMC) {
		this.KSMC = KSMC;
	}

	public String getKCB_ID_CD() {
		return KCB_ID_CD;
	}

	public void setKCB_ID_CD(String KCB_ID_CD) {
		this.KCB_ID_CD = KCB_ID_CD;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	public CKXXService getCKXX() {
		return ckxxService;
	}

	public void setckxxService(CKXXService ckxxService) {
		this.ckxxService = ckxxService;
	}

	public String getCKXX_ID() {
		return CKXX_ID;
	}

	public void setCKXX_ID(String CKXX_ID) {
		this.CKXX_ID = CKXX_ID;
	}

	public String getOptionflag() {
		return optionflag;
	}

	public void setOptionflag(String optionflag) {
		this.optionflag = optionflag;
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

	public String getAddJsName() {
		return addJsName;
	}

	public void setAddJsName(String addJsName) {
		this.addJsName = addJsName;
	}

	public CKXXService getCkxxService() {
		return ckxxService;
	}

	public void setCkxxService(CKXXService ckxxService) {
		this.ckxxService = ckxxService;
	}

	public String getAddJgGh() {
		return addJgGh;
	}

	public void setAddJgGh(String addJgGh) {
		this.addJgGh = addJgGh;
	}

	public String getAddJgName() {
		return addJgName;
	}

	public void setAddJgName(String addJgName) {
		this.addJgName = addJgName;
	}

	public String getAddKcName() {
		return addKcName;
	}

	public void setAddKcName(String addKcName) {
		this.addKcName = addKcName;
	}

	public String getAddKsName() {
		return addKsName;
	}

	public void setAddKsName(String addKsName) {
		this.addKsName = addKsName;
	}

	public String getAddKcName_CD() {
		return addKcName_CD;
	}

	public void setAddKcName_CD(String addKcName_CD) {
		this.addKcName_CD = addKcName_CD;
	}

	public String getAddKsName_CD() {
		return addKsName_CD;
	}

	public void setAddKsName_CD(String addKsName_CD) {
		this.addKsName_CD = addKsName_CD;
	}

	public String getAddJgGh_CD() {
		return addJgGh_CD;
	}

	public void setAddJgGh_CD(String addJgGh_CD) {
		this.addJgGh_CD = addJgGh_CD;
	}

	public String getAddJsName_CD() {
		return addJsName_CD;
	}

	public void setAddJsName_CD(String addJsName_CD) {
		this.addJsName_CD = addJsName_CD;
	}

	public String getAddJgName_CD() {
		return addJgName_CD;
	}

	public void setAddJgName_CD(String addJgName_CD) {
		this.addJgName_CD = addJgName_CD;
	}

	public String getAddJsId() {
		return addJsId;
	}

	public void setAddJsId(String addJsId) {
		this.addJsId = addJsId;
	}

	public String getAddJgId() {
		return addJgId;
	}

	public void setAddJgId(String addJgId) {
		this.addJgId = addJgId;
	}

	public String getAddKcId() {
		return addKcId;
	}

	public void setAddKcId(String addKcId) {
		this.addKcId = addKcId;
	}

	public String getAddKsId() {
		return addKsId;
	}

	public void setAddKsId(String addKsId) {
		this.addKsId = addKsId;
	}

	public String getAddJsId_CD() {
		return addJsId_CD;
	}

	public void setAddJsId_CD(String addJsId_CD) {
		this.addJsId_CD = addJsId_CD;
	}

	public String getAddJgId_CD() {
		return addJgId_CD;
	}

	public void setAddJgId_CD(String addJgId_CD) {
		this.addJgId_CD = addJgId_CD;
	}

	public String getAddKcId_CD() {
		return addKcId_CD;
	}

	public void setAddKcId_CD(String addKcId_CD) {
		this.addKcId_CD = addKcId_CD;
	}

	public String getAddKsId_CD() {
		return addKsId_CD;
	}

	public void setAddKsId_CD(String addKsId_CD) {
		this.addKsId_CD = addKsId_CD;
	}

	public JIAOGONGService getJgService() {
		return jgService;
	}

	public void setJgService(JIAOGONGService jgService) {
		this.jgService = jgService;
	}

}