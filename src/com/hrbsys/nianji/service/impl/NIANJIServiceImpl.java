package com.hrbsys.nianji.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.nianji.service.NIANJIService;

public class NIANJIServiceImpl implements NIANJIService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addNIANJI(NIANJI y) {
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delNIANJI(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			NIANJI yhz = findNIANJI(id);
			if(null != yhz){
				if (!baseDao.delete(yhz)) {
					return false;
				}
			}
		}
		return true;
	}

	// update
	@Override
	public boolean updateNIANJI(NIANJI t) {
		NIANJI tmpt = baseDao.findAll("from NIANJI where NJ_ID='" + t.getNJ_ID() + "'", NIANJI.class).get(0);
		tmpt.setNJMC(t.getNJMC());
		tmpt.setNJDM(t.getNJDM());
		return baseDao.update(tmpt);
	}

	// findById
	@Override
	public NIANJI findNIANJI(String t_id) {
		List<NIANJI> list = baseDao.findAll("from NIANJI where NJ_ID='" + t_id + "'", NIANJI.class);
		if(0 < list.size()){
			return list.get(0);
		}
		return null;
	}

	// findByPage
	@Override
	public List<NIANJI> findNIANJIByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from NIANJI where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			param = "and NJMC like '%" + params.get("NJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("NJDM") && !"".equals(params.get("NJDM"))) {
			param = "and NJDM like '%" + params.get("NJDM").toString() + "%'";
			params2.add(param);
		}
		List<NIANJI> list = baseDao.findByPage(hql, NIANJI.class, start, number, params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountNIANJI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from NIANJI where 1=1 ";
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			hql += "and NJMC like '%" + params.get("NJMC").toString() + "%'";
		}
		if (null != params.get("NJDM") && !"".equals(params.get("NJDM"))) {
			hql += "and NJDM like '%" + params.get("NJDM").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class).get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<NIANJI> findNIANJIByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from NIANJI where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			param = "and NJMC like '%" + params.get("NJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("NJDM") && !"".equals(params.get("NJDM"))) {
			param = "and NJDM like '%" + params.get("NJDM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("NJ_ID") && !"".equals(params.get("NJ_ID"))) {
			hql+=" AND NJ_ID='"+params.get("NJ_ID")+"'";
		}
		System.out.println("年级service中打印的sql："+hql);
		return baseDao.findAll(hql, NIANJI.class);
	}

	@Override
	public NIANJI findNIANJIByName(String Name) {
		List<NIANJI> list = baseDao.findAll("from NIANJI where NJMC = '" + Name + "'", NIANJI.class);
		if(0 < list.size()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public NIANJI findNIANJIByCode(String Code) {
		List<NIANJI> list = baseDao.findAll("from NIANJI where NJDM = '" + Code + "'", NIANJI.class);
		if(0 < list.size()){
			return list.get(0);
		}
		return null;
	}

}