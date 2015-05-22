package com.hrbsys.realtime.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.XueShengKQ;
import com.hrbsys.realtime.service.RealTimeService;
import com.hrbsys.tools.TeamPrint;
import com.hrbsys.tools.UUIDTools;

public class RealTimeServiceImpl implements RealTimeService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 统计学院
	public HashMap<String, Integer> getKQQK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi) {
		// 调专业
		return getKQQK(jiaoshi, laoshi, keshi, kecheng, riqi, null, null, null,
				null);
	}

	// 统计专业考勤情况
	public HashMap<String, Integer> getKQQK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi, String andparam,
			String nianjiparam, String banjiparam, String zhuanyeparam) {
		// 调底层
		return getKQQK(jiaoshi, laoshi, keshi, kecheng, riqi, andparam,
				nianjiparam, banjiparam, zhuanyeparam, null);
	}

	// 统计班级的出勤情况
	public HashMap<String, Integer> getKQQK_XUESHENG_TONGJI(String jiaoshi,
			String laoshi, String keshi, String kecheng, String riqi,
			String andparam, String nianjiparam, String banjiparam, String xsid) {
		return getKQQK(jiaoshi, laoshi, keshi, kecheng, riqi, andparam,
				nianjiparam, banjiparam, null, xsid);
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
			String keshi, String kecheng, String riqi, String andparam,
			String nianjiparam, String banjiparam, String zhuanyeparam,
			String xsid) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new Date()); // 当前时间转化成字符串
                                    String hql = "select * from kechengb a where 1=1  and kcblb in (N'专业选修课',N'专业必修课',N'公共选修课') and xueqi_id in (select xq_id from xueqi where to_date(ksrq,'yyyy-mm-dd')<=sysdate and to_date(jsrq,'yyyy-mm-dd')>=sysdate) ";
		System.out.println("RealtimeServiceImpl中打印::" + "andparam是：#########3"
				+ andparam);
		if (null != andparam && !"".equals(andparam)) {
			hql += andparam;
			System.out.println("RealtimeServiceImpl中打印::" + hql);
		}
		if (null != riqi && (!"".equals(riqi))) { // 查询课程表的时候加上日期标志
			hql += " and a.xingqixh=(select to_char(to_date('" + riqi.trim()
					+ "','yyyy-mm-dd'),'D') from dual)";
		} else { // 如果参数中没传入日期参数，则将日期设置为当前天
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			riqi = df1.format(new Date());
			hql += " and a.xingqixh=(select to_char(to_date('" + riqi.trim()
					+ "','yyyy-mm-dd'),'D') from dual)";
		}
		// 给拼凑的sql加：年级、班级begin#######################################################################begin
		hql += "and a.kcb_id in (select kcb_id from skxsxx where xs_id in(select x.xs_id from xsxx x where 1=1";
		if (null != nianjiparam && !"".equals(nianjiparam)) {
			hql += " and x.nj_id='" + nianjiparam + "'";
			System.out.println("RealtimeServiceImpl中打印::" + "传递进来的：NJ_ID是：：：：：："+ nianjiparam + "    :" + " and x.nj_id='" + nianjiparam + "'");
		}else{
			System.out.println("nianjiparam为空是：：："+nianjiparam);
		}

		if (null != banjiparam && !"".equals(banjiparam)) {
			hql += "and x.bj_id='" + banjiparam + "'";
			System.out.println("RealtimeServiceImpl中打印::" + "传递进来的：BJ_ID是：：：：：："+ banjiparam + "    :" + "and x.bj_id='" + banjiparam + "'");	
		}else{
			System.out.println("banjiparam为空是：：："+banjiparam);
		}
		
		hql += "))";
		// 给拼凑的sql加：年级、班级end#######################################################################end
		if (null != keshi && (!"".equals(keshi)) && (!"".equals(keshi.trim()))) { // 加课时
			hql += " and a.ks_id='" + keshi.trim() + "'";
		}
		if (null != jiaoshi && (!"".equals(jiaoshi))
				&& (!"".equals(jiaoshi.trim()))) { // 加教室
			hql += " and a.js_id='" + jiaoshi.trim() + "'";
		}
		if (null != laoshi && (!"".equals(laoshi))
				&& (!"".equals(laoshi.trim()))) {// 加老师
			hql += " and a.laoshi_id='" + laoshi.trim() + "'";
		}
		if (null != kecheng && (!"".equals(kecheng))) {// 加课程
			if (!"".equals(kecheng.trim())) {
				hql += " and a.kcb_id='" + kecheng.trim() + "'";
			}
		}
		System.out.println("RealtimeServiceImpl中打印::" + "查询sql语句时：" + hql);
		// 执行HQL语句，查出要统计的所有考勤信息集合
		List all_kcb = baseDao.executeSQL(hql);
		int sum_yingchuxi = 0;// 应出席
		int sum_quexi = 0;// 缺席
		int sum_zhengchang = 0;// 正常
		int sum_chidao = 0;// 迟到
		int sum_zaotui = 0;// 早退

		for (int kcb_i = 0; kcb_i < all_kcb.size(); kcb_i++) { // 循环课程列表，计算考勤信息
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
			System.out.println("RealtimeServiceImpl中打印::" + "当前课程表为：" + kcxxmc
					+ "---------->" + laoshimc + "---------->" + laoshi_id);
			System.out.println("输出的当前时间是: " + nowTime);
			String riqiTime_begin = riqi.trim() + " " + ks_kssj.trim() + ":00";
			String riqiTime_end = riqi.trim() + " " + ks_jssj.trim() + ":00";
			System.out.println("RealTimeServiceImpl中打印::" + "输出的传入日期时间是: "
					+ riqiTime_begin);
			System.out.println("RealTimeServiceImpl中打印::" + "当前时间<上课时间 比较结果是: "
					+ !compare_date(nowTime, riqiTime_begin));
			System.out.println("RealTimeServiceImpl中打印::"
					+ "如果上课时间<当前时<下课时间 比较结果是: "
					+ (compare_date(nowTime, riqiTime_begin) && !compare_date(
							nowTime, riqiTime_end)));
			System.out.println("RealTimeServiceImpl中打印::"
					+ "当前时间>下课时间：正常计算出勤情况 比较结果是: "
					+ compare_date(nowTime, riqiTime_end));
			// 如果当前时间<上课时间：出勤情况计算为全部出勤
			if (!compare_date(nowTime, riqiTime_begin)) {
				sum_yingchuxi += 0;
				sum_quexi += 0;
				sum_zhengchang += 0;
				sum_chidao += 0;
				sum_zaotui += 0;
				System.out.println("RealTimeServiceImpl中打印::"
						+ "当前时间<开始时间，不计算考勤#############");
			}
			/*
			 * //如果上课时间<当前时<下课时间 ：不计算早退 //如果上课时间<当前时<下课时间 ：缺席：无打卡记录的
			 * //如果上课时间<当前时<下课时间 ：迟到：上课时间段无记录的 //如果上课时间<当前时<下课时间 ：正常：上课时间有打卡记录的
			 */
			if (compare_date(nowTime, riqiTime_begin)
					&& !compare_date(nowTime, riqiTime_end)) {
				System.out
						.println("RealTimeServiceImpl中打印::"
								+ "如果上课时间<当前时<下课时间============================================================================================================begin");
				String ycxsql = "select distinct xs_id,t.xsxm,t.xuehao from skxsxx t where t.kcb_id='"
						+ kcb_id
						+ "' and t.xs_id in (select x.xs_id from xsxx x where 1=1";
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					ycxsql += " and x.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					ycxsql += " and x.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					ycxsql += " and x.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					ycxsql += " and x.xs_id='" + xsid + "'";
				}
				ycxsql += ")";
				// 增加班级年级统计end######################################################################################

				System.out.println("RealTimeServiceImpl中打印::" + "应出席人数sql："
						+ ycxsql);
				List all_ying = baseDao.executeSQL(ycxsql);
				// list_count_yingchuxi.add(all_ying.size());//应出席人数
				sum_yingchuxi += all_ying.size();

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
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					queqinsql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					queqinsql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					queqinsql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					queqinsql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################

				System.out.println("RealTimeServiceImpl中打印::" + "缺席人数sql："
						+ queqinsql);
				List all_queqin = baseDao.executeSQL(queqinsql);
				// list_count_quexi.add(all_queqin.size()); //缺勤人数
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
						+ ks_id + "')) ";
				// +
				// "and k.xh in ( select z.xuehao from kqxx_day z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char(to_date('"+riqiTime_begin+"','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char(to_date('"+riqiTime_begin+"','yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))";
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					zhengchangsql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					zhengchangsql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					zhengchangsql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					zhengchangsql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################

				System.out.println("RealTimeServiceImpl中打印::" + "正常人数SQL："
						+ zhengchangsql);
				List all_kaoqin_zhengchang = baseDao.executeSQL(zhengchangsql);
				// list_count_zhengchang.add(all_kaoqin_zhengchang.size());//正常人数
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
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					chidaosql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					chidaosql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					chidaosql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					chidaosql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################
				System.out.println("RealTimeServiceImpl中打印::" + "迟到人数SQL："
						+ chidaosql);
				List all_kaoqin_chidao = baseDao.executeSQL(chidaosql);
				int czcount = all_ying.size() - all_queqin.size()
						- all_kaoqin_zhengchang.size()
						- all_kaoqin_chidao.size();// 既迟到又早退：应出席-缺勤-正常-迟到-早退
				// list_count_chidao.add(all_kaoqin_chidao.size()+czcount);
				// //迟到人数
				sum_chidao += all_kaoqin_chidao.size() + czcount;
				// list_count_zaotui.add(0);//早退人数
				sum_zaotui += 0;
				System.out
						.println("RealTimeServiceImpl中打印::"
								+ "如果上课时间<当前时<下课时间============================================================================================================end");
			}
			// 当前时间>下课时间：正常计算出勤情况
			if (compare_date(nowTime, riqiTime_end)) {
				System.out
						.println("RealTimeServiceImpl中打印::"
								+ "当前时间>下课时间============================================================================================================begin");
				String ycxsql = "select distinct xs_id,t.xsxm,t.xuehao from skxsxx t where t.kcb_id='"
						+ kcb_id
						+ "' and t.xs_id in (select x.xs_id from xsxx x where 1=1";
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					ycxsql += " and x.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					ycxsql += " and x.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					ycxsql += " and x.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					ycxsql += " and x.xs_id='" + xsid + "'";
				}
				ycxsql += ")";
				System.out.println("RealTimeServiceImpl中打印::" + "应出席人数sql："
						+ ycxsql);
				// 增加班级年级统计end######################################################################################
				List all_ying = baseDao.executeSQL(ycxsql);
				sum_yingchuxi += all_ying.size();
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
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					queqinsql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					queqinsql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					queqinsql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					queqinsql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################

				System.out.println("RealTimeServiceImpl中打印::" + "缺席人数sql："
						+ queqinsql);
				List all_queqin = baseDao.executeSQL(queqinsql);
				// list_count_quexi.add(all_queqin.size()); //缺勤人数
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
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					zhengchangsql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					zhengchangsql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					zhengchangsql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					zhengchangsql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################

				List all_kaoqin_zhengchang = baseDao.executeSQL(zhengchangsql);
				// list_count_zhengchang.add(all_kaoqin_zhengchang.size());//正常人数
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
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					chidaosql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					chidaosql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					chidaosql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					chidaosql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################

				System.out.println("RealTimeServiceImpl中打印::" + "迟到SQL："
						+ chidaosql);
				List all_kaoqin_chidao = baseDao.executeSQL(chidaosql);

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
				// 增加年级、班级统计begin##################################################################################
				if (null != nianjiparam && !"".equals(nianjiparam)) {
					zaotuisql += " and k.nj_id='" + nianjiparam + "'";
				}
				if (null != banjiparam && !"".equals(banjiparam)) {
					zaotuisql += " and k.bj_id='" + banjiparam + "'";
				}
				if (null != zhuanyeparam && !"".equals(zhuanyeparam)) {
					zaotuisql += " and k.zy_id='" + zhuanyeparam + "'";
				}
				if (null != xsid && !"".equals(xsid)) {
					zaotuisql += " and k.xs_id='" + xsid + "'";
				}
				// 增加班级年级统计end######################################################################################
				System.out.println("RealTimeServiceImpl中打印::" + "早退人数SQL："
						+ zaotuisql);
				List all_kaoqin_zaotui = baseDao.executeSQL(zaotuisql); // 列出出勤情况正常的考勤信息

				int czcount = all_ying.size() - all_queqin.size()
						- all_kaoqin_zhengchang.size()
						- all_kaoqin_chidao.size() - all_kaoqin_zaotui.size();// 既迟到又早退：应出席-缺勤-正常-迟到-早退
				// list_count_chidao.add(all_kaoqin_chidao.size()+czcount);
				// //迟到人数=迟到人数+既迟到又早退的人数
				sum_chidao += all_kaoqin_chidao.size() + czcount;
				// list_count_zaotui.add(all_kaoqin_zaotui.size()+czcount);//早退人数=早退人数+既迟到又早退人叔
				sum_zaotui += all_kaoqin_zaotui.size() + czcount;
			}
			System.out
					.println("RealTimeServiceImpl中打印::"
							+ "当前时间>下课时间============================================================================================================end");
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("yingchuqin", sum_yingchuxi);
		map.put("queqin", sum_quexi);
		map.put("zhengchang", sum_zhengchang);
		map.put("chidao", sum_chidao);
		map.put("zaotui", sum_zaotui);
		return map;
	}

	// 分学生计算考勤信息
	@Override
	public ArrayList<XueShengKQ> getKQQK_XS(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi, String xs_id,
			String cqqk, String xueshengxh) {
		try {
			ArrayList<XueShengKQ> all = new ArrayList<XueShengKQ>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(new Date()); // 当前时间转化成字符串
			String hql = "select * from kechengb a where 1=1  and kcblb in ('专业选修课','专业必修课','公共选修课') and xueqi_id in (select xq_id from xueqi where to_date(ksrq,'yyyy-mm-dd')<=sysdate and to_date(jsrq,'yyyy-mm-dd')>=sysdate)  ";
			if (null != riqi && (!"".equals(riqi))) { // 查询课程表的时候加上日期标志
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			} else { // 如果参数中没传入日期参数，则将日期设置为当前天
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				riqi = df1.format(new Date());
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			}
			if (null != keshi && (!"".equals(keshi))
					&& (!"".equals(keshi.trim()))) { // 加课时
				hql += " and a.ks_id='" + keshi.trim() + "'";
			}
			if (null != jiaoshi && (!"".equals(jiaoshi))
					&& (!"".equals(jiaoshi.trim()))) { // 加教室
				hql += " and a.js_id='" + jiaoshi.trim() + "'";
			}
			if (null != laoshi && (!"".equals(laoshi))
					&& (!"".equals(laoshi.trim()))) { // 加老师
				hql += " and a.laoshi_id='" + laoshi.trim() + "'";
			}
			if (null != kecheng && (!"".equals(kecheng))
					&& (!"".equals(kecheng.trim()))) { // 加课程
				hql += " and a.kcb_id='" + kecheng.trim() + "'";
			}
			if (null != xs_id && (!"".equals(xs_id)) && (!"".equals(xs_id))) { // 加课程
				hql += " and a.kcb_id in (select kcb_id from skxsxx where xs_id='"
						+ xs_id + "')";
			}
			// 执行HQL语句，查出要统计的所有考勤信息集合
			System.out.println("RealTimeServiceImpl中打印::" + "打出来的sql是：" + hql);
			List all_kcb = baseDao.executeSQL(hql);
			for (int kcb_i = 0; kcb_i < all_kcb.size(); kcb_i++) {// 循环课程列表，计算考勤信息
				Object[] tmpObjArray = (Object[]) all_kcb.get(kcb_i);
				String kcb_id = (null == tmpObjArray[0]) ? "" : tmpObjArray[0]
						.toString();
				String kcb_fks_id = (null == tmpObjArray[1]) ? ""
						: tmpObjArray[1].toString();
				String kcxx_id = (null == tmpObjArray[2]) ? "" : tmpObjArray[2]
						.toString();
				String kcxxmc = (null == tmpObjArray[3]) ? "" : tmpObjArray[3]
						.toString();
				String laoshi_id = (null == tmpObjArray[4]) ? ""
						: tmpObjArray[4].toString();
				String laoshimc = (null == tmpObjArray[5]) ? ""
						: tmpObjArray[5].toString();
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
				String xingqi = (null == tmpObjArray[11]) ? ""
						: tmpObjArray[11].toString();
				String kszhou = (null == tmpObjArray[12]) ? ""
						: tmpObjArray[12].toString();
				String jszhou = (null == tmpObjArray[13]) ? ""
						: tmpObjArray[13].toString();
				String xingqixh = (null == tmpObjArray[22]) ? ""
						: tmpObjArray[22].toString();
				String ks_kssj = (null == tmpObjArray[23]) ? ""
						: tmpObjArray[23].toString();
				String ks_jssj = (null == tmpObjArray[24]) ? ""
						: tmpObjArray[24].toString();
				String laoshi_gh = (null == tmpObjArray[25]) ? ""
						: tmpObjArray[25].toString();
				String riqiTime_begin = riqi.trim() + " " + ks_kssj.trim()
						+ ":00";
				String riqiTime_end = riqi.trim() + " " + ks_jssj.trim()
						+ ":00";

				// ////////////////////////////////////////////////////////////////////////////////////////////////////////begin
				/**
				 * 1、如果传入的学号为null的话，去这么做 1、查skxsxx表中对应课程表的学生： 2、循环学号获取应出席学生的信息
				 * */
				if (null != xueshengxh && !"".equals(xueshengxh)) { // //////////////////////////////如果学号不为空，则是计算某个学生的迟到信息
					// 调用学生的考勤信息方法，计算学生出勤
					String kqjg = getXueShengKQ(xueshengxh, kcb_id, keshi,
							nowTime, riqiTime_begin, riqiTime_end);
					String tmp = "";
					XueShengKQ xk = new XueShengKQ();
					xk.setKcbid(kcb_id);
					xk.setKcxxmc(kcxxmc);
					// 设置课程相关信息：
					xk.setJs_id(js_id);
					xk.setJsmc(jsmc);
					xk.setLs_id(laoshi_id);
					xk.setLsmc(laoshimc);
					xk.setLsgh(laoshi_gh);
					xk.setKcb_id(kcb_id);
					xk.setKcb_kcxxmc(kcxxmc);
					xk.setXuehao(xueshengxh);
					xk.setKs_id(ks_id);
					xk.setKsmc(ksmc);

					if ("a".equals(kqjg)) {
						tmp = "正常";
					}
					if ("b".equals(kqjg)) {
						tmp = "迟到";
					}
					if ("c".equals(kqjg)) {
						tmp = "早退";
					}
					if ("d".equals(kqjg)) {
						tmp = "既迟到又早退";
					}
					if ("e".equals(kqjg)) {
						tmp = "缺席";
					}
					if ("x".equals(kqjg)) {
						tmp = "该学生不需要上此课程";
					}
					xk.setCqqk(tmp);
					// 判断出勤情况
					if (null != cqqk && !"".equals(cqqk)) {
						if (cqqk.equals(xk.getCqqk())) { // 如果两个相同才显示
							all.add(xk);
						} else if ("迟到".equals(cqqk)
								&& "既迟到又早退".equals(xk.getCqqk())) {
							all.add(xk);
						} else if ("早退".equals(cqqk)
								&& "既迟到又早退".equals(xk.getCqqk())) {
							all.add(xk);
						}
					} else {
						all.add(xk);
					}
				} else {// ////////////////////////////////////////////////////////////////////否则计算所有学生的考勤信息
					// String
					// sql_skxsxx="select kcb_id,kcb_fxs_id,kcb_kcxxmc,xs_id,xsxm,xuehao from skxsxx t where t.kcb_fxs_id='"+kcb_fks_id+"'";
					String sql_skxsxx = "select kcb_id,kcb_fxs_id,kcb_kcxxmc,t.xs_id,t.xsxm,t.xuehao,a.xy_id,a.xymc,a.zy_id,a.zymc,a.nj_id,a.njmc,a.bj_id,a.bjmc from skxsxx t,xsxx a where t.xuehao=a.xh and t.kcb_fxs_id='"
							+ kcb_fks_id + "'";
					System.out.println("RealTimeServiceImpl中打印::" + "打出来的sql是："
							+ sql_skxsxx);
					List all_skxs = baseDao.executeSQL(sql_skxsxx);
					for (int sk_i = 0; sk_i < all_skxs.size(); sk_i++) {// 循环课程列表，计算考勤信息
						Object[] tmpSkxsArray = (Object[]) all_skxs.get(sk_i);
						String sk_kcb_id = (null == tmpSkxsArray[0]) ? ""
								: tmpSkxsArray[0].toString();
						String sk_kcb_fxs_id = (null == tmpSkxsArray[1]) ? ""
								: tmpSkxsArray[1].toString();
						String sk_kcb_kcxxmc = (null == tmpSkxsArray[2]) ? ""
								: tmpSkxsArray[2].toString();
						String sk_xs_id = (null == tmpSkxsArray[3]) ? ""
								: tmpSkxsArray[3].toString();
						String sk_xsxm = (null == tmpSkxsArray[4]) ? ""
								: tmpSkxsArray[4].toString();
						String sk_xuehao = (null == tmpSkxsArray[5]) ? ""
								: tmpSkxsArray[5].toString();
						String sk_xy_id = (null == tmpSkxsArray[6]) ? ""
								: tmpSkxsArray[6].toString();
						String sk_xymc = (null == tmpSkxsArray[7]) ? ""
								: tmpSkxsArray[7].toString();
						String sk_zy_id = (null == tmpSkxsArray[8]) ? ""
								: tmpSkxsArray[8].toString();
						String sk_zymc = (null == tmpSkxsArray[9]) ? ""
								: tmpSkxsArray[9].toString();
						String sk_nj_id = (null == tmpSkxsArray[10]) ? ""
								: tmpSkxsArray[10].toString();
						String sk_njmc = (null == tmpSkxsArray[11]) ? ""
								: tmpSkxsArray[11].toString();
						String sk_bj_id = (null == tmpSkxsArray[12]) ? ""
								: tmpSkxsArray[12].toString();
						String sk_bjmc = (null == tmpSkxsArray[13]) ? ""
								: tmpSkxsArray[13].toString();

						// System.out.println("RealTimeServiceImpl中打印::"+"打出来的sql是：KCB_ID::"+sk_kcb_id);
						// System.out.println("RealTimeServiceImpl中打印::"+"打出来的sql是：KCB_fxs_id::"+sk_kcb_fxs_id);
						// System.out.println("RealTimeServiceImpl中打印::"+"打出来的sql是：KCB_kcxxmc::"+sk_kcb_kcxxmc);
						// System.out.println("RealTimeServiceImpl中打印::"+"打出来的sql是：XS_ID::"+sk_xs_id);
						// System.out.println("RealTimeServiceImpl中打印::"+"打出来的sql是：XSXM::"+sk_xsxm);
						// System.out.println("RealTimeServiceImpl中打印::"+"打出来的sql是：XUEHAO::"+sk_xuehao);

						// 调用学生的考勤信息方法，计算学生出勤
						String kqjg = getXueShengKQ(sk_xuehao, kcb_id, keshi,
								nowTime, riqiTime_begin, riqiTime_end);
						String tmp = "";
						XueShengKQ xk = new XueShengKQ();
						xk.setKcbid(kcb_id);
						xk.setKcxxmc(kcxxmc);
						// 设置课程相关信息：
						xk.setJs_id(js_id);
						xk.setJsmc(jsmc);
						xk.setLs_id(laoshi_id);
						xk.setLsmc(laoshimc);
						xk.setLsgh(laoshi_gh);
						xk.setKcb_id(kcb_id);
						xk.setKcb_kcxxmc(kcxxmc);
						xk.setXuehao(sk_xuehao);
						xk.setXsxm(sk_xsxm);
						xk.setXs_id(sk_xs_id);
						xk.setXy_id(sk_xy_id);
						xk.setXymc(sk_xymc);
						xk.setZy_id(sk_zy_id);
						xk.setZymc(sk_zymc);
						xk.setNj_id(sk_nj_id);
						xk.setNjmc(sk_njmc);
						xk.setBj_id(sk_bj_id);
						xk.setBjmc(sk_bjmc);

						xk.setLs_id(laoshi_id);
						xk.setLsmc(laoshimc);
						xk.setLsgh(laoshi_gh);

						xk.setJs_id(js_id);
						xk.setJsmc(jsmc);

						xk.setKs_id(ks_id);
						xk.setKsmc(ksmc);
						TeamPrint
								.println(
										RealTimeServiceImpl.class,
										"getKQQK_XS",
										"SQL语句值",
										"beginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbeginbegin");
						TeamPrint.println(RealTimeServiceImpl.class,
								"getKQQK_XS", "SQL语句值", xk.getJsmc());
						TeamPrint.println(RealTimeServiceImpl.class,
								"getKQQK_XS", "SQL语句值", xk.getLsmc());
						TeamPrint.println(RealTimeServiceImpl.class,
								"getKQQK_XS", "SQL语句值", xk.getKs_id());
						TeamPrint.println(RealTimeServiceImpl.class,
								"getKQQK_XS", "SQL语句值", xk.getKsmc());
						TeamPrint
								.println(
										RealTimeServiceImpl.class,
										"getKQQK_XS",
										"SQL语句值",
										"endendendendendendendendendendendendendendendendendendendendendendendendendendendendendendendendendendendend");

						if ("a".equals(kqjg)) {
							tmp = "正常";
						}
						if ("b".equals(kqjg)) {
							tmp = "迟到";
						}
						if ("c".equals(kqjg)) {
							tmp = "早退";
						}
						if ("d".equals(kqjg)) {
							tmp = "既迟到又早退";
						}
						if ("e".equals(kqjg)) {
							tmp = "缺席";
						}
						if ("x".equals(kqjg)) {
							tmp = "该学生不需要上此课程";
						}
						xk.setCqqk(tmp);
						// 判断出勤情况
						if (null != cqqk && !"".equals(cqqk)) {
							if (cqqk.equals(xk.getCqqk())) { // 如果两个相同才显示
								all.add(xk);
							} else if ("迟到".equals(cqqk)
									&& "既迟到又早退".equals(xk.getCqqk())) {
								all.add(xk);
							} else if ("早退".equals(cqqk)
									&& "既迟到又早退".equals(xk.getCqqk())) {
								all.add(xk);
							}
						} else {
							all.add(xk);
						}
					}
					// ///////////////////////////////////////////////////////////////////////////////////////////////////////end
					System.out.println("RealTimeServiceImpl中打印::" + all.size());
				}
			}
			return all;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 分学生计算考勤信息
	@Override
	public ArrayList<XueShengKQ> getKQQK_XSbyJGorJS(String jiaoshi,
			String laoshi, String keshi, String kecheng, String riqi) {
		try {
			ArrayList<XueShengKQ> all = new ArrayList<XueShengKQ>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(new Date()); // 当前时间转化成字符串
			String hql = "select * from kechengb a where 1=1 and kcblb in ('专业选修课','专业必修课','公共选修课') and xueqi_id in (select xq_id from xueqi where to_date(ksrq,'yyyy-mm-dd')<=sysdate and to_date(jsrq,'yyyy-mm-dd')>=sysdate) ";
			if (null != riqi && (!"".equals(riqi))) { // 查询课程表的时候加上日期标志
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			} else { // 如果参数中没传入日期参数，则将日期设置为当前天
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				riqi = df1.format(new Date());
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			}
			if (null != keshi && (!"".equals(keshi))
					&& (!"".equals(keshi.trim()))) { // 加课时
				hql += " and a.ks_id='" + keshi.trim() + "'";
			}
			if (null != jiaoshi && (!"".equals(jiaoshi))
					&& (!"".equals(jiaoshi.trim()))) { // 加教室
				hql += " and a.js_id='" + jiaoshi.trim() + "'";
			}
			if (null != laoshi && (!"".equals(laoshi))
					&& (!"".equals(laoshi.trim()))) { // 加老师
				hql += " and a.laoshi_id='" + laoshi.trim() + "'";
			}
			if (null != kecheng && (!"".equals(kecheng))
					&& (!"".equals(kecheng.trim()))) { // 加课程
				hql += " and a.kcb_id='" + kecheng.trim() + "'";
			}

			// 执行HQL语句，查出要统计的所有考勤信息集合
			System.out.println("RealTimeServiceImpl中打印::" + "打出来的sql是：" + hql);
			List all_kcb = baseDao.executeSQL(hql);
			for (int kcb_i = 0; kcb_i < all_kcb.size(); kcb_i++) {// 循环课程列表，计算考勤信息
				Object[] tmpObjArray = (Object[]) all_kcb.get(kcb_i);
				String kcb_id = (null == tmpObjArray[0]) ? "" : tmpObjArray[0]
						.toString();
				String kcb_fks_id = (null == tmpObjArray[1]) ? ""
						: tmpObjArray[1].toString();
				String kcxx_id = (null == tmpObjArray[2]) ? "" : tmpObjArray[2]
						.toString();
				String kcxxmc = (null == tmpObjArray[3]) ? "" : tmpObjArray[3]
						.toString();
				String laoshi_id = (null == tmpObjArray[4]) ? ""
						: tmpObjArray[4].toString();
				String laoshimc = (null == tmpObjArray[5]) ? ""
						: tmpObjArray[5].toString();
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
				String xingqi = (null == tmpObjArray[11]) ? ""
						: tmpObjArray[11].toString();
				String kszhou = (null == tmpObjArray[12]) ? ""
						: tmpObjArray[12].toString();
				String jszhou = (null == tmpObjArray[13]) ? ""
						: tmpObjArray[13].toString();
				String xingqixh = (null == tmpObjArray[22]) ? ""
						: tmpObjArray[22].toString();
				String ks_kssj = (null == tmpObjArray[23]) ? ""
						: tmpObjArray[23].toString();
				String ks_jssj = (null == tmpObjArray[24]) ? ""
						: tmpObjArray[24].toString();
				String laoshi_gh = (null == tmpObjArray[25]) ? ""
						: tmpObjArray[25].toString();
				String riqiTime_begin = riqi.trim() + " " + ks_kssj.trim()
						+ ":00";
				String riqiTime_end = riqi.trim() + " " + ks_jssj.trim()
						+ ":00";

				List all_xs = baseDao
						.executeSQL("select distinct a.xs_id,a.xsxm,a.xuehao,b.xy_id,b.xymc,b.zy_id_lq,b.zymc,b.nj_id,b.njmc,b.bj_id,b.bjmc from skxsxx a,xsxx b where a.xs_id=b.xs_id and kcb_id='"
								+ kcb_id + "'");
				for (int xsi = 0; xsi < all_xs.size(); xsi++) {
					// ///////////////////////////////////////////////////////////////////////////////////////////////////////////循环学生
					// begin
					String xs_id = (null == tmpObjArray[0]) ? ""
							: tmpObjArray[0].toString();
					String xsxm = (null == tmpObjArray[1]) ? ""
							: tmpObjArray[0].toString();
					String xueshengxh = (null == tmpObjArray[2]) ? ""
							: tmpObjArray[0].toString();
					String xy_id = (null == tmpObjArray[3]) ? ""
							: tmpObjArray[0].toString();
					String xymc = (null == tmpObjArray[4]) ? ""
							: tmpObjArray[0].toString();
					String zy_id = (null == tmpObjArray[5]) ? ""
							: tmpObjArray[0].toString();
					String zymc = (null == tmpObjArray[6]) ? ""
							: tmpObjArray[0].toString();
					String nj_id = (null == tmpObjArray[7]) ? ""
							: tmpObjArray[0].toString();
					String njmc = (null == tmpObjArray[8]) ? ""
							: tmpObjArray[0].toString();
					String bj_id = (null == tmpObjArray[9]) ? ""
							: tmpObjArray[0].toString();
					String bjmc = (null == tmpObjArray[10]) ? ""
							: tmpObjArray[0].toString();

					// 调用学生的考勤信息方法，计算学生出勤
					String kqjg = getXueShengKQ(xueshengxh, kcb_id, keshi,
							nowTime, riqiTime_begin, riqiTime_end);
					String tmp = "";
					XueShengKQ xk = new XueShengKQ();
					xk.setKcbid(kcb_id);
					xk.setKcxxmc(kcxxmc);
					// 设置课程相关信息：
					xk.setJs_id(js_id);
					xk.setJsmc(jsmc);

					xk.setKs_id(ks_id);
					xk.setKsmc(ksmc);

					xk.setLs_id(laoshi_id);
					xk.setLsmc(laoshimc);
					xk.setLsgh(laoshi_gh);

					xk.setKcb_id(kcb_id);
					xk.setKcb_kcxxmc(kcxxmc);

					xk.setXy_id(xy_id);
					xk.setXymc(xymc);
					xk.setZy_id(zy_id);
					xk.setZymc(zymc);
					xk.setNj_id(nj_id);
					xk.setNjmc(njmc);
					xk.setBj_id(bj_id);
					xk.setBjmc(bjmc);

					xk.setXuehao(xueshengxh);
					if ("a".equals(kqjg)) {
						tmp = "正常";
					}
					if ("b".equals(kqjg)) {
						tmp = "迟到";
					}
					if ("c".equals(kqjg)) {
						tmp = "早退";
					}
					if ("d".equals(kqjg)) {
						tmp = "既迟到又早退";
					}
					if ("e".equals(kqjg)) {
						tmp = "缺席";
					}
					if ("x".equals(kqjg)) {
						tmp = "该学生不需要上此课程";
					}
					xk.setCqqk(tmp);
					all.add(xk);
					// ///////////////////////////////////////////////////////////////////////////////////////////////////////////循环学生
					// end
				}
				System.out.println("RealTimeServiceImpl中打印::" + all.size());
			}
			return all;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 根据学号、课程表ID、日期、课时ID、当前时间、课时开始时间起始值、课时开始时间终止值、课时结束时间起始值、课时结束时间终止值，查相应的学生出勤情况
	// a：正常出席、b：迟到、c：早退 、d：既迟到又早退、e：缺席、x：本课程，该学生无需上课。
	public String getXueShengKQ(String xuehao, String kcb_id, String keshi,
			String nowTime, String riqiTime_begin, String riqiTime_end) {
		String sql_skxsxx = "select * from skxsxx t where 1=1 "; // 查看是否存在该学生对应的课程，
		if (null != xuehao && !"".equals(xuehao)) {
			sql_skxsxx += " and t.xuehao='" + xuehao + "'";
		}
		if (null != kcb_id && !"".equals(kcb_id)) {
			sql_skxsxx += " and t.kcb_id='" + kcb_id + "'";
		}
		System.out.println("RealTimeServiceImpl中打印::" + "SQL_SKXSXX::::"
				+ sql_skxsxx);
		List issk_list = baseDao.executeSQL(sql_skxsxx);
		if (issk_list.size() == 0) {
			return "x";
		}
		// 如果当前时间<上课时间：出勤情况计算为全部出勤
		if (!compare_date(nowTime, riqiTime_begin)) {
			return "a"; // 正常出席
		}
		String sql_kaishi = " select * from kqxx_day t where t.xuehao='"
				+ xuehao + "' and t.djsj>=to_date('" + riqiTime_begin
				+ "','yyyy-mm-dd hh24:mi:ss')-10/(24*60) and t.djsj<=to_date('"
				+ riqiTime_begin + "','yyyy-mm-dd hh24:mi:ss')+10/(24*60)";
		System.out.println("RealTimeServiceImpl中打印::" + "SQL_KAISHI::::"
				+ sql_kaishi);
		List kaishi_list = baseDao.executeSQL(sql_kaishi);
		String sql_kejian = " select * from kqxx_day t where t.xuehao='"
				+ xuehao + "' and t.djsj>to_date('" + riqiTime_begin
				+ "','yyyy-mm-dd hh24:mi:ss')+10/(24*60) and t.djsj<to_date('"
				+ riqiTime_end + "','yyyy-mm-dd hh24:mi:ss')-10/(24*60)";
		List kejian_list = baseDao.executeSQL(sql_kejian);
		System.out.println("RealTimeServiceImpl中打印::" + "SQL_KEJIAN::::"
				+ sql_kejian);
		// 如果上课时间<当前时<下课时间 ：不计算早退
		if (compare_date(nowTime, riqiTime_begin)
				&& !compare_date(nowTime, riqiTime_end)) {
			if (kaishi_list.size() > 0) { // 如果学生上课打卡了，则正常出席
				return "a";
			}
			if (kaishi_list.size() == 0 && kejian_list.size() > 0) {// 如果学生上课没打卡，上课进行中打卡了，证明迟到。
				return "b";
			}
			// 如果上课时候没打卡、上课期间也没打卡，则缺席。
			return "d";
		}
		String sql_jieshu = "select * from kqxx_day t where t.xuehao='"
				+ xuehao + "' and t.djsj>=to_date('" + riqiTime_end
				+ "','yyyy-mm-dd hh24:mi:ss')-10/(24*60) and t.djsj<=to_date('"
				+ riqiTime_end + "','yyyy-mm-dd hh24:mi:ss')+10/(24*60)";
		List jieshu_list = baseDao.executeSQL(sql_jieshu);
		System.out.println("RealTimeServiceImpl中打印::" + "SQL_JIESHU::::"
				+ sql_jieshu);

		// 当前时间>下课时间：正常计算出勤情况
		if (compare_date(nowTime, riqiTime_end)) {
			if (kaishi_list.size() > 0 && jieshu_list.size() > 0) { // 如果学生上课打卡了，下课期间也打卡了，则正常出席
				return "a";
			}
			if (kaishi_list.size() > 0 && jieshu_list.size() == 0) { // 如果学生上课打卡了，下课期间没打卡了，则早退
				return "c";
			}
			if (kaishi_list.size() == 0 && jieshu_list.size() > 0) { // 如果学生上课没打卡，下课期间打卡了，则迟到
				return "b";
			}
			if (kaishi_list.size() == 0 && jieshu_list.size() == 0
					&& kejian_list.size() > 0) { // 如果学生上课、下课都没打卡，则既迟到又早退
				return "d";
			}
			// 不是以上类型，则计算为缺席。
			return "e";
		}
		return null;
	}

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
	 * 开放性课程实时查询计算接口： param: jiaoshi:教室ID param: laoshi:老师ID param: keshi:课时ID
	 * param: kecheng:课程信息ID param: 日期:上课日期：格式为：YYYY-MM-DD ，例如：2014-11-19 param:
	 * jiaoshi:教室ID param: jiaoshi:教室ID
	 * */
	@Override
	public ArrayList<XueShengKQ> getKQQK_KFK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi) {
		
		int sum_zhengchang = 0;// 正常
		int sum_chidao = 0;// 迟到
		int sum_zaotui = 0;// 早退
		int sum_ztAndcd = 0;// 既迟到又早退
		
		try {
			ArrayList<XueShengKQ> all = new ArrayList<XueShengKQ>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(new Date()); // 当前时间转化成字符串
			String hql = "select * from kechengb a where 1=1  and kcblb in ('开放性课程')  and xueqi_id in (select xq_id from xueqi where to_date(ksrq,'yyyy-mm-dd')<=sysdate and to_date(jsrq,'yyyy-mm-dd')>=sysdate) ";
			if (null != riqi && (!"".equals(riqi))) { // 查询课程表的时候加上日期标志
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			} else { // 如果参数中没传入日期参数，则将日期设置为当前天
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				riqi = df1.format(new Date());
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			}
			if (null != keshi && (!"".equals(keshi))
					&& (!"".equals(keshi.trim()))) { // 加课时
				hql += " and a.ks_id='" + keshi.trim() + "'";
			}
			if (null != jiaoshi && (!"".equals(jiaoshi))
					&& (!"".equals(jiaoshi.trim()))) { // 加教室
				hql += " and a.js_id='" + jiaoshi.trim() + "'";
			}
			if (null != laoshi && (!"".equals(laoshi))
					&& (!"".equals(laoshi.trim()))) { // 加老师
				hql += " and a.laoshi_id='" + laoshi.trim() + "'";
			}
			if (null != kecheng && (!"".equals(kecheng))
					&& (!"".equals(kecheng.trim()))) { // 加课程
				hql += " and a.kcb_id='" + kecheng.trim() + "'";
			}
			// if(null!=xs_id&&(!"".equals(xs_id))&&(!"".equals(xs_id))){ //加学生
			// //////////////////////////////////////////////////////////////////////////////////
			// }
			// 执行HQL语句，查出要统计的所有考勤信息集合
			System.out.println("【开放性课程：】RealTimeServiceImpl中打印::" + "打出来的sql是："
					+ hql);
			// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// XueShengKQ xk=new XueShengKQ();
			// xk.setKcbid(kcb_id);
			// xk.setKcxxmc(kcxxmc);
			// //设置课程相关信息：
			// xk.setJs_id(js_id);
			// xk.setJsmc(jsmc);
			// xk.setLs_id(laoshi_id);
			// xk.setLsmc(laoshimc);
			// xk.setLsgh(laoshi_gh);
			// xk.setKcb_id(kcb_id);
			// xk.setKcb_kcxxmc(kcxxmc);
			// xk.setXuehao(xueshengxh);
			// xk.setKs_id(ks_id);
			// xk.setKsmc(ksmc);
			//
			// if("a".equals(kqjg)){
			// tmp="正常";
			// }
			// if("b".equals(kqjg)){
			// tmp="迟到";
			// }
			// if("c".equals(kqjg)){
			// tmp="早退";
			// }
			// if("d".equals(kqjg)){
			// tmp="既迟到又早退";
			// }
			// if("e".equals(kqjg)){
			// tmp="缺席";
			// }
			// if("x".equals(kqjg)){
			// tmp="该学生不需要上此课程";
			// }
			// xk.setCqqk(tmp);
			// //判断出勤情况
			// if(null!=cqqk&&!"".equals(cqqk)){
			// if(cqqk.equals(xk.getCqqk())){ //如果两个相同才显示
			// all.add(xk);
			// }else if("迟到".equals(cqqk)&&"既迟到又早退".equals(xk.getCqqk())){
			// all.add(xk);
			// }else if("早退".equals(cqqk)&&"既迟到又早退".equals(xk.getCqqk())){
			// all.add(xk);
			// }
			// }else{
			// all.add(xk);
			// }
			// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			List all_kcb = baseDao.executeSQL(hql);// 列出当天所有课程
			System.out.println("当前查询课程表sql是：" + hql);
			for (int kcb_i = 0; kcb_i < all_kcb.size(); kcb_i++) { // 循环课程列表，计算考勤信息
				Object[] tmpObjArray = (Object[]) all_kcb.get(kcb_i);
				String kcb_id = (null == tmpObjArray[0]) ? "" : tmpObjArray[0]
						.toString();
				String kcb_fks_id = (null == tmpObjArray[1]) ? ""
						: tmpObjArray[1].toString();
				String kcxx_id = (null == tmpObjArray[2]) ? "" : tmpObjArray[2]
						.toString();
				String kcxxmc = (null == tmpObjArray[3]) ? "" : tmpObjArray[3]
						.toString();
				String laoshi_id = (null == tmpObjArray[4]) ? ""
						: tmpObjArray[4].toString();
				String laoshimc = (null == tmpObjArray[5]) ? ""
						: tmpObjArray[5].toString();
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
				String xingqi = (null == tmpObjArray[11]) ? ""
						: tmpObjArray[11].toString();
				String kszhou = (null == tmpObjArray[12]) ? ""
						: tmpObjArray[12].toString();
				String jszhou = (null == tmpObjArray[13]) ? ""
						: tmpObjArray[13].toString();
				String xingqixh = (null == tmpObjArray[22]) ? ""
						: tmpObjArray[22].toString();
				String ks_kssj = (null == tmpObjArray[23]) ? ""
						: tmpObjArray[23].toString();
				String ks_jssj = (null == tmpObjArray[24]) ? ""
						: tmpObjArray[24].toString();
				String laoshi_gh = (null == tmpObjArray[25]) ? ""
						: tmpObjArray[25].toString();
				String maxrs = (null == tmpObjArray[30]) ? "0"
						: tmpObjArray[30].toString();

				int maxrs_int = Integer.parseInt(maxrs);// 将上课人数转换为整数类型
				
				int flag = 0;
				// 查询出过来上课的学生学号:
				String xueshengsql = "select distinct xuehao,to_char(min(djsj),'yyyy-mm-dd hh24:mi:ss') from kqxx_day"
						+ " where  xuehao not like 'L%' and djsj>=to_date(to_char( to_date('"
						+ riqi
						+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
						+ ks_kssj
						+ "','yyyy-mm-dd hh24:mi:ss')-10/24/60"
						+ "   and djsj<=to_date(to_char( to_date('"
						+ riqi
						+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
						+ ks_jssj
						+ "','yyyy-mm-dd hh24:mi:ss')+10/24/60"
						+ " and shebei_id in(select sbxx_id from sbxx where js_id='"+js_id+"')"
						+ " group by xuehao "
						+ " order by to_char(min(djsj),'yyyy-mm-dd hh24:mi:ss') asc";
				System.out.println("开放性课程:学生的学号SQL语句是：" + xueshengsql);
				List all_kaoqin_xuesheng = baseDao.executeSQL(xueshengsql);
				for (int zhengchang_i = 0; zhengchang_i < all_kaoqin_xuesheng
						.size(); zhengchang_i++) {
					Object[] xsarr = (Object[]) all_kaoqin_xuesheng
							.get(zhengchang_i);
					String xuehao = (null == xsarr[0]) ? "" : xsarr[0]
							.toString();// 获取上课学生的学号

					
					
					String skdjsj = (null == xsarr[1]) ? "" : xsarr[1]
							.toString();// 获取上课学生的上课打卡时间
					if (flag < maxrs_int) { // 如果人数小于最大上课人数,则计算出勤情况，否则跳出循环.
						System.out.println(flag + "：flag小于最大人数：" + maxrs_int);
						// 找下课打卡时间：
						String xiakesql = "select t.xuehao,to_char(djsj,'yyyy-mm-dd hh24:mi:ss') from kqxx t where t.xuehao ='"
								+ xuehao
								+ "'"
								+ " and djsj>=to_date(to_char( to_date('"
								+ riqi
								+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
								+ ks_kssj
								+ "','yyyy-mm-dd hh24:mi:ss')-10/24/60"
								+ " and djsj<=to_date(to_char( to_date('"
								+ riqi
								+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
								+ ks_jssj
								+ "','yyyy-mm-dd hh24:mi:ss')+10/24/60"
								+ " and shebei_id in(select sbxx_id from sbxx where js_id='"+js_id+"') order by djsj desc";
						List all_kaoqin_xuesheng_xk = baseDao
								.executeSQL(xiakesql);
						for (int xki = 0; xki < all_kaoqin_xuesheng_xk.size(); xki++) {
							// 学号、上课时间、下课时间都有了，判断学生是正常、迟到、早退、既迟到又早退
							Object[] xsxkarr = (Object[]) all_kaoqin_xuesheng_xk
									.get(xki);
							String xkdjsj = (null == xsxkarr[1]) ? ""
									: xsxkarr[1].toString();// 获取上课学生的下课打卡时间
							String xs_cqqk = "";
							// 如果学生上课打卡时间>上课时间：则迟到
							// 如果学生下课打卡时间<下课时间：则早退
							// 如果两者皆满足：则既迟到又早退
							// 如果上述都不满足，则正常
							boolean skbool = false; // 上课是否迟到
							boolean xkbool = false; // 下课是否早退

							if (isDateBefore(riqi + " " + ks_kssj + ":00",
									skdjsj, 10 * 60 * 1000, 0)) {// 如果上课开始时间在打卡时间之前，则迟到
								skbool = true;
							}
							if (isDateBefore(xkdjsj, riqi + " " + ks_jssj
									+ ":00", 10 * 60 * 1000, 0)) {// 如果下课打卡时间在下课之前，则早退
								xkbool = true;
							}
							System.out.println();
							System.out.println("拼凑出来的上课时间是：" + riqi + " "
									+ ks_kssj + ":00" + "  上课打卡时间是：" + skdjsj
									+ "    真假为：" + skbool);
							System.out.println("拼凑出来的下课时间是：" + riqi + " "
									+ ks_jssj + ":00" + "  下课打卡时间是：" + xkdjsj
									+ "    真假为：" + skbool);

							if (skbool) {// 如果迟到
								if (xkbool) {// 如果迟到的情况下早退
									xs_cqqk = "既迟到又早退";
									sum_ztAndcd++;
									System.out.println("111111111111");
								} else { // 只迟到没早退
									xs_cqqk = "迟到";
									sum_chidao++;
									System.out.println("111111111112");
								}
							} else if (xkbool) {// 否则，如果早退
								if (skbool) {// 如果早退、又迟到
									xs_cqqk = "既迟到又早退";
									sum_ztAndcd++;
									System.out.println("111111111113");
								} else {
									xs_cqqk = "早退";
									sum_zaotui++;
									System.out.println("111111111114");
								}
							} else {
								xs_cqqk = "正常";
								sum_zhengchang++;
								System.out.println("111111111115");
							}
							String xssqlins = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where k.xh='"
									+ xuehao + "'";
							System.out.println("开放性课程：查询出学生的信息SQL：" + xssqlins);
							List all_kaoqin_chidao = baseDao
									.executeSQL(xssqlins); // 列出出勤情况正常的考勤信息
							for (int ini = 0; ini < all_kaoqin_chidao.size(); ini++) {
								Object[] zhengchang_xs = (Object[]) all_kaoqin_chidao
										.get(ini);
								String zc_xs_id = (null == zhengchang_xs[0]) ? ""
										: zhengchang_xs[0].toString();
								String zc_xh = (null == zhengchang_xs[1]) ? ""
										: zhengchang_xs[1].toString();
								String zc_zsxm = (null == zhengchang_xs[2]) ? ""
										: zhengchang_xs[2].toString();
								String zc_yh_id = (null == zhengchang_xs[3]) ? ""
										: zhengchang_xs[3].toString();
								String zc_xy_id = (null == zhengchang_xs[4]) ? ""
										: zhengchang_xs[4].toString();
								String zc_zy_id = (null == zhengchang_xs[5]) ? ""
										: zhengchang_xs[5].toString();
								String zc_nj_id = (null == zhengchang_xs[6]) ? ""
										: zhengchang_xs[6].toString();
								String zc_bj_id = (null == zhengchang_xs[7]) ? ""
										: zhengchang_xs[7].toString();

								XueShengKQ xk = new XueShengKQ();
								xk.setKcbid(kcb_id);
								xk.setKcxxmc(kcxxmc);
								// 设置课程相关信息：
								xk.setJs_id(js_id);
								xk.setJsmc(jsmc);
								xk.setLs_id(laoshi_id);
								xk.setLsmc(laoshimc);
								xk.setLsgh(laoshi_gh);
								xk.setKcb_id(kcb_id);
								xk.setKcb_kcxxmc(kcxxmc);
								xk.setXuehao(zc_xh);
								xk.setXs_id(zc_xs_id);
								xk.setKs_id(ks_id);
								xk.setKsmc(ksmc);
								xk.setCqqk(xs_cqqk);
								xk.setXy_id(zc_xy_id);
								xk.setZy_id(zc_zy_id);
								xk.setNj_id(zc_nj_id);
								xk.setBj_id(zc_bj_id);
								xk.setZc(String.valueOf(sum_zhengchang));
								xk.setCd(String.valueOf(sum_chidao));
								xk.setZt(String.valueOf(sum_zaotui));
								xk.setCdAndzt(String.valueOf(sum_ztAndcd));
								xk.setSksj(ks_kssj);
								xk.setXksj(ks_jssj);
								all.add(xk);
								break;
							}
							break;
						}
						flag++;
					} else {
						break;
					}
				}
				// 正常上课学生:计算....................结束.........
			}
			return all;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 公共选修课实时查询计算接口： param: jiaoshi:教室ID param: laoshi:老师ID param: keshi:课时ID
	 * param: kecheng:课程信息ID param: 日期:上课日期：格式为：YYYY-MM-DD ，例如：2014-11-19 param:
	 * jiaoshi:教室ID param: jiaoshi:教室ID
	 * */
	@Override
	public ArrayList<XueShengKQ> getKQQK_GGK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi) {
		
		int sum_zhengchang = 0;// 正常
		int sum_chidao = 0;// 迟到
		int sum_zaotui = 0;// 早退
		int sum_ztAndcd = 0;// 既迟到又早退
		
		try {
			ArrayList<XueShengKQ> all = new ArrayList<XueShengKQ>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(new Date()); // 当前时间转化成字符串
			String hql = "select * from kechengb a where 1=1  and kcblb in ('公共选修课') and xueqi_id in (select xq_id from xueqi where to_date(ksrq,'yyyy-mm-dd')<=sysdate and to_date(jsrq,'yyyy-mm-dd')>=sysdate) ";
			if (null != riqi && (!"".equals(riqi))) { // 查询课程表的时候加上日期标志
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			} else { // 如果参数中没传入日期参数，则将日期设置为当前天
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				riqi = df1.format(new Date());
				hql += " and a.xingqixh=(select to_char(to_date('"
						+ riqi.trim() + "','yyyy-mm-dd'),'D') from dual)";
			}
			if (null != keshi && (!"".equals(keshi))
					&& (!"".equals(keshi.trim()))) { // 加课时
				hql += " and a.ks_id='" + keshi.trim() + "'";
			}
			if (null != jiaoshi && (!"".equals(jiaoshi))
					&& (!"".equals(jiaoshi.trim()))) { // 加教室
				hql += " and a.js_id='" + jiaoshi.trim() + "'";
			}
			if (null != laoshi && (!"".equals(laoshi))
					&& (!"".equals(laoshi.trim()))) { // 加老师
				hql += " and a.laoshi_id='" + laoshi.trim() + "'";
			}
			if (null != kecheng && (!"".equals(kecheng))
					&& (!"".equals(kecheng.trim()))) { // 加课程
				hql += " and a.kcb_id='" + kecheng.trim() + "'";
			}
			
			// 执行HQL语句，查出要统计的所有考勤信息集合
			System.out.println("【公共选修课：】RealTimeServiceImpl中打印::" + "打出来的sql是："
					+ hql);
			List all_kcb = baseDao.executeSQL(hql);// 列出当天所有课程
			System.out.println("当前查询课程表sql是：" + hql);
			for (int kcb_i = 0; kcb_i < all_kcb.size(); kcb_i++) { // 循环课程列表，计算考勤信息
				Object[] tmpObjArray = (Object[]) all_kcb.get(kcb_i);
				String kcb_id = (null == tmpObjArray[0]) ? "" : tmpObjArray[0]
						.toString();
				String kcb_fks_id = (null == tmpObjArray[1]) ? ""
						: tmpObjArray[1].toString();
				String kcxx_id = (null == tmpObjArray[2]) ? "" : tmpObjArray[2]
						.toString();
				String kcxxmc = (null == tmpObjArray[3]) ? "" : tmpObjArray[3]
						.toString();
				String laoshi_id = (null == tmpObjArray[4]) ? ""
						: tmpObjArray[4].toString();
				String laoshimc = (null == tmpObjArray[5]) ? ""
						: tmpObjArray[5].toString();
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
				String xingqi = (null == tmpObjArray[11]) ? ""
						: tmpObjArray[11].toString();
				String kszhou = (null == tmpObjArray[12]) ? ""
						: tmpObjArray[12].toString();
				String jszhou = (null == tmpObjArray[13]) ? ""
						: tmpObjArray[13].toString();
				String xingqixh = (null == tmpObjArray[22]) ? ""
						: tmpObjArray[22].toString();
				String ks_kssj = (null == tmpObjArray[23]) ? ""
						: tmpObjArray[23].toString();
				String ks_jssj = (null == tmpObjArray[24]) ? ""
						: tmpObjArray[24].toString();
				String laoshi_gh = (null == tmpObjArray[25]) ? ""
						: tmpObjArray[25].toString();
				String maxrs = (null == tmpObjArray[30]) ? "0"
						: tmpObjArray[30].toString();
				//根据课程表id查询出应出勤的人数
				

				int maxrs_int = Integer.parseInt(maxrs);// 将上课人数转换为整数类型
				
				int flag = 0;
				// 查询出过来上课的学生学号:
				String xueshengsql = "select distinct xuehao,to_char(min(djsj),'yyyy-mm-dd hh24:mi:ss') from kqxx_day"
						+ " where  xuehao not like 'L%' and djsj>=to_date(to_char( to_date('"
						+ riqi
						+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
						+ ks_kssj
						+ "','yyyy-mm-dd hh24:mi:ss')-10/24/60"
						+ "   and djsj<=to_date(to_char( to_date('"
						+ riqi
						+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
						+ ks_jssj
						+ "','yyyy-mm-dd hh24:mi:ss')+10/24/60"
						+ " and shebei_id in(select sbxx_id from sbxx where js_id='"+js_id+"')"
						+ " group by xuehao "
						+ " order by to_char(min(djsj),'yyyy-mm-dd hh24:mi:ss') asc";
				System.out.println("公共选修课程:学生的学号SQL语句是：" + xueshengsql);
				List all_kaoqin_xuesheng = baseDao.executeSQL(xueshengsql);
				for (int zhengchang_i = 0; zhengchang_i < all_kaoqin_xuesheng
						.size(); zhengchang_i++) {
					Object[] xsarr = (Object[]) all_kaoqin_xuesheng
							.get(zhengchang_i);
					String xuehao = (null == xsarr[0]) ? "" : xsarr[0]
							.toString();// 获取上课学生的学号

					
					
					String skdjsj = (null == xsarr[1]) ? "" : xsarr[1]
							.toString();// 获取上课学生的上课打卡时间
					if (flag < maxrs_int) { // 如果人数小于最大上课人数,则计算出勤情况，否则跳出循环.
						System.out.println(flag + "：flag小于最大人数：" + maxrs_int);
						// 找下课打卡时间：
						String xiakesql = "select t.xuehao,to_char(djsj,'yyyy-mm-dd hh24:mi:ss') from kqxx t where t.xuehao ='"
								+ xuehao
								+ "'"
								+ " and djsj>=to_date(to_char( to_date('"
								+ riqi
								+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
								+ ks_kssj
								+ "','yyyy-mm-dd hh24:mi:ss')-10/24/60"
								+ " and djsj<=to_date(to_char( to_date('"
								+ riqi
								+ "','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"
								+ ks_jssj
								+ "','yyyy-mm-dd hh24:mi:ss')+10/24/60"
								+ " and shebei_id in(select sbxx_id from sbxx where js_id='"+js_id+"') order by djsj desc";
						List all_kaoqin_xuesheng_xk = baseDao
								.executeSQL(xiakesql);
						for (int xki = 0; xki < all_kaoqin_xuesheng_xk.size(); xki++) {
							// 学号、上课时间、下课时间都有了，判断学生是正常、迟到、早退、既迟到又早退
							Object[] xsxkarr = (Object[]) all_kaoqin_xuesheng_xk
									.get(xki);
							String xkdjsj = (null == xsxkarr[1]) ? ""
									: xsxkarr[1].toString();// 获取上课学生的下课打卡时间
							String xs_cqqk = "";
							// 如果学生上课打卡时间>上课时间：则迟到
							// 如果学生下课打卡时间<下课时间：则早退
							// 如果两者皆满足：则既迟到又早退
							// 如果上述都不满足，则正常
							boolean skbool = false; // 上课是否迟到
							boolean xkbool = false; // 下课是否早退

							if (isDateBefore(riqi + " " + ks_kssj + ":00",
									skdjsj, 10 * 60 * 1000, 0)) {// 如果上课开始时间在打卡时间之前，则迟到
								skbool = true;
							}
							if (isDateBefore(xkdjsj, riqi + " " + ks_jssj
									+ ":00", 10 * 60 * 1000, 0)) {// 如果下课打卡时间在下课之前，则早退
								xkbool = true;
							}
							System.out.println();
							System.out.println("拼凑出来的上课时间是：" + riqi + " "
									+ ks_kssj + ":00" + "  上课打卡时间是：" + skdjsj
									+ "    真假为：" + skbool);
							System.out.println("拼凑出来的下课时间是：" + riqi + " "
									+ ks_jssj + ":00" + "  下课打卡时间是：" + xkdjsj
									+ "    真假为：" + skbool);

							if (skbool) {// 如果迟到
								if (xkbool) {// 如果迟到的情况下早退
									xs_cqqk = "既迟到又早退";
									sum_ztAndcd++;
								} else { // 只迟到没早退
									xs_cqqk = "迟到";
									sum_chidao++;
								}
							} else if (xkbool) {// 否则，如果早退
								if (skbool) {// 如果早退、又迟到
									xs_cqqk = "既迟到又早退";
									sum_ztAndcd++;
								} else {
									xs_cqqk = "早退";
									sum_zaotui++;
								}
							} else {
								xs_cqqk = "正常";
								sum_zhengchang++;
							}
							String xssqlins = "select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where k.xh='"
									+ xuehao + "'";
							System.out.println("开放性课程：查询出学生的信息SQL：" + xssqlins);
							List all_kaoqin_chidao = baseDao
									.executeSQL(xssqlins); // 列出出勤情况正常的考勤信息
							for (int ini = 0; ini < all_kaoqin_chidao.size(); ini++) {
								Object[] zhengchang_xs = (Object[]) all_kaoqin_chidao
										.get(ini);
								String zc_xs_id = (null == zhengchang_xs[0]) ? ""
										: zhengchang_xs[0].toString();
								String zc_xh = (null == zhengchang_xs[1]) ? ""
										: zhengchang_xs[1].toString();
								String zc_zsxm = (null == zhengchang_xs[2]) ? ""
										: zhengchang_xs[2].toString();
								String zc_yh_id = (null == zhengchang_xs[3]) ? ""
										: zhengchang_xs[3].toString();
								String zc_xy_id = (null == zhengchang_xs[4]) ? ""
										: zhengchang_xs[4].toString();
								String zc_zy_id = (null == zhengchang_xs[5]) ? ""
										: zhengchang_xs[5].toString();
								String zc_nj_id = (null == zhengchang_xs[6]) ? ""
										: zhengchang_xs[6].toString();
								String zc_bj_id = (null == zhengchang_xs[7]) ? ""
										: zhengchang_xs[7].toString();

								XueShengKQ xk = new XueShengKQ();
								xk.setKcbid(kcb_id);
								xk.setKcxxmc(kcxxmc);
								// 设置课程相关信息：
								xk.setJs_id(js_id);
								xk.setJsmc(jsmc);
								xk.setLs_id(laoshi_id);
								xk.setLsmc(laoshimc);
								xk.setLsgh(laoshi_gh);
								xk.setKcb_id(kcb_id);
								xk.setKcb_kcxxmc(kcxxmc);
								xk.setXuehao(zc_xh);
								xk.setXs_id(zc_xs_id);
								xk.setKs_id(ks_id);
								xk.setKsmc(ksmc);
								xk.setCqqk(xs_cqqk);
								xk.setXy_id(zc_xy_id);
								xk.setZy_id(zc_zy_id);
								xk.setNj_id(zc_nj_id);
								xk.setBj_id(zc_bj_id);
								xk.setZc(String.valueOf(sum_zhengchang));
								xk.setCd(String.valueOf(sum_chidao));
								xk.setZt(String.valueOf(sum_zaotui));
								xk.setCdAndzt(String.valueOf(sum_ztAndcd));
								xk.setSksj(ks_kssj);
								xk.setXksj(ks_jssj);
								all.add(xk);
								break;
							}
							break;
						}
						flag++;
					} else {
						break;
					}
				}
				// 正常上课学生:计算....................结束.........
			}
			return all;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean isDateBefore(String date1, String date2, int in1, int in2) {
		try {

			DateFormat df = DateFormat.getDateTimeInstance();
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			// return df.parse(date1).before(df.parse(date2));
			String tmp1 = df.format(d1.getTime() + in1);
			String tmp2 = df.format(d2.getTime() + in2);
			System.out.println();
			System.out.println(in1 + "-->" + in2);
			System.out.println("tmp1的值是：" + tmp1);
			System.out.println("tmp2的值是：" + tmp2);
			System.out.println();
			return df.parse(tmp1).before(df.parse(tmp2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

}