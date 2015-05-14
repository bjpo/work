package com.hrbsys.tongzhi.service.impl;

//import java.util.Properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.base.dao.impl.BaseDaoImpl;
import com.hrbsys.bean.YOUJIANLB;
import com.hrbsys.tongzhi.service.TongzhiService;

public class TongzhiServiceImpl implements TongzhiService {

	private BaseDao baseDao = new BaseDaoImpl();
	String host = "smtp.sina.com";
	String user = "htgk2014@sina.com";
	String password = "w123456";

	public void send(String from, String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host); // 指定SMTP服务器
        props.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
        try {
            Session mailSession = Session.getDefaultInstance(props);
            Message message = new MimeMessage(mailSession);
            InternetAddress tfrom =new InternetAddress(from);
            message.setFrom(tfrom); // 发件人
            InternetAddress tto =new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, tto); // 收件人
            message.setSubject(subject); // 邮件主题
            message.setText(content); // 邮件内容
            message.saveChanges();
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
	
	@Override
	public void doSQLbyDay() {
		List<YOUJIANLB> list = baseDao.findAll("from YOUJIANLB", YOUJIANLB.class);
		int iSize1 = list.size();
		if(0 == iSize1){
			return;
		}
		List list2 = baseDao.executeSQL("select * from kqxx_czrz t where t.sfcg!='成功'and t.czsj<=to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and t.czsj>to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd')");
		int iSize2 = list2.size();
		if(0 == iSize2){
			List list3 = baseDao.executeSQL("select * from kqxx_czrz t where t.czsj > (sysdate-1)");
			int iSize3 = list3.size();
			if(0 == iSize3){
				for(int i = 0 ; i < iSize1 ; i++){
					YOUJIANLB yj = list.get(i);
					if((null != yj) && (!"".equals(yj.getYXMC()))){
						send("htgk2014@sina.com", yj.getYXMC(), "警告", "考勤信息操作日志表没有昨天记录");
					}
				}
			}
			return;
		}
		for(int i = 0 ; i < iSize1 ; i++){
			YOUJIANLB yj = list.get(i);
			if((null != yj) && (!"".equals(yj.getYXMC()))){
				String content = "";
				for(int j = 0 ; j < iSize2 ; j++)
				{
					Object[] ObjArray=(Object[]) list2.get(j);
					String id = (null==ObjArray[0])?"":ObjArray[0].toString();
					String czmc = (null==ObjArray[1])?"":ObjArray[1].toString();
					String czsj = (null==ObjArray[2])?"":ObjArray[2].toString();
					String buzhou = (null==ObjArray[3])?"":ObjArray[3].toString();
					String sfcg = (null==ObjArray[4])?"":ObjArray[4].toString();
					content += id + " " + czmc + " " + czsj + " " + buzhou + " " + sfcg + "\n";
				}
				send("htgk2014@sina.com", yj.getYXMC(), "警告", content);
			}
		}
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<YOUJIANLB> findAllYOUJIANLB() {
		return baseDao.findAll("from YOUJIANLB", YOUJIANLB.class);
	}
}
