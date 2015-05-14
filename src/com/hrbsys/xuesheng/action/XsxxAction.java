package com.hrbsys.xuesheng.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.student.action.Base64;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.tools.Validator;
import com.hrbsys.xuesheng.service.XsxxService;

public class XsxxAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	private XsxxService xsxxService;
	private Xsxx xsxx;
	private File imgfile;// 上传文件域
	private static final int BUFFER_SIZE = 1024 * 1024;
	private String contentType;// 文件类型
	private String fileName;// 文件名
	private String savePath;// 文件配置值

	public void listXsxx() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		YONGHU user = (YONGHU) super.session.get("yonghu");
		if (null == user || "".equals(user.getYhid())) {
			jsonMap.put("status", "false");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		xsxx = xsxxService.findStudentById(user.getYhid());
		if (null == xsxx) {
			Xsxx xs = new Xsxx();
			String TmpId = UUIDTools.getUUID();
			xs.setXsId(TmpId);
			xs.setYhId(user.getYhid());
			if (!xsxxService.addStudent(xs)) {
				jsonMap.put("success", false);
				jsonMap.put("message", "保存失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
			
			jsonMap.put("xsId", TmpId);
			jsonMap.put("yhid", user.getYhid());
			jsonMap.put("status", "new");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("status", "display");
			jsonMap.put("xsId", xsxx.getXsId());
			jsonMap.put("xh", xsxx.getXh());
			jsonMap.put("zsxm", xsxx.getZsxm());
			jsonMap.put("cym", xsxx.getCym());
			jsonMap.put("xb", xsxx.getXb());
			jsonMap.put("sfzhm", xsxx.getSfzhm());
			jsonMap.put("csny", xsxx.getCsny());
			jsonMap.put("mz", xsxx.getMz());
			jsonMap.put("rtsj", xsxx.getRtsj());
			jsonMap.put("csd", xsxx.getCsd());
			jsonMap.put("hjlb", xsxx.getHjlb());
			jsonMap.put("sushehao", xsxx.getSushehao());
			jsonMap.put("ssdh", xsxx.getSsdh());
			jsonMap.put("gatdm", xsxx.getGatdm());
			jsonMap.put("lxr", xsxx.getLxr());
			jsonMap.put("yzbm", xsxx.getYzbm());
			jsonMap.put("dahh", xsxx.getDahh());
			jsonMap.put("dayh", xsxx.getDayh());
			jsonMap.put("dzyx", xsxx.getDzyx());
			jsonMap.put("lxdz", xsxx.getLxdz());
			jsonMap.put("lxdh", xsxx.getLxdh());
			jsonMap.put("zsjj", xsxx.getZsjj());
			jsonMap.put("zkzh", xsxx.getZkzh());
			jsonMap.put("ksh", xsxx.getKsh());
			jsonMap.put("kslb", xsxx.getKslb());
			jsonMap.put("jkzk", xsxx.getJkzk());
			jsonMap.put("kstz", xsxx.getKstz());
			jsonMap.put("rxwhcd", xsxx.getRxwhcd());
			jsonMap.put("sysf", xsxx.getSysf());
			jsonMap.put("bylb", xsxx.getBylb());
			jsonMap.put("sydq", xsxx.getSydq());
			jsonMap.put("kldm", xsxx.getKldm());
			jsonMap.put("rxcj", xsxx.getRxcj());
			jsonMap.put("xxtj", xsxx.getXxtj());
			jsonMap.put("tc", xsxx.getTc());
			jsonMap.put("rxsj", xsxx.getRxsj());
			jsonMap.put("zyIdLq", xsxx.getZyIdLq());
			jsonMap.put("xz", xsxx.getXz());
			jsonMap.put("rxnf", xsxx.getRxnf());
			jsonMap.put("rxfs", xsxx.getRxfs());
			jsonMap.put("pylb", xsxx.getPylb());
			jsonMap.put("pydx", xsxx.getPydx());
			jsonMap.put("pycc", xsxx.getPycc());
			jsonMap.put("bxfs", xsxx.getBxfs());
			jsonMap.put("xxnx", xsxx.getXxnx());
			jsonMap.put("qtbxxs", xsxx.getQtbxxs());
			jsonMap.put("zxwyyz", xsxx.getZxwyyz());
			jsonMap.put("zxwyjb", xsxx.getZxwyjb());
			jsonMap.put("bxlx", xsxx.getBxlx());
			jsonMap.put("byzx", xsxx.getByzx());
			jsonMap.put("sg", xsxx.getSg());
			jsonMap.put("tz", xsxx.getTz());
			jsonMap.put("jsjnldj", xsxx.getJsjnldj());
			jsonMap.put("jtzz", xsxx.getJtzz());
			jsonMap.put("yhid", xsxx.getYhId());
			jsonMap.put("xyId", xsxx.getXyId());
			jsonMap.put("zyId", xsxx.getZyId());
			jsonMap.put("njId", xsxx.getNjId());
			jsonMap.put("bjId", xsxx.getBjId());
			jsonMap.put("modify_num", xsxx.getMODIFY_NUM());
			jsonMap.put("xszw1",xsxx.getZhiwenId1());
			jsonMap.put("xszw2", xsxx.getZhiwenId2());

			String tmpPath = getSavePath() + "//Pictures//" + xsxx.getXh() + ".jpg";

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
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
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

	// public String CheckStundentData() {
	// Validator validator = new Validator();
	// if (null == xsxx.getXh() && "".equals(xsxx.getXh())) {
	// return "请输入学号";
	// }
	//
	// // if (null != xsxx.getZsxm() && !"".equals(xsxx.getZsxm())) {
	// // if (!validator.checkChinese(xsxx.getZsxm())) {
	// // return "姓名请输入中文名字！";
	// // }
	// // }
	//
	// // if (null != xsxx.getCym() && !"".equals(xsxx.getCym())) {
	// // if (!validator.checkChinese(xsxx.getCym())) {
	// // return "曾用名请输入中文名字！";
	// // }
	// // }
	//
	// // if (null != xsxx.getXb() && !"".equals(xsxx.getXb())) {
	// // if (!"男".equals(xsxx.getXb()) && !"女".equals(xsxx.getXb())) {
	// // return "性别项填写错误！";
	// // }
	// // }
	//
	// // if (null != xsxx.getSfzhm() && !"".equals(xsxx.getSfzhm())) {
	// // if (!validator.checkIdCard(xsxx.getSfzhm())) {
	// // return "身份证填写错误！";
	// // }
	// // }
	// //
	// // if (null != xsxx.getCsny() && !"".equals(xsxx.getCsny())) {
	// // if (!validator.checkDate1(xsxx.getCsny())) {
	// // return "出生年月填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getMz() && !"".equals(xsxx.getMz())) {
	// // if (!validator.checkEthnic(xsxx.getMz())) {
	// // return "民族填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getHjlb() && !"".equals(xsxx.getHjlb())) {
	// // if (!"非农业户口".equals(xsxx.getHjlb()) && !"农业户口".equals(xsxx.getHjlb()))
	// {
	// // return "户籍类别填写错误！";
	// // }
	// // }
	// //
	// // if (null != xsxx.getSushehao() && !"".equals(xsxx.getSushehao())) {
	// // if (!validator.checkPInteger(xsxx.getSushehao())) {
	// // return "宿舍号填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getSsdh() && !"".equals(xsxx.getSsdh())) {
	// // if (!validator.checkTel(xsxx.getSsdh())) {
	// // return "宿舍电话填写错误(0451-88888888)";
	// // }
	// // }
	// //
	// // if (null != xsxx.getYzbm() && !"".equals(xsxx.getYzbm())) {
	// // if (!validator.checkZipCode(xsxx.getYzbm())) {
	// // return "邮政编码填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getDahh() && !"".equals(xsxx.getDahh())) {
	// // if (!validator.checkPInteger(xsxx.getDahh())) {
	// // return "档案行号填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getDayh() && !"".equals(xsxx.getDayh())) {
	// // if (!validator.checkPInteger(xsxx.getDayh())) {
	// // return "档案页号填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getDzyx() && !"".equals(xsxx.getDzyx())) {
	// // if (!validator.checkEmail(xsxx.getDzyx())) {
	// // return "电子邮箱填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getLxdh() && !"".equals(xsxx.getLxdh())) {
	// // if (!validator.checkTel(xsxx.getLxdh())) {
	// // return "联系电话填写错误(0451-88888888)";
	// // }
	// // }
	// //
	// // if (null != xsxx.getZsjj() && !"".equals(xsxx.getZsjj())) {
	// // if (!"春季".equals(xsxx.getZsjj()) && !"秋季".equals(xsxx.getZsjj())) {
	// // return "招生季节填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getZkzh() && !"".equals(xsxx.getZkzh())) {
	// // if (!validator.checkPInteger(xsxx.getZkzh())) {
	// // return "准考证号填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getKsh() && !"".equals(xsxx.getKsh())) {
	// // if (!validator.checkPInteger(xsxx.getKsh())) {
	// // return "考生号填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getKslb() && !"".equals(xsxx.getKslb())) {
	// // if (!"统招".equals(xsxx.getKslb()) && !"自考".equals(xsxx.getKslb()) &&
	// !"成人".equals(xsxx.getKslb()) && !"函授".equals(xsxx.getKslb())) {
	// // return "考生类别填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getSysf() && !"".equals(xsxx.getSysf())) {
	// // if (!validator.checkProvice(xsxx.getSysf())) {
	// // return "生源省份填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getBylb() && !"".equals(xsxx.getBylb())) {
	// // if (!Validator.checkGraduating(xsxx.getBylb())) {
	// // return "毕业类别填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getRxcj() && !"".equals(xsxx.getRxcj())) {
	// // if (!validator.checkPFloat(xsxx.getRxcj())) {
	// // return "入学成绩格式错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getRxsj() && !"".equals(xsxx.getRxsj())) {
	// // if (!validator.checkDate1(xsxx.getRxsj())) {
	// // return "入学时间填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getXz() && !"".equals(xsxx.getXz())) {
	// // if (!"两年制".equals(xsxx.getXz()) && !"三年制".equals(xsxx.getXz()) &&
	// !"四年制".equals(xsxx.getXz()) && !"五年制".equals(xsxx.getXz())) {
	// // return "学制填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getRxnf() && !"".equals(xsxx.getRxnf())) {
	// // if (!validator.checkDate1(xsxx.getRxnf())) {
	// // return "入学年份填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getRxfs() && !"".equals(xsxx.getRxfs())) {
	// // if (!"考试".equals(xsxx.getRxfs()) && !"推荐".equals(xsxx.getRxfs()) &&
	// !"测验".equals(xsxx.getRxfs()) && !"面谈".equals(xsxx.getRxfs())) {
	// // return "入学方式填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getPylb() && !"".equals(xsxx.getPylb())) {
	// // if (!"国家计划外自筹经费".equals(xsxx.getPylb()) &&
	// !"国家计划内定向培养".equals(xsxx.getPylb()) &&
	// !"国家计划外委托培养".equals(xsxx.getPylb())) {
	// // return "培养类别填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getPycc() && !"".equals(xsxx.getPycc())) {
	// // if (!"博士".equals(xsxx.getPycc()) && !"硕士".equals(xsxx.getPycc()) &&
	// !"本科".equals(xsxx.getPycc()) && !"大专".equals(xsxx.getPycc()) &&
	// !"中专".equals(xsxx.getPycc())) {
	// // return "培养层次填写错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getSg() && !"".equals(xsxx.getSg())) {
	// // if (!validator.checkPFloat(xsxx.getSg())) {
	// // return "身高格式错误";
	// // }
	// // }
	// //
	// // if (null != xsxx.getTz() && !"".equals(xsxx.getTz())) {
	// // if (!validator.checkPFloat(xsxx.getTz())) {
	// // return "体重格式错误";
	// // }
	// // }
	//
	// return "";
	// }

	public void AddRecord(String id, String type, String opt) {
		UPDATEVERSION uv = new UPDATEVERSION();
		uv.setID(UUIDTools.getUUID());
		uv.setNAME(id);
		uv.setOPT(opt);
		uv.setTYPE(type);
		xsxxService.RecordOptLog(uv);
	}

	public void upateXsxx() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// String ChkRst = CheckStundentData();
		// if (!"".equals(ChkRst)) {
		// jsonMap.put("success", false);
		// jsonMap.put("message", ChkRst);
		// new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		// return;
		// }
		xsxx.setZhaopianId("");
		xsxx.setZhiwenId1(xsxx.getZhiwenId1());
		xsxx.setZhiwenId2(xsxx.getZhiwenId2());
		if (null != getImgfile()) {
			String tmpPath = getSavePath() + "//Pictures//" + xsxx.getXh() + ".jpg";
			File f = new File(tmpPath);
			while (f.exists()) {
				f.delete();
			}
			AddRecord(xsxx.getXh(), "jpg", "Del");
			try {
				FileInputStream fin;
				fin = new FileInputStream(getImgfile());
				FileOutputStream fout = new FileOutputStream(tmpPath);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while ((len = fin.read(buffer)) > 0) {
					fout.write(buffer, 0, len);
				}
				xsxx.setZhaopianId("已存在");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ((null != xsxx.getXyId()) && (!"".equals(xsxx.getXyId()))) {
			XUEYUAN xy = xsxxService.findXUEYUANById(xsxx.getXyId());
			if ((null != xy) && (null != xy.getXymc())) {
				xsxx.setXYMC(xy.getXymc());
			} else {
				xsxx.setXYMC("");
			}
		} else {
			xsxx.setXYMC("");
		}
		if ((null != xsxx.getZyId()) && (!"".equals(xsxx.getZyId()))) {
			ZHUANYE zy = xsxxService.findZHUANYEById(xsxx.getZyId());
			if ((null != zy) && (null != zy.getZYMC())) {
				xsxx.setZYMC(zy.getZYMC());
			} else {
				xsxx.setZYMC("");
			}
		} else {
			xsxx.setZYMC("");
		}
		if ((null != xsxx.getNjId()) && (!"".equals(xsxx.getNjId()))) {
			NIANJI nj = xsxxService.findNIANJIById(xsxx.getNjId());
			if ((null != nj) && (null != nj.getNJMC())) {
				xsxx.setNJMC(nj.getNJMC());
			} else {
				xsxx.setNJMC("");
			}
		} else {
			xsxx.setNJMC("");
		}
		if ((null != xsxx.getBjId()) && (!"".equals(xsxx.getBjId()))) {
			BANJI bj = xsxxService.findBANJIById(xsxx.getBjId());
			if ((null != bj) && (null != bj.getBJMC())) {
				xsxx.setBJMC(bj.getBJMC());
			} else {
				xsxx.setBJMC("");
			}
		} else {
			xsxx.setBJMC("");
		}

		if (null == xsxx.getXsId() || "".equals(xsxx.getXsId())) {
			xsxx.setXsId(UUIDTools.getUUID());
			if (xsxxService.addStudent(xsxx)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "保存成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "保存失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		} else {
			xsxx.setMODIFY_NUM("1");
			if (xsxxService.updateStudent(xsxx)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "更新成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "更新失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}

	}

	public XsxxService getXsxxService() {
		return xsxxService;
	}

	public void setXsxxService(XsxxService xsxxService) {
		this.xsxxService = xsxxService;
	}

	public Xsxx getXsxx() {
		return xsxx;
	}

	public void setXsxx(Xsxx xsxx) {
		this.xsxx = xsxx;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
