package com.wangzhu.thread;

/**
 * 同步与互斥
 * 
 * @author wangzhu
 * @date 2015-3-21下午4:37:35
 * 
 */
public class SynchronizedTest {

    static class Output {
	public void print(String str) {
	    synchronized (this) {// 若不加同步与互斥，则会出现数据结果丢失
		for (int i = 0, len = str.length(); i < len; i++) {
		    System.out.print(str.charAt(i));
		}
		System.out.println();
	    }
	}
    }

    public static void main(String[] args) {
	final Output output = new Output();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(10);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    output.print("qingyezhu");
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    try {
			Thread.sleep(10);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    output.print("welcome");
		}
	    };
	}.start();

    }
}
