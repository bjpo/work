package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 配置查询条件 实体类(校长前台)
 *
 */
@Entity
@Table(name="CONDITIONS")
public class CONDITIONS implements Serializable {
	@Id
	@Column(name="ID")
	private String id;//配置查询条件 主键
	@Column(name="XY_ID")
	private String xy_id;//学院id
	@Column(name="ZY_ID")
	private String zy_id;//专业id
	@Column(name="NJ_ID")
	private String nj_id;//年级id
	@Column(name="BJ_ID")
	private String bj_id;//班级id
	@Column(name="QUERYTITLE")
	private String queryTitle;//查询标题
	@Column(name="KSRQ")
	private String ksrq;//开始日期
	@Column(name="JSRQ")
	private String jsrq;//结束日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXy_id() {
		return xy_id;
	}
	public void setXy_id(String xy_id) {
		this.xy_id = xy_id;
	}
	public String getZy_id() {
		return zy_id;
	}
	public void setZy_id(String zy_id) {
		this.zy_id = zy_id;
	}
	public String getNj_id() {
		return nj_id;
	}
	public void setNj_id(String nj_id) {
		this.nj_id = nj_id;
	}
	public String getBj_id() {
		return bj_id;
	}
	public void setBj_id(String bj_id) {
		this.bj_id = bj_id;
	}
	public String getQueryTitle() {
		return queryTitle;
	}
	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	@Override
	public String toString() {
		return "CONDITIONS [id=" + id + ", xy_id=" + xy_id + ", zy_id=" + zy_id
				+ ", nj_id=" + nj_id + ", bj_id=" + bj_id + ", queryTitle="
				+ queryTitle + ", ksrq=" + ksrq + ", jsrq=" + jsrq + "]";
	}
	
}
