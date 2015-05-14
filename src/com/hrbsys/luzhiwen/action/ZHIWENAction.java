package com.hrbsys.luzhiwen.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.student.action.Base64;
import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.luzhiwen.service.LuzhiwenService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.zwlr.service.ZWLRLBService;

/**
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class ZHIWENAction extends ActionBase {
	private File imgfile;// 上传文件域
	private LuzhiwenService luzhiwenService;
	private StudentService stuService;
	private static final int BUFFER_SIZE = 1024 * 1024;
	private String contentType;// 文件类型
	private String fileName;// 文件名
	private String savePath;// 文件配置值
	private String xsId;
	private String xyId;
	private String zyId;
	private String njId;
	private String bjId;
	private String xh;
	private String zsxm;
	private String zhiwenId1;
	private String zhiwenId2;
	private String tmp1;
	private String tmp2;
	private String tmp3;
	private String tmp4;

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listLUZHIWEN() {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		
		params.put("xh", xh);
		params.put("xyId", xyId);
		params.put("zyId", zyId);
		params.put("njId", njId);
		params.put("bjId", bjId);
		params.put("zsxm", zsxm);

		List<Xsxx> list = luzhiwenService.findStudentByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		int iSize = list.size();
		for (int i = 0; i < iSize; i++) {
			Xsxx xsxx = list.get(i);
			if (null != xsxx) {
				if (!"".equals(xsxx.getXyId())) {
					XUEYUAN txy = luzhiwenService.findXUEYUANById(xsxx
							.getXyId());
					if (null != txy) {
						if (null == txy.getXymc()) {
							list.get(i).setTmp1("无");
						} else {
							list.get(i).setTmp1(txy.getXymc());
						}
					} else {
						list.get(i).setTmp1("无");
					}
				}
				if (!"".equals(xsxx.getZyId())) {
					ZHUANYE tzy = luzhiwenService.findZHUANYEById(xsxx
							.getZyId());
					if (null != tzy) {
						if (null == tzy.getZYMC()) {
							list.get(i).setTmp2("无");
						} else {
							list.get(i).setTmp2(tzy.getZYMC());
						}
					} else {
						list.get(i).setTmp2("无");
					}
				}
				if (!"".equals(xsxx.getNjId())) {
					NIANJI tnj = luzhiwenService.findNIANJIById(xsxx.getNjId());
					if (null != tnj) {
						if (null == tnj.getNJMC()) {
							list.get(i).setTmp3("无");
						} else {
							list.get(i).setTmp3(tnj.getNJMC());
						}
					} else {
						list.get(i).setTmp3("无");
					}
				}
				if (!"".equals(xsxx.getBjId())) {
					BANJI tbj = luzhiwenService.findBANJIById(xsxx.getBjId());
					if (null != tbj) {
						if (null == tbj.getBJMC()) {
							list.get(i).setTmp4("无");
						} else {
							list.get(i).setTmp4(tbj.getBJMC());
						}
					} else {
						list.get(i).setTmp4("无");
					}
				}
				if ((null == xsxx.getZhiwenId1())
						|| ("".equals(xsxx.getZhiwenId1()))) {
					list.get(i).setTmp5("无");
				} else {
					list.get(i).setTmp5(xsxx.getZhiwenId1());
				}
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		jsonMap.put("total", luzhiwenService.getCountStudent(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	public void listZHIWEN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String tmpPath = getSavePath() + "//Dats//" + xh + "1.dat";
		File f = new File(tmpPath);
		if (f.exists()) {
			jsonMap.put("success", true);
		} else {
			jsonMap.put("success", false);
		}
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addZHIWEN() throws Exception {
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
		//通过学号查到到学生id
		Xsxx xs=stuService.findStudentByXh(this.xh);
		// 判断学生是不是为空
		if (null == xs) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未查到学生信息！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		// 判断输入的指纹是否为空，如果为空则不执行
		if (zhiwenId1 != null) {
			byte[] Dat1 = Base64.decode(zhiwenId1);
			String DatPath1 = getSavePath() + "//Dats//" + xs.getXh() + "1.dat";
			// 截取指纹1文件的名字
			String[] str = DatPath1.split("//");
			String name1 = str[str.length - 1].toString();
			list.add(name1.substring(0, name1.length() - 4));
			FileOutputStream fDatout1 = new FileOutputStream(DatPath1);
			fDatout1.write(Dat1, 0, Dat1.length);
			// 对流进行关闭
			fDatout1.close();
			xs.setZhiwenId1("有");
			// 对指纹1字段进行更新
			luzhiwenService.updateStudent(xs);
		}

		if (zhiwenId2 != null) {
			byte[] Dat2 = Base64.decode(zhiwenId2);
			String DatPath2 = getSavePath() + "//Dats//" + xs.getXh() + "2.dat";
			// 截取指纹2文件的名字
			String[] str = DatPath2.split("//");
			String name2 = str[str.length - 1].toString();
			list.add(name2.substring(0, name2.length() - 4));

			FileOutputStream fDatout2 = new FileOutputStream(DatPath2);
			fDatout2.write(Dat2, 0, Dat2.length);
			// 对流进行关闭
			fDatout2.close();
			xs.setZhiwenId2("有");
			// 对指纹2字段进行更新
			luzhiwenService.updateStudent(xs);

		}

		// 改变指纹有无状态以及将文件名存储到数据库中
		for (int i = 0; i < list.size(); i++) {
			AddRecord(String.valueOf(list.get(i)), "dat", "Add");
			// AddRecord(xs.getXh(), "dat", "Add");
		}
		jsonMap.put("success", true);
		jsonMap.put("message", "信息成功！");
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void AddRecord(String id, String type, String opt) {
		UPDATEVERSION uv = new UPDATEVERSION();
		uv.setID(UUIDTools.getUUID());
		uv.setNAME(id);
		uv.setOPT(opt);
		uv.setTYPE(type);
		luzhiwenService.RecordOptLog(uv);
	}

	public void delZHIWEN() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		String[] ids = xsId.split(",");
		for (String id : ids) {
			Xsxx xs = luzhiwenService.findStudentById(id);
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
			if (!luzhiwenService.updateStudent(xs)) {
				jsonMap.put("success", false);
				jsonMap.put("message", "文件删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
				return;
			}
			AddRecord(xs.getXh(), "dat", "Del");
		}
		jsonMap.put("success", true);
		jsonMap.put("message", "文件删除成功！");
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
		return;
	}

	public File getImgfile() {
		return imgfile;
	}

	public void setImgfile(File imgfile) {
		this.imgfile = imgfile;
	}

	public LuzhiwenService getLuzhiwenService() {
		return luzhiwenService;
	}

	public void setLuzhiwenService(LuzhiwenService luzhiwenService) {
		this.luzhiwenService = luzhiwenService;
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

	public String getXsId() {
		return xsId;
	}

	public void setXsId(String xsId) {
		this.xsId = xsId;
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

	public String getTmp1() {
		return tmp1;
	}

	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}

	public String getTmp2() {
		return tmp2;
	}

	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}

	public String getTmp3() {
		return tmp3;
	}

	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	}

	public String getTmp4() {
		return tmp4;
	}

	public void setTmp4(String tmp4) {
		this.tmp4 = tmp4;
	}

	public StudentService getStuService() {
		return stuService;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}

}