package com.hrbsys.kqxxbjxx.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.KQXX_BJXX;
import com.hrbsys.kqxxbjxx.service.KQXX_BJXXService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
public class KQXX_BJXXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private KQXX_BJXXService kqxx_bjxxService;
	private String optionflag;
	private String KQXX_BJXX_ID; 
	private Float CQL;
	private Integer ZTRS;
	private String KCMC;
	private Integer ZCCXRS;
	private Float QXL;
	private String KCB_ID;
	private String LS_ID;
	private String ZHOU;
	private String KESHI_ID;
	private String MS;
	private String SKSJ;
	private String BZ;
	private String LSXM;
	private Float CDL;
	private String XINGQI;
	private Integer SJSKRS;
	private String KCLB;
	private String JSMC;
	private Integer CDRS;
	private String KCB_FKS_ID;
	private Float ZTL;
	private String KSMC;
	private String LSGH;
	private Integer QXRS;
	private Integer YSKRS;
	private String JS_ID;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listKQXX_BJXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("KCMC", this.KCMC);
		params.put("KCB_ID", this.KCB_ID);
		params.put("LS_ID", this.LS_ID);
		params.put("ZHOU", this.ZHOU);
		params.put("KESHI_ID", this.KESHI_ID);
		params.put("MS", this.MS);
		params.put("SKSJ", this.SKSJ);
		params.put("BZ", this.BZ);
		params.put("LSXM", this.LSXM);
		params.put("XINGQI", this.XINGQI);
		params.put("KCLB", this.KCLB);
		params.put("JSMC", this.JSMC);
		params.put("KCB_FKS_ID", this.KCB_FKS_ID);
		params.put("KSMC", this.KSMC);
		params.put("LSGH", this.LSGH);
		params.put("JS_ID", this.JS_ID);
		List<KQXX_BJXX> list = kqxx_bjxxService.findKQXX_BJXXByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", kqxx_bjxxService.getCountKQXX_BJXX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addKQXX_BJXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		KQXX_BJXX t = new KQXX_BJXX();
		t.setKQXX_BJXX_ID(UUIDTools.getUUID());
		t.setCQL(this.getCQL());
		t.setZTRS(this.getZTRS());
		t.setKCMC(this.getKCMC());
		t.setZCCXRS(this.getZCCXRS());
		t.setQXL(this.getQXL());
		t.setKCB_ID(this.getKCB_ID());
		t.setLS_ID(this.getLS_ID());
		t.setZHOU(this.getZHOU());
		t.setKESHI_ID(this.getKESHI_ID());
		t.setMS(this.getMS());
		t.setSKSJ(this.getSKSJ());
		t.setBZ(this.getBZ());
		t.setLSXM(this.getLSXM());
		t.setCDL(this.getCDL());
		t.setXINGQI(this.getXINGQI());
		t.setSJSKRS(this.getSJSKRS());
		t.setKCLB(this.getKCLB());
		t.setJSMC(this.getJSMC());
		t.setCDRS(this.getCDRS());
		t.setKCB_FKS_ID(this.getKCB_FKS_ID());
		t.setZTL(this.getZTL());
		t.setKSMC(this.getKSMC());
		t.setLSGH(this.getLSGH());
		t.setQXRS(this.getQXRS());
		t.setYSKRS(this.getYSKRS());
		t.setJS_ID(this.getJS_ID());
		if (kqxx_bjxxService.addKQXX_BJXX(t)) {
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

	public void delKQXX_BJXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != KQXX_BJXX_ID) && !"".equals(KQXX_BJXX_ID)) {
			if (kqxx_bjxxService.delKQXX_BJXX(KQXX_BJXX_ID)) {
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

	public void updateKQXX_BJXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(KQXX_BJXX_ID) || null == KQXX_BJXX_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		KQXX_BJXX t = new KQXX_BJXX();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateKQXX_BJXX".equals(optionflag)) {
				t.setKQXX_BJXX_ID(KQXX_BJXX_ID);
				t.setCQL(this.getCQL());
				t.setZTRS(this.getZTRS());
				t.setKCMC(this.getKCMC());
				t.setZCCXRS(this.getZCCXRS());
				t.setQXL(this.getQXL());
				t.setKCB_ID(this.getKCB_ID());
				t.setLS_ID(this.getLS_ID());
				t.setZHOU(this.getZHOU());
				t.setKESHI_ID(this.getKESHI_ID());
				t.setMS(this.getMS());
				t.setSKSJ(this.getSKSJ());
				t.setBZ(this.getBZ());
				t.setLSXM(this.getLSXM());
				t.setCDL(this.getCDL());
				t.setXINGQI(this.getXINGQI());
				t.setSJSKRS(this.getSJSKRS());
				t.setKCLB(this.getKCLB());
				t.setJSMC(this.getJSMC());
				t.setCDRS(this.getCDRS());
				t.setKCB_FKS_ID(this.getKCB_FKS_ID());
				t.setZTL(this.getZTL());
				t.setKSMC(this.getKSMC());
				t.setLSGH(this.getLSGH());
				t.setQXRS(this.getQXRS());
				t.setYSKRS(this.getYSKRS());
				t.setJS_ID(this.getJS_ID());
				if (kqxx_bjxxService.updateKQXX_BJXX(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(kqxx_bjxxService.findKQXX_BJXX(KQXX_BJXX_ID),config));
	}

	public void listAllKQXX_BJXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("KCMC", this.KCMC);
		params.put("KCB_ID", this.KCB_ID);
		params.put("LS_ID", this.LS_ID);
		params.put("ZHOU", this.ZHOU);
		params.put("KESHI_ID", this.KESHI_ID);
		params.put("MS", this.MS);
		params.put("SKSJ", this.SKSJ);
		params.put("BZ", this.BZ);
		params.put("LSXM", this.LSXM);
		params.put("XINGQI", this.XINGQI);
		params.put("KCLB", this.KCLB);
		params.put("JSMC", this.JSMC);
		params.put("KCB_FKS_ID", this.KCB_FKS_ID);
		params.put("KSMC", this.KSMC);
		params.put("LSGH", this.LSGH);
		params.put("JS_ID", this.JS_ID);
		List<KQXX_BJXX> list = kqxx_bjxxService.findKQXX_BJXXByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public Float getCQL() {
			return CQL;
		}
		public void setCQL(Float CQL) {
			this.CQL = CQL;
		}

		public Integer getZTRS() {
			return ZTRS;
		}
		public void setZTRS(Integer ZTRS) {
			this.ZTRS = ZTRS;
		}

		public String getKCMC() {
			return KCMC;
		}
		public void setKCMC(String KCMC) {
			this.KCMC = KCMC;
		}

		public Integer getZCCXRS() {
			return ZCCXRS;
		}
		public void setZCCXRS(Integer ZCCXRS) {
			this.ZCCXRS = ZCCXRS;
		}

		public Float getQXL() {
			return QXL;
		}
		public void setQXL(Float QXL) {
			this.QXL = QXL;
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

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getSKSJ() {
			return SKSJ;
		}
		public void setSKSJ(String SKSJ) {
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

		public Float getCDL() {
			return CDL;
		}
		public void setCDL(Float CDL) {
			this.CDL = CDL;
		}

		public String getXINGQI() {
			return XINGQI;
		}
		public void setXINGQI(String XINGQI) {
			this.XINGQI = XINGQI;
		}

		public Integer getSJSKRS() {
			return SJSKRS;
		}
		public void setSJSKRS(Integer SJSKRS) {
			this.SJSKRS = SJSKRS;
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

		public Integer getCDRS() {
			return CDRS;
		}
		public void setCDRS(Integer CDRS) {
			this.CDRS = CDRS;
		}

		public String getKCB_FKS_ID() {
			return KCB_FKS_ID;
		}
		public void setKCB_FKS_ID(String KCB_FKS_ID) {
			this.KCB_FKS_ID = KCB_FKS_ID;
		}

		public Float getZTL() {
			return ZTL;
		}
		public void setZTL(Float ZTL) {
			this.ZTL = ZTL;
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

		public Integer getQXRS() {
			return QXRS;
		}
		public void setQXRS(Integer QXRS) {
			this.QXRS = QXRS;
		}

		public Integer getYSKRS() {
			return YSKRS;
		}
		public void setYSKRS(Integer YSKRS) {
			this.YSKRS = YSKRS;
		}

		public String getJS_ID() {
			return JS_ID;
		}
		public void setJS_ID(String JS_ID) {
			this.JS_ID = JS_ID;
		}

		public KQXX_BJXXService getKQXX_BJXX() {
			return kqxx_bjxxService;
		}

		public void setkqxx_bjxxService(KQXX_BJXXService kqxx_bjxxService) {
			this.kqxx_bjxxService = kqxx_bjxxService;
		}

		public String getKQXX_BJXX_ID() {
			return KQXX_BJXX_ID;
		}
		public void setKQXX_BJXX_ID(String KQXX_BJXX_ID) {
			this.KQXX_BJXX_ID = KQXX_BJXX_ID;
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