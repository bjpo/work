package com.hrbsys.moban.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.MOBAN;
import com.hrbsys.bean.MOBAN_ZIDUAN;
import com.hrbsys.moban.service.MOBANService;
import com.hrbsys.mobanziduan.service.MOBAN_ZIDUANService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class MOBANAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private MOBANService mobanService;
	private MOBAN_ZIDUANService mbzdService;

	public MOBAN_ZIDUANService getMbzdService() {
		return mbzdService;
	}

	public void setMbzdService(MOBAN_ZIDUANService mbzdService) {
		this.mbzdService = mbzdService;
	}

	private String optionflag;
	private String MB_ID;
	private String MB_NAME;
	private String BIAOMING;
	private String updateMB_ID;

	private String txtExcelName;
	private String txtDB;
	private String ZHUJIAN;
	private String userColumn;//用户名对应列名
	private String isCreateUser;//是否创建用户标识
	private String insertType;//判断插入数据的类型是学生还是老师 0：学生 1： 老师
	public String getZHUJIAN() {
		return ZHUJIAN;
	}

	public void setZHUJIAN(String zHUJIAN) {
		ZHUJIAN = zHUJIAN;
	}

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

	public void listMOBAN() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("MB_NAME", this.MB_NAME);
		params.put("MB_ID", this.MB_ID);
		params.put("BIAOMING", this.BIAOMING);
		List<MOBAN> list = mobanService.findMOBANByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", mobanService.getCountMOBAN(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addMOBAN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		/** 存储模板 **/
		MOBAN t = new MOBAN();
		t.setMB_ID(UUIDTools.getUUID());
		t.setMB_NAME(this.getMB_NAME());
		t.setBIAOMING(this.getBIAOMING());
		t.setZHUJIAN(this.getZHUJIAN());
		t.setUSER_COLUMN(this.userColumn);
		t.setISCREATEUSER(this.isCreateUser);
		t.setInsertType(this.insertType);
		mobanService.addMOBAN(t);

		String[] DBArray = txtDB.split(",");
		String[] excelNameArray = txtExcelName.split(",");

		for (int i = 0; i <excelNameArray.length; i++) {
			MOBAN_ZIDUAN t1 = new MOBAN_ZIDUAN();
			t1.setZIDUAN_ID(UUIDTools.getUUID());
			t1.setLIE(excelNameArray[i]);
			System.out.println(excelNameArray[i]);
			t1.setMB_ID(t.getMB_ID());
			t1.setZIDUANMC(DBArray[i]);
			System.out.println(DBArray[i]);
			if (mbzdService.addMOBAN_ZIDUAN(t1)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "模板字段信息新增成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "模板字段信息添加失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}
	}

	public void delMOBAN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != MB_ID) && !"".equals(MB_ID)) {
			if (mobanService.delMOBAN(MB_ID)) {
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

	public void updateMOBAN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(MB_ID) || null == MB_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		MOBAN t = new MOBAN();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateMOBAN".equals(optionflag)) {
				t.setMB_NAME(this.getMB_NAME());
				t.setMB_ID(this.getUpdateMB_ID());
				t.setBIAOMING(this.getBIAOMING());
				t.setZHUJIAN(this.getZHUJIAN());
				if (mobanService.updateMOBAN(t)) {
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
				mobanService.findMOBAN(MB_ID), config));
	}

	public void listAllMOBAN() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("MB_NAME", this.MB_NAME);
		params.put("MB_ID", this.MB_ID);
		params.put("BIAOMING", this.BIAOMING);
		List<MOBAN> list = mobanService.findMOBANByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	public String getMB_NAME() {
		return MB_NAME;
	}

	public void setMB_NAME(String MB_NAME) {
		this.MB_NAME = MB_NAME;
	}

	public String getBIAOMING() {
		return BIAOMING;
	}

	public void setBIAOMING(String BIAOMING) {
		this.BIAOMING = BIAOMING;
	}

	public MOBANService getMOBAN() {
		return mobanService;
	}

	public void setmobanService(MOBANService mobanService) {
		this.mobanService = mobanService;
	}

	public String getMB_ID() {
		return MB_ID;
	}

	public void setMB_ID(String MB_ID) {
		this.MB_ID = MB_ID;
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

	public MOBANService getMobanService() {
		return mobanService;
	}

	public void setMobanService(MOBANService mobanService) {
		this.mobanService = mobanService;
	}

	public String getUpdateMB_ID() {
		return updateMB_ID;
	}

	public void setUpdateMB_ID(String updateMB_ID) {
		this.updateMB_ID = updateMB_ID;
	}

	public String getUserColumn() {
		return userColumn;
	}

	public void setUserColumn(String userColumn) {
		this.userColumn = userColumn;
	}

	public String getInsertType() {
		return insertType;
	}

	public void setInsertType(String insertType) {
		this.insertType = insertType;
	}

	public String getIsCreateUser() {
		return isCreateUser;
	}

	public void setIsCreateUser(String isCreateUser) {
		this.isCreateUser = isCreateUser;
	}
	
}