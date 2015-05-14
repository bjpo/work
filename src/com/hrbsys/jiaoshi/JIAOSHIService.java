package com.hrbsys.jiaoshi;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.FANGJIAN;
import com.hrbsys.bean.JIAOSHI;

public interface JIAOSHIService {
	public boolean addJIAOSHI(JIAOSHI y);

	public boolean delJIAOSHI(String y);

	public boolean updateJIAOSHI(JIAOSHI yhz);

	public JIAOSHI findJIAOSHI(String yhz_id);
	
	public JIAOSHI findJIAOSHIByFJID(String fjid);
	
	public JIAOSHI findJIAOSHIByName(String name);

	public List<JIAOSHI> findJIAOSHIByPageApp(int start, int number, HashMap<String, String> params, String order, String sort);

	public int getCountJIAOSHI(HashMap<String, String> params);

	public List<JIAOSHI> findJIAOSHIByPageApp(HashMap<String, String> params, String order, String sort);
	
	public List<FANGJIAN> findFANGJIANByPageApp();
	
	public FANGJIAN findFANGJIAN(String id);
}