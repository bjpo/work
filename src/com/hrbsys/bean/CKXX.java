package com.hrbsys.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CKXX")
public class CKXX implements Serializable {
	@Id
	
	@Column(name = "CKXX_ID")
	private String CKXX_ID;
	@Column(name = "KCB_ID")
	private String KCB_ID;
	@Column(name = "LAOSHI_ID")
	private String LAOSHI_ID;
	@Column(name = "JS_ID")
	private String JS_ID;
	@Column(name = "KS_ID")
	private String KS_ID;
	
	
	@Column(name = "LAOSHI_ID_CD")
	private String LAOSHI_ID_CD;
	@Column(name = "JS_ID_CD")
	private String JS_ID_CD;
	@Column(name = "KS_ID_CD")
	private String KS_ID_CD;
	@Column(name = "KCB_ID_CD")
	private String KCB_ID_CD;
	
	@Column(name = "XINGQI_CD")
	private String XINGQI_CD;
	@Column(name = "KSMC_CD")
	private String KSMC_CD;
	@Column(name = "KCB_KCMC_CD")
	private String KCB_KCMC_CD;
	@Column(name = "ZHOU_CD")
	private String ZHOU_CD;
	@Column(name = "JSMC_CD")
	private String JSMC_CD;
	@Column(name = "LAOSHI_XM_CD")
	private String LAOSHI_XM_CD;
	@Column(name = "LAOSHI_GH_CD")
	private String LAOSHI_GH_CD;
	
	
	@Column(name = "LAOSHI_XM")
	private String LAOSHI_XM;
	@Column(name = "KCB_KCMC")
	private String KCB_KCMC;
	@Column(name = "ZHOU")
	private String ZHOU;
	@Column(name = "MS")
	private String MS;
	@Column(name = "LAOSHI_GH")
	private String LAOSHI_GH;
	@Column(name = "BZ")
	private String BZ;
	@Column(name = "XINGQI")
	private String XINGQI;
	@Column(name = "JSMC")
	private String JSMC;
	@Column(name = "KSMC")
	private String KSMC;

	

	

	
	public String getLAOSHI_XM_CD() {
		return LAOSHI_XM_CD;
	}

	public void setLAOSHI_XM_CD(String LAOSHI_XM_CD) {
		this.LAOSHI_XM_CD = LAOSHI_XM_CD;
	}

	public String getKSMC() {
		return KSMC;
	}

	public void setKSMC(String KSMC) {
		this.KSMC = KSMC;
	}

	public String getKCB_ID_CD() {
		return KCB_ID_CD;
	}

	public void setKCB_ID_CD(String KCB_ID_CD) {
		this.KCB_ID_CD = KCB_ID_CD;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String JS_ID) {
		this.JS_ID = JS_ID;
	}

	public String getCKXX_ID() {
		return CKXX_ID;
	}

	public void setCKXX_ID(String CKXX_ID) {
		this.CKXX_ID = CKXX_ID;
	}

	public String getXINGQI_CD() {
		return XINGQI_CD;
	}

	public void setXINGQI_CD(String XINGQI_CD) {
		this.XINGQI_CD = XINGQI_CD;
	}

	public String getKSMC_CD() {
		return KSMC_CD;
	}

	public void setKSMC_CD(String KSMC_CD) {
		this.KSMC_CD = KSMC_CD;
	}

	public String getLAOSHI_XM() {
		return LAOSHI_XM;
	}

	public void setLAOSHI_XM(String LAOSHI_XM) {
		this.LAOSHI_XM = LAOSHI_XM;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String KCB_ID) {
		this.KCB_ID = KCB_ID;
	}

	public String getKCB_KCMC() {
		return KCB_KCMC;
	}

	public void setKCB_KCMC(String KCB_KCMC) {
		this.KCB_KCMC = KCB_KCMC;
	}

	public String getLAOSHI_ID() {
		return LAOSHI_ID;
	}

	public void setLAOSHI_ID(String LAOSHI_ID) {
		this.LAOSHI_ID = LAOSHI_ID;
	}

	public String getLAOSHI_ID_CD() {
		return LAOSHI_ID_CD;
	}

	public void setLAOSHI_ID_CD(String LAOSHI_ID_CD) {
		this.LAOSHI_ID_CD = LAOSHI_ID_CD;
	}

	public String getJS_ID_CD() {
		return JS_ID_CD;
	}

	public void setJS_ID_CD(String JS_ID_CD) {
		this.JS_ID_CD = JS_ID_CD;
	}

	public String getZHOU() {
		return ZHOU;
	}

	public void setZHOU(String ZHOU) {
		this.ZHOU = ZHOU;
	}

	public String getKCB_KCMC_CD() {
		return KCB_KCMC_CD;
	}

	public void setKCB_KCMC_CD(String KCB_KCMC_CD) {
		this.KCB_KCMC_CD = KCB_KCMC_CD;
	}

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public String getLAOSHI_GH() {
		return LAOSHI_GH;
	}

	public void setLAOSHI_GH(String LAOSHI_GH) {
		this.LAOSHI_GH = LAOSHI_GH;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String KS_ID) {
		this.KS_ID = KS_ID;
	}

	public String getLAOSHI_GH_CD() {
		return LAOSHI_GH_CD;
	}

	public void setLAOSHI_GH_CD(String LAOSHI_GH_CD) {
		this.LAOSHI_GH_CD = LAOSHI_GH_CD;
	}

	public String getXINGQI() {
		return XINGQI;
	}

	public void setXINGQI(String XINGQI) {
		this.XINGQI = XINGQI;
	}

	public String getZHOU_CD() {
		return ZHOU_CD;
	}

	public void setZHOU_CD(String ZHOU_CD) {
		this.ZHOU_CD = ZHOU_CD;
	}

	public String getJSMC_CD() {
		return JSMC_CD;
	}

	public void setJSMC_CD(String JSMC_CD) {
		this.JSMC_CD = JSMC_CD;
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String JSMC) {
		this.JSMC = JSMC;
	}

	public String getKS_ID_CD() {
		return KS_ID_CD;
	}

	public void setKS_ID_CD(String KS_ID_CD) {
		this.KS_ID_CD = KS_ID_CD;
	}
}