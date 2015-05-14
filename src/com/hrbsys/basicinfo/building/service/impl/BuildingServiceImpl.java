package com.hrbsys.basicinfo.building.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.basicinfo.building.service.BuildingService;
import com.hrbsys.bean.JXL;

public class BuildingServiceImpl implements BuildingService {
	
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<JXL> findJXLByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from JXL where 1=1";
		
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jxlmc")&& !"".equals(params.get("jxlmc"))) {
			String param = " and JXLMC like '%"+ params.get("jxlmc") + "%'";
			params2.add(param);
		}
		if (null != params.get("jxldm") && !"".equals(params.get("jxldm"))) {
			String param = " and JXLDM like '%" + params.get("jxldm")+ "%'";
			params2.add(param);
		}
		if (null != params.get("jxllh") && !"".equals(params.get("jxllh"))) {
			String param = " and JXLLH like '%" + params.get("jxllh")+ "%'";
			params2.add(param);
		}
		if (null != params.get("jxlwz") && !"".equals(params.get("jxlwz"))) {
			String param = " and JXLWZ like '%" + params.get("jxlwz")+ "%'";
			params2.add(param);
		}
		
		List<JXL> list = baseDao.findByPage(hql, JXL.class, start,number, params2, order, sort);
		return list;
	}

	@Override
	public boolean addJXL(JXL o) {
		return baseDao.save(o);
	}

	@Override
	public boolean delJXL(String jxl_id) {
		String[] ids = jxl_id.split(",");
		for (String id : ids) {
			if(null!=id||!"".equals(id)){
				JXL tmp=findJXLById(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateJXL(JXL o) {
		JXL tmpjs = baseDao.findAll("from JXL where JXL_ID='" + o.getJxlId() + "'",JXL.class).get(0);
		tmpjs.setJxlId(o.getJxlId());
		tmpjs.setJxlmc(o.getJxlmc());
		tmpjs.setJxldm(o.getJxldm());
		tmpjs.setJxllh(o.getJxllh());
		tmpjs.setJxlwz(o.getJxlwz());
		tmpjs.setBz(o.getBz());
		tmpjs.setMs(o.getMs());
		return baseDao.update(tmpjs);
	}

	@Override
	public JXL findJXLById(String jxl_id) {
		return baseDao.findAll("from JXL where JXL_ID='" + jxl_id + "'",JXL.class).get(0);
	}

	@Override
	public int getCountJXL(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JXL where 1=1";
		if (null != params.get("jxlmc")
				&& !"".equals(params.get("jxlmc"))) {
			hql += " and JXLMC like '%" + params.get("jxlmc").toString()
					+ "%'";
		}
		if (null != params.get("jxldm") && !"".equals(params.get("jxldm"))) {
			hql += " and JXLDM like '%" + params.get("jxldm").toString() + "%'";
		}
		if (null != params.get("jxllh") && !"".equals(params.get("jxllh"))) {
			hql += " and JXLLH like '%" + params.get("jxllh").toString() + "%'";
		}
		if (null != params.get("jxlwz") && !"".equals(params.get("jxlwz"))) {
			hql += " and JXLWZ like '%" + params.get("jxlwz").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<JXL> findJXLByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from JXL where 1=1";
		List<JXL> list = baseDao.findAll(hql, JXL.class);
		return list;
	}
}
