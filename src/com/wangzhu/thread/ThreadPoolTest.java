package com.wangzhu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳���Executors��<br/>
 * 
 * @author wangzhu
 * @date 2015-3-22����2:50:28
 * 
 */
public class ThreadPoolTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// �̶���С���̳߳أ� ÿ�����ֻ�������߳̽�������
	ExecutorService threadPool = Executors.newFixedThreadPool(3);
	// ��һ�̣߳�ֻ��һ������̣߳������߳�����ʱ����������һ�����ŵ��߳����滻
	// Executors.newSingleThreadExecutor();
	// �����̳߳أ���������Ķ��٣��������ٸ��̣߳���û�л������ʱ�������߳�
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
	// ��û������ʱ���̳߳عر�
	threadPool.shutdown();

	// 10���ִ��
	Executors.newScheduledThreadPool(3).schedule(new Runnable() {

	    @Override
	    public void run() {
		System.out.println("schedule");
	    }
	}, 10, TimeUnit.SECONDS);

	// 5���ִ�У�֮��ÿ��2��ִ��һ��
	Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

	    @Override
	    public void run() {
		System.out.println("schedule at rate!");
	    }
	}, 5, 2, TimeUnit.SECONDS);
    }
}
