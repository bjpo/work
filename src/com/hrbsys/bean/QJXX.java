package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QJXX")
public class QJXX implements java.io.Serializable {

	private String QJXX_ID;
	private String XS_ID;
	private String XSXM;
	private String XSXH;
	private String QJKSSJ;
	private String QJJSJS;
	private String DJRQ;
	private String XGRQ;
	private String BZ;
	private String MS;
	private String JG_ID;
	private String JGXM;
	private String JGGH;

	@Id
	@Column(name = "QJXX_ID")
	public String getQJXX_ID() {
		return this.QJXX_ID;
	}

	public void setQJXX_ID(String QJXX_ID) {
		this.QJXX_ID = QJXX_ID;
	}

	@Column(name = "XS_ID")
	public String getXS_ID() {
		return this.XS_ID;
	}

	public void setXS_ID(String XS_ID) {
		this.XS_ID = XS_ID;
	}

	@Column(name = "XSXM")
	public String getXSXM() {
		return this.XSXM;
	}

	public void setXSXM(String XSXM) {
		this.XSXM = XSXM;
	}

	@Column(name = "XSXH")
	public String getXSXH() {
		return this.XSXH;
	}

	public void setXSXH(String XSXH) {
		this.XSXH = XSXH;
	}

	@Column(name = "QJKSSJ")
	public String getQJKSSJ() {
		return this.QJKSSJ;
	}

	public void setQJKSSJ(String QJKSSJ) {
		this.QJKSSJ = QJKSSJ;
	}

	@Column(name = "QJJSJS")
	public String getQJJSJS() {
		return this.QJJSJS;
	}

	public void setQJJSJS(String QJJSJS) {
		this.QJJSJS = QJJSJS;
	}

	@Column(name = "DJRQ")
	public String getDJRQ() {
		return this.DJRQ;
	}

	public void setDJRQ(String DJRQ) {
		this.DJRQ = DJRQ;
	}

	@Column(name = "XGRQ")
	public String getXGRQ() {
		return this.XGRQ;
	}

	public void setXGRQ(String XGRQ) {
		this.XGRQ = XGRQ;
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

	@Column(name = "JG_ID")
	public String getJG_ID() {
		return this.JG_ID;
	}

	public void setJG_ID(String JG_ID) {
		this.JG_ID = JG_ID;
	}

	@Column(name = "JGXM")
	public String getJGXM() {
		return this.JGXM;
	}

	public void setJGXM(String JGXM) {
		this.JGXM = JGXM;
	}

	@Column(name = "JGGH")
	public String getJGGH() {
		return this.JGGH;
	}

	public void setJGGH(String JGGH) {
		this.JGGH = JGGH;
	}

}