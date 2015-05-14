package com.hrbsys.kechengxx.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.KECHENGXX;
import com.hrbsys.kechengxx.service.KECHENGXXService;
public class KECHENGXXServiceImpl implements KECHENGXXService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addKECHENGXX(KECHENGXX y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delKECHENGXX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {KECHENGXX yhz=findKECHENGXX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateKECHENGXX(KECHENGXX t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		KECHENGXX tmpt = baseDao.findAll("from KECHENGXX where KECHENGXX_ID='" + t.getKECHENGXX_ID() + "'",KECHENGXX.class).get(0);
		
   	    tmpt.setKHFS(t.getKHFS());
   	    tmpt.setKECHENGMC(t.getKECHENGMC());
   	    tmpt.setKSXQ(t.getKSXQ());
   	    tmpt.setXSFP_SYSJ(t.getXSFP_SYSJ());
   	    tmpt.setYXXF(t.getYXXF());
   	    tmpt.setZYMC(t.getZYMC());
   	    tmpt.setTMP6(t.getTMP6());
   	    tmpt.setKSXF(t.getKSXF());
   	    tmpt.setTMP5(t.getTMP5());
   	    tmpt.setTMP4(t.getTMP4());
   	    tmpt.setTMP3(t.getTMP3());
   	    tmpt.setTMP2(t.getTMP2());
   	    tmpt.setTMP1(t.getTMP1());
   	    tmpt.setKECHENGXXLB_ID(t.getKECHENGXXLB_ID());
   	    tmpt.setMS(t.getMS());
   	    tmpt.setKECHENGXXLBMC(t.getKECHENGXXLBMC());
   	    tmpt.setXSFP_LLJX(t.getXSFP_LLJX());
   	    tmpt.setZY_ID(t.getZY_ID());
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setKECHENGDM(t.getKECHENGDM());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public KECHENGXX findKECHENGXX(String t_id) {
		return baseDao.findAll("from KECHENGXX where KECHENGXX_ID='" + t_id + "'",KECHENGXX.class).get(0);
	}

    //findByPage
	@Override
	public List<KECHENGXX> findKECHENGXXByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from KECHENGXX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KHFS") && !"".equals(params.get("KHFS"))) {
			param = "and KHFS like '%" + params.get("KHFS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KECHENGMC") && !"".equals(params.get("KECHENGMC"))) {
			param = "and KECHENGMC like '%" + params.get("KECHENGMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KSXQ") && !"".equals(params.get("KSXQ"))) {
			param = "and KSXQ like '%" + params.get("KSXQ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XSFP_SYSJ") && !"".equals(params.get("XSFP_SYSJ"))) {
			param = "and XSFP_SYSJ like '%" + params.get("XSFP_SYSJ").toString()
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
		if (null != params.get("KECHENGXXLB_ID") && !"".equals(params.get("KECHENGXXLB_ID"))) {
			param = "and KECHENGXXLB_ID like '%" + params.get("KECHENGXXLB_ID").toString()
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
		if (null != params.get("KECHENGXXLBMC") && !"".equals(params.get("KECHENGXXLBMC"))) {
			param = "and KECHENGXXLBMC like '%" + params.get("KECHENGXXLBMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XSFP_LLJX") && !"".equals(params.get("XSFP_LLJX"))) {
			param = "and XSFP_LLJX like '%" + params.get("XSFP_LLJX").toString()
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
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KECHENGDM") && !"".equals(params.get("KECHENGDM"))) {
			param = "and KECHENGDM like '%" + params.get("KECHENGDM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<KECHENGXX> list = baseDao.findByPage(hql, KECHENGXX.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountKECHENGXX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from KECHENGXX where 1=1 ";
		if (null != params.get("KHFS")
				&& !"".equals(params.get("KHFS"))) {
			hql += "and KHFS like '%" + params.get("KHFS").toString()
					+ "%'";
		}
		if (null != params.get("KECHENGMC")
				&& !"".equals(params.get("KECHENGMC"))) {
			hql += "and KECHENGMC like '%" + params.get("KECHENGMC").toString()
					+ "%'";
		}
		if (null != params.get("KSXQ")
				&& !"".equals(params.get("KSXQ"))) {
			hql += "and KSXQ like '%" + params.get("KSXQ").toString()
					+ "%'";
		}
		if (null != params.get("XSFP_SYSJ")
				&& !"".equals(params.get("XSFP_SYSJ"))) {
			hql += "and XSFP_SYSJ like '%" + params.get("XSFP_SYSJ").toString()
					+ "%'";
		}
		if (null != params.get("ZYMC")
				&& !"".equals(params.get("ZYMC"))) {
			hql += "and ZYMC like '%" + params.get("ZYMC").toString()
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
		if (null != params.get("KECHENGXXLB_ID")
				&& !"".equals(params.get("KECHENGXXLB_ID"))) {
			hql += "and KECHENGXXLB_ID like '%" + params.get("KECHENGXXLB_ID").toString()
					+ "%'";
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("KECHENGXXLBMC")
				&& !"".equals(params.get("KECHENGXXLBMC"))) {
			hql += "and KECHENGXXLBMC like '%" + params.get("KECHENGXXLBMC").toString()
					+ "%'";
		}
		if (null != params.get("XSFP_LLJX")
				&& !"".equals(params.get("XSFP_LLJX"))) {
			hql += "and XSFP_LLJX like '%" + params.get("XSFP_LLJX").toString()
					+ "%'";
		}
		if (null != params.get("ZY_ID")
				&& !"".equals(params.get("ZY_ID"))) {
			hql += "and ZY_ID like '%" + params.get("ZY_ID").toString()
					+ "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("KECHENGDM")
				&& !"".equals(params.get("KECHENGDM"))) {
			hql += "and KECHENGDM like '%" + params.get("KECHENGDM").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<KECHENGXX> findKECHENGXXByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from KECHENGXX where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KHFS")
				&& !"".equals(params.get("KHFS"))) {
			param = "and KHFS like '%"
					+ params.get("KHFS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KECHENGMC")
				&& !"".equals(params.get("KECHENGMC"))) {
			param = "and KECHENGMC like '%"
					+ params.get("KECHENGMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSXQ")
				&& !"".equals(params.get("KSXQ"))) {
			param = "and KSXQ like '%"
					+ params.get("KSXQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XSFP_SYSJ")
				&& !"".equals(params.get("XSFP_SYSJ"))) {
			param = "and XSFP_SYSJ like '%"
					+ params.get("XSFP_SYSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZYMC")
				&& !"".equals(params.get("ZYMC"))) {
			param = "and ZYMC like '%"
					+ params.get("ZYMC").toString() + "%'";
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
		if (null != params.get("KECHENGXXLB_ID")
				&& !"".equals(params.get("KECHENGXXLB_ID"))) {
			param = "and KECHENGXXLB_ID like '%"
					+ params.get("KECHENGXXLB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KECHENGXXLBMC")
				&& !"".equals(params.get("KECHENGXXLBMC"))) {
			param = "and KECHENGXXLBMC like '%"
					+ params.get("KECHENGXXLBMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XSFP_LLJX")
				&& !"".equals(params.get("XSFP_LLJX"))) {
			param = "and XSFP_LLJX like '%"
					+ params.get("XSFP_LLJX").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZY_ID")
				&& !"".equals(params.get("ZY_ID"))) {
			param = "and ZY_ID like '%"
					+ params.get("ZY_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KECHENGDM")
				&& !"".equals(params.get("KECHENGDM"))) {
			param = "and KECHENGDM like '%"
					+ params.get("KECHENGDM").toString() + "%'";
			params2.add(param);
		}
		List<KECHENGXX> list = baseDao.findAll(hql,KECHENGXX.class);
		return list;
	}

}