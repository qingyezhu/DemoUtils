package com.wangzhu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

/**
 * �����ź���<br/>
 * �Ӹ����Ͻ����ź���ά����һ����ɼ��ϡ����б�Ҫ������ɿ���ǰ������ÿһ��acquire��<br/>
 * Ȼ���ٻ�ȡ����ɡ� ÿ��release���һ����ɣ��Ӷ������ͷ�һ�����������Ļ�ȡ�ߡ�<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5����8:12:59
 * 
 */
public class SemaphoreDemo {
    private static final Logger logger = Logger.getLogger(SemaphoreDemo.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	// �̳߳�
	ExecutorService exec = Executors.newCachedThreadPool();
	// ֻ��5���߳�ͬʱ����
	final Semaphore semaphore = new Semaphore(5);
	// ģ��50���ͻ��˷���
	for (int i = 0; i < 50; i++) {
	    final int index = i;
	    Runnable run = new Runnable() {

		@Override
		public void run() {
		    try {
			semaphore.acquire();
			logger.info("Accessing: " + index);
			Thread.sleep(10000);
			semaphore.release();
			// availablePermitsָ���ǵ�ǰ�źŵƿ����ж��ٸ����Ա�ʹ��
			logger.info("============"
				+ semaphore.availablePermits());
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    };
	    exec.execute(run);
	}
	// �˳��̳߳�
	exec.shutdown();
    }
}
