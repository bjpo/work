package com.hrbsys.jiaogong.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.business.KechengbiaoTools;
import com.hrbsys.jiaogong.service.JIAOGONGService;
public class JIAOGONGServiceImpl implements JIAOGONGService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addJIAOGONG(JIAOGONG y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delJIAOGONG(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {JIAOGONG yhz=findJIAOGONG(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateJIAOGONG(JIAOGONG t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JIAOGONG tmpt = baseDao.findAll("from JIAOGONG where JG_ID='" + t.getJG_ID() + "'",JIAOGONG.class).get(0);
   	    tmpt.setJGMC(t.getJGMC());
   	    tmpt.setTMP6(t.getTMP6());
   	    tmpt.setTMP5(t.getTMP5());
   	    tmpt.setJTZZ(t.getJTZZ());
   	    tmpt.setSHENGAO(t.getSHENGAO());
   	    tmpt.setTMP4(t.getTMP4());
   	    tmpt.setTMP3(t.getTMP3());
   	    tmpt.setTMP2(t.getTMP2());
   	    tmpt.setTMP1(t.getTMP1());
   	    tmpt.setSFZHM(t.getSFZHM());
   	    tmpt.setMZ(t.getMZ());
   	    tmpt.setJGLBMC(t.getJGLBMC());
   	    tmpt.setTIZHONG(t.getTIZHONG());
   	    tmpt.setZP_ID(t.getZP_ID());
   	    tmpt.setMS(t.getMS());
   	    tmpt.setJGLB_ID(t.getJGLB_ID());
   	    tmpt.setXUELI(t.getXUELI());
   	    tmpt.setBYYX(t.getBYYX());
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setCSNY(t.getCSNY());
   	    tmpt.setXB(t.getXB());
   	    tmpt.setYH_ID(t.getYH_ID());
   	    tmpt.setZSXM(t.getZSXM());
   	    tmpt.setJGGH(t.getJGGH());
   	    tmpt.setZHIWEN_ID1(t.getZHIWEN_ID1());
   	    tmpt.setZHIWEN_ID2(t.getZHIWEN_ID2());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public JIAOGONG findJIAOGONG(String t_id) {
		List<JIAOGONG> all= baseDao.findAll("from JIAOGONG where JG_ID='" + t_id + "'",JIAOGONG.class);
		if(all.size()>0){
			return all.get(0);	
		}
		return null;
	}

    //findByPage
	@Override
	public List<JIAOGONG> findJIAOGONGByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from JIAOGONG where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("JGMC") && !"".equals(params.get("JGMC"))) {
			param = "and JGMC like '%" + params.get("JGMC").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("TMP6") && !"".equals(params.get("TMP6"))) {
			param = "and TMP6 like '%" + params.get("TMP6").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("TMP5") && !"".equals(params.get("TMP5"))) {
			param = "and TMP5 like '%" + params.get("TMP5").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("JTZZ") && !"".equals(params.get("JTZZ"))) {
			param = "and JTZZ like '%" + params.get("JTZZ").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("SHENGAO") && !"".equals(params.get("SHENGAO"))) {
			param = "and SHENGAO like '%" + params.get("SHENGAO").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("TMP4") && !"".equals(params.get("TMP4"))) {
			param = "and TMP4 like '%" + params.get("TMP4").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("TMP3") && !"".equals(params.get("TMP3"))) {
			param = "and TMP3 like '%" + params.get("TMP3").toString()
				+ "%'";
			params2.add(param);
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
		if (null != params.get("SFZHM") && !"".equals(params.get("SFZHM"))) {
			param = "and SFZHM like '%" + params.get("SFZHM").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("MZ") && !"".equals(params.get("MZ"))) {
			param = "and MZ like '%" + params.get("MZ").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("JGLBMC") && !"".equals(params.get("JGLBMC"))) {
			param = "and JGLBMC like '%" + params.get("JGLBMC").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("TIZHONG") && !"".equals(params.get("TIZHONG"))) {
			param = "and TIZHONG like '%" + params.get("TIZHONG").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZP_ID") && !"".equals(params.get("ZP_ID"))) {
			param = "and ZP_ID like '%" + params.get("ZP_ID").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("MS") && !"".equals(params.get("MS"))) {
			param = "and MS like '%" + params.get("MS").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("JGLB_ID") && !"".equals(params.get("JGLB_ID"))) {
			param = "and JGLB_ID like '%" + params.get("JGLB_ID").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("ZW_ID") && !"".equals(params.get("ZW_ID"))) {
			param = "and ZW_ID like '%" + params.get("ZW_ID").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("XUELI") && !"".equals(params.get("XUELI"))) {
			param = "and XUELI like '%" + params.get("XUELI").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("BYYX") && !"".equals(params.get("BYYX"))) {
			param = "and BYYX like '%" + params.get("BYYX").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
			param = "and BZ like '%" + params.get("BZ").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("CSNY") && !"".equals(params.get("CSNY"))) {
			param = "and CSNY like '%" + params.get("CSNY").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("XB") && !"".equals(params.get("XB"))) {
			param = "and XB like '%" + params.get("XB").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("YH_ID") && !"".equals(params.get("YH_ID"))) {
			param = "and YH_ID like '%" + params.get("YH_ID").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("ZSXM") && !"".equals(params.get("ZSXM"))) {
			param = "and ZSXM like '%" + params.get("ZSXM").toString()
				+ "%'";
			params2.add(param);
		}
		if (null != params.get("JGGH") && !"".equals(params.get("JGGH"))) {
			param = "and JGGH like '%" + params.get("JGGH").toString()
				+ "%'";
			params2.add(param);
		}
		List<JIAOGONG> list = baseDao.findByPage(hql, JIAOGONG.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountJIAOGONG(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JIAOGONG where 1=1 ";
		if (null != params.get("JGMC")
				&& !"".equals(params.get("JGMC"))) {
			hql += "and JGMC like '%" + params.get("JGMC").toString()
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
		if (null != params.get("JTZZ")
				&& !"".equals(params.get("JTZZ"))) {
			hql += "and JTZZ like '%" + params.get("JTZZ").toString()
					+ "%'";
		}
		if (null != params.get("SHENGAO")
				&& !"".equals(params.get("SHENGAO"))) {
			hql += "and SHENGAO like '%" + params.get("SHENGAO").toString()
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
		if (null != params.get("SFZHM")
				&& !"".equals(params.get("SFZHM"))) {
			hql += "and SFZHM like '%" + params.get("SFZHM").toString()
					+ "%'";
		}
		if (null != params.get("MZ")
				&& !"".equals(params.get("MZ"))) {
			hql += "and MZ like '%" + params.get("MZ").toString()
					+ "%'";
		}
		if (null != params.get("JGLBMC")
				&& !"".equals(params.get("JGLBMC"))) {
			hql += "and JGLBMC like '%" + params.get("JGLBMC").toString()
					+ "%'";
		}
		if (null != params.get("TIZHONG")
				&& !"".equals(params.get("TIZHONG"))) {
			hql += "and TIZHONG like '%" + params.get("TIZHONG").toString()
					+ "%'";
		}
		if (null != params.get("ZP_ID")
				&& !"".equals(params.get("ZP_ID"))) {
			hql += "and ZP_ID like '%" + params.get("ZP_ID").toString()
					+ "%'";
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("JGLB_ID")
				&& !"".equals(params.get("JGLB_ID"))) {
			hql += "and JGLB_ID like '%" + params.get("JGLB_ID").toString()
					+ "%'";
		}
		if (null != params.get("ZW_ID")
				&& !"".equals(params.get("ZW_ID"))) {
			hql += "and ZW_ID like '%" + params.get("ZW_ID").toString()
					+ "%'";
		}
		if (null != params.get("XUELI")
				&& !"".equals(params.get("XUELI"))) {
			hql += "and XUELI like '%" + params.get("XUELI").toString()
					+ "%'";
		}
		if (null != params.get("BYYX")
				&& !"".equals(params.get("BYYX"))) {
			hql += "and BYYX like '%" + params.get("BYYX").toString()
					+ "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("CSNY")
				&& !"".equals(params.get("CSNY"))) {
			hql += "and CSNY like '%" + params.get("CSNY").toString()
					+ "%'";
		}
		if (null != params.get("XB")
				&& !"".equals(params.get("XB"))) {
			hql += "and XB like '%" + params.get("XB").toString()
					+ "%'";
		}
		if (null != params.get("YH_ID")
				&& !"".equals(params.get("YH_ID"))) {
			hql += "and YH_ID like '%" + params.get("YH_ID").toString()
					+ "%'";
		}
		if (null != params.get("ZSXM")
				&& !"".equals(params.get("ZSXM"))) {
			hql += "and ZSXM like '%" + params.get("ZSXM").toString()
					+ "%'";
		}
		if (null != params.get("JGGH")
				&& !"".equals(params.get("JGGH"))) {
			hql += "and JGGH like '%" + params.get("JGGH").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<JIAOGONG> findJIAOGONGByPageApp(HashMap<String, String> params, String order, String sort,String jgqx,String jgid) {
		String hql = "from JIAOGONG where 1=1";
		//加入权限限制
		
		//只能查看自己的排课
		if(KechengbiaoTools.GEREN.equals(jgqx)){
			hql+=" AND JG_ID='"+jgid+"'";
		}
		//可查看学院下所有教师排课:
		if(KechengbiaoTools.XUEYUAN.equals(jgqx)){
			hql+=" AND JG_ID in (select b.JG_ID from JIAOGONG b where b.XY_ID in(select a.XY_ID from JIAOGONG a where a.JG_ID='"+jgid+"'))";
		}
		System.out.println("打印的教工查询sql："+hql);
		//不能查看排课
		if(KechengbiaoTools.BUNENGPK.equals(jgqx)){
			return new ArrayList<JIAOGONG>();
		}
		System.out.println("排课权限打印的HQL是：："+hql);
		List<JIAOGONG> list = baseDao.findAll(hql,JIAOGONG.class);
		return list;
	}
	@Override
	public JIAOGONG findJIAOGONGbyYONGHUID(String yh_id) {
		List<JIAOGONG>all= baseDao.findAll("from JIAOGONG where YH_ID='" + yh_id + "'",JIAOGONG.class);
		if(all.size()>0){
			return all.get(0);	
		}
		return null;
	}
	@Override
	public List<JIAOGONG> findJIAOGONGByPageApp(int start, int number,HashMap<String, String> params, String order, String sort,String jgqx, String jgid) {

		String hql = "from JIAOGONG where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("JGMC") && !"".equals(params.get("JGMC").toString())) {
			param = "and JGMC like '%" + params.get("JGMC").toString()
				+ "%'";
			params2.add(param);
			param = "";
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
		if (null != params.get("JTZZ") && !"".equals(params.get("JTZZ"))) {
			param = "and JTZZ like '%" + params.get("JTZZ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("SHENGAO") && !"".equals(params.get("SHENGAO"))) {
			param = "and SHENGAO like '%" + params.get("SHENGAO").toString()
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
		if (null != params.get("SFZHM") && !"".equals(params.get("SFZHM"))) {
			param = "and SFZHM like '%" + params.get("SFZHM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("MZ") && !"".equals(params.get("MZ"))) {
			param = "and MZ like '%" + params.get("MZ").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JGLBMC") && !"".equals(params.get("JGLBMC"))) {
			param = "and JGLBMC like '%" + params.get("JGLBMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("TIZHONG") && !"".equals(params.get("TIZHONG"))) {
			param = "and TIZHONG like '%" + params.get("TIZHONG").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZP_ID") && !"".equals(params.get("ZP_ID"))) {
			param = "and ZP_ID like '%" + params.get("ZP_ID").toString()
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
		if (null != params.get("JGLB_ID") && !"".equals(params.get("JGLB_ID"))) {
			param = "and JGLB_ID like '%" + params.get("JGLB_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZW_ID") && !"".equals(params.get("ZW_ID"))) {
			param = "and ZW_ID like '%" + params.get("ZW_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XUELI") && !"".equals(params.get("XUELI"))) {
			param = "and XUELI like '%" + params.get("XUELI").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("BYYX") && !"".equals(params.get("BYYX"))) {
			param = "and BYYX like '%" + params.get("BYYX").toString()
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
		if (null != params.get("CSNY") && !"".equals(params.get("CSNY"))) {
			param = "and CSNY like '%" + params.get("CSNY").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XB") && !"".equals(params.get("XB"))) {
			param = "and XB like '%" + params.get("XB").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("YH_ID") && !"".equals(params.get("YH_ID"))) {
			param = "and YH_ID like '%" + params.get("YH_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("ZSXM") && !"".equals(params.get("ZSXM"))) {
			param = "and ZSXM like '%" + params.get("ZSXM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("JGGH") && !"".equals(params.get("JGGH"))) {
			param = "and JGGH like '%" + params.get("JGGH").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		//加入权限限制
		//只能查看自己的排课
		if(KechengbiaoTools.GEREN.equals(jgqx)){
			hql+=" AND JG_ID='"+jgid+"'";
		}
		//可查看学院下所有教师排课:
		if(KechengbiaoTools.XUEYUAN.equals(jgqx)){
			hql+=" AND JG_ID in (select b.JG_ID from JIAOGONG b where b.XY_ID in(select a.XY_ID from JIAOGONG a where a.JG_ID='"+jgid+"'))";
		}
		System.out.println("打印的教工查询sql："+hql);
		//不能查看排课
		if(KechengbiaoTools.BUNENGPK.equals(jgqx)){
			System.out.println("不能查看排课.....");
			return new ArrayList<JIAOGONG>();
		}
		System.out.println("..............................................#################");
		List<JIAOGONG> list = baseDao.findByPage(hql, JIAOGONG.class, start,
		number, params2, order, sort);
		return list;
	}

}