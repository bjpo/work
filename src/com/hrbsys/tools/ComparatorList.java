package com.hrbsys.tools;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.hrbsys.bean.MenuTree;
 

/**
 * 对List字符串数组进行排序
 * @author Administrator
 *
 */
public class ComparatorList implements Comparator
{
 private static final Logger logger = Logger.getLogger(ComparatorList.class);

 public int compare(Object value1, Object value2) 
 {
  //http://j2ee-yohn.javaeye.com/blog/272006  此帖子关于中文拼音排序很详细。
  // TODO Auto-generated method stub
  if(value1.getClass().getName().equals("org.openjweb.core.util.CodeNameBean"))
  {
   String s1 = ((MenuTree)value1).getText().toString();
      String s2 = ((MenuTree)value2).getText().toString();
      return Collator.getInstance(Locale.CHINESE).compare(s1, s2); 
  }
  else
  if(value1.getClass().getName().equals("java.lang.String"))
  {
   String s1 = value1.toString();
   String s2 = value2.toString();
    return Collator.getInstance(Locale.CHINESE).compare(s1, s2); 
  }
  return 0; //0表示相同。
 }

 
 
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
 
 
 public static List sort(List strList)
 {
  ComparatorList comp = new ComparatorList();
  Collections.sort(strList,comp);
  return strList; //返回排序后的列表
 }
 
// public static void main(String[] args) {
//	 
//	 ArrayList<String> list=getDateArrays("2017-14-01","2018-02-01");
//	 for(String d:list){
//		 System.out.println(d);
//	 }
//	 System.out.println(list.size());
//
//}
}

