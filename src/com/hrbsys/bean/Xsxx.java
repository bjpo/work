package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XSXX")
public class Xsxx implements java.io.Serializable {

	private String xsId;
	private String xh;
	private String zsxm;
	private String cym;
	private String xb;
	private String sfzhm;
	private String csny;
	private String mz;
	private String rtsj;
	private String csd;
	private String hjlb;
	private String sushehao;
	private String ssdh;
	private String gatdm;
	private String lxr;
	private String yzbm;
	private String dahh;
	private String dayh;
	private String dzyx;
	private String lxdz;
	private String lxdh;
	private String zsjj;
	private String zkzh;
	private String ksh;
	private String kslb;
	private String jkzk;
	private String kstz;
	private String rxwhcd;
	private String sysf;
	private String bylb;
	private String sydq;
	private String kldm;
	private String rxcj;
	private String xxtj;
	private String tc;
	private String rxsj;
	private String zyIdLq;
	private String xz;
	private String rxnf;
	private String rxfs;
	private String pylb;
	private String pydx;
	private String pycc;
	private String bxfs;
	private String xxnx;
	private String qtbxxs;
	private String zxwyyz;
	private String zxwyjb;
	private String bxlx;
	private String byzx;
	private String sg;
	private String tz;
	private String jsjnldj;
	private String jtzz;
	private String yhId;
	private String xyId;
	private String zyId;
	private String njId;
	private String bjId;
	private String zhiwenId1;
	private String zhiwenId2;
	private String zhaopianId;
	private String bz;
	private String ms;
	private String XYMC;
	private String ZYMC;
	private String NJMC;
	private String BJMC;
	private String MODIFY_NUM;//记录学生修改信息的次数
	// private String cqzt;// 出勤状态
	private String tmp1;
	private String tmp2;
	private String tmp3;
	private String tmp4;
	private String tmp5;
	private String tmp6;

    public Xsxx() {
    }

    public Xsxx(String xh, String zsxm) {
        this.xh = xh;
        this.zsxm = zsxm;
    }

	@Id
	@Column(name = "XS_ID")
	public String getXsId() {
		return this.xsId;
	}

	public void setXsId(String xsId) {
		this.xsId = xsId;
	}

