package com.hrbsys.monitongji.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

public class MonitongjiAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private String cx;
	private String qx;
	private String cd;
	private String zt;

	public void findMoni() throws Exception {
		Random rand = new Random();
		int i = rand.nextInt(99)+1;
		cx = String.valueOf((i%2));
		qx = String.valueOf((i%3));
		cd = String.valueOf((i%4));
		zt = String.valueOf((i%5));
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("cx", cx);
		jsonMap.put("qx", qx);
		jsonMap.put("cd", cd);
		jsonMap.put("zt", zt);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	public String getQx() {
		return qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}