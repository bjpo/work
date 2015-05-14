package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "juese")
public class JUESE implements Serializable  {
	@Id
	@Column(name="js_id")
	private String jsid;	
	@Column(name="jsmc")
	private String jsmc;
	@Column(name="bz")
	private String bz;
	@Column(name="ms")
	private String ms;
	@Column(name="djrq")
	private String djrq;
	@Column(name="xgrq")
	private String xgrq;
	
	public String getJsid() {
		return jsid;
	}
	public void setJsid(String jsid) {
		this.jsid = jsid;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getXgrq() {
		return xgrq;
	}
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}
}
