package com.wangzhu.thread;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

/**
 * CountDownLatch����һ��ͬ��������������ʱ����int�������ò������Ǽ������ĳ�ʼֵ��<br/>
 * ÿ����һ��countDown��������������1������������0ʱ��await�����������������ִ�С�<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5����7:49:07
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
	    latch.await();// �ȴ����й������
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
		Thread.sleep(3000);// ģ�⹤��
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    logger.info("Worker " + workerName + " work end");
	    latch.countDown();// ������ɣ���������һ
	}
    }
}
