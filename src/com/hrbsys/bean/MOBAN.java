package com.hrbsys.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOBAN")
public class MOBAN implements Serializable {
	@Id
	@Column(name = "MB_ID")
	private String MB_ID;
	
	@Column(name = "MB_NAME")
	private String MB_NAME;

	@Column(name = "BIAOMING")
	private String BIAOMING;

	@Column(name = "ZHUJIAN")
	private String ZHUJIAN;
	@Column(name = "USER_COLUMN")
	private String USER_COLUMN;//用户名对应列名
	@Column(name="ISCREATEUSER")
	private String ISCREATEUSER;//是否创建相应用户 0：创建用户 1：不创建用户
	@Column(name="INSERTYPE")
	private String insertType;//插入数据的类型

	public String getZHUJIAN() {
		return ZHUJIAN;
	}

	public void setZHUJIAN(String zHUJIAN) {
		ZHUJIAN = zHUJIAN;
	}

	public String getMB_NAME() {
		return MB_NAME;
	}

	public void setMB_NAME(String MB_NAME) {
		this.MB_NAME = MB_NAME;
	}

	public String getBIAOMING() {
		return BIAOMING;
	}

	public void setBIAOMING(String BIAOMING) {
		this.BIAOMING = BIAOMING;
	}

	public String getMB_ID() {
		return MB_ID;
	}

	public void setMB_ID(String MB_ID) {
		this.MB_ID = MB_ID;
	}

	public String getUSER_COLUMN() {
		return USER_COLUMN;
	}

	public void setUSER_COLUMN(String uSER_COLUMN) {
		USER_COLUMN = uSER_COLUMN;
	}

	public String getISCREATEUSER() {
		return ISCREATEUSER;
	}

	public void setISCREATEUSER(String iSCREATEUSER) {
		ISCREATEUSER = iSCREATEUSER;
	}

	public String getInsertType() {
		return insertType;
	}

	public void setInsertType(String insertType) {
		this.insertType = insertType;
	}
	
}