package com.hrbsys.youjianlb.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.YOUJIANLB;

public interface YOUJIANLBService {
	public boolean addYOUJIANLB(YOUJIANLB y);

	public boolean delYOUJIANLB(String y);

	public boolean updateYOUJIANLB(YOUJIANLB yhz);

	public YOUJIANLB findYOUJIANLB(String yhz_id);
	
	public List<YOUJIANLB> findYOUJIANLBByPageApp(int start, int number, HashMap<String, String> params, String order, String sort);

	public int getCountYOUJIANLB(HashMap<String, String> params);

	List<YOUJIANLB> findYOUJIANLBByPageApp(HashMap<String, String> params, String order, String sort);

}