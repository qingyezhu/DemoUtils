package com.wangzhu.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 场景：有三个工人为老板干活，这个老板有个习惯，就是当三个工人把一天的活干完了的时候，<br/>
 * 他才来检查所有工人所干的活。注意：是工人们先干完活，老板才开始检查。<br/>
 * 
 * @author wangzhu
 * @date 2015-5-12上午12:12:16
 * 
 */
public class CountDownLatchDemo1 {
    private static final Logger logger = LoggerFactory
	    .getLogger(CountDownLatchDemo1.class);

    static class Worker implements Runnable {
	private CountDownLatch downLatch;
	private String name;

	public Worker(CountDownLatch downLatch, String name) {
	    this.downLatch = downLatch;
	    this.name = name;
	}

	@Override
	public void run() {
	    this.doWork();
	    try {
		TimeUnit.SECONDS.sleep(new Random().nextInt(10));
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info("{},活干完了！", name);
	    downLatch.countDown();
	}

	public void doWork() {
	    logger.info("{},正在干活！", name);
	}
    }

    static class Boss implements Runnable {

	private CountDownLatch downLatch;

	public Boss(CountDownLatch downLatch) {
	    this.downLatch = downLatch;
	}

	@Override
	public void run() {
	    logger.info("老板正在等所有的工人干完活。。。。。。");
	    try {
		downLatch.await();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info("工人们活都干完了，老板开始检查了！");
	}

    }

    public static void main(String[] args) {
	ExecutorService executor = Executors.newCachedThreadPool();
	CountDownLatch downLatch = new CountDownLatch(3);
	Worker w1 = new Worker(downLatch, "张三");
	Worker w2 = new Worker(downLatch, "李四");
	Worker w3 = new Worker(downLatch, "王五");

	Boss boss = new Boss(downLatch);

	executor.execute(w1);
	executor.execute(w2);
	executor.execute(w3);
	executor.execute(boss);

	executor.shutdown();

    }
}
