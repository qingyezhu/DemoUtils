package com.wangzhu.thread;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

/**
 * ArrayBlockingQueue(��������)<br/>
 * ���� put(anObject):<br/>
 * ��anObject�ӵ�BlockingQueue��,���BlockQueueû�пռ�,<br/>
 * ����ô˷������̱߳����,ֱ��BlockingQueue�����пռ��ټ���.<br/>
 * 
 * �� take():<br/>
 * ȡ��BlockingQueue��������λ�Ķ���,��BlockingQueueΪ��,<br/>
 * ��Ͻ���ȴ�״ֱ̬��,BlockingQueue���µ����ݱ�����;<br/>
 * 
 * @author wangzhu
 * @date 2015-3-23����9:31:22
 * 
 */
public class ArrayBlockingQueueCommunication {
    private static final Logger logger = Logger
	    .getLogger(ArrayBlockingQueueCommunication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	final Bussiness bussiness = new Bussiness();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.sub(i);
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.main(i);
		}
	    };
	}.start();
    }

    static class Bussiness {
	// ��������
	ArrayBlockingQueue<Integer> mainQuery = new ArrayBlockingQueue<Integer>(
		1);
	ArrayBlockingQueue<Integer> subQuery = new ArrayBlockingQueue<Integer>(
		1);
	{
	    try {
		subQuery.put(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	public void main(int k) {
	    try {
		mainQuery.put(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < 10; i++) {
		logger.info(k + "==main thread====" + i);
	    }
	    try {
		subQuery.take();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	public void sub(int k) {
	    try {
		subQuery.put(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < 100; i++) {
		logger.info(k + "==sub thread====" + i);
	    }
	    try {
		mainQuery.take();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
