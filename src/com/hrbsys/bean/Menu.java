package com.hrbsys.bean;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单类
 * 
 */
@Entity
@Table(name = "MENU")
public class Menu implements Serializable {
	@Id
	@Column(name="MENUCATE_ID")
	private String menuCateId;// 菜单类别ID
	@Column(name="MENUNAME")
	private String menuName;//菜单名称
	@Column(name="MENUNOTES")
	private String menuNotes;//备注
	
	
	public String getMenuCateId() {
		return menuCateId;
	}
	public void setMenuCateId(String menuCateId) {
		this.menuCateId = menuCateId;
	}
	public String getMenuName() {
		return menuName;
	}
	public String getMenuNotes() {
		return menuNotes;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuNotes(String menuNotes) {
		this.menuNotes = menuNotes;
	}
	
	

}
