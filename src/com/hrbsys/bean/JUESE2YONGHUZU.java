package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yonghuzu_juese")
public class JUESE2YONGHUZU  implements Serializable {
	@Id
	@Column(name = "YHZJS_ID")
	private String jsyhzid;

	@Column(name = "yhzid")
	private String yhzid;

	@Column(name = "jsid")
	private String jsid;

	@Column(name = "ms")
	private String ms;

	@Column(name = "djrq")
	private String djrq;

	@Column(name = "xgrq")
	private String xgrq;

	@Column(name = "bz")
	private String bz;

	@Column(name = "JSMC")
	private String jsmc;

	@Column(name = "yhzmc")
	private String yhzmc;

	public String getJsyhzid() {
		return jsyhzid;
	}

	public void setJsyhzid(String jsyhid) {
		this.jsyhzid = jsyhid;
	}

	public String getYhzid() {
		return yhzid;
	}

	public void setYhzid(String yhzid) {
		this.yhzid = yhzid;
	}

	public String getJsid() {
		return jsid;
	}

	public void setJsid(String jsid) {
		this.jsid = jsid;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	public String getYhzmc() {
		return yhzmc;
	}

	public void setYhzmc(String yhzmc) {
		this.yhzmc = yhzmc;
	}
}
