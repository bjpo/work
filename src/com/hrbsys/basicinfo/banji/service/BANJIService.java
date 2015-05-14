package com.hrbsys.basicinfo.banji.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.ZHUANYE;

public interface BANJIService {
	public boolean addBANJI(BANJI y);

	public boolean delBANJI(String y);

	public boolean updateBANJI(BANJI yhz);

	public BANJI findBANJI(String yhz_id);
	
	public BANJI findBANJIByName(String Name);
	
	public BANJI findBANJIByNIANJI(HashMap<String, String> params);
	
	public BANJI findBANJIByZHUANYE(HashMap<String, String> params);
	
	public NIANJI findNIANJI(String id);
	
	public ZHUANYE findZHUANYE(String id);

	public List<BANJI> findBANJIByPageApp(int start, int number, HashMap<String, String> params, String order, String sort);

	public int getCountBANJI(HashMap<String, String> params);

	List<BANJI> findBANJIByPageApp(HashMap<String, String> params, String order, String sort);

	public List<NIANJI> findNIANJI();
	public List<BANJI> findBANJIFORZYANDNIANJI(HashMap<String, String> params);
	public List<ZHUANYE> findZHUANYE();
	public List<BANJI> findXSByZYandNJandBJ(String zy_id,String nj_id,String bj_id);
	
	
}