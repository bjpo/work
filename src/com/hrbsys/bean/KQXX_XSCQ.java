package com.hrbsys.bean;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="KQXX_XSCQ")
public class KQXX_XSCQ  implements Serializable {
@Id
@Column(name="KQXX_XSCQ_ID")
private String KQXX_XSCQ_ID;

public String getKQXX_XSCQ_ID() {
 return KQXX_XSCQ_ID; 
}
public void setKQXX_XSCQ_ID(String KQXX_XSCQ_ID) { 
this.KQXX_XSCQ_ID = KQXX_XSCQ_ID;
}
@Column(name="KCMC")
private String KCMC;

public String getKCMC() {
 return KCMC; 
}
public void setKCMC(String KCMC) { 
this.KCMC = KCMC;
}
@Column(name="NJ_ID")
private String NJ_ID;

public String getNJ_ID() {
 return NJ_ID; 
}
public void setNJ_ID(String NJ_ID) { 
this.NJ_ID = NJ_ID;
}
@Column(name="KCB_ID")
private String KCB_ID;

public String getKCB_ID() {
 return KCB_ID; 
}
public void setKCB_ID(String KCB_ID) { 
this.KCB_ID = KCB_ID;
}
@Column(name="LS_ID")
private String LS_ID;

public String getLS_ID() {
 return LS_ID; 
}
public void setLS_ID(String LS_ID) { 
this.LS_ID = LS_ID;
}
@Column(name="ZHOU")
private String ZHOU;

public String getZHOU() {
 return ZHOU; 
}
public void setZHOU(String ZHOU) { 
this.ZHOU = ZHOU;
}
@Column(name="KESHI_ID")
private String KESHI_ID;

public String getKESHI_ID() {
 return KESHI_ID; 
}
public void setKESHI_ID(String KESHI_ID) { 
this.KESHI_ID = KESHI_ID;
}
@Column(name="XSXM")
private String XSXM;

public String getXSXM() {
 return XSXM; 
}
public void setXSXM(String XSXM) { 
this.XSXM = XSXM;
}
@Column(name="MS")
private String MS;

public String getMS() {
 return MS; 
}
public void setMS(String MS) { 
this.MS = MS;
}
@Column(name="XSXH")
private String XSXH;

public String getXSXH() {
 return XSXH; 
}
public void setXSXH(String XSXH) { 
this.XSXH = XSXH;
}
@Column(name="XY_ID")
private String XY_ID;

public String getXY_ID() {
 return XY_ID; 
}
public void setXY_ID(String XY_ID) { 
this.XY_ID = XY_ID;
}
@Column(name="SKSJ")
private Date SKSJ;

public Date getSKSJ() {
 return SKSJ; 
}
public void setSKSJ(Date SKSJ) { 
this.SKSJ = SKSJ;
}
@Column(name="BZ")
private String BZ;

public String getBZ() {
 return BZ; 
}
public void setBZ(String BZ) { 
this.BZ = BZ;
}
@Column(name="LSXM")
private String LSXM;

public String getLSXM() {
 return LSXM; 
}
public void setLSXM(String LSXM) { 
this.LSXM = LSXM;
}
@Column(name="XS_ID")
private String XS_ID;

public String getXS_ID() {
 return XS_ID; 
}
public void setXS_ID(String XS_ID) { 
this.XS_ID = XS_ID;
}
@Column(name="XINGQI")
private String XINGQI;

public String getXINGQI() {
 return XINGQI; 
}
public void setXINGQI(String XINGQI) { 
this.XINGQI = XINGQI;
}
@Column(name="BJ_ID")
private String BJ_ID;

public String getBJ_ID() {
 return BJ_ID; 
}
public void setBJ_ID(String BJ_ID) { 
this.BJ_ID = BJ_ID;
}
@Column(name="KCLB")
private String KCLB;

public String getKCLB() {
 return KCLB; 
}
public void setKCLB(String KCLB) { 
this.KCLB = KCLB;
}
@Column(name="JSMC")
private String JSMC;

public String getJSMC() {
 return JSMC; 
}
public void setJSMC(String JSMC) { 
this.JSMC = JSMC;
}
@Column(name="ZY_ID")
private String ZY_ID;

public String getZY_ID() {
 return ZY_ID; 
}
public void setZY_ID(String ZY_ID) { 
this.ZY_ID = ZY_ID;
}
@Column(name="KCB_FKS_ID")
private String KCB_FKS_ID;

public String getKCB_FKS_ID() {
 return KCB_FKS_ID; 
}
public void setKCB_FKS_ID(String KCB_FKS_ID) { 
this.KCB_FKS_ID = KCB_FKS_ID;
}
@Column(name="CQQK")
private String CQQK;

public String getCQQK() {
 return CQQK; 
}
public void setCQQK(String CQQK) { 
this.CQQK = CQQK;
}
@Column(name="KSMC")
private String KSMC;

public String getKSMC() {
 return KSMC; 
}
public void setKSMC(String KSMC) { 
this.KSMC = KSMC;
}
@Column(name="LSGH")
private String LSGH;

public String getLSGH() {
 return LSGH; 
}
public void setLSGH(String LSGH) { 
this.LSGH = LSGH;
}
@Column(name="JS_ID")
private String JS_ID;

public String getJS_ID() {
 return JS_ID; 
}
public void setJS_ID(String JS_ID) { 
this.JS_ID = JS_ID;
}
@Column(name="XKSJ")
public String XKSJ;

public String getXKSJ() {
	return XKSJ;
}
public void setXKSJ(String xKSJ) {
	XKSJ = xKSJ;
}

}