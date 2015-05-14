package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mokuai")
public class MOKUAI implements Serializable {
	@Id
	@Column(name = "mk_id")
	private String mkid;

	@Column(name = "mkmc")
	private String mkmc;

	@Column(name = "mkbm")
	private String mkbm;

	@Column(name = "ms")
	private String ms;

	@Column(name = "bz")
	private String bz;

	@Column(name = "mkurl")
	private String mkurl;

	@Column(name = "djrq")
	private String djrq;

	@Column(name = "xgrq")
	private String xgrq;

	@Column(name = "openstate")
	private String openstate;

	@Column(name = "iconcls")
	private String iconcls;

	@Column(name = "fk_mk")
	private String fkmk;

	@Column(name = "paixu")
	private String paixu;

	@Column(name = "mkym")
	private String mkym;
	@Column(name = "MENUCATE_ID")
	private String menuCateId;

	public String getMkym() {
		return mkym;
	}

	public void setMkym(String mkym) {
		this.mkym = mkym;
	}

	public String getIsShowInLeftMenu() {
		return isShowInLeftMenu;
	}

	public void setIsShowInLeftMenu(String isShowInLeftMenu) {
		this.isShowInLeftMenu = isShowInLeftMenu;
	}

	@Column(name = "isshowinleftmenu")
	private String isShowInLeftMenu;

	@Column(name = "fk_mkmc")
	private String fkmkmc;

	public String getFkmkmc() {
		return fkmkmc;
	}

	public void setFkmkmc(String fkmkmc) {
		this.fkmkmc = fkmkmc;
	}

	public String getPaixu() {
		return paixu;
	}

	public void setPaixu(String paixu) {
		this.paixu = paixu;
	}

	public String getMkid() {
		return mkid;
	}

	public void setMkid(String mkid) {
		this.mkid = mkid;
	}

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getMkbm() {
		return mkbm;
	}

	public void setMkbm(String mkbm) {
		this.mkbm = mkbm;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getMkurl() {
		return mkurl;
	}

	public void setMkurl(String mkurl) {
		this.mkurl = mkurl;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public String getXgrq() {
		return xgrq;
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getOpenstate() {
		return openstate;
	}

	public void setOpenstate(String openstate) {
		this.openstate = openstate;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getFkmk() {
		return fkmk;
	}

	public void setFkmk(String fkmk) {
		this.fkmk = fkmk;
	}

	public String getMenuCateId() {
		return menuCateId;
	}

	public void setMenuCateId(String menuCateId) {
		this.menuCateId = menuCateId;
	}
}
