package com.hrbsys.gongongke.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.bean.GONGGONGKE;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.JIAOSHI;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.TONGJI;

/**
 * 公共课的服务接口
 */
public interface GONGGONGKEService {

	/**************************** 公共课列表接口 ***************/
	public List<KECHENGB> findGONGGONGKByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);

	/******************* 查询课程表中老师的名称接口 ************/
	public List findLAOSHIMC();

	/********************** 查询教室的名称 ********************/
	public List findJSMC();
	/******************查询公共选修课的课时名称*****************/
	public List findKSMC();

	/********************** 查询授课课程 **********************/
	public List findSKMC();

	/*********************** 统计出勤情况 *******************************/
	public Map<String, Integer> getKQQK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi_ks, String riqi_js);

	/************************ 进一步查看时查看每一个学生的出勤情况 ****************************************/
//	public List findClassXsxx();
	
	public List  findGGKByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);
	
}
