package com.wangzhu.thread;

import org.apache.log4j.Logger;

/**
 * 两个线程执行的代码片段要实现同步互斥的效果，它们必须用同一个Lock对象。<br/>
 * 锁是上在代表要操作的资源的类的内部方法中，而不是线程代码中。<br/>
 * 
 * 样例：<br/>
 * 1）、主线程执行10次循环，接着子线程执行100此循环；<br/>
 * 2）、重复1）操作50次，结束。<br/>
 * 
 * @author wangzhu
 * @date 2015-3-21下午8:00:57
 * 
 */
public class ThreadCommunication {
    public static void main(String[] args) {
	final Bussiness bussiness = new Bussiness();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.main(i);
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.sub(i);
		}
	    };
	}.start();

    }

}

class Bussiness {
    private static final Logger logger = Logger.getLogger(Bussiness.class);

    /**
     * true:主线程<br/>
     * false:子线程<br/>
     * 默认执行主线程<br/>
     */
    private boolean mark = false;

    public synchronized void main(int c) {
	while (mark) {
	    // 表示当前是主线程，则进入等待状态
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	// 当不是主线程，则开始执行
	for (int i = 0; i < 10; i++) {
	    logger.info(c + "==main thread====" + i);
	}
	// 表示当前是主线程
	mark = true;
	// 唤醒所有的线程
	this.notifyAll();
    }

    public synchronized void sub(int c) {
	while (!mark) {
	    // 表示当前是子线程，则进入等待状态
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	// 当不是子线程，则开始执行
	for (int i = 0; i < 100; i++) {
	    logger.info(c + "==sub thread====" + i);
	}
	// 表示当前是子线程
	mark = false;
	// 唤醒所有的线程
	this.notifyAll();
    }

}