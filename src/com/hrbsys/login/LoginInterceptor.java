package com.hrbsys.login;

import java.util.List;
import java.util.Map;

import com.hrbsys.bean.MOKUAI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		String requestAction = invocation.getInvocationContext().getName();
		Map<String, Object> session = actionContext.getSession();
		@SuppressWarnings("unchecked")
		List<MOKUAI> list2 = (List<MOKUAI>) session.get("allmokuai");
		List<MOKUAI> list = list2;
		List<MOKUAI> allmokuai = list;
		if(null!=allmokuai){
			for (MOKUAI mk : allmokuai) {
				if (requestAction.equals(mk.getMkurl())) {
					return invocation.invoke();
				}
			}			
		}
		ActionContext.getContext().put("message", "你没有权限执行该操作");
		return "success";
	}

}
