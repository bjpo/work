package com.hrbsys.kechengb.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.JIAOSHI;
import com.hrbsys.bean.KECHENGB;
import com.hrbsys.bean.XUEQI;
public interface KECHENGBService {
public boolean addKECHENGB(KECHENGB y); 
public boolean delKECHENGB(String y);
public boolean updateKECHENGB(KECHENGB yhz);
public KECHENGB findKECHENGB(String yhz_id);
public List<KECHENGB> findKECHENGBByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountKECHENGB(HashMap<String,String> params);
public List<KECHENGB> findKECHENGBByPageApp(HashMap<String, String> params,String order, String sort);
public List<KECHENGB> findKECHENGBforSHOWkechengb(HashMap<String, String> params, String order, String sort) ;
public List<KECHENGB> findKECHENGBByPageApp(int start, int number,HashMap<String,String> params,String order,String sort,String pkqx,String jgid);
public List<KECHENGB> findeKECHENGBByJGIDandXUEQI(JIAOGONG jg,XUEQI xueqi,String ksrq); //根据教工和学期获取其课程表课程
public List<KECHENGB> findeKECHENGBByJSIDandXUEQI(JIAOSHI js,XUEQI xueqi,String ksrq); //根据教室和学期获取其课程表课程
public List<KECHENGB> findKECHENGBSbyKCBID(String t_id);
}