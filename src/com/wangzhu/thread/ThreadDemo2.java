package com.wangzhu.thread;


public class ThreadDemo2 {

	public static void main(String[] args) {

		ThreadDemo2 test = new ThreadDemo2();
		ThreadAdd add = test.new ThreadAdd();
		ThreadReduce reduce = test.new ThreadReduce();
		Thread thread = null;
		for (int i = 0; i < 2; i++) {
			thread = new Thread(add, "add" + i);
			thread.start();
			thread = new Thread(reduce, "reduce" + i);
			thread.start();
		}
	}

	int i;

	public synchronized void add() {
		i++;
		System.out.println(Thread.currentThread().getName() + "===add==" + i);
	}

	public synchronized void reduce() {
		i--;
		System.out
				.println(Thread.currentThread().getName() + "===reduce==" + i);
	}

	public class ThreadAdd implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				ThreadDemo2.this.add();
			}
		}
	}

	public class ThreadReduce implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				ThreadDemo2.this.reduce();
			}
		};
	}

}
