package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "YOUJIANLB")
public class YOUJIANLB implements java.io.Serializable {

	private String YOUJIANLB_ID;
	private String YXMC;
	private String XINGMING;
	private String MS;
	private String BZ;

	@Id
	@Column(name = "YOUJIANLB_ID")
	public String getYOUJIANLB_ID() {
		return this.YOUJIANLB_ID;
	}

	public void setYOUJIANLB_ID(String YOUJIANLB_ID) {
		this.YOUJIANLB_ID = YOUJIANLB_ID;
	}

	@Column(name = "YXMC")
	public String getYXMC() {
		return this.YXMC;
	}

	public void setYXMC(String YXMC) {
		this.YXMC = YXMC;
	}

	@Column(name = "XINGMING")
	public String getXINGMING() {
		return this.XINGMING;
	}

	public void setXINGMING(String XINGMING) {
		this.XINGMING = XINGMING;
	}

	@Column(name = "MS")
	public String getMS() {
		return this.MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	@Column(name = "BZ")
	public String getBZ() {
		return this.BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

}