package com.hrbsys.message.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.CommonMessage;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.MESSAGE;
import com.hrbsys.message.service.MessageService;

public class MessageServiceImpl implements MessageService {
	private BaseDao baseDao;

	/**
	 * 获取数据库中属于当前用户的所有消息
	 */
	@Override
	public List<MESSAGE> getAllMessage(Map<String, String> params, int start,
			int number, String order, String sort) {
		String hql = "FROM MESSAGE where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (params.get("yh_id").toString().trim() != null
				&& !"".equals(params.get("yh_id").toString().trim())) {
			param = " and yhId='" + params.get("yh_id").toString().trim() + "' or megType='all'";
			params2.add(param);
		}
		
		return baseDao.findByPage(hql, MESSAGE.class, start,
				number, params2, order, sort);
	}

	/**
	 * 修改用户读取消息的状态
	 * 
	 * @param message
	 * @return
	 */
	public boolean updateMessage(MESSAGE message) {
		String hql = "from MESSAGE where megId='" + message.getMegId() + "'";
		MESSAGE meg = baseDao.findAll(hql, MESSAGE.class).get(0);
		meg.setReadStatus(message.getReadStatus());
		return baseDao.update(meg);
	}

	/**
	 * 保存用户读取的公共消息的状态
	 * 
	 * @return
	 */
	public boolean updateCommonMessage(CommonMessage Message) {
		return baseDao.save(Message);
	}

	/**
	 * 获取公共消息读取的状态
	 * 
	 * @param id
	 */
	public boolean getAllCommonMessage(String id) {
		List<CommonMessage> list = baseDao.findAll(
				"from CommonMessage where 1=1 and yhId='" + id + "'",
				CommonMessage.class);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 根据用户的id查询出其未读消息的条数
	 * 
	 * @param id    用户id
	 * @return
	 */
	public int getMessageCount(String id) {
		long count = 0;
		try {		
			// 获取普通类型消息条数（未读的）
			String hql = "select count(*) FROM MESSAGE where yhId='" + id+ "' and megType='normal' and readStatus='0'";
			List normalCount = baseDao.findAll(hql, MESSAGE.class);
			count = (Long) normalCount.get(0);
			// 获取公共消息条数（未读的）
			String hql1 = "from MESSAGE where megId in (select msgId FROM CommonMessage where yhId='"+ id + "' and megType='all'and readStatus='1')";
			if (baseDao.findAll(hql1, MESSAGE.class)==null||baseDao.findAll(hql1, MESSAGE.class).size()==0) {
				count++;
			}
			System.out.println("更改消息状态hql是："+hql);
			System.out.println("获取公共消息条数是hql是："+hql);
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) count;
	}
	
	/**
	 * 查询出当前用户消息的所有记录条数
	 * @param id 用户id
	 * @return
	 */
	public int getTotal(String id){
		String hql="SELECT count(*) FROM MESSAGE where 1=1 and yhId='"+id+"' or megType='all'";
		List list=baseDao.findAll(hql, MESSAGE.class);
		Long count = (Long) list.get(0);
		return count.intValue();
	}
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
