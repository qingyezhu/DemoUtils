package com.wangzhu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池与Executors类<br/>
 * 
 * @author wangzhu
 * @date 2015-3-22下午2:50:28
 * 
 */
public class ThreadPoolTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// 固定大小的线程池： 每次最多只有三个线程交叉运行
	ExecutorService threadPool = Executors.newFixedThreadPool(3);
	// 单一线程：只有一个活动的线程，当此线程死了时，将会有另一个活着的线程来替换
	// Executors.newSingleThreadExecutor();
	// 缓存线程池：根据任务的多少，创建多少个线程，当没有活动的任务时，销毁线程
	// Executors.newCachedThreadPool();

	for (int i = 0; i < 10; i++) {
	    final int task = i;
	    threadPool.execute(new Runnable() {

		@Override
		public void run() {
		    for (int i = 0; i < 30; i++) {
			try {
			    Thread.sleep(30);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			System.out
				.println(Thread.currentThread().getName()
					+ " current loop is " + i
					+ " for task " + task);
		    }
		}
	    });
	}
	// 当没有任务时，线程池关闭
	threadPool.shutdown();

	// 10秒后执行
	Executors.newScheduledThreadPool(3).schedule(new Runnable() {

	    @Override
	    public void run() {
		System.out.println("schedule");
	    }
	}, 10, TimeUnit.SECONDS);

	// 5秒后执行，之后每个2秒执行一次
	Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

	    @Override
	    public void run() {
		System.out.println("schedule at rate!");
	    }
	}, 5, 2, TimeUnit.SECONDS);
    }
}
