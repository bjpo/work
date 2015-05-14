package com.hrbsys.bean;

import java.io.Serializable;

/**
 * 开放性课程实体类
 */
public class KaiFang implements Serializable {

	/*
	 * 首页用到的字段
	 */
	private String kcmc;// 课程名称
	private String skjs;// 上课的老师
	private String skfj;// 上课房间
	private String zc;// 正常
	private String cd;// 迟到
	private String zt;// 早退
	private String cdAndZt;// 迟到与早退
	private String sksj;// 上课时间

	/*
	 * 详细页面用到的字段
	 */
	private String xh;//学生的学号
	private String xm;//学生的姓名
	private String cqqk;//出勤情况
	private String skdk;//上课打卡时间
	private String xkdk;//下课打卡时间

	/*
	 * 首页用到的get与set方法
	 */

	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getSkjs() {
		return skjs;
	}
	public void setSkjs(String skjs) {
		this.skjs = skjs;
	}
	public String getSkfj() {
		return skfj;
	}
	public void setSkfj(String skfj) {
		this.skfj = skfj;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getCdAndZt() {
		return cdAndZt;
	}
	public void setCdAndZt(String cdAndZt) {
		this.cdAndZt = cdAndZt;
	}
	public String getSksj() {
		return sksj;
	}
	public void setSksj(String sksj) {
		this.sksj = sksj;
	}

	/*
	 * 详细页面用到的get与set方法
	 */

	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getCqqk() {
		return cqqk;
	}
	public void setCqqk(String cqqk) {
		this.cqqk = cqqk;
	}
	public String getSkdk() {
		return skdk;
	}
	public void setSkdk(String skdk) {
		this.skdk = skdk;
	}
	public String getXkdk() {
		return xkdk;
	}
	public void setXkdk(String xkdk) {
		this.xkdk = xkdk;
	}

}
