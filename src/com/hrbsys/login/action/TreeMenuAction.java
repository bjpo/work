package com.hrbsys.login.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.Menu;
import com.hrbsys.bean.MenuTree;
import com.hrbsys.login.service.LoginService;
import com.hrbsys.menu.service.MenuService;
import com.hrbsys.tools.JsonPrintTools;

public class TreeMenuAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	private LoginService lservice;
	private String id;

	//生成菜单树
	public void getMenuTree() throws Exception{
		//获取模块列表
		@SuppressWarnings("unchecked")
		List<MOKUAI> allmk=(List<MOKUAI>) session.get("allmokuai");
		//根据模块列表新增tree对象。
		if(null!=allmk){
			//设置treemenu对象。
			Map<String,MenuTree> allmt=lservice.getAllMenuTree(allmk,"");
			Set<MenuTree> alltree=lservice.tranMapToList(allmt);
			//前台打印输出tree对象。
			new JsonPrintTools().printJSON_Array(JSONArray.fromObject(alltree));
			return;
		}
	}
	
	public LoginService getLservice() {
		return lservice;
	}
	public void setLservice(LoginService lservice) {
		this.lservice = lservice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
