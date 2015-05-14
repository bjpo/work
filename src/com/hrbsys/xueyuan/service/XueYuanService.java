package com.hrbsys.xueyuan.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.XUEYUAN;
/**
 * 学院管理使用的接口
 * @author 
 *
 */
public interface XueYuanService {
	
	/*查询所有的学院并进行分页和排序的接口*/
	public List<XUEYUAN> findXUEYUANByPage(int start, int number, HashMap<String, String> params, String order, String sort);
	/*查询所有的学院不进行分页进行排序的接口*/
	//public List<XUEYUAN> findXUEYUANByPageApp(HashMap<String, String> params,String order, String sort);
	/*根据学院的ID进行查询的接口*/
	public XUEYUAN findXueYuanById(String xy_id);
	/*添加学院的接口*/
	public boolean addXueYuan(XUEYUAN xy); 
	/*删除学院的接口*/
	public boolean delXueYuan(String xy_id);
	/*修改学院 的接口*/
	public boolean updateXueYuan(XUEYUAN xy);
	/*获取数据的条目数*/
	public int getCountXUEYUAN(HashMap<String,String> params);
	/*获取数据并对其进行排序*/
	public List<XUEYUAN> findXUEYUANByPageApp(HashMap<String, String> params,String order, String sort);
	
	
	
}
