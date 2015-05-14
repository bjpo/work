package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FANGJIAN")
public class FANGJIAN implements java.io.Serializable {

	private String fjId;
	private String fjdm;
	private String fjmc;
	private String fjdz;
	private String fjh;
	
	private Integer louceng;
	private String isdmt;
	private Integer renshu;
	private String jxlId;
	private String jxlMc;
	private String jxllh;
	private String bz;
	private String ms;
	private String tmp1;
	private String tmp2;
	private String tmp3;
	private String tmp4;
	private String tmp5;
	private String tmp6;

	@Id
	@Column(name = "FJ_ID")
	public String getFjId() {
		return this.fjId;
	}

	public void setFjId(String fjId) {
		this.fjId = fjId;
	}

	@Column(name = "FJDM")
	public String getFjdm() {
		return this.fjdm;
	}

	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}

	@Column(name = "FJMC")
	public String getFjmc() {
		return this.fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	@Column(name = "FJDZ")
	public String getFjdz() {
		return this.fjdz;
	}

	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}

	@Column(name = "FJH")
	public String getFjh() {
		return this.fjh;
	}

	public void setFjh(String fjh) {
		this.fjh = fjh;
	}

	@Column(name = "LOUCENG")
	public Integer getLouceng() {
		return this.louceng;
	}

	public void setLouceng(Integer louceng) {
		this.louceng = louceng;
	}

	@Column(name = "ISDMT")
	public String getIsdmt() {
		return this.isdmt;
	}

	public void setIsdmt(String isdmt) {
		this.isdmt = isdmt;
	}

	@Column(name = "RENSHU")
	public Integer getRenshu() {
		return this.renshu;
	}

	public void setRenshu(Integer renshu) {
		this.renshu = renshu;
	}

	@Column(name = "JXL_ID")
	public String getJxlId() {
		return this.jxlId;
	}

	public void setJxlId(String jxlId) {
		this.jxlId = jxlId;
	}

	@Column(name = "JXL_MC")
	public String getJxlMc() {
		return this.jxlMc;
	}

	public void setJxlMc(String jxlMc) {
		this.jxlMc = jxlMc;
	}

	@Column(name = "JXLLH")
	public String getJxllh() {
		return this.jxllh;
	}

	public void setJxllh(String jxllh) {
		this.jxllh = jxllh;
	}

	@Column(name = "BZ")
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "MS")
	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	@Column(name = "TMP1")
	public String getTmp1() {
		return this.tmp1;
	}

	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}

	@Column(name = "TMP2")
	public String getTmp2() {
		return this.tmp2;
	}

	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}

	@Column(name = "TMP3")
	public String getTmp3() {
		return this.tmp3;
	}

	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	}

	@Column(name = "TMP4")
	public String getTmp4() {
		return this.tmp4;
	}

	public void setTmp4(String tmp4) {
		this.tmp4 = tmp4;
	}

	@Column(name = "TMP5")
	public String getTmp5() {
		return this.tmp5;
	}

	public void setTmp5(String tmp5) {
		this.tmp5 = tmp5;
	}

	@Column(name = "TMP6")
	public String getTmp6() {
		return this.tmp6;
	}

	public void setTmp6(String tmp6) {
		this.tmp6 = tmp6;
	}

}