	@Column(name = "XH")
	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "ZSXM")
	public String getZsxm() {
		return this.zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	@Column(name = "CYM")
	public String getCym() {
		return this.cym;
	}

	public void setCym(String cym) {
		this.cym = cym;
	}

	@Column(name = "XB")
	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	@Column(name = "SFZHM")
	public String getSfzhm() {
		return this.sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	@Column(name = "CSNY")
	public String getCsny() {
		return this.csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
	}

	@Column(name = "MZ")
	public String getMz() {
		return this.mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	@Column(name = "RTSJ")
	public String getRtsj() {
		return this.rtsj;
	}

	public void setRtsj(String rtsj) {
		this.rtsj = rtsj;
	}

	@Column(name = "CSD")
	public String getCsd() {
		return this.csd;
	}

	public void setCsd(String csd) {
		this.csd = csd;
	}

	@Column(name = "HJLB")
	public String getHjlb() {
		return this.hjlb;
	}

	public void setHjlb(String hjlb) {
		this.hjlb = hjlb;
	}

	@Column(name = "SUSHEHAO")
	public String getSushehao() {
		return this.sushehao;
	}

	public void setSushehao(String sushehao) {
		this.sushehao = sushehao;
	}

	@Column(name = "SSDH")
	public String getSsdh() {
		return this.ssdh;
	}

	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}

	@Column(name = "GATDM")
	public String getGatdm() {
		return this.gatdm;
	}

	public void setGatdm(String gatdm) {
		this.gatdm = gatdm;
	}

	@Column(name = "LXR")
	public String getLxr() {
		return this.lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	@Column(name = "YZBM")
	public String getYzbm() {
		return this.yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	@Column(name = "DAHH")
	public String getDahh() {
		return this.dahh;
	}

	public void setDahh(String dahh) {
		this.dahh = dahh;
	}

	@Column(name = "DAYH")
	public String getDayh() {
		return this.dayh;
	}

	public void setDayh(String dayh) {
		this.dayh = dayh;
	}

	@Column(name = "DZYX")
	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	@Column(name = "LXDZ")
	public String getLxdz() {
		return this.lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	@Column(name = "LXDH")
	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	@Column(name = "ZSJJ")
	public String getZsjj() {
		return this.zsjj;
	}

	public void setZsjj(String zsjj) {
		this.zsjj = zsjj;
	}

	@Column(name = "ZKZH")
	public String getZkzh() {
		return this.zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	@Column(name = "KSH")
	public String getKsh() {
		return this.ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	@Column(name = "KSLB")
	public String getKslb() {
		return this.kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
	}

	@Column(name = "JKZK")
	public String getJkzk() {
		return this.jkzk;
	}

	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}

	@Column(name = "KSTZ")
	public String getKstz() {
		return this.kstz;
	}

	public void setKstz(String kstz) {
		this.kstz = kstz;
	}

	@Column(name = "RXWHCD")
	public String getRxwhcd() {
		return this.rxwhcd;
	}

	public void setRxwhcd(String rxwhcd) {
		this.rxwhcd = rxwhcd;
	}

	@Column(name = "SYSF")
	public String getSysf() {
		return this.sysf;
	}

	public void setSysf(String sysf) {
		this.sysf = sysf;
	}

	@Column(name = "BYLB")
	public String getBylb() {
		return this.bylb;
	}

	public void setBylb(String bylb) {
		this.bylb = bylb;
	}

	@Column(name = "SYDQ")
	public String getSydq() {
		return this.sydq;
	}

	public void setSydq(String sydq) {
		this.sydq = sydq;
	}

	@Column(name = "KLDM")
	public String getKldm() {
		return this.kldm;
	}

	public void setKldm(String kldm) {
		this.kldm = kldm;
	}

	@Column(name = "RXCJ")
	public String getRxcj() {
		return this.rxcj;
	}

	public void setRxcj(String rxcj) {
		this.rxcj = rxcj;
	}

	@Column(name = "XXTJ")
	public String getXxtj() {
		return this.xxtj;
	}

	public void setXxtj(String xxtj) {
		this.xxtj = xxtj;
	}

	@Column(name = "TC")
	public String getTc() {
		return this.tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	@Column(name = "RXSJ")
	public String getRxsj() {
		return this.rxsj;
	}

	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
	}

	@Column(name = "ZY_ID_LQ")
	public String getZyIdLq() {
		return this.zyIdLq;
	}

	public void setZyIdLq(String zyIdLq) {
		this.zyIdLq = zyIdLq;
	}

	@Column(name = "XZ")
	public String getXz() {
		return this.xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	@Column(name = "RXNF")
	public String getRxnf() {
		return this.rxnf;
	}

	public void setRxnf(String rxnf) {
		this.rxnf = rxnf;
	}

	@Column(name = "RXFS")
	public String getRxfs() {
		return this.rxfs;
	}

	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}

	@Column(name = "PYLB")
	public String getPylb() {
		return this.pylb;
	}

	public void setPylb(String pylb) {
		this.pylb = pylb;
	}

	@Column(name = "PYDX")
	public String getPydx() {
		return this.pydx;
	}

	public void setPydx(String pydx) {
		this.pydx = pydx;
	}

	@Column(name = "PYCC")
	public String getPycc() {
		return this.pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	@Column(name = "BXFS")
	public String getBxfs() {
		return this.bxfs;
	}

	public void setBxfs(String bxfs) {
		this.bxfs = bxfs;
	}

	@Column(name = "XXNX")
	public String getXxnx() {
		return this.xxnx;
	}

	public void setXxnx(String xxnx) {
		this.xxnx = xxnx;
	}

	@Column(name = "QTBXXS")
	public String getQtbxxs() {
		return this.qtbxxs;
	}

	public void setQtbxxs(String qtbxxs) {
		this.qtbxxs = qtbxxs;
	}

	@Column(name = "ZXWYYZ")
	public String getZxwyyz() {
		return this.zxwyyz;
	}

	public void setZxwyyz(String zxwyyz) {
		this.zxwyyz = zxwyyz;
	}

	@Column(name = "ZXWYJB")
	public String getZxwyjb() {
		return this.zxwyjb;
	}

	public void setZxwyjb(String zxwyjb) {
		this.zxwyjb = zxwyjb;
	}

	@Column(name = "BXLX")
	public String getBxlx() {
		return this.bxlx;
	}

	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}

	@Column(name = "BYZX")
	public String getByzx() {
		return this.byzx;
	}

	public void setByzx(String byzx) {
		this.byzx = byzx;
	}

	@Column(name = "SG")
	public String getSg() {
		return this.sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	@Column(name = "TZ")
	public String getTz() {
		return this.tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	@Column(name = "JSJNLDJ")
	public String getJsjnldj() {
		return this.jsjnldj;
	}

	public void setJsjnldj(String jsjnldj) {
		this.jsjnldj = jsjnldj;
	}

	@Column(name = "JTZZ")
	public String getJtzz() {
		return this.jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	@Column(name = "YH_ID")
	public String getYhId() {
		return this.yhId;
	}

	public void setYhId(String yhId) {
		this.yhId = yhId;
	}

	@Column(name = "XY_ID")
	public String getXyId() {
		return this.xyId;
	}

	public void setXyId(String xyId) {
		this.xyId = xyId;
	}

	@Column(name = "ZY_ID")
	public String getZyId() {
		return this.zyId;
	}

	public void setZyId(String zyId) {
		this.zyId = zyId;
	}

	@Column(name = "NJ_ID")
	public String getNjId() {
		return this.njId;
	}

	public void setNjId(String njId) {
		this.njId = njId;
	}

	@Column(name = "BJ_ID")
	public String getBjId() {
		return this.bjId;
	}

	public void setBjId(String bjId) {
		this.bjId = bjId;
	}

	@Column(name = "ZHIWEN_ID1")
	public String getZhiwenId1() {
		return this.zhiwenId1;
	}

	public void setZhiwenId1(String zhiwenId1) {
		this.zhiwenId1 = zhiwenId1;
	}

	@Column(name = "ZHIWEN_ID2")
	public String getZhiwenId2() {
		return this.zhiwenId2;
	}

	public void setZhiwenId2(String zhiwenId2) {
		this.zhiwenId2 = zhiwenId2;
	}

	@Column(name = "ZHAOPIAN_ID")
	public String getZhaopianId() {
		return this.zhaopianId;
	}

	public void setZhaopianId(String zhaopianId) {
		this.zhaopianId = zhaopianId;
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

	@Column(name = "XYMC")
	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String xYMC) {
		XYMC = xYMC;
	}

	@Column(name = "ZYMC")
	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	@Column(name = "NJMC")
	public String getNJMC() {
		return NJMC;
	}

	public void setNJMC(String nJMC) {
		NJMC = nJMC;
	}

	@Column(name = "BJMC")
	public String getBJMC() {
		return BJMC;
	}

	public void setBJMC(String bJMC) {
		BJMC = bJMC;
	}
	@Column(name = "MODIFY_NUM")
	public String getMODIFY_NUM() {
		return MODIFY_NUM;
	}

	public void setMODIFY_NUM(String mODIFY_NUM) {
		MODIFY_NUM = mODIFY_NUM;
	}

}