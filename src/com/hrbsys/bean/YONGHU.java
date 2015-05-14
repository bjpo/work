package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "yonghu")
public class YONGHU implements Serializable {
	@Id
	@Column(name = "YH_ID")
	private String yhid;// 用户ID

	@Column(name = "YHM")
	private String yhm;

	@Column(name = "YHMM")
	private String yhmm;

	@Column(name = "FK_YHZL")
	private String yhzl;

	@Column(name = "FK_YHZLLX")
	private String yhzllx;

	@Column(name = "DJRQ")
	private String djrq;

	@Column(name = "XGRQ")
	private String xgrq;
	@Column(name = "LASTLOGINTIME")
	private String lastLoginTime;// 用户上次登录的时间
	@Column(name = "LOGINTIME")
	private String loginTime;// 用户本次登录的时间
	@Column(name = "LOGINNUM")
	private String loginNum;// 用户登录的次数

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getYhmm() {
		return yhmm;
	}

	public void setYhmm(String yhmm) {
		this.yhmm = yhmm;
	}

	public String getYhzl() {
		return yhzl;
	}

	public void setYhzl(String yhzl) {
		this.yhzl = yhzl;
	}

	public String getYhzllx() {
		return yhzllx;
	}

	public void setYhzllx(String yhzllx) {
		this.yhzllx = yhzllx;
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

	public YONGHUZU getYonghuzu() {
		return yonghuzu;
	}

	public void setYonghuzu(YONGHUZU yonghuzu) {
		this.yonghuzu = yonghuzu;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "YHZ_ID", nullable = false, referencedColumnName = "YHZ_ID")
	private YONGHUZU yonghuzu;

	// 定义一下用户组id、用户组名称
	@Transient
	private String json_yhzid;
	@Transient
	private String json_yhzmc;

	public String getJson_yhzid() {
		return json_yhzid;
	}

	public void setJson_yhzid(String json_yhzid) {
		this.json_yhzid = json_yhzid;
	}

	public String getJson_yhzmc() {
		return json_yhzmc;
	}

	public void setJson_yhzmc(String json_yhzmc) {
		this.json_yhzmc = json_yhzmc;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public String getLoginNum() {
		return loginNum;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public void setLoginNum(String loginNum) {
		this.loginNum = loginNum;
	}

}
