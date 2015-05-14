package com.hrbsys.message.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.hibernate.mapping.Array;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.CommonMessage;
import com.hrbsys.bean.MESSAGE;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.message.service.MessageService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class MessageAction extends ActionBase {
	// 消息接口
	private MessageService messageService;
	private String megId;// 用户消息的id
	private String readStatu;// 消息读取的状态（用于传值）
	private String megType;// 消息的类型
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	// 排序相关
	private String order;
	private String sort;

	/**
	 * 获取与用户相关的全部消息
	 */
	public void getAllMessage() {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "5"
				: rows);
		int start = (intPage - 1) * number;

		// 获取当前登录用户的信息
		YONGHU yh = (YONGHU) session.get("yonghu");
		// 存放参数
		params.put("yh_id", yh.getYhid());
		// 调用接口获取与当前用户相关的所有消息,根据读取状态显示
		List<MESSAGE> messageList = messageService.getAllMessage(params, start,
				number, order, sort);
		List<MESSAGE> list1 = new ArrayList<MESSAGE>();
		for (MESSAGE message : messageList) {

			if (message.getMegType().trim().equals("all")) {
				// 到commonMessage查询回相关数据
				MESSAGE messageObject = new MESSAGE();
				messageObject.setMegId(message.getMegId());
				messageObject.setText(message.getText());
				messageObject.setMegType(message.getMegType());
				messageObject.setReleaseTime(message.getReleaseTime());
				if (messageService.getAllCommonMessage(yh.getYhid())) {
					messageObject.setReadStatus("1");
				} else {
					messageObject.setReadStatus("0");
				}
				list1.add(messageObject);
			} else {
				// 如果类型不是all,就正常添加
				list1.add(message);
			}
		}
		// 将数据放入到Map中
		jsonMap.put("total",
				String.valueOf(messageService.getTotal(yh.getYhid())));
		jsonMap.put("rows", list1);
		jsonMap.put("page", intPage);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	/**
	 * 修改信息的读取状态
	 */
	public void updateMessage() {
		Map<String, String> jsonMap = new HashMap<String, String>();
		System.out.println("进入到messageaciton：：：："+this.megType);

		log.info("action>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
		YONGHU yh = (YONGHU) session.get("yonghu");
		if (this.megType != null) {
			// 修改普通信息的读取状态
			if (this.megType.equals("normal")) {
				MESSAGE message = new MESSAGE();
				message.setMegId(this.megId);
				message.setReadStatus(this.readStatu);
				messageService.updateMessage(message);
			}
			// 修改公共信息的读取状态
			if (this.megType.equals("all")) {
				CommonMessage message = new CommonMessage();
				message.setSmId(UUIDTools.getUUID());
				message.setMsgId(this.megId);
				message.setReadStatus(this.readStatu);
				message.setMegType(this.megType);
				message.setYhId(yh.getYhid());
				messageService.updateCommonMessage(message);
			}
		}

			log.info("x");
			System.out.println("xxxxxxxxxxxxxxxxx");
			jsonMap.put("messageNum",String.valueOf(messageService.getMessageCount(yh.getYhid())));
			System.out.println("messageAction中打印出来的JSON字符串："+JSONObject.fromObject(jsonMap));
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			log.info("aaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (Exception e) {
			log.info("bbbbbbbbbbbbbbbbbbbbbbbbbb");
			e.printStackTrace();
		}
	}
	
	
	
	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getMegId() {
		return megId;
	}

	public void setMegId(String megId) {
		this.megId = megId;
	}

	public String getReadStatu() {
		return readStatu;
	}

	public void setReadStatu(String readStatu) {
		this.readStatu = readStatu;
	}

	public String getMegType() {
		return megType;
	}

	public void setMegType(String megType) {
		this.megType = megType;
	}

	public String getRows() {
		return rows;
	}
}
