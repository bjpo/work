package com.hrbsys.business;

public class KechengbiaoTools {
	//定义三个常量：分别代表排课和查看课程信息的时候，可以查看/排课哪些人的课程。
	/**
	 * 课程信息类别表中对应关系：a:可为全校教师排课。
	 * 						   b:可为全学院教师排课。
	 * 						   c：只能为自己排课。
	 * **/
	public static final  String QUANXIAO = "xuexiao"; //可以排课全学校的所有教师。
	public static final  String XUEYUAN = "xueyuan";//可以排课全学院所有教师。
	public static final  String GEREN = "geren"; //只能为自己排课
	public static final  String BUNENGPK="buneng"; //不能为任何人排课
	public static final  String NOJIAOGONGID="NOJIAOGONGID";//没有教工ID
	private static final String K_QUANXIAO="a";
	private static final String K_XUEYUAN="b";
	private static final String K_GEREN="c";
	//数据库中的排课权限转化为定义的变量对应关系。
	public static String getJGLBtoPAIKEQX(String tmp){
		if(K_QUANXIAO.equals(tmp)){
			return QUANXIAO;
		}
		if(K_XUEYUAN.equals(tmp)){
			return XUEYUAN;
		}
		if(K_GEREN.equals(tmp)){
			return GEREN;
		}
		return BUNENGPK;
	}
//	public static void main(String[] args) {
//		System.out.println(getJGLBtoPAIKEQX("d"));
//	}
}
