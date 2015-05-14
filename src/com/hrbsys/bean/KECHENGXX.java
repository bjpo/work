package com.hrbsys.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="KECHENGXX")
public class KECHENGXX  implements Serializable {
@Id
@Column(name="KECHENGXX_ID")
private String KECHENGXX_ID;

public String getKECHENGXX_ID() {
 return KECHENGXX_ID; 
}
public void setKECHENGXX_ID(String KECHENGXX_ID) { 
this.KECHENGXX_ID = KECHENGXX_ID;
}
@Column(name="KHFS")
private String KHFS;

public String getKHFS() {
 return KHFS; 
}
public void setKHFS(String KHFS) { 
this.KHFS = KHFS;
}
@Column(name="KECHENGMC")
private String KECHENGMC;

public String getKECHENGMC() {
 return KECHENGMC; 
}
public void setKECHENGMC(String KECHENGMC) { 
this.KECHENGMC = KECHENGMC;
}
@Column(name="KSXQ")
private String KSXQ;

public String getKSXQ() {
 return KSXQ; 
}
public void setKSXQ(String KSXQ) { 
this.KSXQ = KSXQ;
}
@Column(name="XSFP_SYSJ")
private String XSFP_SYSJ;

public String getXSFP_SYSJ() {
 return XSFP_SYSJ; 
}
public void setXSFP_SYSJ(String XSFP_SYSJ) { 
this.XSFP_SYSJ = XSFP_SYSJ;
}
@Column(name="YXXF")
private Integer YXXF;

public Integer getYXXF() {
 return YXXF; 
}
public void setYXXF(Integer YXXF) { 
this.YXXF = YXXF;
}
@Column(name="ZYMC")
private String ZYMC;

public String getZYMC() {
 return ZYMC; 
}
public void setZYMC(String ZYMC) { 
this.ZYMC = ZYMC;
}
@Column(name="TMP6")
private String TMP6;

public String getTMP6() {
 return TMP6; 
}
public void setTMP6(String TMP6) { 
this.TMP6 = TMP6;
}
@Column(name="KSXF")
private Integer KSXF;

public Integer getKSXF() {
 return KSXF; 
}
public void setKSXF(Integer KSXF) { 
this.KSXF = KSXF;
}
@Column(name="TMP5")
private String TMP5;

public String getTMP5() {
 return TMP5; 
}
public void setTMP5(String TMP5) { 
this.TMP5 = TMP5;
}
@Column(name="TMP4")
private String TMP4;

public String getTMP4() {
 return TMP4; 
}
public void setTMP4(String TMP4) { 
this.TMP4 = TMP4;
}
@Column(name="TMP3")
private String TMP3;

public String getTMP3() {
 return TMP3; 
}
public void setTMP3(String TMP3) { 
this.TMP3 = TMP3;
}
@Column(name="TMP2")
private String TMP2;

public String getTMP2() {
 return TMP2; 
}
public void setTMP2(String TMP2) { 
this.TMP2 = TMP2;
}
@Column(name="TMP1")
private String TMP1;

public String getTMP1() {
 return TMP1; 
}
public void setTMP1(String TMP1) { 
this.TMP1 = TMP1;
}
@Column(name="KECHENGXXLB_ID")
private String KECHENGXXLB_ID;

public String getKECHENGXXLB_ID() {
 return KECHENGXXLB_ID; 
}
public void setKECHENGXXLB_ID(String KECHENGXXLB_ID) { 
this.KECHENGXXLB_ID = KECHENGXXLB_ID;
}
@Column(name="MS")
private String MS;

public String getMS() {
 return MS; 
}
public void setMS(String MS) { 
this.MS = MS;
}
@Column(name="KECHENGXXLBMC")
private String KECHENGXXLBMC;

public String getKECHENGXXLBMC() {
 return KECHENGXXLBMC; 
}
public void setKECHENGXXLBMC(String KECHENGXXLBMC) { 
this.KECHENGXXLBMC = KECHENGXXLBMC;
}
@Column(name="XSFP_LLJX")
private String XSFP_LLJX;

public String getXSFP_LLJX() {
 return XSFP_LLJX; 
}
public void setXSFP_LLJX(String XSFP_LLJX) { 
this.XSFP_LLJX = XSFP_LLJX;
}
@Column(name="ZY_ID")
private String ZY_ID;

public String getZY_ID() {
 return ZY_ID; 
}
public void setZY_ID(String ZY_ID) { 
this.ZY_ID = ZY_ID;
}
@Column(name="BZ")
private String BZ;

public String getBZ() {
 return BZ; 
}
public void setBZ(String BZ) { 
this.BZ = BZ;
}
@Column(name="KECHENGDM")
private String KECHENGDM;

public String getKECHENGDM() {
 return KECHENGDM; 
}
public void setKECHENGDM(String KECHENGDM) { 
this.KECHENGDM = KECHENGDM;
}

}