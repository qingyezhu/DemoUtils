package com.wangzhu.thread;

/**
 * �������̵߳������߳����ڽ��̻����ϵĻ��֣������������ڲ���ϵͳ�ϵĻ��֡�<br/>
 * ʹ�ö��߳̿��Է�����ͬһ��ʱ��������еĳ����ǽ�����еġ�<br/>
 * ���߳�ʵ�ַ�ʽ��ʵ��Runnable�ӿڣ��̳�Thread�ࡣ<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1����4:48:00
 * 
 */
public class ThreadDemo3 {
	public static void main(String[] args) {
		Thread3 thread = new Thread3();
		thread.setName("test thread");
		ThreadDemo3.printMsg("before start", thread.isAlive());

		thread.start();
		ThreadDemo3.printMsg("just after", thread.isAlive());

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread.printMsg("ThreadDemo3");
		}
		ThreadDemo3.printMsg("buding", thread.isAlive());
	}

	static void printMsg(String prefix, boolean suffix) {
		StringBuilder accum = new StringBuilder();
		accum.append(prefix);
		accum.append("====");
		accum.append(suffix);
		System.out.println(accum.toString());
	}
}

class Thread3 extends Thread {

	public Thread3() {
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			this.printMsg("Thread3");
		}
	}

	public void printMsg(String str) {

		Thread thread = Thread.currentThread();
		String name = thread.getName();
		StringBuilder accum = new StringBuilder();
		accum.append(str);
		accum.append(name);
		System.out.println(accum.toString());
	}

}