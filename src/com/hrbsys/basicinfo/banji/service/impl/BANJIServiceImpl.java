package com.hrbsys.basicinfo.banji.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.basicinfo.banji.service.BANJIService;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.ZHUANYE;

public class BANJIServiceImpl implements BANJIService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addBANJI(BANJI y) {
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delBANJI(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			BANJI yhz = findBANJI(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// update
	@Override
	public boolean updateBANJI(BANJI t) {
		return baseDao.update(t);
	}

	// findById
	@Override
	public BANJI findBANJI(String t_id) {
		List<BANJI> bj = baseDao.findAll("from BANJI where BJ_ID='" + t_id + "'", BANJI.class);
		if (0 == bj.size()) {
			return null;
		}
		return bj.get(0);
	}

	// findByPage
	@Override
	public List<BANJI> findBANJIByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from BANJI where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			param = "and NJMC like '%" + params.get("NJMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("NJDM") && !"".equals(params.get("NJDM"))) {
			param = "and NJDM like '%" + params.get("NJDM").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZYMC") && !"".equals(params.get("ZYMC"))) {
			param = "and ZYMC like '%" + params.get("ZYMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZYDM") && !"".equals(params.get("ZYDM"))) {
			param = "and ZYDM like '%" + params.get("ZYDM").toString() + "%'";
			params2.add(param);
			param = "";
		}

		if (null != params.get("BZK") && !"".equals(params.get("BZK"))) {
			param = "and BZK like '%" + params.get("BZK").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("BJMC") && !"".equals(params.get("BJMC"))) {
			param = "and BJMC like '%" + params.get("BJMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("BJHM") && !"".equals(params.get("BJHM"))) {
			param = "and BJHM like '%" + params.get("BJHM").toString() + "%'";
			params2.add(param);
			param = "";
		}
		List<BANJI> list = baseDao.findByPage(hql, BANJI.class, start, number, params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountBANJI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from BANJI where 1=1 ";
		if (null != params.get("NJDM") && !"".equals(params.get("NJDM"))) {
			hql += "and NJDM like '%" + params.get("NJDM").toString() + "%'";
		}
		if (null != params.get("ZYDM") && !"".equals(params.get("ZYDM"))) {
			hql += "and ZYDM like '%" + params.get("ZYDM").toString() + "%'";
		}
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			hql += "and NJMC like '%" + params.get("NJMC").toString() + "%'";
		}
		if (null != params.get("ZYMC") && !"".equals(params.get("ZYMC"))) {
			hql += "and ZYMC like '%" + params.get("ZYMC").toString() + "%'";
		}
		if (null != params.get("BZK") && !"".equals(params.get("BZK"))) {
			hql += "and BZK like '%" + params.get("BZK").toString() + "%'";
		}
		if (null != params.get("BJMC") && !"".equals(params.get("BJMC"))) {
			hql += "and BJMC like '%" + params.get("BJMC").toString() + "%'";
		}
		if (null != params.get("BJHM") && !"".equals(params.get("BJHM"))) {
			hql += "and BJHM like '%" + params.get("BJHM").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class).get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<BANJI> findBANJIByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from BANJI where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("NJ_ID") && !"".equals(params.get("NJ_ID"))) {
			hql+= " and NJ_ID='" + params.get("NJ_ID").toString() + "'";
		}
		if (null != params.get("ZY_ID") && !"".equals(params.get("ZY_ID"))) {
			hql+= " and ZY_ID='" + params.get("ZY_ID").toString() + "'";
		}
		if (null != params.get("NJDM") && !"".equals(params.get("NJDM"))) {
			param = " and NJDM like '%" + params.get("NJDM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZYDM") && !"".equals(params.get("ZYDM"))) {
			param = "and ZYDM like '%" + params.get("ZYDM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			param = "and NJMC like '%" + params.get("NJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZYMC") && !"".equals(params.get("ZYMC"))) {
			param = "and ZYMC like '%" + params.get("ZYMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZK") && !"".equals(params.get("BZK"))) {
			param = "and BZK like '%" + params.get("BZK").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BJMC") && !"".equals(params.get("BJMC"))) {
			param = "and BJMC like '%" + params.get("BJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BJHM") && !"".equals(params.get("BJHM"))) {
			param = "and BJHM like '%" + params.get("BJHM").toString() + "%'";
			params2.add(param);
		}
		System.out.println("--------------"+hql);
		List<BANJI> list = baseDao.findAll(hql, BANJI.class);
		return list;
	}

	@Override
	public List<NIANJI> findNIANJI() {
		return baseDao.findAll("from NIANJI where 1=1", NIANJI.class);
	}

	@Override
	public List<ZHUANYE> findZHUANYE() {
		return baseDao.findAll("from ZHUANYE where 1=1", ZHUANYE.class);
	}

	@Override
	public BANJI findBANJIByName(String Name) {
		List<BANJI> bj = baseDao.findAll("from BANJI where BJMC='" + Name + "'", BANJI.class);
		if (0 == bj.size()) {
			return null;
		}
		return bj.get(0);
	}

	@Override
	public NIANJI findNIANJI(String id) {
		List<NIANJI> nj = baseDao.findAll("from NIANJI where NJ_ID='" + id + "'", NIANJI.class);
		if (0 == nj.size()) {
			return null;
		}
		return nj.get(0);
	}

	@Override
	public ZHUANYE findZHUANYE(String id) {
		List<ZHUANYE> zy = baseDao.findAll("from ZHUANYE where ZY_ID='" + id + "'", ZHUANYE.class);
		if (0 == zy.size()) {
			return null;
		}
		return zy.get(0);
	}

	@Override
	public BANJI findBANJIByNIANJI(HashMap<String, String> params) {
		
		String hql = "from BANJI where 1=1";
		if (null != params.get("NJ_ID") && !"".equals(params.get("NJ_ID"))) {
			hql += " and NJ_ID = '" + params.get("NJ_ID").toString() + "'";
		}
		if (null != params.get("BJMC") && !"".equals(params.get("BJMC"))) {
			hql += " and BJMC = '" + params.get("BJMC").toString() + "'";
		}
		List<BANJI> list = baseDao.findAll(hql, BANJI.class);
		if(0 == list.size()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public BANJI findBANJIByZHUANYE(HashMap<String, String> params) {
		
		String hql = "from BANJI where 1=1";
		if (null != params.get("ZY_ID") && !"".equals(params.get("ZY_ID"))) {
			hql += " and ZY_ID = '" + params.get("ZY_ID").toString() + "'";
		}
		if (null != params.get("BJMC") && !"".equals(params.get("BJMC"))) {
			hql += " and BJMC = '" + params.get("BJMC").toString() + "'";
		}
		List<BANJI> list = baseDao.findAll(hql, BANJI.class);
		if(0 == list.size()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<BANJI> findXSByZYandNJandBJ(String zy_id,String nj_id,String bj_id) {
		String hql = "from BANJI where 1=1";
		if (null != zy_id && !"".equals(zy_id)) {
			hql+=" and ZY_ID='"+zy_id+"'";
		}
		if (null != nj_id && !"".equals(nj_id)) {
			hql+=" and NJ_ID='"+nj_id+"'";
		}
		if (null != bj_id && !"".equals(bj_id)) {
			hql+=" and BJ_ID='"+bj_id+"'";
		}
		System.out.println("BANJISERVICE中打印出的HQL：：：："+hql);
		List<BANJI> list = baseDao.findAll(hql,BANJI.class);
		return list;
	}

	@Override
	public List<BANJI> findBANJIFORZYANDNIANJI(HashMap<String, String> params) {
		String hql = "from BANJI where 1=1";
		if (null != params.get("NJ_ID") && !"".equals(params.get("NJ_ID"))) {
			hql += " and NJ_ID = '" + params.get("NJ_ID").toString() + "'";
		}
		if (null != params.get("ZY_ID") && !"".equals(params.get("ZY_ID"))) {
			hql += " and ZY_ID = '" + params.get("ZY_ID").toString() + "'";
		}
		return baseDao.findAll(hql, BANJI.class);
	}
}