package com.hrbsys.xueqi.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.xueqi.service.XUEQIService;
public class XUEQIServiceImpl implements XUEQIService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addXUEQI(XUEQI y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delXUEQI(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {XUEQI yhz=findXUEQI(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateXUEQI(XUEQI t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		XUEQI tmpt = baseDao.findAll("from XUEQI where XQ_ID='" + t.getXQ_ID() + "'",XUEQI.class).get(0);
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setKSRQ(t.getKSRQ());
   	    tmpt.setMS(t.getMS());
   	    tmpt.setJSRQ(t.getJSRQ());
   	    tmpt.setXQMC(t.getXQMC());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public XUEQI findXUEQI(String t_id) {
		List<XUEQI> allxq=baseDao.findAll("from XUEQI where XQ_ID='" + t_id + "'",XUEQI.class);
		if(allxq.size()>0){
			return allxq.get(0);	
		}
		return null;
	}

    //findByPage
	@Override
	public List<XUEQI> findXUEQIByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from XUEQI where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KSRQ") && !"".equals(params.get("KSRQ"))) {
			param = "and KSRQ like '%" + params.get("KSRQ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JSRQ") && !"".equals(params.get("JSRQ"))) {
			param = "and JSRQ like '%" + params.get("JSRQ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XQMC") && !"".equals(params.get("XQMC"))) {
			param = "and XQMC like '%" + params.get("XQMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<XUEQI> list = baseDao.findByPage(hql, XUEQI.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountXUEQI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from XUEQI where 1=1 ";
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("KSRQ")
				&& !"".equals(params.get("KSRQ"))) {
			hql += "and KSRQ like '%" + params.get("KSRQ").toString()
					+ "%'";
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("JSRQ")
				&& !"".equals(params.get("JSRQ"))) {
			hql += "and JSRQ like '%" + params.get("JSRQ").toString()
					+ "%'";
		}
		if (null != params.get("XQMC")
				&& !"".equals(params.get("XQMC"))) {
			hql += "and XQMC like '%" + params.get("XQMC").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<XUEQI> findXUEQIByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from XUEQI where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSRQ")
				&& !"".equals(params.get("KSRQ"))) {
			param = "and KSRQ like '%"
					+ params.get("KSRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSRQ")
				&& !"".equals(params.get("JSRQ"))) {
			param = "and JSRQ like '%"
					+ params.get("JSRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XQMC")
				&& !"".equals(params.get("XQMC"))) {
			param = "and XQMC like '%"
					+ params.get("XQMC").toString() + "%'";
			params2.add(param);
		}
		List<XUEQI> list = baseDao.findAll(hql,XUEQI.class);
		return list;
	}
	
    //findbypagelike
	@Override
	public List<XUEQI> findXUEQIByPageAppforKCB(HashMap<String, String> params, String order, String sort) {
		String hql = "from XUEQI where to_date(JSRQ,'yyyy-mm-dd')>sysdate ";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSRQ")
				&& !"".equals(params.get("KSRQ"))) {
			param = "and KSRQ like '%"
					+ params.get("KSRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSRQ")
				&& !"".equals(params.get("JSRQ"))) {
			param = "and JSRQ like '%"
					+ params.get("JSRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XQMC")
				&& !"".equals(params.get("XQMC"))) {
			param = "and XQMC like '%"
					+ params.get("XQMC").toString() + "%'";
			params2.add(param);
		}
		List<XUEQI> list = baseDao.findAll(hql,XUEQI.class);
		return list;
	}
	
	@Override
	public XUEQI getCurrentXUEQI() {
		String hql="from XUEQI WHERE sysdate>=to_date(KSRQ,'yyyy-mm-dd') and sysdate<=to_date(JSRQ,'yyyy-mm-dd')";
		List<XUEQI> list = baseDao.findAll(hql,XUEQI.class);
		if(list.size()>0){
			return list.get(0);
		}
		System.out.println("XUEQIService中打印hql为:："+hql);
		return null;
	}
	@Override
	public boolean isInTerm(String riqi) {
		String hql=" from XUEQI where  to_date(JSRQ,'yyyy-mm-dd')>=to_date('"+riqi+"','yyyy-mm-dd') and to_date(KSRQ,'yyyy-mm-dd')<=to_date('"+riqi+"','yyyy-mm-dd')";
		List<XUEQI> list = baseDao.findAll(hql,XUEQI.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}
}