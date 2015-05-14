package com.hrbsys.tongzhi.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.YOUJIANLB;

public interface TongzhiService {
	// 跑中间表
	public void doSQLbyDay();

	public List<YOUJIANLB> findAllYOUJIANLB();
	
	

	public void send(String from, String to, String subject, String content);
}