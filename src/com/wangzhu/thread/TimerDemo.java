package com.wangzhu.thread;

import java.util.Timer;
import java.util.TimerTask;

import com.wangzhu.dateutil.DateUtil;

/**
 * 定时器
 * 
 * @author wangzhu
 * @date 2015-3-21下午3:03:38
 * 
 */
public class TimerDemo {
    public static void main(String[] args) {
	Timer timer1 = new Timer();
	timer1.schedule(new TimerTask() {

	    @Override
	    public void run() {
		System.out.println(Thread.currentThread().getName()
			+ "====timer====" + DateUtil.getYMDHMST());
	    }
	}, 2000, 3000);// 在2000毫秒之后开始调用，且之后每隔3000毫秒调用一次
	int c = 0;
	while (true) {
	    if (c == 16) {
		timer1.cancel();// 取消调用
		break;
	    }
	    System.out.println(DateUtil.getYMDHMST());
	    try {
		c++;
		Thread.sleep(1000);// 休息1000毫秒，即1秒
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}

	// 在1000毫秒之后开始调用，每隔1000毫秒或2000或3000毫秒调用一次，以此反复调用
	new Timer().schedule(new MyTimerTask(), 1000);

    }

    static int k = 3;

    static class MyTimerTask extends TimerTask {
	static int c = 0;

	@Override
	public void run() {
	    c = (c + 1) % k;
	    System.out.println(Thread.currentThread().getName()
		    + "====MyTimerTask====" + DateUtil.getYMDHMST());

	    new Timer().schedule(new MyTimerTask(), 1000 + (1000 * c));
	}

    }
}
