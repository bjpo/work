package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BANJI")
public class BANJI implements java.io.Serializable {

    private String BJ_ID;
    private String BJMC;
    private String BJHM;
    private String BZK;
    private String NJ_ID;
    private String NJMC;
    private String NJDM;
    private String ZY_ID;
    private String ZYMC;
    private String ZYDM;
    private String BZ;
    private String MS;
    private String TMP1;
    private String TMP2;
    private String TMP3;
    private String TMP4;
    private String TMP5;
    private String TMP6;

    public BANJI() {
    }

    public BANJI(String BJMC) {
        this.BJMC = BJMC;
    }

    public BANJI(String BJ_ID, String BJMC) {
        this.BJ_ID = BJ_ID;
        this.BJMC = BJMC;
    }

    @Id
    @Column(name = "BJ_ID")
    public String getBJ_ID() {
        return this.BJ_ID;
    }

    public void setBJ_ID(String BJ_ID) {
        this.BJ_ID = BJ_ID;
    }

    @Column(name = "BJMC", length = 100)
    public String getBJMC() {
        return this.BJMC;
    }

    public void setBJMC(String BJMC) {
        this.BJMC = BJMC;
    }

    @Column(name = "BJHM", length = 100)
    public String getBJHM() {
        return this.BJHM;
    }

    public void setBJHM(String BJHM) {
        this.BJHM = BJHM;
    }

    @Column(name = "BZK", length = 100)
    public String getBZK() {
        return this.BZK;
    }

    public void setBZK(String BZK) {
        this.BZK = BZK;
    }

    @Column(name = "NJ_ID", length = 36)
    public String getNJ_ID() {
        return this.NJ_ID;
    }

    public void setNJ_ID(String NJ_ID) {
        this.NJ_ID = NJ_ID;
    }

    @Column(name = "NJMC", length = 100)
    public String getNJMC() {
        return this.NJMC;
    }

    public void setNJMC(String NJMC) {
        this.NJMC = NJMC;
    }

    @Column(name = "NJDM", length = 100)
    public String getNJDM() {
        return this.NJDM;
    }

    public void setNJDM(String NJDM) {
        this.NJDM = NJDM;
    }

    @Column(name = "ZY_ID", length = 36)
    public String getZY_ID() {
        return this.ZY_ID;
    }

    public void setZY_ID(String ZY_ID) {
        this.ZY_ID = ZY_ID;
    }

    @Column(name = "ZYMC", length = 100)
    public String getZYMC() {
        return this.ZYMC;
    }

    public void setZYMC(String ZYMC) {
        this.ZYMC = ZYMC;
    }

    @Column(name = "ZYDM", length = 100)
    public String getZYDM() {
        return this.ZYDM;
    }

    public void setZYDM(String ZYDM) {
        this.ZYDM = ZYDM;
    }

    @Column(name = "BZ", length = 500)
    public String getBZ() {
        return this.BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    @Column(name = "MS", length = 500)
    public String getMS() {
        return this.MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    @Column(name = "TMP1", length = 500)
    public String getTMP1() {
        return this.TMP1;
    }

    public void setTMP1(String TMP1) {
        this.TMP1 = TMP1;
    }

    @Column(name = "TMP2", length = 500)
    public String getTMP2() {
        return this.TMP2;
    }

    public void setTMP2(String TMP2) {
        this.TMP2 = TMP2;
    }

    @Column(name = "TMP3", length = 500)
    public String getTMP3() {
        return this.TMP3;
    }

    public void setTMP3(String TMP3) {
        this.TMP3 = TMP3;
    }

    @Column(name = "TMP4", length = 500)
    public String getTMP4() {
        return this.TMP4;
    }

    public void setTMP4(String TMP4) {
        this.TMP4 = TMP4;
    }

    @Column(name = "TMP5", length = 500)
    public String getTMP5() {
        return this.TMP5;
    }

    public void setTMP5(String TMP5) {
        this.TMP5 = TMP5;
    }

    @Column(name = "TMP6", length = 500)
    public String getTMP6() {
        return this.TMP6;
    }

    public void setTMP6(String TMP6) {
        this.TMP6 = TMP6;
    }
}