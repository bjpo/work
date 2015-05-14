package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

/**
 * 公共消息类
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "COMMONMESSAGE")
public class CommonMessage implements Serializable{
	@Id
	@Column(name = "SM_ID")
	private String smId;// 公共消息id
	@Column(name="YH_ID")
	private String yhId;// 用户id
	@Column(name="MEGTYPE")
	private String megType;// 消息类型
	@Column(name="READSTATUS")
	private String readStatus;// 读取状态
	@Column(name="MSG_ID")
	private String msgId;//公共信息ID
	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public String getYhId() {
		return yhId;
	}

	public void setYhId(String yhId) {
		this.yhId = yhId;
	}

	public String getMegType() {
		return megType;
	}

	public void setMegType(String megType) {
		this.megType = megType;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
