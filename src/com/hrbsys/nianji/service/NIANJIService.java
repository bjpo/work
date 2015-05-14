package com.hrbsys.nianji.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.NIANJI;

public interface NIANJIService {
	public boolean addNIANJI(NIANJI y);

	public boolean delNIANJI(String y);

	public boolean updateNIANJI(NIANJI yhz);

	public NIANJI findNIANJI(String yhz_id);

	public NIANJI findNIANJIByName(String Name);

	public NIANJI findNIANJIByCode(String Code);

	public List<NIANJI> findNIANJIByPageApp(int start, int number, HashMap<String, String> params, String order, String sort);

	public int getCountNIANJI(HashMap<String, String> params);

	List<NIANJI> findNIANJIByPageApp(HashMap<String, String> params, String order, String sort);

}