package com.hrbsys.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="YHZ")
public class YONGHUZU  implements Serializable {
	@Id
	@Column(name="YHZ_ID")
	private String yhzid;
	
	@Column(name = "YHZMC")
	private String yhzmc;
	
	@Column(name = "BZ")
	private String bz;
	
	@Column(name = "djrq")
	private String djrq;
	
	@Column(name = "xgrq")
	private String xgrq;
	
	@OneToMany(mappedBy = "yonghuzu",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<YONGHU> yonghus = new HashSet<YONGHU>();

	public Set<YONGHU> getYonghus() {
		return yonghus;
	}

	public void setYonghus(Set<YONGHU> yonghus) {
		this.yonghus = yonghus;
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

	public String getYhzid() {
		return yhzid;
	}

	public void setYhzid(String yhzid) {
		this.yhzid = yhzid;
	}

	public String getYhzmc() {
		return yhzmc;
	}

	public void setYhzmc(String yhzmc) {
		this.yhzmc = yhzmc;
	}

	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}
