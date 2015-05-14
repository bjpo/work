package com.hrbsys.qjxx.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.QJXX;
import com.hrbsys.bean.Xsxx;

public interface QJXXService {
	public boolean addQJXX(QJXX y);

	public boolean delQJXX(String y);

	public boolean updateQJXX(QJXX yhz);

	public QJXX findQJXX(String yhz_id);

	public List<QJXX> findQJXXByPageApp(int start, int number, HashMap<String, String> params, String order, String sort);

	public int getCountQJXX(HashMap<String, String> params);

	List<QJXX> findQJXXByPageApp(HashMap<String, String> params, String order, String sort);

	public Xsxx findXsxxByXh(String xh);
	
	public JIAOGONG findJIAOGONGByGh(String gh);
	
	List<JIAOGONG> findJIAOGONGForQJ();
}