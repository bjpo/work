package com.hrbsys.moban.service;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.MOBAN;
public interface MOBANService {
public boolean addMOBAN(MOBAN y); 
public boolean delMOBAN(String y);
public boolean updateMOBAN(MOBAN yhz);
public MOBAN findMOBAN(String yhz_id);
public List<MOBAN> findMOBANByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
public int getCountMOBAN(HashMap<String,String> params);
List<MOBAN> findMOBANByPageApp(HashMap<String, String> params,String order, String sort);

}