package com.hrbsys.test.dao;
import java.util.List;

import com.hrbsys.test.bean.FKUser;
public interface UserDao {
	public List<FKUser> queryAllUsers();
	boolean saveUser(FKUser user);
}