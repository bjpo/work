package com.hrbsys.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KECHENGB")
public class KECHENGB implements Serializable {
	@Id
	@Column(name = "KCB_FXS_ID")
	private String KCB_FXS_ID;

	public String getKCB_FXS_ID() {
		return KCB_FXS_ID;
	}

	public void setKCB_FXS_ID(String KCB_FXS_ID) {
		this.KCB_FXS_ID = KCB_FXS_ID;
	}

	@Column(name = "XUEQI_ID")
	private String XUEQI_ID;

	public String getXUEQI_ID() {
		return XUEQI_ID;
	}

	public void setXUEQI_ID(String xUEQI_ID) {
		XUEQI_ID = xUEQI_ID;
	}

	@Column(name = "XQMC")
	public String XQMC;

	public String getXQMC() {
		return XQMC;
	}

	public void setXQMC(String xQMC) {
		XQMC = xQMC;
	}

	@Column(name = "KS_KSSJ")
	private String KS_KSSJ;

	public String getKS_KSSJ() {
		return KS_KSSJ;
	}

	public void setKS_KSSJ(String KS_KSSJ) {
		this.KS_KSSJ = KS_KSSJ;
	}

	@Column(name = "KCB_ID")
	private String KCB_ID;

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String KCB_ID) {
		this.KCB_ID = KCB_ID;
	}

	@Column(name = "LAOSHI_ID")
	private String LAOSHI_ID;

	public String getLAOSHI_ID() {
		return LAOSHI_ID;
	}

	public void setLAOSHI_ID(String LAOSHI_ID) {
		this.LAOSHI_ID = LAOSHI_ID;
	}

	@Column(name = "KCBLB")
	private String KCBLB;

	public String getKCBLB() {
		return KCBLB;
	}

	public void setKCBLB(String KCBLB) {
		this.KCBLB = KCBLB;
	}

	@Column(name = "LAOSHIGH")
	private String LAOSHIGH;

	public String getLAOSHIGH() {
		return LAOSHIGH;
	}

	public void setLAOSHIGH(String LAOSHIGH) {
		this.LAOSHIGH = LAOSHIGH;
	}

	@Column(name = "LAOSHIMC")
	private String LAOSHIMC;

	public String getLAOSHIMC() {
		return LAOSHIMC;
	}

	public void setLAOSHIMC(String LAOSHIMC) {
		this.LAOSHIMC = LAOSHIMC;
	}

	@Column(name = "JSZHOU")
	private String JSZHOU;

	public String getJSZHOU() {
		return JSZHOU;
	}

	public void setJSZHOU(String JSZHOU) {
		this.JSZHOU = JSZHOU;
	}

	@Column(name = "TMP6")
	private String TMP6;

	public String getTMP6() {
		return TMP6;
	}

	public void setTMP6(String TMP6) {
		this.TMP6 = TMP6;
	}

	@Column(name = "KSZHOU")
	private String KSZHOU;

	public String getKSZHOU() {
		return KSZHOU;
	}

	public void setKSZHOU(String KSZHOU) {
		this.KSZHOU = KSZHOU;
	}

	@Column(name = "TMP5")
	private String TMP5;

	public String getTMP5() {
		return TMP5;
	}

	public void setTMP5(String TMP5) {
		this.TMP5 = TMP5;
	}

	@Column(name = "KCXX_ID")
	private String KCXX_ID;

	public String getKCXX_ID() {
		return KCXX_ID;
	}

	public void setKCXX_ID(String KCXX_ID) {
		this.KCXX_ID = KCXX_ID;
	}

	@Column(name = "TMP4")
	private String TMP4;

	public String getTMP4() {
		return TMP4;
	}

	public void setTMP4(String TMP4) {
		this.TMP4 = TMP4;
	}

	@Column(name = "KS_ID")
	private String KS_ID;

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String KS_ID) {
		this.KS_ID = KS_ID;
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

	@Column(name = "XINGQI")
	private String XINGQI;

	public String getXINGQI() {
		return XINGQI;
	}

	public void setXINGQI(String XINGQI) {
		this.XINGQI = XINGQI;
	}

	@Column(name = "TMP1")
	private String TMP1;

	public String getTMP1() {
		return TMP1;
	}

	public void setTMP1(String TMP1) {
		this.TMP1 = TMP1;
	}

	@Column(name = "MS")
	private String MS;

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	@Column(name = "JSMC")
	private String JSMC;

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	@Column(name = "BZ")
	private String BZ;

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	@Column(name = "XINGQIXH")
	private String XINGQIXH;

	public String getXINGQIXH() {
		return XINGQIXH;
	}

	public void setXINGQIXH(String XINGQIXH) {
		this.XINGQIXH = XINGQIXH;
	}

	@Column(name = "KSMC")
	private String KSMC;

	public String getKSMC() {
		return KSMC;
	}

	public void setKSMC(String KSMC) {
		this.KSMC = KSMC;
	}

	@Column(name = "KS_JSSJ")
	private String KS_JSSJ;

	public String getKS_JSSJ() {
		return KS_JSSJ;
	}

	public void setKS_JSSJ(String KS_JSSJ) {
		this.KS_JSSJ = KS_JSSJ;
	}

	@Column(name = "KCXXMC")
	private String KCXXMC;

	public String getKCXXMC() {
		return KCXXMC;
	}

	public void setKCXXMC(String KCXXMC) {
		this.KCXXMC = KCXXMC;
	}

	@Column(name = "JS_ID")
	private String JS_ID;

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	@Column(name = "CAPACITY")
	private String CAPACITY;

	@Column(name = "NUMSELECTED")
	private String NUMSELECTED;

	public String getCAPACITY() {
		return CAPACITY;
	}

	public void setCAPACITY(String cAPACITY) {
		CAPACITY = cAPACITY;
	}

	public String getNUMSELECTED() {
		return NUMSELECTED;
	}

	public void setNUMSELECTED(String nUMSELECTED) {
		NUMSELECTED = nUMSELECTED;
	}

	@Column(name = "MAXRS")
	private String MAXRS;

	public String getMAXRS() {
		return MAXRS;
	}

	public void setMAXRS(String mAXRS) {
		MAXRS = mAXRS;
	}

}