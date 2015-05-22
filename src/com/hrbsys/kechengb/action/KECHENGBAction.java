package com.hrbsys.kechengb.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.JIAOSHI;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KECHENGXX;
import com.hrbsys.bean.KECHENGXXLB;
import com.hrbsys.bean.KESHI;
import com.hrbsys.bean.SKXSXX;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.jiaogong.service.JIAOGONGService;
import com.hrbsys.jiaoshi.JIAOSHIService;
import com.hrbsys.kechengb.service.KECHENGBService;
import com.hrbsys.kechengxx.service.KECHENGXXService;
import com.hrbsys.kechengxxlb.service.KECHENGXXLBService;
import com.hrbsys.keshi.service.KESHIService;
import com.hrbsys.skxsxx.service.SKXSXXService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.tools.XingQiToXingQiXH;
import com.hrbsys.xueqi.service.XUEQIService;

/**
 * 课程表Action
 *
 * @author admin
 *
 */
public class KECHENGBAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private KECHENGBService kechengbService;
	private JIAOGONGService jiaogongService;
	private KESHIService keshiService;
	private KECHENGXXService kechengxxService;
	private JIAOSHIService jiaoshiService;
	private StudentService xueshengService;
	private SKXSXXService skxsxxService;
	private KECHENGXXLBService kechengxxlbService;
	private XUEQIService xueqiService;

	private String optionflag;	private String KCB_FXS_ID;
	private String KS_KSSJ;
	private String KCB_ID;
	private String LAOSHI_ID;
	private String KCBLB;
	private String LAOSHIGH;
	private String LAOSHIMC;
	private String JSZHOU;
	private String TMP6;
	private String KSZHOU;
	private String TMP5;
	private String KCXX_ID;
	private String TMP4;
	private String KS_ID;
	private String TMP3;
	private String TMP2;
	private String XINGQI;
	private String TMP1;
	private String MS;
	private String JSMC;
	private String BZ;
	private String XINGQIXH;
	private String KSMC;
	private String KS_JSSJ;
	private String KCXXMC;
	private String JS_ID;
	private String xuehao;
	private String SKBJ;
	private String CAPACITY;
	private String NUMSELECTED;
	private String YH_ID;
	private String XUEQI;
	private String maxrs;// 课程最大容纳的人数

	public String getYH_ID() {
		return YH_ID;
	}

	public void setYH_ID(String yH_ID) {
		YH_ID = yH_ID;
	}

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

