package com.hrbsys.luzhiwen.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.luzhiwen.service.LuzhiwenService;

public class LuzhiwenServiceImpl implements LuzhiwenService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int getCountStudent(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from Xsxx where 1=1";
		if (null != params.get("xh")/* 学号 */
				&& !"".equals(params.get("xh"))) {
			hql += " and XH like '%" + params.get("xh").toString() + "%'";
		}

		if (null != params.get("xyId")/* 学院 */
				&& !"".equals(params.get("xyId"))) {
			hql += " and XY_ID like '%" + params.get("xyId").toString() + "%'";
		}
		
		if (null != params.get("zyId")/* 专业 */
				&& !"".equals(params.get("zyId"))) {
			hql += " and ZY_ID like '%" + params.get("zyId").toString() + "%'";
		}
		
		if (null != params.get("njId")/* 年级 */
				&& !"".equals(params.get("njId"))) {
			hql += " and NJ_ID like '%" + params.get("njId").toString() + "%'";
		}
		
		if (null != params.get("bjId")/* 班级 */
				&& !"".equals(params.get("bjId"))) {
			hql += " and BJ_ID like '%" + params.get("bjId").toString() + "%'";
		}

		if (null != params.get("zsxm")/* 真实姓名 */
				&& !"".equals(params.get("zsxm"))) {
			hql += " and ZSXM like '%" + params.get("zsxm").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class).get(0).toString());
		return count;
	}

	@Override
	public List<Xsxx> findStudentByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from Xsxx where 1=1";

		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("xh") && !"".equals(params.get("xh"))) {
			String param = " and XH like '%" + params.get("xh") + "%'";
			params2.add(param);
		}
		if (null != params.get("zsxm") && !"".equals(params.get("zsxm"))) {
			String param = " and ZSXM like '%" + params.get("zsxm") + "%'";
			params2.add(param);
		}
		if (null != params.get("xyId") && !"".equals(params.get("xyId"))) {
			String param = " and XY_ID like '%" + params.get("xyId") + "%'";
			params2.add(param);
		}
		if (null != params.get("zyId") && !"".equals(params.get("zyId"))) {
			String param = " and ZY_ID like '%" + params.get("zyId") + "%'";
			params2.add(param);
		}
		if (null != params.get("njId") && !"".equals(params.get("njId"))) {
			String param = " and NJ_ID like '%" + params.get("njId") + "%'";
			params2.add(param);
		}
		if (null != params.get("bjId") && !"".equals(params.get("bjId"))) {
			String param = " and BJ_ID like '%" + params.get("bjId") + "%'";
			params2.add(param);
		}

		List<Xsxx> list = baseDao.findByPage(hql, Xsxx.class, start, number, params2, order, sort);
		return list;
	}

	@Override
	public List<Xsxx> findStudentByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from Xsxx where 1=1";
		if (null != params.get("xh")/* 学号 */
				&& !"".equals(params.get("xh"))) {
			hql += " and XH like '%" + params.get("xh").toString() + "%'";
		}

		if (null != params.get("zsxm")/* 真实姓名 */
				&& !"".equals(params.get("zsxm"))) {
			hql += " and ZSXM like '%" + params.get("zsxm").toString() + "%'";
		}

		return baseDao.findAll(hql, Xsxx.class);
	}

	@Override
	public XUEYUAN findXUEYUANById(String id) {
		String hql = "from XUEYUAN where XY_ID='" + id + "'";
		if (baseDao.findAll(hql, XUEYUAN.class).size() > 0) {
			return baseDao.findAll(hql, XUEYUAN.class).get(0);
		}
		return null;
	}

	@Override
	public ZHUANYE findZHUANYEById(String id) {
		String hql = "from ZHUANYE where ZY_ID='" + id + "'";
		if (baseDao.findAll(hql, ZHUANYE.class).size() > 0) {
			return baseDao.findAll(hql, ZHUANYE.class).get(0);
		}
		return null;
	}

	@Override
	public NIANJI findNIANJIById(String id) {
		String hql = "from NIANJI where NJ_ID='" + id + "'";
		if (baseDao.findAll(hql, NIANJI.class).size() > 0) {
			return baseDao.findAll(hql, NIANJI.class).get(0);
		}
		return null;
	}

	@Override
	public BANJI findBANJIById(String id) {
		String hql = "from BANJI where BJ_ID='" + id + "'";
		if (baseDao.findAll(hql, BANJI.class).size() > 0) {
			return baseDao.findAll(hql, BANJI.class).get(0);
		}
		return null;
	}

	@Override
	public Xsxx findStudentById(String id) {
		String hql = "from Xsxx where XS_ID='" + id + "'";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}

	@Override
	public boolean updateStudent(Xsxx o) {
		return baseDao.update(o);
	}

	@Override
	public boolean RecordOptLog(UPDATEVERSION o) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		o.setUPDATETIME(df.format(new Date()).toString());
		String hql = "from UPDATEVERSION where NAME = '" + o.getNAME() + "' and TYPE = '" + o.getTYPE() + "'";
		List<UPDATEVERSION> list = baseDao.findAll(hql, UPDATEVERSION.class);
		if(0 < list.size())
		{
			UPDATEVERSION uv = list.get(0);
			uv.setOPT(o.getOPT());
			uv.setUPDATETIME(o.getUPDATETIME());
			return baseDao.update(uv);
		}
		return baseDao.save(o);
	}

}
