package com.hrbsys.kechengxx.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.KECHENGXX;
public interface KECHENGXXService {
public boolean addKECHENGXX(KECHENGXX y); 
public boolean delKECHENGXX(String y);
public boolean updateKECHENGXX(KECHENGXX yhz);
public KECHENGXX findKECHENGXX(String yhz_id);
public List<KECHENGXX> findKECHENGXXByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountKECHENGXX(HashMap<String,String> params);
List<KECHENGXX> findKECHENGXXByPageApp(HashMap<String, String> params,String order, String sort);

}