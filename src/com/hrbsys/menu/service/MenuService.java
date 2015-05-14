package com.hrbsys.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.Menu;

/**
 * 菜单接口
 * 
 */
public interface MenuService {
	// 获取全部菜单分类
	public List<Menu> getAllMenuCate(Map<String, String> params);
	//根据菜单类别ID获取模块
	public List<MOKUAI> getMKWithMenuId(Map<String, String> params);
}
