package com.hrbsys.basicinfo.student.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.tools.Validator;
import com.hrbsys.users.service.UserService;

public class StudentAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	private StudentService studentService;
	private UserService userService;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	private File imgfile;// 上传文件域
	private static final int BUFFER_SIZE = 1024 * 1024;
	private String contentType;// 文件类型
	private String fileName;// 文件名
	private String savePath;// 文件配置值
	private String xsId;
	private String xh;
	private String zsxm;
	private String cym;
	private String xb;
	private String sfzhm;
	private String csny;
	private String mz;
	private String rtsj;
	private String csd;
	private String hjlb;
	private String sushehao;
	private String ssdh;
	private String gatdm;
	private String lxr;
	private String yzbm;
	private String dahh;
	private String dayh;
	private String dzyx;
	private String lxdz;
	private String lxdh;
	private String zsjj;
	private String zkzh;
	private String ksh;
	private String kslb;
	private String jkzk;
	private String kstz;
	private String rxwhcd;
	private String sysf;
	private String bylb;
	private String sydq;
	private String kldm;
	private String rxcj;
	private String xxtj;
	private String tc;
	private String rxsj;
	private String zyIdLq;
	private String xz;
	private String rxnf;
	private String rxfs;
	private String pylb;
	private String pydx;
	private String pycc;
	private String bxfs;
	private String xxnx;
	private String qtbxxs;
	private String zxwyyz;
	private String zxwyjb;
	private String bxlx;
	private String byzx;
	private String sg;
	private String tz;
	private String jsjnldj;
	private String jtzz;
	private String yhId;
	private String xyId;
	private String zyId;
	private String njId;
	private String bjId;
	private String zhaopianId;

	/** action *****************************************************************************/
	// 列表
	public void listStudent() {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("xh", xh);
		params.put("zsxm", zsxm);

		List<Xsxx> list = studentService.findStudentByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		jsonMap.put("total", studentService.getCountStudent(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	public String CheckStundentData() {
		Validator validator = new Validator();
		if (null == xh && "".equals(xh)) {
			return "请输入学号";
		}
		if (null != zsxm && !"".equals(zsxm)) {
			if (!validator.checkChinese(zsxm)) {
				return "姓名请输入中文名字！";
			}
		}

		// if (null != cym && !"".equals(cym)) {
		// if (!validator.checkChinese(cym)) {
		// return "曾用名请输入中文名字！";
		// }
		// }

		if (null != xb && !"".equals(xb)) {
			if (!"男".equals(xb) && !"女".equals(xb)) {
				return "性别项填写错误！";
			}
		}

		// if (null != sfzhm && !"".equals(sfzhm)) {
		// if (!validator.checkIdCard(sfzhm)) {
		// return "身份证填写错误！";
		// }
		// }
		//
		// if (null != csny && !"".equals(csny)) {
		// if (!validator.checkDate1(csny)) {
		// return "出生年月填写错误";
		// }
		// }

		// if (null != mz && !"".equals(mz)) {
		// if (!validator.checkEthnic(mz)) {
		// return "民族填写错误";
		// }
		// }

		// if (null != hjlb && !"".equals(hjlb)) {
		// if (!hjlb.equals("非农业户口") && !hjlb.equals("农业户口")) {
		// return "户籍类别填写错误！";
		// }
		// }

		// if (null != sushehao && !"".equals(sushehao)) {
		// if (!validator.checkPInteger(sushehao)) {
		// return "宿舍号填写错误";
		// }
		// }

		// if (null != ssdh && !"".equals(ssdh)) {
		// if (!validator.checkTel(ssdh)) {
		// return "宿舍电话填写错误(0451-88888888)";
		// }
		// }
		//
		// if (null != yzbm && !"".equals(yzbm)) {
		// if (!validator.checkZipCode(yzbm)) {
		// return "邮政编码填写错误";
		// }
		// }

		// if (null != dahh && !"".equals(dahh)) {
		// if (!validator.checkPInteger(dahh)) {
		// return "档案行号填写错误";
		// }
		// }
		//
		// if (null != dayh && !"".equals(dayh)) {
		// if (!validator.checkPInteger(dayh)) {
		// return "档案页号填写错误";
		// }
		// }

		// if (null != dzyx && !"".equals(dzyx)) {
		// if (!validator.checkEmail(dzyx)) {
		// return "电子邮箱填写错误";
		// }
		// }

		// if (null != lxdh && !"".equals(lxdh)) {
		// if (!validator.checkTel(lxdh)) {
		// return "联系电话填写错误(0451-88888888)";
		// }
		// }
		//
		// if (null != zsjj && !"".equals(zsjj)) {
		// if (!"春季".equals(zsjj) && !"秋季".equals(zsjj)) {
		// return "招生季节填写错误";
		// }
		// }
		//
		// if (null != zkzh && !"".equals(zkzh)) {
		// if (!validator.checkPInteger(zkzh)) {
		// return "准考证号填写错误";
		// }
		// }
		//
		// if (null != ksh && !"".equals(ksh)) {
		// if (!validator.checkPInteger(ksh)) {
		// return "考生号填写错误";
		// }
		// }
		//
		// if (null != kslb && !"".equals(kslb)) {
		// if (!"统招".equals(kslb) && !"自考".equals(kslb) && !"成人".equals(kslb) &&
		// !"函授".equals(kslb)) {
		// return "考生类别填写错误";
		// }
		// }
		//
		// if (null != sysf && !"".equals(sysf)) {
		// if (!validator.checkProvice(sysf)) {
		// return "生源省份填写错误";
		// }
		// }
		//
		// if (null != sysf && !"".equals(bylb)) {
		// if (!Validator.checkGraduating(bylb)) {
		// return "毕业类别填写错误";
		// }
		// }
		//
		// if (null != rxcj && !"".equals(rxcj)) {
		// if (!validator.checkPFloat(rxcj)) {
		// return "入学成绩格式错误";
		// }
		// }
		//
		// if (null != rxsj && !"".equals(rxsj)) {
		// if (!validator.checkDate1(rxsj)) {
		// return "入学时间填写错误";
		// }
		// }
		//
		// if (null != xz && !"".equals(xz)) {
		// if (!"两年制".equals(xz) && !"三年制".equals(xz) && !"四年制".equals(xz) &&
		// !"五年制".equals(xz)) {
		// return "学制填写错误";
		// }
		// }
		//
		// if (null != rxnf && !"".equals(rxnf)) {
		// if (!validator.checkDate1(rxnf)) {
		// return "入学年份填写错误";
		// }
		// }
		//
		// if (null != rxfs && !"".equals(rxfs)) {
		// if (!"考试".equals(rxfs) && !"推荐".equals(rxfs) && !"测验".equals(rxfs) &&
		// !"面谈".equals(rxfs)) {
		// return "入学方式填写错误";
		// }
		// }
		//
		// if (null != pylb && !"".equals(pylb)) {
		// if (!"国家计划外自筹经费".equals(pylb) && !"国家计划内定向培养".equals(pylb) &&
		// !"国家计划外委托培养".equals(pylb)) {
		// return "培养类别填写错误";
		// }
		// }
		//
		// if (null != pycc && !"".equals(pycc)) {
		// if (!"博士".equals(pycc) && !"硕士".equals(pycc) && !"本科".equals(pycc) &&
		// !"大专".equals(pycc) && !"中专".equals(pycc)) {
		// return "培养层次填写错误";
		// }
		// }
		//
		// if (null != sg && !"".equals(sg)) {
		// if (!validator.checkPFloat(sg)) {
		// return "身高格式错误";
		// }
		// }
		//
		// if (null != tz && !"".equals(tz)) {
		// if (!validator.checkPFloat(tz)) {
		// return "体重格式错误";
		// }
		// }

		return "";
	}

	public void AddRecord(String id, String type, String opt) {
		UPDATEVERSION uv = new UPDATEVERSION();
		uv.setID(UUIDTools.getUUID());
		uv.setNAME(id);
		uv.setOPT(opt);
		uv.setTYPE(type);
		studentService.RecordOptLog(uv);
	}

	public void AddPic(String PicPath) {
		File f1 = new File(PicPath);
		try {
			FileInputStream fin = new FileInputStream(getImgfile());
			FileOutputStream fout = new FileOutputStream(PicPath);
			do {
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while ((len = fin.read(buffer)) > 0) {
					fout.write(buffer, 0, len);
				}
			} while (!f1.exists());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 添加学生信息:
	public void addStudent() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String ChkRst = CheckStundentData();
		if (!"".equals(ChkRst)) {
			jsonMap.put("success", false);
			jsonMap.put("message", ChkRst);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if (null != studentService.findStudentByXh(xh)) {
			jsonMap.put("success", false);
			jsonMap.put("message", "该学号信息已存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		Xsxx xs = new Xsxx();
		if (null != getImgfile()) {
			try {
				String tmpPath = getSavePath() + "//Pictures//" + xh + ".jpg";
				// FileInputStream fin = new FileInputStream(getImgfile());
				//
				// FileOutputStream fout = new FileOutputStream(tmpPath);
				// byte[] buffer = new byte[BUFFER_SIZE];
				// int len = 0;
				// while ((len = fin.read(buffer)) > 0) {
				// fout.write(buffer, 0, len);
				// }
				AddPic(tmpPath);
				AddRecord(xh, "jpg", "Add");
				xs.setZhaopianId("已存在");
			} catch (Exception e) {
				e.printStackTrace();
				jsonMap.put("success", false);
				jsonMap.put("message", "照片上传失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}

		xs.setXsId(UUIDTools.getUUID());
		xs.setXh(xh);
		xs.setZsxm(zsxm);
		xs.setCym(cym);
		xs.setXb(xb);
		xs.setSfzhm(sfzhm);
		xs.setCsny(csny);
		xs.setMz(mz);
		xs.setRtsj(rtsj);
		xs.setCsd(csd);
		xs.setHjlb(hjlb);
		xs.setSushehao(sushehao);
		xs.setSsdh(ssdh);
		xs.setGatdm(gatdm);
		xs.setLxr(lxr);
		xs.setYzbm(yzbm);
		xs.setDahh(dahh);
		xs.setDayh(dayh);
		xs.setDzyx(dzyx);
		xs.setLxdz(lxdz);
		xs.setLxdh(lxdh);
		xs.setZsjj(zsjj);
		xs.setZkzh(zkzh);
		xs.setKsh(ksh);
		xs.setKslb(kslb);
		xs.setJkzk(jkzk);
		xs.setKstz(kstz);
		xs.setRxwhcd(rxwhcd);
		xs.setSysf(sysf);
		xs.setBylb(bylb);
		xs.setSydq(sydq);
		xs.setKldm(kldm);
		xs.setRxcj(rxcj);
		xs.setXxtj(xxtj);
		xs.setTc(tc);
		xs.setRxsj(rxsj);
		xs.setZyIdLq(zyIdLq);
		xs.setXz(xz);
		xs.setRxnf(rxnf);
		xs.setRxfs(rxfs);
		xs.setPylb(pylb);
		xs.setPydx(pydx);
		xs.setPycc(pycc);
		xs.setBxfs(bxfs);
		xs.setXxnx(xxnx);
		xs.setQtbxxs(qtbxxs);
		xs.setZxwyyz(zxwyyz);
		xs.setZxwyjb(zxwyjb);
		xs.setBxlx(bxlx);
		xs.setByzx(byzx);
		xs.setSg(sg);
		xs.setTz(tz);
		xs.setJsjnldj(jsjnldj);
		xs.setJtzz(jtzz);
		xs.setXyId(xyId);
		xs.setZyId(zyId);
		xs.setNjId(njId);
		xs.setBjId(bjId);
		String xuehao = xs.getXh();
		if ((null != xyId) && (!"".equals(xyId))) {
			XUEYUAN xy = studentService.findXUEYUANById(xyId);
			if ((null != xy) && (null != xy.getXymc())) {
				xs.setXYMC(xy.getXymc());
			} else {
				xs.setXYMC("");
			}
		} else {
			xs.setXYMC("");
		}
		if ((null != zyId) && (!"".equals(zyId))) {
			ZHUANYE zy = studentService.findZHUANYEById(zyId);
			if ((null != zy) && (null != zy.getZYMC())) {
				xs.setZYMC(zy.getZYMC());
			} else {
				xs.setZYMC("");
			}
		} else {
			xs.setZYMC("");
		}
		if ((null != njId) && (!"".equals(njId))) {
			NIANJI nj = studentService.findNIANJIById(njId);
			if ((null != nj) && (null != nj.getNJMC())) {
				xs.setNJMC(nj.getNJMC());
			} else {
				xs.setNJMC("");
			}
		} else {
			xs.setNJMC("");
		}
		if ((null != bjId) && (!"".equals(bjId))) {
			BANJI bj = studentService.findBANJIById(bjId);
			if ((null != bj) && (null != bj.getBJMC())) {
				xs.setBJMC(bj.getBJMC());
			} else {
				xs.setBJMC("");
			}
		} else {
			xs.setBJMC("");
		}

		if (studentService.addStudent(xs)) {
			YONGHU yh = new YONGHU();
			yh.setYhid(UUIDTools.getUUID());
			// 默认分配为学生用户组
			YONGHUZU yz = userService
					.findYHZ("7edb15f1-0c39-409b-ab56-b54e46f17692");
			yh.setYonghuzu(yz);
			yh.setYhm(xuehao);
			yh.setYhmm(xuehao);
			//增加用户ID到学生信息表中 begin
			xs.setYhId(yh.getYhid());
			studentService.updateStudent(xs);
			//增加用户ID到学生信息表中 end
			// 判断向用户表中是否添加成功
			if (userService.addYONGHU(yh)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "学生信息添加成功！");
			}
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "学生信息添加失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void findStudent() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String tmpPath = getSavePath() + "//Pictures//" + xh + ".jpg";

		try {
			File f = new File(tmpPath);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				byte[] data = new byte[in.available()];
				in.read(data);
				in.close();
				String EnRst = Base64.encodeToString(data);
				String rstPic = "data:image/jpg;base64," + EnRst;
				jsonMap.put("success", true);
				jsonMap.put("message", rstPic);
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "照片不存在！");
			}
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changePic() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			FileInputStream in = new FileInputStream(getImgfile());
			byte[] data = new byte[in.available()];
			in.read(data);
			in.close();
			String EnRst = Base64.encodeToString(data);
			String rstPic = "data:image/jpg;base64," + EnRst;
			jsonMap.put("success", true);
			jsonMap.put("message", rstPic);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} catch (Exception e) {
			jsonMap.put("success", false);
			jsonMap.put("message", "读取图片失败");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	public void delFile(String path) {
		File f1 = new File(path);
		while (f1.exists()) {
			f1.delete();
		}
	}

	public void updateStudent() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String ChkRst = CheckStundentData();
		if (!"".equals(ChkRst)) {
			jsonMap.put("success", false);
			jsonMap.put("message", ChkRst);
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		Xsxx xs = studentService.findStudentById(xsId);
		if (null == xs) {
			jsonMap.put("success", false);
			jsonMap.put("message", "学生信息修改失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		try {
			if (null != getImgfile()) {
				// FileInputStream fin = new FileInputStream(getImgfile());
				String tmpPath1 = getSavePath() + "//Pictures//" + xs.getXh()
						+ ".jpg";
				delFile(tmpPath1);
				String tmpPath2 = getSavePath() + "//Pictures//" + xh + ".jpg";
				AddPic(tmpPath2);
				AddRecord(xs.getXh(), "jpg", "Add");
				// FileOutputStream fout = new FileOutputStream(tmpPath2);
				// byte[] buffer = new byte[BUFFER_SIZE];
				// int len = 0;
				// while ((len = fin.read(buffer)) > 0) {
				// fout.write(buffer, 0, len);
				// }
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
			jsonMap.put("message", "照片上传失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		xs.setXh(xh);
		xs.setZsxm(zsxm);
		xs.setCym(cym);
		xs.setXb(xb);
		xs.setSfzhm(sfzhm);
		xs.setCsny(csny);
		xs.setMz(mz);
		xs.setRtsj(rtsj);
		xs.setCsd(csd);
		xs.setHjlb(hjlb);
		xs.setSushehao(sushehao);
		xs.setSsdh(ssdh);
		xs.setGatdm(gatdm);
		xs.setLxr(lxr);
		xs.setYzbm(yzbm);
		xs.setDahh(dahh);
		xs.setDayh(dayh);
		xs.setDzyx(dzyx);
		xs.setLxdz(lxdz);
		xs.setLxdh(lxdh);
		xs.setZsjj(zsjj);
		xs.setZkzh(zkzh);
		xs.setKsh(ksh);
		xs.setKslb(kslb);
		xs.setJkzk(jkzk);
		xs.setKstz(kstz);
		xs.setRxwhcd(rxwhcd);
		xs.setSysf(sysf);
		xs.setBylb(bylb);
		xs.setSydq(sydq);
		xs.setKldm(kldm);
		xs.setRxcj(rxcj);
		xs.setXxtj(xxtj);
		xs.setTc(tc);
		xs.setRxsj(rxsj);
		xs.setZyIdLq(zyIdLq);
		xs.setXz(xz);
		xs.setRxnf(rxnf);
		xs.setRxfs(rxfs);
		xs.setPylb(pylb);
		xs.setPydx(pydx);
		xs.setPycc(pycc);
		xs.setBxfs(bxfs);
		xs.setXxnx(xxnx);
		xs.setQtbxxs(qtbxxs);
		xs.setZxwyyz(zxwyyz);
		xs.setZxwyjb(zxwyjb);
		xs.setBxlx(bxlx);
		xs.setByzx(byzx);
		xs.setSg(sg);
		xs.setTz(tz);
		xs.setJsjnldj(jsjnldj);
		xs.setJtzz(jtzz);
		xs.setXyId(xyId);
		xs.setZyId(zyId);
		xs.setNjId(njId);
		xs.setBjId(bjId);
		if ((null != xyId) && (!"".equals(xyId))) {
			XUEYUAN xy = studentService.findXUEYUANById(xyId);
			if ((null != xy) && (null != xy.getXymc())) {
				xs.setXYMC(xy.getXymc());
			} else {
				xs.setXYMC("");
			}
		} else {
			xs.setXYMC("");
		}
		if ((null != zyId) && (!"".equals(zyId))) {
			ZHUANYE zy = studentService.findZHUANYEById(zyId);
			if ((null != zy) && (null != zy.getZYMC())) {
				xs.setZYMC(zy.getZYMC());
			} else {
				xs.setZYMC("");
			}
		} else {
			xs.setZYMC("");
		}
		if ((null != njId) && (!"".equals(njId))) {
			NIANJI nj = studentService.findNIANJIById(njId);
			if ((null != nj) && (null != nj.getNJMC())) {
				xs.setNJMC(nj.getNJMC());
			} else {
				xs.setNJMC("");
			}
		} else {
			xs.setNJMC("");
		}
		if ((null != bjId) && (!"".equals(bjId))) {
			BANJI bj = studentService.findBANJIById(bjId);
			if ((null != bj) && (null != bj.getBJMC())) {
				xs.setBJMC(bj.getBJMC());
			} else {
				xs.setBJMC("");
			}
		} else {
			xs.setBJMC("");
		}
		studentService.updateStudent(xs);
		jsonMap.put("success", true);
		jsonMap.put("message", "学生信息修改成功！");
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void delStudent() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (!"".equals(xsId)) {
			String[] ids = xsId.split(",");
			for (String id : ids) {
				Xsxx xs = studentService.findStudentById(id);
				String xuehao = xs.getXh();
				if (null != xs) {
					// 判断删除学生是否删除成功
					if (studentService.delStudent(id)) {
						//查询用户表是否有此用户
					if (xuehao!=null) {
						YONGHU yh = userService.findYONGHUName(xuehao);
						//判断用户是否为空
						if (yh != null) {
							//如果不为空就进行删除
							if (userService.delYONGHU(yh.getYhid())) {
								//给予用户提示信息
								jsonMap.put("success", true);
								jsonMap.put("message", "删除成功！");
							}
						}
					}
						jsonMap.put("success", true);
						jsonMap.put("message", "删除成功！");
						String tmpPath = getSavePath() + "//Pictures//"
								+ xs.getXh() + ".jpg";
						delFile(tmpPath);
						AddRecord(xs.getXh(), "jpg", "Del");

						String tmpFpPath1 = getSavePath() + "//Dats//"
								+ xs.getXh() + "1.dat";
						delFile(tmpFpPath1);

						String tmpFpPath2 = getSavePath() + "//Dats//"
								+ xs.getXh() + "2.dat";
						delFile(tmpFpPath2);
						AddRecord(xs.getXh(), "dat", "Del");
					} else {
						jsonMap.put("success", false);
						jsonMap.put("message", "删除失败！");
					}
				} else {
					jsonMap.put("success", true);
					jsonMap.put("message", "删除成功！");
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	public void listZyForXueYuan() {

	}

	/*
	 * 增加：action 功能：列表全部学生 author 于洋 date 2014-05-30
	 */
	public void listAllXUESHENG() {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("xh", xh);
		params.put("zsxm", zsxm);
		List<Xsxx> list = studentService.findStudentByPageApp(params, order,
				sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
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

	public File getImgfile() {
		return imgfile;
	}

	public void setImgfile(File imgfile) {
		this.imgfile = imgfile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setImgfileContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setImgfileFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getXsId() {
		return xsId;
	}

	public void setXsId(String xsId) {
		this.xsId = xsId;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public String getCym() {
		return cym;
	}

	public void setCym(String cym) {
		this.cym = cym;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getSfzhm() {
		return sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	public String getCsny() {
		return csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getRtsj() {
		return rtsj;
	}

	public void setRtsj(String rtsj) {
		this.rtsj = rtsj;
	}

	public String getCsd() {
		return csd;
	}

	public void setCsd(String csd) {
		this.csd = csd;
	}

	public String getHjlb() {
		return hjlb;
	}

	public void setHjlb(String hjlb) {
		this.hjlb = hjlb;
	}

	public String getSushehao() {
		return sushehao;
	}

	public void setSushehao(String sushehao) {
		this.sushehao = sushehao;
	}

	public String getSsdh() {
		return ssdh;
	}

	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}

	public String getGatdm() {
		return gatdm;
	}

	public void setGatdm(String gatdm) {
		this.gatdm = gatdm;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getDahh() {
		return dahh;
	}

	public void setDahh(String dahh) {
		this.dahh = dahh;
	}

	public String getDayh() {
		return dayh;
	}

	public void setDayh(String dayh) {
		this.dayh = dayh;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getZsjj() {
		return zsjj;
	}

	public void setZsjj(String zsjj) {
		this.zsjj = zsjj;
	}

	public String getZkzh() {
		return zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	public String getKslb() {
		return kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
	}

	public String getJkzk() {
		return jkzk;
	}

	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}

	public String getKstz() {
		return kstz;
	}

	public void setKstz(String kstz) {
		this.kstz = kstz;
	}

	public String getRxwhcd() {
		return rxwhcd;
	}

	public void setRxwhcd(String rxwhcd) {
		this.rxwhcd = rxwhcd;
	}

	public String getSysf() {
		return sysf;
	}

	public void setSysf(String sysf) {
		this.sysf = sysf;
	}

	public String getBylb() {
		return bylb;
	}

	public void setBylb(String bylb) {
		this.bylb = bylb;
	}

	public String getSydq() {
		return sydq;
	}

	public void setSydq(String sydq) {
		this.sydq = sydq;
	}

	public String getKldm() {
		return kldm;
	}

	public void setKldm(String kldm) {
		this.kldm = kldm;
	}

	public String getRxcj() {
		return rxcj;
	}

	public void setRxcj(String rxcj) {
		this.rxcj = rxcj;
	}

	public String getXxtj() {
		return xxtj;
	}

	public void setXxtj(String xxtj) {
		this.xxtj = xxtj;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getRxsj() {
		return rxsj;
	}

	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
	}

	public String getZyIdLq() {
		return zyIdLq;
	}

	public void setZyIdLq(String zyIdLq) {
		this.zyIdLq = zyIdLq;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getRxnf() {
		return rxnf;
	}

	public void setRxnf(String rxnf) {
		this.rxnf = rxnf;
	}

	public String getRxfs() {
		return rxfs;
	}

	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}

	public String getPylb() {
		return pylb;
	}

	public void setPylb(String pylb) {
		this.pylb = pylb;
	}

	public String getPydx() {
		return pydx;
	}

	public void setPydx(String pydx) {
		this.pydx = pydx;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getBxfs() {
		return bxfs;
	}

	public void setBxfs(String bxfs) {
		this.bxfs = bxfs;
	}

	public String getXxnx() {
		return xxnx;
	}

	public void setXxnx(String xxnx) {
		this.xxnx = xxnx;
	}

	public String getQtbxxs() {
		return qtbxxs;
	}

	public void setQtbxxs(String qtbxxs) {
		this.qtbxxs = qtbxxs;
	}

	public String getZxwyyz() {
		return zxwyyz;
	}

	public void setZxwyyz(String zxwyyz) {
		this.zxwyyz = zxwyyz;
	}

	public String getZxwyjb() {
		return zxwyjb;
	}

	public void setZxwyjb(String zxwyjb) {
		this.zxwyjb = zxwyjb;
	}

	public String getBxlx() {
		return bxlx;
	}

	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}

	public String getByzx() {
		return byzx;
	}

	public void setByzx(String byzx) {
		this.byzx = byzx;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getJsjnldj() {
		return jsjnldj;
	}

	public void setJsjnldj(String jsjnldj) {
		this.jsjnldj = jsjnldj;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getYhId() {
		return yhId;
	}

	public void setYhId(String yhId) {
		this.yhId = yhId;
	}

	public String getXyId() {
		return xyId;
	}

	public void setXyId(String xyId) {
		this.xyId = xyId;
	}

	public String getZyId() {
		return zyId;
	}

	public void setZyId(String zyId) {
		this.zyId = zyId;
	}

	public String getNjId() {
		return njId;
	}

	public void setNjId(String njId) {
		this.njId = njId;
	}

	public String getBjId() {
		return bjId;
	}

	public void setBjId(String bjId) {
		this.bjId = bjId;
	}

	public String getZhaopianId() {
		return zhaopianId;
	}

	public void setZhaopianId(String zhaopianId) {
		this.zhaopianId = zhaopianId;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
