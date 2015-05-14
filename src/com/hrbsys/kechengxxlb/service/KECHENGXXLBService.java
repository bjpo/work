package com.hrbsys.kechengxxlb.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.KECHENGXXLB;
public interface KECHENGXXLBService {
public boolean addKECHENGXXLB(KECHENGXXLB y); 
public boolean delKECHENGXXLB(String y);
public boolean updateKECHENGXXLB(KECHENGXXLB yhz);
public KECHENGXXLB findKECHENGXXLB(String yhz_id);
public List<KECHENGXXLB> findKECHENGXXLBByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountKECHENGXXLB(HashMap<String,String> params);
List<KECHENGXXLB> findKECHENGXXLBByPageApp(HashMap<String, String> params,String order, String sort);

}