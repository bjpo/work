package com.hrbsys.test.action;

import java.util.List;

import net.sf.json.JSONArray;


import com.hrbsys.base.action.ActionBase;
import com.hrbsys.test.bean.FKUser;
import com.hrbsys.test.service.UserService;

public class JsonTestAction extends ActionBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private JSONArray rows;


	public JSONArray getRows() {
		return rows;
	}

	public void setRows(JSONArray rows) {
		this.rows = rows;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		// boolean status = userService.saveUser(user);
		// System.out.println(status);
		// userService.queryAllUsers();
		List<FKUser> all=userService.queryAllUsers();
		rows=JSONArray.fromObject(all);
		System.out.println("AAAAAAAAAA::::::::::::::"+rows.toString());
		 return SUCCESS;
	}
}
