package com.hrbsys.sbxx.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.SBXX;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.sbxx.service.SBXXService;

public class SBXXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private SBXXService sbxxService;
	private String optionflag;

	private String ID;
	private String SBXX_ID;
	private String JSMC;
	private String BZ;
	private String JS_ID;
	private String MS;
	private String SBMC;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	private String addJsName;
	private String updateJsMc;
	private String addJsId;

	public void listSBXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0")
				? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0")
				? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("ID", this.ID);
		params.put("JSMC", this.JSMC);
		params.put("BZ", this.BZ);
		params.put("JS_ID", this.JS_ID);
		params.put("MS", this.MS);
		params.put("SBXX_ID", this.SBXX_ID);
		params.put("SBMC", this.SBMC);
		List<SBXX> list = sbxxService.findSBXXByPageApp(start, number, params,
				order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", sbxxService.getCountSBXX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addSBXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//进行添加之前，到数据库中查询一下，是否以存在
		if (sbxxService.findName(this.getSBXX_ID())) {
			jsonMap.put("success", false);
			jsonMap.put("message", "此设备ID以存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		}else{
			SBXX t = new SBXX();
			t.setID(UUIDTools.getUUID());
			t.setSBXX_ID(this.getSBXX_ID());
			t.setSBMC(this.getSBMC());
			t.setJSMC(URLDecoder.decode(this.getAddJsName(), "UTF-8"));
			t.setJS_ID(this.getAddJsId());
			t.setBZ(this.getBZ());
			t.setMS(this.getMS());

			if (sbxxService.addSBXX(t)) {
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
		
	}
	public void delSBXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != ID) && !"".equals(ID)) {
			if (sbxxService.delSBXX(ID)) {
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

	public void updateSBXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(this.ID) || null == this.ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		SBXX t = new SBXX();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if (this.SBXX_ID.length() > 4) {
				jsonMap.put("success", false);
				jsonMap.put("message", "设备ID位数太长了！正确格式：1-9999");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
			if ("updateSBXX".equals(optionflag)) {
				t.setID(this.getID());
				t.setSBXX_ID(this.getSBXX_ID());
				t.setJS_ID(this.getJS_ID());
				t.setJSMC(this.getJSMC());
				t.setBZ(this.getBZ());
				t.setMS(this.getMS());
				t.setSBMC(this.getSBMC());

				if (sbxxService.updateSBXX(t)) {
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
				sbxxService.findSBXX(this.ID), config));
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getSBMC() {
		return SBMC;
	}

	public void setSBMC(String SBMC) {
		this.SBMC = SBMC;
	}

	public SBXXService getSBXX() {
		return sbxxService;
	}

	public void setsbxxService(SBXXService sbxxService) {
		this.sbxxService = sbxxService;
	}

	public String getSBXX_ID() {
		return SBXX_ID;
	}

	public void setSBXX_ID(String SBXX_ID) {
		this.SBXX_ID = SBXX_ID;
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

	public String getAddJsId() {
		return addJsId;
	}

	public void setAddJsId(String addJsId) {
		this.addJsId = addJsId;
	}

	public String getUpdateJsMc() {
		return updateJsMc;
	}

	public void setUpdateJsMc(String updateJsMc) {
		this.updateJsMc = updateJsMc;
	}

	public String getAddJsName() {
		return addJsName;
	}

	public void setAddJsName(String addJsName) {
		this.addJsName = addJsName;
	}

	public SBXXService getSbxxService() {
		return sbxxService;
	}

	public void setSbxxService(SBXXService sbxxService) {
		this.sbxxService = sbxxService;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}