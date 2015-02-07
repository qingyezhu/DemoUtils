package com.wangzhu.thread;

public class ThreadDemo4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyRunnable4 run4 = new MyRunnable4();
		Thread thread = null;
		ThreadDemo4.printMsg("before start", false);
		for (int i = 0; i < 4; i++) {
			thread = new Thread(run4);
			thread.setName("test thread" + i);
			thread.start();
		}
		ThreadDemo4.printMsg("just after", thread.isAlive());

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			run4.printMsg("ThreadDemo4");
		}
		ThreadDemo4.printMsg("buding", thread.isAlive());

	}

	static void printMsg(String prefix, boolean suffix) {
		StringBuilder accum = new StringBuilder();
		accum.append(prefix);
		accum.append("====");
		accum.append(suffix);
		System.out.println(accum.toString());
	}
}

class MyRunnable4 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			this.printMsg("MyRunnable4");
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