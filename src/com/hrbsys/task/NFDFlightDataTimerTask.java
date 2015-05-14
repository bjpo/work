package com.hrbsys.task;

import java.util.TimerTask;

import com.hrbsys.task.service.TaskService;

public class NFDFlightDataTimerTask extends TimerTask {
	private TaskService ts;
	public TaskService getTs() {
		return ts;
	}
	public void setTs(TaskService ts) {
		this.ts = ts;
	}
	@Override
	public void run() {
		try {
			//执行每天的从按天表中更新到总表。
			ts.updateKQXXbyDay();
			// 在这里写你要执行的内容
			ts.doSQLbyDay();
		} catch (Exception e) {
		}
	}
}
