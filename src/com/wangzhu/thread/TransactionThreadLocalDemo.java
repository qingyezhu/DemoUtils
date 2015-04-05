package com.wangzhu.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionThreadLocalDemo {

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException,
	    IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	int testCase = Integer.parseInt(br.readLine());
	br.close();
	switch (testCase) {
	case 1:
	    // 无线程池、线程不休眠、并且清除THREAD_LOCAL里面的线程变量
	    testWithThread(true, 0);
	    break;
	case 2:
	    // 无线程池、线程不休眠、不清除THREAD_LOCAL里面的线程变量
	    testWithThread(false, 0);
	    break;
	case 3:
	    // 无线程池、线程休眠1000毫秒、清除THREAD_LOCAL里面的线程变量
	    testWithThread(true, 1000);
	    break;
	case 4:
	    // 无线程池、线程永久休眠、并且清除THREAD_LOCAL里面的线程变量
	    testWithThread(true, Integer.MAX_VALUE);
	    break;
	case 5:
	    // 有线程池，线程池大小50，线程不休眠，并且清除THREAD_LOCAL里面的线程变量
	    testWithThreadPool(50, true, 0);
	    break;
	case 6:
	    // 有线程池，线程池大小50，线程不休眠，不清除THREAD_LOCAL里面的线程变量
	    testWithThreadPool(50, false, 0);
	    break;
	case 7:
	    testWithThreadPool(50, true, Integer.MAX_VALUE);
	    break;
	case 8:
	    testWithThreadPool(100, true, Integer.MAX_VALUE);
	    break;
	default:
	    break;
	}
    }

    public static void testWithThread(boolean clearThreadLocal, long sleepTime) {
	while (true) {
	    try {
		Thread.sleep(100);
		new Thread(new TestTask(clearThreadLocal, sleepTime)).start();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    public static void testWithThreadPool(int poolSize,
	    boolean clearThreadLocal, long sleepTime) {
	ExecutorService service = Executors.newFixedThreadPool(poolSize);
	while (true) {
	    try {
		Thread.sleep(100);
		service.execute(new TestTask(clearThreadLocal, sleepTime));
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    public static final byte[] allocateMem() {
	byte[] buf = new byte[1024 * 1024];
	return buf;
    }

    static class TestTask implements Runnable {

	private boolean clearThreadLocal;
	private long sleepTime;

	public TestTask(boolean clearThreadLocal, long sleepTime) {
	    this.clearThreadLocal = clearThreadLocal;
	    this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
	    try {
		ThreadLocalHolder.set(allocateMem());
		try {
		    if (sleepTime > 0) {
			Thread.sleep(sleepTime);
		    }
		} catch (InterruptedException e) {

		}
	    } finally {
		if (clearThreadLocal) {
		    ThreadLocalHolder.clear();
		}
	    }
	}
    }

}

class ThreadLocalHolder {
    public static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>();

    public static final void set(byte[] buf) {
	THREAD_LOCAL.set(buf);
    }

    public static final void clear() {
	THREAD_LOCAL.set(null);
    }
}