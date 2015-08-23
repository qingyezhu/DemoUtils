package com.wangzhu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Synchronized��Lock��һ�ּ�ʵ�֣�һ��Lock���Զ�Ӧ���Condition��
 * ��Synchronized��Lock��Condition�ϲ��ˣ�<br/>
 * һ��Synchronized Lockֻ��Ӧһ��Condition������˵Synchronized��Lock�ļ򻯰汾��<br/>
 * ��JDK5��SynchronizedҪ��Lock���ܶ࣬����JDK6�У����ǵ�Ч�ʲ�ࡣ<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5����9:47:40
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
