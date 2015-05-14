package com.hrbsys.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hrbsys.task.TimerManager;

public class DingshiTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/ApplicationContext.xml");
			TimerManager tm=(TimerManager) context.getBean("timerManager");
			
		}
}
