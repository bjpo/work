package com.hrbsys.basicinfo.student.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;

public interface StudentService {
	public boolean addStudent(Xsxx o);

	public boolean delStudent(String xs_id);


	public boolean updateStudent(Xsxx o);

	public Xsxx findStudentById(String xs_id);

	public Xsxx findStudentByXh(String xs_xh);

	public Xsxx findStudentByYHID(String yhid);
	
	public int getCountStudent(HashMap<String, String> params);

	public List<Xsxx> findStudentByPageApp(int start, int number, HashMap<String, String> params, String order, String sort);

	public List<Xsxx> findStudentByPageApp(HashMap<String, String> params, String order, String sort);

	public List<Xsxx> findStudentByBanJi(String banji);// 2014-05-29 于洋 加:课程表排课

	public boolean RecordOptLog(UPDATEVERSION o);
	
	public XUEYUAN findXUEYUANById(String id);
	
	public ZHUANYE findZHUANYEById(String id);
	
	public NIANJI findNIANJIById(String id);
	
	public BANJI findBANJIById(String id);
}
