package com.hrbsys.xsxk.service.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.CKXX;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.SKXSXX;
import com.hrbsys.bean.XSXK;
import com.hrbsys.tools.TeamPrint;
import com.hrbsys.xsxk.service.XsXuanKeService;

public class XsXuanKeServiceImpl implements XsXuanKeService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	/***
	 * 获取数据并对其进行排序与分页
	 */
	public List<KECHENGB> findXSXKByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from KECHENGB where KCBLB='公共选修课'";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		List<KECHENGB> list = baseDao.findByPage(hql, KECHENGB.class, start,
				number, params2, order, sort);
		return list;
	}

	/**
	 * 获取数据并对其进行排序
	 */
	@Override
	public List<KECHENGB> findXSXKByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from KECHENGB where KCBLB='公共选修课'";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
			params2.add(param);
		}
		List<KECHENGB> list = baseDao.findAll(hql, KECHENGB.class);
		return list;
	}

	/**
	 * 获取符合条件的数据总条数
	 */
	@Override
	public int getCountXSXK(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from KECHENGB where KCBLB='公共选修课'";
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	/**
	 * 将学生选课信息添加到上课信息中
	 */
	@Override
	public boolean addSKXSXX(SKXSXX y) {
		return baseDao.save(y);
	}

	/**
	 * 删除上课信息
	 */
	@Override
	public boolean delSKXSXX(String y) {
		return baseDao.delete(y);
	}
	/**
	 * 查找上课的课程，通过sql方式查询
	 * */
	public List<KECHENGB> findGGK_XSXKKCByPage(int start, int number,HashMap<String, String> params, String order, String sort){
		List<KECHENGB> allkcb=new ArrayList<KECHENGB>();
		String sql="select t.kcb_id,t.laoshimc,t.laoshigh,t.kcxxmc,wmsys.wm_concat(t.ksmc||'') as ksmc ,wmsys.wm_concat(t.xingqi||'') ,wmsys.wm_concat(t.jsmc||''),t.numselected,t.capacity from kechengb t where t.kcblb='公共选修课' group by t.kcb_id,t.kcxxmc,t.laoshimc,t.laoshigh,t.numselected,t.capacity";
		List allsqllist=baseDao.executeSQL(sql);
		for(int i=0;i<allsqllist.size();i++){
			Object[] tmpObjArray=(Object[]) allsqllist.get(i);
			String kcb_id=(null==tmpObjArray[0])?"":tmpObjArray[0].toString();//课程表ID
			String laoshimc=(null==tmpObjArray[1])?"":tmpObjArray[1].toString();//老师姓名
			String laoshigh=(null==tmpObjArray[2])?"":tmpObjArray[2].toString();//老师工号
			String kcxxmc=(null==tmpObjArray[3])?"":tmpObjArray[3].toString();//课程信息名称
			String ksmc=(null==tmpObjArray[4])?"":tmpObjArray[4].toString();//课时名称
			String xingqi=(null==tmpObjArray[5])?"":tmpObjArray[5].toString();//星期
			String jsmc=(null==tmpObjArray[6])?"":tmpObjArray[6].toString();//教室名称
			String yxrs=(null==tmpObjArray[7])?"":tmpObjArray[7].toString();//已选经选课的人数
			String zrs=(null==tmpObjArray[8])?"":tmpObjArray[8].toString();//可选课总人数
			
			KECHENGB tmpkb=new KECHENGB();
			tmpkb.setKCB_ID(kcb_id);
			tmpkb.setLAOSHIMC(laoshimc);
			tmpkb.setLAOSHIGH(laoshigh);
			tmpkb.setKCXXMC(kcxxmc);
			tmpkb.setKSMC(ksmc);
			tmpkb.setXINGQI(xingqi);
			tmpkb.setJSMC(jsmc);
			tmpkb.setNUMSELECTED(yxrs);
			tmpkb.setCAPACITY(zrs);
			
			allkcb.add(tmpkb);
		}
		return allkcb;
	}
	
	
	/*时间是否冲突*/
	public boolean isSameTime(String kcb_id,String xs_id){
		String sql="select * from kechengb b where b.kcb_id='"+kcb_id+"' and b.xingqi in (select a.xingqi from kechengb a where a.kcb_id in (select distinct t.kcb_id from skxsxx t where t.xs_id='efe61c33-5ea9-439c-a53d-ee16086f35d6')) and b.ks_id in (select a.ks_id from kechengb a where a.kcb_id in (select distinct t.kcb_id from skxsxx t where t.xs_id='"+xs_id+"'))";
		TeamPrint.println(XsXuanKeServiceImpl.class,"isSameTime","SQL语句", sql);
		List allsqllist=baseDao.executeSQL(sql);
		if (allsqllist.size()==0) 
			return false;
		return true;
	}
	
}