package com.hrbsys.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="kcb_bj")
public class KCBBJ  implements Serializable {
	@Id
	@Column(name="kcbbj_id")
	private String kcbbj_id;
	@Column(name="kcb_id")
	private String kcb_id;
	@Column(name="bj_id")
	private String bj_id;
	
	public String getKcbbj_id() {
		return kcbbj_id;
	}
	public void setKcbbj_id(String kcbbj_id) {
		this.kcbbj_id = kcbbj_id;
	}
	public String getKcb_id() {
		return kcb_id;
	}
	public void setKcb_id(String kcb_id) {
		this.kcb_id = kcb_id;
	}
	public String getBj_id() {
		return bj_id;
	}
	public void setBj_id(String bj_id) {
		this.bj_id = bj_id;
	}
	
}
