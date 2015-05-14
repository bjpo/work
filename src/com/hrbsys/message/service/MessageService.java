package com.hrbsys.message.service;

import java.util.List;
import java.util.Map;

import com.hrbsys.bean.CommonMessage;
import com.hrbsys.bean.MESSAGE;

/**
 * 消息接口
 * 
 * @author admin
 * 
 */
public interface MessageService {
	/**获取当前用户的全部消息接口*****/
	public List<MESSAGE> getAllMessage(Map<String, String> params, int start,int number, String order, String sort);
	/*****修改消息读取状态*********/
	public boolean updateMessage(MESSAGE message);
	/****保存用户读取的公共消息的状态***/
	public boolean updateCommonMessage(CommonMessage Message);
	/***获取公共消息读取的状态*/
	public boolean getAllCommonMessage(String id);
	/*** 根据用户的id查询出其未读消息的条数*/
	public int getMessageCount(String id);
	/*** 查询出当前用户消息的所有记录条数*/
	public int getTotal(String id);
}
