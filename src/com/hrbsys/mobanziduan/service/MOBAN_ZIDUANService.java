package com.hrbsys.mobanziduan.service;

import java.util.HashMap;

import java.util.List;

import com.hrbsys.bean.MOBAN_ZIDUAN;

public interface MOBAN_ZIDUANService {
	public boolean addMOBAN_ZIDUAN(MOBAN_ZIDUAN y);

	public boolean delMOBAN_ZIDUAN(String y);

	public boolean updateMOBAN_ZIDUAN(MOBAN_ZIDUAN yhz);

	public MOBAN_ZIDUAN findMOBAN_ZIDUAN(String yhz_id);

	public List<MOBAN_ZIDUAN> findMOBAN_ZIDUANByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);

	public int getCountMOBAN_ZIDUAN(HashMap<String, String> params);

	List<MOBAN_ZIDUAN> findMOBAN_ZIDUANByPageApp(
			HashMap<String, String> params, String order, String sort);

	/** 根据模板的ID号在MOBAN_ZIDUAN表查询出所有的字段 */
	public List<MOBAN_ZIDUAN> getMOBAN_ZIDUANList(String sql);
	
	public boolean importExccelToDB(String drive,String savePath,String mbid, String excelFileName);
}