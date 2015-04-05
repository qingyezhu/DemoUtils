package com.wangzhu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * void shutdown()：启动一次顺序关闭，执行以前提交的任务，但不接收新任务。如果已经关闭，则调用没有其他作用。<br/>
 * boolean isTerminated()：如果关闭所有任务都已完成，则返回true。注意，除非首先调用shutdown或shutdownNow，
 * 否则isTerminated永不为true。<br/>
 * 
 * @author wangzhu
 * @date 2015-3-31上午9:06:00
 * 
 */
public class ExecutorsDemo {

    private static final Logger logger = Logger.getLogger(ExecutorsDemo.class);

    public static void main(String[] args) throws InterruptedException {
	ExecutorService service = Executors.newFixedThreadPool(10);
	for (int i = 0; i < 5; i++) {
	    service.execute(new MyThread(i));
	}
	logger.info("已经开启所有子线程");
	service.shutdown();
	logger.info("shutdown()：启动一次顺序关闭，执行以前提交的任务，但不接受新任务");
	while (true) {
	    if (service.isTerminated()) {
		logger.info("所有子线程执行结束");
		break;
	    }
	    Thread.sleep(200);
	}
    }

    static class MyThread extends Thread {
	private int threadNum;

	public MyThread(int threadNum) {
	    this.threadNum = threadNum;
	}

	@Override
	public void run() {
	    try {
		logger.info("子线程[" + threadNum + "]开启");
		Thread.sleep(1000 * 10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    } finally {
		logger.info("子线程[" + threadNum + "]结束");
	    }
	}
    }
}
