package com.wangzhu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * void shutdown()������һ��˳��رգ�ִ����ǰ�ύ�����񣬵�����������������Ѿ��رգ������û���������á�<br/>
 * boolean isTerminated()������ر�������������ɣ��򷵻�true��ע�⣬�������ȵ���shutdown��shutdownNow��
 * ����isTerminated����Ϊtrue��<br/>
 * 
 * @author wangzhu
 * @date 2015-3-31����9:06:00
 * 
 */
public class ExecutorsDemo {

    private static final Logger logger = Logger.getLogger(ExecutorsDemo.class);

    public static void main(String[] args) throws InterruptedException {
	ExecutorService service = Executors.newFixedThreadPool(10);
	for (int i = 0; i < 5; i++) {
	    service.execute(new MyThread(i));
	}
	logger.info("�Ѿ������������߳�");
	service.shutdown();
	logger.info("shutdown()������һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������");
	while (true) {
	    if (service.isTerminated()) {
		logger.info("�������߳�ִ�н���");
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
		logger.info("���߳�[" + threadNum + "]����");
		Thread.sleep(1000 * 10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    } finally {
		logger.info("���߳�[" + threadNum + "]����");
	    }
	}
    }
}
