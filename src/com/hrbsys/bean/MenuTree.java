package com.hrbsys.bean;

import java.util.ArrayList;
import java.util.List;

public class MenuTree implements Comparable  {
	private String id;
	private String text;
	private String fid;
	private String state;
	private String iconCls;
	private String paixu;
	public String getPaixu() {
		return paixu;
	}
	public void setPaixu(String paixu) {
		this.paixu = paixu;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	private Attributes attributes;
	private List<MenuTree> children=new ArrayList<MenuTree>();
	

	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	@Override
	public int compareTo(Object o) {
		int tmp=0;
		 if(!(o instanceof MenuTree)){
			MenuTree s =(MenuTree) o;   
	        if(new Double(this.paixu) > new Double(s.getPaixu())){
	        	tmp=1;
	        }else if(new Double(this.paixu) == new Double(s.getPaixu())){
	        	tmp=0;
	        }
	        tmp= -1;   
		 }
		return tmp;
	}
	
}
