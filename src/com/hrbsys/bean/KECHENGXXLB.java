package com.hrbsys.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="KECHENGXXLB")
public class KECHENGXXLB  implements Serializable {
@Id
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
@Column(name="TMP6")
private String TMP6;

public String getTMP6() {
 return TMP6; 
}
public void setTMP6(String TMP6) { 
this.TMP6 = TMP6;
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
@Column(name="BZ")
private String BZ;

public String getBZ() {
 return BZ; 
}
public void setBZ(String BZ) { 
this.BZ = BZ;
}
@Column(name="KECHENGXXLBMC")
private String KECHENGXXLBMC;

public String getKECHENGXXLBMC() {
 return KECHENGXXLBMC; 
}
public void setKECHENGXXLBMC(String KECHENGXXLBMC) { 
this.KECHENGXXLBMC = KECHENGXXLBMC;
}

}