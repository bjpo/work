package com.hrbsys.jiaoshi.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.FANGJIAN;
import com.hrbsys.bean.JIAOSHI;
import com.hrbsys.jiaoshi.JIAOSHIService;
public class JIAOSHIServiceImpl implements JIAOSHIService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addJIAOSHI(JIAOSHI y) {
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delJIAOSHI(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			JIAOSHI yhz=findJIAOSHI(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateJIAOSHI(JIAOSHI t) {
		return baseDao.update(t);
	}

    //findById
	@Override
	public JIAOSHI findJIAOSHI(String t_id) {
		return baseDao.findAll("from JIAOSHI where JS_ID='" + t_id + "'",JIAOSHI.class).get(0);
	}

    //findByPage
	@Override
	public List<JIAOSHI> findJIAOSHIByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from JIAOSHI where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = " and JSMC like '%" + params.get("JSMC").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("FJMC") && !"".equals(params.get("FJMC"))) {
			param = " and FJMC like '%" + params.get("FJMC").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("FJID") && !"".equals(params.get("FJID"))) {
			param = " and FJID like '%" + params.get("FJID").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("ISDMT") && !"".equals(params.get("ISDMT"))) {
			param = " and ISDMT like '%" + params.get("ISDMT").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("FJDM") && !"".equals(params.get("FJDM"))) {
			param = " and FJDM like '%" + params.get("FJDM").toString()
				+ "%'";
			params2.add(param);
		}
		List<JIAOSHI> list = baseDao.findByPage(hql, JIAOSHI.class, start,number, params2, order, sort);
		// if(0 == list.size()){
		// return null;
		// }
		return list;
	}

    //getCount
	@Override
	public int getCountJIAOSHI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JIAOSHI where 1=1 ";
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("TMP6")
				&& !"".equals(params.get("TMP6"))) {
			hql += "and TMP6 like '%" + params.get("TMP6").toString()
					+ "%'";
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			hql += "and JSMC like '%" + params.get("JSMC").toString()
					+ "%'";
		}
		if (null != params.get("TMP5")
				&& !"".equals(params.get("TMP5"))) {
			hql += "and TMP5 like '%" + params.get("TMP5").toString()
					+ "%'";
		}
		if (null != params.get("TMP4")
				&& !"".equals(params.get("TMP4"))) {
			hql += "and TMP4 like '%" + params.get("TMP4").toString()
					+ "%'";
		}
		if (null != params.get("FJMC")
				&& !"".equals(params.get("FJMC"))) {
			hql += "and FJMC like '%" + params.get("FJMC").toString()
					+ "%'";
		}
		if (null != params.get("TMP3")
				&& !"".equals(params.get("TMP3"))) {
			hql += "and TMP3 like '%" + params.get("TMP3").toString()
					+ "%'";
		}
		if (null != params.get("TMP2")
				&& !"".equals(params.get("TMP2"))) {
			hql += "and TMP2 like '%" + params.get("TMP2").toString()
					+ "%'";
		}
		if (null != params.get("TMP1")
				&& !"".equals(params.get("TMP1"))) {
			hql += "and TMP1 like '%" + params.get("TMP1").toString()
					+ "%'";
		}
		if (null != params.get("FJID")
				&& !"".equals(params.get("FJID"))) {
			hql += "and FJID like '%" + params.get("FJID").toString()
					+ "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("ISDMT")
				&& !"".equals(params.get("ISDMT"))) {
			hql += "and ISDMT like '%" + params.get("ISDMT").toString()
					+ "%'";
		}
		if (null != params.get("FJDM")
				&& !"".equals(params.get("FJDM"))) {
			hql += "and FJDM like '%" + params.get("FJDM").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<JIAOSHI> findJIAOSHIByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from JIAOSHI where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			param = " and JSMC like '%"
					+ params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("FJMC")
				&& !"".equals(params.get("FJMC"))) {
			param = " and FJMC like '%"
					+ params.get("FJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("FJID")
				&& !"".equals(params.get("FJID"))) {
			param = " and FJID like '%"
					+ params.get("FJID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ISDMT")
				&& !"".equals(params.get("ISDMT"))) {
			param = " and ISDMT like '%"
					+ params.get("ISDMT").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("FJDM")
				&& !"".equals(params.get("FJDM"))) {
			param = " and FJDM like '%"
					+ params.get("FJDM").toString() + "%'";
			params2.add(param);
		}
		/*于洋 2014-05-29 18:39 分改*/
//		List<JIAOSHI> list = baseDao.findAll(hql, JIAOSHI.class, params2);
		List<JIAOSHI> list = baseDao.findAll(hql, JIAOSHI.class);
		if(0 == list.size()){
			return null;
		}
		return list;
	}
	@Override
	public List<FANGJIAN> findFANGJIANByPageApp() {
		String hql = "from FANGJIAN where 1=1";
		List<FANGJIAN> list = baseDao.findAll(hql,FANGJIAN.class);
		if(0 == list.size()){
			return null;
		}
		return list;
	}
	@Override
	public FANGJIAN findFANGJIAN(String id) {
		String hql = "from FANGJIAN where 1=1 and FJ_ID ='"+id+"'";
		List<FANGJIAN> list = baseDao.findAll(hql,FANGJIAN.class);
		if(0 == list.size()){
			return null;
		}
		return list.get(0);
	}
	@Override
	public JIAOSHI findJIAOSHIByName(String name) {
		String hql = "from JIAOSHI where 1=1 and JSMC ='"+name+"'";
		List<JIAOSHI> list = baseDao.findAll(hql,JIAOSHI.class);
		if(0 == list.size()){
			return null;
		}
		return list.get(0);
	}
	@Override
	public JIAOSHI findJIAOSHIByFJID(String fjid) {
		String hql = "from JIAOSHI where 1=1 and FJ_ID ='"+fjid+"'";
		List<JIAOSHI> list = baseDao.findAll(hql,JIAOSHI.class);
		if(0 == list.size()){
			return null;
		}
		return list.get(0);
	}

}