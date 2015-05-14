package com.hrbsys.bean;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *设备信息管理实体类
 */
@Entity
@Table(name = "SBXX")
public class SBXX implements Serializable {
	@Id
	@Column(name = "ID")
	private String ID;	//主键
	
	private String SBXX_ID;//设备ID
	
	@Column(name = "JSMC")
	private String JSMC;// 教室名称
	@Column(name = "BZ")
	private String BZ;	//备注
	@Column(name = "MS")
	private String MS; //描述
	@Column(name = "SBMC")
	private String SBMC; //设备名称
	@Column(name = "JS_ID")
	private String JS_ID; //教室ID
	
	@Column(name = "SBXX_ID")
	public String getSBXX_ID() {
		return SBXX_ID;
	}
	public void setSBXX_ID(String SBXX_ID) {
		this.SBXX_ID = SBXX_ID;
	}

	public String getJSMC() {
		return JSMC;
	}
	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	public String getBZ() {
		return BZ;
	}
	public void setBZ(String BZ) {
		this.BZ = BZ;
	}
	public String getJS_ID() {
		return JS_ID;
	}
	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}
	public String getMS() {
		return MS;
	}
	public void setMS(String MS) {
		this.MS = MS;
	}
	public String getSBMC() {
		return SBMC;
	}
	public void setSBMC(String SBMC) {
		this.SBMC = SBMC;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

}