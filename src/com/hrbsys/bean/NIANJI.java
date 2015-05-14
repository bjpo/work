package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NIANJI")
public class NIANJI implements java.io.Serializable {

	// Fields

	private String NJ_ID;
	private String NJMC;
	private String NJDM;
	private String BZ;
	private String MS;
	private String TMP1;
	private String TMP2;
	private String TMP3;
	private String TMP4;
	private String TMP5;
	private String TMP6;

	@Id
	@Column(name = "NJ_ID")
	public String getNJ_ID() {
		return this.NJ_ID;
	}

	public void setNJ_ID(String NJ_ID) {
		this.NJ_ID = NJ_ID;
	}

	@Column(name = "NJMC")
	public String getNJMC() {
		return this.NJMC;
	}

	public void setNJMC(String NJMC) {
		this.NJMC = NJMC;
	}

	@Column(name = "NJDM")
	public String getNJDM() {
		return this.NJDM;
	}

	public void setNJDM(String NJDM) {
		this.NJDM = NJDM;
	}

	@Column(name = "BZ")
	public String getBZ() {
		return this.BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	@Column(name = "MS")
	public String getMS() {
		return this.MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	@Column(name = "TMP1")
	public String getTMP1() {
		return this.TMP1;
	}

	public void setTMP1(String TMP1) {
		this.TMP1 = TMP1;
	}

	@Column(name = "TMP2")
	public String getTMP2() {
		return this.TMP2;
	}

	public void setTMP2(String TMP2) {
		this.TMP2 = TMP2;
	}

	@Column(name = "TMP3")
	public String getTMP3() {
		return this.TMP3;
	}

	public void setTMP3(String TMP3) {
		this.TMP3 = TMP3;
	}

	@Column(name = "TMP4")
	public String getTMP4() {
		return this.TMP4;
	}

	public void setTMP4(String TMP4) {
		this.TMP4 = TMP4;
	}

	@Column(name = "TMP5")
	public String getTMP5() {
		return this.TMP5;
	}

	public void setTMP5(String TMP5) {
		this.TMP5 = TMP5;
	}

	@Column(name = "TMP6")
	public String getTMP6() {
		return this.TMP6;
	}

	public void setTMP6(String TMP6) {
		this.TMP6 = TMP6;
	}

}