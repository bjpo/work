package com.hrbsys.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="KQXX_BJXX")
public class KQXX_BJXX  implements Serializable {
@Id
@Column(name="KQXX_BJXX_ID")
private String KQXX_BJXX_ID;

public String getKQXX_BJXX_ID() {
 return KQXX_BJXX_ID; 
}
public void setKQXX_BJXX_ID(String KQXX_BJXX_ID) { 
this.KQXX_BJXX_ID = KQXX_BJXX_ID;
}
@Column(name="CQL")
private Float CQL;

public Float getCQL() {
 return CQL; 
}
public void setCQL(Float CQL) { 
this.CQL = CQL;
}
@Column(name="ZTRS")
private Integer ZTRS=0;

public Integer getZTRS() {
 return ZTRS; 
}
public void setZTRS(Integer ZTRS) { 
this.ZTRS = ZTRS;
}
@Column(name="KCMC")
private String KCMC;

public String getKCMC() {
 return KCMC; 
}
public void setKCMC(String KCMC) { 
this.KCMC = KCMC;
}
@Column(name="ZCCXRS")
private Integer ZCCXRS=0;

public Integer getZCCXRS() {
 return ZCCXRS; 
}
public void setZCCXRS(Integer ZCCXRS) { 
this.ZCCXRS = ZCCXRS;
}
@Column(name="QXL")
private Float QXL;

public Float getQXL() {
 return QXL; 
}
public void setQXL(Float QXL) { 
this.QXL = QXL;
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
@Column(name="MS")
private String MS;

public String getMS() {
 return MS; 
}
public void setMS(String MS) { 
this.MS = MS;
}
@Column(name="SKSJ")
private String SKSJ;

public String getSKSJ() {
 return SKSJ; 
}
public void setSKSJ(String SKSJ) { 
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
@Column(name="CDL")
private Float CDL;

public Float getCDL() {
 return CDL; 
}
public void setCDL(Float CDL) { 
this.CDL = CDL;
}
@Column(name="XINGQI")
private String XINGQI;

public String getXINGQI() {
 return XINGQI; 
}
public void setXINGQI(String XINGQI) { 
this.XINGQI = XINGQI;
}
@Column(name="SJSKRS")
private Integer SJSKRS=0;

public Integer getSJSKRS() {
 return SJSKRS; 
}
public void setSJSKRS(Integer SJSKRS) { 
this.SJSKRS = SJSKRS;
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
@Column(name="CDRS")
private Integer CDRS=0;

public Integer getCDRS() {
 return CDRS; 
}
public void setCDRS(Integer CDRS) { 
this.CDRS = CDRS;
}
@Column(name="KCB_FKS_ID")
private String KCB_FKS_ID;

public String getKCB_FKS_ID() {
 return KCB_FKS_ID; 
}
public void setKCB_FKS_ID(String KCB_FKS_ID) { 
this.KCB_FKS_ID = KCB_FKS_ID;
}
@Column(name="ZTL")
private Float ZTL;

public Float getZTL() {
 return ZTL; 
}
public void setZTL(Float ZTL) { 
this.ZTL = ZTL;
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
@Column(name="QXRS")
private Integer QXRS=0;

public Integer getQXRS() {
 return QXRS; 
}
public void setQXRS(Integer QXRS) { 
this.QXRS = QXRS;
}
@Column(name="YSKRS")
private Integer YSKRS=0;

public Integer getYSKRS() {
 return YSKRS; 
}
public void setYSKRS(Integer YSKRS) { 
this.YSKRS = YSKRS;
}
@Column(name="JS_ID")
private String JS_ID;

public String getJS_ID() {
 return JS_ID; 
}
public void setJS_ID(String JS_ID) { 
this.JS_ID = JS_ID;
}
@Column(name="CDZTRS")
private Integer CDZTRS=0;

public Integer getCDZTRS() {
	return CDZTRS;
}
public void setCDZTRS(Integer cDZTRS) {
	CDZTRS = cDZTRS;
}

}