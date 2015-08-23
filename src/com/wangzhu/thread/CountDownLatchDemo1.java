package com.wangzhu.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����������������Ϊ�ϰ�ɻ����ϰ��и�ϰ�ߣ����ǵ��������˰�һ��Ļ�����˵�ʱ��<br/>
 * ������������й������ɵĻע�⣺�ǹ������ȸ����ϰ�ſ�ʼ��顣<br/>
 * 
 * @author wangzhu
 * @date 2015-5-12����12:12:16
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
	    logger.info("{},������ˣ�", name);
	    downLatch.countDown();
	}

	public void doWork() {
	    logger.info("{},���ڸɻ", name);
	}
    }

    static class Boss implements Runnable {

	private CountDownLatch downLatch;

	public Boss(CountDownLatch downLatch) {
	    this.downLatch = downLatch;
	}

	@Override
	public void run() {
	    logger.info("�ϰ����ڵ����еĹ��˸�������������");
	    try {
		downLatch.await();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info("�����ǻ�����ˣ��ϰ忪ʼ����ˣ�");
	}

    }

    public static void main(String[] args) {
	ExecutorService executor = Executors.newCachedThreadPool();
	CountDownLatch downLatch = new CountDownLatch(3);
	Worker w1 = new Worker(downLatch, "����");
	Worker w2 = new Worker(downLatch, "����");
	Worker w3 = new Worker(downLatch, "����");

	Boss boss = new Boss(downLatch);

	executor.execute(w1);
	executor.execute(w2);
	executor.execute(w3);
	executor.execute(boss);

	executor.shutdown();

    }
}
