package com.hrbsys.kaifang.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.XueShengKQ;
import com.hrbsys.jiaogong.service.JIAOGONGService;
import com.hrbsys.kaifang.service.KaiFangService;
import com.hrbsys.kechengb.service.KECHENGBService;
import com.hrbsys.kqxxxscq.service.KQXX_XSCQService;
import com.hrbsys.realtime.service.RealTimeService;
import com.hrbsys.tools.JsonPrintTools;

/**
 * 开放性课程Action
 */
public class KaiFangAction {

	private RealTimeService realTimeService;// 调用实时查询数据的接口
	private KECHENGBService kcbService;// 课程表接口
	private KaiFangService kaiFangService;// 开放性课程接口
	private StudentService stuService;// 学生信息接口
	private JIAOGONGService jgService;// 教工信息接口
	private KQXX_XSCQService kqxx_xsService;

	private String JS_ID;// 教室ID
	private String JG_ID;// 老师ID
	private String KS_ID;// 课时ID
	private String KCXX_ID;// 课程信息ID
	private String KCB_ID;// 课程表id
	private String KCB_FXS_ID;// 课程表分学时ID
	private String skkssj;// 上课的开始时间
	private String xkjssj;// 下课的结束时间
	private String KCXXMC;// 课程信息名称
	private String xsxh;// 学生学号
	private String xsxm;// 学生姓名
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	// 排序相关
	private String order;
	private String sort;

	public void listKaiFang() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		HashMap<String, String> params = new HashMap<String, String>();
		JsonConfig config = new JsonConfig();

		params.put("JS_ID", this.JS_ID);
		params.put("JG_ID", this.JG_ID);
		params.put("KS_ID", this.KS_ID);
		params.put("KCB_ID", this.KCB_ID);
		params.put("skkssj", this.skkssj);
		params.put("xkjssj", this.xkjssj);
		// 用于存储出勤的人数
		int zt = 0;
		int cd = 0;
		int zc = 0;
		int cdAndzt = 0;

