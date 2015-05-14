package com.hrbsys.xsxk.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.CKXX;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.SKXSXX;
import com.hrbsys.bean.XSXK;

public interface XsXuanKeService {
	/** 获取数据并对其进行排序与分页 */
	public List<KECHENGB> findXSXKByPageApp(int start, int number,HashMap<String, String> params, String order, String sort);

	/** 获取数据不对其进行分页 **/
	List<KECHENGB> findXSXKByPageApp(HashMap<String, String> params,
			String order, String sort);

	/** 获取数据的总条数 */
	public int getCountXSXK(HashMap<String, String> params);

	/**
	 * 将选课信息添加到上课信息表中
	 */
	public boolean addSKXSXX(SKXSXX y);

	/**
	 * 删除上课信息
	 */
	public boolean delSKXSXX(String y);

	public List<KECHENGB> findGGK_XSXKKCByPage(int start, int number,HashMap<String, String> params, String order, String sort);
	public boolean isSameTime(String kcb_id,String xs_id);//判断学生是否在此时此刻有课程需要上
}
