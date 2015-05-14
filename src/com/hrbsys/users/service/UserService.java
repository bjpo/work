package com.hrbsys.users.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.JUESE;
import com.hrbsys.bean.JUESE2MOKUAI;
import com.hrbsys.bean.JUESE2YONGHU;
import com.hrbsys.bean.JUESE2YONGHUZU;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;

public interface UserService {
	//用户组相关
	public boolean addYHZ(YONGHUZU y); 
	public boolean delYHZ(String y);
	public boolean updateYHZ(YONGHUZU yhz);
	public YONGHUZU findYHZ(String yhz_id);
	public List<YONGHUZU> findYONGHUZUByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountYONGHUZU(HashMap<String,String> params);
	List<YONGHUZU> findYONGHUZUByPageApp(HashMap<String, String> params,String order, String sort);
	//用户相关
	public boolean addYONGHU(YONGHU yh); 
	public boolean delYONGHU(String yh);
	public boolean updateYONGHU(YONGHU yh);
	public YONGHU findYONGHU(String yh_id);
	public boolean updateYONGHULoginTime(YONGHU yh);//修改用户登录的时间
	//根据用户名进行查询用户
	public  YONGHU findYONGHUName(String yh_name);
	public List<YONGHU> findYONGHUByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountYONGHU(HashMap<String,String> params);
	
	//角色相关
	public boolean addJUESE(JUESE js); 
	public boolean delJUESE(String js_id);
	public boolean updateJUESE(JUESE js);
	public JUESE findJUESE(String js_id);
	public List<JUESE> findJUESEByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountJUESE(HashMap<String,String> params);
	List<JUESE> findJUESEByPageApp(HashMap<String, String> params,String order, String sort);
	
	//模块相关
	public boolean addMOKUAI(MOKUAI mk); 
	public boolean delMOKUAI(String mk_id);
	public boolean updateMOKUAI(MOKUAI mk);
	public MOKUAI findMOKUAI(String mk_id);
	public List<MOKUAI> findMOKUAIByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountMOKUAI(HashMap<String,String> params);
	List<MOKUAI> findMOKUAIByPageApp(HashMap<String, String> params,String order, String sort);
	
	//角色赋予用户相关
	public boolean addJUESE2YONGHU(JUESE2YONGHU jy); 
	public boolean delJUESE2YONGHU(String jy_id);
	public boolean updateJUESE2YONGHU(JUESE2YONGHU jy);
	public JUESE2YONGHU findJUESE2YONGHU(String jy_id);
	public List<JUESE2YONGHU> findJUESE2YONGHUByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountJUESE2YONGHU(HashMap<String,String> params);
	List<JUESE2YONGHU> findJUESE2YONGHUByPageApp(HashMap<String, String> params,String order, String sort);
	public List<YONGHU> findYONGHUByPageApp(HashMap<String, String> params,String order, String sort);
	

	//角色赋予用户组相关
	public boolean addJUESE2YONGHUZU(JUESE2YONGHUZU jy); 
	public boolean delJUESE2YONGHUZU(String jy_id);
	public boolean updateJUESE2YONGHUZU(JUESE2YONGHUZU jy);
	public JUESE2YONGHUZU findJUESE2YONGHUZU(String jy_id);
	public List<JUESE2YONGHUZU> findJUESE2YONGHUZUByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountJUESE2YONGHUZU(HashMap<String,String> params);
	List<JUESE2YONGHUZU> findJUESE2YONGHUZUByPageApp(HashMap<String, String> params,String order, String sort);

	//角色赋予模块相关
	public boolean addJUESE2MOKUAI(JUESE2MOKUAI jm);
	public boolean delJUESE2MOKUAI(String jm_id);
	public boolean updateJUESE2MOKUAI(JUESE2MOKUAI jm);
	public JUESE2MOKUAI findJUESE2MOKUAI(String jm_id);
	public List<JUESE2MOKUAI> findJUESE2MOKUAIByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountJUESE2MOKUAI(HashMap<String,String> params);
	List<JUESE2MOKUAI> findJUESE2MOKUAIByPageApp(HashMap<String, String> params,String order, String sort);
}
