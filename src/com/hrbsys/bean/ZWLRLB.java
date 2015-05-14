package com.hrbsys.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "ZWLRLB")
public class ZWLRLB implements Serializable {
	
	@Id
	@Column(name = "ZWLR_ID")
	private String ZWLR_ID;

	public String getZWLR_ID() {
		return ZWLR_ID;
	}
	public void setZWLR_ID(String ZWLR_ID) {
		this.ZWLR_ID = ZWLR_ID;
	}
	@Column(name = "LBMC")
	private String LBMC;

	public String getLBMC() {
		return LBMC;
	}
	public void setLBMC(String LBMC) {
		this.LBMC = LBMC;
	}
	@Column(name = "SFLR")
	private String SFLR;

	public String getSFLR() {
		return SFLR;
	}
	public void setSFLR(String SFLR) {
		this.SFLR = SFLR;
	}
	@Column(name = "ZWLRSJ")
	private String ZWLRSJ;

	public String getZWLRSJ() {
		return ZWLRSJ;
	}
	public void setZWLRSJ(String ZWLRSJ) {
		this.ZWLRSJ = ZWLRSJ;
	}
	@Column(name = "LBCJSJ")
	private String LBCJSJ;

	public String getLBCJSJ() {
		return LBCJSJ;
	}
	public void setLBCJSJ(String LBCJSJ) {
		this.LBCJSJ = LBCJSJ;
	}
	@Column(name="LASTMODIFYTIME")
	private String 	LASTMODIFYTIME;

	public String getLASTMODIFYTIME() {
		return LASTMODIFYTIME;
	}
	public void setLASTMODIFYTIME(String lASTMODIFYTIME) {
		LASTMODIFYTIME = lASTMODIFYTIME;
	}
	

}