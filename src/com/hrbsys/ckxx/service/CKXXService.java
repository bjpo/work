package com.hrbsys.ckxx.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.CKXX;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.KECHENGB;

/**
 * 串课信息管理模块接口
 * 
 * @author Administrator
 * 
 */
public interface CKXXService {
	/* 添加串课信息 */
	public boolean addCKXX(CKXX y);

	/* 删除串课信息 */
	public boolean delCKXX(String y);

	/* 修改串课信息 */
	public boolean updateCKXX(CKXX yhz);

	public CKXX findCKXX(String yhz_id);

	// 对查询到的数据进行分页与排序
	public List<CKXX> findCKXXByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);

	// 统计总共有多少条数据
	public int getCountCKXX(HashMap<String, String> params);

	// 对查询到的数据进行排序
	List<CKXX> findCKXXByPageApp(HashMap<String, String> params, String order,
			String sort);

	// 通过教工ID查询相应的信息
	public List<JIAOGONG> findJIAOGONG(String JG_ID);

	// 通过课程表ID查询相应的信息
	public KECHENGB findKECHENGB(String KCB_ID);


}