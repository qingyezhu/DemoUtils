package com.wangzhu.thread;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * ThreadLocalΪÿ��ʹ�øñ������߳��ṩ�����ı���������<br/>
 * ����ÿһ���̶߳����Զ����ظı��Լ��ĸ�����������Ӱ�������߳�����Ӧ�ĸ�����<br/>
 * 
 * 
 * ʹ��synchronized�Ļ�����ʾ��ǰֻ��1���̲߳��ܷ��ʷ����������̶߳��ᱻ������<br/>
 * �����ʵ��߳�Ҳ������ʱ���������з��ʸ÷������߳�ȫ��������������������൱�ء���ʱ����<br/>
 * ʹ��ThreadLocal�Ļ�����ʾÿ���̵߳ı��ر����ж���������ʵ�������ã�Ҳ���Ǹ����߳�֮����ȫû�й�ϵ��Ҳ�Ͳ�����ͬ�������ˡ�<br/>
 * 
 * ����������ʹ��synchronized��һ�֡���ʱ�任�ռ䡱�ĸ����ʹ��ThreadLocal���ǡ��Կռ任ʱ�䡱�ĸ��<br/>
 * 
 * @author wangzhu
 * @date 2015-3-31����10:26:52
 * 
 */
public class ThreadLocalDemo1 {
    private static final Logger logger = Logger
	    .getLogger(ThreadLocalDemo1.class);

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {

	@Override
	protected Integer initialValue() {
	    return 0;
	}
    };

    public int getNextNum() {
	seqNum.set(seqNum.get() + 1);
	return seqNum.get();
    }

    public static void main(String[] args) {
	ThreadLocalDemo1 demo = new ThreadLocalDemo1();

	MyThread thread = null;
	for (int i = 0; i < 3; i++) {
	    thread = new MyThread(demo);
	    thread.start();
	}
    }

    static class MyThread extends Thread {
	private ThreadLocalDemo1 demo;

	public MyThread(ThreadLocalDemo1 demo) {
	    this.demo = demo;
	}

	@Override
	public void run() {
	    for (int i = 0; i < 3; i++) {
		logger.info("thread[" + Thread.currentThread().getName()
			+ "],seqNum[" + demo.getNextNum() + "]");
		try {
		    Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

}
