package com.wangzhu.thread;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

/**
 * CountDownLatch类是一个同步计数器，构造时传入int参数，该参数就是计数器的初始值，<br/>
 * 每调用一次countDown方法，计数器减1，计数器大于0时，await方法会阻塞程序继续执行。<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5下午7:49:07
 * 
 */
public class CountDownLatchDemo {

    private static final Logger logger = Logger
	    .getLogger(CountDownLatchDemo.class);

    public static void main(String[] args) {
	CountDownLatch latch = new CountDownLatch(2);
	Worker worker1 = new Worker("111", latch);
	Worker worker2 = new Worker("222", latch);
	worker1.start();
	worker2.start();
	try {
	    latch.await();// 等待所有工作完成
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	logger.info("CountDownLatchDemo done.");
    }

    static class Worker extends Thread {

	String workerName;
	CountDownLatch latch;

	public Worker(String workerName, CountDownLatch latch) {
	    this.workerName = workerName;
	    this.latch = latch;
	}

	@Override
	public void run() {
	    logger.info("Worker " + workerName + " work start");
	    try {
		Thread.sleep(3000);// 模拟工作
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info("Worker " + workerName + " work end");
	    latch.countDown();// 工作完成，计数器减一
	}
    }
}
