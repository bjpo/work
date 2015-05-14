package com.hrbsys.kechengxx.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.KECHENGXX;
import com.hrbsys.bean.KECHENGXXLB;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.kechengxx.service.KECHENGXXService;
import com.hrbsys.kechengxxlb.service.KECHENGXXLBService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.zhuanye.service.ZhuanYeService;
public class KECHENGXXAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private KECHENGXXService kechengxxService;
	private KECHENGXXLBService kechengxxlbService;
	private ZhuanYeService zhuanyeService;

	private String optionflag;
	private String KECHENGXX_ID; 
	private String KHFS;
	private String KECHENGMC;
	private String KSXQ;
	private String XSFP_SYSJ;
	private Integer YXXF;
	private String ZYMC;
	private String TMP6;
	private Integer KSXF;
	private String TMP5;
	private String TMP4;
	private String TMP3;
	private String TMP2;
	private String TMP1;
	private String KECHENGXXLB_ID;
	private String MS;
	private String KECHENGXXLBMC;
	private String XSFP_LLJX;
	private String ZY_ID;
	private String BZ;
	private String KECHENGDM;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listKECHENGXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		params.put("KHFS", this.KHFS);
		params.put("KECHENGMC", this.KECHENGMC);
		params.put("KSXQ", this.KSXQ);
		params.put("XSFP_SYSJ", this.XSFP_SYSJ);
//		params.put("YXXF", this.YXXF);
		params.put("ZYMC", this.ZYMC);
		params.put("TMP6", this.TMP6);
