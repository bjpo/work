package com.hrbsys.xueyuan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.xueyuan.service.XueYuanService;

/**
 * 学院管理接口实现类
 * 
 * @author
 * 
 */
public class XueYuanServiceImpl implements XueYuanService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	//查询学院并进行排序与分页
	@Override
	public List<XUEYUAN> findXUEYUANByPage(int start, int number, HashMap<String, String> params, String order, String sort) {
		
		String hql = "from XUEYUAN where 1=1";
		
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("xymc")&& !"".equals(params.get("xymc"))) {
			String param = " and XYMC like '%"+ params.get("xymc") + "%'";
			params2.add(param);
		}
		List<XUEYUAN> list = baseDao.findByPage(hql, XUEYUAN.class, start,number, params2, order, sort);
		return list;
	}

	// 根据XUEYUAN表中的Id进行查询返回
	@Override
	public XUEYUAN findXueYuanById(String xy_id) {
		System.out.println("打印学院sql：from XUEYUAN where XY_ID='" + xy_id + "'");
		List<XUEYUAN> a=baseDao.findAll("from XUEYUAN where XY_ID='" + xy_id + "'",
				XUEYUAN.class);
		if(a.size()>0){
			return a.get(0);	
		}
		return null;
	}

	// 添加学院（向将数据库中的XUEYUAN表中添加学院数据）

	@Override
	public boolean addXueYuan(XUEYUAN xy) {
		return baseDao.save(xy);		
	}

	// 删除学院（将数据库中的XUEYUAN表中学院数据删除)
	@Override
	public boolean delXueYuan(String xy_id) {
		String[] ids = xy_id.split(",");
		for (String id : ids) {
			XUEYUAN xy = findXueYuanById(id);
			if (!baseDao.delete(xy)) {
				return false;
			}
		}
		return true;
	}

	// 修改学院
	@Override
	public boolean updateXueYuan(XUEYUAN xy) {
		return baseDao.update(xy);
	}

	//查询数据的条目数
	@Override
	public int getCountXUEYUAN(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from XUEYUAN where 1=1";
		if (null != params.get("xymc")
				&& !"".equals(params.get("xymc"))) {
			hql += "and XYMC like '%" + params.get("xymc").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	//查询学院的名称放入到fxymc中去
	@Override
	public List<XUEYUAN> findXUEYUANByPageApp(HashMap<String, String> params,String order, String sort)
	{
		String hql = "from XUEYUAN where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("xymc")&& !"".equals(params.get("xymc"))) {
			String param = "and XYMC like '%"
					+ params.get("xymc").toString() + "%'";
			params2.add(param);
		}
		List<XUEYUAN> list = baseDao.findAll(hql,XUEYUAN.class);
		
		return list;
	}



}
