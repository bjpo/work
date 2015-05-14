package com.hrbsys.jiaogong.action;

import java.io.File;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.student.action.Base64;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.JIAOGONGLB;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.jiaogong.service.JIAOGONGService;
import com.hrbsys.jiaogonglb.service.JIAOGONGLBService;
import com.hrbsys.luzhiwen.service.LuzhiwenService;
import com.hrbsys.users.service.UserService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class JIAOGONGAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private JIAOGONGService jiaogongService;
	private JIAOGONGLBService jiaogonglbService;
	private LuzhiwenService luzhiwenService;
	private UserService userService;

	private String optionflag;
	private String JG_ID;
	private String JGMC;
	private String TMP6;
	private String TMP5;
	private String JTZZ;
	private String SHENGAO;
	private String TMP4;
	private String TMP3;
	private String TMP2;
	private String TMP1;
	private String SFZHM;
	private String MZ;
	private String JGLBMC;
	private String TIZHONG;
	private String ZP_ID;
	private String MS;
	private String JGLB_ID;
	private String ZW_ID;
	private String XUELI;
	private String BYYX;
	private String BZ;
	private String CSNY;
	private String XB;
	private String YH_ID;
	private String ZSXM;
	private String JGGH;
	private String zhiwenId1;
	private String zhiwenId2;
	private String savePath;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listJIAOGONG() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("JGMC", this.JGMC);
		params.put("TMP6", this.TMP6);
		params.put("TMP5", this.TMP5);
		params.put("JTZZ", this.JTZZ);
		params.put("SHENGAO", this.SHENGAO);
		params.put("TMP4", this.TMP4);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("TMP1", this.TMP1);
		params.put("SFZHM", this.SFZHM);
		params.put("MZ", this.MZ);
		params.put("JGLBMC", this.JGLBMC);
		params.put("TIZHONG", this.TIZHONG);
		params.put("ZP_ID", this.ZP_ID);
		params.put("MS", this.MS);
		params.put("JGLB_ID", this.JGLB_ID);
		params.put("ZW_ID", this.ZW_ID);
		params.put("XUELI", this.XUELI);
		params.put("BYYX", this.BYYX);
		params.put("BZ", this.BZ);
		params.put("CSNY", this.CSNY);
		params.put("XB", this.XB);
		params.put("YH_ID", this.YH_ID);
		params.put("ZSXM", this.ZSXM);
		params.put("JGGH", this.JGGH);
		// 从session中读取该用户有哪些操作权限：
		String pkqx = super.session.get("kechengbQX").toString(); // 获取排课权限
		String jgid = super.session.get("JIAOGONGID").toString();// 获取教工工号
		List<JIAOGONG> list = jiaogongService.findJIAOGONGByPageApp(start,
				number, params, order, sort, pkqx, jgid);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", jiaogongService.getCountJIAOGONG(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		System.out.println(list.size());
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addJIAOGONG() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JIAOGONG t = new JIAOGONG();
		t.setJG_ID(UUIDTools.getUUID());
		t.setJGMC(this.getJGMC());
		t.setTMP6(this.getTMP6());
		t.setTMP5(this.getTMP5());
		t.setJTZZ(this.getJTZZ());
		t.setSHENGAO(this.getSHENGAO());
		t.setTMP4(this.getTMP4());
		t.setTMP3(this.getTMP3());
		t.setTMP2(this.getTMP2());
		t.setTMP1(this.getTMP1());
		t.setSFZHM(this.getSFZHM());
		t.setMZ(this.getMZ());
		// t.setJGLBMC(this.getJGLBMC());
		if (null != this.getJGLBMC() && !"".equals(this.getJGLBMC())) {
			JIAOGONGLB jglb = jiaogonglbService
					.findJIAOGONGLB(this.getJGLBMC());
			t.setJGLB_ID(jglb.getJIAOGONGLB_ID());
			System.out.println(t.getJGLB_ID() + "-->" + jglb.getJIAOGONGLB_ID()
					+ "-->");
			// t.setJGLB_ID(this.getJGLBMC());
			t.setJGLBMC(jglb.getJIAOGONGLBMC());
		}
		t.setTIZHONG(this.getTIZHONG());
		t.setZP_ID(this.getZP_ID());
		t.setMS(this.getMS());
		t.setXUELI(this.getXUELI());
		t.setBYYX(this.getBYYX());
		t.setBZ(this.getBZ());
		t.setCSNY(this.getCSNY());
		t.setXB(this.getXB());
		t.setYH_ID(this.getYH_ID());
		t.setZSXM(this.getZSXM());
		t.setJGGH("L" + this.getJGGH());
		String jggh = t.getJGGH();
		// 判断教工信息添加是否成功
		if (jiaogongService.addJIAOGONG(t)) {
			// 教工信息添加成功之后，在用户表中创建一个教工用户
			YONGHU yh = new YONGHU();
			yh.setYhid(UUIDTools.getUUID());
			// 默认分配为学生用户组
			YONGHUZU yz = userService
					.findYHZ("592f084e-87c7-48de-87d6-752a29356c7c");
			yh.setYonghuzu(yz);
			yh.setYhm(jggh);
			yh.setYhmm(jggh);
			//教工插入用户ID begin
			t.setYH_ID(yh.getYhid());
			jiaogongService.updateJIAOGONG(t);
			//教工插入用户ID end
			// 判断向用户表中是否添加成功
			if (userService.addYONGHU(yh)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "信息新增成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}

		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "信息添加失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	public void delJIAOGONG() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != this.JG_ID) && !"".equals(this.JG_ID)) {
			String[] ids = this.JG_ID.split(",");
			for (int i = 0; i < ids.length; i++) {
				JIAOGONG jg = jiaogongService.findJIAOGONG(ids[i]);
				if (jiaogongService.delJIAOGONG(jg.getJG_ID())) {
					// 查询用户表是否有此用户
					YONGHU yh = userService.findYONGHUName(jg.getJGGH());
					// 判断用户是否为空
					if (yh != null) {
						// 如果不为空就进行删除
						if (userService.delYONGHU(yh.getYhid())) {
							// 给予用户提示信息
							jsonMap.put("success", true);
							jsonMap.put("message", "信息删除成功！");
							new JsonPrintTools().printJSON(JSONObject
									.fromObject(jsonMap));
						}
					}
					jsonMap.put("success", true);
					jsonMap.put("message", "信息删除成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "信息删除失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
				}
			}
		}
	}

	public void updateJIAOGONG() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(JG_ID) || null == JG_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JIAOGONG t = new JIAOGONG();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateJIAOGONG".equals(optionflag)) {
				t.setJG_ID(JG_ID);
				t.setJGMC(this.getJGMC());
				t.setTMP6(this.getTMP6());
				t.setTMP5(this.getTMP5());
				t.setJTZZ(this.getJTZZ());
				t.setSHENGAO(this.getSHENGAO());
				t.setTMP4(this.getTMP4());
				t.setTMP3(this.getTMP3());
				t.setTMP2(this.getTMP2());
				t.setTMP1(this.getTMP1());
				t.setSFZHM(this.getSFZHM());
				t.setMZ(this.getMZ());
				// t.setJGLBMC(this.getJGLBMC());
				t.setTIZHONG(this.getTIZHONG());
				t.setZP_ID(this.getZP_ID());
				t.setMS(this.getMS());
				// t.setJGLB_ID(this.getJGLB_ID());
				if (null != this.getJGLBMC() && !"".equals(this.getJGLBMC())) {
					JIAOGONGLB jglb = jiaogonglbService.findJIAOGONGLB(this
							.getJGLBMC());
					t.setJGLB_ID(jglb.getJIAOGONGLB_ID());
					t.setJGLBMC(jglb.getJIAOGONGLBMC());
				}
				t.setXUELI(this.getXUELI());
				t.setBYYX(this.getBYYX());
				t.setBZ(this.getBZ());
				t.setCSNY(this.getCSNY());
				t.setXB(this.getXB());
				t.setYH_ID(this.getYH_ID());
				t.setZSXM(this.getZSXM());
				t.setJGGH("L" + this.getJGGH());
				if (jiaogongService.updateJIAOGONG(t)) {
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
				jiaogongService.findJIAOGONG(JG_ID), config));
	}

	public void listAllJIAOGONG() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("JGMC", this.JGMC);
		params.put("TMP6", this.TMP6);
		params.put("TMP5", this.TMP5);
		params.put("JTZZ", this.JTZZ);
		params.put("SHENGAO", this.SHENGAO);
		params.put("TMP4", this.TMP4);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("TMP1", this.TMP1);
		params.put("SFZHM", this.SFZHM);
		params.put("MZ", this.MZ);
		params.put("JGLBMC", this.JGLBMC);
		params.put("TIZHONG", this.TIZHONG);
		params.put("ZP_ID", this.ZP_ID);
		params.put("MS", this.MS);
		params.put("JGLB_ID", this.JGLB_ID);
		params.put("ZW_ID", this.ZW_ID);
		params.put("XUELI", this.XUELI);
		params.put("BYYX", this.BYYX);
		params.put("BZ", this.BZ);
		params.put("CSNY", this.CSNY);
		params.put("XB", this.XB);
		params.put("YH_ID", this.YH_ID);
		params.put("ZSXM", this.ZSXM);
		params.put("JGGH", this.JGGH);

		// 从session中读取该用户有哪些操作权限：
		String pkqx = null==super.session.get("kechengbQX")?"0":super.session.get("kechengbQX").toString(); // 获取排课权限
		String jgid = super.session.get("JIAOGONGID").toString();// 获取教工工号
		List<JIAOGONG> list = jiaogongService.findJIAOGONGByPageApp(params,
				order, sort, pkqx, jgid);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/**
	 * 教工录入指纹
	 * 
	 * @return
	 */
	public void addJGZHIWEN() throws Exception {
		System.out.println("我进入到了addJGZHIWEN!");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 存放Name
		List list = new ArrayList();
		// 判断指纹1是不是为空
		if ("".equals(zhiwenId1) || "".equals(zhiwenId2)) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请录入指纹（两次）！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		// 通过教工ID查找到教工
		JIAOGONG jg = jiaogongService.findJIAOGONG(this.JG_ID);
		// 判断学生是不是为空
		if (null == jg) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未查到此教工信息！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		// 判断输入的指纹是否为空，如果为空则不执行
		if (zhiwenId1 != null && !"".equals(zhiwenId1)) {
			byte[] Dat1 = Base64.decode(zhiwenId1);
			String DatPath1 = getSavePath() + "//Dats//" + jg.getJGGH()
					+ "1.dat";
			// 截取指纹1文件的名字
			String[] str = DatPath1.split("//");
			String name1 = str[str.length - 1].toString();
			list.add(name1.substring(0, name1.length() - 4));
			FileOutputStream fDatout1 = new FileOutputStream(DatPath1);
			fDatout1.write(Dat1, 0, Dat1.length);
			// 对流进行关闭
			fDatout1.close();
			jg.setZHIWEN_ID1("有");
		}

		if (zhiwenId2 != null && !"".equals(zhiwenId2)) {
			byte[] Dat2 = Base64.decode(zhiwenId2);
			String DatPath2 = getSavePath() + "//Dats//" + jg.getJGGH()
					+ "2.dat";
			// 截取指纹2文件的名字
			String[] str = DatPath2.split("//");
			String name2 = str[str.length - 1].toString();
			list.add(name2.substring(0, name2.length() - 4));

			FileOutputStream fDatout2 = new FileOutputStream(DatPath2);
			fDatout2.write(Dat2, 0, Dat2.length);
			// 对流进行关闭
			fDatout2.close();
			jg.setZHIWEN_ID2("有");
		}

		if (jiaogongService.updateJIAOGONG(jg)) {
			// 改变指纹有无状态以及将文件名存储到数据库中
			for (int i = 0; i < list.size(); i++) {
				AddRecord(String.valueOf(list.get(i)), "dat", "Add");
				// AddRecord(xs.getXh(), "dat", "Add");
			}
			jsonMap.put("success", true);
			jsonMap.put("message", "指纹信息存储成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "指纹信息存储失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	public void AddRecord(String id, String type, String opt) {
		UPDATEVERSION uv = new UPDATEVERSION();
		uv.setID(UUIDTools.getUUID());
		uv.setNAME(id);
		uv.setOPT(opt);
		uv.setTYPE(type);
		luzhiwenService.RecordOptLog(uv);
	}

	/**
	 * 删除教工指纹
	 * 
	 * @throws Exception
	 */
	public void delJGZHIWEN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();

		// 通过教工ID查找到教工
		JIAOGONG jg = jiaogongService.findJIAOGONG(this.JG_ID);

		// 在查看一下指纹信息的文件有没有
		// 判断学生是不是为空
		if (null == jg) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未查到此教工信息！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			// 删除指纹之前，先查看一下指纹信息的状态
			if (" ".equals(jg.getZHIWEN_ID1())
					&& " ".equals(jg.getZHIWEN_ID2())) {
				jsonMap.put("success", false);
				jsonMap.put("message", "你还没有录入指纹，还不能进行删除！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			} else {
				// 获取指纹1文件所在的具体位置
				String tmpPath1 = getSavePath() + "//Dats//" + jg.getJGGH()
						+ "1.dat";
				File f1 = new File(tmpPath1);
				// 判断路径下是否存存在这个文件
				if (f1.exists()) {
					// 当文件删除失败时，给予提示
					if (!f1.delete()) {
						jsonMap.put("success", false);
						jsonMap.put("message", "文件删除失败！");
						new JsonPrintTools().printJSON(JSONObject.fromObject(
								jsonMap, config));
						return;
					} else {
						// 将指纹1的状态置空
						jg.setZHIWEN_ID1(" ");
					}
				}

				// 获取指纹2文件所在的具体位置
				String tmpPath2 = getSavePath() + "//Dats//" + jg.getJGGH()
						+ "2.dat";
				File f2 = new File(tmpPath2);
				// 判断指纹2的文件是否存在于当前这个路径下
				if (f2.exists()) {
					// 当文件删除失败时，给予提示
					if (!f2.delete()) {
						jsonMap.put("success", false);
						jsonMap.put("message", "文件删除失败！");
						new JsonPrintTools().printJSON(JSONObject.fromObject(
								jsonMap, config));
						return;
					} else {
						// 将指纹2的状态置空
						jg.setZHIWEN_ID2(" ");
					}
				}

				// 判断更新教工信息是否成功
				if (!jiaogongService.updateJIAOGONG(jg)) {
					jsonMap.put("success", false);
					jsonMap.put("message", "指纹信息删除失败！");
					new JsonPrintTools().printJSON(JSONObject.fromObject(
							jsonMap, config));
				} else {
					// 将当前的操作信息更新到数据库
					AddRecord(jg.getJGGH(), "dat", "Del");
					jsonMap.put("success", true);
					jsonMap.put("message", "指纹信息删除成功！");
					new JsonPrintTools().printJSON(JSONObject.fromObject(
							jsonMap, config));
				}
			}

		}

	}

	public String getJGMC() {
		return JGMC;
	}

	public void setJGMC(String JGMC) {
		this.JGMC = JGMC;
	}

	public String getTMP6() {
		return TMP6;
	}

	public void setTMP6(String TMP6) {
		this.TMP6 = TMP6;
	}

	public String getTMP5() {
		return TMP5;
	}

	public void setTMP5(String TMP5) {
		this.TMP5 = TMP5;
	}

	public String getJTZZ() {
		return JTZZ;
	}

	public void setJTZZ(String JTZZ) {
		this.JTZZ = JTZZ;
	}

	public String getSHENGAO() {
		return SHENGAO;
	}

	public void setSHENGAO(String SHENGAO) {
		this.SHENGAO = SHENGAO;
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

	public String getSFZHM() {
		return SFZHM;
	}

	public void setSFZHM(String SFZHM) {
		this.SFZHM = SFZHM;
	}

	public String getMZ() {
		return MZ;
	}

	public void setMZ(String MZ) {
		this.MZ = MZ;
	}

	public String getJGLBMC() {
		return JGLBMC;
	}

	public void setJGLBMC(String JGLBMC) {
		this.JGLBMC = JGLBMC;
	}

	public String getTIZHONG() {
		return TIZHONG;
	}

	public void setTIZHONG(String TIZHONG) {
		this.TIZHONG = TIZHONG;
	}

	public String getZP_ID() {
		return ZP_ID;
	}

	public void setZP_ID(String ZP_ID) {
		this.ZP_ID = ZP_ID;
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getJGLB_ID() {
		return JGLB_ID;
	}

	public void setJGLB_ID(String JGLB_ID) {
		this.JGLB_ID = JGLB_ID;
	}

	public String getZW_ID() {
		return ZW_ID;
	}

	public void setZW_ID(String ZW_ID) {
		this.ZW_ID = ZW_ID;
	}

	public String getXUELI() {
		return XUELI;
	}

	public void setXUELI(String XUELI) {
		this.XUELI = XUELI;
	}

	public String getBYYX() {
		return BYYX;
	}

	public void setBYYX(String BYYX) {
		this.BYYX = BYYX;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getCSNY() {
		return CSNY;
	}

	public void setCSNY(String CSNY) {
		this.CSNY = CSNY;
	}

	public String getXB() {
		return XB;
	}

	public void setXB(String XB) {
		this.XB = XB;
	}

	public String getYH_ID() {
		return YH_ID;
	}

	public void setYH_ID(String YH_ID) {
		this.YH_ID = YH_ID;
	}

	public String getZSXM() {
		return ZSXM;
	}

	public void setZSXM(String ZSXM) {
		this.ZSXM = ZSXM;
	}

	public String getJGGH() {
		return JGGH;
	}

	public void setJGGH(String JGGH) {
		this.JGGH = JGGH;
	}

	public JIAOGONGService getJIAOGONG() {
		return jiaogongService;
	}

	public void setjiaogongService(JIAOGONGService jiaogongService) {
		this.jiaogongService = jiaogongService;
	}

	public String getJG_ID() {
		return JG_ID;
	}

	public void setJG_ID(String JG_ID) {
		this.JG_ID = JG_ID;
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

	public JIAOGONGLBService getJiaogonglbService() {
		return jiaogonglbService;
	}

	public void setJiaogonglbService(JIAOGONGLBService jiaogonglbService) {
		this.jiaogonglbService = jiaogonglbService;
	}

	public String getZhiwenId1() {
		return zhiwenId1;
	}

	public void setZhiwenId1(String zhiwenId1) {
		this.zhiwenId1 = zhiwenId1;
	}

	public String getZhiwenId2() {
		return zhiwenId2;
	}

	public void setZhiwenId2(String zhiwenId2) {
		this.zhiwenId2 = zhiwenId2;
	}

	public JIAOGONGService getJiaogongService() {
		return jiaogongService;
	}

	public void setJiaogongService(JIAOGONGService jiaogongService) {
		this.jiaogongService = jiaogongService;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}