package com.hrbsys.ckxx.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.CKXX;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.KECHENGXX;
import com.hrbsys.ckxx.service.CKXXService;

public class CKXXServiceImpl implements CKXXService {
	// 引入BaseDao
	private BaseDao baseDao;

	// 添加串课信息
	@Override
	public boolean addCKXX(CKXX y) {
		return baseDao.save(y);
	}

	// 删除串课信息
	@Override
	public boolean delCKXX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			CKXX yhz = findCKXX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// 修改串课信息
	@Override
	public boolean updateCKXX(CKXX t) {
		String[] str = t.getCKXX_ID().split(",");
		System.out.println(t.getCKXX_ID());
		System.out.println("fdasfafa" + str[0].toString().trim());
		List<CKXX> allck = baseDao.findAll("from CKXX where CKXX_ID='"
				+ str[0].toString().trim() + "'", CKXX.class);
		System.out.println("11111111111111111111111111111111");
		if (allck.size() == 0) {
			return false;
		}
		System.out.println("22222222222222222222222222");
		CKXX tmpt = allck.get(0);
		tmpt.setJSMC(t.getJSMC());
		tmpt.setLAOSHI_GH(t.getLAOSHI_GH());
		tmpt.setKCB_KCMC(t.getKCB_KCMC());
		tmpt.setLAOSHI_XM(t.getLAOSHI_XM());
		tmpt.setZHOU(t.getZHOU());

		tmpt.setKSMC(t.getKSMC());
		tmpt.setXINGQI(t.getXINGQI());

		tmpt.setJS_ID(t.getJS_ID());
		tmpt.setKCB_ID(t.getKCB_ID());
		tmpt.setLAOSHI_ID(t.getLAOSHI_ID());
		tmpt.setKS_ID(t.getKS_ID());

		tmpt.setZHOU_CD(t.getZHOU_CD());
		tmpt.setJSMC_CD(t.getJSMC_CD());
		tmpt.setKCB_KCMC_CD(t.getKCB_KCMC_CD());
		tmpt.setXINGQI_CD(t.getXINGQI_CD());
		tmpt.setKSMC_CD(t.getKSMC_CD());
		tmpt.setLAOSHI_GH_CD(t.getLAOSHI_GH_CD());
		tmpt.setLAOSHI_XM_CD(t.getLAOSHI_XM_CD());

		tmpt.setJS_ID_CD(t.getJS_ID_CD());
		tmpt.setLAOSHI_ID_CD(t.getLAOSHI_ID_CD());
		tmpt.setKS_ID_CD(t.getKS_ID_CD());
		tmpt.setKCB_ID_CD(t.getKCB_ID_CD());

		tmpt.setBZ(t.getBZ());
		tmpt.setMS(t.getMS());

		return baseDao.update(tmpt);
	}

	// findById
	@Override
	public CKXX findCKXX(String t_id) {
		return baseDao.findAll("from CKXX where CKXX_ID='" + t_id + "'",
				CKXX.class).get(0);

	}

