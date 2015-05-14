package com.hrbsys.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.Menu;
import com.hrbsys.bean.MenuTree;
import com.hrbsys.menu.service.MenuService;

/**
 * 菜单接口实现类
 */
public class MenuServiceImpl implements MenuService {
	private BaseDao baseDao;

	// 获取全部菜单分类
	public List<Menu> getAllMenuCate(Map<String, String> params) {
		String hql = "from Menu where 1=1";
		if (params.get("menuCateId") != null
				&& !"".equals(params.get("menuCateId").toString())) {
			hql += " and menuCateId='" + params.get("menuCateId").toString()
					+ "'";
		}
		return baseDao.findAll(hql, Menu.class);
	}

	// 根据菜单类别ID获取模块
	@Override
	public List<MOKUAI> getMKWithMenuId(Map<String, String> params) {
		String hql = "from MOKUAI where 1=1";
		if (params.get("menuCateId") != null
				&& !"".equals(params.get("menuCateId").toString())) {
			hql += " and menuCateId='" + params.get("menuCateId").toString()
					+ "'";
		}
		if (params.get("mkId") != null
				&& !"".equals(params.get("mkId").toString())) {
			hql += " and mkid='" + params.get("mkId").toString() + "'";
		}
		if ( baseDao.findAll(hql, MOKUAI.class)!=null) {
			return  baseDao.findAll(hql, MOKUAI.class);
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
