package com.wangzhu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

/**
 * 计数信号量<br/>
 * 从概念上讲，信号量维护了一个许可集合。如有必要，在许可可用前会阻塞每一个acquire，<br/>
 * 然后再获取该许可。 每个release添加一个许可，从而可能释放一个正在阻塞的获取者。<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5下午8:12:59
 * 
 */
public class SemaphoreDemo {
    private static final Logger logger = Logger.getLogger(SemaphoreDemo.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	// 线程池
	ExecutorService exec = Executors.newCachedThreadPool();
	// 只能5个线程同时访问
	final Semaphore semaphore = new Semaphore(5);
	// 模拟50个客户端访问
	for (int i = 0; i < 50; i++) {
	    final int index = i;
	    Runnable run = new Runnable() {

		@Override
		public void run() {
		    try {
			semaphore.acquire();
			logger.info("Accessing: " + index);
			Thread.sleep(10000);
			semaphore.release();
			// availablePermits指的是当前信号灯库中有多少个可以被使用
			logger.info("============"
				+ semaphore.availablePermits());
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    };
	    exec.execute(run);
	}
	// 退出线程池
	exec.shutdown();
    }
}
