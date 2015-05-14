package com.hrbsys.test.service;

import java.util.List;

import com.hrbsys.test.bean.FKUser;

public interface UserService {
	public List<FKUser> queryAllUsers();
	boolean saveUser(FKUser user);
}
