package com.wangzhu.thread;

import org.apache.log4j.Logger;

/**
 * 同步的对象不一样！！！
 * 
 * @author wangzhu
 * @date 2015-4-5下午7:39:31
 * 
 */
public class SynchronizedTest1 {
    private static final Logger logger = Logger
	    .getLogger(SynchronizedTest1.class);

    private byte[] lock = new byte[0];

    public void output2() {
	synchronized (lock) {
	    logger.info("SynchronizedTest1 output2 start");
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info("SynchronizedTest1 output2 end");

	}
    }

    public synchronized void output1() {
	logger.info("SynchronizedTest1 output1 start");
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	logger.info("SynchronizedTest1 output1 end");

    }

    public static void main(String[] args) {
	final SynchronizedTest1 test = new SynchronizedTest1();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    test.output1();
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    test.output2();
		}
	    };
	}.start();

    }
}
