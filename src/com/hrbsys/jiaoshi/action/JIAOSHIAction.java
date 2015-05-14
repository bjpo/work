package com.hrbsys.jiaoshi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.FANGJIAN;
import com.hrbsys.bean.JIAOSHI;
import com.hrbsys.jiaoshi.JIAOSHIService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class JIAOSHIAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private JIAOSHIService jiaoshiService;
	private String optionflag;
	private String JS_ID;
	private String JSMC;
	private String FJMC;
	private String FJID;
	private String ISDMT;
	private String FJDM;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listJIAOSHI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		int start = (intPage - 1) * number;
		params.put("JSMC", this.JSMC);
		params.put("FJMC", this.FJMC);
		params.put("FJID", this.FJID);
		params.put("ISDMT", this.ISDMT);
		params.put("FJDM", this.FJDM);
		List<JIAOSHI> list = jiaoshiService.findJIAOSHIByPageApp(start, number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", jiaoshiService.getCountJIAOSHI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}
	
	public void listAllFANGJIAN()
	{
		List<FANGJIAN> list = jiaoshiService.findFANGJIANByPageApp();
		int iSize = list.size();
		for(int i = 0 ; i < iSize ; i++)
		{
			FANGJIAN fj = (FANGJIAN)list.get(i);
			if(null == fj.getFjdm()){
				fj.setFjdm("");
			}
			if(null == fj.getFjh()){
				fj.setFjh("");
			}
			if(null == fj.getIsdmt()){
				fj.setIsdmt("");
			}
			fj.setFjdm("代码："+fj.getFjdm()+"   房间号："+fj.getFjh()+ "   多媒体："+fj.getIsdmt());
		}
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

	public void addJIAOSHI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(null != jiaoshiService.findJIAOSHIByFJID(FJID)){
			jsonMap.put("success", false);
			jsonMap.put("message", "房间已被占用！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if(null != jiaoshiService.findJIAOSHIByName(JSMC)){
			jsonMap.put("success", false);
			jsonMap.put("message", "教室已存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		FANGJIAN fj = jiaoshiService.findFANGJIAN(FJID);
		if(null == fj){
			jsonMap.put("success", false);
			jsonMap.put("message", "房间信息出现错误！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JIAOSHI t = new JIAOSHI();
		t.setJS_ID(UUIDTools.getUUID());
		t.setJSMC(this.getJSMC());
		t.setFJMC(fj.getFjmc());
		t.setFJID(fj.getFjId());
		t.setISDMT(fj.getIsdmt());
		t.setFJDM(fj.getFjdm());
		if (jiaoshiService.addJIAOSHI(t)) {
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

	public void delJIAOSHI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != JS_ID) && !"".equals(JS_ID)) {
			if (jiaoshiService.delJIAOSHI(JS_ID)) {
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

	public void updateJIAOSHI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(JS_ID) || null == JS_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JIAOSHI js = jiaoshiService.findJIAOSHIByFJID(FJID);
		
		if(null != js){
			if(!JS_ID.equals(js.getJS_ID())){
				jsonMap.put("success", false);
				jsonMap.put("message", "房间已被占用！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}
		
		FANGJIAN fj = jiaoshiService.findFANGJIAN(FJID);
		if(null == fj){
			jsonMap.put("success", false);
			jsonMap.put("message", "房间信息出现错误！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JIAOSHI t = new JIAOSHI();
		t.setJS_ID(JS_ID);
		t.setJSMC(JSMC);
		t.setFJMC(fj.getFjmc());
		t.setFJID(FJID);
		t.setISDMT(fj.getIsdmt());
		t.setFJDM(fj.getFjdm());
		if (jiaoshiService.updateJIAOSHI(t)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "修改信息成功！");
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "修改信息失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void listAllJIAOSHI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		List<JIAOSHI> list = jiaoshiService.findJIAOSHIByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	public JIAOSHIService getJiaoshiService() {
		return jiaoshiService;
	}

	public void setJiaoshiService(JIAOSHIService jiaoshiService) {
		this.jiaoshiService = jiaoshiService;
	}

	public String getOptionflag() {
		return optionflag;
	}

	public void setOptionflag(String optionflag) {
		this.optionflag = optionflag;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String jS_ID) {
		JS_ID = jS_ID;
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String jSMC) {
		JSMC = jSMC;
	}

	public String getFJMC() {
		return FJMC;
	}

	public void setFJMC(String fJMC) {
		FJMC = fJMC;
	}

	public String getFJID() {
		return FJID;
	}

	public void setFJID(String fJID) {
		FJID = fJID;
	}

	public String getISDMT() {
		return ISDMT;
	}

	public void setISDMT(String iSDMT) {
		ISDMT = iSDMT;
	}

	public String getFJDM() {
		return FJDM;
	}

	public void setFJDM(String fJDM) {
		FJDM = fJDM;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}