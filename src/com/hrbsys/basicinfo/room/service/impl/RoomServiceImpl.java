package com.hrbsys.basicinfo.room.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.basicinfo.room.service.RoomService;
import com.hrbsys.bean.FANGJIAN;
import com.hrbsys.bean.JXL;

public class RoomServiceImpl implements RoomService {

	private BaseDao baseDao;	
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public boolean addRoom(FANGJIAN o) {
		return baseDao.save(o);
	}

	@Override
	public boolean delRoom(String fj_id) {
		String[] ids = fj_id.split(",");
		for (String id : ids) {
			FANGJIAN fj=findRoomById(id);
			if (!baseDao.delete(fj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateRoom(FANGJIAN o) {
		FANGJIAN fj = baseDao.findAll("from FANGJIAN where FJ_ID='" + o.getFjId() + "'",FANGJIAN.class).get(0);
		BeanUtils.copyProperties(o, fj, new String[] { "fjId" });
		return baseDao.update(fj);
	}

	@Override
	public FANGJIAN findRoomById(String fj_id) {
		return baseDao.findAll("from FANGJIAN where FJ_ID='" + fj_id + "'",FANGJIAN.class).get(0);
	}
	
	@Override
	public int getCountRoom(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from FANGJIAN where 1=1";
		if (null != params.get("fjmc")
				&& !"".equals(params.get("fjmc"))) {
			hql += " and FJMC like '%" + params.get("fjmc").toString()
					+ "%'";
		}
		if (null != params.get("fjdm") && !"".equals(params.get("fjdm"))) {
			hql += " and FJDM like '%" + params.get("fjdm").toString() + "%'";
		}
		if (null != params.get("fjdz") && !"".equals(params.get("fjdz"))) {
			hql += " and FJDZ like '%" + params.get("fjdz").toString() + "%'";
		}
		if (null != params.get("fjh") && !"".equals(params.get("fjh"))) {
			hql += " and FJH like '%" + params.get("fjh").toString() + "%'";
		}
		if (null != params.get("louceng") && !"".equals(params.get("louceng"))) {
			hql += " and LOUCENG =" + params.get("louceng").toString();
		}
		if (null != params.get("isdmt") && !"".equals(params.get("isdmt"))) {
			hql += " and ISDMT like '%" + params.get("isdmt").toString() + "%'";
		}
		if (null != params.get("renshu") && !"".equals(params.get("renshu"))) {
			hql += " and RENSHU =" + params.get("renshu").toString();
		}
		if (null != params.get("jxlId") && !"".equals(params.get("jxlId"))) {
			hql += " and JXL_ID like '%" + params.get("jxlId").toString() + "%'";
		}
		if (null != params.get("jxlMc") && !"".equals(params.get("jxlMc"))) {
			hql += " and JXL_MC like '%" + params.get("jxlMc").toString() + "%'";
		}
		if (null != params.get("jxllh") && !"".equals(params.get("jxllh"))) {
			hql += " and JXLLH like '%" + params.get("jxllh").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<FANGJIAN> findRoomByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from FANGJIAN where 1=1";
		
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("fjmc")&& !"".equals(params.get("fjmc"))) {
			String param = " and FJMC like '%"+ params.get("fjmc") + "%'";
			params2.add(param);
		}
		if (null != params.get("fjdm") && !"".equals(params.get("fjdm"))) {
			String param = " and FJDM like '%" + params.get("fjdm")+ "%'";
			params2.add(param);
		}
		if (null != params.get("fjdz") && !"".equals(params.get("fjdz"))) {
			String param = " and FJDZ like '%" + params.get("fjdz")+ "%'";
			params2.add(param);
		}
		if (null != params.get("fjh") && !"".equals(params.get("fjh"))) {
			String param = " and FJH like '%" + params.get("fjh")+ "%'";
			params2.add(param);
		}
		if (null != params.get("louceng") && !"".equals(params.get("louceng"))) {
			String param = " and LOUCENG =" + params.get("louceng");
			params2.add(param);
		}
		if (null != params.get("isdmt") && !"".equals(params.get("isdmt"))) {
			String param = " and ISDMT like '%" + params.get("isdmt")+ "%'";
			params2.add(param);
		}
		if (null != params.get("renshu") && !"".equals(params.get("renshu"))) {
			String param = " and RENSHU =" +  params.get("renshu");
			params2.add(param);
		}
		if (null != params.get("jxlId") && !"".equals(params.get("jxlId"))) {
			String param = " and JXL_ID like '%" + params.get("jxlId")+ "%'";
			params2.add(param);
		}
		if (null != params.get("jxlMc") && !"".equals(params.get("jxlMc"))) {
			String param = " and JXL_MC like '%" + params.get("jxlMc")+ "%'";
			params2.add(param);
		}
		if (null != params.get("jxllh") && !"".equals(params.get("jxllh"))) {
			String param = " and JXLLH like '%" + params.get("jxllh")+ "%'";
			params2.add(param);
		}
		
		List<FANGJIAN> list = baseDao.findByPage(hql, FANGJIAN.class, start,number, params2, order, sort);
		return list;
	}

	@Override
	public List<FANGJIAN> findRoomByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from FANGJIAN where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("fjmc") && !"".equals(params.get("fjmc"))) {
			hql += " and FJMC = '" + params.get("fjmc").toString() + "'";
		}
		if (null != params.get("fjdm") && !"".equals(params.get("fjdm"))) {
			hql += " and FJDM = '" + params.get("fjdm").toString()+ "'";
		}
		if (null != params.get("fjdz") && !"".equals(params.get("fjdz"))) {
			hql += " and FJDZ = '" + params.get("fjdz").toString()+ "'";
		}
		if (null != params.get("fjh") && !"".equals(params.get("fjh"))) {
			hql += " and FJH = '" + params.get("fjh").toString()+ "'";
		}
	
		return baseDao.findAll(hql,FANGJIAN.class);
	}

	@Override
	public List<JXL> findJXLByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from JXL where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jxlmc") && !"".equals(params.get("jxlmc"))) {
			String param = "and JXLMC like '%" + params.get("jxlmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jxldm") && !"".equals(params.get("jxldm"))) {
			String param = "and JXLDM like '%" + params.get("jxldm").toString()
					+ "%'";
			params2.add(param);
		}
		List<JXL> list = baseDao.findAll(hql,JXL.class);
		return list;
	}

	@Override
	public JXL findJXLById(String jxl_id) {
		return baseDao.findAll("from JXL where JXL_ID='" + jxl_id + "'",JXL.class).get(0);
	}
}
