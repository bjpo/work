package com.hrbsys.xsxk.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.SKXSXX;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.kechengb.service.KECHENGBService;
import com.hrbsys.skxsxx.service.SKXSXXService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.xsxk.service.XsXuanKeService;
import com.hrbsys.xuesheng.service.XsxxService;

public class XsXuanKeAction extends ActionBase {
	private XsXuanKeService xsxkService;
	private XsxxService xsxxService;
	private SKXSXXService skxsxxService;
	private KECHENGBService kechengbService;
	private String KCB_ID;// 课程表编号
	private String KCBLB;// 课程表类别
	private String strClassTime;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	private List<KECHENGB> list;
	// 排序相关
	private String order;
	private String sort;
	/**
	 * 可选课信息列表
	 * @throws Exception
	 */
	public void listXSXK() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("KCB_ID", this.KCB_ID);
		params.put("KCBLB", this.KCBLB);
//		list = xsxkService.findXSXKByPageApp(start, number, params, order, sort);// 每页的数据，放入list
		list=xsxkService.findGGK_XSXKKCByPage(start, number, params, order, sort);
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", list.size());
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	/**
	 * 获取数据不进行分页
	 * 
	 * @return
	 */
	public void listAllXSXK() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("KCB_ID", this.KCB_ID);
		List<KECHENGB> list = xsxkService
				.findXSXKByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/**
	 * 学生选课
	 * */
	public void xuanke() throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		log.info(this.getKCB_ID());
		//如果课程表ID为空则不继续进行
		if(null==this.getKCB_ID()||"".equals(this.getKCB_ID())){
			log.info("课程表ID为空");
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择要上的选修课课程!");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return ;
		}
		/**
		 * 1、判断该用户是否存在学生用户.
		 * 2、判断是否选课已满.
		 * 3、判断该学生用户是否有课程与所选课程相冲突.
		 * 3、选课：插入到SKXSXX表中.
		 * 4、更新课程表中已选课人数numselected字段.
		 * */
		YONGHU y=(YONGHU) super.session.get("yonghu");
		log.info(y.getYhm());
		Xsxx xs=xsxxService.findStudentByYhId(y.getYhid());
		log.info(xs.getXsId());
		if(null==xs||"".equals(xs)){
			log.info("无学生账户信息");
			jsonMap.put("success", false);
			jsonMap.put("message", "对不起，该登录用户不是学生，不能选择选修课程!");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return ;
		}
		List<KECHENGB> allkb=kechengbService.findKECHENGBSbyKCBID(this.getKCB_ID()); //获取课程表
		if(allkb.size()<1){
			log.info("查无此课程表");			
			jsonMap.put("success", false);
			jsonMap.put("message", "对不起，没有查询到您要选修的课程，请重新选择!");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return ;
		}
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("KCB_ID",this.getKCB_ID());
		int yxrs=skxsxxService.getCountSKXS(map); //获取上这门课的学生已选人数
		if(yxrs>Integer.parseInt(allkb.get(0).getCAPACITY())-1){//如果已报人数数量>总人数-1，则提示不让填报。
			log.info("此课程填报学生已满");
			jsonMap.put("success", false);
			jsonMap.put("message", "对不起，选课失败。您所要选择的课程学生已满!");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return ;
		}
		/////////////////////////////////查询该学生是否存在有冲突的课程:上课时间
		if(xsxkService.isSameTime(this.getKCB_ID(),xs.getXsId())){
			log.info("学生上课时间有冲突");
			jsonMap.put("success", false);
			jsonMap.put("message", "对不起，选课失败。您有其他课程与该课程时间冲突！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return ;
		}
		//判断学生是否已经选择了此课程
		if(skxsxxService.isSelectedKC(xs.getXsId(),this.getKCB_ID())){
			log.info("已经选择了此课程，无需在进行选择。");
			jsonMap.put("success", false);
			jsonMap.put("message", "您好，您已经选择此课程，无需再次选择！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return ;
		}
		//学生选课
		for(KECHENGB k:allkb){
			SKXSXX sx=new SKXSXX();
			sx.setKCXS_ID(UUIDTools.getUUID());
			sx.setKCB_ID(k.getKCB_ID());
			sx.setKCB_FXS_ID(k.getKCB_FXS_ID());
			sx.setKCB_KCXXMC(k.getKCXXMC());
			sx.setXS_ID(xs.getXsId());
			sx.setXSXM(xs.getZsxm());
			sx.setXUEHAO(xs.getXh());
			skxsxxService.addSKXSXX(sx);
			int tmp=yxrs+1;
			k.setNUMSELECTED(tmp+"");
			log.info(k.getKCXXMC()+":::选课人数：yxrs:::"+yxrs+" --> "+tmp+" --> "+k.getNUMSELECTED());
			kechengbService.updateKECHENGB(k);
		}
		////打印输出选课成功
		jsonMap.put("success", true);
		jsonMap.put("message", "选课成功!");
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}
	
	public void addKcXX() {

	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String kCB_ID) {
		KCB_ID = kCB_ID;
	}

	public String getKCBLB() {
		return KCBLB;
	}

	public void setKCBLB(String kCBLB) {
		KCBLB = kCBLB;
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

	public XsXuanKeService getXsxkService() {
		return xsxkService;
	}

	public void setXsxkService(XsXuanKeService xsxkService) {
		this.xsxkService = xsxkService;
	}
	public XsxxService getXsxxService() {
		return xsxxService;
	}

	public void setXsxxService(XsxxService xsxxService) {
		this.xsxxService = xsxxService;
	}
	
	public SKXSXXService getSkxsxxService() {
		return skxsxxService;
	}
	public void setSkxsxxService(SKXSXXService skxsxxService) {
		this.skxsxxService = skxsxxService;
	}
	public List<KECHENGB> getList() {
		return list;
	}
	public void setList(List<KECHENGB> list) {
		this.list = list;
	}
	public String getStrClassTime() {
		return strClassTime;
	}
	public void setStrClassTime(String strClassTime) {
		this.strClassTime = strClassTime;
	}
	public KECHENGBService getKechengbService() {
		return kechengbService;
	}
	public void setKechengbService(KECHENGBService kechengbService) {
		this.kechengbService = kechengbService;
	}
}
