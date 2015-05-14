package com.hrbsys.qjxx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.QJXX;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.qjxx.service.QJXXService;

public class QJXXServiceImpl implements QJXXService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addQJXX(QJXX y) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		y.setDJRQ(df.format(d).toString());
		y.setXGRQ(df.format(d).toString());
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delQJXX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			QJXX yhz = findQJXX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// update
	@Override
	public boolean updateQJXX(QJXX t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(t);
	}

	// findById
	@Override
	public QJXX findQJXX(String t_id) {
		List<QJXX> list = baseDao.findAll("from QJXX where QJXX_ID='" + t_id + "'", QJXX.class);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	// findByPage
	@Override
	public List<QJXX> findQJXXByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
		String hql = "from QJXX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("XSXM") && !"".equals(params.get("XSXM"))) {
			param = "and XSXM like '%" + params.get("XSXM").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JGXM") && !"".equals(params.get("JGXM"))) {
			param = "and JGXM like '%" + params.get("JGXM").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JGGH") && !"".equals(params.get("JGGH"))) {
			param = "and JGGH like '%" + params.get("JGGH").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("DJRQ") && !"".equals(params.get("DJRQ"))) {
			param = "and DJRQ like '%" + params.get("DJRQ").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JG_ID") && !"".equals(params.get("JG_ID"))) {
			param = "and JG_ID like '%" + params.get("JG_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("XSXH") && !"".equals(params.get("XSXH"))) {
			param = "and XSXH like '%" + params.get("XSXH").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("XGRQ") && !"".equals(params.get("XGRQ"))) {
			param = "and XGRQ like '%" + params.get("XGRQ").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("QJJSJS") && !"".equals(params.get("QJJSJS"))) {
			param = "and QJJSJS like '%" + params.get("QJJSJS").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("QJKSSJ") && !"".equals(params.get("QJKSSJ"))) {
			param = "and QJKSSJ like '%" + params.get("QJKSSJ").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("XS_ID") && !"".equals(params.get("XS_ID"))) {
			param = "and XS_ID like '%" + params.get("XS_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		List<QJXX> list = baseDao.findByPage(hql, QJXX.class, start, number, params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountQJXX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from QJXX where 1=1 ";
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString() + "%'";
		}
		if (null != params.get("XSXM") && !"".equals(params.get("XSXM"))) {
			hql += "and XSXM like '%" + params.get("XSXM").toString() + "%'";
		}
		if (null != params.get("JGXM") && !"".equals(params.get("JGXM"))) {
			hql += "and JGXM like '%" + params.get("JGXM").toString() + "%'";
		}
		if (null != params.get("JGGH") && !"".equals(params.get("JGGH"))) {
			hql += "and JGGH like '%" + params.get("JGGH").toString() + "%'";
		}
		if (null != params.get("DJRQ") && !"".equals(params.get("DJRQ"))) {
			hql += "and DJRQ like '%" + params.get("DJRQ").toString() + "%'";
		}
		if (null != params.get("JG_ID") && !"".equals(params.get("JG_ID"))) {
			hql += "and JG_ID like '%" + params.get("JG_ID").toString() + "%'";
		}
		if (null != params.get("XSXH") && !"".equals(params.get("XSXH"))) {
			hql += "and XSXH like '%" + params.get("XSXH").toString() + "%'";
		}
		if (null != params.get("XGRQ") && !"".equals(params.get("XGRQ"))) {
			hql += "and XGRQ like '%" + params.get("XGRQ").toString() + "%'";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString() + "%'";
		}
		if (null != params.get("QJJSJS") && !"".equals(params.get("QJJSJS"))) {
			hql += "and QJJSJS like '%" + params.get("QJJSJS").toString() + "%'";
		}
		if (null != params.get("QJKSSJ") && !"".equals(params.get("QJKSSJ"))) {
			hql += "and QJKSSJ like '%" + params.get("QJKSSJ").toString() + "%'";
		}
		if (null != params.get("XS_ID") && !"".equals(params.get("XS_ID"))) {
			hql += "and XS_ID like '%" + params.get("XS_ID").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class).get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<QJXX> findQJXXByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from QJXX where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XSXM") && !"".equals(params.get("XSXM"))) {
			param = "and XSXM like '%" + params.get("XSXM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JGXM") && !"".equals(params.get("JGXM"))) {
			param = "and JGXM like '%" + params.get("JGXM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JGGH") && !"".equals(params.get("JGGH"))) {
			param = "and JGGH like '%" + params.get("JGGH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("DJRQ") && !"".equals(params.get("DJRQ"))) {
			param = "and DJRQ like '%" + params.get("DJRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JG_ID") && !"".equals(params.get("JG_ID"))) {
			param = "and JG_ID like '%" + params.get("JG_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XSXH") && !"".equals(params.get("XSXH"))) {
			param = "and XSXH like '%" + params.get("XSXH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XGRQ") && !"".equals(params.get("XGRQ"))) {
			param = "and XGRQ like '%" + params.get("XGRQ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("QJJSJS") && !"".equals(params.get("QJJSJS"))) {
			param = "and QJJSJS like '%" + params.get("QJJSJS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("QJKSSJ") && !"".equals(params.get("QJKSSJ"))) {
			param = "and QJKSSJ like '%" + params.get("QJKSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_ID") && !"".equals(params.get("XS_ID"))) {
			param = "and XS_ID like '%" + params.get("XS_ID").toString() + "%'";
			params2.add(param);
		}
		List<QJXX> list = baseDao.findAll(hql, QJXX.class);
		return list;
	}

	@Override
	public Xsxx findXsxxByXh(String xh) {
		String hql = "from Xsxx where XH='"+xh+"'";
		List<Xsxx> list = baseDao.findAll(hql, Xsxx.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public JIAOGONG findJIAOGONGByGh(String gh) {
		String hql = "from JIAOGONG where JGGH='"+gh+"'";
		List<JIAOGONG> list = baseDao.findAll(hql, JIAOGONG.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<JIAOGONG> findJIAOGONGForQJ() {
		List<JIAOGONG> list = baseDao.findAll("from JIAOGONG", JIAOGONG.class);
		int iSize = list.size();
		for(int i = 0 ; i < iSize ; i++){
			JIAOGONG jg = list.get(i);
			list.get(i).setTMP1(jg.getZSXM()+" "+jg.getJGGH());
		}
		return list;
	}

}