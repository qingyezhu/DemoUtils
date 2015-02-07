package com.wangzhu.thread;

import java.util.Date;

public class SleepTest {
	public static void main(String[] args) {
		SleepDemo sd = new SleepDemo();

		// new MyThread1(sd).start();
		// new MyThread2(sd).start();

		new MyThread3(sd).start();
		new MyThread4(sd).start();

		// new MyThread5(sd).start();
		// new MyThread6(sd).start();

	}

}

class SleepDemo {
	public synchronized void wantSleep() {
		try {
			Thread.sleep(1000 * 6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//  Õ∑≈∂‘œÛÀ¯
		// try {
		// this.wait(1000 * 6);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		System.out.println("wantSleep");
	}

	public synchronized void say() {
		System.out.println("say");
	}

	public void this1() {
		System.out.println("this1===" + new Date());
		synchronized (this) {
			System.out.println("this1=start==" + new Date());
			try {
				Thread.sleep(1000 * 6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("this1");
			System.out.println("this1==end=" + new Date());
		}
	}

	public void this2() {
		System.out.println("this2===" + new Date());
		synchronized (this) {
			System.out.println("this2=start==" + new Date());
			System.out.println("this2");
			System.out.println("this2==end=" + new Date());
		}
	}

	// –Èƒ‚À¯
	String vLock1 = "vLock1";
	String vLock2 = "vLock2";

	public void m1() {
		synchronized (vLock1) {
			try {
				Thread.sleep(1000 * 6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m1");
		}
	}

	public void m2() {
		synchronized (vLock2) {
			System.out.println("m2");
		}
	}

}

class MyThread1 extends Thread {
	SleepDemo sd;

	public MyThread1(SleepDemo sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		sd.wantSleep();
	}
}

class MyThread2 extends Thread {
	SleepDemo sd;

	public MyThread2(SleepDemo sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		sd.say();
	}
}

class MyThread3 extends Thread {
	SleepDemo sd;

	public MyThread3(SleepDemo sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		sd.this1();
	}
}

class MyThread4 extends Thread {
	SleepDemo sd;

	public MyThread4(SleepDemo sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		sd.this2();
	}
}

class MyThread5 extends Thread {
	SleepDemo sd;

	public MyThread5(SleepDemo sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		sd.m1();
	}
}

class MyThread6 extends Thread {
	SleepDemo sd;

	public MyThread6(SleepDemo sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		sd.m2();
	}
}