//    private List hebing(List<KECHENGB> list1) {
//        List<KECHENGB> list = new ArrayList<>();
//        Map<String, KECHENGB> map = new HashMap<>();
//        for (KECHENGB kechengb : list1) {
//            if (map.containsKey(kechengb.getKCXXMC())) {
//                String key = kechengb.getKCXXMC();
//                String value = map.get(key).getLAOSHIGH() + "," + kechengb.getLAOSHIGH();
//                map.get(key).setLAOSHIGH(value);
//                value = map.get(key).getLAOSHIMC() + "," + kechengb.getLAOSHIMC();
//                map.get(key).setLAOSHIMC(value);
//                value = map.get(key).getKCBLB() + "," + kechengb.getKCBLB();
//                map.get(key).setKCBLB(value);
//                value = map.get(key).getJSMC() + "," + kechengb.getJSMC();
//                map.get(key).setJSMC(value);
//                value = map.get(key).getKSMC() + "," + kechengb.getKSMC();
//                map.get(key).setKSMC(value);
//                value = map.get(key).getKSZHOU() + "," + kechengb.getKSZHOU();
//                map.get(key).setKSZHOU(value);
//                value = map.get(key).getJSZHOU() + "," + kechengb.getJSZHOU();
//                map.get(key).setJSZHOU(value);
//                value = map.get(key).getXINGQI() + "," + kechengb.getXINGQI();
//                map.get(key).setXINGQI(value);
//                value = map.get(key).getXINGQIXH() + "," + kechengb.getXINGQIXH();
//                map.get(key).setXINGQIXH(value);
//                value = map.get(key).getXINGQIXH() + "," + kechengb.getXINGQIXH();
//                map.get(key).setXINGQIXH(value);
//                value = map.get(key).getKS_KSSJ() + "," + kechengb.getKS_KSSJ();
//                map.get(key).setKS_KSSJ(value);
//                value = map.get(key).getKS_JSSJ() + "," + kechengb.getKS_JSSJ();
//                map.get(key).setKS_JSSJ(value);
//                value = map.get(key).getMS() == null ? "无" : map.get(key).getMS() + "," + kechengb.getMS() == null ? "无" : kechengb.getMS();
//                map.get(key).setMS(value);
//                value = map.get(key).getBZ() == null ? "无" : map.get(key).getBZ() + "," + kechengb.getBZ() == null ? "无" : kechengb.getBZ();
//                map.get(key).setBZ(value);
//            } else {
//                map.put(kechengb.getKCXXMC(), kechengb);
//            }
//        }
//        for (String key : map.keySet()) {
//            list.add(map.get(key));
//        }
//        return list;
//    }

	public void listKECHENGB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		JsonConfig config = new JsonConfig();
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("KS_KSSJ", this.KS_KSSJ);
		params.put("KCB_ID", this.KCB_ID);
		params.put("LAOSHI_ID", this.LAOSHI_ID);
		params.put("KCBLB", this.KCBLB);
		params.put("LAOSHIGH", this.LAOSHIGH);
		params.put("LAOSHIMC", this.LAOSHIMC);
		params.put("JSZHOU", this.JSZHOU);
		params.put("TMP6", this.TMP6);
		params.put("KSZHOU", this.KSZHOU);
		params.put("TMP5", this.TMP5);
		params.put("KCXX_ID", this.KCXX_ID);
		params.put("TMP4", this.TMP4);
		params.put("KS_ID", this.KS_ID);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("XINGQI", this.XINGQI);
		params.put("TMP1", this.TMP1);
		params.put("MS", this.MS);
		params.put("JSMC", this.JSMC);
		params.put("BZ", this.BZ);
		params.put("XINGQIXH", this.XINGQIXH);
		params.put("KSMC", this.KSMC);
		params.put("KS_JSSJ", this.KS_JSSJ);
		params.put("KCXXMC", this.KCXXMC);
		params.put("JS_ID", this.JS_ID);

		// 从session中读取该用户有哪些操作权限：
		String pkqx = super.session.get("kechengbQX").toString(); // 获取排课权限
		String jgid = super.session.get("JIAOGONGID").toString();// 获取教工工号
		log.info(pkqx + "-->" + jgid);

		List<KECHENGB> list = kechengbService.findKECHENGBByPageApp(start,
				number, params, order, sort, pkqx, jgid);// 每页的数据，放入list
                                    //List<KECHENGB> list1 = kechengbService.findKECHENGBByPageApp(start, number, params, order, sort, pkqx, jgid);//合并
            //List<KECHENGB> list = hebing(list1);//合并

		// 判断权限是不是个人
		if (pkqx.equals("geren")) {
			//判断集合的大小
			if (list.isEmpty()) {
				//显示“无记录”
				jsonNull();
			}else{
				jsonMap.put("total",list.size());
				jsonMap.put("rows", list);// rows键 存放每页记录 list
				jsonMap.put("page", intPage);
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
			}
		}else{
			jsonMap.put("total",kechengbService.getCountKECHENGB(params));
                    //jsonMap.put("total", list.size());//合并
			jsonMap.put("rows", list);// rows键 存放每页记录 list
			jsonMap.put("page", intPage);
			// config.setExcludes(new String[]{"yonghus"});//除去级联属性
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
		}



	}

	public void addKECHENGB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 如果老师为空，则提示
		if ("".equals(this.getLAOSHIGH()) || null == this.getLAOSHIGH()) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择任课教师");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		// 课程信息为空，则提示
		if ("".equals(this.getKCXXMC()) || null == this.getKCXXMC()) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择上课课程");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		// 课时信息为空，则提示
		if ("".equals(this.getKS_ID()) || null == this.getKS_ID()) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择上课课时");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		KECHENGB t = new KECHENGB();
		t.setKCB_FXS_ID(UUIDTools.getUUID());
		// 获取课程信息类别名称
		KECHENGXXLB kcxxlb = kechengxxlbService.findKECHENGXXLB(this.KCBLB);
		XUEQI xq = xueqiService.findXUEQI(this.XUEQI);
		t.setKCBLB(kcxxlb.getKECHENGXXLBMC());
		t.setJSZHOU(this.getJSZHOU());
		t.setTMP6(this.getTMP6());
		t.setKSZHOU(this.getKSZHOU());
		t.setTMP5(this.getTMP5());
		t.setTMP4(this.getTMP4());
		t.setKS_ID(this.getKS_ID());
		t.setTMP3(this.getTMP3());
		t.setTMP2(this.getTMP2());
		t.setXINGQI(this.getXINGQI());
		t.setTMP1(this.getTMP1());
		t.setMS(this.getMS());
		t.setKCB_ID(this.getKCB_ID());
		t.setJSMC(this.getJSMC());
		t.setBZ(this.getBZ());
		t.setXINGQIXH(XingQiToXingQiXH.toXH(this.getXINGQI()));
		t.setKSMC(this.getKSMC());
		t.setKS_JSSJ(this.getKS_JSSJ());
		t.setKCXXMC(this.getKCXXMC());
		t.setJS_ID(this.getJS_ID());
		t.setXUEQI_ID(this.XUEQI);
		t.setXQMC(xq.getXQMC());
		t.setMAXRS(this.getMaxrs());
		// 设置老师相关信息：
		if (null != this.getLAOSHIGH() && !"".equals(this.getLAOSHIGH())) {
			JIAOGONG jg = jiaogongService.findJIAOGONG(this.getLAOSHIGH());
			t.setLAOSHI_ID(jg.getJG_ID());
			t.setLAOSHIGH(jg.getJGGH());
			t.setLAOSHIMC(jg.getJGMC());
		}
		// 设置课程信息
		if (null != this.getKCXXMC() && !"".equals(this.getKCXXMC())) {
			KECHENGXX kc = kechengxxService.findKECHENGXX(this.getKCXXMC());
			t.setKCXX_ID(kc.getKECHENGXX_ID());
			t.setKCXXMC(kc.getKECHENGMC());
		}
		// 设置课时信息
		if (null != this.getKS_ID() && !"".equals(this.getKS_ID())) {
			KESHI kc = keshiService.findKESHI(this.getKS_ID());
			t.setKS_ID(kc.getKS_ID());
			t.setKSMC(kc.getKSMC());
			t.setKS_KSSJ(kc.getKSSJ());
			t.setKS_JSSJ(kc.getJSSJ());
		}
		// 设置教室信息
		if (null != this.getJS_ID() && !"".equals(this.getJS_ID())) {
			JIAOSHI j = jiaoshiService.findJIAOSHI(this.getJS_ID());
			t.setJS_ID(j.getJS_ID());
			t.setJSMC(j.getFJMC());
		}

		// 判断是专业课还是选修课，存入相应学生到上课学生信息表中
		if (null != kcxxlb.getKECHENGXXLBMC()
				&& !"".equals(kcxxlb.getKECHENGXXLBMC())) {
			if ("公共选修课".equals(kcxxlb.getKECHENGXXLBMC())) { // 公共选修课学生自选课程，不需要进行学生的附加
				// 此处不需要做任何操作，学生在学生界面进行选课。
			}
			// 专业选修课：上课班级固定,则需要插入到数据库中
			if ("专业选修课".equals(kcxxlb.getKECHENGXXLBMC())) {
				if (null != kcxxlb.getKECHENGXXLBMC()
						&& !"".equals(kcxxlb.getKECHENGXXLBMC())) { // 如果班级ID不为空，则循环班级，插学生
					String bj_ids[] = this.getSKBJ().split(",");
					log.info(bj_ids + ":-->:" + bj_ids.length + ":-->:"
							+ this.getSKBJ());
					for (String bid : bj_ids) {
						log.info("循环出来的班级ID信息：" + bid);
						List<Xsxx> listxueshengbybj = xueshengService
								.findStudentByBanJi(bid.trim());
						// 将循环出来的学生，插入到上课学生信息表中。
						for (Xsxx x : listxueshengbybj) { // 循环学生列表，插入上课学生信息中
							SKXSXX sx = new SKXSXX();
							sx.setKCXS_ID(UUIDTools.getUUID());
							sx.setKCB_ID(t.getKCB_ID());
							sx.setKCB_KCXXMC(t.getKCXXMC());
							sx.setXUEHAO(x.getXh());
							sx.setXS_ID(x.getXsId());
							sx.setXSXM(x.getZsxm());
							sx.setKCB_FXS_ID(t.getKCB_FXS_ID());
							skxsxxService.addSKXSXX(sx);
						}
					}
				}
			}
			// 专业必修课：上课班级固定，则需要插入到数据库中
			if ("专业必修课".equals(kcxxlb.getKECHENGXXLBMC())) {
				if (null != this.getSKBJ() && !"".equals(this.getSKBJ())) { // 如果班级ID不为空，则循环班级，插学生
					String bj_ids[] = this.getSKBJ().split(",");
					for (String bid : bj_ids) {
						List<Xsxx> listxueshengbybj = xueshengService
								.findStudentByBanJi(bid);
						// 将循环出来的学生，插入到上课学生信息表中。
						for (Xsxx x : listxueshengbybj) { // 循环学生列表，插入上课学生信息中
							SKXSXX sx = new SKXSXX();
							sx.setKCXS_ID(UUIDTools.getUUID());
							sx.setKCB_ID(t.getKCB_ID());
							sx.setKCB_KCXXMC(t.getKCXXMC());
							sx.setXUEHAO(x.getXh());
							sx.setXS_ID(x.getXsId());
							sx.setXSXM(x.getZsxm());
							sx.setKCB_FXS_ID(t.getKCB_FXS_ID());
							skxsxxService.addSKXSXX(sx);
						}
					}
				}
			}

		}
		if (kechengbService.addKECHENGB(t)) {
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

	public void delKECHENGB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != KCB_FXS_ID) && !"".equals(KCB_FXS_ID)) {
			skxsxxService.delSKXSXXbyFXS_ID(KCB_FXS_ID);//删除课程表之前，先删除上课学生信息表中数据.
			if (kechengbService.delKECHENGB(KCB_FXS_ID)&& skxsxxService.delSKXSXXbyKCB_FXS_ID(KCB_FXS_ID)) {
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

	// 获取课程表ID
	public void getKCBID() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("uuid", UUIDTools.getUUID().toString());
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	public void updateKECHENGB() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghus"});//除去级联属性
		if ("".equals(KCB_FXS_ID) || null == KCB_FXS_ID) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		KECHENGB t = new KECHENGB();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateKECHENGB".equals(optionflag)) {
				// 如果老师为空，则提示
				if ("".equals(this.getLAOSHIMC()) || null == this.getLAOSHIMC()) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择任课教师");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				// 课程信息为空，则提示
				if ("".equals(this.getKCXXMC()) || null == this.getKCXXMC()) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择上课课程");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				// 课时信息为空，则提示
				if ("".equals(this.getKSMC()) || null == this.getKSMC()) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择上课课时");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				// 教室信息为空，则提示
				if ("".equals(this.getJS_ID()) || null == this.getJS_ID()) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择教室！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				t.setKCB_FXS_ID(KCB_FXS_ID);
				t.setKS_KSSJ(this.getKS_KSSJ());
				t.setKCBLB(this.getKCBLB());
				t.setKCB_ID(this.getKCB_ID());
				t.setLAOSHIGH(this.getLAOSHIGH());
				t.setJSZHOU(this.getJSZHOU());
				t.setTMP6(this.getTMP6());
				t.setKSZHOU(this.getKSZHOU());
				t.setTMP5(this.getTMP5());
				t.setTMP4(this.getTMP4());
				t.setTMP3(this.getTMP3());
				t.setTMP2(this.getTMP2());
				t.setXINGQI(this.getXINGQI());
				t.setTMP1(this.getTMP1());
				t.setMS(this.getMS());
				// t.setJSMC(this.getJSMC());
				t.setBZ(this.getBZ());
				t.setXINGQIXH(XingQiToXingQiXH.toXH(this.getXINGQI()));
				t.setKSMC(this.getKSMC());
				t.setKS_JSSJ(this.getKS_JSSJ());
				t.setKCXXMC(this.getKCXXMC());
				t.setMAXRS(this.getMaxrs());
				// t.setJS_ID(this.getJS_ID());

				// 设置老师相关信息：
				if (null != this.getLAOSHIMC()
						&& !"".equals(this.getLAOSHIMC())) {
					JIAOGONG jg = jiaogongService.findJIAOGONG(this
							.getLAOSHIMC());
					if (null != jg) {
						t.setLAOSHI_ID(jg.getJG_ID());
						t.setLAOSHIGH(jg.getJGGH());
						t.setLAOSHIMC(jg.getJGMC());
					}
				}
				// 设置课程信息
				if (null != this.getKCXXMC() && !"".equals(this.getKCXXMC())) {
					KECHENGXX kc = kechengxxService.findKECHENGXX(this
							.getKCXXMC());
					t.setKCXX_ID(kc.getKECHENGXX_ID());
					t.setKCXXMC(kc.getKECHENGMC());
				}
				// 设置课时信息
				if (null != this.getKSMC() && !"".equals(this.getKSMC())) {
					KESHI kc = keshiService.findKESHI(this.getKSMC());
					t.setKS_ID(kc.getKS_ID());
					t.setKSMC(kc.getKSMC());
					t.setKS_KSSJ(kc.getKSSJ());
					t.setKS_JSSJ(kc.getJSSJ());
				}
				// 设置教室信息
				if (null != this.getJS_ID() && !"".equals(this.getJS_ID())) {
					JIAOSHI js = jiaoshiService.findJIAOSHI(this.getJS_ID());
					t.setJS_ID(js.getJS_ID());
					t.setJSMC(js.getFJMC());
				}
				if (kechengbService.updateKECHENGB(t)) {
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
				kechengbService.findKECHENGB(KCB_FXS_ID), config));
	}

	public void listAllKECHENGB() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("KS_KSSJ", this.KS_KSSJ);
		params.put("KCB_ID", this.KCB_ID);
		params.put("LAOSHI_ID", this.LAOSHI_ID);
		params.put("KCBLB", this.KCBLB);
		params.put("LAOSHIGH", this.LAOSHIGH);
		params.put("LAOSHIMC", this.LAOSHIMC);
		params.put("JSZHOU", this.JSZHOU);
		params.put("TMP6", this.TMP6);
		params.put("KSZHOU", this.KSZHOU);
		params.put("TMP5", this.TMP5);
		params.put("KCXX_ID", this.KCXX_ID);
		params.put("TMP4", this.TMP4);
		params.put("KS_ID", this.KS_ID);
		params.put("TMP3", this.TMP3);
		params.put("TMP2", this.TMP2);
		params.put("XINGQI", this.XINGQI);
		params.put("TMP1", this.TMP1);
		params.put("MS", this.MS);
		params.put("JSMC", this.JSMC);
		params.put("BZ", this.BZ);
		params.put("XINGQIXH", this.XINGQIXH);
		params.put("KSMC", this.KSMC);
		params.put("KS_JSSJ", this.KS_JSSJ);
		params.put("KCXXMC", this.KCXXMC);
		params.put("JS_ID", this.JS_ID);

		List<KECHENGB> list = kechengbService.findKECHENGBByPageApp(params,
				order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	// 获取课程表，返回整张课程表。供外部其他模块使用。
	public String getKECHENGBIAO() {
		try {
			System.out.println("aaa");//zhbb
			HashMap<String, String> params = new HashMap<String, String>();
			HashMap<String, String> paramsks = new HashMap<String, String>();
			log.info("学号是：" + this.getXuehao());
			params.put("LAOSHI_ID", this.getLAOSHI_ID()); // 老师
			params.put("KCXX_ID", this.getKCXX_ID());// 课程
			params.put("JS_ID", this.getJS_ID());// 教室
			params.put("XUEHAO", this.getXuehao());// 学生学号
			// 如果传递进来的是用户ID，则根据用户查到学生
			if (null != this.getYH_ID() && !"".equals(this.YH_ID)) {
				Xsxx xs1 = xueshengService.findStudentByYHID(this.getYH_ID());
				if (null != xs1 || !"".equals(xs1)) {
					params.put("XUEHAO", xs1.getXh());
				}
			}
			List<KECHENGB> list = kechengbService.findKECHENGBforSHOWkechengb(
					params, order, sort);

			List<KESHI> list_ks = keshiService.findKESHIByPageApp(paramsks,
					order, sort);
			request.put("list_kechengb", list);
			request.put("list_keshi", list_ks);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// 排课时选择排课的学期，对学期进行判断，是否为上学期，如果为上学期，不上其进行排课
	public String deterTerm() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		// 判断学期工具
		Calendar cd = Calendar.getInstance();
		int year = cd.get(Calendar.YEAR);// 当前年
		int month = (cd.get(Calendar.MONTH) + 1); // 当前月
		String[] dd = this.XUEQI.split("~");
		// 判断年份是否是已经过期
		if ((Integer.parseInt(dd[0]) < year) && month > 7) {
			 jsonMap.put("success", false);
			 jsonMap.put("message", "你选择的这个学期以过去,请重新选择！");
			 new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		} else {
			// 判断是否是当前年份
			if (String.valueOf(year).equals(String.valueOf(dd[0]))) {
				// 判断当前系统时间的月份
				if (month < 7) {
					jsonMap.put("success", false);
					jsonMap.put("message", "你不能选择此学期进行排课,此学期以过去！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
				} else {
					// 如果是当前或者是下一年，可以进行排课
					switch (month) {
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
						jsonMap.put("success", false);
						jsonMap.put("message", "你不能选择此学期，进行排课");
						new JsonPrintTools().printJSON(JSONObject
								.fromObject(jsonMap));
						break;
					case 8:
					case 9:
					case 10:
					case 11:
					case 12:
						break;
					}
				}
			} else {
				// 如果为下学期则可以进行排课
				jsonMap.put("success", true);
				jsonMap.put("message",
						"这是" + dd[0] + "~" + dd[1].substring(0, 4)
								+ "学期你可以进行排课！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
		return SUCCESS;
	}


	public void jsonNull(){
		Map<String,Object> jsonMap=new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		List noData =new ArrayList();
		KECHENGB kcb=new KECHENGB();
		kcb.setKCB_ID(null);
		kcb.setKCXX_ID(null);
		kcb.setKCB_FXS_ID(null);
		kcb.setXUEQI_ID(null);
		kcb.setJS_ID(null);
		kcb.setLAOSHI_ID(null);
		kcb.setKS_ID(null);

		kcb.setKCBLB("无记录");
		kcb.setKCXXMC("无记录");
		kcb.setJSMC("无记录");
		kcb.setJSZHOU("无记录");
		kcb.setKS_JSSJ("无记录");
		kcb.setKS_KSSJ("无记录");
		kcb.setKSMC("无记录");
		kcb.setKSZHOU("无记录");
		kcb.setJSZHOU("无记录");
		kcb.setLAOSHIGH("无记录");
		kcb.setLAOSHIMC("无记录");
		kcb.setXINGQIXH("无记录");
		kcb.setXINGQI("无记录");
		kcb.setXQMC("无记录");
		kcb.setBZ("无记录");
		kcb.setMS("无记录");
		noData.add(kcb);
		jsonMap.put("total", noData.size());
		jsonMap.put("rows", noData);// rows键 存放每页记录 list
		jsonMap.put("page", 1);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
		return;
	}

	public String getKS_KSSJ() {
		return KS_KSSJ;
	}

	public void setKS_KSSJ(String KS_KSSJ) {
		this.KS_KSSJ = KS_KSSJ;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String KCB_ID) {
		this.KCB_ID = KCB_ID;
	}

	public String getLAOSHI_ID() {
		return LAOSHI_ID;
	}

	public void setLAOSHI_ID(String LAOSHI_ID) {
		this.LAOSHI_ID = LAOSHI_ID;
	}

	public String getKCBLB() {
		return KCBLB;
	}

	public void setKCBLB(String KCBLB) {
		this.KCBLB = KCBLB;
	}

	public String getLAOSHIGH() {
		return LAOSHIGH;
	}

	public void setLAOSHIGH(String LAOSHIGH) {
		this.LAOSHIGH = LAOSHIGH;
	}

	public String getLAOSHIMC() {
		return LAOSHIMC;
	}

	public void setLAOSHIMC(String LAOSHIMC) {
		this.LAOSHIMC = LAOSHIMC;
	}

	public String getJSZHOU() {
		return JSZHOU;
	}

	public void setJSZHOU(String JSZHOU) {
		this.JSZHOU = JSZHOU;
	}

	public String getTMP6() {
		return TMP6;
	}

	public void setTMP6(String TMP6) {
		this.TMP6 = TMP6;
	}

	public String getKSZHOU() {
		return KSZHOU;
	}

	public void setKSZHOU(String KSZHOU) {
		this.KSZHOU = KSZHOU;
	}

	public String getTMP5() {
		return TMP5;
	}

	public void setTMP5(String TMP5) {
		this.TMP5 = TMP5;
	}

	public String getKCXX_ID() {
		return KCXX_ID;
	}

	public void setKCXX_ID(String KCXX_ID) {
		this.KCXX_ID = KCXX_ID;
	}

	public String getTMP4() {
		return TMP4;
	}

	public void setTMP4(String TMP4) {
		this.TMP4 = TMP4;
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String KS_ID) {
		this.KS_ID = KS_ID;
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

	public String getXINGQI() {
		return XINGQI;
	}

	public void setXINGQI(String XINGQI) {
		this.XINGQI = XINGQI;
	}

	public String getTMP1() {
		return TMP1;
	}

	public void setTMP1(String TMP1) {
		this.TMP1 = TMP1;
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getXINGQIXH() {
		return XINGQIXH;
	}

	public void setXINGQIXH(String XINGQIXH) {
		this.XINGQIXH = XINGQIXH;
	}

	public String getKSMC() {
		return KSMC;
	}

	public void setKSMC(String KSMC) {
		this.KSMC = KSMC;
	}

	public String getKS_JSSJ() {
		return KS_JSSJ;
	}

	public void setKS_JSSJ(String KS_JSSJ) {
		this.KS_JSSJ = KS_JSSJ;
	}

	public String getKCXXMC() {
		return KCXXMC;
	}

	public void setKCXXMC(String KCXXMC) {
		this.KCXXMC = KCXXMC;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	public KECHENGBService getKECHENGB() {
		return kechengbService;
	}

	public void setkechengbService(KECHENGBService kechengbService) {
		this.kechengbService = kechengbService;
	}

	public String getKCB_FXS_ID() {
		return KCB_FXS_ID;
	}

	public void setKCB_FXS_ID(String KCB_FXS_ID) {
		this.KCB_FXS_ID = KCB_FXS_ID;
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

	public JIAOGONGService getJiaogongService() {
		return jiaogongService;
	}

	public void setJiaogongService(JIAOGONGService jiaogongService) {
		this.jiaogongService = jiaogongService;
	}

	public KESHIService getKeshiService() {
		return keshiService;
	}

	public void setKeshiService(KESHIService keshiService) {
		this.keshiService = keshiService;
	}

	public KECHENGXXService getKechengxxService() {
		return kechengxxService;
	}

	public void setKechengxxService(KECHENGXXService kechengxxService) {
		this.kechengxxService = kechengxxService;
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}

	public JIAOSHIService getJiaoshiService() {
		return jiaoshiService;
	}

	public void setJiaoshiService(JIAOSHIService jiaoshiService) {
		this.jiaoshiService = jiaoshiService;
	}

	public SKXSXXService getSkxsxxService() {
		return skxsxxService;
	}

	public void setSkxsxxService(SKXSXXService skxsxxService) {
		this.skxsxxService = skxsxxService;
	}

	public StudentService getXueshengService() {
		return xueshengService;
	}

	public void setXueshengService(StudentService xueshengService) {
		this.xueshengService = xueshengService;
	}

	public String getSKBJ() {
		return SKBJ;
	}

	public void setSKBJ(String sKBJ) {
		SKBJ = sKBJ;
	}

	public KECHENGBService getKechengbService() {
		return kechengbService;
	}

	public void setKechengbService(KECHENGBService kechengbService) {
		this.kechengbService = kechengbService;
	}

	public String getCAPACITY() {
		return CAPACITY;
	}

	public void setCAPACITY(String cAPACITY) {
		CAPACITY = cAPACITY;
	}

	public String getNUMSELECTED() {
		return NUMSELECTED;
	}

	public void setNUMSELECTED(String nUMSELECTED) {
		NUMSELECTED = nUMSELECTED;
	}

	public String getXUEQI() {
		return XUEQI;
	}

	public void setXUEQI(String xUEQI) {
		XUEQI = xUEQI;
	}

	public KECHENGXXLBService getKechengxxlbService() {
		return kechengxxlbService;
	}

	public void setKechengxxlbService(KECHENGXXLBService kechengxxlbService) {
		this.kechengxxlbService = kechengxxlbService;
	}

	public XUEQIService getXueqiService() {
		return xueqiService;
	}

	public void setXueqiService(XUEQIService xueqiService) {
		this.xueqiService = xueqiService;
	}

	public String getMaxrs() {
		return maxrs;
	}

	public void setMaxrs(String maxrs) {
		this.maxrs = maxrs;
	}

}