package com.hrbsys.xueqi.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.xueqi.service.XUEQIService;
public class XUEQIAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private XUEQIService xueqiService;
	private String optionflag;
	private String XQ_ID; 
	private String BZ;
	private String KSRQ;
	private String MS;
	private String JSRQ;
	private String XQMC;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listXUEQI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("BZ", this.BZ);
		params.put("KSRQ", this.KSRQ);
		params.put("MS", this.MS);
		params.put("JSRQ", this.JSRQ);
		params.put("XQMC", this.XQMC);
		List<XUEQI> list = xueqiService.findXUEQIByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", xueqiService.getCountXUEQI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addXUEQI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		XUEQI t = new XUEQI();
		t.setXQ_ID(UUIDTools.getUUID());
		t.setBZ(this.getBZ());
		t.setKSRQ(this.getKSRQ());
		t.setMS(this.getMS());
		t.setJSRQ(this.getJSRQ());
		t.setXQMC(this.getXQMC());
		if (xueqiService.addXUEQI(t)) {
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

	public void delXUEQI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != XQ_ID) && !"".equals(XQ_ID)) {
			if (xueqiService.delXUEQI(XQ_ID)) {
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

	public void updateXUEQI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(XQ_ID) || null == XQ_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		XUEQI t = new XUEQI();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateXUEQI".equals(optionflag)) {
				t.setXQ_ID(XQ_ID);
				t.setBZ(this.getBZ());
				t.setKSRQ(this.getKSRQ());
				t.setMS(this.getMS());
				t.setJSRQ(this.getJSRQ());
				t.setXQMC(this.getXQMC());
				if (xueqiService.updateXUEQI(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(xueqiService.findXUEQI(XQ_ID),config));
	}

	public void listAllXUEQI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BZ", this.BZ);
		params.put("KSRQ", this.KSRQ);
		params.put("MS", this.MS);
		params.put("JSRQ", this.JSRQ);
		params.put("XQMC", this.XQMC);
		List<XUEQI> list = xueqiService.findXUEQIByPageAppforKCB(params, order, sort);// 每页的数据，放入list
		
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}
	//给学期增加课程表
	public void listAllXUEQIforKCB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BZ", this.BZ);
		params.put("KSRQ", this.KSRQ);
		params.put("MS", this.MS);
		params.put("JSRQ", this.JSRQ);
		params.put("XQMC", this.XQMC);
		List<XUEQI> list = xueqiService.findXUEQIByPageApp(params, order, sort);// 每页的数据，放入list
		
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getBZ() {
			return BZ;
		}
		public void setBZ(String BZ) {
			this.BZ = BZ;
		}

		public String getKSRQ() {
			return KSRQ;
		}
		public void setKSRQ(String KSRQ) {
			this.KSRQ = KSRQ;
		}

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getJSRQ() {
			return JSRQ;
		}
		public void setJSRQ(String JSRQ) {
			this.JSRQ = JSRQ;
		}

		public String getXQMC() {
			return XQMC;
		}
		public void setXQMC(String XQMC) {
			this.XQMC = XQMC;
		}

		public XUEQIService getXUEQI() {
			return xueqiService;
		}

		public void setxueqiService(XUEQIService xueqiService) {
			this.xueqiService = xueqiService;
		}

		public String getXQ_ID() {
			return XQ_ID;
		}
		public void setXQ_ID(String XQ_ID) {
			this.XQ_ID = XQ_ID;
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