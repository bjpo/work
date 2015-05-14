package com.hrbsys.zwlr.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.banji.service.BANJIService;
import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZWLRLB;
import com.hrbsys.bean.ZWLRMD;
import com.hrbsys.luzhiwen.service.LuzhiwenService;
import com.hrbsys.tools.BaseChangeTool;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.xuesheng.service.XsxxService;
import com.hrbsys.zwlr.service.ZWLRLBService;

/**
 * 指纹录入列表Action
 * 
 */
public class ZWLRLBAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	// 所用到的接口
	private ZWLRLBService zwlrlbService;
	private BANJIService banjiService;
	private XsxxService xsxxService;
	private StudentService stuService;
	private LuzhiwenService luzhiwenService;
	private String savePath;
	// 所用到的字段
	private String optionflag;// 状态标志
	private String ZWLR_ID;
	private String LBMC;
	private String SFLR;
	private String ZWLRSJ;
	private String LBCJSJ;
	private String BJ_ID;
	private String XUEHAO;
	private String XH;
	private String arrayXH;// 存放学生的数组
	private String delArrayXH;// 修改数据中的删除数组，用来存放学号
	private String LASTMODIFYTIME;// 最后的修改时间
	public int loc;// 数据查询的位置

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	/*********************************************** 指纹录入列表首页数据 ****************************************/
	// 查询出所有的指纹录入列表(进行分页)
	public void listZWLRLB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("LBMC", this.LBMC);
		params.put("SFLR", this.SFLR);
		params.put("ZWLRSJ", this.ZWLRSJ);
		params.put("LBCJSJ", this.LBCJSJ);
		// 每页的数据，放入list
		List<ZWLRLB> list = zwlrlbService.findZWLRLBByPageApp(start, number,
				params, order, sort);
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", zwlrlbService.getCountZWLRLB(params));
		// rows键 存放每页记录 list
		jsonMap.put("rows", list);
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	// 查询出所有的指纹录入列表(不进行分页)
	public void listAllZWLRLB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("LBMC", this.LBMC);
		params.put("SFLR", this.SFLR);
		params.put("ZWLRSJ", this.ZWLRSJ);
		params.put("LBCJSJ", this.LBCJSJ);
		List<ZWLRLB> list = zwlrlbService.findZWLRLBByPageApp(params, order,
				sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/*********************************************** 添加指纹列表标题 ********************************/
	public void addZWLRLB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 添加指纹录入列表名称之前，先去查询一下数据库中这个列表名称是否存在
		if (zwlrlbService.findZWLRLBId(this.LBMC) != null) {
			// 如果用户填写的标题存于数据库中
			jsonMap.put("success", false);
			jsonMap.put("message", "此标题以存在，请填写其他标题！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			ZWLRLB t = new ZWLRLB();
			t.setZWLR_ID(UUIDTools.getUUID());
			t.setLBMC(URLDecoder.decode(this.getLBMC(), "UTF-8"));
			t.setSFLR("否");
			t.setLBCJSJ(BaseChangeTool.getCurrentTime());
			t.setLASTMODIFYTIME(BaseChangeTool.getCurrentTime());
			// 如果用户填写的标题不存于数据库中
			if (zwlrlbService.addZWLRLB(t)) {
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

	/************************************************** 删除指纹录入列表标题 ************************************/
	public void delZWLRLB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 判断zwlr_id是否为空
		if ((null != ZWLR_ID) && !"".equals(ZWLR_ID)) {
			// 判断删除子表中的数据是否删除
			if (zwlrlbService.delZWLRLBAndZWLRMD(ZWLR_ID)) {
				// 判断主表中的数据是否删除
				if (zwlrlbService.delZWLRLB(ZWLR_ID)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "信息删除成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "信息删除失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
	}

	/**************************************************** 更新指纹录入列表 ***************************************/
	public void updateZWLRLB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// 判断指纹录入列表的id是否为空
		if ("".equals(this.ZWLR_ID) || null == this.ZWLR_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		ZWLRLB t = new ZWLRLB();
		// 判断标实是否为空
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateZWLRLB".equals(optionflag)) {
				t.setZWLR_ID(this.ZWLR_ID);
				t.setLBMC(new String(this.getLBMC().getBytes("iso-8859-1"),
						"UTF-8"));
				t.setSFLR(new String(this.getSFLR().getBytes("iso-8859-1"),
						"UTF-8"));
				t.setZWLRSJ(this.getZWLRSJ());
				t.setLBCJSJ(this.getLBCJSJ());
				t.setLASTMODIFYTIME(BaseChangeTool.getCurrentTime());
				if (zwlrlbService.updateZWLRLB(t)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改标题信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改标题信息失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}

		ZWLRMD update_zwlrmd = new ZWLRMD();
		// 判断前台传过来的optionflag标实是否为空
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateList".equals(optionflag)) {
				int total = 0;
				// 对前台传过来的学生的学号字符串进行分割
				String[] xh = this.arrayXH.split(",");
				for (int i = 0; i < xh.length; i++) {
					total++;
					// 看指纹录入名单中有没有
					if (zwlrlbService.findZwlrMdStu(xh[i]) != null
							&& !"".equals(zwlrlbService.findZwlrMdStu(xh[i]))) {
						// 如果有这条记录名单不变
						continue;
					} else {
						// 如果没有这条记录，就进行添加
						Xsxx xs = stuService.findStudentByXh(xh[i]);
						update_zwlrmd.setID(UUIDTools.getUUID());
						update_zwlrmd.setZWLRMD_ID(this.ZWLR_ID);
						update_zwlrmd.setXH(xs.getXh());
						update_zwlrmd.setZSXM(xs.getZsxm());
						update_zwlrmd.setXB(xs.getXb());
						update_zwlrmd.setZYMC(xs.getZYMC());
						update_zwlrmd.setNJ_ID(xs.getNjId());
						update_zwlrmd.setNJMC(xs.getNJMC());
						update_zwlrmd.setBJ_ID(xs.getBjId());
						update_zwlrmd.setBJMC(xs.getBJMC());
						update_zwlrmd.setXYMC(xs.getXYMC());
						// 对数据进行添加
						zwlrlbService.addZWLRMD(update_zwlrmd);
					}
				}

				// 判断学号的个数与遍历的个数是否相等
				if (xh.length == total) {
					ZWLRLB zwlrlb = zwlrlbService.findZWLRLB(this.ZWLR_ID);
					t.setZWLR_ID(this.ZWLR_ID);
					t.setLBCJSJ(zwlrlb.getLBCJSJ());
					t.setLBMC(URLDecoder.decode(zwlrlb.getLBMC(), "UTF-8"));
					t.setSFLR(URLDecoder.decode(zwlrlb.getSFLR(), "UTF-8"));
					t.setZWLRSJ(zwlrlb.getZWLRSJ());
					t.setLASTMODIFYTIME(BaseChangeTool.getCurrentTime());
					if (zwlrlbService.updateZWLRLB(t)) {
						jsonMap.put("success", true);
						jsonMap.put("message", "修改数据成功！");
						new JsonPrintTools().printJSON(JSONObject
								.fromObject(jsonMap));
						return;
					}
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改数据失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		// 查询出要修改的信息并返回到前台
		new JsonPrintTools().printJSON(JSONObject.fromObject(
				zwlrlbService.findZWLRLB(ZWLR_ID), config));
	}

	// 更新时，删除数据的方法
	public void update_del() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 修改时 判断delArrayXH是否为空，为空则返回
		if (this.delArrayXH == "") {
			return;
		}
		// 判断是否要有删除的学生的学号
		if ("".equals(this.delArrayXH) && null == this.delArrayXH) {
			// 如果为空，则没要删除的学生
			return;
		} else {
			String[] delXH = this.delArrayXH.split(",");
			for (int i = 0; i < delXH.length; i++) {
				if (zwlrlbService.delZWLRMD(delXH[i])) {
					jsonMap.put("success", true);
					jsonMap.put("messager", "修改数据信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
	}

	/**
	 * 更新时，查找指纹录入列表的标题， 看看这个标题在数据库中有没有
	 */
	public void listTitleId() {
		ZWLRLB zwlr = zwlrlbService.findZWLRLBId(this.LBMC);
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(zwlr));
	}

	/********************************************* 更新时 将学生信息添加到指纹录入名单中 *******************************************/
	public void stuToZWLRList() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 对前台传过来的学生的学号字符串进行分割
		String[] xh = arrayXH.split(",");
		// 统计插入的的数据条数
		int total = 0;
		// 进行遍历学生的学号
		for (int i = 0; i < xh.length; i++) {
			if (xh[i] == null || xh[i].equals("")) {
				// 如果学号有空的则继续执行
				continue;
			} else {
				// 判断ZWLR_ID是否为空
				if (this.ZWLR_ID == null) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请输入指纹录入列表标题！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					// 读取数组中的学号，在去查询学生的具体的信息
					Xsxx xsxx = stuService.findStudentByXh(xh[i]);
					// 将学生的信息存在到指纹录入名单中
					ZWLRMD zwlr = new ZWLRMD();
					zwlr.setID(UUIDTools.getUUID());
					zwlr.setZWLRMD_ID(this.ZWLR_ID);
					zwlr.setXH(xsxx.getXh());
					zwlr.setZSXM(xsxx.getZsxm());
					zwlr.setXB(xsxx.getXb());
					zwlr.setBJ_ID(xsxx.getBjId());
					zwlr.setBJMC(xsxx.getBJMC());
					zwlr.setNJ_ID(xsxx.getNjId());
					zwlr.setNJMC(xsxx.getNJMC());
					zwlr.setZYMC(xsxx.getZYMC());
					zwlr.setXYMC(xsxx.getXYMC());

					// 判断是否添加成功，添加成功，就total++
					if (zwlrlbService.addZWLRMD(zwlr)) {
						total = total + 1;
						// 判断数组的长度是否与i等，相等时表示添加完成，并提示“信息新增成功”
						if (xh.length == total) {
							jsonMap.put("success", true);
							jsonMap.put("message", "信息新增成功！");
							new JsonPrintTools().printJSON(JSONObject
									.fromObject(jsonMap));
							return;
						}
					} else {
						jsonMap.put("success", false);
						jsonMap.put("message", "信息新增失败！");
						new JsonPrintTools().printJSON(JSONObject
								.fromObject(jsonMap));
						return;
					}

				}
			}
		}
	}

	/**
	 * 获取属于修改名单中的学生
	 */
	public void updateInXs() {
		// 查询出属于这个名单中学生信息集合
		List queryList = zwlrlbService.updateInXs(this.ZWLR_ID);
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(queryList));
	}

	/**
	 * 获取不属于修改名单中的学生，并没有进行分配的的学生
	 */
	public void updateNotInXs() {
		List<Xsxx> list = zwlrlbService.updateNotInXs();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list));
	}

	/************************************* 根据班级下列表框中选择的内容，查询出属于此班级的学生 **************************/
	public void banJiForXS() {
		// jsonMap存储相关信息，返回前台进行显示
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 属于此班级学生的集合
		List<Xsxx> xsxx = stuService.findStudentByBanJi(this.BJ_ID);
		// 判断查询回来的数据集合是否为空
		if (xsxx.size() <= 0) {
			// 如果查询结结果没有数据，进行显示
			jsonMap.put("success", false);
			jsonMap.put("message", "不存在这个班级的学生");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			// 如果查询结果有数据，进行提示
			jsonMap.put("success", true);
			jsonMap.put("stuList", xsxx);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

	}

	/************************************ 指纹录入功能部分 *********************************/
	// 显示要录入指纹的总人数
	public void getCountZWLRMD() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ZWLRMD_ID", this.ZWLR_ID);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Integer> map = zwlrlbService.getCountZWLRMD(params);
		// 指纹录入名单中录入指纹的人数
		jsonMap.put("zwlrrsCount", map.get("zwlrrsCount"));
		// 指纹录入名单中的总人数
		jsonMap.put("totalCount", map.get("totalCount"));

		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 指纹录入功能 查询回来两条数据
	// 初始化数据显示的条数
	public void findTwoData() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != this.optionflag) && !"".equals(this.optionflag)) {
			if ("initFindTwoData".equals(this.optionflag)) {
				List list = zwlrlbService.findTwoData(this.ZWLR_ID, this.loc);
				jsonMap.put("list", list);
				jsonMap.put("loc", loc);
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
		// 获取的数据为当前数据的下一条
		List list = zwlrlbService.nextData(this.ZWLR_ID, loc, ++loc);
		jsonMap.put("list", list);// 获取的数据
		jsonMap.put("loc", loc);// 获取数据查询到第几条
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));

	}

	/**
	 * 查询出指纹录入名单中，没有录入指纹的学生信息
	 */
	public void notFP() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Xsxx> list = zwlrlbService.notFP(this.ZWLR_ID);
		jsonMap.put("list", list);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	/**
	 * 批量删除学生的指纹信息
	 * 
	 * @throws Exception
	 */
	public void delStuZHIWEN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		String[] delXH = this.delArrayXH.split(",");
		// 遍历删除指纹信息的学生的学号
		for (String xh : delXH) {
			// 根据学号去查询相应的学生
			Xsxx xs = stuService.findStudentByXh(xh);
			String tmpPath1 = getSavePath() + "//Dats//" + xs.getXh() + "1.dat";
			File f1 = new File(tmpPath1);

			if (f1.exists()) {
				if (!f1.delete()) {
					jsonMap.put("success", false);
					jsonMap.put("message", "文件删除失败！");
					new JsonPrintTools().printJSON(JSONObject.fromObject(
							jsonMap, config));
					return;
				}
			}
			xs.setZhiwenId1("");
			String tmpPath2 = getSavePath() + "//Dats//" + xs.getXh() + "2.dat";
			File f2 = new File(tmpPath2);
			// 判断文件是否存在
			if (f2.exists()) {
				if (!f2.delete()) {
					jsonMap.put("success", false);
					jsonMap.put("message", "文件删除失败！");
					new JsonPrintTools().printJSON(JSONObject.fromObject(
							jsonMap, config));
					return;
				}
			}
			xs.setZhiwenId2("");
			if (!stuService.updateStudent(xs)) {
				jsonMap.put("success", false);
				jsonMap.put("message", "文件删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
				return;
			}
			AddRecord(xs.getXh(), "dat", "Del");
		}
		jsonMap.put("success", true);
		jsonMap.put("message", "删除指纹成功！");
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	// 将操作记录插入到数据库中
	public void AddRecord(String id, String type, String opt) {
		UPDATEVERSION uv = new UPDATEVERSION();
		uv.setID(UUIDTools.getUUID());
		uv.setNAME(id);
		uv.setOPT(opt);
		uv.setTYPE(type);
		luzhiwenService.RecordOptLog(uv);
	}

	/**
	 * get set 方法
	 * 
	 * @return
	 */

	public String getLBMC() {
		return LBMC;
	}

	public void setLBMC(String LBMC) {
		this.LBMC = LBMC;
	}

	public String getSFLR() {
		return SFLR;
	}

	public void setSFLR(String SFLR) {
		this.SFLR = SFLR;
	}

	public String getZWLRSJ() {
		return ZWLRSJ;
	}

	public void setZWLRSJ(String ZWLRSJ) {
		this.ZWLRSJ = ZWLRSJ;
	}

	public String getLBCJSJ() {
		return LBCJSJ;
	}

	public void setLBCJSJ(String LBCJSJ) {
		this.LBCJSJ = LBCJSJ;
	}

	public ZWLRLBService getZWLRLB() {
		return zwlrlbService;
	}

	public void setzwlrlbService(ZWLRLBService zwlrlbService) {
		this.zwlrlbService = zwlrlbService;
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

	public String getBJ_ID() {
		return BJ_ID;
	}

	public void setBJ_ID(String bJ_ID) {
		BJ_ID = bJ_ID;
	}

	public String getXUEHAO() {
		return XUEHAO;
	}

	public void setXUEHAO(String xUEHAO) {
		XUEHAO = xUEHAO;
	}

	public BANJIService getBanjiService() {
		return banjiService;
	}

	public void setBanjiService(BANJIService banjiService) {
		this.banjiService = banjiService;
	}

	public XsxxService getXsxxService() {
		return xsxxService;
	}

	public void setXsxxService(XsxxService xsxxService) {
		this.xsxxService = xsxxService;
	}

	public StudentService getStuService() {
		return stuService;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}

	public ZWLRLBService getZwlrlbService() {
		return zwlrlbService;
	}

	public void setZwlrlbService(ZWLRLBService zwlrlbService) {
		this.zwlrlbService = zwlrlbService;
	}

	public String getZWLR_ID() {
		return ZWLR_ID;
	}

	public void setZWLR_ID(String zWLR_ID) {
		ZWLR_ID = zWLR_ID;
	}

	public String getArrayXH() {
		return arrayXH;
	}

	public void setArrayXH(String arrayXH) {
		this.arrayXH = arrayXH;
	}

	public String getXH() {
		return XH;
	}

	public void setXH(String xH) {
		XH = xH;
	}

	public String getDelArrayXH() {
		return delArrayXH;
	}

	public void setDelArrayXH(String delArrayXH) {
		this.delArrayXH = delArrayXH;
	}

	public String getLASTMODIFYTIME() {
		return LASTMODIFYTIME;
	}

	public void setLASTMODIFYTIME(String lASTMODIFYTIME) {
		LASTMODIFYTIME = lASTMODIFYTIME;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public LuzhiwenService getLuzhiwenService() {
		return luzhiwenService;
	}

	public void setLuzhiwenService(LuzhiwenService luzhiwenService) {
		this.luzhiwenService = luzhiwenService;
	}

}