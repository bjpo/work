package com.hrbsys.zwlr.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZWLRLB;
import com.hrbsys.bean.ZWLRMD;
/**
 * 指纹录入列表接口
 * 
 */
public interface ZWLRLBService {

	/* 指纹录入列表接口方法 */
	public boolean addZWLRLB(ZWLRLB y);
	public boolean delZWLRLB(String y);
	public boolean updateZWLRLB(ZWLRLB yhz);

	// 删除指纹录入列表时，连带删除指纹录入名单
	public boolean delZWLRLBAndZWLRMD(String arrayId);
	// 指纹录入列表删除标题时，根据zwlrmd_id查询出所有数据
	public List<ZWLRMD> findZWLRLBMD(String t_id);

	public ZWLRLB findZWLRLB(String yhz_id);
	public ZWLRLB findZWLRLBId(String t_listTitle);
	public List<ZWLRLB> findZWLRLBByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort);
	public int getCountZWLRLB(HashMap<String, String> params);
	public List<ZWLRLB> findZWLRLBByPageApp(HashMap<String, String> params,
			String order, String sort);

	/* 指纹录入名单接口方法 */
	// 将学生信息添加到指纹录入名单中接口
	public boolean addZWLRMD(ZWLRMD md);
	public boolean delZWLRMD(String y);

	public List updateNotInXs();
	public List updateInXs(String zwlrmd_id);
	public boolean updateZWLRMD(ZWLRMD md);
	public ZWLRMD findZwlrMdStu(String xh);
	// public List<ZWLRMD> findZWLRLBMD(int start, int number,
	// HashMap<String, String> params, String order, String sort);
	// 获取当前名单中的全部人数
	public Map<String,Integer> getCountZWLRMD(HashMap<String, String> params);
	// 指纹录入功能 查询回来两条数据
	public List findTwoData(String id, int pageNum);
	// 从指定的范围内查询数据
	public List nextData(String ZWLR_ID, int start, int end);
	// 查询指纹录入名单中，没有录入指纹的学生信息
	public List<Xsxx> notFP(String zwlr_id);

}