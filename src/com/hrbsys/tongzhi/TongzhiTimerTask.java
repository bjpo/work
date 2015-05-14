package com.hrbsys.tongzhi;

import java.util.TimerTask;

import com.hrbsys.tongzhi.service.TongzhiService;

public class TongzhiTimerTask extends TimerTask {
	TongzhiService ts;
	
	@Override
	public void run() {
		ts.doSQLbyDay();
	}

	public TongzhiService getTs() {
		return ts;
	}

	public void setTs(TongzhiService ts) {
		this.ts = ts;
	}

}
