package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生选课类
 */

@Entity
@Table(name = "SKXSXX")
public class SKXSXX implements Serializable {
	@Id
	@Column(name = "KCXS_ID")
	private String KCXS_ID;
	@Column(name = "KCB_KCXXMC")
	private String KCB_KCXXMC;
	@Column(name = "KCB_FXS_ID")
	private String KCB_FXS_ID;
	@Column(name = "XSXM")
	private String XSXM;
	@Column(name = "XUEHAO")
	private String XUEHAO;
	@Column(name = "KCB_ID")
	private String KCB_ID;
	@Column(name = "XS_ID")
	private String XS_ID;

	/*************************** get和set方法 ******************************/
	public String getKCXS_ID() {
		return KCXS_ID;
	}

	public void setKCXS_ID(String KCXS_ID) {
		this.KCXS_ID = KCXS_ID;
	}

	public String getKCB_FXS_ID() {
		return KCB_FXS_ID;
	}

	public void setKCB_FXS_ID(String kCB_FXS_ID) {
		KCB_FXS_ID = kCB_FXS_ID;
	}

	public String getKCB_KCXXMC() {
		return KCB_KCXXMC;
	}

	public void setKCB_KCXXMC(String KCB_KCXXMC) {
		this.KCB_KCXXMC = KCB_KCXXMC;
	}

	public String getXSXM() {
		return XSXM;
	}

	public void setXSXM(String XSXM) {
		this.XSXM = XSXM;
	}

	public String getXUEHAO() {
		return XUEHAO;
	}

	public void setXUEHAO(String XUEHAO) {
		this.XUEHAO = XUEHAO;
	}

	public String getKCB_ID() {
		return KCB_ID;
	}

	public void setKCB_ID(String KCB_ID) {
		this.KCB_ID = KCB_ID;
	}

	public String getXS_ID() {
		return XS_ID;
	}

	public void setXS_ID(String XS_ID) {
		this.XS_ID = XS_ID;
	}
}