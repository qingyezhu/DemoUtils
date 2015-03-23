package com.wangzhu.thread;

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
 * @date 2015-3-21����8:00:57
 * 
 */
public class ThreadCommunication {
    public static void main(String[] args) {
	final Bussiness bussiness = new Bussiness();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.main(i);
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 50; i++) {
		    bussiness.sub(i);
		}
	    };
	}.start();

    }

}

class Bussiness {
    private static final Logger logger = Logger.getLogger(Bussiness.class);

    /**
     * true:���߳�<br/>
     * false:���߳�<br/>
     * Ĭ��ִ�����߳�<br/>
     */
    private boolean mark = false;

    public synchronized void main(int c) {
	while (mark) {
	    // ��ʾ��ǰ�����̣߳������ȴ�״̬
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	// ���������̣߳���ʼִ��
	for (int i = 0; i < 10; i++) {
	    logger.info(c + "==main thread====" + i);
	}
	// ��ʾ��ǰ�����߳�
	mark = true;
	// �������е��߳�
	this.notifyAll();
    }

    public synchronized void sub(int c) {
	while (!mark) {
	    // ��ʾ��ǰ�����̣߳������ȴ�״̬
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	// ���������̣߳���ʼִ��
	for (int i = 0; i < 100; i++) {
	    logger.info(c + "==sub thread====" + i);
	}
	// ��ʾ��ǰ�����߳�
	mark = false;
	// �������е��߳�
	this.notifyAll();
    }

}