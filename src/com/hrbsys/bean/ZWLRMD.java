package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指纹录入名单实体类
 * 
 */
@Entity
@Table(name = "ZWLRMD")
public class ZWLRMD implements Serializable {
	
	//指纹录入名单 主键 id
	private String ID;//主键
	private String ZWLRMD_ID;//外键
	private String XB;//学生性别
	private String XH;//学生学号
	private String ZSXM;//学生的真实姓名
	private String XYMC;//学院的名称
	private String ZYMC;//专业的名称
	private String NJ_ID;//年级ID
	private String NJMC;//年级的名称
	private String BJ_ID;//班级ID
	private String BJMC;//班级的名称
	
	@Id
	@Column(name="ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	
	@Column(name="ZWLRMD_ID")
	public String getZWLRMD_ID() {
		return ZWLRMD_ID;
	}
	
	public void setZWLRMD_ID(String zWLRMD_ID) {
		ZWLRMD_ID = zWLRMD_ID;
	}
	@Column(name="XB")
	public String getXB() {
		return XB;
	}
	public void setXB(String xB) {
		XB = xB;
	}
	@Column(name="XH")
	public String getXH() {
		return XH;
	}
	public void setXH(String xH) {
		XH = xH;
	}
	@Column(name="ZSXM")
	public String getZSXM() {
		return ZSXM;
	}
	public void setZSXM(String zSXM) {
		ZSXM = zSXM;
	}
	@Column(name="XYMC")
	public String getXYMC() {
		return XYMC;
	}
	public void setXYMC(String xYMC) {
		XYMC = xYMC;
	}
	@Column(name="ZYMC")
	public String getZYMC() {
		return ZYMC;
	}
	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}
	@Column(name="NJMC")
	public String getNJMC() {
		return NJMC;
	}
	public void setNJMC(String nJMC) {
		NJMC = nJMC;
	}
	
	@Column(name="BJMC")
	public String getBJMC() {
		return BJMC;
	}
	public void setBJMC(String bJMC) {
		BJMC = bJMC;
	}
	@Column(name="NJ_ID")
	public String getNJ_ID() {
		return NJ_ID;
	}
	public void setNJ_ID(String nJ_ID) {
		NJ_ID = nJ_ID;
	}
	@Column(name="BJ_ID")
	public String getBJ_ID() {
		return BJ_ID;
	}
	public void setBJ_ID(String bJ_ID) {
		BJ_ID = bJ_ID;
	}
	
}
