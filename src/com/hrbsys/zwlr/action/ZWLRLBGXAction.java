package com.hrbsys.zwlr.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.From;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.ZWLRLBGX;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.zwlr.service.ZWLRLBGXService;
public class ZWLRLBGXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private ZWLRLBGXService zwlrlbgxService;
	private String optionflag;
	private String ZWLRGX_ID; 
	private String ZWLRBIAO_TMP4;
	private String ZWLRBIAO_TMP3;
	private String ZWLRBIAO_TMP2;
	private String ZWLRBIAO_TMP1;
	private String ZWLRBIAO;
	private String ZWLR_MC;
	private String ZWLRBIAO_ID;
	private String ZWLRBIAO_MC;
	private String ZWLRBIAO_TMP9;
	private String ZWLRBIAO_HM;
	private String ZWLRBIAO_TMP8;
	private String ZWLRBIAO_TMP7;
	private String ZWLRBIAO_TMP6;
	private int PAIXU;
	private String ZWLRBIAO_TMP5;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listZWLRLBGX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("ZWLRBIAO_TMP4", this.ZWLRBIAO_TMP4);
		params.put("ZWLRBIAO_TMP3", this.ZWLRBIAO_TMP3);
		params.put("ZWLRBIAO_TMP2", this.ZWLRBIAO_TMP2);
		params.put("ZWLRBIAO_TMP1", this.ZWLRBIAO_TMP1);
		params.put("ZWLRBIAO", this.ZWLRBIAO);
		params.put("ZWLR_MC", this.ZWLR_MC);
		params.put("ZWLRBIAO_ID", this.ZWLRBIAO_ID);
		params.put("ZWLRBIAO_MC", this.ZWLRBIAO_MC);
		params.put("ZWLRBIAO_TMP9", this.ZWLRBIAO_TMP9);
		params.put("ZWLRBIAO_HM", this.ZWLRBIAO_HM);
		params.put("ZWLRBIAO_TMP8", this.ZWLRBIAO_TMP8);
		params.put("ZWLRBIAO_TMP7", this.ZWLRBIAO_TMP7);
		params.put("ZWLRBIAO_TMP6", this.ZWLRBIAO_TMP6);
