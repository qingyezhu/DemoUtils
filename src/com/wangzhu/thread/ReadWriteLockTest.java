package com.wangzhu.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	final MyQueue queue = new MyQueue();

	for (int i = 0; i < 3; i++) {
	    new Thread() {
		@Override
		public void run() {
		    while (true) {
			queue.get();
		    }
		};
	    }.start();
	    new Thread() {
		@Override
		public void run() {
		    while (true) {
			queue.put(new Random().nextInt(10000));
		    }
		};
	    }.start();
	}

    }

}

class MyQueue {
    private Object data = null;

    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {
	rwl.readLock().lock();
	try {
	    System.out.println(Thread.currentThread().getName()
		    + " start reader data");
	    Thread.sleep((long) (Math.random() * 1000));
	    System.out.println(Thread.currentThread().getName()
		    + "end get reader: " + data);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	    rwl.readLock().unlock();
	}
    }

    public void put(Object data) {
	rwl.writeLock().lock();
	try {
	    System.out.println(Thread.currentThread().getName()
		    + " start writer data");
	    Thread.sleep((long) (Math.random() * 1000));
	    this.data = data;
	    System.out.println(Thread.currentThread().getName()
		    + "end writer data: " + data);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	    rwl.writeLock().unlock();
	}

    }

}