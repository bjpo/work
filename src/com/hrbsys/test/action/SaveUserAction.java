package com.hrbsys.test.action;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.test.bean.FKUser;
import com.hrbsys.test.service.UserService;
import com.hrbsys.tools.UUIDTools;

public class SaveUserAction extends ActionBase {
	private UserService userService;
	private String username;
	private String userpwd;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	@Override
	public String execute() throws Exception {
		FKUser user = new FKUser();
		user.setUsername(this.getUsername());
		user.setUserpwd(this.getUserpwd());
		user.setUserId(UUIDTools.getUUID());
		// boolean status = userService.saveUser(user);
		// System.out.println(status);
		// userService.queryAllUsers();
		session.put("aaa", "aaaa");
		request.put("bbb", "bbb");
		application.put("ccc", "ccc");
		userService.saveUser(user);
		return SUCCESS;
	}
}
