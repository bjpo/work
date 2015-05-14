package com.hrbsys.kqxxbjxx.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.KQXX_BJXX;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.kqxxbjxx.service.KQXX_BJXXService;
import com.hrbsys.tools.BaseChangeTool;
public class KQXX_BJXXServiceImpl implements KQXX_BJXXService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addKQXX_BJXX(KQXX_BJXX y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delKQXX_BJXX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {KQXX_BJXX yhz=findKQXX_BJXX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

    //update
	@Override 
	public boolean updateKQXX_BJXX(KQXX_BJXX t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		KQXX_BJXX tmpt = baseDao.findAll("from KQXX_BJXX where KQXX_BJXX_ID='" + t.getKQXX_BJXX_ID() + "'",KQXX_BJXX.class).get(0);
   	    tmpt.setCQL(t.getCQL());
   	    tmpt.setZTRS(t.getZTRS());
   	    tmpt.setKCMC(t.getKCMC());
   	    tmpt.setZCCXRS(t.getZCCXRS());
   	    tmpt.setQXL(t.getQXL());
   	    tmpt.setKCB_ID(t.getKCB_ID());
   	    tmpt.setLS_ID(t.getLS_ID());
   	    tmpt.setZHOU(t.getZHOU());
   	    tmpt.setKESHI_ID(t.getKESHI_ID());
   	    tmpt.setMS(t.getMS());
   	    tmpt.setSKSJ(t.getSKSJ());
   	    tmpt.setBZ(t.getBZ());
   	    tmpt.setLSXM(t.getLSXM());
   	    tmpt.setCDL(t.getCDL());
   	    tmpt.setXINGQI(t.getXINGQI());
   	    tmpt.setSJSKRS(t.getSJSKRS());
   	    tmpt.setKCLB(t.getKCLB());
   	    tmpt.setJSMC(t.getJSMC());
   	    tmpt.setCDRS(t.getCDRS());
   	    tmpt.setKCB_FKS_ID(t.getKCB_FKS_ID());
   	    tmpt.setZTL(t.getZTL());
   	    tmpt.setKSMC(t.getKSMC());
   	    tmpt.setLSGH(t.getLSGH());
   	    tmpt.setQXRS(t.getQXRS());
   	    tmpt.setYSKRS(t.getYSKRS());
   	    tmpt.setJS_ID(t.getJS_ID());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public KQXX_BJXX findKQXX_BJXX(String t_id) {
		return baseDao.findAll("from KQXX_BJXX where KQXX_BJXX_ID='" + t_id + "'",KQXX_BJXX.class).get(0);
	}

    //findByPage
	@Override
	public List<KQXX_BJXX> findKQXX_BJXXByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from KQXX_BJXX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KCMC") && !"".equals(params.get("KCMC"))) {
			param = "and KCMC like '%" + params.get("KCMC").toString()
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
		if (null != params.get("LS_ID") && !"".equals(params.get("LS_ID"))) {
			param = "and LS_ID like '%" + params.get("LS_ID").toString()
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
		if (null != params.get("KESHI_ID") && !"".equals(params.get("KESHI_ID"))) {
			param = "and KESHI_ID like '%" + params.get("KESHI_ID").toString()
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
		if (null != params.get("SKSJ") && !"".equals(params.get("SKSJ"))) {
			param = "and SKSJ like '%" + params.get("SKSJ").toString()
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
		if (null != params.get("LSXM") && !"".equals(params.get("LSXM"))) {
			param = "and LSXM like '%" + params.get("LSXM").toString()
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
		if (null != params.get("KCLB") && !"".equals(params.get("KCLB"))) {
			param = "and KCLB like '%" + params.get("KCLB").toString()
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
		if (null != params.get("KCB_FKS_ID") && !"".equals(params.get("KCB_FKS_ID"))) {
			param = "and KCB_FKS_ID like '%" + params.get("KCB_FKS_ID").toString()
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
		if (null != params.get("LSGH") && !"".equals(params.get("LSGH"))) {
			param = "and LSGH like '%" + params.get("LSGH").toString()
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
		List<KQXX_BJXX> list = baseDao.findByPage(hql, KQXX_BJXX.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountKQXX_BJXX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from KQXX_BJXX where 1=1 ";
		if (null != params.get("KCMC")
				&& !"".equals(params.get("KCMC"))) {
			hql += "and KCMC like '%" + params.get("KCMC").toString()
					+ "%'";
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			hql += "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
		}
		if (null != params.get("LS_ID")
				&& !"".equals(params.get("LS_ID"))) {
			hql += "and LS_ID like '%" + params.get("LS_ID").toString()
					+ "%'";
		}
		if (null != params.get("ZHOU")
				&& !"".equals(params.get("ZHOU"))) {
			hql += "and ZHOU like '%" + params.get("ZHOU").toString()
					+ "%'";
		}
		if (null != params.get("KESHI_ID")
				&& !"".equals(params.get("KESHI_ID"))) {
			hql += "and KESHI_ID like '%" + params.get("KESHI_ID").toString()
					+ "%'";
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			hql += "and MS like '%" + params.get("MS").toString()
					+ "%'";
		}
		if (null != params.get("SKSJ")
				&& !"".equals(params.get("SKSJ"))) {
			hql += "and SKSJ like '%" + params.get("SKSJ").toString()
					+ "%'";
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			hql += "and BZ like '%" + params.get("BZ").toString()
					+ "%'";
		}
		if (null != params.get("LSXM")
				&& !"".equals(params.get("LSXM"))) {
			hql += "and LSXM like '%" + params.get("LSXM").toString()
					+ "%'";
		}
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			hql += "and XINGQI like '%" + params.get("XINGQI").toString()
					+ "%'";
		}
		if (null != params.get("KCLB")
				&& !"".equals(params.get("KCLB"))) {
			hql += "and KCLB like '%" + params.get("KCLB").toString()
					+ "%'";
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			hql += "and JSMC like '%" + params.get("JSMC").toString()
					+ "%'";
		}
		if (null != params.get("KCB_FKS_ID")
				&& !"".equals(params.get("KCB_FKS_ID"))) {
			hql += "and KCB_FKS_ID like '%" + params.get("KCB_FKS_ID").toString()
					+ "%'";
		}
		if (null != params.get("KSMC")
				&& !"".equals(params.get("KSMC"))) {
			hql += "and KSMC like '%" + params.get("KSMC").toString()
					+ "%'";
		}
		if (null != params.get("LSGH")
				&& !"".equals(params.get("LSGH"))) {
			hql += "and LSGH like '%" + params.get("LSGH").toString()
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
	public List<KQXX_BJXX> findKQXX_BJXXByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from KQXX_BJXX where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KCMC")
				&& !"".equals(params.get("KCMC"))) {
			param = "and KCMC like '%"
					+ params.get("KCMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%"
					+ params.get("KCB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LS_ID")
				&& !"".equals(params.get("LS_ID"))) {
			param = "and LS_ID like '%"
					+ params.get("LS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZHOU")
				&& !"".equals(params.get("ZHOU"))) {
			param = "and ZHOU like '%"
					+ params.get("ZHOU").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KESHI_ID")
				&& !"".equals(params.get("KESHI_ID"))) {
			param = "and KESHI_ID like '%"
					+ params.get("KESHI_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MS")
				&& !"".equals(params.get("MS"))) {
			param = "and MS like '%"
					+ params.get("MS").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("SKSJ")
				&& !"".equals(params.get("SKSJ"))) {
			param = "and SKSJ like '%"
					+ params.get("SKSJ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("BZ")
				&& !"".equals(params.get("BZ"))) {
			param = "and BZ like '%"
					+ params.get("BZ").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LSXM")
				&& !"".equals(params.get("LSXM"))) {
			param = "and LSXM like '%"
					+ params.get("LSXM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XINGQI")
				&& !"".equals(params.get("XINGQI"))) {
			param = "and XINGQI like '%"
					+ params.get("XINGQI").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCLB")
				&& !"".equals(params.get("KCLB"))) {
			param = "and KCLB like '%"
					+ params.get("KCLB").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JSMC")
				&& !"".equals(params.get("JSMC"))) {
			param = "and JSMC like '%"
					+ params.get("JSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_FKS_ID")
				&& !"".equals(params.get("KCB_FKS_ID"))) {
			param = "and KCB_FKS_ID like '%"
					+ params.get("KCB_FKS_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KSMC")
				&& !"".equals(params.get("KSMC"))) {
			param = "and KSMC like '%"
					+ params.get("KSMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("LSGH")
				&& !"".equals(params.get("LSGH"))) {
			param = "and LSGH like '%"
					+ params.get("LSGH").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("JS_ID")
				&& !"".equals(params.get("JS_ID"))) {
			param = "and JS_ID like '%"
					+ params.get("JS_ID").toString() + "%'";
			params2.add(param);
		}
		List<KQXX_BJXX> list = baseDao.findAll(hql,KQXX_BJXX.class);
		return list;
	}
	
	//考勤信息统计：
	@Override
	public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid,XUEQI xq, String xingqi,String zhou,String ksrq,String jsrq) {
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		int countYCXRS=0;
		int countQUEXIRS=0;
		int countZHENGCHANGRS=0;
		int countCHIDAORS=0;
		int countZAOTUIRS=0;
		int countCHIZAORS=0;
		String hql=" from KQXX_BJXX WHERE 1=1";
		if (null !=jsid&&!"".equals(jsid)&&!"".equals(jsid)){ //增加教室ID
			hql+=" and JS_ID="+"'"+jsid+"'";
		}
		if (null !=  jgid && !"".equals(jgid)){//增加老师ID
			hql+=" and LS_ID="+"'"+jgid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			hql+=" and KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			hql+=" and KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			hql+=" and SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			hql+=" and SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String> riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=hql;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					
					if(null!=xq&&!"".equals(xq)){
						tmphql+="and  SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
					}
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"周不为空时，拼凑的hql：："+tmphql);
					List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
					for(KQXX_BJXX b:all_kq1){
						countYCXRS+=b.getYSKRS();
						countQUEXIRS+=b.getQXRS();
						countZHENGCHANGRS+=b.getZCCXRS();
						countCHIDAORS+=b.getCDRS();
						countZAOTUIRS+=b.getZTRS();
						if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
							countCHIZAORS+=b.getCDZTRS();	
						}
//						countCHIZAORS+=b.getCDZTRS();
						System.out.println("KQXX_BJXXServiceImpl中打印::"+"应上课人数:"+b.getYSKRS()+"-->"+countYCXRS);
						System.out.println("KQXX_BJXXServiceImpl中打印::"+"缺席人数:"+b.getQXRS()+"-->"+countQUEXIRS);
						System.out.println("KQXX_BJXXServiceImpl中打印::"+"迟到人数:"+b.getCDRS()+"-->"+countCHIDAORS);
						System.out.println("KQXX_BJXXServiceImpl中打印::"+"早退人数:"+b.getZTRS()+"-->"+countZAOTUIRS);					
						System.out.println("KQXX_BJXXServiceImpl中打印::"+"正常人数:"+b.getZCCXRS()+"-->"+countZHENGCHANGRS);
						System.out.println("KQXX_BJXXServiceImpl中打印::"+"迟到早退人数:"+b.getCDZTRS()+"-->"+countCHIZAORS);
					}
				}
		}else{ //如果周为空
			String tmphql=hql;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and XINGQI='"+xingqi+"'";
			}
			if(null!=xq&&!"".equals(xq)){
				tmphql+="and  SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
			}
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"周是空时，拼凑的hql：："+tmphql);
				List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
				for(KQXX_BJXX b:all_kq1){
					countYCXRS+=b.getYSKRS();
					countQUEXIRS+=b.getQXRS();
					countZHENGCHANGRS+=b.getZCCXRS();
					countCHIDAORS+=b.getCDRS();
					countZAOTUIRS+=b.getZTRS();
//					System.out.println("KQXX_BJXXServiceImpl中打印::"+b.getCDZTRS()+"#########################################");
					if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
						countCHIZAORS+=b.getCDZTRS();	
					}
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"应上课人数:"+b.getYSKRS()+"-->"+countYCXRS);
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"缺席人数:"+b.getQXRS()+"-->"+countQUEXIRS);
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"迟到人数:"+b.getCDRS()+"-->"+countCHIDAORS);
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"早退人数:"+b.getZTRS()+"-->"+countZAOTUIRS);
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"正常人数:"+b.getZCCXRS()+"-->"+countZHENGCHANGRS);
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"迟到早退人数:"+b.getCDZTRS()+"-->"+countCHIZAORS);
				}
		}
		map.put("ycxrs", countYCXRS);
		map.put("qxrs",countQUEXIRS);
		map.put("zcrs", countZHENGCHANGRS);
		map.put("cdrs",countCHIDAORS);
		map.put("ztrs",countZAOTUIRS);
		map.put("czrs", countCHIZAORS);
		return map;
	}
	//统计考勤信息，分学院
	@Override
	public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id) {
		if(null==xy_id||"".equals(xy_id)){
			System.out.println("KQXX_BJXXServiceImpl中打印::"+"学院ID为空，调用全校方法...");
			return getBJKQXX(jsid, jgid, ksid, kcbid, xq, xingqi, zhou, ksrq, jsrq);
		}
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		int countYCXRS=0;
		int countQUEXIRS=0;
		int countZHENGCHANGRS=0;
		int countCHIDAORS=0;
		int countZAOTUIRS=0;
		int	countCHIZAORS=0;	
		
		String hql="from KQXX_BJXX d where d.KCB_ID in(select c.KCB_ID from KECHENGB c where c.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID in (select a.ZY_ID from ZHUANYE a where a.XYID='"+xy_id+"')))";
		if (null !=jsid&&!"".equals(jsid)&&!"".equals(jsid)){ //增加教室ID
			hql+=" and d.JS_ID="+"'"+jsid+"'";
		}
		if (null !=  jgid && !"".equals(jgid)){//增加老师ID
			hql+=" and d.LS_ID="+"'"+jgid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			hql+=" and d.KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			hql+=" and d.KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			hql+=" and d.SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			hql+=" and d.SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=xq&&!"".equals(xq)){
			hql+="and  SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
		}
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String>riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=hql;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and d.SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and d.SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"分学院：周不为空时，拼凑的hql：："+tmphql);
					List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
					for(KQXX_BJXX b:all_kq1){
						countYCXRS+=b.getYSKRS();
						countQUEXIRS+=b.getQXRS();
						countZHENGCHANGRS+=b.getZCCXRS();
						countCHIDAORS+=b.getCDRS();
						countZAOTUIRS+=b.getZTRS();
						if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
							countCHIZAORS+=b.getCDZTRS();	
						}
					}
				}
		}else{ //如果周为空
			String tmphql=hql;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and d.XINGQI='"+xingqi+"'";
			}
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"分学院：周是空时，拼凑的hql：："+tmphql);
				List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
				for(KQXX_BJXX b:all_kq1){
					countYCXRS+=b.getYSKRS();
					countQUEXIRS+=b.getQXRS();
					countZHENGCHANGRS+=b.getZCCXRS();
					countCHIDAORS+=b.getCDRS();
					countZAOTUIRS+=b.getZTRS();
					if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
						countCHIZAORS+=b.getCDZTRS();	
					}
				}
		}
		map.put("ycxrs", countYCXRS);
		map.put("qxrs",countQUEXIRS);
		map.put("zcrs", countZHENGCHANGRS);
		map.put("cdrs",countCHIDAORS);
		map.put("ztrs",countZAOTUIRS);
		map.put("czrs", countCHIZAORS);
		return map;
	}
	
	
	//统计考勤信息，分学院-->专业
	@Override
	public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id,String zy_id) {
		if(null==zy_id||"".equals(zy_id)){
			System.out.println("KQXX_BJXXServiceImpl中打印::"+"专业ID为空，调用全校方法...");
			return getBJKQXX(jsid, jgid, ksid, kcbid, xq, xingqi, zhou, ksrq, jsrq,xy_id);
		}
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		int countYCXRS=0;
		int countQUEXIRS=0;
		int countZHENGCHANGRS=0;
		int countCHIDAORS=0;
		int countZAOTUIRS=0;
		int countCHIZAORS=0;
		String hql="from KQXX_BJXX d where d.KCB_ID in(select c.KCB_ID from KECHENGB c where c.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID='"+zy_id+"'))";
					
		if (null !=jsid&&!"".equals(jsid)&&!"".equals(jsid)){ //增加教室ID
			hql+=" and d.JS_ID="+"'"+jsid+"'";
		}
		if (null !=  jgid && !"".equals(jgid)){//增加老师ID
			hql+=" and d.LS_ID="+"'"+jgid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			hql+=" and d.KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			hql+=" and d.KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			hql+=" and d.SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			hql+=" and d.SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=xq&&!"".equals(xq)){
			hql+="and  SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
		}
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String>riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=hql;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and d.SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and d.SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"分转业：周不为空时，拼凑的hql：："+tmphql);
					List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
					for(KQXX_BJXX b:all_kq1){
						countYCXRS+=b.getYSKRS();
						countQUEXIRS+=b.getQXRS();
						countZHENGCHANGRS+=b.getZCCXRS();
						countCHIDAORS+=b.getCDRS();
						countZAOTUIRS+=b.getZTRS();
						if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
							countCHIZAORS+=b.getCDZTRS();	
						}
					}
				}
		}else{ //如果周为空
			String tmphql=hql;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and d.XINGQI="+xingqi;
			}
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"分专业：周是空时，拼凑的hql：："+tmphql);
				List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
				for(KQXX_BJXX b:all_kq1){
					countYCXRS+=b.getYSKRS();
					countQUEXIRS+=b.getQXRS();
					countZHENGCHANGRS+=b.getZCCXRS();
					countCHIDAORS+=b.getCDRS();
					countZAOTUIRS+=b.getZTRS();
					if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
						countCHIZAORS+=b.getCDZTRS();	
					}
				}
		}
		map.put("ycxrs", countYCXRS);
		map.put("qxrs",countQUEXIRS);
		map.put("zcrs", countZHENGCHANGRS);
		map.put("cdrs",countCHIDAORS);
		map.put("ztrs",countZAOTUIRS);
		map.put("czrs",countCHIZAORS);
		return map;
	}
	
	//统计考勤信息，分学院-->专业-->年级
	@Override
	public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id,String zy_id,String nj_id) {
		if(null==xy_id||"".equals(xy_id)){
			System.out.println("KQXX_BJXXServiceImpl中打印::"+"学院ID为空，调用全校方法...");
			return getBJKQXX(jsid, jgid, ksid, kcbid, xq, xingqi, zhou, ksrq, jsrq);
		}
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		int countYCXRS=0;
		int countQUEXIRS=0;
		int countZHENGCHANGRS=0;
		int countCHIDAORS=0;
		int countZAOTUIRS=0;
		int countCHIZAORS=0;
		String hql="from KQXX_BJXX d where d.KCB_ID in(select c.KCB_ID from KECHENGB c where c.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID in (select a.ZY_ID from ZHUANYE a where a.XYID='"+xy_id+"')))";
		if (null !=jsid&&!"".equals(jsid)&&!"".equals(jsid)){ //增加教室ID
			hql+=" and d.JS_ID="+"'"+jsid+"'";
		}
		if (null !=  jgid && !"".equals(jgid)){//增加老师ID
			hql+=" and d.LS_ID="+"'"+jgid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			hql+=" and d.KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			hql+=" and d.KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			hql+=" and d.SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			hql+=" and d.SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=xq&&!"".equals(xq)){
			hql+="and  SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
		}
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String>riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=hql;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and d.SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and d.SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"分学院：周不为空时，拼凑的hql：："+tmphql);
					List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
					for(KQXX_BJXX b:all_kq1){
						countYCXRS+=b.getYSKRS();
						countQUEXIRS+=b.getQXRS();
						countZHENGCHANGRS+=b.getZCCXRS();
						countCHIDAORS+=b.getCDRS();
						countZAOTUIRS+=b.getZTRS();
						if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
							countCHIZAORS+=b.getCDZTRS();	
						}
					}
				}
		}else{ //如果周为空
			String tmphql=hql;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and d.XINGQI="+"'"+xingqi+"'";
			}
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"分学院：周是空时，拼凑的hql：："+tmphql);
				List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
				for(KQXX_BJXX b:all_kq1){
					countYCXRS+=b.getYSKRS();
					countQUEXIRS+=b.getQXRS();
					countZHENGCHANGRS+=b.getZCCXRS();
					countCHIDAORS+=b.getCDRS();
					countZAOTUIRS+=b.getZTRS();
					if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
						countCHIZAORS+=b.getCDZTRS();	
					}
				}
		}
		map.put("ycxrs", countYCXRS);
		map.put("qxrs",countQUEXIRS);
		map.put("zcrs", countZHENGCHANGRS);
		map.put("cdrs",countCHIDAORS);
		map.put("ztrs",countZAOTUIRS);
		map.put("czrs", countCHIZAORS);
		return map;
	}

	//统计考勤信息，分学院-->专业-->年级-->班级
	@Override
	public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id,String zy_id,String nj_id,String bj_id) {
		if(null==xy_id||"".equals(xy_id)){
			System.out.println("KQXX_BJXXServiceImpl中打印::"+"学院ID为空，调用全校方法...");
			return getBJKQXX(jsid, jgid, ksid, kcbid, xq, xingqi, zhou, ksrq, jsrq);
		}
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		int countYCXRS=0;
		int countQUEXIRS=0;
		int countZHENGCHANGRS=0;
		int countCHIDAORS=0;
		int countZAOTUIRS=0;
		int countCHIZAORS=0;
		String hql="from KQXX_BJXX d where d.KCB_ID in(select c.KCB_ID from KECHENGB c where c.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID in (select a.ZY_ID from ZHUANYE a where a.XYID='"+xy_id+"')))";
		if (null !=jsid&&!"".equals(jsid)&&!"".equals(jsid)){ //增加教室ID
			hql+=" and d.JS_ID="+"'"+jsid+"'";
		}
		if (null !=  jgid && !"".equals(jgid)){//增加老师ID
			hql+=" and d.LS_ID="+"'"+jgid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			hql+=" and d.KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			hql+=" and d.KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			hql+=" and d.SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			hql+=" and d.SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=xq&&!"".equals(xq)){
			hql+="and  SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
		}
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String>riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=hql;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and d.SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and d.SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"分学院：周不为空时，拼凑的hql：："+tmphql);
					List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
					for(KQXX_BJXX b:all_kq1){
						countYCXRS+=b.getYSKRS();
						countQUEXIRS+=b.getQXRS();
						countZHENGCHANGRS+=b.getZCCXRS();
						countCHIDAORS+=b.getCDRS();
						countZAOTUIRS+=b.getZTRS();
						if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
							countCHIZAORS+=b.getCDZTRS();
						}
					}
				}
		}else{ //如果周为空
			String tmphql=hql;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and d.XINGQI="+"'"+xingqi+"'";
			}
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"分学院：周是空时，拼凑的hql：："+tmphql);
				List<KQXX_BJXX> all_kq1=baseDao.findAll(tmphql,KQXX_BJXX.class);
				for(KQXX_BJXX b:all_kq1){
					countYCXRS+=b.getYSKRS();
					countQUEXIRS+=b.getQXRS();
					countZHENGCHANGRS+=b.getZCCXRS();
					countCHIDAORS+=b.getCDRS();
					countZAOTUIRS+=b.getZTRS();
					if(null!=b.getCDZTRS()&&!"".equals(b.getCDZTRS())){
						countCHIZAORS+=b.getCDZTRS();	
					}
				}
		}
		map.put("ycxrs", countYCXRS);
		map.put("qxrs",countQUEXIRS);
		map.put("zcrs", countZHENGCHANGRS);
		map.put("cdrs",countCHIDAORS);
		map.put("ztrs",countZAOTUIRS);
		map.put("czrs", countCHIZAORS);
		return map;
	}
	
	
	//统计考勤信息，分教师
	@Override
	public HashMap<String, Integer> getBJXXKQXXbyJG(String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq) {
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		String sql1="select t.kcb_id,t.kcmc,sum(t.yskrs) 应出席人数,sum(t.zccxrs) 正常出席人数,sum(t.qxrs) 缺席人数,sum(t.cdrs) 迟到人数,sum(t.ztrs) 早退人数,sum(t.cdztrs) 迟到早退人数 from kqxx_bjxx t where 1=1 ";
		String sql2=" group by t.kcb_id,t.kcmc";
		if (null !=  jgid && !"".equals(jgid)){//增加老师ID
			sql1+=" and t.LS_ID='"+jgid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			sql1+=" and t.KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			sql1+=" and t.KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			sql1+=" and t.SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			sql1+=" and t.SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=xq&&!"".equals(xq)){
			sql1+="and  t.SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
		}
		List allcq=null;
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String>riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=sql1;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and t.SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and t.SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					tmphql+=sql2;
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"周为空时，分老师统计考勤信息：："+tmphql);
					allcq=baseDao.executeSQL(tmphql);
				}
		}else{ //如果周为空
			String tmphql=sql1;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and t.XINGQI="+xingqi;
			}
			tmphql+=sql2;
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"周为空时，分老师统计考勤信息：："+tmphql);
				allcq=baseDao.executeSQL(tmphql);
		}
		if(null!=allcq){//如果有结果集
			if(allcq.size()>0){
				Object[] tmpObjArray=(Object[]) allcq.get(0);
				String kcb_id=(null==tmpObjArray[0])?"0":tmpObjArray[0].toString(); //课程表ID
				String kcb_kcmc=(null==tmpObjArray[1])?"0":tmpObjArray[1].toString();//课程表名称
				String ycxrs=(null==tmpObjArray[2])?"0":tmpObjArray[2].toString();//应出席人数
				String zccxrs=(null==tmpObjArray[3])?"0":tmpObjArray[3].toString();//正常出席人数
				String qxrs=(null==tmpObjArray[4])?"0":tmpObjArray[4].toString();//缺席人数
				String cdrs=(null==tmpObjArray[5])?"0":tmpObjArray[5].toString();//
				String ztrs=(null==tmpObjArray[6])?"0":tmpObjArray[6].toString();
				String cdztrs=(null==tmpObjArray[7])?"0":tmpObjArray[7].toString();
				map.put("ycxrs", Integer.parseInt(ycxrs));
				map.put("qxrs",Integer.parseInt(qxrs));
				map.put("zcrs",Integer.parseInt(zccxrs));
				map.put("cdrs",Integer.parseInt(cdrs));
				map.put("ztrs",Integer.parseInt(ztrs));
				map.put("czrs",Integer.parseInt(cdztrs));
				return map;
			}
		}
		return null;
	}
	
	//统计考勤信息，分教师
	@Override
	public HashMap<String, Integer> getBJXXKQXXbyJS(String jsid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq) {
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		String sql1="select t.kcb_id,t.kcmc,sum(t.yskrs) 应出席人数,sum(t.zccxrs) 正常出席人数,sum(t.qxrs) 缺席人数,sum(t.cdrs) 迟到人数,sum(t.ztrs) 早退人数,sum(t.cdztrs) 迟到早退人数 from kqxx_bjxx t where 1=1 ";
		String sql2=" group by t.kcb_id,t.kcmc";
		if (null !=  jsid && !"".equals(jsid)){//增加教室ID
			sql1+=" and t.JS_ID='"+jsid+"'";
		}
		if (null !=  ksid && !"".equals(ksid)){//增加课时id
			sql1+=" and t.KESHI_ID="+"'"+ksid+"'";
		}
		if (null !=  kcbid && !"".equals(kcbid)){//增加课程表ID
			sql1+=" and t.KCB_ID="+"'"+kcbid+"'";
		}
		if (null !=  ksrq && !"".equals(ksrq)){//增加开始日期
			sql1+=" and t.SKSJ>=to_date('"+ksrq+"','yyyy-mm-dd')";
		}
		if (null !=  jsrq && !"".equals(jsrq)){//增加结束日期
			sql1+=" and t.SKSJ<=to_date('"+jsrq+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=xq&&!"".equals(xq)){
			sql1+="and  t.SKSJ between to_date('"+xq.getKSRQ().trim()+"','yyyy-mm-dd') and to_date('"+xq.getJSRQ().trim()+"','yyyy-mm-dd')";
		}
		List allcq=null;
		if (null !=  zhou&&!"".equals(zhou)){//周不为空
				//如果星期不为空，则计算星期中课时
				List<String>riqi=BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				for(int i=0;i<riqi.size();i++){
					String riqitmp=riqi.get(i);
					String tmphql=sql1;
					if (null !=  riqitmp && !"".equals(riqitmp)){//增加课程表ID
						tmphql+=" and t.SKSJ>=to_date('"+riqitmp+"','yyyy-mm-dd') and t.SKSJ<=to_date('"+riqitmp+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
					}
					tmphql+=sql2;
					System.out.println("KQXX_BJXXServiceImpl中打印::"+"周为空时，分老师统计考勤信息：："+tmphql);
					allcq=baseDao.executeSQL(tmphql);
				}
		}else{ //如果周为空
			String tmphql=sql1;
			if(null!=xingqi&&!"".equals(xingqi)){
				tmphql+=" and t.XINGQI="+"'"+xingqi+"'";
			}
			tmphql+=sql2;
				System.out.println("KQXX_BJXXServiceImpl中打印::"+"周为空时，分老师统计考勤信息：："+tmphql);
				allcq=baseDao.executeSQL(tmphql);
		}
		if(null!=allcq){//如果有结果集
			if(allcq.size()>0){
				Object[] tmpObjArray=(Object[]) allcq.get(0);
				String kcb_id=(null==tmpObjArray[0])?"0":tmpObjArray[0].toString(); //课程表ID
				String kcb_kcmc=(null==tmpObjArray[1])?"0":tmpObjArray[1].toString();//课程表名称
				String ycxrs=(null==tmpObjArray[2])?"0":tmpObjArray[2].toString();//应出席人数
				String zccxrs=(null==tmpObjArray[3])?"0":tmpObjArray[3].toString();//正常出席人数
				String qxrs=(null==tmpObjArray[4])?"0":tmpObjArray[4].toString();//缺席人数
				String cdrs=(null==tmpObjArray[5])?"0":tmpObjArray[5].toString();//
				String ztrs=(null==tmpObjArray[6])?"0":tmpObjArray[6].toString();
				String cdztrs=(null==tmpObjArray[7])?"0":tmpObjArray[7].toString();
				map.put("ycxrs", Integer.parseInt(ycxrs));
				map.put("qxrs",Integer.parseInt(qxrs));
				map.put("zcrs",Integer.parseInt(zccxrs));
				map.put("cdrs",Integer.parseInt(cdrs));
				map.put("ztrs",Integer.parseInt(ztrs));
				map.put("czrs",Integer.parseInt(cdztrs));
				return map;
			}
		}
		return null;
	}
}