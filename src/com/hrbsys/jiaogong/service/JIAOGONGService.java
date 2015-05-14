package com.hrbsys.jiaogong.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.JIAOGONG;
public interface JIAOGONGService {
public boolean addJIAOGONG(JIAOGONG y); 
public boolean delJIAOGONG(String y);
public boolean updateJIAOGONG(JIAOGONG yhz);
public JIAOGONG findJIAOGONG(String yhz_id);
public List<JIAOGONG> findJIAOGONGByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountJIAOGONG(HashMap<String,String> params);
List<JIAOGONG> findJIAOGONGByPageApp(HashMap<String, String> params,String order, String sort,String jgqx,String jgid);
//查找教工信息通过用户ID
public JIAOGONG findJIAOGONGbyYONGHUID(String yh_id);
//设置权限
public List<JIAOGONG> findJIAOGONGByPageApp(int start, int number,HashMap<String,String> params,String order,String sort,String jgqx,String jgid);

}