package com.hrbsys.qjxx.action;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.QJXX;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.qjxx.service.QJXXService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
public class QJXXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private QJXXService qjxxService;
	private String optionflag;
	private String QJXX_ID; 
	private String BZ;
	private String XSXM;
	private String JGXM;
	private String JGGH;
	private String DJRQ;
	private String JG_ID;
	private String XSXH;
	private String XGRQ;
	private String MS;
	private String QJJSJS;
	private String QJKSSJ;
	private String XS_ID;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listQJXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("BZ", this.BZ);
		params.put("XSXM", this.XSXM);
		params.put("JGXM", this.JGXM);
		params.put("JGGH", this.JGGH);
		params.put("DJRQ", this.DJRQ);
		params.put("JG_ID", this.JG_ID);
		params.put("XSXH", this.XSXH);
		params.put("XGRQ", this.XGRQ);
		params.put("MS", this.MS);
		params.put("QJJSJS", this.QJJSJS);
		params.put("QJKSSJ", this.QJKSSJ);
		params.put("XS_ID", this.XS_ID);
		List<QJXX> list = qjxxService.findQJXXByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", qjxxService.getCountQJXX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public String checkData(){
		if((null == XSXM) || ("".equals(XSXM))){
			return "学生姓名不能为空";
		}
	
		if((null == XSXH) || ("".equals(XSXH))){
			return "学生学号不能为空";
		}
		
		if((null == QJKSSJ) || ("".equals(QJKSSJ))){
			return "请假开始时间不能为空";
		}
		
		if((null == QJJSJS) || ("".equals(QJJSJS))){
			return "请假结束时间不能为空";
		}
		
		if((null == JGXM) || ("".equals(JGXM))){
			return "填写请假信息的教师姓名不能为空";
		}
		
		if((null == JGGH) || ("".equals(JGGH))){
			return "填写请假信息的教师工号不能为空";
		}
		DateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateS = null;
		Date dateE = null;
		try {
			dateS=dd.parse(QJKSSJ);
			dateE=dd.parse(QJJSJS);
			if(dateS.equals(dateE)){
				return "请假开始时间和结束时间不能相同";
			}
			
			if(dateS.after(dateE)){
				return "请假开始时间不能比结束时间晚";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	public void addQJXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		String ChkRst = checkData();
		if (!"".equals(ChkRst)) {
			jsonMap.put("success", false);
			jsonMap.put("message", ChkRst);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		Xsxx xs = qjxxService.findXsxxByXh(XSXH);
		if(null == xs){
			jsonMap.put("success", false);
			jsonMap.put("message", "学号所对应的学生信息不存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if(!XSXM.equals(xs.getZsxm())){
			jsonMap.put("success", false);
			jsonMap.put("message", "学生学号与学生姓名不对应！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		JIAOGONG jg = qjxxService.findJIAOGONGByGh(JGGH);
		if(null == jg){
			jsonMap.put("success", false);
			jsonMap.put("message", "工号所对应的教师信息不存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if(!JGXM.equals(jg.getZSXM())){
			jsonMap.put("success", false);
			jsonMap.put("message", "教师工号与教师姓名不对应！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		QJXX t = new QJXX();
		t.setQJXX_ID(UUIDTools.getUUID());
		t.setXSXH(this.getXSXH());
		t.setXSXM(this.getXSXM());
		t.setJGGH(this.getJGGH());
		t.setJGXM(this.getJGXM());
		t.setXS_ID(xs.getXsId());
		t.setJG_ID(jg.getJG_ID());
		t.setQJKSSJ(QJKSSJ);
		t.setQJJSJS(QJJSJS);
		t.setMS(this.getMS());
		t.setBZ(this.getBZ());
		
		if (qjxxService.addQJXX(t)) {
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

	public void delQJXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != QJXX_ID) && !"".equals(QJXX_ID)) {
			if (qjxxService.delQJXX(QJXX_ID)) {
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

	public void updateQJXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(QJXX_ID) || null == QJXX_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		if ("".equals(QJKSSJ) || null == QJKSSJ) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请假的开始时间不能为空！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		if ("".equals(QJJSJS) || null == QJJSJS) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请假的结束时间不能为空！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		DateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateS = null;
		Date dateE = null;
		try {
			dateS=dd.parse(QJKSSJ);
			dateE=dd.parse(QJJSJS);
			if(dateS.equals(dateE)){
				jsonMap.put("success", false);
				jsonMap.put("message", "请假开始时间和结束时间不能相同！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
			
			if(dateS.after(dateE)){
				jsonMap.put("success", false);
				jsonMap.put("message", "请假开始时间不能比结束时间晚！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QJXX t = qjxxService.findQJXX(QJXX_ID);
		if(null == t){
			jsonMap.put("success", false);
			jsonMap.put("message", "请假记录查询错误！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateQJXX".equals(optionflag)) {
				t.setBZ(this.getBZ());
				t.setMS(this.getMS());
				t.setQJJSJS(this.getQJJSJS());
				t.setQJKSSJ(this.getQJKSSJ());
				if (qjxxService.updateQJXX(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(qjxxService.findQJXX(QJXX_ID),config));
	}

	public void listAllQJXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("BZ", this.BZ);
		params.put("XSXM", this.XSXM);
		params.put("JGXM", this.JGXM);
		params.put("JGGH", this.JGGH);
		params.put("DJRQ", this.DJRQ);
		params.put("JG_ID", this.JG_ID);
		params.put("XSXH", this.XSXH);
		params.put("XGRQ", this.XGRQ);
		params.put("MS", this.MS);
		params.put("QJJSJS", this.QJJSJS);
		params.put("QJKSSJ", this.QJKSSJ);
		params.put("XS_ID", this.XS_ID);
		List<QJXX> list = qjxxService.findQJXXByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}
	
	public void listJIAOGONGForQJ(){
		List<JIAOGONG> list = qjxxService.findJIAOGONGForQJ();
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getBZ() {
			return BZ;
		}
		public void setBZ(String BZ) {
			this.BZ = BZ;
		}

		public String getXSXM() {
			return XSXM;
		}
		public void setXSXM(String XSXM) {
			this.XSXM = XSXM;
		}

		public String getJGXM() {
			return JGXM;
		}
		public void setJGXM(String JGXM) {
			this.JGXM = JGXM;
		}

		public String getJGGH() {
			return JGGH;
		}
		public void setJGGH(String JGGH) {
			this.JGGH = JGGH;
		}

		public String getDJRQ() {
			return DJRQ;
		}
		public void setDJRQ(String DJRQ) {
			this.DJRQ = DJRQ;
		}

		public String getJG_ID() {
			return JG_ID;
		}
		public void setJG_ID(String JG_ID) {
			this.JG_ID = JG_ID;
		}

		public String getXSXH() {
			return XSXH;
		}
		public void setXSXH(String XSXH) {
			this.XSXH = XSXH;
		}

		public String getXGRQ() {
			return XGRQ;
		}
		public void setXGRQ(String XGRQ) {
			this.XGRQ = XGRQ;
		}

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getQJJSJS() {
			return QJJSJS;
		}
		public void setQJJSJS(String QJJSJS) {
			this.QJJSJS = QJJSJS;
		}

		public String getQJKSSJ() {
			return QJKSSJ;
		}
		public void setQJKSSJ(String QJKSSJ) {
			this.QJKSSJ = QJKSSJ;
		}

		public String getXS_ID() {
			return XS_ID;
		}
		public void setXS_ID(String XS_ID) {
			this.XS_ID = XS_ID;
		}

		public QJXXService getQJXX() {
			return qjxxService;
		}

		public void setqjxxService(QJXXService qjxxService) {
			this.qjxxService = qjxxService;
		}

		public String getQJXX_ID() {
			return QJXX_ID;
		}
		public void setQJXX_ID(String QJXX_ID) {
			this.QJXX_ID = QJXX_ID;
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