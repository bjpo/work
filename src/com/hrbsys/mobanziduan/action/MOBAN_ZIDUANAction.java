package com.hrbsys.mobanziduan.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.MOBAN;
import com.hrbsys.bean.MOBAN_ZIDUAN;
import com.hrbsys.mobanziduan.service.MOBAN_ZIDUANService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class MOBAN_ZIDUANAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private MOBAN_ZIDUANService moban_ziduanService;
	private String optionflag;
	private String ZIDUAN_ID;// 字段编号
	private String LIE;// 列
	private String MB_ID;// 外键，模板编号
	private String ZIDUANMC;// 字段名称

	private String txtExcelName;
	private String txtDB;

	public String getTxtExcelName() {
		return txtExcelName;
	}

	public void setTxtExcelName(String txtExcelName) {
		this.txtExcelName = txtExcelName;
	}

	public String getTxtDB() {
		return txtDB;
	}

	public void setTxtDB(String txtDB) {
		this.txtDB = txtDB;
	}

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listMOBAN_ZIDUAN() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("LIE", this.LIE);
		params.put("ZIDUAN_ID", this.ZIDUAN_ID);
		params.put("MB_ID", this.MB_ID);
		params.put("ZIDUANMC", this.ZIDUANMC);
		List<MOBAN_ZIDUAN> list = moban_ziduanService
				.findMOBAN_ZIDUANByPageApp(start, number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", moban_ziduanService.getCountMOBAN_ZIDUAN(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addMOBAN_ZIDUAN() throws Exception {
		
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		MOBAN_ZIDUAN t = new MOBAN_ZIDUAN();
		t.setZIDUAN_ID(UUIDTools.getUUID());
		t.setLIE(this.getTxtExcelName());
		t.setMB_ID(this.getMB_ID());
		t.setZIDUANMC(this.getTxtDB());
		if (moban_ziduanService.addMOBAN_ZIDUAN(t)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "模板字段信息新增成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "模板字段信息添加失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	public void delMOBAN_ZIDUAN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != ZIDUAN_ID) && !"".equals(ZIDUAN_ID)) {
			if (moban_ziduanService.delMOBAN_ZIDUAN(ZIDUAN_ID)) {
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

	public void updateMOBAN_ZIDUAN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(ZIDUAN_ID) || null == ZIDUAN_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		MOBAN_ZIDUAN t = new MOBAN_ZIDUAN();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateMOBAN_ZIDUAN".equals(optionflag)) {
				t.setZIDUAN_ID(ZIDUAN_ID);
				t.setLIE(this.getLIE());
				t.setZIDUAN_ID(this.getZIDUAN_ID());
				t.setMB_ID(this.getMB_ID());
				t.setZIDUANMC(this.getZIDUANMC());
				if (moban_ziduanService.updateMOBAN_ZIDUAN(t)) {
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
				moban_ziduanService.findMOBAN_ZIDUAN(ZIDUAN_ID), config));
	}

	public void listAllMOBAN_ZIDUAN() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LIE", this.LIE);
		params.put("ZIDUAN_ID", this.ZIDUAN_ID);
		params.put("MB_ID", this.MB_ID);
		params.put("ZIDUANMC", this.ZIDUANMC);
		List<MOBAN_ZIDUAN> list = moban_ziduanService
				.findMOBAN_ZIDUANByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	public String getLIE() {
		return LIE;
	}

	public void setLIE(String LIE) {
		this.LIE = LIE;
	}

	public MOBAN_ZIDUANService getMoban_ziduanService() {
		return moban_ziduanService;
	}

	public void setMoban_ziduanService(MOBAN_ZIDUANService moban_ziduanService) {
		this.moban_ziduanService = moban_ziduanService;
	}

	public String getMB_ID() {
		return MB_ID;
	}

	public void setMB_ID(String MB_ID) {
		this.MB_ID = MB_ID;
	}

	public String getZIDUANMC() {
		return ZIDUANMC;
	}

	public void setZIDUANMC(String ZIDUANMC) {
		this.ZIDUANMC = ZIDUANMC;
	}

	public MOBAN_ZIDUANService getMOBAN_ZIDUAN() {
		return moban_ziduanService;
	}

	public void setmoban_ziduanService(MOBAN_ZIDUANService moban_ziduanService) {
		this.moban_ziduanService = moban_ziduanService;
	}

	public String getZIDUAN_ID() {
		return ZIDUAN_ID;
	}

	public void setZIDUAN_ID(String ZIDUAN_ID) {
		this.ZIDUAN_ID = ZIDUAN_ID;
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