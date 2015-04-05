package com.wangzhu.thread;

import org.apache.log4j.Logger;

class TicketWindow implements Runnable {
    private static final Logger logger = Logger.getLogger(TicketWindow.class);
    private int max_value = 0;

    public int getMax_value() {
	return max_value;
    }

    @Override
    public void run() {
	while (true) {
	    // 当run方法加了synchronized时，就不会进行到这里了
	    if (this.ticket()) {
		break;
	    }
	}
	logger.info("threadName: " + Thread.currentThread().getName());
    }

    private synchronized boolean ticket() {
	if (max_value > 500) {
	    return true;
	}
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	logger.info(Thread.currentThread().getName() + ":" + max_value++);
	return false;
    }
}

public class TicketWindowTest {
    public static void main(String[] args) {
	final TicketWindow tw = new TicketWindow();
	Thread thread1 = new Thread(tw);
	Thread thread2 = new Thread(tw);
	Thread thread3 = new Thread(tw);
	thread1.start();
	thread2.start();
	thread3.start();
    }
}
