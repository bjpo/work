package com.hrbsys.basicinfo.student.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.basicinfo.student.service.StudentService;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;

public class StudentServiceImpl implements StudentService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public boolean addStudent(Xsxx o) {
		return baseDao.save(o);
	}

	@Override
	public boolean delStudent(String xs_id) {
		String[] ids = xs_id.split(",");
		for (String id : ids) {
			Xsxx xs = findStudentById(id);
			if (!baseDao.delete(xs)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateStudent(Xsxx o) {
		Xsxx xs = baseDao.findAll("from Xsxx where XS_ID ='" + o.getXsId() + "'", Xsxx.class).get(0);
		BeanUtils.copyProperties(o, xs, new String[] { "xsId" });
		return baseDao.update(xs);
	}

	@Override
	public Xsxx findStudentById(String xs_id) {
		String hql = "from Xsxx where XS_ID='" + xs_id + "'";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}

	@Override
	public int getCountStudent(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from Xsxx where 1=1";
		if (null != params.get("xh")/* 学号 */
				&& !"".equals(params.get("xh"))) {
			hql += " and XH like '%" + params.get("xh").toString() + "%'";
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
	public Xsxx findStudentByXh(String xs_xh) {
		String hql = "from Xsxx where XH = '" + xs_xh + "'";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}

	/*
	 * 2014-05-29 于洋加 根据班级查学生列表
	 */
	@Override
	public List<Xsxx> findStudentByBanJi(String banji) {
		String hql = "from Xsxx where 1=1";
		if (null != banji && !"".equals(banji)) {
			String param = " and bj_id = '" + banji + "'";
		}
		hql += " and bj_id = '" + banji + "'";
		System.out.println(hql);
		List<Xsxx> list = baseDao.findAll(hql, Xsxx.class);
		return list;
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

	@Override
	public XUEYUAN findXUEYUANById(String id) {
		String hql = "from XUEYUAN where 1=1 and XY_ID = '" + id + "'";
		if (baseDao.findAll(hql, XUEYUAN.class).size() > 0) {
			return baseDao.findAll(hql, XUEYUAN.class).get(0);
		}
		return null;
	}

	@Override
	public ZHUANYE findZHUANYEById(String id) {
		String hql = "from ZHUANYE where 1=1 and ZY_ID = '" + id + "'";
		if (baseDao.findAll(hql, ZHUANYE.class).size() > 0) {
			return baseDao.findAll(hql, ZHUANYE.class).get(0);
		}
		return null;
	}

	@Override
	public NIANJI findNIANJIById(String id) {
		String hql = "from NIANJI where 1=1 and NJ_ID = '" + id + "'";
		if (baseDao.findAll(hql, NIANJI.class).size() > 0) {
			return baseDao.findAll(hql, NIANJI.class).get(0);
		}
		return null;
	}

	@Override
	public BANJI findBANJIById(String id) {
		String hql = "from BANJI where 1=1 and BJ_ID = '" + id + "'";
		if (baseDao.findAll(hql, BANJI.class).size() > 0) {
			return baseDao.findAll(hql, BANJI.class).get(0);
		}
		return null;
	}

	@Override
	public Xsxx findStudentByYHID(String yhid) {
		String hql = "from Xsxx where yhId=" + yhid + "";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}
}
