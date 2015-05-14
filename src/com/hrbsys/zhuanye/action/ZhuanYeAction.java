package com.hrbsys.zhuanye.action;

import java.net.URLDecoder;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.zhuanye.service.ZhuanYeService;

public class ZhuanYeAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private ZhuanYeService zhuanyeService;
	private String optionflag;
	private String ZY_ID;
	private String MS;
	private String XYID;
	private String ZYMC;

	private String BZ;
	private String XYMC;
	private String ZYDM;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;
	// 学院下拉列表中的值
	private String addXyName;
	private String updateXyName;
	

	public void listZHUANYE() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("MS", this.MS);
		params.put("XYID", this.XYID);
		params.put("ZYMC", this.ZYMC);
		params.put("BZ", this.BZ);
		params.put("XYMC", this.XYMC);
		params.put("ZYDM", this.ZYDM);
		List<ZHUANYE> list = zhuanyeService.findZHUANYEByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", zhuanyeService.getCountZHUANYE(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addZHUANYE() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ZHUANYE t = new ZHUANYE();
		t.setZY_ID(UUIDTools.getUUID());
		t.setMS(this.getMS());
		t.setXYID(this.getXYID());
		t.setZYMC(this.getZYMC());
		t.setBZ(this.getBZ());
		t.setXYMC(URLDecoder.decode(this.getAddXyName(), "UTF-8"));
		t.setZYDM(this.getZYDM());
		if (zhuanyeService.addZHUANYE(t)) {
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

	public void delZHUANYE() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != ZY_ID) && !"".equals(ZY_ID)) {
			if (zhuanyeService.delZHUANYE(ZY_ID)) {
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

	public void updateZHUANYE() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(ZY_ID) || null == ZY_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		ZHUANYE t = new ZHUANYE();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateZHUANYE".equals(optionflag)) {
				t.setZY_ID(ZY_ID);
				t.setMS(this.getMS());
				t.setXYID(this.getXYID());
				t.setZYMC(this.getZYMC());
				t.setBZ(this.getBZ());
				t.setXYMC(XYMC);
				t.setZYDM(this.getZYDM());
				if (zhuanyeService.updateZHUANYE(t)) {
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
				zhuanyeService.findZHUANYE(ZY_ID), config));
	}

	public void listAllZHUANYE() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("MS", this.MS);
		params.put("XYID", this.XYID);
		params.put("ZYMC", this.ZYMC);
		params.put("BZ", this.BZ);
		params.put("XYMC", this.XYMC);
		params.put("ZYDM", this.ZYDM);
		List<ZHUANYE> list = zhuanyeService.findZHUANYEByPageApp(params, order,
				sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}
	
	public void listZHUANYEFORXUEYUAN(){
		if(!"".equals(XYID)){
			List<ZHUANYE> list = zhuanyeService.findZHUANYEByXueYuan(XYID);
			JsonConfig config = new JsonConfig();
			new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
		}
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getXYID() {
		return XYID;
	}

	public void setXYID(String XYID) {
		this.XYID = XYID;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String ZYMC) {
		this.ZYMC = ZYMC;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String XYMC) {
		this.XYMC = XYMC;
	}

	public String getZYDM() {
		return ZYDM;
	}

	public void setZYDM(String ZYDM) {
		this.ZYDM = ZYDM;
	}

	public ZhuanYeService getZHUANYE() {
		return zhuanyeService;
	}

	public void setzhuanyeService(ZhuanYeService zhuanyeService) {
		this.zhuanyeService = zhuanyeService;
	}

	public String getZY_ID() {
		return ZY_ID;
	}

	public void setZY_ID(String ZY_ID) {
		this.ZY_ID = ZY_ID;
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

	public ZhuanYeService getZhuanyeService() {
		return zhuanyeService;
	}

	public void setZhuanyeService(ZhuanYeService zhuanyeService) {
		this.zhuanyeService = zhuanyeService;
	}

	public String getAddXyName() {
		return addXyName;
	}

	public void setAddXyName(String addXyName) {
		this.addXyName = addXyName;
	}
	public String getUpdateXyName() {
		return updateXyName;
	}

	public void setUpdateXyName(String updateXyName) {
		this.updateXyName = updateXyName;
	}

}