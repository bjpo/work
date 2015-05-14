package com.hrbsys.login.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.JIAOGONG;
import com.hrbsys.bean.JIAOGONGLB;
import com.hrbsys.bean.JUESE;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.business.KechengbiaoTools;
import com.hrbsys.jiaogong.service.JIAOGONGService;
import com.hrbsys.jiaogonglb.service.JIAOGONGLBService;
import com.hrbsys.login.service.LoginService;
import com.hrbsys.realtime.service.RealTimeService;
import com.hrbsys.users.service.UserService;
import com.hrbsys.xuesheng.service.XsxxService;
import com.hrbsys.login.action.*;
import com.hrbsys.message.service.MessageService;

public class LoginAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	private LoginService lservice;
	private RealTimeService realTimeService;
	private JIAOGONGService jiaogongService;
	private JIAOGONGLBService jiaogonglbService;
	private UserService userService;
	private XsxxService xsxxSerivce;
	private MessageService messageService;

	public JIAOGONGLBService getJiaogonglbService() {
		return jiaogonglbService;
	}

	public void setJiaogonglbService(JIAOGONGLBService jiaogonglbService) {
		this.jiaogonglbService = jiaogonglbService;
	}

	public JIAOGONGService getJiaogongService() {
		return jiaogongService;
	}

	public void setJiaogongService(JIAOGONGService jiaogongService) {
		this.jiaogongService = jiaogongService;
	}

	public RealTimeService getRealTimeService() {
		return realTimeService;
	}

	public void setRealTimeService(RealTimeService realTimeService) {
		this.realTimeService = realTimeService;
	}

	// 用户登录相关
	private String uname;// 用户名
	private String upassword;// 密码
	private String imageCode;// 验证码
	private String errorMsg;// 错误信息
	private boolean flag;// 标识

	/** 用户登录方法 */
	public String login() throws Exception {
		System.out.println("进入到登录action...............................");
		// 获取验证码
		String code = (String) session.get("imageCode");
		// 判断验证码是否正确
		if (imageCode != null && imageCode.equalsIgnoreCase(code)) {
			if ((null == uname && "".equals(uname) || (null == upassword && ""
					.equals(upassword)))) {
				flag = false;
				errorMsg = "用户名或密码不能为空！";
				return "fail";
			}
			// 输入的验证码和图片中一致，代码向下执行，验证用户名密码
			if ((null != uname) && !"".equals(uname)) {
				List<MOKUAI> allmokuai = new ArrayList<MOKUAI>();
				// 数据库交互 查看用户的ID
				YONGHU y = lservice.getYONGHUbyYHMCandYHMM(uname, upassword);
				if (null == y || "".equals(y.getYhid())) {
					// 登录时用户名不存在时，进行提示
					errorMsg = "用户名或密码错误!";
					return "fail";
				}
				// 查找用户对应的角色
				List<JUESE> alljueseforyh = lservice.getJUESEbyYONGHU(y);
				// 查找用户所属用户组
				List<YONGHUZU> allyhz = lservice.getYONGHUZUforYONGHU(y);
				// 查找用户组对应的角色
				List<JUESE> alljueseforyhz = lservice
						.getJUESEbyYONGHUZU(allyhz);
				// 查找用户组对应的模块,session中存入相应的模块名称与URL列表
				List<MOKUAI> allmokuai1 = lservice
						.getMOKUAIbyJUESE(alljueseforyh);
				List<MOKUAI> allmokuai2 = lservice
						.getMOKUAIbyJUESE(alljueseforyhz);
				allmokuai.addAll(allmokuai1);
				allmokuai.addAll(allmokuai2);
				// 排序
				Collections.sort(allmokuai, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						MOKUAI m1 = (MOKUAI) o1;
						MOKUAI m2 = (MOKUAI) o2;
						if (null == m1.getPaixu() || null == m2.getPaixu()) {
							return 0;
						}
						return new Double(m1.getPaixu()).compareTo(new Double(
								m2.getPaixu()));
					}
				});
				super.session.put("allmokuai", allmokuai);// 将模块所有信息存入session中
				super.session.put("yonghu", y);// 将用户放入session
				super.session.put("allyonghuzu", allyhz);// 将用户所在用户组放入session

				/**************************************** 个人中心相关业务开始 ********************************************/
				// 存放用户组名称,角色名称
				String yhzMc = "", jsMc = "";
				// 遍历当前用户所在的用户组集合
				for (int i = 0; i < allyhz.size(); i++) {
					if (allyhz.size() <= 1) {
						yhzMc = allyhz.get(i).getYhzmc();
					} else {
						yhzMc = "无";
					}
				}
				// 遍历当前用户所在的角色集合
				for (int i = 0; i < alljueseforyhz.size(); i++) {
					// 判断用户组角色集合中是否为空
					if (alljueseforyhz.size() <= 1) {
						// 不为空时，进行存放
						jsMc = alljueseforyhz.get(i).getJsmc();
					} else if (alljueseforyhz.size() > 1) {
						jsMc += " " + alljueseforyhz.get(i).getJsmc();
					} else {
						jsMc = "无";
					}
				}
				// 用户组名称
				super.session.put("yhzMc", yhzMc.trim());
				// 用户所属于的角色
				super.session.put("yhJs", jsMc.trim());
				/**************************************** 个人中心相关业务结束 ********************************************/
				// 增加课程表相关权限：可为哪些人排课
				JIAOGONG jg = jiaogongService.findJIAOGONGbyYONGHUID(y
						.getYhid());
				if (null != jg && !"".equals(jg)) {
					JIAOGONGLB jglb = jiaogonglbService.findJIAOGONGLB(jg
							.getJGLB_ID());
					if (null != jglb && !"".equals(jglb)) { // 如果有教工类别
						super.session
								.put("kechengbQX", KechengbiaoTools
										.getJGLBtoPAIKEQX(jg.getTMP1()));// 查看是否能够排课
						super.session.put("JIAOGONGID", jg.getJG_ID());// 定义教工ID
					}
				} else {
					super.session.put("kechengbQX", KechengbiaoTools.BUNENGPK);// 定义为不能排课
					super.session.put("JIAOGONGID",
							KechengbiaoTools.NOJIAOGONGID);// 定义为没有教工ID
				}
				/*********************************** 记录用户登录的时间 开始 *****************************************/
				userService.updateYONGHULoginTime(y);
				/*********************************** 记录用户登录的时间 结束 *****************************************/
				/** 存放学生或老师的个人具体信息 获取用户的ID,到学生表与老师用中去查询 开始 ****/
				JIAOGONG jiaogong = jiaogongService.findJIAOGONG(y.getYhid());
				Xsxx xs = xsxxSerivce.findStudentById(y.getYhid());
				if (jiaogong != null) {
					session.put("jg", jiaogong);
				}
				if (xs != null) {
					session.put("xs", xs);
				}
				/** 存放学生或老师的个人具体信息 获取用户的ID,到学生表与老师用中去查询 结束 ****/
				/**获取当前用户未读的消息条数 开始*/
				session.put("message", messageService.getMessageCount(y.getYhid()));
				/**获取当前用户未读的消息条数 结束*/
				return "success";
			}
		} else {
			flag = false;
			// 不一致，则返回fail
			errorMsg = "验证码输入有误！";
			return "fail";
		}
		return "fail";
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		super.session.remove("allmokuai");
		super.session.remove("yonghu");
		super.session.remove("allyonghuzu");
		session.clear();
		flag=true;
		return SUCCESS;
	}

	public LoginService getLservice() {
		return lservice;
	}

	public void setLservice(LoginService lservice) {
		this.lservice = lservice;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public XsxxService getXsxxSerivce() {
		return xsxxSerivce;
	}

	public void setXsxxSerivce(XsxxService xsxxSerivce) {
		this.xsxxSerivce = xsxxSerivce;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
