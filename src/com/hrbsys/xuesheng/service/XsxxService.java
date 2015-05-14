package com.hrbsys.xuesheng.service;

import java.util.List;

import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZHUANYE;

public interface XsxxService {
	public Xsxx findStudentById(String id);

	public boolean addStudent(Xsxx o);

	public boolean updateStudent(Xsxx o);

	public boolean RecordOptLog(UPDATEVERSION o);

	public List<Xsxx> findBANJIByZYandNJ(String zy_ID, String nj_ID, String bj_ID);

	public Xsxx findStudentByXSId(String id);

	public XUEYUAN findXUEYUANById(String id);

	public ZHUANYE findZHUANYEById(String id);

	public NIANJI findNIANJIById(String id);

	public BANJI findBANJIById(String id);
	public Xsxx findStudentByYhId(String yhid);
}
