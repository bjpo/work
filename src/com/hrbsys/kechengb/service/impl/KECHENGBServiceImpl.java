package com.hrbsys.kechengb.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.JIAOSHI;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.business.KechengbiaoTools;
import com.hrbsys.kechengb.service.KECHENGBService;
import com.hrbsys.tools.BaseChangeTool;
import com.hrbsys.tools.TeamPrint;
public class KECHENGBServiceImpl implements KECHENGBService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addKECHENGB(KECHENGB y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delKECHENGB(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {KECHENGB yhz=findKECHENGB(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateKECHENGB(KECHENGB t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		KECHENGB tmpt = baseDao.findAll("from KECHENGB where KCB_FXS_ID='" + t.getKCB_FXS_ID() + "'",KECHENGB.class).get(0);
   	    tmpt.setKS_KSSJ(t.getKS_KSSJ());
   	    tmpt.setKCB_ID(t.getKCB_ID());
   	    tmpt.setLAOSHI_ID(t.getLAOSHI_ID());
   	    tmpt.setKCBLB(t.getKCBLB());
   	    tmpt.setLAOSHIGH(t.getLAOSHIGH());
   	    tmpt.setLAOSHIMC(t.getLAOSHIMC());
   	    tmpt.setJSZHOU(t.getJSZHOU());
   	    tmpt.setTMP6(t.getTMP6());
   	    tmpt.setKSZHOU(t.getKSZHOU());
   	    tmpt.setTMP5(t.getTMP5());
   	    tmpt.setKCXX_ID(t.getKCXX_ID());
   	    tmpt.setTMP4(t.getTMP4());
   	    tmpt.setKS_ID(t.getKS_ID());
   	    tmpt.setTMP3(t.getTMP3());
   	    tmpt.setTMP2(t.getTMP2());
   	    tmpt.setXINGQI(t.getXINGQI());
   	    tmpt.setTMP1(t.getTMP1());
   	    tmpt.setMS(t.getMS());
   	    tmpt.setJSMC(t.getJSMC());
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setXINGQIXH(t.getXINGQIXH());
   	    tmpt.setKSMC(t.getKSMC());
   	    tmpt.setKS_JSSJ(t.getKS_JSSJ());
   	    tmpt.setKCXXMC(t.getKCXXMC());
   	    tmpt.setJS_ID(t.getJS_ID());
   	    tmpt.setNUMSELECTED(t.getNUMSELECTED());
   	    TeamPrint.println(KECHENGBServiceImpl.class,"updateKECHENGB","打印属性",t.getNUMSELECTED()+" --> "+tmpt.getNUMSELECTED());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public KECHENGB findKECHENGB(String t_id) {
		String sql="from KECHENGB where KCB_FXS_ID='" + t_id + "'";
		System.out.println("KECHENGBService中findKECHENGB打印的SQL为：：："+sql);
		List<KECHENGB> all= baseDao.findAll(sql,KECHENGB.class);
		if(all.size()>0){
			return all.get(0);
		}
		return null;
	}

    //findByPage
	@Override
	public List<KECHENGB> findKECHENGBByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from KECHENGB where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KS_KSSJ") && !"".equals(params.get("KS_KSSJ"))) {
			param = "and KS_KSSJ like '%" + params.get("KS_KSSJ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%" + params.get("KCB_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("LAOSHI_ID") && !"".equals(params.get("LAOSHI_ID"))) {
			param = "and LAOSHI_ID like '%" + params.get("LAOSHI_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KCBLB") && !"".equals(params.get("KCBLB"))) {
			param = "and KCBLB like '%" + params.get("KCBLB").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("LAOSHIGH") && !"".equals(params.get("LAOSHIGH"))) {
			param = "and LAOSHIGH like '%" + params.get("LAOSHIGH").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("LAOSHIMC") && !"".equals(params.get("LAOSHIMC"))) {
			param = "and LAOSHIMC like '%" + params.get("LAOSHIMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JSZHOU") && !"".equals(params.get("JSZHOU"))) {
			param = "and JSZHOU like '%" + params.get("JSZHOU").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KSZHOU") && !"".equals(params.get("KSZHOU"))) {
			param = "and KSZHOU like '%" + params.get("KSZHOU").toString()
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
		
		if (null != params.get("KS_ID") && !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%" + params.get("KS_ID").toString()
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
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%" + params.get("JSMC").toString()
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
		if (null != params.get("XINGQIXH") && !"".equals(params.get("XINGQIXH"))) {
			param = "and XINGQIXH like '%" + params.get("XINGQIXH").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KSMC") && !"".equals(params.get("KSMC"))) {
			param = "and KSMC like '%" + params.get("KSMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KS_JSSJ") && !"".equals(params.get("KS_JSSJ"))) {
			param = "and KS_JSSJ like '%" + params.get("KS_JSSJ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("KCXXMC") && !"".equals(params.get("KCXXMC"))) {
			param = "and KCXXMC like '%" + params.get("KCXXMC").toString()
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
		List<KECHENGB> list = baseDao.findByPage(hql, KECHENGB.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountKECHENGB(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from KECHENGB where 1=1 ";
		if (null != params.get("KS_KSSJ")
				&& !"".equals(params.get("KS_KSSJ"))) {
			hql += "and KS_KSSJ like '%" + params.get("KS_KSSJ").toString()
					+ "%'";
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			hql += "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHI_ID")
				&& !"".equals(params.get("LAOSHI_ID"))) {
			hql += "and LAOSHI_ID like '%" + params.get("LAOSHI_ID").toString()
					+ "%'";
		}
		if (null != params.get("KCBLB")
				&& !"".equals(params.get("KCBLB"))) {
			hql += "and KCBLB like '%" + params.get("KCBLB").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHIGH")
				&& !"".equals(params.get("LAOSHIGH"))) {
			hql += "and LAOSHIGH like '%" + params.get("LAOSHIGH").toString()
					+ "%'";
		}
		if (null != params.get("LAOSHIMC")
				&& !"".equals(params.get("LAOSHIMC"))) {
			hql += "and LAOSHIMC like '%" + params.get("LAOSHIMC").toString()
					+ "%'";
		}
		if (null != params.get("JSZHOU")
				&& !"".equals(params.get("JSZHOU"))) {
			hql += "and JSZHOU like '%" + params.get("JSZHOU").toString()
					+ "%'";
		}
		if (null != params.get("TMP6")
				&& !"".equals(params.get("TMP6"))) {
			hql += "and TMP6 like '%" + params.get("TMP6").toString()
					+ "%'";
		}
		if (null != params.get("KSZHOU")
				&& !"".equals(params.get("KSZHOU"))) {
			hql += "and KSZHOU like '%" + params.get("KSZHOU").toString()
					+ "%'";
		}
		if (null != params.get("TMP5")
				&& !"".equals(params.get("TMP5"))) {
			hql += "and TMP5 like '%" + params.get("TMP5").toString()
					+ "%'";
		}
		if (null != params.get("KCXX_ID")
				&& !"".equals(params.get("KCXX_ID"))) {
			hql += "and KCXX_ID like '%" + params.get("KCXX_ID").toString()
					+ "%'";
		}
		if (null != params.get("TMP4")
				&& !"".equals(params.get("TMP4"))) {
			hql += "and TMP4 like '%" + params.get("TMP4").toString()
					+ "%'";
		}
		if (null != params.get("KS_ID")
				&& !"".equals(params.get("KS_ID"))) {
			hql += "and KS_ID like '%" + params.get("KS_ID").toString()
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
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			hql += "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
		}
		if (null != params.get("TMP1")
				&& !"".equals(params.get("TMP1"))) {
			hql += "and TMP1 like '%" + params.get("TMP1").toString()
					+ "%'";
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			hql += "and JSMC like '%" + params.get("JSMC").toString()
					+ "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("XINGQIXH")
				&& !"".equals(params.get("XINGQIXH"))) {
			hql += "and XINGQIXH like '%" + params.get("XINGQIXH").toString()
					+ "%'";
		}
		if (null != params.get("KSMC")
				&& !"".equals(params.get("KSMC"))) {
			hql += "and KSMC like '%" + params.get("KSMC").toString()
					+ "%'";
		}
		if (null != params.get("KS_JSSJ")
				&& !"".equals(params.get("KS_JSSJ"))) {
			hql += "and KS_JSSJ like '%" + params.get("KS_JSSJ").toString()
					+ "%'";
		}
		if (null != params.get("KCXXMC")
				&& !"".equals(params.get("KCXXMC"))) {
			hql += "and KCXXMC like '%" + params.get("KCXXMC").toString()
					+ "%'";
		}
		if (null != params.get("JS_ID")
				&& !"".equals(params.get("JS_ID"))) {
			hql += "and JS_ID like '%" + params.get("JS_ID").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<KECHENGB> findKECHENGBByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from KECHENGB where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KS_KSSJ")
				&& !"".equals(params.get("KS_KSSJ"))) {
			param = "and KS_KSSJ like '%"
					+ params.get("KS_KSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%"
					+ params.get("KCB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_ID")
				&& !"".equals(params.get("LAOSHI_ID"))) {
			param = "and LAOSHI_ID like '%"
					+ params.get("LAOSHI_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCBLB")
				&& !"".equals(params.get("KCBLB"))) {
			param = "and KCBLB like '%"
					+ params.get("KCBLB").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHIGH")
				&& !"".equals(params.get("LAOSHIGH"))) {
			param = "and LAOSHIGH like '%"
					+ params.get("LAOSHIGH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHIMC")
				&& !"".equals(params.get("LAOSHIMC"))) {
			param = "and LAOSHIMC like '%"
					+ params.get("LAOSHIMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSZHOU")
				&& !"".equals(params.get("JSZHOU"))) {
			param = "and JSZHOU like '%"
					+ params.get("JSZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP6")
				&& !"".equals(params.get("TMP6"))) {
			param = "and TMP6 like '%"
					+ params.get("TMP6").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSZHOU")
				&& !"".equals(params.get("KSZHOU"))) {
			param = "and KSZHOU like '%"
					+ params.get("KSZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP5")
				&& !"".equals(params.get("TMP5"))) {
			param = "and TMP5 like '%"
					+ params.get("TMP5").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXX_ID")
				&& !"".equals(params.get("KCXX_ID"))) {
			param = "and KCXX_ID like '%"
					+ params.get("KCXX_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP4")
				&& !"".equals(params.get("TMP4"))) {
			param = "and TMP4 like '%"
					+ params.get("TMP4").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_ID")
				&& !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%"
					+ params.get("KS_ID").toString() + "%'";
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
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%"
					+ params.get("XINGQI").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP1")
				&& !"".equals(params.get("TMP1"))) {
			param = "and TMP1 like '%"
					+ params.get("TMP1").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%"
					+ params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQIXH")
				&& !"".equals(params.get("XINGQIXH"))) {
			param = "and XINGQIXH like '%"
					+ params.get("XINGQIXH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSMC")
				&& !"".equals(params.get("KSMC"))) {
			param = "and KSMC like '%"
					+ params.get("KSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_JSSJ")
				&& !"".equals(params.get("KS_JSSJ"))) {
			param = "and KS_JSSJ like '%"
					+ params.get("KS_JSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXXMC")
				&& !"".equals(params.get("KCXXMC"))) {
			param = "and KCXXMC like '%"
					+ params.get("KCXXMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID")
				&& !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%"
					+ params.get("JS_ID").toString() + "%'";
			params2.add(param);
		}
		System.out.println("打印的课程表查询sql"+hql);
		List<KECHENGB> list = baseDao.findAll(hql,KECHENGB.class);
		return list;
	}

    //findbypagelike
	@Override
	public List<KECHENGB> findKECHENGBforSHOWkechengb(HashMap<String, String> params, String order, String sort) {
		String hql = "from KECHENGB where 1=1 ";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KS_KSSJ")
				&& !"".equals(params.get("KS_KSSJ"))) {
			param = "and KS_KSSJ like '%"
					+ params.get("KS_KSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID")  //课程表ID也是等于
				&& !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID = '"
					+ params.get("KCB_ID").toString() + "'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_ID") //老师是等于
				&& !"".equals(params.get("LAOSHI_ID"))) {
			param = "and LAOSHI_ID = '"
					+ params.get("LAOSHI_ID").toString() + "'";
			params2.add(param);
		}
		if (null != params.get("KCBLB")
				&& !"".equals(params.get("KCBLB"))) {
			param = "and KCBLB like '%"
					+ params.get("KCBLB").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHIGH")
				&& !"".equals(params.get("LAOSHIGH"))) {
			param = "and LAOSHIGH like '"
					+ params.get("LAOSHIGH").toString() + "'";
			params2.add(param);
		}
		if (null != params.get("LAOSHIMC")
				&& !"".equals(params.get("LAOSHIMC"))) {
			param = "and LAOSHIMC like '%"
					+ params.get("LAOSHIMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSZHOU")
				&& !"".equals(params.get("JSZHOU"))) {
			param = "and JSZHOU like '%"
					+ params.get("JSZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP6")
				&& !"".equals(params.get("TMP6"))) {
			param = "and TMP6 like '%"
					+ params.get("TMP6").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSZHOU")
				&& !"".equals(params.get("KSZHOU"))) {
			param = "and KSZHOU like '%"
					+ params.get("KSZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP5")
				&& !"".equals(params.get("TMP5"))) {
			param = "and TMP5 like '%"
					+ params.get("TMP5").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXX_ID") //课程是等于
				&& !"".equals(params.get("KCXX_ID"))) {
			param = "and KCXX_ID = '"
					+ params.get("KCXX_ID").toString() + "'";
			params2.add(param);
		}
		if (null != params.get("TMP4")
				&& !"".equals(params.get("TMP4"))) {
			param = "and TMP4 like '%"
					+ params.get("TMP4").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_ID")
				&& !"".equals(params.get("KS_ID"))) {
			param = "and KS_ID like '%"
					+ params.get("KS_ID").toString() + "%'";
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
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%"
					+ params.get("XINGQI").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("TMP1")
				&& !"".equals(params.get("TMP1"))) {
			param = "and TMP1 like '%"
					+ params.get("TMP1").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%"
					+ params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQIXH")
				&& !"".equals(params.get("XINGQIXH"))) {
			param = "and XINGQIXH like '%"
					+ params.get("XINGQIXH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSMC")
				&& !"".equals(params.get("KSMC"))) {
			param = "and KSMC like '%"
					+ params.get("KSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_JSSJ")
				&& !"".equals(params.get("KS_JSSJ"))) {
			param = "and KS_JSSJ like '%"
					+ params.get("KS_JSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXXMC")
				&& !"".equals(params.get("KCXXMC"))) {
			param = "and KCXXMC like '%"
					+ params.get("KCXXMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID")    //教室是等于
				&& !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID = '"
					+ params.get("JS_ID").toString() + "'";
			params2.add(param);
		}
		if (null != params.get("XUEHAO")&& !"".equals(params.get("XUEHAO"))) {//学号是等于
			//如果传递过来的学号不是空，则到上课学生信息表中，查找学生应该上的课程表ID
			List list_skxsxx=baseDao.executeSQL("select t.kcxs_id,t.kcb_id,t.kcb_kcxxmc,t.xs_id,t.xsxm,t.xuehao from ht123.skxsxx t where t.xuehao='"+params.get("XUEHAO")+"'");
			if(list_skxsxx.size()>0){
			param="and KCB_ID IN(";	
			
			for(int kcxx_i=0;kcxx_i<list_skxsxx.size();kcxx_i++){ //循环课程列表，计算考勤信息
				Object[] tmpObjArray=(Object[]) list_skxsxx.get(kcxx_i);
				String kcb_id=(null==tmpObjArray[0])?"":tmpObjArray[1].toString();
				String kcb_kcxxmc=(null==tmpObjArray[0])?"":tmpObjArray[2].toString();
				String xs_id=(null==tmpObjArray[0])?"":tmpObjArray[3].toString();
				String xsxm=(null==tmpObjArray[0])?"":tmpObjArray[4].toString();
				String xuehao=(null==tmpObjArray[0])?"":tmpObjArray[5].toString();
				if(kcxx_i+1==list_skxsxx.size()){ //
					param+="'"+kcb_id+"'";
				}else{
					param+="'"+kcb_id+"',";
				}
			}
			param+=")";
			}
			//查出来的课程表ID 用in的方式加到hql中。
			params2.add(param);
		}
		List<KECHENGB> list = baseDao.findByLike(hql,KECHENGB.class, params2, order, sort);
		return list;
	}
	@Override
	public List<KECHENGB> findKECHENGBByPageApp(int start, int number,HashMap<String, String> params, String order, String sort,String pkqx, String jgid) {
		String hql = "from KECHENGB where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KS_KSSJ")
				&& !"".equals(params.get("KS_KSSJ"))) {
			param = " and KS_KSSJ like '%"
					+ params.get("KS_KSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			param = " and KCB_ID like '%"
					+ params.get("KCB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHI_ID")
				&& !"".equals(params.get("LAOSHI_ID"))) {
			param = " and LAOSHI_ID like '%"
					+ params.get("LAOSHI_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCBLB")
				&& !"".equals(params.get("KCBLB"))) {
			param = " and KCBLB like '%"
					+ params.get("KCBLB").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHIGH")
				&& !"".equals(params.get("LAOSHIGH"))) {
			param = " and LAOSHIGH like '%"
					+ params.get("LAOSHIGH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LAOSHIMC")
				&& !"".equals(params.get("LAOSHIMC"))) {
			param = " and LAOSHIMC like '%"
					+ params.get("LAOSHIMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSZHOU")
				&& !"".equals(params.get("JSZHOU"))) {
			param = " and JSZHOU like '%"
					+ params.get("JSZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSZHOU")
				&& !"".equals(params.get("KSZHOU"))) {
			param = " and KSZHOU like '%"
					+ params.get("KSZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXX_ID")
				&& !"".equals(params.get("KCXX_ID"))) {
			param = " and KCXX_ID like '%"
					+ params.get("KCXX_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_ID")
				&& !"".equals(params.get("KS_ID"))) {
			param = " and KS_ID like '%"
					+ params.get("KS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			param = " and XINGQI like '%"
					+ params.get("XINGQI").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = " and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			param = " and JSMC like '%"
					+ params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = " and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQIXH")
				&& !"".equals(params.get("XINGQIXH"))) {
			param = " and XINGQIXH like '%"
					+ params.get("XINGQIXH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSMC")
				&& !"".equals(params.get("KSMC"))) {
			param = " and KSMC like '%"
					+ params.get("KSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KS_JSSJ")
				&& !"".equals(params.get("KS_JSSJ"))) {
			param = " and KS_JSSJ like '%"
					+ params.get("KS_JSSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCXXMC")
				&& !"".equals(params.get("KCXXMC"))) {
			param = " and KCXXMC like '%"
					+ params.get("KCXXMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID")
				&& !"".equals(params.get("JS_ID"))) {
			param = " and JS_ID like '%"
					+ params.get("JS_ID").toString() + "%'";
			params2.add(param);
		}
		//加入权限限制
		
		//只能查看自己的排课
		if(KechengbiaoTools.GEREN.equals(pkqx)){
			hql+=" AND LAOSHI_ID='"+jgid+"'";
		}
		//可查看学院下所有教师排课:
		if(KechengbiaoTools.XUEYUAN.equals(pkqx)){
			hql+=" AND LAOSHI_ID in (select b.JG_ID from JIAOGONG b where b.XY_ID in(select a.XY_ID from JIAOGONG a where a.JG_ID='"+jgid+"'))";
		}
		
		System.out.println("打印的课程表查询sql"+hql);
		//不能查看排课
		if(KechengbiaoTools.BUNENGPK.equals(pkqx)){
			return new ArrayList<KECHENGB>();
		}
		List<KECHENGB> list = baseDao.findByPage(hql, KECHENGB.class, start,
				number, params2, order, sort);
		return list;
	}
	public List<KECHENGB> findeKECHENGBByJGIDandXUEQI(JIAOGONG jg,XUEQI xueqi,String ksrq){//根据教工和学期获取其课程表课程
		try {
		String xingqi="";
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if((null != ksrq) && (!"".equals(ksrq))){
				xingqi=BaseChangeTool.getWeek(sf.parse(ksrq));
		}else{
			xingqi=BaseChangeTool.getWeek(new Date());
		}
		TeamPrint.println(KECHENGBServiceImpl.class,"findeKECHENGBByJGIDandXUEQI", "日期类型",xingqi);
		String hql="FROM KECHENGB WHERE 1=1";
		//判断教工ID、学期是否为空
		if(null!=jg&&!"".equals(jg)){
			hql+=" AND LAOSHI_ID='"+jg.getJG_ID()+"'";
		}
		if(null!=xueqi&&!"".equals(xueqi)){
			hql+=" AND XUEQI_ID='"+xueqi.getXQ_ID()+"'";
		}
		if(null!=xingqi&&!"".equals(xingqi)){
			hql+=" AND XINGQI='"+xingqi+"'";
		}
		TeamPrint.println(KECHENGBServiceImpl.class,"findeKECHENGBByJGIDandXUEQI", "SQL 类型",hql);
		List<KECHENGB> list = baseDao.findAll(hql,KECHENGB.class);
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<KECHENGB> findeKECHENGBByJSIDandXUEQI(JIAOSHI js,XUEQI xueqi,String ksrq){//根据教工和学期获取其课程表课程
		try {
		String xingqi="";
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if((null != ksrq) && (!"".equals(ksrq))){
				xingqi=BaseChangeTool.getWeek(sf.parse(ksrq));
		}else{
			xingqi=BaseChangeTool.getWeek(new Date());
		}
		TeamPrint.println(KECHENGBServiceImpl.class,"findeKECHENGBByJSIDandXUEQI", "日期 类型",xingqi);
		String hql="FROM KECHENGB WHERE 1=1";
		//判断教室ID、学期是否为空
		if(null!=js&&!"".equals(js)){
			hql+=" AND JS_ID='"+js.getJS_ID()+"'";
		}
		if(null!=xueqi&&!"".equals(xueqi)){
			hql+=" AND XUEQI_ID='"+xueqi.getXQ_ID()+"'";
		}
		if(null!=xingqi&&!"".equals(xingqi)){
			hql+=" AND XINGQI='"+xingqi+"'";
		}
		TeamPrint.println(KECHENGBServiceImpl.class,"findeKECHENGBByJSIDandXUEQI", "SQL 类型",hql);
		List<KECHENGB> list = baseDao.findAll(hql,KECHENGB.class);
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<KECHENGB> findKECHENGBSbyKCBID(String t_id) {
		String sql="from KECHENGB where KCB_ID='" + t_id + "'";
		System.out.println("KECHENGBService中findKECHENGB打印的SQL为：：："+sql);
		List<KECHENGB> all= baseDao.findAll(sql,KECHENGB.class);
		return all;
	}

}