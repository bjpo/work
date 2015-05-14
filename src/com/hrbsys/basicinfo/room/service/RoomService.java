package com.hrbsys.basicinfo.room.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.FANGJIAN;
import com.hrbsys.bean.JXL;


public interface RoomService {
	public boolean addRoom(FANGJIAN o);
	public boolean delRoom(String fj_id);
	public boolean updateRoom(FANGJIAN o);
	public FANGJIAN findRoomById(String fj_id);
	public int getCountRoom(HashMap<String, String> params);
	public List<FANGJIAN> findRoomByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);
	public List<FANGJIAN> findRoomByPageApp(HashMap<String, String> params,String order, String sort);
	
	public List<JXL> findJXLByPageApp(HashMap<String, String> params,String order, String sort);
	public JXL findJXLById(String jxl_id);
}
