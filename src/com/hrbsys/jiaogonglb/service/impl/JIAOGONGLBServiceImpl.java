package com.hrbsys.jiaogonglb.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.JIAOGONGLB;
import com.hrbsys.jiaogonglb.service.JIAOGONGLBService;
public class JIAOGONGLBServiceImpl implements JIAOGONGLBService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addJIAOGONGLB(JIAOGONGLB y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delJIAOGONGLB(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {JIAOGONGLB yhz=findJIAOGONGLB(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateJIAOGONGLB(JIAOGONGLB t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JIAOGONGLB tmpt = baseDao.findAll("from JIAOGONGLB where JIAOGONGLB_ID='" + t.getJIAOGONGLB_ID() + "'",JIAOGONGLB.class).get(0);
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setMS(t.getMS());
   	    tmpt.setTMP6(t.getTMP6());
   	    tmpt.setTMP5(t.getTMP5());
   	    tmpt.setTMP4(t.getTMP4());
   	    tmpt.setTMP3(t.getTMP3());
   	    tmpt.setTMP2(t.getTMP2());
   	    tmpt.setTMP1(t.getTMP1());
   	    tmpt.setJIAOGONGLBMC(t.getJIAOGONGLBMC());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public JIAOGONGLB findJIAOGONGLB(String t_id) {
		List<JIAOGONGLB> all=baseDao.findAll("from JIAOGONGLB where JIAOGONGLB_ID='" + t_id + "'",JIAOGONGLB.class);
		if(all.size()>0){
			return all.get(0);
		}
		return null;
	}

    //findByPage
	@Override
	public List<JIAOGONGLB> findJIAOGONGLBByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from JIAOGONGLB where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString()
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
		if (null != params.get("TMP6") && !"".equals(params.get("TMP6"))) {
			param = "and TMP6 like '%" + params.get("TMP6").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("TMP5") && !"".equals(params.get("TMP5"))) {
			param = "and TMP5 like '%" + params.get("TMP5").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("TMP4") && !"".equals(params.get("TMP4"))) {
			param = "and TMP4 like '%" + params.get("TMP4").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("TMP3") && !"".equals(params.get("TMP3"))) {
			param = "and TMP3 like '%" + params.get("TMP3").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("TMP2") && !"".equals(params.get("TMP2"))) {
			param = "and TMP2 like '%" + params.get("TMP2").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("TMP1") && !"".equals(params.get("TMP1"))) {
			param = "and TMP1 like '%" + params.get("TMP1").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JIAOGONGLBMC") && !"".equals(params.get("JIAOGONGLBMC"))) {
			param = "and JIAOGONGLBMC like '%" + params.get("JIAOGONGLBMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<JIAOGONGLB> list = baseDao.findByPage(hql, JIAOGONGLB.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountJIAOGONGLB(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JIAOGONGLB where 1=1 ";
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
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
		if (null != params.get("JIAOGONGLBMC")
				&& !"".equals(params.get("JIAOGONGLBMC"))) {
			hql += "and JIAOGONGLBMC like '%" + params.get("JIAOGONGLBMC").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<JIAOGONGLB> findJIAOGONGLBByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from JIAOGONGLB where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP6")
				&& !"".equals(params.get("TMP6"))) {
			param = "and TMP6 like '%"
					+ params.get("TMP6").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP5")
				&& !"".equals(params.get("TMP5"))) {
			param = "and TMP5 like '%"
					+ params.get("TMP5").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP4")
				&& !"".equals(params.get("TMP4"))) {
			param = "and TMP4 like '%"
					+ params.get("TMP4").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP3")
				&& !"".equals(params.get("TMP3"))) {
			param = "and TMP3 like '%"
					+ params.get("TMP3").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP2")
				&& !"".equals(params.get("TMP2"))) {
			param = "and TMP2 like '%"
					+ params.get("TMP2").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP1")
				&& !"".equals(params.get("TMP1"))) {
			param = "and TMP1 like '%"
					+ params.get("TMP1").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JIAOGONGLBMC")
				&& !"".equals(params.get("JIAOGONGLBMC"))) {
			param = "and JIAOGONGLBMC like '%"
					+ params.get("JIAOGONGLBMC").toString() + "%'";
			params2.add(param);
		}
		List<JIAOGONGLB> list = baseDao.findAll(hql,JIAOGONGLB.class);
		return list;
	}

}