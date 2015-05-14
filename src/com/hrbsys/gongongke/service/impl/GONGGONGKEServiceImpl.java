package com.hrbsys.gongongke.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.gongongke.service.GONGGONGKEService;
import com.hrbsys.xuesheng.service.XsxxService;

/**
 * 公共课接口实现类
 * 
 */
public class GONGGONGKEServiceImpl implements GONGGONGKEService {

	private BaseDao baseDao;

	// private List all_queqin;// 缺席
	// private List all_kaoqin_zhengchang;// 正常
	// private List all_kaoqin_chidao;// 迟到
	// private List all_kaoqin_zaotui;// 早退

	/**
	 * 获取公共课的列表
	 */
	@Override
	public List<KECHENGB> findGONGGONGKByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "FROM KECHENGB WHERE  1=1 and kcblb='公共选修课' ";
		String param = "";

		ArrayList<String> params2 = new ArrayList<String>();
		// 老师名称
		if (null != params.get("LAOSHIMC")
				&& !"".equals(params.get("LAOSHIMC"))) {
			param = "and LAOSHIMC like '%" + params.get("LAOSHIMC").toString()
					+ "%'";
			params2.add(param);
		}
		// 教室名称
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%" + params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		// 课程信息名称
		if (null != params.get("KCXXMC") && !"".equals(params.get("KCXXMC"))) {
			param = "and KCXXMC like '%" + params.get("KCXXMC").toString()
					+ "%'";
			params2.add(param);
		}

		// 判断日期的开始时间与结束是否为空
		if (null != params.get("KS_KSSJ") && !"".equals(params.get("KS_KSSJ"))
				&& null != params.get("KS_JSSJ")
				&& !"".equals(params.get("KS_JSSJ"))) {
			// 当前开始时间与结束时间都不为空时,设置查询往天的条件
			param = " and XINGQIXH between (select to_char(to_date('"
					+ params.get("KS_KSSJ").toString()
					+ "','yyyy-mm-dd'),'D') from Dual) "
					+ "and (select to_char(to_date('"
					+ params.get("KS_JSSJ").toString()
					+ "','yyyy-mm-dd'),'D') from Dual)";
			params2.add(param);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 当开始时间与结束时间为空时，设置查询实时的条件
			param = " and XINGQIXH=(select  to_char(to_date('"
					+ sdf.format(new Date())
					+ "','yyyy-mm-dd'),'D') from Dual) ";
			params2.add(param);
		}
		// // 课时的开始时间与结束时间
		// if (null != params.get("KS_KSSJ") &&
		// !"".equals(params.get("KS_KSSJ"))
		// && null != params.get("KS_JSSJ")
		// && !"".equals(params.get("KS_JSSJ"))) {
		// // 查询课程表的时候加上日期标志
		// // 同时不为空时
		// param = " and (XINGQIXH=(select  to_char(to_date('"
		// + params.get("KS_KSSJ").toString()
		// +
		// "','yyyy-mm-dd'),'D') from Dual) or XINGQIXH =(select to_char(to_date('"
		// + params.get("KS_JSSJ").toString()
		// + "','yyyy-mm-dd'),'D') from Dual))";
		// params2.add(param);
		// } else if (null == params.get("KS_KSSJ")
		// && "".equals(params.get("KS_KSSJ"))
		// && null != params.get("KS_JSSJ")
		// && !"".equals(params.get("KS_JSSJ"))) {
		// // 开始时间为空，结束时间不为空
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// param = " and (XINGQIXH=(select  to_char(to_date('"
		// + sdf.format(new Date())
		// +
		// "','yyyy-mm-dd'),'D') from Dual) or XINGQIXH =(select to_char(to_date('"
		// + params.get("KS_KSSJ").toString()
		// + "','yyyy-mm-dd'),'D') from Dual))";
		// params2.add(param);
		// } else if (null != params.get("KS_KSSJ")
		// && !"".equals(params.get("KS_KSSJ"))
		// && null == params.get("KS_JSSJ")
		// && "".equals(params.get("KS_JSSJ"))) {
		// // 开始时间不为空，结束时间为空
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// param = " and (XINGQIXH=(select  to_char(to_date('"
		// + params.get("KS_JSSJ").toString()
		// +
		// "','yyyy-mm-dd'),'D') from Dual) and XINGQIXH =(select to_char(to_date('"
		// + sdf.format(new Date())
		// + "','yyyy-mm-dd'),'D') from Dual))";
		// params2.add(param);
		// } else if (null == params.get("KS_KSSJ")
		// && "".equals(params.get("KS_KSSJ"))
		// && null == params.get("KS_JSSJ")
		// && "".equals(params.get("KS_JSSJ"))) {
		// // 同时为空时
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// param = " and (XINGQIXH=(select  to_char(to_date('"
		// + sdf.format(new Date())
		// +
		// "','yyyy-mm-dd'),'D') from Dual) or XINGQIXH =(select to_char(to_date('"
		// + sdf.format(new Date())
		// + "','yyyy-mm-dd'),'D') from Dual))";
		// params2.add(param);
		// } else {
		// System.out.println("List<KECHENGB> findGONGGONGKByPageApp什么也没有执行！");
		// }
		// 学期
		if (null != params.get("XUEQI") && !"".equals(params.get("XUEQI"))) {
			param = "and XQMC like '%" + params.get("XUEQI").toString() + "%'";
			params2.add(param);
		}
		// 星期
		if (null != params.get("XINGQI") && !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
			params2.add(param);
		}

