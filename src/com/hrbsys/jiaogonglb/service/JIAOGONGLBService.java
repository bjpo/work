package com.hrbsys.jiaogonglb.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.JIAOGONGLB;
public interface JIAOGONGLBService {
public boolean addJIAOGONGLB(JIAOGONGLB y); 
public boolean delJIAOGONGLB(String y);
public boolean updateJIAOGONGLB(JIAOGONGLB yhz);
public JIAOGONGLB findJIAOGONGLB(String yhz_id);
public List<JIAOGONGLB> findJIAOGONGLBByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountJIAOGONGLB(HashMap<String,String> params);
List<JIAOGONGLB> findJIAOGONGLBByPageApp(HashMap<String, String> params,String order, String sort);


}