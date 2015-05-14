package com.hrbsys.tongji.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.banji.service.BANJIService;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.TONGJI;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.bean.XueShengKQ;
import com.hrbsys.jiaogong.service.JIAOGONGService;
import com.hrbsys.kechengb.service.KECHENGBService;
import com.hrbsys.keshi.service.KESHIService;
import com.hrbsys.kqxxbjxx.service.KQXX_BJXXService;
import com.hrbsys.kqxxxscq.service.KQXX_XSCQService;
import com.hrbsys.nianji.service.NIANJIService;
import com.hrbsys.realtime.service.RealTimeService;
import com.hrbsys.task.service.TaskService;
import com.hrbsys.tongji.service.TONGJIService;
import com.hrbsys.tools.BaseChangeTool;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.xueqi.service.XUEQIService;
import com.hrbsys.xuesheng.service.XsxxService;
import com.hrbsys.xueyuan.service.XueYuanService;
import com.hrbsys.zhuanye.service.ZhuanYeService;

public class TONGJIbyJGAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private TONGJIService tongjiService;
	private RealTimeService realTimeService;
	private KQXX_BJXXService kqxxbjxxService;
	private KQXX_XSCQService kcxxxscqService;
	private XUEQIService xueqiService;
	private XueYuanService xueyuanService;
	private ZhuanYeService zhuanyeService;
	private NIANJIService nianjiService;
	private BANJIService banjiService;
	private XsxxService xsxxService;
	private TaskService taskService;
	private KECHENGBService kechengbService;
	private JIAOGONGService jiaogongService;
	private KESHIService keshiService;
	public KESHIService getKeshiService() {
		return keshiService;
	}
	public void setKeshiService(KESHIService keshiService) {
		this.keshiService = keshiService;
	}
	public JIAOGONGService getJiaogongService() {
		return jiaogongService;
	}
	public void setJiaogongService(JIAOGONGService jiaogongService) {
		this.jiaogongService = jiaogongService;
	}
	private String optionflag;
	private String TONGJI_ID;
	private String XS_CD;
	private String XS_ID;
	private String XS_XM;
	private String QXRS;
	private String NJ_ID;
	private String CDRSBL;
	private String XS_XH;
	private String ZCCXRS;
	private String JS_JSMC;
	private String KSRQ;
	private String XS_QX;
	private String ZTRS;
	private String NJMC;
	private String YSKRS;
	private String JG_JGGH;
	private String XS_ZT;
	private String BJMC;
	private String BJ_ID;
	private String JG_JGMC;
	private String KCXX_KCMC;
	private String SKRQ;
	private String XINGQI;
	private String KCXX_ID;
	private String ZYMC;
	private String ZCCXBL;
	private String XY_ID;
	private String CQQK;
	private String ZTRSBL;
	private String ZHOU;
	private String KS_ID;
	private String XYMC;
	private String CDRS;
	private String XUEQI_ID;
	private String XS_ZCCX;
	private String JSRQ;
	private String QXRSBL;
	private String JS_ID;
	private String JG_ID;
	private String ZY_ID;
	private String riqi;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;
	//按照老师方式统计
	public void listTONGJIbyJG() throws Exception {
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		
		List<TONGJI> listtj = new ArrayList<TONGJI>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		JsonConfig config = new JsonConfig();
		dayin();
		List<TONGJI> all_tongji = new ArrayList<TONGJI>();
		
		if (null == this.getJG_ID() || "".equals(this.getJG_ID())) {
			log.info("老师ID为空，不统计！");
			//当没有数据时，调用此方法
			jsonOutPutNULL();
			return;
		}
		XUEQI xq =null;
		if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
			xq = xueqiService.findXUEQI(this.getXUEQI_ID());
			log.info("存在学期。。。");
		} else {
			xq = xueqiService.getCurrentXUEQI();
			log.info("没有学期。。。"+xq.getXQMC());
		}
		//获取课程表
		JIAOGONG jg=jiaogongService.findJIAOGONG(this.getJG_ID());
		List<KECHENGB> all_kcb=kechengbService.findeKECHENGBByJGIDandXUEQI(jg, xq,this.getKSRQ());
		/**
		 * 1、分当天、往天查询条件
		 * 2、循环该教师的查询学期的课程表
		 * 3、根据列表的课程表，找出相应的班级考勤计算结果、存入统计结果中。
		 * */
		// 如果开始日期不为空
		if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))|| ((null != this.getZHOU())&& (!"".equals(this.getZHOU()))|| (null != this.getXINGQI() && !"".equals(this.getXINGQI())) || (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())))) {
			log.info("分教师统计，往天查询...");
			// 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
			if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
				this.setJSRQ(this.getKSRQ());
			}
			all_tongji.clear(); // 清空原有数据
			for(KECHENGB kb:all_kcb){ //循环该教师的课程表、计算相应的考勤数据
				HashMap<String, Integer> mapkqxxbj = kqxxbjxxService.getBJXXKQXXbyJG( this.getJG_ID(), this.getKS_ID(), this.getKCXX_ID(), xq, this.getXINGQI(), this.getZHOU(), this.getKSRQ(), this.getJSRQ());
				TONGJI tj = new TONGJI();
				if(null!=mapkqxxbj){
					tj.setKCXX_ID(kb.getKCB_FXS_ID());//设置课程信息ID
					tj.setKCXX_KCMC(kb.getKCXXMC());//设置课程信息名称
					tj.setJG_ID(jg.getJG_ID());
					tj.setJG_JGGH(jg.getJGGH());
					tj.setJG_JGMC(jg.getJGMC());
					tj.setJS_ID(kb.getJS_ID());
					tj.setJS_JSMC(kb.getJSMC());
					tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
					tj.setQXRS(mapkqxxbj.get("qxrs").toString()); 
					tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
					tj.setCDRS(mapkqxxbj.get("cdrs").toString());
					tj.setZTRS(mapkqxxbj.get("ztrs").toString());
					log.info("::::::应出席人数："+mapkqxxbj.get("ycxrs").toString());
					log.info("::::::缺席人数：："+mapkqxxbj.get("qxrs").toString());
					log.info("::::::正常人数: ："+mapkqxxbj.get("zcrs").toString());
					log.info("::::::迟到人数：："+mapkqxxbj.get("cdrs").toString());
					log.info("::::::早退人数：："+mapkqxxbj.get("ztrs").toString());
				}
				all_tongji.add(tj); // 新增一个学院的统计信息
			}
			jsonMap.clear();
			jsonMap.put("total", all_tongji.size());
			jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
			jsonMap.put("page", intPage);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
			return;
		} else {
			log.info("按教师查询：进入当天实时查询");
			all_tongji.clear();// 清空原有数据
			for(KECHENGB kb:all_kcb){ //循环该教师的课程表、计算相应的考勤数据
				// 如果没有查询天，则为当天，当天考勤信息为实时调用。
				String tmpandParam = "";
				HashMap<String, Integer> mapkq = realTimeService.getKQQK(this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),kb.getKCB_ID(), null, tmpandParam, this.getNJ_ID(),this.getBJ_ID(),this.getZY_ID());
				TONGJI tj = new TONGJI();
				tj.setKCXX_ID(kb.getKCB_FXS_ID());//设置课程信息ID
				tj.setKCXX_KCMC(kb.getKCXXMC());//设置课程信息名称
				tj.setJG_ID(jg.getJG_ID());
				tj.setJG_JGGH(jg.getJGGH());
				tj.setJG_JGMC(jg.getJGMC());
				tj.setJS_ID(kb.getJS_ID());
				tj.setJS_JSMC(kb.getJSMC());
				tj.setYSKRS((mapkq.get("yingchuqin")).toString());
				tj.setQXRS((mapkq.get("queqin")).toString());
				tj.setZCCXRS((mapkq.get("zhengchang")).toString());
				tj.setCDRS((mapkq.get("chidao")).toString());
				tj.setZTRS((mapkq.get("zaotui")).toString());
				listtj.add(tj);
			}
			jsonMap.clear();
			jsonMap.put("total", listtj.size());
			jsonMap.put("rows", listtj);// rows键 存放每页记录 list
			jsonMap.put("page", intPage);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
		}
	}

	/*
	 * 如果传递的参数有为空的，则没有信息
	 * */
	public void jsonOutPutNULL(){
		List<TONGJI> listtj = new ArrayList<TONGJI>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		jsonMap.clear();
		TONGJI tj = new TONGJI();
		tj.setKCXX_ID("null");
		tj.setKCXX_KCMC("无记录");
		tj.setJG_JGMC("无记录");
		tj.setYSKRS("无记录");
		tj.setQXRS("无记录");
		tj.setZCCXRS("无记录");
		tj.setCDRS("无记录");
		tj.setZTRS("无记录");
		listtj.add(tj);
		jsonMap.put("total", listtj.size());
		jsonMap.put("rows", listtj);// rows键 存放每页记录 list
		jsonMap.put("page", 1);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
	}

	// 按教师统计：详细信息
	public void listTONGJIbyJGdetial() throws Exception {
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@1");
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		List<TONGJI> listtj = new ArrayList<TONGJI>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		JsonConfig config = new JsonConfig();
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		dayin();
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@3");
		List<TONGJI> all_tongji = new ArrayList<TONGJI>();
		if (null == this.getJG_ID() || "".equals(this.getJG_ID())) {
			log.info("老师ID为空，不统计！");
			
			return;
		}

		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@4");
		XUEQI xq =null;
		if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
			xq = xueqiService.findXUEQI(this.getXUEQI_ID());
			log.info("存在学期。。。");
		} else {
			xq = xueqiService.getCurrentXUEQI();
			log.info("没有学期。。。"+xq.getXQMC());
		}
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@5");
		//获取课程表
		JIAOGONG jg=jiaogongService.findJIAOGONG(this.getJG_ID());
