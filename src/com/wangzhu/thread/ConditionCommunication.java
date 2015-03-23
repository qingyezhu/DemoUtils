package com.wangzhu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
 * @date 2015-3-22下午9:50:11
 * 
 */
public class ConditionCommunication {
    private static final Logger logger = Logger
	    .getLogger(ConditionCommunication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	final Bussiness bussiness = new Bussiness();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.sub(i);
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.main(i);
		}
	    };
	}.start();
    }

    static class Bussiness {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	/**
	 * true:主线程<br/>
	 * false:子线程<br/>
	 */
	private boolean mark;

	public void main(int k) {
	    // 加锁
	    lock.lock();
	    try {
		while (mark) {
		    // 当前是主线程，则等待
		    try {
			condition.await();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		for (int i = 0; i < 10; i++) {
		    logger.info(k + "==main thread====" + i);
		}
		// 标记当前执行的是主线程
		mark = true;
		// 发出信号
		condition.signal();
	    } finally {
		// 释放锁
		lock.unlock();
	    }
	}

	public void sub(int k) {
	    // 加锁
	    lock.lock();
	    try {
		while (!mark) {
		    try {
			condition.await();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		for (int i = 0; i < 100; i++) {
		    logger.info(k + "==sub thread====" + i);
		}
		// 标记当前执行的是子线程
		mark = false;
		// 发出信号
		condition.signal();
	    } finally {
		// 释放锁
		lock.unlock();
	    }
	}
    }
}
