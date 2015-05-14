package com.hrbsys.tongji.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.CONDITIONS;
import com.hrbsys.bean.MESSAGE;
import com.hrbsys.bean.TONGJI;
import com.hrbsys.tongji.service.TONGJIService;
public class TONGJIServiceImpl implements TONGJIService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addTONGJI(TONGJI y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delTONGJI(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {TONGJI yhz=findTONGJI(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateTONGJI(TONGJI t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TONGJI tmpt = baseDao.findAll("from TONGJI where TONGJI_ID='" + t.getTONGJI_ID() + "'",TONGJI.class).get(0);
   	    tmpt.setXS_CD(t.getXS_CD());
   	    tmpt.setXS_ID(t.getXS_ID());
   	    tmpt.setXS_XM(t.getXS_XM());
   	    tmpt.setQXRS(t.getQXRS());
   	    tmpt.setNJ_ID(t.getNJ_ID());
   	    tmpt.setCDRSBL(t.getCDRSBL());
   	    tmpt.setXS_XH(t.getXS_XH());
   	    tmpt.setZCCXRS(t.getZCCXRS());
   	    tmpt.setJS_JSMC(t.getJS_JSMC());
   	    tmpt.setKSRQ(t.getKSRQ());
   	    tmpt.setXS_QX(t.getXS_QX());
   	    tmpt.setZTRS(t.getZTRS());
   	    tmpt.setNJMC(t.getNJMC());
   	    tmpt.setYSKRS(t.getYSKRS());
   	    tmpt.setJG_JGGH(t.getJG_JGGH());
   	    tmpt.setXS_ZT(t.getXS_ZT());
   	    tmpt.setBJMC(t.getBJMC());
   	    tmpt.setBJ_ID(t.getBJ_ID());
   	    tmpt.setJG_JGMC(t.getJG_JGMC());
   	    tmpt.setKCXX_KCMC(t.getKCXX_KCMC());
   	    tmpt.setSKRQ(t.getSKRQ());
   	    tmpt.setXINGQI(t.getXINGQI());
   	    tmpt.setKCXX_ID(t.getKCXX_ID());
   	    tmpt.setZYMC(t.getZYMC());
   	    tmpt.setZCCXBL(t.getZCCXBL());
   	    tmpt.setXY_ID(t.getXY_ID());
   	    tmpt.setCQQK(t.getCQQK());
   	    tmpt.setZTRSBL(t.getZTRSBL());
   	    tmpt.setZHOU(t.getZHOU());
   	    tmpt.setKS_ID(t.getKS_ID());
   	    tmpt.setXYMC(t.getXYMC());
   	    tmpt.setCDRS(t.getCDRS());
   	    tmpt.setXUEQI_ID(t.getXUEQI_ID());
   	    tmpt.setXS_ZCCX(t.getXS_ZCCX());
   	    tmpt.setJSRQ(t.getJSRQ());
   	    tmpt.setQXRSBL(t.getQXRSBL());
   	    tmpt.setJS_ID(t.getJS_ID());
   	    tmpt.setJG_ID(t.getJG_ID());
   	    tmpt.setZY_ID(t.getZY_ID());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public TONGJI findTONGJI(String t_id) {
		return baseDao.findAll("from TONGJI where TONGJI_ID='" + t_id + "'",TONGJI.class).get(0);
	}

    //findByPage
	@Override
	public List<TONGJI> findTONGJIByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from TONGJI where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("XS_CD") && !"".equals(params.get("XS_CD"))) {
			param = "and XS_CD like '%" + params.get("XS_CD").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XS_ID") && !"".equals(params.get("XS_ID"))) {
			param = "and XS_ID like '%" + params.get("XS_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XS_XM") && !"".equals(params.get("XS_XM"))) {
			param = "and XS_XM like '%" + params.get("XS_XM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("QXRS") && !"".equals(params.get("QXRS"))) {
			param = "and QXRS like '%" + params.get("QXRS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("NJ_ID") && !"".equals(params.get("NJ_ID"))) {
			param = "and NJ_ID like '%" + params.get("NJ_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("CDRSBL") && !"".equals(params.get("CDRSBL"))) {
			param = "and CDRSBL like '%" + params.get("CDRSBL").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XS_XH") && !"".equals(params.get("XS_XH"))) {
			param = "and XS_XH like '%" + params.get("XS_XH").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZCCXRS") && !"".equals(params.get("ZCCXRS"))) {
			param = "and ZCCXRS like '%" + params.get("ZCCXRS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JS_JSMC") && !"".equals(params.get("JS_JSMC"))) {
			param = "and JS_JSMC like '%" + params.get("JS_JSMC").toString()
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
		if (null != params.get("XS_QX") && !"".equals(params.get("XS_QX"))) {
			param = "and XS_QX like '%" + params.get("XS_QX").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZTRS") && !"".equals(params.get("ZTRS"))) {
			param = "and ZTRS like '%" + params.get("ZTRS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("NJMC") && !"".equals(params.get("NJMC"))) {
			param = "and NJMC like '%" + params.get("NJMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("YSKRS") && !"".equals(params.get("YSKRS"))) {
			param = "and YSKRS like '%" + params.get("YSKRS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JG_JGGH") && !"".equals(params.get("JG_JGGH"))) {
			param = "and JG_JGGH like '%" + params.get("JG_JGGH").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XS_ZT") && !"".equals(params.get("XS_ZT"))) {
			param = "and XS_ZT like '%" + params.get("XS_ZT").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("BJMC") && !"".equals(params.get("BJMC"))) {
			param = "and BJMC like '%" + params.get("BJMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("BJ_ID") && !"".equals(params.get("BJ_ID"))) {
			param = "and BJ_ID like '%" + params.get("BJ_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JG_JGMC") && !"".equals(params.get("JG_JGMC"))) {
			param = "and JG_JGMC like '%" + params.get("JG_JGMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KCXX_KCMC") && !"".equals(params.get("KCXX_KCMC"))) {
			param = "and KCXX_KCMC like '%" + params.get("KCXX_KCMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("SKRQ") && !"".equals(params.get("SKRQ"))) {
			param = "and SKRQ like '%" + params.get("SKRQ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XINGQI") && !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%" + params.get("XINGQI").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KCXX_ID") && !"".equals(params.get("KCXX_ID"))) {
			param = "and KCXX_ID like '%" + params.get("KCXX_ID").toString()
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
		if (null != params.get("ZCCXBL") && !"".equals(params.get("ZCCXBL"))) {
			param = "and ZCCXBL like '%" + params.get("ZCCXBL").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XY_ID") && !"".equals(params.get("XY_ID"))) {
			param = "and XY_ID like '%" + params.get("XY_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("CQQK") && !"".equals(params.get("CQQK"))) {
			param = "and CQQK like '%" + params.get("CQQK").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZTRSBL") && !"".equals(params.get("ZTRSBL"))) {
			param = "and ZTRSBL like '%" + params.get("ZTRSBL").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZHOU") && !"".equals(params.get("ZHOU"))) {
			param = "and ZHOU like '%" + params.get("ZHOU").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KS_ID") && !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%" + params.get("KS_ID").toString()
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
		if (null != params.get("CDRS") && !"".equals(params.get("CDRS"))) {
			param = "and CDRS like '%" + params.get("CDRS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XUEQI_ID") && !"".equals(params.get("XUEQI_ID"))) {
			param = "and XUEQI_ID like '%" + params.get("XUEQI_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XS_ZCCX") && !"".equals(params.get("XS_ZCCX"))) {
			param = "and XS_ZCCX like '%" + params.get("XS_ZCCX").toString()
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
		if (null != params.get("QXRSBL") && !"".equals(params.get("QXRSBL"))) {
			param = "and QXRSBL like '%" + params.get("QXRSBL").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%" + params.get("JS_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JG_ID") && !"".equals(params.get("JG_ID"))) {
			param = "and JG_ID like '%" + params.get("JG_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZY_ID") && !"".equals(params.get("ZY_ID"))) {
			param = "and ZY_ID like '%" + params.get("ZY_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<TONGJI> list = baseDao.findByPage(hql, TONGJI.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountTONGJI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from TONGJI where 1=1 ";
		if (null != params.get("XS_CD")
				&& !"".equals(params.get("XS_CD"))) {
			hql += "and XS_CD like '%" + params.get("XS_CD").toString()
					+ "%'";
		}
		if (null != params.get("XS_ID")
				&& !"".equals(params.get("XS_ID"))) {
			hql += "and XS_ID like '%" + params.get("XS_ID").toString()
					+ "%'";
		}
		if (null != params.get("XS_XM")
				&& !"".equals(params.get("XS_XM"))) {
			hql += "and XS_XM like '%" + params.get("XS_XM").toString()
					+ "%'";
		}
		if (null != params.get("QXRS")
				&& !"".equals(params.get("QXRS"))) {
			hql += "and QXRS like '%" + params.get("QXRS").toString()
					+ "%'";
		}
		if (null != params.get("NJ_ID")
				&& !"".equals(params.get("NJ_ID"))) {
			hql += "and NJ_ID like '%" + params.get("NJ_ID").toString()
					+ "%'";
		}
		if (null != params.get("CDRSBL")
				&& !"".equals(params.get("CDRSBL"))) {
			hql += "and CDRSBL like '%" + params.get("CDRSBL").toString()
					+ "%'";
		}
		if (null != params.get("XS_XH")
				&& !"".equals(params.get("XS_XH"))) {
			hql += "and XS_XH like '%" + params.get("XS_XH").toString()
					+ "%'";
		}
		if (null != params.get("ZCCXRS")
				&& !"".equals(params.get("ZCCXRS"))) {
			hql += "and ZCCXRS like '%" + params.get("ZCCXRS").toString()
					+ "%'";
		}
		if (null != params.get("JS_JSMC")
				&& !"".equals(params.get("JS_JSMC"))) {
			hql += "and JS_JSMC like '%" + params.get("JS_JSMC").toString()
					+ "%'";
		}
		if (null != params.get("KSRQ")
				&& !"".equals(params.get("KSRQ"))) {
			hql += "and KSRQ like '%" + params.get("KSRQ").toString()
					+ "%'";
		}
		if (null != params.get("XS_QX")
				&& !"".equals(params.get("XS_QX"))) {
			hql += "and XS_QX like '%" + params.get("XS_QX").toString()
					+ "%'";
		}
		if (null != params.get("ZTRS")
				&& !"".equals(params.get("ZTRS"))) {
			hql += "and ZTRS like '%" + params.get("ZTRS").toString()
					+ "%'";
		}
		if (null != params.get("NJMC")
				&& !"".equals(params.get("NJMC"))) {
			hql += "and NJMC like '%" + params.get("NJMC").toString()
					+ "%'";
		}
		if (null != params.get("YSKRS")
				&& !"".equals(params.get("YSKRS"))) {
			hql += "and YSKRS like '%" + params.get("YSKRS").toString()
					+ "%'";
		}
		if (null != params.get("JG_JGGH")
				&& !"".equals(params.get("JG_JGGH"))) {
			hql += "and JG_JGGH like '%" + params.get("JG_JGGH").toString()
					+ "%'";
		}
		if (null != params.get("XS_ZT")
				&& !"".equals(params.get("XS_ZT"))) {
			hql += "and XS_ZT like '%" + params.get("XS_ZT").toString()
					+ "%'";
		}
		if (null != params.get("BJMC")
				&& !"".equals(params.get("BJMC"))) {
			hql += "and BJMC like '%" + params.get("BJMC").toString()
					+ "%'";
		}
		if (null != params.get("BJ_ID")
				&& !"".equals(params.get("BJ_ID"))) {
			hql += "and BJ_ID like '%" + params.get("BJ_ID").toString()
					+ "%'";
		}
		if (null != params.get("JG_JGMC")
				&& !"".equals(params.get("JG_JGMC"))) {
			hql += "and JG_JGMC like '%" + params.get("JG_JGMC").toString()
					+ "%'";
		}
		if (null != params.get("KCXX_KCMC")
				&& !"".equals(params.get("KCXX_KCMC"))) {
			hql += "and KCXX_KCMC like '%" + params.get("KCXX_KCMC").toString()
					+ "%'";
		}
		if (null != params.get("SKRQ")
				&& !"".equals(params.get("SKRQ"))) {
			hql += "and SKRQ like '%" + params.get("SKRQ").toString()
					+ "%'";
		}
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			hql += "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
		}
		if (null != params.get("KCXX_ID")
				&& !"".equals(params.get("KCXX_ID"))) {
			hql += "and KCXX_ID like '%" + params.get("KCXX_ID").toString()
					+ "%'";
		}
		if (null != params.get("ZYMC")
				&& !"".equals(params.get("ZYMC"))) {
			hql += "and ZYMC like '%" + params.get("ZYMC").toString()
					+ "%'";
		}
		if (null != params.get("ZCCXBL")
				&& !"".equals(params.get("ZCCXBL"))) {
			hql += "and ZCCXBL like '%" + params.get("ZCCXBL").toString()
					+ "%'";
		}
		if (null != params.get("XY_ID")
				&& !"".equals(params.get("XY_ID"))) {
			hql += "and XY_ID like '%" + params.get("XY_ID").toString()
					+ "%'";
		}
		if (null != params.get("CQQK")
				&& !"".equals(params.get("CQQK"))) {
			hql += "and CQQK like '%" + params.get("CQQK").toString()
					+ "%'";
		}
		if (null != params.get("ZTRSBL")
				&& !"".equals(params.get("ZTRSBL"))) {
			hql += "and ZTRSBL like '%" + params.get("ZTRSBL").toString()
					+ "%'";
		}
		if (null != params.get("ZHOU")
				&& !"".equals(params.get("ZHOU"))) {
			hql += "and ZHOU like '%" + params.get("ZHOU").toString()
					+ "%'";
		}
		if (null != params.get("KS_ID")
				&& !"".equals(params.get("KS_ID"))) {
			hql += "and KS_ID like '%" + params.get("KS_ID").toString()
					+ "%'";
		}
		if (null != params.get("XYMC")
				&& !"".equals(params.get("XYMC"))) {
			hql += "and XYMC like '%" + params.get("XYMC").toString()
					+ "%'";
		}
		if (null != params.get("CDRS")
				&& !"".equals(params.get("CDRS"))) {
			hql += "and CDRS like '%" + params.get("CDRS").toString()
					+ "%'";
		}
		if (null != params.get("XUEQI_ID")
				&& !"".equals(params.get("XUEQI_ID"))) {
			hql += "and XUEQI_ID like '%" + params.get("XUEQI_ID").toString()
					+ "%'";
		}
		if (null != params.get("XS_ZCCX")
				&& !"".equals(params.get("XS_ZCCX"))) {
			hql += "and XS_ZCCX like '%" + params.get("XS_ZCCX").toString()
					+ "%'";
		}
		if (null != params.get("JSRQ")
				&& !"".equals(params.get("JSRQ"))) {
			hql += "and JSRQ like '%" + params.get("JSRQ").toString()
					+ "%'";
		}
		if (null != params.get("QXRSBL")
				&& !"".equals(params.get("QXRSBL"))) {
			hql += "and QXRSBL like '%" + params.get("QXRSBL").toString()
					+ "%'";
		}
		if (null != params.get("JS_ID")
				&& !"".equals(params.get("JS_ID"))) {
			hql += "and JS_ID like '%" + params.get("JS_ID").toString()
					+ "%'";
		}
		if (null != params.get("JG_ID")
				&& !"".equals(params.get("JG_ID"))) {
			hql += "and JG_ID like '%" + params.get("JG_ID").toString()
					+ "%'";
		}
		if (null != params.get("ZY_ID")
				&& !"".equals(params.get("ZY_ID"))) {
			hql += "and ZY_ID like '%" + params.get("ZY_ID").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<TONGJI> findTONGJIByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "FROM TONGJI where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("XS_CD")
				&& !"".equals(params.get("XS_CD"))) {
			param = "and XS_CD like '%"
					+ params.get("XS_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_ID")
				&& !"".equals(params.get("XS_ID"))) {
			param = "and XS_ID like '%"
					+ params.get("XS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_XM")
				&& !"".equals(params.get("XS_XM"))) {
			param = "and XS_XM like '%"
					+ params.get("XS_XM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("QXRS")
				&& !"".equals(params.get("QXRS"))) {
			param = "and QXRS like '%"
					+ params.get("QXRS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("NJ_ID")
				&& !"".equals(params.get("NJ_ID"))) {
			param = "and NJ_ID like '%"
					+ params.get("NJ_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("CDRSBL")
				&& !"".equals(params.get("CDRSBL"))) {
			param = "and CDRSBL like '%"
					+ params.get("CDRSBL").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_XH")
				&& !"".equals(params.get("XS_XH"))) {
			param = "and XS_XH like '%"
					+ params.get("XS_XH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZCCXRS")
				&& !"".equals(params.get("ZCCXRS"))) {
			param = "and ZCCXRS like '%"
					+ params.get("ZCCXRS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_JSMC")
				&& !"".equals(params.get("JS_JSMC"))) {
			param = "and JS_JSMC like '%"
					+ params.get("JS_JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSRQ")
				&& !"".equals(params.get("KSRQ"))) {
			param = "and KSRQ like '%"
					+ params.get("KSRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_QX")
				&& !"".equals(params.get("XS_QX"))) {
			param = "and XS_QX like '%"
					+ params.get("XS_QX").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZTRS")
				&& !"".equals(params.get("ZTRS"))) {
			param = "and ZTRS like '%"
					+ params.get("ZTRS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("NJMC")
				&& !"".equals(params.get("NJMC"))) {
			param = "and NJMC like '%"
					+ params.get("NJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("YSKRS")
				&& !"".equals(params.get("YSKRS"))) {
			param = "and YSKRS like '%"
					+ params.get("YSKRS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JG_JGGH")
				&& !"".equals(params.get("JG_JGGH"))) {
			param = "and JG_JGGH like '%"
					+ params.get("JG_JGGH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_ZT")
				&& !"".equals(params.get("XS_ZT"))) {
			param = "and XS_ZT like '%"
					+ params.get("XS_ZT").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BJMC")
				&& !"".equals(params.get("BJMC"))) {
			param = "and BJMC like '%"
					+ params.get("BJMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BJ_ID")
				&& !"".equals(params.get("BJ_ID"))) {
			param = "and BJ_ID like '%"
					+ params.get("BJ_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JG_JGMC")
				&& !"".equals(params.get("JG_JGMC"))) {
			param = "and JG_JGMC like '%"
					+ params.get("JG_JGMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXX_KCMC")
				&& !"".equals(params.get("KCXX_KCMC"))) {
			param = "and KCXX_KCMC like '%"
					+ params.get("KCXX_KCMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("SKRQ")
				&& !"".equals(params.get("SKRQ"))) {
			param = "and SKRQ like '%"
					+ params.get("SKRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%"
					+ params.get("XINGQI").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXX_ID")
				&& !"".equals(params.get("KCXX_ID"))) {
			param = "and KCXX_ID like '%"
					+ params.get("KCXX_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZYMC")
				&& !"".equals(params.get("ZYMC"))) {
			param = "and ZYMC like '%"
					+ params.get("ZYMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZCCXBL")
				&& !"".equals(params.get("ZCCXBL"))) {
			param = "and ZCCXBL like '%"
					+ params.get("ZCCXBL").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XY_ID")
				&& !"".equals(params.get("XY_ID"))) {
			param = "and XY_ID like '%"
					+ params.get("XY_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("CQQK")
				&& !"".equals(params.get("CQQK"))) {
			param = "and CQQK like '%"
					+ params.get("CQQK").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZTRSBL")
				&& !"".equals(params.get("ZTRSBL"))) {
			param = "and ZTRSBL like '%"
					+ params.get("ZTRSBL").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZHOU")
				&& !"".equals(params.get("ZHOU"))) {
			param = "and ZHOU like '%"
					+ params.get("ZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_ID")
				&& !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%"
					+ params.get("KS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XYMC")
				&& !"".equals(params.get("XYMC"))) {
			param = "and XYMC like '%"
					+ params.get("XYMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("CDRS")
				&& !"".equals(params.get("CDRS"))) {
			param = "and CDRS like '%"
					+ params.get("CDRS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XUEQI_ID")
				&& !"".equals(params.get("XUEQI_ID"))) {
			param = "and XUEQI_ID like '%"
					+ params.get("XUEQI_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_ZCCX")
				&& !"".equals(params.get("XS_ZCCX"))) {
			param = "and XS_ZCCX like '%"
					+ params.get("XS_ZCCX").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSRQ")
				&& !"".equals(params.get("JSRQ"))) {
			param = "and JSRQ like '%"
					+ params.get("JSRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("QXRSBL")
				&& !"".equals(params.get("QXRSBL"))) {
			param = "and QXRSBL like '%"
					+ params.get("QXRSBL").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID")
				&& !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%"
					+ params.get("JS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JG_ID")
				&& !"".equals(params.get("JG_ID"))) {
			param = "and JG_ID like '%"
					+ params.get("JG_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZY_ID")
				&& !"".equals(params.get("ZY_ID"))) {
			param = "and ZY_ID like '%"
					+ params.get("ZY_ID").toString() + "%'";
			params2.add(param);
		}
		List<TONGJI> list = baseDao.findAll(hql,TONGJI.class);
		return list;
	}
	
	/**
	 * 添加查询条件配置(添加)
	 */
	@Override
	public boolean addCondition(CONDITIONS con) {
		//获取数据的总条数
		String hql="select count(*) from CONDITIONS";
		List list=baseDao.findAll(hql, CONDITIONS.class);
		Long count = (Long) list.get(0);
		//判断数据的条数是否大于10
		if (count.intValue()<10) {
			//如果数据的条数不大于10进行保存
			return baseDao.save(con);
		}
		//如果数据的条数在于10不让其进行保存
		return false;
	}
	
	/**
	 * 删除查询条件配置(删除)
	 */
	@Override
	public boolean delCondition(String id) {
		CONDITIONS con=baseDao.findAll("FROM CONDITIONS where ID='" + id + "'",CONDITIONS.class).get(0);
		//先查询看看这条数据存在不
		if (con != null) {
			//如果数据存在就进行删除
			return baseDao.delete(con);
		}
		return false;
	}
	/**
	 *查询出所有的查询配置条件
	 */
	@Override
	public List<CONDITIONS> findAllConditionsConfiguration(Map<String, String> params,String order,String sort) {
		//查询语句
		String hql="from CONDITIONS where 1=1 ";
		if (params.get("id")!=null&&!"".equals(params.get("id"))) {
			hql+=" and id='"+params.get("id").toString()+"'";
		}
		//进行判断获取的数据集合是否为空
		if (baseDao.findAll(hql, CONDITIONS.class)!=null) {
			//数据集合返回
			return baseDao.findAll(hql, CONDITIONS.class);
		}
		//没有数据时，返回空
		return null;
	}

}