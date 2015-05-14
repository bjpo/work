package com.hrbsys.luzhiwen.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;


public interface LuzhiwenService {

	public int getCountStudent(HashMap<String, String> params);
	public List<Xsxx> findStudentByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public List<Xsxx> findStudentByPageApp(HashMap<String, String> params,String order, String sort);
	public XUEYUAN findXUEYUANById(String id);
	public ZHUANYE findZHUANYEById(String id);
	public NIANJI  findNIANJIById(String id);
	public BANJI   findBANJIById(String id);
	public Xsxx    findStudentById(String id);
	public boolean updateStudent(Xsxx o);
	public boolean RecordOptLog(UPDATEVERSION o);
}