		// 分页相关变量
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		/*
		 * 进行往天的数据查询
		 */
		if (this.JS_ID != null && !"".equals(this.JS_ID) || this.KS_ID != null
				&& !"".equals(this.KS_ID) || this.JG_ID != null
				&& !"".equals(this.JG_ID) || this.skkssj != null
				&& !"".equals(this.skkssj) || this.xkjssj != null
				&& !"".equals(this.xkjssj)) {
			List dataList = new ArrayList();
			// 查询回“开放课程的信息”
			List<KQXX_XSCQ> list = kaiFangService.findGKaiFangByPageApp(start,
					number, params, order, sort);
			// 判断数据集合是否为空
			if (list.isEmpty()) {
				// 当前数据集合为空时，调用此方法
				jsonNull();
			} else {
				Set set = new HashSet();
				// 获取记录中的课程表id
				for (int i = 0; i < list.size(); i++) {
					// 获取往天记录的每一个记录对象
					KQXX_XSCQ xs1 = list.get(i);
					// 获取每一个课程表id并去重复
					set.add(xs1.getKCB_ID());
				}

				// 对set集合进行迭代，获取其中存放的每一个课程表id
				Iterator it = set.iterator();
				while (it.hasNext()) {
					String gh = ""; // 要存放工号

					XueShengKQ xs = new XueShengKQ();
					String kcb_id = (String) it.next();
					// 获取到课程表id后去课程表中查询到相应的信息
					KECHENGB kcb = kcbService.findKECHENGBSbyKCBID(kcb_id).get(
							0);
					// 将获取的数据存放到xs的对象中
					xs.setKs_id(kcb.getKS_ID());
					xs.setKsmc(kcb.getKSMC());
					xs.setKcb_id(kcb.getKCB_ID());
					xs.setKcmc(kcb.getKCXXMC());
					xs.setJs_id(kcb.getJS_ID());
					xs.setJsmc(kcb.getJSMC());
					xs.setKcb_fxs_id(kcb.getKCB_FXS_ID());
					xs.setSksj(String.valueOf(kcb.getKS_KSSJ()));
					String sksj = this.skkssj + "   " + kcb.getKS_KSSJ()
							+ ":00";
					String xksj = this.xkjssj + "   " + kcb.getKS_JSSJ()
							+ ":00";
					String js_id = kcb.getJS_ID();
					// 调用findKFXLS方法,获取老师集合
					List allLS = kaiFangService.findKFXLS(sksj, xksj, js_id);
					for (int i = 0; i < allLS.size(); i++) {
						Object[] tmpObjArray = (Object[]) allLS.get(i);
						gh = (null == tmpObjArray[4]) ? "" : tmpObjArray[4]
								.toString();// 获取最后一个打指纹的老师的工号
						break;
					}

					// 根据查询出来的工号去查询老师信息
					JIAOGONG jg = new JIAOGONG();
					List allJG = kaiFangService.findJG(gh);
					for (int i = 0; i < allJG.size(); i++) {
						Object[] tmpObjArray = (Object[]) allJG.get(i);
						JIAOGONG j = new JIAOGONG();
						j.setJG_ID((null == tmpObjArray[0]) ? ""
								: tmpObjArray[0].toString());
						j.setJGMC((null == tmpObjArray[2]) ? ""
								: tmpObjArray[2].toString());
						jg = j;
					}

					// 根据课程表id去获取总的出勤情况
					String kcb_fxs_id = kcb.getKCB_FXS_ID();
					for (int i = 0; i < list.size(); i++) {
						KQXX_XSCQ kqxs = list.get(i);
						if (String.valueOf(kcb_id).equals(kqxs.getKCB_ID())
								&& String.valueOf(kcb.getKCB_FXS_ID()).equals(
										kcb_fxs_id)) {
							if ("早退".equals(kqxs.getCQQK())) {
								zt++;
							}
							if ("迟到".equals(kqxs.getCQQK())) {
								cd++;
							}
							if ("正常".equals(kqxs.getCQQK())) {
								zc++;
							}
							if ("迟到&早退".equals(kqxs.getCQQK())) {
								cdAndzt++;
							}
						}
					}
					xs.setZc(String.valueOf(zc));// 出勤正常
					xs.setZt(String.valueOf(zt));// 出勤早退
					xs.setCd(String.valueOf(cd));// 出勤迟到
					xs.setCdAndzt(String.valueOf(cdAndzt));// 出勤早退及迟到
					xs.setLsxm(jg.getJGMC());
					xs.setLs_id(jg.getJG_ID());
					// xs对象放入到list集合中
					dataList.add(xs);
				}

				// 将数据信息返回到前台，进行显示
				jsonMap.put("total", dataList.size());
				jsonMap.put("rows", dataList);// rows键 存放每页记录 list
				jsonMap.put("page", intPage);
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
			}
		} else {
			/*
			 * 进行实时的数据查询
			 */
			List dataList = new ArrayList();
			String gh = "";
			// 获取当前系统时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			// 查询出当前“开放性课程”课程信息
			List realKcxx = kaiFangService.findRealKcxx(time);
			// 遍历课程信息集合
			for (int i = 0; i < realKcxx.size(); i++) {
				XueShengKQ xs = new XueShengKQ();
				Object[] obj = (Object[]) realKcxx.get(i);
				// 获取数据，将其存放到变量中
				String kcb_id = (null == obj[0]) ? "" : obj[0].toString();
				String kcb_fxs_id = (null == obj[1]) ? "" : obj[1].toString();
				String kcxx_id = (null == obj[2]) ? "" : obj[2].toString();
				String kcxx = (null == obj[3]) ? "" : obj[3].toString();
				String js_id = (null == obj[6]) ? "" : obj[6].toString();
				String jsmc = (null == obj[7]) ? "" : obj[7].toString();
				String ks_id = (null == obj[8]) ? "" : obj[8].toString();
				String ksmc = (null == obj[9]) ? "" : obj[9].toString();
				String kclb = (null == obj[10]) ? "" : obj[10].toString();
				String sksj = (null == obj[23]) ? "" : obj[23].toString();
				String xksj = (null == obj[24]) ? "" : obj[24].toString();
				// 将获取到的信息，存入到对象的属性中
				xs.setKcmc(kcxx);
				xs.setKcb_id(kcb_id);
				String sksj1 = time + "   " + sksj + ":00";
				String xksj1 = time + "   " + xksj + ":00";
				// 获取实时上“开放性课程”的老师
				List allLS = kaiFangService.findRealKFXLS(sksj1, xksj1, js_id);
				for (int ls_i = 0; ls_i < allLS.size(); ls_i++) {
					Object[] tmpObjArray = (Object[]) allLS.get(ls_i);
					gh = (null == tmpObjArray[4]) ? "" : tmpObjArray[4]
							.toString();// 获取最后一个打指纹的老师的工号
					break;
				}
				// 根据查询出来的工号去查询老师信息
				JIAOGONG jg = new JIAOGONG();
				List allJG = kaiFangService.findJG(gh);
				if (allJG.isEmpty()) {
					xs.setLsxm("无老师上课");
					xs.setLs_id(null);
				} else {
					for (int jg_i = 0; jg_i < allJG.size(); jg_i++) {
						Object[] tmpObjArray = (Object[]) allJG.get(jg_i);
						JIAOGONG j = new JIAOGONG();
						j.setJG_ID((null == tmpObjArray[0]) ? ""
								: tmpObjArray[0].toString());
						j.setJGMC((null == tmpObjArray[2]) ? ""
								: tmpObjArray[2].toString());
						jg = j;
					}
					xs.setLsxm(jg.getJGMC());
					xs.setLs_id(jg.getJG_ID());
				}
				xs.setJs_id(js_id);
				xs.setJsmc(jsmc);
				xs.setKcb_fxs_id(kcb_fxs_id);
				xs.setKsmc(ksmc);
				xs.setSksj(sksj);
				// 统计实时数据，统计出勤情况
				ArrayList<XueShengKQ> listKaiFang1 = realTimeService
						.getKQQK_KFK(js_id, null, ks_id, kcb_id, time);

				// 判断获取的数据集合是否为空
				if (listKaiFang1.isEmpty()) {
					// 如果数据集合为空，则正常，迟到，早退，早退&迟到 都为0
					xs.setZc(String.valueOf(0));
					xs.setCd(String.valueOf(0));
					xs.setZt(String.valueOf(0));
					xs.setCdAndzt(String.valueOf(0));
				} else {
					for (int j = 0; j < listKaiFang1.size(); j++) {
						// 如果数据集合不为空，则获取对象，读取其统计数据
						XueShengKQ xscq = listKaiFang1.get(i);
						if ("早退".equals(xscq.getCqqk())) {
							zt++;
						}
						if ("迟到".equals(xscq.getCqqk())) {
							cd++;
						}
						if ("正常".equals(xscq.getCqqk())) {
							zc++;
						}
						if ("迟到&早退".equals(xscq.getCqqk())) {
							cdAndzt++;
						}

					}
					xs.setZc(String.valueOf(zc));
					xs.setCd(String.valueOf(cd));
					xs.setZt(String.valueOf(zt));
					xs.setCdAndzt(String.valueOf(cdAndzt));
				}
				// 将对象加入到集合中
				dataList.add(xs);
			}

			if (dataList.isEmpty()) {
				jsonNull();
			} else {
				// 总的记录条数
				jsonMap.put("total", dataList.size());
				// rows键 存放每页记录 list
				jsonMap.put("rows", dataList);
				jsonMap.put("page", intPage);
				// 将jsonMap中的信息返回到前台页面中
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
			}

		}
	}

	/**
	 * 详细页面数据展示
	 */
	public void detail() {
		/*
		 * 1.当开始时间与结束时间都不为空时，进行往天查询 2.当开始间与结束时间有一个为空，就进行实时查询
		 */
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		HashMap<String, String> params = new HashMap<String, String>();
		JsonConfig config = new JsonConfig();
		// 根据分学时id去查询出当前课程的人数容量
		KECHENGB kcb = kcbService.findKECHENGB(this.KCB_FXS_ID);
		params.put("JS_ID", this.JS_ID);
		params.put("JG_ID", this.JG_ID);
		params.put("KS_ID", this.KS_ID);
		params.put("KCB_ID", this.KCB_ID);
		params.put("kbc_fxs", this.KCB_FXS_ID);
		params.put("skkssj", this.skkssj);
		params.put("xkjssj", this.xkjssj);
		params.put("xsxh", this.xsxh);
		params.put("xsxm", this.xsxm);
		params.put("maxrs", kcb.getMAXRS());

		// 分页相关变量
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;

		/*
		 * 进行往天查询： 1.上课的开始日期与结束日期都不能为空，有一个为空，就认为是实时查询
		 */
		if (this.skkssj != null && !"".equals(this.skkssj)
				&& this.xkjssj != null && !"".equals(this.xkjssj)) {
			List dataList = new ArrayList();
			// 查询回“开放课程的信息”
			List list = kaiFangService.findGKaiFangByPageApp(start, number,
					params, order, sort);

			// 存放要显示的数据的集合
			List detailList = new ArrayList();
			/*
			 * 遍历数据集合
			 */
			if (list.isEmpty()) {
				jsonNull();
			} else {
				// 根据教工的jg_id查询出教工的具体信息
				JIAOGONG jg = jgService.findJIAOGONG(this.JG_ID);
				for (int i = 0; i < list.size(); i++) {
					XueShengKQ xs = new XueShengKQ();
					KQXX_XSCQ xs1 = (KQXX_XSCQ) list.get(i);// 获取每个数据对象
					xs.setXskqid(xs1.getKQXX_XSCQ_ID());// 学生考勤id
					xs.setKcb_id(xs1.getKCB_ID());// 课程表id
					xs.setKcmc(xs1.getKCMC());// 课程名称
					xs.setLsxm(jg.getJGMC());// 上课老师
					xs.setXsxh(xs1.getXSXH());// 学号
					xs.setXsxm(xs1.getXSXM());// 学生姓名
					xs.setCqqk(xs1.getCQQK());// 出勤情况
					xs.setSksj(String.valueOf(xs1.getSKSJ()).split(" ")[1]
							.substring(0, 5));// 上课打卡时间
					xs.setXksj(xs1.getXKSJ().split(" ")[1].substring(0, 5));// 下课打卡时间
					// 向detailList添加数据对象
					detailList.add(xs);
				}

				// 将数据信息返回到前台，进行显示
				jsonMap.put("total", detailList.size());
				jsonMap.put("rows", detailList);// rows键 存放每页记录 list
				jsonMap.put("page", intPage);
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
			}

		} else {
			/*************************************************/
			/*
			 * 进行实时的数据查询
			 */
			List dataList = new ArrayList();
			// 获取当前系统时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(new Date());
			// 详细页面 实时查询 查询课程表"开放性课程"中的所有信息
			ArrayList<XueShengKQ> listKaiFang = realTimeService.getKQQK_KFK(
					this.getJS_ID(), null, this.getKS_ID(), this.getKCB_ID(),
					time);
			List list = new ArrayList();
			// 判断数据集合是否为空
			if (listKaiFang.isEmpty()) {
				// 当数据集合为空时，调用此方法
				jsonNull();
			} else {
				String jgmc="";
				//根据教工id获取教工的具体信息
				JIAOGONG jg=jgService.findJIAOGONG(this.JG_ID);
				//判断教工对象是否为空
				if (jg==null) {
					jgmc="无老师上课";
				}else{
					jgmc=jg.getJGMC();
				}
				// 当数据集合不为空时，进行遍历数据
				for (int i = 0; i < listKaiFang.size(); i++) {
					XueShengKQ xs = new XueShengKQ();
					XueShengKQ xs1 = listKaiFang.get(i);
					xs.setKs_id(xs1.getKs_id());
					xs.setKsmc(xs1.getKsmc());
					xs.setKcb_id(xs1.getKcb_id());
					xs.setLsxm(jgmc);
					xs.setKcmc(xs1.getKcxxmc());
					xs.setJsmc(xs1.getJsmc());
					xs.setJs_id(xs1.getJs_id());
					xs.setSksj(String.valueOf(xs1.getSksj()).split(" ")[0]);
					xs.setXksj(xs1.getXksj());
					xs.setXsxh(xs1.getXuehao());
					Xsxx xsxx = stuService.findStudentById(xs1.getXs_id());
					xs.setXsxm(xsxx.getZsxm());
					xs.setCqqk(xs1.getCqqk());
					// 向list集合中添加数据
					list.add(xs);
				}
				
				// 对实时数据进行过滤 获取用户想查询的数据
				if (this.xsxh != null && !"".equals(this.xsxh)
						|| this.xsxm != null && !"".equals(this.xsxm)) {
					// 在用户获取要查询的记录之前，先清空存储数据的集合
					list.clear();
					// 遍历实时数据，并过滤用户想要的数据
					for (int i = 0; i < listKaiFang.size(); i++) {
						XueShengKQ xs = new XueShengKQ();
						XueShengKQ xs1 = listKaiFang.get(i);
						Xsxx xsxx = stuService.findStudentById(xs1.getXs_id());
						if (this.xsxh.equals(xsxx.getXh())
								|| this.xsxm.equals(xsxx.getZsxm())) {
							xs.setKsmc(xs1.getKsmc());
							xs.setKcmc(xs1.getKcxxmc());
							xs.setLsxm(xs1.getLsmc());
							xs.setSksj(String.valueOf(xs1.getSksj()).split(" ")[0]);
							xs.setXksj(xs1.getXksj());
							xs.setXsxh(xsxx.getXh());
							xs.setXsxm(xsxx.getZsxm());
							xs.setCqqk(xs1.getCqqk());
							// 将符合用户要求的数据放入到其中
							list.add(xs);
						}
					}
				}
				 // 判断过滤后的数据集合大小是否小于0
				if (list.size() < 0) {
					jsonNull();
					return;
				}

				// 判断是升序还是降序 asc 升序 desc降序
				if (this.order != null && !"".equals(this.order)) {
					if (this.order.equals("desc")) {
						// 集合倒序
						Collections.reverse(list);
					}
				}

				// 总的记录条数
				jsonMap.put("total", list.size());
				// rows键 存放每页记录 list
				jsonMap.put("rows", list);
				jsonMap.put("page", intPage);
				// 将jsonMap中的信息返回到前台页面中
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
						config));
			}

		}

	}

	/**
	 * 当查询的数据为空时，调用此方法
	 */
	public void jsonNull() {
		JsonConfig config = new JsonConfig();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 用于存储参数
		List noData = new ArrayList();// 存储“无记录”信息
		XueShengKQ xs = new XueShengKQ();
		xs.setXskqid("null");
		xs.setKcmc("无记录");
		xs.setZc("无记录");
		xs.setJsmc("无记录");
		xs.setLsxm("无记录");
		xs.setZc("无记录");
		xs.setCd("无记录");
		xs.setZt("无记录");
		xs.setCdAndzt("无记录");
		xs.setKsmc("无记录");
		xs.setSksj("无记录");
		xs.setXsxh("无记录");
		xs.setXksj("无记录");
		xs.setXsxm("无记录");
		xs.setCqqk("无记录");
		noData.add(xs);
		jsonMap.put("total", noData.size());
		jsonMap.put("rows", noData);// rows键 存放每页记录 list
		jsonMap.put("page", 1);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
		return;
	}

	/**
	 * get set方法
	 * 
	 * @return
	 */
	public KaiFangService getKaiFangService() {
		return kaiFangService;
	}

	public void setKaiFangService(KaiFangService kaiFangService) {
		this.kaiFangService = kaiFangService;
	}

	public RealTimeService getRealTimeService() {
		return realTimeService;
	}

	public void setRealTimeService(RealTimeService realTimeService) {
		this.realTimeService = realTimeService;
	}

	public KECHENGBService getKcbService() {
		return kcbService;
	}

	public void setKcbService(KECHENGBService kcbService) {
		this.kcbService = kcbService;
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

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String jS_ID) {
		JS_ID = jS_ID;
	}

	public String getJG_ID() {
		return JG_ID;
	}

	public void setJG_ID(String jG_ID) {
		JG_ID = jG_ID;
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String kS_ID) {
		KS_ID = kS_ID;
	}

	public String getKCXX_ID() {
		return KCXX_ID;
	}

	public void setKCXX_ID(String kCXX_ID) {
		KCXX_ID = kCXX_ID;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String kCB_ID) {
		KCB_ID = kCB_ID;
	}

	public String getSkkssj() {
		return skkssj;
	}

	public void setSkkssj(String skkssj) {
		this.skkssj = skkssj;
	}

	public String getXkjssj() {
		return xkjssj;
	}

	public void setXkjssj(String xkjssj) {
		this.xkjssj = xkjssj;
	}

	public StudentService getStuService() {
		return stuService;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}

	public String getKCXXMC() {
		return KCXXMC;
	}

	public void setKCXXMC(String kCXXMC) {
		KCXXMC = kCXXMC;
	}

	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}

	public String getKCB_FXS_ID() {
		return KCB_FXS_ID;
	}

	public void setKCB_FXS_ID(String kCB_FXS_ID) {
		KCB_FXS_ID = kCB_FXS_ID;
	}

	public JIAOGONGService getJgService() {
		return jgService;
	}

	public void setJgService(JIAOGONGService jgService) {
		this.jgService = jgService;
	}

	public KQXX_XSCQService getKqxx_xsService() {
		return kqxx_xsService;
	}

	public void setKqxx_xsService(KQXX_XSCQService kqxx_xsService) {
		this.kqxx_xsService = kqxx_xsService;
	}

}
