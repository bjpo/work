package com.hrbsys.zwlr.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.ZWLRLBGX;
import com.hrbsys.zwlr.service.ZWLRLBGXService;
public class ZWLRLBGXServiceImpl implements ZWLRLBGXService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addZWLRLBGX(ZWLRLBGX y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delZWLRLBGX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {ZWLRLBGX yhz=findZWLRLBGX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateZWLRLBGX(ZWLRLBGX t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ZWLRLBGX tmpt = baseDao.findAll("from ZWLRLBGX where ZWLRGX_ID='" + t.getZWLRGX_ID() + "'",ZWLRLBGX.class).get(0);
   	    tmpt.setZWLRBIAO_TMP4(t.getZWLRBIAO_TMP4());
   	    tmpt.setZWLRBIAO_TMP3(t.getZWLRBIAO_TMP3());
   	    tmpt.setZWLRBIAO_TMP2(t.getZWLRBIAO_TMP2());
   	    tmpt.setZWLRBIAO_TMP1(t.getZWLRBIAO_TMP1());
   	    tmpt.setZWLRBIAO(t.getZWLRBIAO());
   	    tmpt.setZWLR_MC(t.getZWLR_MC());
   	    tmpt.setZWLRBIAO_ID(t.getZWLRBIAO_ID());
   	    tmpt.setZWLRBIAO_MC(t.getZWLRBIAO_MC());
   	    tmpt.setZWLRBIAO_TMP9(t.getZWLRBIAO_TMP9());
   	    tmpt.setZWLRBIAO_HM(t.getZWLRBIAO_HM());
   	    tmpt.setZWLRBIAO_TMP8(t.getZWLRBIAO_TMP8());
   	    tmpt.setZWLRBIAO_TMP7(t.getZWLRBIAO_TMP7());
   	    tmpt.setZWLRBIAO_TMP6(t.getZWLRBIAO_TMP6());
   	    tmpt.setPAIXU(t.getPAIXU());
   	    tmpt.setZWLRBIAO_TMP5(t.getZWLRBIAO_TMP5());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public ZWLRLBGX findZWLRLBGX(String t_id) {
		return baseDao.findAll("from ZWLRLBGX where ZWLRGX_ID='" + t_id + "'",ZWLRLBGX.class).get(0);
	}

    //findByPage
	@Override
	public List<ZWLRLBGX> findZWLRLBGXByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from ZWLRLBGX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("ZWLRBIAO_TMP4") && !"".equals(params.get("ZWLRBIAO_TMP4"))) {
			param = "and ZWLRBIAO_TMP4 like '%" + params.get("ZWLRBIAO_TMP4").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP3") && !"".equals(params.get("ZWLRBIAO_TMP3"))) {
			param = "and ZWLRBIAO_TMP3 like '%" + params.get("ZWLRBIAO_TMP3").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP2") && !"".equals(params.get("ZWLRBIAO_TMP2"))) {
			param = "and ZWLRBIAO_TMP2 like '%" + params.get("ZWLRBIAO_TMP2").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP1") && !"".equals(params.get("ZWLRBIAO_TMP1"))) {
			param = "and ZWLRBIAO_TMP1 like '%" + params.get("ZWLRBIAO_TMP1").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO") && !"".equals(params.get("ZWLRBIAO"))) {
			param = "and ZWLRBIAO like '%" + params.get("ZWLRBIAO").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLR_MC") && !"".equals(params.get("ZWLR_MC"))) {
			param = "and ZWLR_MC like '%" + params.get("ZWLR_MC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_ID") && !"".equals(params.get("ZWLRBIAO_ID"))) {
			param = "and ZWLRBIAO_ID like '%" + params.get("ZWLRBIAO_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_MC") && !"".equals(params.get("ZWLRBIAO_MC"))) {
			param = "and ZWLRBIAO_MC like '%" + params.get("ZWLRBIAO_MC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP9") && !"".equals(params.get("ZWLRBIAO_TMP9"))) {
			param = "and ZWLRBIAO_TMP9 like '%" + params.get("ZWLRBIAO_TMP9").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_HM") && !"".equals(params.get("ZWLRBIAO_HM"))) {
			param = "and ZWLRBIAO_HM like '%" + params.get("ZWLRBIAO_HM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP8") && !"".equals(params.get("ZWLRBIAO_TMP8"))) {
			param = "and ZWLRBIAO_TMP8 like '%" + params.get("ZWLRBIAO_TMP8").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP7") && !"".equals(params.get("ZWLRBIAO_TMP7"))) {
			param = "and ZWLRBIAO_TMP7 like '%" + params.get("ZWLRBIAO_TMP7").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP6") && !"".equals(params.get("ZWLRBIAO_TMP6"))) {
			param = "and ZWLRBIAO_TMP6 like '%" + params.get("ZWLRBIAO_TMP6").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZWLRBIAO_TMP5") && !"".equals(params.get("ZWLRBIAO_TMP5"))) {
			param = "and ZWLRBIAO_TMP5 like '%" + params.get("ZWLRBIAO_TMP5").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<ZWLRLBGX> list = baseDao.findByPage(hql, ZWLRLBGX.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountZWLRLBGX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from ZWLRLBGX where 1=1 ";
		if (null != params.get("ZWLRBIAO_TMP4")
				&& !"".equals(params.get("ZWLRBIAO_TMP4"))) {
			hql += "and ZWLRBIAO_TMP4 like '%" + params.get("ZWLRBIAO_TMP4").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP3")
				&& !"".equals(params.get("ZWLRBIAO_TMP3"))) {
			hql += "and ZWLRBIAO_TMP3 like '%" + params.get("ZWLRBIAO_TMP3").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP2")
				&& !"".equals(params.get("ZWLRBIAO_TMP2"))) {
			hql += "and ZWLRBIAO_TMP2 like '%" + params.get("ZWLRBIAO_TMP2").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP1")
				&& !"".equals(params.get("ZWLRBIAO_TMP1"))) {
			hql += "and ZWLRBIAO_TMP1 like '%" + params.get("ZWLRBIAO_TMP1").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO")
				&& !"".equals(params.get("ZWLRBIAO"))) {
			hql += "and ZWLRBIAO like '%" + params.get("ZWLRBIAO").toString()
					+ "%'";
		}
		if (null != params.get("ZWLR_MC")
				&& !"".equals(params.get("ZWLR_MC"))) {
			hql += "and ZWLR_MC like '%" + params.get("ZWLR_MC").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_ID")
				&& !"".equals(params.get("ZWLRBIAO_ID"))) {
			hql += "and ZWLRBIAO_ID like '%" + params.get("ZWLRBIAO_ID").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_MC")
				&& !"".equals(params.get("ZWLRBIAO_MC"))) {
			hql += "and ZWLRBIAO_MC like '%" + params.get("ZWLRBIAO_MC").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP9")
				&& !"".equals(params.get("ZWLRBIAO_TMP9"))) {
			hql += "and ZWLRBIAO_TMP9 like '%" + params.get("ZWLRBIAO_TMP9").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_HM")
				&& !"".equals(params.get("ZWLRBIAO_HM"))) {
			hql += "and ZWLRBIAO_HM like '%" + params.get("ZWLRBIAO_HM").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP8")
				&& !"".equals(params.get("ZWLRBIAO_TMP8"))) {
			hql += "and ZWLRBIAO_TMP8 like '%" + params.get("ZWLRBIAO_TMP8").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP7")
				&& !"".equals(params.get("ZWLRBIAO_TMP7"))) {
			hql += "and ZWLRBIAO_TMP7 like '%" + params.get("ZWLRBIAO_TMP7").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP6")
				&& !"".equals(params.get("ZWLRBIAO_TMP6"))) {
			hql += "and ZWLRBIAO_TMP6 like '%" + params.get("ZWLRBIAO_TMP6").toString()
					+ "%'";
		}
		if (null != params.get("ZWLRBIAO_TMP5")
				&& !"".equals(params.get("ZWLRBIAO_TMP5"))) {
			hql += "and ZWLRBIAO_TMP5 like '%" + params.get("ZWLRBIAO_TMP5").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<ZWLRLBGX> findZWLRLBGXByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from ZWLRLBGX where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("ZWLRBIAO_TMP4")
				&& !"".equals(params.get("ZWLRBIAO_TMP4"))) {
			param = "and ZWLRBIAO_TMP4 like '%"
					+ params.get("ZWLRBIAO_TMP4").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP3")
				&& !"".equals(params.get("ZWLRBIAO_TMP3"))) {
			param = "and ZWLRBIAO_TMP3 like '%"
					+ params.get("ZWLRBIAO_TMP3").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP2")
				&& !"".equals(params.get("ZWLRBIAO_TMP2"))) {
			param = "and ZWLRBIAO_TMP2 like '%"
					+ params.get("ZWLRBIAO_TMP2").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP1")
				&& !"".equals(params.get("ZWLRBIAO_TMP1"))) {
			param = "and ZWLRBIAO_TMP1 like '%"
					+ params.get("ZWLRBIAO_TMP1").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO")
				&& !"".equals(params.get("ZWLRBIAO"))) {
			param = "and ZWLRBIAO like '%"
					+ params.get("ZWLRBIAO").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLR_MC")
				&& !"".equals(params.get("ZWLR_MC"))) {
			param = "and ZWLR_MC like '%"
					+ params.get("ZWLR_MC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_ID")
				&& !"".equals(params.get("ZWLRBIAO_ID"))) {
			param = "and ZWLRBIAO_ID like '%"
					+ params.get("ZWLRBIAO_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_MC")
				&& !"".equals(params.get("ZWLRBIAO_MC"))) {
			param = "and ZWLRBIAO_MC like '%"
					+ params.get("ZWLRBIAO_MC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP9")
				&& !"".equals(params.get("ZWLRBIAO_TMP9"))) {
			param = "and ZWLRBIAO_TMP9 like '%"
					+ params.get("ZWLRBIAO_TMP9").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_HM")
				&& !"".equals(params.get("ZWLRBIAO_HM"))) {
			param = "and ZWLRBIAO_HM like '%"
					+ params.get("ZWLRBIAO_HM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP8")
				&& !"".equals(params.get("ZWLRBIAO_TMP8"))) {
			param = "and ZWLRBIAO_TMP8 like '%"
					+ params.get("ZWLRBIAO_TMP8").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP7")
				&& !"".equals(params.get("ZWLRBIAO_TMP7"))) {
			param = "and ZWLRBIAO_TMP7 like '%"
					+ params.get("ZWLRBIAO_TMP7").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP6")
				&& !"".equals(params.get("ZWLRBIAO_TMP6"))) {
			param = "and ZWLRBIAO_TMP6 like '%"
					+ params.get("ZWLRBIAO_TMP6").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRBIAO_TMP5")
				&& !"".equals(params.get("ZWLRBIAO_TMP5"))) {
			param = "and ZWLRBIAO_TMP5 like '%"
					+ params.get("ZWLRBIAO_TMP5").toString() + "%'";
			params2.add(param);
		}
		List<ZWLRLBGX> list = baseDao.findAll(hql,ZWLRLBGX.class);
		return list;
	}

}