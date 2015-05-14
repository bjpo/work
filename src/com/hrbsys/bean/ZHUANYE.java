package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZHUANYE")
public class ZHUANYE implements Serializable {
	@Id
	@Column(name = "ZY_ID")
	private String ZY_ID;// 专业的编号
	@Column(name = "ZYDM")
	private String ZYDM;// 专业代码
	@Column(name = "ZYMC")
	private String ZYMC;// 专业名字
	@Column(name = "XYID")
	private String XYID;// 学院编号
	@Column(name = "XYMC")
	private String XYMC;// 学院名称
	@Column(name = "BZ")
	private String BZ;// 备注
	@Column(name = "MS")
	private String MS;// 描述

	public String getMS() {
		return MS;
	}

	/******************************* get与set方法 *****************************/
	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getXYID() {
		return XYID;
	}

	public void setXYID(String XYID) {
		this.XYID = XYID;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String ZYMC) {
		this.ZYMC = ZYMC;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String XYMC) {
		this.XYMC = XYMC;
	}

	public String getZYDM() {
		return ZYDM;
	}

	public void setZYDM(String ZYDM) {
		this.ZYDM = ZYDM;
	}

	public String getZY_ID() {
		return ZY_ID;
	}

	public void setZY_ID(String ZY_ID) {
		this.ZY_ID = ZY_ID;
	}

}