		if (null != params.get("Day_KSMC")
				&& !"".equals(params.get("Day_KSMC"))) {
			param = "and KSMC like '%" + params.get("Day_KSMC").toString()
					+ "%'";
			params2.add(param);
		}
		// 按学期查询时，如果课时条件为"全部课时"时查询全部的课时
		// if (params.get("Term_KSMC").equals("全部课时")) {
		// param =
		// "and KSMC  in (select KSMC from KECHENGB where kcblb='公共选修课' )";
		// params2.add(param);
		// } else {
		// // 否则，根据用户选择的课时进行相应的查询
		// param = "and KSMC like '%" + params.get("Term_KSMC").toString()
		// + "%'";
		// params2.add(param);
		// }
		// 按学期方式查询中的课时
		// if (null != params.get("Term_KSMC")
		// && !"".equals(params.get("Term_KSMC"))) {
		// // 按学期查询时，如果课时条件为"全部课时"时查询全部的课时
		// if (params.get("Term_KSMC").equals("全部课时")) {
		// param =
		// "and KSMC  in (select KSMC from KECHENGB where kcblb='公共选修课' )";
		// params2.add(param);
		// } else {
		// // 否则，根据用户选择的课时进行相应的查询
		// param = "and KSMC like '%" + params.get("Term_KSMC").toString()
		// + "%'";
		// params2.add(param);
		// }
		//
		// }
		// 按日期查询的课时名称
		// if (null != params.get("Day_KSMC")
		// && !"".equals(params.get("Day_KSMC"))) {
		// // 按日期查询时，如果课时条件为"全部课时"时查询全部的课时
		// if (params.get("Day_KSMC").equals("全部课时")) {
		// param =
		// "and KSMC IN (select KSMC from KECHENGB where kcblb='公共选修课' )";
		// params2.add(param);
		// } else {
		// // 否则，根据用户选择的课时进行相应的查询
		// param = "and KSMC like '%" + params.get("Day_KSMC").toString()
		// + "%'";
		// params2.add(param);
		// }
		// }

