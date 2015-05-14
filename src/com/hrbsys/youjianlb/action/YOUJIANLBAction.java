package com.hrbsys.youjianlb.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.YOUJIANLB;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.tools.Validator;
import com.hrbsys.youjianlb.service.YOUJIANLBService;
public class YOUJIANLBAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private YOUJIANLBService youjianlbService;
	private String optionflag;
	private String YOUJIANLB_ID; 
	private String MS;
	private String XINGMING;
	private String YXMC;
	private String BZ;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listYOUJIANLB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("XINGMING", this.XINGMING);
		params.put("YXMC", this.YXMC);
		List<YOUJIANLB> list = youjianlbService.findYOUJIANLBByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", youjianlbService.getCountYOUJIANLB(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addYOUJIANLB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		YOUJIANLB t = new YOUJIANLB();
		t.setYOUJIANLB_ID(UUIDTools.getUUID());
		t.setMS(this.getMS());
		t.setXINGMING(this.getXINGMING());
		t.setYXMC(this.getYXMC());
		t.setBZ(this.getBZ());
		if (youjianlbService.addYOUJIANLB(t)) {
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

	public void delYOUJIANLB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != YOUJIANLB_ID) && !"".equals(YOUJIANLB_ID)) {
			if (youjianlbService.delYOUJIANLB(YOUJIANLB_ID)) {
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

	public void updateYOUJIANLB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(YOUJIANLB_ID) || null == YOUJIANLB_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		YOUJIANLB t = new YOUJIANLB();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateYOUJIANLB".equals(optionflag)) {
				t.setYOUJIANLB_ID(YOUJIANLB_ID);
				t.setMS(this.getMS());
				t.setXINGMING(this.getXINGMING());
				t.setYXMC(this.getYXMC());
				t.setBZ(this.getBZ());
				if (youjianlbService.updateYOUJIANLB(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(youjianlbService.findYOUJIANLB(YOUJIANLB_ID),config));
	}

	public void listAllYOUJIANLB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("MS", this.MS);
		params.put("XINGMING", this.XINGMING);
		params.put("YXMC", this.YXMC);
		params.put("BZ", this.BZ);
		List<YOUJIANLB> list = youjianlbService.findYOUJIANLBByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getXINGMING() {
			return XINGMING;
		}
		public void setXINGMING(String XINGMING) {
			this.XINGMING = XINGMING;
		}

		public String getYXMC() {
			return YXMC;
		}
		public void setYXMC(String YXMC) {
			this.YXMC = YXMC;
		}

		public String getBZ() {
			return BZ;
		}
		public void setBZ(String BZ) {
			this.BZ = BZ;
		}

		public YOUJIANLBService getYOUJIANLB() {
			return youjianlbService;
		}

		public void setyoujianlbService(YOUJIANLBService youjianlbService) {
			this.youjianlbService = youjianlbService;
		}

		public String getYOUJIANLB_ID() {
			return YOUJIANLB_ID;
		}
		public void setYOUJIANLB_ID(String YOUJIANLB_ID) {
			this.YOUJIANLB_ID = YOUJIANLB_ID;
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