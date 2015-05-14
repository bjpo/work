package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学院类
 * */
@Entity
@Table(name="XUEYUAN")
public class XUEYUAN implements Serializable{
	
	@Id
	@Column(name="XY_ID")
	private String xyid;//学院ID
	@Column(name="XYDM")
	private String xydm;//学院代码
	@Column(name="XYMC")
	private String xymc;//学院名称
	@Column(name="XYDZ")
	private String xydz;//学院地址
	@Column(name="YZBM")
	private String yzbm;//邮政编码
	@Column(name="XXXX")
	private String xxxx;//详细信息
	@Column(name="BZ")
	private String bz;//备注
	@Column(name="MS")
	private String ms;//描述
	@Column(name="FXY_ID")
	private String fxyid;//上级院系ID
	@Column(name="FXYMC")
	private String fxymc;//上级院系名称
	
	
	public String getXyid() {
		return xyid;
	}
	public void setXyid(String xyid) {
		this.xyid = xyid;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getXydz() {
		return xydz;
	}
	public void setXydz(String xydz) {
		this.xydz = xydz;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getXxxx() {
		return xxxx;
	}
	public void setXxxx(String xxxx) {
		this.xxxx = xxxx;
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
	public String getFxyid() {
		return fxyid;
	}
	public void setFxyid(String fxyid) {
		this.fxyid = fxyid;
	}
	public String getFxymc() {
		return fxymc;
	}
	public void setFxymc(String fxymc) {
		this.fxymc = fxymc;
	}
	
}
