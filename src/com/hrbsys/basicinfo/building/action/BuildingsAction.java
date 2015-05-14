package com.hrbsys.basicinfo.building.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.building.service.BuildingService;
import com.hrbsys.bean.JXL;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class BuildingsAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	private BuildingService buildingService;

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	private String jxlId;
	private String jxlmc;
	private String jxldm;
	private String jxllh;
	private String jxlwz;
	private String bz;
	private String ms;

	/** 教学楼action *****************************************************************************/
	// 列表
	public void listJXL() {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		int start = (intPage - 1) * number;
		params.put("jxlmc", this.jxlmc);
		params.put("jxldm", this.jxldm);
		params.put("jxllh", this.jxllh);
		params.put("jxlwz", this.jxlwz);

		List<JXL> list = buildingService.findJXLByPageApp(start, number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		jsonMap.put("total", buildingService.getCountJXL(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);

		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 新增教学楼:
	public void addJXL() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(jxlmc) || null == jxlmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jxldm) || null == jxldm) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写代码！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jxllh) || null == jxllh) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写楼号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jxlwz) || null == jxlwz) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写位置");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JXL jxl = new JXL();
		jxl.setJxlId(UUIDTools.getUUID());
		jxl.setJxlmc(this.jxlmc);
		jxl.setJxldm(this.jxldm);
		jxl.setJxllh(this.jxllh);
		jxl.setJxlwz(this.jxlwz);

		if (buildingService.addJXL(jxl)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "添加增成功！");

		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "添加增失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	// 删除教学楼:
	public void delJXL() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != jxlId) && !"".equals(jxlId)) {
			if (buildingService.delJXL(jxlId)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改教学楼:
	public void updateJXL() throws Exception {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		if ("".equals(jxlId) || null == jxlId) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有该信息！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(jxlmc) || null == jxlmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(jxldm) || null == jxldm) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写代码！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(jxllh) || null == jxllh) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写楼号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(jxlwz) || null == jxlwz) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写位置！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		JXL jxl = new JXL();
		jxl.setJxlId(this.jxlId);
		jxl.setJxlmc(this.jxlmc);
		jxl.setJxldm(this.jxldm);
		jxl.setJxllh(this.jxllh);
		jxl.setJxlwz(this.jxlwz);

		if (buildingService.updateJXL(jxl)) {
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

	public void listAllJXL() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		List<JXL> list = buildingService.findJXLByPageApp(params, order, sort);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	public BuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
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

	public String getJxlId() {
		return jxlId;
	}

	public void setJxlId(String jxlId) {
		this.jxlId = jxlId;
	}

	public String getJxlmc() {
		return jxlmc;
	}

	public void setJxlmc(String jxlmc) {
		this.jxlmc = jxlmc;
	}

	public String getJxldm() {
		return jxldm;
	}

	public void setJxldm(String jxldm) {
		this.jxldm = jxldm;
	}

	public String getJxllh() {
		return jxllh;
	}

	public void setJxllh(String jxllh) {
		this.jxllh = jxllh;
	}

	public String getJxlwz() {
		return jxlwz;
	}

	public void setJxlwz(String jxlwz) {
		this.jxlwz = jxlwz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
