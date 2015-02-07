package com.wangzhu.thread;

public class ThreadDemo {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread1().start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		R1 r1 = new R1();
		for (int i = 0; i < 10; i++) {
			// 统一实例的多个线程
			new Thread(r1).start();
		}
	}

}

class Thread1 extends Thread {
	private int x = 0;

	@Override
	public void run() {
		System.out.println("Thread1: " + (++x));
	}
}

class R1 implements Runnable {
	private int x = 0;

	@Override
	public void run() {
		System.out.println("R1: " + (++x));
	}
}