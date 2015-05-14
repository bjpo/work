package com.hrbsys.basicinfo.banji.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.banji.service.BANJIService;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class BANJIAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private BANJIService banjiService;
	private String optionflag;
	private String BJ_ID;
	private String NJDM;
	private String NJ_ID;
	private String ZYDM;
	private String NJMC;
	private String ZYMC;
	private String MS;
	private String BZK;
	private String BJMC;
	private String BZ;
	private String BJHM;
	private String ZY_ID;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listBANJI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		int start = (intPage - 1) * number;
		params.put("NJDM", this.NJDM);
		params.put("ZYDM", this.ZYDM);
		params.put("NJMC", this.NJMC);
		params.put("ZYMC", this.ZYMC);
		params.put("BZK", this.BZK);
		params.put("BJMC", this.BJMC);
		params.put("BJHM", this.BJHM);
		List<BANJI> list = banjiService.findBANJIByPageApp(start, number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", banjiService.getCountBANJI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void listAllNIANJIFORBANJI() {
		List<NIANJI> list = banjiService.findNIANJI();
		int iSize = list.size();
		for (int i = 0; i < iSize; i++) {
			NIANJI nj = (NIANJI) list.get(i);
			if (null == nj.getNJMC()) {
				nj.setNJMC("无");
			}
			if (null == nj.getNJDM()) {
				nj.setNJDM("无");
			}
			nj.setNJMC("年级名称：" + nj.getNJMC() + " 年级代码：" + nj.getNJDM());
		}
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	public void listAllZHUANYEFORBANJI() {
		List<ZHUANYE> list = banjiService.findZHUANYE();
		int iSize = list.size();
		for (int i = 0; i < iSize; i++) {
			ZHUANYE zy = (ZHUANYE) list.get(i);
			if (null == zy.getZYMC()) {
				zy.setZYMC("无");
			}
			if (null == zy.getZYDM()) {
				zy.setZYDM("无");
			}
			zy.setZYMC("专业名称：" + zy.getZYMC() + "　专业代码：" + zy.getZYDM());
		}
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	public void addBANJI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		NIANJI nj = banjiService.findNIANJI(NJ_ID);
		if (null == nj) {
			jsonMap.put("success", false);
			jsonMap.put("message", "年级信息未选择！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		ZHUANYE zy = banjiService.findZHUANYE(ZY_ID);
		if (null == zy) {
			jsonMap.put("success", false);
			jsonMap.put("message", "专业信息未选择！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		if(null == BZK){
			jsonMap.put("success", false);
			jsonMap.put("message", "学生类型未选择！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		BJMC = zy.getZYMC() + nj.getNJMC() + BZK + BJHM;
		BANJI bj = banjiService.findBANJIByName(BJMC);
		if (null != bj) {
			jsonMap.put("success", false);
			jsonMap.put("message", "班级名称已存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		BANJI t = new BANJI();
		t.setBJ_ID(UUIDTools.getUUID());
		t.setNJDM(nj.getNJDM());
		t.setNJMC(nj.getNJMC());
		t.setNJ_ID(NJ_ID);
		t.setZYDM(zy.getZYDM());
		t.setZYMC(zy.getZYMC());
		t.setZY_ID(ZY_ID);
		t.setMS(MS);
		t.setBZK(BZK);
		t.setBJMC(BJMC);
		t.setBZ(BZ);
		t.setBJHM(BJHM);

		if (banjiService.addBANJI(t)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "信息新增成功！");
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "信息添加失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void delBANJI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != BJ_ID) && !"".equals(BJ_ID)) {
			if (banjiService.delBANJI(BJ_ID)) {
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

	public void updateBANJI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(BJ_ID) || null == BJ_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		HashMap<String, String> params1 = new HashMap<String, String>();
		params1.put("NJ_ID", this.NJ_ID);
		params1.put("BJMC", this.BJMC);
		BANJI bj1 = banjiService.findBANJIByNIANJI(params1);
		if (null != bj1) {
			if (!BJ_ID.equals(bj1.getBJ_ID())) {
				jsonMap.put("success", false);
				jsonMap.put("message", "该年级信息已被占用！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}

		HashMap<String, String> params2 = new HashMap<String, String>();
		params2.put("ZY_ID", this.ZY_ID);
		params2.put("BJMC", this.BJMC);
		BANJI bj2 = banjiService.findBANJIByZHUANYE(params2);
		if (null != bj2) {
			if (!BJ_ID.equals(bj2.getBJ_ID())) {
				jsonMap.put("success", false);
				jsonMap.put("message", "该专业信息已被占用！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}

		NIANJI nj = banjiService.findNIANJI(NJ_ID);
		if (null == nj) {
			jsonMap.put("success", false);
			jsonMap.put("message", "年级信息未选择！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		ZHUANYE zy = banjiService.findZHUANYE(ZY_ID);
		if (null == zy) {
			jsonMap.put("success", false);
			jsonMap.put("message", "专业信息未选择！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		BANJI t = new BANJI();
		t.setBJ_ID(BJ_ID);
		t.setNJDM(nj.getNJDM());
		t.setNJMC(nj.getNJMC());
		t.setNJ_ID(NJ_ID);
		t.setZYDM(zy.getZYDM());
		t.setZYMC(zy.getZYMC());
		t.setZY_ID(ZY_ID);
		t.setMS(MS);
		t.setBZK(BZK);
		t.setBJMC(BJMC);
		t.setBZ(BZ);
		t.setBJHM(BJHM);
		if (banjiService.updateBANJI(t)) {
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

	public void listAllBANJI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("NJ_ID", this.NJ_ID);
		params.put("ZY_ID", this.ZY_ID);
		List<BANJI> list = banjiService.findBANJIByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}
	
	public void listBANJIByZHUANYEAndNIANJI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("NJ_ID", this.NJ_ID);
		params.put("ZY_ID", this.ZY_ID);
		List<BANJI> list = banjiService.findBANJIFORZYANDNIANJI(params);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	public BANJIService getBanjiService() {
		return banjiService;
	}

	public void setBanjiService(BANJIService banjiService) {
		this.banjiService = banjiService;
	}

	public String getOptionflag() {
		return optionflag;
	}

	public void setOptionflag(String optionflag) {
		this.optionflag = optionflag;
	}

	public String getBJ_ID() {
		return BJ_ID;
	}

	public void setBJ_ID(String bJ_ID) {
		BJ_ID = bJ_ID;
	}

	public String getNJDM() {
		return NJDM;
	}

	public void setNJDM(String nJDM) {
		NJDM = nJDM;
	}

	public String getNJ_ID() {
		return NJ_ID;
	}

	public void setNJ_ID(String nJ_ID) {
		NJ_ID = nJ_ID;
	}

	public String getZYDM() {
		return ZYDM;
	}

	public void setZYDM(String zYDM) {
		ZYDM = zYDM;
	}

	public String getNJMC() {
		return NJMC;
	}

	public void setNJMC(String nJMC) {
		NJMC = nJMC;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String mS) {
		MS = mS;
	}

	public String getBZK() {
		return BZK;
	}

	public void setBZK(String bZK) {
		BZK = bZK;
	}

	public String getBJMC() {
		return BJMC;
	}

	public void setBJMC(String bJMC) {
		BJMC = bJMC;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
	}

	public String getBJHM() {
		return BJHM;
	}

	public void setBJHM(String bJHM) {
		BJHM = bJHM;
	}

	public String getZY_ID() {
		return ZY_ID;
	}

	public void setZY_ID(String zY_ID) {
		ZY_ID = zY_ID;
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