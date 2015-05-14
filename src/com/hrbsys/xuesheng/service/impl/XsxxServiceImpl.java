package com.hrbsys.xuesheng.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.xuesheng.service.XsxxService;

public class XsxxServiceImpl implements XsxxService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Xsxx findStudentById(String id) {
		String hql = "from Xsxx where YH_ID='" + id + "'";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}

	@Override
	public Xsxx findStudentByXSId(String id) {
		String hql = "from Xsxx where xsId='" + id + "'";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}
	
	@Override
	public boolean addStudent(Xsxx o) {
		return baseDao.save(o);
	}

	@Override
	public boolean updateStudent(Xsxx o) {
		return baseDao.update(o);
	}

	@Override
	public boolean RecordOptLog(UPDATEVERSION o) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		o.setUPDATETIME(df.format(new Date()).toString());
		String hql = "from UPDATEVERSION where NAME = '" + o.getNAME() + "' and TYPE = '" + o.getTYPE() + "'";
		List<UPDATEVERSION> list = baseDao.findAll(hql, UPDATEVERSION.class);
		if(0 < list.size())
		{
			UPDATEVERSION uv = list.get(0);
			uv.setOPT(o.getOPT());
			uv.setUPDATETIME(o.getUPDATETIME());
			return baseDao.update(uv);
		}
		return baseDao.save(o);
	}

	
	@Override
	public List<Xsxx> findBANJIByZYandNJ(String zy_id, String nj_id,String bj_id) {
		String hql = "from Xsxx where 1=1";
		if (null != zy_id && !"".equals(zy_id)) {
			hql+=" and zyId='"+zy_id+"'";
		}
		if (null != nj_id && !"".equals(nj_id)) {
			hql+=" and njId='"+nj_id+"'";
		}
		if (null != bj_id && !"".equals(bj_id)) {
			hql+=" and bjId='"+bj_id+"'";
		}
		List<Xsxx> list = baseDao.findAll(hql,Xsxx.class);
		System.out.println(hql+"学生信息service中打印出来的：：：：：：：：：：：："+list.size());
		return list;
	}

	@Override
	public XUEYUAN findXUEYUANById(String id) {
		String hql = "from XUEYUAN where 1=1 and XY_ID = '" + id + "'";
		if (baseDao.findAll(hql, XUEYUAN.class).size() > 0) {
			return baseDao.findAll(hql, XUEYUAN.class).get(0);
		}
		return null;
	}

	@Override
	public ZHUANYE findZHUANYEById(String id) {
		String hql = "from ZHUANYE where 1=1 and ZY_ID = '" + id + "'";
		if (baseDao.findAll(hql, ZHUANYE.class).size() > 0) {
			return baseDao.findAll(hql, ZHUANYE.class).get(0);
		}
		return null;
	}

	@Override
	public NIANJI findNIANJIById(String id) {
		String hql = "from NIANJI where 1=1 and NJ_ID = '" + id + "'";
		if (baseDao.findAll(hql, NIANJI.class).size() > 0) {
			return baseDao.findAll(hql, NIANJI.class).get(0);
		}
		return null;
	}

	@Override
	public BANJI findBANJIById(String id) {
		String hql = "from BANJI where 1=1 and BJ_ID = '" + id + "'";
		if (baseDao.findAll(hql, BANJI.class).size() > 0) {
			return baseDao.findAll(hql, BANJI.class).get(0);
		}
		return null;
	}
	
	@Override
	public Xsxx findStudentByYhId(String yhid) {
		String hql = "from Xsxx where yhId='" + yhid + "'";
		if (baseDao.findAll(hql, Xsxx.class).size() > 0) {
			return baseDao.findAll(hql, Xsxx.class).get(0);
		}
		return null;
	}
	
}
