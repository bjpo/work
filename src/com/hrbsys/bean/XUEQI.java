package com.hrbsys.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="XUEQI")
public class XUEQI  implements Serializable {
@Id
@Column(name="XQ_ID")
private String XQ_ID;

public String getXQ_ID() {
 return XQ_ID; 
}
public void setXQ_ID(String XQ_ID) { 
this.XQ_ID = XQ_ID;
}
@Column(name="BZ")
private String BZ;

public String getBZ() {
 return BZ; 
}
public void setBZ(String BZ) { 
this.BZ = BZ;
}
@Column(name="KSRQ")
private String KSRQ;

public String getKSRQ() {
 return KSRQ; 
}
public void setKSRQ(String KSRQ) { 
this.KSRQ = KSRQ;
}
@Column(name="MS")
private String MS;

public String getMS() {
 return MS; 
}
public void setMS(String MS) { 
this.MS = MS;
}
@Column(name="JSRQ")
private String JSRQ;

public String getJSRQ() {
 return JSRQ; 
}
public void setJSRQ(String JSRQ) { 
this.JSRQ = JSRQ;
}
@Column(name="XQMC")
private String XQMC;

public String getXQMC() {
 return XQMC; 
}
public void setXQMC(String XQMC) { 
this.XQMC = XQMC;
}

}