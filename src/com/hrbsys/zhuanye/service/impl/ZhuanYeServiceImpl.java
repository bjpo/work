package com.hrbsys.zhuanye.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.zhuanye.service.ZhuanYeService;


public class ZhuanYeServiceImpl implements ZhuanYeService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addZHUANYE(ZHUANYE y) {
      // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// y.setDJRQ(df.format(new Date()).toString());
		// y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delZHUANYE(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {ZHUANYE yhz=findZHUANYE(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateZHUANYE(ZHUANYE t) {
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ZHUANYE tmpt = baseDao.findAll("from ZHUANYE where ZY_ID='" + t.getZY_ID() + "'",ZHUANYE.class).get(0);
   	    tmpt.setMS(t.getMS());
   	    tmpt.setXYID(t.getXYID());
   	    tmpt.setZYMC(t.getZYMC());
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setXYMC(t.getXYMC());
   	    tmpt.setZYDM(t.getZYDM());
		//tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public ZHUANYE findZHUANYE(String t_id) {
		List<ZHUANYE> all=baseDao.findAll("from ZHUANYE where ZY_ID='" + t_id + "'",ZHUANYE.class);
		if(all.size()<1){
			return null;
		}
		return all.get(0);
	}

    //findByPage
	@Override
	public List<ZHUANYE> findZHUANYEByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from ZHUANYE where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XYID") && !"".equals(params.get("XYID"))) {
			param = "and XYID like '%" + params.get("XYID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZYMC") && !"".equals(params.get("ZYMC"))) {
			param = "and ZYMC like '%" + params.get("ZYMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XYMC") && !"".equals(params.get("XYMC"))) {
			param = "and XYMC like '%" + params.get("XYMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZYDM") && !"".equals(params.get("ZYDM"))) {
			param = "and ZYDM like '%" + params.get("ZYDM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<ZHUANYE> list = baseDao.findByPage(hql, ZHUANYE.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountZHUANYE(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from ZHUANYE where 1=1 ";
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("XYID")
				&& !"".equals(params.get("XYID"))) {
			hql += "and XYID like '%" + params.get("XYID").toString()
					+ "%'";
		}
		if (null != params.get("ZYMC")
				&& !"".equals(params.get("ZYMC"))) {
			hql += "and ZYMC like '%" + params.get("ZYMC").toString()
					+ "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("XYMC")
				&& !"".equals(params.get("XYMC"))) {
			hql += "and XYMC like '%" + params.get("XYMC").toString()
					+ "%'";
		}
		if (null != params.get("ZYDM")
				&& !"".equals(params.get("ZYDM"))) {
			hql += "and ZYDM like '%" + params.get("ZYDM").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<ZHUANYE> findZHUANYEByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from ZHUANYE where 1=1 ";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XYID")
				&& !"".equals(params.get("XYID"))) {
			hql+=" and XYID='"+ params.get("XYID").toString()+"'";
		}
		if (null != params.get("ZYMC")
				&& !"".equals(params.get("ZYMC"))) {
			hql+= "and ZYMC like '%"
					+ params.get("ZYMC").toString() + "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql+= "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
		}
		if (null != params.get("XYMC")
				&& !"".equals(params.get("XYMC"))) {
			hql+= "and XYMC like '%"
					+ params.get("XYMC").toString() + "%'";
		}
		if (null != params.get("ZYDM")
				&& !"".equals(params.get("ZYDM"))) {
			hql+= "and ZYDM like '%"
					+ params.get("ZYDM").toString() + "%'";
		}
		List<ZHUANYE> list = baseDao.findAll(hql,ZHUANYE.class);
		return list;
	}
	@Override
	public List<ZHUANYE> findZHUANYEByXueYuan(String xy_id) {
		String hql = "from ZHUANYE where 1=1";
		if (null != xy_id && !"".equals(xy_id)) {
			hql+=" and XYID='"+xy_id+"'";
		}
		System.out.println("专业service中FINDzhuanyebyxueyuan打印的sql："+hql);
		List<ZHUANYE> list = baseDao.findAll(hql,ZHUANYE.class);
		return list;
	}

}