//		List<KECHENGB> all_kcb=kechengbService.findeKECHENGBByJGIDandXUEQI(jg, xq,this.getKSRQ());
		if(null==this.getKCXX_ID()||"".equals(this.getKCXX_ID())){
			log.info("请求中没有课程表ID...不进行统计...");
			return ;
		}
		KECHENGB kb=kechengbService.findKECHENGB(this.getKCXX_ID());
		if(null==kb||"".equals(kb)){
			log.info("课程表中无此ID对应的课表信息...不进行统计...");
			return ;
		}

		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@6");
		/**
		 * 1、分当天、往天查询条件
		 * 2、循环该教师的查询学期的课程表
		 * 3、根据列表的课程表，找出相应的班级考勤计算结果、存入统计结果中。
		 * */
		// 如果开始日期不为空
		if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))|| ((null != this.getZHOU())&& (!"".equals(this.getZHOU()))|| (null != this.getXINGQI() && !"".equals(this.getXINGQI())) || (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())))) {
			log.info("分教师统计，往天查询...");
			// 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
			if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
				this.setJSRQ(this.getKSRQ());
			}
			all_tongji.clear(); // 清空原有数据
			List<KQXX_XSCQ> allxs=kcxxxscqService.findKQXXbyJGdetail(null,this.getJG_ID(),this.getKS_ID(),this.getKCXX_ID(), xq,this.getXINGQI(),this.getZHOU(),this.getKSRQ(),this.getJSRQ()); //获取相应的学生考勤信息
			for(KQXX_XSCQ k:allxs){
				TONGJI t=new TONGJI();
				t.setJG_ID(jg.getJG_ID());
				t.setJG_JGGH(jg.getJGGH());
				t.setJG_JGMC(jg.getJGMC());
				t.setKCXX_ID(k.getKCB_ID());
				t.setKCXX_KCMC(k.getKCMC());
				t.setKS_ID(k.getKESHI_ID());
				t.setKSMC(keshiService.findKESHI(k.getKESHI_ID()).getKSMC());
				t.setXY_ID(k.getXY_ID());
				t.setXYMC(xueyuanService.findXueYuanById(k.getXY_ID()).getXymc());
				t.setZY_ID(k.getZY_ID());
				t.setZYMC(zhuanyeService.findZHUANYE(k.getZY_ID()).getZYMC());
				t.setNJ_ID(k.getNJ_ID());
				t.setNJMC(nianjiService.findNIANJI(k.getNJ_ID()).getNJMC());
				t.setBJ_ID(k.getBJ_ID());
				t.setBJMC(banjiService.findBANJI(k.getBJ_ID()).getBJMC());
				t.setXS_ID(k.getXS_ID());
				t.setXS_XM(k.getXSXM());
				t.setXS_XH(k.getXSXH());
				t.setCQQK(k.getCQQK());
				t.setSKRQ(k.getSKSJ().toString());
				all_tongji.add(t);
			}
			jsonMap.clear();
			jsonMap.put("total", all_tongji.size());
			jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
			jsonMap.put("page", intPage);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@7_1");
			return;
		} else {
			log.info("按教师查询：进入当天实时查询");
			all_tongji.clear();// 清空原有数据

			// 如果没有查询天，则为当天，当天考勤信息为实时调用。
				String tmpandParam = ""; 
				List<XueShengKQ> allxk = realTimeService.getKQQK_XS(null,jg.getJG_ID(),this.getKS_ID(),kb.getKCB_ID(), tmpandParam, null,null,null);
				for(XueShengKQ x:allxk){
					TONGJI t=new TONGJI();
					t.setJG_ID(jg.getJG_ID());
					t.setJG_JGGH(jg.getJGGH());
					t.setJG_JGMC(jg.getJGMC());
					t.setKCXX_ID(x.getKcb_id());
					t.setKCXX_KCMC(x.getKcb_kcxxmc());
					t.setKS_ID(x.getKs_id());
					t.setKSMC(x.getKsmc());
					t.setXY_ID(x.getXy_id());
					t.setXYMC(x.getXymc());
					t.setZY_ID(x.getZy_id());
					t.setZYMC(x.getZymc());
					t.setNJ_ID(x.getNj_id());
					t.setNJMC(x.getNjmc());
					t.setBJ_ID(x.getBj_id());
					t.setBJMC(x.getBjmc());
					t.setXS_ID(x.getXs_id());
					t.setXS_XM(x.getXsxm());
					t.setXS_XH(x.getXuehao());
					t.setCQQK(x.getCqqk());
					t.setSKRQ(x.getCqqk());
					all_tongji.add(t);
					listtj.add(t);
				}
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@7_2");
			jsonMap.clear();
			jsonMap.put("total", listtj.size());
			jsonMap.put("rows", listtj);// rows键 存放每页记录 list
			jsonMap.put("page", intPage);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
		}
	}


	public String getXS_CD() {
		return XS_CD;
	}

	public void setXS_CD(String XS_CD) {
		this.XS_CD = XS_CD;
	}

	public String getXS_ID() {
		return XS_ID;
	}

	public void setXS_ID(String XS_ID) {
		this.XS_ID = XS_ID;
	}

	public String getXS_XM() {
		return XS_XM;
	}

	public void setXS_XM(String XS_XM) {
		this.XS_XM = XS_XM;
	}

	public String getQXRS() {
		return QXRS;
	}

	public void setQXRS(String QXRS) {
		this.QXRS = QXRS;
	}

	public String getNJ_ID() {
		return NJ_ID;
	}

	public void setNJ_ID(String NJ_ID) {
		this.NJ_ID = NJ_ID;
	}

	public String getCDRSBL() {
		return CDRSBL;
	}

	public void setCDRSBL(String CDRSBL) {
		this.CDRSBL = CDRSBL;
	}

	public String getXS_XH() {
		return XS_XH;
	}

	public void setXS_XH(String XS_XH) {
		this.XS_XH = XS_XH;
	}

	public String getZCCXRS() {
		return ZCCXRS;
	}

	public void setZCCXRS(String ZCCXRS) {
		this.ZCCXRS = ZCCXRS;
	}

	public String getJS_JSMC() {
		return JS_JSMC;
	}

	public void setJS_JSMC(String JS_JSMC) {
		this.JS_JSMC = JS_JSMC;
	}

	public String getKSRQ() {
		return KSRQ;
	}

	public void setKSRQ(String KSRQ) {
		this.KSRQ = KSRQ;
	}

	public String getXS_QX() {
		return XS_QX;
	}

	public void setXS_QX(String XS_QX) {
		this.XS_QX = XS_QX;
	}

	public String getZTRS() {
		return ZTRS;
	}

	public void setZTRS(String ZTRS) {
		this.ZTRS = ZTRS;
	}

	public String getNJMC() {
		return NJMC;
	}

	public void setNJMC(String NJMC) {
		this.NJMC = NJMC;
	}

	public String getYSKRS() {
		return YSKRS;
	}

	public void setYSKRS(String YSKRS) {
		this.YSKRS = YSKRS;
	}

	public String getJG_JGGH() {
		return JG_JGGH;
	}

	public void setJG_JGGH(String JG_JGGH) {
		this.JG_JGGH = JG_JGGH;
	}

	public String getXS_ZT() {
		return XS_ZT;
	}

	public void setXS_ZT(String XS_ZT) {
		this.XS_ZT = XS_ZT;
	}

	public String getBJMC() {
		return BJMC;
	}

	public void setBJMC(String BJMC) {
		this.BJMC = BJMC;
	}

	public String getBJ_ID() {
		return BJ_ID;
	}

	public void setBJ_ID(String BJ_ID) {
		this.BJ_ID = BJ_ID;
	}

	public String getJG_JGMC() {
		return JG_JGMC;
	}

	public void setJG_JGMC(String JG_JGMC) {
		this.JG_JGMC = JG_JGMC;
	}

	public String getKCXX_KCMC() {
		return KCXX_KCMC;
	}

	public void setKCXX_KCMC(String KCXX_KCMC) {
		this.KCXX_KCMC = KCXX_KCMC;
	}

	public String getSKRQ() {
		return SKRQ;
	}

	public void setSKRQ(String SKRQ) {
		this.SKRQ = SKRQ;
	}

	public String getXINGQI() {
		return XINGQI;
	}

	public void setXINGQI(String XINGQI) {
//		this.XINGQI = XINGQI;
		this.XINGQI=BaseChangeTool.getXingQibyXingQiXH(XINGQI);
	}

	public String getKCXX_ID() {
		return KCXX_ID;
	}

	public void setKCXX_ID(String KCXX_ID) {
		this.KCXX_ID = KCXX_ID;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String ZYMC) {
		this.ZYMC = ZYMC;
	}

	public String getZCCXBL() {
		return ZCCXBL;
	}

	public void setZCCXBL(String ZCCXBL) {
		this.ZCCXBL = ZCCXBL;
	}

	public String getXY_ID() {
		return XY_ID;
	}

	public void setXY_ID(String XY_ID) {
		this.XY_ID = XY_ID;
	}

	public String getCQQK() {
		return CQQK;
	}

	public void setCQQK(String CQQK) {
		this.CQQK = CQQK;
	}

	public String getZTRSBL() {
		return ZTRSBL;
	}

	public void setZTRSBL(String ZTRSBL) {
		this.ZTRSBL = ZTRSBL;
	}

	public String getZHOU() {
		return ZHOU;
	}

	public void setZHOU(String ZHOU) {
//		this.ZHOU = ZHOU;
		this.ZHOU=BaseChangeTool.changeZhouIDtoZHOU(ZHOU);
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String KS_ID) {
		this.KS_ID = KS_ID;
	}

	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String XYMC) {
		this.XYMC = XYMC;
	}

	public String getCDRS() {
		return CDRS;
	}

	public void setCDRS(String CDRS) {
		this.CDRS = CDRS;
	}

	public String getXUEQI_ID() {
		return XUEQI_ID;
	}

	public void setXUEQI_ID(String XUEQI_ID) {
		this.XUEQI_ID = XUEQI_ID;
	}

	public String getXS_ZCCX() {
		return XS_ZCCX;
	}

	public void setXS_ZCCX(String XS_ZCCX) {
		this.XS_ZCCX = XS_ZCCX;
	}

	public String getJSRQ() {
		return JSRQ;
	}

	public void setJSRQ(String JSRQ) {
		this.JSRQ = JSRQ;
	}

	public String getQXRSBL() {
		return QXRSBL;
	}

	public void setQXRSBL(String QXRSBL) {
		this.QXRSBL = QXRSBL;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	public String getJG_ID() {
		return JG_ID;
	}

	public void setJG_ID(String JG_ID) {
		this.JG_ID = JG_ID;
	}

	public String getZY_ID() {
		return ZY_ID;
	}

	public void setZY_ID(String ZY_ID) {
		this.ZY_ID = ZY_ID;
	}

	public TONGJIService getTONGJI() {
		return tongjiService;
	}

	public void settongjiService(TONGJIService tongjiService) {
		this.tongjiService = tongjiService;
	}

	public String getTONGJI_ID() {
		return TONGJI_ID;
	}

	public void setTONGJI_ID(String TONGJI_ID) {
		this.TONGJI_ID = TONGJI_ID;
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

	public RealTimeService getRealTimeService() {
		return realTimeService;
	}

	public void setRealTimeService(RealTimeService realTimeService) {
		this.realTimeService = realTimeService;
	}

	public KQXX_BJXXService getKqxxbjxxService() {
		return kqxxbjxxService;
	}

	public void setKqxxbjxxService(KQXX_BJXXService kqxxbjxxService) {
		this.kqxxbjxxService = kqxxbjxxService;
	}

	public XUEQIService getXueqiService() {
		return xueqiService;
	}

	public void setXueqiService(XUEQIService xueqiService) {
		this.xueqiService = xueqiService;
	}

	public XueYuanService getXueyuanService() {
		return xueyuanService;
	}

	public void setXueyuanService(XueYuanService xueyuanService) {
		this.xueyuanService = xueyuanService;
	}

	public ZhuanYeService getZhuanyeService() {
		return zhuanyeService;
	}

	public void setZhuanyeService(ZhuanYeService zhuanyeService) {
		this.zhuanyeService = zhuanyeService;
	}

	public NIANJIService getNianjiService() {
		return nianjiService;
	}

	public void setNianjiService(NIANJIService nianjiService) {
		this.nianjiService = nianjiService;
	}

	public BANJIService getBanjiService() {
		return banjiService;
	}

	public void setBanjiService(BANJIService banjiService) {
		this.banjiService = banjiService;
	}

	public KQXX_XSCQService getKcxxxscqService() {
		return kcxxxscqService;
	}

	public void setKcxxxscqService(KQXX_XSCQService kcxxxscqService) {
		this.kcxxxscqService = kcxxxscqService;
	}

	public XsxxService getXsxxService() {
		return xsxxService;
	}

	public void setXsxxService(XsxxService xsxxService) {
		this.xsxxService = xsxxService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	public KECHENGBService getKechengbService() {
		return kechengbService;
	}
	public void setKechengbService(KECHENGBService kechengbService) {
		this.kechengbService = kechengbService;
	}

	//后台打印输出传递的参数：
	public void dayin(){
		log.info("传入参数打印开始：----------------------------------------------------begin");
		log.info("教室ID是：" + this.getJS_ID());
		log.info("老师ID是：" + this.getJG_ID());
		log.info("课时ID是：" + this.getKS_ID());
		log.info("课程信息ID是：" + this.getKCXX_ID());
		log.info("学期ID是：" + this.getXUEQI_ID());
		log.info("星期ID是：" + this.getXINGQI());
		log.info("周ID是：" + this.getZHOU());
		log.info("开始日期是：" + this.getKSRQ());
		log.info("结束日期是：" + this.getJSRQ());
		log.info("专业ID是：" + this.getZY_ID());
		log.info("年级ID是：" + this.getNJ_ID());
		log.info("班级ID是：" + this.getBJ_ID());
		log.info("传入参数打印结束：------------------------------------------------------end");
	}
}