	// findByPage
	@Override
	public List<CKXX> findCKXXByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from CKXX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("XINGQI_CD")
				&& !"".equals(params.get("XINGQI_CD"))) {
			param = "and XINGQI_CD like '%"
					+ params.get("XINGQI_CD").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KSMC_CD") && !"".equals(params.get("KSMC_CD"))) {
			param = "and KSMC_CD like '%" + params.get("KSMC_CD").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LAOSHI_XM")
				&& !"".equals(params.get("LAOSHI_XM"))) {
			param = "and LAOSHI_XM like '%"
					+ params.get("LAOSHI_XM").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KCB_KCMC")
				&& !"".equals(params.get("KCB_KCMC"))) {
			param = "and KCB_KCMC like '%" + params.get("KCB_KCMC").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LAOSHI_ID")
				&& !"".equals(params.get("LAOSHI_ID"))) {
			param = "and LAOSHI_ID like '%"
					+ params.get("LAOSHI_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LAOSHI_ID_CD")
				&& !"".equals(params.get("LAOSHI_ID_CD"))) {
			param = "and LAOSHI_ID_CD like '%"
					+ params.get("LAOSHI_ID_CD").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JS_ID_CD")
				&& !"".equals(params.get("JS_ID_CD"))) {
			param = "and JS_ID_CD like '%" + params.get("JS_ID_CD").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZHOU") && !"".equals(params.get("ZHOU"))) {
			param = "and ZHOU like '%" + params.get("ZHOU").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KCB_KCMC_CD")
				&& !"".equals(params.get("KCB_KCMC_CD"))) {
			param = "and KCB_KCMC_CD like '%"
					+ params.get("KCB_KCMC_CD").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LAOSHI_GH")
				&& !"".equals(params.get("LAOSHI_GH"))) {
			param = "and LAOSHI_GH like '%"
					+ params.get("LAOSHI_GH").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KS_ID") && !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%" + params.get("KS_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LAOSHI_GH_CD")
				&& !"".equals(params.get("LAOSHI_GH_CD"))) {
			param = "and LAOSHI_GH_CD like '%"
					+ params.get("LAOSHI_GH_CD").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("XINGQI") && !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZHOU_CD") && !"".equals(params.get("ZHOU_CD"))) {
			param = "and ZHOU_CD like '%" + params.get("ZHOU_CD").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JSMC_CD") && !"".equals(params.get("JSMC_CD"))) {
			param = "and JSMC_CD like '%" + params.get("JSMC_CD").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%" + params.get("JSMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KS_ID_CD")
				&& !"".equals(params.get("KS_ID_CD"))) {
			param = "and KS_ID_CD like '%" + params.get("KS_ID_CD").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LAOSHI_XM_CD")
				&& !"".equals(params.get("LAOSHI_XM_CD"))) {
			param = "and LAOSHI_XM_CD like '%"
					+ params.get("LAOSHI_XM_CD").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KSMC") && !"".equals(params.get("KSMC"))) {
			param = "and KSMC like '%" + params.get("KSMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("KCB_ID_CD")
				&& !"".equals(params.get("KCB_ID_CD"))) {
			param = "and KCB_ID_CD like '%"
					+ params.get("KCB_ID_CD").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%" + params.get("JS_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		List<CKXX> list = baseDao.findByPage(hql, CKXX.class, start, number,
				params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountCKXX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from CKXX where 1=1 ";
		if (null != params.get("XINGQI_CD")
				&& !"".equals(params.get("XINGQI_CD"))) {
			hql += "and XINGQI_CD like '%" + params.get("XINGQI_CD").toString()
					+ "%'";
		}
		if (null != params.get("KSMC_CD") && !"".equals(params.get("KSMC_CD"))) {
			hql += "and KSMC_CD like '%" + params.get("KSMC_CD").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHI_XM")
				&& !"".equals(params.get("LAOSHI_XM"))) {
			hql += "and LAOSHI_XM like '%" + params.get("LAOSHI_XM").toString()
					+ "%'";
		}
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			hql += "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
		}
		if (null != params.get("KCB_KCMC")
				&& !"".equals(params.get("KCB_KCMC"))) {
			hql += "and KCB_KCMC like '%" + params.get("KCB_KCMC").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHI_ID")
				&& !"".equals(params.get("LAOSHI_ID"))) {
			hql += "and LAOSHI_ID like '%" + params.get("LAOSHI_ID").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHI_ID_CD")
				&& !"".equals(params.get("LAOSHI_ID_CD"))) {
			hql += "and LAOSHI_ID_CD like '%"
					+ params.get("LAOSHI_ID_CD").toString() + "%'";
		}
		if (null != params.get("JS_ID_CD")
				&& !"".equals(params.get("JS_ID_CD"))) {
			hql += "and JS_ID_CD like '%" + params.get("JS_ID_CD").toString()
					+ "%'";
		}
		if (null != params.get("ZHOU") && !"".equals(params.get("ZHOU"))) {
			hql += "and ZHOU like '%" + params.get("ZHOU").toString() + "%'";
		}
		if (null != params.get("KCB_KCMC_CD")
				&& !"".equals(params.get("KCB_KCMC_CD"))) {
			hql += "and KCB_KCMC_CD like '%"
					+ params.get("KCB_KCMC_CD").toString() + "%'";
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString() + "%'";
		}
		if (null != params.get("LAOSHI_GH")
				&& !"".equals(params.get("LAOSHI_GH"))) {
			hql += "and LAOSHI_GH like '%" + params.get("LAOSHI_GH").toString()
					+ "%'";
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString() + "%'";
		}
		if (null != params.get("KS_ID") && !"".equals(params.get("KS_ID"))) {
			hql += "and KS_ID like '%" + params.get("KS_ID").toString() + "%'";
		}
		if (null != params.get("LAOSHI_GH_CD")
				&& !"".equals(params.get("LAOSHI_GH_CD"))) {
			hql += "and LAOSHI_GH_CD like '%"
					+ params.get("LAOSHI_GH_CD").toString() + "%'";
		}
		if (null != params.get("XINGQI") && !"".equals(params.get("XINGQI"))) {
			hql += "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
		}
		if (null != params.get("ZHOU_CD") && !"".equals(params.get("ZHOU_CD"))) {
			hql += "and ZHOU_CD like '%" + params.get("ZHOU_CD").toString()
					+ "%'";
		}
		if (null != params.get("JSMC_CD") && !"".equals(params.get("JSMC_CD"))) {
			hql += "and JSMC_CD like '%" + params.get("JSMC_CD").toString()
					+ "%'";
		}
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			hql += "and JSMC like '%" + params.get("JSMC").toString() + "%'";
		}
		if (null != params.get("KS_ID_CD")
				&& !"".equals(params.get("KS_ID_CD"))) {
			hql += "and KS_ID_CD like '%" + params.get("KS_ID_CD").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHI_XM_CD")
				&& !"".equals(params.get("LAOSHI_XM_CD"))) {
			hql += "and LAOSHI_XM_CD like '%"
					+ params.get("LAOSHI_XM_CD").toString() + "%'";
		}
		if (null != params.get("KSMC") && !"".equals(params.get("KSMC"))) {
			hql += "and KSMC like '%" + params.get("KSMC").toString() + "%'";
		}
		if (null != params.get("KCB_ID_CD")
				&& !"".equals(params.get("KCB_ID_CD"))) {
			hql += "and KCB_ID_CD like '%" + params.get("KCB_ID_CD").toString()
					+ "%'";
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			hql += "and JS_ID like '%" + params.get("JS_ID").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<CKXX> findCKXXByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from CKXX where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("XINGQI_CD")
				&& !"".equals(params.get("XINGQI_CD"))) {
			param = "and XINGQI_CD like '%"
					+ params.get("XINGQI_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSMC_CD") && !"".equals(params.get("KSMC_CD"))) {
			param = "and KSMC_CD like '%" + params.get("KSMC_CD").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_XM")
				&& !"".equals(params.get("LAOSHI_XM"))) {
			param = "and LAOSHI_XM like '%"
					+ params.get("LAOSHI_XM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_KCMC")
				&& !"".equals(params.get("KCB_KCMC"))) {
			param = "and KCB_KCMC like '%" + params.get("KCB_KCMC").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_ID")
				&& !"".equals(params.get("LAOSHI_ID"))) {
			param = "and LAOSHI_ID like '%"
					+ params.get("LAOSHI_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_ID_CD")
				&& !"".equals(params.get("LAOSHI_ID_CD"))) {
			param = "and LAOSHI_ID_CD like '%"
					+ params.get("LAOSHI_ID_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID_CD")
				&& !"".equals(params.get("JS_ID_CD"))) {
			param = "and JS_ID_CD like '%" + params.get("JS_ID_CD").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("ZHOU") && !"".equals(params.get("ZHOU"))) {
			param = "and ZHOU like '%" + params.get("ZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_KCMC_CD")
				&& !"".equals(params.get("KCB_KCMC_CD"))) {
			param = "and KCB_KCMC_CD like '%"
					+ params.get("KCB_KCMC_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_GH")
				&& !"".equals(params.get("LAOSHI_GH"))) {
			param = "and LAOSHI_GH like '%"
					+ params.get("LAOSHI_GH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_ID") && !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%" + params.get("KS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_GH_CD")
				&& !"".equals(params.get("LAOSHI_GH_CD"))) {
			param = "and LAOSHI_GH_CD like '%"
					+ params.get("LAOSHI_GH_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQI") && !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("ZHOU_CD") && !"".equals(params.get("ZHOU_CD"))) {
			param = "and ZHOU_CD like '%" + params.get("ZHOU_CD").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("JSMC_CD") && !"".equals(params.get("JSMC_CD"))) {
			param = "and JSMC_CD like '%" + params.get("JSMC_CD").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%" + params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_ID_CD")
				&& !"".equals(params.get("KS_ID_CD"))) {
			param = "and KS_ID_CD like '%" + params.get("KS_ID_CD").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_XM_CD")
				&& !"".equals(params.get("LAOSHI_XM_CD"))) {
			param = "and LAOSHI_XM_CD like '%"
					+ params.get("LAOSHI_XM_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSMC") && !"".equals(params.get("KSMC"))) {
			param = "and KSMC like '%" + params.get("KSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID_CD")
				&& !"".equals(params.get("KCB_ID_CD"))) {
			param = "and KCB_ID_CD like '%"
					+ params.get("KCB_ID_CD").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%" + params.get("JS_ID").toString() + "%'";
			params2.add(param);
		}
		List<CKXX> list = baseDao.findAll(hql, CKXX.class);
		return list;
	}

	public List<JIAOGONG> findJIAOGONG(String JG_ID) {
		String hql = "from JIAOGONG where JG_ID='" + JG_ID + "'";
		List<JIAOGONG> jg = (List<JIAOGONG>) baseDao.findAll(hql,
				JIAOGONG.class);
		return jg;
	}

	@Override
	public KECHENGB findKECHENGB(String KCB_ID) {
		String hql = "from KECHENGB where KCB_ID='" + KCB_ID + "'";
		List<KECHENGB> kcb = baseDao.findAll(hql, KECHENGB.class);
		if (kcb.size() > 0) {
			return kcb.get(0);
		}
		return null;
	}

	public BaseDao getBaseDao() {

		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}