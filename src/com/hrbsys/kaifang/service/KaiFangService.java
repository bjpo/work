package com.hrbsys.kaifang.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.XueShengKQ;

/**
 * 开放性课程的接口
 * 
 */
public interface KaiFangService {
	//分页加排序查询
	public List<KQXX_XSCQ> findGKaiFangByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);
	// 查询往天上开放性课程的老师的工号
	public List findKFXLS(String sksj, String xksj, String js_id);
	//往天 根据工号去查询老师信息
	public List findJG(String gh);
	//查询实时开放性课程信息
	public List findRealKcxx(String riqi);
	//实时 根据工号去查询老师信息
	public List findRealKFXLS(String sksj, String xksj, String js_id);
//	public List<KQXX_XSCQ> xsTodayKq(String jsid, String jgid,String ksid, String kcbid,String ksrq,String jsrq);
}
