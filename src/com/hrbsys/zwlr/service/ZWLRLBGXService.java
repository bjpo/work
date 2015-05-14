package com.hrbsys.zwlr.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.ZWLRLBGX;
public interface ZWLRLBGXService {
public boolean addZWLRLBGX(ZWLRLBGX y); 
public boolean delZWLRLBGX(String y);
public boolean updateZWLRLBGX(ZWLRLBGX yhz);
public ZWLRLBGX findZWLRLBGX(String yhz_id);
public List<ZWLRLBGX> findZWLRLBGXByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountZWLRLBGX(HashMap<String,String> params);
List<ZWLRLBGX> findZWLRLBGXByPageApp(HashMap<String, String> params,String order, String sort);

}