package com.wangzhu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * �����߳�ִ�еĴ���Ƭ��Ҫʵ��ͬ�������Ч�������Ǳ�����ͬһ��Lock����<br/>
 * �������ڴ���Ҫ��������Դ������ڲ������У��������̴߳����С�<br/>
 * 
 * ������<br/>
 * 1�������߳�ִ��10��ѭ�����������߳�ִ��100��ѭ����<br/>
 * 2�����ظ�1������50�Σ�������<br/>
 * 
 * @author wangzhu
 * @date 2015-3-22����9:50:11
 * 
 */
public class ConditionCommunication {
    private static final Logger logger = Logger
	    .getLogger(ConditionCommunication.class);

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
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	/**
	 * true:���߳�<br/>
	 * false:���߳�<br/>
	 */
	private boolean mark;

	public void main(int k) {
	    // ����
	    lock.lock();
	    try {
		while (mark) {
		    // ��ǰ�����̣߳���ȴ�
		    try {
			condition.await();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		for (int i = 0; i < 10; i++) {
		    logger.info(k + "==main thread====" + i);
		}
		// ��ǵ�ǰִ�е������߳�
		mark = true;
		// �����ź�
		condition.signal();
	    } finally {
		// �ͷ���
		lock.unlock();
	    }
	}

	public void sub(int k) {
	    // ����
	    lock.lock();
	    try {
		while (!mark) {
		    try {
			condition.await();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		for (int i = 0; i < 100; i++) {
		    logger.info(k + "==sub thread====" + i);
		}
		// ��ǵ�ǰִ�е������߳�
		mark = false;
		// �����ź�
		condition.signal();
	    } finally {
		// �ͷ���
		lock.unlock();
	    }
	}
    }
}
