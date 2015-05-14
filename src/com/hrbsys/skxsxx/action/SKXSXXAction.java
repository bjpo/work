package com.hrbsys.skxsxx.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.SKXSXX;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.skxsxx.service.SKXSXXService;
public class SKXSXXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private SKXSXXService skxsxxService;
	private String optionflag;
	private String KCXS_ID; 
	private String KCB_KCXXMC;
	private String XSXM;
	private String XUEHAO;
	private String KCB_ID;
	private String XS_ID;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listSKXSXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("KCB_KCXXMC", this.KCB_KCXXMC);
		params.put("XSXM", this.XSXM);
		params.put("XUEHAO", this.XUEHAO);
		params.put("KCB_ID", this.KCB_ID);
		params.put("XS_ID", this.XS_ID);
		List<SKXSXX> list = skxsxxService.findSKXSXXByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", skxsxxService.getCountSKXSXX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addSKXSXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		SKXSXX t = new SKXSXX();
		t.setKCXS_ID(UUIDTools.getUUID());
		t.setKCB_KCXXMC(this.getKCB_KCXXMC());
		t.setXSXM(this.getXSXM());
		t.setXUEHAO(this.getXUEHAO());
		t.setKCB_ID(this.getKCB_ID());
		t.setXS_ID(this.getXS_ID());
		if (skxsxxService.addSKXSXX(t)) {
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

	public void delSKXSXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != KCXS_ID) && !"".equals(KCXS_ID)) {
			if (skxsxxService.delSKXSXX(KCXS_ID)) {
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

	public void updateSKXSXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(KCXS_ID) || null == KCXS_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		SKXSXX t = new SKXSXX();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateSKXSXX".equals(optionflag)) {
				t.setKCXS_ID(KCXS_ID);
				t.setKCB_KCXXMC(this.getKCB_KCXXMC());
				t.setXSXM(this.getXSXM());
				t.setXUEHAO(this.getXUEHAO());
				t.setKCB_ID(this.getKCB_ID());
				t.setXS_ID(this.getXS_ID());
				if (skxsxxService.updateSKXSXX(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(skxsxxService.findSKXSXX(KCXS_ID),config));
	}

	public void listAllSKXSXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("KCB_KCXXMC", this.KCB_KCXXMC);
		params.put("XSXM", this.XSXM);
		params.put("XUEHAO", this.XUEHAO);
		params.put("KCB_ID", this.KCB_ID);
		params.put("XS_ID", this.XS_ID);
		List<SKXSXX> list = skxsxxService.findSKXSXXByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getKCB_KCXXMC() {
			return KCB_KCXXMC;
		}
		public void setKCB_KCXXMC(String KCB_KCXXMC) {
			this.KCB_KCXXMC = KCB_KCXXMC;
		}

		public String getXSXM() {
			return XSXM;
		}
		public void setXSXM(String XSXM) {
			this.XSXM = XSXM;
		}

		public String getXUEHAO() {
			return XUEHAO;
		}
		public void setXUEHAO(String XUEHAO) {
			this.XUEHAO = XUEHAO;
		}

		public String getKCB_ID() {
			return KCB_ID;
		}
		public void setKCB_ID(String KCB_ID) {
			this.KCB_ID = KCB_ID;
		}

		public String getXS_ID() {
			return XS_ID;
		}
		public void setXS_ID(String XS_ID) {
			this.XS_ID = XS_ID;
		}

		public SKXSXXService getSKXSXX() {
			return skxsxxService;
		}

		public void setskxsxxService(SKXSXXService skxsxxService) {
			this.skxsxxService = skxsxxService;
		}

		public String getKCXS_ID() {
			return KCXS_ID;
		}
		public void setKCXS_ID(String KCXS_ID) {
			this.KCXS_ID = KCXS_ID;
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