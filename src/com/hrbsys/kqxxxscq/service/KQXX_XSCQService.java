package com.hrbsys.kqxxxscq.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.XUEQI;
public interface KQXX_XSCQService {
public boolean addKQXX_XSCQ(KQXX_XSCQ y); 
public boolean delKQXX_XSCQ(String y);
public boolean updateKQXX_XSCQ(KQXX_XSCQ yhz);
public KQXX_XSCQ findKQXX_XSCQ(String yhz_id);
public List<KQXX_XSCQ> findKQXX_XSCQByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountKQXX_XSCQ(HashMap<String,String> params);
public List<KQXX_XSCQ> findKQXX_XSCQByPageApp(HashMap<String, String> params,String order, String sort);
           public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id,String zy_id,String nj_id);
	       public HashMap<String, Integer> getBJKQXX(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id,String zy_id,String nj_id,String bj_id);
public HashMap<String, Integer> getBJKQXX_XSKQ_COUNT(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xy_id,String zy_id,String nj_id,String bj_id,String xsid);
           public List<KQXX_XSCQ> getBJKQXX_XSKQ_FEN(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq, String xsid,String kqqk);
public List<KQXX_XSCQ> findBANJIByZYandNJ(String zy_ID, String nj_ID,String bj_ID);
/*按老师/教室统计出勤情况*/
public List<KQXX_XSCQ> findKQXXbyJGdetail(String jsid, String jgid,String ksid, String kcbid, XUEQI xq, String xingqi,String zhou, String ksrq, String jsrq);
}