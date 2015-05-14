package com.hrbsys.menu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.KECHENGXX;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.Menu;
import com.hrbsys.bean.MenuTree;
import com.hrbsys.bean.SBXX;
import com.hrbsys.login.service.LoginService;
import com.hrbsys.menu.service.MenuService;
import com.hrbsys.tools.JsonPrintTools;

/**
 * 菜单Action
 * 
 */
public class MenuAction extends ActionBase {
	private String menuCateId;// 菜单类别ID

	private MenuService menuService;// 菜单接口
	private LoginService loginService;// 登录接口

	/*
	 * 获取全部分类
	 */
	public void getAllMenuCate() {
		JsonConfig config = new JsonConfig();
		// 存放参数
		Map<String, String> params = new HashMap<String, String>();
		// 通过session获取所有模块
		List<MOKUAI> allmk = (List<MOKUAI>) session.get("allmokuai");
		// 用于将菜单类别ID去重重
		Set set = new LinkedHashSet();
		// 用于存放菜单类别
		List<Menu> allMenuCate = new ArrayList<Menu>();
		// 获取模块的menuCateId
		for (MOKUAI mk : allmk) {
			// 判断set中是否以存在菜单类别ID
			if (set.contains(mk.getMenuCateId()) || mk.getMenuCateId() == null) {
				// 如果存在，就继续执行
				continue;
			}
			// 将菜单类别ID放入到set中
			set.add(mk.getMenuCateId());
		}
		// 进行遍历菜单类别ID
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String id = (String) it.next();
			params.put("menuCateId", id);
			if (menuService.getAllMenuCate(params) != null) {
				allMenuCate.add(menuService.getAllMenuCate(params).get(0));
			}
		}
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(allMenuCate,config));
	}

	/*
	 * 获取菜单类别中的树形菜单
	 */
	public void getMKWithMenuId() {
		// 存放参数
		Map<String, String> params = new HashMap<String, String>();
		List<MOKUAI> allmk = (List<MOKUAI>) session.get("allmokuai");
		List<MOKUAI> allMoKuai = new LinkedList<MOKUAI>();
		Set set = new LinkedHashSet();
		// 获取模块的menuCateId
		for (MOKUAI mk : allmk) {
			if (set.contains(mk.getMkid()) || mk.getMkid() == null) {
				continue;
			}
			// 放入到set中
			set.add(mk.getMkid());
		}
		// 进行遍历
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String mkId = (String) it.next();
			params.put("menuCateId", this.menuCateId);
			params.put("mkId", mkId);
			// 获取模块的集合
			if (menuService.getMKWithMenuId(params).size() > 0) {
				allMoKuai.add(menuService.getMKWithMenuId(params).get(0));
			}
		}
		Map<String, MenuTree> allmt = loginService
				.getAllMenuTree(allMoKuai, "");
		Set<MenuTree> alltree = loginService.tranMapToList(allmt);
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(alltree));
	}

	public void listAllMenu() throws Exception {
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String getMenuCateId() {
		return menuCateId;
	}

	public void setMenuCateId(String menuCateId) {
		this.menuCateId = menuCateId;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
