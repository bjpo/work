package com.hrbsys.bean;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教工实体类
 */

@Entity
@Table(name = "JIAOGONG")
public class JIAOGONG implements Serializable {
	@Id
	@Column(name = "JG_ID")
	private String JG_ID;

	@Column(name = "XY_ID")
	private String XY_ID;

	public String getXY_ID() {
		return XY_ID;
	}

	public void setXY_ID(String xY_ID) {
		XY_ID = xY_ID;
	}

	public String getJG_ID() {
		return JG_ID;
	}

	public void setJG_ID(String JG_ID) {
		this.JG_ID = JG_ID;
	}

	@Column(name = "JGMC")
	private String JGMC;

	public String getJGMC() {
		return JGMC;
	}

	public void setJGMC(String JGMC) {
		this.JGMC = JGMC;
	}

	@Column(name = "TMP6")
	private String TMP6;

	public String getTMP6() {
		return TMP6;
	}

	public void setTMP6(String TMP6) {
		this.TMP6 = TMP6;
	}

	@Column(name = "TMP5")
	private String TMP5;

	public String getTMP5() {
		return TMP5;
	}

	public void setTMP5(String TMP5) {
		this.TMP5 = TMP5;
	}

	@Column(name = "JTZZ")
	private String JTZZ;

	public String getJTZZ() {
		return JTZZ;
	}

	public void setJTZZ(String JTZZ) {
		this.JTZZ = JTZZ;
	}

	@Column(name = "SHENGAO")
	private String SHENGAO;

	public String getSHENGAO() {
		return SHENGAO;
	}

	public void setSHENGAO(String SHENGAO) {
		this.SHENGAO = SHENGAO;
	}

	@Column(name = "TMP4")
	private String TMP4;

	public String getTMP4() {
		return TMP4;
	}

	public void setTMP4(String TMP4) {
		this.TMP4 = TMP4;
	}

	@Column(name = "TMP3")
	private String TMP3;

	public String getTMP3() {
		return TMP3;
	}

	public void setTMP3(String TMP3) {
		this.TMP3 = TMP3;
	}

	@Column(name = "TMP2")
	private String TMP2;

	public String getTMP2() {
		return TMP2;
	}

	public void setTMP2(String TMP2) {
		this.TMP2 = TMP2;
	}

	@Column(name = "TMP1")
	private String TMP1;

	public String getTMP1() {
		return TMP1;
	}

	public void setTMP1(String TMP1) {
		this.TMP1 = TMP1;
	}

	@Column(name = "SFZHM")
	private String SFZHM;

	public String getSFZHM() {
		return SFZHM;
	}

	public void setSFZHM(String SFZHM) {
		this.SFZHM = SFZHM;
	}

	@Column(name = "MZ")
	private String MZ;

	public String getMZ() {
		return MZ;
	}

	public void setMZ(String MZ) {
		this.MZ = MZ;
	}

	@Column(name = "JGLBMC")
	private String JGLBMC;

	public String getJGLBMC() {
		return JGLBMC;
	}

	public void setJGLBMC(String JGLBMC) {
		this.JGLBMC = JGLBMC;
	}

	@Column(name = "TIZHONG")
	private String TIZHONG;

	public String getTIZHONG() {
		return TIZHONG;
	}

	public void setTIZHONG(String TIZHONG) {
		this.TIZHONG = TIZHONG;
	}

	@Column(name = "ZP_ID")
	private String ZP_ID;

	public String getZP_ID() {
		return ZP_ID;
	}

	public void setZP_ID(String ZP_ID) {
		this.ZP_ID = ZP_ID;
	}

	@Column(name = "MS")
	private String MS;

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	@Column(name = "JGLB_ID")
	private String JGLB_ID;

	public String getJGLB_ID() {
		return JGLB_ID;
	}

	public void setJGLB_ID(String JGLB_ID) {
		this.JGLB_ID = JGLB_ID;
	}

	// @Column(name = "ZW_ID")
	// private String ZW_ID;
	//
	// public String getZW_ID() {
	// return ZW_ID;
	// }
	//
	// public void setZW_ID(String ZW_ID) {
	// this.ZW_ID = ZW_ID;
	// }

	@Column(name = "XUELI")
	private String XUELI;

	public String getXUELI() {
		return XUELI;
	}

	public void setXUELI(String XUELI) {
		this.XUELI = XUELI;
	}

	@Column(name = "BYYX")
	private String BYYX;

	public String getBYYX() {
		return BYYX;
	}

	public void setBYYX(String BYYX) {
		this.BYYX = BYYX;
	}

	@Column(name = "BZ")
	private String BZ;

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	@Column(name = "CSNY")
	private String CSNY;

	public String getCSNY() {
		return CSNY;
	}

	public void setCSNY(String CSNY) {
		this.CSNY = CSNY;
	}

	@Column(name = "XB")
	private String XB;

	public String getXB() {
		return XB;
	}

	public void setXB(String XB) {
		this.XB = XB;
	}

	@Column(name = "YH_ID")
	private String YH_ID;

	public String getYH_ID() {
		return YH_ID;
	}

	public void setYH_ID(String YH_ID) {
		this.YH_ID = YH_ID;
	}

	@Column(name = "ZSXM")
	private String ZSXM;

	public String getZSXM() {
		return ZSXM;
	}

	public void setZSXM(String ZSXM) {
		this.ZSXM = ZSXM;
	}

	@Column(name = "JGGH")
	private String JGGH;

	public String getJGGH() {
		return JGGH;
	}

	public void setJGGH(String JGGH) {
		this.JGGH = JGGH;
	}

	@Column(name = "ZHIWEN_ID1")
	private String ZHIWEN_ID1;

	public String getZHIWEN_ID1() {
		return ZHIWEN_ID1;
	}

	public void setZHIWEN_ID1(String zHIWEN_ID1) {
		ZHIWEN_ID1 = zHIWEN_ID1;
	}

	@Column(name = "ZHIWEN_ID2")
	private String ZHIWEN_ID2;

	public String getZHIWEN_ID2() {
		return ZHIWEN_ID2;
	}

	public void setZHIWEN_ID2(String zHIWEN_ID2) {
		ZHIWEN_ID2 = zHIWEN_ID2;
	}

}