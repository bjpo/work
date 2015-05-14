package com.hrbsys.task.service;

public interface TaskService {
	//跑中间表
	public void doSQLbyDay();
	public void doSQLbyDay_XUESHENG(String riqi);
	public void doSQLbyDay_JIAOSHI(String riqi);
	public void updateKQXXbyDay();
	//20141119日新增需求：增加开放性课程类别考勤计算
	public void doSQLbyDay_XUESHENG_KFK(String riqi);
	public void doSQLbyDay_JIAOSHI_KFK(String riqi);
	
}