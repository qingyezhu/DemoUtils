package com.wangzhu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Synchronized是Lock的一种简化实现，一个Lock可以对应多个Condition，
 * 而Synchronized把Lock和Condition合并了，<br/>
 * 一个Synchronized Lock只对应一个Condition，可以说Synchronized是Lock的简化版本。<br/>
 * 在JDK5，Synchronized要比Lock慢很多，但在JDK6中，他们的效率差不多。<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5下午9:47:40
 * 
 */
public class LockTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

	final LockOutputer outputer = new LockOutputer();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(20);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    outputer.output("welcome");
		}
	    }
	}).start();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(10);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    outputer.output("communication");
		}
	    }
	}).start();

    }

    static class LockOutputer {

	Lock lock = new ReentrantLock();

	public void output(String str) {
	    lock.lock();
	    try {
		for (int i = 0, len = str.length(); i < len; i++) {
		    System.out.print(str.charAt(i));
		}
		System.out.println();
	    } finally {
		lock.unlock();
	    }
	}
    }
}
