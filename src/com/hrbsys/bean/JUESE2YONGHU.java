package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yonghu_juese")
public class JUESE2YONGHU  implements Serializable {
	@Id
	@Column(name = "YHJS_ID")
	private String jsyhid;

	@Column(name = "yh_id")
	private String yhid;

	@Column(name = "js_id")
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
	
	@Column(name = "yhmc")
	private String yhmc;
	
	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getJsyhid() {
		return jsyhid;
	}

	public void setJsyhid(String jsyhid) {
		this.jsyhid = jsyhid;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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
