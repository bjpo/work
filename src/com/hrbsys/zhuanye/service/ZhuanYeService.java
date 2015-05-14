package com.hrbsys.zhuanye.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.ZHUANYE;

public interface ZhuanYeService {
	public boolean addZHUANYE(ZHUANYE y);

	public boolean delZHUANYE(String y);

	public boolean updateZHUANYE(ZHUANYE yhz);

	public ZHUANYE findZHUANYE(String yhz_id);

	public List<ZHUANYE> findZHUANYEByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);

	public int getCountZHUANYE(HashMap<String, String> params);

	List<ZHUANYE> findZHUANYEByPageApp(HashMap<String, String> params,String order, String sort);
	List<ZHUANYE> findZHUANYEByXueYuan(String xy_id);
}