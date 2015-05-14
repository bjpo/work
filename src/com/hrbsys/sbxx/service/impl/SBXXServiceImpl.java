package com.hrbsys.sbxx.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.functions.IfFunc;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.SBXX;
import com.hrbsys.sbxx.service.SBXXService;
public class SBXXServiceImpl implements SBXXService {
	private BaseDao baseDao;
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addSBXX(SBXX y) {
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delSBXX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			SBXX yhz = findSBXX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// update
	@Override
	public boolean updateSBXX(SBXX t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SBXX tmpt = baseDao.findAll("from SBXX where ID='" + t.getID() + "'",
				SBXX.class).get(0);
		tmpt.setJSMC(t.getJSMC());
		tmpt.setBZ(t.getBZ());
		tmpt.setJS_ID(t.getJS_ID());
		tmpt.setMS(t.getMS());
		tmpt.setSBXX_ID(t.getSBXX_ID());
		tmpt.setID(t.getID());
		tmpt.setSBMC(t.getSBMC());
		return baseDao.update(tmpt);
	}

	// findById
	@Override
	public SBXX findSBXX(String t_id) {
		return baseDao.findAll("from SBXX where ID='" + t_id + "'", SBXX.class)
				.get(0);
	}

	// findByPage
	@Override
	public List<SBXX> findSBXXByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from SBXX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("ID") && !"".equals(params.get("ID"))) {
			param = "and ID like '%" + params.get("ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%" + params.get("JSMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%" + params.get("JS_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("SBXX_ID") && !"".equals(params.get("SBXX_ID"))) {
			param = "and SBXX_ID like '%" + params.get("SBXX_ID").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("SBMC") && !"".equals(params.get("SBMC"))) {
			param = "and SBMC like '%" + params.get("SBMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		List<SBXX> list = baseDao.findByPage(hql, SBXX.class, start, number,
				params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountSBXX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from SBXX where 1=1 ";
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			hql += "and JSMC like '%" + params.get("JSMC").toString() + "%'";
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString() + "%'";
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			hql += "and JS_ID like '%" + params.get("JS_ID").toString() + "%'";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString() + "%'";
		}

		if (null != params.get("ID") && !"".equals(params.get("ID"))) {
			hql += "and ID like '%" + params.get("ID").toString() + "%'";
		}
		if (null != params.get("SBXX_ID") && !"".equals(params.get("SBXX_ID"))) {
			hql += "and SBXX_ID like '%" + params.get("SBXX_ID").toString()
					+ "%'";
		}
		if (null != params.get("SBMC") && !"".equals(params.get("SBMC"))) {
			hql += "and SBMC like '%" + params.get("SBMC").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<SBXX> findSBXXByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from SBXX where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%" + params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%" + params.get("JS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ID") && !"".equals(params.get("ID"))) {
			param = "and SBXX_ID like '%" + params.get("ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("SBXX_ID") && !"".equals(params.get("SBXX_ID"))) {
			param = "and SBXX_ID like '%" + params.get("SBXX_ID").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("SBMC") && !"".equals(params.get("SBMC"))) {
			param = "and SBMC like '%" + params.get("SBMC").toString() + "%'";
			params2.add(param);
		}
		List<SBXX> list = baseDao.findAll(hql, SBXX.class);
		return list;
	}
	@Override
	public boolean findName(String sbName) {
		String hql = "from SBXX where SBXX_ID='" + sbName+"'";
		if (baseDao.findAll(hql, SBXX.class).size()>0) {
			return true;
		}
			return false;
	}
}