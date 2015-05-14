package com.hrbsys.tools;

public class TeamPrint {

	public static void println(Class c,String method,String leixing,String str){
		if(null==leixing){
			leixing="";
		}else{
			leixing="["+leixing+"]";
		}
		System.out.println(c.getName()+"类中["+method+"]方法 打印的"+leixing+"语句是："+str);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		println(TeamPrint.class,"main","SQL","哈哈哈哈哈哈哈哈");
	}

}
