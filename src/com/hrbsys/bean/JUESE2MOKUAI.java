package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "juesemokuai")
public class JUESE2MOKUAI implements Serializable  {
	@Id
	@Column(name = "jsmk_id")
	private String jsmkid;

	@Column(name = "js_id")
	private String jsid;

	@Column(name = "mk_id")
	private String mkid;

	@Column(name = "ms")
	private String ms;
	
	@Column(name = "djrq")
	private String djrq;
	
	public String getJsmkid() {
		return jsmkid;
	}

	public void setJsmkid(String jsmkid) {
		this.jsmkid = jsmkid;
	}

	public String getJsid() {
		return jsid;
	}

	public void setJsid(String jsid) {
		this.jsid = jsid;
	}

	public String getMkid() {
		return mkid;
	}

	public void setMkid(String mkid) {
		this.mkid = mkid;
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

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getMkurl() {
		return mkurl;
	}

	public void setMkurl(String mkurl) {
		this.mkurl = mkurl;
	}

	public String getCanfq() {
		return canfq;
	}

	public void setCanfq(String canfq) {
		this.canfq = canfq;
	}

	@Column(name = "xgrq")
	private String xgrq;

	@Column(name = "bz")
	private String bz;

	@Column(name = "JSMC")
	private String jsmc;
	
	@Column(name = "mkmc")
	private String mkmc;
	
	@Column(name = "mkurl")
	private String mkurl;

	@Column(name = "canfq")
	private String canfq;
	
}
