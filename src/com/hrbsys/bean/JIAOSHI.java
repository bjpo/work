package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "JIAOSHI")
public class JIAOSHI implements java.io.Serializable {

	// Fields

	private String JS_ID;
	private String JSMC;
	private String FJID;
	private String FJMC;
	private String FJDM;
	private String ISDMT;
	private String BZ;
	private String MS;
	private String TMP1;
	private String TMP2;
	private String TMP3;
	private String TMP4;
	private String TMP5;
	private String TMP6;

	@Id
	@Column(name = "JS_ID")
	public String getJS_ID() {
		return this.JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	@Column(name = "JSMC", length = 100)
	public String getJSMC() {
		return this.JSMC;
	}

	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	@Column(name = "FJ_ID", length = 36)
	public String getFJID() {
		return this.FJID;
	}

	public void setFJID(String FJID) {
		this.FJID = FJID;
	}

	@Column(name = "FJMC", length = 100)
	public String getFJMC() {
		return this.FJMC;
	}

	public void setFJMC(String FJMC) {
		this.FJMC = FJMC;
	}

	@Column(name = "FJDM", length = 50)
	public String getFJDM() {
		return this.FJDM;
	}

	public void setFJDM(String FJDM) {
		this.FJDM = FJDM;
	}

	@Column(name = "ISDMT", length = 36)
	public String getISDMT() {
		return this.ISDMT;
	}

	public void setISDMT(String ISDMT) {
		this.ISDMT = ISDMT;
	}

	@Column(name = "BZ", length = 500)
	public String getBZ() {
		return this.BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	@Column(name = "MS", length = 500)
	public String getMS() {
		return this.MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	@Column(name = "TMP1", length = 500)
	public String getTMP1() {
		return this.TMP1;
	}

	public void setTMP1(String TMP1) {
		this.TMP1 = TMP1;
	}

	@Column(name = "TMP2", length = 500)
	public String getTMP2() {
		return this.TMP2;
	}

	public void setTMP2(String TMP2) {
		this.TMP2 = TMP2;
	}

	@Column(name = "TMP3", length = 500)
	public String getTMP3() {
		return this.TMP3;
	}

	public void setTMP3(String TMP3) {
		this.TMP3 = TMP3;
	}

	@Column(name = "TMP4", length = 500)
	public String getTMP4() {
		return this.TMP4;
	}

	public void setTMP4(String TMP4) {
		this.TMP4 = TMP4;
	}

	@Column(name = "TMP5", length = 500)
	public String getTMP5() {
		return this.TMP5;
	}

	public void setTMP5(String TMP5) {
		this.TMP5 = TMP5;
	}

	@Column(name = "TMP6", length = 500)
	public String getTMP6() {
		return this.TMP6;
	}

	public void setTMP6(String TMP6) {
		this.TMP6 = TMP6;
	}

}