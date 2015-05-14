package com.hrbsys.sbxx.service;
import java.util.HashMap;

import java.util.List;

import com.hrbsys.bean.SBXX;
/**
 * 设备信息接口
 */
public interface SBXXService {
	public boolean addSBXX(SBXX y);
	public boolean delSBXX(String y);
	public boolean updateSBXX(SBXX yhz);
	public SBXX findSBXX(String yhz_id);
	public List<SBXX> findSBXXByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);
	public int getCountSBXX(HashMap<String, String> params);
	List<SBXX> findSBXXByPageApp(HashMap<String, String> params, String order,
			String sort);
	//查询设备名称是否存在
	public boolean findName(String sbName);

}