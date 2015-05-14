package com.hrbsys.moban.service.impl;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.MOBAN;
import com.hrbsys.moban.service.MOBANService;
public class MOBANServiceImpl implements MOBANService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addMOBAN(MOBAN y) {
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delMOBAN(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {MOBAN yhz=findMOBAN(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateMOBAN(MOBAN t) {
		MOBAN tmpt = baseDao.findAll("from MOBAN where MB_ID='" + t.getMB_ID() + "'",MOBAN.class).get(0);
   	    tmpt.setMB_NAME(t.getMB_NAME());
   	    tmpt.setMB_ID(t.getMB_ID());
   	    tmpt.setBIAOMING(t.getBIAOMING());
   	    tmpt.setZHUJIAN(t.getZHUJIAN());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public MOBAN findMOBAN(String t_id) {
		return baseDao.findAll("from MOBAN where MB_ID='" + t_id + "'",MOBAN.class).get(0);
	}

    //findByPage
	@Override
	public List<MOBAN> findMOBANByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from MOBAN where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("MB_NAME") && !"".equals(params.get("MB_NAME"))) {
			param = "and MB_NAME like '%" + params.get("MB_NAME").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("MB_ID") && !"".equals(params.get("MB_ID"))) {
			param = "and MB_ID like '%" + params.get("MB_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("BIAOMING") && !"".equals(params.get("BIAOMING"))) {
			param = "and BIAOMING like '%" + params.get("BIAOMING").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<MOBAN> list = baseDao.findByPage(hql, MOBAN.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountMOBAN(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from MOBAN where 1=1 ";
		if (null != params.get("MB_NAME")
				&& !"".equals(params.get("MB_NAME"))) {
			hql += "and MB_NAME like '%" + params.get("MB_NAME").toString()
					+ "%'";
		}
		if (null != params.get("MB_ID")
				&& !"".equals(params.get("MB_ID"))) {
			hql += "and MB_ID like '%" + params.get("MB_ID").toString()
					+ "%'";
		}
		if (null != params.get("BIAOMING")
				&& !"".equals(params.get("BIAOMING"))) {
			hql += "and BIAOMING like '%" + params.get("BIAOMING").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<MOBAN> findMOBANByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from MOBAN where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("MB_NAME")
				&& !"".equals(params.get("MB_NAME"))) {
			param = "and MB_NAME like '%"
					+ params.get("MB_NAME").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MB_ID")
				&& !"".equals(params.get("MB_ID"))) {
			param = "and MB_ID like '%"
					+ params.get("MB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BIAOMING")
				&& !"".equals(params.get("BIAOMING"))) {
			param = "and BIAOMING like '%"
					+ params.get("BIAOMING").toString() + "%'";
			params2.add(param);
		}
		List<MOBAN> list = baseDao.findAll(hql,MOBAN.class);
		return list;
	}

}