//		params.put("KSXF", this.KSXF);
		params.put("TMP5", this.TMP5);
		params.put("TMP4", this.TMP4);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("TMP1", this.TMP1);
		params.put("KECHENGXXLB_ID", this.KECHENGXXLB_ID);
		params.put("MS", this.MS);
		params.put("KECHENGXXLBMC", this.KECHENGXXLBMC);
		params.put("XSFP_LLJX", this.XSFP_LLJX);
		params.put("ZY_ID", this.ZY_ID);
		params.put("BZ", this.BZ);
		params.put("KECHENGDM", this.KECHENGDM);
		List<KECHENGXX> list = kechengxxService.findKECHENGXXByPageApp(start, number,params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", kechengxxService.getCountKECHENGXX(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	public void addKECHENGXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		KECHENGXX t = new KECHENGXX();
		t.setKECHENGXX_ID(UUIDTools.getUUID());
		t.setKHFS(this.getKHFS());
		t.setKECHENGMC(this.getKECHENGMC());
		t.setKSXQ(this.getKSXQ());
		t.setXSFP_SYSJ(this.getXSFP_SYSJ());
		t.setYXXF(this.getYXXF());
		t.setTMP6(this.getTMP6());
		t.setKSXF(this.getKSXF());
		t.setTMP5(this.getTMP5());
		t.setTMP4(this.getTMP4());
		t.setTMP3(this.getTMP3());
		t.setTMP2(this.getTMP2());
		t.setTMP1(this.getTMP1());
		t.setMS(this.getMS());
		//课程信息类别
		if(null!=this.getKECHENGXXLBMC()&&!"".equals(this.getKECHENGXXLBMC())){
			KECHENGXXLB kb=kechengxxlbService.findKECHENGXXLB(this.getKECHENGXXLBMC());
			if(null!=kb&&!"".equals(kb)){
				t.setKECHENGXXLB_ID(kb.getKECHENGXXLB_ID());
				t.setKECHENGXXLBMC(kb.getKECHENGXXLBMC());
			}
		}
		//专业
		if(null!=this.getZYMC()&&!"".equals(this.getZYMC())){
			ZHUANYE z=zhuanyeService.findZHUANYE(this.getZYMC());
			if(null!=z&&!"".equals(z)){
				t.setZY_ID(z.getZY_ID());
				t.setZYMC(z.getZYMC());
			}
		}
		
		t.setXSFP_LLJX(this.getXSFP_LLJX());
		t.setBZ(this.getBZ());
		t.setKECHENGDM(this.getKECHENGDM());
		if (kechengxxService.addKECHENGXX(t)) {
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

	public void delKECHENGXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != KECHENGXX_ID) && !"".equals(KECHENGXX_ID)) {
			if (kechengxxService.delKECHENGXX(KECHENGXX_ID)) {
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

	public void updateKECHENGXX() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(KECHENGXX_ID) || null == KECHENGXX_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		KECHENGXX t = new KECHENGXX();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateKECHENGXX".equals(optionflag)) {
				t.setKECHENGXX_ID(KECHENGXX_ID);
				t.setKHFS(this.getKHFS());
				t.setKECHENGMC(this.getKECHENGMC());
				t.setKSXQ(this.getKSXQ());
				t.setXSFP_SYSJ(this.getXSFP_SYSJ());
				t.setYXXF(this.getYXXF());
				t.setTMP6(this.getTMP6());
				t.setKSXF(this.getKSXF());
				t.setTMP5(this.getTMP5());
				t.setTMP4(this.getTMP4());
				t.setTMP3(this.getTMP3());
				t.setTMP2(this.getTMP2());
				t.setTMP1(this.getTMP1());
				//课程信息类别
				if(null!=this.getKECHENGXXLBMC()&&!"".equals(this.getKECHENGXXLBMC())){
					KECHENGXXLB kb=kechengxxlbService.findKECHENGXXLB(this.getKECHENGXXLBMC());
					if(null!=kb&&!"".equals(kb)){
						t.setKECHENGXXLB_ID(kb.getKECHENGXXLB_ID());
						t.setKECHENGXXLBMC(kb.getKECHENGXXLBMC());
					}
				}
				//专业
				if(null!=this.getZYMC()&&!"".equals(this.getZYMC())){
					ZHUANYE z=zhuanyeService.findZHUANYE(this.getZYMC());
					if(null!=z&&!"".equals(z)){
						t.setZY_ID(z.getZY_ID());
						t.setZYMC(z.getZYMC());
					}
				}
				t.setMS(this.getMS());
				t.setXSFP_LLJX(this.getXSFP_LLJX());
				t.setBZ(this.getBZ());
				t.setKECHENGDM(this.getKECHENGDM());
				if (kechengxxService.updateKECHENGXX(t)) {
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
		new JsonPrintTools().printJSON(JSONObject.fromObject(kechengxxService.findKECHENGXX(KECHENGXX_ID),config));
	}

	public void listAllKECHENGXX() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("KHFS", this.KHFS);
		params.put("KECHENGMC", this.KECHENGMC);
		params.put("KSXQ", this.KSXQ);
		params.put("XSFP_SYSJ", this.XSFP_SYSJ);
//		params.put("YXXF", this.YXXF);
		params.put("ZYMC", this.ZYMC);
		params.put("TMP6", this.TMP6);
//		params.put("KSXF", this.KSXF);
		params.put("TMP5", this.TMP5);
		params.put("TMP4", this.TMP4);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("TMP1", this.TMP1);
		params.put("KECHENGXXLB_ID", this.KECHENGXXLB_ID);
		params.put("MS", this.MS);
		params.put("KECHENGXXLBMC", this.KECHENGXXLBMC);
		params.put("XSFP_LLJX", this.XSFP_LLJX);
		params.put("ZY_ID", this.ZY_ID);
		params.put("BZ", this.BZ);
		params.put("KECHENGDM", this.KECHENGDM);
		List<KECHENGXX> list = kechengxxService.findKECHENGXXByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}

		public String getKHFS() {
			return KHFS;
		}
		public void setKHFS(String KHFS) {
			this.KHFS = KHFS;
		}

		public String getKECHENGMC() {
			return KECHENGMC;
		}
		public void setKECHENGMC(String KECHENGMC) {
			this.KECHENGMC = KECHENGMC;
		}

		public String getKSXQ() {
			return KSXQ;
		}
		public void setKSXQ(String KSXQ) {
			this.KSXQ = KSXQ;
		}

		public String getXSFP_SYSJ() {
			return XSFP_SYSJ;
		}
		public void setXSFP_SYSJ(String XSFP_SYSJ) {
			this.XSFP_SYSJ = XSFP_SYSJ;
		}

		public Integer getYXXF() {
			return YXXF;
		}
		public void setYXXF(Integer YXXF) {
			this.YXXF = YXXF;
		}

		public String getZYMC() {
			return ZYMC;
		}
		public void setZYMC(String ZYMC) {
			this.ZYMC = ZYMC;
		}

		public String getTMP6() {
			return TMP6;
		}
		public void setTMP6(String TMP6) {
			this.TMP6 = TMP6;
		}

		public Integer getKSXF() {
			return KSXF;
		}
		public void setKSXF(Integer KSXF) {
			this.KSXF = KSXF;
		}

		public String getTMP5() {
			return TMP5;
		}
		public void setTMP5(String TMP5) {
			this.TMP5 = TMP5;
		}

		public String getTMP4() {
			return TMP4;
		}
		public void setTMP4(String TMP4) {
			this.TMP4 = TMP4;
		}

		public String getTMP3() {
			return TMP3;
		}
		public void setTMP3(String TMP3) {
			this.TMP3 = TMP3;
		}

		public String getTMP2() {
			return TMP2;
		}
		public void setTMP2(String TMP2) {
			this.TMP2 = TMP2;
		}

		public String getTMP1() {
			return TMP1;
		}
		public void setTMP1(String TMP1) {
			this.TMP1 = TMP1;
		}

		public String getKECHENGXXLB_ID() {
			return KECHENGXXLB_ID;
		}
		public void setKECHENGXXLB_ID(String KECHENGXXLB_ID) {
			this.KECHENGXXLB_ID = KECHENGXXLB_ID;
		}

		public String getMS() {
			return MS;
		}
		public void setMS(String MS) {
			this.MS = MS;
		}

		public String getKECHENGXXLBMC() {
			return KECHENGXXLBMC;
		}
		public void setKECHENGXXLBMC(String KECHENGXXLBMC) {
			this.KECHENGXXLBMC = KECHENGXXLBMC;
		}

		public String getXSFP_LLJX() {
			return XSFP_LLJX;
		}
		public void setXSFP_LLJX(String XSFP_LLJX) {
			this.XSFP_LLJX = XSFP_LLJX;
		}

		public String getZY_ID() {
			return ZY_ID;
		}
		public void setZY_ID(String ZY_ID) {
			this.ZY_ID = ZY_ID;
		}

		public String getBZ() {
			return BZ;
		}
		public void setBZ(String BZ) {
			this.BZ = BZ;
		}

		public String getKECHENGDM() {
			return KECHENGDM;
		}
		public void setKECHENGDM(String KECHENGDM) {
			this.KECHENGDM = KECHENGDM;
		}

		public KECHENGXXService getKECHENGXX() {
			return kechengxxService;
		}

		public void setkechengxxService(KECHENGXXService kechengxxService) {
			this.kechengxxService = kechengxxService;
		}

		public String getKECHENGXX_ID() {
			return KECHENGXX_ID;
		}
		public void setKECHENGXX_ID(String KECHENGXX_ID) {
			this.KECHENGXX_ID = KECHENGXX_ID;
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
		public KECHENGXXLBService getKechengxxlbService() {
			return kechengxxlbService;
		}

		public void setKechengxxlbService(KECHENGXXLBService kechengxxlbService) {
			this.kechengxxlbService = kechengxxlbService;
		}
		public ZhuanYeService getZhuanyeService() {
			return zhuanyeService;
		}

		public void setZhuanyeService(ZhuanYeService zhuanyeService) {
			this.zhuanyeService = zhuanyeService;
		}

}