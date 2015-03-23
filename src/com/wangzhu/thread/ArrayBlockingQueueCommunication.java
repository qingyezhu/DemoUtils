package com.wangzhu.thread;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

/**
 * ArrayBlockingQueue(阻塞队列)<br/>
 * 　　 put(anObject):<br/>
 * 把anObject加到BlockingQueue里,如果BlockQueue没有空间,<br/>
 * 则调用此方法的线程被阻断,直到BlockingQueue里面有空间再继续.<br/>
 * 
 * 　 take():<br/>
 * 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,<br/>
 * 阻断进入等待状态直到,BlockingQueue有新的数据被加入;<br/>
 * 
 * @author wangzhu
 * @date 2015-3-23上午9:31:22
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
	// 阻塞队列
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
