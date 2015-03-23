package com.wangzhu.thread;

import java.util.Timer;
import java.util.TimerTask;

import com.wangzhu.dateutil.DateUtil;

/**
 * ��ʱ��
 * 
 * @author wangzhu
 * @date 2015-3-21����3:03:38
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
	}, 2000, 3000);// ��2000����֮��ʼ���ã���֮��ÿ��3000�������һ��
	int c = 0;
	while (true) {
	    if (c == 16) {
		timer1.cancel();// ȡ������
		break;
	    }
	    System.out.println(DateUtil.getYMDHMST());
	    try {
		c++;
		Thread.sleep(1000);// ��Ϣ1000���룬��1��
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}

	// ��1000����֮��ʼ���ã�ÿ��1000�����2000��3000�������һ�Σ��Դ˷�������
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
