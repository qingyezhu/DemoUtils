package com.wangzhu.thread;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * ThreadLocal为每个使用该变量的线程提供独立的变量副本，<br/>
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其他线程所对应的副本。<br/>
 * 
 * 
 * 使用synchronized的话，表示当前只有1个线程才能访问方法，其他线程都会被阻塞。<br/>
 * 当访问的线程也阻塞的时候，其他所有访问该方法的线程全部都会阻塞，这个方法相当地“耗时”。<br/>
 * 使用ThreadLocal的话，表示每个线程的本地变量中都有这个类的实例的引用，也就是各个线程之间完全没有关系，也就不存在同步问题了。<br/>
 * 
 * 综上所述，使用synchronized是一种“以时间换空间”的概念。而使用ThreadLocal则是“以空间换时间”的概念。<br/>
 * 
 * @author wangzhu
 * @date 2015-3-31上午10:26:52
 * 
 */
public class ThreadLocalDemo1 {
    private static final Logger logger = Logger
	    .getLogger(ThreadLocalDemo1.class);

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {

	@Override
	protected Integer initialValue() {
	    return 0;
	}
    };

    public int getNextNum() {
	seqNum.set(seqNum.get() + 1);
	return seqNum.get();
    }

    public static void main(String[] args) {
	ThreadLocalDemo1 demo = new ThreadLocalDemo1();

	MyThread thread = null;
	for (int i = 0; i < 3; i++) {
	    thread = new MyThread(demo);
	    thread.start();
	}
    }

    static class MyThread extends Thread {
	private ThreadLocalDemo1 demo;

	public MyThread(ThreadLocalDemo1 demo) {
	    this.demo = demo;
	}

	@Override
	public void run() {
	    for (int i = 0; i < 3; i++) {
		logger.info("thread[" + Thread.currentThread().getName()
			+ "],seqNum[" + demo.getNextNum() + "]");
		try {
		    Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

}
