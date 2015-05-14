package com.hrbsys.tools;

public class XingQiToXingQiXH {
  public static String toXH(String xingqi){
	  if(null!=xingqi&&!"".equals(xingqi)){
		  if("星期一".equals(xingqi)){
			  return "2";
		  }
		  if("星期二".equals(xingqi)){
			  return "3";
		  }
		  if("星期三".equals(xingqi)){
			  return "4";
		  }
		  if("星期四".equals(xingqi)){
			  return "5";
		  }
		  if("星期五".equals(xingqi)){
			  return "6";
		  }
		  if("星期六".equals(xingqi)){
			  return "7";
		  }
		  if("星期日".equals(xingqi)){
			  return "1";
		  }
		  return "2";
	  }
	  return "2";
  }
}
