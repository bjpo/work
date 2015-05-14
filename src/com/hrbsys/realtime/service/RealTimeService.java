package com.hrbsys.realtime.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.hrbsys.bean.XueShengKQ;

public interface RealTimeService {
	public HashMap<String, Integer> getKQQK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi);// 参数：教室、老师、课时、课程、日期

	public HashMap<String, Integer> getKQQK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi, String andparam,
			String nianjiparam, String banjiparam, String zhuanyeparam); // 增加参数：

	public HashMap<String, Integer> getKQQK_XUESHENG_TONGJI(String jiaoshi,
			String laoshi, String keshi, String kecheng, String riqi,
			String andparam, String nianjiparam, String banjiparam, String xsid); // 按照学生去统计:

	public ArrayList<XueShengKQ> getKQQK_XS(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi, String xs_id,
			String cqqk, String xuehao);// 参数：教室、老师、课时、课程、日期、学生id

	public ArrayList<XueShengKQ> getKQQK_XSbyJGorJS(String jiaoshi,
			String laoshi, String keshi, String kecheng, String riqi);// 按老师/教室查询出勤情

	public ArrayList<XueShengKQ> getKQQK_KFK(String jiaoshi, String laoshi,
			String keshi, String kecheng, String riqi);

	// 公共选修课实时查询接口
	public ArrayList<XueShengKQ> getKQQK_GGK(String jiaoshi, String laoshi,String keshi, String kecheng, String riqi);
}
