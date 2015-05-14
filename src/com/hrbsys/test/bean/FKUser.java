package com.hrbsys.test.bean;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="fkuser")
public class FKUser implements Serializable {
	@Id
	private String ids;
	
	@Column(name = "names")
	private String username;
	@Column(name = "passwords")
	private String userpwd;
	public String getUserId() {
	return ids;
	}
	public void setUserId(String userId) {
	this.ids = userId;
	}
	public String getUsername() {
	return username;
	}
	public void setUsername(String username) {
	this.username = username;
	}
	public String getUserpwd() {
	return userpwd;
	}
	public void setUserpwd(String userpwd) {
	this.userpwd = userpwd;
	}
	}