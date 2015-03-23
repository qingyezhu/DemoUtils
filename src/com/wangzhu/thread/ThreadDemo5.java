package com.wangzhu.thread;

import org.apache.log4j.Logger;

/**
 * �����߳�ִ�еĴ���Ƭ��Ҫʵ��ͬ�������Ч�������Ǳ�����ͬһ��Lock����<br/>
 * �������ڴ���Ҫ��������Դ������ڲ������У��������̴߳����С�<br/>
 * 
 * @author wangzhu
 * @date 2015-3-21����7:39:34
 * 
 */
public class ThreadDemo5 {

    private static final Logger logger = Logger.getLogger(ThreadDemo5.class);

    public static void main(String[] args) {

	final ThreadDemo5 demo = new ThreadDemo5();

	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    demo.init1(i);
		}
	    };
	}.start();
	for (int i = 0; i < 50; i++) {
	    demo.init(i);
	}
    }

    private boolean flag;

    public synchronized void init(int k) {
	try {
	    while (flag) {
		this.wait();
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	for (int i = 0; i < 10; i++) {
	    logger.info(k + "===" + Thread.currentThread().getName() + "==" + i);
	}
	flag = true;
	this.notify();
    }

    public synchronized void init1(int k) {
	try {
	    while (!flag) {
		this.wait();
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	for (int i = 0; i < 100; i++) {
	    logger.info(k + "===" + Thread.currentThread().getName() + "==" + i);
	}
	flag = false;
	this.notify();
    }
}
