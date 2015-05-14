package com.hrbsys.keshi.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.KESHI;
public interface KESHIService {
public boolean addKESHI(KESHI y); 
public boolean delKESHI(String y);
public boolean updateKESHI(KESHI yhz);
public KESHI findKESHI(String yhz_id);
public List<KESHI> findKESHIByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountKESHI(HashMap<String,String> params);
List<KESHI> findKESHIByPageApp(HashMap<String, String> params,String order, String sort);

}