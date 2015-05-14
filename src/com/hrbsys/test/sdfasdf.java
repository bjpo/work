package com.hrbsys.test;

import java.math.BigDecimal;

public class sdfasdf {
private String abcasdf;

	public String getAbcasdf() {
	return abcasdf;
}
public void setAbcasdf(String abcasdf) {
	this.abcasdf = abcasdf;
}
	static int multiply(int n){        
		if(n==1||n==0)        
		return n;        
		else        
			System.out.println(n);
		return n*multiply(n-1);        
	}   
	public static void main(String[] args) {
		String zheng_rs="1";
		String zong_rs="6";
		String que_rs="2";
		String chi_rs="1";
		String zao_rs="1";
		float cql=(Integer.parseInt(zheng_rs)/Integer.parseInt(zong_rs))*100; //出勤率
		float qxl=(Integer.parseInt(que_rs)/Integer.parseInt(zong_rs))*100;//缺席率
		float cdl=(Integer.parseInt(chi_rs)/Integer.parseInt(zong_rs))*100;//迟到率
		float ztl=(Integer.parseInt(zao_rs)/Integer.parseInt(zong_rs))*100;//早退率
		System.out.println(Integer.parseInt(zheng_rs));
		System.out.println(Integer.parseInt(zong_rs));
		System.out.println((Integer.parseInt(zheng_rs)/Integer.parseInt(zong_rs))*100);
		System.out.println(cql);
		double asdf=5;

		
		// 方式三
		String result = String.format("%.2f", 1/6);
		System.out.println(result);
		double acceptNum = 10;
		double handledNum = 3;
		Double d = Double.valueOf((acceptNum / handledNum));
		System.out.println(d);

		
		
	}
}
