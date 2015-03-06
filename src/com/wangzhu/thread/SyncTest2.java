package com.wangzhu.thread;

public class SyncTest2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	final Fot fot = new Fot();

	new Thread() {
	    @Override
	    public void run() {
		Fot.methodA();
		fot.methodB();
	    };
	}.start();

	new Thread() {
	    @Override
	    public void run() {
		Fot.methodA();
		fot.methodB();
	    };
	}.start();

    }

}

class Fot {

    public synchronized static void methodA() {
	System.out.println("methodA===" + Thread.currentThread().getName());
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void methodB() {
	synchronized (Fot.class) {
	    System.out.println("methodB==" + Thread.currentThread().getName());
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
