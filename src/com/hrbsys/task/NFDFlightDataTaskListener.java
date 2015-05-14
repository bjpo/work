package com.hrbsys.task;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NFDFlightDataTaskListener implements ServletContextListener {
	

	public void contextInitialized(ServletContextEvent event) {
		 ServletContext servletContext = event.getServletContext(); 
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);  
        TimerManager tm= (TimerManager)wac.getBean("timerManager");  
        tm.doExecute();
	 }

	 public void contextDestroyed(ServletContextEvent event) {
	 }

	}