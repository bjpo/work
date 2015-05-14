package com.hrbsys.test.service.impl;

import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.test.bean.FKUser;
import com.hrbsys.test.dao.UserDao;
import com.hrbsys.test.service.UserService;

public class UserServiceImpl implements UserService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public List<FKUser> queryAllUsers() {
//		return userDao.queryAllUsers();
		List<FKUser> all= baseDao.findAll("from FKUser",FKUser.class);
			for(FKUser u:all){
				System.out.println(u.getUserId()+"-->"+u.getUsername()+"-->"+u.getUserpwd()+"\n");
			}
		return all;
	}

	public boolean saveUser(FKUser user) {
//		return userDao.saveUser(user);
		return baseDao.save(user);
	}
}
