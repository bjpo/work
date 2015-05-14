package com.hrbsys.keshi.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.KESHI;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.keshi.service.KESHIService;
public class KESHIAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private KESHIService keshiService;
	private String optionflag;
	private String KS_ID; 
	private String MS;
	private String TMP6;
	private String TMP5;
	private String TMP4;
	private String TMP3;
	private String TMP2;
	private String TMP1;
	private String KSSJ;
	private String BZ;
	private String JSSJ;
	private String KSMC;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listKESHI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("MS", this.MS);
		params.put("TMP6", this.TMP6);
		params.put("TMP5", this.TMP5);
		params.put("TMP4", this.TMP4);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("TMP1", this.TMP1);
		params.put("KSSJ", this.KSSJ);
		params.put("BZ", this.BZ);
		params.put("JSSJ", this.JSSJ);
		params.put("KSMC", this.KSMC);
		List<KESHI> list = keshiService.findKESHIByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", keshiService.getCountKESHI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addKESHI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		KESHI t = new KESHI();
		t.setKS_ID(UUIDTools.getUUID());
		t.setMS(this.getMS());
		t.setTMP6(this.getTMP6());
		t.setTMP5(this.getTMP5());
		t.setTMP4(this.getTMP4());
		t.setTMP3(this.getTMP3());
		t.setTMP2(this.getTMP2());
		t.setTMP1(this.getTMP1());
		t.setKSSJ(this.getKSSJ());
		t.setBZ(this.getBZ());
		t.setJSSJ(this.getJSSJ());
		t.setKSMC(this.getKSMC());
		if (keshiService.addKESHI(t)) {
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

	public void delKESHI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != KS_ID) && !"".equals(KS_ID)) {
			if (keshiService.delKESHI(KS_ID)) {
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

	public void updateKESHI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(KS_ID) || null == KS_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		KESHI t = new KESHI();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateKESHI".equals(optionflag)) {
				t.setKS_ID(KS_ID);
				t.setMS(this.getMS());
				t.setTMP6(this.getTMP6());
				t.setTMP5(this.getTMP5());
				t.setTMP4(this.getTMP4());
				t.setTMP3(this.getTMP3());
				t.setTMP2(this.getTMP2());
				t.setTMP1(this.getTMP1());
				t.setKSSJ(this.getKSSJ());
				t.setBZ(this.getBZ());
				t.setJSSJ(this.getJSSJ());
				t.setKSMC(this.getKSMC());
				if (keshiService.updateKESHI(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(keshiService.findKESHI(KS_ID),config));
	}

	public void listAllKESHI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("MS", this.MS);
		params.put("TMP6", this.TMP6);
		params.put("TMP5", this.TMP5);
		params.put("TMP4", this.TMP4);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("TMP1", this.TMP1);
		params.put("KSSJ", this.KSSJ);
		params.put("BZ", this.BZ);
		params.put("JSSJ", this.JSSJ);
		params.put("KSMC", this.KSMC);
		List<KESHI> list = keshiService.findKESHIByPageApp(params, order, sort);// 每页的数据，放入list
		KESHI k=new KESHI();
		k.setKSMC("全部课时");
		list.add(k);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getTMP6() {
			return TMP6;
		}
		public void setTMP6(String TMP6) {
			this.TMP6 = TMP6;
		}

		public String getTMP5() {
			return TMP5;
		}
		public void setTMP5(String TMP5) {
			this.TMP5 = TMP5;
		}

		public String getTMP4() {
			return TMP4;
		}
		public void setTMP4(String TMP4) {
			this.TMP4 = TMP4;
		}

		public String getTMP3() {
			return TMP3;
		}
		public void setTMP3(String TMP3) {
			this.TMP3 = TMP3;
		}

		public String getTMP2() {
			return TMP2;
		}
		public void setTMP2(String TMP2) {
			this.TMP2 = TMP2;
		}

		public String getTMP1() {
			return TMP1;
		}
		public void setTMP1(String TMP1) {
			this.TMP1 = TMP1;
		}

		public String getKSSJ() {
			return KSSJ;
		}
		public void setKSSJ(String KSSJ) {
			this.KSSJ = KSSJ;
		}

		public String getBZ() {
			return BZ;
		}
		public void setBZ(String BZ) {
			this.BZ = BZ;
		}

		public String getJSSJ() {
			return JSSJ;
		}
		public void setJSSJ(String JSSJ) {
			this.JSSJ = JSSJ;
		}

		public String getKSMC() {
			return KSMC;
		}
		public void setKSMC(String KSMC) {
			this.KSMC = KSMC;
		}

		public KESHIService getKESHI() {
			return keshiService;
		}

		public void setkeshiService(KESHIService keshiService) {
			this.keshiService = keshiService;
		}

		public String getKS_ID() {
			return KS_ID;
		}
		public void setKS_ID(String KS_ID) {
			this.KS_ID = KS_ID;
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