package com.hrbsys.task.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.base.dao.impl.BaseDaoImpl;
import com.hrbsys.task.service.TaskService;
import com.hrbsys.tools.UUIDTools;

public class TaskServiceImpl implements TaskService {
	private BaseDao baseDao =new BaseDaoImpl();

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void doSQLbyDay(){
		updateKQXXbyDay();  //更新数据到汇总表
		doSQLbyDay_XUESHENG(null); //执行按学生计算方法
		doSQLbyDay_JIAOSHI(null);//执行按教室计算方法
		deleteKQXX_DAYto7days();//删除7天前的考勤记录
	}

	/**
	 * 每天跑SQL，学生信息
	 * 算法分析：
	 * 1、学生如果在课时阶段没有考勤记录，并且在应上课列表中，则视为 【缺席】。
	 * 2、学生在上课时间点A 加减10分钟内考勤，并且学生在下课时间点B加减十分钟内有考勤记录，则视为【正常出勤】。
	 * 3、学生在上课时间点A 加减10分钟内考勤，并且在下课时间点B加减十分钟内无考勤，并且在上课时间点A之后，在下课时间点B之前考勤，则视为【早退】
	 * 4、学生在上课时间点A 加减10分钟内无考勤，并且在下课时间点B加减十分钟内有考勤，并且上课时间点A之后，在下课时间点B之前考勤，则视为【迟到】
	 * 5、若学生在应上课学生表中，又不在上述范围内，则视为【既迟到又早退】
	 * */
	@Override
	public void doSQLbyDay_XUESHENG(String riqi) {
		logComputeStepToDatabase("计算学生考勤信息","开始","成功","0000","0000");
		try{
		Date kqdate;
		String kqdateStr;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=riqi&&!"".equals(riqi)){
			kqdate=df.parse(riqi);	
		}else{
			kqdate=new Date();
		}
				
		kqdateStr=df.format(kqdate);//设置考勤时间的值
		
		//判断日期是否在学期当中
		String isInTermSQL="select * from xueqi where  to_date(JSRQ,'yyyy-mm-dd')" +
				">=to_date('"+kqdateStr+"','yyyy-mm-dd') and to_date(KSRQ,'yyyy-mm-dd')<=to_date('"+kqdateStr+"','yyyy-mm-dd')";
		List isInTermList=baseDao.executeSQL(isInTermSQL);//列出当天所有课程
		//如果不在学期当中，则直接退出
		if(isInTermList.size()<1){
			logComputeStepToDatabase("计算学生考勤信息","结束","非学期当中，不予计算","0000","0000");
			return ;
		}
		//删除当天的考勤信息
		String sqldel="delete from kqxx_xscq t where t.sksj >=to_date('"+kqdateStr+"','yyyy-mm-dd') and t.sksj<to_date('"+kqdateStr+"','yyyy-mm-dd')+1";
		System.out.println("删除当天的考勤信息SQL为："+sqldel);
		baseDao.executeDoSQL(sqldel);
		
		System.out.println(" 按课时【学生】考勤信息统计，开始####################################################################################");
		List all_kcb=baseDao.executeSQL("select * from kechengb a where 1=1 and kcblb in ('专业选修课','专业必修课','公共选修课') and a.xingqixh=(SELECT to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'D') FROM DUAL)");//列出当天所有课程
		for(int kcb_i=0;kcb_i<all_kcb.size();kcb_i++){ //循环课程列表，计算考勤信息
			Object[] tmpObjArray=(Object[]) all_kcb.get(kcb_i);
			String kcb_id=(null==tmpObjArray[0])?"":tmpObjArray[0].toString();
			String kcb_fks_id=(null==tmpObjArray[1])?"":tmpObjArray[1].toString();
			String kcxx_id=(null==tmpObjArray[2])?"":tmpObjArray[2].toString();
			String kcxxmc=(null==tmpObjArray[3])?"":tmpObjArray[3].toString();
			String laoshi_id=(null==tmpObjArray[4])?"":tmpObjArray[4].toString();
			String laoshimc=(null==tmpObjArray[5])?"":tmpObjArray[5].toString();
			String js_id=(null==tmpObjArray[6])?"":tmpObjArray[6].toString();
			String jsmc=(null==tmpObjArray[7])?"":tmpObjArray[7].toString();
			String ks_id=(null==tmpObjArray[8])?"":tmpObjArray[8].toString();
			String ksmc=(null==tmpObjArray[9])?"":tmpObjArray[9].toString();
			String kcblb=(null==tmpObjArray[10])?"":tmpObjArray[10].toString();
			String xingqi=(null==tmpObjArray[11])?"":tmpObjArray[11].toString();
			String kszhou=(null==tmpObjArray[12])?"":tmpObjArray[12].toString();
			String jszhou=(null==tmpObjArray[13])?"":tmpObjArray[13].toString();
			String xingqixh=(null==tmpObjArray[22])?"":tmpObjArray[22].toString();
			String ks_kssj=(null==tmpObjArray[23])?"":tmpObjArray[23].toString();
			String ks_jssj=(null==tmpObjArray[24])?"":tmpObjArray[24].toString();
			String laoshi_gh=(null==tmpObjArray[25])?"":tmpObjArray[25].toString();	
			
			//缺席:计算....................开始.........
				String queqinsql="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where " +
					"k.xh in (select distinct d.xuehao from skxsxx d where d.kcb_id ='"+tmpObjArray[0]+"' minus select distinct e.xuehao from kqxx e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"+tmpObjArray[0]+"') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"+tmpObjArray[6]+"') and e.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"+tmpObjArray[8]+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"+tmpObjArray[8]+"'))";
				System.out.println("查缺席学生学号SQL为："+queqinsql);
				List all_queqin=baseDao.executeSQL(queqinsql);//找到缺席的学生，将其插入到数据库kqjl_xsjl表中
				for(int queqin_i=0;queqin_i<all_queqin.size();queqin_i++){ //循环缺席学生
					Object[] zhengchang_xs=(Object[]) all_queqin.get(queqin_i);
					String zc_xs_id=(null==zhengchang_xs[0])?"":zhengchang_xs[0].toString();
					String zc_xh=(null==zhengchang_xs[1])?"":zhengchang_xs[1].toString();
					String zc_zsxm=(null==zhengchang_xs[2])?"":zhengchang_xs[2].toString();
					String zc_yh_id=(null==zhengchang_xs[3])?"":zhengchang_xs[3].toString();
					String zc_xy_id=(null==zhengchang_xs[4])?"":zhengchang_xs[4].toString();
					String zc_zy_id=(null==zhengchang_xs[5])?"":zhengchang_xs[5].toString();
					String zc_nj_id=(null==zhengchang_xs[6])?"":zhengchang_xs[6].toString();
					String zc_bj_id=(null==zhengchang_xs[7])?"":zhengchang_xs[7].toString();
					String insert_zhengchang_sql="insert into KQXX_XSCQ (kqxx_xscq_id,js_id,jsmc,sksj,xksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,xs_id,xsxm,xsxh,cqqk,xy_id,zy_id,nj_id,bj_id,jlsj)" +
							" values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"','"+zc_xs_id+"','"+zc_zsxm+"','"+zc_xh+"','缺席','"+zc_xy_id+"','"+zc_zy_id+"','"+zc_nj_id+"','"+zc_bj_id+"', to_date('"+kqdateStr+"','yyyy-mm-dd') )";
					System.out.println("插入缺席学生信息SQL:"+insert_zhengchang_sql);
					baseDao.executeInsert_SQL(insert_zhengchang_sql);
					//插入tmp_xuehao表，为了比对本节课既迟到又早退的学号
					baseDao.executeInsert_SQL("insert into tmp_xuehao values('"+zc_xh+"')");
				}
			//缺席:计算....................结束.........

			//正常上课学生:计算.................... 开始.........
				String zhengchangsql="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where " +
						" k.xh in(select e.xuehao from kqxx e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"+kcb_id+"') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"+js_id+"') and e.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"+ks_id+"')) " +
					 "and k.xh in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))";
				System.out.println("查询正常出勤的学生SQL为："+zhengchangsql);
				List all_kaoqin_zhengchang=baseDao.executeSQL(zhengchangsql); //列出出勤情况正常的考勤信息
				for(int zhengchang_i=0;zhengchang_i<all_kaoqin_zhengchang.size();zhengchang_i++){
					Object[] zhengchang_xs=(Object[]) all_kaoqin_zhengchang.get(zhengchang_i);
					String zc_xs_id=(null==zhengchang_xs[0])?"":zhengchang_xs[0].toString();
					String zc_xh=(null==zhengchang_xs[1])?"":zhengchang_xs[1].toString();
					String zc_zsxm=(null==zhengchang_xs[2])?"":zhengchang_xs[2].toString();
					String zc_yh_id=(null==zhengchang_xs[3])?"":zhengchang_xs[3].toString();
					String zc_xy_id=(null==zhengchang_xs[4])?"":zhengchang_xs[4].toString();
					String zc_zy_id=(null==zhengchang_xs[5])?"":zhengchang_xs[5].toString();
					String zc_nj_id=(null==zhengchang_xs[6])?"":zhengchang_xs[6].toString();
					String zc_bj_id=(null==zhengchang_xs[7])?"":zhengchang_xs[7].toString();
					
					String insert_zhengchang_sql="insert into KQXX_XSCQ (kqxx_xscq_id,js_id,jsmc,sksj,xksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,xs_id,xsxm,xsxh,cqqk,xy_id,zy_id,nj_id,bj_id,jlsj)" +
							" values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"','"+zc_xs_id+"','"+zc_zsxm+"','"+zc_xh+"','正常','"+zc_xy_id+"','"+zc_zy_id+"','"+zc_nj_id+"','"+zc_bj_id+"', to_date('"+kqdateStr+"','yyyy-mm-dd') )";
					System.out.println("插入正常学生信息SQL:"+insert_zhengchang_sql);
					baseDao.executeInsert_SQL(insert_zhengchang_sql);
					//插入tmp_xuehao表，为了比对本节课既迟到又早退的学号
					baseDao.executeInsert_SQL("insert into tmp_xuehao values('"+zc_xh+"')");
				}
				//正常上课学生:计算....................结束.........

				
				//早退上课学生:计算....................开始.........
				String zaotuisql="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where " +
						" k.xh in(select e.xuehao from kqxx e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"+kcb_id+"') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"+js_id+"') and e.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"+ks_id+"')) " +
//						"and k.xh in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))" +
					 "and k.xh not in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))";
				System.out.println("查询早退出勤的学生SQL为："+zaotuisql);
				List all_kaoqin_zaotui=baseDao.executeSQL(zaotuisql); //列出出勤情况正常的考勤信息
				for(int zhengchang_i=0;zhengchang_i<all_kaoqin_zaotui.size();zhengchang_i++){
					Object[] zhengchang_xs=(Object[]) all_kaoqin_zaotui.get(zhengchang_i);
					String zc_xs_id=(null==zhengchang_xs[0])?"":zhengchang_xs[0].toString();
					String zc_xh=(null==zhengchang_xs[1])?"":zhengchang_xs[1].toString();
					String zc_zsxm=(null==zhengchang_xs[2])?"":zhengchang_xs[2].toString();
					String zc_yh_id=(null==zhengchang_xs[3])?"":zhengchang_xs[3].toString();
					String zc_xy_id=(null==zhengchang_xs[4])?"":zhengchang_xs[4].toString();
					String zc_zy_id=(null==zhengchang_xs[5])?"":zhengchang_xs[5].toString();
					String zc_nj_id=(null==zhengchang_xs[6])?"":zhengchang_xs[6].toString();
					String zc_bj_id=(null==zhengchang_xs[7])?"":zhengchang_xs[7].toString();
					
					String insert_zhengchang_sql="insert into KQXX_XSCQ (kqxx_xscq_id,js_id,jsmc,sksj,xksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,xs_id,xsxm,xsxh,cqqk,xy_id,zy_id,nj_id,bj_id,jlsj)" +
							" values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"','"+zc_xs_id+"','"+zc_zsxm+"','"+zc_xh+"','早退','"+zc_xy_id+"','"+zc_zy_id+"','"+zc_nj_id+"','"+zc_bj_id+"', to_date('"+kqdateStr+"','yyyy-mm-dd') )";
					System.out.println("插入早退学生信息SQL:"+insert_zhengchang_sql);
					baseDao.executeInsert_SQL(insert_zhengchang_sql);
					//插入tmp_xuehao表，为了比对本节课既迟到又早退的学号
					baseDao.executeInsert_SQL("insert into tmp_xuehao values('"+zc_xh+"')");
				}
				//早退上课学生:计算....................结束.........
				
				//迟到上课学生:计算....................开始.........
				String chidaosql="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where " +
						" k.xh in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))" +
//						"and k.xh in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))" +
						" and k.xh not in(select e.xuehao from kqxx e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"+kcb_id+"') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"+js_id+"') and e.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"+ks_id+"')) ";
				System.out.println("查询迟到出勤的学生SQL为："+chidaosql);
				List all_kaoqin_chidao=baseDao.executeSQL(chidaosql); //列出出勤情况正常的考勤信息
				for(int zhengchang_i=0;zhengchang_i<all_kaoqin_chidao.size();zhengchang_i++){
					Object[] zhengchang_xs=(Object[]) all_kaoqin_chidao.get(zhengchang_i);
					String zc_xs_id=(null==zhengchang_xs[0])?"":zhengchang_xs[0].toString();
					String zc_xh=(null==zhengchang_xs[1])?"":zhengchang_xs[1].toString();
					String zc_zsxm=(null==zhengchang_xs[2])?"":zhengchang_xs[2].toString();
					String zc_yh_id=(null==zhengchang_xs[3])?"":zhengchang_xs[3].toString();
					String zc_xy_id=(null==zhengchang_xs[4])?"":zhengchang_xs[4].toString();
					String zc_zy_id=(null==zhengchang_xs[5])?"":zhengchang_xs[5].toString();
					String zc_nj_id=(null==zhengchang_xs[6])?"":zhengchang_xs[6].toString();
					String zc_bj_id=(null==zhengchang_xs[7])?"":zhengchang_xs[7].toString();
					
					String insert_zhengchang_sql="insert into KQXX_XSCQ (kqxx_xscq_id,js_id,jsmc,sksj,xksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,xs_id,xsxm,xsxh,cqqk,xy_id,zy_id,nj_id,bj_id,jlsj)" +
							" values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"','"+zc_xs_id+"','"+zc_zsxm+"','"+zc_xh+"','迟到','"+zc_xy_id+"','"+zc_zy_id+"','"+zc_nj_id+"','"+zc_bj_id+"', to_date('"+kqdateStr+"','yyyy-mm-dd') )";
					System.out.println("插入迟到学生信息SQL:"+insert_zhengchang_sql);
					baseDao.executeInsert_SQL(insert_zhengchang_sql);
					//插入tmp_xuehao表，为了比对本节课既迟到又早退的学号
					baseDao.executeInsert_SQL("insert into tmp_xuehao values('"+zc_xh+"')");
				}
				//迟到上课学生:计算....................结束.........
				
				//既迟到又早退上课学生:计算....................开始.........
//					String cdztsql="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where " +
//							"k.xh in( select distinct d.xuehao from skxsxx d where d.kcb_id ='"+kcb_id+"' minus select xuehao from tmp_xuehao) ";
				
				String cdztsql="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where " +
				"k.xh in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))" +
				" and k.xh not in(select e.xuehao from kqxx e where e.xuehao in (select d.xuehao from skxsxx d where d.kcb_id ='"+kcb_id+"') and e.shebei_id in (select b.sbxx_id from sbxx b where b.js_id='"+js_id+"') and e.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi t where t.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| t.kssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi t where t.ks_id='"+ks_id+"')) "
				+" and k.xh not in ( select z.xuehao from kqxx z where z.xuehao in (select y.xuehao from skxsxx y where y.kcb_id='"+kcb_id+"') and z.shebei_id in (select w.sbxx_id from sbxx w where w.js_id='"+js_id+"') and z.djsj between (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')-10/(24*60) from keshi h where h.ks_id='"+ks_id+"') and (select to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| h.jssj,'yyyy-mm-dd hh24:mi:ss')+10/(24*60) from keshi h where h.ks_id='"+ks_id+"'))" ;
				System.out.println("查既迟到又早退学生的考勤信息sql："+cdztsql);
					List all_kaoqin_cdzt=baseDao.executeSQL(cdztsql); //列出出勤情况正常的考勤信息
					for(int zhengchang_i=0;zhengchang_i<all_kaoqin_cdzt.size();zhengchang_i++){
						Object[] zhengchang_xs=(Object[]) all_kaoqin_cdzt.get(zhengchang_i);
						String zc_xs_id=(null==zhengchang_xs[0])?"":zhengchang_xs[0].toString();
						String zc_xh=(null==zhengchang_xs[1])?"":zhengchang_xs[1].toString();
						String zc_zsxm=(null==zhengchang_xs[2])?"":zhengchang_xs[2].toString();
						String zc_yh_id=(null==zhengchang_xs[3])?"":zhengchang_xs[3].toString();
						String zc_xy_id=(null==zhengchang_xs[4])?"":zhengchang_xs[4].toString();
						String zc_zy_id=(null==zhengchang_xs[5])?"":zhengchang_xs[5].toString();
						String zc_nj_id=(null==zhengchang_xs[6])?"":zhengchang_xs[6].toString();
						String zc_bj_id=(null==zhengchang_xs[7])?"":zhengchang_xs[7].toString();
						
						String insert_zhengchang_sql="insert into KQXX_XSCQ (kqxx_xscq_id,js_id,jsmc,sksj,xksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,xs_id,xsxm,xsxh,cqqk,xy_id,zy_id,nj_id,bj_id,jlsj)" +
								" values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"','"+zc_xs_id+"','"+zc_zsxm+"','"+zc_xh+"','迟到早退','"+zc_xy_id+"','"+zc_zy_id+"','"+zc_nj_id+"','"+zc_bj_id+"', to_date('"+kqdateStr+"','yyyy-mm-dd') )";
						System.out.println("插入迟到又早退学生信息SQL:"+insert_zhengchang_sql);
						baseDao.executeInsert_SQL(insert_zhengchang_sql);
					}
					//清空tmp_xuehao表
					baseDao.executeInsert_SQL("delete from tmp_xuehao");
					//既迟到又早退上课学生:计算....................结束.........
		}
		logComputeStepToDatabase("计算学生考勤信息","结束","成功","0000","0000");
		} catch(Exception e){
			e.printStackTrace();
			logComputeStepToDatabase("计算学生考勤信息","结束","失败-报异常","0001","0001");
		}
		System.out.println(" 按课时【学生】考勤信息统计，结束####################################################################################");
		doSQLbyDay_XUESHENG_KFK(riqi);
		
	}
	
	/**
	 * 
	 *按课时计算班级出勤情况： 
	 *1、循环出当天上的课程、老师、教室
	 *2、根据课程找【学生出勤情况表】对应的出勤总人数，得到           总人数。
	 *3、根据课程找【学生出勤情况表】对应的出勤总人数，得到           总正常人数。
	 *4、根据课程找【学生出勤情况表】对应的出勤总人数，得到           总迟到人数。
	 *5、根据课程找【学生出勤情况表】对应的出勤总人数，得到           总早退人数。
	 *6、根据课程找【学生出勤情况表】对应的出勤总人数，得到           总缺席人数。
	 *7、根据课程找【学生出勤情况表】对应的出勤总人数，得到           总既迟到又早退人数。
	 *8、计算出勤率： {总人数-缺席)/总人数
	 *9、正常率：正常人数/总人数
	 *10、早退率：早退人数+既迟到又早退人数/总人数
	 *11、迟到率：迟到人数+既迟到又早退人数/总人数
	 * */
	public void doSQLbyDay_JIAOSHI(String riqi){
		logComputeStepToDatabase("计算教室考勤信息","开始","成功","0000","0000");
		try{
		System.out.println(" 按课时【教室】考勤信息统计，开始####################################################################################");
		
		Date kqdate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=riqi&&!"".equals(riqi)){
				kqdate=df.parse(riqi);
		}else{
			kqdate=new Date();
		}
		String kqdateStr=df.format(kqdate);
		//删除当天的班级考勤信息
		String sqldel="delete from kqxx_bjxx t where t.sksj >=to_date('"+kqdateStr+"','yyyy-mm-dd') and t.sksj<to_date('"+kqdateStr+"','yyyy-mm-dd')+1";
		System.out.println("删除当天的班级考勤信息SQL为："+sqldel);
		baseDao.executeDoSQL(sqldel);
			List all_kcb=baseDao.executeSQL("select * from kechengb a where 1=1 and kcblb in ('专业选修课','专业必修课','公共选修课') and a.xingqixh=(SELECT to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'D') FROM DUAL)");//列出当天所有课程
			for(int kcb_i=0;kcb_i<all_kcb.size();kcb_i++){ //循环课程列表，计算考勤信息
				Object[] tmpObjArray=(Object[]) all_kcb.get(kcb_i);
				String kcb_id=(null==tmpObjArray[0])?"":tmpObjArray[0].toString();
				String kcb_fks_id=(null==tmpObjArray[1])?"":tmpObjArray[1].toString();
				String kcxx_id=(null==tmpObjArray[2])?"":tmpObjArray[2].toString();
				String kcxxmc=(null==tmpObjArray[3])?"":tmpObjArray[3].toString();
				String laoshi_id=(null==tmpObjArray[4])?"":tmpObjArray[4].toString();
				String laoshimc=(null==tmpObjArray[5])?"":tmpObjArray[5].toString();	
				String js_id=(null==tmpObjArray[6])?"":tmpObjArray[6].toString();
				String jsmc=(null==tmpObjArray[7])?"":tmpObjArray[7].toString();
				String ks_id=(null==tmpObjArray[8])?"":tmpObjArray[8].toString();
				String ksmc=(null==tmpObjArray[9])?"":tmpObjArray[9].toString();
				String kcblb=(null==tmpObjArray[10])?"":tmpObjArray[10].toString();
				String xingqi=(null==tmpObjArray[11])?"":tmpObjArray[11].toString();
				String kszhou=(null==tmpObjArray[12])?"":tmpObjArray[12].toString();
				String jszhou=(null==tmpObjArray[13])?"":tmpObjArray[13].toString();
				String xingqixh=(null==tmpObjArray[22])?"":tmpObjArray[22].toString();
				String ks_kssj=(null==tmpObjArray[23])?"":tmpObjArray[23].toString();
				String ks_jssj=(null==tmpObjArray[24])?"":tmpObjArray[24].toString();
				String laoshi_gh=(null==tmpObjArray[25])?"":tmpObjArray[25].toString();	
				//统计相关信息SQL语句：
				String zong_sql="select to_char(count(distinct xs_id)) from skxsxx t where t.kcb_id='"+kcb_id+"'";
				System.out.println("应出席总人数："+zong_sql);
				String zong_rs=baseDao.executeSQL(zong_sql).get(0).toString();//总人数
				System.out.println("总人数是："+zong_rs);
				String que_sql="select to_char(count(distinct xs_id))  from kqxx_xscq t where t.keshi_id= '"+ks_id+"' and t.js_id='"+js_id+"' and t.kcb_fks_id='"+kcb_fks_id+"' and trim(t.cqqk)='缺席'  and t.sksj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd'),'yyyy-mm-dd hh24:mi:ss') and t.sksj<to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd')||'23:59:59','yyyy-mm-dd hh24:mi:ss')";
				System.out.println("缺席总人数："+que_sql);
				String que_rs=(String) baseDao.executeSQL(que_sql).get(0).toString();//缺席人数
				System.out.println("缺席人数是："+que_rs);
				String zheng_sql="select to_char(count(distinct xs_id))  from kqxx_xscq t where t.keshi_id= '"+ks_id+"' and t.js_id='"+js_id+"' and t.kcb_fks_id='"+kcb_fks_id+"' and trim(t.cqqk)='正常'  and t.sksj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd'),'yyyy-mm-dd hh24:mi:ss') and t.sksj<to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd')||'23:59:59','yyyy-mm-dd hh24:mi:ss')";
				System.out.println("正常出席总人数："+zheng_sql);
				String zheng_rs=(String) baseDao.executeSQL(zheng_sql).get(0).toString();//正常人数
				System.out.println("正常人数是："+zheng_rs);
				String chi_sql="select to_char(count(distinct xs_id))  from kqxx_xscq t where t.keshi_id= '"+ks_id+"' and t.js_id='"+js_id+"' and t.kcb_fks_id='"+kcb_fks_id+"' and trim(t.cqqk)='迟到'  and t.sksj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd'),'yyyy-mm-dd hh24:mi:ss') and t.sksj<to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd')||'23:59:59','yyyy-mm-dd hh24:mi:ss')";
				System.out.println("迟到总人数："+chi_sql);
				String chi_rs=(String) baseDao.executeSQL(chi_sql).get(0).toString();//迟到人数
				System.out.println("迟到人数是："+chi_rs);
				String zao_sql="select to_char(count(distinct xs_id))  from kqxx_xscq t where t.keshi_id= '"+ks_id+"' and t.js_id='"+js_id+"' and t.kcb_fks_id='"+kcb_fks_id+"' and trim(t.cqqk)='早退'  and t.sksj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd'),'yyyy-mm-dd hh24:mi:ss') and t.sksj<to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd')||'23:59:59','yyyy-mm-dd hh24:mi:ss')";
				System.out.println("早退总人数："+zao_sql);
				String zao_rs=(String) baseDao.executeSQL(zao_sql).get(0).toString();//早退人数
				System.out.println("早退人数是："+zao_rs);
				String chizao_sql="select to_char(count(distinct xs_id))  from kqxx_xscq t where t.keshi_id= '"+ks_id+"' and t.js_id='"+js_id+"' and t.kcb_fks_id='"+kcb_fks_id+"' and trim(t.cqqk)='迟到早退'  and t.sksj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd'),'yyyy-mm-dd hh24:mi:ss') and t.sksj<to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd')||'23:59:59','yyyy-mm-dd hh24:mi:ss')";
				System.out.println("既迟到又早退总人数："+chizao_sql);
				String chizao_rs=(String) baseDao.executeSQL(chizao_sql).get(0).toString();//迟到又早退人数
				System.out.println("既迟到又早退人数是："+chizao_rs);
				
				Integer chi_rs11=(Integer.parseInt(chi_rs)+Integer.parseInt(chizao_rs));
				chi_rs=chi_rs11.toString();

				Integer zao_rs11=(Integer.parseInt(zao_rs)+Integer.parseInt(chizao_rs));
				zao_rs=zao_rs11.toString();
				
				String insert_sql="insert into kqxx_bjxx(kqxx_bjxx_id,js_id,jsmc,sksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,yskrs,sjskrs,zccxrs,qxrs,cdrs,ztrs,cdztrs) " +
						"values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"
						+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"',"+zong_rs+",null,"+zheng_rs+","+que_rs+","+chi_rs+","+zao_rs+",'"+chizao_rs+"')";
				System.out.println("按照班级统计出勤情况插入SQL:"+insert_sql);
				baseDao.executeInsert_SQL(insert_sql);
			}
			logComputeStepToDatabase("计算教室考勤信息","结束","成功","0000","0000");
		}catch(Exception e){
			e.printStackTrace();
			logComputeStepToDatabase("计算教室考勤信息","结束","失败","0001","0001");
		}
		System.out.println(" 按课时【教室】考勤信息统计，结束####################################################################################");
	}
	
	/*从考勤信息实时表中更新数据到总表中，以备计算时使用*/
	public void updateKQXXbyDay(){
		logComputeStepToDatabase("更新KQXX_DAY数据到KQXX表","开始","成功","0000","0000");
		try {
			//将记录从kqxx_day表中更新到考勤信息表kqxx中
			baseDao.executeDoSQL("MERGE INTO kqxx p USING kqxx_day np ON (p.kqxx_id=np.kqxx_id) WHEN NOT MATCHED THEN INSERT VALUES (np.kqxx_id,np.shebei_id,np.sbmc,np.djsj,np.xuehao) where np.djsj is not null");
			logComputeStepToDatabase("更新KQXX_DAY数据到KQXX表","结束","成功","0000","0000");
			System.out.println("更新完毕...");
		} catch (Exception e) {
			e.printStackTrace();
			logComputeStepToDatabase("更新KQXX_DAY数据到KQXX表","结束","失败-报异常","0001","0001");
		}
	}
	
	/*删除七天前的kqxx_day表记录*/
	public void deleteKQXX_DAYto7days(){
		logComputeStepToDatabase("清除KQXX_DAY表7天前数据","开始","成功","0000","0000");
		try {
			baseDao.executeDoSQL("delete from kqxx_day t where t.djsj<=to_date(to_char((sysdate-7),'yyyy-mm-dd'),'yyyy-mm-dd')");
			logComputeStepToDatabase("清除KQXX_DAY表7天前数据","结束","成功","0000","0000");
		} catch (Exception e) {
			e.printStackTrace();
			logComputeStepToDatabase("清除KQXX_DAY表7天前数据","结束","失败-报异常","0001","0001");
		}
	}
	
	
	/**
	 * 开放性课程：每天跑SQL，学生信息
	 * 算法分析：
	 * 1、查询课程表中kcblb='开放性课程'数据.
	 * 2、找到相应的教室-->指纹设备-->上课时间
	 * 3、kqxx表中，找到当时课时的先打卡的前N位学生的学生学号
	 * 4、计算这n位学生的考勤信息数据。计算方式同学生考勤。
	 * */
	@Override
	public void doSQLbyDay_XUESHENG_KFK(String riqi) {
		logComputeStepToDatabase("计算学生考勤信息::开放性课程","开始","成功","0000","0000");
		try{
		Date kqdate;
		String kqdateStr;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=riqi&&!"".equals(riqi)){
			kqdate=df.parse(riqi);	
		}else{
			kqdate=new Date();
		}
				
		kqdateStr=df.format(kqdate);//设置考勤时间的值
		//删除当天的考勤信息
		System.out.println(" 开放性课程:按课时【学生】考勤信息统计，开始####################################################################################");
		List all_kcb=baseDao.executeSQL("select * from kechengb a where 1=1 and kcblb ='开放性课程' and a.xingqixh=(SELECT to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'D') FROM DUAL)");//列出当天所有课程
		System.out.println("当前查询课程表sql是：select * from kechengb a where 1=1 and kcblb ='开放性课程' and a.xingqixh=(SELECT to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'D') FROM DUAL)");
		for(int kcb_i=0;kcb_i<all_kcb.size();kcb_i++){ //循环课程列表，计算考勤信息
			Object[] tmpObjArray=(Object[]) all_kcb.get(kcb_i);
			String kcb_id=(null==tmpObjArray[0])?"":tmpObjArray[0].toString();
			String kcb_fks_id=(null==tmpObjArray[1])?"":tmpObjArray[1].toString();
			String kcxx_id=(null==tmpObjArray[2])?"":tmpObjArray[2].toString();
			String kcxxmc=(null==tmpObjArray[3])?"":tmpObjArray[3].toString();
			String laoshi_id=(null==tmpObjArray[4])?"":tmpObjArray[4].toString();
			String laoshimc=(null==tmpObjArray[5])?"":tmpObjArray[5].toString();
			String js_id=(null==tmpObjArray[6])?"":tmpObjArray[6].toString();
			String jsmc=(null==tmpObjArray[7])?"":tmpObjArray[7].toString();
			String ks_id=(null==tmpObjArray[8])?"":tmpObjArray[8].toString();
			String ksmc=(null==tmpObjArray[9])?"":tmpObjArray[9].toString();
			String kcblb=(null==tmpObjArray[10])?"":tmpObjArray[10].toString();
			String xingqi=(null==tmpObjArray[11])?"":tmpObjArray[11].toString();
			String kszhou=(null==tmpObjArray[12])?"":tmpObjArray[12].toString();
			String jszhou=(null==tmpObjArray[13])?"":tmpObjArray[13].toString();
			String xingqixh=(null==tmpObjArray[22])?"":tmpObjArray[22].toString();
			String ks_kssj=(null==tmpObjArray[23])?"":tmpObjArray[23].toString();
			String ks_jssj=(null==tmpObjArray[24])?"":tmpObjArray[24].toString();
			String laoshi_gh=(null==tmpObjArray[25])?"":tmpObjArray[25].toString();	
			String maxrs=(null==tmpObjArray[30])?"0":tmpObjArray[30].toString();
			
			int maxrs_int=Integer.parseInt(maxrs);//将上课人数转换为整数类型
			int flag=0;
			//查询出过来上课的学生学号:
				String xueshengsql="select distinct xuehao,to_char(min(djsj),'yyyy-mm-dd hh24:mi:ss') from kqxx"+
							" where xuehao not like 'L%' and djsj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss')-10/24/60"+
							"   and djsj<=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss')+10/24/60"+
							" and shebei_id in(select sbxx_id from sbxx where js_id='"+js_id+"')"+
							" group by xuehao "+
							" order by to_char(min(djsj),'yyyy-mm-dd hh24:mi:ss') asc";
				System.out.println("开放性课程:学生的学号SQL语句是："+xueshengsql);
				List all_kaoqin_xuesheng=baseDao.executeSQL(xueshengsql); 
				for(int zhengchang_i=0;zhengchang_i<all_kaoqin_xuesheng.size();zhengchang_i++){
					Object[] xsarr=(Object[]) all_kaoqin_xuesheng.get(zhengchang_i);
					String xuehao=(null==xsarr[0])?"":xsarr[0].toString();//获取上课学生的学号
					String skdjsj=(null==xsarr[1])?"":xsarr[1].toString();//获取上课学生的上课打卡时间
					if(flag<maxrs_int){ //如果人数小于最大上课人数,则计算出勤情况，否则跳出循环.
						System.out.println(flag+"：flag小于最大人数："+maxrs_int);
						//找下课打卡时间：
						String xiakesql="select t.xuehao,to_char(djsj,'yyyy-mm-dd hh24:mi:ss') from kqxx t where t.xuehao ='"+xuehao+"'" +
								" and djsj>=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss')-10/24/60" +
								" and djsj<=to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss')+10/24/60" +
								" and shebei_id in(select sbxx_id from sbxx where js_id='"+js_id+"') order by djsj desc";
						List all_kaoqin_xuesheng_xk=baseDao.executeSQL(xiakesql);
						for(int xki=0;xki<all_kaoqin_xuesheng_xk.size();xki++){
							//学号、上课时间、下课时间都有了，判断学生是正常、迟到、早退、既迟到又早退
							Object[] xsxkarr=(Object[]) all_kaoqin_xuesheng_xk.get(xki);
							String xkdjsj=(null==xsxkarr[1])?"":xsxkarr[1].toString();//获取上课学生的下课打卡时间
							String xs_cqqk="";
							//如果学生上课打卡时间>上课时间：则迟到
							//如果学生下课打卡时间<下课时间：则早退
							//如果两者皆满足：则既迟到又早退
							//如果上述都不满足，则正常
							boolean skbool=false; //上课是否迟到
							boolean xkbool=false; //下课是否早退
							
							if(isDateBefore(kqdateStr+" "+ks_kssj+":00", skdjsj,10*60*1000,0)){//如果上课开始时间在打卡时间之前，则迟到
								skbool=true;
							}
							if(isDateBefore(xkdjsj,kqdateStr+" "+ks_jssj+":00",10*60*1000,0)){//如果下课打卡时间在下课之前，则早退
								xkbool=true;
							}
							System.out.println();
							System.out.println("拼凑出来的上课时间是："+kqdateStr+" "+ks_kssj+":00"+"  上课打卡时间是："+skdjsj+"    真假为："+skbool);
							System.out.println("拼凑出来的下课时间是："+kqdateStr+" "+ks_jssj+":00"+"  下课打卡时间是："+xkdjsj+"    真假为："+skbool);
							
							if(skbool){//如果迟到
								if(xkbool){//如果迟到的情况下早退
									xs_cqqk="既迟到又早退";
									System.out.println("111111111111");
								}else{    //只迟到没早退
									xs_cqqk="迟到";
									System.out.println("111111111112");
								}
							}else if(xkbool){//否则，如果早退
								if(skbool){//如果早退、又迟到
									xs_cqqk="既迟到又早退";
									System.out.println("111111111113");
								}else{
									xs_cqqk="早退";
									System.out.println("111111111114");
								}
							}else{
								xs_cqqk="正常";
								System.out.println("111111111115");
							}
							String xssqlins="select k.xs_id,k.xh,k.zsxm,k.yh_id,k.xy_id,k.zy_id,k.nj_id,k.bj_id from xsxx k where k.xh='"+xuehao+"'";
							System.out.println("开放性课程：查询出学生的信息SQL："+xssqlins);
							List all_kaoqin_chidao=baseDao.executeSQL(xssqlins); //列出出勤情况正常的考勤信息
							for(int ini=0;ini<all_kaoqin_chidao.size();ini++){
								Object[] zhengchang_xs=(Object[]) all_kaoqin_chidao.get(ini);
								String zc_xs_id=(null==zhengchang_xs[0])?"":zhengchang_xs[0].toString();
								String zc_xh=(null==zhengchang_xs[1])?"":zhengchang_xs[1].toString();
								String zc_zsxm=(null==zhengchang_xs[2])?"":zhengchang_xs[2].toString();
								String zc_yh_id=(null==zhengchang_xs[3])?"":zhengchang_xs[3].toString();
								String zc_xy_id=(null==zhengchang_xs[4])?"":zhengchang_xs[4].toString();
								String zc_zy_id=(null==zhengchang_xs[5])?"":zhengchang_xs[5].toString();
								String zc_nj_id=(null==zhengchang_xs[6])?"":zhengchang_xs[6].toString();
								String zc_bj_id=(null==zhengchang_xs[7])?"":zhengchang_xs[7].toString();
								String insert_sql="insert into KQXX_XSCQ (kqxx_xscq_id,js_id,jsmc,sksj,xksj,xingqi,zhou,keshi_id,ksmc,ls_id,lsxm,lsgh,kcb_id,kcb_fks_id,kcmc,kclb,xs_id,xsxm,xsxh,cqqk,xy_id,zy_id,nj_id,bj_id,jlsj)" +
										" values('"+UUIDTools.getUUID()+"','"+js_id+"','"+jsmc+"',to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_kssj+"','yyyy-mm-dd hh24:mi:ss'),to_date(to_char( to_date('"+kqdateStr+"','yyyy-mm-dd') ,'yyyy-mm-dd ')|| '"+ks_jssj+"','yyyy-mm-dd hh24:mi:ss'),'"+xingqi+"',null,'"+ks_id+"','"+ksmc+"','"+laoshi_id+"','"+laoshimc+"','"+laoshi_gh+"','"+kcb_id+"','"+kcb_fks_id+"','"+kcxxmc+"','"+kcblb+"','"+zc_xs_id+"','"+zc_zsxm+"','"+zc_xh+"','"+xs_cqqk+"','"+zc_xy_id+"','"+zc_zy_id+"','"+zc_nj_id+"','"+zc_bj_id+"', to_date('"+kqdateStr+"','yyyy-mm-dd') )";
								System.out.println("开放性课程插入SQL为:"+insert_sql);
								baseDao.executeInsert_SQL(insert_sql);
								break;
							}
						break;
						}
						flag++;
					}
					else{
						System.out.println(flag+"：flag大于最大人数："+maxrs_int);
						break;
					}
				}
				//正常上课学生:计算....................结束.........
		}
		logComputeStepToDatabase("计算学生考勤信息::开放性课程","结束","成功","0000","0000");
		} catch(Exception e){
			e.printStackTrace();
			logComputeStepToDatabase("计算学生考勤信息:开放性课程","结束","失败-报异常","0001","0001");
		}
		System.out.println(" 按课时【学生】考勤信息统计，结束####################################################################################");
	}
	
	public void doSQLbyDay_JIAOSHI_KFK(String riqi){
		//计算班级的
	}
	 public boolean isDateBefore(String date1,String date2,int in1,int in2){
		  try{
		   
		   DateFormat df = DateFormat.getDateTimeInstance();
		   Date d1=df.parse(date1);
		   Date d2=df.parse(date2);
		   String tmp1=df.format(d1.getTime()+in1);
		   String tmp2=df.format(d2.getTime()+in2);
		   System.out.println();
		   System.out.println(in1+"-->"+in2);
		   System.out.println("tmp1的值是："+tmp1);
		   System.out.println("tmp2的值是："+tmp2);
		   System.out.println();
		   return df.parse(tmp1).before(df.parse(tmp2));
		  }catch(ParseException e){
		   System.out.print("[SYS] " + e.getMessage());
		   return false;
		  }
	 }
	 
	 /**
	  * logComputeStepToDatabase 记录执行过程日志
	  * <p>logComputeStepToDatabase 该方法用于记录操作结果步骤等信息到数据库中<br>  
	  *  @param caozuomc 操作名称，所要记录的是哪个计算过程。
	  *  @param buzhou 过程的操作的步骤。
	  *  @param sfcg 成功/失败。
	  *  @param jieguobm 结果编码:"0000"代表成功、"0001"代表失败。
	  *  @param jieguobm 结果编码:"0000"代表成功、"0001"代表失败。
	  *  @return 空
	  * */
	 public void logComputeStepToDatabase(String caozuomc,String buzhou,String sfcg,String jieguobm,String jieguoms){
			baseDao.executeDoSQL("insert into kqxx_czrz(kqxxczrz_id,czmc,BUZHOU,czsj,sfcg,jgbm,jgms)" +
					" values('"+UUIDTools.getUUID()+"','"+caozuomc+"','"+buzhou+"',sysdate,'"+sfcg+"','"+jieguobm+"','"+jieguoms+"')");
	 }
	 
	 public static void main(String[] args) {
		 boolean skbool=false;
		 boolean xkbool=false;
		 String xs_cqqk="";
		 skbool=new TaskServiceImpl().isDateBefore("2014-11-22 07:00:00", "2014-11-22 07:09:24",0,10*60*1000);
			if(skbool){//如果迟到
				if(xkbool){//如果迟到的情况下早退
					xs_cqqk="既迟到又早退";
				}else{    //只迟到没早退
					xs_cqqk="迟到";
				}
			}else if(xkbool){//否则，如果早退
				if(skbool){//如果早退、又迟到
					xs_cqqk="既迟到又早退";
				}else{
					xs_cqqk="早退";
				}
			}else{
				xs_cqqk="正常";
			}
		 
		 System.out.println(xs_cqqk);
	}
}