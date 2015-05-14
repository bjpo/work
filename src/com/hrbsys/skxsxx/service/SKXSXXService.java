package com.hrbsys.skxsxx.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.SKXSXX;
public interface SKXSXXService {
public boolean addSKXSXX(SKXSXX y); 
public boolean delSKXSXX(String y);
public boolean delSKXSXXbyFXS_ID(String fxs_id) ;
public boolean updateSKXSXX(SKXSXX yhz);
public SKXSXX findSKXSXX(String yhz_id);
public List<SKXSXX> findSKXSXXByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountSKXSXX(HashMap<String,String> params);
public int getCountSKXS(HashMap<String,String> params);
List<SKXSXX> findSKXSXXByPageApp(HashMap<String, String> params,String order, String sort);
public boolean delSKXSXXbyKCB_FXS_ID(String kCB_FXS_ID);

public boolean isSelectedKC(String xs_id,String kcb_id);

}