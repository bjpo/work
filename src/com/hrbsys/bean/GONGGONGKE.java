package com.hrbsys.bean;

import java.io.Serializable;

/**
 * 公共课实体类
 * 
 */
public class GONGGONGKE implements Serializable {
	/* 统计用到的字段 */
	private String YCXRS;// 应出席人数
	private String ZCCXRS;// 正常出席人数
	private String CDRS;// 迟到人数
	private String QXRS;// 缺席人数
	private String ZTRS;// 早退人数
	private String CDANDZT;// 迟到及早退

	/* 基础显示数据字段 */
	private String KCB_FXS_ID;// 课程表分学时ID
	private String KCB_ID;// 课程表ID
	private String LAOSHIMC;// 任课老师名称
	private String LAOSHI_ID;
	private String JSMC;// 教室名称
	private String JS_ID;// 教室的ID
	private String KS_ID;
	private String KCXXMC;// 课程信息名称
	private String KS_KSSJ;// 课时开始时间
	private String KS_JSSJ;// 课程的结束时间
	private String SKSJ;// 上课时间
	private String NJMC;// 年级名称
	private String BJMC;// 班级名
	private String ZYMC;// 专业名称
	private String XSXH;
	private String XB;
	private String ZSXM;
	private String CQZT;
	private String XS_ID;
	// private String XINGQI;// 星期
	// private String XUEQI;// 学期
	// private String ZHOU;// 周

	public String getYCXRS() {
		return YCXRS;
	}

	public String getSKSJ() {
		return SKSJ;
	}

	public void setSKSJ(String sKSJ) {
		SKSJ = sKSJ;
	}

	public void setYCXRS(String yCXRS) {
		YCXRS = yCXRS;
	}

	public String getZCCXRS() {
		return ZCCXRS;
	}

	public void setZCCXRS(String zCCXRS) {
		ZCCXRS = zCCXRS;
	}

	public String getCDRS() {
		return CDRS;
	}

	public void setCDRS(String cDRS) {
		CDRS = cDRS;
	}

	public String getQXRS() {
		return QXRS;
	}

	public void setQXRS(String qXRS) {
		QXRS = qXRS;
	}

	public String getZTRS() {
		return ZTRS;
	}

	public void setZTRS(String zTRS) {
		ZTRS = zTRS;
	}

	public String getLAOSHIMC() {
		return LAOSHIMC;
	}

	public void setLAOSHIMC(String lAOSHIMC) {
		LAOSHIMC = lAOSHIMC;
	}

	public String getJSMC() {
		return JSMC;
	}

	public void setJSMC(String jSMC) {
		JSMC = jSMC;
	}

	public String getKCXXMC() {
		return KCXXMC;
	}

	public void setKCXXMC(String kCXXMC) {
		KCXXMC = kCXXMC;
	}

	public String getKS_KSSJ() {
		return KS_KSSJ;
	}

	public void setKS_KSSJ(String kS_KSSJ) {
		KS_KSSJ = kS_KSSJ;
	}

	public String getKS_JSSJ() {
		return KS_JSSJ;
	}

	public void setKS_JSSJ(String kS_JSSJ) {
		KS_JSSJ = kS_JSSJ;
	}

	public String getKCB_FXS_ID() {
		return KCB_FXS_ID;
	}

	public void setKCB_FXS_ID(String kCB_FXS_ID) {
		KCB_FXS_ID = kCB_FXS_ID;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String kCB_ID) {
		KCB_ID = kCB_ID;
	}

	public String getCDANDZT() {
		return CDANDZT;
	}

	public void setCDANDZT(String cDANDZT) {
		CDANDZT = cDANDZT;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String jS_ID) {
		JS_ID = jS_ID;
	}

	public String getLAOSHI_ID() {
		return LAOSHI_ID;
	}

	public void setLAOSHI_ID(String lAOSHI_ID) {
		LAOSHI_ID = lAOSHI_ID;
	}

	public String getKS_ID() {
		return KS_ID;
	}

	public void setKS_ID(String kS_ID) {
		KS_ID = kS_ID;
	}

	public String getNJMC() {
		return NJMC;
	}

	public void setNJMC(String nJMC) {
		NJMC = nJMC;
	}

	public String getBJMC() {
		return BJMC;
	}

	public void setBJMC(String bJMC) {
		BJMC = bJMC;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	public String getXSXH() {
		return XSXH;
	}

	public void setXSXH(String xSXH) {
		XSXH = xSXH;
	}

	public String getXB() {
		return XB;
	}

	public void setXB(String xB) {
		XB = xB;
	}

	public String getZSXM() {
		return ZSXM;
	}

	public void setZSXM(String zSXM) {
		ZSXM = zSXM;
	}

	public String getCQZT() {
		return CQZT;
	}

	public void setCQZT(String cQZT) {
		CQZT = cQZT;
	}

	public String getXS_ID() {
		return XS_ID;
	}

	public void setXS_ID(String xS_ID) {
		XS_ID = xS_ID;
	}
	
	
}
