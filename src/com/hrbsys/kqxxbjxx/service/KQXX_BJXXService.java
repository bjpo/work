package com.hrbsys.kqxxbjxx.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.KQXX_BJXX;
import com.hrbsys.bean.XUEQI;
public interface KQXX_BJXXService {
public boolean addKQXX_BJXX(KQXX_BJXX y); 
public boolean delKQXX_BJXX(String y);
public boolean updateKQXX_BJXX(KQXX_BJXX yhz);
public KQXX_BJXX findKQXX_BJXX(String yhz_id);
public List<KQXX_BJXX> findKQXX_BJXXByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountKQXX_BJXX(HashMap<String,String> params);
List<KQXX_BJXX> findKQXX_BJXXByPageApp(HashMap<String, String> params,String order, String sort);
//考勤信息统计：
public HashMap<String,Integer> getBJKQXX(String jsid,String jgid,String ksid,String kcbid,XUEQI xq,String xingqi,String zhou,String ksrq,String jsrq);
//考勤信息统计,分学院：
public HashMap<String,Integer> getBJKQXX(String jsid,String jgid,String ksid,String kcbid,XUEQI xq,String xingqi,String zhou,String ksrq,String jsrq,String xy_id);
//考勤信息统计,分学院-->专业：
public HashMap<String,Integer> getBJKQXX(String jsid,String jgid,String ksid,String kcbid,XUEQI xq,String xingqi,String zhou,String ksrq,String jsrq,String xy_id,String zy_id);
//考勤信息统计,分学院-->专业-->年级：
public HashMap<String,Integer> getBJKQXX(String jsid,String jgid,String ksid,String kcbid,XUEQI xq,String xingqi,String zhou,String ksrq,String jsrq,String xy_id,String zy_id,String nj_id);
//考勤信息统计,分学院-->专业-->年级-->
public HashMap<String,Integer> getBJKQXX(String jsid,String jgid,String ksid,String kcbid,XUEQI xq,String xingqi,String zhou,String ksrq,String jsrq,String xy_id,String zy_id,String nj_id,String bj_id);
//考勤信息统计，按教师统计
public HashMap<String, Integer> getBJXXKQXXbyJG(String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq);
//考勤信息统计，按教室统计
public HashMap<String, Integer> getBJXXKQXXbyJS(String jsid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq);
}