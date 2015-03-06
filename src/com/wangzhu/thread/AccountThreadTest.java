package com.wangzhu.thread;

public class AccountThreadTest {

    private static int NUM_OF_THREAD = 1000;
    static Thread[] threads = new Thread[NUM_OF_THREAD];

    public static void main(String[] args) {

	final Account acc = new Account("qingyezhu", 1000.0f);

	for (int i = 0; i < NUM_OF_THREAD; i++) {
	    threads[i] = new Thread(new Runnable() {

		@Override
		public void run() {
		    acc.deposit(100.0f);
		    acc.withdraw(100.0f);
		}
	    });
	    threads[i].start();
	}

	for (int i = 0; i < NUM_OF_THREAD; i++) {
	    try {
		threads[i].join();// 等待所有线程运行结束
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("finally: " + acc.getBalance());
    }

}

class Account {
    String name;
    float amount;

    public Account(String name, float amount) {
	this.name = name;
	this.amount = amount;
    }

    public synchronized void deposit(float amt) {
	float tmp = amount;

	tmp += amt;
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	amount = tmp;
    }

    public synchronized void withdraw(float amt) {
	float tmp = amount;

	tmp -= amt;

	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	amount = tmp;
    }

    public float getBalance() {
	return amount;
    }
}