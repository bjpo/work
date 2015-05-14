package com.hrbsys.xueyuan.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.xueyuan.service.XueYuanService;

/**
 * 学院Action类
 * 
 * @author Administrator
 * 
 */
public class XueYuanAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	private XueYuanService xueyuanService;

	// 分页相关
	private String rows; // 每页显示的记录数

	private String page;// 当前页码(第几页)
	private JSONObject result;

	// 排序相关
	private String order;
	private String sort;

	private String xyid;// 学院ID
	private String update_xyid;// 修改学院信息时用到的id

	private String xydm;// 学院代码
	private String xymc;// 学院名称
	private String xydz;// 学院地址
	private String yzbm;// 邮政编码
	private String xxxx;// 详细信息
	private String bz;// 备注
	private String ms;// 描述
	private String fxyid;// 上级院系ID
	private String fxymc;// 上级院系名称
	
	/**
	 * 学院管理相关Action
	 */

	/* 学院信息列表 */
	public void listXueYuan() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("xymc", this.xymc);

		List<XUEYUAN> list = xueyuanService.findXUEYUANByPage(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", xueyuanService.getCountXUEYUAN(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		//记录一共有多少页(校长前台 ，配置页面，学院分页)
		int totalPages=0;
			if (xueyuanService.getCountXUEYUAN(params)%Integer.parseInt(rows)== 0) {
				totalPages=xueyuanService.getCountXUEYUAN(params)/ Integer.parseInt(rows);
			} else {
				totalPages= xueyuanService.getCountXUEYUAN(params)/ Integer.parseInt(rows) + 1;
			}
		jsonMap.put("totalPages", totalPages);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void listAllXUEYUAN() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("YZBM", this.yzbm);
		params.put("BZ", this.bz);
		params.put("XYDM", this.xydm);
		params.put("XY_ID", this.xyid);
		params.put("FXYMC", this.fxymc);
		params.put("MS", this.ms);
		params.put("XYMC", this.xymc);
		params.put("FXY_ID", this.fxyid);
		params.put("XXXX", this.xxxx);
		params.put("XYDZ", this.xydz);
		List<XUEYUAN> list = xueyuanService.findXUEYUANByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}
	/* 查询学院名称 */
	public void listforXUEYUAN() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("xymc", this.xymc);
		List<XUEYUAN> list = xueyuanService.findXUEYUANByPageApp(params, order,
				sort); // 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/* 添加学院信息 */
	public void addXueYuan() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(xymc) || null == xymc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请填写学院名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(xydm) || null == xydm) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请填写学院代码！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		// fxyid与xyid共享UUID
		String str = UUIDTools.getUUID();
		// 将数据存入到XUEYUAN对象的属性中
		XUEYUAN xy = new XUEYUAN();
		xy.setXyid(str);
		xy.setXydm(xydm);
		xy.setXymc(xymc);
		xy.setXydz(xydz);
		xy.setYzbm(yzbm);
		xy.setBz(bz);
		xy.setMs(ms);
		xy.setFxyid(str);
		xy.setFxymc(xymc);

		// 判断是否存入成功，是否成功给予提示信息
		if (xueyuanService.addXueYuan(xy)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "添加学院成功！");

		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "添加学院失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	/* 删除学院信息 */
	public void delXueYuan() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != xyid) && !"".equals(xyid)) {
			if (xueyuanService.delXueYuan(xyid)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "学院删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "学院删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	/* 修改学院信息 */
	public void updateXueYuan() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		XUEYUAN xy = new XUEYUAN();
		xy.setXyid(update_xyid);
		xy.setXydm(xydm);
		xy.setXymc(xymc);
		xy.setXydz(xydz);
		xy.setYzbm(yzbm);
		xy.setXxxx(xxxx);
		xy.setBz(bz);
		xy.setMs(ms);
		xy.setFxymc(fxymc);
		if (xueyuanService.updateXueYuan(xy)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "修改学院信息成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "修改学院信息失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * get和set方法
	 */
	public XueYuanService getXueyuanService() {
		return xueyuanService;
	}

	public void setXueyuanService(XueYuanService xueyuanService) {
		this.xueyuanService = xueyuanService;
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

	public String getXyid() {
		return xyid;
	}

	public void setXyid(String xyid) {
		this.xyid = xyid;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXydz() {
		return xydz;
	}

	public void setXydz(String xydz) {
		this.xydz = xydz;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getXxxx() {
		return xxxx;
	}

	public void setXxxx(String xxxx) {
		this.xxxx = xxxx;
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

	public String getFxyid() {
		return fxyid;
	}

	public void setFxyid(String fxyid) {
		this.fxyid = fxyid;
	}

	public String getFxymc() {
		return fxymc;
	}

	public void setFxymc(String fxymc) {
		this.fxymc = fxymc;
	}

	public String getUpdate_xyid() {
		return update_xyid;
	}

	public void setUpdate_xyid(String update_xyid) {
		this.update_xyid = update_xyid;
	}

}
