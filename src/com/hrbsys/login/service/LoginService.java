package com.hrbsys.login.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hrbsys.bean.JUESE;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.MenuTree;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;

public interface LoginService {
	//获取用户
	public YONGHU getYONGHUbyYHMCandYHMM(String uname,String upassword);
	//获取用户对应的角色
	public List<JUESE> getJUESEbyYONGHU(YONGHU y);
	
	//获取用户所属用户组
	public List<YONGHUZU> getYONGHUZUforYONGHU(YONGHU y);
	//获取用户组的角色
	public List<JUESE> getJUESEbyYONGHUZU(YONGHUZU yz);

	//获取用户组的角色
	public List<JUESE> getJUESEbyYONGHUZU(List<YONGHUZU> allyz);

	//获取模块信息
	public List<MOKUAI> getMOKUAIbyJUESE(JUESE js);
	//获取模块信息
	public List<MOKUAI> getMOKUAIbyJUESE(List<JUESE> alljuese);
	public Map<String,MenuTree> getAllMenuTree(List<MOKUAI> allmk,String id);
	public Set<MenuTree> tranMapToList(Map<String,MenuTree> allmap);
}
