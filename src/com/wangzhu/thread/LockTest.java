package com.wangzhu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
