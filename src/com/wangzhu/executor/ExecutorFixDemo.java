package com.wangzhu.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorFixDemo {

    public static void main(String[] args) {
	final ExecutorService service = Executors.newFixedThreadPool(5);

	for (int i = 0; i < 10; i++) {
	    service.execute(new FixThread(i));
	}
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    ThreadPoolExecutor pool = (ThreadPoolExecutor) service;
		    int count = pool.getActiveCount();
		    System.out.println(count + " size=" + pool.getPoolSize()
			    + " core=" + pool.getCorePoolSize() + " max="
			    + pool.getMaximumPoolSize());
		    if (count == 0) {
			break;
		    }

		}

	    };
	}.start();

	service.shutdown();
    }

    static class FixThread extends Thread {
	private int index;

	public FixThread(int index) {
	    this.index = index;
	}

	@Override
	public void run() {
	    try {
		Thread.sleep(30);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println(Thread.currentThread());
	    if (index == 3) {
		System.out.println((index / index) - 3);
	    }
	    try {
		Thread.sleep(20);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
