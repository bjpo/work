package com.hrbsys.xueqi.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.XUEQI;
public interface XUEQIService {
public boolean addXUEQI(XUEQI y); 
public boolean delXUEQI(String y);
public boolean updateXUEQI(XUEQI yhz);
public XUEQI findXUEQI(String yhz_id);
public List<XUEQI> findXUEQIByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountXUEQI(HashMap<String,String> params);
List<XUEQI> findXUEQIByPageApp(HashMap<String, String> params,String order, String sort);
//课程表中list学期(只查询当前日期所在学期和以后的学期)
List<XUEQI> findXUEQIByPageAppforKCB(HashMap<String, String> params,String order, String sort);
public XUEQI getCurrentXUEQI();
//判断日期是否在学期当中
public boolean isInTerm(String riqi);
}