		// 查询结果集
		List<KECHENGB> list = baseDao.findByPage(hql, KECHENGB.class, start,
				number, params2, order, sort);
		return list;
	}

	/**
	 * 获取考勤情况
	 * <p>
	 * 获取考勤情况
	 * </p>
	 * 
	 * @param jiaoshi
	 *            教室
	 * @param laoshi
	 *            任课教师
	 * @param xingqi
	 *            星期
	 * @param keshi
	 *            课时
	 * @return 统计情况的MAP，其中map中
	 *         map.get("zong"):应出席总人数、map.get("zheng"):正常出席人数、map
	 *         .get("que"):缺席人数、map.get("chi"):迟到人数、map.get("zao"):早退人数
	 */
	public HashMap<String, Integer> getKQQK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi_ks, String riqi_js) {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new Date()); // 当前时间转化成字符串

		// 根据条件查询的hql语句
		String hql = "select * from kechengb a  where 1=1 and kcblb='公共选修课'  ";
		// 向hql中添加教室ID
		if (jiaoshi != null && !"".equals(jiaoshi)) {
			hql += " and js_id='" + jiaoshi + "'";
		}
		// 向hql中添加老师id
		if (laoshi != null && !"".equals(laoshi)) {
			hql += " and laoshi_id='" + laoshi + "'";
		}
		// 向hql中添加课程表id
		if (kecheng != null && !"".equals(kecheng)) {
			hql += " and kcb_id='" + kecheng + "'";
		}
		if (keshi != null && !"".equals(keshi)) {
			hql += " and ks_id='" + keshi + "'";
		}
		// 控制台提示信息
		System.out.println("GongGongKeServiceImpl中打印::" + "查询sql语句时：" + hql);
		// 执行HQL语句，查出要统计的所有考勤信息集合
		List all_kcb = baseDao.executeSQL(hql);
		Map<String, String> map = new HashMap<String, String>();
		/*
		 * 统计出勤情况相关变量
		 */
		int sum_yingchuxi = 0;// 应出席
		int sum_quexi = 0;// 缺席
		int sum_zhengchang = 0;// 正常
		int sum_chidao = 0;// 迟到
		int sum_zaotui = 0;// 早退

		/*
		 * 循环课程列表，计算考勤信息
		 */
		for (int kcb_i = 0; kcb_i < all_kcb.size(); kcb_i++) {
			Object[] tmpObjArray = (Object[]) all_kcb.get(kcb_i);
			String kcb_id = (null == tmpObjArray[0]) ? "" : tmpObjArray[0]
					.toString();
			String kcb_fks_id = (null == tmpObjArray[1]) ? "" : tmpObjArray[1]
					.toString();
			String kcxx_id = (null == tmpObjArray[2]) ? "" : tmpObjArray[2]
					.toString();
			String kcxxmc = (null == tmpObjArray[3]) ? "" : tmpObjArray[3]
					.toString();
			String laoshi_id = (null == tmpObjArray[4]) ? "" : tmpObjArray[4]
					.toString();
			String laoshimc = (null == tmpObjArray[5]) ? "" : tmpObjArray[5]
					.toString();
			String js_id = (null == tmpObjArray[6]) ? "" : tmpObjArray[6]
					.toString();
			String jsmc = (null == tmpObjArray[7]) ? "" : tmpObjArray[7]
					.toString();
			String ks_id = (null == tmpObjArray[8]) ? "" : tmpObjArray[8]
					.toString();
			String ksmc = (null == tmpObjArray[9]) ? "" : tmpObjArray[9]
					.toString();
			String kcblb = (null == tmpObjArray[10]) ? "" : tmpObjArray[10]
					.toString();
			String xingqi = (null == tmpObjArray[11]) ? "" : tmpObjArray[11]
					.toString();
			String kszhou = (null == tmpObjArray[12]) ? "" : tmpObjArray[12]
					.toString();
			String jszhou = (null == tmpObjArray[13]) ? "" : tmpObjArray[13]
					.toString();
			String xingqixh = (null == tmpObjArray[22]) ? "" : tmpObjArray[22]
					.toString();
			String ks_kssj = (null == tmpObjArray[23]) ? "" : tmpObjArray[23]
					.toString();
			String ks_jssj = (null == tmpObjArray[24]) ? "" : tmpObjArray[24]
					.toString();
			String laoshi_gh = (null == tmpObjArray[25]) ? "" : tmpObjArray[25]
					.toString();
			System.out.println("GongGongKeServiceImpl中打印::" + "当前课程表为："
					+ kcxxmc + "---------->" + laoshimc + "---------->"
					+ laoshi_id);
			// 控制台提示信息
			System.out.println("输出的当前时间是: " + nowTime);

			String riqiTime_begin = "";// 日期的开始时间
			String riqiTime_end = "";// 日期的结束时间

			// 判断授课日期的开始时间是否为空
			if (riqi_ks!= null && (!"".equals(riqi_ks))) {
				riqiTime_begin = riqi_ks.trim() + " " + ks_kssj.trim() + ":00";
			} else {
				// 如果日期的开始时间为空设置为当前系统时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				riqiTime_begin = sdf.format(new Date()) + " " + ks_kssj.trim()
						+ ":00";
			}
			// 判断授课日期的结束时间是否为空
			if (riqi_js!= null && (!"".equals(riqi_js))) {
				riqiTime_end = riqi_js.trim() + " " + ks_kssj.trim() + ":00";
			} else {
				// 如果日期的结束时间为空设置为当前系统时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				riqiTime_end = sdf.format(new Date()) + " " + ks_jssj.trim()
						+ ":00";
			}
			// 控制台提示信息
			System.out.println("GongGongKeServiceImpl中打印::" + "输出的传入日期时间是: "
					+ riqiTime_begin);
			System.out.println("GongGongKeServiceImpl中打印::"
					+ "当前时间<上课时间 比较结果是: "
					+ !compare_date(nowTime, riqiTime_begin));
			System.out.println("GongGongKeServiceImpl中打印::"
					+ "如果上课时间<当前时<下课时间 比较结果是: "
					+ (compare_date(nowTime, riqiTime_begin) && !compare_date(
							nowTime, riqiTime_end)));
			System.out.println("GongGongKeServiceImpl中打印::"
					+ "当前时间>下课时间：正常计算出勤情况 比较结果是: "
					+ compare_date(nowTime, riqiTime_end));

			// 如果当前时间<上课时间：出勤情况计算为全部出勤
			if (!compare_date(nowTime, riqiTime_begin)) {
				sum_yingchuxi += 0;
				sum_quexi += 0;
				sum_zhengchang += 0;
				sum_chidao += 0;
				sum_zaotui += 0;
				System.out.println("GongGongKeServiceImpl中打印::"
						+ "当前时间<开始时间，不计算考勤#############");
			}
			/*
			 * //如果上课时间<当前时<下课时间 ：不计算早退 //如果上课时间<当前时<下课时间 ：缺席：无打卡记录的
			 * //如果上课时间<当前时<下课时间 ：迟到：上课时间段无记录的 //如果上课时间<当前时<下课时间 ：正常：上课时间有打卡记录的
			 */
			if (compare_date(nowTime, riqiTime_begin)
					&& !compare_date(nowTime, riqiTime_end)) {
				System.out
						.println("GongGongKeServiceImpl中打印::"
								+ "如果上课时间<当前时<下课时间============================================================================================================begin");
				/*
				 * 应出勤统计
				 */
				String ycxsql = "select distinct xs_id,t.xsxm,t.xuehao from skxsxx t where t.kcb_id='"
						+ kcb_id
						+ "' and t.xs_id in (select x.xs_id from xsxx x where 1=1";
				ycxsql += ")";
				System.out.println("RealTimeServiceImpl中打印::" + "应出席人数sql："
						+ ycxsql);
				List all_ying = baseDao.executeSQL(ycxsql);

				sum_yingchuxi += all_ying.size();

				/*
				 * 缺席统计
				 */
				String queqinsql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ "k.xh in (select distinct d.xuehao from skxsxx d where d.kcb_id ='"
						+ tmpObjArray[0]
						+ "' minus select distinct e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ tmpObjArray[0]
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ tmpObjArray[6]
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ tmpObjArray[8]
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ tmpObjArray[8] + "'))";

				// 控制台信息
				System.out.println("GongGongKeServiceImpl中打印::" + "缺席人数sql："
						+ queqinsql);

				List all_queqin = baseDao.executeSQL(queqinsql);
				sum_quexi += all_queqin.size();
				/*
				 * 正常出勤统计
				 */
				String zhengchangsql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ " k.xh in(select e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ kcb_id
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ js_id
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ ks_id + "')) ";

				System.out.println("GongGongKeServiceImpl中打印::" + "正常人数SQL："
						+ zhengchangsql);
				List all_kaoqin_zhengchang = baseDao.executeSQL(zhengchangsql);
				sum_zhengchang += all_kaoqin_zhengchang.size();
				/*
				 * 迟到统计
				 */
				String chidaosql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ " k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_end
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "'))"
						+ "and k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_end
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "'))"
						+ " and k.xh not in(select e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ kcb_id
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ js_id
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_end
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ ks_id + "')) ";

				System.out.println("GongGongKeServiceImpl中打印::" + "迟到人数SQL："
						+ chidaosql);
				List all_kaoqin_chidao = baseDao.executeSQL(chidaosql);

				int czcount = all_ying.size() - all_queqin.size()
						- all_kaoqin_zhengchang.size()
						- all_kaoqin_chidao.size();// 既迟到又早退：应出席-缺勤-正常-迟到-早退
//				sum_chidao += all_kaoqin_chidao.size() + czcount;
				sum_chidao += all_kaoqin_chidao.size();
				sum_zaotui += 0;
				System.out
						.println("GongGongKeServiceImpl中打印::"
								+ "如果上课时间<当前时<下课时间============================================================================================================end");
			}
			// 当前时间>下课时间：正常计算出勤情况
			if (compare_date(nowTime, riqiTime_end)) {
				System.out
						.println("GongGongKeServiceImpl中打印::"
								+ "当前时间>下课时间============================================================================================================begin");
				/*
				 * 应出勤统计
				 */
				String ycxsql = "select distinct xs_id,t.xsxm,t.xuehao from skxsxx t where t.kcb_id='"
						+ kcb_id
						+ "' and t.xs_id in (select x.xs_id from xsxx x where 1=1";
				ycxsql += ")";
				System.out.println("RealTimeServiceImpl中打印::" + "应出席人数sql："
						+ ycxsql);
				// 增加班级年级统计end######################################################################################
				List all_ying = baseDao.executeSQL(ycxsql);
				sum_yingchuxi += all_ying.size();
				/*
				 * 缺席统计
				 */
				String queqinsql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ "k.xh in (select distinct d.xuehao from skxsxx d where d.kcb_id ='"
						+ tmpObjArray[0]
						+ "' minus select distinct e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ tmpObjArray[0]
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ tmpObjArray[6]
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ tmpObjArray[8]
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ tmpObjArray[8] + "'))";

				System.out.println("GongGongKeServiceImpl中打印::" + "缺席人数sql："
						+ queqinsql);

				List all_queqin = baseDao.executeSQL(queqinsql);

				sum_quexi += all_queqin.size();

				String zhengchangsql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ " k.xh in(select e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ kcb_id
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ js_id
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "')) "
						+ "and k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id + "'))";
				System.out.println("RealTimeServiceImpl中打印::" + "正常人数SQL："
						+ zhengchangsql);

				/*
				 * 正常出勤统计
				 */
				List all_kaoqin_zhengchang = baseDao.executeSQL(zhengchangsql);

				sum_zhengchang += all_kaoqin_zhengchang.size();
				String chidaosql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ " k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "'))"
						+ "and k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "'))"
						+ " and k.xh not in(select e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ kcb_id
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ js_id
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ ks_id + "')) ";
				System.out.println("GongGongKeServiceImpl中打印::" + "迟到SQL："
						+ chidaosql);
				List all_kaoqin_chidao = baseDao.executeSQL(chidaosql);
				/*
				 * 早退统计
				 */
				String zaotuisql = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where "
						+ " k.xh in(select e.xuehao from kqxx_day e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"
						+ kcb_id
						+ "') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"
						+ js_id
						+ "') and e.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"
						+ ks_id
						+ "')) "
						+ "and k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "'))"
						+ "and k.xh not in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"
						+ kcb_id
						+ "') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"
						+ js_id
						+ "') and z.djsj between (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"
						+ ks_id
						+ "') and (select to_date(to_char(to_date('"
						+ riqiTime_begin
						+ "','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"
						+ ks_id + "'))";
				System.out.println("RealTimeServiceImpl中打印::" + "早退人数SQL："
						+ zaotuisql);
				List all_kaoqin_zaotui = baseDao.executeSQL(zaotuisql); // 列出出勤情况正常的考勤信息

				int czcount = all_ying.size() - all_queqin.size()
						- all_kaoqin_zhengchang.size()
						- all_kaoqin_chidao.size() - all_kaoqin_zaotui.size();// 既迟到又早退：应出席-缺勤-正常-迟到-早退
				// //迟到人数=迟到人数+既迟到又早退的人数
//				sum_chidao += all_kaoqin_chidao.size() + czcount;
				sum_chidao += all_kaoqin_chidao.size();
				sum_zaotui += all_kaoqin_zaotui.size() + czcount;
			}

			System.out
					.println("GongGongKeServiceImpl中打印::"
							+ "当前时间>下课时间============================================================================================================end");
		}
		HashMap<String, Integer> totalMap = new HashMap<String, Integer>();
		totalMap.put("yingchuqin", sum_yingchuxi);
		totalMap.put("queqin", sum_quexi);
		totalMap.put("zhengchang", sum_zhengchang);
		totalMap.put("chidao", sum_chidao);
		totalMap.put("zaotui", sum_zaotui);
		return totalMap;
	}

	// // 查询出在某个教室上课学生的信息
	// public List<Xsxx> findClassXsxx() {
	// // 存放每一条记录
	// List<Xsxx> list = new ArrayList<Xsxx>();
	// /*
	// * 正常
	// */
	// if (all_kaoqin_zhengchang.size() > 0) {
	// for (int i = 0; i < all_kaoqin_zhengchang.size(); i++) {
	// Xsxx xsAll = (Xsxx) all_kaoqin_zhengchang.get(i);
	// Xsxx xs = new Xsxx();
	// xs.setXsId(xsAll.getXsId());
	// // xs.setCqzt("正常");
	// list.add(xs);
	// }
	// }
	//
	// /*
	// * 缺席
	// */
	// if (all_queqin.size() > 0) {
	// for (int i = 0; i < all_queqin.size(); i++) {
	// Xsxx xsAll = (Xsxx) all_queqin.get(i);
	// Xsxx xs = new Xsxx();
	// xs.setXsId(xsAll.getXsId());
	// // xs.setCqzt("缺席");
	// list.add(xs);
	// }
	// }
	// // 迟到
	// if (all_kaoqin_chidao.size() > 0) {
	// for (int i = 0; i < all_kaoqin_chidao.size(); i++) {
	// Xsxx xsAll = (Xsxx) all_kaoqin_chidao.get(i);
	// Xsxx xs = new Xsxx();
	// xs.setXsId(xsAll.getXsId());
	// // xs.setCqzt("迟到");
	// list.add(xs);
	// }
	// }
	//
	// /*
	// * 早退
	// */
	// if (all_kaoqin_zaotui != null) {
	// for (int i = 0; i < all_kaoqin_zaotui.size(); i++) {
	// Xsxx xsAll = (Xsxx) all_kaoqin_zaotui.get(i);
	// Xsxx xs = new Xsxx();
	// xs.setXsId(xsAll.getXsId());
	// // xs.setCqzt("早退");
	// list.add(xs);
	// }
	// }
	// // 返回数据信息
	// return list;
	// }

	// date1>date2则 返回true 否则返回false
	public static boolean compare_date(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() - dt2.getTime() > 0) {
				return true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取公共课的老师的名字
	 */
	public List findLAOSHIMC() {
		return baseDao
				.executeSQL("select distinct laoshimc,laoshi_id from kechengb where kcblb='公共选修课'");
	}

	/**
	 * 获取公共课的教室名称
	 */
	@Override
	public List findJSMC() {
		return baseDao
				.executeSQL("select distinct jsmc,js_id from kechengb where kcblb='公共选修课'");
	}

	/*
	 * 查询出往天学生出勤的所有的数据
	 */
	public List findGGKByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		// 查询往天记录的hql
		String hql = "FROM KQXX_XSCQ where 1=1 and kclb='公共选修课'";

		ArrayList<String> params2 = new ArrayList<String>();
		String param = "";
		// 增加教室ID
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			param = " and JS_ID='" + params.get("JS_ID").toString() + "'";
			params2.add(param);
		}
		// 增加教师ID
		if (null != params.get("JG_ID") && !"".equals(params.get("JG_ID"))) {// 增加老师ID
			param += " and LS_ID='" + params.get("JG_ID").toString() + "'";
			params2.add(param);
		}
		// 增加课时ID
		if (null != params.get("KS_ID") && !"".equals(params.get("KS_ID"))) {
			param += " and KESHI_ID='" + params.get("KS_ID").toString() + "'";
			params2.add(param);
		}

		// 增加课程表分课时ID
		if (null != params.get("kbc_fxs") && !"".equals(params.get("kbc_fxs"))) {
			param += " and KCB_FKS_ID='" + params.get("kbc_fxs").toString()
					+ "'";
			params2.add(param);
		}
		// 增加课程表id
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			param += " and KCB_ID='" + params.get("KCB_ID").toString() + "'";
			params2.add(param);
		}

		// 增加上课的开始日期
		if (null != params.get("KS_KSSJ") && !"".equals(params.get("KS_KSSJ"))) {
			param += " and SKSJ>=to_date('" + params.get("KS_KSSJ").toString()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			params2.add(param);
		}
		// 增加上课的结束日期
		if (null != params.get("KS_JSSJ") && !"".equals(params.get("KS_JSSJ"))) {
			param += " and XKSJ<=to_date('" + params.get("KS_JSSJ").toString()
					+ "','yyyy-mm-dd hh24:mi:ss')+1";
			params2.add(param);
		}
		// 增加学生的学号
		if (null != params.get("xsxh") && !"".equals(params.get("xsxh"))) {
			param += " and xsxh like '%" + params.get("xsxh").toString() + "%'";
			params2.add(param);
		}
		// 增加学生姓名
		if (null != params.get("xsxm") && !"".equals(params.get("xsxm"))) {
			param += " and xsxm like '%" + params.get("xsxm").toString() + "%'";
			params2.add(param);
		}
		// 增加出勤状态
		if (null != params.get("cqzt") && !"".equals(params.get("cqzt"))) {
			param += " and cqqk like '%" + params.get("cqzt").toString() + "%'";
			params2.add(param);
		}

		// 添加年级名称
		if (null != params.get("njmc") && !"".equals(params.get("njmc"))) {
			param += " and nj_id='" + params.get("njmc").toString() + "'";
			params2.add(param);
		}
		// 添加班级名称
		if (null != params.get("bjmc") && !"".equals(params.get("bjmc"))) {
			param += " and bj_id=" + params.get("bjmc").toString() + "'";
			params2.add(param);
		}
		// 添加专业名称
		if (null != params.get("zymc") && !"".equals(params.get("zymc"))) {
			param += " and zy_id=" + params.get("zymc").toString() + "'";
			params2.add(param);
		}
		// 查询结果集
		List list = baseDao.findByPage(hql, KQXX_XSCQ.class, start, number,
				params2, order, sort);
		return list;
	}

	/**
	 * 获取公共课的授课名称
	 */
	@Override
	public List findSKMC() {
		return baseDao
				.executeSQL("select distinct kcxxmc,kcxx_id from kechengb where kcblb='公共选修课'");
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List findKSMC() {
		return baseDao
				.executeSQL("select distinct ksmc,ks_id from kechengb where kcblb='公共选修课'");
	}
}
