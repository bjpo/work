package com.hrbsys.kaifang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.kaifang.service.KaiFangService;

/**
 * 开放性课程接口实现类
 * 
 */
public class KaiFangServiceImpl implements KaiFangService {
	private BaseDao baseDao;

	/*
	 * 查询出往天学生出勤的所有的数据
	 */
	public List<KQXX_XSCQ> findGKaiFangByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		// 查询往天记录的hql
		String hql = "FROM KQXX_XSCQ where 1=1 and kclb='开放性课程'";

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
			param += " and KCB_FKS_ID='" + params.get("kbc_fxs").toString() + "'";
			params2.add(param);
		}
		//增加课程表id
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			param += " and KCB_ID='" + params.get("KCB_ID").toString() + "'";
			params2.add(param);
		}
		
		// 增加上课的开始日期
		if (null != params.get("skkssj") && !"".equals(params.get("skkssj"))) {
			param += " and SKSJ>=to_date('" + params.get("skkssj").toString()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			params2.add(param);
		}
		// 增加上课的结束日期
		if (null != params.get("xkjssj") && !"".equals(params.get("xkjssj"))) {
			param += " and XKSJ<=to_date('" + params.get("xkjssj").toString()
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
		if (null != params.get("maxrs") && !"".equals(params.get("maxrs"))) {
			param += " and rownum<=" + params.get("maxrs").toString();
			params2.add(param);
		}
		// 查询结果集
		List list = baseDao.findByPage(hql, KQXX_XSCQ.class, start, number,
				params2, order, sort);
		return list;
	}

	// 查询往天“开放性课程”的老师的工号
	public List findKFXLS(String sksj, String xksj, String js_id) {
		String sql = "select * from kqxx t where t.djsj>=to_date('"
				+ sksj
				+ "','yyyy-mm-dd hh24:mi:ss')-10/24/60"
				+ " and t.djsj<=to_date('"
				+ xksj
				+ "','yyyy-mm-dd hh24:mi:ss')+10/24/60"
				+ " and t.shebei_id in (select a.sbxx_id from sbxx a where a.js_id='"
				+ js_id + "')" + "and t.xuehao like 'L%' order by t.djsj desc";
		List all = baseDao.executeSQL(sql);
		return all;
	}
	//查询实时“开放性课程”的老师的工号
	public List findRealKFXLS(String sksj, String xksj, String js_id) {
		String sql = "select * from kqxx_day t where t.djsj>=to_date('"
				+ sksj
				+ "','yyyy-mm-dd hh24:mi:ss')-10/24/60"
				+ " and t.djsj<=to_date('"
				+ xksj
				+ "','yyyy-mm-dd hh24:mi:ss')+10/24/60"
				+ " and t.shebei_id in (select a.sbxx_id from sbxx a where a.js_id='"
				+ js_id + "')" + "and t.xuehao like 'L%' order by t.djsj desc";
		System.out.println("我是findRealKFXLS   sql-------->"+sql);
		List all = baseDao.executeSQL(sql);
		return all;
	}
	// 根据工号去查询老师
	public List findJG(String gh) {
		String sql = "select * from jiaogong t where t.jggh='" + gh + "'";
		List alllaoshi = baseDao.executeSQL(sql);
		return alllaoshi;
	}

	public List findRealKcxx(String riqi) {
		String sql = "select * from kechengb a where 1=1  and kcblb in ('开放性课程')  "
				+ "and a.xingqixh=(select to_char(to_date('"
				+ riqi
				+ "','yyyy-mm-dd'),'D') from dual)";
		List kcbList = baseDao.executeSQL(sql);
		return kcbList;
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