//		params.put("PAIXU", this.PAIXU);
		params.put("ZWLRBIAO_TMP5", this.ZWLRBIAO_TMP5);
		List<ZWLRLBGX> list = zwlrlbgxService.findZWLRLBGXByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", zwlrlbgxService.getCountZWLRLBGX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addZWLRLBGX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ZWLRLBGX t = new ZWLRLBGX();
		t.setZWLRGX_ID(UUIDTools.getUUID());
		t.setZWLRBIAO_TMP4(this.getZWLRBIAO_TMP4());
		t.setZWLRBIAO_TMP3(this.getZWLRBIAO_TMP3());
		t.setZWLRBIAO_TMP2(this.getZWLRBIAO_TMP2());
		t.setZWLRBIAO_TMP1(this.getZWLRBIAO_TMP1());
		t.setZWLRBIAO(this.getZWLRBIAO());
		t.setZWLR_MC(this.getZWLR_MC());
		t.setZWLRBIAO_ID(this.getZWLRBIAO_ID());
		t.setZWLRBIAO_MC(this.getZWLRBIAO_MC());
		t.setZWLRBIAO_TMP9(this.getZWLRBIAO_TMP9());
		t.setZWLRBIAO_HM(this.getZWLRBIAO_HM());
		t.setZWLRBIAO_TMP8(this.getZWLRBIAO_TMP8());
		t.setZWLRBIAO_TMP7(this.getZWLRBIAO_TMP7());
		t.setZWLRBIAO_TMP6(this.getZWLRBIAO_TMP6());
		t.setPAIXU(this.getPAIXU());
		t.setZWLRBIAO_TMP5(this.getZWLRBIAO_TMP5());
		if (zwlrlbgxService.addZWLRLBGX(t)) {
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

	public void delZWLRLBGX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != ZWLRGX_ID) && !"".equals(ZWLRGX_ID)) {
			if (zwlrlbgxService.delZWLRLBGX(ZWLRGX_ID)) {
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

	public void updateZWLRLBGX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(ZWLRGX_ID) || null == ZWLRGX_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		ZWLRLBGX t = new ZWLRLBGX();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateZWLRLBGX".equals(optionflag)) {
				t.setZWLRGX_ID(ZWLRGX_ID);
				t.setZWLRBIAO_TMP4(this.getZWLRBIAO_TMP4());
				t.setZWLRBIAO_TMP3(this.getZWLRBIAO_TMP3());
				t.setZWLRBIAO_TMP2(this.getZWLRBIAO_TMP2());
				t.setZWLRBIAO_TMP1(this.getZWLRBIAO_TMP1());
				t.setZWLRBIAO(this.getZWLRBIAO());
				t.setZWLR_MC(this.getZWLR_MC());
				t.setZWLRBIAO_ID(this.getZWLRBIAO_ID());
				t.setZWLRBIAO_MC(this.getZWLRBIAO_MC());
				t.setZWLRBIAO_TMP9(this.getZWLRBIAO_TMP9());
				t.setZWLRBIAO_HM(this.getZWLRBIAO_HM());
				t.setZWLRBIAO_TMP8(this.getZWLRBIAO_TMP8());
				t.setZWLRBIAO_TMP7(this.getZWLRBIAO_TMP7());
				t.setZWLRBIAO_TMP6(this.getZWLRBIAO_TMP6());
				t.setPAIXU(this.getPAIXU());
				t.setZWLRBIAO_TMP5(this.getZWLRBIAO_TMP5());
				if (zwlrlbgxService.updateZWLRLBGX(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(zwlrlbgxService.findZWLRLBGX(ZWLRGX_ID),config));
	}

	public void listAllZWLRLBGX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ZWLRBIAO_TMP4", this.ZWLRBIAO_TMP4);
		params.put("ZWLRBIAO_TMP3", this.ZWLRBIAO_TMP3);
		params.put("ZWLRBIAO_TMP2", this.ZWLRBIAO_TMP2);
		params.put("ZWLRBIAO_TMP1", this.ZWLRBIAO_TMP1);
		params.put("ZWLRBIAO", this.ZWLRBIAO);
		params.put("ZWLR_MC", this.ZWLR_MC);
		params.put("ZWLRBIAO_ID", this.ZWLRBIAO_ID);
		params.put("ZWLRBIAO_MC", this.ZWLRBIAO_MC);
		params.put("ZWLRBIAO_TMP9", this.ZWLRBIAO_TMP9);
		params.put("ZWLRBIAO_HM", this.ZWLRBIAO_HM);
		params.put("ZWLRBIAO_TMP8", this.ZWLRBIAO_TMP8);
		params.put("ZWLRBIAO_TMP7", this.ZWLRBIAO_TMP7);
		params.put("ZWLRBIAO_TMP6", this.ZWLRBIAO_TMP6);
//		params.put("PAIXU", this.PAIXU);
		params.put("ZWLRBIAO_TMP5", this.ZWLRBIAO_TMP5);
		List<ZWLRLBGX> list = zwlrlbgxService.findZWLRLBGXByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getZWLRBIAO_TMP4() {
			return ZWLRBIAO_TMP4;
		}
		public void setZWLRBIAO_TMP4(String ZWLRBIAO_TMP4) {
			this.ZWLRBIAO_TMP4 = ZWLRBIAO_TMP4;
		}

		public String getZWLRBIAO_TMP3() {
			return ZWLRBIAO_TMP3;
		}
		public void setZWLRBIAO_TMP3(String ZWLRBIAO_TMP3) {
			this.ZWLRBIAO_TMP3 = ZWLRBIAO_TMP3;
		}

		public String getZWLRBIAO_TMP2() {
			return ZWLRBIAO_TMP2;
		}
		public void setZWLRBIAO_TMP2(String ZWLRBIAO_TMP2) {
			this.ZWLRBIAO_TMP2 = ZWLRBIAO_TMP2;
		}

		public String getZWLRBIAO_TMP1() {
			return ZWLRBIAO_TMP1;
		}
		public void setZWLRBIAO_TMP1(String ZWLRBIAO_TMP1) {
			this.ZWLRBIAO_TMP1 = ZWLRBIAO_TMP1;
		}

		public String getZWLRBIAO() {
			return ZWLRBIAO;
		}
		public void setZWLRBIAO(String ZWLRBIAO) {
			this.ZWLRBIAO = ZWLRBIAO;
		}

		public String getZWLR_MC() {
			return ZWLR_MC;
		}
		public void setZWLR_MC(String ZWLR_MC) {
			this.ZWLR_MC = ZWLR_MC;
		}

		public String getZWLRBIAO_ID() {
			return ZWLRBIAO_ID;
		}
		public void setZWLRBIAO_ID(String ZWLRBIAO_ID) {
			this.ZWLRBIAO_ID = ZWLRBIAO_ID;
		}

		public String getZWLRBIAO_MC() {
			return ZWLRBIAO_MC;
		}
		public void setZWLRBIAO_MC(String ZWLRBIAO_MC) {
			this.ZWLRBIAO_MC = ZWLRBIAO_MC;
		}

		public String getZWLRBIAO_TMP9() {
			return ZWLRBIAO_TMP9;
		}
		public void setZWLRBIAO_TMP9(String ZWLRBIAO_TMP9) {
			this.ZWLRBIAO_TMP9 = ZWLRBIAO_TMP9;
		}

		public String getZWLRBIAO_HM() {
			return ZWLRBIAO_HM;
		}
		public void setZWLRBIAO_HM(String ZWLRBIAO_HM) {
			this.ZWLRBIAO_HM = ZWLRBIAO_HM;
		}

		public String getZWLRBIAO_TMP8() {
			return ZWLRBIAO_TMP8;
		}
		public void setZWLRBIAO_TMP8(String ZWLRBIAO_TMP8) {
			this.ZWLRBIAO_TMP8 = ZWLRBIAO_TMP8;
		}

		public String getZWLRBIAO_TMP7() {
			return ZWLRBIAO_TMP7;
		}
		public void setZWLRBIAO_TMP7(String ZWLRBIAO_TMP7) {
			this.ZWLRBIAO_TMP7 = ZWLRBIAO_TMP7;
		}

		public String getZWLRBIAO_TMP6() {
			return ZWLRBIAO_TMP6;
		}
		public void setZWLRBIAO_TMP6(String ZWLRBIAO_TMP6) {
			this.ZWLRBIAO_TMP6 = ZWLRBIAO_TMP6;
		}

		public int getPAIXU() {
			return PAIXU;
		}
		public void setPAIXU(int PAIXU) {
			this.PAIXU = PAIXU;
		}

		public String getZWLRBIAO_TMP5() {
			return ZWLRBIAO_TMP5;
		}
		public void setZWLRBIAO_TMP5(String ZWLRBIAO_TMP5) {
			this.ZWLRBIAO_TMP5 = ZWLRBIAO_TMP5;
		}

		public ZWLRLBGXService getZWLRLBGX() {
			return zwlrlbgxService;
		}

		public void setzwlrlbgxService(ZWLRLBGXService zwlrlbgxService) {
			this.zwlrlbgxService = zwlrlbgxService;
		}

		public String getZWLRGX_ID() {
			return ZWLRGX_ID;
		}
		public void setZWLRGX_ID(String ZWLRGX_ID) {
			this.ZWLRGX_ID = ZWLRGX_ID;
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