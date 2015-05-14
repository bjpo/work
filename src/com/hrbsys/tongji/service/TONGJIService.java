package com.hrbsys.tongji.service;
import java.util.HashMap;
import java.util.Map;
/**
 * 统计接口
 */
import java.util.List;

import com.hrbsys.bean.CONDITIONS;
import com.hrbsys.bean.TONGJI;
public interface TONGJIService {
public boolean addTONGJI(TONGJI y); 
public boolean delTONGJI(String y);
public boolean updateTONGJI(TONGJI yhz);
public TONGJI findTONGJI(String yhz_id);
public List<TONGJI> findTONGJIByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountTONGJI(HashMap<String,String> params);
List<TONGJI> findTONGJIByPageApp(HashMap<String, String> params,String order, String sort);

/***********************配置查询条件（校长前台）****************************/
public boolean addCondition(CONDITIONS con);//添加查询条件
public boolean delCondition(String id);//删除查询条件
public List<CONDITIONS> findAllConditionsConfiguration(Map<String, String> params,String order,String sort);//查询出数据库中所有的查询条件
}