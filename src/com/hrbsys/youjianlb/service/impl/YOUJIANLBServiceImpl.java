package com.hrbsys.youjianlb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.YOUJIANLB;
import com.hrbsys.youjianlb.service.YOUJIANLBService;

public class YOUJIANLBServiceImpl implements YOUJIANLBService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addYOUJIANLB(YOUJIANLB y) {
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delYOUJIANLB(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			YOUJIANLB yhz = findYOUJIANLB(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// update
	@Override
	public boolean updateYOUJIANLB(YOUJIANLB t) {
		YOUJIANLB tmpt = baseDao.findAll("from YOUJIANLB where YOUJIANLB_ID='" + t.getYOUJIANLB_ID() + "'", YOUJIANLB.class).get(0);
		tmpt.setMS(t.getMS());
		tmpt.setXINGMING(t.getXINGMING());
		tmpt.setYXMC(t.getYXMC());
		tmpt.setBZ(t.getBZ());
		return baseDao.update(tmpt);
	}

	// findById
	@Override
	public YOUJIANLB findYOUJIANLB(String t_id) {
		List<YOUJIANLB> list = baseDao.findAll("from YOUJIANLB where YOUJIANLB_ID='" + t_id + "'", YOUJIANLB.class);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	// findByPage
	@Override
	public List<YOUJIANLB> findYOUJIANLBByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from YOUJIANLB where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("XINGMING") && !"".equals(params.get("XINGMING"))) {
			param = "and XINGMING like '%" + params.get("XINGMING").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("YXMC") && !"".equals(params.get("YXMC"))) {
			param = "and YXMC like '%" + params.get("YXMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		List<YOUJIANLB> list = baseDao.findByPage(hql, YOUJIANLB.class, start, number, params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountYOUJIANLB(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from YOUJIANLB where 1=1 ";
		if (null != params.get("XINGMING") && !"".equals(params.get("XINGMING"))) {
			hql += "and XINGMING like '%" + params.get("XINGMING").toString() + "%'";
		}
		if (null != params.get("YXMC") && !"".equals(params.get("YXMC"))) {
			hql += "and YXMC like '%" + params.get("YXMC").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class).get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<YOUJIANLB> findYOUJIANLBByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from YOUJIANLB where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("XINGMING") && !"".equals(params.get("XINGMING"))) {
			param = "and XINGMING like '%" + params.get("XINGMING").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("YXMC") && !"".equals(params.get("YXMC"))) {
			param = "and YXMC like '%" + params.get("YXMC").toString() + "%'";
			params2.add(param);
		}
		List<YOUJIANLB> list = baseDao.findAll(hql, YOUJIANLB.class);
		return list;
	}

}