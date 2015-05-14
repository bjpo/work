package com.hrbsys.tools;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseChangeTool {
	/**
	 * 
	 * 工具方法：星期转换为星期序号
	 * 
	 * */
	public static String getXingQiXH(String xingqi){
		if(null != xingqi && !"".equals(xingqi)){
			if("星期日".equals(xingqi.trim())){
				return "1";
			}
			if("星期一".equals(xingqi.trim())){
				return "2";
			}
			if("星期二".equals(xingqi.trim())){
				return "3";
			}
			if("星期三".equals(xingqi.trim())){
				return "4";
			}
			if("星期四".equals(xingqi.trim())){
				return "5";
			}
			if("星期五".equals(xingqi.trim())){
				return "6";
			}
			if("星期六".equals(xingqi.trim())){
				return "7";
			}
		}
		return null;
	}
	
	public static String getXingQibyXingQiXH(String xingqi){
		if(null != xingqi && !"".equals(xingqi)){
			if("1".equals(xingqi.trim())){
				return "星期日";
			}
			if("2".equals(xingqi.trim())){
				return "星期一";
			}
			if("3".equals(xingqi.trim())){
				return "星期二";
			}
			if("4".equals(xingqi.trim())){
				return "星期三";
			}
			if("5".equals(xingqi.trim())){
				return "星期四";
			}
			if("6".equals(xingqi.trim())){
				return "星期五";
			}
			if("7".equals(xingqi.trim())){
				return "星期六";
			}
		}
		return null;
	}
	
	 /**
	  * 工具方法：时间段内有多少天：将结果返回
	  * **/
	 public static ArrayList<String> getDateArrays(String start1, String end1) {
		 ArrayList<String> list=new ArrayList<String>();
		 try{
			 int calendarType=Calendar.DAY_OF_MONTH;
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		 Date start=sf.parse(start1);
		 Date end =sf.parse(end1);
		 ArrayList ret = new ArrayList();
	     Calendar calendar = Calendar.getInstance();
	     calendar.setTime(start);
	     Date tmpDate = calendar.getTime();
	     long endTime = end.getTime();
	     while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
	       ret.add(calendar.getTime());
	       calendar.add(calendarType, 1);
	       tmpDate = calendar.getTime();
	     }
	     Date[] dates = new Date[ret.size()];
	     Date[] ds=(Date[]) ret.toArray(dates);
	     for(Date tmpd:ds){
	    	 String tmp=sf.format(tmpd);
	    	 list.add(tmp);
	     }
		 } catch(Exception e){
			 e.printStackTrace();
		 }
		 return list;
	   } 
	 
	 
	 /**
	  * 工具方法：以第一个参数为基准，比对后一个日期和他相差几周
	  * **/
		public static String getWeekCounterByEventStartDate(String event_s_date, String dailyDate) {
	        if (isSameWeek(event_s_date, dailyDate)) {
	            return "1";
	        }
	        Calendar c_base = Calendar.getInstance();
	        Calendar c2 = Calendar.getInstance();
	        Calendar tempC = Calendar.getInstance();
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        int week = 0;
	        try {
	            c_base.setTime(df.parse(event_s_date));
	            c2.setTime(df.parse(dailyDate));
	            Long time1 = c2.getTimeInMillis();
	            Long time2 = c_base.getTimeInMillis();
	            Long dayDiffer = Math.abs(time1 - time2) / (1000 * 60 * 60 * 24);//毫秒*秒*分*小时
	            if (dayDiffer.intValue() == 0) {
	                week++;
	                return "" + week;
	            }
	            int curTodayWeek = c_base.get(Calendar.DAY_OF_WEEK) - 1;
	            int differIsWeek = 7 - curTodayWeek;
	            tempC = (Calendar) c_base.clone();

	            if (differIsWeek != 0) {
	                week++;
	            }
	            if (differIsWeek != 7) {
	                tempC.add(Calendar.DAY_OF_YEAR, differIsWeek);
	            }
	            time1 = c2.getTimeInMillis();
	            time2 = tempC.getTimeInMillis();
	            dayDiffer = Math.abs(time1 - time2) / (1000 * 60 * 60 * 24);//毫秒*秒*分*小时
	            if (dayDiffer.intValue() == 1) {//差一天
	                week++;
	            } else if (dayDiffer.intValue() < 7) {
	                week++;
	            } else {
	                DecimalFormat decimalFormat = new DecimalFormat("#.0");
	                String dayNum = decimalFormat.format((double) dayDiffer.intValue() / 7);
	                int day = Integer.parseInt(dayNum.substring(0, dayNum.indexOf(".")));
	                week += day;
	                int dec = Integer.parseInt(dayNum.substring(dayNum.indexOf(".") + 1, dayNum.length()));
	                if (dec != 0) {
	                    week++;
	                }
	            }
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	            return "";
	        }
	        return "" + week;
	    }
	    //第一种  isSameWeek
	    public static boolean isSameWeek(String date1, String date2) {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date d1 = null;
	        Date d2 = null;
	        try {
	            d1 = format.parse(date1);
	            d2 = format.parse(date2);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Calendar cal1 = Calendar.getInstance();
	        Calendar cal2 = Calendar.getInstance();
	        cal1.setTime(d1);
	        cal2.setTime(d2);

	        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	        int subMonth = (cal1.get(Calendar.MONTH) + 1) - (cal2.get(Calendar.MONTH) + 1);

	        //同一年，同一月
	        if (subYear == 0 && subMonth == 0) {
	            int week1 = cal1.get(Calendar.WEEK_OF_MONTH);
	            int week2 = cal2.get(Calendar.WEEK_OF_MONTH);
	            int wd1 = cal1.get(Calendar.DAY_OF_WEEK);
	            int wd2 = cal2.get(Calendar.DAY_OF_WEEK);
	            if (wd1 == 1) {
	                week1 -= 1;
	            }
	            if (wd2 == 1) {
	                week2 -= 1;
	            }
	            return week1 == week2;
	        }
	        return false;
	    }
	    public static boolean isSameWeek(Date d1, Date d2) {
	        SimpleDateFormat format = new SimpleDateFormat("yyyyww");//年周
	        Calendar cal = Calendar.getInstance();
	        cal.setFirstDayOfWeek(Calendar.MONDAY);//Calendar.DAY_OF_WEEK 是不被改变的
	        format.setCalendar(cal);
	        return format.format(d1).equals(format.format(d2));//format.format(d1) 年+一年的第几周字符串例如 2013-8-1 是一年的第31周
	    }

	    
	    
	    
	    
	  /**
	   * 工具方法：给定的日期是星期几
	   * */
		public static String getWeek(Date date){
			String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(week_index<0){
				week_index = 0;
			} 
			return weeks[week_index];
		}
	    
	    /**
	     * 获得第一个日期后，第二个参数天。
	     * */
		public static String getDateAfter(String riqi, int day) {
	    	try{
		    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				Date d=sf.parse(riqi);
		        Calendar now = Calendar.getInstance();
		        now.setTime(d);
		        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		        return sf.format(now.getTime());
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	    
		/**
		 * 工具方法：开学周，以星期一为开学第一天。获得几周后的一个星期，或指定星期是哪天。
		 * */
		public static ArrayList<String> getRIQIhouZHOUXINGQI(String riqi,String xingqi,int zengjiazhou){
			ArrayList<String> all=new ArrayList<String>();
		
			try{
				if((null != xingqi) && !"".equals(xingqi)){
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					Date d=sf.parse(riqi);
					String riqistr=getWeek(d);
					if("星期一".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7));
					}
					if("星期二".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7+1));
					}
					if("星期三".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7+2));
					}
					if("星期四".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7+3));
					}
					if("星期五".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7+4));
					}
					if("星期六".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7+5));
					}
					if("星期日".equals(xingqi.trim())){
						all.add(getDateAfter(riqi,(zengjiazhou-1)*7+6));
					}
				}else{
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7));
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7+1));
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7+2));
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7+3));
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7+4));
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7+5));
					all.add(getDateAfter(riqi,(zengjiazhou-1)*7+6));
				}				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return all;
		}
		
		
		/**
		 * 获得学期中的第几周从哪天开始到哪天结束
		 * 
		 * */
		public static int getZHOUtoInt(String zhou){
			if("第一周".equals(zhou)){
				return 1;
			}
			if("第二周".equals(zhou)){
				return 2;
			}
			if("第三周".equals(zhou)){
				return 3;
			}
			if("第四周".equals(zhou)){
				return 4;
			}
			if("第五周".equals(zhou)){
				return 5;
			}
			if("第六周".equals(zhou)){
				return 6;
			}
			if("第七周".equals(zhou)){
				return 7;
			}
			if("第八周".equals(zhou)){
				return 8;
			}
			if("第九周".equals(zhou)){
				return 9;
			}
			if("第十周".equals(zhou)){
				return 10;
			}
			if("第十一周".equals(zhou)){
				return 11;
			}
			if("第十二周".equals(zhou)){
				return 12;
			}
			if("第十三周".equals(zhou)){
				return 13;
			}
			if("第十四周".equals(zhou)){
				return 14;
			}
			if("第十五周".equals(zhou)){
				return 15;
			}
			if("第十六周".equals(zhou)){
				return 16;
			}
			if("第十七周".equals(zhou)){
				return 17;
			}
			if("第十八周".equals(zhou)){
				return 18;
			}
			return 1;
		}
		

		
		
		public static String changeZhouIDtoZHOU(String zhou){
			if(null==zhou||"".equals(zhou)){
				return null;
			}
			if("1".equals(zhou)){
				return "第一周";
			}
			if("2".equals(zhou)){
				return "第二周";
			}
			if("3".equals(zhou)){
				return "第三周";
			}
			if("4".equals(zhou)){
				return "第四周";
			}
			if("5".equals(zhou)){
				return "第五周";
			}
			if("6".equals(zhou)){
				return "第六周";
			}
			if("7".equals(zhou)){
				return "第七周";
			}
			if("8".equals(zhou)){
				return "第八周";
			}
			if("9".equals(zhou)){
				return "第九周";
			}
			if("10".equals(zhou)){
				return "第十周";
			}
			if("11".equals(zhou)){
				return "第十一周";
			}
			if("12".equals(zhou)){
				return "第十二周";
			}
			if("13".equals(zhou)){
				return "第十三周";
			}
			if("14".equals(zhou)){
				return "第十四周";
			}
			if("15".equals(zhou)){
				return "第十五周";
			}
			if("16".equals(zhou)){
				return "第十六周";
			}
			if("17".equals(zhou)){
				return "第十七周";
			}
			if("18".equals(zhou)){
				return "第十八周";
			}
			return null;
		}
		
		
		
		
		public static String getKQQKbyChar(String kqjg){
			String tmp="";
			if("a".equals(kqjg)){
				tmp="正常";
			}
			if("b".equals(kqjg)){
				tmp="迟到";
			}
			if("c".equals(kqjg)){
				tmp="早退";
			}
			if("d".equals(kqjg)){
				tmp="既迟到又早退";
			}
			if("e".equals(kqjg)){
				tmp="缺席";
			}
			if("x".equals(kqjg)){
				tmp="该学生不需要上此课程";
			}
			return tmp;
		}
		
		//解码url传值
		public static String decodeURIValue(String value){
			
			try {
				if(null!=value){
					return java.net.URLDecoder.decode(value , "UTF-8");	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		public static String getCurrentTime(){
			try{
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sf.format(new Date());
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
		
		
	    public static void main(String[] args) {
	    	System.out.println(getCurrentTime());
		}
}