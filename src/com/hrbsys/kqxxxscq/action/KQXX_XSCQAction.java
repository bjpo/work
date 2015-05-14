package com.hrbsys.kqxxxscq.action;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.kqxxxscq.service.KQXX_XSCQService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
public class KQXX_XSCQAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private KQXX_XSCQService kqxx_xscqService;
	private String optionflag;
	private String KQXX_XSCQ_ID; 
	private String KCMC;
	private String NJ_ID;
	private String KCB_ID;
	private String LS_ID;
	private String ZHOU;
	private String KESHI_ID;
	private String XSXM;
	private String MS;
	private String XSXH;
	private String XY_ID;
	private Date SKSJ;
	private String BZ;
	private String LSXM;
	private String XS_ID;
	private String XINGQI;
	private String BJ_ID;
	private String KCLB;
	private String JSMC;
	private String ZY_ID;
	private String KCB_FKS_ID;
	private String CQQK;
	private String KSMC;
	private String LSGH;
	private String JS_ID;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listKQXX_XSCQ() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("KCMC", this.KCMC);
		params.put("NJ_ID", this.NJ_ID);
		params.put("KCB_ID", this.KCB_ID);
		params.put("LS_ID", this.LS_ID);
		params.put("ZHOU", this.ZHOU);
		params.put("KESHI_ID", this.KESHI_ID);
		params.put("XSXM", this.XSXM);
		params.put("MS", this.MS);
		params.put("XSXH", this.XSXH);
		params.put("XY_ID", this.XY_ID);
		params.put("BZ", this.BZ);
		params.put("LSXM", this.LSXM);
		params.put("XS_ID", this.XS_ID);
		params.put("XINGQI", this.XINGQI);
		params.put("BJ_ID", this.BJ_ID);
		params.put("KCLB", this.KCLB);
		params.put("JSMC", this.JSMC);
		params.put("ZY_ID", this.ZY_ID);
		params.put("KCB_FKS_ID", this.KCB_FKS_ID);
		params.put("CQQK", this.CQQK);
		params.put("KSMC", this.KSMC);
		params.put("LSGH", this.LSGH);
		params.put("JS_ID", this.JS_ID);
		List<KQXX_XSCQ> list = kqxx_xscqService.findKQXX_XSCQByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", kqxx_xscqService.getCountKQXX_XSCQ(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addKQXX_XSCQ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		KQXX_XSCQ t = new KQXX_XSCQ();
		t.setKQXX_XSCQ_ID(UUIDTools.getUUID());
		t.setKCMC(this.getKCMC());
		t.setNJ_ID(this.getNJ_ID());
		t.setKCB_ID(this.getKCB_ID());
		t.setLS_ID(this.getLS_ID());
		t.setZHOU(this.getZHOU());
		t.setKESHI_ID(this.getKESHI_ID());
		t.setXSXM(this.getXSXM());
		t.setMS(this.getMS());
		t.setXSXH(this.getXSXH());
		t.setXY_ID(this.getXY_ID());
		t.setSKSJ(this.getSKSJ());
		t.setBZ(this.getBZ());
		t.setLSXM(this.getLSXM());
		t.setXS_ID(this.getXS_ID());
		t.setXINGQI(this.getXINGQI());
		t.setBJ_ID(this.getBJ_ID());
		t.setKCLB(this.getKCLB());
		t.setJSMC(this.getJSMC());
		t.setZY_ID(this.getZY_ID());
		t.setKCB_FKS_ID(this.getKCB_FKS_ID());
		t.setCQQK(this.getCQQK());
		t.setKSMC(this.getKSMC());
		t.setLSGH(this.getLSGH());
		t.setJS_ID(this.getJS_ID());
		if (kqxx_xscqService.addKQXX_XSCQ(t)) {
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

	public void delKQXX_XSCQ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != KQXX_XSCQ_ID) && !"".equals(KQXX_XSCQ_ID)) {
			if (kqxx_xscqService.delKQXX_XSCQ(KQXX_XSCQ_ID)) {
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

	public void updateKQXX_XSCQ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(KQXX_XSCQ_ID) || null == KQXX_XSCQ_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		KQXX_XSCQ t = new KQXX_XSCQ();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateKQXX_XSCQ".equals(optionflag)) {
				t.setKQXX_XSCQ_ID(KQXX_XSCQ_ID);
				t.setKCMC(this.getKCMC());
				t.setNJ_ID(this.getNJ_ID());
				t.setKCB_ID(this.getKCB_ID());
				t.setLS_ID(this.getLS_ID());
				t.setZHOU(this.getZHOU());
				t.setKESHI_ID(this.getKESHI_ID());
				t.setXSXM(this.getXSXM());
				t.setMS(this.getMS());
				t.setXSXH(this.getXSXH());
				t.setXY_ID(this.getXY_ID());
				t.setSKSJ(this.getSKSJ());
				t.setBZ(this.getBZ());
				t.setLSXM(this.getLSXM());
				t.setXS_ID(this.getXS_ID());
				t.setXINGQI(this.getXINGQI());
				t.setBJ_ID(this.getBJ_ID());
				t.setKCLB(this.getKCLB());
				t.setJSMC(this.getJSMC());
				t.setZY_ID(this.getZY_ID());
				t.setKCB_FKS_ID(this.getKCB_FKS_ID());
				t.setCQQK(this.getCQQK());
				t.setKSMC(this.getKSMC());
				t.setLSGH(this.getLSGH());
				t.setJS_ID(this.getJS_ID());
				if (kqxx_xscqService.updateKQXX_XSCQ(t)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改信息成功！");
					new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改信息失败！");
					new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(kqxx_xscqService.findKQXX_XSCQ(KQXX_XSCQ_ID),config));
	}

	public void listAllKQXX_XSCQ() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("KCMC", this.KCMC);
		params.put("NJ_ID", this.NJ_ID);
		params.put("KCB_ID", this.KCB_ID);
		params.put("LS_ID", this.LS_ID);
		params.put("ZHOU", this.ZHOU);
		params.put("KESHI_ID", this.KESHI_ID);
		params.put("XSXM", this.XSXM);
		params.put("MS", this.MS);
		params.put("XSXH", this.XSXH);
		params.put("XY_ID", this.XY_ID);
		params.put("BZ", this.BZ);
		params.put("LSXM", this.LSXM);
		params.put("XS_ID", this.XS_ID);
		params.put("XINGQI", this.XINGQI);
		params.put("BJ_ID", this.BJ_ID);
		params.put("KCLB", this.KCLB);
		params.put("JSMC", this.JSMC);
		params.put("ZY_ID", this.ZY_ID);
		params.put("KCB_FKS_ID", this.KCB_FKS_ID);
		params.put("CQQK", this.CQQK);
		params.put("KSMC", this.KSMC);
		params.put("LSGH", this.LSGH);
		params.put("JS_ID", this.JS_ID);
		List<KQXX_XSCQ> list = kqxx_xscqService.findKQXX_XSCQByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getKCMC() {
			return KCMC;
		}
		public void setKCMC(String KCMC) {
			this.KCMC = KCMC;
		}

		public String getNJ_ID() {
			return NJ_ID;
		}
		public void setNJ_ID(String NJ_ID) {
			this.NJ_ID = NJ_ID;
		}

		public String getKCB_ID() {
			return KCB_ID;
		}
		public void setKCB_ID(String KCB_ID) {
			this.KCB_ID = KCB_ID;
		}

		public String getLS_ID() {
			return LS_ID;
		}
		public void setLS_ID(String LS_ID) {
			this.LS_ID = LS_ID;
		}

		public String getZHOU() {
			return ZHOU;
		}
		public void setZHOU(String ZHOU) {
			this.ZHOU = ZHOU;
		}

		public String getKESHI_ID() {
			return KESHI_ID;
		}
		public void setKESHI_ID(String KESHI_ID) {
			this.KESHI_ID = KESHI_ID;
		}

		public String getXSXM() {
			return XSXM;
		}
		public void setXSXM(String XSXM) {
			this.XSXM = XSXM;
		}

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getXSXH() {
			return XSXH;
		}
		public void setXSXH(String XSXH) {
			this.XSXH = XSXH;
		}

		public String getXY_ID() {
			return XY_ID;
		}
		public void setXY_ID(String XY_ID) {
			this.XY_ID = XY_ID;
		}

		public Date getSKSJ() {
			return SKSJ;
		}
		public void setSKSJ(Date SKSJ) {
			this.SKSJ = SKSJ;
		}

		public String getBZ() {
			return BZ;
		}
		public void setBZ(String BZ) {
			this.BZ = BZ;
		}

		public String getLSXM() {
			return LSXM;
		}
		public void setLSXM(String LSXM) {
			this.LSXM = LSXM;
		}

		public String getXS_ID() {
			return XS_ID;
		}
		public void setXS_ID(String XS_ID) {
			this.XS_ID = XS_ID;
		}

		public String getXINGQI() {
			return XINGQI;
		}
		public void setXINGQI(String XINGQI) {
			this.XINGQI = XINGQI;
		}

		public String getBJ_ID() {
			return BJ_ID;
		}
		public void setBJ_ID(String BJ_ID) {
			this.BJ_ID = BJ_ID;
		}

		public String getKCLB() {
			return KCLB;
		}
		public void setKCLB(String KCLB) {
			this.KCLB = KCLB;
		}

		public String getJSMC() {
			return JSMC;
		}
		public void setJSMC(String JSMC) {
			this.JSMC = JSMC;
		}

		public String getZY_ID() {
			return ZY_ID;
		}
		public void setZY_ID(String ZY_ID) {
			this.ZY_ID = ZY_ID;
		}

		public String getKCB_FKS_ID() {
			return KCB_FKS_ID;
		}
		public void setKCB_FKS_ID(String KCB_FKS_ID) {
			this.KCB_FKS_ID = KCB_FKS_ID;
		}

		public String getCQQK() {
			return CQQK;
		}
		public void setCQQK(String CQQK) {
			this.CQQK = CQQK;
		}

		public String getKSMC() {
			return KSMC;
		}
		public void setKSMC(String KSMC) {
			this.KSMC = KSMC;
		}

		public String getLSGH() {
			return LSGH;
		}
		public void setLSGH(String LSGH) {
			this.LSGH = LSGH;
		}

		public String getJS_ID() {
			return JS_ID;
		}
		public void setJS_ID(String JS_ID) {
			this.JS_ID = JS_ID;
		}

		public KQXX_XSCQService getKQXX_XSCQ() {
			return kqxx_xscqService;
		}

		public void setkqxx_xscqService(KQXX_XSCQService kqxx_xscqService) {
			this.kqxx_xscqService = kqxx_xscqService;
		}

		public String getKQXX_XSCQ_ID() {
			return KQXX_XSCQ_ID;
		}
		public void setKQXX_XSCQ_ID(String KQXX_XSCQ_ID) {
			this.KQXX_XSCQ_ID = KQXX_XSCQ_ID;
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
}