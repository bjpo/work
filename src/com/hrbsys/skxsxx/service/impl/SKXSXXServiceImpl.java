package com.hrbsys.skxsxx.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.SKXSXX;
import com.hrbsys.skxsxx.service.SKXSXXService;
import com.hrbsys.tools.TeamPrint;
public class SKXSXXServiceImpl implements SKXSXXService {
	private BaseDao baseDao;	
	public BaseDao getBaseDao() {
   	return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
   	this.baseDao = baseDao;
	}

    //add
	@Override
    public boolean addSKXSXX(SKXSXX y) {
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
       return baseDao.save(y);
    }

    //delete
	@Override 
 	public boolean delSKXSXX(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {SKXSXX yhz=findSKXSXX(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	@Override 
 	public boolean delSKXSXXbyFXS_ID(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			//分学时ID找到相应的上课学生信息
			String delsql="delete from skxsxx where kcb_fxs_id='"+id+"'";
			if(!baseDao.executeDoSQL(delsql)){
				return false;
			}
			//删除掉
		}
		return true;
	}

    //update
	@Override 
	public boolean updateSKXSXX(SKXSXX t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SKXSXX tmpt = baseDao.findAll("from SKXSXX where KCXS_ID='" + t.getKCXS_ID() + "'",SKXSXX.class).get(0);
   	    tmpt.setKCB_KCXXMC(t.getKCB_KCXXMC());
   	    tmpt.setXSXM(t.getXSXM());
   	    tmpt.setXUEHAO(t.getXUEHAO());
   	    tmpt.setKCB_ID(t.getKCB_ID());
   	    tmpt.setXS_ID(t.getXS_ID());
//		tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

    //findById
	@Override
	public SKXSXX findSKXSXX(String t_id) {
		List<SKXSXX> slist=baseDao.findAll("from SKXSXX where KCXS_ID='" + t_id + "'",SKXSXX.class);
		if(slist.size()>0){
			return slist.get(0);	
		}
//		return new SKXSXX();
		return null;
	}

    //findByPage
	@Override
	public List<SKXSXX> findSKXSXXByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {
		String hql = "from SKXSXX where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KCB_KCXXMC") && !"".equals(params.get("KCB_KCXXMC"))) {
			param = "and KCB_KCXXMC like '%" + params.get("KCB_KCXXMC").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XSXM") && !"".equals(params.get("XSXM"))) {
			param = "and XSXM like '%" + params.get("XSXM").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		if (null != params.get("XUEHAO") && !"".equals(params.get("XUEHAO"))) {
			param = "and XUEHAO like '%" + params.get("XUEHAO").toString()
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
		if (null != params.get("XS_ID") && !"".equals(params.get("XS_ID"))) {
			param = "and XS_ID like '%" + params.get("XS_ID").toString()
				+ "%'";
			params2.add(param);
			param="";
		}
		List<SKXSXX> list = baseDao.findByPage(hql, SKXSXX.class, start,
		number, params2, order, sort);
		return list;
	}

    //getCount
	@Override
	public int getCountSKXSXX(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from SKXSXX where 1=1 ";
		if (null != params.get("KCB_KCXXMC")
				&& !"".equals(params.get("KCB_KCXXMC"))) {
			hql += "and KCB_KCXXMC like '%" + params.get("KCB_KCXXMC").toString()
					+ "%'";
		}
		if (null != params.get("XSXM")
				&& !"".equals(params.get("XSXM"))) {
			hql += "and XSXM like '%" + params.get("XSXM").toString()
					+ "%'";
		}
		if (null != params.get("XUEHAO")
				&& !"".equals(params.get("XUEHAO"))) {
			hql += "and XUEHAO like '%" + params.get("XUEHAO").toString()
					+ "%'";
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			hql += "and KCB_ID like '%" + params.get("KCB_ID").toString()
					+ "%'";
		}
		if (null != params.get("XS_ID")
				&& !"".equals(params.get("XS_ID"))) {
			hql += "and XS_ID like '%" + params.get("XS_ID").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

    //findbypagelike
	@Override
	public List<SKXSXX> findSKXSXXByPageApp(HashMap<String, String> params, String order, String sort) {
		String hql = "from SKXSXX where 1=1";
		String param="";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("KCB_KCXXMC")
				&& !"".equals(params.get("KCB_KCXXMC"))) {
			param = "and KCB_KCXXMC like '%"
					+ params.get("KCB_KCXXMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XSXM")
				&& !"".equals(params.get("XSXM"))) {
			param = "and XSXM like '%"
					+ params.get("XSXM").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XUEHAO")
				&& !"".equals(params.get("XUEHAO"))) {
			param = "and XUEHAO like '%"
					+ params.get("XUEHAO").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("KCB_ID")
				&& !"".equals(params.get("KCB_ID"))) {
			param = "and KCB_ID like '%"
					+ params.get("KCB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("XS_ID")
				&& !"".equals(params.get("XS_ID"))) {
			param = "and XS_ID like '%"
					+ params.get("XS_ID").toString() + "%'";
			params2.add(param);
		}
		List<SKXSXX> list = baseDao.findAll(hql,SKXSXX.class);
		return list;
	}
	@Override
	public boolean delSKXSXXbyKCB_FXS_ID(String y) {
		boolean flag=true;
		try {
			String[] ids = y.split(",");
			for (String id : ids) {
				baseDao.executeDoSQL("delete from skxsxx t where t.kcb_fxs_id='"+id+"'");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	
	
	
	@Override
	public int getCountSKXS(HashMap<String, String> params) {
		String hql = "select count(distinct XS_ID) from SKXSXX where 1=1 ";
		if (null != params.get("KCB_ID")&& !"".equals(params.get("KCB_ID"))) {
			hql += "and KCB_ID like '%" + params.get("KCB_ID").toString()+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class).get(0).toString());
		return count;
	}
	
	
	public boolean isSelectedKC(String xs_id,String kcb_id){
		String sql="select * from skxsxx where kcb_id='"+kcb_id+"' and xs_id='"+xs_id+"'";
		List allsqllist=baseDao.executeSQL(sql);
		TeamPrint.println(SKXSXXServiceImpl.class,"isSelectedKC", "SQL语句",sql);
		TeamPrint.println(SKXSXXServiceImpl.class,"isSelectedKC", "比对",allsqllist.size()+"   -->    "+(allsqllist.size()<1));
		
		if (allsqllist.size()<1)
			return false;
		return true;
	}
}