package com.hrbsys.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOBAN_ZIDUAN")
public class MOBAN_ZIDUAN implements Serializable {
	@Id
	@Column(name = "ZIDUAN_ID")
	private String ZIDUAN_ID;

	public String getZIDUAN_ID() {
		return ZIDUAN_ID;
	}

	public void setZIDUAN_ID(String ZIDUAN_ID) {
		this.ZIDUAN_ID = ZIDUAN_ID;
	}

	@Column(name = "LIE")
	private String LIE;

	public String getLIE() {
		return LIE;
	}

	public void setLIE(String LIE) {
		this.LIE = LIE;
	}

	@Column(name = "MB_ID")
	private String MB_ID;

	public String getMB_ID() {
		return MB_ID;
	}

	public void setMB_ID(String MB_ID) {
		this.MB_ID = MB_ID;
	}

	@Column(name = "ZIDUANMC")
	private String ZIDUANMC;

	public String getZIDUANMC() {
		return ZIDUANMC;
	}

	public void setZIDUANMC(String ZIDUANMC) {
		this.ZIDUANMC = ZIDUANMC;
	}

}