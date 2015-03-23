package com.wangzhu.thread;

import org.apache.log4j.Logger;

/**
 * 两个线程执行的代码片段要实现同步互斥的效果，它们必须用同一个Lock对象。<br/>
 * 锁是上在代表要操作的资源的类的内部方法中，而不是线程代码中。<br/>
 * 
 * @author wangzhu
 * @date 2015-3-21下午7:39:34
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
