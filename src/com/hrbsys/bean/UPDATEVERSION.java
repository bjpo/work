package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "UPDATEVERSION")
public class UPDATEVERSION implements java.io.Serializable {

	// Fields

	private String ID;
	private String OPT;
	private String NAME;
	private String TYPE;
	private String UPDATETIME;

	@Id
	@Column(name = "ID")
	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	@Column(name = "OPT")
	public String getOPT() {
		return this.OPT;
	}

	public void setOPT(String OPT) {
		this.OPT = OPT;
	}

	@Column(name = "NAME")
	public String getNAME() {
		return this.NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	@Column(name = "TYPE")
	public String getTYPE() {
		return this.TYPE;
	}

	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}

	@Column(name = "UPDATETIME")
	public String getUPDATETIME() {
		return this.UPDATETIME;
	}

	public void setUPDATETIME(String UPDATETIME) {
		this.UPDATETIME = UPDATETIME;
	}

}