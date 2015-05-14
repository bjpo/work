package com.hrbsys.basicinfo.building.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.JXL;
import com.hrbsys.bean.NIANJI;


public interface BuildingService {
	public boolean addJXL(JXL o);
	public boolean delJXL(String jxl_id);
	public boolean updateJXL(JXL o);
	public JXL findJXLById(String jxl_id);
	public List<JXL> findJXLByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public int getCountJXL(HashMap<String, String> params);
	List<JXL> findJXLByPageApp(HashMap<String, String> params, String order, String sort);
}
