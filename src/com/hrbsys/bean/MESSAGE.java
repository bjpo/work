package com.hrbsys.bean;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 消息类
 */
@Entity
@Table(name = "MESSAGE")
public class MESSAGE implements Serializable {
	@Id
	@Column(name = "MSG_ID")
	private String megId;// 消息ID
	@Column(name = "TEXT")
	private String text;// 文本
	@Column(name = "RELEASETIME")
	private String releaseTime;// 发布时间
	@Column(name = "READSTATUS")
	private String readStatus;// 读取状态
	@Column(name = "YH_ID")
	private String yhId;// 用户ID
	@Column(name = "MEGTYPE")
	private String megType;// 消息类
	
	public String getMegId() {
		
		return megId;
	}

	public void setMegId(String megId) {
		this.megId = megId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
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
	
}
